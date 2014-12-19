package com.ext.portlet.model.impl;

import com.ext.portlet.model.FocusArea;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FocusArea in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FocusArea
 * @generated
 */
public class FocusAreaCacheModel implements CacheModel<FocusArea>,
    Externalizable {
    public long id;
    public String name;
    public int order;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(id);
        sb.append(", name=");
        sb.append(name);
        sb.append(", order=");
        sb.append(order);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public FocusArea toEntityModel() {
        FocusAreaImpl focusAreaImpl = new FocusAreaImpl();

        focusAreaImpl.setId(id);

        if (name == null) {
            focusAreaImpl.setName(StringPool.BLANK);
        } else {
            focusAreaImpl.setName(name);
        }

        focusAreaImpl.setOrder(order);

        focusAreaImpl.resetOriginalValues();

        return focusAreaImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        name = objectInput.readUTF();
        order = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        objectOutput.writeInt(order);
    }
}
