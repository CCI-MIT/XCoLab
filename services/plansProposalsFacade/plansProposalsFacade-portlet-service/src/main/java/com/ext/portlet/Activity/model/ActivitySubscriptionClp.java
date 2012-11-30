package com.ext.portlet.Activity.model;

import java.io.Serializable;
import java.lang.reflect.Proxy;
import java.util.Date;

import com.ext.portlet.Activity.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;


public class ActivitySubscriptionClp extends BaseModelImpl<ActivitySubscription>
    implements ActivitySubscription {
    private Long _pk;
    private Long _classNameId;
    private Long _classPK;
    private Integer _type;
    private String _extraData;
    private Long _receiverId;
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

    public Long getPrimaryKey() {
        return _pk;
    }

    public void setPrimaryKey(Long primaryKey) {
        setPk(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_pk);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getPk() {
        return _pk;
    }

    public void setPk(Long pk) {
        _pk = pk;
    }

    public String getClassName() {
        if (getClassNameId() <= 0) {
            return StringPool.BLANK;
        }

        return PortalUtil.getClassName(getClassNameId());
    }

    public Long getClassNameId() {
        return _classNameId;
    }

    public void setClassNameId(Long classNameId) {
        _classNameId = classNameId;
    }

    public Long getClassPK() {
        return _classPK;
    }

    public void setClassPK(Long classPK) {
        _classPK = classPK;
    }

    public Integer getType() {
        return _type;
    }

    public void setType(Integer type) {
        _type = type;
    }

    public String getExtraData() {
        return _extraData;
    }

    public void setExtraData(String extraData) {
        _extraData = extraData;
    }

    public Long getReceiverId() {
        return _receiverId;
    }

    public void setReceiverId(Long receiverId) {
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

    public void store() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.Activity.ICollabActivityInterpreter getInterpreter() {
        throw new UnsupportedOperationException();
    }

    public java.lang.String getName() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.Activity.SubscriptionType getSubscriptionType() {
        throw new UnsupportedOperationException();
    }

    public void delete() {
        throw new UnsupportedOperationException();
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
        Long primaryKey = activitySubscription.getPrimaryKey();

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

        Long primaryKey = activitySubscription.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
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
