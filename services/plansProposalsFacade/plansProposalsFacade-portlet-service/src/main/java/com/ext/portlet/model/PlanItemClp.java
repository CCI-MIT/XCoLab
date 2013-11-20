package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanItemLocalServiceUtil;

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


public class PlanItemClp extends BaseModelImpl<PlanItem> implements PlanItem {
    private long _id;
    private long _planId;
    private String _state;
    private Date _updated;
    private long _updateAuthorId;
    private String _updateType;
    private long _version;
    private BaseModel<?> _planItemRemoteModel;

    public PlanItemClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanItem.class;
    }

    @Override
    public String getModelClassName() {
        return PlanItem.class.getName();
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
        attributes.put("state", getState());
        attributes.put("updated", getUpdated());
        attributes.put("updateAuthorId", getUpdateAuthorId());
        attributes.put("updateType", getUpdateType());
        attributes.put("version", getVersion());

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

        String state = (String) attributes.get("state");

        if (state != null) {
            setState(state);
        }

        Date updated = (Date) attributes.get("updated");

        if (updated != null) {
            setUpdated(updated);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }

        String updateType = (String) attributes.get("updateType");

        if (updateType != null) {
            setUpdateType(updateType);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_planItemRemoteModel != null) {
            try {
                Class<?> clazz = _planItemRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_planItemRemoteModel, id);
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

        if (_planItemRemoteModel != null) {
            try {
                Class<?> clazz = _planItemRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planItemRemoteModel, planId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getState() {
        return _state;
    }

    @Override
    public void setState(String state) {
        _state = state;

        if (_planItemRemoteModel != null) {
            try {
                Class<?> clazz = _planItemRemoteModel.getClass();

                Method method = clazz.getMethod("setState", String.class);

                method.invoke(_planItemRemoteModel, state);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getUpdated() {
        return _updated;
    }

    @Override
    public void setUpdated(Date updated) {
        _updated = updated;

        if (_planItemRemoteModel != null) {
            try {
                Class<?> clazz = _planItemRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdated", Date.class);

                method.invoke(_planItemRemoteModel, updated);
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

        if (_planItemRemoteModel != null) {
            try {
                Class<?> clazz = _planItemRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdateAuthorId", long.class);

                method.invoke(_planItemRemoteModel, updateAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUpdateType() {
        return _updateType;
    }

    @Override
    public void setUpdateType(String updateType) {
        _updateType = updateType;

        if (_planItemRemoteModel != null) {
            try {
                Class<?> clazz = _planItemRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdateType", String.class);

                method.invoke(_planItemRemoteModel, updateType);
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

        if (_planItemRemoteModel != null) {
            try {
                Class<?> clazz = _planItemRemoteModel.getClass();

                Method method = clazz.getMethod("setVersion", long.class);

                method.invoke(_planItemRemoteModel, version);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanItemRemoteModel() {
        return _planItemRemoteModel;
    }

    public void setPlanItemRemoteModel(BaseModel<?> planItemRemoteModel) {
        _planItemRemoteModel = planItemRemoteModel;
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

        Class<?> remoteModelClass = _planItemRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planItemRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanItemLocalServiceUtil.addPlanItem(this);
        } else {
            PlanItemLocalServiceUtil.updatePlanItem(this);
        }
    }

    @Override
    public PlanItem toEscapedModel() {
        return (PlanItem) ProxyUtil.newProxyInstance(PlanItem.class.getClassLoader(),
            new Class[] { PlanItem.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanItemClp clone = new PlanItemClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setState(getState());
        clone.setUpdated(getUpdated());
        clone.setUpdateAuthorId(getUpdateAuthorId());
        clone.setUpdateType(getUpdateType());
        clone.setVersion(getVersion());

        return clone;
    }

    @Override
    public int compareTo(PlanItem planItem) {
        int value = 0;

        if (getId() < planItem.getId()) {
            value = -1;
        } else if (getId() > planItem.getId()) {
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

        if (!(obj instanceof PlanItemClp)) {
            return false;
        }

        PlanItemClp planItem = (PlanItemClp) obj;

        long primaryKey = planItem.getPrimaryKey();

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
        sb.append(", state=");
        sb.append(getState());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append(", updateType=");
        sb.append(getUpdateType());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanItem");
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
            "<column><column-name>state</column-name><column-value><![CDATA[");
        sb.append(getState());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateType</column-name><column-value><![CDATA[");
        sb.append(getUpdateType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
