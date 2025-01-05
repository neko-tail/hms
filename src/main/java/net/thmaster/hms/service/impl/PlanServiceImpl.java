package net.thmaster.hms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.exception.ResourceNotFoundException;
import net.thmaster.hms.model.entity.Exercise;
import net.thmaster.hms.model.entity.Plan;
import net.thmaster.hms.model.req.PlanInfoRequest;
import net.thmaster.hms.model.req.query.PlanQueryRequest;
import net.thmaster.hms.repository.ExerciseRepository;
import net.thmaster.hms.repository.PlanRepository;
import net.thmaster.hms.service.PlanService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;

    private final ExerciseRepository exerciseRepository;

    @Override
    public Plan save(Long userId, PlanInfoRequest info) {
        Plan plan = new Plan();
        plan.setUserId(userId);
        plan.setName(info.name());
        plan.setDesc(info.desc());
        plan.setCreateTime(LocalDateTime.now());
        plan.setFin(false);

        planRepository.save(plan);

        return get(plan.getUserId(), plan.getId());
    }

    @Override
    public Plan get(Long userId, Long planId) {
        return planRepository.getOptById(planId)
                .filter(x -> x.getUserId().equals(userId))
                .orElseThrow(() -> new ResourceNotFoundException("计划不存在"));
    }

    @Override
    public List<Plan> list(Long userId, PlanQueryRequest query) {
        return planRepository.lambdaQuery()
                .func(query != null, w -> w
                        .like(query.name() != null, Plan::getName, query.name())
                        .like(query.desc() != null, Plan::getDesc, query.desc())
                        .func(w2 -> {
                            if (query.day() != null) {
                                w2.ge(query.startTime() != null,
                                        Plan::getCreateTime,
                                        query.day().atStartOfDay()
                                ).le(query.endTime() != null,
                                        Plan::getCreateTime,
                                        query.day().plusDays(1).atStartOfDay()
                                );
                            } else {
                                w2.ge(query.startTime() != null, Plan::getCreateTime, query.startTime())
                                        .le(query.endTime() != null, Plan::getCreateTime, query.endTime());
                            }
                        }).eq(query.fin() != null, Plan::isFin, query.fin())
                ).eq(Plan::getUserId, userId)
                .list();
    }

    @Override
    public Plan update(Long userId, Long planId, PlanInfoRequest info) {
        planRepository.lambdaUpdate()
                .func(info != null, w -> w
                        .set(info.name() != null, Plan::getName, info.name())
                        .set(info.desc() != null, Plan::getDesc, info.desc())
                ).eq(Plan::getId, planId)
                .eq(Plan::getUserId, userId)
                .update();

        return get(userId, planId);
    }

    @Override
    public void delete(Long userId, Long planId) {
        Plan plan = get(userId, planId);
        planRepository.removeById(plan.getId());
        exerciseRepository.lambdaUpdate()
                .eq(Exercise::getPlanId, plan.getId())
                .remove();
    }

    @Override
    public Plan finish(Long userId, Long planId) {
        planRepository.lambdaUpdate()
                .set(Plan::isFin, true)
                .set(Plan::getFinTime, LocalDateTime.now())
                .eq(Plan::getId, planId)
                .eq(Plan::getUserId, userId)
                .update();

        return get(userId, planId);
    }

}
