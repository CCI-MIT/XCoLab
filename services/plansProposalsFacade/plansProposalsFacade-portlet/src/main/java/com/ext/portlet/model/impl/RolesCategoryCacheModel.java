package com.ext.portlet.model.impl;

import com.ext.portlet.model.RolesCategory;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing RolesCategory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see RolesCategory
 * @generated
 */
public class RolesCategoryCacheModel implements CacheModel<RolesCategory>,
    Externalizable {
    public long roleId;
    public String categoryName;
    public int roleOrdinal;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{roleId=");
        sb.append(roleId);
        sb.append(", categoryName=");
        sb.append(categoryName);
        sb.append(", roleOrdinal=");
        sb.append(roleOrdinal);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public RolesCategory toEntityModel() {
        RolesCategoryImpl rolesCategoryImpl = new RolesCategoryImpl();

        rolesCategoryImpl.setRoleId(roleId);

        if (categoryName == null) {
            rolesCategoryImpl.setCategoryName(StringPool.BLANK);
        } else {
            rolesCategoryImpl.setCategoryName(categoryName);
        }

        rolesCategoryImpl.setRoleOrdinal(roleOrdinal);

        rolesCategoryImpl.resetOriginalValues();

        return rolesCategoryImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        roleId = objectInput.readLong();
        categoryName = objectInput.readUTF();
        roleOrdinal = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(roleId);

        if (categoryName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(categoryName);
        }

        objectOutput.writeInt(roleOrdinal);
    }
}
