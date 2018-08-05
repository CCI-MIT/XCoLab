package org.xcolab.service.members.domain.messaginguserpreferences;

import org.xcolab.model.tables.pojos.MessagingUserPreferences;

import java.util.Optional;

public interface MessagingUserPreferencesDao {

    Optional<MessagingUserPreferences> get(long id);
    Optional<MessagingUserPreferences> getByuserId(long userId);
    Optional<MessagingUserPreferences> create(MessagingUserPreferences messagingUserPreferences);
    boolean update(MessagingUserPreferences messagingUserPreferences);
    boolean delete(long messagingPreferencesId);
}
