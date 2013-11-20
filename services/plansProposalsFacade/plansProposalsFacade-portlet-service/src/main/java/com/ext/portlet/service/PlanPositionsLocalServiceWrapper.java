package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanPositionsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionsLocalService
 * @generated
 */
public class PlanPositionsLocalServiceWrapper
    implements PlanPositionsLocalService,
        ServiceWrapper<PlanPositionsLocalService> {
    private PlanPositionsLocalService _planPositionsLocalService;

    public PlanPositionsLocalServiceWrapper(
        PlanPositionsLocalService planPositionsLocalService) {
        _planPositionsLocalService = planPositionsLocalService;
    }

    /**
    * Adds the plan positions to the database. Also notifies the appropriate model listeners.
    *
    * @param planPositions the plan positions
    * @return the plan positions that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanPositions addPlanPositions(
        com.ext.portlet.model.PlanPositions planPositions)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.addPlanPositions(planPositions);
    }

    /**
    * Creates a new plan positions with the primary key. Does not add the plan positions to the database.
    *
    * @param id the primary key for the new plan positions
    * @return the new plan positions
    */
    @Override
    public com.ext.portlet.model.PlanPositions createPlanPositions(long id) {
        return _planPositionsLocalService.createPlanPositions(id);
    }

    /**
    * Deletes the plan positions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions that was removed
    * @throws PortalException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanPositions deletePlanPositions(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.deletePlanPositions(id);
    }

    /**
    * Deletes the plan positions from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositions the plan positions
    * @return the plan positions that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanPositions deletePlanPositions(
        com.ext.portlet.model.PlanPositions planPositions)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.deletePlanPositions(planPositions);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _planPositionsLocalService.dynamicQuery();
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
        return _planPositionsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planPositionsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planPositionsLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _planPositionsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _planPositionsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.PlanPositions fetchPlanPositions(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.fetchPlanPositions(id);
    }

    /**
    * Returns the plan positions with the primary key.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions
    * @throws PortalException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanPositions getPlanPositions(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.getPlanPositions(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan positionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan positionses
    * @param end the upper bound of the range of plan positionses (not inclusive)
    * @return the range of plan positionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlanPositions> getPlanPositionses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.getPlanPositionses(start, end);
    }

    /**
    * Returns the number of plan positionses.
    *
    * @return the number of plan positionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPlanPositionsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.getPlanPositionsesCount();
    }

    /**
    * Updates the plan positions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPositions the plan positions
    * @return the plan positions that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanPositions updatePlanPositions(
        com.ext.portlet.model.PlanPositions planPositions)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.updatePlanPositions(planPositions);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planPositionsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planPositionsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planPositionsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public com.ext.portlet.model.PlanPositions getCurrentForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.getCurrentForPlan(plan);
    }

    @Override
    public com.ext.portlet.model.PlanPositions createPlanPositions(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.createPlanPositions(plan);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanPositions> getAllForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.getAllForPlan(plan);
    }

    @Override
    public com.ext.portlet.model.PlanPositions createNewVersionForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.createNewVersionForPlan(plan);
    }

    @Override
    public com.ext.portlet.model.PlanPositions createNewVersionForPlan(
        com.ext.portlet.model.PlanItem plan, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.createNewVersionForPlan(plan, store);
    }

    @Override
    public java.util.List<java.lang.Long> getPositionsIds(
        com.ext.portlet.model.PlanPositions pp)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.getPositionsIds(pp);
    }

    @Override
    public void store(com.ext.portlet.model.PlanPositions pp)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPositionsLocalService.store(pp);
    }

    @Override
    public void setPositionsIds(com.ext.portlet.model.PlanPositions pp,
        java.util.List<java.lang.Long> positionsIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planPositionsLocalService.setPositionsIds(pp, positionsIds);
    }

    @Override
    public com.liferay.portal.model.User getUpdateAuthor(
        com.ext.portlet.model.PlanPositions pp)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPositionsLocalService.getUpdateAuthor(pp);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanPositionsLocalService getWrappedPlanPositionsLocalService() {
        return _planPositionsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanPositionsLocalService(
        PlanPositionsLocalService planPositionsLocalService) {
        _planPositionsLocalService = planPositionsLocalService;
    }

    @Override
    public PlanPositionsLocalService getWrappedService() {
        return _planPositionsLocalService;
    }

    @Override
    public void setWrappedService(
        PlanPositionsLocalService planPositionsLocalService) {
        _planPositionsLocalService = planPositionsLocalService;
    }
}
