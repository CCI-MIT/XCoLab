package com.ext.portlet.Activity;

import com.liferay.portlet.social.model.SocialActivityFeedEntry;

public class BaseFeedEntryWithMailInfo extends SocialActivityFeedEntry implements FeedEntryWithMailInfo {
    private final String mailSubject;
    private final String mailBody;

    public BaseFeedEntryWithMailInfo(String link, String title, String body, String mailSubject, String mailBody) {
        super(link, title, body);
        this.mailSubject = mailSubject;
        this.mailBody = mailBody;
    }

    public BaseFeedEntryWithMailInfo(String title, String body, String mailSubject, String mailBody) {
        super(title, body);
        this.mailSubject = mailSubject;
        this.mailBody = mailBody;
    }

    @Override
    public String getMailSubject() {
        return mailSubject;
    }

    @Override
    public String getMailBody() {
        return mailBody;
    }

}
