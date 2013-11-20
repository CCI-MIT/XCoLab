package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanPositionsLocalServiceUtil;

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


public class PlanPositionsClp extends BaseModelImpl<PlanPositions>
    implements PlanPositions {
    private long _id;
    private long _planId;
    private long _planVersion;
    private long _version;
    private Date _created;
    private long _updateAuthorId;
    private BaseModel<?> _planPositionsRemoteModel;

    public PlanPositionsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanPositions.class;
    }

    @Override
    public String getModelClassName() {
        return PlanPositions.class.getName();
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

        if (_planPositionsRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionsRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_planPositionsRemoteModel, id);
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

        if (_planPositionsRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionsRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planPositionsRemoteModel, planId);
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

        if (_planPositionsRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionsRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanVersion", long.class);

                method.invoke(_planPositionsRemoteModel, planVersion);
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

        if (_planPositionsRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionsRemoteModel.getClass();

                Method method = clazz.getMethod("setVersion", long.class);

                method.invoke(_planPositionsRemoteModel, version);
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

        if (_planPositionsRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreated", Date.class);

                method.invoke(_planPositionsRemoteModel, created);
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

        if (_planPositionsRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionsRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdateAuthorId", long.class);

                method.invoke(_planPositionsRemoteModel, updateAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanPositionsRemoteModel() {
        return _planPositionsRemoteModel;
    }

    public void setPlanPositionsRemoteModel(
        BaseModel<?> planPositionsRemoteModel) {
        _planPositionsRemoteModel = planPositionsRemoteModel;
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

        Class<?> remoteModelClass = _planPositionsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planPositionsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanPositionsLocalServiceUtil.addPlanPositions(this);
        } else {
            PlanPositionsLocalServiceUtil.updatePlanPositions(this);
        }
    }

    @Override
    public PlanPositions toEscapedModel() {
        return (PlanPositions) ProxyUtil.newProxyInstance(PlanPositions.class.getClassLoader(),
            new Class[] { PlanPositions.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanPositionsClp clone = new PlanPositionsClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setPlanVersion(getPlanVersion());
        clone.setVersion(getVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    @Override
    public int compareTo(PlanPositions planPositions) {
        int value = 0;

        if (getId() < planPositions.getId()) {
            value = -1;
        } else if (getId() > planPositions.getId()) {
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

        if (!(obj instanceof PlanPositionsClp)) {
            return false;
        }

        PlanPositionsClp planPositions = (PlanPositionsClp) obj;

        long primaryKey = planPositions.getPrimaryKey();

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

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
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
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanPositions");
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
