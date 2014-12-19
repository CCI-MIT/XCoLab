package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanItemGroupLocalServiceUtil;

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


public class PlanItemGroupClp extends BaseModelImpl<PlanItemGroup>
    implements PlanItemGroup {
    private long _planId;
    private long _groupId;
    private BaseModel<?> _planItemGroupRemoteModel;

    public PlanItemGroupClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanItemGroup.class;
    }

    @Override
    public String getModelClassName() {
        return PlanItemGroup.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _planId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setPlanId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _planId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planId", getPlanId());
        attributes.put("groupId", getGroupId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Long groupId = (Long) attributes.get("groupId");

        if (groupId != null) {
            setGroupId(groupId);
        }
    }

    @Override
    public long getPlanId() {
        return _planId;
    }

    @Override
    public void setPlanId(long planId) {
        _planId = planId;

        if (_planItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _planItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanId", long.class);

                method.invoke(_planItemGroupRemoteModel, planId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getGroupId() {
        return _groupId;
    }

    @Override
    public void setGroupId(long groupId) {
        _groupId = groupId;

        if (_planItemGroupRemoteModel != null) {
            try {
                Class<?> clazz = _planItemGroupRemoteModel.getClass();

                Method method = clazz.getMethod("setGroupId", long.class);

                method.invoke(_planItemGroupRemoteModel, groupId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanItemGroupRemoteModel() {
        return _planItemGroupRemoteModel;
    }

    public void setPlanItemGroupRemoteModel(
        BaseModel<?> planItemGroupRemoteModel) {
        _planItemGroupRemoteModel = planItemGroupRemoteModel;
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

        Class<?> remoteModelClass = _planItemGroupRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planItemGroupRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanItemGroupLocalServiceUtil.addPlanItemGroup(this);
        } else {
            PlanItemGroupLocalServiceUtil.updatePlanItemGroup(this);
        }
    }

    @Override
    public PlanItemGroup toEscapedModel() {
        return (PlanItemGroup) ProxyUtil.newProxyInstance(PlanItemGroup.class.getClassLoader(),
            new Class[] { PlanItemGroup.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanItemGroupClp clone = new PlanItemGroupClp();

        clone.setPlanId(getPlanId());
        clone.setGroupId(getGroupId());

        return clone;
    }

    @Override
    public int compareTo(PlanItemGroup planItemGroup) {
        long primaryKey = planItemGroup.getPrimaryKey();

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

        if (!(obj instanceof PlanItemGroupClp)) {
            return false;
        }

        PlanItemGroupClp planItemGroup = (PlanItemGroupClp) obj;

        long primaryKey = planItemGroup.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{planId=");
        sb.append(getPlanId());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanItemGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
