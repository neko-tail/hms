package net.thmaster.hms.service;

import net.thmaster.hms.model.entity.Movement;
import net.thmaster.hms.model.req.MovementInfoRequest;
import net.thmaster.hms.model.req.query.MovementQueryRequest;

import java.util.List;

/**
 * @author master
 */
public interface MovementService {

    Movement save(MovementInfoRequest info);

    Movement get(Long movementId);

    List<Movement> list(MovementQueryRequest query);

    Movement update(Long movementId, MovementInfoRequest info);

    void delete(Long movementId);

}
