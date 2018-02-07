package org.xcolab.util.enums.theme;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public enum ColabTheme {
    CLIMATE_COLAB,
    SOLVE_COLAB,
    CROWDSENSOR,
    RESILIENCE_DIALOGUES,
    CLIMATE_RISKS_COLAB;

    private final String themeName;

    ColabTheme() {
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

    public String getLogoPathSocial() {
        return "/images/" + themeName + "-logo-sketchy.png";
    }

    public String getLogoPathBig() {
        return "/images/" + themeName + "-logo-big.png";
    }

    public String getLogoPathSquare() {
        return "/images/" + themeName + "-logo-square.png";
    }

    public String getCssPath() {
        return "/css/themes/" + themeName;
    }

    public String getOverrideImagePath() {
        return "/static/themes/" + themeName + "/images";
    }
}
