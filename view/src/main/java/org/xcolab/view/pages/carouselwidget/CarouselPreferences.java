package org.xcolab.view.pages.carouselwidget;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
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

    private static final ObjectReader logoListReader =
            new ObjectMapper().readerFor(new TypeReference<List<LogoElement>>() {});

    private String title;
    private List<LogoElement> logos;
    private int logosCount;

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
            try {
                logos = logoListReader.readValue(logosArray.toString());
            } catch (IOException exception) {
                throw new IllegalStateException(exception);
            }
        } else {
            logos = new ArrayList<>();
        }
        logosCount = logos.size();
    }

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
        return ConfigurationAttributeKey.PORTLET_CAROUSEL_PREFERENCES;
    }

    public void submit() {
        JSONObject prefsToSave = new JSONObject();
        prefsToSave.put(TITLE_PREFERENCE, title);
        JSONArray ja = new JSONArray();

        logos.forEach(logo -> ja.put(logo.toJson()));
        prefsToSave.put(LOGOS_PREFERENCE, ja);

        savePreferences(prefsToSave, preferenceId);

    }
}
