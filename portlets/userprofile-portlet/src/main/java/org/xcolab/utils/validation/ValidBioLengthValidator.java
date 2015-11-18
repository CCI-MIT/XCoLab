package org.xcolab.utils.validation;

import org.jsoup.Jsoup;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Mente on 27/04/14.
 */

public class ValidBioLengthValidator implements
		ConstraintValidator<ValidBioLength, Object> {
	private String bioProperty;

	private static final int BIO_MAX_LENGTH = 2000;

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
				final String message = "The length of your bio must be less than or equal " + BIO_MAX_LENGTH + " characters";
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