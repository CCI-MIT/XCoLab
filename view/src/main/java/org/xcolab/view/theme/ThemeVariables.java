package org.xcolab.view.theme;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.util.enums.theme.ColabTheme;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

public class ThemeVariables {
    private boolean mitHeaderBarShow;
    private String mitHeaderBarLinkText;
    private String mitHeaderBarLinkUrl;
    private boolean navbarShowIcons;

    private boolean betaRibbonShow;
    private boolean showSearchMenuItem;

    private Long footerArticleId;
    private boolean isHomePage;
    private List contestPages;

    private boolean isResponsive;

    private Map<String, String> themePaths;

    public ThemeVariables(HttpServletRequest request, I18nVariables i18NVariables) {
        this.mitHeaderBarShow = ConfigurationAttributeKey.MIT_HEADER_BAR_SHOW.get();
        if (isMitHeaderBarShow()) {
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

        this.isResponsive = PlatformAttributeKey.LAYOUT_IS_RESPONSIVE.get();

        this.themePaths = new HashMap<>();
        // TODO: just expose the activeTheme object, that is enough
        ColabTheme activeTheme = ConfigurationAttributeKey.ACTIVE_THEME.get();

        //TODO COLAB-2446: move cdn resolution to CdnUrlEncodingFilter
        final String themeImageDomain = PlatformAttributeKey.CDN_URL_IMAGES_STATIC.get();

        getThemePaths().put("_themeStylesheetPath", activeTheme.getStylesheetPath());
        getThemePaths().put("_logoPath", themeImageDomain + activeTheme.getLogoPath());
        getThemePaths().put("_logoPathSocial", themeImageDomain + activeTheme.getLogoPathSocial());
        getThemePaths().put("_logoPathBig", themeImageDomain + activeTheme.getLogoPathBig());
        getThemePaths().put("_logoPathSquare", themeImageDomain + activeTheme.getLogoPathSquare());
    }

    public boolean isMitHeaderBarShow() {
        return mitHeaderBarShow;
    }

    public String getMitHeaderBarLinkText() {
        return mitHeaderBarLinkText;
    }

    public String getMitHeaderBarLinkUrl() {
        return mitHeaderBarLinkUrl;
    }

    public boolean isNavbarShowIcons() {
        return navbarShowIcons;
    }

    public boolean isBetaRibbonShow() {
        return betaRibbonShow;
    }

    public boolean isShowSearchMenuItem() {
        return showSearchMenuItem;
    }

    public Long getFooterArticleId() {
        return footerArticleId;
    }

    public boolean isHomePage() {
        return isHomePage;
    }

    public List getContestPages() {
        return contestPages;
    }

    public boolean isResponsive() {
        return isResponsive;
    }

    public Map<String, String> getThemePaths() {
        return themePaths;
    }
}
