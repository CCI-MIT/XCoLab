package org.xcolab.portlets.messaging.utils;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.portlets.messaging.beans.MessageBean;

import javax.portlet.PortletRequest;

public class MessagingPermissions {

    private final long memberId;
    private MessageBean message;
    private Boolean isRecipient;

    public MessagingPermissions(PortletRequest request) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        memberId = themeDisplay.getUser().getUserId();
    }

    public MessagingPermissions(PortletRequest request, MessageBean message) {
        this(request);
        this.message = message;
    }

    public boolean getCanSendMessage() {
        return MessagingClient.canMemberSendMessage(memberId, 1) || getCanAdminAll();
    }

    public boolean getCanViewMessage() {
        return message.getFrom().getUserId() == memberId
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
