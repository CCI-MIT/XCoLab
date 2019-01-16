package org.xcolab.client.user.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SsoClientDetails implements Serializable {

    private static final long serialVersionUID = -1119078696;

    public static final TypeProvider<SsoClientDetails> TYPES = new TypeProvider<>(
            SsoClientDetails.class, new ParameterizedTypeReference<List<SsoClientDetails>>() {});

    private String id;
    private String secret;
    private String scope;
    private String registeredRedirectUri;

    public SsoClientDetails() {}

    public SsoClientDetails(SsoClientDetails value) {
        this.id = value.id;
        this.secret = value.secret;
        this.scope = value.scope;
        this.registeredRedirectUri = value.registeredRedirectUri;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRegisteredRedirectUri() {
        return this.registeredRedirectUri;
    }

    public void setRegisteredRedirectUri(String registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SsoClientDetails)) {
            return false;
        }
        SsoClientDetails that = (SsoClientDetails) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getSecret(), that.getSecret())
                && Objects.equals(getScope(), that.getScope())
                && Objects.equals(getRegisteredRedirectUri(), that.getRegisteredRedirectUri());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSecret(), getScope(), getRegisteredRedirectUri());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("secret", secret)
                .append("scope", scope)
                .append("registeredRedirectUri", registeredRedirectUri)
                .toString();
    }
}
