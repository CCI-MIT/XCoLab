package org.xcolab.view.config.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("xcolab")
public class XCoLabProperties {

    /**
     * Application secret used for remember-me tokens.
     */
    private String secret;

    /**
     * Remember me configuration.
     */
    private final RememberMe rememberMe = new RememberMe();

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public RememberMe getRememberMe() {
        return rememberMe;
    }

    public static class RememberMe {

        /**
         * Length of remember me token in seconds. Default is one day.
         */
        private int tokenValiditySeconds = 86_400;

        public int getTokenValiditySeconds() {
            return tokenValiditySeconds;
        }

        public void setTokenValiditySeconds(int tokenValiditySeconds) {
            this.tokenValiditySeconds = tokenValiditySeconds;
        }
    }
}
