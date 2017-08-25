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
        this.title = "News Features";
        String testLogoUrl = "http://i.imgur.com/Q1PTZxN.png";
        String testLinkUrl = "https://www.google.com";
        String testAltText = "Thomson Reuters";
        LogoElement logo = new LogoElement(testLogoUrl, testLinkUrl, testAltText);
        this.logos = new ArrayList<>();
        this.logos.add(new LogoElement("http://www.topinfopost.com/wp-content/uploads/2013/06/forbes-logo.png", null, null));
        this.logos.add(new LogoElement("http://logodatabases.com/wp-content/uploads/2012/01/national-geographic-logo.png", null, null));
        this.logos.add(new LogoElement("https://cf.press.discovery.com/ugc/logos/2009/10/22/DSC_DIGITAL_MEDIApos_4c___.jpg", null, null));
        this.logos.add(logo);
        this.logos.add(new LogoElement("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Vox_logo.svg/2000px-Vox_logo.svg.png", null, null));
    }
}
