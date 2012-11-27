package com.ext.portlet.discussions.model;

import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class DiscussionCategoryGroupClp extends BaseModelImpl<DiscussionCategoryGroup>
    implements DiscussionCategoryGroup {
    private Long _id;
    private String _description;
    private String _url;
    private Long _commentsThread;

    public DiscussionCategoryGroupClp() {
    }

    public Class<?> getModelClass() {
        return DiscussionCategoryGroup.class;
    }

    public String getModelClassName() {
        return DiscussionCategoryGroup.class.getName();
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
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

    public Long getCommentsThread() {
        return _commentsThread;
    }

    public void setCommentsThread(Long commentsThread) {
        _commentsThread = commentsThread;
    }

    public com.ext.portlet.discussions.model.DiscussionCategory getCategoryById(
        java.lang.Long categoryId) {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.discussions.model.DiscussionMessage getThreadById(
        java.lang.Long threadId) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> getCategories() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.discussions.model.DiscussionCategory addCategory(
        java.lang.String name, java.lang.String description,
        com.liferay.portal.model.User creator) {
        throw new UnsupportedOperationException();
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.discussions.model.DiscussionMessage getCommentThread() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.discussions.model.DiscussionMessage addComment(
        java.lang.String title, java.lang.String description,
        com.liferay.portal.model.User author) {
        throw new UnsupportedOperationException();
    }

    public int getCommentsCount() {
        throw new UnsupportedOperationException();
    }

    public void copyEverything(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup source) {
        throw new UnsupportedOperationException();
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
        Long primaryKey = discussionCategoryGroup.getPrimaryKey();

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

        Long primaryKey = discussionCategoryGroup.getPrimaryKey();

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
        sb.append("com.ext.portlet.discussions.model.DiscussionCategoryGroup");
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
