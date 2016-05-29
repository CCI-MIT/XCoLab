package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestTeamMember;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContestTeamMember in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMember
 * @generated
 */
public class ContestTeamMemberCacheModel implements CacheModel<ContestTeamMember>,
    Externalizable {
    public long id;
    public long contestId;
    public long userId;
    public long roleId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(id);
        sb.append(", contestId=");
        sb.append(contestId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", roleId=");
        sb.append(roleId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ContestTeamMember toEntityModel() {
        ContestTeamMemberImpl contestTeamMemberImpl = new ContestTeamMemberImpl();

        contestTeamMemberImpl.setId(id);
        contestTeamMemberImpl.setContestId(contestId);
        contestTeamMemberImpl.setUserId(userId);
        contestTeamMemberImpl.setRoleId(roleId);

        contestTeamMemberImpl.resetOriginalValues();

        return contestTeamMemberImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        contestId = objectInput.readLong();
        userId = objectInput.readLong();
        roleId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(contestId);
        objectOutput.writeLong(userId);
        objectOutput.writeLong(roleId);
    }
}
