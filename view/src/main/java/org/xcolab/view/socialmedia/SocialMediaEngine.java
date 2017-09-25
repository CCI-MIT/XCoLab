package org.xcolab.view.socialmedia;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SocialMediaEngine {
    facebook(ConfigurationAttributeKey.FACEBOOK_URL.get(), true, true),
    twitter(ConfigurationAttributeKey.TWITTER_URL.get(), true, true),
    linkedin(ConfigurationAttributeKey.LINKEDIN_URL.get(), true, true),
    google(ConfigurationAttributeKey.GOOGLE_URL.get(), true, true),
    storify(ConfigurationAttributeKey.STORIFY_URL.get(), false, false),
    youtube(ConfigurationAttributeKey.YOUTUBE_URL.get(), true, true),
    email(null, true, true);

    public static String SOCIAL_MEDIA_SPACE_HOLDER = "socialMediaEngine";
    private String followMeUrl;

    private boolean isShearable;
    private boolean isActive;

    SocialMediaEngine(String followMeUrl, boolean isShearable, boolean isActive) {
        this.followMeUrl = followMeUrl;
        this.isShearable = isShearable;
        this.isActive = isActive;

    }

    private static SocialMediaEngine[] getAllAvailableSocialMediaEngines() {
        return SocialMediaEngine.values();
    }

    public static List<SocialMediaEngine> getShearableSocialMediaEngines() {
        return Arrays.stream(getAllAvailableSocialMediaEngines()).filter(p -> p.isShearable())
                .collect(Collectors.toList());
    }

    public static List<SocialMediaEngine> getFollowableSocialMediaEngines() {
        return Arrays.stream(getAllAvailableSocialMediaEngines())
                .filter(p -> p.isFollowable() && p.isActive()).collect(Collectors.toList());
    }
    public static String replacePlaceholder(String url, String engine){
        return url.replace(SocialMediaEngine.SOCIAL_MEDIA_SPACE_HOLDER,engine);
    }

    public static String getUtmParameters(String url) {
        String urlToReturn = url;
        if (!url.contains("?")) {
            urlToReturn += "?";
        } else {
            urlToReturn += "&";
        }
        return urlToReturn+"utm_source="+SOCIAL_MEDIA_SPACE_HOLDER+"&utm_medium=Social&utm_campaign=Share";
    }

    public String getFollowMeUrl() {
        return followMeUrl;
    }

    public boolean isFollowable() {
        if (this.followMeUrl == null || this.followMeUrl.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isShearable() {
        return isShearable;
    }

    public boolean isActive() {
        return isActive;
    }
}
