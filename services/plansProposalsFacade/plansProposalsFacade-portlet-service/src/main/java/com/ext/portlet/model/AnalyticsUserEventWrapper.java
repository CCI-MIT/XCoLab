package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AnalyticsUserEvent}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsUserEvent
 * @generated
 */
public class AnalyticsUserEventWrapper implements AnalyticsUserEvent,
    ModelWrapper<AnalyticsUserEvent> {
    private AnalyticsUserEvent _analyticsUserEvent;

    public AnalyticsUserEventWrapper(AnalyticsUserEvent analyticsUserEvent) {
        _analyticsUserEvent = analyticsUserEvent;
    }

    @Override
    public Class<?> getModelClass() {
        return AnalyticsUserEvent.class;
    }

    @Override
    public String getModelClassName() {
        return AnalyticsUserEvent.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("idString", getIdString());
        attributes.put("category", getCategory());
        attributes.put("action", getAction());
        attributes.put("label", getLabel());
        attributes.put("value", getValue());
        attributes.put("created", getCreated());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String idString = (String) attributes.get("idString");

        if (idString != null) {
            setIdString(idString);
        }

        String category = (String) attributes.get("category");

        if (category != null) {
            setCategory(category);
        }

        String action = (String) attributes.get("action");

        if (action != null) {
            setAction(action);
        }

        String label = (String) attributes.get("label");

        if (label != null) {
            setLabel(label);
        }

        Integer value = (Integer) attributes.get("value");

        if (value != null) {
            setValue(value);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }
    }

    /**
    * Returns the primary key of this analytics user event.
    *
    * @return the primary key of this analytics user event
    */
    @Override
    public com.ext.portlet.service.persistence.AnalyticsUserEventPK getPrimaryKey() {
        return _analyticsUserEvent.getPrimaryKey();
    }

    /**
    * Sets the primary key of this analytics user event.
    *
    * @param primaryKey the primary key of this analytics user event
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK primaryKey) {
        _analyticsUserEvent.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this analytics user event.
    *
    * @return the user ID of this analytics user event
    */
    @Override
    public long getUserId() {
        return _analyticsUserEvent.getUserId();
    }

    /**
    * Sets the user ID of this analytics user event.
    *
    * @param userId the user ID of this analytics user event
    */
    @Override
    public void setUserId(long userId) {
        _analyticsUserEvent.setUserId(userId);
    }

    /**
    * Returns the user uuid of this analytics user event.
    *
    * @return the user uuid of this analytics user event
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEvent.getUserUuid();
    }

    /**
    * Sets the user uuid of this analytics user event.
    *
    * @param userUuid the user uuid of this analytics user event
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _analyticsUserEvent.setUserUuid(userUuid);
    }

    /**
    * Returns the id string of this analytics user event.
    *
    * @return the id string of this analytics user event
    */
    @Override
    public java.lang.String getIdString() {
        return _analyticsUserEvent.getIdString();
    }

    /**
    * Sets the id string of this analytics user event.
    *
    * @param idString the id string of this analytics user event
    */
    @Override
    public void setIdString(java.lang.String idString) {
        _analyticsUserEvent.setIdString(idString);
    }

    /**
    * Returns the category of this analytics user event.
    *
    * @return the category of this analytics user event
    */
    @Override
    public java.lang.String getCategory() {
        return _analyticsUserEvent.getCategory();
    }

    /**
    * Sets the category of this analytics user event.
    *
    * @param category the category of this analytics user event
    */
    @Override
    public void setCategory(java.lang.String category) {
        _analyticsUserEvent.setCategory(category);
    }

    /**
    * Returns the action of this analytics user event.
    *
    * @return the action of this analytics user event
    */
    @Override
    public java.lang.String getAction() {
        return _analyticsUserEvent.getAction();
    }

    /**
    * Sets the action of this analytics user event.
    *
    * @param action the action of this analytics user event
    */
    @Override
    public void setAction(java.lang.String action) {
        _analyticsUserEvent.setAction(action);
    }

    /**
    * Returns the label of this analytics user event.
    *
    * @return the label of this analytics user event
    */
    @Override
    public java.lang.String getLabel() {
        return _analyticsUserEvent.getLabel();
    }

    /**
    * Sets the label of this analytics user event.
    *
    * @param label the label of this analytics user event
    */
    @Override
    public void setLabel(java.lang.String label) {
        _analyticsUserEvent.setLabel(label);
    }

    /**
    * Returns the value of this analytics user event.
    *
    * @return the value of this analytics user event
    */
    @Override
    public int getValue() {
        return _analyticsUserEvent.getValue();
    }

    /**
    * Sets the value of this analytics user event.
    *
    * @param value the value of this analytics user event
    */
    @Override
    public void setValue(int value) {
        _analyticsUserEvent.setValue(value);
    }

    /**
    * Returns the created of this analytics user event.
    *
    * @return the created of this analytics user event
    */
    @Override
    public java.util.Date getCreated() {
        return _analyticsUserEvent.getCreated();
    }

    /**
    * Sets the created of this analytics user event.
    *
    * @param created the created of this analytics user event
    */
    @Override
    public void setCreated(java.util.Date created) {
        _analyticsUserEvent.setCreated(created);
    }

    @Override
    public boolean isNew() {
        return _analyticsUserEvent.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _analyticsUserEvent.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _analyticsUserEvent.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _analyticsUserEvent.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _analyticsUserEvent.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _analyticsUserEvent.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _analyticsUserEvent.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _analyticsUserEvent.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _analyticsUserEvent.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _analyticsUserEvent.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _analyticsUserEvent.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AnalyticsUserEventWrapper((AnalyticsUserEvent) _analyticsUserEvent.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent) {
        return _analyticsUserEvent.compareTo(analyticsUserEvent);
    }

    @Override
    public int hashCode() {
        return _analyticsUserEvent.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.AnalyticsUserEvent> toCacheModel() {
        return _analyticsUserEvent.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.AnalyticsUserEvent toEscapedModel() {
        return new AnalyticsUserEventWrapper(_analyticsUserEvent.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.AnalyticsUserEvent toUnescapedModel() {
        return new AnalyticsUserEventWrapper(_analyticsUserEvent.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _analyticsUserEvent.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _analyticsUserEvent.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _analyticsUserEvent.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AnalyticsUserEventWrapper)) {
            return false;
        }

        AnalyticsUserEventWrapper analyticsUserEventWrapper = (AnalyticsUserEventWrapper) obj;

        if (Validator.equals(_analyticsUserEvent,
                    analyticsUserEventWrapper._analyticsUserEvent)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public AnalyticsUserEvent getWrappedAnalyticsUserEvent() {
        return _analyticsUserEvent;
    }

    @Override
    public AnalyticsUserEvent getWrappedModel() {
        return _analyticsUserEvent;
    }

    @Override
    public void resetOriginalValues() {
        _analyticsUserEvent.resetOriginalValues();
    }
}
