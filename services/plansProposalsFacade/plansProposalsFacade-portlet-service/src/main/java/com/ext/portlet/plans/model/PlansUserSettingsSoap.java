package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlansUserSettingsServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlansUserSettingsServiceSoap
 * @generated
 */
public class PlansUserSettingsSoap implements Serializable {
    private Long _planUserSettingsId;
    private Long _userId;
    private Long _planTypeId;
    private String _sortColumn;
    private String _sortDirection;
    private Boolean _filterEnabled;
    private Boolean _filterPositionsAll;

    public PlansUserSettingsSoap() {
    }

    public static PlansUserSettingsSoap toSoapModel(PlansUserSettings model) {
        PlansUserSettingsSoap soapModel = new PlansUserSettingsSoap();

        soapModel.setPlanUserSettingsId(model.getPlanUserSettingsId());
        soapModel.setUserId(model.getUserId());
        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setSortColumn(model.getSortColumn());
        soapModel.setSortDirection(model.getSortDirection());
        soapModel.setFilterEnabled(model.getFilterEnabled());
        soapModel.setFilterPositionsAll(model.getFilterPositionsAll());

        return soapModel;
    }

    public static PlansUserSettingsSoap[] toSoapModels(
        PlansUserSettings[] models) {
        PlansUserSettingsSoap[] soapModels = new PlansUserSettingsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlansUserSettingsSoap[][] toSoapModels(
        PlansUserSettings[][] models) {
        PlansUserSettingsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlansUserSettingsSoap[models.length][models[0].length];
        } else {
            soapModels = new PlansUserSettingsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlansUserSettingsSoap[] toSoapModels(
        List<PlansUserSettings> models) {
        List<PlansUserSettingsSoap> soapModels = new ArrayList<PlansUserSettingsSoap>(models.size());

        for (PlansUserSettings model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlansUserSettingsSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _planUserSettingsId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanUserSettingsId(pk);
    }

    public Long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    public void setPlanUserSettingsId(Long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public String getSortColumn() {
        return _sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        _sortColumn = sortColumn;
    }

    public String getSortDirection() {
        return _sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        _sortDirection = sortDirection;
    }

    public Boolean getFilterEnabled() {
        return _filterEnabled;
    }

    public void setFilterEnabled(Boolean filterEnabled) {
        _filterEnabled = filterEnabled;
    }

    public Boolean getFilterPositionsAll() {
        return _filterPositionsAll;
    }

    public void setFilterPositionsAll(Boolean filterPositionsAll) {
        _filterPositionsAll = filterPositionsAll;
    }
}
