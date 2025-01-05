package net.thmaster.hms.repository.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import net.thmaster.hms.mapper.WeightMapper;
import net.thmaster.hms.model.entity.Weight;
import net.thmaster.hms.repository.WeightRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 体重记录 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class WeightRepositoryImpl extends MPJBaseServiceImpl<WeightMapper, Weight> implements WeightRepository {

    @Override
    public Optional<Weight> getLatest(Long userId) {
        return lambdaQuery()
                .eq(Weight::getUserId, userId)
                .orderByDesc(Weight::getDate)
                .last("limit 1")
                .oneOpt();
    }

}
