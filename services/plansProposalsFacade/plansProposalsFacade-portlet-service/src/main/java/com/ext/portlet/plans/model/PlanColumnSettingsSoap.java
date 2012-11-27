package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlanColumnSettingsServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlanColumnSettingsServiceSoap
 * @generated
 */
public class PlanColumnSettingsSoap implements Serializable {
    private Long _planColumnSettingsId;
    private String _columnName;
    private Long _planUserSettingsId;
    private Boolean _visible;

    public PlanColumnSettingsSoap() {
    }

    public static PlanColumnSettingsSoap toSoapModel(PlanColumnSettings model) {
        PlanColumnSettingsSoap soapModel = new PlanColumnSettingsSoap();

        soapModel.setPlanColumnSettingsId(model.getPlanColumnSettingsId());
        soapModel.setColumnName(model.getColumnName());
        soapModel.setPlanUserSettingsId(model.getPlanUserSettingsId());
        soapModel.setVisible(model.getVisible());

        return soapModel;
    }

    public static PlanColumnSettingsSoap[] toSoapModels(
        PlanColumnSettings[] models) {
        PlanColumnSettingsSoap[] soapModels = new PlanColumnSettingsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanColumnSettingsSoap[][] toSoapModels(
        PlanColumnSettings[][] models) {
        PlanColumnSettingsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanColumnSettingsSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanColumnSettingsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanColumnSettingsSoap[] toSoapModels(
        List<PlanColumnSettings> models) {
        List<PlanColumnSettingsSoap> soapModels = new ArrayList<PlanColumnSettingsSoap>(models.size());

        for (PlanColumnSettings model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanColumnSettingsSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _planColumnSettingsId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanColumnSettingsId(pk);
    }

    public Long getPlanColumnSettingsId() {
        return _planColumnSettingsId;
    }

    public void setPlanColumnSettingsId(Long planColumnSettingsId) {
        _planColumnSettingsId = planColumnSettingsId;
    }

    public String getColumnName() {
        return _columnName;
    }

    public void setColumnName(String columnName) {
        _columnName = columnName;
    }

    public Long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    public void setPlanUserSettingsId(Long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;
    }

    public Boolean getVisible() {
        return _visible;
    }

    public void setVisible(Boolean visible) {
        _visible = visible;
    }
}
