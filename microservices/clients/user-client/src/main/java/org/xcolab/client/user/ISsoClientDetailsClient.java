package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.user.pojo.ISsoClientDetails;
import org.xcolab.client.user.pojo.SsoClientDetails;

@FeignClient("xcolab-user-service")
public interface ISsoClientDetailsClient {

    @GetMapping("/ssoClientDetails/{clientId}")
    SsoClientDetails getSsoClientDetails(@PathVariable String clientId);

}
