package org.xcolab.service.contest.domain.ontologyspace;


import org.xcolab.model.tables.pojos.OntologySpace;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface OntologySpaceDao {

    List<OntologySpace> findByGiven();

    int delete(Long id);

    boolean update(OntologySpace ontologySpace);

    OntologySpace get(Long id) throws NotFoundException;

    OntologySpace create(OntologySpace ontologySpace);
}
