package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ModelCategoryLocalServiceUtil;

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


public class ModelCategoryClp extends BaseModelImpl<ModelCategory>
    implements ModelCategory {
    private long _modelCategoryPK;
    private String _modelCategoryName;
    private String _modelCategoryDescription;
    private int _modelCategoryDisplayWeight;
    private BaseModel<?> _modelCategoryRemoteModel;

    public ModelCategoryClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ModelCategory.class;
    }

    @Override
    public String getModelClassName() {
        return ModelCategory.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _modelCategoryPK;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setModelCategoryPK(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _modelCategoryPK;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelCategoryPK", getModelCategoryPK());
        attributes.put("modelCategoryName", getModelCategoryName());
        attributes.put("modelCategoryDescription", getModelCategoryDescription());
        attributes.put("modelCategoryDisplayWeight",
            getModelCategoryDisplayWeight());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelCategoryPK = (Long) attributes.get("modelCategoryPK");

        if (modelCategoryPK != null) {
            setModelCategoryPK(modelCategoryPK);
        }

        String modelCategoryName = (String) attributes.get("modelCategoryName");

        if (modelCategoryName != null) {
            setModelCategoryName(modelCategoryName);
        }

        String modelCategoryDescription = (String) attributes.get(
                "modelCategoryDescription");

        if (modelCategoryDescription != null) {
            setModelCategoryDescription(modelCategoryDescription);
        }

        Integer modelCategoryDisplayWeight = (Integer) attributes.get(
                "modelCategoryDisplayWeight");

        if (modelCategoryDisplayWeight != null) {
            setModelCategoryDisplayWeight(modelCategoryDisplayWeight);
        }
    }

    @Override
    public long getModelCategoryPK() {
        return _modelCategoryPK;
    }

    @Override
    public void setModelCategoryPK(long modelCategoryPK) {
        _modelCategoryPK = modelCategoryPK;

        if (_modelCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _modelCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setModelCategoryPK", long.class);

                method.invoke(_modelCategoryRemoteModel, modelCategoryPK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelCategoryName() {
        return _modelCategoryName;
    }

    @Override
    public void setModelCategoryName(String modelCategoryName) {
        _modelCategoryName = modelCategoryName;

        if (_modelCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _modelCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setModelCategoryName",
                        String.class);

                method.invoke(_modelCategoryRemoteModel, modelCategoryName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModelCategoryDescription() {
        return _modelCategoryDescription;
    }

    @Override
    public void setModelCategoryDescription(String modelCategoryDescription) {
        _modelCategoryDescription = modelCategoryDescription;

        if (_modelCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _modelCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setModelCategoryDescription",
                        String.class);

                method.invoke(_modelCategoryRemoteModel,
                    modelCategoryDescription);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModelCategoryDisplayWeight() {
        return _modelCategoryDisplayWeight;
    }

    @Override
    public void setModelCategoryDisplayWeight(int modelCategoryDisplayWeight) {
        _modelCategoryDisplayWeight = modelCategoryDisplayWeight;

        if (_modelCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _modelCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setModelCategoryDisplayWeight",
                        int.class);

                method.invoke(_modelCategoryRemoteModel,
                    modelCategoryDisplayWeight);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getModelCategoryRemoteModel() {
        return _modelCategoryRemoteModel;
    }

    public void setModelCategoryRemoteModel(
        BaseModel<?> modelCategoryRemoteModel) {
        _modelCategoryRemoteModel = modelCategoryRemoteModel;
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

        Class<?> remoteModelClass = _modelCategoryRemoteModel.getClass();

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

        Object returnValue = method.invoke(_modelCategoryRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelCategoryLocalServiceUtil.addModelCategory(this);
        } else {
            ModelCategoryLocalServiceUtil.updateModelCategory(this);
        }
    }

    @Override
    public ModelCategory toEscapedModel() {
        return (ModelCategory) ProxyUtil.newProxyInstance(ModelCategory.class.getClassLoader(),
            new Class[] { ModelCategory.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelCategoryClp clone = new ModelCategoryClp();

        clone.setModelCategoryPK(getModelCategoryPK());
        clone.setModelCategoryName(getModelCategoryName());
        clone.setModelCategoryDescription(getModelCategoryDescription());
        clone.setModelCategoryDisplayWeight(getModelCategoryDisplayWeight());

        return clone;
    }

    @Override
    public int compareTo(ModelCategory modelCategory) {
        long primaryKey = modelCategory.getPrimaryKey();

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

        if (!(obj instanceof ModelCategoryClp)) {
            return false;
        }

        ModelCategoryClp modelCategory = (ModelCategoryClp) obj;

        long primaryKey = modelCategory.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{modelCategoryPK=");
        sb.append(getModelCategoryPK());
        sb.append(", modelCategoryName=");
        sb.append(getModelCategoryName());
        sb.append(", modelCategoryDescription=");
        sb.append(getModelCategoryDescription());
        sb.append(", modelCategoryDisplayWeight=");
        sb.append(getModelCategoryDisplayWeight());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ModelCategory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelCategoryPK</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelCategoryName</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelCategoryDescription</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelCategoryDisplayWeight</column-name><column-value><![CDATA[");
        sb.append(getModelCategoryDisplayWeight());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
