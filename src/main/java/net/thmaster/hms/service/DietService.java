package net.thmaster.hms.service;

import net.thmaster.hms.model.dto.DietDTO;
import net.thmaster.hms.model.req.DietInfoRequest;
import net.thmaster.hms.model.req.query.DietQueryRequest;

import java.util.List;

/**
 * @author master
 */
public interface DietService {

    DietDTO save(Long userId, DietInfoRequest info);

    DietDTO get(Long userId, Long dietId);

    List<DietDTO> list(Long userId, DietQueryRequest query);

    DietDTO update(Long userId, Long dietId, DietInfoRequest info);

    void delete(Long userId, Long dietId);
}
