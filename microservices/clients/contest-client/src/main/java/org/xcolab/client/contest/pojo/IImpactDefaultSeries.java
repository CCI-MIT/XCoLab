package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ImpactDefaultSeries;

@JsonDeserialize(as = ImpactDefaultSeries.class)
public interface IImpactDefaultSeries {

    Long getSeriesId();

    void setSeriesId(Long seriesId);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Long getFocusAreaId();

    void setFocusAreaId(Long focusAreaId);

    Boolean getVisible();

    void setVisible(Boolean visible);

    Boolean getEditable();

    void setEditable(Boolean editable);
}
