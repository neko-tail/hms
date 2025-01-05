package net.thmaster.hms.repository.impl;

import net.thmaster.hms.model.entity.Movement;
import net.thmaster.hms.mapper.MovementMapper;
import net.thmaster.hms.repository.MovementRepository;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 运动 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class MovementRepositoryImpl extends MPJBaseServiceImpl<MovementMapper, Movement> implements MovementRepository {

}
