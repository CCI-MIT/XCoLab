package com.ext.portlet.Activity.model;

import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class ActivitySubscriptionClp extends BaseModelImpl<ActivitySubscription>
    implements ActivitySubscription {
    private long _pk;
    private long _classNameId;
    private long _classPK;
    private int _type;
    private String _extraData;
    private long _receiverId;
    private Date _createDate;
    private Date _modifiedDate;

    public ActivitySubscriptionClp() {
    }

    public Class<?> getModelClass() {
        return ActivitySubscription.class;
    }

    public String getModelClassName() {
        return ActivitySubscription.class.getName();
    }

    public long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(long primaryKey) {
        setPk(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_pk);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getPk() {
        return _pk;
    }

    public void setPk(long pk) {
        _pk = pk;
    }

    public String getClassName() {
        if (getClassNameId() <= 0) {
            return StringPool.BLANK;
        }

        return PortalUtil.getClassName(getClassNameId());
    }

    public long getClassNameId() {
        return _classNameId;
    }

    public void setClassNameId(long classNameId) {
        _classNameId = classNameId;
    }

    public long getClassPK() {
        return _classPK;
    }

    public void setClassPK(long classPK) {
        _classPK = classPK;
    }

    public int getType() {
        return _type;
    }

    public void setType(int type) {
        _type = type;
    }

    public String getExtraData() {
        return _extraData;
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }

    public long getReceiverId() {
        return _receiverId;
    }

    public void setReceiverId(long receiverId) {
        _receiverId = receiverId;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ActivitySubscriptionLocalServiceUtil.addActivitySubscription(this);
        } else {
            ActivitySubscriptionLocalServiceUtil.updateActivitySubscription(this);
        }
    }

    @Override
    public ActivitySubscription toEscapedModel() {
        return (ActivitySubscription) Proxy.newProxyInstance(ActivitySubscription.class.getClassLoader(),
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
        clone.setExtraData(getExtraData());
        clone.setReceiverId(getReceiverId());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

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
        if (obj == null) {
            return false;
        }

        ActivitySubscriptionClp activitySubscription = null;

        try {
            activitySubscription = (ActivitySubscriptionClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

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
        StringBundler sb = new StringBundler(17);

        sb.append("{pk=");
        sb.append(getPk());
        sb.append(", classNameId=");
        sb.append(getClassNameId());
        sb.append(", classPK=");
        sb.append(getClassPK());
        sb.append(", type=");
        sb.append(getType());
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

    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.Activity.model.ActivitySubscription");
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
