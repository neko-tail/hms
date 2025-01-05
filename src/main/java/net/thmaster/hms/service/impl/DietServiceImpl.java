package net.thmaster.hms.service.impl;

import com.github.yulichang.toolkit.JoinWrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.model.dto.DietDTO;
import net.thmaster.hms.model.entity.Diet;
import net.thmaster.hms.model.entity.Food;
import net.thmaster.hms.model.req.DietInfoRequest;
import net.thmaster.hms.model.req.query.DietQueryRequest;
import net.thmaster.hms.repository.DietRepository;
import net.thmaster.hms.repository.FoodRepository;
import net.thmaster.hms.service.DietService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DietServiceImpl implements DietService {

    private final DietRepository dietRepository;

    private final FoodRepository foodRepository;

    @Override
    public DietDTO save(Long userId, DietInfoRequest info) {
        Diet diet = new Diet();
        diet.setUserId(userId);
        diet.setType(info.type());
        diet.setFoodId(info.foodId());
        diet.setCount(info.count());
        diet.setTime(Optional.ofNullable(info.time()).orElse(LocalDateTime.now()));

        dietRepository.save(diet);

        return get(diet.getUserId(), diet.getId());
    }

    @Override
    public DietDTO get(@NonNull Long userId, @NonNull Long dietId) {
        return dietRepository.lambdaQuery()
                .eq(Diet::getId, dietId)
                .eq(Diet::getUserId, userId)
                .oneOpt()
                .map(diet -> DietDTO.builder()
                        .id(diet.getId())
                        .userId(diet.getUserId())
                        .type(diet.getType())
                        .count(diet.getCount())
                        .time(diet.getTime())
                        .food(foodRepository.getById(diet.getFoodId()))
                        .build()
                ).orElseThrow(() -> new IllegalArgumentException("饮食记录不存在"));
    }

    @Override
    public List<DietDTO> list(@NonNull Long userId, DietQueryRequest query) {
        return dietRepository.selectJoinList(DietDTO.class, JoinWrappers.lambda(Diet.class)
                .selectAll()
                .selectAssociation(Food.class, DietDTO::getFood)
                .innerJoin(Food.class, Food::getId, Diet::getFoodId)
                .eq(Diet::getUserId, userId)
                .func(query != null, w -> {
                            w.like(query.foodName() != null, Food::getName, query.foodName());
                            if (query.day() != null) {
                                w.ge(Diet::getTime, query.day().atStartOfDay());
                                w.lt(Diet::getTime, query.day().plusDays(1).atStartOfDay());
                            } else {
                                w.ge(query.startTime() != null, Diet::getTime, query.startTime());
                                w.le(query.endTime() != null, Diet::getTime, query.endTime());
                            }
                        }
                )
        );
    }

    @Override
    public DietDTO update(Long userId, Long dietId, DietInfoRequest info) {
        dietRepository.lambdaUpdate()
                .set(info.type() != null, Diet::getType, info.type())
                .set(info.foodId() != null, Diet::getFoodId, info.foodId())
                .set(info.count() != null, Diet::getCount, info.count())
                .set(info.time() != null, Diet::getTime, info.time())
                .eq(Diet::getId, dietId)
                .eq(Diet::getUserId, userId)
                .update();

        return get(userId, dietId);
    }

    @Override
    public void delete(Long userId, Long dietId) {
        DietDTO dto = get(userId, dietId);
        dietRepository.removeById(dto.getId());
    }

}
