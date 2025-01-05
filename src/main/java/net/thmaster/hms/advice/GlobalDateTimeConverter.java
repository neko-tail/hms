package net.thmaster.hms.advice;

import net.thmaster.hms.config.jackson.deserializer.LocalDateDeserializer;
import net.thmaster.hms.config.jackson.deserializer.LocalDateTimeDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author master
 */
@Component
@RestControllerAdvice
public class GlobalDateTimeConverter {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // RequestParam 中的日期时间参数转换
        binder.registerCustomEditor(
                LocalDateTime.class, new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException {
                        setValue(LocalDateTimeDeserializer.deserialize(text));
                    }
                }
        );
        binder.registerCustomEditor(
                LocalDate.class, new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException {
                        setValue(LocalDateDeserializer.deserialize(text));
                    }
                }
        );
    }
}
