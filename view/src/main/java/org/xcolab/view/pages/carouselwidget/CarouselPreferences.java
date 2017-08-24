package org.xcolab.view.pages.carouselwidget;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.entity.utils.WidgetPreference;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.i18n.I18nUtils;

import java.util.ArrayList;
import java.util.List;

public class CarouselPreferences extends WidgetPreference {
    private String title;
    private List<LogoElement> logos;

    public String getTitle() {
        return title;
    }
    public List<LogoElement> getLogos() {
        return logos;
    }

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_CONTESTS_PREFERENCES;
    }

    public CarouselPreferences() {
        this(null, I18nUtils.DEFAULT_LANGUAGE);
    }
    public CarouselPreferences(String preferenceId, String locale) {
        this.title = "LOLOLOL";
        String testLogoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Thomson_Reuters_logo.svg/2000px-Thomson_Reuters_logo.svg.png";
        String testLinkUrl = "https://www.google.com";
        String testAltText = "Thomson Reuters";
        LogoElement logo = new LogoElement(testLogoUrl, testLinkUrl, testAltText);
        this.logos = new ArrayList<>();
        this.logos.add(logo);
        this.logos.add(logo);
        this.logos.add(logo);
    }
}
