package com.ext.portlet.model;

import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ActivitySubscriptionClp extends BaseModelImpl<ActivitySubscription>
    implements ActivitySubscription {
    private long _pk;
    private long _classNameId;
    private long _classPK;
    private int _type;
    private int _automaticSubscriptionCounter;
    private String _extraData;
    private long _receiverId;
    private Date _createDate;
    private Date _modifiedDate;
    private BaseModel<?> _activitySubscriptionRemoteModel;

    public ActivitySubscriptionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ActivitySubscription.class;
    }

    @Override
    public String getModelClassName() {
        return ActivitySubscription.class.getName();
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
        attributes.put("classNameId", getClassNameId());
        attributes.put("classPK", getClassPK());
        attributes.put("type", getType());
        attributes.put("automaticSubscriptionCounter",
            getAutomaticSubscriptionCounter());
        attributes.put("extraData", getExtraData());
        attributes.put("receiverId", getReceiverId());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long pk = (Long) attributes.get("pk");

        if (pk != null) {
            setPk(pk);
        }

        Long classNameId = (Long) attributes.get("classNameId");

        if (classNameId != null) {
            setClassNameId(classNameId);
        }

        Long classPK = (Long) attributes.get("classPK");

        if (classPK != null) {
            setClassPK(classPK);
        }

        Integer type = (Integer) attributes.get("type");

        if (type != null) {
            setType(type);
        }

        Integer automaticSubscriptionCounter = (Integer) attributes.get(
                "automaticSubscriptionCounter");

        if (automaticSubscriptionCounter != null) {
            setAutomaticSubscriptionCounter(automaticSubscriptionCounter);
        }

        String extraData = (String) attributes.get("extraData");

        if (extraData != null) {
            setExtraData(extraData);
        }

        Long receiverId = (Long) attributes.get("receiverId");

        if (receiverId != null) {
            setReceiverId(receiverId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    @Override
    public long getPk() {
        return _pk;
    }

    @Override
    public void setPk(long pk) {
        _pk = pk;

        if (_activitySubscriptionRemoteModel != null) {
            try {
                Class<?> clazz = _activitySubscriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setPk", long.class);

                method.invoke(_activitySubscriptionRemoteModel, pk);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getClassName() {
        if (getClassNameId() <= 0) {
            return StringPool.BLANK;
        }

        return PortalUtil.getClassName(getClassNameId());
    }

    @Override
    public void setClassName(String className) {
        long classNameId = 0;

        if (Validator.isNotNull(className)) {
            classNameId = PortalUtil.getClassNameId(className);
        }

        setClassNameId(classNameId);
    }

    @Override
    public long getClassNameId() {
        return _classNameId;
    }

    @Override
    public void setClassNameId(long classNameId) {
        _classNameId = classNameId;

        if (_activitySubscriptionRemoteModel != null) {
            try {
                Class<?> clazz = _activitySubscriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setClassNameId", long.class);

                method.invoke(_activitySubscriptionRemoteModel, classNameId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getClassPK() {
        return _classPK;
    }

    @Override
    public void setClassPK(long classPK) {
        _classPK = classPK;

        if (_activitySubscriptionRemoteModel != null) {
            try {
                Class<?> clazz = _activitySubscriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setClassPK", long.class);

                method.invoke(_activitySubscriptionRemoteModel, classPK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getType() {
        return _type;
    }

    @Override
    public void setType(int type) {
        _type = type;

        if (_activitySubscriptionRemoteModel != null) {
            try {
                Class<?> clazz = _activitySubscriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setType", int.class);

                method.invoke(_activitySubscriptionRemoteModel, type);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAutomaticSubscriptionCounter() {
        return _automaticSubscriptionCounter;
    }

    @Override
    public void setAutomaticSubscriptionCounter(
        int automaticSubscriptionCounter) {
        _automaticSubscriptionCounter = automaticSubscriptionCounter;

        if (_activitySubscriptionRemoteModel != null) {
            try {
                Class<?> clazz = _activitySubscriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setAutomaticSubscriptionCounter",
                        int.class);

                method.invoke(_activitySubscriptionRemoteModel,
                    automaticSubscriptionCounter);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getExtraData() {
        return _extraData;
    }

    @Override
    public void setExtraData(String extraData) {
        _extraData = extraData;

        if (_activitySubscriptionRemoteModel != null) {
            try {
                Class<?> clazz = _activitySubscriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setExtraData", String.class);

                method.invoke(_activitySubscriptionRemoteModel, extraData);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getReceiverId() {
        return _receiverId;
    }

    @Override
    public void setReceiverId(long receiverId) {
        _receiverId = receiverId;

        if (_activitySubscriptionRemoteModel != null) {
            try {
                Class<?> clazz = _activitySubscriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setReceiverId", long.class);

                method.invoke(_activitySubscriptionRemoteModel, receiverId);
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

        if (_activitySubscriptionRemoteModel != null) {
            try {
                Class<?> clazz = _activitySubscriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_activitySubscriptionRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_activitySubscriptionRemoteModel != null) {
            try {
                Class<?> clazz = _activitySubscriptionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_activitySubscriptionRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getActivitySubscriptionRemoteModel() {
        return _activitySubscriptionRemoteModel;
    }

    public void setActivitySubscriptionRemoteModel(
        BaseModel<?> activitySubscriptionRemoteModel) {
        _activitySubscriptionRemoteModel = activitySubscriptionRemoteModel;
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

        Class<?> remoteModelClass = _activitySubscriptionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_activitySubscriptionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ActivitySubscriptionLocalServiceUtil.addActivitySubscription(this);
        } else {
            ActivitySubscriptionLocalServiceUtil.updateActivitySubscription(this);
        }
    }

    @Override
    public ActivitySubscription toEscapedModel() {
        return (ActivitySubscription) ProxyUtil.newProxyInstance(ActivitySubscription.class.getClassLoader(),
            new Class[] { ActivitySubscription.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ActivitySubscriptionClp clone = new ActivitySubscriptionClp();

        clone.setPk(getPk());
        clone.setClassNameId(getClassNameId());
        clone.setClassPK(getClassPK());
        clone.setType(getType());
        clone.setAutomaticSubscriptionCounter(getAutomaticSubscriptionCounter());
        clone.setExtraData(getExtraData());
        clone.setReceiverId(getReceiverId());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(ActivitySubscription activitySubscription) {
        long primaryKey = activitySubscription.getPrimaryKey();

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

        if (!(obj instanceof ActivitySubscriptionClp)) {
            return false;
        }

        ActivitySubscriptionClp activitySubscription = (ActivitySubscriptionClp) obj;

        long primaryKey = activitySubscription.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{pk=");
        sb.append(getPk());
        sb.append(", classNameId=");
        sb.append(getClassNameId());
        sb.append(", classPK=");
        sb.append(getClassPK());
        sb.append(", type=");
        sb.append(getType());
        sb.append(", automaticSubscriptionCounter=");
        sb.append(getAutomaticSubscriptionCounter());
        sb.append(", extraData=");
        sb.append(getExtraData());
        sb.append(", receiverId=");
        sb.append(getReceiverId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ActivitySubscription");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>pk</column-name><column-value><![CDATA[");
        sb.append(getPk());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>classNameId</column-name><column-value><![CDATA[");
        sb.append(getClassNameId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>classPK</column-name><column-value><![CDATA[");
        sb.append(getClassPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>automaticSubscriptionCounter</column-name><column-value><![CDATA[");
        sb.append(getAutomaticSubscriptionCounter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>extraData</column-name><column-value><![CDATA[");
        sb.append(getExtraData());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>receiverId</column-name><column-value><![CDATA[");
        sb.append(getReceiverId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
