package com.ext.portlet.model;

import com.ext.portlet.service.AnalyticsUserEventLocalServiceUtil;
import com.ext.portlet.service.persistence.AnalyticsUserEventPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


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

    public AnalyticsUserEventClp() {
    }

    public Class<?> getModelClass() {
        return AnalyticsUserEvent.class;
    }

    public String getModelClassName() {
        return AnalyticsUserEvent.class.getName();
    }

    public AnalyticsUserEventPK getPrimaryKey() {
        return new AnalyticsUserEventPK(_userId, _idString);
    }

    public void setPrimaryKey(AnalyticsUserEventPK primaryKey) {
        setUserId(primaryKey.userId);
        setIdString(primaryKey.idString);
    }

    public Serializable getPrimaryKeyObj() {
        return new AnalyticsUserEventPK(_userId, _idString);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((AnalyticsUserEventPK) primaryKeyObj);
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    public String getIdString() {
        return _idString;
    }

    public void setIdString(String idString) {
        _idString = idString;
    }

    public String getCategory() {
        return _category;
    }

    public void setCategory(String category) {
        _category = category;
    }

    public String getAction() {
        return _action;
    }

    public void setAction(String action) {
        _action = action;
    }

    public String getLabel() {
        return _label;
    }

    public void setLabel(String label) {
        _label = label;
    }

    public int getValue() {
        return _value;
    }

    public void setValue(int value) {
        _value = value;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            AnalyticsUserEventLocalServiceUtil.addAnalyticsUserEvent(this);
        } else {
            AnalyticsUserEventLocalServiceUtil.updateAnalyticsUserEvent(this);
        }
    }

    @Override
    public AnalyticsUserEvent toEscapedModel() {
        return (AnalyticsUserEvent) Proxy.newProxyInstance(AnalyticsUserEvent.class.getClassLoader(),
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

    public int compareTo(AnalyticsUserEvent analyticsUserEvent) {
        AnalyticsUserEventPK primaryKey = analyticsUserEvent.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        AnalyticsUserEventClp analyticsUserEvent = null;

        try {
            analyticsUserEvent = (AnalyticsUserEventClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

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
