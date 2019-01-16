package org.xcolab.view.widgets;

import org.json.JSONObject;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.IConfigurationAttribute;
import org.xcolab.client.admin.pojo.tables.pojos.ConfigurationAttribute;
import org.xcolab.commons.attributes.AttributeGetter;
import org.xcolab.util.i18n.I18nUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class WidgetPreference {

    private static final String DEFAULT_ID = "default";
    private static final String PREFERENCES_JSON_OBJECT = "preferences";
    private static final String DIVIDER = "_";

    private final List<String> allPreferenceIds = new ArrayList<>();

    protected String preferenceId;
    protected String language;

    protected JSONObject jsonPreferences;

    public WidgetPreference() {
        this(DEFAULT_ID, I18nUtils.DEFAULT_LANGUAGE);
    }

    public WidgetPreference(String id, String language) {

        if (id == null) {
            id = DEFAULT_ID;
        } else if (language != null && !id.contains("_")) {
            id += DIVIDER + language;
        }

        jsonPreferences = new JSONObject(getConfigurationAttribute().get());

        if (jsonPreferences.has(PREFERENCES_JSON_OBJECT)) {

            JSONObject preferencesArray = jsonPreferences.getJSONObject(PREFERENCES_JSON_OBJECT);
            for (int i = 0; i < preferencesArray.names().length(); i++) {
                allPreferenceIds.add(preferencesArray.names().get(i).toString());
            }

            preferenceId = id;
            if (preferencesArray.has(preferenceId)) {
                jsonPreferences = preferencesArray.getJSONObject(preferenceId);
            } else {
                jsonPreferences = preferencesArray.getJSONObject(DEFAULT_ID);
                preferenceId = DEFAULT_ID + DIVIDER + language;
            }

        } else {
            allPreferenceIds.add(DEFAULT_ID);
            preferenceId = DEFAULT_ID;
        }
    }


    public abstract AttributeGetter<String> getConfigurationAttribute();

    public abstract void savePreferences();

    protected void savePreferencesInternal(JSONObject prefsToSave, String id) {
        final String preferenceId = (id != null ? id : DEFAULT_ID);

        JSONObject currentPreferences = new JSONObject(getConfigurationAttribute().get());
        if (!currentPreferences.has(PREFERENCES_JSON_OBJECT)) {
            JSONObject defaultPrefs = currentPreferences;

            currentPreferences = new JSONObject();
            JSONObject preferences = new JSONObject();
            preferences.put(preferenceId, prefsToSave);
            if (!preferenceId.equals(DEFAULT_ID)) {
                preferences.put(DEFAULT_ID, defaultPrefs);
            }

            currentPreferences.put(PREFERENCES_JSON_OBJECT, preferences);

        } else {

            JSONObject preferences = currentPreferences.getJSONObject(PREFERENCES_JSON_OBJECT);

            preferences.put(preferenceId, prefsToSave);
            currentPreferences.put(PREFERENCES_JSON_OBJECT, preferences);
        }
        IConfigurationAttribute configurationAttribute = new ConfigurationAttribute();
        configurationAttribute.setName(getConfigurationAttribute().name());
        configurationAttribute.setStringValue(currentPreferences.toString());
        StaticAdminContext.getAdminClient().updateConfigurationAttribute(configurationAttribute);
    }

    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public JSONObject getJsonPreferences() {
        return jsonPreferences;
    }

    public void setJsonPreferences(JSONObject jsonPreferences) {
        this.jsonPreferences = jsonPreferences;
    }

    public List<String> getAllPreferenceIds() {
        return allPreferenceIds;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public interface Supplier<T extends WidgetPreference> {

        T get(String id, String language);
    }
}
