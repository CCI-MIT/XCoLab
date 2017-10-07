package org.xcolab.view.socialmedia;

import org.apache.commons.lang.StringUtils;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

public enum SocialMediaEngine {
    FACEBOOK(ConfigurationAttributeKey.FACEBOOK_URL.get(), true, true),
    TWITTER(ConfigurationAttributeKey.TWITTER_URL.get(), true, true),
    LINKEDIN(ConfigurationAttributeKey.LINKEDIN_URL.get(), true, true),
    GOOGLE(ConfigurationAttributeKey.GOOGLE_URL.get(), true, true),
    STORIFY(ConfigurationAttributeKey.STORIFY_URL.get(), false, false),
    YOUTUBE(ConfigurationAttributeKey.YOUTUBE_URL.get(), false, true),
    EMAIL(null, true, true);

    public static String SOCIAL_MEDIA_SPACE_HOLDER = "socialMediaEngine";
    private static String TAB_IDENTIFIER = "tab";
    private final String followMeUrl;

    private final boolean isShareable;
    private final boolean isActive;

    SocialMediaEngine(String followMeUrl, boolean isShareable, boolean isActive) {
        this.followMeUrl = followMeUrl;
        this.isShareable = isShareable;
        this.isActive = isActive;

    }

    private static SocialMediaEngine[] getAllAvailableSocialMediaEngines() {
        return SocialMediaEngine.values();
    }

    public static List<SocialMediaEngine> getShearableSocialMediaEngines() {
        return Arrays.stream(getAllAvailableSocialMediaEngines()).filter(p -> p.isShareable())
                .collect(Collectors.toList());
    }

    public static List<SocialMediaEngine> getFollowableSocialMediaEngines() {
        return Arrays.stream(getAllAvailableSocialMediaEngines())
                .filter(p -> p.isFollowable() && p.isActive()).collect(Collectors.toList());
    }


    public static String getUtmParameters(String productionURL ,HttpServletRequest request) {
        String url = productionURL;
        String currentTab = ((HttpRewriteWrappedRequest) request).getParameter("tab");

        if (currentTab != null) {
            url += request.getRequestURI() + "/" + TAB_IDENTIFIER + "/" + currentTab;
        } else {
            url += request.getRequestURI();
        }
        String urlToReturn = url;
        if (!url.contains("?")) {
            urlToReturn += "?";
        } else {
            urlToReturn += "&";
        }
        return urlToReturn + "utm_source=" + SOCIAL_MEDIA_SPACE_HOLDER
                + "&utm_medium=Social&utm_campaign=Share";
    }

    public String getFollowMeUrl() {
        return followMeUrl;
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public boolean isFollowable() {
        return !StringUtils.isEmpty(this.followMeUrl);
    }

    public boolean isShareable() {
        return isShareable;
    }

    public boolean isActive() {
        return isActive;
    }
}
