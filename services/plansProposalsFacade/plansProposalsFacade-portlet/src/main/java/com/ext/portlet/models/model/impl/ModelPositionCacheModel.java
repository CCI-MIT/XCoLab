package com.ext.portlet.models.model.impl;

import com.ext.portlet.models.model.ModelPosition;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing ModelPosition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ModelPosition
 * @generated
 */
public class ModelPositionCacheModel implements CacheModel<ModelPosition>,
    Serializable {
    public Long id;
    public Long positionId;
    public Long modelId;

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

    public ModelPosition toEntityModel() {
        ModelPositionImpl modelPositionImpl = new ModelPositionImpl();

        modelPositionImpl.setId(id);
        modelPositionImpl.setPositionId(positionId);
        modelPositionImpl.setModelId(modelId);

        modelPositionImpl.resetOriginalValues();

        return modelPositionImpl;
    }
}
