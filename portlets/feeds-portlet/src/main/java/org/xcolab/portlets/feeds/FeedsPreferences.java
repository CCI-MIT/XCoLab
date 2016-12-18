package org.xcolab.portlets.feeds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class FeedsPreferences implements Serializable {

    private static final Logger _log = LoggerFactory.getLogger(FeedsPreferences.class);

	private static final long serialVersionUID = 1L;

	private String portletTitle;
	private int feedSize;
    private FeedType feedType;
    private String feedTitle;
    private Boolean removeAdmin;
    private String feedStyle;
	private Boolean seeMoreLinkShown;

	private int feedMaxLength;

	private final static String PORTLET_TITLE = "PORTLET_TITLE";
    private final static String FEED_SIZE_PREF = "FEED_SIZE";
    private final static String FEED_TITLE_PREF = "FEED_TITLE";
    private final static String FEED_TYPE_PREF = "FEED_TYPE";
    private final static String FEED_REMOVE_ADMIN = "FEED_REMOVE_ADMIN";
    private final static String FEED_DISPLAY_STYLE = "FEED_DISPLAY_STYLE";
	private final static String FEED_SEE_MORE_LINK_SHOWN = "FEED_SEE_MORE_LINK_SHOWN";
	private final static String FEED_MAX_LENGTH = "FEED_MAX_LENGTH";


    private final static String LONG = "LONG";
    
    private final static int defaultFeedSize = 20;
    private final static String defaultFeedTitle = null;
    private final static FeedType defaultFeedType = FeedType.ACTIVITIES;
    private final static String defaultStyle = LONG;
    private final static Boolean defaultRemoveAdmin = false;
	private final static String defaultPortletTitle = "";
	private final static Boolean defaultSeeMoreShown = false;
	private final static Integer defaultFeedMaxLength = 0;
        
    public FeedsPreferences() {
    	
    }
    public FeedsPreferences(PortletRequest request) {
        PortletPreferences prefs = request.getPreferences();

        feedSize = defaultFeedSize; 
        try {
            feedSize = Integer.parseInt(prefs.getValue(FEED_SIZE_PREF, String.valueOf(defaultFeedSize)));
        }
        catch (NumberFormatException e) {
            _log.warn("Could not parse feedSize: "
                    + prefs.getValue(FEED_SIZE_PREF, String.valueOf(defaultFeedSize)));
        }
        
        feedType = defaultFeedType;
        try {
            feedType = FeedType.valueOf(prefs.getValue(FEED_TYPE_PREF, defaultFeedType.name()));
        }
        catch (IllegalArgumentException e) {
            _log.warn("Could not parse feedType: "
                    + prefs.getValue(FEED_TYPE_PREF, defaultFeedType.name()));
        }
        
        feedTitle = prefs.getValue(FEED_TITLE_PREF, defaultFeedTitle);
        if (feedTitle == null) {
            feedTitle = feedType.getDescription();
        }

        feedStyle = prefs.getValue(FEED_DISPLAY_STYLE,defaultStyle);
        if (feedStyle == null) {
            feedStyle = defaultStyle;
        }

        portletTitle = prefs.getValue(PORTLET_TITLE, defaultPortletTitle);

        removeAdmin = Boolean.parseBoolean(prefs.getValue(FEED_REMOVE_ADMIN,String.valueOf(defaultRemoveAdmin)));
		seeMoreLinkShown = Boolean.parseBoolean(prefs.getValue(FEED_SEE_MORE_LINK_SHOWN,String.valueOf(defaultSeeMoreShown)));
		feedMaxLength = Integer.parseInt(prefs.getValue(FEED_MAX_LENGTH, String.valueOf(defaultFeedMaxLength)));
    }

    
    public String store(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {

        PortletPreferences prefs = request.getPreferences();
        prefs.setValue(FEED_SIZE_PREF, String.valueOf(feedSize));
        prefs.setValue(FEED_TITLE_PREF, feedTitle);
        prefs.setValue(FEED_TYPE_PREF, feedType.name());
        prefs.setValue(FEED_REMOVE_ADMIN,String.valueOf(removeAdmin));
        prefs.setValue(FEED_DISPLAY_STYLE,feedStyle);
		prefs.setValue(PORTLET_TITLE, String.valueOf(portletTitle));
		prefs.setValue(FEED_SEE_MORE_LINK_SHOWN, String.valueOf(seeMoreLinkShown));
		prefs.setValue(FEED_MAX_LENGTH, String.valueOf(feedMaxLength));

        prefs.store();
        
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

	public String getPortletTitle() {
		return portletTitle;
	}

	public void setPortletTitle(String portletTitle) {
		this.portletTitle = portletTitle;
	}

	public Boolean getSeeMoreLinkShown() {
		return seeMoreLinkShown;
	}

	public void setSeeMoreLinkShown(Boolean seeMoreLinkShown) {
		this.seeMoreLinkShown = seeMoreLinkShown;
	}

	public int getFeedMaxLength() {
		return feedMaxLength;
	}

	public void setFeedMaxLength(int feedMaxLength) {
		this.feedMaxLength = feedMaxLength;
	}
}
