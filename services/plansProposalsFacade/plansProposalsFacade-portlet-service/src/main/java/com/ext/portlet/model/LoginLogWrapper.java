package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link LoginLog}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoginLog
 * @generated
 */
public class LoginLogWrapper implements LoginLog, ModelWrapper<LoginLog> {
    private LoginLog _loginLog;

    public LoginLogWrapper(LoginLog loginLog) {
        _loginLog = loginLog;
    }

    @Override
    public Class<?> getModelClass() {
        return LoginLog.class;
    }

    @Override
    public String getModelClassName() {
        return LoginLog.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("pk", getPk());
        attributes.put("userId", getUserId());
        attributes.put("createDate", getCreateDate());
        attributes.put("ipAddress", getIpAddress());
        attributes.put("city", getCity());
        attributes.put("country", getCountry());
        attributes.put("entryUrl", getEntryUrl());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long pk = (Long) attributes.get("pk");

        if (pk != null) {
            setPk(pk);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        String ipAddress = (String) attributes.get("ipAddress");

        if (ipAddress != null) {
            setIpAddress(ipAddress);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String entryUrl = (String) attributes.get("entryUrl");

        if (entryUrl != null) {
            setEntryUrl(entryUrl);
        }
    }

    /**
    * Returns the primary key of this login log.
    *
    * @return the primary key of this login log
    */
    @Override
    public long getPrimaryKey() {
        return _loginLog.getPrimaryKey();
    }

    /**
    * Sets the primary key of this login log.
    *
    * @param primaryKey the primary key of this login log
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _loginLog.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pk of this login log.
    *
    * @return the pk of this login log
    */
    @Override
    public long getPk() {
        return _loginLog.getPk();
    }

    /**
    * Sets the pk of this login log.
    *
    * @param pk the pk of this login log
    */
    @Override
    public void setPk(long pk) {
        _loginLog.setPk(pk);
    }

    /**
    * Returns the user ID of this login log.
    *
    * @return the user ID of this login log
    */
    @Override
    public long getUserId() {
        return _loginLog.getUserId();
    }

    /**
    * Sets the user ID of this login log.
    *
    * @param userId the user ID of this login log
    */
    @Override
    public void setUserId(long userId) {
        _loginLog.setUserId(userId);
    }

    /**
    * Returns the user uuid of this login log.
    *
    * @return the user uuid of this login log
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _loginLog.getUserUuid();
    }

    /**
    * Sets the user uuid of this login log.
    *
    * @param userUuid the user uuid of this login log
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _loginLog.setUserUuid(userUuid);
    }

    /**
    * Returns the create date of this login log.
    *
    * @return the create date of this login log
    */
    @Override
    public java.util.Date getCreateDate() {
        return _loginLog.getCreateDate();
    }

    /**
    * Sets the create date of this login log.
    *
    * @param createDate the create date of this login log
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _loginLog.setCreateDate(createDate);
    }

    /**
    * Returns the ip address of this login log.
    *
    * @return the ip address of this login log
    */
    @Override
    public java.lang.String getIpAddress() {
        return _loginLog.getIpAddress();
    }

    /**
    * Sets the ip address of this login log.
    *
    * @param ipAddress the ip address of this login log
    */
    @Override
    public void setIpAddress(java.lang.String ipAddress) {
        _loginLog.setIpAddress(ipAddress);
    }

    /**
    * Returns the city of this login log.
    *
    * @return the city of this login log
    */
    @Override
    public java.lang.String getCity() {
        return _loginLog.getCity();
    }

    /**
    * Sets the city of this login log.
    *
    * @param city the city of this login log
    */
    @Override
    public void setCity(java.lang.String city) {
        _loginLog.setCity(city);
    }

    /**
    * Returns the country of this login log.
    *
    * @return the country of this login log
    */
    @Override
    public java.lang.String getCountry() {
        return _loginLog.getCountry();
    }

    /**
    * Sets the country of this login log.
    *
    * @param country the country of this login log
    */
    @Override
    public void setCountry(java.lang.String country) {
        _loginLog.setCountry(country);
    }

    /**
    * Returns the entry url of this login log.
    *
    * @return the entry url of this login log
    */
    @Override
    public java.lang.String getEntryUrl() {
        return _loginLog.getEntryUrl();
    }

    /**
    * Sets the entry url of this login log.
    *
    * @param entryUrl the entry url of this login log
    */
    @Override
    public void setEntryUrl(java.lang.String entryUrl) {
        _loginLog.setEntryUrl(entryUrl);
    }

    @Override
    public boolean isNew() {
        return _loginLog.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _loginLog.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _loginLog.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _loginLog.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _loginLog.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _loginLog.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _loginLog.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _loginLog.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _loginLog.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _loginLog.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _loginLog.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new LoginLogWrapper((LoginLog) _loginLog.clone());
    }

    @Override
    public int compareTo(LoginLog loginLog) {
        return _loginLog.compareTo(loginLog);
    }

    @Override
    public int hashCode() {
        return _loginLog.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<LoginLog> toCacheModel() {
        return _loginLog.toCacheModel();
    }

    @Override
    public LoginLog toEscapedModel() {
        return new LoginLogWrapper(_loginLog.toEscapedModel());
    }

    @Override
    public LoginLog toUnescapedModel() {
        return new LoginLogWrapper(_loginLog.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _loginLog.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _loginLog.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _loginLog.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof LoginLogWrapper)) {
            return false;
        }

        LoginLogWrapper loginLogWrapper = (LoginLogWrapper) obj;

        if (Validator.equals(_loginLog, loginLogWrapper._loginLog)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public LoginLog getWrappedLoginLog() {
        return _loginLog;
    }

    @Override
    public LoginLog getWrappedModel() {
        return _loginLog;
    }

    @Override
    public void resetOriginalValues() {
        _loginLog.resetOriginalValues();
    }
}
