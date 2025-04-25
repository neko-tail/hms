package net.thmaster.hms.service;

import net.thmaster.hms.model.dto.ExerciseDTO;
import net.thmaster.hms.model.req.ExerciseInfoRequest;
import net.thmaster.hms.model.req.query.ExerciseQueryRequest;

import java.util.List;

/**
 * @author master
 */
public interface ExerciseService {

    ExerciseDTO save(Long userId, ExerciseInfoRequest info);

    ExerciseDTO get(Long userId, Long exerciseId);

    List<ExerciseDTO> list(Long userId, ExerciseQueryRequest query);

    ExerciseDTO update(Long userId, Long exerciseId, ExerciseInfoRequest info);

    void delete(Long userId, Long exerciseId);

}
