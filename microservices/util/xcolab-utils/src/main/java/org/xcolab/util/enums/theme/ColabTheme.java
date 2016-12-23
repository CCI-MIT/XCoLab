package org.xcolab.util.enums.theme;

public enum ColabTheme {
    CLIMATE_COLAB("climateColab"),
    SOLVE_COLAB("solveColab"),
    CROWDSENSOR("crowdsensor"),
    RESILIENCE_DIALOGUES("resilienceDialogues");

    private final String themeName;

    ColabTheme(String themeName) {
        this.themeName = themeName;
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
