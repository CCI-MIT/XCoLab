package org.xcolab.service.members.domain.role;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.xcolab.model.tables.pojos.Role;

import java.util.List;

import static org.xcolab.model.Tables.CONTEST_TEAM_MEMBER;
import static org.xcolab.model.Tables.ROLE;
import static org.xcolab.model.Tables.USER_ROLE;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final DSLContext dslContext;

    @Autowired
    public RoleDaoImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<Role> getUserRoles(Long userId) {
        return this.dslContext.select()
                .from(USER_ROLE)
                .join(ROLE).on(ROLE.ROLE_ID.eq(USER_ROLE.ROLE_ID))
                .where(USER_ROLE.USER_ID.equal(userId)).fetchInto(Role.class);
    }

    @Override
    public List<Role> getUserRolesInContest(Long userId, Long contestId) {
        return this.dslContext.select()
                .from(ROLE)
                .join(CONTEST_TEAM_MEMBER).on(CONTEST_TEAM_MEMBER.ROLE_ID.equal(ROLE.ROLE_ID))
                .where(CONTEST_TEAM_MEMBER.CONTEST_ID.equal(contestId))
                .and(CONTEST_TEAM_MEMBER.USER_ID.equal(userId))
                .fetchInto(Role.class);
    }

    @Override
    public void assignUserRole(Long userId, Long roleId) {
        this.dslContext.insertInto(USER_ROLE)
                .set(USER_ROLE.ROLE_ID, roleId)
                .set(USER_ROLE.USER_ID, userId)
                .execute();
    }
    @Override
    public boolean memberHasRole(Long userId, Long roleId) {
        return this.dslContext.selectCount()
                .from(USER_ROLE)
                .where(USER_ROLE.ROLE_ID.eq(roleId)).and(USER_ROLE.USER_ID.equal(userId))
                .fetchOne(0, Integer.class) > 0;

    }

    @Override
    public boolean deleteUserRole(long userId, long roleId) {
        return dslContext.deleteFrom(USER_ROLE)
                .where(USER_ROLE.USER_ID.eq(userId).and(USER_ROLE.ROLE_ID.eq(roleId)))
                .execute() > 0;
    }
}
