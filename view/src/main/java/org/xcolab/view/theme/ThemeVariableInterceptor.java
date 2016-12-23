package org.xcolab.view.theme;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.util.enums.theme.ColabTheme;
import org.xcolab.view.auth.AuthenticationContext;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ThemeVariableInterceptor extends HandlerInterceptorAdapter {

    private final AuthenticationContext authenticationContext;

    public ThemeVariableInterceptor(AuthenticationContext authenticationContext) {
        Assert.notNull(authenticationContext, "AuthenticationContext is required");
        this.authenticationContext = authenticationContext;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) {
        if (modelAndView != null) {
            final boolean isLoggedIn = authenticationContext.isLoggedIn();
            modelAndView.addObject("_isLoggedIn", isLoggedIn);

            //        final boolean isImpersonating = MemberAuthUtil.isImpersonating(request);
            final boolean isImpersonating = false;
            modelAndView.addObject("_showImpersonationBar", isImpersonating);
            if (isImpersonating) {
                final long realMemberId = MemberAuthUtil.getRealMemberId(request);
                modelAndView
                        .addObject("_realMember", MembersClient.getMemberUnchecked(realMemberId));
            }

            if (isLoggedIn) {
                Member member = authenticationContext.getMemberOrThrow();
                modelAndView.addObject("_member", member);
                modelAndView.addObject("_unreadMessages",
                        MessagingClient.countUnreadMessagesForUser(member.getUserId()));
            }

            ColabTheme activeTheme = ConfigurationAttributeKey.ACTIVE_THEME.get();

            modelAndView.addObject("_themeCssFolder", activeTheme.getCssPath());
            modelAndView.addObject("_themeJsFolder", activeTheme.getJsPath());
            modelAndView.addObject("_themeImageFolder", activeTheme.getImagePath());
            modelAndView.addObject("_libCssFolder", "/css/lib");
            modelAndView.addObject("_libJsFolder", "/js/lib");

            modelAndView.addObject("_logoPath", activeTheme.getLogoPath());
            modelAndView.addObject("_logoPathSocial", activeTheme.getLogoPathSocial());
            modelAndView.addObject("_logoPathBig", activeTheme.getLogoPathBig());

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

            modelAndView
                    .addObject("_isSharedColab", ConfigurationAttributeKey.IS_SHARED_COLAB.get());
            final String partnerColabName = ConfigurationAttributeKey.PARTNER_COLAB_NAME.get();
            final String partnerColabImgsAndClasses = partnerColabName.replace(" ", "");
            modelAndView.addObject("_partnerColabName", partnerColabName);
            modelAndView
                    .addObject("_partnerColabClassName", partnerColabImgsAndClasses + "-sketchy");
            modelAndView
                    .addObject("_partnerColabLogo", partnerColabImgsAndClasses + "PartnerLogo.png");
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
        }
    }
}
