package net.thmaster.hms.repository.impl;

import net.thmaster.hms.model.entity.User;
import net.thmaster.hms.mapper.UserMapper;
import net.thmaster.hms.repository.UserRepository;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class UserRepositoryImpl extends MPJBaseServiceImpl<UserMapper, User> implements UserRepository {

}
