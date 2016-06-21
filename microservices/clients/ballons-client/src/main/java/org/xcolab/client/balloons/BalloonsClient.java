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
    private static final RestResource balloonLinkResource = new RestResource(balloonService,
            "balloonLinks");
    private static final RestResource balloonUserTrackingResource =
            new RestResource(balloonService, "balloonUserTracking");
    private static final RestResource balloonTextResource = new RestResource(balloonService,
            "balloonTexts");

    public static BalloonLink getBalloonLink(String uuid) throws BalloonUserTrackingNotFound {
        final UriBuilder uriBuilder = balloonLinkResource.getResourceUrl(uuid);

        try {
            return RequestUtils.get(uriBuilder, BalloonLink.class);
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonLink " + uuid + " does not exist");
        }
    }

    public static BalloonLink createBalloonLink(
            BalloonLink balloonLink) {
        final UriBuilder uriBuilder = balloonLinkResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, balloonLink, BalloonLink.class);
    }
    public static void updateBalloonLink(BalloonLink balloonLink) {
        final UriBuilder uriBuilder = balloonLinkResource.getResourceUrl(balloonLink.getUuid_());
        RequestUtils.put(uriBuilder, balloonLink);
    }

    public static BalloonLink getBalloonLinkByMemberUuid(String memberUuid) throws BalloonUserTrackingNotFound {
        final UriBuilder uriBuilder = balloonLinkResource.getResourceUrl()
                .queryParam("memberUuid", memberUuid);

        try {
            return RequestUtils.get(uriBuilder, BalloonLink.class);
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonLink with memberUuid " + memberUuid + " does not exist");
        }
    }

    public static BalloonUserTracking getBalloonUserTracking(String uuid) throws BalloonUserTrackingNotFound {
        final UriBuilder uriBuilder = balloonUserTrackingResource.getResourceUrl(uuid);

        try {
            return RequestUtils.get(uriBuilder, BalloonUserTracking.class);
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonUserTracking " + uuid + " does not exist");
        }
    }

    public static List<BalloonUserTracking> getBalloonUserTrackingByEmail(String email)  {
        final UriBuilder uriBuilder = balloonUserTrackingResource.getResourceUrl()
                .queryParam("email", email);

            return RequestUtils.getList(uriBuilder,
                    new ParameterizedTypeReference<List<BalloonUserTracking>>() {
                    });
    }

    public static List<BalloonUserTracking> getAllBalloonUserTracking() {
        final UriBuilder uriBuilder = balloonUserTrackingResource.getResourceUrl();

        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<BalloonUserTracking>>() {
                });
    }

    public static BalloonUserTracking createBalloonUserTracking(
            BalloonUserTracking balloonUserTracking) {
        final UriBuilder uriBuilder = balloonUserTrackingResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, balloonUserTracking, BalloonUserTracking.class);
    }
    public static void updateBalloonUserTracking(BalloonUserTracking balloonUserTracking) {
        final UriBuilder uriBuilder = balloonUserTrackingResource.getResourceUrl(
                balloonUserTracking.getUuid_());
        RequestUtils.put(uriBuilder, balloonUserTracking);
    }

    public static BalloonText getBalloonText(Long id) throws BalloonUserTrackingNotFound {
        final UriBuilder uriBuilder = balloonTextResource.getResourceUrl(id);

        try {
            return RequestUtils.get(uriBuilder, BalloonText.class);
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonText " + id + " does not exist");
        }
    }


    public static BalloonText createBalloonText(
            BalloonText balloonText) {
        final UriBuilder uriBuilder = balloonTextResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, balloonText, BalloonText.class);
    }

    public static List<BalloonText> getAllEnabledBalloonTexts() {
        final UriBuilder uriBuilder = balloonTextResource.getResourceUrl();
        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<BalloonText>>() {
                });
    }

    public static void updateBalloonText(BalloonText balloonText) {
        final UriBuilder uriBuilder = balloonTextResource.getResourceUrl(balloonText.getId_());
        RequestUtils.put(uriBuilder, balloonText);
    }

    public static void deleteBalloonText(Long id) {
        final UriBuilder uriBuilder = balloonTextResource.getResourceUrl(id);
        RequestUtils.delete(uriBuilder);
    }
}
