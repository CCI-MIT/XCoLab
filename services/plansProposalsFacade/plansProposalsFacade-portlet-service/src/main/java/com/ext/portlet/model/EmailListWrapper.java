package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EmailList}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailList
 * @generated
 */
public class EmailListWrapper implements EmailList, ModelWrapper<EmailList> {
    private EmailList _emailList;

    public EmailListWrapper(EmailList emailList) {
        _emailList = emailList;
    }

    @Override
    public Class<?> getModelClass() {
        return EmailList.class;
    }

    @Override
    public String getModelClassName() {
        return EmailList.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("name", getName());
        attributes.put("email", getEmail());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String email = (String) attributes.get("email");

        if (email != null) {
            setEmail(email);
        }
    }

    /**
    * Returns the primary key of this email list.
    *
    * @return the primary key of this email list
    */
    @Override
    public long getPrimaryKey() {
        return _emailList.getPrimaryKey();
    }

    /**
    * Sets the primary key of this email list.
    *
    * @param primaryKey the primary key of this email list
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _emailList.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this email list.
    *
    * @return the ID of this email list
    */
    @Override
    public long getId() {
        return _emailList.getId();
    }

    /**
    * Sets the ID of this email list.
    *
    * @param id the ID of this email list
    */
    @Override
    public void setId(long id) {
        _emailList.setId(id);
    }

    /**
    * Returns the name of this email list.
    *
    * @return the name of this email list
    */
    @Override
    public java.lang.String getName() {
        return _emailList.getName();
    }

    /**
    * Sets the name of this email list.
    *
    * @param name the name of this email list
    */
    @Override
    public void setName(java.lang.String name) {
        _emailList.setName(name);
    }

    /**
    * Returns the email of this email list.
    *
    * @return the email of this email list
    */
    @Override
    public java.lang.String getEmail() {
        return _emailList.getEmail();
    }

    /**
    * Sets the email of this email list.
    *
    * @param email the email of this email list
    */
    @Override
    public void setEmail(java.lang.String email) {
        _emailList.setEmail(email);
    }

    @Override
    public boolean isNew() {
        return _emailList.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _emailList.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _emailList.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _emailList.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _emailList.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _emailList.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _emailList.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _emailList.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _emailList.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _emailList.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _emailList.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new EmailListWrapper((EmailList) _emailList.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.EmailList emailList) {
        return _emailList.compareTo(emailList);
    }

    @Override
    public int hashCode() {
        return _emailList.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.EmailList> toCacheModel() {
        return _emailList.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.EmailList toEscapedModel() {
        return new EmailListWrapper(_emailList.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.EmailList toUnescapedModel() {
        return new EmailListWrapper(_emailList.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _emailList.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _emailList.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _emailList.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EmailListWrapper)) {
            return false;
        }

        EmailListWrapper emailListWrapper = (EmailListWrapper) obj;

        if (Validator.equals(_emailList, emailListWrapper._emailList)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public EmailList getWrappedEmailList() {
        return _emailList;
    }

    @Override
    public EmailList getWrappedModel() {
        return _emailList;
    }

    @Override
    public void resetOriginalValues() {
        _emailList.resetOriginalValues();
    }
}
