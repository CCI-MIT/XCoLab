package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactTemplateSeriesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateSeriesLocalService
 * @generated
 */
public class ImpactTemplateSeriesLocalServiceWrapper
    implements ImpactTemplateSeriesLocalService,
        ServiceWrapper<ImpactTemplateSeriesLocalService> {
    private ImpactTemplateSeriesLocalService _impactTemplateSeriesLocalService;

    public ImpactTemplateSeriesLocalServiceWrapper(
        ImpactTemplateSeriesLocalService impactTemplateSeriesLocalService) {
        _impactTemplateSeriesLocalService = impactTemplateSeriesLocalService;
    }

    /**
    * Adds the impact template series to the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateSeries the impact template series
    * @return the impact template series that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateSeries addImpactTemplateSeries(
        com.ext.portlet.model.ImpactTemplateSeries impactTemplateSeries)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateSeriesLocalService.addImpactTemplateSeries(impactTemplateSeries);
    }

    /**
    * Creates a new impact template series with the primary key. Does not add the impact template series to the database.
    *
    * @param seriesId the primary key for the new impact template series
    * @return the new impact template series
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateSeries createImpactTemplateSeries(
        long seriesId) {
        return _impactTemplateSeriesLocalService.createImpactTemplateSeries(seriesId);
    }

    /**
    * Deletes the impact template series with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param seriesId the primary key of the impact template series
    * @return the impact template series that was removed
    * @throws PortalException if a impact template series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateSeries deleteImpactTemplateSeries(
        long seriesId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateSeriesLocalService.deleteImpactTemplateSeries(seriesId);
    }

    /**
    * Deletes the impact template series from the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateSeries the impact template series
    * @return the impact template series that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateSeries deleteImpactTemplateSeries(
        com.ext.portlet.model.ImpactTemplateSeries impactTemplateSeries)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateSeriesLocalService.deleteImpactTemplateSeries(impactTemplateSeries);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _impactTemplateSeriesLocalService.dynamicQuery();
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
        return _impactTemplateSeriesLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _impactTemplateSeriesLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _impactTemplateSeriesLocalService.dynamicQuery(dynamicQuery,
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
        return _impactTemplateSeriesLocalService.dynamicQueryCount(dynamicQuery);
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
        return _impactTemplateSeriesLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ImpactTemplateSeries fetchImpactTemplateSeries(
        long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateSeriesLocalService.fetchImpactTemplateSeries(seriesId);
    }

    /**
    * Returns the impact template series with the primary key.
    *
    * @param seriesId the primary key of the impact template series
    * @return the impact template series
    * @throws PortalException if a impact template series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateSeries getImpactTemplateSeries(
        long seriesId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateSeriesLocalService.getImpactTemplateSeries(seriesId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateSeriesLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the impact template serieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template serieses
    * @param end the upper bound of the range of impact template serieses (not inclusive)
    * @return the range of impact template serieses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ImpactTemplateSeries> getImpactTemplateSerieses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateSeriesLocalService.getImpactTemplateSerieses(start,
            end);
    }

    /**
    * Returns the number of impact template serieses.
    *
    * @return the number of impact template serieses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImpactTemplateSeriesesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateSeriesLocalService.getImpactTemplateSeriesesCount();
    }

    /**
    * Updates the impact template series in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateSeries the impact template series
    * @return the impact template series that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateSeries updateImpactTemplateSeries(
        com.ext.portlet.model.ImpactTemplateSeries impactTemplateSeries)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateSeriesLocalService.updateImpactTemplateSeries(impactTemplateSeries);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _impactTemplateSeriesLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impactTemplateSeriesLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impactTemplateSeriesLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImpactTemplateSeriesLocalService getWrappedImpactTemplateSeriesLocalService() {
        return _impactTemplateSeriesLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImpactTemplateSeriesLocalService(
        ImpactTemplateSeriesLocalService impactTemplateSeriesLocalService) {
        _impactTemplateSeriesLocalService = impactTemplateSeriesLocalService;
    }

    @Override
    public ImpactTemplateSeriesLocalService getWrappedService() {
        return _impactTemplateSeriesLocalService;
    }

    @Override
    public void setWrappedService(
        ImpactTemplateSeriesLocalService impactTemplateSeriesLocalService) {
        _impactTemplateSeriesLocalService = impactTemplateSeriesLocalService;
    }
}
