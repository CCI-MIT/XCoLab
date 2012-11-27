package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanPositionsServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanPositionsServiceSoap
 * @generated
 */
public class PlanPositionsSoap implements Serializable {
    private Long _id;
    private Long _planId;
    private Long _planVersion;
    private Long _version;
    private Date _created;
    private Long _updateAuthorId;

    public PlanPositionsSoap() {
    }

    public static PlanPositionsSoap toSoapModel(PlanPositions model) {
        PlanPositionsSoap soapModel = new PlanPositionsSoap();

        soapModel.setId(model.getId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setPlanVersion(model.getPlanVersion());
        soapModel.setVersion(model.getVersion());
        soapModel.setCreated(model.getCreated());
        soapModel.setUpdateAuthorId(model.getUpdateAuthorId());

        return soapModel;
    }

    public static PlanPositionsSoap[] toSoapModels(PlanPositions[] models) {
        PlanPositionsSoap[] soapModels = new PlanPositionsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionsSoap[][] toSoapModels(PlanPositions[][] models) {
        PlanPositionsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanPositionsSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanPositionsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionsSoap[] toSoapModels(List<PlanPositions> models) {
        List<PlanPositionsSoap> soapModels = new ArrayList<PlanPositionsSoap>(models.size());

        for (PlanPositions model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanPositionsSoap[soapModels.size()]);
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

    public Long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(Long planVersion) {
        _planVersion = planVersion;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
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
}
