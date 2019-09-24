package org.xcolab.service.contest.domain.focusarea;

import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface FocusAreaDao {

    FocusAreaWrapper get(Long id) throws NotFoundException;

    List<FocusAreaWrapper> findByGiven();

    FocusAreaWrapper create(FocusAreaWrapper focusArea);

    int delete(Long id);

    boolean update(FocusAreaWrapper focusArea);
}
