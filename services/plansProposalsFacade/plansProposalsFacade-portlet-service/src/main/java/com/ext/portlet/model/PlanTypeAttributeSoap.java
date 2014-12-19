package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanTypeAttributeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PlanTypeAttributeServiceSoap
 * @generated
 */
public class PlanTypeAttributeSoap implements Serializable {
    private long _planTypeAttributeId;
    private long _planTypeId;
    private String _attributeName;

    public PlanTypeAttributeSoap() {
    }

    public static PlanTypeAttributeSoap toSoapModel(PlanTypeAttribute model) {
        PlanTypeAttributeSoap soapModel = new PlanTypeAttributeSoap();

        soapModel.setPlanTypeAttributeId(model.getPlanTypeAttributeId());
        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setAttributeName(model.getAttributeName());

        return soapModel;
    }

    public static PlanTypeAttributeSoap[] toSoapModels(
        PlanTypeAttribute[] models) {
        PlanTypeAttributeSoap[] soapModels = new PlanTypeAttributeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanTypeAttributeSoap[][] toSoapModels(
        PlanTypeAttribute[][] models) {
        PlanTypeAttributeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanTypeAttributeSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanTypeAttributeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanTypeAttributeSoap[] toSoapModels(
        List<PlanTypeAttribute> models) {
        List<PlanTypeAttributeSoap> soapModels = new ArrayList<PlanTypeAttributeSoap>(models.size());

        for (PlanTypeAttribute model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanTypeAttributeSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _planTypeAttributeId;
    }

    public void setPrimaryKey(long pk) {
        setPlanTypeAttributeId(pk);
    }

    public long getPlanTypeAttributeId() {
        return _planTypeAttributeId;
    }

    public void setPlanTypeAttributeId(long planTypeAttributeId) {
        _planTypeAttributeId = planTypeAttributeId;
    }

    public long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;
    }

    public String getAttributeName() {
        return _attributeName;
    }

    public void setAttributeName(String attributeName) {
        _attributeName = attributeName;
    }
}
