package com.ext.portlet.ontology.model;

import com.ext.portlet.ontology.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class FocusAreaOntologyTermClp extends BaseModelImpl<FocusAreaOntologyTerm>
    implements FocusAreaOntologyTerm {
    private Long _focusAreaId;
    private Long _ontologyTermId;

    public FocusAreaOntologyTermClp() {
    }

    public Class<?> getModelClass() {
        return FocusAreaOntologyTerm.class;
    }

    public String getModelClassName() {
        return FocusAreaOntologyTerm.class.getName();
    }

    public FocusAreaOntologyTermPK getPrimaryKey() {
        return new FocusAreaOntologyTermPK(_focusAreaId, _ontologyTermId);
    }

    public void setPrimaryKey(FocusAreaOntologyTermPK primaryKey) {
        setFocusAreaId(primaryKey.focusAreaId);
        setOntologyTermId(primaryKey.ontologyTermId);
    }

    public Serializable getPrimaryKeyObj() {
        return new FocusAreaOntologyTermPK(_focusAreaId, _ontologyTermId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((FocusAreaOntologyTermPK) primaryKeyObj);
    }

    public Long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(Long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public Long getOntologyTermId() {
        return _ontologyTermId;
    }

    public void setOntologyTermId(Long ontologyTermId) {
        _ontologyTermId = ontologyTermId;
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.ontology.model.OntologyTerm getTerm() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.ontology.model.FocusArea getArea() {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            FocusAreaOntologyTermLocalServiceUtil.addFocusAreaOntologyTerm(this);
        } else {
            FocusAreaOntologyTermLocalServiceUtil.updateFocusAreaOntologyTerm(this);
        }
    }

    @Override
    public FocusAreaOntologyTerm toEscapedModel() {
        return (FocusAreaOntologyTerm) Proxy.newProxyInstance(FocusAreaOntologyTerm.class.getClassLoader(),
            new Class[] { FocusAreaOntologyTerm.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        FocusAreaOntologyTermClp clone = new FocusAreaOntologyTermClp();

        clone.setFocusAreaId(getFocusAreaId());
        clone.setOntologyTermId(getOntologyTermId());

        return clone;
    }

    public int compareTo(FocusAreaOntologyTerm focusAreaOntologyTerm) {
        FocusAreaOntologyTermPK primaryKey = focusAreaOntologyTerm.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        FocusAreaOntologyTermClp focusAreaOntologyTerm = null;

        try {
            focusAreaOntologyTerm = (FocusAreaOntologyTermClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        FocusAreaOntologyTermPK primaryKey = focusAreaOntologyTerm.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
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

        sb.append("{focusAreaId=");
        sb.append(getFocusAreaId());
        sb.append(", ontologyTermId=");
        sb.append(getOntologyTermId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(10);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.ontology.model.FocusAreaOntologyTerm");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>focusAreaId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ontologyTermId</column-name><column-value><![CDATA[");
        sb.append(getOntologyTermId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
