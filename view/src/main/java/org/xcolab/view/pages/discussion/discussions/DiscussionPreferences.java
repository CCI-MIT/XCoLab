package org.xcolab.view.pages.discussion.discussions;

import org.json.JSONArray;
import org.json.JSONObject;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.entity.utils.WidgetPreference;
import org.xcolab.util.attributes.AttributeGetter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiscussionPreferences extends WidgetPreference {

    private final static String CATEGORY_GROUP_ID_PREFERENCE = "CATEGORY_GROUP_ID";

    private final static Long DEFAULT_CATEGORY_GROUP_ID = 701L;
    private long categoryGroupId;


    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_DISCUSSION_PREFERENCES;
    }

    public DiscussionPreferences() {
        this(null);
    }
    public DiscussionPreferences(String preferenceId) {
        super(preferenceId);

        try {
            categoryGroupId = Integer.parseInt((prefs.has(CATEGORY_GROUP_ID_PREFERENCE)) ? (prefs
                    .getString(CATEGORY_GROUP_ID_PREFERENCE))
                    : (DEFAULT_CATEGORY_GROUP_ID.toString()));
        } catch (NumberFormatException e) {
            categoryGroupId = DEFAULT_CATEGORY_GROUP_ID;
        }
    }

    public String submit() throws IOException {
        JSONObject prefs = new JSONObject();

        prefs.put(CATEGORY_GROUP_ID_PREFERENCE, categoryGroupId + "");

        savePreferences(prefs,null);

        return null;
    }

    public long getCategoryGroupId() {
        return categoryGroupId;
    }

    public void setCategoryGroupId(long categoryGroupId) {
        this.categoryGroupId = categoryGroupId;
    }
}
