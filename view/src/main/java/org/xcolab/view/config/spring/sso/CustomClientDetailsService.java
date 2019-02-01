package org.xcolab.view.config.spring.sso;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.SsoClientDetailsWrapper;
import org.xcolab.view.config.spring.sso.openid.OpenIdHelper;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomClientDetailsService implements ClientDetailsService {

    private static final List<String> DEFAULT_SCOPES = Arrays.asList(
            OpenIdHelper.SCOPE_OPENID, OpenIdHelper.SCOPE_EMAIL, OpenIdHelper.SCOPE_PROFILE);

    @Override
    public ClientDetails loadClientByClientId(String clientId)
            throws ClientRegistrationException {
        SsoClientDetailsWrapper clientDetails = StaticUserContext.getSsoClientDetailsClient().getSsoClientDetails(clientId);
        return new CustomClientDetails(clientDetails);
    }

    public static class CustomClientDetails implements ClientDetails {

        private final SsoClientDetailsWrapper clientDetails;

        public CustomClientDetails(SsoClientDetailsWrapper clientDetails) {
            this.clientDetails = clientDetails;
        }

        @Override
        public String getClientId() {
            return clientDetails.getId();
        }

        @Override
        public String getClientSecret() {
            return clientDetails.getSecret();
        }

        @Override
        public Set<String> getScope() {
            final List<String> scopes;
            if (!StringUtils.isEmpty(clientDetails.getScope())) {
                scopes = Arrays.asList(StringUtils.split(clientDetails.getScope(), ','));
            } else {
                scopes = DEFAULT_SCOPES;
            }
            return new HashSet<>(scopes);
        }

        @Override
        public Set<String> getRegisteredRedirectUri() {
            if (!StringUtils.isEmpty(clientDetails.getRegisteredredirecturi())) {
                return Collections.singleton(clientDetails.getRegisteredredirecturi());
            }
            return Collections.emptySet();
        }

        @Override
        public boolean isSecretRequired() {
            return true;
        }

        // Defaults

        @Override
        public Set<String> getResourceIds() {
            return Collections.emptySet();
        }

        @Override
        public boolean isScoped() {
            return true;
        }

        @Override
        public Set<String> getAuthorizedGrantTypes() {
            return new LinkedHashSet<>(Arrays.asList("authorization_code", "refresh_token"));
        }

        @Override
        public Collection<GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }

        @Override
        public Integer getAccessTokenValiditySeconds() {
            return null;
        }

        @Override
        public Integer getRefreshTokenValiditySeconds() {
            return null;
        }

        @Override
        public boolean isAutoApprove(String scope) {
            return false;
        }

        @Override
        public Map<String, Object> getAdditionalInformation() {
            return Collections.emptyMap();
        }
    }
}
