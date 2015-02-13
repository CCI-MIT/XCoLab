package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;

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


public class PlanTemplateClp extends BaseModelImpl<PlanTemplate>
    implements PlanTemplate {
    private long _id;
    private String _name;
    private Long _baseTemplateId;
    private BaseModel<?> _planTemplateRemoteModel;

    public PlanTemplateClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PlanTemplate.class;
    }

    @Override
    public String getModelClassName() {
        return PlanTemplate.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _id;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _id;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("name", getName());
        attributes.put("baseTemplateId", getBaseTemplateId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Long baseTemplateId = (Long) attributes.get("baseTemplateId");

        if (baseTemplateId != null) {
            setBaseTemplateId(baseTemplateId);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_planTemplateRemoteModel != null) {
            try {
                Class<?> clazz = _planTemplateRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_planTemplateRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setName(String name) {
        _name = name;

        if (_planTemplateRemoteModel != null) {
            try {
                Class<?> clazz = _planTemplateRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_planTemplateRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Long getBaseTemplateId() {
        return _baseTemplateId;
    }

    @Override
    public void setBaseTemplateId(Long baseTemplateId) {
        _baseTemplateId = baseTemplateId;

        if (_planTemplateRemoteModel != null) {
            try {
                Class<?> clazz = _planTemplateRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseTemplateId", Long.class);

                method.invoke(_planTemplateRemoteModel, baseTemplateId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPlanTemplateRemoteModel() {
        return _planTemplateRemoteModel;
    }

    public void setPlanTemplateRemoteModel(BaseModel<?> planTemplateRemoteModel) {
        _planTemplateRemoteModel = planTemplateRemoteModel;
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

        Class<?> remoteModelClass = _planTemplateRemoteModel.getClass();

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

        Object returnValue = method.invoke(_planTemplateRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTemplateLocalServiceUtil.addPlanTemplate(this);
        } else {
            PlanTemplateLocalServiceUtil.updatePlanTemplate(this);
        }
    }

    @Override
    public PlanTemplate toEscapedModel() {
        return (PlanTemplate) ProxyUtil.newProxyInstance(PlanTemplate.class.getClassLoader(),
            new Class[] { PlanTemplate.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTemplateClp clone = new PlanTemplateClp();

        clone.setId(getId());
        clone.setName(getName());
        clone.setBaseTemplateId(getBaseTemplateId());

        return clone;
    }

    @Override
    public int compareTo(PlanTemplate planTemplate) {
        long primaryKey = planTemplate.getPrimaryKey();

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

        if (!(obj instanceof PlanTemplateClp)) {
            return false;
        }

        PlanTemplateClp planTemplate = (PlanTemplateClp) obj;

        long primaryKey = planTemplate.getPrimaryKey();

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
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", baseTemplateId=");
        sb.append(getBaseTemplateId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanTemplate");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseTemplateId</column-name><column-value><![CDATA[");
        sb.append(getBaseTemplateId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
