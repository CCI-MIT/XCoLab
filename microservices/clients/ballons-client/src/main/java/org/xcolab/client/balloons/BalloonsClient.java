package org.xcolab.client.balloons;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonLink;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class BalloonsClient {

    private static final RestService balloonService = new RestService("balloons-service");
    private static final RestResource<BalloonLink> balloonLinkResource = new RestResource<>(
            balloonService, "balloonLinks", BalloonLink.class,
            new ParameterizedTypeReference<List<BalloonLink>>() {
    });
    private static final RestResource<BalloonUserTracking> balloonUserTrackingResource =
            new RestResource<>(balloonService, "balloonUserTracking", BalloonUserTracking.class,
                    new ParameterizedTypeReference<List<BalloonUserTracking>>() {
                    });
    private static final RestResource<BalloonText> balloonTextResource = new RestResource<>(
            balloonService, "balloonTexts", BalloonText.class,
            new ParameterizedTypeReference<List<BalloonText>>() {
    });

    public static BalloonLink getBalloonLink(String uuid) throws BalloonUserTrackingNotFound {
        try {
            return balloonLinkResource.get(uuid).execute();
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonLink " + uuid + " does not exist");
        }
    }

    public static BalloonLink createBalloonLink(BalloonLink balloonLink) {
        return balloonLinkResource.create(balloonLink).execute();
    }
    public static boolean updateBalloonLink(BalloonLink balloonLink) {
        return balloonLinkResource.update(balloonLink, balloonLink.getUuid_()).execute();
    }

    public static BalloonLink getBalloonLinkByMemberUuid(String memberUuid)
            throws BalloonUserTrackingNotFound {
        //TODO: port to new methods
        final UriBuilder uriBuilder = balloonLinkResource.getResourceUrl()
                .queryParam("memberUuid", memberUuid);

        try {
            return RequestUtils.get(uriBuilder, BalloonLink.class);
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonLink with memberUuid " + memberUuid + " does not exist");
        }
    }

    public static BalloonUserTracking getBalloonUserTracking(String uuid)
            throws BalloonUserTrackingNotFound {
        try {
            return balloonUserTrackingResource.get(uuid).execute();
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonUserTracking " + uuid + " does not exist");
        }
    }

    public static List<BalloonUserTracking> getBalloonUserTrackingByEmail(String email)  {
        return balloonUserTrackingResource.list()
                .queryParam("email", email)
                .execute();
    }

    public static List<BalloonUserTracking> getAllBalloonUserTracking() {
        return balloonUserTrackingResource.list().execute();
    }

    public static BalloonUserTracking createBalloonUserTracking(
            BalloonUserTracking balloonUserTracking) {
        return balloonUserTrackingResource.create(balloonUserTracking).execute();
    }
    public static boolean updateBalloonUserTracking(BalloonUserTracking balloonUserTracking) {
        return balloonUserTrackingResource.update(balloonUserTracking,
                balloonUserTracking.getUuid_()).execute();
    }

    public static BalloonText getBalloonText(Long id) throws BalloonUserTrackingNotFound {
        try {
            return balloonTextResource.get(id).execute();
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonText " + id + " does not exist");
        }
    }

    public static BalloonText createBalloonText(BalloonText balloonText) {
        return balloonTextResource.create(balloonText).execute();
    }

    public static List<BalloonText> getAllEnabledBalloonTexts() {
        return balloonTextResource.list().execute();
    }

    public static boolean updateBalloonText(BalloonText balloonText) {
        return balloonTextResource.update(balloonText, balloonText.getId_()).execute();
    }

    public static boolean deleteBalloonText(Long id) {
        return balloonTextResource.delete(id).execute();
    }
}
