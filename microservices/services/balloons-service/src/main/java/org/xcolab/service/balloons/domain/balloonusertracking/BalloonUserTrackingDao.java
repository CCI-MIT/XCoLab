package org.xcolab.service.balloons.domain.balloonusertracking;

import org.xcolab.model.tables.pojos.BalloonUserTracking;
import org.xcolab.service.balloons.exceptions.NotFoundException;

public interface BalloonUserTrackingDao {
    BalloonUserTracking getBallonUserTrackingByUuid(String uuid) throws NotFoundException;

    BalloonUserTracking getBallonUserTrackingByEmail(String email) throws NotFoundException;

    boolean update(BalloonUserTracking balloonUserTracking);

    BalloonUserTracking create(BalloonUserTracking balloonUserTracking);
}
