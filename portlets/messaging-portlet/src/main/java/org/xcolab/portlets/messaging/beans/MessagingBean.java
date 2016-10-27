package org.xcolab.portlets.messaging.beans;

import com.liferay.portal.model.User;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.portlets.messaging.paging.MessageDataPage;
import org.xcolab.portlets.messaging.paging.PageLinkWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessagingBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int PAGE_SIZE = 10;
    private final static int PAGER_RANGE = 3;

    private User user;
    private MessageType messageType = MessageType.INBOX;
    private int messagesCount;

    private MessageDataPage dataPage;

    private int pageNumber = 1;

    private boolean messageLimitExceeded = false;

    @SuppressWarnings("unused")
    public MessagingBean() { }

    public MessagingBean(User user, int pageNumber, MessageType messageType) {
        this.user = user;
        this.pageNumber = pageNumber;
        this.messageType = messageType;

        dataPage = new MessageDataPage(user, messageType, PAGE_SIZE, pageNumber);

        messagesCount = MessagingClient.countMessages(user.getUserId(), messageType);

        messageLimitExceeded = !MessagingClient.canMemberSendMessage(user.getUserId());
    }

    public MessageType getType() {
        return messageType;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public MessageDataPage getDataPage() {
        return dataPage;
    }

    public void setDataPage(MessageDataPage dataPage) {
        this.dataPage = dataPage;
    }

    public List<PageLinkWrapper> getPageLinks() {
        List<PageLinkWrapper> pageLinks = new ArrayList<>();
        pageLinks.add(new PageLinkWrapper("<< First", 1, messageType));
        if (pageNumber > 1) {
            pageLinks.add(new PageLinkWrapper("< Previous", pageNumber - 1, messageType));
        }
        for (int i = Math.max(1, pageNumber - PAGER_RANGE), stop =
             Math.min(getNumberOfPages(), pageNumber + PAGER_RANGE); i <= stop; i++) {
            pageLinks.add(new PageLinkWrapper("", i, messageType));
        }
        if (pageNumber < getNumberOfPages()) {
            pageLinks.add(new PageLinkWrapper("Next >", pageNumber + 1, messageType));
        }
        pageLinks.add(new PageLinkWrapper("Last >>", getNumberOfPages(), messageType));
        return pageLinks;
    }

    public int getNumberOfPages() {
        int numPages = messagesCount / PAGE_SIZE;
        if (messagesCount % PAGE_SIZE != 0) {
            numPages++;
        }
        return numPages;
    }

    public boolean isMessageLimitExceeded() {
        return this.messageLimitExceeded;
    }
}
