package org.xcolab.service.tracking.domain.balloonlink;

import org.xcolab.model.tables.pojos.BalloonLink;
import org.xcolab.service.tracking.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface BalloonLinkDao {

    BalloonLink getBalloonLink(String uuid) throws NotFoundException;

    List<BalloonLink> findByGiven(PaginationHelper paginationHelper, String uuid);

    boolean update(BalloonLink balloonLink);

    BalloonLink create(BalloonLink balloonLink);
}
