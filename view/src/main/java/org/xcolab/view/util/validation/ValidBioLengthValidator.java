package org.xcolab.view.util.validation;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidBioLengthValidator implements
        ConstraintValidator<ValidBioLength, Object> {
    private static final int BIO_MAX_LENGTH = 2000;
    private String bioProperty;

    private final ResourceMessageResolver resourceMessageResolver;

    @Autowired
    public ValidBioLengthValidator(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initialize(ValidBioLength constraintAnnotation) {
        bioProperty = constraintAnnotation.bioProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String bio = ConstraintValidatorHelper.getPropertyValue(
                String.class, bioProperty, value);
        if (bio == null) {
            // ignore in case of null. Another validator will care about this
            return true;
        }

        if (validateBio(bio)) {
            return true;
        } else {
            boolean isDefaultMessage = "".equals(context
                    .getDefaultConstraintMessageTemplate());
            /*
			 * if custom message was provided, don't touch it, otherwise build
			 * the default message
			 */
            if (isDefaultMessage) {
                context.disableDefaultConstraintViolation();
                String message = resourceMessageResolver.getLocalizedMessage(
                        "register.form.validation.biography.message", new String[]{BIO_MAX_LENGTH+""});
                ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder = context
                        .buildConstraintViolationWithTemplate(message);
                violationBuilder.addConstraintViolation();
            }

            return false;
        }
    }

    public boolean validateBio(String bio) {

        return Jsoup.parse(bio).text().length() <= BIO_MAX_LENGTH;
    }
}
