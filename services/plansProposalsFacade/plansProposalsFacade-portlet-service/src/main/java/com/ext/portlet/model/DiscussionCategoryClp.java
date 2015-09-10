package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
    private BaseModel<?> _discussionCategoryRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public DiscussionCategoryClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DiscussionCategory.class;
    }

    @Override
    public String getModelClassName() {
        return DiscussionCategory.class.getName();
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
        attributes.put("categoryId", getCategoryId());
        attributes.put("categoryGroupId", getCategoryGroupId());
        attributes.put("authorId", getAuthorId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("createDate", getCreateDate());
        attributes.put("deleted", getDeleted());
        attributes.put("threadsCount", getThreadsCount());
        attributes.put("lastActivityDate", getLastActivityDate());
        attributes.put("lastActivityAuthorId", getLastActivityAuthorId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long pk = (Long) attributes.get("pk");

        if (pk != null) {
            setPk(pk);
        }

        Long categoryId = (Long) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        Long categoryGroupId = (Long) attributes.get("categoryGroupId");

        if (categoryGroupId != null) {
            setCategoryGroupId(categoryGroupId);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date deleted = (Date) attributes.get("deleted");

        if (deleted != null) {
            setDeleted(deleted);
        }

        Integer threadsCount = (Integer) attributes.get("threadsCount");

        if (threadsCount != null) {
            setThreadsCount(threadsCount);
        }

        Date lastActivityDate = (Date) attributes.get("lastActivityDate");

        if (lastActivityDate != null) {
            setLastActivityDate(lastActivityDate);
        }

        Long lastActivityAuthorId = (Long) attributes.get(
                "lastActivityAuthorId");

        if (lastActivityAuthorId != null) {
            setLastActivityAuthorId(lastActivityAuthorId);
        }
    }

    @Override
    public long getPk() {
        return _pk;
    }

    @Override
    public void setPk(long pk) {
        _pk = pk;

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setPk", long.class);

                method.invoke(_discussionCategoryRemoteModel, pk);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCategoryId() {
        return _categoryId;
    }

    @Override
    public void setCategoryId(long categoryId) {
        _categoryId = categoryId;

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryId", long.class);

                method.invoke(_discussionCategoryRemoteModel, categoryId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCategoryGroupId() {
        return _categoryGroupId;
    }

    @Override
    public void setCategoryGroupId(long categoryGroupId) {
        _categoryGroupId = categoryGroupId;

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setCategoryGroupId", long.class);

                method.invoke(_discussionCategoryRemoteModel, categoryGroupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getAuthorId() {
        return _authorId;
    }

    @Override
    public void setAuthorId(long authorId) {
        _authorId = authorId;

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorId", long.class);

                method.invoke(_discussionCategoryRemoteModel, authorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_discussionCategoryRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_discussionCategoryRemoteModel, description);
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

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_discussionCategoryRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getDeleted() {
        return _deleted;
    }

    @Override
    public void setDeleted(Date deleted) {
        _deleted = deleted;

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setDeleted", Date.class);

                method.invoke(_discussionCategoryRemoteModel, deleted);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getThreadsCount() {
        return _threadsCount;
    }

    @Override
    public void setThreadsCount(int threadsCount) {
        _threadsCount = threadsCount;

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setThreadsCount", int.class);

                method.invoke(_discussionCategoryRemoteModel, threadsCount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastActivityDate() {
        return _lastActivityDate;
    }

    @Override
    public void setLastActivityDate(Date lastActivityDate) {
        _lastActivityDate = lastActivityDate;

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setLastActivityDate",
                        Date.class);

                method.invoke(_discussionCategoryRemoteModel, lastActivityDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getLastActivityAuthorId() {
        return _lastActivityAuthorId;
    }

    @Override
    public void setLastActivityAuthorId(long lastActivityAuthorId) {
        _lastActivityAuthorId = lastActivityAuthorId;

        if (_discussionCategoryRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryRemoteModel.getClass();

                Method method = clazz.getMethod("setLastActivityAuthorId",
                        long.class);

                method.invoke(_discussionCategoryRemoteModel,
                    lastActivityAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDiscussionCategoryRemoteModel() {
        return _discussionCategoryRemoteModel;
    }

    public void setDiscussionCategoryRemoteModel(
        BaseModel<?> discussionCategoryRemoteModel) {
        _discussionCategoryRemoteModel = discussionCategoryRemoteModel;
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

        Class<?> remoteModelClass = _discussionCategoryRemoteModel.getClass();

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

        Object returnValue = method.invoke(_discussionCategoryRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DiscussionCategoryLocalServiceUtil.addDiscussionCategory(this);
        } else {
            DiscussionCategoryLocalServiceUtil.updateDiscussionCategory(this);
        }
    }

    @Override
    public DiscussionCategory toEscapedModel() {
        return (DiscussionCategory) ProxyUtil.newProxyInstance(DiscussionCategory.class.getClassLoader(),
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

    @Override
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DiscussionCategoryClp)) {
            return false;
        }

        DiscussionCategoryClp discussionCategory = (DiscussionCategoryClp) obj;

        long primaryKey = discussionCategory.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    public Class<?> getClpSerializerClass() {
        return _clpSerializerClass;
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

    @Override
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
