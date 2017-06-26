package org.xcolab.view.theme;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.enums.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ThemeVariableInterceptor extends HandlerInterceptorAdapter {

    private final AuthenticationService authenticationService;

    public ThemeVariableInterceptor(AuthenticationService authenticationService) {
        Assert.notNull(authenticationService, "AuthenticationContext is required");
        this.authenticationService = authenticationService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {
        if (modelAndView != null && !isRedirectView(modelAndView)) {
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
                    PlatformAttributeKey.PLATFORM_SERVER_ENVIRONMENT.get();
            modelAndView.addObject("_isProduction",
                    serverEnvironment == ServerEnvironment.PRODUCTION);

            ColabTheme activeTheme = ConfigurationAttributeKey.ACTIVE_THEME.get();

            final String scriptDomain = PlatformAttributeKey.PLATFORM_SCRIPT_DOMAIN.get();
            final String themeImageDomain = PlatformAttributeKey.PLATFORM_STATIC_IMAGE_DOMAIN.get();
            final String userImageDomain = PlatformAttributeKey.PLATFORM_UPLOADED_IMAGE_DOMAIN.get();

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

            modelAndView.addObject("_contestPages", ContestClientUtil.getActiveContestTypes());
            modelAndView.addObject("_colabName", ConfigurationAttributeKey.COLAB_NAME.get());
            modelAndView
                    .addObject("_colabShortName", ConfigurationAttributeKey.COLAB_SHORT_NAME.get());
            modelAndView.addObject("_googleAnalyticsKey",
                    ConfigurationAttributeKey.GOOGLE_ANALYTICS_KEY.get());

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
                modelAndView.addObject("_metaPageDescription", ConfigurationAttributeKey.META_PAGE_DESCRIPTION.get());
            }
            final String metaKeywordsAttribute = (String) request.getAttribute(MetaKeys.KEYWORDS.getAttributeName());
            if (StringUtils.isNotBlank(metaKeywordsAttribute)) {
                modelAndView.addObject("_metaPageKeywords", metaKeywordsAttribute);
            } else {
                modelAndView.addObject("_metaPageKeywords", ConfigurationAttributeKey.META_PAGE_KEYWORDS.get());
            }

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

            List<ContestType> contestTypes = ContestClientUtil.getAllContestTypes();
            if (!contestTypes.isEmpty()) {
                modelAndView.addObject("_contestNameLowerCase",
                        contestTypes.get(contestTypes.size() - 1).getContestName().toLowerCase());
                modelAndView.addObject("_proposalNameLowerCase",
                        contestTypes.get(contestTypes.size() - 1).getProposalName().toLowerCase());
            }

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
