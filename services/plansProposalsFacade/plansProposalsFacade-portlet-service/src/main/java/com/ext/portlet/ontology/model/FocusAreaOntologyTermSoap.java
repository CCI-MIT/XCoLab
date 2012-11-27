package com.ext.portlet.ontology.model;

import com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.ontology.service.http.FocusAreaOntologyTermServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.ontology.service.http.FocusAreaOntologyTermServiceSoap
 * @generated
 */
public class FocusAreaOntologyTermSoap implements Serializable {
    private Long _focusAreaId;
    private Long _ontologyTermId;

    public FocusAreaOntologyTermSoap() {
    }

    public static FocusAreaOntologyTermSoap toSoapModel(
        FocusAreaOntologyTerm model) {
        FocusAreaOntologyTermSoap soapModel = new FocusAreaOntologyTermSoap();

        soapModel.setFocusAreaId(model.getFocusAreaId());
        soapModel.setOntologyTermId(model.getOntologyTermId());

        return soapModel;
    }

    public static FocusAreaOntologyTermSoap[] toSoapModels(
        FocusAreaOntologyTerm[] models) {
        FocusAreaOntologyTermSoap[] soapModels = new FocusAreaOntologyTermSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static FocusAreaOntologyTermSoap[][] toSoapModels(
        FocusAreaOntologyTerm[][] models) {
        FocusAreaOntologyTermSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new FocusAreaOntologyTermSoap[models.length][models[0].length];
        } else {
            soapModels = new FocusAreaOntologyTermSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static FocusAreaOntologyTermSoap[] toSoapModels(
        List<FocusAreaOntologyTerm> models) {
        List<FocusAreaOntologyTermSoap> soapModels = new ArrayList<FocusAreaOntologyTermSoap>(models.size());

        for (FocusAreaOntologyTerm model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new FocusAreaOntologyTermSoap[soapModels.size()]);
    }

    public FocusAreaOntologyTermPK getPrimaryKey() {
        return new FocusAreaOntologyTermPK(_focusAreaId, _ontologyTermId);
    }

    public void setPrimaryKey(FocusAreaOntologyTermPK pk) {
        setFocusAreaId(pk.focusAreaId);
        setOntologyTermId(pk.ontologyTermId);
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
}
