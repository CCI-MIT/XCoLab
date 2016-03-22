package org.xcolab.service.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.xcolab.pojo.User_;

import java.util.List;

public class MembersClient {

    private static final String EUREKA_APPLICATION_ID = "members-service";

    @Autowired
    static RestTemplate restTemplate = new RestTemplate();

    public static void getAllUsers() {

        //User user = restTemplate.getForObject("http://"+EUREKA_APPLICATION_ID+"/members", User.class);

        ResponseEntity<List<User_>> response = restTemplate.exchange("http://"+EUREKA_APPLICATION_ID+"/members",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User_>>(){});

        List<User_> userList = response.getBody();
    }
}
