package org.xcolab.service.contest.domain.ontologyterm;

import org.xcolab.model.tables.pojos.OntologyTerm;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface OntologyTermDao {
    OntologyTerm get(Long id_) throws NotFoundException;
    List<OntologyTerm> findByGiven(String name, Long parentId, Long ontologySpaceId);
    boolean update(OntologyTerm ontologyTerm);
    OntologyTerm create(OntologyTerm ontologyTerm);
    int delete(Long id_);

    List<OntologyTerm> getOntologyTermByFocusAreaAndOntologySpaceName(Long focusArea,
            String ontologySpaceName);
}
