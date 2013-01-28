package com.ext.portlet.model;

import com.ext.portlet.service.DiscussionMessageFlagLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class DiscussionMessageFlagClp extends BaseModelImpl<DiscussionMessageFlag>
    implements DiscussionMessageFlag {
    private long _pk;
    private long _messageId;
    private String _flagType;
    private String _data;
    private Date _created;
    private long _userId;
    private String _userUuid;

    public DiscussionMessageFlagClp() {
    }

    public Class<?> getModelClass() {
        return DiscussionMessageFlag.class;
    }

    public String getModelClassName() {
        return DiscussionMessageFlag.class.getName();
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

    public long getMessageId() {
        return _messageId;
    }

    public void setMessageId(long messageId) {
        _messageId = messageId;
    }

    public String getFlagType() {
        return _flagType;
    }

    public void setFlagType(String flagType) {
        _flagType = flagType;
    }

    public String getData() {
        return _data;
    }

    public void setData(String data) {
        _data = data;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
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

    public void persist() throws SystemException {
        if (this.isNew()) {
            DiscussionMessageFlagLocalServiceUtil.addDiscussionMessageFlag(this);
        } else {
            DiscussionMessageFlagLocalServiceUtil.updateDiscussionMessageFlag(this);
        }
    }

    @Override
    public DiscussionMessageFlag toEscapedModel() {
        return (DiscussionMessageFlag) Proxy.newProxyInstance(DiscussionMessageFlag.class.getClassLoader(),
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
        if (obj == null) {
            return false;
        }

        DiscussionMessageFlagClp discussionMessageFlag = null;

        try {
            discussionMessageFlag = (DiscussionMessageFlagClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

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
