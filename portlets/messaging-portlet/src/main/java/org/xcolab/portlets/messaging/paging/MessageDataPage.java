package org.xcolab.portlets.messaging.paging;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.Message;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.portlets.messaging.beans.MessageBean;
import org.xcolab.legacy.enums.MessageType;

import java.util.ArrayList;
import java.util.List;

public class MessageDataPage {

    private User user;
    private List<MessageBean> messages;

    @SuppressWarnings("unused")
    public MessageDataPage() { }

    public MessageDataPage(User user, MessageType messageType, int pageSize, int pageNumber)
            throws PortalException, SystemException {
        this.user = user;

        List<Message> messagesRaw = MessageUtil.getMessages(user.getUserId(),
                pageSize * (pageNumber - 1), pageSize * pageNumber, messageType);

        this.messages = new ArrayList<>();
        for (Message message : messagesRaw) {
            messages.add(new MessageBean(message));
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MessageBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageBean> messages) {
        this.messages = messages;
    }
}
