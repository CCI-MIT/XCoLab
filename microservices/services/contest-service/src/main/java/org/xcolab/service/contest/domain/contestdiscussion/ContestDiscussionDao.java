package org.xcolab.service.contest.domain.contestdiscussion;

import org.xcolab.model.tables.pojos.ContestDiscussion;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface ContestDiscussionDao {

    ContestDiscussion create(ContestDiscussion contest);

    boolean update(ContestDiscussion contest);

    Optional<ContestDiscussion> get(Long contestId) throws NotFoundException;

    List<ContestDiscussion> findByGiven(PaginationHelper paginationHelper, Long contestId, String tab);
}
