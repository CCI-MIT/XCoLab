package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanSectionPlanMapLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanSectionPlanMapPK;

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


public class PlanSectionPlanMapClp extends BaseModelImpl<PlanSectionPlanMap>
    implements PlanSectionPlanMap {
    private long _sectionId;
    private long _relatedPlanId;
    private BaseModel<?> _planSectionPlanMapRemoteModel;

    public PlanSectionPlanMapClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanSectionPlanMap.class;
    }

    @Override
    public String getModelClassName() {
        return PlanSectionPlanMap.class.getName();
    }

    @Override
    public PlanSectionPlanMapPK getPrimaryKey() {
        return new PlanSectionPlanMapPK(_sectionId, _relatedPlanId);
    }

    @Override
    public void setPrimaryKey(PlanSectionPlanMapPK primaryKey) {
        setSectionId(primaryKey.sectionId);
        setRelatedPlanId(primaryKey.relatedPlanId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new PlanSectionPlanMapPK(_sectionId, _relatedPlanId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanSectionPlanMapPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("sectionId", getSectionId());
        attributes.put("relatedPlanId", getRelatedPlanId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long sectionId = (Long) attributes.get("sectionId");

        if (sectionId != null) {
            setSectionId(sectionId);
        }

        Long relatedPlanId = (Long) attributes.get("relatedPlanId");

        if (relatedPlanId != null) {
            setRelatedPlanId(relatedPlanId);
        }
    }

    @Override
    public long getSectionId() {
        return _sectionId;
    }

    @Override
    public void setSectionId(long sectionId) {
        _sectionId = sectionId;

        if (_planSectionPlanMapRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionPlanMapRemoteModel.getClass();

                Method method = clazz.getMethod("setSectionId", long.class);

                method.invoke(_planSectionPlanMapRemoteModel, sectionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getRelatedPlanId() {
        return _relatedPlanId;
    }

    @Override
    public void setRelatedPlanId(long relatedPlanId) {
        _relatedPlanId = relatedPlanId;

        if (_planSectionPlanMapRemoteModel != null) {
            try {
                Class<?> clazz = _planSectionPlanMapRemoteModel.getClass();

                Method method = clazz.getMethod("setRelatedPlanId", long.class);

                method.invoke(_planSectionPlanMapRemoteModel, relatedPlanId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanSectionPlanMapRemoteModel() {
        return _planSectionPlanMapRemoteModel;
    }

    public void setPlanSectionPlanMapRemoteModel(
        BaseModel<?> planSectionPlanMapRemoteModel) {
        _planSectionPlanMapRemoteModel = planSectionPlanMapRemoteModel;
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

        Class<?> remoteModelClass = _planSectionPlanMapRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planSectionPlanMapRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanSectionPlanMapLocalServiceUtil.addPlanSectionPlanMap(this);
        } else {
            PlanSectionPlanMapLocalServiceUtil.updatePlanSectionPlanMap(this);
        }
    }

    @Override
    public PlanSectionPlanMap toEscapedModel() {
        return (PlanSectionPlanMap) ProxyUtil.newProxyInstance(PlanSectionPlanMap.class.getClassLoader(),
            new Class[] { PlanSectionPlanMap.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanSectionPlanMapClp clone = new PlanSectionPlanMapClp();

        clone.setSectionId(getSectionId());
        clone.setRelatedPlanId(getRelatedPlanId());

        return clone;
    }

    @Override
    public int compareTo(PlanSectionPlanMap planSectionPlanMap) {
        PlanSectionPlanMapPK primaryKey = planSectionPlanMap.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanSectionPlanMapClp)) {
            return false;
        }

        PlanSectionPlanMapClp planSectionPlanMap = (PlanSectionPlanMapClp) obj;

        PlanSectionPlanMapPK primaryKey = planSectionPlanMap.getPrimaryKey();

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

        sb.append("{sectionId=");
        sb.append(getSectionId());
        sb.append(", relatedPlanId=");
        sb.append(getRelatedPlanId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanSectionPlanMap");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>sectionId</column-name><column-value><![CDATA[");
        sb.append(getSectionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relatedPlanId</column-name><column-value><![CDATA[");
        sb.append(getRelatedPlanId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
