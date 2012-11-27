package com.ext.portlet.discussions.model;

import com.ext.portlet.discussions.service.DiscussionMessageFlagLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class DiscussionMessageFlagClp extends BaseModelImpl<DiscussionMessageFlag>
    implements DiscussionMessageFlag {
    private Long _pk;
    private Long _messageId;
    private String _flagType;
    private String _data;
    private Date _created;
    private Long _userId;

    public DiscussionMessageFlagClp() {
    }

    public Class<?> getModelClass() {
        return DiscussionMessageFlag.class;
    }

    public String getModelClassName() {
        return DiscussionMessageFlag.class.getName();
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

    public Long getMessageId() {
        return _messageId;
    }

    public void setMessageId(Long messageId) {
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

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
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
        Long primaryKey = discussionMessageFlag.getPrimaryKey();

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

        Long primaryKey = discussionMessageFlag.getPrimaryKey();

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
        sb.append("com.ext.portlet.discussions.model.DiscussionMessageFlag");
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
