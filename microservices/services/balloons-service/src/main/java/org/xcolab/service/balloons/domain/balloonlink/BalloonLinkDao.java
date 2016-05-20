package org.xcolab.service.balloons.domain.balloonlink;

import org.xcolab.model.tables.pojos.BalloonLink;
import org.xcolab.service.balloons.exceptions.NotFoundException;

public interface BalloonLinkDao {
    BalloonLink getBalloonLink(String uuid) throws NotFoundException;

    BalloonLink getBalloonLinkByUserUuid(String uuid) throws NotFoundException;

    boolean update(BalloonLink balloonLink);

    BalloonLink create(BalloonLink balloonLink);
}
