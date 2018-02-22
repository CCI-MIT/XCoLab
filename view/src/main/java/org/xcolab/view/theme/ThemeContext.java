package org.xcolab.view.theme;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.util.http.servlet.RequestUtil;
import org.xcolab.view.auth.AuthenticationService;

import javax.servlet.http.HttpServletRequest;

public class ThemeContext {

    public I18nVariables i18NVariables;
    public AuthenticationVariables authenticationVariables;
    public CredentialVariables credentialVariables;
    public ThemeVariables themeVariables;
    public MetaVariables metaVariables;
    public SharedColabVariables sharedColabVariables;
    public MessageVariables messageVariables;
    public SocialMediaVariables socialMediaVariables;

    public ServerEnvironment serverEnvironment;
    // public boolean isProductionEnvironment;
    // Now use: serverEnvironment.isProduction()

    public String colabName;
    public String colabLongName;
    public String colabShortName;
    public String colabUrl;
    public String colabUrlProduction;
    public String blogAdminUrl;
    public String adminEmail;
    public ContestType defaultContestType;

    public String requestUri;

    private void initSubObjects(AuthenticationService authenticationService,
                                HttpServletRequest request) {
        this.i18NVariables = new I18nVariables();
        this.authenticationVariables = new AuthenticationVariables(authenticationService, request);
        this.credentialVariables = new CredentialVariables();
        this.themeVariables = new ThemeVariables(request, this.i18NVariables);
        this.metaVariables = new MetaVariables(request, this.i18NVariables);
        this.sharedColabVariables = new SharedColabVariables();
        this.messageVariables = new MessageVariables(request);
        this.socialMediaVariables = new SocialMediaVariables(request);
    }

    public ThemeContext(AuthenticationService authenticationService,
                        HttpServletRequest request) {
        this.initSubObjects(authenticationService, request);

        this.serverEnvironment = PlatformAttributeKey.SERVER_ENVIRONMENT.get();

        this.colabName = ConfigurationAttributeKey.COLAB_NAME.get();
        this.colabLongName = ConfigurationAttributeKey.COLAB_LONG_NAME.get();
        this.colabShortName = ConfigurationAttributeKey.COLAB_SHORT_NAME.get();

        this.colabUrl = PlatformAttributeKey.COLAB_URL.get();
        this.colabUrlProduction = ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get();
        this.blogAdminUrl = ConfigurationAttributeKey.BLOG_ADMIN_URL.get();

        this.adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();

        final Long defaultContestTypeId =
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
        this.defaultContestType = ContestTypeClient
                .getContestType(defaultContestTypeId, this.i18NVariables.language);

        this.requestUri = RequestUtil.getOriginalUri(request);
    }

}
