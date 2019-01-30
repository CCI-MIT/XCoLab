package org.xcolab.view.pages.messaging.views;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.user.IMessagingClient;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MessageNotFoundException;
import org.xcolab.client.user.exceptions.MessageOrThreadNotFoundException;
import org.xcolab.client.user.legacy.enums.MessageType;
import org.xcolab.client.user.messaging.MessageLimitExceededException;
import org.xcolab.client.user.pojo.wrapper.MessageWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.IdListUtil;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.messaging.beans.MessageBean;
import org.xcolab.view.pages.messaging.beans.MessagingBean;
import org.xcolab.view.pages.messaging.beans.SendMessageBean;
import org.xcolab.view.pages.messaging.utils.MessagingPermissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/messaging")
public class MessagingController {

    @Autowired
    private IMessagingClient messagingClient;

    @ModelAttribute("communityTopContentArticleId")
    public Long getCommunityTopContentArticleId() {
        return ConfigurationAttributeKey.MEMBERS_CONTENT_ARTICLE_ID.get();
    }

    @GetMapping
    public String showMessagesDefault(HttpServletResponse response, Model model,
            @RequestParam(defaultValue = "1") Integer pageNumber, UserWrapper loggedInMember) {
        return showMessages(response, model, "INBOX", pageNumber, loggedInMember);
    }

    @GetMapping("mailbox/{mailboxType}")
    public String showMessagesBoxType(HttpServletResponse response, Model model,
            @PathVariable String mailboxType, @RequestParam(defaultValue = "1") Integer pageNumber,
            UserWrapper loggedInMember) {
        return showMessages(response, model, mailboxType, pageNumber, loggedInMember);
    }

    private String showMessages(HttpServletResponse response, Model model, String mailboxType,
            Integer pageNumber, UserWrapper loggedInMember) {

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
            @PathVariable Integer messageId, UserWrapper loggedInMember)
            throws MessageNotFoundException {

        final MessageBean messageBean = new MessageBean(messagingClient.getMessage(messageId));
        final MessagingPermissions messagingPermissions =
                new MessagingPermissions(loggedInMember, messageBean);

        if (!messagingPermissions.getCanViewMessage()) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }

        if (messagingPermissions.isRecipient()) {
            messageBean.markMessageAsOpened(loggedInMember.getId());
        }
        model.addAttribute("user", loggedInMember);

