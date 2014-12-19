package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanTemplateSectionLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanTemplateSectionPK;

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


public class PlanTemplateSectionClp extends BaseModelImpl<PlanTemplateSection>
    implements PlanTemplateSection {
    private long _planTemplateId;
    private long _planSectionId;
    private int _weight;
    private BaseModel<?> _planTemplateSectionRemoteModel;

    public PlanTemplateSectionClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanTemplateSection.class;
    }

    @Override
    public String getModelClassName() {
        return PlanTemplateSection.class.getName();
    }

    @Override
    public PlanTemplateSectionPK getPrimaryKey() {
        return new PlanTemplateSectionPK(_planTemplateId, _planSectionId);
    }

    @Override
    public void setPrimaryKey(PlanTemplateSectionPK primaryKey) {
        setPlanTemplateId(primaryKey.planTemplateId);
        setPlanSectionId(primaryKey.planSectionId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new PlanTemplateSectionPK(_planTemplateId, _planSectionId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanTemplateSectionPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("planTemplateId", getPlanTemplateId());
        attributes.put("planSectionId", getPlanSectionId());
        attributes.put("weight", getWeight());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long planTemplateId = (Long) attributes.get("planTemplateId");

        if (planTemplateId != null) {
            setPlanTemplateId(planTemplateId);
        }

        Long planSectionId = (Long) attributes.get("planSectionId");

        if (planSectionId != null) {
            setPlanSectionId(planSectionId);
        }

        Integer weight = (Integer) attributes.get("weight");

        if (weight != null) {
            setWeight(weight);
        }
    }

    @Override
    public long getPlanTemplateId() {
        return _planTemplateId;
    }

    @Override
    public void setPlanTemplateId(long planTemplateId) {
        _planTemplateId = planTemplateId;

        if (_planTemplateSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planTemplateSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanTemplateId", long.class);

                method.invoke(_planTemplateSectionRemoteModel, planTemplateId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getPlanSectionId() {
        return _planSectionId;
    }

    @Override
    public void setPlanSectionId(long planSectionId) {
        _planSectionId = planSectionId;

        if (_planTemplateSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planTemplateSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setPlanSectionId", long.class);

                method.invoke(_planTemplateSectionRemoteModel, planSectionId);
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

        if (_planTemplateSectionRemoteModel != null) {
            try {
                Class<?> clazz = _planTemplateSectionRemoteModel.getClass();

                Method method = clazz.getMethod("setWeight", int.class);

                method.invoke(_planTemplateSectionRemoteModel, weight);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanTemplateSectionRemoteModel() {
        return _planTemplateSectionRemoteModel;
    }

    public void setPlanTemplateSectionRemoteModel(
        BaseModel<?> planTemplateSectionRemoteModel) {
        _planTemplateSectionRemoteModel = planTemplateSectionRemoteModel;
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

        Class<?> remoteModelClass = _planTemplateSectionRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planTemplateSectionRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTemplateSectionLocalServiceUtil.addPlanTemplateSection(this);
        } else {
            PlanTemplateSectionLocalServiceUtil.updatePlanTemplateSection(this);
        }
    }

    @Override
    public PlanTemplateSection toEscapedModel() {
        return (PlanTemplateSection) ProxyUtil.newProxyInstance(PlanTemplateSection.class.getClassLoader(),
            new Class[] { PlanTemplateSection.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTemplateSectionClp clone = new PlanTemplateSectionClp();

        clone.setPlanTemplateId(getPlanTemplateId());
        clone.setPlanSectionId(getPlanSectionId());
        clone.setWeight(getWeight());

        return clone;
    }

    @Override
    public int compareTo(PlanTemplateSection planTemplateSection) {
        int value = 0;

        if (getWeight() < planTemplateSection.getWeight()) {
            value = -1;
        } else if (getWeight() > planTemplateSection.getWeight()) {
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

        if (!(obj instanceof PlanTemplateSectionClp)) {
            return false;
        }

        PlanTemplateSectionClp planTemplateSection = (PlanTemplateSectionClp) obj;

        PlanTemplateSectionPK primaryKey = planTemplateSection.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{planTemplateId=");
        sb.append(getPlanTemplateId());
        sb.append(", planSectionId=");
        sb.append(getPlanSectionId());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanTemplateSection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTemplateId</column-name><column-value><![CDATA[");
        sb.append(getPlanTemplateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planSectionId</column-name><column-value><![CDATA[");
        sb.append(getPlanSectionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
