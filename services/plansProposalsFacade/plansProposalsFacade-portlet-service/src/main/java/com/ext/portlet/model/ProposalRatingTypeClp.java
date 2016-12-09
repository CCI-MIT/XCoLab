package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;

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


public class ProposalRatingTypeClp extends BaseModelImpl<ProposalRatingType>
    implements ProposalRatingType {
    private long _id;
    private String _label;
    private String _description;
    private int _judgeType;
    private boolean _isActive;
    private BaseModel<?> _proposalRatingTypeRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ProposalRatingTypeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalRatingType.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalRatingType.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _id;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("label", getLabel());
        attributes.put("description", getDescription());
        attributes.put("judgeType", getJudgeType());
        attributes.put("isActive", getIsActive());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String label = (String) attributes.get("label");

        if (label != null) {
            setLabel(label);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Integer judgeType = (Integer) attributes.get("judgeType");

        if (judgeType != null) {
            setJudgeType(judgeType);
        }

        Boolean isActive = (Boolean) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_proposalRatingTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_proposalRatingTypeRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLabel() {
        return _label;
    }

    @Override
    public void setLabel(String label) {
        _label = label;

        if (_proposalRatingTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setLabel", String.class);

                method.invoke(_proposalRatingTypeRemoteModel, label);
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

        if (_proposalRatingTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_proposalRatingTypeRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getJudgeType() {
        return _judgeType;
    }

    @Override
    public void setJudgeType(int judgeType) {
        _judgeType = judgeType;

        if (_proposalRatingTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setJudgeType", int.class);

                method.invoke(_proposalRatingTypeRemoteModel, judgeType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getIsActive() {
        return _isActive;
    }

    @Override
    public boolean isIsActive() {
        return _isActive;
    }

    @Override
    public void setIsActive(boolean isActive) {
        _isActive = isActive;

        if (_proposalRatingTypeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingTypeRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", boolean.class);

                method.invoke(_proposalRatingTypeRemoteModel, isActive);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalRatingTypeRemoteModel() {
        return _proposalRatingTypeRemoteModel;
    }

    public void setProposalRatingTypeRemoteModel(
        BaseModel<?> proposalRatingTypeRemoteModel) {
        _proposalRatingTypeRemoteModel = proposalRatingTypeRemoteModel;
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

        Class<?> remoteModelClass = _proposalRatingTypeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalRatingTypeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalRatingTypeLocalServiceUtil.addProposalRatingType(this);
        } else {
            ProposalRatingTypeLocalServiceUtil.updateProposalRatingType(this);
        }
    }

    @Override
    public ProposalRatingType toEscapedModel() {
        return (ProposalRatingType) ProxyUtil.newProxyInstance(ProposalRatingType.class.getClassLoader(),
            new Class[] { ProposalRatingType.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalRatingTypeClp clone = new ProposalRatingTypeClp();

        clone.setId(getId());
        clone.setLabel(getLabel());
        clone.setDescription(getDescription());
        clone.setJudgeType(getJudgeType());
        clone.setIsActive(getIsActive());

        return clone;
    }

    @Override
    public int compareTo(ProposalRatingType proposalRatingType) {
        long primaryKey = proposalRatingType.getPrimaryKey();

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

        if (!(obj instanceof ProposalRatingTypeClp)) {
            return false;
        }

        ProposalRatingTypeClp proposalRatingType = (ProposalRatingTypeClp) obj;

        long primaryKey = proposalRatingType.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", label=");
        sb.append(getLabel());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", judgeType=");
        sb.append(getJudgeType());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalRatingType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>label</column-name><column-value><![CDATA[");
        sb.append(getLabel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>judgeType</column-name><column-value><![CDATA[");
        sb.append(getJudgeType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
