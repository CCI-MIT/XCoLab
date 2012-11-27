package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlansFilterLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlansFilterLocalService
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
    public com.ext.portlet.plans.model.PlansFilter addPlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.addPlansFilter(plansFilter);
    }

    /**
    * Creates a new plans filter with the primary key. Does not add the plans filter to the database.
    *
    * @param plansFilterPK the primary key for the new plans filter
    * @return the new plans filter
    */
    public com.ext.portlet.plans.model.PlansFilter createPlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK) {
        return _plansFilterLocalService.createPlansFilter(plansFilterPK);
    }

    /**
    * Deletes the plans filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPK the primary key of the plans filter
    * @throws PortalException if a plans filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _plansFilterLocalService.deletePlansFilter(plansFilterPK);
    }

    /**
    * Deletes the plans filter from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilter the plans filter
    * @throws SystemException if a system exception occurred
    */
    public void deletePlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        _plansFilterLocalService.deletePlansFilter(plansFilter);
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
        return _plansFilterLocalService.dynamicQuery(dynamicQuery);
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
        return _plansFilterLocalService.dynamicQuery(dynamicQuery, start, end);
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
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlansFilter fetchPlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
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
    public com.ext.portlet.plans.model.PlansFilter getPlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.getPlansFilter(plansFilterPK);
    }

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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plans filters
    * @param end the upper bound of the range of plans filters (not inclusive)
    * @return the range of plans filters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlansFilter> getPlansFilters(
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
    public com.ext.portlet.plans.model.PlansFilter updatePlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.updatePlansFilter(plansFilter);
    }

    /**
    * Updates the plans filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansFilter the plans filter
    * @param merge whether to merge the plans filter with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plans filter that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlansFilter updatePlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterLocalService.updatePlansFilter(plansFilter, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _plansFilterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _plansFilterLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlansFilterLocalService getWrappedPlansFilterLocalService() {
        return _plansFilterLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlansFilterLocalService(
        PlansFilterLocalService plansFilterLocalService) {
        _plansFilterLocalService = plansFilterLocalService;
    }

    public PlansFilterLocalService getWrappedService() {
        return _plansFilterLocalService;
    }

    public void setWrappedService(
        PlansFilterLocalService plansFilterLocalService) {
        _plansFilterLocalService = plansFilterLocalService;
    }
}
