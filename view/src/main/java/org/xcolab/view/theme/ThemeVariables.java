package org.xcolab.view.theme;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.util.enums.theme.ColabTheme;
import org.xcolab.view.auth.AuthenticationContext;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

public class ThemeVariables {
    private final boolean navbarShowIcons;

    private final boolean betaRibbonShow;
    private final boolean showSearchMenuItem;

    private final Long footerArticleId;
    private final boolean isHomePage;
    private final List contestPages;

    private final boolean isRegistrationOpen;
    private final boolean isPointsActive;

    private final boolean isCommentsInOwnTab;

    private final ColabTheme activeTheme;

    private final boolean allowSelfRegistration;

    public ThemeVariables(HttpServletRequest request, I18nVariables i18NVariables) {
        this.navbarShowIcons = ConfigurationAttributeKey.NAVBAR_SHOW_ICONS.get();
        this.betaRibbonShow = ConfigurationAttributeKey.BETA_RIBBON_SHOW.get();
        this.showSearchMenuItem = ConfigurationAttributeKey.SHOW_SEARCH_MENU_ITEM.get();

        this.footerArticleId = ConfigurationAttributeKey.FOOTER_CONTENT_ARTICLE_ID.getOpt()
                .orElse(null);

        this.isHomePage = request.getRequestURI().equals("/");

        final UserWrapper loggedInMember = new AuthenticationContext().getMemberOrNull();
        this.contestPages = StaticAdminContext.getContestTypeClient().getActiveContestTypes()
                .stream()
                .filter(contestType -> {
                    if (!contestType.isRestrictedAccess()) {
                        return true;
                    }
                    if (loggedInMember == null) {
                        return false;
                    }
                    final long userId = loggedInMember.getId();
                    return StaticUserContext.getPermissionClient().hasRoleGroup(userId, contestType.getRoleGroup());
                })
                .map(contestType -> contestType.withLocale(i18NVariables.getLanguage()))
                .collect(Collectors.toList());

        this.isRegistrationOpen = ConfigurationAttributeKey.REGISTRATION_IS_OPEN.get();
        this.isPointsActive = ConfigurationAttributeKey.POINTS_IS_ACTIVE.get();

        this.isCommentsInOwnTab = ConfigurationAttributeKey.PROPOSALS_COMMENTS_IN_SEPARATE_TAB.get();
        this.activeTheme = ConfigurationAttributeKey.ACTIVE_THEME.get();

        this.allowSelfRegistration = ConfigurationAttributeKey.ALLOW_SELF_REGISTRATION.get();
    }

    public boolean getNavbarShowIcons() {
        return navbarShowIcons;
    }

    public boolean getBetaRibbonShow() {
        return betaRibbonShow;
    }

    public boolean getShowSearchMenuItem() {
        return showSearchMenuItem;
    }

    public Long getFooterArticleId() {
        return footerArticleId;
    }

    public boolean getIsHomePage() {
        return isHomePage;
    }

    public List getContestPages() {
        return contestPages;
    }

    public boolean getIsRegistrationOpen() {
        return isRegistrationOpen;
    }

    public boolean getIsPointsActive() {
        return isPointsActive;
    }

    public boolean getIsCommentsInOwnTab() {
        return isCommentsInOwnTab;
    }

    public ColabTheme getActiveTheme() {
        return activeTheme;
    }

    public boolean getAllowSelfRegistration() { return allowSelfRegistration; }
}
