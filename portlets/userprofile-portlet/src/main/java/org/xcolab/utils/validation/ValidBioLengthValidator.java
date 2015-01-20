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
	private long DEFAULT_COMPANY_ID = 10112L;

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
				StringBuilder sb = new StringBuilder();
				sb.append("The length of your bio must be less than or equal ");
				sb.append(BIO_MAX_LENGTH);
				sb.append(" characters");
				context.disableDefaultConstraintViolation();
				ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder = context
						.buildConstraintViolationWithTemplate(sb.toString());
				violationBuilder.addConstraintViolation();
			}

			return false;
		}
	}

	public boolean validateBio(String bio) {

		return Jsoup.parse(bio).text().length() <= BIO_MAX_LENGTH;
	}
}