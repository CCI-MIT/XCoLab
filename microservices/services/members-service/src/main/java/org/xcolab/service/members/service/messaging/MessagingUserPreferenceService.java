package org.xcolab.service.members.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.MessagingUserPreference;
import org.xcolab.service.members.domain.messaginguserpreferences.DefaultMessagingUserPreference;
import org.xcolab.service.members.domain.messaginguserpreferences.MessagingUserPreferenceDao;

@Service
public class MessagingUserPreferenceService {

    private final MessagingUserPreferenceDao messagingUserPreferencesDao;

    @Autowired
    public MessagingUserPreferenceService(MessagingUserPreferenceDao messagingUserPreferencesDao) {
        this.messagingUserPreferencesDao = messagingUserPreferencesDao;
    }

    public MessagingUserPreference getByuserId(long userId) {
        return messagingUserPreferencesDao.getByUserId(userId)
                .orElse(new DefaultMessagingUserPreference(userId));
    }
}
