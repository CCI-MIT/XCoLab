package org.xcolab.client.admin;

import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

public class AdminClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:"+RequestUtils.getServicesPort()+"/admin-service";

    public static ConfigurationAttribute getConfigurationAttribute(ConfigurationAttributeKey attributeKey) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/attributes/" + attributeKey.name());

        try {
            return RequestUtils.get(uriBuilder, ConfigurationAttribute.class, "_configurationAttribute_" + attributeKey.name());
        } catch (EntityNotFoundException e) {
            throw new ConfigurationAttributeNotFoundException(attributeKey);
        }
    }
}
