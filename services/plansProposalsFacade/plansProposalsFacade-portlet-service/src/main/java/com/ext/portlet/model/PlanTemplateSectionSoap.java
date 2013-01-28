package com.ext.portlet.model;

import com.ext.portlet.service.persistence.PlanTemplateSectionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanTemplateSectionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.PlanTemplateSectionServiceSoap
 * @generated
 */
public class PlanTemplateSectionSoap implements Serializable {
    private long _planTemplateId;
    private long _planSectionId;
    private int _weight;

    public PlanTemplateSectionSoap() {
    }

    public static PlanTemplateSectionSoap toSoapModel(PlanTemplateSection model) {
        PlanTemplateSectionSoap soapModel = new PlanTemplateSectionSoap();

        soapModel.setPlanTemplateId(model.getPlanTemplateId());
        soapModel.setPlanSectionId(model.getPlanSectionId());
        soapModel.setWeight(model.getWeight());

        return soapModel;
    }

    public static PlanTemplateSectionSoap[] toSoapModels(
        PlanTemplateSection[] models) {
        PlanTemplateSectionSoap[] soapModels = new PlanTemplateSectionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanTemplateSectionSoap[][] toSoapModels(
        PlanTemplateSection[][] models) {
        PlanTemplateSectionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanTemplateSectionSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanTemplateSectionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanTemplateSectionSoap[] toSoapModels(
        List<PlanTemplateSection> models) {
        List<PlanTemplateSectionSoap> soapModels = new ArrayList<PlanTemplateSectionSoap>(models.size());

        for (PlanTemplateSection model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanTemplateSectionSoap[soapModels.size()]);
    }

    public PlanTemplateSectionPK getPrimaryKey() {
        return new PlanTemplateSectionPK(_planTemplateId, _planSectionId);
    }

    public void setPrimaryKey(PlanTemplateSectionPK pk) {
        setPlanTemplateId(pk.planTemplateId);
        setPlanSectionId(pk.planSectionId);
    }

    public long getPlanTemplateId() {
        return _planTemplateId;
    }

    public void setPlanTemplateId(long planTemplateId) {
        _planTemplateId = planTemplateId;
    }

    public long getPlanSectionId() {
        return _planSectionId;
    }

    public void setPlanSectionId(long planSectionId) {
        _planSectionId = planSectionId;
    }

    public int getWeight() {
        return _weight;
    }

    public void setWeight(int weight) {
        _weight = weight;
    }
}
