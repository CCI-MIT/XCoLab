package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestTeamMemberLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestTeamMemberLocalService
 * @generated
 */
public class ContestTeamMemberLocalServiceWrapper
    implements ContestTeamMemberLocalService,
        ServiceWrapper<ContestTeamMemberLocalService> {
    private ContestTeamMemberLocalService _contestTeamMemberLocalService;

    public ContestTeamMemberLocalServiceWrapper(
        ContestTeamMemberLocalService contestTeamMemberLocalService) {
        _contestTeamMemberLocalService = contestTeamMemberLocalService;
    }

    /**
    * Adds the contest team member to the database. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMember the contest team member
    * @return the contest team member that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestTeamMember addContestTeamMember(
        com.ext.portlet.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.addContestTeamMember(contestTeamMember);
    }

    /**
    * Creates a new contest team member with the primary key. Does not add the contest team member to the database.
    *
    * @param id the primary key for the new contest team member
    * @return the new contest team member
    */
    public com.ext.portlet.model.ContestTeamMember createContestTeamMember(
        long id) {
        return _contestTeamMemberLocalService.createContestTeamMember(id);
    }

    /**
    * Deletes the contest team member with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest team member
    * @throws PortalException if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteContestTeamMember(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestTeamMemberLocalService.deleteContestTeamMember(id);
    }

    /**
    * Deletes the contest team member from the database. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMember the contest team member
    * @throws SystemException if a system exception occurred
    */
    public void deleteContestTeamMember(
        com.ext.portlet.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestTeamMemberLocalService.deleteContestTeamMember(contestTeamMember);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.ContestTeamMember fetchContestTeamMember(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.fetchContestTeamMember(id);
    }

    /**
    * Returns the contest team member with the primary key.
    *
    * @param id the primary key of the contest team member
    * @return the contest team member
    * @throws PortalException if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestTeamMember getContestTeamMember(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.getContestTeamMember(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest team members.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest team members
    * @param end the upper bound of the range of contest team members (not inclusive)
    * @return the range of contest team members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestTeamMember> getContestTeamMembers(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.getContestTeamMembers(start, end);
    }

    /**
    * Returns the number of contest team members.
    *
    * @return the number of contest team members
    * @throws SystemException if a system exception occurred
    */
    public int getContestTeamMembersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.getContestTeamMembersCount();
    }

    /**
    * Updates the contest team member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMember the contest team member
    * @return the contest team member that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestTeamMember updateContestTeamMember(
        com.ext.portlet.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.updateContestTeamMember(contestTeamMember);
    }

    /**
    * Updates the contest team member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMember the contest team member
    * @param merge whether to merge the contest team member with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contest team member that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestTeamMember updateContestTeamMember(
        com.ext.portlet.model.ContestTeamMember contestTeamMember, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.updateContestTeamMember(contestTeamMember,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _contestTeamMemberLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestTeamMemberLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.model.ContestTeamMember addContestTeamMember(
        java.lang.Long userId, java.lang.Long contestPk, java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.addContestTeamMember(userId,
            contestPk, role);
    }

    public java.util.List<com.ext.portlet.model.ContestTeamMember> findForContest(
        java.lang.Long contestPk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.findForContest(contestPk);
    }

    public void store(com.ext.portlet.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestTeamMemberLocalService.store(contestTeamMember);
    }

    public void delete(
        com.ext.portlet.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestTeamMemberLocalService.delete(contestTeamMember);
    }

    public com.liferay.portal.model.User getUser(
        com.ext.portlet.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.getUser(contestTeamMember);
    }

    public com.ext.portlet.model.Contest getContest(
        com.ext.portlet.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberLocalService.getContest(contestTeamMember);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestTeamMemberLocalService getWrappedContestTeamMemberLocalService() {
        return _contestTeamMemberLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestTeamMemberLocalService(
        ContestTeamMemberLocalService contestTeamMemberLocalService) {
        _contestTeamMemberLocalService = contestTeamMemberLocalService;
    }

    public ContestTeamMemberLocalService getWrappedService() {
        return _contestTeamMemberLocalService;
    }

    public void setWrappedService(
        ContestTeamMemberLocalService contestTeamMemberLocalService) {
        _contestTeamMemberLocalService = contestTeamMemberLocalService;
    }
}
