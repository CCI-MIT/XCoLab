package org.xcolab.util.enums.theme;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public enum ColabTheme {
    CLIMATE_COLAB,
    CROWDSENSOR(true),
    RESILIENCE_DIALOGUES,
    CLIMATE_RISKS_COLAB(true);

    private final String themeName;
    private final boolean hasCustomStylesheet;

    ColabTheme() {
        this(false);
    }

    ColabTheme(boolean hasCustomStylesheet) {
        this.hasCustomStylesheet = hasCustomStylesheet;
        final String camelCaseName = WordUtils.capitalizeFully(name(), '_')
                .replaceAll("_", "");
        this.themeName = StringUtils.uncapitalize(camelCaseName);
    }

    public String getThemeName() {
        return themeName;
    }

    public String getLogoPath() {
        return "/images/" + themeName + "-logo.png";
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
