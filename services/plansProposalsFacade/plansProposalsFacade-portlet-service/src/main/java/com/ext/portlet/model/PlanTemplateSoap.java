package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanTemplateServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PlanTemplateServiceSoap
 * @generated
 */
public class PlanTemplateSoap implements Serializable {
    private long _id;
    private String _name;
    private Long _baseTemplateId;
    private Long _impactSeriesTemplateId;
    private Long _focusAreaListTemplateId;

    public PlanTemplateSoap() {
    }

    public static PlanTemplateSoap toSoapModel(PlanTemplate model) {
        PlanTemplateSoap soapModel = new PlanTemplateSoap();

        soapModel.setId(model.getId());
        soapModel.setName(model.getName());
        soapModel.setBaseTemplateId(model.getBaseTemplateId());
        soapModel.setImpactSeriesTemplateId(model.getImpactSeriesTemplateId());
        soapModel.setFocusAreaListTemplateId(model.getFocusAreaListTemplateId());

        return soapModel;
    }

    public static PlanTemplateSoap[] toSoapModels(PlanTemplate[] models) {
        PlanTemplateSoap[] soapModels = new PlanTemplateSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanTemplateSoap[][] toSoapModels(PlanTemplate[][] models) {
        PlanTemplateSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanTemplateSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanTemplateSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanTemplateSoap[] toSoapModels(List<PlanTemplate> models) {
        List<PlanTemplateSoap> soapModels = new ArrayList<PlanTemplateSoap>(models.size());

        for (PlanTemplate model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanTemplateSoap[soapModels.size()]);
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

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Long getBaseTemplateId() {
        return _baseTemplateId;
    }

    public void setBaseTemplateId(Long baseTemplateId) {
        _baseTemplateId = baseTemplateId;
    }

    public Long getImpactSeriesTemplateId() {
        return _impactSeriesTemplateId;
    }

    public void setImpactSeriesTemplateId(Long impactSeriesTemplateId) {
        _impactSeriesTemplateId = impactSeriesTemplateId;
    }

    public Long getFocusAreaListTemplateId() {
        return _focusAreaListTemplateId;
    }

    public void setFocusAreaListTemplateId(Long focusAreaListTemplateId) {
        _focusAreaListTemplateId = focusAreaListTemplateId;
    }
}
