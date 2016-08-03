package org.xcolab.service.contest.domain.focusarea;

import org.xcolab.model.tables.pojos.FocusArea;
import org.xcolab.service.contest.exceptions.NotFoundException;

public interface FocusAreaDao {
    FocusArea get(Long id_) throws NotFoundException;
}
