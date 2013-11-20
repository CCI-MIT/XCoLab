package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.OntologyTermServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.OntologyTermServiceSoap
 * @generated
 */
public class OntologyTermSoap implements Serializable {
    private long _id;
    private long _parentId;
    private long _ontologySpaceId;
    private String _name;
    private String _descriptionUrl;

    public OntologyTermSoap() {
    }

    public static OntologyTermSoap toSoapModel(OntologyTerm model) {
        OntologyTermSoap soapModel = new OntologyTermSoap();

        soapModel.setId(model.getId());
        soapModel.setParentId(model.getParentId());
        soapModel.setOntologySpaceId(model.getOntologySpaceId());
        soapModel.setName(model.getName());
        soapModel.setDescriptionUrl(model.getDescriptionUrl());

        return soapModel;
    }

    public static OntologyTermSoap[] toSoapModels(OntologyTerm[] models) {
        OntologyTermSoap[] soapModels = new OntologyTermSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static OntologyTermSoap[][] toSoapModels(OntologyTerm[][] models) {
        OntologyTermSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new OntologyTermSoap[models.length][models[0].length];
        } else {
            soapModels = new OntologyTermSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static OntologyTermSoap[] toSoapModels(List<OntologyTerm> models) {
        List<OntologyTermSoap> soapModels = new ArrayList<OntologyTermSoap>(models.size());

        for (OntologyTerm model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new OntologyTermSoap[soapModels.size()]);
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
}
