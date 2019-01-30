package org.xcolab.view.pages.messaging.paging;

import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.legacy.enums.MessageType;
import org.xcolab.client.user.pojo.wrapper.MessageWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.pages.messaging.beans.MessageBean;

import java.util.ArrayList;
import java.util.List;

public class MessageDataPage {

    private UserWrapper member;
    private List<MessageBean> messages;

    @SuppressWarnings("unused")
    public MessageDataPage() { }

    public MessageDataPage(UserWrapper member, MessageType messageType, int pageSize, int pageNumber) {
        this.member = member;

        final int firstMessage = pageSize * (pageNumber - 1);
        final int lastMessage = pageSize * pageNumber;
        List<MessageWrapper> messagesRaw = StaticUserContext.getMessagingClient()
                .getMessages(member.getId(), firstMessage, lastMessage, messageType);

        this.messages = new ArrayList<>();
        MessageWrapper previous = null;
        for (MessageWrapper message : messagesRaw) {
            if (previous == null || !message.getId().equals(previous.getId())) {
                messages.add(new MessageBean(message));
            }
            previous = message;
        }
    }

    public UserWrapper getUser() {
        return member;
    }

    public void setUser(UserWrapper user) {
        this.member = user;
    }

    public List<MessageBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageBean> messages) {
        this.messages = messages;
    }
}
