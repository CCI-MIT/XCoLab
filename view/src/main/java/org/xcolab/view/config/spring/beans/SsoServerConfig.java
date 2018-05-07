package org.xcolab.view.config.spring.beans;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.members.pojo.Member;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("ProhibitedExceptionDeclared")
@EnableAuthorizationServer
@RestController
@Configuration
public class SsoServerConfig extends OAuth2AuthorizationServerConfiguration {

    @Autowired
    public SsoServerConfig(BaseClientDetails details,
            AuthenticationManager authenticationManager, ObjectProvider<TokenStore> tokenStore,
            ObjectProvider<AccessTokenConverter> tokenConverter,
            AuthorizationServerProperties properties) {
        super(details, authenticationManager, tokenStore, tokenConverter, properties);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
            throws Exception {
        security.allowFormAuthenticationForClients();
        super.configure(security);
    }

    @GetMapping("/api/user")
    public Map<String, String> user(Member member) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("sub", Long.toString(member.getId_()));
        map.put("name", member.getFullName());
        map.put("given_name", member.getFirstName());
        map.put("family_name", member.getLastName());
        map.put("email", member.getEmailAddress());
        map.put("preferred_username", member.getScreenName());
        if (StringUtils.isNotEmpty(member.getAbsoluteImageUrl())) {
            map.put("picture", member.getAbsoluteImageUrl());
        }
        return map;
    }

    @GetMapping("/oauth-configuration")
    public Map<String, Object> openIdConfiguration() {
        Map<String, Object> map = new LinkedHashMap<>();
        final String url = PlatformAttributeKey.COLAB_URL.get();
        map.put("issuer", url);
        map.put("authorization_endpoint", url + "/oauth/authorize");
        map.put("token_endpoint", url + "/oauth/token");
        map.put("userinfo_endpoint", url + "/api/user");
        map.put("token_endpoint_auth_methods_supported", new String[]{"client_secret_basic", "client_secret_post"});
        map.put("scopes_supported", new String[] {"email", "profile"});
        map.put("claims_supported", new String[] {"sub", "email", "name", "given_name", "family_name", "picture"});
        return map;
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/user")
                    .authorizeRequests().anyRequest().authenticated();
        }
    }
}
