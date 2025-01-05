package net.thmaster.hms.service.impl;

import com.github.yulichang.toolkit.JoinWrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.exception.ResourceNotFoundException;
import net.thmaster.hms.model.dto.ExerciseDTO;
import net.thmaster.hms.model.entity.Exercise;
import net.thmaster.hms.model.entity.Movement;
import net.thmaster.hms.model.req.ExerciseInfoRequest;
import net.thmaster.hms.model.req.query.ExerciseQueryRequest;
import net.thmaster.hms.repository.ExerciseRepository;
import net.thmaster.hms.repository.MovementRepository;
import net.thmaster.hms.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final MovementRepository movementRepository;

    @Override
    public ExerciseDTO save(Long userId, Long planId, ExerciseInfoRequest info) {
        Exercise entity = new Exercise();
        entity.setPlanId(planId);
        entity.setMovementId(info.movementId());
        entity.setCount(info.count());

        exerciseRepository.save(entity);

        return get(userId, planId, entity.getId());
    }

    @Override
    public ExerciseDTO get(Long userId, Long planId, Long exerciseId) {
        return exerciseRepository.getOptById(exerciseId)
                .filter(x -> Objects.equals(x.getPlanId(), planId))
                .map(exercise -> ExerciseDTO.builder()
                        .id(exercise.getId())
                        .userId(userId)
                        .movement(movementRepository.getById(exercise.getMovementId()))
                        .count(exercise.getCount())
                        .build()
                ).orElseThrow(() -> new ResourceNotFoundException("运动不存在"));
    }

    @Override
    public List<ExerciseDTO> list(Long userId, Long planId, ExerciseQueryRequest query) {
        return exerciseRepository.selectJoinList(ExerciseDTO.class,
                        JoinWrappers.lambda(Exercise.class)
                                .selectAll()
                                .selectAssociation(Movement.class, ExerciseDTO::getMovement)
                                .leftJoin(Movement.class, Movement::getId, Exercise::getMovementId)
                                .func(query != null, w -> w
                                        .like(query.movementName() != null, Movement::getName, query.movementName())
                                ).eq(Exercise::getPlanId, planId)
                ).stream().peek(x -> x.setUserId(userId))
                .toList();
    }

    @Override
    public ExerciseDTO update(Long userId, Long planId, Long exerciseId, ExerciseInfoRequest info) {
        exerciseRepository.lambdaUpdate()
                .set(info.movementId() != null, Exercise::getMovementId, info.movementId())
                .set(info.count() != null, Exercise::getCount, info.count())
                .eq(Exercise::getId, exerciseId)
                .eq(Exercise::getPlanId, planId)
                .update();

        return get(userId, planId, exerciseId);
    }

    @Override
    public void delete(Long userId, Long planId, Long exerciseId) {
        ExerciseDTO dto = get(userId, planId, exerciseId);
        exerciseRepository.removeById(dto.getId());
    }

}
