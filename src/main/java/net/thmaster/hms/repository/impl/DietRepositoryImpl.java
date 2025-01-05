package net.thmaster.hms.repository.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import net.thmaster.hms.model.entity.Diet;
import net.thmaster.hms.mapper.DietMapper;
import net.thmaster.hms.repository.DietRepository;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 饮食记录 Repository 实现
 * </p>
 *
 * @author master
 */
@Service
public class DietRepositoryImpl extends MPJBaseServiceImpl<DietMapper, Diet> implements DietRepository {

}
