package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.Message;
import org.xcolab.service.members.domain.messaging.MessageDao;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.service.messaging.MessagingService;
import org.xcolab.service.utils.ControllerUtils;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MessagingController {

    @Autowired
    private MessagingService messagingService;

    @Autowired
    private MessageDao messageDao;

    @RequestMapping(value = "/messages", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Message> getMemberMessages(HttpServletResponse response,
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long recipientId,
            @RequestParam(required = false) Long senderId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "true") boolean includeCount) {

        final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                sort);

        if (includeCount) {
            response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                    Integer.toString(messageDao.countByGiven(senderId, recipientId, isArchived, isOpened)));
        }
        return messageDao.findByGiven(paginationHelper, senderId, recipientId, isArchived, isOpened);
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    public Message getMessage(@PathVariable("messageId") long messageId) throws NotFoundException {
        if (messageId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            return messageDao.getMessage(messageId);
        }
    }

    @RequestMapping(value = "/messages/{messageId}/recipients", method = RequestMethod.GET)
    public List<Member> getMessageRecipients(@PathVariable("messageId") long messageId) throws NotFoundException {
        if (messageId == 0) {
            throw new NotFoundException("No message id given");
        } else {
            return messageDao.getRecipients(messageId);
        }
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public void createMessage(@RequestBody Message message) {
        messagingService.createMessage(message.getMessageId(), message.getFromId(), message.getRepliesTo(),
                message.getSubject(), message.getContent());
    }

    @RequestMapping(value = "/messages/{messageId}/recipients", method = RequestMethod.POST)
    public void createMessageRecipient(@PathVariable("messageId") long messageId,
            @RequestParam long recipientStatusId, //TODO: liferay generated id
            @RequestParam long recipientId) {
        messagingService.createRecipient(recipientStatusId, messageId, recipientId);
    }

    //TODO: patch doesn't work
    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.PUT)
    public void patchMessage(@PathVariable("messageId") long messageId,
            @RequestParam Long memberId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened) {
        if (isOpened != null) {
            messageDao.setOpened(messageId, memberId, isOpened);
        }
        if (isArchived != null) {
            messageDao.setArchived(messageId, memberId, isArchived);
        }
    }
}
