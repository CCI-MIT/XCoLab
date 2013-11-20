package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;

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


public class FocusAreaClp extends BaseModelImpl<FocusArea> implements FocusArea {
    private long _id;
    private String _name;
    private int _order;
    private BaseModel<?> _focusAreaRemoteModel;

    public FocusAreaClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return FocusArea.class;
    }

    @Override
    public String getModelClassName() {
        return FocusArea.class.getName();
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
        attributes.put("order", getOrder());

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

        Integer order = (Integer) attributes.get("order");

        if (order != null) {
            setOrder(order);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_focusAreaRemoteModel != null) {
            try {
                Class<?> clazz = _focusAreaRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_focusAreaRemoteModel, id);
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

        if (_focusAreaRemoteModel != null) {
            try {
                Class<?> clazz = _focusAreaRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_focusAreaRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getOrder() {
        return _order;
    }

    @Override
    public void setOrder(int order) {
        _order = order;

        if (_focusAreaRemoteModel != null) {
            try {
                Class<?> clazz = _focusAreaRemoteModel.getClass();

                Method method = clazz.getMethod("setOrder", int.class);

                method.invoke(_focusAreaRemoteModel, order);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getFocusAreaRemoteModel() {
        return _focusAreaRemoteModel;
    }

    public void setFocusAreaRemoteModel(BaseModel<?> focusAreaRemoteModel) {
        _focusAreaRemoteModel = focusAreaRemoteModel;
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

        Class<?> remoteModelClass = _focusAreaRemoteModel.getClass();

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

        Object returnValue = method.invoke(_focusAreaRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            FocusAreaLocalServiceUtil.addFocusArea(this);
        } else {
            FocusAreaLocalServiceUtil.updateFocusArea(this);
        }
    }

    @Override
    public FocusArea toEscapedModel() {
        return (FocusArea) ProxyUtil.newProxyInstance(FocusArea.class.getClassLoader(),
            new Class[] { FocusArea.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FocusAreaClp clone = new FocusAreaClp();

        clone.setId(getId());
        clone.setName(getName());
        clone.setOrder(getOrder());

        return clone;
    }

    @Override
    public int compareTo(FocusArea focusArea) {
        int value = 0;

        if (getOrder() < focusArea.getOrder()) {
            value = -1;
        } else if (getOrder() > focusArea.getOrder()) {
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

        if (!(obj instanceof FocusAreaClp)) {
            return false;
        }

        FocusAreaClp focusArea = (FocusAreaClp) obj;

        long primaryKey = focusArea.getPrimaryKey();

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
        sb.append(", order=");
        sb.append(getOrder());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.FocusArea");
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
            "<column><column-name>order</column-name><column-value><![CDATA[");
        sb.append(getOrder());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
