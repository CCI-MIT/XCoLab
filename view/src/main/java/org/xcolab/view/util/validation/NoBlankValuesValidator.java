package org.xcolab.view.util.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

import javax.validation.ConstraintValidatorContext;

public class NoBlankValuesValidator extends CustomValidator<NoBlankValues> {

    @Override
    public void initialize(NoBlankValues noBlankValues) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) {
            return true;
        }
        if (o instanceof Map) {
            o = ((Map) o).values();
        }
        if (o instanceof Collection) {
            Collection collection = (Collection) o;
            for (Object val : collection) {
                if (val instanceof String) {
                    String s = (String) val;
                    if (StringUtils.isBlank(s)) {
                        return false;
                    }
                } else {
                    throw new InvalidTargetException();
                }
            }
            return true;
        } else {
            throw new InvalidTargetException();
        }
    }

    private static class InvalidTargetException extends RuntimeException {

        InvalidTargetException() {
            super("Target must be a collection of String values");
        }
    }

}
