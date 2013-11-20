package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ModelInputGroupLocalServiceUtil;

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


public class ModelInputGroupClp extends BaseModelImpl<ModelInputGroup>
    implements ModelInputGroup {
    private long _modelInputGroupPK;
    private long _modelId;
    private long _nameAndDescriptionMetaDataId;
    private String _name;
    private String _description;
    private int _displayItemOrder;
    private String _groupType;
    private long _parentGroupPK;
    private BaseModel<?> _modelInputGroupRemoteModel;

    public ModelInputGroupClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ModelInputGroup.class;
    }

    @Override
    public String getModelClassName() {
        return ModelInputGroup.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _modelInputGroupPK;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setModelInputGroupPK(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _modelInputGroupPK;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelInputGroupPK", getModelInputGroupPK());
        attributes.put("modelId", getModelId());
        attributes.put("nameAndDescriptionMetaDataId",
            getNameAndDescriptionMetaDataId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("displayItemOrder", getDisplayItemOrder());
        attributes.put("groupType", getGroupType());
        attributes.put("parentGroupPK", getParentGroupPK());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelInputGroupPK = (Long) attributes.get("modelInputGroupPK");

        if (modelInputGroupPK != null) {
            setModelInputGroupPK(modelInputGroupPK);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Long nameAndDescriptionMetaDataId = (Long) attributes.get(
                "nameAndDescriptionMetaDataId");

        if (nameAndDescriptionMetaDataId != null) {
            setNameAndDescriptionMetaDataId(nameAndDescriptionMetaDataId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Integer displayItemOrder = (Integer) attributes.get("displayItemOrder");

        if (displayItemOrder != null) {
            setDisplayItemOrder(displayItemOrder);
        }

        String groupType = (String) attributes.get("groupType");

        if (groupType != null) {
            setGroupType(groupType);
        }

        Long parentGroupPK = (Long) attributes.get("parentGroupPK");

        if (parentGroupPK != null) {
            setParentGroupPK(parentGroupPK);
        }
    }

    @Override
    public long getModelInputGroupPK() {
        return _modelInputGroupPK;
    }

    @Override
    public void setModelInputGroupPK(long modelInputGroupPK) {
        _modelInputGroupPK = modelInputGroupPK;

        if (_modelInputGroupRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModelInputGroupPK",
                        long.class);

                method.invoke(_modelInputGroupRemoteModel, modelInputGroupPK);
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

        if (_modelInputGroupRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setModelId", long.class);

                method.invoke(_modelInputGroupRemoteModel, modelId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getNameAndDescriptionMetaDataId() {
        return _nameAndDescriptionMetaDataId;
    }

    @Override
    public void setNameAndDescriptionMetaDataId(
        long nameAndDescriptionMetaDataId) {
        _nameAndDescriptionMetaDataId = nameAndDescriptionMetaDataId;

        if (_modelInputGroupRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setNameAndDescriptionMetaDataId",
                        long.class);

                method.invoke(_modelInputGroupRemoteModel,
                    nameAndDescriptionMetaDataId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;

        if (_modelInputGroupRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_modelInputGroupRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_modelInputGroupRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_modelInputGroupRemoteModel, description);
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

        if (_modelInputGroupRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setDisplayItemOrder", int.class);

                method.invoke(_modelInputGroupRemoteModel, displayItemOrder);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGroupType() {
        return _groupType;
    }

    @Override
    public void setGroupType(String groupType) {
        _groupType = groupType;

        if (_modelInputGroupRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupType", String.class);

                method.invoke(_modelInputGroupRemoteModel, groupType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getParentGroupPK() {
        return _parentGroupPK;
    }

    @Override
    public void setParentGroupPK(long parentGroupPK) {
        _parentGroupPK = parentGroupPK;

        if (_modelInputGroupRemoteModel != null) {
            try {
                Class<?> clazz = _modelInputGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setParentGroupPK", long.class);

                method.invoke(_modelInputGroupRemoteModel, parentGroupPK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getModelInputGroupRemoteModel() {
        return _modelInputGroupRemoteModel;
    }

    public void setModelInputGroupRemoteModel(
        BaseModel<?> modelInputGroupRemoteModel) {
        _modelInputGroupRemoteModel = modelInputGroupRemoteModel;
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

        Class<?> remoteModelClass = _modelInputGroupRemoteModel.getClass();

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

        Object returnValue = method.invoke(_modelInputGroupRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelInputGroupLocalServiceUtil.addModelInputGroup(this);
        } else {
            ModelInputGroupLocalServiceUtil.updateModelInputGroup(this);
        }
    }

    @Override
    public ModelInputGroup toEscapedModel() {
        return (ModelInputGroup) ProxyUtil.newProxyInstance(ModelInputGroup.class.getClassLoader(),
            new Class[] { ModelInputGroup.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelInputGroupClp clone = new ModelInputGroupClp();

        clone.setModelInputGroupPK(getModelInputGroupPK());
        clone.setModelId(getModelId());
        clone.setNameAndDescriptionMetaDataId(getNameAndDescriptionMetaDataId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setDisplayItemOrder(getDisplayItemOrder());
        clone.setGroupType(getGroupType());
        clone.setParentGroupPK(getParentGroupPK());

        return clone;
    }

    @Override
    public int compareTo(ModelInputGroup modelInputGroup) {
        long primaryKey = modelInputGroup.getPrimaryKey();

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

        if (!(obj instanceof ModelInputGroupClp)) {
            return false;
        }

        ModelInputGroupClp modelInputGroup = (ModelInputGroupClp) obj;

        long primaryKey = modelInputGroup.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{modelInputGroupPK=");
        sb.append(getModelInputGroupPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", nameAndDescriptionMetaDataId=");
        sb.append(getNameAndDescriptionMetaDataId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", displayItemOrder=");
        sb.append(getDisplayItemOrder());
        sb.append(", groupType=");
        sb.append(getGroupType());
        sb.append(", parentGroupPK=");
        sb.append(getParentGroupPK());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ModelInputGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelInputGroupPK</column-name><column-value><![CDATA[");
        sb.append(getModelInputGroupPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nameAndDescriptionMetaDataId</column-name><column-value><![CDATA[");
        sb.append(getNameAndDescriptionMetaDataId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>displayItemOrder</column-name><column-value><![CDATA[");
        sb.append(getDisplayItemOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupType</column-name><column-value><![CDATA[");
        sb.append(getGroupType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentGroupPK</column-name><column-value><![CDATA[");
        sb.append(getParentGroupPK());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
