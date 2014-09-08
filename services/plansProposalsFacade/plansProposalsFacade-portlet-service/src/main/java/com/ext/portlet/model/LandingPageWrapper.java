package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LandingPage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LandingPage
 * @generated
 */
public class LandingPageWrapper implements LandingPage,
    ModelWrapper<LandingPage> {
    private LandingPage _landingPage;

    public LandingPageWrapper(LandingPage landingPage) {
        _landingPage = landingPage;
    }

    @Override
    public Class<?> getModelClass() {
        return LandingPage.class;
    }

    @Override
    public String getModelClassName() {
        return LandingPage.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("baseUrl", getBaseUrl());
        attributes.put("targetUrl", getTargetUrl());
        attributes.put("updated", getUpdated());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String baseUrl = (String) attributes.get("baseUrl");

        if (baseUrl != null) {
            setBaseUrl(baseUrl);
        }

        String targetUrl = (String) attributes.get("targetUrl");

        if (targetUrl != null) {
            setTargetUrl(targetUrl);
        }

        Date updated = (Date) attributes.get("updated");

        if (updated != null) {
            setUpdated(updated);
        }
    }

    /**
    * Returns the primary key of this landing page.
    *
    * @return the primary key of this landing page
    */
    @Override
    public long getPrimaryKey() {
        return _landingPage.getPrimaryKey();
    }

    /**
    * Sets the primary key of this landing page.
    *
    * @param primaryKey the primary key of this landing page
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _landingPage.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this landing page.
    *
    * @return the ID of this landing page
    */
    @Override
    public long getId() {
        return _landingPage.getId();
    }

    /**
    * Sets the ID of this landing page.
    *
    * @param id the ID of this landing page
    */
    @Override
    public void setId(long id) {
        _landingPage.setId(id);
    }

    /**
    * Returns the base url of this landing page.
    *
    * @return the base url of this landing page
    */
    @Override
    public java.lang.String getBaseUrl() {
        return _landingPage.getBaseUrl();
    }

    /**
    * Sets the base url of this landing page.
    *
    * @param baseUrl the base url of this landing page
    */
    @Override
    public void setBaseUrl(java.lang.String baseUrl) {
        _landingPage.setBaseUrl(baseUrl);
    }

    /**
    * Returns the target url of this landing page.
    *
    * @return the target url of this landing page
    */
    @Override
    public java.lang.String getTargetUrl() {
        return _landingPage.getTargetUrl();
    }

    /**
    * Sets the target url of this landing page.
    *
    * @param targetUrl the target url of this landing page
    */
    @Override
    public void setTargetUrl(java.lang.String targetUrl) {
        _landingPage.setTargetUrl(targetUrl);
    }

    /**
    * Returns the updated of this landing page.
    *
    * @return the updated of this landing page
    */
    @Override
    public java.util.Date getUpdated() {
        return _landingPage.getUpdated();
    }

    /**
    * Sets the updated of this landing page.
    *
    * @param updated the updated of this landing page
    */
    @Override
    public void setUpdated(java.util.Date updated) {
        _landingPage.setUpdated(updated);
    }

    @Override
    public boolean isNew() {
        return _landingPage.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _landingPage.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _landingPage.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _landingPage.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _landingPage.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _landingPage.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _landingPage.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _landingPage.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _landingPage.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _landingPage.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _landingPage.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new LandingPageWrapper((LandingPage) _landingPage.clone());
    }

    @Override
    public int compareTo(LandingPage landingPage) {
        return _landingPage.compareTo(landingPage);
    }

    @Override
    public int hashCode() {
        return _landingPage.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<LandingPage> toCacheModel() {
        return _landingPage.toCacheModel();
    }

    @Override
    public LandingPage toEscapedModel() {
        return new LandingPageWrapper(_landingPage.toEscapedModel());
    }

    @Override
    public LandingPage toUnescapedModel() {
        return new LandingPageWrapper(_landingPage.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _landingPage.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _landingPage.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _landingPage.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof LandingPageWrapper)) {
            return false;
        }

        LandingPageWrapper landingPageWrapper = (LandingPageWrapper) obj;

        if (Validator.equals(_landingPage, landingPageWrapper._landingPage)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public LandingPage getWrappedLandingPage() {
        return _landingPage;
    }

    @Override
    public LandingPage getWrappedModel() {
        return _landingPage;
    }

    @Override
    public void resetOriginalValues() {
        _landingPage.resetOriginalValues();
    }
}
