package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanSectionDefinition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanSectionDefinition
 * @generated
 */
public class PlanSectionDefinitionWrapper implements PlanSectionDefinition,
    ModelWrapper<PlanSectionDefinition> {
    private PlanSectionDefinition _planSectionDefinition;

    public PlanSectionDefinitionWrapper(
        PlanSectionDefinition planSectionDefinition) {
        _planSectionDefinition = planSectionDefinition;
    }

    public Class<?> getModelClass() {
        return PlanSectionDefinition.class;
    }

    public String getModelClassName() {
        return PlanSectionDefinition.class.getName();
    }

    /**
    * Returns the primary key of this plan section definition.
    *
    * @return the primary key of this plan section definition
    */
    public long getPrimaryKey() {
        return _planSectionDefinition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan section definition.
    *
    * @param primaryKey the primary key of this plan section definition
    */
    public void setPrimaryKey(long primaryKey) {
        _planSectionDefinition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan section definition.
    *
    * @return the ID of this plan section definition
    */
    public long getId() {
        return _planSectionDefinition.getId();
    }

    /**
    * Sets the ID of this plan section definition.
    *
    * @param id the ID of this plan section definition
    */
    public void setId(long id) {
        _planSectionDefinition.setId(id);
    }

    /**
    * Returns the type of this plan section definition.
    *
    * @return the type of this plan section definition
    */
    public java.lang.String getType() {
        return _planSectionDefinition.getType();
    }

    /**
    * Sets the type of this plan section definition.
    *
    * @param type the type of this plan section definition
    */
    public void setType(java.lang.String type) {
        _planSectionDefinition.setType(type);
    }

    /**
    * Returns the admin title of this plan section definition.
    *
    * @return the admin title of this plan section definition
    */
    public java.lang.String getAdminTitle() {
        return _planSectionDefinition.getAdminTitle();
    }

    /**
    * Sets the admin title of this plan section definition.
    *
    * @param adminTitle the admin title of this plan section definition
    */
    public void setAdminTitle(java.lang.String adminTitle) {
        _planSectionDefinition.setAdminTitle(adminTitle);
    }

    /**
    * Returns the title of this plan section definition.
    *
    * @return the title of this plan section definition
    */
    public java.lang.String getTitle() {
        return _planSectionDefinition.getTitle();
    }

    /**
    * Sets the title of this plan section definition.
    *
    * @param title the title of this plan section definition
    */
    public void setTitle(java.lang.String title) {
        _planSectionDefinition.setTitle(title);
    }

    /**
    * Returns the default text of this plan section definition.
    *
    * @return the default text of this plan section definition
    */
    public java.lang.String getDefaultText() {
        return _planSectionDefinition.getDefaultText();
    }

    /**
    * Sets the default text of this plan section definition.
    *
    * @param defaultText the default text of this plan section definition
    */
    public void setDefaultText(java.lang.String defaultText) {
        _planSectionDefinition.setDefaultText(defaultText);
    }

    /**
    * Returns the help text of this plan section definition.
    *
    * @return the help text of this plan section definition
    */
    public java.lang.String getHelpText() {
        return _planSectionDefinition.getHelpText();
    }

    /**
    * Sets the help text of this plan section definition.
    *
    * @param helpText the help text of this plan section definition
    */
    public void setHelpText(java.lang.String helpText) {
        _planSectionDefinition.setHelpText(helpText);
    }

    /**
    * Returns the character limit of this plan section definition.
    *
    * @return the character limit of this plan section definition
    */
    public int getCharacterLimit() {
        return _planSectionDefinition.getCharacterLimit();
    }

    /**
    * Sets the character limit of this plan section definition.
    *
    * @param characterLimit the character limit of this plan section definition
    */
    public void setCharacterLimit(int characterLimit) {
        _planSectionDefinition.setCharacterLimit(characterLimit);
    }

    /**
    * Returns the focus area ID of this plan section definition.
    *
    * @return the focus area ID of this plan section definition
    */
    public long getFocusAreaId() {
        return _planSectionDefinition.getFocusAreaId();
    }

    /**
    * Sets the focus area ID of this plan section definition.
    *
    * @param focusAreaId the focus area ID of this plan section definition
    */
    public void setFocusAreaId(long focusAreaId) {
        _planSectionDefinition.setFocusAreaId(focusAreaId);
    }

    /**
    * Returns the locked of this plan section definition.
    *
    * @return the locked of this plan section definition
    */
    public boolean getLocked() {
        return _planSectionDefinition.getLocked();
    }

    /**
    * Returns <code>true</code> if this plan section definition is locked.
    *
    * @return <code>true</code> if this plan section definition is locked; <code>false</code> otherwise
    */
    public boolean isLocked() {
        return _planSectionDefinition.isLocked();
    }

    /**
    * Sets whether this plan section definition is locked.
    *
    * @param locked the locked of this plan section definition
    */
    public void setLocked(boolean locked) {
        _planSectionDefinition.setLocked(locked);
    }

    public boolean isNew() {
        return _planSectionDefinition.isNew();
    }

    public void setNew(boolean n) {
        _planSectionDefinition.setNew(n);
    }

    public boolean isCachedModel() {
        return _planSectionDefinition.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planSectionDefinition.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planSectionDefinition.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planSectionDefinition.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planSectionDefinition.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planSectionDefinition.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planSectionDefinition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanSectionDefinitionWrapper((PlanSectionDefinition) _planSectionDefinition.clone());
    }

    public int compareTo(PlanSectionDefinition planSectionDefinition) {
        return _planSectionDefinition.compareTo(planSectionDefinition);
    }

    @Override
    public int hashCode() {
        return _planSectionDefinition.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanSectionDefinition> toCacheModel() {
        return _planSectionDefinition.toCacheModel();
    }

    public PlanSectionDefinition toEscapedModel() {
        return new PlanSectionDefinitionWrapper(_planSectionDefinition.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planSectionDefinition.toString();
    }

    public java.lang.String toXmlString() {
        return _planSectionDefinition.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSectionDefinition.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanSectionDefinition getWrappedPlanSectionDefinition() {
        return _planSectionDefinition;
    }

    public PlanSectionDefinition getWrappedModel() {
        return _planSectionDefinition;
    }

    public void resetOriginalValues() {
        _planSectionDefinition.resetOriginalValues();
    }
}
