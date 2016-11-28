package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestTeamMemberRoleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberRoleLocalService
 * @generated
 */
public class ContestTeamMemberRoleLocalServiceWrapper
    implements ContestTeamMemberRoleLocalService,
        ServiceWrapper<ContestTeamMemberRoleLocalService> {
    private ContestTeamMemberRoleLocalService _contestTeamMemberRoleLocalService;

    public ContestTeamMemberRoleLocalServiceWrapper(
        ContestTeamMemberRoleLocalService contestTeamMemberRoleLocalService) {
        _contestTeamMemberRoleLocalService = contestTeamMemberRoleLocalService;
    }

    /**
    * Adds the contest team member role to the database. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMemberRole the contest team member role
    * @return the contest team member role that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestTeamMemberRole addContestTeamMemberRole(
        com.ext.portlet.model.ContestTeamMemberRole contestTeamMemberRole)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.addContestTeamMemberRole(contestTeamMemberRole);
    }

    /**
    * Creates a new contest team member role with the primary key. Does not add the contest team member role to the database.
    *
    * @param id the primary key for the new contest team member role
    * @return the new contest team member role
    */
    @Override
    public com.ext.portlet.model.ContestTeamMemberRole createContestTeamMemberRole(
        long id) {
        return _contestTeamMemberRoleLocalService.createContestTeamMemberRole(id);
    }

    /**
    * Deletes the contest team member role with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest team member role
    * @return the contest team member role that was removed
    * @throws PortalException if a contest team member role with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestTeamMemberRole deleteContestTeamMemberRole(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.deleteContestTeamMemberRole(id);
    }

    /**
    * Deletes the contest team member role from the database. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMemberRole the contest team member role
    * @return the contest team member role that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestTeamMemberRole deleteContestTeamMemberRole(
        com.ext.portlet.model.ContestTeamMemberRole contestTeamMemberRole)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.deleteContestTeamMemberRole(contestTeamMemberRole);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _contestTeamMemberRoleLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ContestTeamMemberRole fetchContestTeamMemberRole(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.fetchContestTeamMemberRole(id);
    }

    /**
    * Returns the contest team member role with the primary key.
    *
    * @param id the primary key of the contest team member role
    * @return the contest team member role
    * @throws PortalException if a contest team member role with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestTeamMemberRole getContestTeamMemberRole(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.getContestTeamMemberRole(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest team member roles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest team member roles
    * @param end the upper bound of the range of contest team member roles (not inclusive)
    * @return the range of contest team member roles
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ContestTeamMemberRole> getContestTeamMemberRoles(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.getContestTeamMemberRoles(start,
            end);
    }

    /**
    * Returns the number of contest team member roles.
    *
    * @return the number of contest team member roles
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getContestTeamMemberRolesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.getContestTeamMemberRolesCount();
    }

    /**
    * Updates the contest team member role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMemberRole the contest team member role
    * @return the contest team member role that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestTeamMemberRole updateContestTeamMemberRole(
        com.ext.portlet.model.ContestTeamMemberRole contestTeamMemberRole)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestTeamMemberRoleLocalService.updateContestTeamMemberRole(contestTeamMemberRole);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestTeamMemberRoleLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestTeamMemberRoleLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestTeamMemberRoleLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestTeamMemberRoleLocalService getWrappedContestTeamMemberRoleLocalService() {
        return _contestTeamMemberRoleLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestTeamMemberRoleLocalService(
        ContestTeamMemberRoleLocalService contestTeamMemberRoleLocalService) {
        _contestTeamMemberRoleLocalService = contestTeamMemberRoleLocalService;
    }

    @Override
    public ContestTeamMemberRoleLocalService getWrappedService() {
        return _contestTeamMemberRoleLocalService;
    }

    @Override
    public void setWrappedService(
        ContestTeamMemberRoleLocalService contestTeamMemberRoleLocalService) {
        _contestTeamMemberRoleLocalService = contestTeamMemberRoleLocalService;
    }
}
