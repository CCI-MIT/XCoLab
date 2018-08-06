package org.xcolab.client.balloons;

import org.xcolab.client.balloons.exceptions.BalloonLinkNotFoundException;
import org.xcolab.client.balloons.exceptions.BalloonTextNotFoundException;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFoundException;
import org.xcolab.client.balloons.pojo.BalloonLink;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class BalloonsClient {

    private static final RestResource<BalloonLink, String> balloonLinkResource = new RestResource1<>(
            BalloonResource.BALLOON_LINK, BalloonLink.TYPES);
    private static final RestResource<BalloonUserTracking, String> balloonUserTrackingResource =
            new RestResource1<>(BalloonResource.BALLOON_USER_TRACKING, BalloonUserTracking.TYPES);
    private static final RestResource<BalloonText, Long> balloonTextResource = new RestResource1<>(
            BalloonResource.BALLOON_TEXT, BalloonText.TYPES);

    public static BalloonLink getBalloonLink(String uuid) throws BalloonLinkNotFoundException {
        try {
            return balloonLinkResource.get(uuid).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new BalloonLinkNotFoundException(
                    "BalloonLink " + uuid + " does not exist");
        }
    }

    public static BalloonLink createBalloonLink(BalloonLink balloonLink) {
        return balloonLinkResource.create(balloonLink).execute();
    }
    public static boolean updateBalloonLink(BalloonLink balloonLink) {
        return balloonLinkResource.update(balloonLink, balloonLink.getUuid()).execute();
    }

    public static BalloonLink getLinkByBalloonUserTrackingUuid(String butUuid)
            throws BalloonLinkNotFoundException {
        final BalloonLink balloonLink = balloonLinkResource.list()
                .queryParam("memberUuid", butUuid)
                .executeWithResult().getFirstIfExists();
        if (balloonLink == null) {
            throw new BalloonLinkNotFoundException(
                    "BalloonLink with butUuid " + butUuid + " does not exist");
        }
        return balloonLink;
    }

    public static BalloonUserTracking getBalloonUserTracking(String uuid)
            throws BalloonUserTrackingNotFoundException {
        try {
            return balloonUserTrackingResource.get(uuid).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new BalloonUserTrackingNotFoundException(
                    "BalloonUserTracking " + uuid + " does not exist");
        }
    }

    public static List<BalloonUserTracking> getBalloonUserTrackingByEmail(String email) {
        return getBalloonUserTrackingByEmail(email, null);
    }

    public static List<BalloonUserTracking> getBalloonUserTrackingByEmail(String email,
            String context)  {
        return balloonUserTrackingResource.list()
                .queryParam("email", email)
                .optionalQueryParam("context", context)
                .execute();
    }

    public static List<BalloonUserTracking> getAllBalloonUserTracking() {
        return balloonUserTrackingResource.list().execute();
    }

    public static boolean deleteBalloonUserTracking(String uuid) {
        return balloonUserTrackingResource.delete(uuid).execute();
    }

    public static BalloonUserTracking createBalloonUserTracking(
            BalloonUserTracking balloonUserTracking) {
        return balloonUserTrackingResource.create(balloonUserTracking).execute();
    }
    public static boolean updateBalloonUserTracking(BalloonUserTracking balloonUserTracking) {
        return balloonUserTrackingResource.update(balloonUserTracking,
                balloonUserTracking.getUuid()).execute();
    }

    public static BalloonText getBalloonText(Long id) throws BalloonTextNotFoundException {
        try {
            return balloonTextResource.get(id).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new BalloonTextNotFoundException(id);
        }
    }

    public static BalloonText createBalloonText(BalloonText balloonText) {
        return balloonTextResource.create(balloonText).execute();
    }

    public static List<BalloonText> getAllEnabledBalloonTexts() {
        return balloonTextResource.list().execute();
    }

    public static boolean updateBalloonText(BalloonText balloonText) {
        return balloonTextResource.update(balloonText, balloonText.getId()).execute();
    }

    public static boolean deleteBalloonText(Long id) {
        return balloonTextResource.delete(id).execute();
    }
}
