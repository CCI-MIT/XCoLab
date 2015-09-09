package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanTeamHistoryLocalServiceUtil;

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


public class PlanTeamHistoryClp extends BaseModelImpl<PlanTeamHistory>
    implements PlanTeamHistory {
    private long _id;
    private long _planId;
    private long _userId;
    private String _userUuid;
    private String _action;
    private String _payload;
    private Date _created;
    private long _updateAuthorId;
    private BaseModel<?> _planTeamHistoryRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PlanTeamHistoryClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanTeamHistory.class;
    }

    @Override
    public String getModelClassName() {
        return PlanTeamHistory.class.getName();
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
        attributes.put("userId", getUserId());
        attributes.put("action", getAction());
        attributes.put("payload", getPayload());
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

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String action = (String) attributes.get("action");

        if (action != null) {
            setAction(action);
        }

        String payload = (String) attributes.get("payload");

        if (payload != null) {
            setPayload(payload);
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

        if (_planTeamHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _planTeamHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_planTeamHistoryRemoteModel, id);
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

        if (_planTeamHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _planTeamHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planTeamHistoryRemoteModel, planId);
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

        if (_planTeamHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _planTeamHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_planTeamHistoryRemoteModel, userId);
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
    public String getAction() {
        return _action;
    }

    @Override
    public void setAction(String action) {
        _action = action;

        if (_planTeamHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _planTeamHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setAction", String.class);

                method.invoke(_planTeamHistoryRemoteModel, action);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPayload() {
        return _payload;
    }

    @Override
    public void setPayload(String payload) {
        _payload = payload;

        if (_planTeamHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _planTeamHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setPayload", String.class);

                method.invoke(_planTeamHistoryRemoteModel, payload);
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

        if (_planTeamHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _planTeamHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setCreated", Date.class);

                method.invoke(_planTeamHistoryRemoteModel, created);
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

        if (_planTeamHistoryRemoteModel != null) {
            try {
                Class<?> clazz = _planTeamHistoryRemoteModel.getClass();

                Method method = clazz.getMethod("setUpdateAuthorId", long.class);

                method.invoke(_planTeamHistoryRemoteModel, updateAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanTeamHistoryRemoteModel() {
        return _planTeamHistoryRemoteModel;
    }

    public void setPlanTeamHistoryRemoteModel(
        BaseModel<?> planTeamHistoryRemoteModel) {
        _planTeamHistoryRemoteModel = planTeamHistoryRemoteModel;
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

        Class<?> remoteModelClass = _planTeamHistoryRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planTeamHistoryRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTeamHistoryLocalServiceUtil.addPlanTeamHistory(this);
        } else {
            PlanTeamHistoryLocalServiceUtil.updatePlanTeamHistory(this);
        }
    }

    @Override
    public PlanTeamHistory toEscapedModel() {
        return (PlanTeamHistory) ProxyUtil.newProxyInstance(PlanTeamHistory.class.getClassLoader(),
            new Class[] { PlanTeamHistory.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTeamHistoryClp clone = new PlanTeamHistoryClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setUserId(getUserId());
        clone.setAction(getAction());
        clone.setPayload(getPayload());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    @Override
    public int compareTo(PlanTeamHistory planTeamHistory) {
        int value = 0;

        if (getId() < planTeamHistory.getId()) {
            value = -1;
        } else if (getId() > planTeamHistory.getId()) {
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

        if (!(obj instanceof PlanTeamHistoryClp)) {
            return false;
        }

        PlanTeamHistoryClp planTeamHistory = (PlanTeamHistoryClp) obj;

        long primaryKey = planTeamHistory.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", action=");
        sb.append(getAction());
        sb.append(", payload=");
        sb.append(getPayload());
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
        sb.append("com.ext.portlet.model.PlanTeamHistory");
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
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>action</column-name><column-value><![CDATA[");
        sb.append(getAction());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>payload</column-name><column-value><![CDATA[");
        sb.append(getPayload());
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
