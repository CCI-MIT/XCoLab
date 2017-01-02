package org.xcolab.view.pages.loginregister.singlesignon;

public enum SsoEndpoint {
    REGISTER_OR_LOGIN("/sso/registerOrLogin"),
    GOOGLE_LOGIN("/sso/google/login"),
    GOOGLE_REGISTER("/sso/google/register"),
    GOOGLE_CALLBACK("/sso/google/callback"),
    FACEBOOK_LOGIN("/sso/facebook/login"),
    FACEBOOK_REGISTER("/sso/facebook/register"),
    FACEBOOK_CALLBACK("/sso/facebook/callback")
    ;

    private final String url;

    SsoEndpoint(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
