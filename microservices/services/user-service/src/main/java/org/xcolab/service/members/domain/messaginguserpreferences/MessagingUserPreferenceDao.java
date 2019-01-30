package org.xcolab.service.members.domain.messaginguserpreferences;

import org.xcolab.client.user.pojo.wrapper.MessagingUserPreferenceWrapper;

import java.util.Optional;

public interface MessagingUserPreferenceDao {

    Optional<MessagingUserPreferenceWrapper> get(long id);

    Optional<MessagingUserPreferenceWrapper> getByUserId(long userId);

    Optional<MessagingUserPreferenceWrapper> create(
            MessagingUserPreferenceWrapper messagingUserPreferences);

    boolean update(MessagingUserPreferenceWrapper messagingUserPreferences);

    boolean delete(long messagingPreferencesId);
}
