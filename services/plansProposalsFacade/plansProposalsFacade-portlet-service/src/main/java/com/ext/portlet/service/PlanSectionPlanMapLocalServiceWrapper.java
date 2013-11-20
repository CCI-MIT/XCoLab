package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanSectionPlanMapLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPlanMapLocalService
 * @generated
 */
public class PlanSectionPlanMapLocalServiceWrapper
    implements PlanSectionPlanMapLocalService,
        ServiceWrapper<PlanSectionPlanMapLocalService> {
    private PlanSectionPlanMapLocalService _planSectionPlanMapLocalService;

    public PlanSectionPlanMapLocalServiceWrapper(
        PlanSectionPlanMapLocalService planSectionPlanMapLocalService) {
        _planSectionPlanMapLocalService = planSectionPlanMapLocalService;
    }

    /**
    * Adds the plan section plan map to the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMap the plan section plan map
    * @return the plan section plan map that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanSectionPlanMap addPlanSectionPlanMap(
        com.ext.portlet.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionPlanMapLocalService.addPlanSectionPlanMap(planSectionPlanMap);
    }

    /**
    * Creates a new plan section plan map with the primary key. Does not add the plan section plan map to the database.
    *
    * @param planSectionPlanMapPK the primary key for the new plan section plan map
    * @return the new plan section plan map
    */
    @Override
    public com.ext.portlet.model.PlanSectionPlanMap createPlanSectionPlanMap(
        com.ext.portlet.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK) {
        return _planSectionPlanMapLocalService.createPlanSectionPlanMap(planSectionPlanMapPK);
    }

    /**
    * Deletes the plan section plan map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMapPK the primary key of the plan section plan map
    * @return the plan section plan map that was removed
    * @throws PortalException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanSectionPlanMap deletePlanSectionPlanMap(
        com.ext.portlet.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSectionPlanMapLocalService.deletePlanSectionPlanMap(planSectionPlanMapPK);
    }

    /**
    * Deletes the plan section plan map from the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMap the plan section plan map
    * @return the plan section plan map that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanSectionPlanMap deletePlanSectionPlanMap(
        com.ext.portlet.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionPlanMapLocalService.deletePlanSectionPlanMap(planSectionPlanMap);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _planSectionPlanMapLocalService.dynamicQuery();
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
        return _planSectionPlanMapLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionPlanMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planSectionPlanMapLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionPlanMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planSectionPlanMapLocalService.dynamicQuery(dynamicQuery,
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
        return _planSectionPlanMapLocalService.dynamicQueryCount(dynamicQuery);
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
        return _planSectionPlanMapLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.PlanSectionPlanMap fetchPlanSectionPlanMap(
        com.ext.portlet.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionPlanMapLocalService.fetchPlanSectionPlanMap(planSectionPlanMapPK);
    }

    /**
    * Returns the plan section plan map with the primary key.
    *
    * @param planSectionPlanMapPK the primary key of the plan section plan map
    * @return the plan section plan map
    * @throws PortalException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanSectionPlanMap getPlanSectionPlanMap(
        com.ext.portlet.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSectionPlanMapLocalService.getPlanSectionPlanMap(planSectionPlanMapPK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSectionPlanMapLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan section plan maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionPlanMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @return the range of plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlanSectionPlanMap> getPlanSectionPlanMaps(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionPlanMapLocalService.getPlanSectionPlanMaps(start, end);
    }

    /**
    * Returns the number of plan section plan maps.
    *
    * @return the number of plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPlanSectionPlanMapsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionPlanMapLocalService.getPlanSectionPlanMapsCount();
    }

    /**
    * Updates the plan section plan map in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMap the plan section plan map
    * @return the plan section plan map that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanSectionPlanMap updatePlanSectionPlanMap(
        com.ext.portlet.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionPlanMapLocalService.updatePlanSectionPlanMap(planSectionPlanMap);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planSectionPlanMapLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planSectionPlanMapLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planSectionPlanMapLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List<java.lang.Long> findPlanIdsForSection(
        java.lang.Long sectionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionPlanMapLocalService.findPlanIdsForSection(sectionId);
    }

    @Override
    public void store(com.ext.portlet.model.PlanSectionPlanMap pspm)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSectionPlanMapLocalService.store(pspm);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanSectionPlanMapLocalService getWrappedPlanSectionPlanMapLocalService() {
        return _planSectionPlanMapLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanSectionPlanMapLocalService(
        PlanSectionPlanMapLocalService planSectionPlanMapLocalService) {
        _planSectionPlanMapLocalService = planSectionPlanMapLocalService;
    }

    @Override
    public PlanSectionPlanMapLocalService getWrappedService() {
        return _planSectionPlanMapLocalService;
    }

    @Override
    public void setWrappedService(
        PlanSectionPlanMapLocalService planSectionPlanMapLocalService) {
        _planSectionPlanMapLocalService = planSectionPlanMapLocalService;
    }
}
