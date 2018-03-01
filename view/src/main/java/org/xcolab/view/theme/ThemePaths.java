package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.util.enums.theme.ColabTheme;

public class ThemePaths {

    private final String themeStylesheetPath;
    private final String logoPath;
    private final String logoPathSocial;
    private final String logoPathBig;
    private final String logoPathSquare;

    public ThemePaths() {
        // TODO: just expose the activeTheme object, that is enough
        ColabTheme activeTheme = ConfigurationAttributeKey.ACTIVE_THEME.get();

        //TODO COLAB-2446: move cdn resolution to CdnUrlEncodingFilter
        final String themeImageDomain = PlatformAttributeKey.CDN_URL_IMAGES_STATIC.get();

        this.themeStylesheetPath = activeTheme.getStylesheetPath();
        this.logoPath = themeImageDomain + activeTheme.getLogoPath();
        this.logoPathSocial = themeImageDomain + activeTheme.getLogoPathSocial();
        this.logoPathBig = themeImageDomain + activeTheme.getLogoPathBig();
        this.logoPathSquare = themeImageDomain + activeTheme.getLogoPathSquare();
    }

    public String getThemeStylesheetPath() {
        return themeStylesheetPath;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getLogoPathSocial() {
        return logoPathSocial;
    }

    public String getLogoPathBig() {
        return logoPathBig;
    }

    public String getLogoPathSquare() {
        return logoPathSquare;
    }

}
