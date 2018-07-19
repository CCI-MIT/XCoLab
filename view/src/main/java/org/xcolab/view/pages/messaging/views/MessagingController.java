package org.xcolab.view.pages.messaging.views;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MessageNotFoundException;
import org.xcolab.client.members.legacy.enums.MessageType;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Message;
import org.xcolab.commons.IdListUtil;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.messaging.beans.MessageBean;
import org.xcolab.view.pages.messaging.beans.MessagingBean;
import org.xcolab.view.pages.messaging.beans.SendMessageBean;
import org.xcolab.view.pages.messaging.utils.MessagingPermissions;
import org.xcolab.commons.servlet.flash.AlertMessage;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/messaging")
public class MessagingController {

    @ModelAttribute("communityTopContentArticleId")
    public Long getCommunityTopContentArticleId() {
        return ConfigurationAttributeKey.MEMBERS_CONTENT_ARTICLE_ID.get();
    }

    @GetMapping
    public String showMessagesDefault(HttpServletResponse response, Model model,
            @RequestParam(defaultValue = "1") Integer pageNumber, Member loggedInMember) {
        return showMessages(response, model, "INBOX", pageNumber, loggedInMember);
    }

    @GetMapping("mailbox/{mailboxType}")
    public String showMessagesBoxType(HttpServletResponse response, Model model,
            @PathVariable String mailboxType, @RequestParam(defaultValue = "1") Integer pageNumber,
            Member loggedInMember) {
        return showMessages(response, model, mailboxType, pageNumber, loggedInMember);
    }

    private String showMessages(HttpServletResponse response, Model model, String mailboxType,
            Integer pageNumber, Member loggedInMember) {

        if (loggedInMember == null) {
            return new AccessDeniedPage(null).toViewName(response);
        }

        final MessagingBean messagingBean =
                new MessagingBean(loggedInMember, pageNumber != null ? pageNumber : 1,
                        StringUtils.isNotBlank(mailboxType) ? MessageType.valueOf(mailboxType)
                                : MessageType.INBOX);
        model.addAttribute("messagingBean", messagingBean);

        model.addAttribute("_activePageLink", "community");
        return "/messaging/messages";
    }

    @GetMapping("message/{messageId}")
    public String showMessage(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable Integer messageId, Member loggedInMember)
            throws MessageNotFoundException {

        final MessageBean messageBean = new MessageBean(MessagingClient.getMessage(messageId));
        final MessagingPermissions messagingPermissions =
                new MessagingPermissions(loggedInMember, messageBean);

        if (!messagingPermissions.getCanViewMessage()) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
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

    @PostMapping("archiveMessages")
    public String archiveMessages(HttpServletRequest request, HttpServletResponse response,
            Model model, @ModelAttribute("messagingBean") MessagingBean messagingBean,
            Member loggedInMember) throws MessageNotFoundException, IOException {

        if (loggedInMember == null) {
            return new AccessDeniedPage(null).toViewName(response);
        }

        if (messagingBean.getDataPage() != null) {
            List<MessageBean> items = messagingBean.getDataPage().getMessages();
            for (MessageBean item : items) {
                if (item.isSelected()) {
                    Message message = item.getMessage();
                    MessagingClient
                            .setArchived(message.getMessageId(), loggedInMember.getId_(), true);
                }
            }
        }
        AlertMessage.success("The message(s) have been archived!").flash(request);
        return showMessages(response, model, "INBOX", 1, loggedInMember);
    }

    @PostMapping("sendMessage")
    public String sendMessage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam String userIdsRecipients, @RequestParam String messageSubject,
            @RequestParam String messageContent, Member loggedInMember) throws IOException {

        if (loggedInMember == null) {
            return new AccessDeniedPage(null).toViewName(response);
        }

        final MessagingPermissions messagingPermissions = new MessagingPermissions(loggedInMember);

        if (messagingPermissions.getCanSendMessage()) {
            List<Long> recipientIds = IdListUtil.getIdsFromString(userIdsRecipients);
            try {
                final String baseUri = PlatformAttributeKey.COLAB_URL.get();
                MessagingClient.checkLimitAndSendMessage(HtmlUtil.cleanAll(messageSubject),
                        HtmlUtil.cleanSome(messageContent, baseUri), loggedInMember.getUserId(),
                        recipientIds);
                AlertMessage.success("The message has been sent!").flash(request);
            } catch (MessageLimitExceededException e) {
                AlertMessage.danger("You have exceeded your daily message limit. "
                        + "Please try again later and send fewer messages.").flash(request);
            }
        } else {
            AlertMessage.danger("Sorry, you are not allowed to send any more messages today.")
                    .flash(request);
        }

        String refererHeader = request.getHeader(HttpHeaders.REFERER);

        if (StringUtils.isNotBlank(refererHeader)) {
            return "redirect:" + UriComponentsBuilder.fromHttpUrl(refererHeader).build().getPath();
        }
        return showMessages(response, model, "INBOX", 1, loggedInMember);
    }

    @GetMapping("sendMessage")
    public String handleInvalidHttpMethod(HttpServletRequest request) {
        AlertMessage.warning("Warning: page reloaded before message was sent.")
                .flash(request);
        String referrer = request.getHeader(HttpHeaders.REFERER);
        if (StringUtils.isNotEmpty(referrer) && LinkUtils.isLocalUrl(referrer)
                //avoid circular redirect
                && !referrer.endsWith("sendMessage")) {
            return "redirect:" + referrer;
        }
        return "redirect:/messaging";
    }
}
