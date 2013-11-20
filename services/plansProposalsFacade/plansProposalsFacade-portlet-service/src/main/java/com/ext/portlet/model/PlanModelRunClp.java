package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanModelRunLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PlanModelRunClp extends BaseModelImpl<PlanModelRun>
    implements PlanModelRun {
    private long _id;
    private long _planId;
    private long _scenarioId;
    private long _planVersion;
    private long _version;
    private Date _created;
    private long _updateAuthorId;
    private BaseModel<?> _planModelRunRemoteModel;

    public PlanModelRunClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanModelRun.class;
    }

    @Override
    public String getModelClassName() {
        return PlanModelRun.class.getName();
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
        attributes.put("planId", getPlanId());
        attributes.put("scenarioId", getScenarioId());
        attributes.put("planVersion", getPlanVersion());
        attributes.put("version", getVersion());
        attributes.put("created", getCreated());
        attributes.put("updateAuthorId", getUpdateAuthorId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Long scenarioId = (Long) attributes.get("scenarioId");

        if (scenarioId != null) {
            setScenarioId(scenarioId);
        }

        Long planVersion = (Long) attributes.get("planVersion");

        if (planVersion != null) {
            setPlanVersion(planVersion);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_planModelRunRemoteModel != null) {
            try {
                Class<?> clazz = _planModelRunRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_planModelRunRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanId() {
        return _planId;
    }

    @Override
    public void setPlanId(long planId) {
        _planId = planId;

        if (_planModelRunRemoteModel != null) {
            try {
                Class<?> clazz = _planModelRunRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planModelRunRemoteModel, planId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getScenarioId() {
        return _scenarioId;
    }

    @Override
    public void setScenarioId(long scenarioId) {
        _scenarioId = scenarioId;

        if (_planModelRunRemoteModel != null) {
            try {
                Class<?> clazz = _planModelRunRemoteModel.getClass();

                Method method = clazz.getMethod("setScenarioId", long.class);

                method.invoke(_planModelRunRemoteModel, scenarioId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanVersion() {
        return _planVersion;
    }

    @Override
    public void setPlanVersion(long planVersion) {
        _planVersion = planVersion;

        if (_planModelRunRemoteModel != null) {
            try {
                Class<?> clazz = _planModelRunRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanVersion", long.class);

                method.invoke(_planModelRunRemoteModel, planVersion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getVersion() {
        return _version;
    }

    @Override
    public void setVersion(long version) {
        _version = version;

        if (_planModelRunRemoteModel != null) {
            try {
                Class<?> clazz = _planModelRunRemoteModel.getClass();

                Method method = clazz.getMethod("setVersion", long.class);

                method.invoke(_planModelRunRemoteModel, version);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreated() {
        return _created;
    }

    @Override
    public void setCreated(Date created) {
        _created = created;

        if (_planModelRunRemoteModel != null) {
            try {
                Class<?> clazz = _planModelRunRemoteModel.getClass();

                Method method = clazz.getMethod("setCreated", Date.class);

                method.invoke(_planModelRunRemoteModel, created);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    @Override
    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;

        if (_planModelRunRemoteModel != null) {
            try {
                Class<?> clazz = _planModelRunRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdateAuthorId", long.class);

                method.invoke(_planModelRunRemoteModel, updateAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanModelRunRemoteModel() {
        return _planModelRunRemoteModel;
    }

    public void setPlanModelRunRemoteModel(BaseModel<?> planModelRunRemoteModel) {
        _planModelRunRemoteModel = planModelRunRemoteModel;
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

        Class<?> remoteModelClass = _planModelRunRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planModelRunRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanModelRunLocalServiceUtil.addPlanModelRun(this);
        } else {
            PlanModelRunLocalServiceUtil.updatePlanModelRun(this);
        }
    }

    @Override
    public PlanModelRun toEscapedModel() {
        return (PlanModelRun) ProxyUtil.newProxyInstance(PlanModelRun.class.getClassLoader(),
            new Class[] { PlanModelRun.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanModelRunClp clone = new PlanModelRunClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setScenarioId(getScenarioId());
        clone.setPlanVersion(getPlanVersion());
        clone.setVersion(getVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    @Override
    public int compareTo(PlanModelRun planModelRun) {
        int value = 0;

        if (getId() < planModelRun.getId()) {
            value = -1;
        } else if (getId() > planModelRun.getId()) {
            value = 1;
        } else {
            value = 0;
        }

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

        if (!(obj instanceof PlanModelRunClp)) {
            return false;
        }

        PlanModelRunClp planModelRun = (PlanModelRunClp) obj;

        long primaryKey = planModelRun.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", scenarioId=");
        sb.append(getScenarioId());
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanModelRun");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>scenarioId</column-name><column-value><![CDATA[");
        sb.append(getScenarioId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planVersion</column-name><column-value><![CDATA[");
        sb.append(getPlanVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
