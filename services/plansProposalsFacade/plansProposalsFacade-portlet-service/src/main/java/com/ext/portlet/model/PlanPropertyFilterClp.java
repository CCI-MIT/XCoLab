package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanPropertyFilterLocalServiceUtil;

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


public class PlanPropertyFilterClp extends BaseModelImpl<PlanPropertyFilter>
    implements PlanPropertyFilter {
    private long _planPropertyFilterId;
    private String _propertyName;
    private long _planUserSettingsId;
    private String _value;
    private BaseModel<?> _planPropertyFilterRemoteModel;

    public PlanPropertyFilterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanPropertyFilter.class;
    }

    @Override
    public String getModelClassName() {
        return PlanPropertyFilter.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _planPropertyFilterId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPlanPropertyFilterId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _planPropertyFilterId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planPropertyFilterId", getPlanPropertyFilterId());
        attributes.put("propertyName", getPropertyName());
        attributes.put("planUserSettingsId", getPlanUserSettingsId());
        attributes.put("value", getValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planPropertyFilterId = (Long) attributes.get(
                "planPropertyFilterId");

        if (planPropertyFilterId != null) {
            setPlanPropertyFilterId(planPropertyFilterId);
        }

        String propertyName = (String) attributes.get("propertyName");

        if (propertyName != null) {
            setPropertyName(propertyName);
        }

        Long planUserSettingsId = (Long) attributes.get("planUserSettingsId");

        if (planUserSettingsId != null) {
            setPlanUserSettingsId(planUserSettingsId);
        }

        String value = (String) attributes.get("value");

        if (value != null) {
            setValue(value);
        }
    }

    @Override
    public long getPlanPropertyFilterId() {
        return _planPropertyFilterId;
    }

    @Override
    public void setPlanPropertyFilterId(long planPropertyFilterId) {
        _planPropertyFilterId = planPropertyFilterId;

        if (_planPropertyFilterRemoteModel != null) {
            try {
                Class<?> clazz = _planPropertyFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanPropertyFilterId",
                        long.class);

                method.invoke(_planPropertyFilterRemoteModel,
                    planPropertyFilterId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPropertyName() {
        return _propertyName;
    }

    @Override
    public void setPropertyName(String propertyName) {
        _propertyName = propertyName;

        if (_planPropertyFilterRemoteModel != null) {
            try {
                Class<?> clazz = _planPropertyFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setPropertyName", String.class);

                method.invoke(_planPropertyFilterRemoteModel, propertyName);
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

        if (_planPropertyFilterRemoteModel != null) {
            try {
                Class<?> clazz = _planPropertyFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanUserSettingsId",
                        long.class);

                method.invoke(_planPropertyFilterRemoteModel, planUserSettingsId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getValue() {
        return _value;
    }

    @Override
    public void setValue(String value) {
        _value = value;

        if (_planPropertyFilterRemoteModel != null) {
            try {
                Class<?> clazz = _planPropertyFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setValue", String.class);

                method.invoke(_planPropertyFilterRemoteModel, value);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanPropertyFilterRemoteModel() {
        return _planPropertyFilterRemoteModel;
    }

    public void setPlanPropertyFilterRemoteModel(
        BaseModel<?> planPropertyFilterRemoteModel) {
        _planPropertyFilterRemoteModel = planPropertyFilterRemoteModel;
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

        Class<?> remoteModelClass = _planPropertyFilterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planPropertyFilterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanPropertyFilterLocalServiceUtil.addPlanPropertyFilter(this);
        } else {
            PlanPropertyFilterLocalServiceUtil.updatePlanPropertyFilter(this);
        }
    }

    @Override
    public PlanPropertyFilter toEscapedModel() {
        return (PlanPropertyFilter) ProxyUtil.newProxyInstance(PlanPropertyFilter.class.getClassLoader(),
            new Class[] { PlanPropertyFilter.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanPropertyFilterClp clone = new PlanPropertyFilterClp();

        clone.setPlanPropertyFilterId(getPlanPropertyFilterId());
        clone.setPropertyName(getPropertyName());
        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setValue(getValue());

        return clone;
    }

    @Override
    public int compareTo(PlanPropertyFilter planPropertyFilter) {
        long primaryKey = planPropertyFilter.getPrimaryKey();

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

        if (!(obj instanceof PlanPropertyFilterClp)) {
            return false;
        }

        PlanPropertyFilterClp planPropertyFilter = (PlanPropertyFilterClp) obj;

        long primaryKey = planPropertyFilter.getPrimaryKey();

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

        sb.append("{planPropertyFilterId=");
        sb.append(getPlanPropertyFilterId());
        sb.append(", propertyName=");
        sb.append(getPropertyName());
        sb.append(", planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", value=");
        sb.append(getValue());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanPropertyFilter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planPropertyFilterId</column-name><column-value><![CDATA[");
        sb.append(getPlanPropertyFilterId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>propertyName</column-name><column-value><![CDATA[");
        sb.append(getPropertyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>value</column-name><column-value><![CDATA[");
        sb.append(getValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
