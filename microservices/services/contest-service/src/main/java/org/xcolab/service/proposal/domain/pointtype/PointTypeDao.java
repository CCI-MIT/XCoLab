package org.xcolab.service.proposal.domain.pointtype;

import org.xcolab.model.tables.pojos.PointType;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface PointTypeDao {

    List<PointType> findByGiven(Long parentPointTypeId);

    PointType create(PointType pointType);

    PointType get(Long id) throws NotFoundException;

    boolean update(PointType pointType);
}
