package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalSupporterLocalServiceUtil;
import com.ext.portlet.service.persistence.ProposalSupporterPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ProposalSupporterClp extends BaseModelImpl<ProposalSupporter>
    implements ProposalSupporter {
    private long _proposalId;
    private long _userId;
    private String _userUuid;
    private Date _createDate;
    private BaseModel<?> _proposalSupporterRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ProposalSupporterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalSupporter.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalSupporter.class.getName();
    }

    @Override
    public ProposalSupporterPK getPrimaryKey() {
        return new ProposalSupporterPK(_proposalId, _userId);
    }

    @Override
    public void setPrimaryKey(ProposalSupporterPK primaryKey) {
        setProposalId(primaryKey.proposalId);
        setUserId(primaryKey.userId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ProposalSupporterPK(_proposalId, _userId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ProposalSupporterPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("userId", getUserId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    @Override
    public long getProposalId() {
        return _proposalId;
    }

    @Override
    public void setProposalId(long proposalId) {
        _proposalId = proposalId;

        if (_proposalSupporterRemoteModel != null) {
            try {
                Class<?> clazz = _proposalSupporterRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_proposalSupporterRemoteModel, proposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_proposalSupporterRemoteModel != null) {
            try {
                Class<?> clazz = _proposalSupporterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_proposalSupporterRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    @Override
    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    @Override
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _createDate = createDate;

        if (_proposalSupporterRemoteModel != null) {
            try {
                Class<?> clazz = _proposalSupporterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_proposalSupporterRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalSupporterRemoteModel() {
        return _proposalSupporterRemoteModel;
    }

    public void setProposalSupporterRemoteModel(
        BaseModel<?> proposalSupporterRemoteModel) {
        _proposalSupporterRemoteModel = proposalSupporterRemoteModel;
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

        Class<?> remoteModelClass = _proposalSupporterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalSupporterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalSupporterLocalServiceUtil.addProposalSupporter(this);
        } else {
            ProposalSupporterLocalServiceUtil.updateProposalSupporter(this);
        }
    }

    @Override
    public ProposalSupporter toEscapedModel() {
        return (ProposalSupporter) ProxyUtil.newProxyInstance(ProposalSupporter.class.getClassLoader(),
            new Class[] { ProposalSupporter.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalSupporterClp clone = new ProposalSupporterClp();

        clone.setProposalId(getProposalId());
        clone.setUserId(getUserId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    @Override
    public int compareTo(ProposalSupporter proposalSupporter) {
        ProposalSupporterPK primaryKey = proposalSupporter.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalSupporterClp)) {
            return false;
        }

        ProposalSupporterClp proposalSupporter = (ProposalSupporterClp) obj;

        ProposalSupporterPK primaryKey = proposalSupporter.getPrimaryKey();

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
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalSupporter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
