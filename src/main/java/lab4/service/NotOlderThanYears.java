package lab4.service;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotOlderThanYearsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotOlderThanYears {

    int value();

    String message() default "{lab4.service.NotOlderThanYears.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
