package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanTypeServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanTypeServiceSoap
 * @generated
 */
public class PlanTypeSoap implements Serializable {
    private Long _planTypeId;
    private String _name;
    private String _description;
    private Long _modelId;
    private String _modelTypeName;
    private Boolean _published;
    private Long _publishedCounterpartId;
    private Boolean _isDefault;
    private Long _defaultModelId;
    private Long _defaultScenarioId;

    public PlanTypeSoap() {
    }

    public static PlanTypeSoap toSoapModel(PlanType model) {
        PlanTypeSoap soapModel = new PlanTypeSoap();

        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setModelId(model.getModelId());
        soapModel.setModelTypeName(model.getModelTypeName());
        soapModel.setPublished(model.getPublished());
        soapModel.setPublishedCounterpartId(model.getPublishedCounterpartId());
        soapModel.setIsDefault(model.getIsDefault());
        soapModel.setDefaultModelId(model.getDefaultModelId());
        soapModel.setDefaultScenarioId(model.getDefaultScenarioId());

        return soapModel;
    }

    public static PlanTypeSoap[] toSoapModels(PlanType[] models) {
        PlanTypeSoap[] soapModels = new PlanTypeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanTypeSoap[][] toSoapModels(PlanType[][] models) {
        PlanTypeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanTypeSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanTypeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanTypeSoap[] toSoapModels(List<PlanType> models) {
        List<PlanTypeSoap> soapModels = new ArrayList<PlanTypeSoap>(models.size());

        for (PlanType model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanTypeSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _planTypeId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanTypeId(pk);
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public String getModelTypeName() {
        return _modelTypeName;
    }

    public void setModelTypeName(String modelTypeName) {
        _modelTypeName = modelTypeName;
    }

    public Boolean getPublished() {
        return _published;
    }

    public void setPublished(Boolean published) {
        _published = published;
    }

    public Long getPublishedCounterpartId() {
        return _publishedCounterpartId;
    }

    public void setPublishedCounterpartId(Long publishedCounterpartId) {
        _publishedCounterpartId = publishedCounterpartId;
    }

    public Boolean getIsDefault() {
        return _isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        _isDefault = isDefault;
    }

    public Long getDefaultModelId() {
        return _defaultModelId;
    }

    public void setDefaultModelId(Long defaultModelId) {
        _defaultModelId = defaultModelId;
    }

    public Long getDefaultScenarioId() {
        return _defaultScenarioId;
    }

    public void setDefaultScenarioId(Long defaultScenarioId) {
        _defaultScenarioId = defaultScenarioId;
    }
}
