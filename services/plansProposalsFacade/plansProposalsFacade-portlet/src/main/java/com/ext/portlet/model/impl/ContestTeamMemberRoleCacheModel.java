package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestTeamMemberRole;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContestTeamMemberRole in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberRole
 * @generated
 */
public class ContestTeamMemberRoleCacheModel implements CacheModel<ContestTeamMemberRole>,
    Externalizable {
    public long id;
    public String role;
    public int sort;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(id);
        sb.append(", role=");
        sb.append(role);
        sb.append(", sort=");
        sb.append(sort);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ContestTeamMemberRole toEntityModel() {
        ContestTeamMemberRoleImpl contestTeamMemberRoleImpl = new ContestTeamMemberRoleImpl();

        contestTeamMemberRoleImpl.setId(id);

        if (role == null) {
            contestTeamMemberRoleImpl.setRole(StringPool.BLANK);
        } else {
            contestTeamMemberRoleImpl.setRole(role);
        }

        contestTeamMemberRoleImpl.setSort(sort);

        contestTeamMemberRoleImpl.resetOriginalValues();

        return contestTeamMemberRoleImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        role = objectInput.readUTF();
        sort = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);

        if (role == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(role);
        }

        objectOutput.writeInt(sort);
    }
}
