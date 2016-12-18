package org.xcolab.portlets.messaging.views;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.util.mail.MailEngineException;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.portlets.messaging.beans.MessageBean;
import org.xcolab.portlets.messaging.beans.MessagingBean;
import org.xcolab.portlets.messaging.beans.SendMessageBean;
import org.xcolab.portlets.messaging.utils.MessagingPermissions;

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

        long memberId = MemberAuthUtil.getMemberId(request);

        final MessagingBean messagingBean = new MessagingBean(MembersClient.getMemberUnchecked(memberId),
                pageNumber != null ? pageNumber : 1,
                StringUtils.isNotBlank(mailboxType) ? MessageType.valueOf(mailboxType) : MessageType.INBOX);
        model.addAttribute("messagingBean", messagingBean);

        return "messages";
    }

    @RenderMapping(params = {"page=composeMessage"})
    public String composeMessage(RenderRequest request, RenderResponse response, Model model,
            @RequestParam(required = false) Integer messageId) {

        long memberId = MemberAuthUtil.getMemberId(request);
        model.addAttribute("sendMessageBean", new SendMessageBean(memberId));
        return "composeMessage";
    }

    @RenderMapping(params = {"page=viewMessage"})
    public String showMessage(RenderRequest request, RenderResponse response, Model model,
            @RequestParam(required = false) Integer messageId)
            throws MessageNotFoundException, DiscussionAuthorizationException {
        long memberId = MemberAuthUtil.getMemberId(request);

        model.addAttribute("user", MembersClient.getMemberUnchecked(memberId));
        final MessageBean messageBean = new MessageBean(MessagingClient.getMessage(messageId));

        final MessagingPermissions messagingPermissions = new MessagingPermissions(request, messageBean);
        if (!messagingPermissions.getCanViewMessage()) {
            throw new DiscussionAuthorizationException("User " + memberId
                    + " is not authorized to view message " + messageId);
        }

        if (messagingPermissions.isRecipient()) {
            messageBean.markMessageAsOpened(memberId);
        }
        final SendMessageBean sendMessageBean = new SendMessageBean(messageBean);
        model.addAttribute("sendMessageBean", sendMessageBean);
        model.addAttribute("messageBean", messageBean);

        return "message";
    }

    @RequestMapping(params = {"action=archiveMessages"})
    public void archiveMessages(ActionRequest request, ActionResponse response, Model model,
            @ModelAttribute("messagingBean") MessagingBean messagingBean)
            throws MessageNotFoundException {
        long memberId = MemberAuthUtil.getMemberId(request);

        if (messagingBean.getDataPage() != null) {
            List<MessageBean> items = messagingBean.getDataPage().getMessages();
            for (MessageBean item : items) {
                if (item.isSelected()) {
                    Message message = item.getMessage();
                    MessagingClient.setArchived(message.getMessageId(), memberId, true);
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

        long memberId = MemberAuthUtil.getMemberId(request);
        Member member = MembersClient.getMemberUnchecked(memberId);

        final MessagingPermissions messagingPermissions = new MessagingPermissions(request);

        if (messagingPermissions.getCanSendMessage()) {
            sendMessageBean.send(member, LinkUtils.getBaseUri(request));
        }
    }
}
