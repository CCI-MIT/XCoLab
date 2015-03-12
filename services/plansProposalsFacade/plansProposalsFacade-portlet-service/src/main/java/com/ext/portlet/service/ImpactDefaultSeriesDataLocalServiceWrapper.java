package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactDefaultSeriesDataLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesDataLocalService
 * @generated
 */
public class ImpactDefaultSeriesDataLocalServiceWrapper
    implements ImpactDefaultSeriesDataLocalService,
        ServiceWrapper<ImpactDefaultSeriesDataLocalService> {
    private ImpactDefaultSeriesDataLocalService _impactDefaultSeriesDataLocalService;

    public ImpactDefaultSeriesDataLocalServiceWrapper(
        ImpactDefaultSeriesDataLocalService impactDefaultSeriesDataLocalService) {
        _impactDefaultSeriesDataLocalService = impactDefaultSeriesDataLocalService;
    }

    /**
    * Adds the impact default series data to the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesData the impact default series data
    * @return the impact default series data that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactDefaultSeriesData addImpactDefaultSeriesData(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.addImpactDefaultSeriesData(impactDefaultSeriesData);
    }

    /**
    * Creates a new impact default series data with the primary key. Does not add the impact default series data to the database.
    *
    * @param impactDefaultSeriesDataPK the primary key for the new impact default series data
    * @return the new impact default series data
    */
    @Override
    public com.ext.portlet.model.ImpactDefaultSeriesData createImpactDefaultSeriesData(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK) {
        return _impactDefaultSeriesDataLocalService.createImpactDefaultSeriesData(impactDefaultSeriesDataPK);
    }

    /**
    * Deletes the impact default series data with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesDataPK the primary key of the impact default series data
    * @return the impact default series data that was removed
    * @throws PortalException if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactDefaultSeriesData deleteImpactDefaultSeriesData(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.deleteImpactDefaultSeriesData(impactDefaultSeriesDataPK);
    }

    /**
    * Deletes the impact default series data from the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesData the impact default series data
    * @return the impact default series data that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactDefaultSeriesData deleteImpactDefaultSeriesData(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.deleteImpactDefaultSeriesData(impactDefaultSeriesData);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _impactDefaultSeriesDataLocalService.dynamicQuery();
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
        return _impactDefaultSeriesDataLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _impactDefaultSeriesDataLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _impactDefaultSeriesDataLocalService.dynamicQuery(dynamicQuery,
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
        return _impactDefaultSeriesDataLocalService.dynamicQueryCount(dynamicQuery);
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
        return _impactDefaultSeriesDataLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ImpactDefaultSeriesData fetchImpactDefaultSeriesData(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.fetchImpactDefaultSeriesData(impactDefaultSeriesDataPK);
    }

    /**
    * Returns the impact default series data with the primary key.
    *
    * @param impactDefaultSeriesDataPK the primary key of the impact default series data
    * @return the impact default series data
    * @throws PortalException if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactDefaultSeriesData getImpactDefaultSeriesData(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.getImpactDefaultSeriesData(impactDefaultSeriesDataPK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the impact default series datas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact default series datas
    * @param end the upper bound of the range of impact default series datas (not inclusive)
    * @return the range of impact default series datas
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> getImpactDefaultSeriesDatas(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.getImpactDefaultSeriesDatas(start,
            end);
    }

    /**
    * Returns the number of impact default series datas.
    *
    * @return the number of impact default series datas
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImpactDefaultSeriesDatasCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.getImpactDefaultSeriesDatasCount();
    }

    /**
    * Updates the impact default series data in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesData the impact default series data
    * @return the impact default series data that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactDefaultSeriesData updateImpactDefaultSeriesData(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.updateImpactDefaultSeriesData(impactDefaultSeriesData);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _impactDefaultSeriesDataLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impactDefaultSeriesDataLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impactDefaultSeriesDataLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> getDefaultSeriesDataBySeriesId(
        long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.getDefaultSeriesDataBySeriesId(seriesId);
    }

    @Override
    public com.ext.portlet.model.ImpactDefaultSeriesData getDefaultSeriesDataBySeriesIdAndYear(
        long seriesId, int year)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactDefaultSeriesDataLocalService.getDefaultSeriesDataBySeriesIdAndYear(seriesId,
            year);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImpactDefaultSeriesDataLocalService getWrappedImpactDefaultSeriesDataLocalService() {
        return _impactDefaultSeriesDataLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImpactDefaultSeriesDataLocalService(
        ImpactDefaultSeriesDataLocalService impactDefaultSeriesDataLocalService) {
        _impactDefaultSeriesDataLocalService = impactDefaultSeriesDataLocalService;
    }

    @Override
    public ImpactDefaultSeriesDataLocalService getWrappedService() {
        return _impactDefaultSeriesDataLocalService;
    }

    @Override
    public void setWrappedService(
        ImpactDefaultSeriesDataLocalService impactDefaultSeriesDataLocalService) {
        _impactDefaultSeriesDataLocalService = impactDefaultSeriesDataLocalService;
    }
}
