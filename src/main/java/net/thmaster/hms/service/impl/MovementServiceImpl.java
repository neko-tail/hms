package net.thmaster.hms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.exception.ResourceNotFoundException;
import net.thmaster.hms.model.entity.Movement;
import net.thmaster.hms.model.req.MovementInfoRequest;
import net.thmaster.hms.model.req.query.MovementQueryRequest;
import net.thmaster.hms.repository.MovementRepository;
import net.thmaster.hms.service.MovementService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;

    @Override
    public Movement save(MovementInfoRequest info) {
        Movement movement = new Movement();
        movement.setName(info.name());
        movement.setDesc(info.desc());
        movement.setCalorie(info.calorie());
        movement.setImg(info.img());

        movementRepository.save(movement);

        return get(movement.getId());
    }

    @Override
    public Movement get(Long movementId) {
        return movementRepository.getOptById(movementId).orElseThrow(() -> new ResourceNotFoundException("运动不存在"));
    }

    @Override
    public List<Movement> list(MovementQueryRequest query) {
        return movementRepository.lambdaQuery()
                .func(query != null, w -> w
                        .like(query != null && query.name() != null, Movement::getName, query.name())
                ).list();
    }

    @Override
    public Movement update(Long movementId, MovementInfoRequest info) {
        movementRepository.lambdaUpdate()
                .set(info.name() != null, Movement::getName, info.name())
                .set(info.desc() != null, Movement::getDesc, info.desc())
                .set(info.calorie() != null, Movement::getCalorie, info.calorie())
                .set(info.img() != null, Movement::getImg, info.img())
                .eq(Movement::getId, movementId)
                .update();

        return get(movementId);
    }

    @Override
    public void delete(Long movementId) {
        movementRepository.removeById(movementId);
    }

}
