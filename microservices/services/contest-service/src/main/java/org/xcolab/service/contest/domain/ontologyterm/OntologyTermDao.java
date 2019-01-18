package org.xcolab.service.contest.domain.ontologyterm;

import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface OntologyTermDao {

    OntologyTermWrapper get(Long id) throws NotFoundException;

    List<OntologyTermWrapper> findByGiven(String name, Long parentId, Long ontologySpaceId);

    boolean update(OntologyTermWrapper ontologyTerm);

    OntologyTermWrapper create(OntologyTermWrapper ontologyTerm);

    int delete(Long id);

    List<OntologyTermWrapper> getOntologyTermByFocusAreaAndOntologySpaceName(Long focusArea,
            String ontologySpaceName);
}
