package net.thmaster.hms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.exception.ResourceNotFoundException;
import net.thmaster.hms.model.entity.Food;
import net.thmaster.hms.model.req.FoodInfoRequest;
import net.thmaster.hms.model.req.query.FoodQueryRequest;
import net.thmaster.hms.repository.FoodRepository;
import net.thmaster.hms.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Override
    public Food save(FoodInfoRequest info) {
        Food food = new Food();
        food.setName(info.name());
        food.setDesc(info.desc());
        food.setCalorie(info.calorie());
        food.setImg(info.img());

        foodRepository.save(food);

        return get(food.getId());
    }

    @Override
    public Food get(Long foodId) {
        return foodRepository.getOptById(foodId).orElseThrow(() -> new ResourceNotFoundException("食物不存在"));
    }

    @Override
    public List<Food> list(FoodQueryRequest query) {
        return foodRepository.lambdaQuery()
                .func(query != null, w -> w
                        .like(query != null && query.name() != null, Food::getName, query.name())
                ).list();
    }

    @Override
    public Food update(Long foodId, FoodInfoRequest info) {
        foodRepository.lambdaUpdate()
                .set(info.name() != null, Food::getName, info.name())
                .set(info.desc() != null, Food::getDesc, info.desc())
                .set(info.calorie() != null, Food::getCalorie, info.calorie())
                .set(info.img() != null, Food::getImg, info.img())
                .eq(Food::getId, foodId)
                .update();

        return get(foodId);
    }

    @Override
    public void delete(Long foodId) {
        foodRepository.removeById(foodId);
    }

}
