package org.xcolab.view.pages.messaging.views;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.entity.utils.flash.AlertMessage;
import org.xcolab.util.IdListUtil;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.messaging.beans.MessageBean;
import org.xcolab.view.pages.messaging.beans.MessagingBean;
import org.xcolab.view.pages.messaging.beans.SendMessageBean;
import org.xcolab.view.pages.messaging.utils.MessagingPermissions;

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
            @RequestParam(required = false) Integer pageNumber, Member loggedInMember) {
        if (pageNumber == null ) {
            pageNumber = 1;
        }
        return showMessages(request, response, model, "INBOX" , pageNumber, loggedInMember);

    }
    @GetMapping("/messaging/mailbox/{mailboxType}")
    public String showMessagesBoxType(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable String mailboxType,
            @RequestParam(required = false) Integer pageNumber, Member loggedInMember) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        return showMessages(request, response, model, mailboxType, pageNumber, loggedInMember);
    }

    private String showMessages(HttpServletRequest request, HttpServletResponse response, Model model,
             String mailboxType, Integer pageNumber, Member loggedInMember) {

        if (loggedInMember == null) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        final MessagingBean messagingBean = new MessagingBean(loggedInMember,
                pageNumber != null ? pageNumber : 1,
                StringUtils.isNotBlank(mailboxType) ? MessageType.valueOf(mailboxType) : MessageType.INBOX);
        model.addAttribute("messagingBean", messagingBean);

        model.addAttribute("_activePageLink", "community");
        return "/messaging/messages";
    }

    @GetMapping("/messaging/compose")
    public String composeMessage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam(required = false) Integer messageId, Member loggedInMember) {

        if (loggedInMember == null) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
        model.addAttribute("sendMessageBean", new SendMessageBean(loggedInMember.getId_()));
        model.addAttribute("_activePageLink", "community");
        return "/messaging/composeMessage";
    }


    @GetMapping("/messaging/message/{messageId}")
    public String showMessage(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable  Integer messageId, Member loggedInMember)
            throws MessageNotFoundException {

        final MessageBean messageBean = new MessageBean(MessagingClient.getMessage(messageId));
        final MessagingPermissions messagingPermissions = new MessagingPermissions(request, messageBean);

        if (!messagingPermissions.getCanViewMessage()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        if (messagingPermissions.isRecipient()) {
            messageBean.markMessageAsOpened(loggedInMember.getId_());
        }
        model.addAttribute("user", loggedInMember);

        final SendMessageBean sendMessageBean = new SendMessageBean(messageBean);
        model.addAttribute("sendMessageBean", sendMessageBean);
        model.addAttribute("messageBean", messageBean);
        model.addAttribute("_activePageLink", "community");
        return "/messaging/message";
    }


    @PostMapping("/messaging/archiveMessages")
    public String archiveMessages(HttpServletRequest request, HttpServletResponse response, Model model,
            @ModelAttribute("messagingBean") MessagingBean messagingBean, Member loggedInMember)
            throws MessageNotFoundException, IOException {

        if (loggedInMember == null) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        if (messagingBean.getDataPage() != null) {
            List<MessageBean> items = messagingBean.getDataPage().getMessages();
            for (MessageBean item : items) {
                if (item.isSelected()) {
                    Message message = item.getMessage();
                    MessagingClient.setArchived(message.getMessageId(), loggedInMember.getId_(), true);
                }
            }
        }
        AlertMessage.success("The message(s) have been archived!").flash(request);
        return showMessages(request, response, model, "INBOX", 1, loggedInMember);
    }

    @PostMapping("/messaging/sendMessage")
    public String sendMessage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam String userIdsRecipients, @RequestParam String messageSubject,
            @RequestParam String messageContent, Member loggedInMember)
            throws MessageLimitExceededException, IOException {

        if (loggedInMember == null ) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        final MessagingPermissions messagingPermissions = new MessagingPermissions(request);

        if (messagingPermissions.getCanSendMessage()) {
            List<Long> recipientIds = IdListUtil.getIdsFromString(userIdsRecipients);

            MessagingClient.checkLimitAndSendMessage(HtmlUtil.cleanAll(messageSubject),
                    HtmlUtil.cleanSome(messageContent, ConfigurationAttributeKey.COLAB_URL.get()),
                    loggedInMember.getUserId(), recipientIds);
        }

        AlertMessage.success("The message has been sent!").flash(request);
        String refererHeader = request.getHeader(HttpHeaders.REFERER);

        if (StringUtils.isNotBlank(refererHeader)) {
            return "redirect:" + UriComponentsBuilder.fromHttpUrl(refererHeader).build().getPath();
        }
        return showMessages(request, response, model, "INBOX", 1, loggedInMember);
    }
}
