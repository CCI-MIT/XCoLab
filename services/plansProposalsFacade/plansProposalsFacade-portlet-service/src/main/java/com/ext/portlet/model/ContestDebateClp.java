package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestDebateLocalServiceUtil;

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


public class ContestDebateClp extends BaseModelImpl<ContestDebate>
    implements ContestDebate {
    private long _id;
    private long _debateId;
    private long _ContestPK;
    private BaseModel<?> _contestDebateRemoteModel;

    public ContestDebateClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestDebate.class;
    }

    @Override
    public String getModelClassName() {
        return ContestDebate.class.getName();
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
        attributes.put("debateId", getDebateId());
        attributes.put("ContestPK", getContestPK());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long debateId = (Long) attributes.get("debateId");

        if (debateId != null) {
            setDebateId(debateId);
        }

        Long ContestPK = (Long) attributes.get("ContestPK");

        if (ContestPK != null) {
            setContestPK(ContestPK);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_contestDebateRemoteModel != null) {
            try {
                Class<?> clazz = _contestDebateRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_contestDebateRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getDebateId() {
        return _debateId;
    }

    @Override
    public void setDebateId(long debateId) {
        _debateId = debateId;

        if (_contestDebateRemoteModel != null) {
            try {
                Class<?> clazz = _contestDebateRemoteModel.getClass();

                Method method = clazz.getMethod("setDebateId", long.class);

                method.invoke(_contestDebateRemoteModel, debateId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getContestPK() {
        return _ContestPK;
    }

    @Override
    public void setContestPK(long ContestPK) {
        _ContestPK = ContestPK;

        if (_contestDebateRemoteModel != null) {
            try {
                Class<?> clazz = _contestDebateRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPK", long.class);

                method.invoke(_contestDebateRemoteModel, ContestPK);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestDebateRemoteModel() {
        return _contestDebateRemoteModel;
    }

    public void setContestDebateRemoteModel(
        BaseModel<?> contestDebateRemoteModel) {
        _contestDebateRemoteModel = contestDebateRemoteModel;
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

        Class<?> remoteModelClass = _contestDebateRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contestDebateRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestDebateLocalServiceUtil.addContestDebate(this);
        } else {
            ContestDebateLocalServiceUtil.updateContestDebate(this);
        }
    }

    @Override
    public ContestDebate toEscapedModel() {
        return (ContestDebate) ProxyUtil.newProxyInstance(ContestDebate.class.getClassLoader(),
            new Class[] { ContestDebate.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestDebateClp clone = new ContestDebateClp();

        clone.setId(getId());
        clone.setDebateId(getDebateId());
        clone.setContestPK(getContestPK());

        return clone;
    }

    @Override
    public int compareTo(ContestDebate contestDebate) {
        long primaryKey = contestDebate.getPrimaryKey();

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

        if (!(obj instanceof ContestDebateClp)) {
            return false;
        }

        ContestDebateClp contestDebate = (ContestDebateClp) obj;

        long primaryKey = contestDebate.getPrimaryKey();

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

        sb.append("{id=");
        sb.append(getId());
        sb.append(", debateId=");
        sb.append(getDebateId());
        sb.append(", ContestPK=");
        sb.append(getContestPK());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestDebate");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>debateId</column-name><column-value><![CDATA[");
        sb.append(getDebateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPK</column-name><column-value><![CDATA[");
        sb.append(getContestPK());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
