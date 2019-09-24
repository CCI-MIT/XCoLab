package org.xcolab.view.pages.profile.beans;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;

import java.io.IOException;
import java.io.Serializable;

public class NewsletterBean implements Serializable {

    private final long userId;

    public NewsletterBean(long userId) {
        this.userId = userId;
    }

    public boolean isEmailSubscribedToNewsletter() {
        try {
            return StaticUserContext.getUserClient().isSubscribedToNewsletter(userId);
        } catch (MemberNotFoundException | IOException e) {
            return false;
        }
    }
}
