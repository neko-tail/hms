package net.thmaster.hms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.model.entity.UserCustom;
import net.thmaster.hms.model.entity.Weight;
import net.thmaster.hms.model.model.MonitorModel;
import net.thmaster.hms.model.req.query.DietQueryRequest;
import net.thmaster.hms.repository.WeightRepository;
import net.thmaster.hms.service.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MonitorServiceImpl implements MonitorService {

    private final UserCustomService userCustomService;

    private final DietService dietService;

    private final ExerciseService exerciseService;

    private final WeightService weightService;

    private final WeightRepository weightRepository;

    @Override
    public MonitorModel get(Long userId) {
        UserCustom userCustom = userCustomService.get(userId);

        Optional<Double> initWeight = Optional.ofNullable(userCustom.getInitWeight());
        Optional<Double> targetWeight = Optional.ofNullable(userCustom.getTargetWeight());
        Optional<Double> calorieLimit = Optional.ofNullable(userCustom.getCalorieLimit());

        LocalDate today = LocalDate.now();

        // 今日摄入
        DietQueryRequest dietQuery = new DietQueryRequest(null, null, today, null, null);
        Double intakesCalories = dietService.list(userId, dietQuery).stream()
                .map(x -> x.getFood().getCalorie() * x.getCount())
                .reduce(Double::sum).orElse(0.0);

        // 今日消耗
        Double burnsCalories = 0.0;

        // 剩余热量
        Optional<Double> remainingCalories = calorieLimit.map(x -> x - intakesCalories);

        Optional<Double> currentWeight = weightRepository.getLatest(userId).map(Weight::getWeight);
        if (currentWeight.isEmpty()) {
            currentWeight = initWeight;
        }

        Optional<Double> lostWeight = Optional.empty();
        if (initWeight.isPresent()) {
            lostWeight = Optional.of(initWeight.get() - currentWeight.get());
        }

        return MonitorModel.builder()
                .userId(userId)
                .initWeight(initWeight.orElse(null))
                .targetWeight(targetWeight.orElse(null))
                .currentWeight(currentWeight.orElse(null))
                .intakesCalories(intakesCalories)
                .caloriesLimit(calorieLimit.orElse(null))
                .burnsCalories(burnsCalories)
                .remainingCalories(remainingCalories.orElse(null))
                .lostWeight(lostWeight.orElse(null))
                .build();
    }

}
