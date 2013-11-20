package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ModelInputItemLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class ModelInputItemClp extends BaseModelImpl<ModelInputItem>
    implements ModelInputItem {
    private long _modelInputItemPK;
    private long _modelId;
    private long _modelInputItemID;
    private long _modelGroupId;
    private int _displayItemOrder;
    private String _type;
    private String _properties;
    private BaseModel<?> _modelInputItemRemoteModel;

    public ModelInputItemClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ModelInputItem.class;
    }

    @Override
    public String getModelClassName() {
        return ModelInputItem.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _modelInputItemPK;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setModelInputItemPK(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _modelInputItemPK;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelInputItemPK", getModelInputItemPK());
        attributes.put("modelId", getModelId());
        attributes.put("modelInputItemID", getModelInputItemID());
        attributes.put("modelGroupId", getModelGroupId());
        attributes.put("displayItemOrder", getDisplayItemOrder());
        attributes.put("type", getType());
        attributes.put("properties", getProperties());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelInputItemPK = (Long) attributes.get("modelInputItemPK");

        if (modelInputItemPK != null) {
            setModelInputItemPK(modelInputItemPK);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Long modelInputItemID = (Long) attributes.get("modelInputItemID");

        if (modelInputItemID != null) {
            setModelInputItemID(modelInputItemID);
        }

        Long modelGroupId = (Long) attributes.get("modelGroupId");

        if (modelGroupId != null) {
            setModelGroupId(modelGroupId);
        }

        Integer displayItemOrder = (Integer) attributes.get("displayItemOrder");

        if (displayItemOrder != null) {
            setDisplayItemOrder(displayItemOrder);
        }

        String type = (String) attributes.get("type");

        if (type != null) {
            setType(type);
        }

        String properties = (String) attributes.get("properties");

        if (properties != null) {
            setProperties(properties);
        }
    }

    @Override
    public long getModelInputItemPK() {
        return _modelInputItemPK;
    }

    @Override
    public void setModelInputItemPK(long modelInputItemPK) {
        _modelInputItemPK = modelInputItemPK;

        if (_modelInputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelInputItemPK",
                        long.class);

                method.invoke(_modelInputItemRemoteModel, modelInputItemPK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getModelId() {
        return _modelId;
    }

    @Override
    public void setModelId(long modelId) {
        _modelId = modelId;

        if (_modelInputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelId", long.class);

                method.invoke(_modelInputItemRemoteModel, modelId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getModelInputItemID() {
        return _modelInputItemID;
    }

    @Override
    public void setModelInputItemID(long modelInputItemID) {
        _modelInputItemID = modelInputItemID;

        if (_modelInputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelInputItemID",
                        long.class);

                method.invoke(_modelInputItemRemoteModel, modelInputItemID);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getModelGroupId() {
        return _modelGroupId;
    }

    @Override
    public void setModelGroupId(long modelGroupId) {
        _modelGroupId = modelGroupId;

        if (_modelInputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelGroupId", long.class);

                method.invoke(_modelInputItemRemoteModel, modelGroupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDisplayItemOrder() {
        return _displayItemOrder;
    }

    @Override
    public void setDisplayItemOrder(int displayItemOrder) {
        _displayItemOrder = displayItemOrder;

        if (_modelInputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setDisplayItemOrder", int.class);

                method.invoke(_modelInputItemRemoteModel, displayItemOrder);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getType() {
        return _type;
    }

    @Override
    public void setType(String type) {
        _type = type;

        if (_modelInputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setType", String.class);

                method.invoke(_modelInputItemRemoteModel, type);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProperties() {
        return _properties;
    }

    @Override
    public void setProperties(String properties) {
        _properties = properties;

        if (_modelInputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setProperties", String.class);

                method.invoke(_modelInputItemRemoteModel, properties);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getModelInputItemRemoteModel() {
        return _modelInputItemRemoteModel;
    }

    public void setModelInputItemRemoteModel(
        BaseModel<?> modelInputItemRemoteModel) {
        _modelInputItemRemoteModel = modelInputItemRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _modelInputItemRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_modelInputItemRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelInputItemLocalServiceUtil.addModelInputItem(this);
        } else {
            ModelInputItemLocalServiceUtil.updateModelInputItem(this);
        }
    }

    @Override
    public ModelInputItem toEscapedModel() {
        return (ModelInputItem) ProxyUtil.newProxyInstance(ModelInputItem.class.getClassLoader(),
            new Class[] { ModelInputItem.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelInputItemClp clone = new ModelInputItemClp();

        clone.setModelInputItemPK(getModelInputItemPK());
        clone.setModelId(getModelId());
        clone.setModelInputItemID(getModelInputItemID());
        clone.setModelGroupId(getModelGroupId());
        clone.setDisplayItemOrder(getDisplayItemOrder());
        clone.setType(getType());
        clone.setProperties(getProperties());

        return clone;
    }

    @Override
    public int compareTo(ModelInputItem modelInputItem) {
        long primaryKey = modelInputItem.getPrimaryKey();

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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModelInputItemClp)) {
            return false;
        }

        ModelInputItemClp modelInputItem = (ModelInputItemClp) obj;

        long primaryKey = modelInputItem.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{modelInputItemPK=");
        sb.append(getModelInputItemPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelInputItemID=");
        sb.append(getModelInputItemID());
        sb.append(", modelGroupId=");
        sb.append(getModelGroupId());
        sb.append(", displayItemOrder=");
        sb.append(getDisplayItemOrder());
        sb.append(", type=");
        sb.append(getType());
        sb.append(", properties=");
        sb.append(getProperties());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ModelInputItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelInputItemPK</column-name><column-value><![CDATA[");
        sb.append(getModelInputItemPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelInputItemID</column-name><column-value><![CDATA[");
        sb.append(getModelInputItemID());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelGroupId</column-name><column-value><![CDATA[");
        sb.append(getModelGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>displayItemOrder</column-name><column-value><![CDATA[");
        sb.append(getDisplayItemOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>properties</column-name><column-value><![CDATA[");
        sb.append(getProperties());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
