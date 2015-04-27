package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ImpactTemplateFocusAreaListLocalServiceUtil;

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


public class ImpactTemplateFocusAreaListClp extends BaseModelImpl<ImpactTemplateFocusAreaList>
    implements ImpactTemplateFocusAreaList {
    private long _focusAreaListId;
    private String _name;
    private BaseModel<?> _impactTemplateFocusAreaListRemoteModel;

    public ImpactTemplateFocusAreaListClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactTemplateFocusAreaList.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactTemplateFocusAreaList.class.getName();
    }

    @Override
    public long getPrimaryKey() {
        return _focusAreaListId;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        setFocusAreaListId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _focusAreaListId;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("focusAreaListId", getFocusAreaListId());
        attributes.put("name", getName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long focusAreaListId = (Long) attributes.get("focusAreaListId");

        if (focusAreaListId != null) {
            setFocusAreaListId(focusAreaListId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }
    }

    @Override
    public long getFocusAreaListId() {
        return _focusAreaListId;
    }

    @Override
    public void setFocusAreaListId(long focusAreaListId) {
        _focusAreaListId = focusAreaListId;

        if (_impactTemplateFocusAreaListRemoteModel != null) {
            try {
                Class<?> clazz = _impactTemplateFocusAreaListRemoteModel.getClass();

                Method method = clazz.getMethod("setFocusAreaListId", long.class);

                method.invoke(_impactTemplateFocusAreaListRemoteModel,
                    focusAreaListId);
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

        if (_impactTemplateFocusAreaListRemoteModel != null) {
            try {
                Class<?> clazz = _impactTemplateFocusAreaListRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_impactTemplateFocusAreaListRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImpactTemplateFocusAreaListRemoteModel() {
        return _impactTemplateFocusAreaListRemoteModel;
    }

    public void setImpactTemplateFocusAreaListRemoteModel(
        BaseModel<?> impactTemplateFocusAreaListRemoteModel) {
        _impactTemplateFocusAreaListRemoteModel = impactTemplateFocusAreaListRemoteModel;
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

        Class<?> remoteModelClass = _impactTemplateFocusAreaListRemoteModel.getClass();

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

        Object returnValue = method.invoke(_impactTemplateFocusAreaListRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImpactTemplateFocusAreaListLocalServiceUtil.addImpactTemplateFocusAreaList(this);
        } else {
            ImpactTemplateFocusAreaListLocalServiceUtil.updateImpactTemplateFocusAreaList(this);
        }
    }

    @Override
    public ImpactTemplateFocusAreaList toEscapedModel() {
        return (ImpactTemplateFocusAreaList) ProxyUtil.newProxyInstance(ImpactTemplateFocusAreaList.class.getClassLoader(),
            new Class[] { ImpactTemplateFocusAreaList.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImpactTemplateFocusAreaListClp clone = new ImpactTemplateFocusAreaListClp();

        clone.setFocusAreaListId(getFocusAreaListId());
        clone.setName(getName());

        return clone;
    }

    @Override
    public int compareTo(
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList) {
        long primaryKey = impactTemplateFocusAreaList.getPrimaryKey();

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

        if (!(obj instanceof ImpactTemplateFocusAreaListClp)) {
            return false;
        }

        ImpactTemplateFocusAreaListClp impactTemplateFocusAreaList = (ImpactTemplateFocusAreaListClp) obj;

        long primaryKey = impactTemplateFocusAreaList.getPrimaryKey();

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

        sb.append("{focusAreaListId=");
        sb.append(getFocusAreaListId());
        sb.append(", name=");
        sb.append(getName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ImpactTemplateFocusAreaList");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>focusAreaListId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaListId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
