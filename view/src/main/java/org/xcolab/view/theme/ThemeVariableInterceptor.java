package org.xcolab.view.theme;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.theme.ColabTheme;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.auth.login.AuthenticationError;
import org.xcolab.view.util.MetaKeys;
import org.xcolab.view.util.entity.flash.AlertMessage;
import org.xcolab.view.util.entity.flash.AnalyticsAttribute;
import org.xcolab.view.util.entity.flash.ErrorMessage;
import org.xcolab.view.util.entity.flash.InfoMessage;

import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ThemeVariableInterceptor extends HandlerInterceptorAdapter {

    private final AuthenticationService authenticationService;
    private final LocaleResolver localeResolver;

    public ThemeVariableInterceptor(AuthenticationService authenticationService,
            LocaleResolver localeResolver) {
        Assert.notNull(authenticationService, "AuthenticationContext is required");
        Assert.notNull(localeResolver, "LocaleResolver is required");
        this.authenticationService = authenticationService;
        this.localeResolver = localeResolver;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {
        if (modelAndView != null && !isRedirectView(modelAndView)) {
            final Locale locale = localeResolver.resolveLocale(request);
            final boolean isLoggedIn = authenticationService.isLoggedIn();
            modelAndView.addObject("_isLoggedIn", isLoggedIn);

            final boolean isImpersonating = authenticationService.isImpersonating(request);
            modelAndView.addObject("_showImpersonationBar", isImpersonating);
            if (isImpersonating) {
                final Member realMember = authenticationService.getRealMemberOrNull();
                modelAndView.addObject("_realMember", realMember);
            }

            if (isLoggedIn) {
                Member member = authenticationService.getMemberOrThrow(request);
                modelAndView.addObject("_member", member);
                modelAndView.addObject("_unreadMessages",
                        MessagingClient.countUnreadMessagesForUser(member.getUserId()));
                boolean isAdmin = PermissionsClient.canAdminAll(member.getUserId());
                modelAndView.addObject("_isAdmin", isAdmin);
            }

            final ServerEnvironment serverEnvironment =
                    PlatformAttributeKey.SERVER_ENVIRONMENT.get();
            final boolean isProductionEnvironment = serverEnvironment == ServerEnvironment.PRODUCTION;
            modelAndView.addObject("_isProduction", isProductionEnvironment);

            ColabTheme activeTheme = ConfigurationAttributeKey.ACTIVE_THEME.get();

            final String scriptDomain = PlatformAttributeKey.SCRIPTS_DOMAIN.get();
            final String themeImageDomain = PlatformAttributeKey.IMAGES_STATIC_DOMAIN.get();
            final String userImageDomain = PlatformAttributeKey.IMAGES_UPLOADED_DOMAIN.get();

            modelAndView.addObject("_libCssFolder",
                    scriptDomain + "/css/lib");
            modelAndView.addObject("_libJsFolder",
                    scriptDomain + "/js/lib");
            modelAndView.addObject("_themeCssFolder",
                    scriptDomain + activeTheme.getCssPath());
            modelAndView.addObject("_themeJsFolder",
                    scriptDomain + activeTheme.getJsPath());
            modelAndView.addObject("_themeImageFolder",
                    themeImageDomain + activeTheme.getImagePath());
            modelAndView.addObject("_uploadedImageFolder",
                    userImageDomain + "/image");
            modelAndView.addObject("_logoPath",
                    themeImageDomain + activeTheme.getLogoPath());
            modelAndView.addObject("_logoPathSocial",
                    themeImageDomain + activeTheme.getLogoPathSocial());
            modelAndView.addObject("_logoPathBig",
                    themeImageDomain + activeTheme.getLogoPathBig());

            modelAndView.addObject("_contestPages", ContestTypeClient
                    .getActiveContestTypes().stream()
                            .map(contestType -> contestType.withLocale(locale.getLanguage()))
                            .collect(Collectors.toList()));
            modelAndView.addObject("_colabName", ConfigurationAttributeKey.COLAB_NAME.get());
            modelAndView.addObject("_colabUrl", PlatformAttributeKey.COLAB_URL.get());
            modelAndView
                    .addObject("_colabShortName", ConfigurationAttributeKey.COLAB_SHORT_NAME.get());
            final String googleAnalyticsKey = ConfigurationAttributeKey.GOOGLE_ANALYTICS_KEY.get();
            modelAndView.addObject("_googleAnalyticsKey", googleAnalyticsKey);

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

            modelAndView.addObject("_adminEmail", ConfigurationAttributeKey.ADMIN_EMAIL.get());

            final Long defaultContestTypeId =
                    ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
            final ContestType defaultContestType = ContestTypeClient
                    .getContestType(defaultContestTypeId, locale.getLanguage());
            modelAndView.addObject("_contestNameLowerCase",
                    defaultContestType.getContestNameLowercase());
            modelAndView.addObject("_proposalNameLowerCase",
                    defaultContestType.getProposalNameLowercase());

            final boolean mitHeaderBarShow = ConfigurationAttributeKey.MIT_HEADER_BAR_SHOW.get();
            modelAndView.addObject("_mitHeaderBarShow", mitHeaderBarShow);
            if (mitHeaderBarShow) {
                modelAndView.addObject("_mitHeaderBarLinkText",
                        ConfigurationAttributeKey.MIT_HEADER_BAR_LINK_TEXT.get());
                modelAndView.addObject("_mitHeaderBarLinkUrl",
                        ConfigurationAttributeKey.MIT_HEADER_BAR_LINK_URL.get());
            }

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

            modelAndView.addObject("_requestUri", request.getRequestURI());
            modelAndView.addObject("_isHomePage", request.getRequestURI().equals("/"));

            modelAndView.addObject("__alertMessage", AlertMessage.extract(request));
            modelAndView.addObject("__analyticsAttribute", AnalyticsAttribute.extract(request));
            modelAndView.addObject("__errorMessage", ErrorMessage.extract(request));
            modelAndView.addObject("__infoMessage", InfoMessage.extract(request));
        }
    }

    private boolean readBooleanParameter(HttpServletRequest request, String name) {
        return Boolean.parseBoolean(request.getParameter(name));
    }

    private boolean isRedirectView(ModelAndView modelAndView) {
        return (modelAndView.getView() != null && modelAndView.getView() instanceof RedirectView)
                || modelAndView.getViewName().startsWith("redirect:");
    }

}
