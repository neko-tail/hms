package net.thmaster.hms.repository;

import net.thmaster.hms.model.entity.Weight;
import com.github.yulichang.base.MPJBaseService;

import java.util.Optional;

/**
 * <p>
 * 体重记录 Repository
 * </p>
 *
 * @author master
 */
public interface WeightRepository extends MPJBaseService<Weight> {

    Optional<Weight> getLatest(Long userId);

}
