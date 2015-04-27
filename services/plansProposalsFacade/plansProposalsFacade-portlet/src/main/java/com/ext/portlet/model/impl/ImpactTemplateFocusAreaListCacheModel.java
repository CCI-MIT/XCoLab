package com.ext.portlet.model.impl;

import com.ext.portlet.model.ImpactTemplateFocusAreaList;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ImpactTemplateFocusAreaList in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateFocusAreaList
 * @generated
 */
public class ImpactTemplateFocusAreaListCacheModel implements CacheModel<ImpactTemplateFocusAreaList>,
    Externalizable {
    public long focusAreaListId;
    public String name;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{focusAreaListId=");
        sb.append(focusAreaListId);
        sb.append(", name=");
        sb.append(name);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ImpactTemplateFocusAreaList toEntityModel() {
        ImpactTemplateFocusAreaListImpl impactTemplateFocusAreaListImpl = new ImpactTemplateFocusAreaListImpl();

        impactTemplateFocusAreaListImpl.setFocusAreaListId(focusAreaListId);

        if (name == null) {
            impactTemplateFocusAreaListImpl.setName(StringPool.BLANK);
        } else {
            impactTemplateFocusAreaListImpl.setName(name);
        }

        impactTemplateFocusAreaListImpl.resetOriginalValues();

        return impactTemplateFocusAreaListImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        focusAreaListId = objectInput.readLong();
        name = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(focusAreaListId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }
    }
}
