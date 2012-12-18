package com.ext.portlet.ontology.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.ontology.service.http.OntologyTermEntityServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.ontology.service.http.OntologyTermEntityServiceSoap
 * @generated
 */
public class OntologyTermEntitySoap implements Serializable {
    private long _id;
    private long _termId;
    private long _classNameId;
    private long _classPK;

    public OntologyTermEntitySoap() {
    }

    public static OntologyTermEntitySoap toSoapModel(OntologyTermEntity model) {
        OntologyTermEntitySoap soapModel = new OntologyTermEntitySoap();

        soapModel.setId(model.getId());
        soapModel.setTermId(model.getTermId());
        soapModel.setClassNameId(model.getClassNameId());
        soapModel.setClassPK(model.getClassPK());

        return soapModel;
    }

    public static OntologyTermEntitySoap[] toSoapModels(
        OntologyTermEntity[] models) {
        OntologyTermEntitySoap[] soapModels = new OntologyTermEntitySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static OntologyTermEntitySoap[][] toSoapModels(
        OntologyTermEntity[][] models) {
        OntologyTermEntitySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new OntologyTermEntitySoap[models.length][models[0].length];
        } else {
            soapModels = new OntologyTermEntitySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static OntologyTermEntitySoap[] toSoapModels(
        List<OntologyTermEntity> models) {
        List<OntologyTermEntitySoap> soapModels = new ArrayList<OntologyTermEntitySoap>(models.size());

        for (OntologyTermEntity model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new OntologyTermEntitySoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long pk) {
        setId(pk);
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public long getTermId() {
        return _termId;
    }

    public void setTermId(long termId) {
        _termId = termId;
    }

    public long getClassNameId() {
        return _classNameId;
    }

    public void setClassNameId(long classNameId) {
        _classNameId = classNameId;
    }

    public long getClassPK() {
        return _classPK;
    }

    public void setClassPK(long classPK) {
        _classPK = classPK;
    }
}
