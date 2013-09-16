package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalAttribute}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalAttribute
 * @generated
 */
public class ProposalAttributeWrapper implements ProposalAttribute,
    ModelWrapper<ProposalAttribute> {
    private ProposalAttribute _proposalAttribute;

    public ProposalAttributeWrapper(ProposalAttribute proposalAttribute) {
        _proposalAttribute = proposalAttribute;
    }

    public Class<?> getModelClass() {
        return ProposalAttribute.class;
    }

    public String getModelClassName() {
        return ProposalAttribute.class.getName();
    }

    /**
    * Returns the primary key of this proposal attribute.
    *
    * @return the primary key of this proposal attribute
    */
    public com.ext.portlet.service.persistence.ProposalAttributePK getPrimaryKey() {
        return _proposalAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this proposal attribute.
    *
    * @param primaryKey the primary key of this proposal attribute
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.ProposalAttributePK primaryKey) {
        _proposalAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the proposal ID of this proposal attribute.
    *
    * @return the proposal ID of this proposal attribute
    */
    public long getProposalId() {
        return _proposalAttribute.getProposalId();
    }

    /**
    * Sets the proposal ID of this proposal attribute.
    *
    * @param proposalId the proposal ID of this proposal attribute
    */
    public void setProposalId(long proposalId) {
        _proposalAttribute.setProposalId(proposalId);
    }

    /**
    * Returns the version of this proposal attribute.
    *
    * @return the version of this proposal attribute
    */
    public int getVersion() {
        return _proposalAttribute.getVersion();
    }

    /**
    * Sets the version of this proposal attribute.
    *
    * @param version the version of this proposal attribute
    */
    public void setVersion(int version) {
        _proposalAttribute.setVersion(version);
    }

    /**
    * Returns the name of this proposal attribute.
    *
    * @return the name of this proposal attribute
    */
    public java.lang.String getName() {
        return _proposalAttribute.getName();
    }

    /**
    * Sets the name of this proposal attribute.
    *
    * @param name the name of this proposal attribute
    */
    public void setName(java.lang.String name) {
        _proposalAttribute.setName(name);
    }

    /**
    * Returns the additional ID of this proposal attribute.
    *
    * @return the additional ID of this proposal attribute
    */
    public long getAdditionalId() {
        return _proposalAttribute.getAdditionalId();
    }

    /**
    * Sets the additional ID of this proposal attribute.
    *
    * @param additionalId the additional ID of this proposal attribute
    */
    public void setAdditionalId(long additionalId) {
        _proposalAttribute.setAdditionalId(additionalId);
    }

    /**
    * Returns the numeric value of this proposal attribute.
    *
    * @return the numeric value of this proposal attribute
    */
    public long getNumericValue() {
        return _proposalAttribute.getNumericValue();
    }

    /**
    * Sets the numeric value of this proposal attribute.
    *
    * @param numericValue the numeric value of this proposal attribute
    */
    public void setNumericValue(long numericValue) {
        _proposalAttribute.setNumericValue(numericValue);
    }

    /**
    * Returns the string value of this proposal attribute.
    *
    * @return the string value of this proposal attribute
    */
    public java.lang.String getStringValue() {
        return _proposalAttribute.getStringValue();
    }

    /**
    * Sets the string value of this proposal attribute.
    *
    * @param stringValue the string value of this proposal attribute
    */
    public void setStringValue(java.lang.String stringValue) {
        _proposalAttribute.setStringValue(stringValue);
    }

    /**
    * Returns the real value of this proposal attribute.
    *
    * @return the real value of this proposal attribute
    */
    public double getRealValue() {
        return _proposalAttribute.getRealValue();
    }

    /**
    * Sets the real value of this proposal attribute.
    *
    * @param realValue the real value of this proposal attribute
    */
    public void setRealValue(double realValue) {
        _proposalAttribute.setRealValue(realValue);
    }

    public boolean isNew() {
        return _proposalAttribute.isNew();
    }

    public void setNew(boolean n) {
        _proposalAttribute.setNew(n);
    }

    public boolean isCachedModel() {
        return _proposalAttribute.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _proposalAttribute.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _proposalAttribute.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _proposalAttribute.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _proposalAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _proposalAttribute.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _proposalAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ProposalAttributeWrapper((ProposalAttribute) _proposalAttribute.clone());
    }

    public int compareTo(ProposalAttribute proposalAttribute) {
        return _proposalAttribute.compareTo(proposalAttribute);
    }

    @Override
    public int hashCode() {
        return _proposalAttribute.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ProposalAttribute> toCacheModel() {
        return _proposalAttribute.toCacheModel();
    }

    public ProposalAttribute toEscapedModel() {
        return new ProposalAttributeWrapper(_proposalAttribute.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _proposalAttribute.toString();
    }

    public java.lang.String toXmlString() {
        return _proposalAttribute.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalAttribute.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ProposalAttribute getWrappedProposalAttribute() {
        return _proposalAttribute;
    }

    public ProposalAttribute getWrappedModel() {
        return _proposalAttribute;
    }

    public void resetOriginalValues() {
        _proposalAttribute.resetOriginalValues();
    }
}
