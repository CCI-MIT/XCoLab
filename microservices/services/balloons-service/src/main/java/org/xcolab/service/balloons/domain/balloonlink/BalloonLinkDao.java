package org.xcolab.service.balloons.domain.balloonlink;

import org.xcolab.model.tables.pojos.BalloonLink;
import org.xcolab.service.balloons.exceptions.NotFoundException;

public interface BalloonLinkDao {
    BalloonLink getBallonLink(String uuid) throws NotFoundException;

    BalloonLink getBallonLinkByUserUuid(String uuid) throws NotFoundException;

    boolean update(BalloonLink balloonLink);

    public BalloonLink create(BalloonLink balloonLink);
}
