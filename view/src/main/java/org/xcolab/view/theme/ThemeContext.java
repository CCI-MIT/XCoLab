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
import org.xcolab.util.html.LabelStringValue;
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

    class I18n {
        public String language;
        public String locale;
        public String defaultTimeZone;

        public boolean isI18NActive;
        public String currentLocale;
        public List<LabelStringValue> languageSelectItems;

        public I18n() {
            final Locale locale = LocaleContextHolder.getLocale();
            this.language = locale.getLanguage();
            this.locale = locale.toLanguageTag(); // TODO: remove locale and use language instead
            this.defaultTimeZone = ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.get();

            this.isI18NActive = ConfigurationAttributeKey.IS_I18N_ACTIVE.get();
            this.currentLocale = locale.getLanguage(); // TODO: remove and use language instead
            this.languageSelectItems = I18nUtils.getSelectList();
        }
    }

    class AuthenticationContext {
        public boolean isLoggedIn;
        public boolean isImpersonating;
        public Member realMember;
        public Member member;
        public boolean isAdmin;

        public boolean isGoogleSsoActive;
        public boolean isFacebookSsoActive;
        public String facebookId;
        public boolean showLoginPopup;
        public AuthenticationError authError;
        public boolean showPasswordResetPopup;
        public boolean showSsoPopup;

        public AuthenticationContext(AuthenticationService authenticationService,
                              HttpServletRequest request) {
            this.isLoggedIn = authenticationService.isLoggedIn();

            this.isImpersonating = authenticationService.isImpersonating(request);
            if (isImpersonating) {
                this.realMember = authenticationService.getRealMemberOrNull();
            }

            if (isLoggedIn) {
                this.member = authenticationService.getMemberOrThrow(request);
                this.isAdmin = PermissionsClient.canAdminAll(member.getUserId());
            }

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
        }
    }

    class Credentials {
        public String googleAnalyticsKey;
        public String pingdomRumId;
        public String typekitId;
        public String typekitIdLocal;

        public Credentials() {
            this.googleAnalyticsKey = ConfigurationAttributeKey.GOOGLE_ANALYTICS_KEY.get();
            this.pingdomRumId = ConfigurationAttributeKey.PINGDOM_RUM_ID.get();
            this.typekitId = ConfigurationAttributeKey.TYPEKIT_KIT_ID.get();
            this.typekitIdLocal = ConfigurationAttributeKey.TYPEKIT_KIT_ID_LOCALHOST.get();
        }
    }

    class Theme {
        public boolean mitHeaderBarShow;
        public String mitHeaderBarLinkText;
        public String mitHeaderBarLinkUrl;
        public boolean navbarShowIcons;

        public boolean betaRibbonShow;
        public boolean showSearchMenuItem;

        public Long footerArticleId;
        public boolean isHomePage;
        public List contestPages;

        public boolean isResponsive;

        public Map<String, String> themePaths;

        public Theme(HttpServletRequest request, I18n i18n) {
            this.mitHeaderBarShow = ConfigurationAttributeKey.MIT_HEADER_BAR_SHOW.get();
            if (mitHeaderBarShow) {
                this.mitHeaderBarLinkText =
                        ConfigurationAttributeKey.MIT_HEADER_BAR_LINK_TEXT.get();
                this.mitHeaderBarLinkUrl =
                        ConfigurationAttributeKey.MIT_HEADER_BAR_LINK_URL.get();
            }

            this.navbarShowIcons = ConfigurationAttributeKey.NAVBAR_SHOW_ICONS.get();
            this.betaRibbonShow = ConfigurationAttributeKey.BETA_RIBBON_SHOW.get();
            this.showSearchMenuItem = ConfigurationAttributeKey.SHOW_SEARCH_MENU_ITEM.get();

            this.footerArticleId = ConfigurationAttributeKey.FOOTER_CONTENT_ARTICLE_ID.get();

            this.isHomePage = request.getRequestURI().equals("/");

            this.contestPages = ContestTypeClient.getActiveContestTypes().stream()
                    .map(contestType -> contestType.withLocale(i18n.language))
                    .collect(Collectors.toList());

            this.isResponsive = PlatformAttributeKey.LAYOUT_IS_RESPONSIVE.get();

            this.themePaths = new HashMap<>();
            ColabTheme activeTheme = ConfigurationAttributeKey.ACTIVE_THEME.get();

            //TODO COLAB-2446: move cdn resolution to CdnUrlEncodingFilter
            final String themeImageDomain = PlatformAttributeKey.CDN_URL_IMAGES_STATIC.get();

            themePaths.put("_themeStylesheetPath", activeTheme.getStylesheetPath());
            themePaths.put("_logoPath", themeImageDomain + activeTheme.getLogoPath());
            themePaths.put("_logoPathSocial", themeImageDomain + activeTheme.getLogoPathSocial());
            themePaths.put("_logoPathBig", themeImageDomain + activeTheme.getLogoPathBig());
            themePaths.put("_logoPathSquare", themeImageDomain + activeTheme.getLogoPathSquare());
        }
    }

    class Meta {
        public String openGraphShareTitle;
        public String openGraphShareDescription;
        public String metaPageDescription;
        public String metaPageKeywords;

        public Meta(HttpServletRequest request) {
            this.openGraphShareTitle = ConfigurationAttributeKey.OPEN_GRAPH_SHARE_TITLE.get();
            this.openGraphShareDescription = ConfigurationAttributeKey.OPEN_GRAPH_SHARE_DESCRIPTION.get();

            final String metaDescriptionAttribute = (String) request.getAttribute(MetaKeys.DESCRIPTION.getAttributeName());
            if (StringUtils.isNotBlank(metaDescriptionAttribute)) {
                this.metaPageDescription = HtmlUtil.cleanAll(metaDescriptionAttribute);
            } else {
                this.metaPageDescription = ConfigurationAttributeKey.META_PAGE_DESCRIPTION.get(locale.getLanguage());
            }
            this.metaPageKeywords = ConfigurationAttributeKey.META_PAGE_KEYWORDS.get();
        }
    }

    class SharedColab {
        public boolean isSharedColab;

        public String partnerColabName; // TODO: check if these are unsued and remove
        public String partnerColabImgsAndClasses;
        public String partnerColabLogo;

        public SharedColab() {
            this.isSharedColab = ConfigurationAttributeKey.IS_SHARED_COLAB.get();
            if (isSharedColab) {
                this.partnerColabName = ConfigurationAttributeKey.PARTNER_COLAB_NAME.get();
                this.partnerColabImgsAndClasses = partnerColabName.replace(" ", "") + "-sketchy";
                this.partnerColabLogo = partnerColabImgsAndClasses + "PartnerLogo.png";
            }
        }
    }

    class Message {
        public AlertMessage _alertMessage;
        public AnalyticsAttribute _analyticsAttribute;
        public ErrorMessage _errorMessage;
        public InfoMessage _infoMessage;

        public Message(HttpServletRequest request) {
            this._alertMessage = AlertMessage.extract(request);
            this._analyticsAttribute = AnalyticsAttribute.extract(request);
            this._errorMessage = ErrorMessage.extract(request);
            this._infoMessage = InfoMessage.extract(request);
        }
    }

    class SocialMedia {
        public String shareRequestUri;
        public boolean showShareButtons;

        public List<SocialMediaEngine> shareableSocialMediaUrls; // TODO: Rename to shareableEngines
        public List<SocialMediaEngine> followableSocialMediaUrls; // TODO: Rename to followableEngines
        public List<SocialMediaEngine> socialMediaEngines; // TODO: Rename to allEngines
        public String donateLink;

        public SocialMedia(HttpServletRequest request) {
            this.shareRequestUri = SocialMediaEngine
                    .getUtmParameters(ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get(), request);
            this.showShareButtons = ConfigurationAttributeKey.SHOW_SHARE_BUTTONS.get();

            this.shareableSocialMediaUrls = SocialMediaEngine.getShareableSocialMediaEngines();
            this.followableSocialMediaUrls = SocialMediaEngine.getFollowableSocialMediaEngines();
            this.socialMediaEngines = SocialMediaEngine.getAllSocialMediaEngines();

            this.donateLink = ConfigurationAttributeKey.NAVBAR_DONATE_LINK.get();
        }
    }

    public I18n i18n;
    public AuthenticationContext authenticationContext;
    public Credentials credentials;
    public Theme theme;
    public Meta meta;
    public SharedColab sharedColab;
    public Message message;
    public SocialMedia socialMedia;

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
        this.i18n = this.new I18n();
        this.authenticationContext = this.new AuthenticationContext(authenticationService, request);
        this.credentials = this.new Credentials();
        this.theme = this.new Theme(request, this.i18n);
        this.meta = this.new Meta(request);
        this.sharedColab = this.new SharedColab();
        this.message = this.new Message(request);
        this.socialMedia = this.new SocialMedia(request);
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
                .getContestType(defaultContestTypeId, this.i18n.language);

        this.requestUri = RequestUtil.getOriginalUri(request);
    }

    private boolean readBooleanParameter(HttpServletRequest request, String name) {
        return Boolean.parseBoolean(request.getParameter(name));
    }

}
