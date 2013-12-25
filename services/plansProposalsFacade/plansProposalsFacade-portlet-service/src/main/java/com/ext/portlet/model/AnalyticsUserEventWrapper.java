package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AnalyticsUserEvent}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AnalyticsUserEvent
 * @generated
 */
public class AnalyticsUserEventWrapper implements AnalyticsUserEvent,
    ModelWrapper<AnalyticsUserEvent> {
    private AnalyticsUserEvent _analyticsUserEvent;

    public AnalyticsUserEventWrapper(AnalyticsUserEvent analyticsUserEvent) {
        _analyticsUserEvent = analyticsUserEvent;
    }

    public Class<?> getModelClass() {
        return AnalyticsUserEvent.class;
    }

    public String getModelClassName() {
        return AnalyticsUserEvent.class.getName();
    }

    /**
    * Returns the primary key of this analytics user event.
    *
    * @return the primary key of this analytics user event
    */
    public com.ext.portlet.service.persistence.AnalyticsUserEventPK getPrimaryKey() {
        return _analyticsUserEvent.getPrimaryKey();
    }

    /**
    * Sets the primary key of this analytics user event.
    *
    * @param primaryKey the primary key of this analytics user event
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK primaryKey) {
        _analyticsUserEvent.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this analytics user event.
    *
    * @return the user ID of this analytics user event
    */
    public long getUserId() {
        return _analyticsUserEvent.getUserId();
    }

    /**
    * Sets the user ID of this analytics user event.
    *
    * @param userId the user ID of this analytics user event
    */
    public void setUserId(long userId) {
        _analyticsUserEvent.setUserId(userId);
    }

    /**
    * Returns the user uuid of this analytics user event.
    *
    * @return the user uuid of this analytics user event
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEvent.getUserUuid();
    }

    /**
    * Sets the user uuid of this analytics user event.
    *
    * @param userUuid the user uuid of this analytics user event
    */
    public void setUserUuid(java.lang.String userUuid) {
        _analyticsUserEvent.setUserUuid(userUuid);
    }

    /**
    * Returns the id string of this analytics user event.
    *
    * @return the id string of this analytics user event
    */
    public java.lang.String getIdString() {
        return _analyticsUserEvent.getIdString();
    }

    /**
    * Sets the id string of this analytics user event.
    *
    * @param idString the id string of this analytics user event
    */
    public void setIdString(java.lang.String idString) {
        _analyticsUserEvent.setIdString(idString);
    }

    /**
    * Returns the category of this analytics user event.
    *
    * @return the category of this analytics user event
    */
    public java.lang.String getCategory() {
        return _analyticsUserEvent.getCategory();
    }

    /**
    * Sets the category of this analytics user event.
    *
    * @param category the category of this analytics user event
    */
    public void setCategory(java.lang.String category) {
        _analyticsUserEvent.setCategory(category);
    }

    /**
    * Returns the action of this analytics user event.
    *
    * @return the action of this analytics user event
    */
    public java.lang.String getAction() {
        return _analyticsUserEvent.getAction();
    }

    /**
    * Sets the action of this analytics user event.
    *
    * @param action the action of this analytics user event
    */
    public void setAction(java.lang.String action) {
        _analyticsUserEvent.setAction(action);
    }

    /**
    * Returns the label of this analytics user event.
    *
    * @return the label of this analytics user event
    */
    public java.lang.String getLabel() {
        return _analyticsUserEvent.getLabel();
    }

    /**
    * Sets the label of this analytics user event.
    *
    * @param label the label of this analytics user event
    */
    public void setLabel(java.lang.String label) {
        _analyticsUserEvent.setLabel(label);
    }

    /**
    * Returns the value of this analytics user event.
    *
    * @return the value of this analytics user event
    */
    public int getValue() {
        return _analyticsUserEvent.getValue();
    }

    /**
    * Sets the value of this analytics user event.
    *
    * @param value the value of this analytics user event
    */
    public void setValue(int value) {
        _analyticsUserEvent.setValue(value);
    }

    /**
    * Returns the created of this analytics user event.
    *
    * @return the created of this analytics user event
    */
    public java.util.Date getCreated() {
        return _analyticsUserEvent.getCreated();
    }

    /**
    * Sets the created of this analytics user event.
    *
    * @param created the created of this analytics user event
    */
    public void setCreated(java.util.Date created) {
        _analyticsUserEvent.setCreated(created);
    }

    public boolean isNew() {
        return _analyticsUserEvent.isNew();
    }

    public void setNew(boolean n) {
        _analyticsUserEvent.setNew(n);
    }

    public boolean isCachedModel() {
        return _analyticsUserEvent.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _analyticsUserEvent.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _analyticsUserEvent.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _analyticsUserEvent.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _analyticsUserEvent.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _analyticsUserEvent.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _analyticsUserEvent.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AnalyticsUserEventWrapper((AnalyticsUserEvent) _analyticsUserEvent.clone());
    }

    public int compareTo(AnalyticsUserEvent analyticsUserEvent) {
        return _analyticsUserEvent.compareTo(analyticsUserEvent);
    }

    @Override
    public int hashCode() {
        return _analyticsUserEvent.hashCode();
    }

    public com.liferay.portal.model.CacheModel<AnalyticsUserEvent> toCacheModel() {
        return _analyticsUserEvent.toCacheModel();
    }

    public AnalyticsUserEvent toEscapedModel() {
        return new AnalyticsUserEventWrapper(_analyticsUserEvent.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _analyticsUserEvent.toString();
    }

    public java.lang.String toXmlString() {
        return _analyticsUserEvent.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _analyticsUserEvent.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public AnalyticsUserEvent getWrappedAnalyticsUserEvent() {
        return _analyticsUserEvent;
    }

    public AnalyticsUserEvent getWrappedModel() {
        return _analyticsUserEvent;
    }

    public void resetOriginalValues() {
        _analyticsUserEvent.resetOriginalValues();
    }
}
