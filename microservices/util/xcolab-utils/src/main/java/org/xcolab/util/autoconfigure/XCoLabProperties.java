package org.xcolab.util.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("xcolab")
public class XCoLabProperties {

    /**
     * Application secret used for remember-me tokens.
     */
    private String secret;

    /**
     * Namespace for service discovery.
     *
     * Customize this property when using several instances on the same Eureka server (e.g. for
     * contest mirroring).
     */
    private String namespace = "xcolab";

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

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
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
