package org.xcolab.client.user.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.time.Instant;
import java.util.List;

public class LoginToken {

    public static final TypeProvider<LoginToken> TYPES = new TypeProvider<>(LoginToken.class,
            new ParameterizedTypeReference<List<LoginToken>>() {});

    private String tokenId;
    private String tokenKey;
    private Instant tokenExpirationDate;

    public LoginToken() {
    }

    public LoginToken(String tokenId, String tokenKey, Instant tokenExpirationDate) {
        this.tokenId = tokenId;
        this.tokenKey = tokenKey;
        this.tokenExpirationDate = tokenExpirationDate;
    }

    public String getTokenId() {
        return tokenId;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public Instant getTokenExpirationDate() {
        return tokenExpirationDate;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public void setTokenExpirationDate(Instant tokenExpirationDate) {
        this.tokenExpirationDate = tokenExpirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoginToken)) {
            return false;
        }

        LoginToken that = (LoginToken) o;

        if (!tokenId.equals(that.tokenId)) {
            return false;
        }
        if (!tokenKey.equals(that.tokenKey)) {
            return false;
        }
        return tokenExpirationDate != null ? tokenExpirationDate.equals(that.tokenExpirationDate)
                : that.tokenExpirationDate == null;
    }

    @Override
    public int hashCode() {
        int result = tokenId.hashCode();
        result = 31 * result + tokenKey.hashCode();
        result = 31 * result + (tokenExpirationDate != null ? tokenExpirationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LoginToken{" + "tokenId='" + tokenId + '\'' + ", tokenKey='" + tokenKey + '\''
                + ", tokenExpirationDate=" + tokenExpirationDate + '}';
    }
}
