package org.xcolab.client.contest.pojo;

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
