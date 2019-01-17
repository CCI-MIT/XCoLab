package org.xcolab.service.contest.domain.contestdiscussion;

import org.xcolab.client.contest.pojo.IContestDiscussion;
import org.xcolab.service.contest.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;
import java.util.Optional;

public interface ContestDiscussionDao {

    IContestDiscussion create(IContestDiscussion contest);

    boolean update(IContestDiscussion contest);

    Optional<IContestDiscussion> get(Long contestId) throws NotFoundException;

    List<IContestDiscussion> findByGiven(PaginationHelper paginationHelper, Long contestId, String tab);
}
