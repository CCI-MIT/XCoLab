package org.xcolab.client.tracking;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.tracking.pojo.ILocation;
import org.xcolab.client.tracking.pojo.ITrackedVisit;
import org.xcolab.client.tracking.pojo.tables.pojos.TrackedVisit;

@FeignClient("xcolab-tracking-service")
public interface ITrackingClient {

    @PostMapping("/trackedVisits")
    ITrackedVisit addTrackedVisit(@RequestBody ITrackedVisit trackedVisit, @RequestParam(value = "userId", required = false) Long userId);

    default ITrackedVisit addTrackedVisit(String uuid, String url, String ip,
            String browser, String referer, String headers, Long userId) {
        ITrackedVisit trackedVisit = new TrackedVisit();
        trackedVisit.setVisitorUuid(uuid);
        trackedVisit.setUrl(url);
        trackedVisit.setIp(ip);
        trackedVisit.setBrowser(browser);
        trackedVisit.setReferer(referer);
        trackedVisit.setHeaders(headers);
        return addTrackedVisit(trackedVisit, userId);
    }

    @GetMapping("/locations")
    ILocation getLocationForIp(@RequestParam(value = "ipAddress") String ipAddress);
}
