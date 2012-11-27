package com.ext.portlet.models.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.models.service.http.ModelOutputChartOrderServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.models.service.http.ModelOutputChartOrderServiceSoap
 * @generated
 */
public class ModelOutputChartOrderSoap implements Serializable {
    private Long _modelOutputChartOrderPK;
    private Long _modelId;
    private String _modelOutputLabel;
    private Integer _modelOutputChartOrder;
    private String _modelIndexRangePolicy;
    private String _modelIndexRangeMessage;
    private String _modelIndexErrorPolicy;
    private String _modelIndexErrorMessage;
    private Boolean _modelChartIsVisible;

    public ModelOutputChartOrderSoap() {
    }

    public static ModelOutputChartOrderSoap toSoapModel(
        ModelOutputChartOrder model) {
        ModelOutputChartOrderSoap soapModel = new ModelOutputChartOrderSoap();

        soapModel.setModelOutputChartOrderPK(model.getModelOutputChartOrderPK());
        soapModel.setModelId(model.getModelId());
        soapModel.setModelOutputLabel(model.getModelOutputLabel());
        soapModel.setModelOutputChartOrder(model.getModelOutputChartOrder());
        soapModel.setModelIndexRangePolicy(model.getModelIndexRangePolicy());
        soapModel.setModelIndexRangeMessage(model.getModelIndexRangeMessage());
        soapModel.setModelIndexErrorPolicy(model.getModelIndexErrorPolicy());
        soapModel.setModelIndexErrorMessage(model.getModelIndexErrorMessage());
        soapModel.setModelChartIsVisible(model.getModelChartIsVisible());

        return soapModel;
    }

    public static ModelOutputChartOrderSoap[] toSoapModels(
        ModelOutputChartOrder[] models) {
        ModelOutputChartOrderSoap[] soapModels = new ModelOutputChartOrderSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ModelOutputChartOrderSoap[][] toSoapModels(
        ModelOutputChartOrder[][] models) {
        ModelOutputChartOrderSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ModelOutputChartOrderSoap[models.length][models[0].length];
        } else {
            soapModels = new ModelOutputChartOrderSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ModelOutputChartOrderSoap[] toSoapModels(
        List<ModelOutputChartOrder> models) {
        List<ModelOutputChartOrderSoap> soapModels = new ArrayList<ModelOutputChartOrderSoap>(models.size());

        for (ModelOutputChartOrder model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ModelOutputChartOrderSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _modelOutputChartOrderPK;
    }

    public void setPrimaryKey(Long pk) {
        setModelOutputChartOrderPK(pk);
    }

    public Long getModelOutputChartOrderPK() {
        return _modelOutputChartOrderPK;
    }

    public void setModelOutputChartOrderPK(Long modelOutputChartOrderPK) {
        _modelOutputChartOrderPK = modelOutputChartOrderPK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public String getModelOutputLabel() {
        return _modelOutputLabel;
    }

    public void setModelOutputLabel(String modelOutputLabel) {
        _modelOutputLabel = modelOutputLabel;
    }

    public Integer getModelOutputChartOrder() {
        return _modelOutputChartOrder;
    }

    public void setModelOutputChartOrder(Integer modelOutputChartOrder) {
        _modelOutputChartOrder = modelOutputChartOrder;
    }

    public String getModelIndexRangePolicy() {
        return _modelIndexRangePolicy;
    }

    public void setModelIndexRangePolicy(String modelIndexRangePolicy) {
        _modelIndexRangePolicy = modelIndexRangePolicy;
    }

    public String getModelIndexRangeMessage() {
        return _modelIndexRangeMessage;
    }

    public void setModelIndexRangeMessage(String modelIndexRangeMessage) {
        _modelIndexRangeMessage = modelIndexRangeMessage;
    }

    public String getModelIndexErrorPolicy() {
        return _modelIndexErrorPolicy;
    }

    public void setModelIndexErrorPolicy(String modelIndexErrorPolicy) {
        _modelIndexErrorPolicy = modelIndexErrorPolicy;
    }

    public String getModelIndexErrorMessage() {
        return _modelIndexErrorMessage;
    }

    public void setModelIndexErrorMessage(String modelIndexErrorMessage) {
        _modelIndexErrorMessage = modelIndexErrorMessage;
    }

    public Boolean getModelChartIsVisible() {
        return _modelChartIsVisible;
    }

    public void setModelChartIsVisible(Boolean modelChartIsVisible) {
        _modelChartIsVisible = modelChartIsVisible;
    }
}
