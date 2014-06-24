package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;

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


public class ProposalRatingValueClp extends BaseModelImpl<ProposalRatingValue>
    implements ProposalRatingValue {
    private long _id;
    private long _ratingTypeId;
    private long _value;
    private String _name;
    private String _description;
    private BaseModel<?> _proposalRatingValueRemoteModel;

    public ProposalRatingValueClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalRatingValue.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalRatingValue.class.getName();
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
        attributes.put("ratingTypeId", getRatingTypeId());
        attributes.put("value", getValue());
        attributes.put("name", getName());
        attributes.put("description", getDescription());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long ratingTypeId = (Long) attributes.get("ratingTypeId");

        if (ratingTypeId != null) {
            setRatingTypeId(ratingTypeId);
        }

        Long value = (Long) attributes.get("value");

        if (value != null) {
            setValue(value);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_proposalRatingValueRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingValueRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_proposalRatingValueRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getRatingTypeId() {
        return _ratingTypeId;
    }

    @Override
    public void setRatingTypeId(long ratingTypeId) {
        _ratingTypeId = ratingTypeId;

        if (_proposalRatingValueRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingValueRemoteModel.getClass();

                Method method = clazz.getMethod("setRatingTypeId", long.class);

                method.invoke(_proposalRatingValueRemoteModel, ratingTypeId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getValue() {
        return _value;
    }

    @Override
    public void setValue(long value) {
        _value = value;

        if (_proposalRatingValueRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingValueRemoteModel.getClass();

                Method method = clazz.getMethod("setValue", long.class);

                method.invoke(_proposalRatingValueRemoteModel, value);
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

        if (_proposalRatingValueRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingValueRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_proposalRatingValueRemoteModel, name);
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

        if (_proposalRatingValueRemoteModel != null) {
            try {
                Class<?> clazz = _proposalRatingValueRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_proposalRatingValueRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalRatingValueRemoteModel() {
        return _proposalRatingValueRemoteModel;
    }

    public void setProposalRatingValueRemoteModel(
        BaseModel<?> proposalRatingValueRemoteModel) {
        _proposalRatingValueRemoteModel = proposalRatingValueRemoteModel;
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

        Class<?> remoteModelClass = _proposalRatingValueRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalRatingValueRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalRatingValueLocalServiceUtil.addProposalRatingValue(this);
        } else {
            ProposalRatingValueLocalServiceUtil.updateProposalRatingValue(this);
        }
    }

    @Override
    public ProposalRatingValue toEscapedModel() {
        return (ProposalRatingValue) ProxyUtil.newProxyInstance(ProposalRatingValue.class.getClassLoader(),
            new Class[] { ProposalRatingValue.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalRatingValueClp clone = new ProposalRatingValueClp();

        clone.setId(getId());
        clone.setRatingTypeId(getRatingTypeId());
        clone.setValue(getValue());
        clone.setName(getName());
        clone.setDescription(getDescription());

        return clone;
    }

    @Override
    public int compareTo(ProposalRatingValue proposalRatingValue) {
        long primaryKey = proposalRatingValue.getPrimaryKey();

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

        if (!(obj instanceof ProposalRatingValueClp)) {
            return false;
        }

        ProposalRatingValueClp proposalRatingValue = (ProposalRatingValueClp) obj;

        long primaryKey = proposalRatingValue.getPrimaryKey();

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
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", ratingTypeId=");
        sb.append(getRatingTypeId());
        sb.append(", value=");
        sb.append(getValue());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalRatingValue");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ratingTypeId</column-name><column-value><![CDATA[");
        sb.append(getRatingTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>value</column-name><column-value><![CDATA[");
        sb.append(getValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
