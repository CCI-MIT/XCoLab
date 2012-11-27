package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanDescriptionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanDescriptionServiceSoap
 * @generated
 */
public class PlanDescriptionSoap implements Serializable {
    private Long _id;
    private Long _planId;
    private String _name;
    private String _description;
    private Long _version;
    private Long _planVersion;
    private Date _created;
    private Long _updateAuthorId;
    private Long _image;
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

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
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

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }

    public Long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(Long planVersion) {
        _planVersion = planVersion;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public Long getImage() {
        return _image;
    }

    public void setImage(Long image) {
        _image = image;
    }

    public String getPitch() {
        return _pitch;
    }

    public void setPitch(String pitch) {
        _pitch = pitch;
    }
}
