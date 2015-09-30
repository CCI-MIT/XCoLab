package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ModelOutputItemLocalServiceUtil;

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


public class ModelOutputItemClp extends BaseModelImpl<ModelOutputItem>
    implements ModelOutputItem {
    private long _modelOutputItemModifierPK;
    private long _modelId;
    private long _modelOutputItemId;
    private int _modelOutputItemOrder;
    private String _modelItemRangePolicy;
    private String _modelItemRangeMessage;
    private String _modelItemErrorPolicy;
    private String _modelItemErrorMessage;
    private String _modelItemLabelFormat;
    private boolean _modelItemIsVisible;
    private String _itemType;
    private long _relatedOutputItem;
    private BaseModel<?> _modelOutputItemRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ModelOutputItemClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ModelOutputItem.class;
    }

    @Override
    public String getModelClassName() {
        return ModelOutputItem.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _modelOutputItemModifierPK;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setModelOutputItemModifierPK(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _modelOutputItemModifierPK;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelOutputItemModifierPK",
            getModelOutputItemModifierPK());
        attributes.put("modelId", getModelId());
        attributes.put("modelOutputItemId", getModelOutputItemId());
        attributes.put("modelOutputItemOrder", getModelOutputItemOrder());
        attributes.put("modelItemRangePolicy", getModelItemRangePolicy());
        attributes.put("modelItemRangeMessage", getModelItemRangeMessage());
        attributes.put("modelItemErrorPolicy", getModelItemErrorPolicy());
        attributes.put("modelItemErrorMessage", getModelItemErrorMessage());
        attributes.put("modelItemLabelFormat", getModelItemLabelFormat());
        attributes.put("modelItemIsVisible", getModelItemIsVisible());
        attributes.put("itemType", getItemType());
        attributes.put("relatedOutputItem", getRelatedOutputItem());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelOutputItemModifierPK = (Long) attributes.get(
                "modelOutputItemModifierPK");

        if (modelOutputItemModifierPK != null) {
            setModelOutputItemModifierPK(modelOutputItemModifierPK);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Long modelOutputItemId = (Long) attributes.get("modelOutputItemId");

        if (modelOutputItemId != null) {
            setModelOutputItemId(modelOutputItemId);
        }

        Integer modelOutputItemOrder = (Integer) attributes.get(
                "modelOutputItemOrder");

        if (modelOutputItemOrder != null) {
            setModelOutputItemOrder(modelOutputItemOrder);
        }

        String modelItemRangePolicy = (String) attributes.get(
                "modelItemRangePolicy");

        if (modelItemRangePolicy != null) {
            setModelItemRangePolicy(modelItemRangePolicy);
        }

        String modelItemRangeMessage = (String) attributes.get(
                "modelItemRangeMessage");

        if (modelItemRangeMessage != null) {
            setModelItemRangeMessage(modelItemRangeMessage);
        }

        String modelItemErrorPolicy = (String) attributes.get(
                "modelItemErrorPolicy");

        if (modelItemErrorPolicy != null) {
            setModelItemErrorPolicy(modelItemErrorPolicy);
        }

        String modelItemErrorMessage = (String) attributes.get(
                "modelItemErrorMessage");

        if (modelItemErrorMessage != null) {
            setModelItemErrorMessage(modelItemErrorMessage);
        }

        String modelItemLabelFormat = (String) attributes.get(
                "modelItemLabelFormat");

        if (modelItemLabelFormat != null) {
            setModelItemLabelFormat(modelItemLabelFormat);
        }

        Boolean modelItemIsVisible = (Boolean) attributes.get(
                "modelItemIsVisible");

        if (modelItemIsVisible != null) {
            setModelItemIsVisible(modelItemIsVisible);
        }

        String itemType = (String) attributes.get("itemType");

        if (itemType != null) {
            setItemType(itemType);
        }

        Long relatedOutputItem = (Long) attributes.get("relatedOutputItem");

        if (relatedOutputItem != null) {
            setRelatedOutputItem(relatedOutputItem);
        }
    }

    @Override
    public long getModelOutputItemModifierPK() {
        return _modelOutputItemModifierPK;
    }

    @Override
    public void setModelOutputItemModifierPK(long modelOutputItemModifierPK) {
        _modelOutputItemModifierPK = modelOutputItemModifierPK;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelOutputItemModifierPK",
                        long.class);

                method.invoke(_modelOutputItemRemoteModel,
                    modelOutputItemModifierPK);
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

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelId", long.class);

                method.invoke(_modelOutputItemRemoteModel, modelId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getModelOutputItemId() {
        return _modelOutputItemId;
    }

    @Override
    public void setModelOutputItemId(long modelOutputItemId) {
        _modelOutputItemId = modelOutputItemId;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelOutputItemId",
                        long.class);

                method.invoke(_modelOutputItemRemoteModel, modelOutputItemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModelOutputItemOrder() {
        return _modelOutputItemOrder;
    }

    @Override
    public void setModelOutputItemOrder(int modelOutputItemOrder) {
        _modelOutputItemOrder = modelOutputItemOrder;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelOutputItemOrder",
                        int.class);

                method.invoke(_modelOutputItemRemoteModel, modelOutputItemOrder);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelItemRangePolicy() {
        return _modelItemRangePolicy;
    }

    @Override
    public void setModelItemRangePolicy(String modelItemRangePolicy) {
        _modelItemRangePolicy = modelItemRangePolicy;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelItemRangePolicy",
                        String.class);

                method.invoke(_modelOutputItemRemoteModel, modelItemRangePolicy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelItemRangeMessage() {
        return _modelItemRangeMessage;
    }

    @Override
    public void setModelItemRangeMessage(String modelItemRangeMessage) {
        _modelItemRangeMessage = modelItemRangeMessage;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelItemRangeMessage",
                        String.class);

                method.invoke(_modelOutputItemRemoteModel, modelItemRangeMessage);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelItemErrorPolicy() {
        return _modelItemErrorPolicy;
    }

    @Override
    public void setModelItemErrorPolicy(String modelItemErrorPolicy) {
        _modelItemErrorPolicy = modelItemErrorPolicy;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelItemErrorPolicy",
                        String.class);

                method.invoke(_modelOutputItemRemoteModel, modelItemErrorPolicy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelItemErrorMessage() {
        return _modelItemErrorMessage;
    }

    @Override
    public void setModelItemErrorMessage(String modelItemErrorMessage) {
        _modelItemErrorMessage = modelItemErrorMessage;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelItemErrorMessage",
                        String.class);

                method.invoke(_modelOutputItemRemoteModel, modelItemErrorMessage);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelItemLabelFormat() {
        return _modelItemLabelFormat;
    }

    @Override
    public void setModelItemLabelFormat(String modelItemLabelFormat) {
        _modelItemLabelFormat = modelItemLabelFormat;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelItemLabelFormat",
                        String.class);

                method.invoke(_modelOutputItemRemoteModel, modelItemLabelFormat);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getModelItemIsVisible() {
        return _modelItemIsVisible;
    }

    @Override
    public boolean isModelItemIsVisible() {
        return _modelItemIsVisible;
    }

    @Override
    public void setModelItemIsVisible(boolean modelItemIsVisible) {
        _modelItemIsVisible = modelItemIsVisible;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setModelItemIsVisible",
                        boolean.class);

                method.invoke(_modelOutputItemRemoteModel, modelItemIsVisible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemType() {
        return _itemType;
    }

    @Override
    public void setItemType(String itemType) {
        _itemType = itemType;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setItemType", String.class);

                method.invoke(_modelOutputItemRemoteModel, itemType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getRelatedOutputItem() {
        return _relatedOutputItem;
    }

    @Override
    public void setRelatedOutputItem(long relatedOutputItem) {
        _relatedOutputItem = relatedOutputItem;

        if (_modelOutputItemRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputItemRemoteModel.getClass();

                Method method = clazz.getMethod("setRelatedOutputItem",
                        long.class);

                method.invoke(_modelOutputItemRemoteModel, relatedOutputItem);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getModelOutputItemRemoteModel() {
        return _modelOutputItemRemoteModel;
    }

    public void setModelOutputItemRemoteModel(
        BaseModel<?> modelOutputItemRemoteModel) {
        _modelOutputItemRemoteModel = modelOutputItemRemoteModel;
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

        Class<?> remoteModelClass = _modelOutputItemRemoteModel.getClass();

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

        Object returnValue = method.invoke(_modelOutputItemRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelOutputItemLocalServiceUtil.addModelOutputItem(this);
        } else {
            ModelOutputItemLocalServiceUtil.updateModelOutputItem(this);
        }
    }

    @Override
    public ModelOutputItem toEscapedModel() {
        return (ModelOutputItem) ProxyUtil.newProxyInstance(ModelOutputItem.class.getClassLoader(),
            new Class[] { ModelOutputItem.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelOutputItemClp clone = new ModelOutputItemClp();

        clone.setModelOutputItemModifierPK(getModelOutputItemModifierPK());
        clone.setModelId(getModelId());
        clone.setModelOutputItemId(getModelOutputItemId());
        clone.setModelOutputItemOrder(getModelOutputItemOrder());
        clone.setModelItemRangePolicy(getModelItemRangePolicy());
        clone.setModelItemRangeMessage(getModelItemRangeMessage());
        clone.setModelItemErrorPolicy(getModelItemErrorPolicy());
        clone.setModelItemErrorMessage(getModelItemErrorMessage());
        clone.setModelItemLabelFormat(getModelItemLabelFormat());
        clone.setModelItemIsVisible(getModelItemIsVisible());
        clone.setItemType(getItemType());
        clone.setRelatedOutputItem(getRelatedOutputItem());

        return clone;
    }

    @Override
    public int compareTo(ModelOutputItem modelOutputItem) {
        long primaryKey = modelOutputItem.getPrimaryKey();

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

        if (!(obj instanceof ModelOutputItemClp)) {
            return false;
        }

        ModelOutputItemClp modelOutputItem = (ModelOutputItemClp) obj;

        long primaryKey = modelOutputItem.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    public Class<?> getClpSerializerClass() {
        return _clpSerializerClass;
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(25);

        sb.append("{modelOutputItemModifierPK=");
        sb.append(getModelOutputItemModifierPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelOutputItemId=");
        sb.append(getModelOutputItemId());
        sb.append(", modelOutputItemOrder=");
        sb.append(getModelOutputItemOrder());
        sb.append(", modelItemRangePolicy=");
        sb.append(getModelItemRangePolicy());
        sb.append(", modelItemRangeMessage=");
        sb.append(getModelItemRangeMessage());
        sb.append(", modelItemErrorPolicy=");
        sb.append(getModelItemErrorPolicy());
        sb.append(", modelItemErrorMessage=");
        sb.append(getModelItemErrorMessage());
        sb.append(", modelItemLabelFormat=");
        sb.append(getModelItemLabelFormat());
        sb.append(", modelItemIsVisible=");
        sb.append(getModelItemIsVisible());
        sb.append(", itemType=");
        sb.append(getItemType());
        sb.append(", relatedOutputItem=");
        sb.append(getRelatedOutputItem());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ModelOutputItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelOutputItemModifierPK</column-name><column-value><![CDATA[");
        sb.append(getModelOutputItemModifierPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputItemId</column-name><column-value><![CDATA[");
        sb.append(getModelOutputItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputItemOrder</column-name><column-value><![CDATA[");
        sb.append(getModelOutputItemOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemRangePolicy</column-name><column-value><![CDATA[");
        sb.append(getModelItemRangePolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemRangeMessage</column-name><column-value><![CDATA[");
        sb.append(getModelItemRangeMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemErrorPolicy</column-name><column-value><![CDATA[");
        sb.append(getModelItemErrorPolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemErrorMessage</column-name><column-value><![CDATA[");
        sb.append(getModelItemErrorMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemLabelFormat</column-name><column-value><![CDATA[");
        sb.append(getModelItemLabelFormat());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemIsVisible</column-name><column-value><![CDATA[");
        sb.append(getModelItemIsVisible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemType</column-name><column-value><![CDATA[");
        sb.append(getItemType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relatedOutputItem</column-name><column-value><![CDATA[");
        sb.append(getRelatedOutputItem());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
