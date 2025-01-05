package net.thmaster.hms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.thmaster.hms.exception.ResourceNotFoundException;
import net.thmaster.hms.model.entity.Weight;
import net.thmaster.hms.model.req.WeightInfoRequest;
import net.thmaster.hms.model.req.query.WeightQueryRequest;
import net.thmaster.hms.repository.WeightRepository;
import net.thmaster.hms.service.WeightService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author master
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WeightServiceImpl implements WeightService {

    private final WeightRepository weightRepository;

    @Override
    public Weight save(Long userId, WeightInfoRequest info) {
        Weight weight = new Weight();
        weight.setUserId(userId);
        weight.setWeight(info.weight());
        weight.setDate(Optional.ofNullable(info.date()).orElse(LocalDate.now()));

        weightRepository.save(weight);

        return get(userId, weight.getId());
    }

    @Override
    public Weight get(Long userId, Long weightId) {
        return weightRepository.getOptById(weightId)
                .filter(x -> x.getUserId().equals(userId))
                .orElseThrow(() -> new ResourceNotFoundException("体重不存在"));
    }

    @Override
    public List<Weight> list(Long userId, WeightQueryRequest query) {
        return weightRepository.lambdaQuery()
                .eq(Weight::getUserId, userId)
                .func(query != null, w -> {
                            if (query.day() != null) {
                                w.ge(Weight::getDate, query.day());
                                w.lt(Weight::getDate, query.day().plusDays(1));
                            } else {
                                w.ge(query.startTime() != null, Weight::getDate, query.startTime());
                                w.le(query.endTime() != null, Weight::getDate, query.endTime());
                            }
                        }
                ).list();
    }

    @Override
    public Weight update(Long userId, Long weightId, WeightInfoRequest info) {
        weightRepository.lambdaUpdate()
                .set(info.weight() != null, Weight::getWeight, info.weight())
                .set(info.date() != null, Weight::getDate, info.date())
                .eq(Weight::getId, weightId)
                .eq(Weight::getUserId, userId)
                .update();

        return get(userId, weightId);
    }

    @Override
    public void delete(Long userId, Long weightId) {
        Weight weight = get(userId, weightId);
        weightRepository.removeById(weight.getId());
    }

}
