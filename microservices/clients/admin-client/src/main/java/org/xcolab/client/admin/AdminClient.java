package org.xcolab.client.admin;

import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.client.admin.pojo.Notification;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public class AdminClient {
    private static final RestService adminService = new RestService(CoLabService.ADMIN,
            ServiceRequestUtils.getNamespace());

    private static final RestResource<ConfigurationAttribute, String> configurationAttributeResource =
            new RestResource1<>(adminService, "attributes", ConfigurationAttribute.TYPES);

    private static final RestResource<Notification, String> notificationResource =
            new RestResource1<> (
                    adminService, "notifications", Notification.TYPES);

    public static List<Notification> getNotifications() {
        return notificationResource.list().execute();
    }

    public static Notification getFirstNotification() {
        return notificationResource.list()
                .executeWithResult()
                .getFirstIfExists();
    }

    public static void setNotifications(Notification notification) {
        notificationResource.create(notification)
                .execute();
    }

    public static void deleteNotifications(String notificationId) {
        notificationResource.delete(notificationId)
                .execute();
    }

    public static ConfigurationAttribute getConfigurationAttribute(String name, String locale) {

        try {
            return configurationAttributeResource.get(name)
                    .optionalQueryParam("locale", locale)
                    .withCache(CacheName.CONFIGURATION)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ConfigurationAttributeNotFoundException(name);
        }
    }

    public static ConfigurationAttribute createConfigurationAttribute(
            ConfigurationAttribute configurationAttribute) {
        return configurationAttributeResource.create(configurationAttribute).execute();
    }

    public static boolean updateConfigurationAttribute(
            ConfigurationAttribute configurationAttribute) {
        final Boolean result = configurationAttributeResource
                .update(configurationAttribute, configurationAttribute.getName())
                .cacheName(CacheName.CONFIGURATION).execute();
        //TODO: more fine grained cache control
        ServiceRequestUtils.clearCache(CacheName.CONFIGURATION);
        return result;
    }
}
