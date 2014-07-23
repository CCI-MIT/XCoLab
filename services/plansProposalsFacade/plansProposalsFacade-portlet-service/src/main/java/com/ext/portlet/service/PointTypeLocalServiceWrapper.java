package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PointTypeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PointTypeLocalService
 * @generated
 */
public class PointTypeLocalServiceWrapper implements PointTypeLocalService,
    ServiceWrapper<PointTypeLocalService> {
    private PointTypeLocalService _pointTypeLocalService;

    public PointTypeLocalServiceWrapper(
        PointTypeLocalService pointTypeLocalService) {
        _pointTypeLocalService = pointTypeLocalService;
    }

    /**
    * Adds the point type to the database. Also notifies the appropriate model listeners.
    *
    * @param pointType the point type
    * @return the point type that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointType addPointType(
        com.ext.portlet.model.PointType pointType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointTypeLocalService.addPointType(pointType);
    }

    /**
    * Creates a new point type with the primary key. Does not add the point type to the database.
    *
    * @param id the primary key for the new point type
    * @return the new point type
    */
    @Override
    public com.ext.portlet.model.PointType createPointType(long id) {
        return _pointTypeLocalService.createPointType(id);
    }

    /**
    * Deletes the point type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the point type
    * @return the point type that was removed
    * @throws PortalException if a point type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointType deletePointType(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _pointTypeLocalService.deletePointType(id);
    }

    /**
    * Deletes the point type from the database. Also notifies the appropriate model listeners.
    *
    * @param pointType the point type
    * @return the point type that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointType deletePointType(
        com.ext.portlet.model.PointType pointType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointTypeLocalService.deletePointType(pointType);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _pointTypeLocalService.dynamicQuery();
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
        return _pointTypeLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _pointTypeLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _pointTypeLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _pointTypeLocalService.dynamicQueryCount(dynamicQuery);
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
        return _pointTypeLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.ext.portlet.model.PointType fetchPointType(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointTypeLocalService.fetchPointType(id);
    }

    /**
    * Returns the point type with the primary key.
    *
    * @param id the primary key of the point type
    * @return the point type
    * @throws PortalException if a point type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointType getPointType(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _pointTypeLocalService.getPointType(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _pointTypeLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the point types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of point types
    * @param end the upper bound of the range of point types (not inclusive)
    * @return the range of point types
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PointType> getPointTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointTypeLocalService.getPointTypes(start, end);
    }

    /**
    * Returns the number of point types.
    *
    * @return the number of point types
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPointTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointTypeLocalService.getPointTypesCount();
    }

    /**
    * Updates the point type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param pointType the point type
    * @return the point type that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointType updatePointType(
        com.ext.portlet.model.PointType pointType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointTypeLocalService.updatePointType(pointType);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _pointTypeLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _pointTypeLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _pointTypeLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PointType> getChildrenOfPointType(
        long parentPointTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointTypeLocalService.getChildrenOfPointType(parentPointTypeId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PointTypeLocalService getWrappedPointTypeLocalService() {
        return _pointTypeLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPointTypeLocalService(
        PointTypeLocalService pointTypeLocalService) {
        _pointTypeLocalService = pointTypeLocalService;
    }

    @Override
    public PointTypeLocalService getWrappedService() {
        return _pointTypeLocalService;
    }

    @Override
    public void setWrappedService(PointTypeLocalService pointTypeLocalService) {
        _pointTypeLocalService = pointTypeLocalService;
    }
}
