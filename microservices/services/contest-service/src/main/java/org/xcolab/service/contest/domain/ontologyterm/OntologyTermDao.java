package org.xcolab.service.contest.domain.ontologyterm;

import org.xcolab.model.tables.pojos.OntologyTerm;
import org.xcolab.service.contest.exceptions.NotFoundException;

public interface OntologyTermDao {
    OntologyTerm get(Long id_) throws NotFoundException;
}
