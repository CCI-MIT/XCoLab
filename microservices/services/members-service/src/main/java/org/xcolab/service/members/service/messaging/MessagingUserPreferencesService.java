package org.xcolab.service.members.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.MessagingUserPreferences;
import org.xcolab.service.members.domain.messaginguserpreferences.DefaultMessagingUserPreferences;
import org.xcolab.service.members.domain.messaginguserpreferences.MessagingUserPreferencesDao;

@Service
public class MessagingUserPreferencesService {

    private final MessagingUserPreferencesDao messagingUserPreferencesDao;

    @Autowired
    public MessagingUserPreferencesService(MessagingUserPreferencesDao messagingUserPreferencesDao) {
        this.messagingUserPreferencesDao = messagingUserPreferencesDao;
    }

    public MessagingUserPreferences getByuserId(long userId) {
        return messagingUserPreferencesDao.getByuserId(userId)
                .orElse(new DefaultMessagingUserPreferences(userId));
    }
}
