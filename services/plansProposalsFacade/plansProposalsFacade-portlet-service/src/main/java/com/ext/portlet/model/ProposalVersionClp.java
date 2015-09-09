package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.ext.portlet.service.persistence.ProposalVersionPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ProposalVersionClp extends BaseModelImpl<ProposalVersion>
    implements ProposalVersion {
    private long _proposalId;
    private int _version;
    private long _authorId;
    private Date _createDate;
    private String _updateType;
    private long _updateAdditionalId;
    private BaseModel<?> _proposalVersionRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ProposalVersionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalVersion.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalVersion.class.getName();
    }

    @Override
    public ProposalVersionPK getPrimaryKey() {
        return new ProposalVersionPK(_proposalId, _version);
    }

    @Override
    public void setPrimaryKey(ProposalVersionPK primaryKey) {
        setProposalId(primaryKey.proposalId);
        setVersion(primaryKey.version);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ProposalVersionPK(_proposalId, _version);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ProposalVersionPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("version", getVersion());
        attributes.put("authorId", getAuthorId());
        attributes.put("createDate", getCreateDate());
        attributes.put("updateType", getUpdateType());
        attributes.put("updateAdditionalId", getUpdateAdditionalId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Integer version = (Integer) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        String updateType = (String) attributes.get("updateType");

        if (updateType != null) {
            setUpdateType(updateType);
        }

        Long updateAdditionalId = (Long) attributes.get("updateAdditionalId");

        if (updateAdditionalId != null) {
            setUpdateAdditionalId(updateAdditionalId);
        }
    }

    @Override
    public long getProposalId() {
        return _proposalId;
    }

    @Override
    public void setProposalId(long proposalId) {
        _proposalId = proposalId;

        if (_proposalVersionRemoteModel != null) {
            try {
                Class<?> clazz = _proposalVersionRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_proposalVersionRemoteModel, proposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getVersion() {
        return _version;
    }

    @Override
    public void setVersion(int version) {
        _version = version;

        if (_proposalVersionRemoteModel != null) {
            try {
                Class<?> clazz = _proposalVersionRemoteModel.getClass();

                Method method = clazz.getMethod("setVersion", int.class);

                method.invoke(_proposalVersionRemoteModel, version);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getAuthorId() {
        return _authorId;
    }

    @Override
    public void setAuthorId(long authorId) {
        _authorId = authorId;

        if (_proposalVersionRemoteModel != null) {
            try {
                Class<?> clazz = _proposalVersionRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorId", long.class);

                method.invoke(_proposalVersionRemoteModel, authorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _createDate = createDate;

        if (_proposalVersionRemoteModel != null) {
            try {
                Class<?> clazz = _proposalVersionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_proposalVersionRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUpdateType() {
        return _updateType;
    }

    @Override
    public void setUpdateType(String updateType) {
        _updateType = updateType;

        if (_proposalVersionRemoteModel != null) {
            try {
                Class<?> clazz = _proposalVersionRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdateType", String.class);

                method.invoke(_proposalVersionRemoteModel, updateType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUpdateAdditionalId() {
        return _updateAdditionalId;
    }

    @Override
    public void setUpdateAdditionalId(long updateAdditionalId) {
        _updateAdditionalId = updateAdditionalId;

        if (_proposalVersionRemoteModel != null) {
            try {
                Class<?> clazz = _proposalVersionRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdateAdditionalId",
                        long.class);

                method.invoke(_proposalVersionRemoteModel, updateAdditionalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalVersionRemoteModel() {
        return _proposalVersionRemoteModel;
    }

    public void setProposalVersionRemoteModel(
        BaseModel<?> proposalVersionRemoteModel) {
        _proposalVersionRemoteModel = proposalVersionRemoteModel;
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

        Class<?> remoteModelClass = _proposalVersionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalVersionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalVersionLocalServiceUtil.addProposalVersion(this);
        } else {
            ProposalVersionLocalServiceUtil.updateProposalVersion(this);
        }
    }

    @Override
    public ProposalVersion toEscapedModel() {
        return (ProposalVersion) ProxyUtil.newProxyInstance(ProposalVersion.class.getClassLoader(),
            new Class[] { ProposalVersion.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalVersionClp clone = new ProposalVersionClp();

        clone.setProposalId(getProposalId());
        clone.setVersion(getVersion());
        clone.setAuthorId(getAuthorId());
        clone.setCreateDate(getCreateDate());
        clone.setUpdateType(getUpdateType());
        clone.setUpdateAdditionalId(getUpdateAdditionalId());

        return clone;
    }

    @Override
    public int compareTo(ProposalVersion proposalVersion) {
        int value = 0;

        if (getVersion() < proposalVersion.getVersion()) {
            value = -1;
        } else if (getVersion() > proposalVersion.getVersion()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalVersionClp)) {
            return false;
        }

        ProposalVersionClp proposalVersion = (ProposalVersionClp) obj;

        ProposalVersionPK primaryKey = proposalVersion.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{proposalId=");
        sb.append(getProposalId());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", updateType=");
        sb.append(getUpdateType());
        sb.append(", updateAdditionalId=");
        sb.append(getUpdateAdditionalId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalVersion");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateType</column-name><column-value><![CDATA[");
        sb.append(getUpdateType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAdditionalId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAdditionalId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
