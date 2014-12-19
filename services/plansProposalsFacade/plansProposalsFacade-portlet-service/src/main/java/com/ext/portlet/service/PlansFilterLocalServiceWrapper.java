package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlansFilterLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterLocalService
 * @generated
 */
public class PlansFilterLocalServiceWrapper implements PlansFilterLocalService,
    ServiceWrapper<PlansFilterLocalService> {
    private PlansFilterLocalService _plansFilterLocalService;

    public PlansFilterLocalServiceWrapper(
        PlansFilterLocalService plansFilterLocalService) {
        _plansFilterLocalService = plansFilterLocalService;
    }

    /**
    * Adds the plans filter to the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilter the plans filter
    * @return the plans filter that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlansFilter addPlansFilter(
        com.ext.portlet.model.PlansFilter plansFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.addPlansFilter(plansFilter);
    }

    /**
    * Creates a new plans filter with the primary key. Does not add the plans filter to the database.
    *
    * @param plansFilterPK the primary key for the new plans filter
    * @return the new plans filter
    */
    @Override
    public com.ext.portlet.model.PlansFilter createPlansFilter(
        com.ext.portlet.service.persistence.PlansFilterPK plansFilterPK) {
        return _plansFilterLocalService.createPlansFilter(plansFilterPK);
    }

    /**
    * Deletes the plans filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPK the primary key of the plans filter
    * @return the plans filter that was removed
    * @throws PortalException if a plans filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlansFilter deletePlansFilter(
        com.ext.portlet.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.deletePlansFilter(plansFilterPK);
    }

    /**
    * Deletes the plans filter from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilter the plans filter
    * @return the plans filter that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlansFilter deletePlansFilter(
        com.ext.portlet.model.PlansFilter plansFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.deletePlansFilter(plansFilter);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _plansFilterLocalService.dynamicQuery();
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
        return _plansFilterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _plansFilterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _plansFilterLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _plansFilterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _plansFilterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.PlansFilter fetchPlansFilter(
        com.ext.portlet.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.fetchPlansFilter(plansFilterPK);
    }

    /**
    * Returns the plans filter with the primary key.
    *
    * @param plansFilterPK the primary key of the plans filter
    * @return the plans filter
    * @throws PortalException if a plans filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlansFilter getPlansFilter(
        com.ext.portlet.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.getPlansFilter(plansFilterPK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plans filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plans filters
    * @param end the upper bound of the range of plans filters (not inclusive)
    * @return the range of plans filters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlansFilter> getPlansFilters(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.getPlansFilters(start, end);
    }

    /**
    * Returns the number of plans filters.
    *
    * @return the number of plans filters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPlansFiltersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.getPlansFiltersCount();
    }

    /**
    * Updates the plans filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansFilter the plans filter
    * @return the plans filter that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlansFilter updatePlansFilter(
        com.ext.portlet.model.PlansFilter plansFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.updatePlansFilter(plansFilter);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _plansFilterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _plansFilterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _plansFilterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlansFilterLocalService getWrappedPlansFilterLocalService() {
        return _plansFilterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlansFilterLocalService(
        PlansFilterLocalService plansFilterLocalService) {
        _plansFilterLocalService = plansFilterLocalService;
    }

    @Override
    public PlansFilterLocalService getWrappedService() {
        return _plansFilterLocalService;
    }

    @Override
    public void setWrappedService(
        PlansFilterLocalService plansFilterLocalService) {
        _plansFilterLocalService = plansFilterLocalService;
    }
}
