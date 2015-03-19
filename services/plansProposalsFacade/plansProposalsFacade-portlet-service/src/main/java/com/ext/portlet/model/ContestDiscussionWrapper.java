package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestDiscussion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestDiscussion
 * @generated
 */
public class ContestDiscussionWrapper implements ContestDiscussion,
    ModelWrapper<ContestDiscussion> {
    private ContestDiscussion _contestDiscussion;

    public ContestDiscussionWrapper(ContestDiscussion contestDiscussion) {
        _contestDiscussion = contestDiscussion;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestDiscussion.class;
    }

    @Override
    public String getModelClassName() {
        return ContestDiscussion.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("DiscussionId", getDiscussionId());
        attributes.put("ContestId", getContestId());
        attributes.put("Tab", getTab());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long DiscussionId = (Long) attributes.get("DiscussionId");

        if (DiscussionId != null) {
            setDiscussionId(DiscussionId);
        }

        Long ContestId = (Long) attributes.get("ContestId");

        if (ContestId != null) {
            setContestId(ContestId);
        }

        String Tab = (String) attributes.get("Tab");

        if (Tab != null) {
            setTab(Tab);
        }
    }

    /**
    * Returns the primary key of this contest discussion.
    *
    * @return the primary key of this contest discussion
    */
    @Override
    public long getPrimaryKey() {
        return _contestDiscussion.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest discussion.
    *
    * @param primaryKey the primary key of this contest discussion
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contestDiscussion.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the discussion ID of this contest discussion.
    *
    * @return the discussion ID of this contest discussion
    */
    @Override
    public long getDiscussionId() {
        return _contestDiscussion.getDiscussionId();
    }

    /**
    * Sets the discussion ID of this contest discussion.
    *
    * @param DiscussionId the discussion ID of this contest discussion
    */
    @Override
    public void setDiscussionId(long DiscussionId) {
        _contestDiscussion.setDiscussionId(DiscussionId);
    }

    /**
    * Returns the contest ID of this contest discussion.
    *
    * @return the contest ID of this contest discussion
    */
    @Override
    public long getContestId() {
        return _contestDiscussion.getContestId();
    }

    /**
    * Sets the contest ID of this contest discussion.
    *
    * @param ContestId the contest ID of this contest discussion
    */
    @Override
    public void setContestId(long ContestId) {
        _contestDiscussion.setContestId(ContestId);
    }

    /**
    * Returns the tab of this contest discussion.
    *
    * @return the tab of this contest discussion
    */
    @Override
    public java.lang.String getTab() {
        return _contestDiscussion.getTab();
    }

    /**
    * Sets the tab of this contest discussion.
    *
    * @param Tab the tab of this contest discussion
    */
    @Override
    public void setTab(java.lang.String Tab) {
        _contestDiscussion.setTab(Tab);
    }

    @Override
    public boolean isNew() {
        return _contestDiscussion.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestDiscussion.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestDiscussion.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestDiscussion.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestDiscussion.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestDiscussion.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestDiscussion.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestDiscussion.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestDiscussion.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestDiscussion.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestDiscussion.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestDiscussionWrapper((ContestDiscussion) _contestDiscussion.clone());
    }

    @Override
    public int compareTo(ContestDiscussion contestDiscussion) {
        return _contestDiscussion.compareTo(contestDiscussion);
    }

    @Override
    public int hashCode() {
        return _contestDiscussion.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ContestDiscussion> toCacheModel() {
        return _contestDiscussion.toCacheModel();
    }

    @Override
    public ContestDiscussion toEscapedModel() {
        return new ContestDiscussionWrapper(_contestDiscussion.toEscapedModel());
    }

    @Override
    public ContestDiscussion toUnescapedModel() {
        return new ContestDiscussionWrapper(_contestDiscussion.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestDiscussion.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestDiscussion.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestDiscussion.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestDiscussionWrapper)) {
            return false;
        }

        ContestDiscussionWrapper contestDiscussionWrapper = (ContestDiscussionWrapper) obj;

        if (Validator.equals(_contestDiscussion,
                    contestDiscussionWrapper._contestDiscussion)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestDiscussion getWrappedContestDiscussion() {
        return _contestDiscussion;
    }

    @Override
    public ContestDiscussion getWrappedModel() {
        return _contestDiscussion;
    }

    @Override
    public void resetOriginalValues() {
        _contestDiscussion.resetOriginalValues();
    }
}
