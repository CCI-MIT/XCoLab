package org.xcolab.service.proposal.domain.ontologyspace;

import org.xcolab.model.tables.pojos.OntologySpace;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

public interface OntologySpaceDao {

    List<OntologySpace> findByGiven(Integer order);

    int delete(Long id_);

    boolean update(OntologySpace ontologySpace);

    OntologySpace get(Long id_) throws NotFoundException;

    OntologySpace create(OntologySpace ontologySpace);
}
