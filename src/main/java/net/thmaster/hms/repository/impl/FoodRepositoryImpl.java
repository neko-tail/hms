package net.thmaster.hms.repository.impl;

import net.thmaster.hms.model.entity.Food;
import net.thmaster.hms.mapper.FoodMapper;
import net.thmaster.hms.repository.FoodRepository;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 食物 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class FoodRepositoryImpl extends MPJBaseServiceImpl<FoodMapper, Food> implements FoodRepository {

}
