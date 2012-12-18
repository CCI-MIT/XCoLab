package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlansFilterLocalServiceUtil;
import com.ext.portlet.plans.service.persistence.PlansFilterPK;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlansFilterClp extends BaseModelImpl<PlansFilter>
    implements PlansFilter {
    private long _userId;
    private String _userUuid;
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

    public PlansFilterClp() {
    }

    public Class<?> getModelClass() {
        return PlansFilter.class;
    }

    public String getModelClassName() {
        return PlansFilter.class.getName();
    }

    public PlansFilterPK getPrimaryKey() {
        return new PlansFilterPK(_userId, _planTypeId);
    }

    public void setPrimaryKey(PlansFilterPK primaryKey) {
        setUserId(primaryKey.userId);
        setPlanTypeId(primaryKey.planTypeId);
    }

    public Serializable getPrimaryKeyObj() {
        return new PlansFilterPK(_userId, _planTypeId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((PlansFilterPK) primaryKeyObj);
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
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

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlansFilterLocalServiceUtil.addPlansFilter(this);
        } else {
            PlansFilterLocalServiceUtil.updatePlansFilter(this);
        }
    }

    @Override
    public PlansFilter toEscapedModel() {
        return (PlansFilter) Proxy.newProxyInstance(PlansFilter.class.getClassLoader(),
            new Class[] { PlansFilter.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlansFilterClp clone = new PlansFilterClp();

        clone.setUserId(getUserId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setName(getName());
        clone.setCreator(getCreator());
        clone.setDescription(getDescription());
        clone.setCO2From(getCO2From());
        clone.setCO2To(getCO2To());
        clone.setVotesFrom(getVotesFrom());
        clone.setVotesTo(getVotesTo());
        clone.setDamageFrom(getDamageFrom());
        clone.setDamageTo(getDamageTo());
        clone.setMitigationFrom(getMitigationFrom());
        clone.setMitigationTo(getMitigationTo());
        clone.setDateFrom(getDateFrom());
        clone.setDateTo(getDateTo());
        clone.setFilterPositionsAll(getFilterPositionsAll());
        clone.setEnabled(getEnabled());

        return clone;
    }

    public int compareTo(PlansFilter plansFilter) {
        PlansFilterPK primaryKey = plansFilter.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlansFilterClp plansFilter = null;

        try {
            plansFilter = (PlansFilterClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        PlansFilterPK primaryKey = plansFilter.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(35);

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", creator=");
        sb.append(getCreator());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", CO2From=");
        sb.append(getCO2From());
        sb.append(", CO2To=");
        sb.append(getCO2To());
        sb.append(", votesFrom=");
        sb.append(getVotesFrom());
        sb.append(", votesTo=");
        sb.append(getVotesTo());
        sb.append(", damageFrom=");
        sb.append(getDamageFrom());
        sb.append(", damageTo=");
        sb.append(getDamageTo());
        sb.append(", mitigationFrom=");
        sb.append(getMitigationFrom());
        sb.append(", mitigationTo=");
        sb.append(getMitigationTo());
        sb.append(", dateFrom=");
        sb.append(getDateFrom());
        sb.append(", dateTo=");
        sb.append(getDateTo());
        sb.append(", filterPositionsAll=");
        sb.append(getFilterPositionsAll());
        sb.append(", enabled=");
        sb.append(getEnabled());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(55);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlansFilter");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>creator</column-name><column-value><![CDATA[");
        sb.append(getCreator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>CO2From</column-name><column-value><![CDATA[");
        sb.append(getCO2From());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>CO2To</column-name><column-value><![CDATA[");
        sb.append(getCO2To());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votesFrom</column-name><column-value><![CDATA[");
        sb.append(getVotesFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votesTo</column-name><column-value><![CDATA[");
        sb.append(getVotesTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>damageFrom</column-name><column-value><![CDATA[");
        sb.append(getDamageFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>damageTo</column-name><column-value><![CDATA[");
        sb.append(getDamageTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mitigationFrom</column-name><column-value><![CDATA[");
        sb.append(getMitigationFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mitigationTo</column-name><column-value><![CDATA[");
        sb.append(getMitigationTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dateFrom</column-name><column-value><![CDATA[");
        sb.append(getDateFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dateTo</column-name><column-value><![CDATA[");
        sb.append(getDateTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>filterPositionsAll</column-name><column-value><![CDATA[");
        sb.append(getFilterPositionsAll());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>enabled</column-name><column-value><![CDATA[");
        sb.append(getEnabled());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
