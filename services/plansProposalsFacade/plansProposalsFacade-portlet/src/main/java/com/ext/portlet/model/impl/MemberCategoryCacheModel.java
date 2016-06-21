package com.ext.portlet.model.impl;

import com.ext.portlet.model.MemberCategory;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MemberCategory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MemberCategory
 * @generated
 */
public class MemberCategoryCacheModel implements CacheModel<MemberCategory>,
    Externalizable {
    public long roleId;
    public String displayName;
    public String categoryName;
    public long sortOrder;
    public boolean showInList;
    public String imageName;
    public String description;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{roleId=");
        sb.append(roleId);
        sb.append(", displayName=");
        sb.append(displayName);
        sb.append(", categoryName=");
        sb.append(categoryName);
        sb.append(", sortOrder=");
        sb.append(sortOrder);
        sb.append(", showInList=");
        sb.append(showInList);
        sb.append(", imageName=");
        sb.append(imageName);
        sb.append(", description=");
        sb.append(description);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MemberCategory toEntityModel() {
        MemberCategoryImpl memberCategoryImpl = new MemberCategoryImpl();

        memberCategoryImpl.setRoleId(roleId);

        if (displayName == null) {
            memberCategoryImpl.setDisplayName(StringPool.BLANK);
        } else {
            memberCategoryImpl.setDisplayName(displayName);
        }

        if (categoryName == null) {
            memberCategoryImpl.setCategoryName(StringPool.BLANK);
        } else {
            memberCategoryImpl.setCategoryName(categoryName);
        }

        memberCategoryImpl.setSortOrder(sortOrder);
        memberCategoryImpl.setShowInList(showInList);

        if (imageName == null) {
            memberCategoryImpl.setImageName(StringPool.BLANK);
        } else {
            memberCategoryImpl.setImageName(imageName);
        }

        if (description == null) {
            memberCategoryImpl.setDescription(StringPool.BLANK);
        } else {
            memberCategoryImpl.setDescription(description);
        }

        memberCategoryImpl.resetOriginalValues();

        return memberCategoryImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        roleId = objectInput.readLong();
        displayName = objectInput.readUTF();
        categoryName = objectInput.readUTF();
        sortOrder = objectInput.readLong();
        showInList = objectInput.readBoolean();
        imageName = objectInput.readUTF();
        description = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(roleId);

        if (displayName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(displayName);
        }

        if (categoryName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(categoryName);
        }

        objectOutput.writeLong(sortOrder);
        objectOutput.writeBoolean(showInList);

        if (imageName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(imageName);
        }

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }
    }
}
