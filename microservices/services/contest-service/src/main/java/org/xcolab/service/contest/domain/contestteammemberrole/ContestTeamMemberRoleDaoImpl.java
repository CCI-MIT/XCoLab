package org.xcolab.service.contest.domain.contestteammemberrole;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import org.xcolab.client.contest.pojo.IContestTeamMemberRole;
import org.xcolab.client.contest.pojo.tables.pojos.ContestTeamMemberRole;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.Optional;

import static org.xcolab.model.Tables.*;

@Repository
public class ContestTeamMemberRoleDaoImpl implements ContestTeamMemberRoleDao {

    private final DSLContext dslContext;

    @Autowired
    public ContestTeamMemberRoleDaoImpl(DSLContext dslContext) {
        Assert.notNull(dslContext, "DSLContext bean is required");
        this.dslContext = dslContext;
    }

    @Override
    public Optional<IContestTeamMemberRole> get(Long id) throws NotFoundException {

        final Record record = this.dslContext.selectFrom(CONTEST_TEAM_MEMBER_ROLE)
                .where(CONTEST_TEAM_MEMBER_ROLE.ID.eq(id))
                .fetchOne();

        if (record == null) {
            return Optional.empty();
        }
        return Optional.of(record.into(ContestTeamMemberRole.class));

    }
}
