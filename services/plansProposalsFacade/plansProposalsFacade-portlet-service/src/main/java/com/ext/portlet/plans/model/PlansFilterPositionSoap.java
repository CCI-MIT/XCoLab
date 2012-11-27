package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlansFilterPositionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlansFilterPositionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlansFilterPositionServiceSoap
 * @generated
 */
public class PlansFilterPositionSoap implements Serializable {
    private Long _userId;
    private Long _planTypeId;
    private Long _positionId;

    public PlansFilterPositionSoap() {
    }

    public static PlansFilterPositionSoap toSoapModel(PlansFilterPosition model) {
        PlansFilterPositionSoap soapModel = new PlansFilterPositionSoap();

        soapModel.setUserId(model.getUserId());
        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setPositionId(model.getPositionId());

        return soapModel;
    }

    public static PlansFilterPositionSoap[] toSoapModels(
        PlansFilterPosition[] models) {
        PlansFilterPositionSoap[] soapModels = new PlansFilterPositionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlansFilterPositionSoap[][] toSoapModels(
        PlansFilterPosition[][] models) {
        PlansFilterPositionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlansFilterPositionSoap[models.length][models[0].length];
        } else {
            soapModels = new PlansFilterPositionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlansFilterPositionSoap[] toSoapModels(
        List<PlansFilterPosition> models) {
        List<PlansFilterPositionSoap> soapModels = new ArrayList<PlansFilterPositionSoap>(models.size());

        for (PlansFilterPosition model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlansFilterPositionSoap[soapModels.size()]);
    }

    public PlansFilterPositionPK getPrimaryKey() {
        return new PlansFilterPositionPK(_userId, _planTypeId, _positionId);
    }

    public void setPrimaryKey(PlansFilterPositionPK pk) {
        setUserId(pk.userId);
        setPlanTypeId(pk.planTypeId);
        setPositionId(pk.positionId);
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public Long getPositionId() {
        return _positionId;
    }

    public void setPositionId(Long positionId) {
        _positionId = positionId;
    }
}
