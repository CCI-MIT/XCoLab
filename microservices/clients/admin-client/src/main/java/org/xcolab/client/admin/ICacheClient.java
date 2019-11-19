package org.xcolab.client.admin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("xcolab-admin-service")
public interface ICacheClient {

    @GetMapping("/clearCache")
    String clearCache();

}
