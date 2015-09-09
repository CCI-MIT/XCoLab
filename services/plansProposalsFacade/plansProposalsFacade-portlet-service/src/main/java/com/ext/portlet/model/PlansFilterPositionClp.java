package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlansFilterPositionLocalServiceUtil;
import com.ext.portlet.service.persistence.PlansFilterPositionPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class PlansFilterPositionClp extends BaseModelImpl<PlansFilterPosition>
    implements PlansFilterPosition {
    private long _userId;
    private String _userUuid;
    private long _planTypeId;
    private long _positionId;
    private BaseModel<?> _plansFilterPositionRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PlansFilterPositionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlansFilterPosition.class;
    }

    @Override
    public String getModelClassName() {
        return PlansFilterPosition.class.getName();
    }

    @Override
    public PlansFilterPositionPK getPrimaryKey() {
        return new PlansFilterPositionPK(_userId, _planTypeId, _positionId);
    }

    @Override
    public void setPrimaryKey(PlansFilterPositionPK primaryKey) {
        setUserId(primaryKey.userId);
        setPlanTypeId(primaryKey.planTypeId);
        setPositionId(primaryKey.positionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new PlansFilterPositionPK(_userId, _planTypeId, _positionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlansFilterPositionPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("positionId", getPositionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        Long positionId = (Long) attributes.get("positionId");

        if (positionId != null) {
            setPositionId(positionId);
        }
    }

    @Override
    public long getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(long userId) {
        _userId = userId;

        if (_plansFilterPositionRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterPositionRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_plansFilterPositionRemoteModel, userId);
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
    public long getPlanTypeId() {
        return _planTypeId;
    }

    @Override
    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;

        if (_plansFilterPositionRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterPositionRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanTypeId", long.class);

                method.invoke(_plansFilterPositionRemoteModel, planTypeId);
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

        if (_plansFilterPositionRemoteModel != null) {
            try {
                Class<?> clazz = _plansFilterPositionRemoteModel.getClass();

                Method method = clazz.getMethod("setPositionId", long.class);

                method.invoke(_plansFilterPositionRemoteModel, positionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlansFilterPositionRemoteModel() {
        return _plansFilterPositionRemoteModel;
    }

    public void setPlansFilterPositionRemoteModel(
        BaseModel<?> plansFilterPositionRemoteModel) {
        _plansFilterPositionRemoteModel = plansFilterPositionRemoteModel;
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

        Class<?> remoteModelClass = _plansFilterPositionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_plansFilterPositionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlansFilterPositionLocalServiceUtil.addPlansFilterPosition(this);
        } else {
            PlansFilterPositionLocalServiceUtil.updatePlansFilterPosition(this);
        }
    }

    @Override
    public PlansFilterPosition toEscapedModel() {
        return (PlansFilterPosition) ProxyUtil.newProxyInstance(PlansFilterPosition.class.getClassLoader(),
            new Class[] { PlansFilterPosition.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlansFilterPositionClp clone = new PlansFilterPositionClp();

        clone.setUserId(getUserId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setPositionId(getPositionId());

        return clone;
    }

    @Override
    public int compareTo(PlansFilterPosition plansFilterPosition) {
        PlansFilterPositionPK primaryKey = plansFilterPosition.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlansFilterPositionClp)) {
            return false;
        }

        PlansFilterPositionClp plansFilterPosition = (PlansFilterPositionClp) obj;

        PlansFilterPositionPK primaryKey = plansFilterPosition.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
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
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlansFilterPosition");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
