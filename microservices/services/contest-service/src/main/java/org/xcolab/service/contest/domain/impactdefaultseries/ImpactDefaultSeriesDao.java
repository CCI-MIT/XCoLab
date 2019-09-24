package org.xcolab.service.contest.domain.impactdefaultseries;

import org.xcolab.client.contest.pojo.IImpactDefaultSeries;

import java.util.List;

public interface ImpactDefaultSeriesDao {

    List<IImpactDefaultSeries> findByGiven(Long focusAreaId, String name);
}
