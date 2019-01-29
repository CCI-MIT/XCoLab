package org.xcolab.view.pages.messaging.beans;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.legacy.enums.MessageType;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.pages.messaging.paging.MessageDataPage;
import org.xcolab.view.util.pagination.PageNavigation;

import java.io.Serializable;

public class MessagingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int PAGE_SIZE = 10;

    private UserWrapper member;
    private MessageType messageType = MessageType.INBOX;
    private int messagesCount;

    private MessageDataPage dataPage;

    private int pageNumber = 1;

    private int numberOfMessagesLeft = Integer.MAX_VALUE;

    @SuppressWarnings("unused")
    public MessagingBean() { }

    public MessagingBean(UserWrapper member, int pageNumber, MessageType messageType) {
        this.member = member;
        this.pageNumber = pageNumber;
        this.messageType = messageType;

        dataPage = new MessageDataPage(member, messageType, PAGE_SIZE, pageNumber);

        messagesCount = StaticUserContext.getMessagingClient().countMessages(member.getId(), messageType);

        numberOfMessagesLeft = StaticUserContext.getMessagingClient().getNumberOfMessagesLeft(member.getId());
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

    public UserWrapper getUser() {
        return member;
    }

    public void setUser(UserWrapper user) {
        this.member = user;
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

    public int getNumberOfPages() {
        int numPages = messagesCount / PAGE_SIZE;
        if (messagesCount % PAGE_SIZE != 0) {
            numPages++;
        }
        return numPages;
    }

    public int getNumberOfMessagesLeft() {
        return numberOfMessagesLeft;
    }

    public PageNavigation getPageNavigation() {
        String url = "/messaging";
        if (messageType != MessageType.INBOX) {
            url += "/mailbox/" + messageType.name();
        }
        final PageNavigation pageNavigation = new PageNavigation(url, pageNumber, getNumberOfPages(),"pageNumber");
        return pageNavigation;
    }
}
