package org.xcolab.service.members.domain.rolegroup;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xcolab.model.tables.pojos.RoleGroup;
import org.xcolab.model.tables.pojos.Role_;
import org.xcolab.model.tables.records.RoleGroupRecord;

import java.util.List;

import static org.xcolab.model.Tables.ROLE_;
import static org.xcolab.model.Tables.ROLE_GROUP;
import static org.xcolab.model.Tables.ROLE_GROUP_ROLES;

@Repository
public class RoleGroupDaoImpl implements RoleGroupDao {

    private final DSLContext dslContext;

    @Autowired
    public RoleGroupDaoImpl(DSLContext dslContext) {this.dslContext = dslContext;}

    //    Create role group
    @Override
    public RoleGroup create(RoleGroup roleGroup) {
        RoleGroupRecord ret = this.dslContext.insertInto(ROLE_GROUP)
                .set(ROLE_GROUP.NAME, roleGroup.getName())
                .returning(ROLE_GROUP.ROLE_GROUP_ID)
                .fetchOne();
        if (ret != null) {
            roleGroup.setRoleGroupId(ret.getValue(ROLE_GROUP.ROLE_GROUP_ID));
            return roleGroup;
        } else {
            return null;
        }
    }

    //    Add role to group
    @Override
    public void addRoleToGroup(Long roleGroupId, Long roleId) {

        this.dslContext.insertInto(ROLE_GROUP_ROLES, ROLE_GROUP_ROLES.ROLE_GROUP_ID, ROLE_GROUP_ROLES.ROLE_ID)
                .values(roleGroupId, roleId).execute();

    }

    //Remove role from group
    @Override
    public void removeRoleFromGroup(Long roleGroupId, Long roleId) {

        this.dslContext.delete(ROLE_GROUP_ROLES)
                .where(ROLE_GROUP_ROLES.ROLE_GROUP_ID.eq(roleGroupId))
                .and(ROLE_GROUP_ROLES.ROLE_ID.eq(roleId));
    }


    @Override
    public List<Role_> getAllRolesInGroup(Long groupId) {
        return this.dslContext.select()
                .from(ROLE_GROUP_ROLES)
                .innerJoin(ROLE_).on(ROLE_.ROLE_ID.eq(ROLE_GROUP_ROLES.ROLE_ID))
                .where(ROLE_GROUP_ROLES.ROLE_GROUP_ID.eq(groupId))
                .fetchInto(Role_.class);
    }

    @Override
    public boolean groupHasRole(Long roleGroupId, Long groupId) {
        Record record =  this.dslContext.select()
                .from(ROLE_GROUP_ROLES)
                .where(ROLE_GROUP_ROLES.ROLE_GROUP_ID.eq(roleGroupId))
                .and(ROLE_GROUP_ROLES.ROLE_ID.eq(roleGroupId))
                .fetchOne();
        return record != null;
    }

}
