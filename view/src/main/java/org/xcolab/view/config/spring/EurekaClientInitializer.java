package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.xcolab.util.http.ServiceRequestUtils;

@Component
public class EurekaClientInitializer implements CommandLineRunner {

    private final RestTemplate restTemplate;

    @Autowired
    public EurekaClientInitializer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... strings) {
        ServiceRequestUtils.initialize(restTemplate);
    }
}
