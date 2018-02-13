package org.xcolab.view.theme;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.theme.ColabTheme;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.util.http.servlet.RequestUtil;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.auth.login.AuthenticationError;
import org.xcolab.view.socialmedia.SocialMediaEngine;
import org.xcolab.view.util.MetaKeys;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.util.entity.flash.AnalyticsAttribute;
import org.xcolab.view.util.entity.flash.ErrorMessage;
import org.xcolab.view.util.entity.flash.InfoMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

public class ThemeContext {

    public String language;
    public String locale;
    public String defaultTimeZone;
    public boolean isLoggedIn;
    public boolean isImpersonating;
    public Member realMember;
    public Member member;
    public boolean isAdmin;
    public ServerEnvironment serverEnvironment;
    public boolean isProductionEnvironment;

    public String colabName;
    public String colabLongName;
    public String colabShortName;
    public String colabUrl;
    public String colabUrlProduction;
    public String blogAdminUrl;

    public String googleAnalyticsKey;
    public String pingdomRumId;
    public String typekitId;
    public String typekitIdLocal;

    public boolean mitHeaderBarShow;
    public String mitHeaderBarLinkText;
    public String mitHeaderBarLinkUrl;
    public boolean navbarShowIcons;

    public boolean betaRibbonShow;
    public boolean showSearchMenuItem;
    public String openGraphShareTitle;
    public String openGraphShareDescription;
    public String metaPageDescription;
    public String metaPageKeywords;
    public Long footerArticleId;

    public boolean isSharedColab;
    public AuthenticationError authError;

    public String partnerColabName;
    public String partnerColabImgsAndClasses;
    public String partnerColabLogo;

    public boolean isI18NActive;
    public String currentLocale;
    public List languageSelectItems;
    public String adminEmail;
    public ContestType defaultContestType;

    public boolean isGoogleSsoActive;
    public boolean isFacebookSsoActive;
    public String facebookId;
    public boolean showLoginPopup;
    public boolean showPasswordResetPopup;
    public boolean showSsoPopup;

    public String requestUri;
    public boolean isHomePage;

    public AlertMessage _alertMessage;
    public AnalyticsAttribute _analyticsAttribute;
    public ErrorMessage _errorMessage;
    public InfoMessage _infoMessage;

    public String shareRequestUri;
    public boolean showShareButtons;

    public List shareableSocialMediaUrls;
    public List followableSocialMediaUrls;
    public List socialMediaEngines;
    public String donateLink;

    public List contestPages;
    public Map<String, String> themePaths;

