package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanPositionItemLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanPositionItemPK;

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


public class PlanPositionItemClp extends BaseModelImpl<PlanPositionItem>
    implements PlanPositionItem {
    private long _planPositionsId;
    private long _positionId;
    private BaseModel<?> _planPositionItemRemoteModel;

    public PlanPositionItemClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanPositionItem.class;
    }

    @Override
    public String getModelClassName() {
        return PlanPositionItem.class.getName();
    }

    @Override
    public PlanPositionItemPK getPrimaryKey() {
        return new PlanPositionItemPK(_planPositionsId, _positionId);
    }

    @Override
    public void setPrimaryKey(PlanPositionItemPK primaryKey) {
        setPlanPositionsId(primaryKey.planPositionsId);
        setPositionId(primaryKey.positionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new PlanPositionItemPK(_planPositionsId, _positionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanPositionItemPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planPositionsId", getPlanPositionsId());
        attributes.put("positionId", getPositionId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planPositionsId = (Long) attributes.get("planPositionsId");

        if (planPositionsId != null) {
            setPlanPositionsId(planPositionsId);
        }

        Long positionId = (Long) attributes.get("positionId");

        if (positionId != null) {
            setPositionId(positionId);
        }
    }

    @Override
    public long getPlanPositionsId() {
        return _planPositionsId;
    }

    @Override
    public void setPlanPositionsId(long planPositionsId) {
        _planPositionsId = planPositionsId;

        if (_planPositionItemRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionItemRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanPositionsId", long.class);

                method.invoke(_planPositionItemRemoteModel, planPositionsId);
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

        if (_planPositionItemRemoteModel != null) {
            try {
                Class<?> clazz = _planPositionItemRemoteModel.getClass();

                Method method = clazz.getMethod("setPositionId", long.class);

                method.invoke(_planPositionItemRemoteModel, positionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanPositionItemRemoteModel() {
        return _planPositionItemRemoteModel;
    }

    public void setPlanPositionItemRemoteModel(
        BaseModel<?> planPositionItemRemoteModel) {
        _planPositionItemRemoteModel = planPositionItemRemoteModel;
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

        Class<?> remoteModelClass = _planPositionItemRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planPositionItemRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanPositionItemLocalServiceUtil.addPlanPositionItem(this);
        } else {
            PlanPositionItemLocalServiceUtil.updatePlanPositionItem(this);
        }
    }

    @Override
    public PlanPositionItem toEscapedModel() {
        return (PlanPositionItem) ProxyUtil.newProxyInstance(PlanPositionItem.class.getClassLoader(),
            new Class[] { PlanPositionItem.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanPositionItemClp clone = new PlanPositionItemClp();

        clone.setPlanPositionsId(getPlanPositionsId());
        clone.setPositionId(getPositionId());

        return clone;
    }

    @Override
    public int compareTo(PlanPositionItem planPositionItem) {
        PlanPositionItemPK primaryKey = planPositionItem.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanPositionItemClp)) {
            return false;
        }

        PlanPositionItemClp planPositionItem = (PlanPositionItemClp) obj;

        PlanPositionItemPK primaryKey = planPositionItem.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{planPositionsId=");
        sb.append(getPlanPositionsId());
        sb.append(", positionId=");
        sb.append(getPositionId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanPositionItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planPositionsId</column-name><column-value><![CDATA[");
        sb.append(getPlanPositionsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>positionId</column-name><column-value><![CDATA[");
        sb.append(getPositionId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
