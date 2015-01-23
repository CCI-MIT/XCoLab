package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.SocialActivityLocalServiceUtil;

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


public class SocialActivityClp extends BaseModelImpl<SocialActivity>
    implements SocialActivity {
    private long _activityId;
    private long _userId;
    private String _userUuid;
    private BaseModel<?> _socialActivityRemoteModel;

    public SocialActivityClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return SocialActivity.class;
    }

    @Override
    public String getModelClassName() {
        return SocialActivity.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _activityId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setActivityId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _activityId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("activityId", getActivityId());
        attributes.put("userId", getUserId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long activityId = (Long) attributes.get("activityId");

        if (activityId != null) {
            setActivityId(activityId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }
    }

    @Override
    public long getActivityId() {
        return _activityId;
    }

    @Override
    public void setActivityId(long activityId) {
        _activityId = activityId;

        if (_socialActivityRemoteModel != null) {
            try {
                Class<?> clazz = _socialActivityRemoteModel.getClass();

                Method method = clazz.getMethod("setActivityId", long.class);

                method.invoke(_socialActivityRemoteModel, activityId);
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

        if (_socialActivityRemoteModel != null) {
            try {
                Class<?> clazz = _socialActivityRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_socialActivityRemoteModel, userId);
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

    public BaseModel<?> getSocialActivityRemoteModel() {
        return _socialActivityRemoteModel;
    }

    public void setSocialActivityRemoteModel(
        BaseModel<?> socialActivityRemoteModel) {
        _socialActivityRemoteModel = socialActivityRemoteModel;
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

        Class<?> remoteModelClass = _socialActivityRemoteModel.getClass();

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

        Object returnValue = method.invoke(_socialActivityRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            SocialActivityLocalServiceUtil.addSocialActivity(this);
        } else {
            SocialActivityLocalServiceUtil.updateSocialActivity(this);
        }
    }

    @Override
    public SocialActivity toEscapedModel() {
        return (SocialActivity) ProxyUtil.newProxyInstance(SocialActivity.class.getClassLoader(),
            new Class[] { SocialActivity.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        SocialActivityClp clone = new SocialActivityClp();

        clone.setActivityId(getActivityId());
        clone.setUserId(getUserId());

        return clone;
    }

    @Override
    public int compareTo(SocialActivity socialActivity) {
        long primaryKey = socialActivity.getPrimaryKey();

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

        if (!(obj instanceof SocialActivityClp)) {
            return false;
        }

        SocialActivityClp socialActivity = (SocialActivityClp) obj;

        long primaryKey = socialActivity.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{activityId=");
        sb.append(getActivityId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.SocialActivity");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>activityId</column-name><column-value><![CDATA[");
        sb.append(getActivityId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
