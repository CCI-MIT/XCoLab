package org.xcolab.view.theme;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.commons.http.servlet.RequestUtil;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.config.spring.beans.SsoClientConfig.SsoServices;

import javax.servlet.http.HttpServletRequest;

public class ThemeContext {

    private final I18nVariables i18NVariables;
    private final AuthenticationVariables authenticationVariables;
    private final CredentialVariables credentialVariables;
    private final ThemeVariables themeVariables;
    private final MetaVariables metaVariables;
    private final MessageVariables messageVariables;
    private final SocialMediaVariables socialMediaVariables;
    private final ServerVariables serverVariables;

    private final String colabName;
    private final String colabLongName;
    private final String colabShortName;
    private final String colabUrl;
    private final String colabUrlProduction;
    private final String blogAdminUrl;
    private final String adminEmail;
    private final ContestType defaultContestType;

    private final String requestUri;

    public ThemeContext(AuthenticationService authenticationService, SsoServices ssoServices,
                        HttpServletRequest request) {

        this.i18NVariables = new I18nVariables();
        this.authenticationVariables = new AuthenticationVariables(authenticationService,
                ssoServices, request);
        this.credentialVariables = new CredentialVariables();
        this.themeVariables = new ThemeVariables(request, this.getI18NVariables());
        this.metaVariables = new MetaVariables(request, this.getI18NVariables());
        this.messageVariables = new MessageVariables(request);
        this.socialMediaVariables = new SocialMediaVariables(request);
        this.serverVariables = new ServerVariables(request.getServletContext());

        this.colabName = ConfigurationAttributeKey.COLAB_NAME.get();
        this.colabLongName = ConfigurationAttributeKey.COLAB_LONG_NAME.get();
        this.colabShortName = ConfigurationAttributeKey.COLAB_SHORT_NAME.get();

        this.colabUrl = PlatformAttributeKey.COLAB_URL.get();
        this.colabUrlProduction = ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get();
        this.blogAdminUrl = ConfigurationAttributeKey.BLOG_ADMIN_URL.get();

        this.adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();

        final Long defaultContestTypeId =
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
        this.defaultContestType = StaticAdminContext.getContestTypeClient()
                .getContestType(defaultContestTypeId, this.getI18NVariables().getLanguage());

        this.requestUri = RequestUtil.getOriginalUri(request);
    }

    public I18nVariables getI18NVariables() {
        return i18NVariables;
    }

    public AuthenticationVariables getAuthenticationVariables() {
        return authenticationVariables;
    }

    public CredentialVariables getCredentialVariables() {
        return credentialVariables;
    }

    public ThemeVariables getThemeVariables() {
        return themeVariables;
    }

    public MetaVariables getMetaVariables() {
        return metaVariables;
    }

    public MessageVariables getMessageVariables() {
        return messageVariables;
    }

    public SocialMediaVariables getSocialMediaVariables() {
        return socialMediaVariables;
    }

    public String getColabName() {
        return colabName;
    }

    public String getColabLongName() {
        return colabLongName;
    }

    public String getColabShortName() {
        return colabShortName;
    }

    public String getColabUrl() {
        return colabUrl;
    }

    public String getColabUrlProduction() {
        return colabUrlProduction;
    }

    public String getBlogAdminUrl() {
        return blogAdminUrl;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public ContestType getDefaultContestType() {
        return defaultContestType;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public ServerVariables getServerVariables() {
        return serverVariables;
    }
}
