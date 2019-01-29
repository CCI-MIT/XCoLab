package org.xcolab.service.contest.domain.ontologyspace;


import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface OntologySpaceDao {

    List<OntologySpaceWrapper> findByGiven();

    int delete(Long id);

    boolean update(OntologySpaceWrapper ontologySpace);

    OntologySpaceWrapper get(Long id) throws NotFoundException;

    OntologySpaceWrapper create(OntologySpaceWrapper ontologySpace);
}
