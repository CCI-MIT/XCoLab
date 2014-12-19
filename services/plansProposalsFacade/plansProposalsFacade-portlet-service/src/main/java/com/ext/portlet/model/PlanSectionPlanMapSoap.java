package com.ext.portlet.model;

import com.ext.portlet.service.persistence.PlanSectionPlanMapPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanSectionPlanMapServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PlanSectionPlanMapServiceSoap
 * @generated
 */
public class PlanSectionPlanMapSoap implements Serializable {
    private long _sectionId;
    private long _relatedPlanId;

    public PlanSectionPlanMapSoap() {
    }

    public static PlanSectionPlanMapSoap toSoapModel(PlanSectionPlanMap model) {
        PlanSectionPlanMapSoap soapModel = new PlanSectionPlanMapSoap();

        soapModel.setSectionId(model.getSectionId());
        soapModel.setRelatedPlanId(model.getRelatedPlanId());

        return soapModel;
    }

    public static PlanSectionPlanMapSoap[] toSoapModels(
        PlanSectionPlanMap[] models) {
        PlanSectionPlanMapSoap[] soapModels = new PlanSectionPlanMapSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanSectionPlanMapSoap[][] toSoapModels(
        PlanSectionPlanMap[][] models) {
        PlanSectionPlanMapSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanSectionPlanMapSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanSectionPlanMapSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanSectionPlanMapSoap[] toSoapModels(
        List<PlanSectionPlanMap> models) {
        List<PlanSectionPlanMapSoap> soapModels = new ArrayList<PlanSectionPlanMapSoap>(models.size());

        for (PlanSectionPlanMap model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanSectionPlanMapSoap[soapModels.size()]);
    }

    public PlanSectionPlanMapPK getPrimaryKey() {
        return new PlanSectionPlanMapPK(_sectionId, _relatedPlanId);
    }

    public void setPrimaryKey(PlanSectionPlanMapPK pk) {
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
