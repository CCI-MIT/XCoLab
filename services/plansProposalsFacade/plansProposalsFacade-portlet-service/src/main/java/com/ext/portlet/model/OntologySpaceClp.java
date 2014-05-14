package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.OntologySpaceLocalServiceUtil;

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


public class OntologySpaceClp extends BaseModelImpl<OntologySpace>
    implements OntologySpace {
    private long _id;
    private String _name;
    private String _description;
    private int _order;
    private BaseModel<?> _ontologySpaceRemoteModel;

    public OntologySpaceClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return OntologySpace.class;
    }

    @Override
    public String getModelClassName() {
        return OntologySpace.class.getName();
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
        attributes.put("description", getDescription());
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

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
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

        if (_ontologySpaceRemoteModel != null) {
            try {
                Class<?> clazz = _ontologySpaceRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_ontologySpaceRemoteModel, id);
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

        if (_ontologySpaceRemoteModel != null) {
            try {
                Class<?> clazz = _ontologySpaceRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_ontologySpaceRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_ontologySpaceRemoteModel != null) {
            try {
                Class<?> clazz = _ontologySpaceRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_ontologySpaceRemoteModel, description);
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

        if (_ontologySpaceRemoteModel != null) {
            try {
                Class<?> clazz = _ontologySpaceRemoteModel.getClass();

                Method method = clazz.getMethod("setOrder", int.class);

                method.invoke(_ontologySpaceRemoteModel, order);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getOntologySpaceRemoteModel() {
        return _ontologySpaceRemoteModel;
    }

    public void setOntologySpaceRemoteModel(
        BaseModel<?> ontologySpaceRemoteModel) {
        _ontologySpaceRemoteModel = ontologySpaceRemoteModel;
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

        Class<?> remoteModelClass = _ontologySpaceRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ontologySpaceRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            OntologySpaceLocalServiceUtil.addOntologySpace(this);
        } else {
            OntologySpaceLocalServiceUtil.updateOntologySpace(this);
        }
    }

    @Override
    public OntologySpace toEscapedModel() {
        return (OntologySpace) ProxyUtil.newProxyInstance(OntologySpace.class.getClassLoader(),
            new Class[] { OntologySpace.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        OntologySpaceClp clone = new OntologySpaceClp();

        clone.setId(getId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setOrder(getOrder());

        return clone;
    }

    @Override
    public int compareTo(OntologySpace ontologySpace) {
        int value = 0;

        if (getOrder() < ontologySpace.getOrder()) {
            value = -1;
        } else if (getOrder() > ontologySpace.getOrder()) {
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

        if (!(obj instanceof OntologySpaceClp)) {
            return false;
        }

        OntologySpaceClp ontologySpace = (OntologySpaceClp) obj;

        long primaryKey = ontologySpace.getPrimaryKey();

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
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", order=");
        sb.append(getOrder());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.OntologySpace");
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
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>order</column-name><column-value><![CDATA[");
        sb.append(getOrder());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
