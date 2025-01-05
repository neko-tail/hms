package net.thmaster.hms.repository.impl;

import net.thmaster.hms.model.entity.Exercise;
import net.thmaster.hms.mapper.ExerciseMapper;
import net.thmaster.hms.repository.ExerciseRepository;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 运动记录 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class ExerciseRepositoryImpl extends MPJBaseServiceImpl<ExerciseMapper, Exercise> implements ExerciseRepository {

}
