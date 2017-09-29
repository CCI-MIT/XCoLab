package org.xcolab.service.proposal.domain.points;

import org.xcolab.model.tables.pojos.Points;

import java.util.List;

public interface PointsDao {

    List<Points> findByGiven(Long userId, Long proposalId);
}
