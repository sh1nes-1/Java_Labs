package lab4.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

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

        //TODO: here fix
        boolean isValid =  localDate.getYear() >= annotationYears;

        if (!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate("must be not older than " + annotationYears + " years")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
