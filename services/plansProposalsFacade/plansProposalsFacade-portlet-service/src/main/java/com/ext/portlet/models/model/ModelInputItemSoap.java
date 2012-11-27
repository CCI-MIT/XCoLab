package com.ext.portlet.models.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.models.service.http.ModelInputItemServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.models.service.http.ModelInputItemServiceSoap
 * @generated
 */
public class ModelInputItemSoap implements Serializable {
    private Long _modelInputItemPK;
    private Long _modelId;
    private Long _modelInputItemID;
    private Long _modelGroupId;
    private Integer _displayItemOrder;
    private String _type;
    private String _properties;

    public ModelInputItemSoap() {
    }

    public static ModelInputItemSoap toSoapModel(ModelInputItem model) {
        ModelInputItemSoap soapModel = new ModelInputItemSoap();

        soapModel.setModelInputItemPK(model.getModelInputItemPK());
        soapModel.setModelId(model.getModelId());
        soapModel.setModelInputItemID(model.getModelInputItemID());
        soapModel.setModelGroupId(model.getModelGroupId());
        soapModel.setDisplayItemOrder(model.getDisplayItemOrder());
        soapModel.setType(model.getType());
        soapModel.setProperties(model.getProperties());

        return soapModel;
    }

    public static ModelInputItemSoap[] toSoapModels(ModelInputItem[] models) {
        ModelInputItemSoap[] soapModels = new ModelInputItemSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelInputItemSoap[][] toSoapModels(ModelInputItem[][] models) {
        ModelInputItemSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelInputItemSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelInputItemSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelInputItemSoap[] toSoapModels(List<ModelInputItem> models) {
        List<ModelInputItemSoap> soapModels = new ArrayList<ModelInputItemSoap>(models.size());

        for (ModelInputItem model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelInputItemSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _modelInputItemPK;
    }

    public void setPrimaryKey(Long pk) {
        setModelInputItemPK(pk);
    }

    public Long getModelInputItemPK() {
        return _modelInputItemPK;
    }

    public void setModelInputItemPK(Long modelInputItemPK) {
        _modelInputItemPK = modelInputItemPK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Long getModelInputItemID() {
        return _modelInputItemID;
    }

    public void setModelInputItemID(Long modelInputItemID) {
        _modelInputItemID = modelInputItemID;
    }

    public Long getModelGroupId() {
        return _modelGroupId;
    }

    public void setModelGroupId(Long modelGroupId) {
        _modelGroupId = modelGroupId;
    }

    public Integer getDisplayItemOrder() {
        return _displayItemOrder;
    }

    public void setDisplayItemOrder(Integer displayItemOrder) {
        _displayItemOrder = displayItemOrder;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public String getProperties() {
        return _properties;
    }

    public void setProperties(String properties) {
        _properties = properties;
    }
}
