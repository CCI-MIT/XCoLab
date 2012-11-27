package com.ext.portlet.models.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.models.service.http.ModelCategoryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.models.service.http.ModelCategoryServiceSoap
 * @generated
 */
public class ModelCategorySoap implements Serializable {
    private Long _modelCategoryPK;
    private String _modelCategoryName;
    private String _modelCategoryDescription;
    private Integer _modelCategoryDisplayWeight;

    public ModelCategorySoap() {
    }

    public static ModelCategorySoap toSoapModel(ModelCategory model) {
        ModelCategorySoap soapModel = new ModelCategorySoap();

        soapModel.setModelCategoryPK(model.getModelCategoryPK());
        soapModel.setModelCategoryName(model.getModelCategoryName());
        soapModel.setModelCategoryDescription(model.getModelCategoryDescription());
        soapModel.setModelCategoryDisplayWeight(model.getModelCategoryDisplayWeight());

        return soapModel;
    }

    public static ModelCategorySoap[] toSoapModels(ModelCategory[] models) {
        ModelCategorySoap[] soapModels = new ModelCategorySoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelCategorySoap[][] toSoapModels(ModelCategory[][] models) {
        ModelCategorySoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelCategorySoap[models.length][models[0].length];
        } else {
            soapModels = new ModelCategorySoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelCategorySoap[] toSoapModels(List<ModelCategory> models) {
        List<ModelCategorySoap> soapModels = new ArrayList<ModelCategorySoap>(models.size());

        for (ModelCategory model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelCategorySoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _modelCategoryPK;
    }

    public void setPrimaryKey(Long pk) {
        setModelCategoryPK(pk);
    }

    public Long getModelCategoryPK() {
        return _modelCategoryPK;
    }

    public void setModelCategoryPK(Long modelCategoryPK) {
        _modelCategoryPK = modelCategoryPK;
    }

    public String getModelCategoryName() {
        return _modelCategoryName;
    }

    public void setModelCategoryName(String modelCategoryName) {
        _modelCategoryName = modelCategoryName;
    }

    public String getModelCategoryDescription() {
        return _modelCategoryDescription;
    }

    public void setModelCategoryDescription(String modelCategoryDescription) {
        _modelCategoryDescription = modelCategoryDescription;
    }

    public Integer getModelCategoryDisplayWeight() {
        return _modelCategoryDisplayWeight;
    }

    public void setModelCategoryDisplayWeight(
        Integer modelCategoryDisplayWeight) {
        _modelCategoryDisplayWeight = modelCategoryDisplayWeight;
    }
}
