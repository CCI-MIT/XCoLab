package org.xcolab.service.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.Member;
import org.xcolab.model.tables.pojos.Message;
import org.xcolab.model.tables.pojos.MessagingUserPreferences;
import org.xcolab.service.members.domain.messaging.MessageDao;
import org.xcolab.service.members.domain.messaginguserpreferences.MessagingUserPreferencesDao;
import org.xcolab.service.members.exceptions.MessageLimitExceededException;
import org.xcolab.service.members.exceptions.NotFoundException;
import org.xcolab.service.members.service.messaging.MessageLimitManager;
import org.xcolab.service.members.service.messaging.MessagingService;
import org.xcolab.service.members.service.messaging.MessagingUserPreferencesService;
import org.xcolab.service.members.wrappers.SendMessageBean;
import org.xcolab.service.utils.ControllerUtils;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.commons.exceptions.InternalException;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MessagingController {

    private final MessagingService messagingService;

    private final MessageLimitManager messageLimitManager;

    private final MessageDao messageDao;

    private final MessagingUserPreferencesDao messagingUserPreferencesDao;

    private final MessagingUserPreferencesService messagingUserPreferencesService;

    @Autowired
    public MessagingController(MessageDao messageDao,
            MessagingUserPreferencesDao messagingUserPreferencesDao,
            MessagingUserPreferencesService messagingUserPreferencesService,
            MessagingService messagingService, MessageLimitManager messageLimitManager) {
        this.messageDao = messageDao;
        this.messagingUserPreferencesDao = messagingUserPreferencesDao;
        this.messagingUserPreferencesService = messagingUserPreferencesService;
        this.messagingService = messagingService;
        this.messageLimitManager = messageLimitManager;
    }

    @RequestMapping(value = "/messages", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Message> getMemberMessages(HttpServletResponse response,
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long recipientId,
            @RequestParam(required = false) Long senderId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened,
            @RequestParam(required = false) Timestamp sinceDate,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "true") boolean includeCount,
            @RequestParam(required = false) Long messageId,
            @RequestParam(required = false) String threadId) throws NotFoundException {
        if (messageId!=null && threadId!=null){
            return messageDao.getFullConversation(messageId, threadId);
        } else {
            final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                    sort);

            if (includeCount) {
                response.setHeader(ControllerUtils.COUNT_HEADER_NAME,
                        Integer.toString(messageDao
                                .countByGiven(senderId, recipientId, isArchived, isOpened, sinceDate)));
            }
            return messageDao.findByGiven(paginationHelper, senderId, recipientId, isArchived, isOpened,
                    sinceDate);
        }
    }

    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    public Message getMessage(@PathVariable long messageId) throws NotFoundException {
        return messageDao.getMessage(messageId);
    }

    @RequestMapping(value = "/messages/{messageId}/recipients", method = RequestMethod.GET)
    public List<Member> getMessageRecipients(@PathVariable long messageId) {
        return messageDao.getRecipients(messageId);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public Message createMessage(@RequestBody SendMessageBean sendMessageBean,
            @RequestParam(required = false, defaultValue = "true") boolean checkLimit,
            @RequestParam(required = false, defaultValue="-1") String threadId)
            throws MessageLimitExceededException {
        return messagingService
                .sendMessage(sendMessageBean, sendMessageBean.getRecipientIds(), checkLimit, threadId);
    }

    @RequestMapping(value = "/messages/{messageId}/recipients/{memberId}", method = RequestMethod.PUT)
    public boolean updateRecipientStatus(@PathVariable long messageId, @PathVariable long memberId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened) {
        boolean success = true;
        if (isOpened != null) {
            success = messageDao.setOpened(messageId, memberId, isOpened);
        }
        if (isArchived != null) {
            success = success && messageDao.setArchived(messageId, memberId, isArchived);
        }
        return success;
    }

    @GetMapping("/members/{memberId}/messagingPreferences")
    public MessagingUserPreferences getMessagingPreferences(@PathVariable long memberId) {
        return messagingUserPreferencesService.getByMemberId(memberId);
    }

    @PutMapping("/members/{memberId}/messagingPreferences/{messagingPreferencesId}")
    public boolean updateMessagingPreferences(@PathVariable long memberId,
            @PathVariable long messagingPreferencesId,
            @RequestBody MessagingUserPreferences messagingUserPreferences) {
        return messagingUserPreferencesDao.update(messagingUserPreferences);
    }

    @PostMapping("/members/{memberId}/messagingPreferences")
    public MessagingUserPreferences createMessagingPreferences(@PathVariable long memberId,
            @RequestBody MessagingUserPreferences messagingUserPreferences) {
        return messagingUserPreferencesDao.create(messagingUserPreferences)
                .orElseThrow(() -> new InternalException(
                "Could not retrieve id of created messagingPreferences: " + messagingUserPreferences));
    }

    @RequestMapping(value = "/members/{memberId}/canSendMessage", method = RequestMethod.GET)
    public boolean canMemberSendMessage(@PathVariable long memberId,
            @RequestParam(required = false, defaultValue = "1") int messagesToSend) {
        return messageLimitManager.canSendMessages(messagesToSend, memberId);
    }

    @RequestMapping(value = "/members/{memberId}/numberOfMessagesLeft", method = RequestMethod.GET)
    public int getNumberOfMessagesLeft(@PathVariable long memberId) {
        return messageLimitManager.getNumberOfMessagesLeft(memberId);
    }
}
