package com.ext.portlet.model.impl;

import com.ext.portlet.model.ImpactTemplateSeries;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ImpactTemplateSeries in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateSeries
 * @generated
 */
public class ImpactTemplateSeriesCacheModel implements CacheModel<ImpactTemplateSeries>,
    Externalizable {
    public long seriesId;
    public long iterationId;
    public String name;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{seriesId=");
        sb.append(seriesId);
        sb.append(", iterationId=");
        sb.append(iterationId);
        sb.append(", name=");
        sb.append(name);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ImpactTemplateSeries toEntityModel() {
        ImpactTemplateSeriesImpl impactTemplateSeriesImpl = new ImpactTemplateSeriesImpl();

        impactTemplateSeriesImpl.setSeriesId(seriesId);
        impactTemplateSeriesImpl.setIterationId(iterationId);

        if (name == null) {
            impactTemplateSeriesImpl.setName(StringPool.BLANK);
        } else {
            impactTemplateSeriesImpl.setName(name);
        }

        impactTemplateSeriesImpl.resetOriginalValues();

        return impactTemplateSeriesImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        seriesId = objectInput.readLong();
        iterationId = objectInput.readLong();
        name = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(seriesId);
        objectOutput.writeLong(iterationId);

        if (name == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(name);
        }
    }
}
