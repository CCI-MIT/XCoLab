package org.xcolab.view.pages.messaging.utils;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.pages.messaging.beans.MessageBean;

import java.util.List;


public class MessagingPermissions {

    private final Member loggedInMember;
    private final boolean isLoggedIn;
    private MessageBean message;

    public MessagingPermissions(Member loggedInMember) {
        this.loggedInMember = loggedInMember;
        this.isLoggedIn = loggedInMember != null;
    }

    public MessagingPermissions(Member loggedInMember, MessageBean message) {
        this(loggedInMember);
        this.message = message;
    }

    public boolean getCanSendMessage() {
        return getCanAdminAll() || (isLoggedIn && MessagingClient
                .canMemberSendMessage(loggedInMember.getId_(), 1));
    }

    public boolean getCanViewMessage() {
        return isRecipient() || isSender();
    }

    public boolean getCanViewThread(String threadId, List<MessageBean> fullConversation) {
        //I sent first message
        boolean firstMessageSent = fullConversation.get(0).getFrom().getUserId() == loggedInMember.getId_();
        //I received it
        boolean firstMessageReceived = fullConversation.get(0).getTo().stream()
                .anyMatch(recipient -> recipient.getId_() == loggedInMember.getId_());
        //ThreadId ends with my ID
        boolean myThread = false;
        if (threadId!=null){
            myThread = threadId.endsWith(String.valueOf(loggedInMember.getId_()));
        }
        //Looking for a single message (backwards compatibility)
        boolean singleMessage=fullConversation.size()==1 && this.getCanViewMessage();

        return firstMessageSent || (firstMessageReceived && myThread) || singleMessage;
    }

    public boolean isSender() {
        return isLoggedIn && message != null && message.getFrom().getUserId() == loggedInMember
                .getId_();
    }

    public boolean isRecipient() {
        return isLoggedIn && message != null && message.getTo().stream()
                .anyMatch(recipient -> recipient.getId_() == loggedInMember.getId_());
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(loggedInMember);
    }
}
