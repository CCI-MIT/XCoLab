package org.xcolab.services.proposalsService.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import org.xcolab.services.proposalsService.model.Foo;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Foo in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Foo
 * @generated
 */
public class FooCacheModel implements CacheModel<Foo>, Serializable {
    public String uuid;
    public long fooId;
    public long groupId;
    public long companyId;
    public long userId;
    public String userName;
    public long createDate;
    public long modifiedDate;
    public String field1;
    public boolean field2;
    public int field3;
    public long field4;
    public String field5;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(27);

        sb.append("{uuid=");
        sb.append(uuid);
        sb.append(", fooId=");
        sb.append(fooId);
        sb.append(", groupId=");
        sb.append(groupId);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", userName=");
        sb.append(userName);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", field1=");
        sb.append(field1);
        sb.append(", field2=");
        sb.append(field2);
        sb.append(", field3=");
        sb.append(field3);
        sb.append(", field4=");
        sb.append(field4);
        sb.append(", field5=");
        sb.append(field5);
        sb.append("}");

        return sb.toString();
    }

    public Foo toEntityModel() {
        FooImpl fooImpl = new FooImpl();

        if (uuid == null) {
            fooImpl.setUuid(StringPool.BLANK);
        } else {
            fooImpl.setUuid(uuid);
        }

        fooImpl.setFooId(fooId);
        fooImpl.setGroupId(groupId);
        fooImpl.setCompanyId(companyId);
        fooImpl.setUserId(userId);

        if (userName == null) {
            fooImpl.setUserName(StringPool.BLANK);
        } else {
            fooImpl.setUserName(userName);
        }

        if (createDate == Long.MIN_VALUE) {
            fooImpl.setCreateDate(null);
        } else {
            fooImpl.setCreateDate(new Date(createDate));
        }

        if (modifiedDate == Long.MIN_VALUE) {
            fooImpl.setModifiedDate(null);
        } else {
            fooImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (field1 == null) {
            fooImpl.setField1(StringPool.BLANK);
        } else {
            fooImpl.setField1(field1);
        }

        fooImpl.setField2(field2);
        fooImpl.setField3(field3);

        if (field4 == Long.MIN_VALUE) {
            fooImpl.setField4(null);
        } else {
            fooImpl.setField4(new Date(field4));
        }

        if (field5 == null) {
            fooImpl.setField5(StringPool.BLANK);
        } else {
            fooImpl.setField5(field5);
        }

        fooImpl.resetOriginalValues();

        return fooImpl;
    }
}
