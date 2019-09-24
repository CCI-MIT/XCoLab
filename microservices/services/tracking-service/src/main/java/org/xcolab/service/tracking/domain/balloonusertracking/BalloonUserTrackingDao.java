package org.xcolab.service.tracking.domain.balloonusertracking;

import org.xcolab.client.tracking.pojo.IBalloonUserTracking;
import org.xcolab.service.tracking.exceptions.NotFoundException;

import java.util.List;

public interface BalloonUserTrackingDao {

    IBalloonUserTracking getBalloonUserTrackingByUuid(String uuid) throws NotFoundException;

    List<IBalloonUserTracking> list(String email, String context);

    boolean update(IBalloonUserTracking balloonUserTracking);

    IBalloonUserTracking create(IBalloonUserTracking balloonUserTracking);

    boolean delete(String uuid);

}
