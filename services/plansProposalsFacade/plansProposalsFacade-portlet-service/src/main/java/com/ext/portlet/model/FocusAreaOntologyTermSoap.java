package com.ext.portlet.model;

import com.ext.portlet.service.persistence.FocusAreaOntologyTermPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.FocusAreaOntologyTermServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.FocusAreaOntologyTermServiceSoap
 * @generated
 */
public class FocusAreaOntologyTermSoap implements Serializable {
    private long _focusAreaId;
    private long _ontologyTermId;
    private int _order;

    public FocusAreaOntologyTermSoap() {
    }

    public static FocusAreaOntologyTermSoap toSoapModel(
        FocusAreaOntologyTerm model) {
        FocusAreaOntologyTermSoap soapModel = new FocusAreaOntologyTermSoap();

        soapModel.setFocusAreaId(model.getFocusAreaId());
        soapModel.setOntologyTermId(model.getOntologyTermId());
        soapModel.setOrder(model.getOrder());

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

    public long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public long getOntologyTermId() {
        return _ontologyTermId;
    }

    public void setOntologyTermId(long ontologyTermId) {
        _ontologyTermId = ontologyTermId;
    }

    public int getOrder() {
        return _order;
    }

    public void setOrder(int order) {
        _order = order;
    }
}
