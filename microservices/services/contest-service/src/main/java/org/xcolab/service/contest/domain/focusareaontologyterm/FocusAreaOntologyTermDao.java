package org.xcolab.service.contest.domain.focusareaontologyterm;

import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;

import java.util.List;

public interface FocusAreaOntologyTermDao {

    List<IFocusAreaOntologyTerm> findByGiven(Long focusAreaId, Long ontologyTermId);

    IFocusAreaOntologyTerm create(IFocusAreaOntologyTerm focusAreaOntologyTerm);

    List<IFocusAreaOntologyTerm> findByOntologyTermIds(List<Long> ontologyTermId);

    int deleteAllFocusAreasOntologyTerms(Long focusAreaId, Long ontologyTermId);
}
