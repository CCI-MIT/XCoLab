package org.xcolab.service.contest.domain.impactdefaultseries;

import org.xcolab.model.tables.pojos.ImpactDefaultSeries;

import java.util.List;

public interface ImpactDefaultSeriesDao {

    List<ImpactDefaultSeries> findByGiven(Long focusAreaId, String name);
}
