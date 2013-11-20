package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanDescriptionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PlanDescriptionServiceSoap
 * @generated
 */
public class PlanDescriptionSoap implements Serializable {
    private long _id;
    private long _planId;
    private String _name;
    private String _description;
    private long _version;
    private long _planVersion;
    private Date _created;
    private long _updateAuthorId;
    private long _image;
    private String _pitch;

    public PlanDescriptionSoap() {
    }

    public static PlanDescriptionSoap toSoapModel(PlanDescription model) {
        PlanDescriptionSoap soapModel = new PlanDescriptionSoap();

        soapModel.setId(model.getId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setVersion(model.getVersion());
        soapModel.setPlanVersion(model.getPlanVersion());
        soapModel.setCreated(model.getCreated());
        soapModel.setUpdateAuthorId(model.getUpdateAuthorId());
        soapModel.setImage(model.getImage());
        soapModel.setPitch(model.getPitch());

        return soapModel;
    }

    public static PlanDescriptionSoap[] toSoapModels(PlanDescription[] models) {
        PlanDescriptionSoap[] soapModels = new PlanDescriptionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanDescriptionSoap[][] toSoapModels(
        PlanDescription[][] models) {
        PlanDescriptionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanDescriptionSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanDescriptionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanDescriptionSoap[] toSoapModels(
        List<PlanDescription> models) {
        List<PlanDescriptionSoap> soapModels = new ArrayList<PlanDescriptionSoap>(models.size());

        for (PlanDescription model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanDescriptionSoap[soapModels.size()]);
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

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
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

    public long getVersion() {
        return _version;
    }

    public void setVersion(long version) {
        _version = version;
    }

    public long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(long planVersion) {
        _planVersion = planVersion;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public long getImage() {
        return _image;
    }

    public void setImage(long image) {
        _image = image;
    }

    public String getPitch() {
        return _pitch;
    }

    public void setPitch(String pitch) {
        _pitch = pitch;
    }
}
