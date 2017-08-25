package org.xcolab.view.pages.carouselwidget;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.entity.utils.WidgetPreference;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.i18n.I18nUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarouselPreferences extends WidgetPreference {
    private final static String TITLE_PREFERENCE = "CAROUSEL_TITLE";
    private final static String LOGOS_PREFERENCE = "CAROUSEL_LOGOS";

    private final static String DEFAULT_TITLE = "Logo Carousel Widget";

    private String title;
    private List<LogoElement> logos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LogoElement> getLogos() {
        return logos;
    }

    public void setLogos(List<LogoElement> logos) {
        this.logos = logos;
    }

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_CONTESTS_PREFERENCES;
    }

    public CarouselPreferences() {
        this(null, I18nUtils.DEFAULT_LANGUAGE);
    }
    public CarouselPreferences(String preferenceId, String locale) {
        super(preferenceId, locale);

//        prefs.getJSONArray()

        if (prefs.has(TITLE_PREFERENCE)) {
            title = prefs.getString(TITLE_PREFERENCE);
        } else {
            title = DEFAULT_TITLE;
        }

        if (prefs.has(LOGOS_PREFERENCE)) {
            JSONArray logosArray = prefs.getJSONArray(LOGOS_PREFERENCE);
            ObjectMapper mapper = new ObjectMapper();
            try {
                logos = mapper.readValue(logosArray.toString(), new TypeReference<List<LogoElement>>() {});
            } catch (IOException ignored) {
            }
        }


//        prefs.

//        title = (prefs.has(TITLE_PREFERENCE))?(prefs.getString(TITLE_PREFERENCE)):( "Featured contests");
//        this.title = "News Features";
//        String testLogoUrl = "http://i.imgur.com/Q1PTZxN.png";
//        String testLinkUrl = "https://www.google.com";
//        String testAltText = "Thomson Reuters";
//        LogoElement logo = new LogoElement(testLogoUrl, testLinkUrl, testAltText);
//        this.logos = new ArrayList<>();
//        this.logos.add(new LogoElement("http://www.topinfopost.com/wp-content/uploads/2013/06/forbes-logo.png", null, null));
//        this.logos.add(new LogoElement("http://logodatabases.com/wp-content/uploads/2012/01/national-geographic-logo.png", null, null));
//        this.logos.add(new LogoElement("https://cf.press.discovery.com/ugc/logos/2009/10/22/DSC_DIGITAL_MEDIApos_4c___.jpg", null, null));
//        this.logos.add(logo);
//        this.logos.add(new LogoElement("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Vox_logo.svg/2000px-Vox_logo.svg.png", null, null));
//
//
//        JSONArray test = new JSONArray(logos);
//        ObjectMapper mapper = new ObjectMapper();
//        List<LogoElement> logoArray = null;
//        try {
//            logoArray = mapper.readValue(test.toString(), new TypeReference<List<LogoElement>>() {});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(logoArray.toString());
//
//        System.out.println(test.toString());
    }

    public void submit() {
        JSONObject prefsToSave = new JSONObject();
        prefsToSave.put(TITLE_PREFERENCE, title);
        prefsToSave.put(LOGOS_PREFERENCE, logos);

        savePreferences(prefsToSave, preferenceId);

    }
}
