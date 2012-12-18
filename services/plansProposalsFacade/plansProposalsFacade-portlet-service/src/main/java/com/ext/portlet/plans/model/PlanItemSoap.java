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
    private long _id;
    private long _planId;
    private String _state;
    private Date _updated;
    private long _updateAuthorId;
    private String _updateType;
    private long _version;

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

    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public String getUpdateType() {
        return _updateType;
    }

    public void setUpdateType(String updateType) {
        _updateType = updateType;
    }

    public long getVersion() {
        return _version;
    }

    public void setVersion(long version) {
        _version = version;
    }
}
