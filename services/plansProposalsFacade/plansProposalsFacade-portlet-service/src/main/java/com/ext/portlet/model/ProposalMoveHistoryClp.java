package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalMoveHistoryLocalServiceUtil;

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


public class ProposalMoveHistoryClp extends BaseModelImpl<ProposalMoveHistory>
    implements ProposalMoveHistory {
    private long _id_;
    private long _sourceProposalId;
    private long _sourceContestId;
    private long _sourcePhaseId;
    private long _targetProposalId;
    private long _targetContestId;
    private long _targetPhaseId;
    private long _movingUserId;
    private String _movingUserUuid;
    private Date _moveDate;
    private String _moveType;
    private BaseModel<?> _proposalMoveHistoryRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ProposalMoveHistoryClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalMoveHistory.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalMoveHistory.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _id_;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setId_(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _id_;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id_", getId_());
        attributes.put("sourceProposalId", getSourceProposalId());
        attributes.put("sourceContestId", getSourceContestId());
        attributes.put("sourcePhaseId", getSourcePhaseId());
        attributes.put("targetProposalId", getTargetProposalId());
        attributes.put("targetContestId", getTargetContestId());
        attributes.put("targetPhaseId", getTargetPhaseId());
        attributes.put("movingUserId", getMovingUserId());
        attributes.put("moveDate", getMoveDate());
        attributes.put("moveType", getMoveType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id_ = (Long) attributes.get("id_");

        if (id_ != null) {
            setId_(id_);
        }

        Long sourceProposalId = (Long) attributes.get("sourceProposalId");

        if (sourceProposalId != null) {
            setSourceProposalId(sourceProposalId);
        }

        Long sourceContestId = (Long) attributes.get("sourceContestId");

        if (sourceContestId != null) {
            setSourceContestId(sourceContestId);
        }

        Long sourcePhaseId = (Long) attributes.get("sourcePhaseId");

        if (sourcePhaseId != null) {
            setSourcePhaseId(sourcePhaseId);
        }

        Long targetProposalId = (Long) attributes.get("targetProposalId");

        if (targetProposalId != null) {
            setTargetProposalId(targetProposalId);
        }

        Long targetContestId = (Long) attributes.get("targetContestId");

        if (targetContestId != null) {
            setTargetContestId(targetContestId);
        }

        Long targetPhaseId = (Long) attributes.get("targetPhaseId");

        if (targetPhaseId != null) {
            setTargetPhaseId(targetPhaseId);
        }

        Long movingUserId = (Long) attributes.get("movingUserId");

        if (movingUserId != null) {
            setMovingUserId(movingUserId);
        }

        Date moveDate = (Date) attributes.get("moveDate");

        if (moveDate != null) {
            setMoveDate(moveDate);
        }

        String moveType = (String) attributes.get("moveType");

        if (moveType != null) {
            setMoveType(moveType);
        }
    }

    @Override
    public long getId_() {
        return _id_;
    }

    @Override
    public void setId_(long id_) {
        _id_ = id_;

        if (_proposalMoveHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _proposalMoveHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setId_", long.class);

                method.invoke(_proposalMoveHistoryRemoteModel, id_);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getSourceProposalId() {
        return _sourceProposalId;
    }

    @Override
    public void setSourceProposalId(long sourceProposalId) {
        _sourceProposalId = sourceProposalId;

        if (_proposalMoveHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _proposalMoveHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setSourceProposalId",
                        long.class);

                method.invoke(_proposalMoveHistoryRemoteModel, sourceProposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getSourceContestId() {
        return _sourceContestId;
    }

    @Override
    public void setSourceContestId(long sourceContestId) {
        _sourceContestId = sourceContestId;

        if (_proposalMoveHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _proposalMoveHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setSourceContestId", long.class);

                method.invoke(_proposalMoveHistoryRemoteModel, sourceContestId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getSourcePhaseId() {
        return _sourcePhaseId;
    }

    @Override
    public void setSourcePhaseId(long sourcePhaseId) {
        _sourcePhaseId = sourcePhaseId;

        if (_proposalMoveHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _proposalMoveHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setSourcePhaseId", long.class);

                method.invoke(_proposalMoveHistoryRemoteModel, sourcePhaseId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getTargetProposalId() {
        return _targetProposalId;
    }

    @Override
    public void setTargetProposalId(long targetProposalId) {
        _targetProposalId = targetProposalId;

        if (_proposalMoveHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _proposalMoveHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setTargetProposalId",
                        long.class);

                method.invoke(_proposalMoveHistoryRemoteModel, targetProposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getTargetContestId() {
        return _targetContestId;
    }

    @Override
    public void setTargetContestId(long targetContestId) {
        _targetContestId = targetContestId;

        if (_proposalMoveHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _proposalMoveHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setTargetContestId", long.class);

                method.invoke(_proposalMoveHistoryRemoteModel, targetContestId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getTargetPhaseId() {
        return _targetPhaseId;
    }

    @Override
    public void setTargetPhaseId(long targetPhaseId) {
        _targetPhaseId = targetPhaseId;

        if (_proposalMoveHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _proposalMoveHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setTargetPhaseId", long.class);

                method.invoke(_proposalMoveHistoryRemoteModel, targetPhaseId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getMovingUserId() {
        return _movingUserId;
    }

    @Override
    public void setMovingUserId(long movingUserId) {
        _movingUserId = movingUserId;

        if (_proposalMoveHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _proposalMoveHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setMovingUserId", long.class);

                method.invoke(_proposalMoveHistoryRemoteModel, movingUserId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMovingUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getMovingUserId(), "uuid",
            _movingUserUuid);
    }

    @Override
    public void setMovingUserUuid(String movingUserUuid) {
        _movingUserUuid = movingUserUuid;
    }

    @Override
    public Date getMoveDate() {
        return _moveDate;
    }

    @Override
    public void setMoveDate(Date moveDate) {
        _moveDate = moveDate;

        if (_proposalMoveHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _proposalMoveHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setMoveDate", Date.class);

                method.invoke(_proposalMoveHistoryRemoteModel, moveDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMoveType() {
        return _moveType;
    }

    @Override
    public void setMoveType(String moveType) {
        _moveType = moveType;

        if (_proposalMoveHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _proposalMoveHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setMoveType", String.class);

                method.invoke(_proposalMoveHistoryRemoteModel, moveType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalMoveHistoryRemoteModel() {
        return _proposalMoveHistoryRemoteModel;
    }

    public void setProposalMoveHistoryRemoteModel(
        BaseModel<?> proposalMoveHistoryRemoteModel) {
        _proposalMoveHistoryRemoteModel = proposalMoveHistoryRemoteModel;
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

        Class<?> remoteModelClass = _proposalMoveHistoryRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalMoveHistoryRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalMoveHistoryLocalServiceUtil.addProposalMoveHistory(this);
        } else {
            ProposalMoveHistoryLocalServiceUtil.updateProposalMoveHistory(this);
        }
    }

    @Override
    public ProposalMoveHistory toEscapedModel() {
        return (ProposalMoveHistory) ProxyUtil.newProxyInstance(ProposalMoveHistory.class.getClassLoader(),
            new Class[] { ProposalMoveHistory.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalMoveHistoryClp clone = new ProposalMoveHistoryClp();

        clone.setId_(getId_());
        clone.setSourceProposalId(getSourceProposalId());
        clone.setSourceContestId(getSourceContestId());
        clone.setSourcePhaseId(getSourcePhaseId());
        clone.setTargetProposalId(getTargetProposalId());
        clone.setTargetContestId(getTargetContestId());
        clone.setTargetPhaseId(getTargetPhaseId());
        clone.setMovingUserId(getMovingUserId());
        clone.setMoveDate(getMoveDate());
        clone.setMoveType(getMoveType());

        return clone;
    }

    @Override
    public int compareTo(ProposalMoveHistory proposalMoveHistory) {
        long primaryKey = proposalMoveHistory.getPrimaryKey();

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

        if (!(obj instanceof ProposalMoveHistoryClp)) {
            return false;
        }

        ProposalMoveHistoryClp proposalMoveHistory = (ProposalMoveHistoryClp) obj;

        long primaryKey = proposalMoveHistory.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{id_=");
        sb.append(getId_());
        sb.append(", sourceProposalId=");
        sb.append(getSourceProposalId());
        sb.append(", sourceContestId=");
        sb.append(getSourceContestId());
        sb.append(", sourcePhaseId=");
        sb.append(getSourcePhaseId());
        sb.append(", targetProposalId=");
        sb.append(getTargetProposalId());
        sb.append(", targetContestId=");
        sb.append(getTargetContestId());
        sb.append(", targetPhaseId=");
        sb.append(getTargetPhaseId());
        sb.append(", movingUserId=");
        sb.append(getMovingUserId());
        sb.append(", moveDate=");
        sb.append(getMoveDate());
        sb.append(", moveType=");
        sb.append(getMoveType());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalMoveHistory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id_</column-name><column-value><![CDATA[");
        sb.append(getId_());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sourceProposalId</column-name><column-value><![CDATA[");
        sb.append(getSourceProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sourceContestId</column-name><column-value><![CDATA[");
        sb.append(getSourceContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sourcePhaseId</column-name><column-value><![CDATA[");
        sb.append(getSourcePhaseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>targetProposalId</column-name><column-value><![CDATA[");
        sb.append(getTargetProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>targetContestId</column-name><column-value><![CDATA[");
        sb.append(getTargetContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>targetPhaseId</column-name><column-value><![CDATA[");
        sb.append(getTargetPhaseId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>movingUserId</column-name><column-value><![CDATA[");
        sb.append(getMovingUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moveDate</column-name><column-value><![CDATA[");
        sb.append(getMoveDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>moveType</column-name><column-value><![CDATA[");
        sb.append(getMoveType());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
