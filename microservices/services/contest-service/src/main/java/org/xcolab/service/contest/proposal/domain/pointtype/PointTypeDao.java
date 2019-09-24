package org.xcolab.service.contest.proposal.domain.pointtype;

import org.xcolab.client.contest.pojo.IPointType;
import org.xcolab.client.contest.pojo.wrapper.PointTypeWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface PointTypeDao {

    List<PointTypeWrapper> findByGiven(Long parentPointTypeId);

    PointTypeWrapper create(PointTypeWrapper pointType);

    PointTypeWrapper get(Long id) throws NotFoundException;

    boolean update(PointTypeWrapper pointType);
}
