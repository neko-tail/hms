package net.thmaster.hms.service;

import net.thmaster.hms.model.entity.Plan;
import net.thmaster.hms.model.req.PlanInfoRequest;
import net.thmaster.hms.model.req.query.PlanQueryRequest;

import java.util.List;

/**
 * @author master
 */
public interface PlanService {

    Plan save(Long userId, PlanInfoRequest info);

    Plan get(Long userId, Long planId);

    List<Plan> list(Long userId, PlanQueryRequest query);

    Plan update(Long userId, Long planId, PlanInfoRequest info);

    void delete(Long userId, Long planId);

    Plan finish(Long userId, Long planId);

}
