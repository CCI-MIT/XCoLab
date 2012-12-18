package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.persistence.PlansFilterPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.plans.service.http.PlansFilterServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.ext.portlet.plans.service.http.PlansFilterServiceSoap
 * @generated
 */
public class PlansFilterSoap implements Serializable {
    private long _userId;
    private long _planTypeId;
    private String _name;
    private String _creator;
    private String _description;
    private Double _CO2From;
    private Double _CO2To;
    private Double _votesFrom;
    private Double _votesTo;
    private Double _damageFrom;
    private Double _damageTo;
    private Double _mitigationFrom;
    private Double _mitigationTo;
    private Date _dateFrom;
    private Date _dateTo;
    private boolean _filterPositionsAll;
    private boolean _enabled;

    public PlansFilterSoap() {
    }

    public static PlansFilterSoap toSoapModel(PlansFilter model) {
        PlansFilterSoap soapModel = new PlansFilterSoap();

        soapModel.setUserId(model.getUserId());
        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setName(model.getName());
        soapModel.setCreator(model.getCreator());
        soapModel.setDescription(model.getDescription());
        soapModel.setCO2From(model.getCO2From());
        soapModel.setCO2To(model.getCO2To());
        soapModel.setVotesFrom(model.getVotesFrom());
        soapModel.setVotesTo(model.getVotesTo());
        soapModel.setDamageFrom(model.getDamageFrom());
        soapModel.setDamageTo(model.getDamageTo());
        soapModel.setMitigationFrom(model.getMitigationFrom());
        soapModel.setMitigationTo(model.getMitigationTo());
        soapModel.setDateFrom(model.getDateFrom());
        soapModel.setDateTo(model.getDateTo());
        soapModel.setFilterPositionsAll(model.getFilterPositionsAll());
        soapModel.setEnabled(model.getEnabled());

        return soapModel;
    }

    public static PlansFilterSoap[] toSoapModels(PlansFilter[] models) {
        PlansFilterSoap[] soapModels = new PlansFilterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlansFilterSoap[][] toSoapModels(PlansFilter[][] models) {
        PlansFilterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlansFilterSoap[models.length][models[0].length];
        } else {
            soapModels = new PlansFilterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlansFilterSoap[] toSoapModels(List<PlansFilter> models) {
        List<PlansFilterSoap> soapModels = new ArrayList<PlansFilterSoap>(models.size());

        for (PlansFilter model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlansFilterSoap[soapModels.size()]);
    }

    public PlansFilterPK getPrimaryKey() {
        return new PlansFilterPK(_userId, _planTypeId);
    }

    public void setPrimaryKey(PlansFilterPK pk) {
        setUserId(pk.userId);
        setPlanTypeId(pk.planTypeId);
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

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getCreator() {
        return _creator;
    }

    public void setCreator(String creator) {
        _creator = creator;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public Double getCO2From() {
        return _CO2From;
    }

    public void setCO2From(Double CO2From) {
        _CO2From = CO2From;
    }

    public Double getCO2To() {
        return _CO2To;
    }

    public void setCO2To(Double CO2To) {
        _CO2To = CO2To;
    }

    public Double getVotesFrom() {
        return _votesFrom;
    }

    public void setVotesFrom(Double votesFrom) {
        _votesFrom = votesFrom;
    }

    public Double getVotesTo() {
        return _votesTo;
    }

    public void setVotesTo(Double votesTo) {
        _votesTo = votesTo;
    }

    public Double getDamageFrom() {
        return _damageFrom;
    }

    public void setDamageFrom(Double damageFrom) {
        _damageFrom = damageFrom;
    }

    public Double getDamageTo() {
        return _damageTo;
    }

    public void setDamageTo(Double damageTo) {
        _damageTo = damageTo;
    }

    public Double getMitigationFrom() {
        return _mitigationFrom;
    }

    public void setMitigationFrom(Double mitigationFrom) {
        _mitigationFrom = mitigationFrom;
    }

    public Double getMitigationTo() {
        return _mitigationTo;
    }

    public void setMitigationTo(Double mitigationTo) {
        _mitigationTo = mitigationTo;
    }

    public Date getDateFrom() {
        return _dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        _dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return _dateTo;
    }

    public void setDateTo(Date dateTo) {
        _dateTo = dateTo;
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

    public boolean getEnabled() {
        return _enabled;
    }

    public boolean isEnabled() {
        return _enabled;
    }

    public void setEnabled(boolean enabled) {
        _enabled = enabled;
    }
}
