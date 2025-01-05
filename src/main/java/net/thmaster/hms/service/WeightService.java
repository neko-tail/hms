package net.thmaster.hms.service;

import net.thmaster.hms.model.entity.Weight;
import net.thmaster.hms.model.req.WeightInfoRequest;
import net.thmaster.hms.model.req.query.WeightQueryRequest;

import java.util.List;

/**
 * @author master
 */
public interface WeightService {

    Weight save(Long userId, WeightInfoRequest info);

    Weight get(Long userId, Long weightId);

    List<Weight> list(Long userId, WeightQueryRequest query);

    Weight update(Long userId, Long weightId, WeightInfoRequest info);

    void delete(Long userId, Long weightId);

}
