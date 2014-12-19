package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ContestTeamMember}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMember
 * @generated
 */
public class ContestTeamMemberWrapper implements ContestTeamMember,
    ModelWrapper<ContestTeamMember> {
    private ContestTeamMember _contestTeamMember;

    public ContestTeamMemberWrapper(ContestTeamMember contestTeamMember) {
        _contestTeamMember = contestTeamMember;
    }

    @Override
    public Class<?> getModelClass() {
        return ContestTeamMember.class;
    }

    @Override
    public String getModelClassName() {
        return ContestTeamMember.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("contestId", getContestId());
        attributes.put("userId", getUserId());
        attributes.put("role", getRole());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long contestId = (Long) attributes.get("contestId");

        if (contestId != null) {
            setContestId(contestId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String role = (String) attributes.get("role");

        if (role != null) {
            setRole(role);
        }
    }

    /**
    * Returns the primary key of this contest team member.
    *
    * @return the primary key of this contest team member
    */
    @Override
    public long getPrimaryKey() {
        return _contestTeamMember.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest team member.
    *
    * @param primaryKey the primary key of this contest team member
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contestTeamMember.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this contest team member.
    *
    * @return the ID of this contest team member
    */
    @Override
    public long getId() {
        return _contestTeamMember.getId();
    }

    /**
    * Sets the ID of this contest team member.
    *
    * @param id the ID of this contest team member
    */
    @Override
    public void setId(long id) {
        _contestTeamMember.setId(id);
    }

    /**
    * Returns the contest ID of this contest team member.
    *
    * @return the contest ID of this contest team member
    */
    @Override
    public long getContestId() {
        return _contestTeamMember.getContestId();
    }

    /**
    * Sets the contest ID of this contest team member.
    *
    * @param contestId the contest ID of this contest team member
    */
    @Override
    public void setContestId(long contestId) {
        _contestTeamMember.setContestId(contestId);
    }

    /**
    * Returns the user ID of this contest team member.
    *
    * @return the user ID of this contest team member
    */
    @Override
    public long getUserId() {
        return _contestTeamMember.getUserId();
    }

    /**
    * Sets the user ID of this contest team member.
    *
    * @param userId the user ID of this contest team member
    */
    @Override
    public void setUserId(long userId) {
        _contestTeamMember.setUserId(userId);
    }

    /**
    * Returns the user uuid of this contest team member.
    *
    * @return the user uuid of this contest team member
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMember.getUserUuid();
    }

    /**
    * Sets the user uuid of this contest team member.
    *
    * @param userUuid the user uuid of this contest team member
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _contestTeamMember.setUserUuid(userUuid);
    }

    /**
    * Returns the role of this contest team member.
    *
    * @return the role of this contest team member
    */
    @Override
    public java.lang.String getRole() {
        return _contestTeamMember.getRole();
    }

    /**
    * Sets the role of this contest team member.
    *
    * @param role the role of this contest team member
    */
    @Override
    public void setRole(java.lang.String role) {
        _contestTeamMember.setRole(role);
    }

    @Override
    public boolean isNew() {
        return _contestTeamMember.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contestTeamMember.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contestTeamMember.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contestTeamMember.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contestTeamMember.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contestTeamMember.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contestTeamMember.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contestTeamMember.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contestTeamMember.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contestTeamMember.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contestTeamMember.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestTeamMemberWrapper((ContestTeamMember) _contestTeamMember.clone());
    }

    @Override
    public int compareTo(ContestTeamMember contestTeamMember) {
        return _contestTeamMember.compareTo(contestTeamMember);
    }

    @Override
    public int hashCode() {
        return _contestTeamMember.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ContestTeamMember> toCacheModel() {
        return _contestTeamMember.toCacheModel();
    }

    @Override
    public ContestTeamMember toEscapedModel() {
        return new ContestTeamMemberWrapper(_contestTeamMember.toEscapedModel());
    }

    @Override
    public ContestTeamMember toUnescapedModel() {
        return new ContestTeamMemberWrapper(_contestTeamMember.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contestTeamMember.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contestTeamMember.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestTeamMember.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestTeamMemberWrapper)) {
            return false;
        }

        ContestTeamMemberWrapper contestTeamMemberWrapper = (ContestTeamMemberWrapper) obj;

        if (Validator.equals(_contestTeamMember,
                    contestTeamMemberWrapper._contestTeamMember)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ContestTeamMember getWrappedContestTeamMember() {
        return _contestTeamMember;
    }

    @Override
    public ContestTeamMember getWrappedModel() {
        return _contestTeamMember;
    }

    @Override
    public void resetOriginalValues() {
        _contestTeamMember.resetOriginalValues();
    }
}
