package com.ext.portlet.models.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.models.service.http.ModelInputGroupServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.models.service.http.ModelInputGroupServiceSoap
 * @generated
 */
public class ModelInputGroupSoap implements Serializable {
    private Long _modelInputGroupPK;
    private Long _modelId;
    private Long _nameAndDescriptionMetaDataId;
    private String _name;
    private String _description;
    private Integer _displayItemOrder;
    private String _groupType;
    private Long _parentGroupPK;

    public ModelInputGroupSoap() {
    }

    public static ModelInputGroupSoap toSoapModel(ModelInputGroup model) {
        ModelInputGroupSoap soapModel = new ModelInputGroupSoap();

        soapModel.setModelInputGroupPK(model.getModelInputGroupPK());
        soapModel.setModelId(model.getModelId());
        soapModel.setNameAndDescriptionMetaDataId(model.getNameAndDescriptionMetaDataId());
        soapModel.setName(model.getName());
        soapModel.setDescription(model.getDescription());
        soapModel.setDisplayItemOrder(model.getDisplayItemOrder());
        soapModel.setGroupType(model.getGroupType());
        soapModel.setParentGroupPK(model.getParentGroupPK());

        return soapModel;
    }

    public static ModelInputGroupSoap[] toSoapModels(ModelInputGroup[] models) {
        ModelInputGroupSoap[] soapModels = new ModelInputGroupSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelInputGroupSoap[][] toSoapModels(
        ModelInputGroup[][] models) {
        ModelInputGroupSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelInputGroupSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelInputGroupSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelInputGroupSoap[] toSoapModels(
        List<ModelInputGroup> models) {
        List<ModelInputGroupSoap> soapModels = new ArrayList<ModelInputGroupSoap>(models.size());

        for (ModelInputGroup model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelInputGroupSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _modelInputGroupPK;
    }

    public void setPrimaryKey(Long pk) {
        setModelInputGroupPK(pk);
    }

    public Long getModelInputGroupPK() {
        return _modelInputGroupPK;
    }

    public void setModelInputGroupPK(Long modelInputGroupPK) {
        _modelInputGroupPK = modelInputGroupPK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Long getNameAndDescriptionMetaDataId() {
        return _nameAndDescriptionMetaDataId;
    }

    public void setNameAndDescriptionMetaDataId(
        Long nameAndDescriptionMetaDataId) {
        _nameAndDescriptionMetaDataId = nameAndDescriptionMetaDataId;
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

    public Integer getDisplayItemOrder() {
        return _displayItemOrder;
    }

    public void setDisplayItemOrder(Integer displayItemOrder) {
        _displayItemOrder = displayItemOrder;
    }

    public String getGroupType() {
        return _groupType;
    }

    public void setGroupType(String groupType) {
        _groupType = groupType;
    }

    public Long getParentGroupPK() {
        return _parentGroupPK;
    }

    public void setParentGroupPK(Long parentGroupPK) {
        _parentGroupPK = parentGroupPK;
    }
}
