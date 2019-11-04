package org.xcolab.view.util.validation;

import org.springframework.beans.factory.annotation.Autowired;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.contest.ContestTypeAttributeKey;
import org.xcolab.view.i18n.ResourceMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfigurableProposalPitchMaxLengthValidator implements ConstraintValidator<ConfigurableProposalPitchMaxLength, Object> {


    private final ResourceMessageResolver resourceMessageResolver;

    @Autowired
    public ConfigurableProposalPitchMaxLengthValidator(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initialize(ConfigurableProposalPitchMaxLength constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String content = (String) value;
        if (content == null) {
            // ignore in case of null. Another validator will care about this
            return true;
        }

        Long maxPitchLength = ConfigurationAttributeKey.PROPOSALS_PITCH_CHAR_LIMIT.get();

        final String plainText = content;
        if (plainText.length() <= maxPitchLength.longValue()) {
            return true;
        } else {

            /*
             * if custom message was provided, don't touch it, otherwise build
             * the default message
             */

            context.disableDefaultConstraintViolation();
            Long contestTypeId = ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
            String proposalPitchName = ContestTypeAttributeKey.PROPOSALS_PITCH_NAME.get(contestTypeId);

            String message = resourceMessageResolver.getLocalizedMessage("contests.proposal.sections.pitch.validation",
                    proposalPitchName, maxPitchLength);
            ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder =
                    context.buildConstraintViolationWithTemplate(message);
            violationBuilder.addConstraintViolation();


            return false;
        }
    }

}

