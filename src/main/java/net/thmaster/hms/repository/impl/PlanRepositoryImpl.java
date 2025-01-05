package net.thmaster.hms.repository.impl;

import net.thmaster.hms.model.entity.Plan;
import net.thmaster.hms.mapper.PlanMapper;
import net.thmaster.hms.repository.PlanRepository;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 训练计划 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class PlanRepositoryImpl extends MPJBaseServiceImpl<PlanMapper, Plan> implements PlanRepository {

}
