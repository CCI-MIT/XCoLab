package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactTemplateFocusAreaListLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateFocusAreaListLocalService
 * @generated
 */
public class ImpactTemplateFocusAreaListLocalServiceWrapper
    implements ImpactTemplateFocusAreaListLocalService,
        ServiceWrapper<ImpactTemplateFocusAreaListLocalService> {
    private ImpactTemplateFocusAreaListLocalService _impactTemplateFocusAreaListLocalService;

    public ImpactTemplateFocusAreaListLocalServiceWrapper(
        ImpactTemplateFocusAreaListLocalService impactTemplateFocusAreaListLocalService) {
        _impactTemplateFocusAreaListLocalService = impactTemplateFocusAreaListLocalService;
    }

    /**
    * Adds the impact template focus area list to the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateFocusAreaList the impact template focus area list
    * @return the impact template focus area list that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateFocusAreaList addImpactTemplateFocusAreaList(
        com.ext.portlet.model.ImpactTemplateFocusAreaList impactTemplateFocusAreaList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateFocusAreaListLocalService.addImpactTemplateFocusAreaList(impactTemplateFocusAreaList);
    }

    /**
    * Creates a new impact template focus area list with the primary key. Does not add the impact template focus area list to the database.
    *
    * @param focusAreaListId the primary key for the new impact template focus area list
    * @return the new impact template focus area list
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateFocusAreaList createImpactTemplateFocusAreaList(
        long focusAreaListId) {
        return _impactTemplateFocusAreaListLocalService.createImpactTemplateFocusAreaList(focusAreaListId);
    }

    /**
    * Deletes the impact template focus area list with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaListId the primary key of the impact template focus area list
    * @return the impact template focus area list that was removed
    * @throws PortalException if a impact template focus area list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateFocusAreaList deleteImpactTemplateFocusAreaList(
        long focusAreaListId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateFocusAreaListLocalService.deleteImpactTemplateFocusAreaList(focusAreaListId);
    }

    /**
    * Deletes the impact template focus area list from the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateFocusAreaList the impact template focus area list
    * @return the impact template focus area list that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateFocusAreaList deleteImpactTemplateFocusAreaList(
        com.ext.portlet.model.ImpactTemplateFocusAreaList impactTemplateFocusAreaList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateFocusAreaListLocalService.deleteImpactTemplateFocusAreaList(impactTemplateFocusAreaList);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _impactTemplateFocusAreaListLocalService.dynamicQuery();
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
        return _impactTemplateFocusAreaListLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateFocusAreaListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _impactTemplateFocusAreaListLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateFocusAreaListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _impactTemplateFocusAreaListLocalService.dynamicQuery(dynamicQuery,
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
        return _impactTemplateFocusAreaListLocalService.dynamicQueryCount(dynamicQuery);
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
        return _impactTemplateFocusAreaListLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ImpactTemplateFocusAreaList fetchImpactTemplateFocusAreaList(
        long focusAreaListId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateFocusAreaListLocalService.fetchImpactTemplateFocusAreaList(focusAreaListId);
    }

    /**
    * Returns the impact template focus area list with the primary key.
    *
    * @param focusAreaListId the primary key of the impact template focus area list
    * @return the impact template focus area list
    * @throws PortalException if a impact template focus area list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateFocusAreaList getImpactTemplateFocusAreaList(
        long focusAreaListId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateFocusAreaListLocalService.getImpactTemplateFocusAreaList(focusAreaListId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateFocusAreaListLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the impact template focus area lists.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateFocusAreaListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template focus area lists
    * @param end the upper bound of the range of impact template focus area lists (not inclusive)
    * @return the range of impact template focus area lists
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ImpactTemplateFocusAreaList> getImpactTemplateFocusAreaLists(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateFocusAreaListLocalService.getImpactTemplateFocusAreaLists(start,
            end);
    }

    /**
    * Returns the number of impact template focus area lists.
    *
    * @return the number of impact template focus area lists
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImpactTemplateFocusAreaListsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateFocusAreaListLocalService.getImpactTemplateFocusAreaListsCount();
    }

    /**
    * Updates the impact template focus area list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateFocusAreaList the impact template focus area list
    * @return the impact template focus area list that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateFocusAreaList updateImpactTemplateFocusAreaList(
        com.ext.portlet.model.ImpactTemplateFocusAreaList impactTemplateFocusAreaList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateFocusAreaListLocalService.updateImpactTemplateFocusAreaList(impactTemplateFocusAreaList);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _impactTemplateFocusAreaListLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impactTemplateFocusAreaListLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impactTemplateFocusAreaListLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImpactTemplateFocusAreaListLocalService getWrappedImpactTemplateFocusAreaListLocalService() {
        return _impactTemplateFocusAreaListLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImpactTemplateFocusAreaListLocalService(
        ImpactTemplateFocusAreaListLocalService impactTemplateFocusAreaListLocalService) {
        _impactTemplateFocusAreaListLocalService = impactTemplateFocusAreaListLocalService;
    }

    @Override
    public ImpactTemplateFocusAreaListLocalService getWrappedService() {
        return _impactTemplateFocusAreaListLocalService;
    }

    @Override
    public void setWrappedService(
        ImpactTemplateFocusAreaListLocalService impactTemplateFocusAreaListLocalService) {
        _impactTemplateFocusAreaListLocalService = impactTemplateFocusAreaListLocalService;
    }
}
