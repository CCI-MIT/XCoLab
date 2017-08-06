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
        return getImagePath() + "/" + themeName + "-logo.png";
    }

    public String getLogoPathSocial() {
        return getImagePath() + "/" + themeName + "-logo-sketchy.png";
    }

    public String getLogoPathBig() {
        return getImagePath() + "/" + themeName + "-logo-big.png";
    }

    public String getLogoPathSquare() {
        return getImagePath() + "/" + themeName + "-logo-square.png";
    }

    public String getCssPath() {
        return "/css/themes/" + themeName;
    }

    public String getJsPath() {
        return "/js";
    }

    public String getImagePath() {
        return "/images";
    }

    public String getOverrideImagePath() {
        return "/static/themes/" + themeName + "/images";
    }
}