    public ThemeContext(AuthenticationService authenticationService,
                        HttpServletRequest request) {
        this.themePaths = new HashMap<>();

        final Locale locale = LocaleContextHolder.getLocale();
        this.language = locale.getLanguage();
        this.locale = locale.toLanguageTag();
        this.defaultTimeZone = ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.get();
        this.isLoggedIn = authenticationService.isLoggedIn();

        this.isImpersonating = authenticationService.isImpersonating(request);
        if (isImpersonating) {
            this.realMember = authenticationService.getRealMemberOrNull();
        }

        if (isLoggedIn) {
            this.member = authenticationService.getMemberOrThrow(request);
            this.isAdmin = PermissionsClient.canAdminAll(member.getUserId());
        }

        this.serverEnvironment = PlatformAttributeKey.SERVER_ENVIRONMENT.get();
        this.isProductionEnvironment = serverEnvironment == ServerEnvironment.PRODUCTION;

        ColabTheme activeTheme = ConfigurationAttributeKey.ACTIVE_THEME.get();

        //TODO COLAB-2446: move cdn resolution to CdnUrlEncodingFilter
        final String themeImageDomain = PlatformAttributeKey.CDN_URL_IMAGES_STATIC.get();

        themePaths.put("_themeStylesheetPath", activeTheme.getStylesheetPath());
        themePaths.put("_logoPath", themeImageDomain + activeTheme.getLogoPath());
        themePaths.put("_logoPathSocial", themeImageDomain + activeTheme.getLogoPathSocial());
        themePaths.put("_logoPathBig", themeImageDomain + activeTheme.getLogoPathBig());
        themePaths.put("_logoPathSquare", themeImageDomain + activeTheme.getLogoPathSquare());

        this.contestPages = ContestTypeClient.getActiveContestTypes().stream()
                .map(contestType -> contestType.withLocale(locale.getLanguage()))
                .collect(Collectors.toList());

        this.colabName = ConfigurationAttributeKey.COLAB_NAME.get();
        this.colabLongName = ConfigurationAttributeKey.COLAB_LONG_NAME.get();
        this.colabShortName = ConfigurationAttributeKey.COLAB_SHORT_NAME.get();

        this.colabUrl = PlatformAttributeKey.COLAB_URL.get();
        this.colabUrlProduction = ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get();
        this.blogAdminUrl = ConfigurationAttributeKey.BLOG_ADMIN_URL.get();

        this.googleAnalyticsKey = ConfigurationAttributeKey.GOOGLE_ANALYTICS_KEY.get();
        this.pingdomRumId = ConfigurationAttributeKey.PINGDOM_RUM_ID.get();
        this.typekitId = ConfigurationAttributeKey.TYPEKIT_KIT_ID.get();
        this.typekitIdLocal = ConfigurationAttributeKey.TYPEKIT_KIT_ID_LOCALHOST.get();

        this.betaRibbonShow = ConfigurationAttributeKey.BETA_RIBBON_SHOW.get();
        this.showSearchMenuItem = ConfigurationAttributeKey.SHOW_SEARCH_MENU_ITEM.get();
        this.openGraphShareTitle = ConfigurationAttributeKey.OPEN_GRAPH_SHARE_TITLE.get();
        this.openGraphShareDescription = ConfigurationAttributeKey.OPEN_GRAPH_SHARE_DESCRIPTION.get();

        final String metaDescriptionAttribute = (String) request.getAttribute(MetaKeys.DESCRIPTION.getAttributeName());
        if (StringUtils.isNotBlank(metaDescriptionAttribute)) {
            this.metaPageDescription = HtmlUtil.cleanAll(metaDescriptionAttribute);
        } else {
            this.metaPageDescription = ConfigurationAttributeKey.META_PAGE_DESCRIPTION.get(locale.getLanguage());
        }
        this.metaPageKeywords = ConfigurationAttributeKey.META_PAGE_KEYWORDS.get();
        this.footerArticleId = ConfigurationAttributeKey.FOOTER_CONTENT_ARTICLE_ID.get();

        this.isSharedColab = ConfigurationAttributeKey.IS_SHARED_COLAB.get();
        if (isSharedColab) {
            this.partnerColabName = ConfigurationAttributeKey.PARTNER_COLAB_NAME.get();
            this.partnerColabImgsAndClasses = partnerColabName.replace(" ", "") + "-sketchy";
            this.partnerColabLogo = partnerColabImgsAndClasses + "PartnerLogo.png";
        }

        this.isI18NActive = ConfigurationAttributeKey.IS_I18N_ACTIVE.get();
        this.currentLocale = locale.getLanguage();
        this.languageSelectItems = I18nUtils.getSelectList();
        this.adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();

        final Long defaultContestTypeId =
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
        this.defaultContestType = ContestTypeClient
                .getContestType(defaultContestTypeId, locale.getLanguage());

        this.mitHeaderBarShow = ConfigurationAttributeKey.MIT_HEADER_BAR_SHOW.get();
        if (mitHeaderBarShow) {
            this.mitHeaderBarLinkText =
                    ConfigurationAttributeKey.MIT_HEADER_BAR_LINK_TEXT.get();
            this.mitHeaderBarLinkUrl =
                    ConfigurationAttributeKey.MIT_HEADER_BAR_LINK_URL.get();
        }

        this.navbarShowIcons = ConfigurationAttributeKey.NAVBAR_SHOW_ICONS.get();

        boolean isSigningIn = readBooleanParameter(request, "isSigningIn");
        boolean isPasswordReminder = readBooleanParameter(request, "isPasswordReminder");
        boolean isSSOSigningIn = readBooleanParameter(request, "isSSOSigningIn");
        if (isSigningIn) {
            this.authError
                    = AuthenticationError.fromName(request.getParameter("signinRegError"));
        }

        this.isGoogleSsoActive = ConfigurationAttributeKey.GOOGLE_SSO_IS_ACTIVE.get();
        this.isFacebookSsoActive = ConfigurationAttributeKey.FACEBOOK_SSO_IS_ACTIVE.get();
        this.facebookId = ConfigurationAttributeKey.FACEBOOK_APPLICATION_ID.get();

        this.showLoginPopup = isSigningIn;
        this.showPasswordResetPopup = isPasswordReminder;
        this.showSsoPopup = isSSOSigningIn;

        this.requestUri = RequestUtil.getOriginalUri(request);
        this.isHomePage = request.getRequestURI().equals("/");

        this._alertMessage = AlertMessage.extract(request);
        this._analyticsAttribute = AnalyticsAttribute.extract(request);
        this._errorMessage = ErrorMessage.extract(request);
        this._infoMessage = InfoMessage.extract(request);

        this.shareRequestUri = SocialMediaEngine
                .getUtmParameters(ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get(), request);
        this.showShareButtons = ConfigurationAttributeKey.SHOW_SHARE_BUTTONS.get();

        this.shareableSocialMediaUrls = SocialMediaEngine.getShareableSocialMediaEngines();
        this.followableSocialMediaUrls = SocialMediaEngine.getFollowableSocialMediaEngines();
        this.socialMediaEngines = SocialMediaEngine.getAllSocialMediaEngines();

        this.donateLink = ConfigurationAttributeKey.NAVBAR_DONATE_LINK.get();
    }

    private boolean readBooleanParameter(HttpServletRequest request, String name) {
        return Boolean.parseBoolean(request.getParameter(name));
    }

}
