package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link LandingPage}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LandingPage
 * @generated
 */
public class LandingPageWrapper implements LandingPage,
    ModelWrapper<LandingPage> {
    private LandingPage _landingPage;

    public LandingPageWrapper(LandingPage landingPage) {
        _landingPage = landingPage;
    }

    public Class<?> getModelClass() {
        return LandingPage.class;
    }

    public String getModelClassName() {
        return LandingPage.class.getName();
    }

    /**
    * Returns the primary key of this landing page.
    *
    * @return the primary key of this landing page
    */
    public long getPrimaryKey() {
        return _landingPage.getPrimaryKey();
    }

    /**
    * Sets the primary key of this landing page.
    *
    * @param primaryKey the primary key of this landing page
    */
    public void setPrimaryKey(long primaryKey) {
        _landingPage.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this landing page.
    *
    * @return the ID of this landing page
    */
    public long getId() {
        return _landingPage.getId();
    }

    /**
    * Sets the ID of this landing page.
    *
    * @param id the ID of this landing page
    */
    public void setId(long id) {
        _landingPage.setId(id);
    }

    /**
    * Returns the base url of this landing page.
    *
    * @return the base url of this landing page
    */
    public java.lang.String getBaseUrl() {
        return _landingPage.getBaseUrl();
    }

    /**
    * Sets the base url of this landing page.
    *
    * @param baseUrl the base url of this landing page
    */
    public void setBaseUrl(java.lang.String baseUrl) {
        _landingPage.setBaseUrl(baseUrl);
    }

    /**
    * Returns the target url of this landing page.
    *
    * @return the target url of this landing page
    */
    public java.lang.String getTargetUrl() {
        return _landingPage.getTargetUrl();
    }

    /**
    * Sets the target url of this landing page.
    *
    * @param targetUrl the target url of this landing page
    */
    public void setTargetUrl(java.lang.String targetUrl) {
        _landingPage.setTargetUrl(targetUrl);
    }

    /**
    * Returns the updated of this landing page.
    *
    * @return the updated of this landing page
    */
    public java.util.Date getUpdated() {
        return _landingPage.getUpdated();
    }

    /**
    * Sets the updated of this landing page.
    *
    * @param updated the updated of this landing page
    */
    public void setUpdated(java.util.Date updated) {
        _landingPage.setUpdated(updated);
    }

    public boolean isNew() {
        return _landingPage.isNew();
    }

    public void setNew(boolean n) {
        _landingPage.setNew(n);
    }

    public boolean isCachedModel() {
        return _landingPage.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _landingPage.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _landingPage.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _landingPage.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _landingPage.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _landingPage.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _landingPage.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new LandingPageWrapper((LandingPage) _landingPage.clone());
    }

    public int compareTo(LandingPage landingPage) {
        return _landingPage.compareTo(landingPage);
    }

    @Override
    public int hashCode() {
        return _landingPage.hashCode();
    }

    public com.liferay.portal.model.CacheModel<LandingPage> toCacheModel() {
        return _landingPage.toCacheModel();
    }

    public LandingPage toEscapedModel() {
        return new LandingPageWrapper(_landingPage.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _landingPage.toString();
    }

    public java.lang.String toXmlString() {
        return _landingPage.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _landingPage.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public LandingPage getWrappedLandingPage() {
        return _landingPage;
    }

    public LandingPage getWrappedModel() {
        return _landingPage;
    }

    public void resetOriginalValues() {
        _landingPage.resetOriginalValues();
    }
}
