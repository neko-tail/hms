package net.thmaster.hms.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * @author master
 */
@Getter
@AllArgsConstructor
public enum DietType {

    BREAKFAST(1, "早餐"),
    LUNCH(2, "午餐"),
    DINNER(3, "晚餐"),
    SNACK(4, "加餐");

    @EnumValue
    private final Integer code;

    private final String name;

    public static Optional<DietType> getByCode(Integer code) {
        for (DietType type : DietType.values()) {
            if (type.getCode().equals(code)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }

    public static Optional<DietType> getByName(String name) {
        for (DietType type : DietType.values()) {
            if (type.getName().equals(name)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
