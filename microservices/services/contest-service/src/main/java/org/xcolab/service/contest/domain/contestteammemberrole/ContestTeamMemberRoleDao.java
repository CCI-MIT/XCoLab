package org.xcolab.service.contest.domain.contestteammemberrole;

import org.xcolab.client.contest.pojo.IContestTeamMemberRole;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.Optional;

public interface ContestTeamMemberRoleDao {
    Optional<IContestTeamMemberRole> get(Long id) throws NotFoundException;
}
