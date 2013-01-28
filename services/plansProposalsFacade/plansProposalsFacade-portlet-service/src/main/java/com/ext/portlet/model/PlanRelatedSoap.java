package com.ext.portlet.model;

import com.ext.portlet.service.persistence.PlanRelatedPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanRelatedServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.PlanRelatedServiceSoap
 * @generated
 */
public class PlanRelatedSoap implements Serializable {
    private long _sectionId;
    private long _relatedPlanId;

    public PlanRelatedSoap() {
    }

    public static PlanRelatedSoap toSoapModel(PlanRelated model) {
        PlanRelatedSoap soapModel = new PlanRelatedSoap();

        soapModel.setSectionId(model.getSectionId());
        soapModel.setRelatedPlanId(model.getRelatedPlanId());

        return soapModel;
    }

    public static PlanRelatedSoap[] toSoapModels(PlanRelated[] models) {
        PlanRelatedSoap[] soapModels = new PlanRelatedSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanRelatedSoap[][] toSoapModels(PlanRelated[][] models) {
        PlanRelatedSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanRelatedSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanRelatedSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanRelatedSoap[] toSoapModels(List<PlanRelated> models) {
        List<PlanRelatedSoap> soapModels = new ArrayList<PlanRelatedSoap>(models.size());

        for (PlanRelated model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanRelatedSoap[soapModels.size()]);
    }

    public PlanRelatedPK getPrimaryKey() {
        return new PlanRelatedPK(_sectionId, _relatedPlanId);
    }

    public void setPrimaryKey(PlanRelatedPK pk) {
        setSectionId(pk.sectionId);
        setRelatedPlanId(pk.relatedPlanId);
    }

    public long getSectionId() {
        return _sectionId;
    }

    public void setSectionId(long sectionId) {
        _sectionId = sectionId;
    }

    public long getRelatedPlanId() {
        return _relatedPlanId;
    }

    public void setRelatedPlanId(long relatedPlanId) {
        _relatedPlanId = relatedPlanId;
    }
}
