package org.xcolab.portlets.messaging.views;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.mail.MailEngineException;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.portlets.messaging.beans.MessageBean;
import org.xcolab.portlets.messaging.beans.MessagingBean;
import org.xcolab.portlets.messaging.beans.SendMessageBean;
import org.xcolab.portlets.messaging.utils.MessagingPermissions;
import org.xcolab.entity.utils.LinkUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Controller
@RequestMapping("view")
public class MessagingController {

    @RenderMapping
    public String showMessages(RenderRequest request, RenderResponse response, Model model,
            @RequestParam(required = false) String mailboxType,
            @RequestParam(required = false) Integer pageNumber) {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();

        final MessagingBean messagingBean = new MessagingBean(user,
                pageNumber != null ? pageNumber : 1,
                StringUtils.isNotBlank(mailboxType) ? MessageType.valueOf(mailboxType) : MessageType.INBOX);
        model.addAttribute("messagingBean", messagingBean);

        return "messages";
    }

    @RenderMapping(params = {"page=composeMessage"})
    public String composeMessage(RenderRequest request, RenderResponse response, Model model,
            @RequestParam(required = false) Integer messageId) {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        User user = themeDisplay.getUser();
        model.addAttribute("sendMessageBean", new SendMessageBean(user));
        return "composeMessage";
    }

    @RenderMapping(params = {"page=viewMessage"})
    public String showMessage(RenderRequest request, RenderResponse response, Model model,
            @RequestParam(required = false) Integer messageId)
            throws MessageNotFoundException, DiscussionAuthorizationException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        User user = themeDisplay.getUser();

        model.addAttribute("user", user);
        final MessageBean messageBean = new MessageBean(MessagingClient.getMessage(messageId));

        final MessagingPermissions messagingPermissions = new MessagingPermissions(request, messageBean);
        if (!messagingPermissions.getCanViewMessage()) {
            throw new DiscussionAuthorizationException("User " + user.getUserId()
                    + " is not authorized to view message " + messageId);
        }

        if (messagingPermissions.isRecipient()) {
            messageBean.markMessageAsOpened(user.getUserId());
        }
        final SendMessageBean sendMessageBean = new SendMessageBean(messageBean);
        model.addAttribute("sendMessageBean", sendMessageBean);
        model.addAttribute("messageBean", messageBean);

        return "message";
    }

    @RequestMapping(params = {"action=archiveMessages"})
    public void archiveMessages(ActionRequest request, ActionResponse response, Model model,
            @ModelAttribute("messagingBean") MessagingBean messagingBean)
            throws PortalException, SystemException, MessageNotFoundException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user = themeDisplay.getUser();

        if (messagingBean.getDataPage() != null) {
            List<MessageBean> items = messagingBean.getDataPage().getMessages();
            for (MessageBean item : items) {
                if (item.isSelected()) {
                    Message message = item.getMessage();
                    MessagingClient.setArchived(message.getMessageId(), user.getUserId(), true);
                }
            }
        }
    }

    @RequestMapping(params = {"action=sendMessage"})
    public void sendMessage(ActionRequest request, ActionResponse response, Model model,
            @ModelAttribute("sendMessageBean") SendMessageBean sendMessageBean)
            throws AddressException, UnsupportedEncodingException, MailEngineException,
            //TODO: show better message for validation error
            MessageLimitExceededException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Member member = MembersClient.getMemberUnchecked(themeDisplay.getUserId());

        final MessagingPermissions messagingPermissions = new MessagingPermissions(request);

        if (messagingPermissions.getCanSendMessage()) {
            sendMessageBean.send(member, LinkUtils.getBaseUri(request));
        }
    }
}
