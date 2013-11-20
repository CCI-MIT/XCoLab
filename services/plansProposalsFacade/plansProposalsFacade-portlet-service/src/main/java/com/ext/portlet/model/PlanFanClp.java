package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanFanLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class PlanFanClp extends BaseModelImpl<PlanFan> implements PlanFan {
    private long _id;
    private long _userId;
    private String _userUuid;
    private long _planId;
    private Date _created;
    private Date _deleted;
    private BaseModel<?> _planFanRemoteModel;

    public PlanFanClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanFan.class;
    }

    @Override
    public String getModelClassName() {
        return PlanFan.class.getName();
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
        attributes.put("userId", getUserId());
        attributes.put("planId", getPlanId());
        attributes.put("created", getCreated());
        attributes.put("deleted", getDeleted());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Date deleted = (Date) attributes.get("deleted");

        if (deleted != null) {
            setDeleted(deleted);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_planFanRemoteModel != null) {
            try {
                Class<?> clazz = _planFanRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_planFanRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_planFanRemoteModel != null) {
            try {
                Class<?> clazz = _planFanRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_planFanRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    @Override
    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    @Override
    public long getPlanId() {
        return _planId;
    }

    @Override
    public void setPlanId(long planId) {
        _planId = planId;

        if (_planFanRemoteModel != null) {
            try {
                Class<?> clazz = _planFanRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planFanRemoteModel, planId);
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

        if (_planFanRemoteModel != null) {
            try {
                Class<?> clazz = _planFanRemoteModel.getClass();

                Method method = clazz.getMethod("setCreated", Date.class);

                method.invoke(_planFanRemoteModel, created);
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

        if (_planFanRemoteModel != null) {
            try {
                Class<?> clazz = _planFanRemoteModel.getClass();

                Method method = clazz.getMethod("setDeleted", Date.class);

                method.invoke(_planFanRemoteModel, deleted);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanFanRemoteModel() {
        return _planFanRemoteModel;
    }

    public void setPlanFanRemoteModel(BaseModel<?> planFanRemoteModel) {
        _planFanRemoteModel = planFanRemoteModel;
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

        Class<?> remoteModelClass = _planFanRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planFanRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanFanLocalServiceUtil.addPlanFan(this);
        } else {
            PlanFanLocalServiceUtil.updatePlanFan(this);
        }
    }

    @Override
    public PlanFan toEscapedModel() {
        return (PlanFan) ProxyUtil.newProxyInstance(PlanFan.class.getClassLoader(),
            new Class[] { PlanFan.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanFanClp clone = new PlanFanClp();

        clone.setId(getId());
        clone.setUserId(getUserId());
        clone.setPlanId(getPlanId());
        clone.setCreated(getCreated());
        clone.setDeleted(getDeleted());

        return clone;
    }

    @Override
    public int compareTo(PlanFan planFan) {
        int value = 0;

        if (getId() < planFan.getId()) {
            value = -1;
        } else if (getId() > planFan.getId()) {
            value = 1;
        } else {
            value = 0;
        }

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

        if (!(obj instanceof PlanFanClp)) {
            return false;
        }

        PlanFanClp planFan = (PlanFanClp) obj;

        long primaryKey = planFan.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", deleted=");
        sb.append(getDeleted());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanFan");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deleted</column-name><column-value><![CDATA[");
        sb.append(getDeleted());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
