package com.ext.portlet.ontology.model;

import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class OntologyTermClp extends BaseModelImpl<OntologyTerm>
    implements OntologyTerm {
    private long _id;
    private long _parentId;
    private long _ontologySpaceId;
    private String _name;
    private String _descriptionUrl;

    public OntologyTermClp() {
    }

    public Class<?> getModelClass() {
        return OntologyTerm.class;
    }

    public String getModelClassName() {
        return OntologyTerm.class.getName();
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

    public long getParentId() {
        return _parentId;
    }

    public void setParentId(long parentId) {
        _parentId = parentId;
    }

    public long getOntologySpaceId() {
        return _ontologySpaceId;
    }

    public void setOntologySpaceId(long ontologySpaceId) {
        _ontologySpaceId = ontologySpaceId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescriptionUrl() {
        return _descriptionUrl;
    }

    public void setDescriptionUrl(String descriptionUrl) {
        _descriptionUrl = descriptionUrl;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            OntologyTermLocalServiceUtil.addOntologyTerm(this);
        } else {
            OntologyTermLocalServiceUtil.updateOntologyTerm(this);
        }
    }

    @Override
    public OntologyTerm toEscapedModel() {
        return (OntologyTerm) Proxy.newProxyInstance(OntologyTerm.class.getClassLoader(),
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

        return clone;
    }

    public int compareTo(OntologyTerm ontologyTerm) {
        long primaryKey = ontologyTerm.getPrimaryKey();

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

        OntologyTermClp ontologyTerm = null;

        try {
            ontologyTerm = (OntologyTermClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = ontologyTerm.getPrimaryKey();

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
        sb.append(", parentId=");
        sb.append(getParentId());
        sb.append(", ontologySpaceId=");
        sb.append(getOntologySpaceId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", descriptionUrl=");
        sb.append(getDescriptionUrl());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(19);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.ontology.model.OntologyTerm");
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

        sb.append("</model>");

        return sb.toString();
    }
}
