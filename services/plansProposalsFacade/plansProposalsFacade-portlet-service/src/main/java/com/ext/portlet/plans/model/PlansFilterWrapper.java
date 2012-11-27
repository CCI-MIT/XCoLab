package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlansFilter}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlansFilter
 * @generated
 */
public class PlansFilterWrapper implements PlansFilter,
    ModelWrapper<PlansFilter> {
    private PlansFilter _plansFilter;

    public PlansFilterWrapper(PlansFilter plansFilter) {
        _plansFilter = plansFilter;
    }

    public Class<?> getModelClass() {
        return PlansFilter.class;
    }

    public String getModelClassName() {
        return PlansFilter.class.getName();
    }

    /**
    * Returns the primary key of this plans filter.
    *
    * @return the primary key of this plans filter
    */
    public com.ext.portlet.plans.service.persistence.PlansFilterPK getPrimaryKey() {
        return _plansFilter.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plans filter.
    *
    * @param primaryKey the primary key of this plans filter
    */
    public void setPrimaryKey(
        com.ext.portlet.plans.service.persistence.PlansFilterPK primaryKey) {
        _plansFilter.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this plans filter.
    *
    * @return the user ID of this plans filter
    */
    public java.lang.Long getUserId() {
        return _plansFilter.getUserId();
    }

    /**
    * Sets the user ID of this plans filter.
    *
    * @param userId the user ID of this plans filter
    */
    public void setUserId(java.lang.Long userId) {
        _plansFilter.setUserId(userId);
    }

    /**
    * Returns the plan type ID of this plans filter.
    *
    * @return the plan type ID of this plans filter
    */
    public java.lang.Long getPlanTypeId() {
        return _plansFilter.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this plans filter.
    *
    * @param planTypeId the plan type ID of this plans filter
    */
    public void setPlanTypeId(java.lang.Long planTypeId) {
        _plansFilter.setPlanTypeId(planTypeId);
    }

    /**
    * Returns the name of this plans filter.
    *
    * @return the name of this plans filter
    */
    public java.lang.String getName() {
        return _plansFilter.getName();
    }

    /**
    * Sets the name of this plans filter.
    *
    * @param name the name of this plans filter
    */
    public void setName(java.lang.String name) {
        _plansFilter.setName(name);
    }

    /**
    * Returns the creator of this plans filter.
    *
    * @return the creator of this plans filter
    */
    public java.lang.String getCreator() {
        return _plansFilter.getCreator();
    }

    /**
    * Sets the creator of this plans filter.
    *
    * @param creator the creator of this plans filter
    */
    public void setCreator(java.lang.String creator) {
        _plansFilter.setCreator(creator);
    }

    /**
    * Returns the description of this plans filter.
    *
    * @return the description of this plans filter
    */
    public java.lang.String getDescription() {
        return _plansFilter.getDescription();
    }

    /**
    * Sets the description of this plans filter.
    *
    * @param description the description of this plans filter
    */
    public void setDescription(java.lang.String description) {
        _plansFilter.setDescription(description);
    }

    /**
    * Returns the c o2 from of this plans filter.
    *
    * @return the c o2 from of this plans filter
    */
    public java.lang.Double getCO2From() {
        return _plansFilter.getCO2From();
    }

    /**
    * Sets the c o2 from of this plans filter.
    *
    * @param CO2From the c o2 from of this plans filter
    */
    public void setCO2From(java.lang.Double CO2From) {
        _plansFilter.setCO2From(CO2From);
    }

    /**
    * Returns the c o2 to of this plans filter.
    *
    * @return the c o2 to of this plans filter
    */
    public java.lang.Double getCO2To() {
        return _plansFilter.getCO2To();
    }

    /**
    * Sets the c o2 to of this plans filter.
    *
    * @param CO2To the c o2 to of this plans filter
    */
    public void setCO2To(java.lang.Double CO2To) {
        _plansFilter.setCO2To(CO2To);
    }

    /**
    * Returns the votes from of this plans filter.
    *
    * @return the votes from of this plans filter
    */
    public java.lang.Double getVotesFrom() {
        return _plansFilter.getVotesFrom();
    }

    /**
    * Sets the votes from of this plans filter.
    *
    * @param votesFrom the votes from of this plans filter
    */
    public void setVotesFrom(java.lang.Double votesFrom) {
        _plansFilter.setVotesFrom(votesFrom);
    }

    /**
    * Returns the votes to of this plans filter.
    *
    * @return the votes to of this plans filter
    */
    public java.lang.Double getVotesTo() {
        return _plansFilter.getVotesTo();
    }

    /**
    * Sets the votes to of this plans filter.
    *
    * @param votesTo the votes to of this plans filter
    */
    public void setVotesTo(java.lang.Double votesTo) {
        _plansFilter.setVotesTo(votesTo);
    }

    /**
    * Returns the damage from of this plans filter.
    *
    * @return the damage from of this plans filter
    */
    public java.lang.Double getDamageFrom() {
        return _plansFilter.getDamageFrom();
    }

    /**
    * Sets the damage from of this plans filter.
    *
    * @param damageFrom the damage from of this plans filter
    */
    public void setDamageFrom(java.lang.Double damageFrom) {
        _plansFilter.setDamageFrom(damageFrom);
    }

    /**
    * Returns the damage to of this plans filter.
    *
    * @return the damage to of this plans filter
    */
    public java.lang.Double getDamageTo() {
        return _plansFilter.getDamageTo();
    }

    /**
    * Sets the damage to of this plans filter.
    *
    * @param damageTo the damage to of this plans filter
    */
    public void setDamageTo(java.lang.Double damageTo) {
        _plansFilter.setDamageTo(damageTo);
    }

    /**
    * Returns the mitigation from of this plans filter.
    *
    * @return the mitigation from of this plans filter
    */
    public java.lang.Double getMitigationFrom() {
        return _plansFilter.getMitigationFrom();
    }

    /**
    * Sets the mitigation from of this plans filter.
    *
    * @param mitigationFrom the mitigation from of this plans filter
    */
    public void setMitigationFrom(java.lang.Double mitigationFrom) {
        _plansFilter.setMitigationFrom(mitigationFrom);
    }

    /**
    * Returns the mitigation to of this plans filter.
    *
    * @return the mitigation to of this plans filter
    */
    public java.lang.Double getMitigationTo() {
        return _plansFilter.getMitigationTo();
    }

    /**
    * Sets the mitigation to of this plans filter.
    *
    * @param mitigationTo the mitigation to of this plans filter
    */
    public void setMitigationTo(java.lang.Double mitigationTo) {
        _plansFilter.setMitigationTo(mitigationTo);
    }

    /**
    * Returns the date from of this plans filter.
    *
    * @return the date from of this plans filter
    */
    public java.util.Date getDateFrom() {
        return _plansFilter.getDateFrom();
    }

    /**
    * Sets the date from of this plans filter.
    *
    * @param dateFrom the date from of this plans filter
    */
    public void setDateFrom(java.util.Date dateFrom) {
        _plansFilter.setDateFrom(dateFrom);
    }

    /**
    * Returns the date to of this plans filter.
    *
    * @return the date to of this plans filter
    */
    public java.util.Date getDateTo() {
        return _plansFilter.getDateTo();
    }

    /**
    * Sets the date to of this plans filter.
    *
    * @param dateTo the date to of this plans filter
    */
    public void setDateTo(java.util.Date dateTo) {
        _plansFilter.setDateTo(dateTo);
    }

    /**
    * Returns the filter positions all of this plans filter.
    *
    * @return the filter positions all of this plans filter
    */
    public java.lang.Boolean getFilterPositionsAll() {
        return _plansFilter.getFilterPositionsAll();
    }

    /**
    * Sets the filter positions all of this plans filter.
    *
    * @param filterPositionsAll the filter positions all of this plans filter
    */
    public void setFilterPositionsAll(java.lang.Boolean filterPositionsAll) {
        _plansFilter.setFilterPositionsAll(filterPositionsAll);
    }

    /**
    * Returns the enabled of this plans filter.
    *
    * @return the enabled of this plans filter
    */
    public java.lang.Boolean getEnabled() {
        return _plansFilter.getEnabled();
    }

    /**
    * Sets the enabled of this plans filter.
    *
    * @param enabled the enabled of this plans filter
    */
    public void setEnabled(java.lang.Boolean enabled) {
        _plansFilter.setEnabled(enabled);
    }

    public boolean isNew() {
        return _plansFilter.isNew();
    }

    public void setNew(boolean n) {
        _plansFilter.setNew(n);
    }

    public boolean isCachedModel() {
        return _plansFilter.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _plansFilter.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _plansFilter.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _plansFilter.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _plansFilter.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _plansFilter.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _plansFilter.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlansFilterWrapper((PlansFilter) _plansFilter.clone());
    }

    public int compareTo(PlansFilter plansFilter) {
        return _plansFilter.compareTo(plansFilter);
    }

    @Override
    public int hashCode() {
        return _plansFilter.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlansFilter> toCacheModel() {
        return _plansFilter.toCacheModel();
    }

    public PlansFilter toEscapedModel() {
        return new PlansFilterWrapper(_plansFilter.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _plansFilter.toString();
    }

    public java.lang.String toXmlString() {
        return _plansFilter.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _plansFilter.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlansFilter getWrappedPlansFilter() {
        return _plansFilter;
    }

    public PlansFilter getWrappedModel() {
        return _plansFilter;
    }

    public void resetOriginalValues() {
        _plansFilter.resetOriginalValues();
    }
}
