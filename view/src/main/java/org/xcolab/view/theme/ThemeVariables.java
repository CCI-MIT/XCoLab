package org.xcolab.view.theme;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.util.enums.theme.ColabTheme;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

public class ThemeVariables {
    private final boolean mitHeaderBarShow;
    private String mitHeaderBarLinkText;
    private String mitHeaderBarLinkUrl;
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
        this.mitHeaderBarShow = ConfigurationAttributeKey.MIT_HEADER_BAR_SHOW.get();
        if (getMitHeaderBarShow()) {
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
                .map(contestType -> contestType.withLocale(i18NVariables.getLanguage()))
                .collect(Collectors.toList());

        this.isRegistrationOpen = ConfigurationAttributeKey.REGISTRATION_IS_OPEN.get();
        this.isPointsActive = ConfigurationAttributeKey.POINTS_IS_ACTIVE.get();

        this.isCommentsInOwnTab = ConfigurationAttributeKey.PROPOSALS_COMMENTS_IN_SEPARATE_TAB.get();
        this.activeTheme = ConfigurationAttributeKey.ACTIVE_THEME.get();

        this.allowSelfRegistration = ConfigurationAttributeKey.ALLOW_SELF_REGISTRATION.get();
    }

    public boolean getMitHeaderBarShow() {
        return mitHeaderBarShow;
    }

    public String getMitHeaderBarLinkText() {
        return mitHeaderBarLinkText;
    }

    public String getMitHeaderBarLinkUrl() {
        return mitHeaderBarLinkUrl;
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
