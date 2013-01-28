package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanAttributeServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.PlanAttributeServiceSoap
 * @generated
 */
public class PlanAttributeSoap implements Serializable {
    private long _attributeId;
    private long _planId;
    private String _attributeName;
    private String _attributeValue;

    public PlanAttributeSoap() {
    }

    public static PlanAttributeSoap toSoapModel(PlanAttribute model) {
        PlanAttributeSoap soapModel = new PlanAttributeSoap();

        soapModel.setAttributeId(model.getAttributeId());
        soapModel.setPlanId(model.getPlanId());
        soapModel.setAttributeName(model.getAttributeName());
        soapModel.setAttributeValue(model.getAttributeValue());

        return soapModel;
    }

    public static PlanAttributeSoap[] toSoapModels(PlanAttribute[] models) {
        PlanAttributeSoap[] soapModels = new PlanAttributeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanAttributeSoap[][] toSoapModels(PlanAttribute[][] models) {
        PlanAttributeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanAttributeSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanAttributeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanAttributeSoap[] toSoapModels(List<PlanAttribute> models) {
        List<PlanAttributeSoap> soapModels = new ArrayList<PlanAttributeSoap>(models.size());

        for (PlanAttribute model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanAttributeSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _attributeId;
    }

    public void setPrimaryKey(long pk) {
        setAttributeId(pk);
    }

    public long getAttributeId() {
        return _attributeId;
    }

    public void setAttributeId(long attributeId) {
        _attributeId = attributeId;
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public String getAttributeName() {
        return _attributeName;
    }

    public void setAttributeName(String attributeName) {
        _attributeName = attributeName;
    }

    public String getAttributeValue() {
        return _attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        _attributeValue = attributeValue;
    }
}
