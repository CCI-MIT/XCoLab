package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MemberCategoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MemberCategoryLocalService
 * @generated
 */
public class MemberCategoryLocalServiceWrapper
    implements MemberCategoryLocalService,
        ServiceWrapper<MemberCategoryLocalService> {
    private MemberCategoryLocalService _memberCategoryLocalService;

    public MemberCategoryLocalServiceWrapper(
        MemberCategoryLocalService memberCategoryLocalService) {
        _memberCategoryLocalService = memberCategoryLocalService;
    }

    /**
    * Adds the member category to the database. Also notifies the appropriate model listeners.
    *
    * @param memberCategory the member category
    * @return the member category that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MemberCategory addMemberCategory(
        com.ext.portlet.model.MemberCategory memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _memberCategoryLocalService.addMemberCategory(memberCategory);
    }

    /**
    * Creates a new member category with the primary key. Does not add the member category to the database.
    *
    * @param roleId the primary key for the new member category
    * @return the new member category
    */
    @Override
    public com.ext.portlet.model.MemberCategory createMemberCategory(
        long roleId) {
        return _memberCategoryLocalService.createMemberCategory(roleId);
    }

    /**
    * Deletes the member category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param roleId the primary key of the member category
    * @return the member category that was removed
    * @throws PortalException if a member category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MemberCategory deleteMemberCategory(
        long roleId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _memberCategoryLocalService.deleteMemberCategory(roleId);
    }

    /**
    * Deletes the member category from the database. Also notifies the appropriate model listeners.
    *
    * @param memberCategory the member category
    * @return the member category that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MemberCategory deleteMemberCategory(
        com.ext.portlet.model.MemberCategory memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _memberCategoryLocalService.deleteMemberCategory(memberCategory);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _memberCategoryLocalService.dynamicQuery();
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
        return _memberCategoryLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _memberCategoryLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _memberCategoryLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _memberCategoryLocalService.dynamicQueryCount(dynamicQuery);
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
        return _memberCategoryLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.MemberCategory fetchMemberCategory(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _memberCategoryLocalService.fetchMemberCategory(roleId);
    }

    /**
    * Returns the member category with the primary key.
    *
    * @param roleId the primary key of the member category
    * @return the member category
    * @throws PortalException if a member category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MemberCategory getMemberCategory(long roleId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _memberCategoryLocalService.getMemberCategory(roleId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _memberCategoryLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the member categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of member categories
    * @param end the upper bound of the range of member categories (not inclusive)
    * @return the range of member categories
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.MemberCategory> getMemberCategories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _memberCategoryLocalService.getMemberCategories(start, end);
    }

    /**
    * Returns the number of member categories.
    *
    * @return the number of member categories
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMemberCategoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _memberCategoryLocalService.getMemberCategoriesCount();
    }

    /**
    * Updates the member category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param memberCategory the member category
    * @return the member category that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.MemberCategory updateMemberCategory(
        com.ext.portlet.model.MemberCategory memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _memberCategoryLocalService.updateMemberCategory(memberCategory);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _memberCategoryLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _memberCategoryLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _memberCategoryLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public com.ext.portlet.model.MemberCategory getByDisplayName(
        java.lang.String displayName) {
        return _memberCategoryLocalService.getByDisplayName(displayName);
    }

    @Override
    public java.util.List<com.ext.portlet.model.MemberCategory> getVisibleMemberCategories()
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return _memberCategoryLocalService.getVisibleMemberCategories();
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MemberCategoryLocalService getWrappedMemberCategoryLocalService() {
        return _memberCategoryLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMemberCategoryLocalService(
        MemberCategoryLocalService memberCategoryLocalService) {
        _memberCategoryLocalService = memberCategoryLocalService;
    }

    @Override
    public MemberCategoryLocalService getWrappedService() {
        return _memberCategoryLocalService;
    }

    @Override
    public void setWrappedService(
        MemberCategoryLocalService memberCategoryLocalService) {
        _memberCategoryLocalService = memberCategoryLocalService;
    }
}
