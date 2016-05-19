package org.xcolab.client.balloons;

import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonLink;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

public final class BalloonsClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/balloons-service";


    public static BalloonLink getBalloonLink(String uuid) throws BalloonUserTrackingNotFound {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonLinks/" + uuid + "");

        try {
            return RequestUtils.get(uriBuilder, BalloonLink.class);
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonLink " + uuid + " does not exist");
        }
    }

    public static BalloonLink createBalloonLink(
            BalloonLink balloonLink) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonLinks/");
        return RequestUtils.post(uriBuilder, balloonLink, BalloonLink.class);
    }
    public static void updateBalloonLink(BalloonLink balloonLink) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonUserTracking/" + balloonLink.getUuid_());

        RequestUtils.put(uriBuilder, balloonLink);
    }

    public static BalloonLink getBalloonLinkByMemberUuid(String memberUuid) throws BalloonUserTrackingNotFound {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonLinks/");
        uriBuilder.queryParam("memberUuid",memberUuid);

        try {
            return RequestUtils.get(uriBuilder, BalloonLink.class);
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonLink with memberUuid " + memberUuid + " does not exist");
        }
    }


    public static BalloonUserTracking getBalloonUserTracking(String uuid) throws BalloonUserTrackingNotFound {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonUserTracking/" + uuid + "");

        try {
            return RequestUtils.get(uriBuilder, BalloonUserTracking.class);
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonUserTracking " + uuid + " does not exist");
        }
    }

    public static BalloonUserTracking getBalloonUserTrackingByEmail(String email) throws BalloonUserTrackingNotFound {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonUserTracking/");
        uriBuilder.queryParam("email",email);

        try {
            return RequestUtils.get(uriBuilder, BalloonUserTracking.class);
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonUserTracking " + email + " does not exist");
        }
    }

    public static BalloonUserTracking createBalloonUserTracking(
            BalloonUserTracking balloonUserTracking) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonUserTracking/");
        return RequestUtils.post(uriBuilder, balloonUserTracking, BalloonUserTracking.class);
    }
    public static void updateBalloonUserTracking(BalloonUserTracking balloonUserTracking) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonUserTracking/" + balloonUserTracking.getUuid_());

        RequestUtils.put(uriBuilder, balloonUserTracking);
    }

    public static BalloonText getBalloonText(Long id) throws BalloonUserTrackingNotFound {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonTexts/" + id + "");

        try {
            return RequestUtils.get(uriBuilder, BalloonText.class);
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFound(
                    "BalloonText " + id + " does not exist");
        }
    }
    public static BalloonText createBalloonText(
            BalloonText balloonText) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonTexts/");
        return RequestUtils.post(uriBuilder, balloonText, BalloonText.class);
    }
    public static void updateBalloonText(BalloonText balloonText) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonTexts/" + balloonText.getId_());
        RequestUtils.put(uriBuilder, balloonText);
    }

    public static void deleteBalloonText(Long id) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/balloonTexts/" + id);
        RequestUtils.delete(uriBuilder);
    }
}
