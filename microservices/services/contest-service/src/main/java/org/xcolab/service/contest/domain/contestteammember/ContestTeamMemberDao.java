package org.xcolab.service.contest.domain.contestteammember;

import org.xcolab.model.tables.pojos.ContestTeamMember;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ContestTeamMemberDao {
    int delete(Long id_);

    ContestTeamMember create(ContestTeamMember contestTeamMember);

    ContestTeamMember get(Long id_) throws NotFoundException;

    boolean update(ContestTeamMember contestTeamMember);

    List<ContestTeamMember> findByGiven(Long contestId);
}
