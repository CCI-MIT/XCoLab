package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.exceptions.MessageNotFoundException;
import org.xcolab.client.user.messaging.MessageLimitExceededException;
import org.xcolab.client.user.pojo.IMessage;
import org.xcolab.client.user.pojo.IMessagingUserPreference;
import org.xcolab.client.user.pojo.IUser;
import org.xcolab.client.user.pojo.SendMessageBean;
import org.xcolab.commons.spring.web.annotation.ListMapping;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@FeignClient("xcolab-messaging-service")
public interface IMessagingClient {
    @ListMapping("/messages")
    List<IMessage> getUserMessages(HttpServletResponse response,
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
            @RequestParam(required = false) String threadId) throws MessageNotFoundException;
    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    IMessage getMessage(@PathVariable long messageId) throws MessageNotFoundException;

    @RequestMapping(value = "/messages/{messageId}/recipients", method = RequestMethod.GET)
    List<IUser> getMessageRecipients(@PathVariable long messageId);

    @RequestMapping(value = "/messages/{messageId}/threads", method = RequestMethod.GET)
    List<String> getMessageThreads(@PathVariable long messageId);

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    IMessage createMessage(@RequestBody SendMessageBean sendMessageBean,
            @RequestParam(required = false, defaultValue = "true") boolean checkLimit,
            @RequestParam(required = false) String threadId)
            throws MessageLimitExceededException;

    @RequestMapping(value = "/messages/{messageId}/recipients/{userId}", method = RequestMethod.PUT)
    boolean updateRecipientStatus(@PathVariable long messageId, @PathVariable long userId,
            @RequestParam(required = false) Boolean isArchived,
            @RequestParam(required = false) Boolean isOpened);
    @GetMapping("/members/{userId}/messagingPreferences")
    IMessagingUserPreference getMessagingPreferences(@PathVariable long userId);

    @PutMapping("/members/{userId}/messagingPreferences/{messagingPreferencesId}")
    boolean updateMessagingPreferences(@PathVariable long userId,
            @PathVariable long messagingPreferencesId,
            @RequestBody IMessagingUserPreference messagingUserPreferences);

    @PostMapping("/members/{userId}/messagingPreferences")
    IMessagingUserPreference createMessagingPreferences(@PathVariable long userId,
            @RequestBody IMessagingUserPreference messagingUserPreferences);

    @RequestMapping(value = "/members/{userId}/canSendMessage", method = RequestMethod.GET)
    boolean canUserSendMessage(@PathVariable long userId,
            @RequestParam(required = false, defaultValue = "1") int messagesToSend);

    @RequestMapping(value = "/members/{userId}/numberOfMessagesLeft", method = RequestMethod.GET)
    int getNumberOfMessagesLeft(@PathVariable long userId);
}
