package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BalloonUserTracking}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonUserTracking
 * @generated
 */
public class BalloonUserTrackingWrapper implements BalloonUserTracking,
    ModelWrapper<BalloonUserTracking> {
    private BalloonUserTracking _balloonUserTracking;

    public BalloonUserTrackingWrapper(BalloonUserTracking balloonUserTracking) {
        _balloonUserTracking = balloonUserTracking;
    }

    @Override
    public Class<?> getModelClass() {
        return BalloonUserTracking.class;
    }

    @Override
    public String getModelClassName() {
        return BalloonUserTracking.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("uuid", getUuid());
        attributes.put("email", getEmail());
        attributes.put("parent", getParent());
        attributes.put("ip", getIp());
        attributes.put("createDate", getCreateDate());
        attributes.put("registrationDate", getRegistrationDate());
        attributes.put("formFiledDate", getFormFiledDate());
        attributes.put("userId", getUserId());
        attributes.put("balloonTextId", getBalloonTextId());
        attributes.put("referrer", getReferrer());
        attributes.put("latitude", getLatitude());
        attributes.put("longitude", getLongitude());
        attributes.put("city", getCity());
        attributes.put("country", getCountry());
        attributes.put("extraData", getExtraData());
        attributes.put("balloonLinkUuid", getBalloonLinkUuid());
        attributes.put("balloonLinkContext", getBalloonLinkContext());
        attributes.put("userAgent", getUserAgent());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        String email = (String) attributes.get("email");

        if (email != null) {
            setEmail(email);
        }

        String parent = (String) attributes.get("parent");

        if (parent != null) {
            setParent(parent);
        }

        String ip = (String) attributes.get("ip");

        if (ip != null) {
            setIp(ip);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date registrationDate = (Date) attributes.get("registrationDate");

        if (registrationDate != null) {
            setRegistrationDate(registrationDate);
        }

        Date formFiledDate = (Date) attributes.get("formFiledDate");

        if (formFiledDate != null) {
            setFormFiledDate(formFiledDate);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long balloonTextId = (Long) attributes.get("balloonTextId");

        if (balloonTextId != null) {
            setBalloonTextId(balloonTextId);
        }

        String referrer = (String) attributes.get("referrer");

        if (referrer != null) {
            setReferrer(referrer);
        }

        Double latitude = (Double) attributes.get("latitude");

        if (latitude != null) {
            setLatitude(latitude);
        }

        Double longitude = (Double) attributes.get("longitude");

        if (longitude != null) {
            setLongitude(longitude);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String extraData = (String) attributes.get("extraData");

        if (extraData != null) {
            setExtraData(extraData);
        }

        String balloonLinkUuid = (String) attributes.get("balloonLinkUuid");

        if (balloonLinkUuid != null) {
            setBalloonLinkUuid(balloonLinkUuid);
        }

        String balloonLinkContext = (String) attributes.get(
                "balloonLinkContext");

        if (balloonLinkContext != null) {
            setBalloonLinkContext(balloonLinkContext);
        }

        String userAgent = (String) attributes.get("userAgent");

        if (userAgent != null) {
            setUserAgent(userAgent);
        }
    }

    /**
    * Returns the primary key of this balloon user tracking.
    *
    * @return the primary key of this balloon user tracking
    */
    @Override
    public java.lang.String getPrimaryKey() {
        return _balloonUserTracking.getPrimaryKey();
    }

    /**
    * Sets the primary key of this balloon user tracking.
    *
    * @param primaryKey the primary key of this balloon user tracking
    */
    @Override
    public void setPrimaryKey(java.lang.String primaryKey) {
        _balloonUserTracking.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the uuid of this balloon user tracking.
    *
    * @return the uuid of this balloon user tracking
    */
    @Override
    public java.lang.String getUuid() {
        return _balloonUserTracking.getUuid();
    }

    /**
    * Sets the uuid of this balloon user tracking.
    *
    * @param uuid the uuid of this balloon user tracking
    */
    @Override
    public void setUuid(java.lang.String uuid) {
        _balloonUserTracking.setUuid(uuid);
    }

    /**
    * Returns the email of this balloon user tracking.
    *
    * @return the email of this balloon user tracking
    */
    @Override
    public java.lang.String getEmail() {
        return _balloonUserTracking.getEmail();
    }

    /**
    * Sets the email of this balloon user tracking.
    *
    * @param email the email of this balloon user tracking
    */
    @Override
    public void setEmail(java.lang.String email) {
        _balloonUserTracking.setEmail(email);
    }

    /**
    * Returns the parent of this balloon user tracking.
    *
    * @return the parent of this balloon user tracking
    */
    @Override
    public java.lang.String getParent() {
        return _balloonUserTracking.getParent();
    }

    /**
    * Sets the parent of this balloon user tracking.
    *
    * @param parent the parent of this balloon user tracking
    */
    @Override
    public void setParent(java.lang.String parent) {
        _balloonUserTracking.setParent(parent);
    }

    /**
    * Returns the ip of this balloon user tracking.
    *
    * @return the ip of this balloon user tracking
    */
    @Override
    public java.lang.String getIp() {
        return _balloonUserTracking.getIp();
    }

    /**
    * Sets the ip of this balloon user tracking.
    *
    * @param ip the ip of this balloon user tracking
    */
    @Override
    public void setIp(java.lang.String ip) {
        _balloonUserTracking.setIp(ip);
    }

    /**
    * Returns the create date of this balloon user tracking.
    *
    * @return the create date of this balloon user tracking
    */
    @Override
    public java.util.Date getCreateDate() {
        return _balloonUserTracking.getCreateDate();
    }

    /**
    * Sets the create date of this balloon user tracking.
    *
    * @param createDate the create date of this balloon user tracking
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _balloonUserTracking.setCreateDate(createDate);
    }

    /**
    * Returns the registration date of this balloon user tracking.
    *
    * @return the registration date of this balloon user tracking
    */
    @Override
    public java.util.Date getRegistrationDate() {
        return _balloonUserTracking.getRegistrationDate();
    }

    /**
    * Sets the registration date of this balloon user tracking.
    *
    * @param registrationDate the registration date of this balloon user tracking
    */
    @Override
    public void setRegistrationDate(java.util.Date registrationDate) {
        _balloonUserTracking.setRegistrationDate(registrationDate);
    }

    /**
    * Returns the form filed date of this balloon user tracking.
    *
    * @return the form filed date of this balloon user tracking
    */
    @Override
    public java.util.Date getFormFiledDate() {
        return _balloonUserTracking.getFormFiledDate();
    }

    /**
    * Sets the form filed date of this balloon user tracking.
    *
    * @param formFiledDate the form filed date of this balloon user tracking
    */
    @Override
    public void setFormFiledDate(java.util.Date formFiledDate) {
        _balloonUserTracking.setFormFiledDate(formFiledDate);
    }

    /**
    * Returns the user ID of this balloon user tracking.
    *
    * @return the user ID of this balloon user tracking
    */
    @Override
    public long getUserId() {
        return _balloonUserTracking.getUserId();
    }

    /**
    * Sets the user ID of this balloon user tracking.
    *
    * @param userId the user ID of this balloon user tracking
    */
    @Override
    public void setUserId(long userId) {
        _balloonUserTracking.setUserId(userId);
    }

    /**
    * Returns the user uuid of this balloon user tracking.
    *
    * @return the user uuid of this balloon user tracking
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _balloonUserTracking.getUserUuid();
    }

    /**
    * Sets the user uuid of this balloon user tracking.
    *
    * @param userUuid the user uuid of this balloon user tracking
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _balloonUserTracking.setUserUuid(userUuid);
    }

    /**
    * Returns the balloon text ID of this balloon user tracking.
    *
    * @return the balloon text ID of this balloon user tracking
    */
    @Override
    public long getBalloonTextId() {
        return _balloonUserTracking.getBalloonTextId();
    }

    /**
    * Sets the balloon text ID of this balloon user tracking.
    *
    * @param balloonTextId the balloon text ID of this balloon user tracking
    */
    @Override
    public void setBalloonTextId(long balloonTextId) {
        _balloonUserTracking.setBalloonTextId(balloonTextId);
    }

    /**
    * Returns the referrer of this balloon user tracking.
    *
    * @return the referrer of this balloon user tracking
    */
    @Override
    public java.lang.String getReferrer() {
        return _balloonUserTracking.getReferrer();
    }

    /**
    * Sets the referrer of this balloon user tracking.
    *
    * @param referrer the referrer of this balloon user tracking
    */
    @Override
    public void setReferrer(java.lang.String referrer) {
        _balloonUserTracking.setReferrer(referrer);
    }

    /**
    * Returns the latitude of this balloon user tracking.
    *
    * @return the latitude of this balloon user tracking
    */
    @Override
    public double getLatitude() {
        return _balloonUserTracking.getLatitude();
    }

    /**
    * Sets the latitude of this balloon user tracking.
    *
    * @param latitude the latitude of this balloon user tracking
    */
    @Override
    public void setLatitude(double latitude) {
        _balloonUserTracking.setLatitude(latitude);
    }

    /**
    * Returns the longitude of this balloon user tracking.
    *
    * @return the longitude of this balloon user tracking
    */
    @Override
    public double getLongitude() {
        return _balloonUserTracking.getLongitude();
    }

    /**
    * Sets the longitude of this balloon user tracking.
    *
    * @param longitude the longitude of this balloon user tracking
    */
    @Override
    public void setLongitude(double longitude) {
        _balloonUserTracking.setLongitude(longitude);
    }

    /**
    * Returns the city of this balloon user tracking.
    *
    * @return the city of this balloon user tracking
    */
    @Override
    public java.lang.String getCity() {
        return _balloonUserTracking.getCity();
    }

    /**
    * Sets the city of this balloon user tracking.
    *
    * @param city the city of this balloon user tracking
    */
    @Override
    public void setCity(java.lang.String city) {
        _balloonUserTracking.setCity(city);
    }

    /**
    * Returns the country of this balloon user tracking.
    *
    * @return the country of this balloon user tracking
    */
    @Override
    public java.lang.String getCountry() {
        return _balloonUserTracking.getCountry();
    }

    /**
    * Sets the country of this balloon user tracking.
    *
    * @param country the country of this balloon user tracking
    */
    @Override
    public void setCountry(java.lang.String country) {
        _balloonUserTracking.setCountry(country);
    }

    /**
    * Returns the extra data of this balloon user tracking.
    *
    * @return the extra data of this balloon user tracking
    */
    @Override
    public java.lang.String getExtraData() {
        return _balloonUserTracking.getExtraData();
    }

    /**
    * Sets the extra data of this balloon user tracking.
    *
    * @param extraData the extra data of this balloon user tracking
    */
    @Override
    public void setExtraData(java.lang.String extraData) {
        _balloonUserTracking.setExtraData(extraData);
    }

    /**
    * Returns the balloon link uuid of this balloon user tracking.
    *
    * @return the balloon link uuid of this balloon user tracking
    */
    @Override
    public java.lang.String getBalloonLinkUuid() {
        return _balloonUserTracking.getBalloonLinkUuid();
    }

    /**
    * Sets the balloon link uuid of this balloon user tracking.
    *
    * @param balloonLinkUuid the balloon link uuid of this balloon user tracking
    */
    @Override
    public void setBalloonLinkUuid(java.lang.String balloonLinkUuid) {
        _balloonUserTracking.setBalloonLinkUuid(balloonLinkUuid);
    }

    /**
    * Returns the balloon link context of this balloon user tracking.
    *
    * @return the balloon link context of this balloon user tracking
    */
    @Override
    public java.lang.String getBalloonLinkContext() {
        return _balloonUserTracking.getBalloonLinkContext();
    }

    /**
    * Sets the balloon link context of this balloon user tracking.
    *
    * @param balloonLinkContext the balloon link context of this balloon user tracking
    */
    @Override
    public void setBalloonLinkContext(java.lang.String balloonLinkContext) {
        _balloonUserTracking.setBalloonLinkContext(balloonLinkContext);
    }

    /**
    * Returns the user agent of this balloon user tracking.
    *
    * @return the user agent of this balloon user tracking
    */
    @Override
    public java.lang.String getUserAgent() {
        return _balloonUserTracking.getUserAgent();
    }

    /**
    * Sets the user agent of this balloon user tracking.
    *
    * @param userAgent the user agent of this balloon user tracking
    */
    @Override
    public void setUserAgent(java.lang.String userAgent) {
        _balloonUserTracking.setUserAgent(userAgent);
    }

    @Override
    public boolean isNew() {
        return _balloonUserTracking.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _balloonUserTracking.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _balloonUserTracking.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _balloonUserTracking.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _balloonUserTracking.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _balloonUserTracking.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _balloonUserTracking.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _balloonUserTracking.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _balloonUserTracking.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _balloonUserTracking.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _balloonUserTracking.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new BalloonUserTrackingWrapper((BalloonUserTracking) _balloonUserTracking.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.BalloonUserTracking balloonUserTracking) {
        return _balloonUserTracking.compareTo(balloonUserTracking);
    }

    @Override
    public int hashCode() {
        return _balloonUserTracking.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.BalloonUserTracking> toCacheModel() {
        return _balloonUserTracking.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.BalloonUserTracking toEscapedModel() {
        return new BalloonUserTrackingWrapper(_balloonUserTracking.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.BalloonUserTracking toUnescapedModel() {
        return new BalloonUserTrackingWrapper(_balloonUserTracking.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _balloonUserTracking.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _balloonUserTracking.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _balloonUserTracking.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BalloonUserTrackingWrapper)) {
            return false;
        }

        BalloonUserTrackingWrapper balloonUserTrackingWrapper = (BalloonUserTrackingWrapper) obj;

        if (Validator.equals(_balloonUserTracking,
                    balloonUserTrackingWrapper._balloonUserTracking)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public BalloonUserTracking getWrappedBalloonUserTracking() {
        return _balloonUserTracking;
    }

    @Override
    public BalloonUserTracking getWrappedModel() {
        return _balloonUserTracking;
    }

    @Override
    public void resetOriginalValues() {
        _balloonUserTracking.resetOriginalValues();
    }
}
