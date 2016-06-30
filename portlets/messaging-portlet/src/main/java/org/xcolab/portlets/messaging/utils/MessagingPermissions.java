package org.xcolab.portlets.messaging.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.portlets.messaging.beans.MessageBean;
import org.xcolab.utils.MessageLimitManager;

import javax.portlet.PortletRequest;

public class MessagingPermissions {

    private final User user;
    private MessageBean message;
    private Boolean isRecipient;

    public MessagingPermissions(PortletRequest request) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        user = themeDisplay.getUser();
    }

    public MessagingPermissions(PortletRequest request, MessageBean message) {
        this(request);
        this.message = message;
    }

    public boolean getCanSendMessage() {
        try {
            return MessageLimitManager.canSendMessages(1, user) || getCanAdminAll();
        } catch (PortalException | SystemException e) {
            return getCanAdminAll();
        }
    }

    public boolean getCanViewMessage() {
        return message.getFrom().getUserId() == user.getUserId()
                || isRecipient();
    }

    public boolean isRecipient() {
        if (isRecipient == null) {
            for (Member recipient : message.getTo()) {
                if (recipient.getId_() == user.getUserId()) {
                    isRecipient = true;
                    return true;
                }
            }
            isRecipient = false;
        }
        return isRecipient;
    }

    /**
     * Returns true if user is admin (not only proposal contributor)
     */
    public boolean getCanAdminAll() {
        return PermissionsClient.canAdminAll(user.getUserId());
    }

    public User getUser() {
        return user;
    }
}
