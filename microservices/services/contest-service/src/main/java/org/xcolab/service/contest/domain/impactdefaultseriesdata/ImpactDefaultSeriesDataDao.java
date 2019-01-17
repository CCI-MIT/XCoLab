package org.xcolab.service.contest.domain.impactdefaultseriesdata;

import org.xcolab.client.contest.pojo.IImpactDefaultSeriesData;

import java.util.List;

public interface ImpactDefaultSeriesDataDao {
    List<IImpactDefaultSeriesData> findByGiven(Long seriesId, Integer year);
}
