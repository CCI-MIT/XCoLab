package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;

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


public class OntologyTermClp extends BaseModelImpl<OntologyTerm>
    implements OntologyTerm {
    private long _id;
    private long _parentId;
    private long _ontologySpaceId;
    private String _name;
    private String _descriptionUrl;
    private int _order_;
    private BaseModel<?> _ontologyTermRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public OntologyTermClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return OntologyTerm.class;
    }

    @Override
    public String getModelClassName() {
        return OntologyTerm.class.getName();
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
        attributes.put("parentId", getParentId());
        attributes.put("ontologySpaceId", getOntologySpaceId());
        attributes.put("name", getName());
        attributes.put("descriptionUrl", getDescriptionUrl());
        attributes.put("order_", getOrder_());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long parentId = (Long) attributes.get("parentId");

        if (parentId != null) {
            setParentId(parentId);
        }

        Long ontologySpaceId = (Long) attributes.get("ontologySpaceId");

        if (ontologySpaceId != null) {
            setOntologySpaceId(ontologySpaceId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String descriptionUrl = (String) attributes.get("descriptionUrl");

        if (descriptionUrl != null) {
            setDescriptionUrl(descriptionUrl);
        }

        Integer order_ = (Integer) attributes.get("order_");

        if (order_ != null) {
            setOrder_(order_);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_ontologyTermRemoteModel != null) {
            try {
                Class<?> clazz = _ontologyTermRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_ontologyTermRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getParentId() {
        return _parentId;
    }

    @Override
    public void setParentId(long parentId) {
        _parentId = parentId;

        if (_ontologyTermRemoteModel != null) {
            try {
                Class<?> clazz = _ontologyTermRemoteModel.getClass();

                Method method = clazz.getMethod("setParentId", long.class);

                method.invoke(_ontologyTermRemoteModel, parentId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getOntologySpaceId() {
        return _ontologySpaceId;
    }

    @Override
    public void setOntologySpaceId(long ontologySpaceId) {
        _ontologySpaceId = ontologySpaceId;

        if (_ontologyTermRemoteModel != null) {
            try {
                Class<?> clazz = _ontologyTermRemoteModel.getClass();

                Method method = clazz.getMethod("setOntologySpaceId", long.class);

                method.invoke(_ontologyTermRemoteModel, ontologySpaceId);
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

        if (_ontologyTermRemoteModel != null) {
            try {
                Class<?> clazz = _ontologyTermRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_ontologyTermRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescriptionUrl() {
        return _descriptionUrl;
    }

    @Override
    public void setDescriptionUrl(String descriptionUrl) {
        _descriptionUrl = descriptionUrl;

        if (_ontologyTermRemoteModel != null) {
            try {
                Class<?> clazz = _ontologyTermRemoteModel.getClass();

                Method method = clazz.getMethod("setDescriptionUrl",
                        String.class);

                method.invoke(_ontologyTermRemoteModel, descriptionUrl);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getOrder_() {
        return _order_;
    }

    @Override
    public void setOrder_(int order_) {
        _order_ = order_;

        if (_ontologyTermRemoteModel != null) {
            try {
                Class<?> clazz = _ontologyTermRemoteModel.getClass();

                Method method = clazz.getMethod("setOrder_", int.class);

                method.invoke(_ontologyTermRemoteModel, order_);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getOntologyTermRemoteModel() {
        return _ontologyTermRemoteModel;
    }

    public void setOntologyTermRemoteModel(BaseModel<?> ontologyTermRemoteModel) {
        _ontologyTermRemoteModel = ontologyTermRemoteModel;
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

        Class<?> remoteModelClass = _ontologyTermRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ontologyTermRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            OntologyTermLocalServiceUtil.addOntologyTerm(this);
        } else {
            OntologyTermLocalServiceUtil.updateOntologyTerm(this);
        }
    }

    @Override
    public OntologyTerm toEscapedModel() {
        return (OntologyTerm) ProxyUtil.newProxyInstance(OntologyTerm.class.getClassLoader(),
            new Class[] { OntologyTerm.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        OntologyTermClp clone = new OntologyTermClp();

        clone.setId(getId());
        clone.setParentId(getParentId());
        clone.setOntologySpaceId(getOntologySpaceId());
        clone.setName(getName());
        clone.setDescriptionUrl(getDescriptionUrl());
        clone.setOrder_(getOrder_());

        return clone;
    }

    @Override
    public int compareTo(OntologyTerm ontologyTerm) {
        int value = 0;

        if (getOrder_() < ontologyTerm.getOrder_()) {
            value = -1;
        } else if (getOrder_() > ontologyTerm.getOrder_()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (getId() < ontologyTerm.getId()) {
            value = -1;
        } else if (getId() > ontologyTerm.getId()) {
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

        if (!(obj instanceof OntologyTermClp)) {
            return false;
        }

        OntologyTermClp ontologyTerm = (OntologyTermClp) obj;

        long primaryKey = ontologyTerm.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
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
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", parentId=");
        sb.append(getParentId());
        sb.append(", ontologySpaceId=");
        sb.append(getOntologySpaceId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", descriptionUrl=");
        sb.append(getDescriptionUrl());
        sb.append(", order_=");
        sb.append(getOrder_());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.OntologyTerm");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentId</column-name><column-value><![CDATA[");
        sb.append(getParentId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ontologySpaceId</column-name><column-value><![CDATA[");
        sb.append(getOntologySpaceId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>descriptionUrl</column-name><column-value><![CDATA[");
        sb.append(getDescriptionUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>order_</column-name><column-value><![CDATA[");
        sb.append(getOrder_());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
