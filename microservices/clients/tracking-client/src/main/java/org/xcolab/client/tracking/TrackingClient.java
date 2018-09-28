package org.xcolab.client.tracking;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.client.tracking.pojo.TrackedVisit;

@FeignClient("xcolab-tracking-service")
public interface TrackingClient {

    @PostMapping("/trackedVisits")
    TrackedVisit addTrackedVisit(TrackedVisit trackedVisit,
            @RequestParam(required = false, value = "userId") Long userId);

    default TrackedVisit addTrackedVisit(String uuid, String url, String ip,
            String browser, String referer, String headers, Long userId) {
        TrackedVisit trackedVisit = new TrackedVisit();
        trackedVisit.setVisitorUuid(uuid);
        trackedVisit.setUrl(url);
        trackedVisit.setIp(ip);
        trackedVisit.setBrowser(browser);
        trackedVisit.setReferer(referer);
        trackedVisit.setHeaders(headers);
        return addTrackedVisit(trackedVisit, userId);
    }

    @GetMapping("/locations")
    Location getLocationForIp(@RequestParam(value = "ipAddress") String ipAddress);

    //
    //    private static final RestResource<TrackedVisit, Long> trackedVisitResource =
    //            new RestResource1<>(TrackingResource.TRACKED_VISITS, TrackedVisit.TYPES);
    //    private static final RestResource<Location, Long> locationResource =
    //            new RestResource1<>(TrackingResource.LOCATIONS, Location.TYPES);
    //
    //
    //    public static TrackedVisit addTrackedVisit(String uuid, String url, String ip,
    //            String browser, String referer, String headers, Long userId) {
    //        TrackedVisit trackedVisit = new TrackedVisit();
    //        trackedVisit.setVisitorUuid(uuid);
    //        trackedVisit.setUrl(url);
    //        trackedVisit.setIp(ip);
    //        trackedVisit.setBrowser(browser);
    //        trackedVisit.setReferer(referer);
    //        trackedVisit.setHeaders(headers);
    //
    //        return trackedVisitResource.create(trackedVisit)
    //                .optionalQueryParam("userId", userId)
    //                .execute();
    //    }
    //
    //    public static Location getLocationForIp(String ipAddress) {
    //        return locationResource.list()
    //                .queryParam("ipAddress", ipAddress)
    //                .withCache(CacheKeys.withClass(Location.class)
    //                .withParameter("ipAddress", ipAddress).asList(), CacheName.MISC_RUNTIME)
    //                .executeWithResult().getOneIfExists();
    //    }
}
