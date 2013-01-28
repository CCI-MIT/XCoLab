package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ModelGlobalPreferenceServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.ModelGlobalPreferenceServiceSoap
 * @generated
 */
public class ModelGlobalPreferenceSoap implements Serializable {
    private long _modelGlobalPreferencePK;
    private long _modelId;
    private boolean _visible;
    private int _weight;
    private long _expertEvaluationPageId;
    private long _modelCategoryId;

    public ModelGlobalPreferenceSoap() {
    }

    public static ModelGlobalPreferenceSoap toSoapModel(
        ModelGlobalPreference model) {
        ModelGlobalPreferenceSoap soapModel = new ModelGlobalPreferenceSoap();

        soapModel.setModelGlobalPreferencePK(model.getModelGlobalPreferencePK());
        soapModel.setModelId(model.getModelId());
        soapModel.setVisible(model.getVisible());
        soapModel.setWeight(model.getWeight());
        soapModel.setExpertEvaluationPageId(model.getExpertEvaluationPageId());
        soapModel.setModelCategoryId(model.getModelCategoryId());

        return soapModel;
    }

    public static ModelGlobalPreferenceSoap[] toSoapModels(
        ModelGlobalPreference[] models) {
        ModelGlobalPreferenceSoap[] soapModels = new ModelGlobalPreferenceSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelGlobalPreferenceSoap[][] toSoapModels(
        ModelGlobalPreference[][] models) {
        ModelGlobalPreferenceSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelGlobalPreferenceSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelGlobalPreferenceSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelGlobalPreferenceSoap[] toSoapModels(
        List<ModelGlobalPreference> models) {
        List<ModelGlobalPreferenceSoap> soapModels = new ArrayList<ModelGlobalPreferenceSoap>(models.size());

        for (ModelGlobalPreference model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelGlobalPreferenceSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _modelGlobalPreferencePK;
    }

    public void setPrimaryKey(long pk) {
        setModelGlobalPreferencePK(pk);
    }

    public long getModelGlobalPreferencePK() {
        return _modelGlobalPreferencePK;
    }

    public void setModelGlobalPreferencePK(long modelGlobalPreferencePK) {
        _modelGlobalPreferencePK = modelGlobalPreferencePK;
    }

    public long getModelId() {
        return _modelId;
    }

    public void setModelId(long modelId) {
        _modelId = modelId;
    }

    public boolean getVisible() {
        return _visible;
    }

    public boolean isVisible() {
        return _visible;
    }

    public void setVisible(boolean visible) {
        _visible = visible;
    }

    public int getWeight() {
        return _weight;
    }

    public void setWeight(int weight) {
        _weight = weight;
    }

    public long getExpertEvaluationPageId() {
        return _expertEvaluationPageId;
    }

    public void setExpertEvaluationPageId(long expertEvaluationPageId) {
        _expertEvaluationPageId = expertEvaluationPageId;
    }

    public long getModelCategoryId() {
        return _modelCategoryId;
    }

    public void setModelCategoryId(long modelCategoryId) {
        _modelCategoryId = modelCategoryId;
    }
}
