package com.ext.portlet.model;

import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ProposalAttributeClp extends BaseModelImpl<ProposalAttribute>
    implements ProposalAttribute {
    private long _id;
    private long _proposalId;
    private int _version;
    private int _versionWhenCreated;
    private String _name;
    private long _additionalId;
    private long _numericValue;
    private String _stringValue;
    private double _realValue;

    public ProposalAttributeClp() {
    }

    public Class<?> getModelClass() {
        return ProposalAttribute.class;
    }

    public String getModelClassName() {
        return ProposalAttribute.class.getName();
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public long getProposalId() {
        return _proposalId;
    }

    public void setProposalId(long proposalId) {
        _proposalId = proposalId;
    }

    public int getVersion() {
        return _version;
    }

    public void setVersion(int version) {
        _version = version;
    }

    public int getVersionWhenCreated() {
        return _versionWhenCreated;
    }

    public void setVersionWhenCreated(int versionWhenCreated) {
        _versionWhenCreated = versionWhenCreated;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public long getAdditionalId() {
        return _additionalId;
    }

    public void setAdditionalId(long additionalId) {
        _additionalId = additionalId;
    }

    public long getNumericValue() {
        return _numericValue;
    }

    public void setNumericValue(long numericValue) {
        _numericValue = numericValue;
    }

    public String getStringValue() {
        return _stringValue;
    }

    public void setStringValue(String stringValue) {
        _stringValue = stringValue;
    }

    public double getRealValue() {
        return _realValue;
    }

    public void setRealValue(double realValue) {
        _realValue = realValue;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ProposalAttributeLocalServiceUtil.addProposalAttribute(this);
        } else {
            ProposalAttributeLocalServiceUtil.updateProposalAttribute(this);
        }
    }

    @Override
    public ProposalAttribute toEscapedModel() {
        return (ProposalAttribute) Proxy.newProxyInstance(ProposalAttribute.class.getClassLoader(),
            new Class[] { ProposalAttribute.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ProposalAttributeClp clone = new ProposalAttributeClp();

        clone.setId(getId());
        clone.setProposalId(getProposalId());
        clone.setVersion(getVersion());
        clone.setVersionWhenCreated(getVersionWhenCreated());
        clone.setName(getName());
        clone.setAdditionalId(getAdditionalId());
        clone.setNumericValue(getNumericValue());
        clone.setStringValue(getStringValue());
        clone.setRealValue(getRealValue());

        return clone;
    }

    public int compareTo(ProposalAttribute proposalAttribute) {
        long primaryKey = proposalAttribute.getPrimaryKey();

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
        if (obj == null) {
            return false;
        }

        ProposalAttributeClp proposalAttribute = null;

        try {
            proposalAttribute = (ProposalAttributeClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = proposalAttribute.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", proposalId=");
        sb.append(getProposalId());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", versionWhenCreated=");
        sb.append(getVersionWhenCreated());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", additionalId=");
        sb.append(getAdditionalId());
        sb.append(", numericValue=");
        sb.append(getNumericValue());
        sb.append(", stringValue=");
        sb.append(getStringValue());
        sb.append(", realValue=");
        sb.append(getRealValue());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ProposalAttribute");
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
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>versionWhenCreated</column-name><column-value><![CDATA[");
        sb.append(getVersionWhenCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>additionalId</column-name><column-value><![CDATA[");
        sb.append(getAdditionalId());
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
