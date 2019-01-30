package org.xcolab.service.members.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.user.pojo.wrapper.MessagingUserPreferenceWrapper;
import org.xcolab.service.members.domain.messaginguserpreferences.DefaultMessagingUserPreference;
import org.xcolab.service.members.domain.messaginguserpreferences.MessagingUserPreferenceDao;

@Service
public class MessagingUserPreferenceService {

    private final MessagingUserPreferenceDao messagingUserPreferencesDao;

    @Autowired
    public MessagingUserPreferenceService(MessagingUserPreferenceDao messagingUserPreferencesDao) {
        this.messagingUserPreferencesDao = messagingUserPreferencesDao;
    }

    public MessagingUserPreferenceWrapper getByuserId(long userId) {
        return messagingUserPreferencesDao.getByUserId(userId)
                .orElse(new DefaultMessagingUserPreference(userId));
    }
}
