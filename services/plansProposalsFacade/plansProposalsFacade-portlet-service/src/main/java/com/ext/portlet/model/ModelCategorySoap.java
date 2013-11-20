package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ModelCategoryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ModelCategoryServiceSoap
 * @generated
 */
public class ModelCategorySoap implements Serializable {
    private long _modelCategoryPK;
    private String _modelCategoryName;
    private String _modelCategoryDescription;
    private int _modelCategoryDisplayWeight;

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

    public long getPrimaryKey() {
        return _modelCategoryPK;
    }

    public void setPrimaryKey(long pk) {
        setModelCategoryPK(pk);
    }

    public long getModelCategoryPK() {
        return _modelCategoryPK;
    }

    public void setModelCategoryPK(long modelCategoryPK) {
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

    public int getModelCategoryDisplayWeight() {
        return _modelCategoryDisplayWeight;
    }

    public void setModelCategoryDisplayWeight(int modelCategoryDisplayWeight) {
        _modelCategoryDisplayWeight = modelCategoryDisplayWeight;
    }
}
