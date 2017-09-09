package org.xcolab.view.widgets.feeds;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.view.widgets.WidgetPreference;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.i18n.I18nUtils;

import java.io.IOException;
import java.io.Serializable;

public class FeedsPreferences extends WidgetPreference implements Serializable {

    private static final Logger _log = LoggerFactory.getLogger(FeedsPreferences.class);

    private static final long serialVersionUID = 1L;

    private static final String PORTLET_TITLE = "PORTLET_TITLE";
    private static final String FEED_SIZE_PREF = "FEED_SIZE";
    private static final String FEED_TITLE_PREF = "FEED_TITLE";
    private static final String FEED_TYPE_PREF = "FEED_TYPE";
    private static final String FEED_REMOVE_ADMIN = "FEED_REMOVE_ADMIN";
    private static final String FEED_DISPLAY_STYLE = "FEED_DISPLAY_STYLE";
    private static final String FEED_SEE_MORE_LINK_SHOWN = "FEED_SEE_MORE_LINK_SHOWN";
    private static final String FEED_MAX_LENGTH = "FEED_MAX_LENGTH";
    private static final String FEED_STYLE_LONG = "LONG";

    private static final int DEFAULT_FEED_SIZE = 20;
    private static final String DEFAULT_FEED_TITLE = null;
    private static final FeedType DEFAULT_FEED_TYPE = FeedType.ACTIVITIES;
    private static final String DEFAULT_STYLE = FEED_STYLE_LONG;
    private static final Boolean DEFAULT_REMOVE_ADMIN = false;
    private static final String DEFAULT_PORTLET_TITLE = "";
    private static final Boolean DEFAULT_SEE_MORE_SHOWN = false;
    private static final Integer DEFAULT_FEED_MAX_LENGTH = 0;

    private String portletTitle;
    private int feedSize;
    private FeedType feedType;
    private String feedTitle;
    private Boolean removeAdmin;
    private String feedStyle;
    private Boolean seeMoreLinkShown;
    private int feedMaxLength;

    public FeedsPreferences() {
        this(null, I18nUtils.DEFAULT_LANGUAGE);
    }

    public FeedsPreferences(String preferenceId, String language) {
        super(preferenceId, language);


        feedSize = DEFAULT_FEED_SIZE;
        try {
            feedSize = Integer.parseInt(
                    (prefs.has(FEED_SIZE_PREF)) ? (prefs.getString(FEED_SIZE_PREF))
                            : (String.valueOf(DEFAULT_FEED_SIZE)));
        } catch (NumberFormatException e) {
            _log.warn("Could not parse feedSize: {}",
                    (prefs.has(FEED_SIZE_PREF)) ? (prefs.getString(FEED_SIZE_PREF))
                            : (String.valueOf(DEFAULT_FEED_SIZE)));
        }

        feedType = DEFAULT_FEED_TYPE;
        try {
            feedType = FeedType.valueOf(
                    ((prefs.has(FEED_TYPE_PREF)) ? (prefs.getString(FEED_TYPE_PREF))
                            : (DEFAULT_FEED_TYPE.name())));
        } catch (IllegalArgumentException e) {
            _log.warn("Could not parse feedType: {}",
                    (prefs.has(FEED_TYPE_PREF)) ? (prefs.getString(FEED_TYPE_PREF))
                            : (DEFAULT_FEED_TYPE.name()));
        }

        feedTitle = (prefs.has(FEED_TITLE_PREF)) ? (prefs.getString(FEED_TITLE_PREF))
                : (DEFAULT_FEED_TITLE);
        if (feedTitle == null) {
            feedTitle = feedType.getDescription();
        }

        feedStyle = (prefs.has(FEED_DISPLAY_STYLE)) ? (prefs.getString(FEED_DISPLAY_STYLE))
                : (DEFAULT_STYLE);
        if (feedStyle == null) {
            feedStyle = DEFAULT_STYLE;
        }

        portletTitle = (prefs.has(PORTLET_TITLE)) ? (prefs.getString(PORTLET_TITLE))
                : (DEFAULT_PORTLET_TITLE);

        removeAdmin = Boolean.parseBoolean(
                (prefs.has(FEED_REMOVE_ADMIN)) ? (prefs.getString(FEED_REMOVE_ADMIN))
                        : (String.valueOf(DEFAULT_REMOVE_ADMIN)));
        seeMoreLinkShown = Boolean.parseBoolean(
                (prefs.has(FEED_REMOVE_ADMIN)) ? (prefs.getString(FEED_SEE_MORE_LINK_SHOWN))
                        : (String.valueOf(DEFAULT_SEE_MORE_SHOWN)));
        feedMaxLength = Integer.parseInt(
                (prefs.has(FEED_REMOVE_ADMIN)) ? (prefs.getString(FEED_MAX_LENGTH))
                        : (String.valueOf(DEFAULT_FEED_MAX_LENGTH)));
    }

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_FEED_PREFERENCES;
    }

    public String store() throws IOException {
        JSONObject prefs = new JSONObject();

        prefs.put(FEED_SIZE_PREF, String.valueOf(feedSize));
        prefs.put(FEED_TITLE_PREF, feedTitle);
        prefs.put(FEED_TYPE_PREF, feedType.name());
        prefs.put(FEED_REMOVE_ADMIN, String.valueOf(removeAdmin));
        prefs.put(FEED_DISPLAY_STYLE, feedStyle);
        prefs.put(PORTLET_TITLE, String.valueOf(portletTitle));
        prefs.put(FEED_SEE_MORE_LINK_SHOWN, String.valueOf(seeMoreLinkShown));
        prefs.put(FEED_MAX_LENGTH, String.valueOf(feedMaxLength));

        savePreferences(prefs, preferenceId);

        return null;
    }


    public int getFeedSize() {
        return feedSize;
    }

    public void setFeedSize(int feedSize) {
        this.feedSize = feedSize;
    }

    public FeedType getFeedType() {
        return feedType;
    }

    public void setFeedType(FeedType feedType) {
        this.feedType = feedType;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    public Boolean getRemoveAdmin() {
        return removeAdmin;
    }

    public void setRemoveAdmin(Boolean removeAdmin) {
        this.removeAdmin = removeAdmin;
    }

    public String getFeedStyle() {
        return feedStyle;
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
