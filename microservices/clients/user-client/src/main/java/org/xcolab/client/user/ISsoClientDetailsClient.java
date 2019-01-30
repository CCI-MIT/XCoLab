package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.user.pojo.wrapper.SsoClientDetailsWrapper;

@FeignClient("xcolab-user-service")
public interface ISsoClientDetailsClient {

    @GetMapping("/ssoClientDetails/{clientId}")
    SsoClientDetailsWrapper getSsoClientDetails(@PathVariable String clientId);

}
