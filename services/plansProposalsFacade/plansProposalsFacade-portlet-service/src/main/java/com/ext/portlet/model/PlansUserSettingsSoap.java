package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.PlansUserSettingsServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.PlansUserSettingsServiceSoap
 * @generated
 */
public class PlansUserSettingsSoap implements Serializable {
    private long _planUserSettingsId;
    private long _userId;
    private long _planTypeId;
    private String _sortColumn;
    private String _sortDirection;
    private boolean _filterEnabled;
    private boolean _filterPositionsAll;

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

    public long getPrimaryKey() {
        return _planUserSettingsId;
    }

    public void setPrimaryKey(long pk) {
        setPlanUserSettingsId(pk);
    }

    public long getPlanUserSettingsId() {
        return _planUserSettingsId;
    }

    public void setPlanUserSettingsId(long planUserSettingsId) {
        _planUserSettingsId = planUserSettingsId;
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
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

    public boolean getFilterEnabled() {
        return _filterEnabled;
    }

    public boolean isFilterEnabled() {
        return _filterEnabled;
    }

    public void setFilterEnabled(boolean filterEnabled) {
        _filterEnabled = filterEnabled;
    }

    public boolean getFilterPositionsAll() {
        return _filterPositionsAll;
    }

    public boolean isFilterPositionsAll() {
        return _filterPositionsAll;
    }

    public void setFilterPositionsAll(boolean filterPositionsAll) {
        _filterPositionsAll = filterPositionsAll;
    }
}
