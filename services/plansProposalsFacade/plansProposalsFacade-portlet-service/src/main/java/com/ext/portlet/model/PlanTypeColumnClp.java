package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanTypeColumnLocalServiceUtil;

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


public class PlanTypeColumnClp extends BaseModelImpl<PlanTypeColumn>
    implements PlanTypeColumn {
    private long _planTypeColumnId;
    private long _planTypeId;
    private int _weight;
    private String _columnName;
    private boolean _visibleByDefault;
    private BaseModel<?> _planTypeColumnRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PlanTypeColumnClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanTypeColumn.class;
    }

    @Override
    public String getModelClassName() {
        return PlanTypeColumn.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _planTypeColumnId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPlanTypeColumnId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _planTypeColumnId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planTypeColumnId", getPlanTypeColumnId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("weight", getWeight());
        attributes.put("columnName", getColumnName());
        attributes.put("visibleByDefault", getVisibleByDefault());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planTypeColumnId = (Long) attributes.get("planTypeColumnId");

        if (planTypeColumnId != null) {
            setPlanTypeColumnId(planTypeColumnId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        Integer weight = (Integer) attributes.get("weight");

        if (weight != null) {
            setWeight(weight);
        }

        String columnName = (String) attributes.get("columnName");

        if (columnName != null) {
            setColumnName(columnName);
        }

        Boolean visibleByDefault = (Boolean) attributes.get("visibleByDefault");

        if (visibleByDefault != null) {
            setVisibleByDefault(visibleByDefault);
        }
    }

    @Override
    public long getPlanTypeColumnId() {
        return _planTypeColumnId;
    }

    @Override
    public void setPlanTypeColumnId(long planTypeColumnId) {
        _planTypeColumnId = planTypeColumnId;

        if (_planTypeColumnRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeColumnRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanTypeColumnId",
                        long.class);

                method.invoke(_planTypeColumnRemoteModel, planTypeColumnId);
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

        if (_planTypeColumnRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeColumnRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanTypeId", long.class);

                method.invoke(_planTypeColumnRemoteModel, planTypeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getWeight() {
        return _weight;
    }

    @Override
    public void setWeight(int weight) {
        _weight = weight;

        if (_planTypeColumnRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeColumnRemoteModel.getClass();

                Method method = clazz.getMethod("setWeight", int.class);

                method.invoke(_planTypeColumnRemoteModel, weight);
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

        if (_planTypeColumnRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeColumnRemoteModel.getClass();

                Method method = clazz.getMethod("setColumnName", String.class);

                method.invoke(_planTypeColumnRemoteModel, columnName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getVisibleByDefault() {
        return _visibleByDefault;
    }

    @Override
    public boolean isVisibleByDefault() {
        return _visibleByDefault;
    }

    @Override
    public void setVisibleByDefault(boolean visibleByDefault) {
        _visibleByDefault = visibleByDefault;

        if (_planTypeColumnRemoteModel != null) {
            try {
                Class<?> clazz = _planTypeColumnRemoteModel.getClass();

                Method method = clazz.getMethod("setVisibleByDefault",
                        boolean.class);

                method.invoke(_planTypeColumnRemoteModel, visibleByDefault);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanTypeColumnRemoteModel() {
        return _planTypeColumnRemoteModel;
    }

    public void setPlanTypeColumnRemoteModel(
        BaseModel<?> planTypeColumnRemoteModel) {
        _planTypeColumnRemoteModel = planTypeColumnRemoteModel;
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

        Class<?> remoteModelClass = _planTypeColumnRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planTypeColumnRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTypeColumnLocalServiceUtil.addPlanTypeColumn(this);
        } else {
            PlanTypeColumnLocalServiceUtil.updatePlanTypeColumn(this);
        }
    }

    @Override
    public PlanTypeColumn toEscapedModel() {
        return (PlanTypeColumn) ProxyUtil.newProxyInstance(PlanTypeColumn.class.getClassLoader(),
            new Class[] { PlanTypeColumn.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTypeColumnClp clone = new PlanTypeColumnClp();

        clone.setPlanTypeColumnId(getPlanTypeColumnId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setWeight(getWeight());
        clone.setColumnName(getColumnName());
        clone.setVisibleByDefault(getVisibleByDefault());

        return clone;
    }

    @Override
    public int compareTo(PlanTypeColumn planTypeColumn) {
        int value = 0;

        if (getWeight() < planTypeColumn.getWeight()) {
            value = -1;
        } else if (getWeight() > planTypeColumn.getWeight()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        value = getColumnName()
                    .compareToIgnoreCase(planTypeColumn.getColumnName());

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

        if (!(obj instanceof PlanTypeColumnClp)) {
            return false;
        }

        PlanTypeColumnClp planTypeColumn = (PlanTypeColumnClp) obj;

        long primaryKey = planTypeColumn.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{planTypeColumnId=");
        sb.append(getPlanTypeColumnId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append(", columnName=");
        sb.append(getColumnName());
        sb.append(", visibleByDefault=");
        sb.append(getVisibleByDefault());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanTypeColumn");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTypeColumnId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeColumnId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>columnName</column-name><column-value><![CDATA[");
        sb.append(getColumnName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>visibleByDefault</column-name><column-value><![CDATA[");
        sb.append(getVisibleByDefault());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
