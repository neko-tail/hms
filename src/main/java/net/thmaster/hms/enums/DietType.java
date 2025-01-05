package net.thmaster.hms.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
