package com.ext.portlet.model.impl;

import com.ext.portlet.model.ImpactDefaultSeries;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ImpactDefaultSeries in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeries
 * @generated
 */
public class ImpactDefaultSeriesCacheModel implements CacheModel<ImpactDefaultSeries>,
    Externalizable {
    public long seriesId;
    public String name;
    public String description;
    public long focusAreaId;
    public boolean visible;
    public boolean editable;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{seriesId=");
        sb.append(seriesId);
        sb.append(", name=");
        sb.append(name);
        sb.append(", description=");
        sb.append(description);
        sb.append(", focusAreaId=");
        sb.append(focusAreaId);
        sb.append(", visible=");
        sb.append(visible);
        sb.append(", editable=");
        sb.append(editable);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ImpactDefaultSeries toEntityModel() {
        ImpactDefaultSeriesImpl impactDefaultSeriesImpl = new ImpactDefaultSeriesImpl();

        impactDefaultSeriesImpl.setSeriesId(seriesId);

        if (name == null) {
            impactDefaultSeriesImpl.setName(StringPool.BLANK);
        } else {
            impactDefaultSeriesImpl.setName(name);
        }

        if (description == null) {
            impactDefaultSeriesImpl.setDescription(StringPool.BLANK);
        } else {
            impactDefaultSeriesImpl.setDescription(description);
        }

        impactDefaultSeriesImpl.setFocusAreaId(focusAreaId);
        impactDefaultSeriesImpl.setVisible(visible);
        impactDefaultSeriesImpl.setEditable(editable);

        impactDefaultSeriesImpl.resetOriginalValues();

        return impactDefaultSeriesImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        seriesId = objectInput.readLong();
        name = objectInput.readUTF();
        description = objectInput.readUTF();
        focusAreaId = objectInput.readLong();
        visible = objectInput.readBoolean();
        editable = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(seriesId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        objectOutput.writeLong(focusAreaId);
        objectOutput.writeBoolean(visible);
        objectOutput.writeBoolean(editable);
    }
}
