package org.xcolab.client.tracking;

import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.client.tracking.pojo.TrackedVisit;
import org.xcolab.client.tracking.pojo.TrackedVisitor;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;

public final class TrackingClient {

    private static final RestService trackingService = new RestService("tracking-service");

    private static final RestResource<TrackedVisit> trackedVisitResource = new RestResource<>(
            trackingService, "trackedVisits", TrackedVisit.TYPES);
    private static final RestResource<TrackedVisitor> trackedVisitorResource = new RestResource<>(
            trackingService, "trackedVisitors", TrackedVisitor.TYPES);

    private static final RestResource<Location> locationResource = new RestResource<>(
            trackingService, "locations", Location.TYPES);


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
                .executeWithResult().getOne();
    }
}
