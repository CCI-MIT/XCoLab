package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.TrackedVisitor2UserLocalServiceUtil;

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


public class TrackedVisitor2UserClp extends BaseModelImpl<TrackedVisitor2User>
    implements TrackedVisitor2User {
    private long _id;
    private String _uuid;
    private long _userId;
    private String _userUuid;
    private Date _createDate;
    private BaseModel<?> _trackedVisitor2UserRemoteModel;

    public TrackedVisitor2UserClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return TrackedVisitor2User.class;
    }

    @Override
    public String getModelClassName() {
        return TrackedVisitor2User.class.getName();
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
        attributes.put("uuid", getUuid());
        attributes.put("userId", getUserId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
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
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_trackedVisitor2UserRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitor2UserRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_trackedVisitor2UserRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUuid() {
        return _uuid;
    }

    @Override
    public void setUuid(String uuid) {
        _uuid = uuid;

        if (_trackedVisitor2UserRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitor2UserRemoteModel.getClass();

                Method method = clazz.getMethod("setUuid", String.class);

                method.invoke(_trackedVisitor2UserRemoteModel, uuid);
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

        if (_trackedVisitor2UserRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitor2UserRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_trackedVisitor2UserRemoteModel, userId);
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

        if (_trackedVisitor2UserRemoteModel != null) {
            try {
                Class<?> clazz = _trackedVisitor2UserRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_trackedVisitor2UserRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getTrackedVisitor2UserRemoteModel() {
        return _trackedVisitor2UserRemoteModel;
    }

    public void setTrackedVisitor2UserRemoteModel(
        BaseModel<?> trackedVisitor2UserRemoteModel) {
        _trackedVisitor2UserRemoteModel = trackedVisitor2UserRemoteModel;
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

        Class<?> remoteModelClass = _trackedVisitor2UserRemoteModel.getClass();

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

        Object returnValue = method.invoke(_trackedVisitor2UserRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            TrackedVisitor2UserLocalServiceUtil.addTrackedVisitor2User(this);
        } else {
            TrackedVisitor2UserLocalServiceUtil.updateTrackedVisitor2User(this);
        }
    }

    @Override
    public TrackedVisitor2User toEscapedModel() {
        return (TrackedVisitor2User) ProxyUtil.newProxyInstance(TrackedVisitor2User.class.getClassLoader(),
            new Class[] { TrackedVisitor2User.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        TrackedVisitor2UserClp clone = new TrackedVisitor2UserClp();

        clone.setId(getId());
        clone.setUuid(getUuid());
        clone.setUserId(getUserId());
        clone.setCreateDate(getCreateDate());

        return clone;
    }

    @Override
    public int compareTo(TrackedVisitor2User trackedVisitor2User) {
        long primaryKey = trackedVisitor2User.getPrimaryKey();

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

        if (!(obj instanceof TrackedVisitor2UserClp)) {
            return false;
        }

        TrackedVisitor2UserClp trackedVisitor2User = (TrackedVisitor2UserClp) obj;

        long primaryKey = trackedVisitor2User.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", uuid=");
        sb.append(getUuid());
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
        sb.append("com.ext.portlet.model.TrackedVisitor2User");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uuid</column-name><column-value><![CDATA[");
        sb.append(getUuid());
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
