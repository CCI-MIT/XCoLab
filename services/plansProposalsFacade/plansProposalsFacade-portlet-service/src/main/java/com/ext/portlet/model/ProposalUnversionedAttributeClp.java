package com.ext.portlet.model;

import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ProposalUnversionedAttributeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ProposalUnversionedAttributeClp extends BaseModelImpl<ProposalUnversionedAttribute>
    implements ProposalUnversionedAttribute {
    private long _id;
    private long _proposalId;
    private long _createAuthorId;
    private long _lastAuthorId;
    private Date _createDate;
    private Date _lastUpdateDate;
    private String _name;
    private int _addtionalId;
    private long _numericValue;
    private String _stringValue;
    private double _realValue;
    private BaseModel<?> _proposalUnversionedAttributeRemoteModel;
    private Class<?> _clpSerializerClass = com.ext.portlet.service.ClpSerializer.class;

    public ProposalUnversionedAttributeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ProposalUnversionedAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return ProposalUnversionedAttribute.class.getName();
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
        attributes.put("proposalId", getProposalId());
        attributes.put("createAuthorId", getCreateAuthorId());
        attributes.put("lastAuthorId", getLastAuthorId());
        attributes.put("createDate", getCreateDate());
        attributes.put("lastUpdateDate", getLastUpdateDate());
        attributes.put("name", getName());
        attributes.put("addtionalId", getAddtionalId());
        attributes.put("numericValue", getNumericValue());
        attributes.put("stringValue", getStringValue());
        attributes.put("realValue", getRealValue());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long proposalId = (Long) attributes.get("proposalId");

        if (proposalId != null) {
            setProposalId(proposalId);
        }

        Long createAuthorId = (Long) attributes.get("createAuthorId");

        if (createAuthorId != null) {
            setCreateAuthorId(createAuthorId);
        }

        Long lastAuthorId = (Long) attributes.get("lastAuthorId");

        if (lastAuthorId != null) {
            setLastAuthorId(lastAuthorId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date lastUpdateDate = (Date) attributes.get("lastUpdateDate");

        if (lastUpdateDate != null) {
            setLastUpdateDate(lastUpdateDate);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Integer addtionalId = (Integer) attributes.get("addtionalId");

        if (addtionalId != null) {
            setAddtionalId(addtionalId);
        }

        Long numericValue = (Long) attributes.get("numericValue");

        if (numericValue != null) {
            setNumericValue(numericValue);
        }

        String stringValue = (String) attributes.get("stringValue");

        if (stringValue != null) {
            setStringValue(stringValue);
        }

        Double realValue = (Double) attributes.get("realValue");

        if (realValue != null) {
            setRealValue(realValue);
        }
    }

    @Override
    public long getId() {
        return _id;
    }

    @Override
    public void setId(long id) {
        _id = id;

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setId", long.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel, id);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getProposalId() {
        return _proposalId;
    }

    @Override
    public void setProposalId(long proposalId) {
        _proposalId = proposalId;

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setProposalId", long.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel,
                    proposalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getCreateAuthorId() {
        return _createAuthorId;
    }

    @Override
    public void setCreateAuthorId(long createAuthorId) {
        _createAuthorId = createAuthorId;

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateAuthorId", long.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel,
                    createAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getLastAuthorId() {
        return _lastAuthorId;
    }

    @Override
    public void setLastAuthorId(long lastAuthorId) {
        _lastAuthorId = lastAuthorId;

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setLastAuthorId", long.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel,
                    lastAuthorId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreateDate() {
        return _createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        _createDate = createDate;

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setCreateDate", Date.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel,
                    createDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastUpdateDate() {
        return _lastUpdateDate;
    }

    @Override
    public void setLastUpdateDate(Date lastUpdateDate) {
        _lastUpdateDate = lastUpdateDate;

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdateDate", Date.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel,
                    lastUpdateDate);
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

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setName", String.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel, name);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAddtionalId() {
        return _addtionalId;
    }

    @Override
    public void setAddtionalId(int addtionalId) {
        _addtionalId = addtionalId;

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setAddtionalId", int.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel,
                    addtionalId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public long getNumericValue() {
        return _numericValue;
    }

    @Override
    public void setNumericValue(long numericValue) {
        _numericValue = numericValue;

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setNumericValue", long.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel,
                    numericValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStringValue() {
        return _stringValue;
    }

    @Override
    public void setStringValue(String stringValue) {
        _stringValue = stringValue;

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setStringValue", String.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel,
                    stringValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getRealValue() {
        return _realValue;
    }

    @Override
    public void setRealValue(double realValue) {
        _realValue = realValue;

        if (_proposalUnversionedAttributeRemoteModel != null) {
            try {
                Class<?> clazz = _proposalUnversionedAttributeRemoteModel.getClass();

                Method method = clazz.getMethod("setRealValue", double.class);

                method.invoke(_proposalUnversionedAttributeRemoteModel,
                    realValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getProposalUnversionedAttributeRemoteModel() {
        return _proposalUnversionedAttributeRemoteModel;
    }

    public void setProposalUnversionedAttributeRemoteModel(
        BaseModel<?> proposalUnversionedAttributeRemoteModel) {
        _proposalUnversionedAttributeRemoteModel = proposalUnversionedAttributeRemoteModel;
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

        Class<?> remoteModelClass = _proposalUnversionedAttributeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_proposalUnversionedAttributeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalUnversionedAttributeLocalServiceUtil.addProposalUnversionedAttribute(this);
        } else {
            ProposalUnversionedAttributeLocalServiceUtil.updateProposalUnversionedAttribute(this);
        }
    }

    @Override
    public ProposalUnversionedAttribute toEscapedModel() {
        return (ProposalUnversionedAttribute) ProxyUtil.newProxyInstance(ProposalUnversionedAttribute.class.getClassLoader(),
            new Class[] { ProposalUnversionedAttribute.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalUnversionedAttributeClp clone = new ProposalUnversionedAttributeClp();

        clone.setId(getId());
        clone.setProposalId(getProposalId());
        clone.setCreateAuthorId(getCreateAuthorId());
        clone.setLastAuthorId(getLastAuthorId());
        clone.setCreateDate(getCreateDate());
        clone.setLastUpdateDate(getLastUpdateDate());
        clone.setName(getName());
        clone.setAddtionalId(getAddtionalId());
        clone.setNumericValue(getNumericValue());
        clone.setStringValue(getStringValue());
        clone.setRealValue(getRealValue());

        return clone;
    }

    @Override
    public int compareTo(
        ProposalUnversionedAttribute proposalUnversionedAttribute) {
        long primaryKey = proposalUnversionedAttribute.getPrimaryKey();

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

        if (!(obj instanceof ProposalUnversionedAttributeClp)) {
            return false;
        }

        ProposalUnversionedAttributeClp proposalUnversionedAttribute = (ProposalUnversionedAttributeClp) obj;

        long primaryKey = proposalUnversionedAttribute.getPrimaryKey();

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
        StringBundler sb = new StringBundler(23);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", proposalId=");
        sb.append(getProposalId());
        sb.append(", createAuthorId=");
        sb.append(getCreateAuthorId());
        sb.append(", lastAuthorId=");
        sb.append(getLastAuthorId());
        sb.append(", createDate=");
        sb.append(getCreateDate());
        sb.append(", lastUpdateDate=");
        sb.append(getLastUpdateDate());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", addtionalId=");
        sb.append(getAddtionalId());
        sb.append(", numericValue=");
        sb.append(getNumericValue());
        sb.append(", stringValue=");
        sb.append(getStringValue());
        sb.append(", realValue=");
        sb.append(getRealValue());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(37);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalUnversionedAttribute");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>proposalId</column-name><column-value><![CDATA[");
        sb.append(getProposalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createAuthorId</column-name><column-value><![CDATA[");
        sb.append(getCreateAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastAuthorId</column-name><column-value><![CDATA[");
        sb.append(getLastAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createDate</column-name><column-value><![CDATA[");
        sb.append(getCreateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastUpdateDate</column-name><column-value><![CDATA[");
        sb.append(getLastUpdateDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addtionalId</column-name><column-value><![CDATA[");
        sb.append(getAddtionalId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>numericValue</column-name><column-value><![CDATA[");
        sb.append(getNumericValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>stringValue</column-name><column-value><![CDATA[");
        sb.append(getStringValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>realValue</column-name><column-value><![CDATA[");
        sb.append(getRealValue());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
