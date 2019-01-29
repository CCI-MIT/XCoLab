package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ImpactDefaultSeriesData;

@JsonDeserialize(as = ImpactDefaultSeriesData.class)
public interface IImpactDefaultSeriesData {

    Long getSeriesId();

    void setSeriesId(Long seriesId);

    Integer getYear();

    void setYear(Integer year);

    Double getValue();

    void setValue(Double value);
}
