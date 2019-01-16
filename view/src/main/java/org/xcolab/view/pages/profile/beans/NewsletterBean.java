package org.xcolab.view.pages.profile.beans;

import org.xcolab.client.user.MembersClient;

import java.io.Serializable;

public class NewsletterBean implements Serializable {

    private final long userId;

    public NewsletterBean(long userId) {
        this.userId = userId;
    }

    public boolean isEmailSubscribedToNewsletter() {
        return MembersClient.isSubscribedToNewsletter(userId);
    }
}
