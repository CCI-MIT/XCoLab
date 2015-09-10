package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class DiscussionCategoryGroupClp extends BaseModelImpl<DiscussionCategoryGroup>
    implements DiscussionCategoryGroup {
    private long _id;
    private String _description;
    private String _url;
    private long _commentsThread;
    private boolean _isQuiet;
    private BaseModel<?> _discussionCategoryGroupRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public DiscussionCategoryGroupClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DiscussionCategoryGroup.class;
    }

    @Override
    public String getModelClassName() {
        return DiscussionCategoryGroup.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _id;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("description", getDescription());
        attributes.put("url", getUrl());
        attributes.put("commentsThread", getCommentsThread());
        attributes.put("isQuiet", getIsQuiet());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String url = (String) attributes.get("url");

        if (url != null) {
            setUrl(url);
        }

        Long commentsThread = (Long) attributes.get("commentsThread");

        if (commentsThread != null) {
            setCommentsThread(commentsThread);
        }

        Boolean isQuiet = (Boolean) attributes.get("isQuiet");

        if (isQuiet != null) {
            setIsQuiet(isQuiet);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_discussionCategoryGroupRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_discussionCategoryGroupRemoteModel, id);
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

        if (_discussionCategoryGroupRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_discussionCategoryGroupRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUrl() {
        return _url;
    }

    @Override
    public void setUrl(String url) {
        _url = url;

        if (_discussionCategoryGroupRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setUrl", String.class);

                method.invoke(_discussionCategoryGroupRemoteModel, url);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCommentsThread() {
        return _commentsThread;
    }

    @Override
    public void setCommentsThread(long commentsThread) {
        _commentsThread = commentsThread;

        if (_discussionCategoryGroupRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setCommentsThread", long.class);

                method.invoke(_discussionCategoryGroupRemoteModel,
                    commentsThread);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getIsQuiet() {
        return _isQuiet;
    }

    @Override
    public boolean isIsQuiet() {
        return _isQuiet;
    }

    @Override
    public void setIsQuiet(boolean isQuiet) {
        _isQuiet = isQuiet;

        if (_discussionCategoryGroupRemoteModel != null) {
            try {
                Class<?> clazz = _discussionCategoryGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setIsQuiet", boolean.class);

                method.invoke(_discussionCategoryGroupRemoteModel, isQuiet);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDiscussionCategoryGroupRemoteModel() {
        return _discussionCategoryGroupRemoteModel;
    }

    public void setDiscussionCategoryGroupRemoteModel(
        BaseModel<?> discussionCategoryGroupRemoteModel) {
        _discussionCategoryGroupRemoteModel = discussionCategoryGroupRemoteModel;
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

        Class<?> remoteModelClass = _discussionCategoryGroupRemoteModel.getClass();

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

        Object returnValue = method.invoke(_discussionCategoryGroupRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DiscussionCategoryGroupLocalServiceUtil.addDiscussionCategoryGroup(this);
        } else {
            DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(this);
        }
    }

    @Override
    public DiscussionCategoryGroup toEscapedModel() {
        return (DiscussionCategoryGroup) ProxyUtil.newProxyInstance(DiscussionCategoryGroup.class.getClassLoader(),
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
        clone.setIsQuiet(getIsQuiet());

        return clone;
    }

    @Override
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DiscussionCategoryGroupClp)) {
            return false;
        }

        DiscussionCategoryGroupClp discussionCategoryGroup = (DiscussionCategoryGroupClp) obj;

        long primaryKey = discussionCategoryGroup.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", url=");
        sb.append(getUrl());
        sb.append(", commentsThread=");
        sb.append(getCommentsThread());
        sb.append(", isQuiet=");
        sb.append(getIsQuiet());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

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
        sb.append(
            "<column><column-name>isQuiet</column-name><column-value><![CDATA[");
        sb.append(getIsQuiet());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
