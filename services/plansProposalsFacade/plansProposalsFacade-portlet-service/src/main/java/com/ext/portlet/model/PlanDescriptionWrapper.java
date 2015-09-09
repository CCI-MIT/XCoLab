package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanDescription}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanDescription
 * @generated
 */
public class PlanDescriptionWrapper implements PlanDescription,
    ModelWrapper<PlanDescription> {
    private PlanDescription _planDescription;

    public PlanDescriptionWrapper(PlanDescription planDescription) {
        _planDescription = planDescription;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanDescription.class;
    }

    @Override
    public String getModelClassName() {
        return PlanDescription.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("planId", getPlanId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("version", getVersion());
        attributes.put("planVersion", getPlanVersion());
        attributes.put("created", getCreated());
        attributes.put("updateAuthorId", getUpdateAuthorId());
        attributes.put("image", getImage());
        attributes.put("pitch", getPitch());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Long version = (Long) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        Long planVersion = (Long) attributes.get("planVersion");

        if (planVersion != null) {
            setPlanVersion(planVersion);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Long updateAuthorId = (Long) attributes.get("updateAuthorId");

        if (updateAuthorId != null) {
            setUpdateAuthorId(updateAuthorId);
        }

        Long image = (Long) attributes.get("image");

        if (image != null) {
            setImage(image);
        }

        String pitch = (String) attributes.get("pitch");

        if (pitch != null) {
            setPitch(pitch);
        }
    }

    /**
    * Returns the primary key of this plan description.
    *
    * @return the primary key of this plan description
    */
    @Override
    public long getPrimaryKey() {
        return _planDescription.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan description.
    *
    * @param primaryKey the primary key of this plan description
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _planDescription.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan description.
    *
    * @return the ID of this plan description
    */
    @Override
    public long getId() {
        return _planDescription.getId();
    }

    /**
    * Sets the ID of this plan description.
    *
    * @param id the ID of this plan description
    */
    @Override
    public void setId(long id) {
        _planDescription.setId(id);
    }

    /**
    * Returns the plan ID of this plan description.
    *
    * @return the plan ID of this plan description
    */
    @Override
    public long getPlanId() {
        return _planDescription.getPlanId();
    }

    /**
    * Sets the plan ID of this plan description.
    *
    * @param planId the plan ID of this plan description
    */
    @Override
    public void setPlanId(long planId) {
        _planDescription.setPlanId(planId);
    }

    /**
    * Returns the name of this plan description.
    *
    * @return the name of this plan description
    */
    @Override
    public java.lang.String getName() {
        return _planDescription.getName();
    }

    /**
    * Sets the name of this plan description.
    *
    * @param name the name of this plan description
    */
    @Override
    public void setName(java.lang.String name) {
        _planDescription.setName(name);
    }

    /**
    * Returns the description of this plan description.
    *
    * @return the description of this plan description
    */
    @Override
    public java.lang.String getDescription() {
        return _planDescription.getDescription();
    }

    /**
    * Sets the description of this plan description.
    *
    * @param description the description of this plan description
    */
    @Override
    public void setDescription(java.lang.String description) {
        _planDescription.setDescription(description);
    }

    /**
    * Returns the version of this plan description.
    *
    * @return the version of this plan description
    */
    @Override
    public long getVersion() {
        return _planDescription.getVersion();
    }

    /**
    * Sets the version of this plan description.
    *
    * @param version the version of this plan description
    */
    @Override
    public void setVersion(long version) {
        _planDescription.setVersion(version);
    }

    /**
    * Returns the plan version of this plan description.
    *
    * @return the plan version of this plan description
    */
    @Override
    public long getPlanVersion() {
        return _planDescription.getPlanVersion();
    }

    /**
    * Sets the plan version of this plan description.
    *
    * @param planVersion the plan version of this plan description
    */
    @Override
    public void setPlanVersion(long planVersion) {
        _planDescription.setPlanVersion(planVersion);
    }

    /**
    * Returns the created of this plan description.
    *
    * @return the created of this plan description
    */
    @Override
    public java.util.Date getCreated() {
        return _planDescription.getCreated();
    }

    /**
    * Sets the created of this plan description.
    *
    * @param created the created of this plan description
    */
    @Override
    public void setCreated(java.util.Date created) {
        _planDescription.setCreated(created);
    }

    /**
    * Returns the update author ID of this plan description.
    *
    * @return the update author ID of this plan description
    */
    @Override
    public long getUpdateAuthorId() {
        return _planDescription.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan description.
    *
    * @param updateAuthorId the update author ID of this plan description
    */
    @Override
    public void setUpdateAuthorId(long updateAuthorId) {
        _planDescription.setUpdateAuthorId(updateAuthorId);
    }

    /**
    * Returns the image of this plan description.
    *
    * @return the image of this plan description
    */
    @Override
    public long getImage() {
        return _planDescription.getImage();
    }

    /**
    * Sets the image of this plan description.
    *
    * @param image the image of this plan description
    */
    @Override
    public void setImage(long image) {
        _planDescription.setImage(image);
    }

    /**
    * Returns the pitch of this plan description.
    *
    * @return the pitch of this plan description
    */
    @Override
    public java.lang.String getPitch() {
        return _planDescription.getPitch();
    }

    /**
    * Sets the pitch of this plan description.
    *
    * @param pitch the pitch of this plan description
    */
    @Override
    public void setPitch(java.lang.String pitch) {
        _planDescription.setPitch(pitch);
    }

    @Override
    public boolean isNew() {
        return _planDescription.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planDescription.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planDescription.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planDescription.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planDescription.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planDescription.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planDescription.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planDescription.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planDescription.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planDescription.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planDescription.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanDescriptionWrapper((PlanDescription) _planDescription.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.PlanDescription planDescription) {
        return _planDescription.compareTo(planDescription);
    }

    @Override
    public int hashCode() {
        return _planDescription.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.PlanDescription> toCacheModel() {
        return _planDescription.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.PlanDescription toEscapedModel() {
        return new PlanDescriptionWrapper(_planDescription.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.PlanDescription toUnescapedModel() {
        return new PlanDescriptionWrapper(_planDescription.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planDescription.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planDescription.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planDescription.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanDescriptionWrapper)) {
            return false;
        }

        PlanDescriptionWrapper planDescriptionWrapper = (PlanDescriptionWrapper) obj;

        if (Validator.equals(_planDescription,
                    planDescriptionWrapper._planDescription)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanDescription getWrappedPlanDescription() {
        return _planDescription;
    }

    @Override
    public PlanDescription getWrappedModel() {
        return _planDescription;
    }

    @Override
    public void resetOriginalValues() {
        _planDescription.resetOriginalValues();
    }
}
