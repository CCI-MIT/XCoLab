package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.FocusAreaOntologyTerm;

@JsonDeserialize(as = FocusAreaOntologyTerm.class)
public interface IFocusAreaOntologyTerm {

    Long getFocusAreaId();

    void setFocusAreaId(Long focusAreaId);

    Long getOntologyTermId();

    void setOntologyTermId(Long ontologyTermId);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);
}
