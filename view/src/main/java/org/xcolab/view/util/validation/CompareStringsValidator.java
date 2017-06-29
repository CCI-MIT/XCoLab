package org.xcolab.view.util.validation;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.view.i18n.ResourceMessageResolver;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

public class CompareStringsValidator implements ConstraintValidator<CompareStrings, Object> {

    private String[] propertyNames;
    private StringComparisonMode comparisonMode;
    private boolean allowNull;

    private final ResourceMessageResolver resourceMessageResolver;

    @Autowired
    public CompareStringsValidator(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initialize(CompareStrings constraintAnnotation) {
        this.propertyNames = constraintAnnotation.propertyNames();
        this.comparisonMode = constraintAnnotation.matchMode();
        this.allowNull = constraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
        boolean isValid = true;
        int validationFailedAtIndex = -1;
        for (int i = 0; i < propertyNames.length; i++) {
            //explode
            List<String> propertyValues = new ArrayList<>(propertyNames.length);
            String[] valueIdentifiers = propertyNames[i].split(",");
            for (String valueIdentifier : valueIdentifiers) {
                String propertyValue =
                        ConstraintValidatorHelper.getPropertyValue(String.class, valueIdentifier, target);
                if (propertyValue == null) {
                    if (!allowNull) {
                        isValid = false;
                        validationFailedAtIndex = i;
                        break;
                    }
                } else {
                    propertyValues.add(propertyValue);
                }
            }

            if (isValid) {
                isValid = ConstraintValidatorHelper.isValid(propertyValues, comparisonMode);
                if (!isValid) {
                    validationFailedAtIndex = i;
                }
            } else {
                break;
            }
        }

        if (!isValid) {
            boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());
            /* if custom message was provided, don't touch it, otherwise build the default message */
            if (isDefaultMessage) {
                String resolvedMessage = ConstraintValidatorHelper
                        .resolveMessage(propertyNames[validationFailedAtIndex].split(","),
                                resourceMessageResolver);
                context.disableDefaultConstraintViolation();
                ConstraintViolationBuilder violationBuilder =
                        context.buildConstraintViolationWithTemplate(resolvedMessage);
                violationBuilder.addConstraintViolation();
            }
        }
        return isValid;
    }
}
