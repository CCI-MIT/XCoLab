package org.xcolab.view.widgets.feeds;

import org.json.JSONObject;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.widgets.WidgetPreference;

import java.io.Serializable;

public class FeedsPreferences extends WidgetPreference implements Serializable {

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


        feedSize = jsonPreferences.optInt(FEED_SIZE_PREF, DEFAULT_FEED_SIZE);
        feedType = jsonPreferences.optEnum(FeedType.class, FEED_TYPE_PREF, DEFAULT_FEED_TYPE);

        feedTitle = jsonPreferences.optString(FEED_TITLE_PREF, DEFAULT_FEED_TITLE);
        //TODO: this condition should be unreachable - verify
        if (feedTitle == null) {
            feedTitle = feedType.getDescription();
        }

        feedStyle = jsonPreferences.optString(FEED_DISPLAY_STYLE, DEFAULT_STYLE);

        portletTitle = jsonPreferences.optString(PORTLET_TITLE, DEFAULT_PORTLET_TITLE);

        removeAdmin = jsonPreferences.optBoolean(FEED_REMOVE_ADMIN, DEFAULT_REMOVE_ADMIN);
        seeMoreLinkShown = jsonPreferences.optBoolean(FEED_SEE_MORE_LINK_SHOWN, DEFAULT_SEE_MORE_SHOWN);
        feedMaxLength = jsonPreferences.optInt(FEED_MAX_LENGTH, DEFAULT_FEED_MAX_LENGTH);
    }

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_FEED_PREFERENCES;
    }

    @Override
    public void savePreferences() {
        JSONObject prefs = new JSONObject();

        prefs.put(FEED_SIZE_PREF, String.valueOf(feedSize));
        prefs.put(FEED_TITLE_PREF, feedTitle);
        prefs.put(FEED_TYPE_PREF, feedType.name());
        prefs.put(FEED_REMOVE_ADMIN, String.valueOf(removeAdmin));
        prefs.put(FEED_DISPLAY_STYLE, feedStyle);
        prefs.put(PORTLET_TITLE, String.valueOf(portletTitle));
        prefs.put(FEED_SEE_MORE_LINK_SHOWN, String.valueOf(seeMoreLinkShown));
        prefs.put(FEED_MAX_LENGTH, String.valueOf(feedMaxLength));

        savePreferencesInternal(prefs, preferenceId);
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
