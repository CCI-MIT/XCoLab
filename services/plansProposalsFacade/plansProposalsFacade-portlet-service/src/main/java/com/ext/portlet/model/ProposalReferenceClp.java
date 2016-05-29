package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalReferenceLocalServiceUtil;
import com.ext.portlet.service.persistence.ProposalReferencePK;

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


public class ProposalReferenceClp extends BaseModelImpl<ProposalReference>
    implements ProposalReference {
    private long _proposalId;
    private long _subProposalId;
    private long _sectionAttributeId;
    private BaseModel<?> _proposalReferenceRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ProposalReferenceClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalReference.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalReference.class.getName();
    }

    @Override
    public ProposalReferencePK getPrimaryKey() {
        return new ProposalReferencePK(_proposalId, _subProposalId);
    }

    @Override
    public void setPrimaryKey(ProposalReferencePK primaryKey) {
        setProposalId(primaryKey.proposalId);
        setSubProposalId(primaryKey.subProposalId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ProposalReferencePK(_proposalId, _subProposalId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ProposalReferencePK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("subProposalId", getSubProposalId());
        attributes.put("sectionAttributeId", getSectionAttributeId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Long subProposalId = (Long) attributes.get("subProposalId");

        if (subProposalId != null) {
            setSubProposalId(subProposalId);
        }

        Long sectionAttributeId = (Long) attributes.get("sectionAttributeId");

        if (sectionAttributeId != null) {
            setSectionAttributeId(sectionAttributeId);
        }
    }

    @Override
    public long getProposalId() {
        return _proposalId;
    }

    @Override
    public void setProposalId(long proposalId) {
        _proposalId = proposalId;

        if (_proposalReferenceRemoteModel != null) {
            try {
                Class<?> clazz = _proposalReferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_proposalReferenceRemoteModel, proposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getSubProposalId() {
        return _subProposalId;
    }

    @Override
    public void setSubProposalId(long subProposalId) {
        _subProposalId = subProposalId;

        if (_proposalReferenceRemoteModel != null) {
            try {
                Class<?> clazz = _proposalReferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setSubProposalId", long.class);

                method.invoke(_proposalReferenceRemoteModel, subProposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getSectionAttributeId() {
        return _sectionAttributeId;
    }

    @Override
    public void setSectionAttributeId(long sectionAttributeId) {
        _sectionAttributeId = sectionAttributeId;

        if (_proposalReferenceRemoteModel != null) {
            try {
                Class<?> clazz = _proposalReferenceRemoteModel.getClass();

                Method method = clazz.getMethod("setSectionAttributeId",
                        long.class);

                method.invoke(_proposalReferenceRemoteModel, sectionAttributeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalReferenceRemoteModel() {
        return _proposalReferenceRemoteModel;
    }

    public void setProposalReferenceRemoteModel(
        BaseModel<?> proposalReferenceRemoteModel) {
        _proposalReferenceRemoteModel = proposalReferenceRemoteModel;
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

        Class<?> remoteModelClass = _proposalReferenceRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalReferenceRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalReferenceLocalServiceUtil.addProposalReference(this);
        } else {
            ProposalReferenceLocalServiceUtil.updateProposalReference(this);
        }
    }

    @Override
    public ProposalReference toEscapedModel() {
        return (ProposalReference) ProxyUtil.newProxyInstance(ProposalReference.class.getClassLoader(),
            new Class[] { ProposalReference.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalReferenceClp clone = new ProposalReferenceClp();

        clone.setProposalId(getProposalId());
        clone.setSubProposalId(getSubProposalId());
        clone.setSectionAttributeId(getSectionAttributeId());

        return clone;
    }

    @Override
    public int compareTo(ProposalReference proposalReference) {
        ProposalReferencePK primaryKey = proposalReference.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalReferenceClp)) {
            return false;
        }

        ProposalReferenceClp proposalReference = (ProposalReferenceClp) obj;

        ProposalReferencePK primaryKey = proposalReference.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
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
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{proposalId=");
        sb.append(getProposalId());
        sb.append(", subProposalId=");
        sb.append(getSubProposalId());
        sb.append(", sectionAttributeId=");
        sb.append(getSectionAttributeId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalReference");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subProposalId</column-name><column-value><![CDATA[");
        sb.append(getSubProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sectionAttributeId</column-name><column-value><![CDATA[");
        sb.append(getSectionAttributeId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
