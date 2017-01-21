package org.xcolab.view.pages.discussion.discussions;

import org.json.JSONObject;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;

import java.io.IOException;

public class DiscussionPreferences {
    private final static String CATEGORY_GROUP_ID_PREFERENCE = "CATEGORY_GROUP_ID";

    private final static Long DEFAULT_CATEGORY_GROUP_ID = 701L;
    private long categoryGroupId;


    
    public DiscussionPreferences() {
        JSONObject prefs = new JSONObject(ConfigurationAttributeKey.PORTLET_DISCUSSION_PREFERENCES.get());

        try {
            categoryGroupId = Integer.parseInt((prefs.has(CATEGORY_GROUP_ID_PREFERENCE))?(prefs.getString(CATEGORY_GROUP_ID_PREFERENCE)):(DEFAULT_CATEGORY_GROUP_ID.toString()));
        } catch (NumberFormatException e) {
            categoryGroupId = DEFAULT_CATEGORY_GROUP_ID;
        }
    }

    public String submit() throws  IOException {
        JSONObject prefs = new JSONObject();

        prefs.put(CATEGORY_GROUP_ID_PREFERENCE, categoryGroupId+"");

        ConfigurationAttribute configurationAttribute = new ConfigurationAttribute();
        configurationAttribute.setName(ConfigurationAttributeKey.PORTLET_CONTACT_FORM_PREFERENCES.name());
        configurationAttribute.setStringValue(prefs.toString());
        AdminClient.updateConfigurationAttribute(configurationAttribute);

        return null;
    }

    public long getCategoryGroupId() {
        return categoryGroupId;
    }

    public void setCategoryGroupId(long categoryGroupId) {
        this.categoryGroupId = categoryGroupId;
    }
}
