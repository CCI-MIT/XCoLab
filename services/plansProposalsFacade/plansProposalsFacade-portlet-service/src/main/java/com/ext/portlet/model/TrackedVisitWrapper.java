package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TrackedVisit}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisit
 * @generated
 */
public class TrackedVisitWrapper implements TrackedVisit,
    ModelWrapper<TrackedVisit> {
    private TrackedVisit _trackedVisit;

    public TrackedVisitWrapper(TrackedVisit trackedVisit) {
        _trackedVisit = trackedVisit;
    }

    @Override
    public Class<?> getModelClass() {
        return TrackedVisit.class;
    }

    @Override
    public String getModelClassName() {
        return TrackedVisit.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("uuid", getUuid());
        attributes.put("ip", getIp());
        attributes.put("city", getCity());
        attributes.put("country", getCountry());
        attributes.put("url", getUrl());
        attributes.put("browser", getBrowser());
        attributes.put("headers", getHeaders());
        attributes.put("referer", getReferer());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        String ip = (String) attributes.get("ip");

        if (ip != null) {
            setIp(ip);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String url = (String) attributes.get("url");

        if (url != null) {
            setUrl(url);
        }

        String browser = (String) attributes.get("browser");

        if (browser != null) {
            setBrowser(browser);
        }

        String headers = (String) attributes.get("headers");

        if (headers != null) {
            setHeaders(headers);
        }

        String referer = (String) attributes.get("referer");

        if (referer != null) {
            setReferer(referer);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    /**
    * Returns the primary key of this tracked visit.
    *
    * @return the primary key of this tracked visit
    */
    @Override
    public long getPrimaryKey() {
        return _trackedVisit.getPrimaryKey();
    }

    /**
    * Sets the primary key of this tracked visit.
    *
    * @param primaryKey the primary key of this tracked visit
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _trackedVisit.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this tracked visit.
    *
    * @return the ID of this tracked visit
    */
    @Override
    public long getId() {
        return _trackedVisit.getId();
    }

    /**
    * Sets the ID of this tracked visit.
    *
    * @param id the ID of this tracked visit
    */
    @Override
    public void setId(long id) {
        _trackedVisit.setId(id);
    }

    /**
    * Returns the uuid of this tracked visit.
    *
    * @return the uuid of this tracked visit
    */
    @Override
    public java.lang.String getUuid() {
        return _trackedVisit.getUuid();
    }

    /**
    * Sets the uuid of this tracked visit.
    *
    * @param uuid the uuid of this tracked visit
    */
    @Override
    public void setUuid(java.lang.String uuid) {
        _trackedVisit.setUuid(uuid);
    }

    /**
    * Returns the ip of this tracked visit.
    *
    * @return the ip of this tracked visit
    */
    @Override
    public java.lang.String getIp() {
        return _trackedVisit.getIp();
    }

    /**
    * Sets the ip of this tracked visit.
    *
    * @param ip the ip of this tracked visit
    */
    @Override
    public void setIp(java.lang.String ip) {
        _trackedVisit.setIp(ip);
    }

    /**
    * Returns the city of this tracked visit.
    *
    * @return the city of this tracked visit
    */
    @Override
    public java.lang.String getCity() {
        return _trackedVisit.getCity();
    }

    /**
    * Sets the city of this tracked visit.
    *
    * @param city the city of this tracked visit
    */
    @Override
    public void setCity(java.lang.String city) {
        _trackedVisit.setCity(city);
    }

    /**
    * Returns the country of this tracked visit.
    *
    * @return the country of this tracked visit
    */
    @Override
    public java.lang.String getCountry() {
        return _trackedVisit.getCountry();
    }

    /**
    * Sets the country of this tracked visit.
    *
    * @param country the country of this tracked visit
    */
    @Override
    public void setCountry(java.lang.String country) {
        _trackedVisit.setCountry(country);
    }

    /**
    * Returns the url of this tracked visit.
    *
    * @return the url of this tracked visit
    */
    @Override
    public java.lang.String getUrl() {
        return _trackedVisit.getUrl();
    }

    /**
    * Sets the url of this tracked visit.
    *
    * @param url the url of this tracked visit
    */
    @Override
    public void setUrl(java.lang.String url) {
        _trackedVisit.setUrl(url);
    }

    /**
    * Returns the browser of this tracked visit.
    *
    * @return the browser of this tracked visit
    */
    @Override
    public java.lang.String getBrowser() {
        return _trackedVisit.getBrowser();
    }

    /**
    * Sets the browser of this tracked visit.
    *
    * @param browser the browser of this tracked visit
    */
    @Override
    public void setBrowser(java.lang.String browser) {
        _trackedVisit.setBrowser(browser);
    }

    /**
    * Returns the headers of this tracked visit.
    *
    * @return the headers of this tracked visit
    */
    @Override
    public java.lang.String getHeaders() {
        return _trackedVisit.getHeaders();
    }

    /**
    * Sets the headers of this tracked visit.
    *
    * @param headers the headers of this tracked visit
    */
    @Override
    public void setHeaders(java.lang.String headers) {
        _trackedVisit.setHeaders(headers);
    }

    /**
    * Returns the referer of this tracked visit.
    *
    * @return the referer of this tracked visit
    */
    @Override
    public java.lang.String getReferer() {
        return _trackedVisit.getReferer();
    }

    /**
    * Sets the referer of this tracked visit.
    *
    * @param referer the referer of this tracked visit
    */
    @Override
    public void setReferer(java.lang.String referer) {
        _trackedVisit.setReferer(referer);
    }

    /**
    * Returns the create date of this tracked visit.
    *
    * @return the create date of this tracked visit
    */
    @Override
    public java.util.Date getCreateDate() {
        return _trackedVisit.getCreateDate();
    }

    /**
    * Sets the create date of this tracked visit.
    *
    * @param createDate the create date of this tracked visit
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _trackedVisit.setCreateDate(createDate);
    }

    @Override
    public boolean isNew() {
        return _trackedVisit.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _trackedVisit.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _trackedVisit.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _trackedVisit.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _trackedVisit.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _trackedVisit.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _trackedVisit.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _trackedVisit.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _trackedVisit.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _trackedVisit.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _trackedVisit.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new TrackedVisitWrapper((TrackedVisit) _trackedVisit.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.TrackedVisit trackedVisit) {
        return _trackedVisit.compareTo(trackedVisit);
    }

    @Override
    public int hashCode() {
        return _trackedVisit.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.TrackedVisit> toCacheModel() {
        return _trackedVisit.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.TrackedVisit toEscapedModel() {
        return new TrackedVisitWrapper(_trackedVisit.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.TrackedVisit toUnescapedModel() {
        return new TrackedVisitWrapper(_trackedVisit.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _trackedVisit.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _trackedVisit.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _trackedVisit.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TrackedVisitWrapper)) {
            return false;
        }

        TrackedVisitWrapper trackedVisitWrapper = (TrackedVisitWrapper) obj;

        if (Validator.equals(_trackedVisit, trackedVisitWrapper._trackedVisit)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public TrackedVisit getWrappedTrackedVisit() {
        return _trackedVisit;
    }

    @Override
    public TrackedVisit getWrappedModel() {
        return _trackedVisit;
    }

    @Override
    public void resetOriginalValues() {
        _trackedVisit.resetOriginalValues();
    }
}
