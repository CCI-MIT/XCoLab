package org.xcolab.service.contest.domain.contestteammemberrole;

import org.xcolab.model.tables.pojos.ContestTeamMemberRole;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.Optional;

public interface ContestTeamMemberRoleDao {
    Optional<ContestTeamMemberRole> get(Long id) throws NotFoundException;
}
