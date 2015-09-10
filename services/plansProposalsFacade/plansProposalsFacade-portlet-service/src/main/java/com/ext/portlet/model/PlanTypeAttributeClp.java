package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanTypeAttributeLocalServiceUtil;

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


public class PlanTypeAttributeClp extends BaseModelImpl<PlanTypeAttribute>
    implements PlanTypeAttribute {
    private long _planTypeAttributeId;
    private long _planTypeId;
    private String _attributeName;
    private BaseModel<?> _planTypeAttributeRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PlanTypeAttributeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanTypeAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return PlanTypeAttribute.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _planTypeAttributeId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPlanTypeAttributeId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _planTypeAttributeId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planTypeAttributeId", getPlanTypeAttributeId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("attributeName", getAttributeName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planTypeAttributeId = (Long) attributes.get("planTypeAttributeId");

        if (planTypeAttributeId != null) {
            setPlanTypeAttributeId(planTypeAttributeId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        String attributeName = (String) attributes.get("attributeName");

        if (attributeName != null) {
            setAttributeName(attributeName);
        }
    }

    @Override
    public long getPlanTypeAttributeId() {
        return _planTypeAttributeId;
    }

    @Override
    public void setPlanTypeAttributeId(long planTypeAttributeId) {
        _planTypeAttributeId = planTypeAttributeId;

        if (_planTypeAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanTypeAttributeId",
                        long.class);

                method.invoke(_planTypeAttributeRemoteModel, planTypeAttributeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanTypeId() {
        return _planTypeId;
    }

    @Override
    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;

        if (_planTypeAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanTypeId", long.class);

                method.invoke(_planTypeAttributeRemoteModel, planTypeId);
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

        if (_planTypeAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setAttributeName", String.class);

                method.invoke(_planTypeAttributeRemoteModel, attributeName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanTypeAttributeRemoteModel() {
        return _planTypeAttributeRemoteModel;
    }

    public void setPlanTypeAttributeRemoteModel(
        BaseModel<?> planTypeAttributeRemoteModel) {
        _planTypeAttributeRemoteModel = planTypeAttributeRemoteModel;
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

        Class<?> remoteModelClass = _planTypeAttributeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planTypeAttributeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTypeAttributeLocalServiceUtil.addPlanTypeAttribute(this);
        } else {
            PlanTypeAttributeLocalServiceUtil.updatePlanTypeAttribute(this);
        }
    }

    @Override
    public PlanTypeAttribute toEscapedModel() {
        return (PlanTypeAttribute) ProxyUtil.newProxyInstance(PlanTypeAttribute.class.getClassLoader(),
            new Class[] { PlanTypeAttribute.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTypeAttributeClp clone = new PlanTypeAttributeClp();

        clone.setPlanTypeAttributeId(getPlanTypeAttributeId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setAttributeName(getAttributeName());

        return clone;
    }

    @Override
    public int compareTo(PlanTypeAttribute planTypeAttribute) {
        long primaryKey = planTypeAttribute.getPrimaryKey();

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

        if (!(obj instanceof PlanTypeAttributeClp)) {
            return false;
        }

        PlanTypeAttributeClp planTypeAttribute = (PlanTypeAttributeClp) obj;

        long primaryKey = planTypeAttribute.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{planTypeAttributeId=");
        sb.append(getPlanTypeAttributeId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", attributeName=");
        sb.append(getAttributeName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanTypeAttribute");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTypeAttributeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeAttributeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>attributeName</column-name><column-value><![CDATA[");
        sb.append(getAttributeName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
