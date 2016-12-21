package org.xcolab.portlets.userprofile.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.portlets.userprofile.beans.MessageBean;
import org.xcolab.portlets.userprofile.utils.JSONHelper;

import java.util.Collections;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class MessageJSONController extends JSONHelper {

    private final SmartValidator validator;

    @Autowired
    public MessageJSONController(SmartValidator validator) {
        this.validator = validator;
    }

    @InitBinder("messageBean")
    public void initMessageBeanBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ResourceMapping("submitSendMessageForm")
    public @ResponseBody void handleSubmitSendMessageFormAJAXRequest(
            ResourceRequest request, ResourceResponse response,
            @ModelAttribute MessageBean messageBean, BindingResult result,
            @RequestParam("userIdRecipient") Long userIdRecipient) {

        if (!result.hasErrors()) {
            try {
                final long senderMemberId = MemberAuthUtil.getMemberId(request);
                sendMessage(messageBean, senderMemberId, userIdRecipient);
                this.writeSuccessResultResponseJSON(true, response);
            } catch (MessageLimitExceededException e) {
                this.writeErrorResultResponseJSON("Daily message limit exceeded", response);
            }
        } else {
            this.writeSuccessResultResponseJSON(false, response);
        }
    }

    private void sendMessage(MessageBean messageBean, long senderMemberId, Long recipientMemberId)
            throws MessageLimitExceededException {
        MessagingClient.checkLimitAndSendMessage(messageBean.getMessageSubject(), messageBean.getMessageText(),
                senderMemberId, Collections.singletonList(recipientMemberId));
    }
}
