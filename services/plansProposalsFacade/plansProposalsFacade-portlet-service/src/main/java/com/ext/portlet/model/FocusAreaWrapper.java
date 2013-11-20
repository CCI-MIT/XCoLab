package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FocusArea}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusArea
 * @generated
 */
public class FocusAreaWrapper implements FocusArea, ModelWrapper<FocusArea> {
    private FocusArea _focusArea;

    public FocusAreaWrapper(FocusArea focusArea) {
        _focusArea = focusArea;
    }

    @Override
    public Class<?> getModelClass() {
        return FocusArea.class;
    }

    @Override
    public String getModelClassName() {
        return FocusArea.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("name", getName());
        attributes.put("order", getOrder());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        Integer order = (Integer) attributes.get("order");

        if (order != null) {
            setOrder(order);
        }
    }

    /**
    * Returns the primary key of this focus area.
    *
    * @return the primary key of this focus area
    */
    @Override
    public long getPrimaryKey() {
        return _focusArea.getPrimaryKey();
    }

    /**
    * Sets the primary key of this focus area.
    *
    * @param primaryKey the primary key of this focus area
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _focusArea.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this focus area.
    *
    * @return the ID of this focus area
    */
    @Override
    public long getId() {
        return _focusArea.getId();
    }

    /**
    * Sets the ID of this focus area.
    *
    * @param id the ID of this focus area
    */
    @Override
    public void setId(long id) {
        _focusArea.setId(id);
    }

    /**
    * Returns the name of this focus area.
    *
    * @return the name of this focus area
    */
    @Override
    public java.lang.String getName() {
        return _focusArea.getName();
    }

    /**
    * Sets the name of this focus area.
    *
    * @param name the name of this focus area
    */
    @Override
    public void setName(java.lang.String name) {
        _focusArea.setName(name);
    }

    /**
    * Returns the order of this focus area.
    *
    * @return the order of this focus area
    */
    @Override
    public int getOrder() {
        return _focusArea.getOrder();
    }

    /**
    * Sets the order of this focus area.
    *
    * @param order the order of this focus area
    */
    @Override
    public void setOrder(int order) {
        _focusArea.setOrder(order);
    }

    @Override
    public boolean isNew() {
        return _focusArea.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _focusArea.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _focusArea.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _focusArea.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _focusArea.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _focusArea.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _focusArea.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _focusArea.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _focusArea.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _focusArea.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _focusArea.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new FocusAreaWrapper((FocusArea) _focusArea.clone());
    }

    @Override
    public int compareTo(FocusArea focusArea) {
        return _focusArea.compareTo(focusArea);
    }

    @Override
    public int hashCode() {
        return _focusArea.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<FocusArea> toCacheModel() {
        return _focusArea.toCacheModel();
    }

    @Override
    public FocusArea toEscapedModel() {
        return new FocusAreaWrapper(_focusArea.toEscapedModel());
    }

    @Override
    public FocusArea toUnescapedModel() {
        return new FocusAreaWrapper(_focusArea.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _focusArea.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _focusArea.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _focusArea.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FocusAreaWrapper)) {
            return false;
        }

        FocusAreaWrapper focusAreaWrapper = (FocusAreaWrapper) obj;

        if (Validator.equals(_focusArea, focusAreaWrapper._focusArea)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public FocusArea getWrappedFocusArea() {
        return _focusArea;
    }

    @Override
    public FocusArea getWrappedModel() {
        return _focusArea;
    }

    @Override
    public void resetOriginalValues() {
        _focusArea.resetOriginalValues();
    }
}
