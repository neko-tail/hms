package net.thmaster.hms.repository;

import com.github.yulichang.base.MPJBaseService;
import net.thmaster.hms.model.entity.UserInfo;

import java.util.Optional;

/**
 * <p>
 * 用户信息 Repository
 * </p>
 *
 * @author master
 */
public interface UserInfoRepository extends MPJBaseService<UserInfo> {

    Optional<UserInfo> getByUserId(Long userId);

}
