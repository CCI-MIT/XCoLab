package org.xcolab.portlets.wiki.util;


import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class WikiPreferences {

    private final static String CONTENT_ARTICLE_ID = "CONTENT_ARTICLE_ID";

    private String wikiFolder;

    public WikiPreferences() {
    }

    public WikiPreferences(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();
        wikiFolder = preferences.getValue(CONTENT_ARTICLE_ID, "0");
    }

    public String getWikiFolder() {
        return wikiFolder;
    }

    public void setWikiFolder(String wikiFolder) {
        this.wikiFolder = wikiFolder;
    }

    public void store(PortletRequest request)
            throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences preferences = request.getPreferences();
        preferences.setValue(CONTENT_ARTICLE_ID, wikiFolder);
        preferences.store();
    }
}
