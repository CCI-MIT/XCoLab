package org.xcolab.client.admin;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class AdminClient {

    private static final RestService adminService = new RestService("admin-service");
    private static final RestResource configurationAttributeResource =
            new RestResource(adminService, "attributes");

    public static ConfigurationAttribute getConfigurationAttribute(
            ConfigurationAttributeKey attributeKey) {
        final UriBuilder uriBuilder = configurationAttributeResource
                .getResourceUrl(attributeKey.name());
        try {
            return RequestUtils.get(uriBuilder, ConfigurationAttribute.class,
                    "configurationAttribute_" + attributeKey.name());
        } catch (EntityNotFoundException e) {
            throw new ConfigurationAttributeNotFoundException(attributeKey);
        }
    }
}
