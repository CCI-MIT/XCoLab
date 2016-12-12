package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PointDistributionTargetLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PointDistributionTargetLocalService
 * @generated
 */
public class PointDistributionTargetLocalServiceWrapper
    implements PointDistributionTargetLocalService,
        ServiceWrapper<PointDistributionTargetLocalService> {
    private PointDistributionTargetLocalService _pointDistributionTargetLocalService;

    public PointDistributionTargetLocalServiceWrapper(
        PointDistributionTargetLocalService pointDistributionTargetLocalService) {
        _pointDistributionTargetLocalService = pointDistributionTargetLocalService;
    }

    /**
    * Adds the point distribution target to the database. Also notifies the appropriate model listeners.
    *
    * @param pointDistributionTarget the point distribution target
    * @return the point distribution target that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointDistributionTarget addPointDistributionTarget(
        com.ext.portlet.model.PointDistributionTarget pointDistributionTarget)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointDistributionTargetLocalService.addPointDistributionTarget(pointDistributionTarget);
    }

    /**
    * Creates a new point distribution target with the primary key. Does not add the point distribution target to the database.
    *
    * @param id the primary key for the new point distribution target
    * @return the new point distribution target
    */
    @Override
    public com.ext.portlet.model.PointDistributionTarget createPointDistributionTarget(
        long id) {
        return _pointDistributionTargetLocalService.createPointDistributionTarget(id);
    }

    /**
    * Deletes the point distribution target with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the point distribution target
    * @return the point distribution target that was removed
    * @throws PortalException if a point distribution target with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointDistributionTarget deletePointDistributionTarget(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _pointDistributionTargetLocalService.deletePointDistributionTarget(id);
    }

    /**
    * Deletes the point distribution target from the database. Also notifies the appropriate model listeners.
    *
    * @param pointDistributionTarget the point distribution target
    * @return the point distribution target that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointDistributionTarget deletePointDistributionTarget(
        com.ext.portlet.model.PointDistributionTarget pointDistributionTarget)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointDistributionTargetLocalService.deletePointDistributionTarget(pointDistributionTarget);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _pointDistributionTargetLocalService.dynamicQuery();
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
        return _pointDistributionTargetLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _pointDistributionTargetLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _pointDistributionTargetLocalService.dynamicQuery(dynamicQuery,
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
        return _pointDistributionTargetLocalService.dynamicQueryCount(dynamicQuery);
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
        return _pointDistributionTargetLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.PointDistributionTarget fetchPointDistributionTarget(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _pointDistributionTargetLocalService.fetchPointDistributionTarget(id);
    }

    /**
    * Returns the point distribution target with the primary key.
    *
    * @param id the primary key of the point distribution target
    * @return the point distribution target
    * @throws PortalException if a point distribution target with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointDistributionTarget getPointDistributionTarget(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _pointDistributionTargetLocalService.getPointDistributionTarget(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _pointDistributionTargetLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the point distribution targets.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of point distribution targets
    * @param end the upper bound of the range of point distribution targets (not inclusive)
    * @return the range of point distribution targets
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PointDistributionTarget> getPointDistributionTargets(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointDistributionTargetLocalService.getPointDistributionTargets(start,
            end);
    }

    /**
    * Returns the number of point distribution targets.
    *
    * @return the number of point distribution targets
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPointDistributionTargetsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointDistributionTargetLocalService.getPointDistributionTargetsCount();
    }

    /**
    * Updates the point distribution target in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param pointDistributionTarget the point distribution target
    * @return the point distribution target that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointDistributionTarget updatePointDistributionTarget(
        com.ext.portlet.model.PointDistributionTarget pointDistributionTarget)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointDistributionTargetLocalService.updatePointDistributionTarget(pointDistributionTarget);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _pointDistributionTargetLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _pointDistributionTargetLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _pointDistributionTargetLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PointDistributionTargetLocalService getWrappedPointDistributionTargetLocalService() {
        return _pointDistributionTargetLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPointDistributionTargetLocalService(
        PointDistributionTargetLocalService pointDistributionTargetLocalService) {
        _pointDistributionTargetLocalService = pointDistributionTargetLocalService;
    }

    @Override
    public PointDistributionTargetLocalService getWrappedService() {
        return _pointDistributionTargetLocalService;
    }

    @Override
    public void setWrappedService(
        PointDistributionTargetLocalService pointDistributionTargetLocalService) {
        _pointDistributionTargetLocalService = pointDistributionTargetLocalService;
    }
}
