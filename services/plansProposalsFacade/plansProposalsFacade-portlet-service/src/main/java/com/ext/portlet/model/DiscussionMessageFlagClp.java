package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.DiscussionMessageFlagLocalServiceUtil;

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


public class DiscussionMessageFlagClp extends BaseModelImpl<DiscussionMessageFlag>
    implements DiscussionMessageFlag {
    private long _pk;
    private long _messageId;
    private String _flagType;
    private String _data;
    private Date _created;
    private long _userId;
    private String _userUuid;
    private BaseModel<?> _discussionMessageFlagRemoteModel;

    public DiscussionMessageFlagClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DiscussionMessageFlag.class;
    }

    @Override
    public String getModelClassName() {
        return DiscussionMessageFlag.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _pk;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPk(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _pk;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("pk", getPk());
        attributes.put("messageId", getMessageId());
        attributes.put("flagType", getFlagType());
        attributes.put("data", getData());
        attributes.put("created", getCreated());
        attributes.put("userId", getUserId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long pk = (Long) attributes.get("pk");

        if (pk != null) {
            setPk(pk);
        }

        Long messageId = (Long) attributes.get("messageId");

        if (messageId != null) {
            setMessageId(messageId);
        }

        String flagType = (String) attributes.get("flagType");

        if (flagType != null) {
            setFlagType(flagType);
        }

        String data = (String) attributes.get("data");

        if (data != null) {
            setData(data);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }
    }

    @Override
    public long getPk() {
        return _pk;
    }

    @Override
    public void setPk(long pk) {
        _pk = pk;

        if (_discussionMessageFlagRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageFlagRemoteModel.getClass();

                Method method = clazz.getMethod("setPk", long.class);

                method.invoke(_discussionMessageFlagRemoteModel, pk);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getMessageId() {
        return _messageId;
    }

    @Override
    public void setMessageId(long messageId) {
        _messageId = messageId;

        if (_discussionMessageFlagRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageFlagRemoteModel.getClass();

                Method method = clazz.getMethod("setMessageId", long.class);

                method.invoke(_discussionMessageFlagRemoteModel, messageId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFlagType() {
        return _flagType;
    }

    @Override
    public void setFlagType(String flagType) {
        _flagType = flagType;

        if (_discussionMessageFlagRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageFlagRemoteModel.getClass();

                Method method = clazz.getMethod("setFlagType", String.class);

                method.invoke(_discussionMessageFlagRemoteModel, flagType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getData() {
        return _data;
    }

    @Override
    public void setData(String data) {
        _data = data;

        if (_discussionMessageFlagRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageFlagRemoteModel.getClass();

                Method method = clazz.getMethod("setData", String.class);

                method.invoke(_discussionMessageFlagRemoteModel, data);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreated() {
        return _created;
    }

    @Override
    public void setCreated(Date created) {
        _created = created;

        if (_discussionMessageFlagRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageFlagRemoteModel.getClass();

                Method method = clazz.getMethod("setCreated", Date.class);

                method.invoke(_discussionMessageFlagRemoteModel, created);
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

        if (_discussionMessageFlagRemoteModel != null) {
            try {
                Class<?> clazz = _discussionMessageFlagRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_discussionMessageFlagRemoteModel, userId);
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

    public BaseModel<?> getDiscussionMessageFlagRemoteModel() {
        return _discussionMessageFlagRemoteModel;
    }

    public void setDiscussionMessageFlagRemoteModel(
        BaseModel<?> discussionMessageFlagRemoteModel) {
        _discussionMessageFlagRemoteModel = discussionMessageFlagRemoteModel;
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

        Class<?> remoteModelClass = _discussionMessageFlagRemoteModel.getClass();

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

        Object returnValue = method.invoke(_discussionMessageFlagRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DiscussionMessageFlagLocalServiceUtil.addDiscussionMessageFlag(this);
        } else {
            DiscussionMessageFlagLocalServiceUtil.updateDiscussionMessageFlag(this);
        }
    }

    @Override
    public DiscussionMessageFlag toEscapedModel() {
        return (DiscussionMessageFlag) ProxyUtil.newProxyInstance(DiscussionMessageFlag.class.getClassLoader(),
            new Class[] { DiscussionMessageFlag.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DiscussionMessageFlagClp clone = new DiscussionMessageFlagClp();

        clone.setPk(getPk());
        clone.setMessageId(getMessageId());
        clone.setFlagType(getFlagType());
        clone.setData(getData());
        clone.setCreated(getCreated());
        clone.setUserId(getUserId());

        return clone;
    }

    @Override
    public int compareTo(DiscussionMessageFlag discussionMessageFlag) {
        long primaryKey = discussionMessageFlag.getPrimaryKey();

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

        if (!(obj instanceof DiscussionMessageFlagClp)) {
            return false;
        }

        DiscussionMessageFlagClp discussionMessageFlag = (DiscussionMessageFlagClp) obj;

        long primaryKey = discussionMessageFlag.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{pk=");
        sb.append(getPk());
        sb.append(", messageId=");
        sb.append(getMessageId());
        sb.append(", flagType=");
        sb.append(getFlagType());
        sb.append(", data=");
        sb.append(getData());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.DiscussionMessageFlag");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>pk</column-name><column-value><![CDATA[");
        sb.append(getPk());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>messageId</column-name><column-value><![CDATA[");
        sb.append(getMessageId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flagType</column-name><column-value><![CDATA[");
        sb.append(getFlagType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>data</column-name><column-value><![CDATA[");
        sb.append(getData());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
