package org.xcolab.client.tracking;

import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.client.tracking.pojo.TrackedVisit;
import org.xcolab.commons.http.caching.CacheKeys;
import org.xcolab.commons.http.caching.CacheName;
import org.xcolab.commons.http.client.RestResource;
import org.xcolab.commons.http.client.RestResource1;

public final class TrackingClient {

    private static final RestResource<TrackedVisit, Long> trackedVisitResource =
            new RestResource1<>(TrackingResource.TRACKED_VISITS, TrackedVisit.TYPES);
    private static final RestResource<Location, Long> locationResource =
            new RestResource1<>(TrackingResource.LOCATIONS, Location.TYPES);


    public static TrackedVisit addTrackedVisit(String uuid, String url, String ip,
            String browser, String referer, String headers, Long userId) {
        TrackedVisit trackedVisit = new TrackedVisit();
        trackedVisit.setUuid_(uuid);
        trackedVisit.setUrl(url);
        trackedVisit.setIp(ip);
        trackedVisit.setBrowser(browser);
        trackedVisit.setReferer(referer);
        trackedVisit.setHeaders(headers);

        return trackedVisitResource.create(trackedVisit)
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public static Location getLocationForIp(String ipAddress) {
        return locationResource.list()
                .queryParam("ipAddress", ipAddress)
                .withCache(CacheKeys.withClass(Location.class)
                .withParameter("ipAddress", ipAddress).asList(), CacheName.MISC_RUNTIME)
                .executeWithResult().getOneIfExists();
    }
}
