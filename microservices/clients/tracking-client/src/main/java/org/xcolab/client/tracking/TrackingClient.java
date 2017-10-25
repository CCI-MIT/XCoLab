package org.xcolab.client.tracking;

import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.client.tracking.pojo.TrackedVisit;
import org.xcolab.client.tracking.pojo.TrackedVisitor;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;

public final class TrackingClient {

    private static final RestResource<TrackedVisit, Long> trackedVisitResource =
            new RestResource1<>(TrackingResource.TRACKED_VISITS, TrackedVisit.TYPES);
    private static final RestResource<TrackedVisitor, Long> trackedVisitorResource =
            new RestResource1<>(TrackingResource.TRACKED_VISITORS, TrackedVisitor.TYPES);
    private static final RestResource<Location, Long> locationResource =
            new RestResource1<>(TrackingResource.LOCATIONS, Location.TYPES);


    public static TrackedVisit addTrackedVisit(String uuid, String url, String ip,
            String browser, String referer, String headers) {
        TrackedVisit trackedVisit = new TrackedVisit();
        trackedVisit.setUuid_(uuid);
        trackedVisit.setUrl(url);
        trackedVisit.setIp(ip);
        trackedVisit.setBrowser(browser);
        trackedVisit.setReferer(referer);
        trackedVisit.setHeaders(headers);

        return trackedVisitResource.create(trackedVisit).execute();
    }

    public static TrackedVisitor getTrackedVisitorOrCreate(long memberId) {
        return trackedVisitorResource.query(TrackedVisitor.class)
                .queryParam("memberId", memberId)
                .post();
    }

    public static String generateUUID() {
        return trackedVisitorResource.query(TrackedVisitor.class)
                .post().getUuid_();
    }

    public static Location getLocationForIp(String ipAddress) {
        return locationResource.list()
                .queryParam("ipAddress", ipAddress)
                .withCache(CacheKeys.withClass(Location.class)
                .withParameter("ipAddress", ipAddress).asList(), CacheName.MISC_RUNTIME)
                .executeWithResult().getOneIfExists();
    }
}
