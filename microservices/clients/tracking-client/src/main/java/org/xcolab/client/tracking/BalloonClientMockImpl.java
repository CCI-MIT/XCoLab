package org.xcolab.client.tracking;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.tracking.pojo.IBalloonLink;
import org.xcolab.client.tracking.pojo.IBalloonText;
import org.xcolab.client.tracking.pojo.IBalloonUserTracking;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class BalloonClientMockImpl implements IBalloonClient {

    @Override
    public IBalloonLink createBalloonLink(IBalloonLink balloonLink) {
        return null;
    }

    @Override
    public boolean updateBalloonLink(IBalloonLink balloonLink, String uuid) {
        return false;
    }

    @Override
    public IBalloonLink getBalloonLink(String uuid) {
        return null;
    }

    @Override
    public List<IBalloonLink> listBalloonLinks(String memberUuid) {
        return null;
    }

    @Override
    public IBalloonUserTracking createBalloonUserTracking(
            IBalloonUserTracking balloonUserTracking) {
        return null;
    }

    @Override
    public IBalloonUserTracking getBalloonUserTracking(String uuid) {
        return null;
    }

    @Override
    public List<IBalloonUserTracking> listBalloonUserTrackings(String email, String context) {
        return Collections.emptyList();
    }

    @Override
    public boolean updateBalloonUserTracking(IBalloonUserTracking balloonUserTracking,
            String uuid) {
        return false;
    }

    @Override
    public boolean deleteBalloonUserTracking(String uuid) {
        return false;
    }

    @Override
    public IBalloonText getBalloonText(Long id) {
        return null;
    }

    @Override
    public boolean updateBalloonText(IBalloonText balloonText, Long id) {
        return false;
    }

    @Override
    public List<IBalloonText> getAllEnabledBalloonLinks() {
        return null;
    }

    @Override
    public IBalloonText createBalloonText(IBalloonText balloonText) {
        return null;
    }

    @Override
    public boolean deleteBalloonText(Long id) {
        return false;
    }
}
