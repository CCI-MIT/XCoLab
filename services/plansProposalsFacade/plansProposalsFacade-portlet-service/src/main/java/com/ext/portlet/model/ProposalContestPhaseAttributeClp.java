package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;

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


public class ProposalContestPhaseAttributeClp extends BaseModelImpl<ProposalContestPhaseAttribute>
    implements ProposalContestPhaseAttribute {
    private long _id;
    private long _proposalId;
    private long _contestPhaseId;
    private String _name;
    private long _additionalId;
    private long _numericValue;
    private String _stringValue;
    private double _realValue;
    private BaseModel<?> _proposalContestPhaseAttributeRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ProposalContestPhaseAttributeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalContestPhaseAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalContestPhaseAttribute.class.getName();
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
        attributes.put("proposalId", getProposalId());
        attributes.put("contestPhaseId", getContestPhaseId());
        attributes.put("name", getName());
        attributes.put("additionalId", getAdditionalId());
        attributes.put("numericValue", getNumericValue());
        attributes.put("stringValue", getStringValue());
        attributes.put("realValue", getRealValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Long contestPhaseId = (Long) attributes.get("contestPhaseId");

        if (contestPhaseId != null) {
            setContestPhaseId(contestPhaseId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Long additionalId = (Long) attributes.get("additionalId");

        if (additionalId != null) {
            setAdditionalId(additionalId);
        }

        Long numericValue = (Long) attributes.get("numericValue");

        if (numericValue != null) {
            setNumericValue(numericValue);
        }

        String stringValue = (String) attributes.get("stringValue");

        if (stringValue != null) {
            setStringValue(stringValue);
        }

        Double realValue = (Double) attributes.get("realValue");

        if (realValue != null) {
            setRealValue(realValue);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_proposalContestPhaseAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalContestPhaseAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_proposalContestPhaseAttributeRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getProposalId() {
        return _proposalId;
    }

    @Override
    public void setProposalId(long proposalId) {
        _proposalId = proposalId;

        if (_proposalContestPhaseAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalContestPhaseAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_proposalContestPhaseAttributeRemoteModel,
                    proposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getContestPhaseId() {
        return _contestPhaseId;
    }

    @Override
    public void setContestPhaseId(long contestPhaseId) {
        _contestPhaseId = contestPhaseId;

        if (_proposalContestPhaseAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalContestPhaseAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhaseId", long.class);

                method.invoke(_proposalContestPhaseAttributeRemoteModel,
                    contestPhaseId);
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

        if (_proposalContestPhaseAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalContestPhaseAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_proposalContestPhaseAttributeRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getAdditionalId() {
        return _additionalId;
    }

    @Override
    public void setAdditionalId(long additionalId) {
        _additionalId = additionalId;

        if (_proposalContestPhaseAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalContestPhaseAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setAdditionalId", long.class);

                method.invoke(_proposalContestPhaseAttributeRemoteModel,
                    additionalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getNumericValue() {
        return _numericValue;
    }

    @Override
    public void setNumericValue(long numericValue) {
        _numericValue = numericValue;

        if (_proposalContestPhaseAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalContestPhaseAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setNumericValue", long.class);

                method.invoke(_proposalContestPhaseAttributeRemoteModel,
                    numericValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStringValue() {
        return _stringValue;
    }

    @Override
    public void setStringValue(String stringValue) {
        _stringValue = stringValue;

        if (_proposalContestPhaseAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalContestPhaseAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setStringValue", String.class);

                method.invoke(_proposalContestPhaseAttributeRemoteModel,
                    stringValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getRealValue() {
        return _realValue;
    }

    @Override
    public void setRealValue(double realValue) {
        _realValue = realValue;

        if (_proposalContestPhaseAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalContestPhaseAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setRealValue", double.class);

                method.invoke(_proposalContestPhaseAttributeRemoteModel,
                    realValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalContestPhaseAttributeRemoteModel() {
        return _proposalContestPhaseAttributeRemoteModel;
    }

    public void setProposalContestPhaseAttributeRemoteModel(
        BaseModel<?> proposalContestPhaseAttributeRemoteModel) {
        _proposalContestPhaseAttributeRemoteModel = proposalContestPhaseAttributeRemoteModel;
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

        Class<?> remoteModelClass = _proposalContestPhaseAttributeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalContestPhaseAttributeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalContestPhaseAttributeLocalServiceUtil.addProposalContestPhaseAttribute(this);
        } else {
            ProposalContestPhaseAttributeLocalServiceUtil.updateProposalContestPhaseAttribute(this);
        }
    }

    @Override
    public ProposalContestPhaseAttribute toEscapedModel() {
        return (ProposalContestPhaseAttribute) ProxyUtil.newProxyInstance(ProposalContestPhaseAttribute.class.getClassLoader(),
            new Class[] { ProposalContestPhaseAttribute.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalContestPhaseAttributeClp clone = new ProposalContestPhaseAttributeClp();

        clone.setId(getId());
        clone.setProposalId(getProposalId());
        clone.setContestPhaseId(getContestPhaseId());
        clone.setName(getName());
        clone.setAdditionalId(getAdditionalId());
        clone.setNumericValue(getNumericValue());
        clone.setStringValue(getStringValue());
        clone.setRealValue(getRealValue());

        return clone;
    }

    @Override
    public int compareTo(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        long primaryKey = proposalContestPhaseAttribute.getPrimaryKey();

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

        if (!(obj instanceof ProposalContestPhaseAttributeClp)) {
            return false;
        }

        ProposalContestPhaseAttributeClp proposalContestPhaseAttribute = (ProposalContestPhaseAttributeClp) obj;

        long primaryKey = proposalContestPhaseAttribute.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", proposalId=");
        sb.append(getProposalId());
        sb.append(", contestPhaseId=");
        sb.append(getContestPhaseId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", additionalId=");
        sb.append(getAdditionalId());
        sb.append(", numericValue=");
        sb.append(getNumericValue());
        sb.append(", stringValue=");
        sb.append(getStringValue());
        sb.append(", realValue=");
        sb.append(getRealValue());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalContestPhaseAttribute");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestPhaseId</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>additionalId</column-name><column-value><![CDATA[");
        sb.append(getAdditionalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>numericValue</column-name><column-value><![CDATA[");
        sb.append(getNumericValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>stringValue</column-name><column-value><![CDATA[");
        sb.append(getStringValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>realValue</column-name><column-value><![CDATA[");
        sb.append(getRealValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
