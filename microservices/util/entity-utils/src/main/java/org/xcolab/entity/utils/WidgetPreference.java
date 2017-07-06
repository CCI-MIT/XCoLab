package org.xcolab.entity.utils;

import org.json.JSONObject;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.util.attributes.AttributeGetter;

import java.util.ArrayList;
import java.util.List;

public abstract class WidgetPreference {

    public abstract AttributeGetter<String> getConfigurationAttribute();

    protected String preferenceId;
    protected JSONObject prefs;
    private final List<String> allPreferenceIds = new ArrayList<>();

    private final static String DEFAULT_ID = "default";
    private final static String PREFERENCES_JSON_OBJECT = "preferences";

    public WidgetPreference(String id) {

        prefs = new JSONObject(getConfigurationAttribute().get());

        if (prefs.has(PREFERENCES_JSON_OBJECT)) {

            JSONObject preferencesArray = prefs.getJSONObject(PREFERENCES_JSON_OBJECT);
            //preferencesArray.keySet().stream().forEach(s -> allPreferenceIds.add(s));
            for(String prefId: preferencesArray.keySet()){
                allPreferenceIds.add(prefId);
            }

            if (id != null) {
                preferenceId = id;
            } else {
                preferenceId = DEFAULT_ID;
                //allPreferenceIds.add(DEFAULT_ID);
            }
            if(preferencesArray.has(preferenceId)) {
                prefs = preferencesArray.getJSONObject(preferenceId);
            }else{
                prefs = preferencesArray.getJSONObject(DEFAULT_ID);
                preferenceId = DEFAULT_ID;
            }

        }else{
            allPreferenceIds.add(DEFAULT_ID);
            preferenceId = DEFAULT_ID;
        }
    }

    public WidgetPreference() {
        this(DEFAULT_ID);
    }

    protected void savePreferences(JSONObject prefsToSave, String id) {
        id = (id==null?(DEFAULT_ID):(id));
        JSONObject currentPreferences = new JSONObject(getConfigurationAttribute().get());
        if (!currentPreferences.has(PREFERENCES_JSON_OBJECT)) {
            JSONObject defaultPrefs =currentPreferences ;

            currentPreferences = new JSONObject();
            JSONObject preferences = new JSONObject();
            preferences.put(id,prefsToSave);
            if(!id.equals(DEFAULT_ID)){
                preferences.put(DEFAULT_ID,defaultPrefs);
            }

            currentPreferences.put(PREFERENCES_JSON_OBJECT,preferences);

        }else{

            JSONObject preferences = currentPreferences.getJSONObject(PREFERENCES_JSON_OBJECT);

            preferences.put(id,prefsToSave);
            currentPreferences.put(PREFERENCES_JSON_OBJECT,preferences);
        }
        ConfigurationAttribute configurationAttribute = new ConfigurationAttribute();
        configurationAttribute.setName(getConfigurationAttribute().name());
        configurationAttribute.setStringValue(currentPreferences.toString());
        AdminClient.updateConfigurationAttribute(configurationAttribute);
    }

    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public JSONObject getPrefs() {
        return prefs;
    }

    public void setPrefs(JSONObject prefs) {
        this.prefs = prefs;
    }

    public List<String> getAllPreferenceIds() {
        return allPreferenceIds;
    }


}
