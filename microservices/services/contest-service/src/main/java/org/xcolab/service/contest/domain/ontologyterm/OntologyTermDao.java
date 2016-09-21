package org.xcolab.service.contest.domain.ontologyterm;

import org.xcolab.model.tables.pojos.OntologyTerm;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface OntologyTermDao {
    OntologyTerm get(Long id_) throws NotFoundException;
    List<OntologyTerm> findByGiven(String name);
}
