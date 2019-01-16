package org.xcolab.service.members.domain.messaginguserpreferences;

import org.xcolab.model.tables.pojos.MessagingUserPreference;

import java.util.Optional;

public interface MessagingUserPreferenceDao {

    Optional<MessagingUserPreference> get(long id);

    Optional<MessagingUserPreference> getByUserId(long userId);

    Optional<MessagingUserPreference> create(MessagingUserPreference messagingUserPreferences);

    boolean update(MessagingUserPreference messagingUserPreferences);

    boolean delete(long messagingPreferencesId);
}
