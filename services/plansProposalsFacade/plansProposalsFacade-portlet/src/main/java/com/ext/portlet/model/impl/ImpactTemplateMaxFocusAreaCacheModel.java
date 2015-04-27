package com.ext.portlet.model.impl;

import com.ext.portlet.model.ImpactTemplateMaxFocusArea;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ImpactTemplateMaxFocusArea in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateMaxFocusArea
 * @generated
 */
public class ImpactTemplateMaxFocusAreaCacheModel implements CacheModel<ImpactTemplateMaxFocusArea>,
    Externalizable {
    public long focusAreaListId;
    public long focusAreaId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{focusAreaListId=");
        sb.append(focusAreaListId);
        sb.append(", focusAreaId=");
        sb.append(focusAreaId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ImpactTemplateMaxFocusArea toEntityModel() {
        ImpactTemplateMaxFocusAreaImpl impactTemplateMaxFocusAreaImpl = new ImpactTemplateMaxFocusAreaImpl();

        impactTemplateMaxFocusAreaImpl.setFocusAreaListId(focusAreaListId);
        impactTemplateMaxFocusAreaImpl.setFocusAreaId(focusAreaId);

        impactTemplateMaxFocusAreaImpl.resetOriginalValues();

        return impactTemplateMaxFocusAreaImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        focusAreaListId = objectInput.readLong();
        focusAreaId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(focusAreaListId);
        objectOutput.writeLong(focusAreaId);
    }
}
