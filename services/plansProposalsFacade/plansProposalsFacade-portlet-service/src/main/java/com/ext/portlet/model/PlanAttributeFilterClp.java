package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanAttributeFilterLocalServiceUtil;

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


public class PlanAttributeFilterClp extends BaseModelImpl<PlanAttributeFilter>
    implements PlanAttributeFilter {
    private long _planAttributeFilterId;
    private String _attributeName;
    private long _planUserSettingsId;
    private Double _max;
    private Double _min;
    private String _stringVal;
    private BaseModel<?> _planAttributeFilterRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PlanAttributeFilterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanAttributeFilter.class;
    }

    @Override
    public String getModelClassName() {
        return PlanAttributeFilter.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _planAttributeFilterId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPlanAttributeFilterId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _planAttributeFilterId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planAttributeFilterId", getPlanAttributeFilterId());
        attributes.put("attributeName", getAttributeName());
        attributes.put("planUserSettingsId", getPlanUserSettingsId());
        attributes.put("max", getMax());
        attributes.put("min", getMin());
        attributes.put("stringVal", getStringVal());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planAttributeFilterId = (Long) attributes.get(
                "planAttributeFilterId");

        if (planAttributeFilterId != null) {
            setPlanAttributeFilterId(planAttributeFilterId);
        }

        String attributeName = (String) attributes.get("attributeName");

        if (attributeName != null) {
            setAttributeName(attributeName);
        }

        Long planUserSettingsId = (Long) attributes.get("planUserSettingsId");

        if (planUserSettingsId != null) {
            setPlanUserSettingsId(planUserSettingsId);
        }

        Double max = (Double) attributes.get("max");

        if (max != null) {
            setMax(max);
        }

        Double min = (Double) attributes.get("min");

        if (min != null) {
            setMin(min);
        }

        String stringVal = (String) attributes.get("stringVal");

        if (stringVal != null) {
            setStringVal(stringVal);
        }
    }

    @Override
    public long getPlanAttributeFilterId() {
        return _planAttributeFilterId;
    }

    @Override
    public void setPlanAttributeFilterId(long planAttributeFilterId) {
        _planAttributeFilterId = planAttributeFilterId;

        if (_planAttributeFilterRemoteModel != null) {
            try {
                Class<?> clazz = _planAttributeFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanAttributeFilterId",
                        long.class);

                method.invoke(_planAttributeFilterRemoteModel,
                    planAttributeFilterId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAttributeName() {
        return _attributeName;
    }

    @Override
    public void setAttributeName(String attributeName) {
        _attributeName = attributeName;

        if (_planAttributeFilterRemoteModel != null) {
            try {
                Class<?> clazz = _planAttributeFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setAttributeName", String.class);

                method.invoke(_planAttributeFilterRemoteModel, attributeName);
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

        if (_planAttributeFilterRemoteModel != null) {
            try {
                Class<?> clazz = _planAttributeFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanUserSettingsId",
                        long.class);

                method.invoke(_planAttributeFilterRemoteModel,
                    planUserSettingsId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Double getMax() {
        return _max;
    }

    @Override
    public void setMax(Double max) {
        _max = max;

        if (_planAttributeFilterRemoteModel != null) {
            try {
                Class<?> clazz = _planAttributeFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setMax", Double.class);

                method.invoke(_planAttributeFilterRemoteModel, max);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Double getMin() {
        return _min;
    }

    @Override
    public void setMin(Double min) {
        _min = min;

        if (_planAttributeFilterRemoteModel != null) {
            try {
                Class<?> clazz = _planAttributeFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setMin", Double.class);

                method.invoke(_planAttributeFilterRemoteModel, min);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStringVal() {
        return _stringVal;
    }

    @Override
    public void setStringVal(String stringVal) {
        _stringVal = stringVal;

        if (_planAttributeFilterRemoteModel != null) {
            try {
                Class<?> clazz = _planAttributeFilterRemoteModel.getClass();

                Method method = clazz.getMethod("setStringVal", String.class);

                method.invoke(_planAttributeFilterRemoteModel, stringVal);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanAttributeFilterRemoteModel() {
        return _planAttributeFilterRemoteModel;
    }

    public void setPlanAttributeFilterRemoteModel(
        BaseModel<?> planAttributeFilterRemoteModel) {
        _planAttributeFilterRemoteModel = planAttributeFilterRemoteModel;
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

        Class<?> remoteModelClass = _planAttributeFilterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planAttributeFilterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanAttributeFilterLocalServiceUtil.addPlanAttributeFilter(this);
        } else {
            PlanAttributeFilterLocalServiceUtil.updatePlanAttributeFilter(this);
        }
    }

    @Override
    public PlanAttributeFilter toEscapedModel() {
        return (PlanAttributeFilter) ProxyUtil.newProxyInstance(PlanAttributeFilter.class.getClassLoader(),
            new Class[] { PlanAttributeFilter.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanAttributeFilterClp clone = new PlanAttributeFilterClp();

        clone.setPlanAttributeFilterId(getPlanAttributeFilterId());
        clone.setAttributeName(getAttributeName());
        clone.setPlanUserSettingsId(getPlanUserSettingsId());
        clone.setMax(getMax());
        clone.setMin(getMin());
        clone.setStringVal(getStringVal());

        return clone;
    }

    @Override
    public int compareTo(PlanAttributeFilter planAttributeFilter) {
        long primaryKey = planAttributeFilter.getPrimaryKey();

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

        if (!(obj instanceof PlanAttributeFilterClp)) {
            return false;
        }

        PlanAttributeFilterClp planAttributeFilter = (PlanAttributeFilterClp) obj;

        long primaryKey = planAttributeFilter.getPrimaryKey();

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
        StringBundler sb = new StringBundler(13);

        sb.append("{planAttributeFilterId=");
        sb.append(getPlanAttributeFilterId());
        sb.append(", attributeName=");
        sb.append(getAttributeName());
        sb.append(", planUserSettingsId=");
        sb.append(getPlanUserSettingsId());
        sb.append(", max=");
        sb.append(getMax());
        sb.append(", min=");
        sb.append(getMin());
        sb.append(", stringVal=");
        sb.append(getStringVal());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanAttributeFilter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planAttributeFilterId</column-name><column-value><![CDATA[");
        sb.append(getPlanAttributeFilterId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeName</column-name><column-value><![CDATA[");
        sb.append(getAttributeName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planUserSettingsId</column-name><column-value><![CDATA[");
        sb.append(getPlanUserSettingsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>max</column-name><column-value><![CDATA[");
        sb.append(getMax());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>min</column-name><column-value><![CDATA[");
        sb.append(getMin());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>stringVal</column-name><column-value><![CDATA[");
        sb.append(getStringVal());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
