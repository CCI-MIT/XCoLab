package com.ext.portlet.model;

import com.ext.portlet.service.AnalyticsUserEventLocalServiceUtil;
import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.persistence.AnalyticsUserEventPK;

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


public class AnalyticsUserEventClp extends BaseModelImpl<AnalyticsUserEvent>
    implements AnalyticsUserEvent {
    private long _userId;
    private String _userUuid;
    private String _idString;
    private String _category;
    private String _action;
    private String _label;
    private int _value;
    private Date _created;
    private BaseModel<?> _analyticsUserEventRemoteModel;

    public AnalyticsUserEventClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AnalyticsUserEvent.class;
    }

    @Override
    public String getModelClassName() {
        return AnalyticsUserEvent.class.getName();
    }

    @Override
    public AnalyticsUserEventPK getPrimaryKey() {
        return new AnalyticsUserEventPK(_userId, _idString);
    }

    @Override
    public void setPrimaryKey(AnalyticsUserEventPK primaryKey) {
        setUserId(primaryKey.userId);
        setIdString(primaryKey.idString);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new AnalyticsUserEventPK(_userId, _idString);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((AnalyticsUserEventPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("idString", getIdString());
        attributes.put("category", getCategory());
        attributes.put("action", getAction());
        attributes.put("label", getLabel());
        attributes.put("value", getValue());
        attributes.put("created", getCreated());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String idString = (String) attributes.get("idString");

        if (idString != null) {
            setIdString(idString);
        }

        String category = (String) attributes.get("category");

        if (category != null) {
            setCategory(category);
        }

        String action = (String) attributes.get("action");

        if (action != null) {
            setAction(action);
        }

        String label = (String) attributes.get("label");

        if (label != null) {
            setLabel(label);
        }

        Integer value = (Integer) attributes.get("value");

        if (value != null) {
            setValue(value);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_analyticsUserEventRemoteModel != null) {
            try {
                Class<?> clazz = _analyticsUserEventRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_analyticsUserEventRemoteModel, userId);
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
    public String getIdString() {
        return _idString;
    }

    @Override
    public void setIdString(String idString) {
        _idString = idString;

        if (_analyticsUserEventRemoteModel != null) {
            try {
                Class<?> clazz = _analyticsUserEventRemoteModel.getClass();

                Method method = clazz.getMethod("setIdString", String.class);

                method.invoke(_analyticsUserEventRemoteModel, idString);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCategory() {
        return _category;
    }

    @Override
    public void setCategory(String category) {
        _category = category;

        if (_analyticsUserEventRemoteModel != null) {
            try {
                Class<?> clazz = _analyticsUserEventRemoteModel.getClass();

                Method method = clazz.getMethod("setCategory", String.class);

                method.invoke(_analyticsUserEventRemoteModel, category);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAction() {
        return _action;
    }

    @Override
    public void setAction(String action) {
        _action = action;

        if (_analyticsUserEventRemoteModel != null) {
            try {
                Class<?> clazz = _analyticsUserEventRemoteModel.getClass();

                Method method = clazz.getMethod("setAction", String.class);

                method.invoke(_analyticsUserEventRemoteModel, action);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLabel() {
        return _label;
    }

    @Override
    public void setLabel(String label) {
        _label = label;

        if (_analyticsUserEventRemoteModel != null) {
            try {
                Class<?> clazz = _analyticsUserEventRemoteModel.getClass();

                Method method = clazz.getMethod("setLabel", String.class);

                method.invoke(_analyticsUserEventRemoteModel, label);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getValue() {
        return _value;
    }

    @Override
    public void setValue(int value) {
        _value = value;

        if (_analyticsUserEventRemoteModel != null) {
            try {
                Class<?> clazz = _analyticsUserEventRemoteModel.getClass();

                Method method = clazz.getMethod("setValue", int.class);

                method.invoke(_analyticsUserEventRemoteModel, value);
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

        if (_analyticsUserEventRemoteModel != null) {
            try {
                Class<?> clazz = _analyticsUserEventRemoteModel.getClass();

                Method method = clazz.getMethod("setCreated", Date.class);

                method.invoke(_analyticsUserEventRemoteModel, created);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAnalyticsUserEventRemoteModel() {
        return _analyticsUserEventRemoteModel;
    }

    public void setAnalyticsUserEventRemoteModel(
        BaseModel<?> analyticsUserEventRemoteModel) {
        _analyticsUserEventRemoteModel = analyticsUserEventRemoteModel;
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

        Class<?> remoteModelClass = _analyticsUserEventRemoteModel.getClass();

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

        Object returnValue = method.invoke(_analyticsUserEventRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AnalyticsUserEventLocalServiceUtil.addAnalyticsUserEvent(this);
        } else {
            AnalyticsUserEventLocalServiceUtil.updateAnalyticsUserEvent(this);
        }
    }

    @Override
    public AnalyticsUserEvent toEscapedModel() {
        return (AnalyticsUserEvent) ProxyUtil.newProxyInstance(AnalyticsUserEvent.class.getClassLoader(),
            new Class[] { AnalyticsUserEvent.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AnalyticsUserEventClp clone = new AnalyticsUserEventClp();

        clone.setUserId(getUserId());
        clone.setIdString(getIdString());
        clone.setCategory(getCategory());
        clone.setAction(getAction());
        clone.setLabel(getLabel());
        clone.setValue(getValue());
        clone.setCreated(getCreated());

        return clone;
    }

    @Override
    public int compareTo(AnalyticsUserEvent analyticsUserEvent) {
        AnalyticsUserEventPK primaryKey = analyticsUserEvent.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AnalyticsUserEventClp)) {
            return false;
        }

        AnalyticsUserEventClp analyticsUserEvent = (AnalyticsUserEventClp) obj;

        AnalyticsUserEventPK primaryKey = analyticsUserEvent.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", idString=");
        sb.append(getIdString());
        sb.append(", category=");
        sb.append(getCategory());
        sb.append(", action=");
        sb.append(getAction());
        sb.append(", label=");
        sb.append(getLabel());
        sb.append(", value=");
        sb.append(getValue());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.AnalyticsUserEvent");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>idString</column-name><column-value><![CDATA[");
        sb.append(getIdString());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>category</column-name><column-value><![CDATA[");
        sb.append(getCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>action</column-name><column-value><![CDATA[");
        sb.append(getAction());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>label</column-name><column-value><![CDATA[");
        sb.append(getLabel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>value</column-name><column-value><![CDATA[");
        sb.append(getValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
