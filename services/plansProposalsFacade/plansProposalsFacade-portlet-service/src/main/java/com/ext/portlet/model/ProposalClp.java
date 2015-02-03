package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalLocalServiceUtil;

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


public class ProposalClp extends BaseModelImpl<Proposal> implements Proposal {
    private long _proposalId;
    private Date _createDate;
    private Date _updatedDate;
    private int _currentVersion;
    private long _authorId;
    private boolean _visible;
    private long _discussionId;
    private long _resultsDiscussionId;
    private long _judgeDiscussionId;
    private long _fellowDiscussionId;
    private long _advisorDiscussionId;
    private long _groupId;
    private BaseModel<?> _proposalRemoteModel;

    public ProposalClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return Proposal.class;
    }

    @Override
    public String getModelClassName() {
        return Proposal.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _proposalId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setProposalId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _proposalId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("proposalId", getProposalId());
        attributes.put("createDate", getCreateDate());
        attributes.put("updatedDate", getUpdatedDate());
        attributes.put("currentVersion", getCurrentVersion());
        attributes.put("authorId", getAuthorId());
        attributes.put("visible", getVisible());
        attributes.put("discussionId", getDiscussionId());
        attributes.put("resultsDiscussionId", getResultsDiscussionId());
        attributes.put("judgeDiscussionId", getJudgeDiscussionId());
        attributes.put("fellowDiscussionId", getFellowDiscussionId());
        attributes.put("advisorDiscussionId", getAdvisorDiscussionId());
        attributes.put("groupId", getGroupId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date updatedDate = (Date) attributes.get("updatedDate");

        if (updatedDate != null) {
            setUpdatedDate(updatedDate);
        }

        Integer currentVersion = (Integer) attributes.get("currentVersion");

        if (currentVersion != null) {
            setCurrentVersion(currentVersion);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }

        Boolean visible = (Boolean) attributes.get("visible");

        if (visible != null) {
            setVisible(visible);
        }

        Long discussionId = (Long) attributes.get("discussionId");

        if (discussionId != null) {
            setDiscussionId(discussionId);
        }

        Long resultsDiscussionId = (Long) attributes.get("resultsDiscussionId");

        if (resultsDiscussionId != null) {
            setResultsDiscussionId(resultsDiscussionId);
        }

        Long judgeDiscussionId = (Long) attributes.get("judgeDiscussionId");

        if (judgeDiscussionId != null) {
            setJudgeDiscussionId(judgeDiscussionId);
        }

        Long fellowDiscussionId = (Long) attributes.get("fellowDiscussionId");

        if (fellowDiscussionId != null) {
            setFellowDiscussionId(fellowDiscussionId);
        }

        Long advisorDiscussionId = (Long) attributes.get("advisorDiscussionId");

        if (advisorDiscussionId != null) {
            setAdvisorDiscussionId(advisorDiscussionId);
        }

        Long groupId = (Long) attributes.get("groupId");

        if (groupId != null) {
            setGroupId(groupId);
        }
    }

    @Override
    public long getProposalId() {
        return _proposalId;
    }

    @Override
    public void setProposalId(long proposalId) {
        _proposalId = proposalId;

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_proposalRemoteModel, proposalId);
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

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_proposalRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getUpdatedDate() {
        return _updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        _updatedDate = updatedDate;

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdatedDate", Date.class);

                method.invoke(_proposalRemoteModel, updatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCurrentVersion() {
        return _currentVersion;
    }

    @Override
    public void setCurrentVersion(int currentVersion) {
        _currentVersion = currentVersion;

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setCurrentVersion", int.class);

                method.invoke(_proposalRemoteModel, currentVersion);
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

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorId", long.class);

                method.invoke(_proposalRemoteModel, authorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getVisible() {
        return _visible;
    }

    @Override
    public boolean isVisible() {
        return _visible;
    }

    @Override
    public void setVisible(boolean visible) {
        _visible = visible;

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setVisible", boolean.class);

                method.invoke(_proposalRemoteModel, visible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getDiscussionId() {
        return _discussionId;
    }

    @Override
    public void setDiscussionId(long discussionId) {
        _discussionId = discussionId;

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscussionId", long.class);

                method.invoke(_proposalRemoteModel, discussionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getResultsDiscussionId() {
        return _resultsDiscussionId;
    }

    @Override
    public void setResultsDiscussionId(long resultsDiscussionId) {
        _resultsDiscussionId = resultsDiscussionId;

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setResultsDiscussionId",
                        long.class);

                method.invoke(_proposalRemoteModel, resultsDiscussionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getJudgeDiscussionId() {
        return _judgeDiscussionId;
    }

    @Override
    public void setJudgeDiscussionId(long judgeDiscussionId) {
        _judgeDiscussionId = judgeDiscussionId;

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setJudgeDiscussionId",
                        long.class);

                method.invoke(_proposalRemoteModel, judgeDiscussionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getFellowDiscussionId() {
        return _fellowDiscussionId;
    }

    @Override
    public void setFellowDiscussionId(long fellowDiscussionId) {
        _fellowDiscussionId = fellowDiscussionId;

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setFellowDiscussionId",
                        long.class);

                method.invoke(_proposalRemoteModel, fellowDiscussionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getAdvisorDiscussionId() {
        return _advisorDiscussionId;
    }

    @Override
    public void setAdvisorDiscussionId(long advisorDiscussionId) {
        _advisorDiscussionId = advisorDiscussionId;

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setAdvisorDiscussionId",
                        long.class);

                method.invoke(_proposalRemoteModel, advisorDiscussionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getGroupId() {
        return _groupId;
    }

    @Override
    public void setGroupId(long groupId) {
        _groupId = groupId;

        if (_proposalRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupId", long.class);

                method.invoke(_proposalRemoteModel, groupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalRemoteModel() {
        return _proposalRemoteModel;
    }

    public void setProposalRemoteModel(BaseModel<?> proposalRemoteModel) {
        _proposalRemoteModel = proposalRemoteModel;
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

        Class<?> remoteModelClass = _proposalRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalLocalServiceUtil.addProposal(this);
        } else {
            ProposalLocalServiceUtil.updateProposal(this);
        }
    }

    @Override
    public Proposal toEscapedModel() {
        return (Proposal) ProxyUtil.newProxyInstance(Proposal.class.getClassLoader(),
            new Class[] { Proposal.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalClp clone = new ProposalClp();

        clone.setProposalId(getProposalId());
        clone.setCreateDate(getCreateDate());
        clone.setUpdatedDate(getUpdatedDate());
        clone.setCurrentVersion(getCurrentVersion());
        clone.setAuthorId(getAuthorId());
        clone.setVisible(getVisible());
        clone.setDiscussionId(getDiscussionId());
        clone.setResultsDiscussionId(getResultsDiscussionId());
        clone.setJudgeDiscussionId(getJudgeDiscussionId());
        clone.setFellowDiscussionId(getFellowDiscussionId());
        clone.setAdvisorDiscussionId(getAdvisorDiscussionId());
        clone.setGroupId(getGroupId());

        return clone;
    }

    @Override
    public int compareTo(Proposal proposal) {
        long primaryKey = proposal.getPrimaryKey();

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

        if (!(obj instanceof ProposalClp)) {
            return false;
        }

        ProposalClp proposal = (ProposalClp) obj;

        long primaryKey = proposal.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{proposalId=");
        sb.append(getProposalId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", updatedDate=");
        sb.append(getUpdatedDate());
        sb.append(", currentVersion=");
        sb.append(getCurrentVersion());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", visible=");
        sb.append(getVisible());
        sb.append(", discussionId=");
        sb.append(getDiscussionId());
        sb.append(", resultsDiscussionId=");
        sb.append(getResultsDiscussionId());
        sb.append(", judgeDiscussionId=");
        sb.append(getJudgeDiscussionId());
        sb.append(", fellowDiscussionId=");
        sb.append(getFellowDiscussionId());
        sb.append(", advisorDiscussionId=");
        sb.append(getAdvisorDiscussionId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.Proposal");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updatedDate</column-name><column-value><![CDATA[");
        sb.append(getUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>currentVersion</column-name><column-value><![CDATA[");
        sb.append(getCurrentVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visible</column-name><column-value><![CDATA[");
        sb.append(getVisible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discussionId</column-name><column-value><![CDATA[");
        sb.append(getDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resultsDiscussionId</column-name><column-value><![CDATA[");
        sb.append(getResultsDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>judgeDiscussionId</column-name><column-value><![CDATA[");
        sb.append(getJudgeDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>fellowDiscussionId</column-name><column-value><![CDATA[");
        sb.append(getFellowDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>advisorDiscussionId</column-name><column-value><![CDATA[");
        sb.append(getAdvisorDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
