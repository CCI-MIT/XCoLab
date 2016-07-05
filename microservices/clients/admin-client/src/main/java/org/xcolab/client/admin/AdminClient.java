package org.xcolab.client.admin;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class AdminClient {

    private static final RestService adminService = new RestService("admin-service");
    private static final RestResource<ConfigurationAttribute> configurationAttributeResource =
            new RestResource<>(adminService, "attributes", ConfigurationAttribute.TYPES);

    public static ConfigurationAttribute getConfigurationAttribute(
            ConfigurationAttributeKey attributeKey) {

        try {
            return configurationAttributeResource.get(attributeKey.name())
                    .cacheIdentifier("configurationAttribute_" + attributeKey.name())
                    .execute();
        } catch (EntityNotFoundException e) {
            throw new ConfigurationAttributeNotFoundException(attributeKey);
        }
    }
}
