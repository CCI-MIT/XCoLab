package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SocialActivity}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivity
 * @generated
 */
public class SocialActivityWrapper implements SocialActivity,
    ModelWrapper<SocialActivity> {
    private SocialActivity _socialActivity;

    public SocialActivityWrapper(SocialActivity socialActivity) {
        _socialActivity = socialActivity;
    }

    @Override
    public Class<?> getModelClass() {
        return SocialActivity.class;
    }

    @Override
    public String getModelClassName() {
        return SocialActivity.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("activityId", getActivityId());
        attributes.put("userId", getUserId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long activityId = (Long) attributes.get("activityId");

        if (activityId != null) {
            setActivityId(activityId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }
    }

    /**
    * Returns the primary key of this social activity.
    *
    * @return the primary key of this social activity
    */
    @Override
    public long getPrimaryKey() {
        return _socialActivity.getPrimaryKey();
    }

    /**
    * Sets the primary key of this social activity.
    *
    * @param primaryKey the primary key of this social activity
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _socialActivity.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the activity ID of this social activity.
    *
    * @return the activity ID of this social activity
    */
    @Override
    public long getActivityId() {
        return _socialActivity.getActivityId();
    }

    /**
    * Sets the activity ID of this social activity.
    *
    * @param activityId the activity ID of this social activity
    */
    @Override
    public void setActivityId(long activityId) {
        _socialActivity.setActivityId(activityId);
    }

    /**
    * Returns the user ID of this social activity.
    *
    * @return the user ID of this social activity
    */
    @Override
    public long getUserId() {
        return _socialActivity.getUserId();
    }

    /**
    * Sets the user ID of this social activity.
    *
    * @param userId the user ID of this social activity
    */
    @Override
    public void setUserId(long userId) {
        _socialActivity.setUserId(userId);
    }

    /**
    * Returns the user uuid of this social activity.
    *
    * @return the user uuid of this social activity
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _socialActivity.getUserUuid();
    }

    /**
    * Sets the user uuid of this social activity.
    *
    * @param userUuid the user uuid of this social activity
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _socialActivity.setUserUuid(userUuid);
    }

    @Override
    public boolean isNew() {
        return _socialActivity.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _socialActivity.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _socialActivity.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _socialActivity.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _socialActivity.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _socialActivity.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _socialActivity.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _socialActivity.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _socialActivity.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _socialActivity.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _socialActivity.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new SocialActivityWrapper((SocialActivity) _socialActivity.clone());
    }

    @Override
    public int compareTo(SocialActivity socialActivity) {
        return _socialActivity.compareTo(socialActivity);
    }

    @Override
    public int hashCode() {
        return _socialActivity.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<SocialActivity> toCacheModel() {
        return _socialActivity.toCacheModel();
    }

    @Override
    public SocialActivity toEscapedModel() {
        return new SocialActivityWrapper(_socialActivity.toEscapedModel());
    }

    @Override
    public SocialActivity toUnescapedModel() {
        return new SocialActivityWrapper(_socialActivity.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _socialActivity.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _socialActivity.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _socialActivity.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SocialActivityWrapper)) {
            return false;
        }

        SocialActivityWrapper socialActivityWrapper = (SocialActivityWrapper) obj;

        if (Validator.equals(_socialActivity,
                    socialActivityWrapper._socialActivity)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public SocialActivity getWrappedSocialActivity() {
        return _socialActivity;
    }

    @Override
    public SocialActivity getWrappedModel() {
        return _socialActivity;
    }

    @Override
    public void resetOriginalValues() {
        _socialActivity.resetOriginalValues();
    }
}
