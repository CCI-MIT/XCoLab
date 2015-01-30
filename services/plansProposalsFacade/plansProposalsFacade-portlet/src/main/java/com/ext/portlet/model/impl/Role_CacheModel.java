package com.ext.portlet.model.impl;

import com.ext.portlet.model.Role_;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Role_ in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Role_
 * @generated
 */
public class Role_CacheModel implements CacheModel<Role_>, Externalizable {
    public long roleId;
    public String name;
    public String categoryName;
    public int roleOrdinal;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{roleId=");
        sb.append(roleId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", categoryName=");
        sb.append(categoryName);
        sb.append(", roleOrdinal=");
        sb.append(roleOrdinal);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public Role_ toEntityModel() {
        Role_Impl role_Impl = new Role_Impl();

        role_Impl.setRoleId(roleId);

        if (name == null) {
            role_Impl.setName(StringPool.BLANK);
        } else {
            role_Impl.setName(name);
        }

        if (categoryName == null) {
            role_Impl.setCategoryName(StringPool.BLANK);
        } else {
            role_Impl.setCategoryName(categoryName);
        }

        role_Impl.setRoleOrdinal(roleOrdinal);

        role_Impl.resetOriginalValues();

        return role_Impl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        roleId = objectInput.readLong();
        name = objectInput.readUTF();
        categoryName = objectInput.readUTF();
        roleOrdinal = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(roleId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (categoryName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(categoryName);
        }

        objectOutput.writeInt(roleOrdinal);
    }
}
