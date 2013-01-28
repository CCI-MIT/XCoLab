package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlansFilterPositionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlansFilterPositionLocalService
 * @generated
 */
public class PlansFilterPositionLocalServiceWrapper
    implements PlansFilterPositionLocalService,
        ServiceWrapper<PlansFilterPositionLocalService> {
    private PlansFilterPositionLocalService _plansFilterPositionLocalService;

    public PlansFilterPositionLocalServiceWrapper(
        PlansFilterPositionLocalService plansFilterPositionLocalService) {
        _plansFilterPositionLocalService = plansFilterPositionLocalService;
    }

    /**
    * Adds the plans filter position to the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPosition the plans filter position
    * @return the plans filter position that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition addPlansFilterPosition(
        com.ext.portlet.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterPositionLocalService.addPlansFilterPosition(plansFilterPosition);
    }

    /**
    * Creates a new plans filter position with the primary key. Does not add the plans filter position to the database.
    *
    * @param plansFilterPositionPK the primary key for the new plans filter position
    * @return the new plans filter position
    */
    public com.ext.portlet.model.PlansFilterPosition createPlansFilterPosition(
        com.ext.portlet.service.persistence.PlansFilterPositionPK plansFilterPositionPK) {
        return _plansFilterPositionLocalService.createPlansFilterPosition(plansFilterPositionPK);
    }

    /**
    * Deletes the plans filter position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPositionPK the primary key of the plans filter position
    * @throws PortalException if a plans filter position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlansFilterPosition(
        com.ext.portlet.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _plansFilterPositionLocalService.deletePlansFilterPosition(plansFilterPositionPK);
    }

    /**
    * Deletes the plans filter position from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPosition the plans filter position
    * @throws SystemException if a system exception occurred
    */
    public void deletePlansFilterPosition(
        com.ext.portlet.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        _plansFilterPositionLocalService.deletePlansFilterPosition(plansFilterPosition);
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
        return _plansFilterPositionLocalService.dynamicQuery(dynamicQuery);
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
        return _plansFilterPositionLocalService.dynamicQuery(dynamicQuery,
            start, end);
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
        return _plansFilterPositionLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
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
        return _plansFilterPositionLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.PlansFilterPosition fetchPlansFilterPosition(
        com.ext.portlet.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterPositionLocalService.fetchPlansFilterPosition(plansFilterPositionPK);
    }

    /**
    * Returns the plans filter position with the primary key.
    *
    * @param plansFilterPositionPK the primary key of the plans filter position
    * @return the plans filter position
    * @throws PortalException if a plans filter position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition getPlansFilterPosition(
        com.ext.portlet.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterPositionLocalService.getPlansFilterPosition(plansFilterPositionPK);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterPositionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plans filter positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plans filter positions
    * @param end the upper bound of the range of plans filter positions (not inclusive)
    * @return the range of plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlansFilterPosition> getPlansFilterPositions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterPositionLocalService.getPlansFilterPositions(start,
            end);
    }

    /**
    * Returns the number of plans filter positions.
    *
    * @return the number of plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public int getPlansFilterPositionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterPositionLocalService.getPlansFilterPositionsCount();
    }

    /**
    * Updates the plans filter position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPosition the plans filter position
    * @return the plans filter position that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition updatePlansFilterPosition(
        com.ext.portlet.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterPositionLocalService.updatePlansFilterPosition(plansFilterPosition);
    }

    /**
    * Updates the plans filter position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPosition the plans filter position
    * @param merge whether to merge the plans filter position with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plans filter position that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlansFilterPosition updatePlansFilterPosition(
        com.ext.portlet.model.PlansFilterPosition plansFilterPosition,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _plansFilterPositionLocalService.updatePlansFilterPosition(plansFilterPosition,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _plansFilterPositionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _plansFilterPositionLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlansFilterPositionLocalService getWrappedPlansFilterPositionLocalService() {
        return _plansFilterPositionLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlansFilterPositionLocalService(
        PlansFilterPositionLocalService plansFilterPositionLocalService) {
        _plansFilterPositionLocalService = plansFilterPositionLocalService;
    }

    public PlansFilterPositionLocalService getWrappedService() {
        return _plansFilterPositionLocalService;
    }

    public void setWrappedService(
        PlansFilterPositionLocalService plansFilterPositionLocalService) {
        _plansFilterPositionLocalService = plansFilterPositionLocalService;
    }
}
