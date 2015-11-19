package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SpamReport}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SpamReport
 * @generated
 */
public class SpamReportWrapper implements SpamReport, ModelWrapper<SpamReport> {
    private SpamReport _spamReport;

    public SpamReportWrapper(SpamReport spamReport) {
        _spamReport = spamReport;
    }

    @Override
    public Class<?> getModelClass() {
        return SpamReport.class;
    }

    @Override
    public String getModelClassName() {
        return SpamReport.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id_", getId_());
        attributes.put("spamUserId", getSpamUserId());
        attributes.put("reporterUserId", getReporterUserId());
        attributes.put("discussionMessageId", getDiscussionMessageId());
        attributes.put("isAdminReport", getIsAdminReport());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id_ = (Long) attributes.get("id_");

        if (id_ != null) {
            setId_(id_);
        }

        Long spamUserId = (Long) attributes.get("spamUserId");

        if (spamUserId != null) {
            setSpamUserId(spamUserId);
        }

        Long reporterUserId = (Long) attributes.get("reporterUserId");

        if (reporterUserId != null) {
            setReporterUserId(reporterUserId);
        }

        Long discussionMessageId = (Long) attributes.get("discussionMessageId");

        if (discussionMessageId != null) {
            setDiscussionMessageId(discussionMessageId);
        }

        Boolean isAdminReport = (Boolean) attributes.get("isAdminReport");

        if (isAdminReport != null) {
            setIsAdminReport(isAdminReport);
        }
    }

    /**
    * Returns the primary key of this spam report.
    *
    * @return the primary key of this spam report
    */
    @Override
    public long getPrimaryKey() {
        return _spamReport.getPrimaryKey();
    }

    /**
    * Sets the primary key of this spam report.
    *
    * @param primaryKey the primary key of this spam report
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _spamReport.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the id_ of this spam report.
    *
    * @return the id_ of this spam report
    */
    @Override
    public long getId_() {
        return _spamReport.getId_();
    }

    /**
    * Sets the id_ of this spam report.
    *
    * @param id_ the id_ of this spam report
    */
    @Override
    public void setId_(long id_) {
        _spamReport.setId_(id_);
    }

    /**
    * Returns the spam user ID of this spam report.
    *
    * @return the spam user ID of this spam report
    */
    @Override
    public long getSpamUserId() {
        return _spamReport.getSpamUserId();
    }

    /**
    * Sets the spam user ID of this spam report.
    *
    * @param spamUserId the spam user ID of this spam report
    */
    @Override
    public void setSpamUserId(long spamUserId) {
        _spamReport.setSpamUserId(spamUserId);
    }

    /**
    * Returns the spam user uuid of this spam report.
    *
    * @return the spam user uuid of this spam report
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getSpamUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _spamReport.getSpamUserUuid();
    }

    /**
    * Sets the spam user uuid of this spam report.
    *
    * @param spamUserUuid the spam user uuid of this spam report
    */
    @Override
    public void setSpamUserUuid(java.lang.String spamUserUuid) {
        _spamReport.setSpamUserUuid(spamUserUuid);
    }

    /**
    * Returns the reporter user ID of this spam report.
    *
    * @return the reporter user ID of this spam report
    */
    @Override
    public long getReporterUserId() {
        return _spamReport.getReporterUserId();
    }

    /**
    * Sets the reporter user ID of this spam report.
    *
    * @param reporterUserId the reporter user ID of this spam report
    */
    @Override
    public void setReporterUserId(long reporterUserId) {
        _spamReport.setReporterUserId(reporterUserId);
    }

    /**
    * Returns the reporter user uuid of this spam report.
    *
    * @return the reporter user uuid of this spam report
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getReporterUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _spamReport.getReporterUserUuid();
    }

    /**
    * Sets the reporter user uuid of this spam report.
    *
    * @param reporterUserUuid the reporter user uuid of this spam report
    */
    @Override
    public void setReporterUserUuid(java.lang.String reporterUserUuid) {
        _spamReport.setReporterUserUuid(reporterUserUuid);
    }

    /**
    * Returns the discussion message ID of this spam report.
    *
    * @return the discussion message ID of this spam report
    */
    @Override
    public long getDiscussionMessageId() {
        return _spamReport.getDiscussionMessageId();
    }

    /**
    * Sets the discussion message ID of this spam report.
    *
    * @param discussionMessageId the discussion message ID of this spam report
    */
    @Override
    public void setDiscussionMessageId(long discussionMessageId) {
        _spamReport.setDiscussionMessageId(discussionMessageId);
    }

    /**
    * Returns the is admin report of this spam report.
    *
    * @return the is admin report of this spam report
    */
    @Override
    public boolean getIsAdminReport() {
        return _spamReport.getIsAdminReport();
    }

    /**
    * Returns <code>true</code> if this spam report is is admin report.
    *
    * @return <code>true</code> if this spam report is is admin report; <code>false</code> otherwise
    */
    @Override
    public boolean isIsAdminReport() {
        return _spamReport.isIsAdminReport();
    }

    /**
    * Sets whether this spam report is is admin report.
    *
    * @param isAdminReport the is admin report of this spam report
    */
    @Override
    public void setIsAdminReport(boolean isAdminReport) {
        _spamReport.setIsAdminReport(isAdminReport);
    }

    @Override
    public boolean isNew() {
        return _spamReport.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _spamReport.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _spamReport.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _spamReport.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _spamReport.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _spamReport.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _spamReport.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _spamReport.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _spamReport.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _spamReport.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _spamReport.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new SpamReportWrapper((SpamReport) _spamReport.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.SpamReport spamReport) {
        return _spamReport.compareTo(spamReport);
    }

    @Override
    public int hashCode() {
        return _spamReport.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.SpamReport> toCacheModel() {
        return _spamReport.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.SpamReport toEscapedModel() {
        return new SpamReportWrapper(_spamReport.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.SpamReport toUnescapedModel() {
        return new SpamReportWrapper(_spamReport.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _spamReport.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _spamReport.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _spamReport.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SpamReportWrapper)) {
            return false;
        }

        SpamReportWrapper spamReportWrapper = (SpamReportWrapper) obj;

        if (Validator.equals(_spamReport, spamReportWrapper._spamReport)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public SpamReport getWrappedSpamReport() {
        return _spamReport;
    }

    @Override
    public SpamReport getWrappedModel() {
        return _spamReport;
    }

    @Override
    public void resetOriginalValues() {
        _spamReport.resetOriginalValues();
    }
}
