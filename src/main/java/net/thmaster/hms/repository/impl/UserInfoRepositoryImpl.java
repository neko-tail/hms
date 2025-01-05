package net.thmaster.hms.repository.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import net.thmaster.hms.mapper.UserInfoMapper;
import net.thmaster.hms.model.entity.UserInfo;
import net.thmaster.hms.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 用户信息 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class UserInfoRepositoryImpl extends MPJBaseServiceImpl<UserInfoMapper, UserInfo> implements UserInfoRepository {

    @Override
    public Optional<UserInfo> getByUserId(Long userId) {
        return lambdaQuery()
                .eq(UserInfo::getId, userId)
                .list()
                .stream().findFirst();
    }

}
