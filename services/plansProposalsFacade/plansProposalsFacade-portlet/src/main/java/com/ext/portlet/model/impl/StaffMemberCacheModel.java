package com.ext.portlet.model.impl;

import com.ext.portlet.model.StaffMember;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing StaffMember in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see StaffMember
 * @generated
 */
public class StaffMemberCacheModel implements CacheModel<StaffMember>,
    Externalizable {
    public long id;
    public long userId;
    public long categoryId;
    public String firstNames;
    public String lastName;
    public String url;
    public String photoUrl;
    public int sort;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(id);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", categoryId=");
        sb.append(categoryId);
        sb.append(", firstNames=");
        sb.append(firstNames);
        sb.append(", lastName=");
        sb.append(lastName);
        sb.append(", url=");
        sb.append(url);
        sb.append(", photoUrl=");
        sb.append(photoUrl);
        sb.append(", sort=");
        sb.append(sort);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StaffMember toEntityModel() {
        StaffMemberImpl staffMemberImpl = new StaffMemberImpl();

        staffMemberImpl.setId(id);
        staffMemberImpl.setUserId(userId);
        staffMemberImpl.setCategoryId(categoryId);

        if (firstNames == null) {
            staffMemberImpl.setFirstNames(StringPool.BLANK);
        } else {
            staffMemberImpl.setFirstNames(firstNames);
        }

        if (lastName == null) {
            staffMemberImpl.setLastName(StringPool.BLANK);
        } else {
            staffMemberImpl.setLastName(lastName);
        }

        if (url == null) {
            staffMemberImpl.setUrl(StringPool.BLANK);
        } else {
            staffMemberImpl.setUrl(url);
        }

        if (photoUrl == null) {
            staffMemberImpl.setPhotoUrl(StringPool.BLANK);
        } else {
            staffMemberImpl.setPhotoUrl(photoUrl);
        }

        staffMemberImpl.setSort(sort);

        staffMemberImpl.resetOriginalValues();

        return staffMemberImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        userId = objectInput.readLong();
        categoryId = objectInput.readLong();
        firstNames = objectInput.readUTF();
        lastName = objectInput.readUTF();
        url = objectInput.readUTF();
        photoUrl = objectInput.readUTF();
        sort = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(userId);
        objectOutput.writeLong(categoryId);

        if (firstNames == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(firstNames);
        }

        if (lastName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lastName);
        }

        if (url == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(url);
        }

        if (photoUrl == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(photoUrl);
        }

        objectOutput.writeInt(sort);
    }
}
