package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ModelDiscussionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.service.http.ModelDiscussionServiceSoap
 * @generated
 */
public class ModelDiscussionSoap implements Serializable {
    private long _modelDiscussionId;
    private long _modelId;
    private long _categoryId;

    public ModelDiscussionSoap() {
    }

    public static ModelDiscussionSoap toSoapModel(ModelDiscussion model) {
        ModelDiscussionSoap soapModel = new ModelDiscussionSoap();

        soapModel.setModelDiscussionId(model.getModelDiscussionId());
        soapModel.setModelId(model.getModelId());
        soapModel.setCategoryId(model.getCategoryId());

        return soapModel;
    }

    public static ModelDiscussionSoap[] toSoapModels(ModelDiscussion[] models) {
        ModelDiscussionSoap[] soapModels = new ModelDiscussionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelDiscussionSoap[][] toSoapModels(
        ModelDiscussion[][] models) {
        ModelDiscussionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelDiscussionSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelDiscussionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelDiscussionSoap[] toSoapModels(
        List<ModelDiscussion> models) {
        List<ModelDiscussionSoap> soapModels = new ArrayList<ModelDiscussionSoap>(models.size());

        for (ModelDiscussion model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelDiscussionSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _modelDiscussionId;
    }

    public void setPrimaryKey(long pk) {
        setModelDiscussionId(pk);
    }

    public long getModelDiscussionId() {
        return _modelDiscussionId;
    }

    public void setModelDiscussionId(long modelDiscussionId) {
        _modelDiscussionId = modelDiscussionId;
    }

    public long getModelId() {
        return _modelId;
    }

    public void setModelId(long modelId) {
        _modelId = modelId;
    }

    public long getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(long categoryId) {
        _categoryId = categoryId;
    }
}
