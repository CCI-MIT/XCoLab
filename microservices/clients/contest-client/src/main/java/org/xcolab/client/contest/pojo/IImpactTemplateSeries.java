package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ImpactTemplateSeries;

@JsonDeserialize(as = ImpactTemplateSeries.class)
public interface IImpactTemplateSeries {

    Long getSeriesId();

    void setSeriesId(Long seriesId);

    Long getIterationId();

    void setIterationId(Long iterationId);

    String getName();

    void setName(String name);
}
