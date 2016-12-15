package org.xcolab.service.members.domain.role;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Role_;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_TEAM_MEMBER;
import static org.xcolab.model.Tables.ROLE_;
import static org.xcolab.model.Tables.USERS_ROLES;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final DSLContext dslContext;

    @Autowired
    public RoleDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<Role_> getMemberRoles(Long memberId) {
        return this.dslContext.select()
                .from(USERS_ROLES)
                .join(ROLE_).on(ROLE_.ROLE_ID.eq(USERS_ROLES.ROLE_ID))
                .where(USERS_ROLES.USER_ID.equal(memberId)).fetchInto(Role_.class);
    }

    @Override
    public List<Role_> getMemberRolesInContest(Long memberId, Long contestId) {
        return this.dslContext.select()
                .from(ROLE_)
                .join(CONTEST_TEAM_MEMBER).on(CONTEST_TEAM_MEMBER.ROLE_ID.equal(ROLE_.ROLE_ID))
                .where(CONTEST_TEAM_MEMBER.CONTEST_ID.equal(contestId))
                .and(CONTEST_TEAM_MEMBER.USER_ID.equal(memberId))
                .fetchInto(Role_.class);
    }

    @Override
    public void assignMemberRole(Long memberId, Long roleId) {
        this.dslContext.insertInto(USERS_ROLES)
                .set(USERS_ROLES.ROLE_ID, roleId)
                .set(USERS_ROLES.USER_ID, memberId)
                .execute();
    }
    @Override
    public boolean memberHasRole(Long memberId, Long roleId) {
        return this.dslContext.selectCount()
                .from(USERS_ROLES)
                .where(USERS_ROLES.ROLE_ID.eq(roleId)).and(USERS_ROLES.USER_ID.equal(memberId))
                .fetchOne(0, Integer.class) > 0;

    }

    @Override
    public boolean deleteMemberRole(long memberId, long roleId) {
        return dslContext.deleteFrom(USERS_ROLES)
                .where(USERS_ROLES.USER_ID.eq(memberId).and(USERS_ROLES.ROLE_ID.eq(roleId)))
                .execute() > 0;
    }
}
