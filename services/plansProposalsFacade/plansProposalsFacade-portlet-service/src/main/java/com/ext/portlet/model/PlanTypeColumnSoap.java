package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlanTypeColumnServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PlanTypeColumnServiceSoap
 * @generated
 */
public class PlanTypeColumnSoap implements Serializable {
    private long _planTypeColumnId;
    private long _planTypeId;
    private int _weight;
    private String _columnName;
    private boolean _visibleByDefault;

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

    public long getPrimaryKey() {
        return _planTypeColumnId;
    }

    public void setPrimaryKey(long pk) {
        setPlanTypeColumnId(pk);
    }

    public long getPlanTypeColumnId() {
        return _planTypeColumnId;
    }

    public void setPlanTypeColumnId(long planTypeColumnId) {
        _planTypeColumnId = planTypeColumnId;
    }

    public long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;
    }

    public int getWeight() {
        return _weight;
    }

    public void setWeight(int weight) {
        _weight = weight;
    }

    public String getColumnName() {
        return _columnName;
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;
    }

    public boolean getVisibleByDefault() {
        return _visibleByDefault;
    }

    public boolean isVisibleByDefault() {
        return _visibleByDefault;
    }

    public void setVisibleByDefault(boolean visibleByDefault) {
        _visibleByDefault = visibleByDefault;
    }
}
