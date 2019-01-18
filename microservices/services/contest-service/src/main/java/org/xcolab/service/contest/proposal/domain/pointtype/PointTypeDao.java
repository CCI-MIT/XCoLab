package org.xcolab.service.contest.proposal.domain.pointtype;

import org.xcolab.client.contest.pojo.IPointType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface PointTypeDao {

    List<IPointType> findByGiven(Long parentPointTypeId);

    IPointType create(IPointType pointType);

    IPointType get(Long id) throws NotFoundException;

    boolean update(IPointType pointType);
}
