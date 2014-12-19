package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StaffMemberLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see StaffMemberLocalService
 * @generated
 */
public class StaffMemberLocalServiceWrapper implements StaffMemberLocalService,
    ServiceWrapper<StaffMemberLocalService> {
    private StaffMemberLocalService _staffMemberLocalService;

    public StaffMemberLocalServiceWrapper(
        StaffMemberLocalService staffMemberLocalService) {
        _staffMemberLocalService = staffMemberLocalService;
    }

    /**
    * Adds the staff member to the database. Also notifies the appropriate model listeners.
    *
    * @param staffMember the staff member
    * @return the staff member that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.StaffMember addStaffMember(
        com.ext.portlet.model.StaffMember staffMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _staffMemberLocalService.addStaffMember(staffMember);
    }

    /**
    * Creates a new staff member with the primary key. Does not add the staff member to the database.
    *
    * @param id the primary key for the new staff member
    * @return the new staff member
    */
    @Override
    public com.ext.portlet.model.StaffMember createStaffMember(long id) {
        return _staffMemberLocalService.createStaffMember(id);
    }

    /**
    * Deletes the staff member with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the staff member
    * @return the staff member that was removed
    * @throws PortalException if a staff member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.StaffMember deleteStaffMember(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _staffMemberLocalService.deleteStaffMember(id);
    }

    /**
    * Deletes the staff member from the database. Also notifies the appropriate model listeners.
    *
    * @param staffMember the staff member
    * @return the staff member that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.StaffMember deleteStaffMember(
        com.ext.portlet.model.StaffMember staffMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _staffMemberLocalService.deleteStaffMember(staffMember);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _staffMemberLocalService.dynamicQuery();
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
        return _staffMemberLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _staffMemberLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _staffMemberLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _staffMemberLocalService.dynamicQueryCount(dynamicQuery);
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
        return _staffMemberLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.StaffMember fetchStaffMember(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _staffMemberLocalService.fetchStaffMember(id);
    }

    /**
    * Returns the staff member with the primary key.
    *
    * @param id the primary key of the staff member
    * @return the staff member
    * @throws PortalException if a staff member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.StaffMember getStaffMember(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _staffMemberLocalService.getStaffMember(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _staffMemberLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the staff members.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of staff members
    * @param end the upper bound of the range of staff members (not inclusive)
    * @return the range of staff members
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.StaffMember> getStaffMembers(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _staffMemberLocalService.getStaffMembers(start, end);
    }

    /**
    * Returns the number of staff members.
    *
    * @return the number of staff members
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getStaffMembersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _staffMemberLocalService.getStaffMembersCount();
    }

    /**
    * Updates the staff member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param staffMember the staff member
    * @return the staff member that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.StaffMember updateStaffMember(
        com.ext.portlet.model.StaffMember staffMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _staffMemberLocalService.updateStaffMember(staffMember);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _staffMemberLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _staffMemberLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _staffMemberLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.StaffMember> getStaffMembersByCategoryId(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _staffMemberLocalService.getStaffMembersByCategoryId(categoryId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public StaffMemberLocalService getWrappedStaffMemberLocalService() {
        return _staffMemberLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedStaffMemberLocalService(
        StaffMemberLocalService staffMemberLocalService) {
        _staffMemberLocalService = staffMemberLocalService;
    }

    @Override
    public StaffMemberLocalService getWrappedService() {
        return _staffMemberLocalService;
    }

    @Override
    public void setWrappedService(
        StaffMemberLocalService staffMemberLocalService) {
        _staffMemberLocalService = staffMemberLocalService;
    }
}
