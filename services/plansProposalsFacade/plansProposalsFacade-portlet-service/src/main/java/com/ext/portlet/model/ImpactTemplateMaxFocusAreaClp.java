package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ImpactTemplateMaxFocusAreaLocalServiceUtil;
import com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK;

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


public class ImpactTemplateMaxFocusAreaClp extends BaseModelImpl<ImpactTemplateMaxFocusArea>
    implements ImpactTemplateMaxFocusArea {
    private long _focusAreaListId;
    private long _focusAreaId;
    private BaseModel<?> _impactTemplateMaxFocusAreaRemoteModel;

    public ImpactTemplateMaxFocusAreaClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactTemplateMaxFocusArea.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactTemplateMaxFocusArea.class.getName();
    }

    @Override
    public ImpactTemplateMaxFocusAreaPK getPrimaryKey() {
        return new ImpactTemplateMaxFocusAreaPK(_focusAreaListId, _focusAreaId);
    }

    @Override
    public void setPrimaryKey(ImpactTemplateMaxFocusAreaPK primaryKey) {
        setFocusAreaListId(primaryKey.focusAreaListId);
        setFocusAreaId(primaryKey.focusAreaId);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new ImpactTemplateMaxFocusAreaPK(_focusAreaListId, _focusAreaId);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((ImpactTemplateMaxFocusAreaPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("focusAreaListId", getFocusAreaListId());
        attributes.put("focusAreaId", getFocusAreaId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long focusAreaListId = (Long) attributes.get("focusAreaListId");

        if (focusAreaListId != null) {
            setFocusAreaListId(focusAreaListId);
        }

        Long focusAreaId = (Long) attributes.get("focusAreaId");

        if (focusAreaId != null) {
            setFocusAreaId(focusAreaId);
        }
    }

    @Override
    public long getFocusAreaListId() {
        return _focusAreaListId;
    }

    @Override
    public void setFocusAreaListId(long focusAreaListId) {
        _focusAreaListId = focusAreaListId;

        if (_impactTemplateMaxFocusAreaRemoteModel != null) {
            try {
                Class<?> clazz = _impactTemplateMaxFocusAreaRemoteModel.getClass();

                Method method = clazz.getMethod("setFocusAreaListId", long.class);

                method.invoke(_impactTemplateMaxFocusAreaRemoteModel,
                    focusAreaListId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getFocusAreaId() {
        return _focusAreaId;
    }

    @Override
    public void setFocusAreaId(long focusAreaId) {
        _focusAreaId = focusAreaId;

        if (_impactTemplateMaxFocusAreaRemoteModel != null) {
            try {
                Class<?> clazz = _impactTemplateMaxFocusAreaRemoteModel.getClass();

                Method method = clazz.getMethod("setFocusAreaId", long.class);

                method.invoke(_impactTemplateMaxFocusAreaRemoteModel,
                    focusAreaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImpactTemplateMaxFocusAreaRemoteModel() {
        return _impactTemplateMaxFocusAreaRemoteModel;
    }

    public void setImpactTemplateMaxFocusAreaRemoteModel(
        BaseModel<?> impactTemplateMaxFocusAreaRemoteModel) {
        _impactTemplateMaxFocusAreaRemoteModel = impactTemplateMaxFocusAreaRemoteModel;
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

        Class<?> remoteModelClass = _impactTemplateMaxFocusAreaRemoteModel.getClass();

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

        Object returnValue = method.invoke(_impactTemplateMaxFocusAreaRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImpactTemplateMaxFocusAreaLocalServiceUtil.addImpactTemplateMaxFocusArea(this);
        } else {
            ImpactTemplateMaxFocusAreaLocalServiceUtil.updateImpactTemplateMaxFocusArea(this);
        }
    }

    @Override
    public ImpactTemplateMaxFocusArea toEscapedModel() {
        return (ImpactTemplateMaxFocusArea) ProxyUtil.newProxyInstance(ImpactTemplateMaxFocusArea.class.getClassLoader(),
            new Class[] { ImpactTemplateMaxFocusArea.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImpactTemplateMaxFocusAreaClp clone = new ImpactTemplateMaxFocusAreaClp();

        clone.setFocusAreaListId(getFocusAreaListId());
        clone.setFocusAreaId(getFocusAreaId());

        return clone;
    }

    @Override
    public int compareTo(ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea) {
        ImpactTemplateMaxFocusAreaPK primaryKey = impactTemplateMaxFocusArea.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImpactTemplateMaxFocusAreaClp)) {
            return false;
        }

        ImpactTemplateMaxFocusAreaClp impactTemplateMaxFocusArea = (ImpactTemplateMaxFocusAreaClp) obj;

        ImpactTemplateMaxFocusAreaPK primaryKey = impactTemplateMaxFocusArea.getPrimaryKey();

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

        sb.append("{focusAreaListId=");
        sb.append(getFocusAreaListId());
        sb.append(", focusAreaId=");
        sb.append(getFocusAreaId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ImpactTemplateMaxFocusArea");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>focusAreaListId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaListId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>focusAreaId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
