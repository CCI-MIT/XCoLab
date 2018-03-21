package org.xcolab.commons.recaptcha;

public enum RecaptchaErrorCode {
    BAD_REQUEST(true),
    MISSING_INPUT_SECRET(true),
    INVALID_INPUT_SECRET(true),
    MISSING_INPUT_RESPONSE,
    INVALID_INPUT_RESPONSE,
    TIMEOUT_OR_DUPLICATE;

    private final boolean shouldThrowException;

    RecaptchaErrorCode() {
        this(false);
    }

    RecaptchaErrorCode(boolean shouldThrowException) {
        this.shouldThrowException = shouldThrowException;
    }

    public boolean isShouldThrowException() {
        return shouldThrowException;
    }
}
