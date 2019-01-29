package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ImpactTemplateMaxFocusArea;

@JsonDeserialize(as = ImpactTemplateMaxFocusArea.class)
public interface IImpactTemplateMaxFocusArea {

    Long getFocusAreaListId();

    void setFocusAreaListId(Long focusAreaListId);

    Long getFocusAreaId();

    void setFocusAreaId(Long focusAreaId);
}
