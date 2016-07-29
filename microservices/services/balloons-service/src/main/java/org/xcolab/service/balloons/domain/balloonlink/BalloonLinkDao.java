package org.xcolab.service.balloons.domain.balloonlink;

import org.xcolab.model.tables.pojos.BalloonLink;
import org.xcolab.service.balloons.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface BalloonLinkDao {
    BalloonLink getBalloonLink(String uuid) throws NotFoundException;

    List<BalloonLink> findByGiven(PaginationHelper paginationHelper, String uuid);

    boolean update(BalloonLink balloonLink);

    BalloonLink create(BalloonLink balloonLink);
}
