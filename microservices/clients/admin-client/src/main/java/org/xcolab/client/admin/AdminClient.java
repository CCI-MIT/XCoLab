package org.xcolab.client.admin;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.types.TypeProvider;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public class AdminClient {



    private static final RestService adminService = new RestService(CoLabService.ADMIN,
            ServiceRequestUtils.getNamespace());
    private static final RestResource<ConfigurationAttribute, String> configurationAttributeResource =
            new RestResource1<>(adminService, "attributes", ConfigurationAttribute.TYPES);


    //create a new resource and name that resource globalMessages
    private static final RestResource<String, String> globalMessages =
            new RestResource1<>(
                    adminService, "globalMessages", new TypeProvider<>(String.class,
                    new ParameterizedTypeReference<List<String>>() {
                    })
            );

    public static ConfigurationAttribute getConfigurationAttribute(String name) {

        try {
            return configurationAttributeResource.get(name)
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
        return configurationAttributeResource.update(configurationAttribute,
                configurationAttribute.getName())
                .cacheName(CacheName.CONFIGURATION)
                .execute();
    }
}
