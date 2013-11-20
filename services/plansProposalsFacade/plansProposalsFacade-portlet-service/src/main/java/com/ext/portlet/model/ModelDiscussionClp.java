package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ModelDiscussionLocalServiceUtil;

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


public class ModelDiscussionClp extends BaseModelImpl<ModelDiscussion>
    implements ModelDiscussion {
    private long _modelDiscussionId;
    private long _modelId;
    private long _categoryId;
    private BaseModel<?> _modelDiscussionRemoteModel;

    public ModelDiscussionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ModelDiscussion.class;
    }

    @Override
    public String getModelClassName() {
        return ModelDiscussion.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _modelDiscussionId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setModelDiscussionId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _modelDiscussionId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelDiscussionId", getModelDiscussionId());
        attributes.put("modelId", getModelId());
        attributes.put("categoryId", getCategoryId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelDiscussionId = (Long) attributes.get("modelDiscussionId");

        if (modelDiscussionId != null) {
            setModelDiscussionId(modelDiscussionId);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Long categoryId = (Long) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }
    }

    @Override
    public long getModelDiscussionId() {
        return _modelDiscussionId;
    }

    @Override
    public void setModelDiscussionId(long modelDiscussionId) {
        _modelDiscussionId = modelDiscussionId;

        if (_modelDiscussionRemoteModel != null) {
            try {
                Class<?> clazz = _modelDiscussionRemoteModel.getClass();

                Method method = clazz.getMethod("setModelDiscussionId",
                        long.class);

                method.invoke(_modelDiscussionRemoteModel, modelDiscussionId);
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

        if (_modelDiscussionRemoteModel != null) {
            try {
                Class<?> clazz = _modelDiscussionRemoteModel.getClass();

                Method method = clazz.getMethod("setModelId", long.class);

                method.invoke(_modelDiscussionRemoteModel, modelId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCategoryId() {
        return _categoryId;
    }

    @Override
    public void setCategoryId(long categoryId) {
        _categoryId = categoryId;

        if (_modelDiscussionRemoteModel != null) {
            try {
                Class<?> clazz = _modelDiscussionRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryId", long.class);

                method.invoke(_modelDiscussionRemoteModel, categoryId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getModelDiscussionRemoteModel() {
        return _modelDiscussionRemoteModel;
    }

    public void setModelDiscussionRemoteModel(
        BaseModel<?> modelDiscussionRemoteModel) {
        _modelDiscussionRemoteModel = modelDiscussionRemoteModel;
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

        Class<?> remoteModelClass = _modelDiscussionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_modelDiscussionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelDiscussionLocalServiceUtil.addModelDiscussion(this);
        } else {
            ModelDiscussionLocalServiceUtil.updateModelDiscussion(this);
        }
    }

    @Override
    public ModelDiscussion toEscapedModel() {
        return (ModelDiscussion) ProxyUtil.newProxyInstance(ModelDiscussion.class.getClassLoader(),
            new Class[] { ModelDiscussion.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelDiscussionClp clone = new ModelDiscussionClp();

        clone.setModelDiscussionId(getModelDiscussionId());
        clone.setModelId(getModelId());
        clone.setCategoryId(getCategoryId());

        return clone;
    }

    @Override
    public int compareTo(ModelDiscussion modelDiscussion) {
        long primaryKey = modelDiscussion.getPrimaryKey();

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

        if (!(obj instanceof ModelDiscussionClp)) {
            return false;
        }

        ModelDiscussionClp modelDiscussion = (ModelDiscussionClp) obj;

        long primaryKey = modelDiscussion.getPrimaryKey();

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

        sb.append("{modelDiscussionId=");
        sb.append(getModelDiscussionId());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ModelDiscussion");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelDiscussionId</column-name><column-value><![CDATA[");
        sb.append(getModelDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
