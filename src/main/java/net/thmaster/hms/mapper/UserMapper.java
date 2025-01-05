package net.thmaster.hms.mapper;

import net.thmaster.hms.model.entity.User;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author master
 */
@Mapper
public interface UserMapper extends MPJBaseMapper<User> {

}
