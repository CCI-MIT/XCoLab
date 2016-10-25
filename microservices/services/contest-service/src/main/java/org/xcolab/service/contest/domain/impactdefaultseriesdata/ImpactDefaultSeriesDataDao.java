package org.xcolab.service.contest.domain.impactdefaultseriesdata;

import org.xcolab.model.tables.pojos.ImpactDefaultSeriesData;

import java.util.List;

public interface ImpactDefaultSeriesDataDao {
    List<ImpactDefaultSeriesData> findByGiven(Long seriesId, Integer year);
}
