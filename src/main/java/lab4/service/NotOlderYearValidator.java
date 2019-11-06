package lab4.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class NotOlderYearValidator implements ConstraintValidator<NotOlderYear, LocalDate> {

    private int annotationYear;

    @Override
    public void initialize(NotOlderYear notOlderYear) {
        this.annotationYear = notOlderYear.value();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintContext) {

        if (localDate == null)
            return true;

        boolean isValid = localDate.getYear() >= annotationYear;

        if (!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate("must be not older than " + annotationYear)
                    .addConstraintViolation();
        }

        return isValid;
    }
}
