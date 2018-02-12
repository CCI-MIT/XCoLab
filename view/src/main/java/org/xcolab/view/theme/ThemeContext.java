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

    public Map<String, String> themePaths;

    public ThemeContext() {
        this.themePaths = new HashMap<>();
    }

    private void init(AuthenticationService authenticationService, HttpServletRequest request) {
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


        modelAndView.addObject("_contestPages", ContestTypeClient
                .getActiveContestTypes().stream()
                .map(contestType -> contestType.withLocale(locale.getLanguage()))
                .collect(Collectors.toList()));

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

        modelAndView
                .addObject("_betaRibbonShow", ConfigurationAttributeKey.BETA_RIBBON_SHOW.get());
        modelAndView.addObject("_showSearchMenuItem",
                ConfigurationAttributeKey.SHOW_SEARCH_MENU_ITEM.get());
        modelAndView.addObject("_openGraphShareTitle",
                ConfigurationAttributeKey.OPEN_GRAPH_SHARE_TITLE.get());
        modelAndView.addObject("_openGraphShareDescription",
                ConfigurationAttributeKey.OPEN_GRAPH_SHARE_DESCRIPTION.get());

        final String metaDescriptionAttribute = (String) request.getAttribute(MetaKeys.DESCRIPTION.getAttributeName());
        if (StringUtils.isNotBlank(metaDescriptionAttribute)) {
            modelAndView.addObject("_metaPageDescription", HtmlUtil.cleanAll(metaDescriptionAttribute));
        } else {
            modelAndView.addObject("_metaPageDescription",
                    ConfigurationAttributeKey.META_PAGE_DESCRIPTION.get(locale.getLanguage()));
        }
        modelAndView.addObject("_metaPageKeywords", ConfigurationAttributeKey.META_PAGE_KEYWORDS.get());

        modelAndView
                .addObject("_footerArticleId", ConfigurationAttributeKey.FOOTER_CONTENT_ARTICLE_ID.get());


        final Boolean isSharedColab = ConfigurationAttributeKey.IS_SHARED_COLAB.get();
        modelAndView.addObject("_isSharedColab", isSharedColab);

        if (isSharedColab) {
            final String partnerColabName = ConfigurationAttributeKey.PARTNER_COLAB_NAME.get();
            modelAndView.addObject("_partnerColabName", partnerColabName);
            final String partnerColabImgsAndClasses = partnerColabName.replace(" ", "");
            modelAndView.addObject("_partnerColabClassName", partnerColabImgsAndClasses + "-sketchy");
            modelAndView.addObject("_partnerColabLogo", partnerColabImgsAndClasses +
                    "PartnerLogo.png");
        }

        modelAndView.addObject("_isI18NActive",ConfigurationAttributeKey.IS_I18N_ACTIVE.get());
        modelAndView.addObject("_currentLocale",locale.getLanguage());
        modelAndView.addObject("_languageSelectItems", I18nUtils.getSelectList());


        modelAndView.addObject("_adminEmail", ConfigurationAttributeKey.ADMIN_EMAIL.get());

        final Long defaultContestTypeId =
                ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
        final ContestType defaultContestType = ContestTypeClient
                .getContestType(defaultContestTypeId, locale.getLanguage());
        modelAndView.addObject("_defaultContestType", defaultContestType);

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
            final AuthenticationError authError
                    = AuthenticationError.fromName(request.getParameter("signinRegError"));
            modelAndView.addObject("_authError", authError);
        }

        modelAndView.addObject("_isGoogleSsoActive", ConfigurationAttributeKey.GOOGLE_SSO_IS_ACTIVE.get());
        modelAndView.addObject("_isFacebookSsoActive", ConfigurationAttributeKey.FACEBOOK_SSO_IS_ACTIVE.get());
        modelAndView.addObject("_facebookId", ConfigurationAttributeKey.FACEBOOK_APPLICATION_ID.get());



        modelAndView.addObject("_showLoginPopup", isSigningIn);
        modelAndView.addObject("_showPasswordResetPopup", isPasswordReminder);
        modelAndView.addObject("_showSsoPopup", isSSOSigningIn);

        modelAndView.addObject("_requestUri", RequestUtil.getOriginalUri(request));
        modelAndView.addObject("_isHomePage", request.getRequestURI().equals("/"));

        modelAndView.addObject("__alertMessage", AlertMessage.extract(request));
        modelAndView.addObject("__analyticsAttribute", AnalyticsAttribute.extract(request));
        modelAndView.addObject("__errorMessage", ErrorMessage.extract(request));
        modelAndView.addObject("__infoMessage", InfoMessage.extract(request));

        modelAndView.addObject("_shareRequestUri", SocialMediaEngine
                .getUtmParameters(ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get(), request));
        modelAndView.addObject("_facebookId", ConfigurationAttributeKey.FACEBOOK_APPLICATION_ID.get());

        modelAndView.addObject("_showShareButtons",
                ConfigurationAttributeKey.SHOW_SHARE_BUTTONS.get());

        modelAndView.addObject("_shareableSocialMediaUrls", SocialMediaEngine.getShareableSocialMediaEngines());
        modelAndView.addObject("_followableSocialMediaUrls", SocialMediaEngine.getFollowableSocialMediaEngines());
        modelAndView.addObject("_socialMediaEngines", SocialMediaEngine.getAllSocialMediaEngines());

        modelAndView.addObject("_donateLink", ConfigurationAttributeKey.NAVBAR_DONATE_LINK.get());
    }

    private boolean readBooleanParameter(HttpServletRequest request, String name) {
        return Boolean.parseBoolean(request.getParameter(name));
    }

}
