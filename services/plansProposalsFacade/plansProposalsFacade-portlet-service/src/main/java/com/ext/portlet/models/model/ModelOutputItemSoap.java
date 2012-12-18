package com.ext.portlet.models.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.models.service.http.ModelOutputItemServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.models.service.http.ModelOutputItemServiceSoap
 * @generated
 */
public class ModelOutputItemSoap implements Serializable {
    private long _modelOutputItemModifierPK;
    private long _modelId;
    private long _modelOutputItemId;
    private int _modelOutputItemOrder;
    private String _modelItemRangePolicy;
    private String _modelItemRangeMessage;
    private String _modelItemErrorPolicy;
    private String _modelItemErrorMessage;
    private String _modelItemLabelFormat;
    private boolean _modelItemIsVisible;
    private String _itemType;
    private long _relatedOutputItem;

    public ModelOutputItemSoap() {
    }

    public static ModelOutputItemSoap toSoapModel(ModelOutputItem model) {
        ModelOutputItemSoap soapModel = new ModelOutputItemSoap();

        soapModel.setModelOutputItemModifierPK(model.getModelOutputItemModifierPK());
        soapModel.setModelId(model.getModelId());
        soapModel.setModelOutputItemId(model.getModelOutputItemId());
        soapModel.setModelOutputItemOrder(model.getModelOutputItemOrder());
        soapModel.setModelItemRangePolicy(model.getModelItemRangePolicy());
        soapModel.setModelItemRangeMessage(model.getModelItemRangeMessage());
        soapModel.setModelItemErrorPolicy(model.getModelItemErrorPolicy());
        soapModel.setModelItemErrorMessage(model.getModelItemErrorMessage());
        soapModel.setModelItemLabelFormat(model.getModelItemLabelFormat());
        soapModel.setModelItemIsVisible(model.getModelItemIsVisible());
        soapModel.setItemType(model.getItemType());
        soapModel.setRelatedOutputItem(model.getRelatedOutputItem());

        return soapModel;
    }

    public static ModelOutputItemSoap[] toSoapModels(ModelOutputItem[] models) {
        ModelOutputItemSoap[] soapModels = new ModelOutputItemSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelOutputItemSoap[][] toSoapModels(
        ModelOutputItem[][] models) {
        ModelOutputItemSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelOutputItemSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelOutputItemSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelOutputItemSoap[] toSoapModels(
        List<ModelOutputItem> models) {
        List<ModelOutputItemSoap> soapModels = new ArrayList<ModelOutputItemSoap>(models.size());

        for (ModelOutputItem model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelOutputItemSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _modelOutputItemModifierPK;
    }

    public void setPrimaryKey(long pk) {
        setModelOutputItemModifierPK(pk);
    }

    public long getModelOutputItemModifierPK() {
        return _modelOutputItemModifierPK;
    }

    public void setModelOutputItemModifierPK(long modelOutputItemModifierPK) {
        _modelOutputItemModifierPK = modelOutputItemModifierPK;
    }

    public long getModelId() {
        return _modelId;
    }

    public void setModelId(long modelId) {
        _modelId = modelId;
    }

    public long getModelOutputItemId() {
        return _modelOutputItemId;
    }

    public void setModelOutputItemId(long modelOutputItemId) {
        _modelOutputItemId = modelOutputItemId;
    }

    public int getModelOutputItemOrder() {
        return _modelOutputItemOrder;
    }

    public void setModelOutputItemOrder(int modelOutputItemOrder) {
        _modelOutputItemOrder = modelOutputItemOrder;
    }

    public String getModelItemRangePolicy() {
        return _modelItemRangePolicy;
    }

    public void setModelItemRangePolicy(String modelItemRangePolicy) {
        _modelItemRangePolicy = modelItemRangePolicy;
    }

    public String getModelItemRangeMessage() {
        return _modelItemRangeMessage;
    }

    public void setModelItemRangeMessage(String modelItemRangeMessage) {
        _modelItemRangeMessage = modelItemRangeMessage;
    }

    public String getModelItemErrorPolicy() {
        return _modelItemErrorPolicy;
    }

    public void setModelItemErrorPolicy(String modelItemErrorPolicy) {
        _modelItemErrorPolicy = modelItemErrorPolicy;
    }

    public String getModelItemErrorMessage() {
        return _modelItemErrorMessage;
    }

    public void setModelItemErrorMessage(String modelItemErrorMessage) {
        _modelItemErrorMessage = modelItemErrorMessage;
    }

    public String getModelItemLabelFormat() {
        return _modelItemLabelFormat;
    }

    public void setModelItemLabelFormat(String modelItemLabelFormat) {
        _modelItemLabelFormat = modelItemLabelFormat;
    }

    public boolean getModelItemIsVisible() {
        return _modelItemIsVisible;
    }

    public boolean isModelItemIsVisible() {
        return _modelItemIsVisible;
    }

    public void setModelItemIsVisible(boolean modelItemIsVisible) {
        _modelItemIsVisible = modelItemIsVisible;
    }

    public String getItemType() {
        return _itemType;
    }

    public void setItemType(String itemType) {
        _itemType = itemType;
    }

    public long getRelatedOutputItem() {
        return _relatedOutputItem;
    }

    public void setRelatedOutputItem(long relatedOutputItem) {
        _relatedOutputItem = relatedOutputItem;
    }
}
