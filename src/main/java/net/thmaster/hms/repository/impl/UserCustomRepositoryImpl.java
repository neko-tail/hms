package net.thmaster.hms.repository.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import net.thmaster.hms.mapper.UserCustomMapper;
import net.thmaster.hms.model.entity.UserCustom;
import net.thmaster.hms.repository.UserCustomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 用户自定义数据 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class UserCustomRepositoryImpl extends MPJBaseServiceImpl<UserCustomMapper, UserCustom>
        implements UserCustomRepository {

    @Override
    public Optional<UserCustom> getByUserId(Long userId) {
        return lambdaQuery().eq(UserCustom::getUserId, userId).list().stream().findFirst();
    }

}
