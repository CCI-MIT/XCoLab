package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ImpactTemplateFocusAreaList;

@JsonDeserialize(as = ImpactTemplateFocusAreaList.class)
public interface IImpactTemplateFocusAreaList {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);
}
