package org.xcolab.client.contest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("xcolab-contest-service")
public interface ICacheClient {

    @GetMapping("/clearCache")
    String clearCache();

}
