package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanPositionLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanPositionPK;

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


public class PlanPositionClp extends BaseModelImpl<PlanPosition>
    implements PlanPosition {
    private long _planId;
    private long _positionId;
    private long _userId;
    private String _userUuid;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;
    private BaseModel<?> _planPositionRemoteModel;

    public PlanPositionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanPosition.class;
    }

    @Override
    public String getModelClassName() {
        return PlanPosition.class.getName();
    }

    @Override
    public PlanPositionPK getPrimaryKey() {
        return new PlanPositionPK(_planId, _positionId);
    }

    @Override
    public void setPrimaryKey(PlanPositionPK primaryKey) {
        setPlanId(primaryKey.planId);
        setPositionId(primaryKey.positionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new PlanPositionPK(_planId, _positionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanPositionPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planId", getPlanId());
        attributes.put("positionId", getPositionId());
        attributes.put("userId", getUserId());
        attributes.put("userName", getUserName());
        attributes.put("createDate", getCreateDate());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Long positionId = (Long) attributes.get("positionId");

        if (positionId != null) {
            setPositionId(positionId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String userName = (String) attributes.get("userName");

        if (userName != null) {
            setUserName(userName);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    @Override
    public long getPlanId() {
        return _planId;
    }

    @Override
    public void setPlanId(long planId) {
        _planId = planId;

        if (_planPositionRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planPositionRemoteModel, planId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPositionId() {
        return _positionId;
    }

    @Override
    public void setPositionId(long positionId) {
        _positionId = positionId;

        if (_planPositionRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionRemoteModel.getClass();

                Method method = clazz.getMethod("setPositionId", long.class);

                method.invoke(_planPositionRemoteModel, positionId);
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

        if (_planPositionRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_planPositionRemoteModel, userId);
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
    public String getUserName() {
        return _userName;
    }

    @Override
    public void setUserName(String userName) {
        _userName = userName;

        if (_planPositionRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionRemoteModel.getClass();

                Method method = clazz.getMethod("setUserName", String.class);

                method.invoke(_planPositionRemoteModel, userName);
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

        if (_planPositionRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_planPositionRemoteModel, createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_planPositionRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_planPositionRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanPositionRemoteModel() {
        return _planPositionRemoteModel;
    }

    public void setPlanPositionRemoteModel(BaseModel<?> planPositionRemoteModel) {
        _planPositionRemoteModel = planPositionRemoteModel;
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

        Class<?> remoteModelClass = _planPositionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planPositionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanPositionLocalServiceUtil.addPlanPosition(this);
        } else {
            PlanPositionLocalServiceUtil.updatePlanPosition(this);
        }
    }

    @Override
    public PlanPosition toEscapedModel() {
        return (PlanPosition) ProxyUtil.newProxyInstance(PlanPosition.class.getClassLoader(),
            new Class[] { PlanPosition.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanPositionClp clone = new PlanPositionClp();

        clone.setPlanId(getPlanId());
        clone.setPositionId(getPositionId());
        clone.setUserId(getUserId());
        clone.setUserName(getUserName());
        clone.setCreateDate(getCreateDate());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(PlanPosition planPosition) {
        int value = 0;

        if (getPlanId() < planPosition.getPlanId()) {
            value = -1;
        } else if (getPlanId() > planPosition.getPlanId()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (getPositionId() < planPosition.getPositionId()) {
            value = -1;
        } else if (getPositionId() > planPosition.getPositionId()) {
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

        if (!(obj instanceof PlanPositionClp)) {
            return false;
        }

        PlanPositionClp planPosition = (PlanPositionClp) obj;

        PlanPositionPK primaryKey = planPosition.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{planId=");
        sb.append(getPlanId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", userName=");
        sb.append(getUserName());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanPosition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userName</column-name><column-value><![CDATA[");
        sb.append(getUserName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
