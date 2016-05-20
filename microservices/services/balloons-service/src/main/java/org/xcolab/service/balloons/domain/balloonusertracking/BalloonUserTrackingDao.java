package org.xcolab.service.balloons.domain.balloonusertracking;

import org.xcolab.model.tables.pojos.BalloonUserTracking;
import org.xcolab.service.balloons.exceptions.NotFoundException;

import java.util.List;

public interface BalloonUserTrackingDao {
    BalloonUserTracking getBallonUserTrackingByUuid(String uuid) throws NotFoundException;

    List<BalloonUserTracking> getBallonUserTrackingByEmail(String email) throws NotFoundException;

    boolean update(BalloonUserTracking balloonUserTracking);

    BalloonUserTracking create(BalloonUserTracking balloonUserTracking);

    List<BalloonUserTracking> getAllBalloonUserTracking();
}
