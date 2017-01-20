package org.xcolab.view.pages.messaging.paging;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.view.pages.messaging.beans.MessageBean;

import java.util.ArrayList;
import java.util.List;

public class MessageDataPage {

    private Member member;
    private List<MessageBean> messages;

    @SuppressWarnings("unused")
    public MessageDataPage() { }

    public MessageDataPage(Member member, MessageType messageType, int pageSize, int pageNumber) {
        this.member = member;

        final int firstMessage = pageSize * (pageNumber - 1);
        final int lastMessage = pageSize * pageNumber;
        List<Message> messagesRaw = MessagingClient
                .getMessages(member.getUserId(), firstMessage, lastMessage, messageType);

        this.messages = new ArrayList<>();
        for (Message message : messagesRaw) {
            messages.add(new MessageBean(message));
        }
    }

    public Member getUser() {
        return member;
    }

    public void setUser(Member user) {
        this.member = user;
    }

    public List<MessageBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageBean> messages) {
        this.messages = messages;
    }
}
