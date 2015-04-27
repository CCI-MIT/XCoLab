package com.ext.portlet.model.impl;

import com.ext.portlet.model.ImpactDefaultSeriesData;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ImpactDefaultSeriesData in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesData
 * @generated
 */
public class ImpactDefaultSeriesDataCacheModel implements CacheModel<ImpactDefaultSeriesData>,
    Externalizable {
    public long seriesId;
    public int year;
    public double value;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{seriesId=");
        sb.append(seriesId);
        sb.append(", year=");
        sb.append(year);
        sb.append(", value=");
        sb.append(value);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ImpactDefaultSeriesData toEntityModel() {
        ImpactDefaultSeriesDataImpl impactDefaultSeriesDataImpl = new ImpactDefaultSeriesDataImpl();

        impactDefaultSeriesDataImpl.setSeriesId(seriesId);
        impactDefaultSeriesDataImpl.setYear(year);
        impactDefaultSeriesDataImpl.setValue(value);

        impactDefaultSeriesDataImpl.resetOriginalValues();

        return impactDefaultSeriesDataImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        seriesId = objectInput.readLong();
        year = objectInput.readInt();
        value = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(seriesId);
        objectOutput.writeInt(year);
        objectOutput.writeDouble(value);
    }
}
