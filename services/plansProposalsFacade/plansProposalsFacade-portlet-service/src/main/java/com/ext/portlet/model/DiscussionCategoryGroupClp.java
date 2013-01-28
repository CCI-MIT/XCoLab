package com.ext.portlet.model;

import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class DiscussionCategoryGroupClp extends BaseModelImpl<DiscussionCategoryGroup>
    implements DiscussionCategoryGroup {
    private long _id;
    private String _description;
    private String _url;
    private long _commentsThread;

    public DiscussionCategoryGroupClp() {
    }

    public Class<?> getModelClass() {
        return DiscussionCategoryGroup.class;
    }

    public String getModelClassName() {
        return DiscussionCategoryGroup.class.getName();
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    public long getCommentsThread() {
        return _commentsThread;
    }

    public void setCommentsThread(long commentsThread) {
        _commentsThread = commentsThread;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            DiscussionCategoryGroupLocalServiceUtil.addDiscussionCategoryGroup(this);
        } else {
            DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(this);
        }
    }

    @Override
    public DiscussionCategoryGroup toEscapedModel() {
        return (DiscussionCategoryGroup) Proxy.newProxyInstance(DiscussionCategoryGroup.class.getClassLoader(),
            new Class[] { DiscussionCategoryGroup.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DiscussionCategoryGroupClp clone = new DiscussionCategoryGroupClp();

        clone.setId(getId());
        clone.setDescription(getDescription());
        clone.setUrl(getUrl());
        clone.setCommentsThread(getCommentsThread());

        return clone;
    }

    public int compareTo(DiscussionCategoryGroup discussionCategoryGroup) {
        long primaryKey = discussionCategoryGroup.getPrimaryKey();

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

        DiscussionCategoryGroupClp discussionCategoryGroup = null;

        try {
            discussionCategoryGroup = (DiscussionCategoryGroupClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = discussionCategoryGroup.getPrimaryKey();

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
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", url=");
        sb.append(getUrl());
        sb.append(", commentsThread=");
        sb.append(getCommentsThread());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.DiscussionCategoryGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>url</column-name><column-value><![CDATA[");
        sb.append(getUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>commentsThread</column-name><column-value><![CDATA[");
        sb.append(getCommentsThread());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
