package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanVotePK;

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


public class PlanVoteClp extends BaseModelImpl<PlanVote> implements PlanVote {
    private long _userId;
    private String _userUuid;
    private long _contestId;
    private long _planId;
    private Date _createDate;
    private BaseModel<?> _planVoteRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PlanVoteClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanVote.class;
    }

    @Override
    public String getModelClassName() {
        return PlanVote.class.getName();
    }

    @Override
    public PlanVotePK getPrimaryKey() {
        return new PlanVotePK(_userId, _contestId);
    }

    @Override
    public void setPrimaryKey(PlanVotePK primaryKey) {
        setUserId(primaryKey.userId);
        setContestId(primaryKey.contestId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new PlanVotePK(_userId, _contestId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanVotePK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("contestId", getContestId());
        attributes.put("planId", getPlanId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long contestId = (Long) attributes.get("contestId");

        if (contestId != null) {
            setContestId(contestId);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_planVoteRemoteModel != null) {
            try {
                Class<?> clazz = _planVoteRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_planVoteRemoteModel, userId);
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
    public long getContestId() {
        return _contestId;
    }

    @Override
    public void setContestId(long contestId) {
        _contestId = contestId;

        if (_planVoteRemoteModel != null) {
            try {
                Class<?> clazz = _planVoteRemoteModel.getClass();

                Method method = clazz.getMethod("setContestId", long.class);

                method.invoke(_planVoteRemoteModel, contestId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanId() {
        return _planId;
    }

    @Override
    public void setPlanId(long planId) {
        _planId = planId;

        if (_planVoteRemoteModel != null) {
            try {
                Class<?> clazz = _planVoteRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planVoteRemoteModel, planId);
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

        if (_planVoteRemoteModel != null) {
            try {
                Class<?> clazz = _planVoteRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_planVoteRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanVoteRemoteModel() {
        return _planVoteRemoteModel;
    }

    public void setPlanVoteRemoteModel(BaseModel<?> planVoteRemoteModel) {
        _planVoteRemoteModel = planVoteRemoteModel;
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

        Class<?> remoteModelClass = _planVoteRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planVoteRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanVoteLocalServiceUtil.addPlanVote(this);
        } else {
            PlanVoteLocalServiceUtil.updatePlanVote(this);
        }
    }

    @Override
    public PlanVote toEscapedModel() {
        return (PlanVote) ProxyUtil.newProxyInstance(PlanVote.class.getClassLoader(),
            new Class[] { PlanVote.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanVoteClp clone = new PlanVoteClp();

        clone.setUserId(getUserId());
        clone.setContestId(getContestId());
        clone.setPlanId(getPlanId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    @Override
    public int compareTo(PlanVote planVote) {
        PlanVotePK primaryKey = planVote.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanVoteClp)) {
            return false;
        }

        PlanVoteClp planVote = (PlanVoteClp) obj;

        PlanVotePK primaryKey = planVote.getPrimaryKey();

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

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", contestId=");
        sb.append(getContestId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanVote");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestId</column-name><column-value><![CDATA[");
        sb.append(getContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
