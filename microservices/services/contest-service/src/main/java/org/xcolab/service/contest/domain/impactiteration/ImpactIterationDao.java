package org.xcolab.service.contest.domain.impactiteration;

import org.xcolab.client.contest.pojo.IImpactIteration;

import java.util.List;

public interface ImpactIterationDao {
    List<IImpactIteration> findByGiven(Long iterationId);
}
