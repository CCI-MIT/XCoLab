package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanTeamHistoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTeamHistoryLocalService
 * @generated
 */
public class PlanTeamHistoryLocalServiceWrapper
    implements PlanTeamHistoryLocalService,
        ServiceWrapper<PlanTeamHistoryLocalService> {
    private PlanTeamHistoryLocalService _planTeamHistoryLocalService;

    public PlanTeamHistoryLocalServiceWrapper(
        PlanTeamHistoryLocalService planTeamHistoryLocalService) {
        _planTeamHistoryLocalService = planTeamHistoryLocalService;
    }

    /**
    * Adds the plan team history to the database. Also notifies the appropriate model listeners.
    *
    * @param planTeamHistory the plan team history
    * @return the plan team history that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanTeamHistory addPlanTeamHistory(
        com.ext.portlet.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.addPlanTeamHistory(planTeamHistory);
    }

    /**
    * Creates a new plan team history with the primary key. Does not add the plan team history to the database.
    *
    * @param id the primary key for the new plan team history
    * @return the new plan team history
    */
    @Override
    public com.ext.portlet.model.PlanTeamHistory createPlanTeamHistory(long id) {
        return _planTeamHistoryLocalService.createPlanTeamHistory(id);
    }

    /**
    * Deletes the plan team history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan team history
    * @return the plan team history that was removed
    * @throws PortalException if a plan team history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanTeamHistory deletePlanTeamHistory(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.deletePlanTeamHistory(id);
    }

    /**
    * Deletes the plan team history from the database. Also notifies the appropriate model listeners.
    *
    * @param planTeamHistory the plan team history
    * @return the plan team history that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanTeamHistory deletePlanTeamHistory(
        com.ext.portlet.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.deletePlanTeamHistory(planTeamHistory);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _planTeamHistoryLocalService.dynamicQuery();
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
        return _planTeamHistoryLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTeamHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planTeamHistoryLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTeamHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planTeamHistoryLocalService.dynamicQuery(dynamicQuery, start,
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
        return _planTeamHistoryLocalService.dynamicQueryCount(dynamicQuery);
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
        return _planTeamHistoryLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.PlanTeamHistory fetchPlanTeamHistory(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.fetchPlanTeamHistory(id);
    }

    /**
    * Returns the plan team history with the primary key.
    *
    * @param id the primary key of the plan team history
    * @return the plan team history
    * @throws PortalException if a plan team history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanTeamHistory getPlanTeamHistory(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.getPlanTeamHistory(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan team histories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTeamHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan team histories
    * @param end the upper bound of the range of plan team histories (not inclusive)
    * @return the range of plan team histories
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlanTeamHistory> getPlanTeamHistories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.getPlanTeamHistories(start, end);
    }

    /**
    * Returns the number of plan team histories.
    *
    * @return the number of plan team histories
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPlanTeamHistoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.getPlanTeamHistoriesCount();
    }

    /**
    * Updates the plan team history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planTeamHistory the plan team history
    * @return the plan team history that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanTeamHistory updatePlanTeamHistory(
        com.ext.portlet.model.PlanTeamHistory planTeamHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.updatePlanTeamHistory(planTeamHistory);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planTeamHistoryLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTeamHistoryLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planTeamHistoryLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public com.ext.portlet.model.PlanTeamHistory newHistoryItem(
        java.lang.Long planId, java.lang.Long userId, java.lang.String action,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.newHistoryItem(planId, userId,
            action, updateAuthorId);
    }

    @Override
    public com.ext.portlet.model.PlanTeamHistory newHistoryItem(
        java.lang.Long planId, java.lang.Long userId, java.lang.String action,
        java.lang.String payload, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.newHistoryItem(planId, userId,
            action, payload, updateAuthorId);
    }

    @Override
    public com.ext.portlet.model.PlanTeamHistory getLastUserActionInPlan(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.NoSuchPlanTeamHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.getLastUserActionInPlan(planId,
            userId);
    }

    @Override
    public void store(com.ext.portlet.model.PlanTeamHistory pth)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTeamHistoryLocalService.store(pth);
    }

    @Override
    public com.liferay.portal.model.User getUser(
        com.ext.portlet.model.PlanTeamHistory pth)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.getUser(pth);
    }

    @Override
    public com.ext.portlet.model.PlanItem getPlan(
        com.ext.portlet.model.PlanTeamHistory pth)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTeamHistoryLocalService.getPlan(pth);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanTeamHistoryLocalService getWrappedPlanTeamHistoryLocalService() {
        return _planTeamHistoryLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanTeamHistoryLocalService(
        PlanTeamHistoryLocalService planTeamHistoryLocalService) {
        _planTeamHistoryLocalService = planTeamHistoryLocalService;
    }

    @Override
    public PlanTeamHistoryLocalService getWrappedService() {
        return _planTeamHistoryLocalService;
    }

    @Override
    public void setWrappedService(
        PlanTeamHistoryLocalService planTeamHistoryLocalService) {
        _planTeamHistoryLocalService = planTeamHistoryLocalService;
    }
}
