package lab4.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

public class NotOlderYearValidator implements ConstraintValidator<NotOlderYear, LocalDate> {

    private int annotationYear;

    @Override
    public void initialize(NotOlderYear dateRange) {
        this.annotationYear = dateRange.value();
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
