package com.ext.portlet.ontology.model;

import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class FocusAreaClp extends BaseModelImpl<FocusArea> implements FocusArea {
    private Long _id;
    private String _name;

    public FocusAreaClp() {
    }

    public Class<?> getModelClass() {
        return FocusArea.class;
    }

    public String getModelClassName() {
        return FocusArea.class.getName();
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

    public void store() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> getTerms() {
        throw new UnsupportedOperationException();
    }

    public void removeTerm(java.lang.Long termId) {
        throw new UnsupportedOperationException();
    }

    public void addTerm(java.lang.Long termId) {
        throw new UnsupportedOperationException();
    }

    public void tagClass(java.lang.Class clasz, java.lang.Long pk) {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            FocusAreaLocalServiceUtil.addFocusArea(this);
        } else {
            FocusAreaLocalServiceUtil.updateFocusArea(this);
        }
    }

    @Override
    public FocusArea toEscapedModel() {
        return (FocusArea) Proxy.newProxyInstance(FocusArea.class.getClassLoader(),
            new Class[] { FocusArea.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FocusAreaClp clone = new FocusAreaClp();

        clone.setId(getId());
        clone.setName(getName());

        return clone;
    }

    public int compareTo(FocusArea focusArea) {
        Long primaryKey = focusArea.getPrimaryKey();

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

        FocusAreaClp focusArea = null;

        try {
            focusArea = (FocusAreaClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = focusArea.getPrimaryKey();

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
        StringBundler sb = new StringBundler(5);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", name=");
        sb.append(getName());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.ontology.model.FocusArea");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
