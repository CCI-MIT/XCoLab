package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.view.socialmedia.SocialMediaEngine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class SocialMediaVariables {
    public String shareRequestUri;
    public boolean showShareButtons;

    public List<SocialMediaEngine> shareableSocialMediaUrls; // TODO: Rename to shareableEngines
    public List<SocialMediaEngine> followableSocialMediaUrls; // TODO: Rename to followableEngines
    public List<SocialMediaEngine> socialMediaEngines; // TODO: Rename to allEngines
    public String donateLink;

    public SocialMediaVariables(HttpServletRequest request) {
        this.shareRequestUri = SocialMediaEngine
                .getUtmParameters(ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get(), request);
        this.showShareButtons = ConfigurationAttributeKey.SHOW_SHARE_BUTTONS.get();

        this.shareableSocialMediaUrls = SocialMediaEngine.getShareableSocialMediaEngines();
        this.followableSocialMediaUrls = SocialMediaEngine.getFollowableSocialMediaEngines();
        this.socialMediaEngines = SocialMediaEngine.getAllSocialMediaEngines();

        this.donateLink = ConfigurationAttributeKey.NAVBAR_DONATE_LINK.get();
    }
}
