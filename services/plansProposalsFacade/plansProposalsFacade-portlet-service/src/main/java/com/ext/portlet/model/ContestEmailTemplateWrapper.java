package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestEmailTemplate}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestEmailTemplate
 * @generated
 */
public class ContestEmailTemplateWrapper implements ContestEmailTemplate,
    ModelWrapper<ContestEmailTemplate> {
    private ContestEmailTemplate _contestEmailTemplate;

    public ContestEmailTemplateWrapper(
        ContestEmailTemplate contestEmailTemplate) {
        _contestEmailTemplate = contestEmailTemplate;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestEmailTemplate.class;
    }

    @Override
    public String getModelClassName() {
        return ContestEmailTemplate.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("type", getType());
        attributes.put("subject", getSubject());
        attributes.put("header", getHeader());
        attributes.put("footer", getFooter());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String type = (String) attributes.get("type");

        if (type != null) {
            setType(type);
        }

        String subject = (String) attributes.get("subject");

        if (subject != null) {
            setSubject(subject);
        }

        String header = (String) attributes.get("header");

        if (header != null) {
            setHeader(header);
        }

        String footer = (String) attributes.get("footer");

        if (footer != null) {
            setFooter(footer);
        }
    }

    /**
    * Returns the primary key of this contest email template.
    *
    * @return the primary key of this contest email template
    */
    @Override
    public java.lang.String getPrimaryKey() {
        return _contestEmailTemplate.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest email template.
    *
    * @param primaryKey the primary key of this contest email template
    */
    @Override
    public void setPrimaryKey(java.lang.String primaryKey) {
        _contestEmailTemplate.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the type of this contest email template.
    *
    * @return the type of this contest email template
    */
    @Override
    public java.lang.String getType() {
        return _contestEmailTemplate.getType();
    }

    /**
    * Sets the type of this contest email template.
    *
    * @param type the type of this contest email template
    */
    @Override
    public void setType(java.lang.String type) {
        _contestEmailTemplate.setType(type);
    }

    /**
    * Returns the subject of this contest email template.
    *
    * @return the subject of this contest email template
    */
    @Override
    public java.lang.String getSubject() {
        return _contestEmailTemplate.getSubject();
    }

    /**
    * Sets the subject of this contest email template.
    *
    * @param subject the subject of this contest email template
    */
    @Override
    public void setSubject(java.lang.String subject) {
        _contestEmailTemplate.setSubject(subject);
    }

    /**
    * Returns the header of this contest email template.
    *
    * @return the header of this contest email template
    */
    @Override
    public java.lang.String getHeader() {
        return _contestEmailTemplate.getHeader();
    }

    /**
    * Sets the header of this contest email template.
    *
    * @param header the header of this contest email template
    */
    @Override
    public void setHeader(java.lang.String header) {
        _contestEmailTemplate.setHeader(header);
    }

    /**
    * Returns the footer of this contest email template.
    *
    * @return the footer of this contest email template
    */
    @Override
    public java.lang.String getFooter() {
        return _contestEmailTemplate.getFooter();
    }

    /**
    * Sets the footer of this contest email template.
    *
    * @param footer the footer of this contest email template
    */
    @Override
    public void setFooter(java.lang.String footer) {
        _contestEmailTemplate.setFooter(footer);
    }

    @Override
    public boolean isNew() {
        return _contestEmailTemplate.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestEmailTemplate.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestEmailTemplate.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestEmailTemplate.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestEmailTemplate.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestEmailTemplate.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestEmailTemplate.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestEmailTemplate.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestEmailTemplate.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestEmailTemplate.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestEmailTemplate.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestEmailTemplateWrapper((ContestEmailTemplate) _contestEmailTemplate.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.ContestEmailTemplate contestEmailTemplate) {
        return _contestEmailTemplate.compareTo(contestEmailTemplate);
    }

    @Override
    public int hashCode() {
        return _contestEmailTemplate.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ContestEmailTemplate> toCacheModel() {
        return _contestEmailTemplate.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ContestEmailTemplate toEscapedModel() {
        return new ContestEmailTemplateWrapper(_contestEmailTemplate.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ContestEmailTemplate toUnescapedModel() {
        return new ContestEmailTemplateWrapper(_contestEmailTemplate.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestEmailTemplate.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestEmailTemplate.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestEmailTemplate.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestEmailTemplateWrapper)) {
            return false;
        }

        ContestEmailTemplateWrapper contestEmailTemplateWrapper = (ContestEmailTemplateWrapper) obj;

        if (Validator.equals(_contestEmailTemplate,
                    contestEmailTemplateWrapper._contestEmailTemplate)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestEmailTemplate getWrappedContestEmailTemplate() {
        return _contestEmailTemplate;
    }

    @Override
    public ContestEmailTemplate getWrappedModel() {
        return _contestEmailTemplate;
    }

    @Override
    public void resetOriginalValues() {
        _contestEmailTemplate.resetOriginalValues();
    }
}
