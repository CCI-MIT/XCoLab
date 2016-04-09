package org.xcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.exceptions.NotFoundException;
import org.xcolab.model.tables.pojos.Message;
import org.xcolab.model.tables.pojos.User_;
import org.xcolab.service.messaging.MessagingService;

import java.util.Collections;
import java.util.List;

@RestController
public class MessagingController {

    @Autowired
    private MessagingService messagingService;

    @RequestMapping(value = "/members/{memberId}/messages/count", method = RequestMethod.GET)
    public Integer getMemberMessageCount(@PathVariable("memberId") Long memberId,
            @RequestParam(required = false) boolean isArchived,
            @RequestParam(required = false) boolean isOpened) {
        if (memberId == null || memberId == 0) {
            return 0;
        } else {
            if (isArchived) {
                return messagingService.countArchivedMessagesForUser(memberId);
            }
            return messagingService.countMessagesForUser(memberId);
        }
    }

    @RequestMapping(value = "/members/{memberId}/messages/countUnread", method = RequestMethod.GET)
    public Integer getMemberMessageCountUnread(@PathVariable("memberId") Long memberId) {
        if (memberId == null || memberId == 0) {
            return 0;
        } else {
            return messagingService.countUnreadMessagesForUser(memberId);
        }
    }

    @RequestMapping(value = "/members/{memberId}/messages/countSent", method = RequestMethod.GET)
    public Integer getMemberMessageCountSent(@PathVariable("memberId") Long memberId) {
        if (memberId == null || memberId == 0) {
            return 0;
        } else {
            return messagingService.countSentMessagesForUser(memberId);
        }
    }

    @RequestMapping(value = "/members/{memberId}/messages", method = RequestMethod.GET)
    public List<Message> getMemberMessages(@PathVariable("memberId") Long memberId,
            @RequestParam int firstRecord,
            @RequestParam int lastRecord,
            @RequestParam(required = false) boolean isArchived) {
        if (memberId == null || memberId == 0) {
            return Collections.emptyList();
        } else {
            if (isArchived) {
                return messagingService.findArchivedMessagesForUser(firstRecord, lastRecord, memberId);
            }
            return messagingService.findMessagesForUser(firstRecord, lastRecord, memberId);
        }
    }

    @RequestMapping(value = "/members/{memberId}/messagesSent", method = RequestMethod.GET)
    public List<Message> getMemberMessagesSent(@PathVariable("memberId") Long memberId,
            @RequestParam int firstRecord,
            @RequestParam int lastRecord) {
        if (memberId == null || memberId == 0) {
            return Collections.emptyList();
        } else {
            return messagingService.findSentMessagesForUser(firstRecord, lastRecord, memberId);
        }
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    public Message getMessage(@PathVariable("messageId") Long messageId) throws NotFoundException {
        if (messageId == null || messageId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            return messagingService.getMessage(messageId);
        }
    }

    @RequestMapping(value = "/messages/{messageId}/recipients", method = RequestMethod.GET)
    public List<User_> getMessageRecipients(@PathVariable("messageId") Long messageId) throws NotFoundException {
        if (messageId == null || messageId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            return messagingService.getRecipients(messageId);
        }
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public void createMessage(@RequestBody Message message) {
        messagingService.createMessage(message.getMessageId(), message.getFromId(), message.getRepliesTo(),
                message.getSubject(), message.getContent());
    }

    @RequestMapping(value = "/messages/{messageId}/recipients", method = RequestMethod.POST)
    public void createMessageRecipient(@PathVariable("messageId") Long messageId,
            @RequestParam long recipientStatusId, //TODO: liferay generated id
            @RequestParam long recipientId) {
        messagingService.createRecipient(recipientStatusId, messageId, recipientId);
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.PATCH)
    public void patchMessage(@PathVariable("messageId") Long messageId,
            @RequestParam Long memberId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened) {
        if (isOpened != null) {
            messagingService.setOpened(messageId, memberId, isOpened);
        }
        if (isArchived != null) {
            messagingService.setArchived(messageId, memberId, isArchived);
        }
    }
}
