package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlansFilter}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilter
 * @generated
 */
public class PlansFilterWrapper implements PlansFilter,
    ModelWrapper<PlansFilter> {
    private PlansFilter _plansFilter;

    public PlansFilterWrapper(PlansFilter plansFilter) {
        _plansFilter = plansFilter;
    }

    @Override
    public Class<?> getModelClass() {
        return PlansFilter.class;
    }

    @Override
    public String getModelClassName() {
        return PlansFilter.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("planTypeId", getPlanTypeId());
        attributes.put("name", getName());
        attributes.put("creator", getCreator());
        attributes.put("description", getDescription());
        attributes.put("CO2From", getCO2From());
        attributes.put("CO2To", getCO2To());
        attributes.put("votesFrom", getVotesFrom());
        attributes.put("votesTo", getVotesTo());
        attributes.put("damageFrom", getDamageFrom());
        attributes.put("damageTo", getDamageTo());
        attributes.put("mitigationFrom", getMitigationFrom());
        attributes.put("mitigationTo", getMitigationTo());
        attributes.put("dateFrom", getDateFrom());
        attributes.put("dateTo", getDateTo());
        attributes.put("filterPositionsAll", getFilterPositionsAll());
        attributes.put("enabled", getEnabled());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long planTypeId = (Long) attributes.get("planTypeId");

        if (planTypeId != null) {
            setPlanTypeId(planTypeId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String creator = (String) attributes.get("creator");

        if (creator != null) {
            setCreator(creator);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Double CO2From = (Double) attributes.get("CO2From");

        if (CO2From != null) {
            setCO2From(CO2From);
        }

        Double CO2To = (Double) attributes.get("CO2To");

        if (CO2To != null) {
            setCO2To(CO2To);
        }

        Double votesFrom = (Double) attributes.get("votesFrom");

        if (votesFrom != null) {
            setVotesFrom(votesFrom);
        }

        Double votesTo = (Double) attributes.get("votesTo");

        if (votesTo != null) {
            setVotesTo(votesTo);
        }

        Double damageFrom = (Double) attributes.get("damageFrom");

        if (damageFrom != null) {
            setDamageFrom(damageFrom);
        }

        Double damageTo = (Double) attributes.get("damageTo");

        if (damageTo != null) {
            setDamageTo(damageTo);
        }

        Double mitigationFrom = (Double) attributes.get("mitigationFrom");

        if (mitigationFrom != null) {
            setMitigationFrom(mitigationFrom);
        }

        Double mitigationTo = (Double) attributes.get("mitigationTo");

        if (mitigationTo != null) {
            setMitigationTo(mitigationTo);
        }

        Date dateFrom = (Date) attributes.get("dateFrom");

        if (dateFrom != null) {
            setDateFrom(dateFrom);
        }

        Date dateTo = (Date) attributes.get("dateTo");

        if (dateTo != null) {
            setDateTo(dateTo);
        }

        Boolean filterPositionsAll = (Boolean) attributes.get(
                "filterPositionsAll");

        if (filterPositionsAll != null) {
            setFilterPositionsAll(filterPositionsAll);
        }

        Boolean enabled = (Boolean) attributes.get("enabled");

        if (enabled != null) {
            setEnabled(enabled);
        }
    }

    /**
    * Returns the primary key of this plans filter.
    *
    * @return the primary key of this plans filter
    */
    @Override
    public com.ext.portlet.service.persistence.PlansFilterPK getPrimaryKey() {
        return _plansFilter.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plans filter.
    *
    * @param primaryKey the primary key of this plans filter
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlansFilterPK primaryKey) {
        _plansFilter.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this plans filter.
    *
    * @return the user ID of this plans filter
    */
    @Override
    public long getUserId() {
        return _plansFilter.getUserId();
    }

    /**
    * Sets the user ID of this plans filter.
    *
    * @param userId the user ID of this plans filter
    */
    @Override
    public void setUserId(long userId) {
        _plansFilter.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plans filter.
    *
    * @return the user uuid of this plans filter
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilter.getUserUuid();
    }

    /**
    * Sets the user uuid of this plans filter.
    *
    * @param userUuid the user uuid of this plans filter
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _plansFilter.setUserUuid(userUuid);
    }

    /**
    * Returns the plan type ID of this plans filter.
    *
    * @return the plan type ID of this plans filter
    */
    @Override
    public long getPlanTypeId() {
        return _plansFilter.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plans filter.
    *
    * @param planTypeId the plan type ID of this plans filter
    */
    @Override
    public void setPlanTypeId(long planTypeId) {
        _plansFilter.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the name of this plans filter.
    *
    * @return the name of this plans filter
    */
    @Override
    public java.lang.String getName() {
        return _plansFilter.getName();
    }

    /**
    * Sets the name of this plans filter.
    *
    * @param name the name of this plans filter
    */
    @Override
    public void setName(java.lang.String name) {
        _plansFilter.setName(name);
    }

    /**
    * Returns the creator of this plans filter.
    *
    * @return the creator of this plans filter
    */
    @Override
    public java.lang.String getCreator() {
        return _plansFilter.getCreator();
    }

    /**
    * Sets the creator of this plans filter.
    *
    * @param creator the creator of this plans filter
    */
    @Override
    public void setCreator(java.lang.String creator) {
        _plansFilter.setCreator(creator);
    }

    /**
    * Returns the description of this plans filter.
    *
    * @return the description of this plans filter
    */
    @Override
    public java.lang.String getDescription() {
        return _plansFilter.getDescription();
    }

    /**
    * Sets the description of this plans filter.
    *
    * @param description the description of this plans filter
    */
    @Override
    public void setDescription(java.lang.String description) {
        _plansFilter.setDescription(description);
    }

    /**
    * Returns the c o2 from of this plans filter.
    *
    * @return the c o2 from of this plans filter
    */
    @Override
    public java.lang.Double getCO2From() {
        return _plansFilter.getCO2From();
    }

    /**
    * Sets the c o2 from of this plans filter.
    *
    * @param CO2From the c o2 from of this plans filter
    */
    @Override
    public void setCO2From(java.lang.Double CO2From) {
        _plansFilter.setCO2From(CO2From);
    }

    /**
    * Returns the c o2 to of this plans filter.
    *
    * @return the c o2 to of this plans filter
    */
    @Override
    public java.lang.Double getCO2To() {
        return _plansFilter.getCO2To();
    }

    /**
    * Sets the c o2 to of this plans filter.
    *
    * @param CO2To the c o2 to of this plans filter
    */
    @Override
    public void setCO2To(java.lang.Double CO2To) {
        _plansFilter.setCO2To(CO2To);
    }

    /**
    * Returns the votes from of this plans filter.
    *
    * @return the votes from of this plans filter
    */
    @Override
    public java.lang.Double getVotesFrom() {
        return _plansFilter.getVotesFrom();
    }

    /**
    * Sets the votes from of this plans filter.
    *
    * @param votesFrom the votes from of this plans filter
    */
    @Override
    public void setVotesFrom(java.lang.Double votesFrom) {
        _plansFilter.setVotesFrom(votesFrom);
    }

    /**
    * Returns the votes to of this plans filter.
    *
    * @return the votes to of this plans filter
    */
    @Override
    public java.lang.Double getVotesTo() {
        return _plansFilter.getVotesTo();
    }

    /**
    * Sets the votes to of this plans filter.
    *
    * @param votesTo the votes to of this plans filter
    */
    @Override
    public void setVotesTo(java.lang.Double votesTo) {
        _plansFilter.setVotesTo(votesTo);
    }

    /**
    * Returns the damage from of this plans filter.
    *
    * @return the damage from of this plans filter
    */
    @Override
    public java.lang.Double getDamageFrom() {
        return _plansFilter.getDamageFrom();
    }

    /**
    * Sets the damage from of this plans filter.
    *
    * @param damageFrom the damage from of this plans filter
    */
    @Override
    public void setDamageFrom(java.lang.Double damageFrom) {
        _plansFilter.setDamageFrom(damageFrom);
    }

    /**
    * Returns the damage to of this plans filter.
    *
    * @return the damage to of this plans filter
    */
    @Override
    public java.lang.Double getDamageTo() {
        return _plansFilter.getDamageTo();
    }

    /**
    * Sets the damage to of this plans filter.
    *
    * @param damageTo the damage to of this plans filter
    */
    @Override
    public void setDamageTo(java.lang.Double damageTo) {
        _plansFilter.setDamageTo(damageTo);
    }

    /**
    * Returns the mitigation from of this plans filter.
    *
    * @return the mitigation from of this plans filter
    */
    @Override
    public java.lang.Double getMitigationFrom() {
        return _plansFilter.getMitigationFrom();
    }

    /**
    * Sets the mitigation from of this plans filter.
    *
    * @param mitigationFrom the mitigation from of this plans filter
    */
    @Override
    public void setMitigationFrom(java.lang.Double mitigationFrom) {
        _plansFilter.setMitigationFrom(mitigationFrom);
    }

    /**
    * Returns the mitigation to of this plans filter.
    *
    * @return the mitigation to of this plans filter
    */
    @Override
    public java.lang.Double getMitigationTo() {
        return _plansFilter.getMitigationTo();
    }

    /**
    * Sets the mitigation to of this plans filter.
    *
    * @param mitigationTo the mitigation to of this plans filter
    */
    @Override
    public void setMitigationTo(java.lang.Double mitigationTo) {
        _plansFilter.setMitigationTo(mitigationTo);
    }

    /**
    * Returns the date from of this plans filter.
    *
    * @return the date from of this plans filter
    */
    @Override
    public java.util.Date getDateFrom() {
        return _plansFilter.getDateFrom();
    }

    /**
    * Sets the date from of this plans filter.
    *
    * @param dateFrom the date from of this plans filter
    */
    @Override
    public void setDateFrom(java.util.Date dateFrom) {
        _plansFilter.setDateFrom(dateFrom);
    }

    /**
    * Returns the date to of this plans filter.
    *
    * @return the date to of this plans filter
    */
    @Override
    public java.util.Date getDateTo() {
        return _plansFilter.getDateTo();
    }

    /**
    * Sets the date to of this plans filter.
    *
    * @param dateTo the date to of this plans filter
    */
    @Override
    public void setDateTo(java.util.Date dateTo) {
        _plansFilter.setDateTo(dateTo);
    }

    /**
    * Returns the filter positions all of this plans filter.
    *
    * @return the filter positions all of this plans filter
    */
    @Override
    public boolean getFilterPositionsAll() {
        return _plansFilter.getFilterPositionsAll();
    }

    /**
    * Returns <code>true</code> if this plans filter is filter positions all.
    *
    * @return <code>true</code> if this plans filter is filter positions all; <code>false</code> otherwise
    */
    @Override
    public boolean isFilterPositionsAll() {
        return _plansFilter.isFilterPositionsAll();
    }

    /**
    * Sets whether this plans filter is filter positions all.
    *
    * @param filterPositionsAll the filter positions all of this plans filter
    */
    @Override
    public void setFilterPositionsAll(boolean filterPositionsAll) {
        _plansFilter.setFilterPositionsAll(filterPositionsAll);
    }

    /**
    * Returns the enabled of this plans filter.
    *
    * @return the enabled of this plans filter
    */
    @Override
    public boolean getEnabled() {
        return _plansFilter.getEnabled();
    }

    /**
    * Returns <code>true</code> if this plans filter is enabled.
    *
    * @return <code>true</code> if this plans filter is enabled; <code>false</code> otherwise
    */
    @Override
    public boolean isEnabled() {
        return _plansFilter.isEnabled();
    }

    /**
    * Sets whether this plans filter is enabled.
    *
    * @param enabled the enabled of this plans filter
    */
    @Override
    public void setEnabled(boolean enabled) {
        _plansFilter.setEnabled(enabled);
    }

    @Override
    public boolean isNew() {
        return _plansFilter.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _plansFilter.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _plansFilter.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _plansFilter.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _plansFilter.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _plansFilter.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _plansFilter.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _plansFilter.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _plansFilter.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _plansFilter.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _plansFilter.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlansFilterWrapper((PlansFilter) _plansFilter.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.PlansFilter plansFilter) {
        return _plansFilter.compareTo(plansFilter);
    }

    @Override
    public int hashCode() {
        return _plansFilter.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlansFilter> toCacheModel() {
        return _plansFilter.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlansFilter toEscapedModel() {
        return new PlansFilterWrapper(_plansFilter.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlansFilter toUnescapedModel() {
        return new PlansFilterWrapper(_plansFilter.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _plansFilter.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _plansFilter.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _plansFilter.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlansFilterWrapper)) {
            return false;
        }

        PlansFilterWrapper plansFilterWrapper = (PlansFilterWrapper) obj;

        if (Validator.equals(_plansFilter, plansFilterWrapper._plansFilter)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlansFilter getWrappedPlansFilter() {
        return _plansFilter;
    }

    @Override
    public PlansFilter getWrappedModel() {
        return _plansFilter;
    }

    @Override
    public void resetOriginalValues() {
        _plansFilter.resetOriginalValues();
    }
}
