package org.xcolab.view.config.spring.sso.openid;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.LinkedHashMap;
import java.util.Map;

public class IdTokenEnhancer implements TokenEnhancer {

    private static final JsonParser OBJECT_MAPPER = JsonParserFactory.create();

    private final Signer signer;

    public IdTokenEnhancer(Signer signer) {
        this.signer = signer;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
            OAuth2Authentication authentication) {
        final OpenIdHelper openIdHelper = new OpenIdHelper(authentication);
        if (openIdHelper.hasOpenIdScope()) {
            final Map<String, Object> idTokenMap = openIdHelper.getIdTokenMap();
            String idTokenContent = OBJECT_MAPPER.formatMap(idTokenMap);
            final Jwt idToken = JwtHelper.encode(idTokenContent, signer);

            final Map<String, Object> additionalInformation = new LinkedHashMap<>();
            additionalInformation.put("id_token", idToken.getEncoded());
            setAdditionalTokenInformation(accessToken, additionalInformation);
        }
        return accessToken;
    }

    private void setAdditionalTokenInformation(OAuth2AccessToken accessToken,
            Map<String, Object> additionalInformation) {
        if (!(accessToken instanceof DefaultOAuth2AccessToken)) {
            throw new IllegalStateException("Unknown access token type: " + accessToken.getClass());
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
    }
}
