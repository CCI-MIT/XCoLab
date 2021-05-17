package org.xcolab.view.util.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import static org.xcolab.view.util.validation.ConstraintValidatorHelper.getPropertyValue;
import static org.xcolab.view.util.validation.ConstraintValidatorHelper.resolveMessage;

public class CompareStringsValidator implements ConstraintValidator<CompareStrings, Object> {

    private String[] propertyNames;
    private StringComparisonMode comparisonMode;

    private final ResourceMessageResolver resourceMessageResolver;

    @Autowired
    public CompareStringsValidator(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initialize(CompareStrings constraintAnnotation) {
        this.propertyNames = constraintAnnotation.propertyNames();
        if (propertyNames.length != 2) {
            throw new IllegalArgumentException("Can only compare exactly two properties, not "
                    + propertyNames.length);
        }
        this.comparisonMode = constraintAnnotation.comparisonMode();
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
        boolean isValid = true;
        String s1 = getPropertyValue(String.class, propertyNames[0], target);
        String s2 = getPropertyValue(String.class, propertyNames[1], target);

        switch (comparisonMode) {
            case EQUAL:
                isValid = StringUtils.equals(s1, s2);
                break;
            case EQUAL_IGNORE_CASE:
                isValid = StringUtils.equalsIgnoreCase(s1, s2);
                break;
            //missing default case
            default:
                // add default case
                break;

        }

        if (!isValid) {
            boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());
            /* if custom message was provided, don't touch it, otherwise build the default
            message */
            if (isDefaultMessage) {
                String resolvedMessage = resolveMessage(propertyNames, resourceMessageResolver);
                context.disableDefaultConstraintViolation();
                ConstraintViolationBuilder violationBuilder = context
                        .buildConstraintViolationWithTemplate(resolvedMessage);
                violationBuilder.addConstraintViolation();
            }
        }
        return isValid;
    }
}
