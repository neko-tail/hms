package net.thmaster.hms.repository;

import net.thmaster.hms.model.entity.UserCustom;
import com.github.yulichang.base.MPJBaseService;

import java.util.Optional;

/**
 * <p>
 * 用户自定义数据 Repository
 * </p>
 *
 * @author master
 */
public interface UserCustomRepository extends MPJBaseService<UserCustom> {

    Optional<UserCustom> getByUserId(Long userId);

}
