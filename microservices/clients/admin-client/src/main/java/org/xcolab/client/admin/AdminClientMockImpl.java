package org.xcolab.client.admin;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.pojo.IConfigurationAttribute;
import org.xcolab.client.admin.pojo.INotification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Profile("test")
public class AdminClientMockImpl implements IAdminClient {

    @Override
    public List<INotification> getNotifications() {
        return Collections.emptyList();
    }

    @Override
    public void createNotification(INotification notification) {

    }

    @Override
    public boolean deleteNotifications(Long notificationId) {
        return false;
    }

    @Override
    public Optional<IConfigurationAttribute> getConfigurationAttribute(String name, String locale) {
        return null;
    }

    @Override
    public IConfigurationAttribute createConfigurationAttribute(
            IConfigurationAttribute configurationAttribute) {
        return null;
    }

    @Override
    public boolean updateConfigurationAttribute(IConfigurationAttribute configurationAttribute) {
        return false;
    }
}
