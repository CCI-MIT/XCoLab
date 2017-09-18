package org.xcolab.view.pages.discussion.discussions;

import org.json.JSONObject;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.widgets.WidgetPreference;

public class DiscussionPreferences extends WidgetPreference {

    private final static String CATEGORY_GROUP_ID_PREFERENCE = "CATEGORY_GROUP_ID";

    private final static Long DEFAULT_CATEGORY_GROUP_ID = 701L;
    private long categoryGroupId;

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_DISCUSSION_PREFERENCES;
    }

    public DiscussionPreferences() {
        this(null, I18nUtils.DEFAULT_LANGUAGE);
    }

    public DiscussionPreferences(String preferenceId, String language) {
        super(preferenceId, language);

        categoryGroupId = jsonPreferences
                .optLong(CATEGORY_GROUP_ID_PREFERENCE, DEFAULT_CATEGORY_GROUP_ID);
    }

    @Override
    public void savePreferences() {
        JSONObject prefs = new JSONObject();

        prefs.put(CATEGORY_GROUP_ID_PREFERENCE, String.valueOf(categoryGroupId));

        savePreferencesInternal(prefs,null);
    }

    public long getCategoryGroupId() {
        return categoryGroupId;
    }

    public void setCategoryGroupId(long categoryGroupId) {
        this.categoryGroupId = categoryGroupId;
    }
}
