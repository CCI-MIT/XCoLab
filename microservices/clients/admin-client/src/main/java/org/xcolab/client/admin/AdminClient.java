package org.xcolab.client.admin;

import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;
import org.xcolab.client.admin.pojo.IConfigurationAttribute;
import org.xcolab.client.admin.pojo.INotification;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public class AdminClient {

    private static final RestResource<IConfigurationAttribute, String>
            configurationAttributeResource = null; //CONFIGURATION_ATTRIBUTE("attributes")

    private static final RestResource<INotification, String> notificationResource = null; //NOTIFICATIONS("notifications")

    public static List<INotification> getNotifications() {
        return notificationResource.list().execute();
    }

    public static INotification getFirstNotification() {
        return notificationResource.list()
                .executeWithResult()
                .getFirstIfExists();
    }

    public static void setNotifications(INotification notification) {
        notificationResource.create(notification)
                .execute();
    }

    public static void deleteNotifications(String notificationId) {
        notificationResource.delete(notificationId)
                .execute();
    }

    public static IConfigurationAttribute getConfigurationAttribute(String name, String locale) {
        try {
            return configurationAttributeResource.get(name)
                    .optionalQueryParam("locale", locale)
                    .withCache(CacheName.CONFIGURATION)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new ConfigurationAttributeNotFoundException(name);
        }
    }

    public static IConfigurationAttribute createConfigurationAttribute(
            IConfigurationAttribute configurationAttribute) {
        return configurationAttributeResource.create(configurationAttribute).execute();
    }

    public static boolean updateConfigurationAttribute(
            IConfigurationAttribute configurationAttribute) {
        final Boolean result = configurationAttributeResource
                .update(configurationAttribute, configurationAttribute.getName())
                .cacheName(CacheName.CONFIGURATION).execute();
        //TODO COLAB-2589: more fine grained cache control
        ServiceRequestUtils.clearCache(CacheName.CONFIGURATION);
        return result;
    }
}
