package org.xcolab.service.members.domain.messaginguserpreferences;

import org.xcolab.client.user.pojo.IMessagingUserPreference;

import java.util.Optional;

public interface MessagingUserPreferenceDao {

    Optional<IMessagingUserPreference> get(long id);

    Optional<IMessagingUserPreference> getByUserId(long userId);

    Optional<IMessagingUserPreference> create(IMessagingUserPreference messagingUserPreferences);

    boolean update(IMessagingUserPreference messagingUserPreferences);

    boolean delete(long messagingPreferencesId);
}
