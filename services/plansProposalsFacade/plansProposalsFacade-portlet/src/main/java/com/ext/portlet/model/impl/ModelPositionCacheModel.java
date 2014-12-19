package com.ext.portlet.model.impl;

import com.ext.portlet.model.ModelPosition;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ModelPosition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelPosition
 * @generated
 */
public class ModelPositionCacheModel implements CacheModel<ModelPosition>,
    Externalizable {
    public long id;
    public long positionId;
    public long modelId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(id);
        sb.append(", positionId=");
        sb.append(positionId);
        sb.append(", modelId=");
        sb.append(modelId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ModelPosition toEntityModel() {
        ModelPositionImpl modelPositionImpl = new ModelPositionImpl();

        modelPositionImpl.setId(id);
        modelPositionImpl.setPositionId(positionId);
        modelPositionImpl.setModelId(modelId);

        modelPositionImpl.resetOriginalValues();

        return modelPositionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        positionId = objectInput.readLong();
        modelId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);
        objectOutput.writeLong(positionId);
        objectOutput.writeLong(modelId);
    }
}
