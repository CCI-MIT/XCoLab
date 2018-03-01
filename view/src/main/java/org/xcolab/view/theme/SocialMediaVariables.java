package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.view.socialmedia.SocialMediaEngine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class SocialMediaVariables {
    private final String shareRequestUri;
    private final boolean showShareButtons;

    private final List<SocialMediaEngine> shareableEngines;
    private final List<SocialMediaEngine> followableEngines;
    private final List<SocialMediaEngine> allEngines;
    private final String donateLink;

    public SocialMediaVariables(HttpServletRequest request) {
        this.shareRequestUri = SocialMediaEngine
                .getUtmParameters(ConfigurationAttributeKey.COLAB_URL_PRODUCTION.get(), request);
        this.showShareButtons = ConfigurationAttributeKey.SHOW_SHARE_BUTTONS.get();

        this.shareableEngines = SocialMediaEngine.getShareableSocialMediaEngines();
        this.followableEngines = SocialMediaEngine.getFollowableSocialMediaEngines();
        this.allEngines = SocialMediaEngine.getAllSocialMediaEngines();

        this.donateLink = ConfigurationAttributeKey.NAVBAR_DONATE_LINK.get();
    }

    public String getShareRequestUri() {
        return shareRequestUri;
    }

    public boolean getShowShareButtons() {
        return showShareButtons;
    }

    public List<SocialMediaEngine> getShareableEngines() {
        return shareableEngines;
    }

    public List<SocialMediaEngine> getFollowableEngines() {
        return followableEngines;
    }

    public List<SocialMediaEngine> getAllEngines() {
        return allEngines;
    }

    public String getDonateLink() {
        return donateLink;
    }
}
