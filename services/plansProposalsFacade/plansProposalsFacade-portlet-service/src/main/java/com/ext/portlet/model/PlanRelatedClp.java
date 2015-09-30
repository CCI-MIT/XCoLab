package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanRelatedLocalServiceUtil;
import com.ext.portlet.service.persistence.PlanRelatedPK;

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


public class PlanRelatedClp extends BaseModelImpl<PlanRelated>
    implements PlanRelated {
    private long _sectionId;
    private long _relatedPlanId;
    private BaseModel<?> _planRelatedRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public PlanRelatedClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanRelated.class;
    }

    @Override
    public String getModelClassName() {
        return PlanRelated.class.getName();
    }

    @Override
    public PlanRelatedPK getPrimaryKey() {
        return new PlanRelatedPK(_sectionId, _relatedPlanId);
    }

    @Override
    public void setPrimaryKey(PlanRelatedPK primaryKey) {
        setSectionId(primaryKey.sectionId);
        setRelatedPlanId(primaryKey.relatedPlanId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new PlanRelatedPK(_sectionId, _relatedPlanId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlanRelatedPK) primaryKeyObj);
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

        if (_planRelatedRemoteModel != null) {
            try {
                Class<?> clazz = _planRelatedRemoteModel.getClass();

                Method method = clazz.getMethod("setSectionId", long.class);

                method.invoke(_planRelatedRemoteModel, sectionId);
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

        if (_planRelatedRemoteModel != null) {
            try {
                Class<?> clazz = _planRelatedRemoteModel.getClass();

                Method method = clazz.getMethod("setRelatedPlanId", long.class);

                method.invoke(_planRelatedRemoteModel, relatedPlanId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanRelatedRemoteModel() {
        return _planRelatedRemoteModel;
    }

    public void setPlanRelatedRemoteModel(BaseModel<?> planRelatedRemoteModel) {
        _planRelatedRemoteModel = planRelatedRemoteModel;
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

        Class<?> remoteModelClass = _planRelatedRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planRelatedRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanRelatedLocalServiceUtil.addPlanRelated(this);
        } else {
            PlanRelatedLocalServiceUtil.updatePlanRelated(this);
        }
    }

    @Override
    public PlanRelated toEscapedModel() {
        return (PlanRelated) ProxyUtil.newProxyInstance(PlanRelated.class.getClassLoader(),
            new Class[] { PlanRelated.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanRelatedClp clone = new PlanRelatedClp();

        clone.setSectionId(getSectionId());
        clone.setRelatedPlanId(getRelatedPlanId());

        return clone;
    }

    @Override
    public int compareTo(PlanRelated planRelated) {
        PlanRelatedPK primaryKey = planRelated.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanRelatedClp)) {
            return false;
        }

        PlanRelatedClp planRelated = (PlanRelatedClp) obj;

        PlanRelatedPK primaryKey = planRelated.getPrimaryKey();

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
        sb.append("com.ext.portlet.model.PlanRelated");
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
