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

    public ThemeVariables(HttpServletRequest request, I18nVariables i18NVariables) {
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
                .map(contestType -> contestType.withLocale(i18NVariables.language))
                .collect(Collectors.toList());

        this.isResponsive = PlatformAttributeKey.LAYOUT_IS_RESPONSIVE.get();

        this.themePaths = new HashMap<>();
        // TODO: just expose the activeTheme object, that is enough
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
