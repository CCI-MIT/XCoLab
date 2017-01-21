package org.xcolab.view.pages.messaging.utils;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.messaging.beans.MessageBean;

import javax.servlet.http.HttpServletRequest;

public class MessagingPermissions {

    private final long memberId;
    private MessageBean message;
    private Boolean isRecipient;

    public MessagingPermissions(HttpServletRequest request) {
        memberId = MemberAuthUtil.getMemberId(request);
    }

    public MessagingPermissions(HttpServletRequest request, MessageBean message) {
        this(request);
        this.message = message;
    }

    public boolean getCanSendMessage() {
        return memberId>0 || MessagingClient.canMemberSendMessage(memberId, 1) || getCanAdminAll();
    }

    public boolean getCanViewMessage() {
        return memberId>0 || message.getFrom().getUserId() == memberId
                || isRecipient();
    }

    public boolean isRecipient() {
        if (isRecipient == null) {
            for (Member recipient : message.getTo()) {
                if (recipient.getId_() == memberId) {
                    isRecipient = true;
                    return true;
                }
            }
            isRecipient = false;
        }
        return isRecipient;
    }

    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(memberId);
    }
}
