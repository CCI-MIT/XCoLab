package org.xcolab.portlets.contentdisplay.util;


import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class ContentDisplayPreferences {

    private final static String CONTENT_ARTICLE_ID = "CONTENT_ARTICLE_ID";

    private String contentArticleId;

    public ContentDisplayPreferences() {
    }

    public ContentDisplayPreferences(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();
        contentArticleId = preferences.getValue(CONTENT_ARTICLE_ID, "1");
    }

    public String getContentArticleId() {
        return contentArticleId;
    }

    public void setContentArticleId(String contentArticleId) {
        this.contentArticleId = contentArticleId;
    }

    public void store(PortletRequest request)
            throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences preferences = request.getPreferences();
        preferences.setValue(CONTENT_ARTICLE_ID, contentArticleId);
        preferences.store();
    }
}
