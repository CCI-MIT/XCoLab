package com.ext.portlet.ontology.model;

import com.ext.portlet.ontology.service.OntologySpaceLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class OntologySpaceClp extends BaseModelImpl<OntologySpace>
    implements OntologySpace {
    private Long _id;
    private String _name;
    private String _description;

    public OntologySpaceClp() {
    }

    public Class<?> getModelClass() {
        return OntologySpace.class;
    }

    public String getModelClassName() {
        return OntologySpace.class.getName();
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.ontology.model.OntologyTerm getTopTerm() {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            OntologySpaceLocalServiceUtil.addOntologySpace(this);
        } else {
            OntologySpaceLocalServiceUtil.updateOntologySpace(this);
        }
    }

    @Override
    public OntologySpace toEscapedModel() {
        return (OntologySpace) Proxy.newProxyInstance(OntologySpace.class.getClassLoader(),
            new Class[] { OntologySpace.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        OntologySpaceClp clone = new OntologySpaceClp();

        clone.setId(getId());
        clone.setName(getName());
        clone.setDescription(getDescription());

        return clone;
    }

    public int compareTo(OntologySpace ontologySpace) {
        Long primaryKey = ontologySpace.getPrimaryKey();

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

        OntologySpaceClp ontologySpace = null;

        try {
            ontologySpace = (OntologySpaceClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = ontologySpace.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
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
        StringBundler sb = new StringBundler(7);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(13);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.ontology.model.OntologySpace");
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

        sb.append("</model>");

        return sb.toString();
    }
}
