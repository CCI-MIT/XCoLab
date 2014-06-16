package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class ProposalRatingClp extends BaseModelImpl<ProposalRating>
    implements ProposalRating {
    private long _id;
    private long _proposalId;
    private long _contestPhaseId;
    private long _userId;
    private String _userUuid;
    private int _ratingType;
    private long _rating;
    private String _comment;
    private String _otherDataString;
    private BaseModel<?> _proposalRatingRemoteModel;

    public ProposalRatingClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalRating.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalRating.class.getName();
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
        attributes.put("userId", getUserId());
        attributes.put("ratingType", getRatingType());
        attributes.put("rating", getRating());
        attributes.put("comment", getComment());
        attributes.put("otherDataString", getOtherDataString());

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

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer ratingType = (Integer) attributes.get("ratingType");

        if (ratingType != null) {
            setRatingType(ratingType);
        }

        Long rating = (Long) attributes.get("rating");

        if (rating != null) {
            setRating(rating);
        }

        String comment = (String) attributes.get("comment");

        if (comment != null) {
            setComment(comment);
        }

        String otherDataString = (String) attributes.get("otherDataString");

        if (otherDataString != null) {
            setOtherDataString(otherDataString);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_proposalRatingRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_proposalRatingRemoteModel, id);
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

        if (_proposalRatingRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_proposalRatingRemoteModel, proposalId);
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

        if (_proposalRatingRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPhaseId", long.class);

                method.invoke(_proposalRatingRemoteModel, contestPhaseId);
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

        if (_proposalRatingRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_proposalRatingRemoteModel, userId);
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
    public int getRatingType() {
        return _ratingType;
    }

    @Override
    public void setRatingType(int ratingType) {
        _ratingType = ratingType;

        if (_proposalRatingRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingRemoteModel.getClass();

                Method method = clazz.getMethod("setRatingType", int.class);

                method.invoke(_proposalRatingRemoteModel, ratingType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getRating() {
        return _rating;
    }

    @Override
    public void setRating(long rating) {
        _rating = rating;

        if (_proposalRatingRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingRemoteModel.getClass();

                Method method = clazz.getMethod("setRating", long.class);

                method.invoke(_proposalRatingRemoteModel, rating);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComment() {
        return _comment;
    }

    @Override
    public void setComment(String comment) {
        _comment = comment;

        if (_proposalRatingRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingRemoteModel.getClass();

                Method method = clazz.getMethod("setComment", String.class);

                method.invoke(_proposalRatingRemoteModel, comment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOtherDataString() {
        return _otherDataString;
    }

    @Override
    public void setOtherDataString(String otherDataString) {
        _otherDataString = otherDataString;

        if (_proposalRatingRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingRemoteModel.getClass();

                Method method = clazz.getMethod("setOtherDataString",
                        String.class);

                method.invoke(_proposalRatingRemoteModel, otherDataString);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean isRatingComplete() {
        try {
            String methodName = "isRatingComplete";

            Class<?>[] parameterTypes = new Class<?>[] {  };

            Object[] parameterValues = new Object[] {  };

            Boolean returnObj = (Boolean) invokeOnRemoteModel(methodName,
                    parameterTypes, parameterValues);

            return returnObj;
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public BaseModel<?> getProposalRatingRemoteModel() {
        return _proposalRatingRemoteModel;
    }

    public void setProposalRatingRemoteModel(
        BaseModel<?> proposalRatingRemoteModel) {
        _proposalRatingRemoteModel = proposalRatingRemoteModel;
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

        Class<?> remoteModelClass = _proposalRatingRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalRatingRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalRatingLocalServiceUtil.addProposalRating(this);
        } else {
            ProposalRatingLocalServiceUtil.updateProposalRating(this);
        }
    }

    @Override
    public ProposalRating toEscapedModel() {
        return (ProposalRating) ProxyUtil.newProxyInstance(ProposalRating.class.getClassLoader(),
            new Class[] { ProposalRating.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalRatingClp clone = new ProposalRatingClp();

        clone.setId(getId());
        clone.setProposalId(getProposalId());
        clone.setContestPhaseId(getContestPhaseId());
        clone.setUserId(getUserId());
        clone.setRatingType(getRatingType());
        clone.setRating(getRating());
        clone.setComment(getComment());
        clone.setOtherDataString(getOtherDataString());

        return clone;
    }

    @Override
    public int compareTo(ProposalRating proposalRating) {
        long primaryKey = proposalRating.getPrimaryKey();

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

        if (!(obj instanceof ProposalRatingClp)) {
            return false;
        }

        ProposalRatingClp proposalRating = (ProposalRatingClp) obj;

        long primaryKey = proposalRating.getPrimaryKey();

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

        sb.append("{id=");
        sb.append(getId());
        sb.append(", proposalId=");
        sb.append(getProposalId());
        sb.append(", contestPhaseId=");
        sb.append(getContestPhaseId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", ratingType=");
        sb.append(getRatingType());
        sb.append(", rating=");
        sb.append(getRating());
        sb.append(", comment=");
        sb.append(getComment());
        sb.append(", otherDataString=");
        sb.append(getOtherDataString());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalRating");
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
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ratingType</column-name><column-value><![CDATA[");
        sb.append(getRatingType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rating</column-name><column-value><![CDATA[");
        sb.append(getRating());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>comment</column-name><column-value><![CDATA[");
        sb.append(getComment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>otherDataString</column-name><column-value><![CDATA[");
        sb.append(getOtherDataString());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
