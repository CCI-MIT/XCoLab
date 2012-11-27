package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanItemServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanItemServiceSoap
 * @generated
 */
public class PlanItemSoap implements Serializable {
    private Long _id;
    private Long _planId;
    private String _state;
    private Date _updated;
    private Long _updateAuthorId;
    private String _updateType;
    private Long _version;

    public PlanItemSoap() {
    }

    public static PlanItemSoap toSoapModel(PlanItem model) {
        PlanItemSoap soapModel = new PlanItemSoap();

        soapModel.setId(model.getId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setState(model.getState());
        soapModel.setUpdated(model.getUpdated());
        soapModel.setUpdateAuthorId(model.getUpdateAuthorId());
        soapModel.setUpdateType(model.getUpdateType());
        soapModel.setVersion(model.getVersion());

        return soapModel;
    }

    public static PlanItemSoap[] toSoapModels(PlanItem[] models) {
        PlanItemSoap[] soapModels = new PlanItemSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanItemSoap[][] toSoapModels(PlanItem[][] models) {
        PlanItemSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanItemSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanItemSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanItemSoap[] toSoapModels(List<PlanItem> models) {
        List<PlanItemSoap> soapModels = new ArrayList<PlanItemSoap>(models.size());

        for (PlanItem model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanItemSoap[soapModels.size()]);
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

    public String getState() {
        return _state;
    }

    public void setState(String state) {
        _state = state;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public String getUpdateType() {
        return _updateType;
    }

    public void setUpdateType(String updateType) {
        _updateType = updateType;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }
}
