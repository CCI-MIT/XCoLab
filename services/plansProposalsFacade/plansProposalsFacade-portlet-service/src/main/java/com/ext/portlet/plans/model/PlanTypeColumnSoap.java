package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanTypeColumnServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanTypeColumnServiceSoap
 * @generated
 */
public class PlanTypeColumnSoap implements Serializable {
    private Long _planTypeColumnId;
    private Long _planTypeId;
    private Integer _weight;
    private String _columnName;
    private Boolean _visibleByDefault;

    public PlanTypeColumnSoap() {
    }

    public static PlanTypeColumnSoap toSoapModel(PlanTypeColumn model) {
        PlanTypeColumnSoap soapModel = new PlanTypeColumnSoap();

        soapModel.setPlanTypeColumnId(model.getPlanTypeColumnId());
        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setWeight(model.getWeight());
        soapModel.setColumnName(model.getColumnName());
        soapModel.setVisibleByDefault(model.getVisibleByDefault());

        return soapModel;
    }

    public static PlanTypeColumnSoap[] toSoapModels(PlanTypeColumn[] models) {
        PlanTypeColumnSoap[] soapModels = new PlanTypeColumnSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanTypeColumnSoap[][] toSoapModels(PlanTypeColumn[][] models) {
        PlanTypeColumnSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanTypeColumnSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanTypeColumnSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanTypeColumnSoap[] toSoapModels(List<PlanTypeColumn> models) {
        List<PlanTypeColumnSoap> soapModels = new ArrayList<PlanTypeColumnSoap>(models.size());

        for (PlanTypeColumn model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanTypeColumnSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _planTypeColumnId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanTypeColumnId(pk);
    }

    public Long getPlanTypeColumnId() {
        return _planTypeColumnId;
    }

    public void setPlanTypeColumnId(Long planTypeColumnId) {
        _planTypeColumnId = planTypeColumnId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public Integer getWeight() {
        return _weight;
    }

    public void setWeight(Integer weight) {
        _weight = weight;
    }

    public String getColumnName() {
        return _columnName;
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;
    }

    public Boolean getVisibleByDefault() {
        return _visibleByDefault;
    }

    public void setVisibleByDefault(Boolean visibleByDefault) {
        _visibleByDefault = visibleByDefault;
    }
}
