package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestDiscussionLocalServiceUtil;

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


public class ContestDiscussionClp extends BaseModelImpl<ContestDiscussion>
    implements ContestDiscussion {
    private long _DiscussionId;
    private long _ContestId;
    private String _Tab;
    private BaseModel<?> _contestDiscussionRemoteModel;

    public ContestDiscussionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestDiscussion.class;
    }

    @Override
    public String getModelClassName() {
        return ContestDiscussion.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _DiscussionId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setDiscussionId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _DiscussionId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("DiscussionId", getDiscussionId());
        attributes.put("ContestId", getContestId());
        attributes.put("Tab", getTab());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long DiscussionId = (Long) attributes.get("DiscussionId");

        if (DiscussionId != null) {
            setDiscussionId(DiscussionId);
        }

        Long ContestId = (Long) attributes.get("ContestId");

        if (ContestId != null) {
            setContestId(ContestId);
        }

        String Tab = (String) attributes.get("Tab");

        if (Tab != null) {
            setTab(Tab);
        }
    }

    @Override
    public long getDiscussionId() {
        return _DiscussionId;
    }

    @Override
    public void setDiscussionId(long DiscussionId) {
        _DiscussionId = DiscussionId;

        if (_contestDiscussionRemoteModel != null) {
            try {
                Class<?> clazz = _contestDiscussionRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscussionId", long.class);

                method.invoke(_contestDiscussionRemoteModel, DiscussionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getContestId() {
        return _ContestId;
    }

    @Override
    public void setContestId(long ContestId) {
        _ContestId = ContestId;

        if (_contestDiscussionRemoteModel != null) {
            try {
                Class<?> clazz = _contestDiscussionRemoteModel.getClass();

                Method method = clazz.getMethod("setContestId", long.class);

                method.invoke(_contestDiscussionRemoteModel, ContestId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTab() {
        return _Tab;
    }

    @Override
    public void setTab(String Tab) {
        _Tab = Tab;

        if (_contestDiscussionRemoteModel != null) {
            try {
                Class<?> clazz = _contestDiscussionRemoteModel.getClass();

                Method method = clazz.getMethod("setTab", String.class);

                method.invoke(_contestDiscussionRemoteModel, Tab);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestDiscussionRemoteModel() {
        return _contestDiscussionRemoteModel;
    }

    public void setContestDiscussionRemoteModel(
        BaseModel<?> contestDiscussionRemoteModel) {
        _contestDiscussionRemoteModel = contestDiscussionRemoteModel;
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

        Class<?> remoteModelClass = _contestDiscussionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contestDiscussionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestDiscussionLocalServiceUtil.addContestDiscussion(this);
        } else {
            ContestDiscussionLocalServiceUtil.updateContestDiscussion(this);
        }
    }

    @Override
    public ContestDiscussion toEscapedModel() {
        return (ContestDiscussion) ProxyUtil.newProxyInstance(ContestDiscussion.class.getClassLoader(),
            new Class[] { ContestDiscussion.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestDiscussionClp clone = new ContestDiscussionClp();

        clone.setDiscussionId(getDiscussionId());
        clone.setContestId(getContestId());
        clone.setTab(getTab());

        return clone;
    }

    @Override
    public int compareTo(ContestDiscussion contestDiscussion) {
        long primaryKey = contestDiscussion.getPrimaryKey();

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

        if (!(obj instanceof ContestDiscussionClp)) {
            return false;
        }

        ContestDiscussionClp contestDiscussion = (ContestDiscussionClp) obj;

        long primaryKey = contestDiscussion.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{DiscussionId=");
        sb.append(getDiscussionId());
        sb.append(", ContestId=");
        sb.append(getContestId());
        sb.append(", Tab=");
        sb.append(getTab());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestDiscussion");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>DiscussionId</column-name><column-value><![CDATA[");
        sb.append(getDiscussionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestId</column-name><column-value><![CDATA[");
        sb.append(getContestId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>Tab</column-name><column-value><![CDATA[");
        sb.append(getTab());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