        final SendMessageBean sendMessageBean = new SendMessageBean(messageBean);
        model.addAttribute("sendMessageBean", sendMessageBean);
        model.addAttribute("currentMessageBean", messageBean);
        model.addAttribute("_activePageLink", "community");
        return "/messaging/message";
    }

    @GetMapping("fullConversation/{messageId}")
    public String showFullConversation(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable Long messageId, UserWrapper loggedInMember,
            @RequestParam(required=false) String threadId) throws MessageOrThreadNotFoundException, MessageNotFoundException {
        List<MessageWrapper> fullConversation = new ArrayList<>();
        //Retrieve conversation and check if it was found
        if (StringUtils.isNotEmpty(threadId)) {
                fullConversation = StaticUserContext.getMessagingClient().getFullConversation(messageId, threadId);
        } else {
            fullConversation.add(StaticUserContext.getMessagingClient().getMessage(messageId));
        }

        boolean isLastMessage = messageId.equals(fullConversation.get(0).getId());

        //Transform messages into beans and discard messages newer than this one
        List<MessageBean> messageBeanListNewestFirst = new ArrayList<>();
        boolean reachedRequiredMessage = false;
        for (MessageWrapper message : fullConversation){
            if (message.getId().equals(messageId)) {
                reachedRequiredMessage = true;
            }
            if (reachedRequiredMessage) {
                messageBeanListNewestFirst.add(new MessageBean(message));
            }
        }
        
        //Manage permissions
        final MessagingPermissions messagingPermissions =
                new MessagingPermissions(loggedInMember, messageBeanListNewestFirst.get(0));
        if (!messagingPermissions.getCanViewThread(threadId, messageBeanListNewestFirst)){
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }

        //Mark first message as read (if it's for me)
        if (messagingPermissions.isRecipient()) {
            messageBeanListNewestFirst.get(0).markMessageAsOpened(loggedInMember.getId());
        }

        final SendMessageBean sendMessageBean = new SendMessageBean(
                messageBeanListNewestFirst.get(0));

        final MessageBean currentMessageBean = messageBeanListNewestFirst.remove(0);

        //Add model attributes
        model.addAttribute("user", loggedInMember);
        model.addAttribute("sendMessageBean", sendMessageBean);
        model.addAttribute("currentMessageBean", currentMessageBean);
        model.addAttribute("_activePageLink", "community");
        model.addAttribute("messageBeanList",messageBeanListNewestFirst);
        model.addAttribute("threadId",threadId);
        model.addAttribute("isLastMessage", isLastMessage);
        model.addAttribute("requestedMessageId", messageId);

        return "/messaging/message";
    }

    @PostMapping("archiveMessages")
    public String archiveMessages(HttpServletRequest request, HttpServletResponse response,
            Model model, @ModelAttribute("messagingBean") MessagingBean messagingBean,
            UserWrapper loggedInMember) throws MessageNotFoundException, IOException {

        if (loggedInMember == null) {
            return new AccessDeniedPage(null).toViewName(response);
        }

        if (messagingBean.getDataPage() != null) {
            List<MessageBean> items = messagingBean.getDataPage().getMessages();
            for (MessageBean item : items) {
                if (item.isSelected()) {
                    MessageWrapper message = item.getMessage();
                    StaticUserContext.getMessagingClient()
                            .setArchived(message.getId(), loggedInMember.getId(), true);
                }
            }
        }
        AlertMessage.success("The message(s) have been archived!").flash(request);
        return showMessages(response, model, "INBOX", 1, loggedInMember);
    }

    @PostMapping("/sendMessage")
    public String sendMessage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam String userIdsRecipients, @RequestParam String messageSubject,
            @RequestParam String messageContent, @RequestParam(required = false) String threadId,
            UserWrapper loggedInMember) throws MessageNotFoundException {

        //Check if I'm logged in
        if (loggedInMember == null) {
            return new AccessDeniedPage(null).toViewName(response);
        }

        final MessagingPermissions messagingPermissions = new MessagingPermissions(loggedInMember);

        //Check if I can send 1 message
        if (messagingPermissions.getCanSendMessage()) {
            List<Long> recipientIds = IdListUtil.getIdsFromString(userIdsRecipients);

            try {
                //If I specify a thread, check that I have permissions on it
                if (StringUtils.isNotEmpty(threadId)) {
                    //Check the permissions for the first message in the thread
                    String[] threadParts = threadId.split("-");
                    Long firstMessageId = Long.parseLong(threadParts[0]);
                        List<MessageBean> firstMessageBeanList = new ArrayList<>(Arrays.asList(
                                new MessageBean(StaticUserContext.getMessagingClient().getMessage(firstMessageId))));
                        if (!messagingPermissions.getCanViewThread(threadId, firstMessageBeanList)) {
                            //Permission denied
                            AlertMessage.danger("You don't have permissions on this thread" ).flash(request);
                            return new AccessDeniedPage(loggedInMember).toViewName(response);
                        }
                }

                final String baseUri = PlatformAttributeKey.COLAB_URL.get();
                StaticUserContext.getMessagingClient().checkLimitAndSendMessage(HtmlUtil.cleanAll(messageSubject),
                        HtmlUtil.cleanSome(messageContent, baseUri), loggedInMember.getId(),
                        HtmlUtil.cleanAll(threadId), recipientIds);
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
            return "redirect:" + LinkUtils.getSafeRedirectUri(refererHeader, "/messaging");
        }
        return showMessages(response, model, "INBOX", 1, loggedInMember);
    }

    @GetMapping("/sendMessage")
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
