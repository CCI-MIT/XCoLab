package org.xcolab.view.pages.messaging.beans;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.pages.messaging.paging.MessageDataPage;
import org.xcolab.view.util.pagination.PageNavigation;

import java.io.Serializable;

public class MessagingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int PAGE_SIZE = 10;

    private Member member;
    private MessageType messageType = MessageType.INBOX;
    private int messagesCount;

    private MessageDataPage dataPage;

    private int pageNumber = 1;

    private int numberOfMessagesLeft = Integer.MAX_VALUE;

    @SuppressWarnings("unused")
    public MessagingBean() { }

    public MessagingBean(Member member, int pageNumber, MessageType messageType) {
        this.member = member;
        this.pageNumber = pageNumber;
        this.messageType = messageType;

        dataPage = new MessageDataPage(member, messageType, PAGE_SIZE, pageNumber);

        messagesCount = MessagingClient.countMessages(member.getId(), messageType);

        numberOfMessagesLeft = MessagingClient.getNumberOfMessagesLeft(member.getId());
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

    public Member getUser() {
        return member;
    }

    public void setUser(Member user) {
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
