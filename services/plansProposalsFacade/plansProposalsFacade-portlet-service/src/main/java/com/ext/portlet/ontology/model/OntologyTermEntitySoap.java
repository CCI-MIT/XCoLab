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
    private Long _id;
    private Long _termId;
    private Long _classNameId;
    private Long _classPK;

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

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long pk) {
        setId(pk);
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public Long getTermId() {
        return _termId;
    }

    public void setTermId(Long termId) {
        _termId = termId;
    }

    public Long getClassNameId() {
        return _classNameId;
    }

    public void setClassNameId(Long classNameId) {
        _classNameId = classNameId;
    }

    public Long getClassPK() {
        return _classPK;
    }

    public void setClassPK(Long classPK) {
        _classPK = classPK;
    }
}
