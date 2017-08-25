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
    private Integer logosCount;

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

    public Integer getLogosCount() {
        return logosCount;
    }

    public void setLogosCount(Integer logosCount) {
        this.logosCount = logosCount;
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
    }

    public void submit() {
        JSONObject prefsToSave = new JSONObject();
        prefsToSave.put(TITLE_PREFERENCE, title);
        prefsToSave.put(LOGOS_PREFERENCE, logos);

        savePreferences(prefsToSave, preferenceId);

    }
}
