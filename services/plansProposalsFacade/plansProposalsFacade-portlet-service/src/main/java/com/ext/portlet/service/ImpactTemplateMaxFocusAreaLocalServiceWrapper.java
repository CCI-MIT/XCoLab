package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactTemplateMaxFocusAreaLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateMaxFocusAreaLocalService
 * @generated
 */
public class ImpactTemplateMaxFocusAreaLocalServiceWrapper
    implements ImpactTemplateMaxFocusAreaLocalService,
        ServiceWrapper<ImpactTemplateMaxFocusAreaLocalService> {
    private ImpactTemplateMaxFocusAreaLocalService _impactTemplateMaxFocusAreaLocalService;

    public ImpactTemplateMaxFocusAreaLocalServiceWrapper(
        ImpactTemplateMaxFocusAreaLocalService impactTemplateMaxFocusAreaLocalService) {
        _impactTemplateMaxFocusAreaLocalService = impactTemplateMaxFocusAreaLocalService;
    }

    /**
    * Adds the impact template max focus area to the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateMaxFocusArea the impact template max focus area
    * @return the impact template max focus area that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea addImpactTemplateMaxFocusArea(
        com.ext.portlet.model.ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateMaxFocusAreaLocalService.addImpactTemplateMaxFocusArea(impactTemplateMaxFocusArea);
    }

    /**
    * Creates a new impact template max focus area with the primary key. Does not add the impact template max focus area to the database.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key for the new impact template max focus area
    * @return the new impact template max focus area
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea createImpactTemplateMaxFocusArea(
        com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK) {
        return _impactTemplateMaxFocusAreaLocalService.createImpactTemplateMaxFocusArea(impactTemplateMaxFocusAreaPK);
    }

    /**
    * Deletes the impact template max focus area with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key of the impact template max focus area
    * @return the impact template max focus area that was removed
    * @throws PortalException if a impact template max focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea deleteImpactTemplateMaxFocusArea(
        com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateMaxFocusAreaLocalService.deleteImpactTemplateMaxFocusArea(impactTemplateMaxFocusAreaPK);
    }

    /**
    * Deletes the impact template max focus area from the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateMaxFocusArea the impact template max focus area
    * @return the impact template max focus area that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea deleteImpactTemplateMaxFocusArea(
        com.ext.portlet.model.ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateMaxFocusAreaLocalService.deleteImpactTemplateMaxFocusArea(impactTemplateMaxFocusArea);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _impactTemplateMaxFocusAreaLocalService.dynamicQuery();
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
        return _impactTemplateMaxFocusAreaLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _impactTemplateMaxFocusAreaLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _impactTemplateMaxFocusAreaLocalService.dynamicQuery(dynamicQuery,
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
        return _impactTemplateMaxFocusAreaLocalService.dynamicQueryCount(dynamicQuery);
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
        return _impactTemplateMaxFocusAreaLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea fetchImpactTemplateMaxFocusArea(
        com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateMaxFocusAreaLocalService.fetchImpactTemplateMaxFocusArea(impactTemplateMaxFocusAreaPK);
    }

    /**
    * Returns the impact template max focus area with the primary key.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key of the impact template max focus area
    * @return the impact template max focus area
    * @throws PortalException if a impact template max focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea getImpactTemplateMaxFocusArea(
        com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateMaxFocusAreaLocalService.getImpactTemplateMaxFocusArea(impactTemplateMaxFocusAreaPK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateMaxFocusAreaLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the impact template max focus areas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template max focus areas
    * @param end the upper bound of the range of impact template max focus areas (not inclusive)
    * @return the range of impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusAreas(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateMaxFocusAreaLocalService.getImpactTemplateMaxFocusAreas(start,
            end);
    }

    /**
    * Returns the number of impact template max focus areas.
    *
    * @return the number of impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImpactTemplateMaxFocusAreasCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateMaxFocusAreaLocalService.getImpactTemplateMaxFocusAreasCount();
    }

    /**
    * Updates the impact template max focus area in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateMaxFocusArea the impact template max focus area
    * @return the impact template max focus area that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ImpactTemplateMaxFocusArea updateImpactTemplateMaxFocusArea(
        com.ext.portlet.model.ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _impactTemplateMaxFocusAreaLocalService.updateImpactTemplateMaxFocusArea(impactTemplateMaxFocusArea);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _impactTemplateMaxFocusAreaLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impactTemplateMaxFocusAreaLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impactTemplateMaxFocusAreaLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImpactTemplateMaxFocusAreaLocalService getWrappedImpactTemplateMaxFocusAreaLocalService() {
        return _impactTemplateMaxFocusAreaLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImpactTemplateMaxFocusAreaLocalService(
        ImpactTemplateMaxFocusAreaLocalService impactTemplateMaxFocusAreaLocalService) {
        _impactTemplateMaxFocusAreaLocalService = impactTemplateMaxFocusAreaLocalService;
    }

    @Override
    public ImpactTemplateMaxFocusAreaLocalService getWrappedService() {
        return _impactTemplateMaxFocusAreaLocalService;
    }

    @Override
    public void setWrappedService(
        ImpactTemplateMaxFocusAreaLocalService impactTemplateMaxFocusAreaLocalService) {
        _impactTemplateMaxFocusAreaLocalService = impactTemplateMaxFocusAreaLocalService;
    }
}
