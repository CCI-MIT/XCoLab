package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanTypeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PlanTypeServiceSoap
 * @generated
 */
public class PlanTypeSoap implements Serializable {
    private long _planTypeId;
    private String _name;
    private String _description;
    private long _modelId;
    private String _modelTypeName;
    private boolean _published;
    private long _publishedCounterpartId;
    private boolean _isDefault;
    private long _defaultModelId;
    private long _defaultScenarioId;

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

    public long getPrimaryKey() {
        return _planTypeId;
    }

    public void setPrimaryKey(long pk) {
        setPlanTypeId(pk);
    }

    public long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
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

    public long getModelId() {
        return _modelId;
    }

    public void setModelId(long modelId) {
        _modelId = modelId;
    }

    public String getModelTypeName() {
        return _modelTypeName;
    }

    public void setModelTypeName(String modelTypeName) {
        _modelTypeName = modelTypeName;
    }

    public boolean getPublished() {
        return _published;
    }

    public boolean isPublished() {
        return _published;
    }

    public void setPublished(boolean published) {
        _published = published;
    }

    public long getPublishedCounterpartId() {
        return _publishedCounterpartId;
    }

    public void setPublishedCounterpartId(long publishedCounterpartId) {
        _publishedCounterpartId = publishedCounterpartId;
    }

    public boolean getIsDefault() {
        return _isDefault;
    }

    public boolean isIsDefault() {
        return _isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        _isDefault = isDefault;
    }

    public long getDefaultModelId() {
        return _defaultModelId;
    }

    public void setDefaultModelId(long defaultModelId) {
        _defaultModelId = defaultModelId;
    }

    public long getDefaultScenarioId() {
        return _defaultScenarioId;
    }

    public void setDefaultScenarioId(long defaultScenarioId) {
        _defaultScenarioId = defaultScenarioId;
    }
}
