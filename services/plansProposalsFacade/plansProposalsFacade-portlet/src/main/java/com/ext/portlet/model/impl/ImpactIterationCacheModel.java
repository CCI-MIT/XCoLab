package com.ext.portlet.model.impl;

import com.ext.portlet.model.ImpactIteration;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ImpactIteration in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactIteration
 * @generated
 */
public class ImpactIterationCacheModel implements CacheModel<ImpactIteration>,
    Externalizable {
    public long iterationId;
    public int year;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{iterationId=");
        sb.append(iterationId);
        sb.append(", year=");
        sb.append(year);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ImpactIteration toEntityModel() {
        ImpactIterationImpl impactIterationImpl = new ImpactIterationImpl();

        impactIterationImpl.setIterationId(iterationId);
        impactIterationImpl.setYear(year);

        impactIterationImpl.resetOriginalValues();

        return impactIterationImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        iterationId = objectInput.readLong();
        year = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(iterationId);
        objectOutput.writeInt(year);
    }
}
