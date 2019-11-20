package lab4.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class NotOlderThanYearsValidator implements ConstraintValidator<NotOlderThanYears, LocalDate> {

    private int annotationYears;

    @Override
    public void initialize(NotOlderThanYears notOlderThanYears) {
        this.annotationYears = notOlderThanYears.value();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintContext) {

        if (localDate == null)
            return true;

        long yearsBetween = Math.abs(ChronoUnit.YEARS.between(LocalDate.now(), localDate));
        boolean isValid = yearsBetween <= annotationYears;

        if (!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate("must be not older than " + annotationYears + " years")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
