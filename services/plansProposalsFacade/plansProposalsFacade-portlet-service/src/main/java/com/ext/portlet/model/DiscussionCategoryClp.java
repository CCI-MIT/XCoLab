package com.ext.portlet.model;

import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class DiscussionCategoryClp extends BaseModelImpl<DiscussionCategory>
    implements DiscussionCategory {
    private long _pk;
    private long _categoryId;
    private long _categoryGroupId;
    private long _authorId;
    private String _name;
    private String _description;
    private Date _createDate;
    private Date _deleted;
    private int _threadsCount;
    private Date _lastActivityDate;
    private long _lastActivityAuthorId;

    public DiscussionCategoryClp() {
    }

    public Class<?> getModelClass() {
        return DiscussionCategory.class;
    }

    public String getModelClassName() {
        return DiscussionCategory.class.getName();
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

    public long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(long categoryId) {
        _categoryId = categoryId;
    }

    public long getCategoryGroupId() {
        return _categoryGroupId;
    }

    public void setCategoryGroupId(long categoryGroupId) {
        _categoryGroupId = categoryGroupId;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getDeleted() {
        return _deleted;
    }

    public void setDeleted(Date deleted) {
        _deleted = deleted;
    }

    public int getThreadsCount() {
        return _threadsCount;
    }

    public void setThreadsCount(int threadsCount) {
        _threadsCount = threadsCount;
    }

    public Date getLastActivityDate() {
        return _lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        _lastActivityDate = lastActivityDate;
    }

    public long getLastActivityAuthorId() {
        return _lastActivityAuthorId;
    }

    public void setLastActivityAuthorId(long lastActivityAuthorId) {
        _lastActivityAuthorId = lastActivityAuthorId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            DiscussionCategoryLocalServiceUtil.addDiscussionCategory(this);
        } else {
            DiscussionCategoryLocalServiceUtil.updateDiscussionCategory(this);
        }
    }

    @Override
    public DiscussionCategory toEscapedModel() {
        return (DiscussionCategory) Proxy.newProxyInstance(DiscussionCategory.class.getClassLoader(),
            new Class[] { DiscussionCategory.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DiscussionCategoryClp clone = new DiscussionCategoryClp();

        clone.setPk(getPk());
        clone.setCategoryId(getCategoryId());
        clone.setCategoryGroupId(getCategoryGroupId());
        clone.setAuthorId(getAuthorId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setCreateDate(getCreateDate());
        clone.setDeleted(getDeleted());
        clone.setThreadsCount(getThreadsCount());
        clone.setLastActivityDate(getLastActivityDate());
        clone.setLastActivityAuthorId(getLastActivityAuthorId());

        return clone;
    }

    public int compareTo(DiscussionCategory discussionCategory) {
        int value = 0;

        value = DateUtil.compareTo(getCreateDate(),
                discussionCategory.getCreateDate());

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        DiscussionCategoryClp discussionCategory = null;

        try {
            discussionCategory = (DiscussionCategoryClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = discussionCategory.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{pk=");
        sb.append(getPk());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append(", categoryGroupId=");
        sb.append(getCategoryGroupId());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", deleted=");
        sb.append(getDeleted());
        sb.append(", threadsCount=");
        sb.append(getThreadsCount());
        sb.append(", lastActivityDate=");
        sb.append(getLastActivityDate());
        sb.append(", lastActivityAuthorId=");
        sb.append(getLastActivityAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.DiscussionCategory");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>pk</column-name><column-value><![CDATA[");
        sb.append(getPk());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryGroupId</column-name><column-value><![CDATA[");
        sb.append(getCategoryGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deleted</column-name><column-value><![CDATA[");
        sb.append(getDeleted());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>threadsCount</column-name><column-value><![CDATA[");
        sb.append(getThreadsCount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastActivityDate</column-name><column-value><![CDATA[");
        sb.append(getLastActivityDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastActivityAuthorId</column-name><column-value><![CDATA[");
        sb.append(getLastActivityAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
