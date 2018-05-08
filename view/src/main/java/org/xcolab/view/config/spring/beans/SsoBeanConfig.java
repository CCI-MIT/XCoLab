package org.xcolab.view.config.spring.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import org.xcolab.view.config.spring.sso.openid.IdTokenEnhancer;

@Configuration
public class SsoBeanConfig {

    private static final Logger log = LoggerFactory.getLogger(SsoBeanConfig.class);

    private String signingKey;
    private final Signer signer;

    public SsoBeanConfig() {
        //TODO: make the key (and algorithm?) configurable
        if (signingKey == null) {
            signingKey = new RandomValueStringGenerator().generate();
            log.info("No JWT signing key configured. Generated key: {}", signingKey);
        }
        signer = new MacSigner(signingKey);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new IdTokenEnhancer(signer);
    }
}
