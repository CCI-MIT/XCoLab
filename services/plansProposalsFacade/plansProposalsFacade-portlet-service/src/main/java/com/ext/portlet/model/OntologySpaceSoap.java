package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.OntologySpaceServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.OntologySpaceServiceSoap
 * @generated
 */
public class OntologySpaceSoap implements Serializable {
    private long _id;
    private String _name;
    private String _description;

    public OntologySpaceSoap() {
    }

    public static OntologySpaceSoap toSoapModel(OntologySpace model) {
        OntologySpaceSoap soapModel = new OntologySpaceSoap();

        soapModel.setId(model.getId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());

        return soapModel;
    }

    public static OntologySpaceSoap[] toSoapModels(OntologySpace[] models) {
        OntologySpaceSoap[] soapModels = new OntologySpaceSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static OntologySpaceSoap[][] toSoapModels(OntologySpace[][] models) {
        OntologySpaceSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new OntologySpaceSoap[models.length][models[0].length];
        } else {
            soapModels = new OntologySpaceSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static OntologySpaceSoap[] toSoapModels(List<OntologySpace> models) {
        List<OntologySpaceSoap> soapModels = new ArrayList<OntologySpaceSoap>(models.size());

        for (OntologySpace model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new OntologySpaceSoap[soapModels.size()]);
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
}
