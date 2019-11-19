package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("xcolab-user-service")
public interface ICacheClient {

    @GetMapping("/clearCache")
    String clearCache();

}
