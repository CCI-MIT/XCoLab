package org.xcolab.service.contest.domain.contestteammember;

import org.xcolab.client.contest.pojo.IContestTeamMember;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ContestTeamMemberDao {
    boolean delete(Long id);

    IContestTeamMember create(IContestTeamMember contestTeamMember);

    Optional<IContestTeamMember> get(Long id) throws NotFoundException;

    boolean exists(Long id);

    boolean update(IContestTeamMember contestTeamMember);

    List<IContestTeamMember> findByGiven(Long userId, Long contestId, Long roleId);

    IContestTeamMember findOneBy(Long userId, Long contestId, Long roleId);

    List<IContestTeamMember> findByContestYear(Long categoryId, Long contestYear);
}
