package org.xcolab.portlets.wiki.util;


import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class WikiPreferences {

    private final static String WIKI_FOLDER_ID = "WIKI_FOLDER_ID";

    private String wikiFolderId;

    public WikiPreferences() {
    }

    public WikiPreferences(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();
        wikiFolderId = preferences.getValue(WIKI_FOLDER_ID, "0");
    }

    public String getWikiFolderId() {
        return wikiFolderId;
    }

    public void setWikiFolderId(String wikiFolderId) {
        this.wikiFolderId = wikiFolderId;
    }

    public void store(PortletRequest request)
            throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences preferences = request.getPreferences();
        preferences.setValue(WIKI_FOLDER_ID, wikiFolderId);
        preferences.store();
    }
}
