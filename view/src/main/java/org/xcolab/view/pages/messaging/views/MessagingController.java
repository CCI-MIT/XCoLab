package org.xcolab.view.pages.messaging.views;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.entity.utils.flash.AlertMessage;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.messaging.beans.MessageBean;
import org.xcolab.view.pages.messaging.beans.MessagingBean;
import org.xcolab.view.pages.messaging.beans.SendMessageBean;
import org.xcolab.view.pages.messaging.utils.MessagingPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions
        .DiscussionAuthorizationException;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class MessagingController {

    private static final long COMMUNITY_TOP_CONTENT_ARTICLE_ID = ConfigurationAttributeKey.MEMBERS_CONTENT_ARTICLE_ID.get();

    @ModelAttribute("communityTopContentArticleId")
            public Long getCommunityTopContentArticleId(){
        return COMMUNITY_TOP_CONTENT_ARTICLE_ID;
    }

    @GetMapping({"/messaging","/web/guest/messaging"})
    public String showMessagesDefault(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam(required = false) Integer pageNumber) {
        if(pageNumber == null ){
            pageNumber = 1;
        }
        return showMessages(request,response,model,"INBOX",pageNumber);

    }
    @GetMapping("/messaging/mailbox/{mailboxType}")
    public String showMessagesBoxType(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable String mailboxType,
            @RequestParam(required = false) Integer pageNumber) {
        if(pageNumber == null ){
            pageNumber = 1;
        }
        return showMessages(request,response,model,mailboxType,pageNumber);
    }

    private String showMessages(HttpServletRequest request, HttpServletResponse response, Model model,
             String mailboxType,
            Integer pageNumber) {

        long memberId = MemberAuthUtil.getMemberId(request);

        if(memberId < 0 ){
            return "notAllowed";
        }
        final MessagingBean messagingBean = new MessagingBean(MembersClient.getMemberUnchecked(memberId),
                pageNumber != null ? pageNumber : 1,
                StringUtils.isNotBlank(mailboxType) ? MessageType.valueOf(mailboxType) : MessageType.INBOX);
        model.addAttribute("messagingBean", messagingBean);

        return "/messaging/messages";
    }

    @GetMapping("/messaging/compose")
    public String composeMessage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam(required = false) Integer messageId) {

        long memberId = MemberAuthUtil.getMemberId(request);
        if(memberId < 0 ){
            return "notAllowed";
        }
        model.addAttribute("sendMessageBean", new SendMessageBean(memberId));
        return "/messaging/composeMessage";
    }


    @GetMapping("/messaging/message/{messageId}")
    public String showMessage(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable  Integer messageId)
            throws MessageNotFoundException {
        long memberId = MemberAuthUtil.getMemberId(request);

        final MessageBean messageBean = new MessageBean(MessagingClient.getMessage(messageId));
        final MessagingPermissions messagingPermissions = new MessagingPermissions(request, messageBean);
        if (!messagingPermissions.getCanViewMessage()) {

            return "notAllowed";
        }

        if (messagingPermissions.isRecipient()) {
            messageBean.markMessageAsOpened(memberId);
        }
        model.addAttribute("user", MembersClient.getMemberUnchecked(memberId));

        final SendMessageBean sendMessageBean = new SendMessageBean(messageBean);
        model.addAttribute("sendMessageBean", sendMessageBean);
        model.addAttribute("messageBean", messageBean);

        return "/messaging/message";
    }


    @PostMapping("/messaging/archiveMessages")
    public String archiveMessages(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("messagingBean") MessagingBean messagingBean)
            throws MessageNotFoundException, IOException {
        long memberId = MemberAuthUtil.getMemberId(request);
        if(memberId < 0 ){
            return "notAllowed";
        }
        if (messagingBean.getDataPage() != null) {
            List<MessageBean> items = messagingBean.getDataPage().getMessages();
            for (MessageBean item : items) {
                if (item.isSelected()) {
                    Message message = item.getMessage();
                    MessagingClient.setArchived(message.getMessageId(), memberId, true);
                }
            }
        }
        AlertMessage.success("The message(s) have been archived!").flash(request);
        return showMessages(request,response,model,"INBOX",1);
    }

    @PostMapping("/messaging/sendMessage")
    public String sendMessage(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("sendMessageBean") SendMessageBean sendMessageBean)
            throws MessageLimitExceededException {

        Member member = MemberAuthUtil.getMemberOrNull(request);

        if(member == null ){
            return "notAllowed";
        }

        final MessagingPermissions messagingPermissions = new MessagingPermissions(request);

        if (messagingPermissions.getCanSendMessage()) {
            sendMessageBean.send(member, LinkUtils.getBaseUri(request));
        }

        AlertMessage.success("The message has been sent!").flash(request);
        return showMessages(request,response,model,"INBOX",1);
    }
}
