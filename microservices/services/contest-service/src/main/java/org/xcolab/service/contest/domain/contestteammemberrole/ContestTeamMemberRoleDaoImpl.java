package org.xcolab.service.contest.domain.contestteammemberrole;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.ContestTeamMemberRole;
import org.xcolab.service.contest.exceptions.NotFoundException;

import static org.xcolab.model.Tables.*;

@Repository
public class ContestTeamMemberRoleDaoImpl implements ContestTeamMemberRoleDao {

    @Autowired
    private DSLContext dslContext;

    public ContestTeamMemberRole get(Long id_) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(CONTEST_TEAM_MEMBER_ROLE)
                .where(CONTEST_TEAM_MEMBER_ROLE.ID_.eq(id_))
                .fetchOne();

        if (record == null) {
            throw new NotFoundException("ContestTeamMemberRole with id " + id_ + " does not exist");
        }
        return record.into(ContestTeamMemberRole.class);

    }
}
