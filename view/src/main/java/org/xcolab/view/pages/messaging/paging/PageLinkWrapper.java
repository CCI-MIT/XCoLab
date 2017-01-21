package org.xcolab.view.pages.messaging.paging;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.view.pages.messaging.beans.MessagingBean;

public class PageLinkWrapper {

    private final String text;
    private final int page;
    private final MessageType messageType;

    public PageLinkWrapper(String text, int page, MessageType messageType) {
        this.text = text;
        this.page = page;
        this.messageType = messageType;
    }

    public String getLinkText() {
        if (StringUtils.isEmpty(text)) {
            return Integer.toString(page);
        }
        return text;
    }

    public int getPage() {
        return page;
    }


    public String getLinkUrl() {
        String url = "/web/guest/messaging/-/messaging";
        if (messageType != MessageType.INBOX) {
            url += "/mailbox/" + messageType.name();
        }
        if (page > 1) {
            url += "/page/" + page;
        }
        return url;
    }

    public boolean isCurrent(MessagingBean messagingBean) {
        return page == messagingBean.getPageNumber();
    }
}