package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ModelOutputChartOrderLocalServiceUtil;

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


public class ModelOutputChartOrderClp extends BaseModelImpl<ModelOutputChartOrder>
    implements ModelOutputChartOrder {
    private long _modelOutputChartOrderPK;
    private long _modelId;
    private String _modelOutputLabel;
    private int _modelOutputChartOrder;
    private String _modelIndexRangePolicy;
    private String _modelIndexRangeMessage;
    private String _modelIndexErrorPolicy;
    private String _modelIndexErrorMessage;
    private boolean _modelChartIsVisible;
    private BaseModel<?> _modelOutputChartOrderRemoteModel;

    public ModelOutputChartOrderClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ModelOutputChartOrder.class;
    }

    @Override
    public String getModelClassName() {
        return ModelOutputChartOrder.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _modelOutputChartOrderPK;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setModelOutputChartOrderPK(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _modelOutputChartOrderPK;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelOutputChartOrderPK", getModelOutputChartOrderPK());
        attributes.put("modelId", getModelId());
        attributes.put("modelOutputLabel", getModelOutputLabel());
        attributes.put("modelOutputChartOrder", getModelOutputChartOrder());
        attributes.put("modelIndexRangePolicy", getModelIndexRangePolicy());
        attributes.put("modelIndexRangeMessage", getModelIndexRangeMessage());
        attributes.put("modelIndexErrorPolicy", getModelIndexErrorPolicy());
        attributes.put("modelIndexErrorMessage", getModelIndexErrorMessage());
        attributes.put("modelChartIsVisible", getModelChartIsVisible());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelOutputChartOrderPK = (Long) attributes.get(
                "modelOutputChartOrderPK");

        if (modelOutputChartOrderPK != null) {
            setModelOutputChartOrderPK(modelOutputChartOrderPK);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        String modelOutputLabel = (String) attributes.get("modelOutputLabel");

        if (modelOutputLabel != null) {
            setModelOutputLabel(modelOutputLabel);
        }

        Integer modelOutputChartOrder = (Integer) attributes.get(
                "modelOutputChartOrder");

        if (modelOutputChartOrder != null) {
            setModelOutputChartOrder(modelOutputChartOrder);
        }

        String modelIndexRangePolicy = (String) attributes.get(
                "modelIndexRangePolicy");

        if (modelIndexRangePolicy != null) {
            setModelIndexRangePolicy(modelIndexRangePolicy);
        }

        String modelIndexRangeMessage = (String) attributes.get(
                "modelIndexRangeMessage");

        if (modelIndexRangeMessage != null) {
            setModelIndexRangeMessage(modelIndexRangeMessage);
        }

        String modelIndexErrorPolicy = (String) attributes.get(
                "modelIndexErrorPolicy");

        if (modelIndexErrorPolicy != null) {
            setModelIndexErrorPolicy(modelIndexErrorPolicy);
        }

        String modelIndexErrorMessage = (String) attributes.get(
                "modelIndexErrorMessage");

        if (modelIndexErrorMessage != null) {
            setModelIndexErrorMessage(modelIndexErrorMessage);
        }

        Boolean modelChartIsVisible = (Boolean) attributes.get(
                "modelChartIsVisible");

        if (modelChartIsVisible != null) {
            setModelChartIsVisible(modelChartIsVisible);
        }
    }

    @Override
    public long getModelOutputChartOrderPK() {
        return _modelOutputChartOrderPK;
    }

    @Override
    public void setModelOutputChartOrderPK(long modelOutputChartOrderPK) {
        _modelOutputChartOrderPK = modelOutputChartOrderPK;

        if (_modelOutputChartOrderRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputChartOrderRemoteModel.getClass();

                Method method = clazz.getMethod("setModelOutputChartOrderPK",
                        long.class);

                method.invoke(_modelOutputChartOrderRemoteModel,
                    modelOutputChartOrderPK);
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

        if (_modelOutputChartOrderRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputChartOrderRemoteModel.getClass();

                Method method = clazz.getMethod("setModelId", long.class);

                method.invoke(_modelOutputChartOrderRemoteModel, modelId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelOutputLabel() {
        return _modelOutputLabel;
    }

    @Override
    public void setModelOutputLabel(String modelOutputLabel) {
        _modelOutputLabel = modelOutputLabel;

        if (_modelOutputChartOrderRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputChartOrderRemoteModel.getClass();

                Method method = clazz.getMethod("setModelOutputLabel",
                        String.class);

                method.invoke(_modelOutputChartOrderRemoteModel,
                    modelOutputLabel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModelOutputChartOrder() {
        return _modelOutputChartOrder;
    }

    @Override
    public void setModelOutputChartOrder(int modelOutputChartOrder) {
        _modelOutputChartOrder = modelOutputChartOrder;

        if (_modelOutputChartOrderRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputChartOrderRemoteModel.getClass();

                Method method = clazz.getMethod("setModelOutputChartOrder",
                        int.class);

                method.invoke(_modelOutputChartOrderRemoteModel,
                    modelOutputChartOrder);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelIndexRangePolicy() {
        return _modelIndexRangePolicy;
    }

    @Override
    public void setModelIndexRangePolicy(String modelIndexRangePolicy) {
        _modelIndexRangePolicy = modelIndexRangePolicy;

        if (_modelOutputChartOrderRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputChartOrderRemoteModel.getClass();

                Method method = clazz.getMethod("setModelIndexRangePolicy",
                        String.class);

                method.invoke(_modelOutputChartOrderRemoteModel,
                    modelIndexRangePolicy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelIndexRangeMessage() {
        return _modelIndexRangeMessage;
    }

    @Override
    public void setModelIndexRangeMessage(String modelIndexRangeMessage) {
        _modelIndexRangeMessage = modelIndexRangeMessage;

        if (_modelOutputChartOrderRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputChartOrderRemoteModel.getClass();

                Method method = clazz.getMethod("setModelIndexRangeMessage",
                        String.class);

                method.invoke(_modelOutputChartOrderRemoteModel,
                    modelIndexRangeMessage);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelIndexErrorPolicy() {
        return _modelIndexErrorPolicy;
    }

    @Override
    public void setModelIndexErrorPolicy(String modelIndexErrorPolicy) {
        _modelIndexErrorPolicy = modelIndexErrorPolicy;

        if (_modelOutputChartOrderRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputChartOrderRemoteModel.getClass();

                Method method = clazz.getMethod("setModelIndexErrorPolicy",
                        String.class);

                method.invoke(_modelOutputChartOrderRemoteModel,
                    modelIndexErrorPolicy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelIndexErrorMessage() {
        return _modelIndexErrorMessage;
    }

    @Override
    public void setModelIndexErrorMessage(String modelIndexErrorMessage) {
        _modelIndexErrorMessage = modelIndexErrorMessage;

        if (_modelOutputChartOrderRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputChartOrderRemoteModel.getClass();

                Method method = clazz.getMethod("setModelIndexErrorMessage",
                        String.class);

                method.invoke(_modelOutputChartOrderRemoteModel,
                    modelIndexErrorMessage);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getModelChartIsVisible() {
        return _modelChartIsVisible;
    }

    @Override
    public boolean isModelChartIsVisible() {
        return _modelChartIsVisible;
    }

    @Override
    public void setModelChartIsVisible(boolean modelChartIsVisible) {
        _modelChartIsVisible = modelChartIsVisible;

        if (_modelOutputChartOrderRemoteModel != null) {
            try {
                Class<?> clazz = _modelOutputChartOrderRemoteModel.getClass();

                Method method = clazz.getMethod("setModelChartIsVisible",
                        boolean.class);

                method.invoke(_modelOutputChartOrderRemoteModel,
                    modelChartIsVisible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getModelOutputChartOrderRemoteModel() {
        return _modelOutputChartOrderRemoteModel;
    }

    public void setModelOutputChartOrderRemoteModel(
        BaseModel<?> modelOutputChartOrderRemoteModel) {
        _modelOutputChartOrderRemoteModel = modelOutputChartOrderRemoteModel;
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

        Class<?> remoteModelClass = _modelOutputChartOrderRemoteModel.getClass();

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

        Object returnValue = method.invoke(_modelOutputChartOrderRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelOutputChartOrderLocalServiceUtil.addModelOutputChartOrder(this);
        } else {
            ModelOutputChartOrderLocalServiceUtil.updateModelOutputChartOrder(this);
        }
    }

    @Override
    public ModelOutputChartOrder toEscapedModel() {
        return (ModelOutputChartOrder) ProxyUtil.newProxyInstance(ModelOutputChartOrder.class.getClassLoader(),
            new Class[] { ModelOutputChartOrder.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelOutputChartOrderClp clone = new ModelOutputChartOrderClp();

        clone.setModelOutputChartOrderPK(getModelOutputChartOrderPK());
        clone.setModelId(getModelId());
        clone.setModelOutputLabel(getModelOutputLabel());
        clone.setModelOutputChartOrder(getModelOutputChartOrder());
        clone.setModelIndexRangePolicy(getModelIndexRangePolicy());
        clone.setModelIndexRangeMessage(getModelIndexRangeMessage());
        clone.setModelIndexErrorPolicy(getModelIndexErrorPolicy());
        clone.setModelIndexErrorMessage(getModelIndexErrorMessage());
        clone.setModelChartIsVisible(getModelChartIsVisible());

        return clone;
    }

    @Override
    public int compareTo(ModelOutputChartOrder modelOutputChartOrder) {
        long primaryKey = modelOutputChartOrder.getPrimaryKey();

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

        if (!(obj instanceof ModelOutputChartOrderClp)) {
            return false;
        }

        ModelOutputChartOrderClp modelOutputChartOrder = (ModelOutputChartOrderClp) obj;

        long primaryKey = modelOutputChartOrder.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{modelOutputChartOrderPK=");
        sb.append(getModelOutputChartOrderPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelOutputLabel=");
        sb.append(getModelOutputLabel());
        sb.append(", modelOutputChartOrder=");
        sb.append(getModelOutputChartOrder());
        sb.append(", modelIndexRangePolicy=");
        sb.append(getModelIndexRangePolicy());
        sb.append(", modelIndexRangeMessage=");
        sb.append(getModelIndexRangeMessage());
        sb.append(", modelIndexErrorPolicy=");
        sb.append(getModelIndexErrorPolicy());
        sb.append(", modelIndexErrorMessage=");
        sb.append(getModelIndexErrorMessage());
        sb.append(", modelChartIsVisible=");
        sb.append(getModelChartIsVisible());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ModelOutputChartOrder");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelOutputChartOrderPK</column-name><column-value><![CDATA[");
        sb.append(getModelOutputChartOrderPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputLabel</column-name><column-value><![CDATA[");
        sb.append(getModelOutputLabel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputChartOrder</column-name><column-value><![CDATA[");
        sb.append(getModelOutputChartOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexRangePolicy</column-name><column-value><![CDATA[");
        sb.append(getModelIndexRangePolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexRangeMessage</column-name><column-value><![CDATA[");
        sb.append(getModelIndexRangeMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexErrorPolicy</column-name><column-value><![CDATA[");
        sb.append(getModelIndexErrorPolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexErrorMessage</column-name><column-value><![CDATA[");
        sb.append(getModelIndexErrorMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelChartIsVisible</column-name><column-value><![CDATA[");
        sb.append(getModelChartIsVisible());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
