package org.xcolab.service.tracking.domain.balloonusertracking;

import org.xcolab.model.tables.pojos.BalloonUserTracking;
import org.xcolab.service.tracking.exceptions.NotFoundException;

import java.util.List;

public interface BalloonUserTrackingDao {

    BalloonUserTracking getBalloonUserTrackingByUuid(String uuid) throws NotFoundException;

    List<BalloonUserTracking> list(String email, String context);

    boolean update(BalloonUserTracking balloonUserTracking);

    BalloonUserTracking create(BalloonUserTracking balloonUserTracking);

    boolean delete(String uuid);

}
