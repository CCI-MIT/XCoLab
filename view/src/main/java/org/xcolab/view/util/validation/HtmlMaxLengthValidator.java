package org.xcolab.view.util.validation;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HtmlMaxLengthValidator implements ConstraintValidator<HtmlMaxLength, Object> {

    private int max;

    private final ResourceMessageResolver resourceMessageResolver;

    @Autowired
    public HtmlMaxLengthValidator(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initialize(HtmlMaxLength constraintAnnotation) {
        max = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String content = (String) value;
        if (content == null) {
            // ignore in case of null. Another validator will care about this
            return true;
        }

        final String plainText = Jsoup.parse(content).text();
        if (plainText.length() <= max) {
            return true;
        } else {
            boolean isDefaultMessage = "".equals(context.getDefaultConstraintMessageTemplate());
            /*
             * if custom message was provided, don't touch it, otherwise build
			 * the default message
			 */
            if (isDefaultMessage) {
                context.disableDefaultConstraintViolation();
                String message = "This field may not be more than " + max + " characters.";
                ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder =
                        context.buildConstraintViolationWithTemplate(message);
                violationBuilder.addConstraintViolation();
            }

            return false;
        }
    }
}
