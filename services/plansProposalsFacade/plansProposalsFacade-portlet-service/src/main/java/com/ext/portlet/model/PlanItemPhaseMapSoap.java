package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanItemPhaseMapServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.PlanItemPhaseMapServiceSoap
 * @generated
 */
public class PlanItemPhaseMapSoap implements Serializable {
    private long _id;
    private long _planId;

    public PlanItemPhaseMapSoap() {
    }

    public static PlanItemPhaseMapSoap toSoapModel(PlanItemPhaseMap model) {
        PlanItemPhaseMapSoap soapModel = new PlanItemPhaseMapSoap();

        soapModel.setId(model.getId());
        soapModel.setPlanId(model.getPlanId());

        return soapModel;
    }

    public static PlanItemPhaseMapSoap[] toSoapModels(PlanItemPhaseMap[] models) {
        PlanItemPhaseMapSoap[] soapModels = new PlanItemPhaseMapSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanItemPhaseMapSoap[][] toSoapModels(
        PlanItemPhaseMap[][] models) {
        PlanItemPhaseMapSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanItemPhaseMapSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanItemPhaseMapSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanItemPhaseMapSoap[] toSoapModels(
        List<PlanItemPhaseMap> models) {
        List<PlanItemPhaseMapSoap> soapModels = new ArrayList<PlanItemPhaseMapSoap>(models.size());

        for (PlanItemPhaseMap model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanItemPhaseMapSoap[soapModels.size()]);
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
}
