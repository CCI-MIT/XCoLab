package org.xcolab.service.contest.domain.contentarticle;

import org.xcolab.model.tables.pojos.Contest;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ContestDao {
    Contest get(Long contestId) throws NotFoundException;

    List<Contest> findByGiven(String contestUrlName, Long contestYear);
}
