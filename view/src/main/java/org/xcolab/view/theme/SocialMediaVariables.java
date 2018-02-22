package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.view.socialmedia.SocialMediaEngine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class SocialMediaVariables {
    private final String shareRequestUri;
    private final boolean showShareButtons;

    private final List<SocialMediaEngine> shareableSocialMediaUrls; // TODO: Rename to shareableEngines
    private final List<SocialMediaEngine> followableSocialMediaUrls; // TODO: Rename to followableEngines
    private final List<SocialMediaEngine> socialMediaEngines; // TODO: Rename to allEngines
    private final String donateLink;

    public SocialMediaVariables(HttpServletRequest request) {
        this.shareRequestUri = SocialMediaEngine
                .getUtmParameters(ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get(), request);
        this.showShareButtons = ConfigurationAttributeKey.SHOW_SHARE_BUTTONS.get();

        this.shareableSocialMediaUrls = SocialMediaEngine.getShareableSocialMediaEngines();
        this.followableSocialMediaUrls = SocialMediaEngine.getFollowableSocialMediaEngines();
        this.socialMediaEngines = SocialMediaEngine.getAllSocialMediaEngines();

        this.donateLink = ConfigurationAttributeKey.NAVBAR_DONATE_LINK.get();
    }

    public String getShareRequestUri() {
        return shareRequestUri;
    }

    public boolean getShowShareButtons() {
        return showShareButtons;
    }

    public List<SocialMediaEngine> getShareableSocialMediaUrls() {
        return shareableSocialMediaUrls;
    }

    public List<SocialMediaEngine> getFollowableSocialMediaUrls() {
        return followableSocialMediaUrls;
    }

    public List<SocialMediaEngine> getSocialMediaEngines() {
        return socialMediaEngines;
    }

    public String getDonateLink() {
        return donateLink;
    }
}
