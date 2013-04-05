package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link EmailList}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       EmailList
 * @generated
 */
public class EmailListWrapper implements EmailList, ModelWrapper<EmailList> {
    private EmailList _emailList;

    public EmailListWrapper(EmailList emailList) {
        _emailList = emailList;
    }

    public Class<?> getModelClass() {
        return EmailList.class;
    }

    public String getModelClassName() {
        return EmailList.class.getName();
    }

    /**
    * Returns the primary key of this email list.
    *
    * @return the primary key of this email list
    */
    public long getPrimaryKey() {
        return _emailList.getPrimaryKey();
    }

    /**
    * Sets the primary key of this email list.
    *
    * @param primaryKey the primary key of this email list
    */
    public void setPrimaryKey(long primaryKey) {
        _emailList.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this email list.
    *
    * @return the ID of this email list
    */
    public long getId() {
        return _emailList.getId();
    }

    /**
    * Sets the ID of this email list.
    *
    * @param id the ID of this email list
    */
    public void setId(long id) {
        _emailList.setId(id);
    }

    /**
    * Returns the name of this email list.
    *
    * @return the name of this email list
    */
    public java.lang.String getName() {
        return _emailList.getName();
    }

    /**
    * Sets the name of this email list.
    *
    * @param name the name of this email list
    */
    public void setName(java.lang.String name) {
        _emailList.setName(name);
    }

    /**
    * Returns the email of this email list.
    *
    * @return the email of this email list
    */
    public java.lang.String getEmail() {
        return _emailList.getEmail();
    }

    /**
    * Sets the email of this email list.
    *
    * @param email the email of this email list
    */
    public void setEmail(java.lang.String email) {
        _emailList.setEmail(email);
    }

    public boolean isNew() {
        return _emailList.isNew();
    }

    public void setNew(boolean n) {
        _emailList.setNew(n);
    }

    public boolean isCachedModel() {
        return _emailList.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _emailList.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _emailList.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _emailList.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _emailList.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _emailList.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _emailList.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new EmailListWrapper((EmailList) _emailList.clone());
    }

    public int compareTo(EmailList emailList) {
        return _emailList.compareTo(emailList);
    }

    @Override
    public int hashCode() {
        return _emailList.hashCode();
    }

    public com.liferay.portal.model.CacheModel<EmailList> toCacheModel() {
        return _emailList.toCacheModel();
    }

    public EmailList toEscapedModel() {
        return new EmailListWrapper(_emailList.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _emailList.toString();
    }

    public java.lang.String toXmlString() {
        return _emailList.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _emailList.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public EmailList getWrappedEmailList() {
        return _emailList;
    }

    public EmailList getWrappedModel() {
        return _emailList;
    }

    public void resetOriginalValues() {
        _emailList.resetOriginalValues();
    }
}
