package com.ext.portlet.model.impl;

import com.ext.portlet.model.Users_Roles;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Users_Roles in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Users_Roles
 * @generated
 */
public class Users_RolesCacheModel implements CacheModel<Users_Roles>,
    Externalizable {
    public long userId;
    public long roleId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{userId=");
        sb.append(userId);
        sb.append(", roleId=");
        sb.append(roleId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Users_Roles toEntityModel() {
        Users_RolesImpl users_RolesImpl = new Users_RolesImpl();

        users_RolesImpl.setUserId(userId);
        users_RolesImpl.setRoleId(roleId);

        users_RolesImpl.resetOriginalValues();

        return users_RolesImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        userId = objectInput.readLong();
        roleId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(userId);
        objectOutput.writeLong(roleId);
    }
}
