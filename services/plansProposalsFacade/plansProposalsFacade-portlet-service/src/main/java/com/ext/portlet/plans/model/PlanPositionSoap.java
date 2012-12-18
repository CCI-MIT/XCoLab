package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlanPositionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanPositionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanPositionServiceSoap
 * @generated
 */
public class PlanPositionSoap implements Serializable {
    private long _planId;
    private long _positionId;
    private long _userId;
    private String _userName;
    private Date _createDate;
    private Date _modifiedDate;

    public PlanPositionSoap() {
    }

    public static PlanPositionSoap toSoapModel(PlanPosition model) {
        PlanPositionSoap soapModel = new PlanPositionSoap();

        soapModel.setPlanId(model.getPlanId());
        soapModel.setPositionId(model.getPositionId());
        soapModel.setUserId(model.getUserId());
        soapModel.setUserName(model.getUserName());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static PlanPositionSoap[] toSoapModels(PlanPosition[] models) {
        PlanPositionSoap[] soapModels = new PlanPositionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionSoap[][] toSoapModels(PlanPosition[][] models) {
        PlanPositionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanPositionSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanPositionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanPositionSoap[] toSoapModels(List<PlanPosition> models) {
        List<PlanPositionSoap> soapModels = new ArrayList<PlanPositionSoap>(models.size());

        for (PlanPosition model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanPositionSoap[soapModels.size()]);
    }

    public PlanPositionPK getPrimaryKey() {
        return new PlanPositionPK(_planId, _positionId);
    }

    public void setPrimaryKey(PlanPositionPK pk) {
        setPlanId(pk.planId);
        setPositionId(pk.positionId);
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public long getPositionId() {
        return _positionId;
    }

    public void setPositionId(long positionId) {
        _positionId = positionId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }
}
