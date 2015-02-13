package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestScheduleLocalServiceUtil;

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


public class ContestScheduleClp extends BaseModelImpl<ContestSchedule>
    implements ContestSchedule {
    private long _ContestSchedulePK;
    private long _ContestPK;
    private String _name;
    private String _description;
    private String _status;
    private boolean _invisible;
    private BaseModel<?> _contestScheduleRemoteModel;

    public ContestScheduleClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContestSchedule.class;
    }

    @Override
    public String getModelClassName() {
        return ContestSchedule.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _ContestSchedulePK;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setContestSchedulePK(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ContestSchedulePK;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ContestSchedulePK", getContestSchedulePK());
        attributes.put("ContestPK", getContestPK());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("status", getStatus());
        attributes.put("invisible", getInvisible());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long ContestSchedulePK = (Long) attributes.get("ContestSchedulePK");

        if (ContestSchedulePK != null) {
            setContestSchedulePK(ContestSchedulePK);
        }

        Long ContestPK = (Long) attributes.get("ContestPK");

        if (ContestPK != null) {
            setContestPK(ContestPK);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Boolean invisible = (Boolean) attributes.get("invisible");

        if (invisible != null) {
            setInvisible(invisible);
        }
    }

    @Override
    public long getContestSchedulePK() {
        return _ContestSchedulePK;
    }

    @Override
    public void setContestSchedulePK(long ContestSchedulePK) {
        _ContestSchedulePK = ContestSchedulePK;

        if (_contestScheduleRemoteModel != null) {
            try {
                Class<?> clazz = _contestScheduleRemoteModel.getClass();

                Method method = clazz.getMethod("setContestSchedulePK",
                        long.class);

                method.invoke(_contestScheduleRemoteModel, ContestSchedulePK);
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

        if (_contestScheduleRemoteModel != null) {
            try {
                Class<?> clazz = _contestScheduleRemoteModel.getClass();

                Method method = clazz.getMethod("setContestPK", long.class);

                method.invoke(_contestScheduleRemoteModel, ContestPK);
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

        if (_contestScheduleRemoteModel != null) {
            try {
                Class<?> clazz = _contestScheduleRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_contestScheduleRemoteModel, name);
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

        if (_contestScheduleRemoteModel != null) {
            try {
                Class<?> clazz = _contestScheduleRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_contestScheduleRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStatus() {
        return _status;
    }

    @Override
    public void setStatus(String status) {
        _status = status;

        if (_contestScheduleRemoteModel != null) {
            try {
                Class<?> clazz = _contestScheduleRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", String.class);

                method.invoke(_contestScheduleRemoteModel, status);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getInvisible() {
        return _invisible;
    }

    @Override
    public boolean isInvisible() {
        return _invisible;
    }

    @Override
    public void setInvisible(boolean invisible) {
        _invisible = invisible;

        if (_contestScheduleRemoteModel != null) {
            try {
                Class<?> clazz = _contestScheduleRemoteModel.getClass();

                Method method = clazz.getMethod("setInvisible", boolean.class);

                method.invoke(_contestScheduleRemoteModel, invisible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContestScheduleRemoteModel() {
        return _contestScheduleRemoteModel;
    }

    public void setContestScheduleRemoteModel(
        BaseModel<?> contestScheduleRemoteModel) {
        _contestScheduleRemoteModel = contestScheduleRemoteModel;
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

        Class<?> remoteModelClass = _contestScheduleRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contestScheduleRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestScheduleLocalServiceUtil.addContestSchedule(this);
        } else {
            ContestScheduleLocalServiceUtil.updateContestSchedule(this);
        }
    }

    @Override
    public ContestSchedule toEscapedModel() {
        return (ContestSchedule) ProxyUtil.newProxyInstance(ContestSchedule.class.getClassLoader(),
            new Class[] { ContestSchedule.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestScheduleClp clone = new ContestScheduleClp();

        clone.setContestSchedulePK(getContestSchedulePK());
        clone.setContestPK(getContestPK());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setStatus(getStatus());
        clone.setInvisible(getInvisible());

        return clone;
    }

    @Override
    public int compareTo(ContestSchedule contestSchedule) {
        long primaryKey = contestSchedule.getPrimaryKey();

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

        if (!(obj instanceof ContestScheduleClp)) {
            return false;
        }

        ContestScheduleClp contestSchedule = (ContestScheduleClp) obj;

        long primaryKey = contestSchedule.getPrimaryKey();

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

        sb.append("{ContestSchedulePK=");
        sb.append(getContestSchedulePK());
        sb.append(", ContestPK=");
        sb.append(getContestPK());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", invisible=");
        sb.append(getInvisible());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ContestSchedule");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ContestSchedulePK</column-name><column-value><![CDATA[");
        sb.append(getContestSchedulePK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPK</column-name><column-value><![CDATA[");
        sb.append(getContestPK());
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
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invisible</column-name><column-value><![CDATA[");
        sb.append(getInvisible());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
