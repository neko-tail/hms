package net.thmaster.hms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.exception.ResourceNotFoundException;
import net.thmaster.hms.model.entity.UserCustom;
import net.thmaster.hms.model.req.UserCustomInfoRequest;
import net.thmaster.hms.repository.UserCustomRepository;
import net.thmaster.hms.service.UserCustomService;
import org.springframework.stereotype.Service;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserCustomServiceImpl implements UserCustomService {

    private final UserCustomRepository userCustomRepository;

    @Override
    public UserCustom get(Long userId) {
        return userCustomRepository.getByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("用户自定义数据不存在"));
    }

    @Override
    public UserCustom update(Long userId, UserCustomInfoRequest info) {
        userCustomRepository.lambdaUpdate()
                .set(info.calorieLimit() != null, UserCustom::getCalorieLimit, info.calorieLimit())
                .set(info.initWeight() != null, UserCustom::getInitWeight, info.initWeight())
                .set(info.targetWeight() != null, UserCustom::getTargetWeight, info.targetWeight())
                .eq(UserCustom::getUserId, userId)
                .update();

        return get(userId);
    }

}
