package org.xcolab.service.contest.domain.focusarea;

import org.xcolab.model.tables.pojos.FocusArea;
import org.xcolab.model.tables.pojos.FocusAreaOntologyTerm;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface FocusAreaDao {
    FocusArea get(Long id_) throws NotFoundException;
    List<FocusArea> findByGiven();
    FocusArea create(FocusArea focusArea);
    int delete(Long id_);
    boolean update(FocusArea focusArea);
}
