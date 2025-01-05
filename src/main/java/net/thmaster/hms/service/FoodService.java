package net.thmaster.hms.service;

import net.thmaster.hms.model.entity.Food;
import net.thmaster.hms.model.req.FoodInfoRequest;
import net.thmaster.hms.model.req.query.FoodQueryRequest;

import java.util.List;

/**
 * @author master
 */
public interface FoodService {

    Food save(FoodInfoRequest info);

    Food get(Long foodId);

    List<Food> list(FoodQueryRequest query);

    Food update(Long foodId, FoodInfoRequest info);

    void delete(Long foodId);

}
