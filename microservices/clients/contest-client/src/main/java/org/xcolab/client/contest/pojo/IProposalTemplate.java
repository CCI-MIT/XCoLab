package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalTemplate;

@JsonDeserialize(as = ProposalTemplate.class)
public interface IProposalTemplate {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    Long getBaseTemplateId();

    void setBaseTemplateId(Long baseTemplateId);

    Long getImpactSeriesTemplateId();

    void setImpactSeriesTemplateId(Long impactSeriesTemplateId);

    Long getFocusAreaListTemplateId();

    void setFocusAreaListTemplateId(Long focusAreaListTemplateId);
}
