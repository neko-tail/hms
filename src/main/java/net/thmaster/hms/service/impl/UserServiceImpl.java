package net.thmaster.hms.service.impl;

import com.github.yulichang.toolkit.JoinWrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.exception.ResourceNotFoundException;
import net.thmaster.hms.model.dto.UserDTO;
import net.thmaster.hms.model.entity.User;
import net.thmaster.hms.model.entity.UserCustom;
import net.thmaster.hms.model.entity.UserInfo;
import net.thmaster.hms.model.req.RegisterRequest;
import net.thmaster.hms.model.req.UserInfoRequest;
import net.thmaster.hms.model.req.query.UserQueryRequest;
import net.thmaster.hms.repository.UserCustomRepository;
import net.thmaster.hms.repository.UserInfoRepository;
import net.thmaster.hms.repository.UserRepository;
import net.thmaster.hms.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserInfoRepository userInfoRepository;

    private final UserCustomRepository userCustomRepository;

    @Override
    public UserDTO register(RegisterRequest register) {
        boolean exists = userRepository.lambdaQuery()
                .eq(User::getUsername, register.username())
                .exists();
        if (exists) {
            throw new IllegalArgumentException("用户名已存在");
        }

        User user = new User();
        user.setUsername(register.username());
        user.setPassword(encrypt(register.password()));
        user.setEmail(register.email());
        user.setPhoneNumber(register.phoneNumber());
        userRepository.save(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNickName(register.username());
        userInfo.setGender(0);
        userInfo.setGenderName("未知");
        userInfoRepository.save(userInfo);

        UserCustom userCustom = new UserCustom();
        userCustom.setUserId(user.getId());
        userCustomRepository.save(userCustom);

        return get(user.getId());
    }

    @Override
    public UserDTO get(Long userId) {
        User user = userRepository.getOptById(userId).orElseThrow(() -> new ResourceNotFoundException("用户不存在"));
        UserDTO dto = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();

        userInfoRepository.getByUserId(user.getId())
                .ifPresent(dto::setInfo);

        return dto;
    }

    @Override
    public List<UserDTO> list(UserQueryRequest query) {
        return userRepository.selectJoinList(UserDTO.class,
                JoinWrappers.lambda(User.class)
                        .selectAll()
                        .selectAssociation(UserInfo.class, UserDTO::getInfo)
                        .leftJoin(UserInfo.class, UserInfo::getUserId, User::getId)
                        .func(query != null, w -> w
                                .like(query.username() != null, User::getUsername, query.username())
                                .like(query.nickname() != null, UserInfo::getNickName, query.nickname())
                                .like(query.email() != null, User::getEmail, query.email())
                                .like(query.phoneNumber() != null, User::getPhoneNumber, query.phoneNumber())
                        )
        );
    }

    @Override
    public UserDTO update(Long userId, UserInfoRequest info) {
        if (info == null) {
            return get(userId);
        }

        if (info.email() != null || info.phoneNumber() != null) {
            userRepository.lambdaUpdate()
                    .set(info.email() != null, User::getEmail, info.email())
                    .set(info.phoneNumber() != null, User::getPhoneNumber, info.phoneNumber())
                    .eq(User::getId, userId)
                    .update();
        }

        userInfoRepository.lambdaUpdate()
                .set(info.nickName() != null, UserInfo::getNickName, info.nickName())
                .set(info.age() != null, UserInfo::getAge, info.age())
                .set(info.gender() != null, UserInfo::getGender, info.gender())
                .set(info.genderName() != null, UserInfo::getGenderName, info.genderName())
                .set(info.height() != null, UserInfo::getHeight, info.height())
                .set(info.weight() != null, UserInfo::getWeight, info.weight())
                .eq(UserInfo::getUserId, userId)
                .update();

        return get(userId);
    }

    @Override
    public void delete(Long userId) {
        userRepository.removeById(userId);
        userInfoRepository.lambdaUpdate()
                .eq(UserInfo::getUserId, userId)
                .remove();
        userCustomRepository.lambdaUpdate()
                .eq(UserCustom::getUserId, userId)
                .remove();
    }

    private String encrypt(String pwd) {
        return DigestUtils.sha256Hex(pwd);
    }

}
