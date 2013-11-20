package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanColumnSettingsLocalServiceUtil;

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


public class PlanColumnSettingsClp extends BaseModelImpl<PlanColumnSettings>
    implements PlanColumnSettings {
    private long _planColumnSettingsId;
    private String _columnName;
    private long _planUserSettingsId;
    private boolean _visible;
    private BaseModel<?> _planColumnSettingsRemoteModel;

    public PlanColumnSettingsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanColumnSettings.class;
    }

    @Override
    public String getModelClassName() {
        return PlanColumnSettings.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _planColumnSettingsId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPlanColumnSettingsId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _planColumnSettingsId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planColumnSettingsId", getPlanColumnSettingsId());
        attributes.put("columnName", getColumnName());
        attributes.put("planUserSettingsId", getPlanUserSettingsId());
        attributes.put("visible", getVisible());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planColumnSettingsId = (Long) attributes.get(
                "planColumnSettingsId");

        if (planColumnSettingsId != null) {
            setPlanColumnSettingsId(planColumnSettingsId);
        }

        String columnName = (String) attributes.get("columnName");

        if (columnName != null) {
            setColumnName(columnName);
        }

        Long planUserSettingsId = (Long) attributes.get("planUserSettingsId");

        if (planUserSettingsId != null) {
            setPlanUserSettingsId(planUserSettingsId);
        }

        Boolean visible = (Boolean) attributes.get("visible");

        if (visible != null) {
            setVisible(visible);
        }
    }

    @Override
    public long getPlanColumnSettingsId() {
        return _planColumnSettingsId;
    }

    @Override
    public void setPlanColumnSettingsId(long planColumnSettingsId) {
        _planColumnSettingsId = planColumnSettingsId;

        if (_planColumnSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _planColumnSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanColumnSettingsId",
                        long.class);

                method.invoke(_planColumnSettingsRemoteModel,
                    planColumnSettingsId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getColumnName() {
        return _columnName;
    }

    @Override
    public void setColumnName(String columnName) {
        _columnName = columnName;

        if (_planColumnSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _planColumnSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setColumnName", String.class);

                method.invoke(_planColumnSettingsRemoteModel, columnName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    @Override
    public void setPlanUserSettingsId(long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;

        if (_planColumnSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _planColumnSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanUserSettingsId",
                        long.class);

                method.invoke(_planColumnSettingsRemoteModel, planUserSettingsId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getVisible() {
        return _visible;
    }

    @Override
    public boolean isVisible() {
        return _visible;
    }

    @Override
    public void setVisible(boolean visible) {
        _visible = visible;

        if (_planColumnSettingsRemoteModel != null) {
            try {
                Class<?> clazz = _planColumnSettingsRemoteModel.getClass();

                Method method = clazz.getMethod("setVisible", boolean.class);

                method.invoke(_planColumnSettingsRemoteModel, visible);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanColumnSettingsRemoteModel() {
        return _planColumnSettingsRemoteModel;
    }

    public void setPlanColumnSettingsRemoteModel(
        BaseModel<?> planColumnSettingsRemoteModel) {
        _planColumnSettingsRemoteModel = planColumnSettingsRemoteModel;
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

        Class<?> remoteModelClass = _planColumnSettingsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planColumnSettingsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanColumnSettingsLocalServiceUtil.addPlanColumnSettings(this);
        } else {
            PlanColumnSettingsLocalServiceUtil.updatePlanColumnSettings(this);
        }
    }

    @Override
    public PlanColumnSettings toEscapedModel() {
        return (PlanColumnSettings) ProxyUtil.newProxyInstance(PlanColumnSettings.class.getClassLoader(),
            new Class[] { PlanColumnSettings.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanColumnSettingsClp clone = new PlanColumnSettingsClp();

        clone.setPlanColumnSettingsId(getPlanColumnSettingsId());
        clone.setColumnName(getColumnName());
        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setVisible(getVisible());

        return clone;
    }

    @Override
    public int compareTo(PlanColumnSettings planColumnSettings) {
        long primaryKey = planColumnSettings.getPrimaryKey();

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

        if (!(obj instanceof PlanColumnSettingsClp)) {
            return false;
        }

        PlanColumnSettingsClp planColumnSettings = (PlanColumnSettingsClp) obj;

        long primaryKey = planColumnSettings.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{planColumnSettingsId=");
        sb.append(getPlanColumnSettingsId());
        sb.append(", columnName=");
        sb.append(getColumnName());
        sb.append(", planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", visible=");
        sb.append(getVisible());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanColumnSettings");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planColumnSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanColumnSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnName</column-name><column-value><![CDATA[");
        sb.append(getColumnName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visible</column-name><column-value><![CDATA[");
        sb.append(getVisible());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
