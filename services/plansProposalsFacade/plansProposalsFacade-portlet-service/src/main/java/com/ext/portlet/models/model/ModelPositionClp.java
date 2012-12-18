package com.ext.portlet.models.model;

import com.ext.portlet.models.service.ModelPositionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ModelPositionClp extends BaseModelImpl<ModelPosition>
    implements ModelPosition {
    private long _id;
    private long _positionId;
    private long _modelId;

    public ModelPositionClp() {
    }

    public Class<?> getModelClass() {
        return ModelPosition.class;
    }

    public String getModelClassName() {
        return ModelPosition.class.getName();
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public long getPositionId() {
        return _positionId;
    }

    public void setPositionId(long positionId) {
        _positionId = positionId;
    }

    public long getModelId() {
        return _modelId;
    }

    public void setModelId(long modelId) {
        _modelId = modelId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelPositionLocalServiceUtil.addModelPosition(this);
        } else {
            ModelPositionLocalServiceUtil.updateModelPosition(this);
        }
    }

    @Override
    public ModelPosition toEscapedModel() {
        return (ModelPosition) Proxy.newProxyInstance(ModelPosition.class.getClassLoader(),
            new Class[] { ModelPosition.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelPositionClp clone = new ModelPositionClp();

        clone.setId(getId());
        clone.setPositionId(getPositionId());
        clone.setModelId(getModelId());

        return clone;
    }

    public int compareTo(ModelPosition modelPosition) {
        long primaryKey = modelPosition.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ModelPositionClp modelPosition = null;

        try {
            modelPosition = (ModelPositionClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = modelPosition.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelPosition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
