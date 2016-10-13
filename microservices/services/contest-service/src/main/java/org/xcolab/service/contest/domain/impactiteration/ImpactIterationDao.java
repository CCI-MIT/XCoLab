package org.xcolab.service.contest.domain.impactiteration;

import org.xcolab.model.tables.pojos.ImpactIteration;

import java.util.List;

public interface ImpactIterationDao {
    List<ImpactIteration> findByGiven(Long iterationId);
}
