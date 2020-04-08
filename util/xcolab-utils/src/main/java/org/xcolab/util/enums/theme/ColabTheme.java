package org.xcolab.util.enums.theme;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public enum ColabTheme {
    CLIMATE_COLAB("30a3fb", 205),
    CROWDSENSOR("d71a11", 109, true),
    RESILIENCE_DIALOGUES("30a3fb", 136),
    CLIMATE_RISKS_COLAB("aa2029", 145, true),
    FUTURES_COLAB("30a3fb", 145, true),
    TRUST_COLAB("30a3fb", 145, true),
    COMPASS_COLAB("30a3fb", 145, true),
    COVID_COLAB("30a3fb", 145, true)
    ;

    private final String themeName;
    private final int logoWidth;
    private final boolean hasCustomStylesheet;
    private final String primaryColorHex;

    ColabTheme(String primaryColorHex, int logoWidth) {
        this(primaryColorHex, logoWidth, false);
    }

    ColabTheme(String primaryColorHex, int logoWidth, boolean hasCustomStylesheet) {
        this.primaryColorHex = primaryColorHex;
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

    public String getPrimaryColorHex() {
        return primaryColorHex;
    }
}
