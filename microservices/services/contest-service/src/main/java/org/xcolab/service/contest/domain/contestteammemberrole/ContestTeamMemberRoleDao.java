package org.xcolab.service.contest.domain.contestteammemberrole;

import org.xcolab.model.tables.pojos.ContestTeamMemberRole;
import org.xcolab.service.contest.exceptions.NotFoundException;

public interface ContestTeamMemberRoleDao {
    ContestTeamMemberRole get(Long id_) throws NotFoundException;
}
