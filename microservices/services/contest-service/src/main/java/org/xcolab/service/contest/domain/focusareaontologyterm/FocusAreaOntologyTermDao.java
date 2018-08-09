package org.xcolab.service.contest.domain.focusareaontologyterm;

import org.xcolab.model.tables.pojos.FocusAreaOntologyTerm;

import java.util.List;

public interface FocusAreaOntologyTermDao {

    List<FocusAreaOntologyTerm> findByGiven(Long focusAreaId, Long ontologyTermId);

    FocusAreaOntologyTerm create(FocusAreaOntologyTerm focusAreaOntologyTerm);

    List<FocusAreaOntologyTerm> findByOntologyTermIds(List<Long> ontologyTermId);

    int deleteAllFocusAreasOntologyTerms(Long focusAreaId, Long ontologyTermId);
}
