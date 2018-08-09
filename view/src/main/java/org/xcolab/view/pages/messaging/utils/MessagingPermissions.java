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
                .canMemberSendMessage(loggedInMember.getId(), 1));
    }

    public boolean getCanViewMessage() {
        return isRecipient() || isSender();
    }

    public boolean getCanViewThread(String threadId, List<MessageBean> fullConversation) {
        if (!isLoggedIn) {
            return false;
        }
        
        fullConversation.sort(new MessageBeanDateComparator());
        MessageBean originalMessage = fullConversation.get(fullConversation.size() - 1);
        boolean didSendOriginalMessage = originalMessage.getFrom().getId() == loggedInMember.getId();
        boolean didReceiveOriginalMessage = originalMessage.getTo().stream()
                .anyMatch(recipient -> recipient.getId() == loggedInMember.getId());
        boolean isMyThread = false;
        if (threadId != null) {
            isMyThread = threadId.endsWith(String.valueOf(loggedInMember.getId()));
        }
        //Looking for a single message (backwards compatibility)
        boolean isSingleMessage = fullConversation.size() == 1 && this.getCanViewMessage();

        return didSendOriginalMessage || (didReceiveOriginalMessage && isMyThread) || isSingleMessage;
    }

    public boolean isSender() {
        return isLoggedIn && message != null && message.getFrom().getId() == loggedInMember
                .getId();
    }

    public boolean isRecipient() {
        return isLoggedIn && message != null && message.getTo().stream()
                .anyMatch(recipient -> recipient.getId() == loggedInMember.getId());
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(loggedInMember);
    }
}
