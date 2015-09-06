package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalVoteLocalServiceUtil;
import com.ext.portlet.service.persistence.ProposalVotePK;

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


public class ProposalVoteClp extends BaseModelImpl<ProposalVote>
    implements ProposalVote {
    private long _proposalId;
    private long _contestPhaseId;
    private long _userId;
    private String _userUuid;
    private Date _createDate;
    private BaseModel<?> _proposalVoteRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ProposalVoteClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalVote.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalVote.class.getName();
    }

    @Override
    public ProposalVotePK getPrimaryKey() {
        return new ProposalVotePK(_contestPhaseId, _userId);
    }

    @Override
    public void setPrimaryKey(ProposalVotePK primaryKey) {
        setContestPhaseId(primaryKey.contestPhaseId);
        setUserId(primaryKey.userId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ProposalVotePK(_contestPhaseId, _userId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ProposalVotePK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("contestPhaseId", getContestPhaseId());
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

        Long contestPhaseId = (Long) attributes.get("contestPhaseId");

        if (contestPhaseId != null) {
            setContestPhaseId(contestPhaseId);
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

        if (_proposalVoteRemoteModel != null) {
            try {
                Class<?> clazz = _proposalVoteRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_proposalVoteRemoteModel, proposalId);
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

        if (_proposalVoteRemoteModel != null) {
            try {
                Class<?> clazz = _proposalVoteRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhaseId", long.class);

                method.invoke(_proposalVoteRemoteModel, contestPhaseId);
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

        if (_proposalVoteRemoteModel != null) {
            try {
                Class<?> clazz = _proposalVoteRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_proposalVoteRemoteModel, userId);
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

        if (_proposalVoteRemoteModel != null) {
            try {
                Class<?> clazz = _proposalVoteRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_proposalVoteRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalVoteRemoteModel() {
        return _proposalVoteRemoteModel;
    }

    public void setProposalVoteRemoteModel(BaseModel<?> proposalVoteRemoteModel) {
        _proposalVoteRemoteModel = proposalVoteRemoteModel;
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

        Class<?> remoteModelClass = _proposalVoteRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalVoteRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalVoteLocalServiceUtil.addProposalVote(this);
        } else {
            ProposalVoteLocalServiceUtil.updateProposalVote(this);
        }
    }

    @Override
    public ProposalVote toEscapedModel() {
        return (ProposalVote) ProxyUtil.newProxyInstance(ProposalVote.class.getClassLoader(),
            new Class[] { ProposalVote.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalVoteClp clone = new ProposalVoteClp();

        clone.setProposalId(getProposalId());
        clone.setContestPhaseId(getContestPhaseId());
        clone.setUserId(getUserId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    @Override
    public int compareTo(ProposalVote proposalVote) {
        ProposalVotePK primaryKey = proposalVote.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalVoteClp)) {
            return false;
        }

        ProposalVoteClp proposalVote = (ProposalVoteClp) obj;

        ProposalVotePK primaryKey = proposalVote.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{proposalId=");
        sb.append(getProposalId());
        sb.append(", contestPhaseId=");
        sb.append(getContestPhaseId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalVote");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestPhaseId</column-name><column-value><![CDATA[");
        sb.append(getContestPhaseId());
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
