package org.xcolab.view.config.spring.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.config.spring.sso.openid.IdTokenEnhancer;
import org.xcolab.view.config.spring.sso.openid.OpenIdHelper;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("ProhibitedExceptionDeclared")
@EnableAuthorizationServer
@RestController
@ConditionalOnBean(BaseClientDetails.class)
@Configuration
public class SsoServerConfig extends OAuth2AuthorizationServerConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SsoServerConfig.class);

    private String signingKey;
    private final Signer signer;

    @Autowired
    public SsoServerConfig(BaseClientDetails details, AuthenticationManager authenticationManager,
            ObjectProvider<TokenStore> tokenStore,
            ObjectProvider<AccessTokenConverter> tokenConverter,
            AuthorizationServerProperties properties) {
        super(details, authenticationManager, tokenStore, tokenConverter, properties);

        //TODO: make the key (and algorithm?) configurable
        if (signingKey == null) {
            signingKey = new RandomValueStringGenerator().generate();
            log.info("No JWT signing key configured. Generated key: {}", signingKey);
        }
        signer = new MacSigner(signingKey);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenEnhancer(tokenEnhancer());
        super.configure(endpoints);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        super.configure(security);
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new IdTokenEnhancer(signer);
    }

    @GetMapping(OpenIdHelper.OAUTH_USERINFO_ENDPOINT)
    public Map<String, Object> user(Member member) {
        OAuth2Authentication authentication =
                (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        final OpenIdHelper openIdHelper = new OpenIdHelper(authentication);
        Map<String, Object> map = new LinkedHashMap<>();
        openIdHelper.addSubjectField(map, member);
        if (openIdHelper.hasEmailScope()) {
            openIdHelper.addEmailScopedFields(map, member);
        }
        if (openIdHelper.hasProfileScope()) {
            openIdHelper.addProfileScopedFields(map, member);
        }
        return map;
    }

    @GetMapping("/.well-known/openid-configuration")
    public Map<String, Object> openIdConfiguration() {
        return OpenIdHelper.getOpenIdConfiguration();
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher(OpenIdHelper.OAUTH_USERINFO_ENDPOINT).authorizeRequests().anyRequest()
                    .authenticated();
        }
    }
}
