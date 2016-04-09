package org.xcolab.portlets.messaging.beans;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.legacy.enums.MessageType;
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

    @SuppressWarnings("unused")
    public MessagingBean() { }

    public MessagingBean(User user, int pageNumber, MessageType messageType) throws SystemException, PortalException {
        this.user = user;
        this.pageNumber = pageNumber;
        this.messageType = messageType;

        dataPage = new MessageDataPage(user, messageType, PAGE_SIZE, pageNumber);

        messagesCount = MessageUtil.countMessages(user.getUserId(), messageType);
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

    public void markMessageAsOpened(long messageId) throws PortalException, SystemException {
        List<MessageRecipientStatus> statuses =
                MessageRecipientStatusLocalServiceUtil.findByMessageId(messageId, 0, Integer.MAX_VALUE);
        for (MessageRecipientStatus mr : statuses) {
            if (mr.getUserId() == user.getUserId()) {
                mr.setOpened(true);
                MessageRecipientStatusLocalServiceUtil.updateMessageRecipientStatus(mr);
            }
        }
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
}
