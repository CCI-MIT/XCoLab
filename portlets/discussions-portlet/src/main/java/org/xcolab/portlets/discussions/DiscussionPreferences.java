package org.xcolab.portlets.discussions;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class DiscussionPreferences {
    private final static String CATEGORY_GROUP_ID_PREFERENCE = "CATEGORY_GROUP_ID";

    private final static Long DEFAULT_CATEGORY_GROUP_ID = 701L;
    private long categoryGroupId;

    public DiscussionPreferences() { }
    
    public DiscussionPreferences(PortletRequest request) {
    	PortletPreferences prefs = request.getPreferences();
        try {
            categoryGroupId = Integer.parseInt(prefs.getValue(CATEGORY_GROUP_ID_PREFERENCE, DEFAULT_CATEGORY_GROUP_ID.toString()));
        } catch (NumberFormatException e) {
            categoryGroupId = DEFAULT_CATEGORY_GROUP_ID;
        }
    }

    public String submit(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences prefs = request.getPreferences();

        prefs.setValue(CATEGORY_GROUP_ID_PREFERENCE, categoryGroupId+"");

        prefs.store();

        return null;
    }

    public long getCategoryGroupId() {
        return categoryGroupId;
    }

    public void setCategoryGroupId(long categoryGroupId) {
        this.categoryGroupId = categoryGroupId;
    }
}
