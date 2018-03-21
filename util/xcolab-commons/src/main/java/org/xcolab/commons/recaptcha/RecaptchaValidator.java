package org.xcolab.commons.recaptcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecaptchaValidator {

    private static final Logger log = LoggerFactory.getLogger(RecaptchaValidator.class);

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL =
            "https://www.google.com/recaptcha/api/siteverify?response={response}&secret={secret}";

    private final RestTemplate restTemplate;
    private final String secret;

    public RecaptchaValidator(String secret) {
        this.restTemplate = new RestTemplate();
        this.secret = secret;
    }

    public boolean verify(String response) {
        Map<String, String> body = new HashMap<>();
        body.put("secret", secret);
        body.put("response", response);

        ResponseEntity<RecaptchaResponse> recaptchaResponseEntity =
                restTemplate.postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL,
                        body, RecaptchaResponse.class, body);

        log.debug("Response from ReCaptcha: {}", recaptchaResponseEntity);
        RecaptchaResponse recaptchaResponse = recaptchaResponseEntity.getBody();

        if (!recaptchaResponse.isSuccess()) {
            List<RecaptchaErrorCode> errorCodes = recaptchaResponse.getErrorCodes().stream()
                    .map(s -> s.replace('-', '_').toUpperCase())
                    .map(RecaptchaErrorCode::valueOf)
                    .collect(Collectors.toList());

            String errorMessage = errorCodes.stream()
                    .map(RecaptchaErrorCode::toString)
                    .collect(Collectors.joining(","));

            log.warn("Error while verifying ReCaptcha: {}", errorMessage);
            if (errorCodes.stream().anyMatch(RecaptchaErrorCode::isShouldThrowException)) {
                throw new RecaptchaException(errorMessage);
            }
            return false;
        } else {
            return true;
        }
    }

    public static class RecaptchaException extends RuntimeException {

        public RecaptchaException(String msg) {
            super(msg);
        }
    }
}
