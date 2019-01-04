package org.xcolab.service.tracking.domain.balloonlink;

import org.xcolab.client.tracking.pojo.IBalloonLink;
import org.xcolab.service.tracking.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface BalloonLinkDao {

    IBalloonLink getBalloonLink(String uuid) throws NotFoundException;

    List<IBalloonLink> findByGiven(PaginationHelper paginationHelper, String uuid);

    boolean update(IBalloonLink balloonLink);

    IBalloonLink create(IBalloonLink balloonLink);
}
