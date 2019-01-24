package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ImpactIteration;

@JsonDeserialize(as = ImpactIteration.class)
public interface IImpactIteration {

    Long getIterationId();

    void setIterationId(Long iterationId);

    Integer getYear();

    void setYear(Integer year);
}
