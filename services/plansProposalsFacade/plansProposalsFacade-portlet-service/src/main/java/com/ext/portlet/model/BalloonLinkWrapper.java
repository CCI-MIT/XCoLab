package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BalloonLink}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonLink
 * @generated
 */
public class BalloonLinkWrapper implements BalloonLink,
    ModelWrapper<BalloonLink> {
    private BalloonLink _balloonLink;

    public BalloonLinkWrapper(BalloonLink balloonLink) {
        _balloonLink = balloonLink;
    }

    @Override
    public Class<?> getModelClass() {
        return BalloonLink.class;
    }

    @Override
    public String getModelClassName() {
        return BalloonLink.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("uuid", getUuid());
        attributes.put("targetUrl", getTargetUrl());
        attributes.put("visits", getVisits());
        attributes.put("balloonUserUuid", getBalloonUserUuid());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        String targetUrl = (String) attributes.get("targetUrl");

        if (targetUrl != null) {
            setTargetUrl(targetUrl);
        }

        Integer visits = (Integer) attributes.get("visits");

        if (visits != null) {
            setVisits(visits);
        }

        String balloonUserUuid = (String) attributes.get("balloonUserUuid");

        if (balloonUserUuid != null) {
            setBalloonUserUuid(balloonUserUuid);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    /**
    * Returns the primary key of this balloon link.
    *
    * @return the primary key of this balloon link
    */
    @Override
    public java.lang.String getPrimaryKey() {
        return _balloonLink.getPrimaryKey();
    }

    /**
    * Sets the primary key of this balloon link.
    *
    * @param primaryKey the primary key of this balloon link
    */
    @Override
    public void setPrimaryKey(java.lang.String primaryKey) {
        _balloonLink.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the uuid of this balloon link.
    *
    * @return the uuid of this balloon link
    */
    @Override
    public java.lang.String getUuid() {
        return _balloonLink.getUuid();
    }

    /**
    * Sets the uuid of this balloon link.
    *
    * @param uuid the uuid of this balloon link
    */
    @Override
    public void setUuid(java.lang.String uuid) {
        _balloonLink.setUuid(uuid);
    }

    /**
    * Returns the target url of this balloon link.
    *
    * @return the target url of this balloon link
    */
    @Override
    public java.lang.String getTargetUrl() {
        return _balloonLink.getTargetUrl();
    }

    /**
    * Sets the target url of this balloon link.
    *
    * @param targetUrl the target url of this balloon link
    */
    @Override
    public void setTargetUrl(java.lang.String targetUrl) {
        _balloonLink.setTargetUrl(targetUrl);
    }

    /**
    * Returns the visits of this balloon link.
    *
    * @return the visits of this balloon link
    */
    @Override
    public int getVisits() {
        return _balloonLink.getVisits();
    }

    /**
    * Sets the visits of this balloon link.
    *
    * @param visits the visits of this balloon link
    */
    @Override
    public void setVisits(int visits) {
        _balloonLink.setVisits(visits);
    }

    /**
    * Returns the balloon user uuid of this balloon link.
    *
    * @return the balloon user uuid of this balloon link
    */
    @Override
    public java.lang.String getBalloonUserUuid() {
        return _balloonLink.getBalloonUserUuid();
    }

    /**
    * Sets the balloon user uuid of this balloon link.
    *
    * @param balloonUserUuid the balloon user uuid of this balloon link
    */
    @Override
    public void setBalloonUserUuid(java.lang.String balloonUserUuid) {
        _balloonLink.setBalloonUserUuid(balloonUserUuid);
    }

    /**
    * Returns the create date of this balloon link.
    *
    * @return the create date of this balloon link
    */
    @Override
    public java.util.Date getCreateDate() {
        return _balloonLink.getCreateDate();
    }

    /**
    * Sets the create date of this balloon link.
    *
    * @param createDate the create date of this balloon link
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _balloonLink.setCreateDate(createDate);
    }

    @Override
    public boolean isNew() {
        return _balloonLink.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _balloonLink.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _balloonLink.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _balloonLink.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _balloonLink.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _balloonLink.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _balloonLink.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _balloonLink.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _balloonLink.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _balloonLink.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _balloonLink.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new BalloonLinkWrapper((BalloonLink) _balloonLink.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.BalloonLink balloonLink) {
        return _balloonLink.compareTo(balloonLink);
    }

    @Override
    public int hashCode() {
        return _balloonLink.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.BalloonLink> toCacheModel() {
        return _balloonLink.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.BalloonLink toEscapedModel() {
        return new BalloonLinkWrapper(_balloonLink.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.BalloonLink toUnescapedModel() {
        return new BalloonLinkWrapper(_balloonLink.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _balloonLink.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _balloonLink.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _balloonLink.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BalloonLinkWrapper)) {
            return false;
        }

        BalloonLinkWrapper balloonLinkWrapper = (BalloonLinkWrapper) obj;

        if (Validator.equals(_balloonLink, balloonLinkWrapper._balloonLink)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public BalloonLink getWrappedBalloonLink() {
        return _balloonLink;
    }

    @Override
    public BalloonLink getWrappedModel() {
        return _balloonLink;
    }

    @Override
    public void resetOriginalValues() {
        _balloonLink.resetOriginalValues();
    }
}
