package org.xcolab.portlets.feeds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import com.liferay.portal.model.User;

public class FeedsPreferences {

    private int feedSize;
    private FeedType feedType;
    private String feedTitle;



    private Boolean removeAdmin;



    private String feedStyle;

    
    private final static String FEED_SIZE_PREF = "FEED_SIZE";
    private final static String FEED_TITLE_PREF = "FEED_TITLE";
    private final static String FEED_TYPE_PREF = "FEED_TYPE";
    private final static String FEED_REMOVE_ADMIN = "FEED_REMOVE_ADMIN";
    private final static String FEED_DISPLAY_STYLE = "FEED_DISPLAY_STYLE";


    private final static String LONG = "LONG";
    private final static String COLLAPSED = "COLLAPSED";
    
    private final static int defaultFeedSize = 20;
    private final static String defaultFeedTitle = null;
    private final static FeedType defaultFeedType = FeedType.ACTIVITIES;
    private final static String defaultStyle = LONG;
    private final static Boolean defaultRemoveAdmin = false;
        
    public FeedsPreferences() {
        PortletPreferences prefs = Helper.getPortletPrefs();
        
        feedSize = defaultFeedSize; 
        try {
            feedSize = Integer.parseInt(prefs.getValue(FEED_SIZE_PREF, String.valueOf(defaultFeedSize)));
        }
        catch (NumberFormatException e) {
            // ignore
        }
        
        feedType = defaultFeedType;
        try {
            feedType = FeedType.valueOf(prefs.getValue(FEED_TYPE_PREF, defaultFeedType.name()));
        }
        catch (Exception e) {
            // ignore
        }
        
        feedTitle = prefs.getValue(FEED_TITLE_PREF, defaultFeedTitle);
        if (feedTitle == null) {
            feedTitle = feedType.getDescription();
        }

        feedStyle = prefs.getValue(FEED_DISPLAY_STYLE,defaultStyle);
        if (feedStyle == null) {
            feedStyle = defaultStyle;
        }

        removeAdmin = Boolean.parseBoolean(prefs.getValue(FEED_REMOVE_ADMIN,String.valueOf(defaultRemoveAdmin)));
        


    }

    
    public String submit() throws ReadOnlyException, ValidatorException, IOException {
        
        PortletPreferences prefs = Helper.getPortletPrefs();
        prefs.setValue(FEED_SIZE_PREF, String.valueOf(feedSize));
        prefs.setValue(FEED_TITLE_PREF, feedTitle);
        prefs.setValue(FEED_TYPE_PREF, feedType.name());
        prefs.setValue(FEED_REMOVE_ADMIN,String.valueOf(removeAdmin));
        prefs.setValue(FEED_DISPLAY_STYLE,feedStyle);

        prefs.store();
            
        
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        
        return null;
    }


    public int getFeedSize() {
        return feedSize;
    }


    public FeedType getFeedType() {
        return feedType;
    }


    public String getFeedTitle() {
        return feedTitle;
    }


    public void setFeedSize(int feedSize) {
        this.feedSize = feedSize;
    }


    public void setFeedType(FeedType feedType) {
        this.feedType = feedType;
    }


    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }
    
    
    public List<SelectItem> getFeedTypes() {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        
        for (FeedType type: FeedType.values()) {
            ret.add(new SelectItem(type, type.name()));
        }
        return ret;
    }

     public Boolean getRemoveAdmin() {
        return removeAdmin;
    }

    public String getFeedStyle() {
        return feedStyle;
    }

     public void setRemoveAdmin(Boolean removeAdmin) {
        this.removeAdmin = removeAdmin;
    }

    public void setFeedStyle(String feedStyle) {
        this.feedStyle = feedStyle;
    }
}
