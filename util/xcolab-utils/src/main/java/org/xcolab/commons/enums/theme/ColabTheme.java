package org.xcolab.commons.enums.theme;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public enum ColabTheme {
    CLIMATE_COLAB(205),
    CROWDSENSOR(109, true),
    RESILIENCE_DIALOGUES(136),
    CLIMATE_RISKS_COLAB(145, true);

    private final String themeName;
    private final int logoWidth;
    private final boolean hasCustomStylesheet;

    ColabTheme(int logoWidth) {
        this(logoWidth, false);
    }

    ColabTheme(int logoWidth, boolean hasCustomStylesheet) {
        this.logoWidth = logoWidth;
        this.hasCustomStylesheet = hasCustomStylesheet;
        final String camelCaseName = WordUtils.capitalizeFully(name(), '_')
                .replaceAll("_", "");
        this.themeName = StringUtils.uncapitalize(camelCaseName);
    }

    public String getThemeName() {
        return themeName;
    }

    public String getLogoPath() {
        return "/images/" + themeName + "-logo";
    }

    public int getLogoWidth() {
        return logoWidth;
    }

    public String getLogoPathBig() {
        return "/images/" + themeName + "-logo-big.png";
    }

    public String getLogoPathSquare() {
        return "/images/" + themeName + "-logo-square.png";
    }

    public String getStylesheetPath() {
        if (hasCustomStylesheet) {
            return "/css/themes/" + themeName + ".css";
        }
        return "/css/main.css";
    }

    public String getOverrideImagePath() {
        return "/static/themes/" + themeName + "/images";
    }
}
