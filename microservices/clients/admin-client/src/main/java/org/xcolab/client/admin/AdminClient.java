package org.xcolab.client.admin;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;

public class AdminClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/admin-service";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static ConfigurationAttribute getConfigurationAttribute(ConfigurationAttributeKey attributeKey) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/attributes/" + attributeKey.name());
        try {
            return restTemplate.getForObject(uriBuilder.build().toString(), ConfigurationAttribute.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ConfigurationAttributeNotFoundException(attributeKey);
            }
            throw e;
        }
    }
}
