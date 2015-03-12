package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImpactTemplateFocusAreaList}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateFocusAreaList
 * @generated
 */
public class ImpactTemplateFocusAreaListWrapper
    implements ImpactTemplateFocusAreaList,
        ModelWrapper<ImpactTemplateFocusAreaList> {
    private ImpactTemplateFocusAreaList _impactTemplateFocusAreaList;

    public ImpactTemplateFocusAreaListWrapper(
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList) {
        _impactTemplateFocusAreaList = impactTemplateFocusAreaList;
    }

    @Override
    public Class<?> getModelClass() {
        return ImpactTemplateFocusAreaList.class;
    }

    @Override
    public String getModelClassName() {
        return ImpactTemplateFocusAreaList.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("focusAreaListId", getFocusAreaListId());
        attributes.put("name", getName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long focusAreaListId = (Long) attributes.get("focusAreaListId");

        if (focusAreaListId != null) {
            setFocusAreaListId(focusAreaListId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }
    }

    /**
    * Returns the primary key of this impact template focus area list.
    *
    * @return the primary key of this impact template focus area list
    */
    @Override
    public long getPrimaryKey() {
        return _impactTemplateFocusAreaList.getPrimaryKey();
    }

    /**
    * Sets the primary key of this impact template focus area list.
    *
    * @param primaryKey the primary key of this impact template focus area list
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _impactTemplateFocusAreaList.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the focus area list ID of this impact template focus area list.
    *
    * @return the focus area list ID of this impact template focus area list
    */
    @Override
    public long getFocusAreaListId() {
        return _impactTemplateFocusAreaList.getFocusAreaListId();
    }

    /**
    * Sets the focus area list ID of this impact template focus area list.
    *
    * @param focusAreaListId the focus area list ID of this impact template focus area list
    */
    @Override
    public void setFocusAreaListId(long focusAreaListId) {
        _impactTemplateFocusAreaList.setFocusAreaListId(focusAreaListId);
    }

    /**
    * Returns the name of this impact template focus area list.
    *
    * @return the name of this impact template focus area list
    */
    @Override
    public java.lang.String getName() {
        return _impactTemplateFocusAreaList.getName();
    }

    /**
    * Sets the name of this impact template focus area list.
    *
    * @param name the name of this impact template focus area list
    */
    @Override
    public void setName(java.lang.String name) {
        _impactTemplateFocusAreaList.setName(name);
    }

    @Override
    public boolean isNew() {
        return _impactTemplateFocusAreaList.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _impactTemplateFocusAreaList.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _impactTemplateFocusAreaList.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _impactTemplateFocusAreaList.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _impactTemplateFocusAreaList.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _impactTemplateFocusAreaList.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _impactTemplateFocusAreaList.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _impactTemplateFocusAreaList.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _impactTemplateFocusAreaList.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _impactTemplateFocusAreaList.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _impactTemplateFocusAreaList.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImpactTemplateFocusAreaListWrapper((ImpactTemplateFocusAreaList) _impactTemplateFocusAreaList.clone());
    }

    @Override
    public int compareTo(
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList) {
        return _impactTemplateFocusAreaList.compareTo(impactTemplateFocusAreaList);
    }

    @Override
    public int hashCode() {
        return _impactTemplateFocusAreaList.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ImpactTemplateFocusAreaList> toCacheModel() {
        return _impactTemplateFocusAreaList.toCacheModel();
    }

    @Override
    public ImpactTemplateFocusAreaList toEscapedModel() {
        return new ImpactTemplateFocusAreaListWrapper(_impactTemplateFocusAreaList.toEscapedModel());
    }

    @Override
    public ImpactTemplateFocusAreaList toUnescapedModel() {
        return new ImpactTemplateFocusAreaListWrapper(_impactTemplateFocusAreaList.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _impactTemplateFocusAreaList.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _impactTemplateFocusAreaList.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _impactTemplateFocusAreaList.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImpactTemplateFocusAreaListWrapper)) {
            return false;
        }

        ImpactTemplateFocusAreaListWrapper impactTemplateFocusAreaListWrapper = (ImpactTemplateFocusAreaListWrapper) obj;

        if (Validator.equals(_impactTemplateFocusAreaList,
                    impactTemplateFocusAreaListWrapper._impactTemplateFocusAreaList)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImpactTemplateFocusAreaList getWrappedImpactTemplateFocusAreaList() {
        return _impactTemplateFocusAreaList;
    }

    @Override
    public ImpactTemplateFocusAreaList getWrappedModel() {
        return _impactTemplateFocusAreaList;
    }

    @Override
    public void resetOriginalValues() {
        _impactTemplateFocusAreaList.resetOriginalValues();
    }
}
