package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPositionItemLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPositionItemLocalService
 * @generated
 */
public class PlanPositionItemLocalServiceWrapper
    implements PlanPositionItemLocalService,
        ServiceWrapper<PlanPositionItemLocalService> {
    private PlanPositionItemLocalService _planPositionItemLocalService;

    public PlanPositionItemLocalServiceWrapper(
        PlanPositionItemLocalService planPositionItemLocalService) {
        _planPositionItemLocalService = planPositionItemLocalService;
    }

    /**
    * Adds the plan position item to the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionItem the plan position item
    * @return the plan position item that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem addPlanPositionItem(
        com.ext.portlet.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionItemLocalService.addPlanPositionItem(planPositionItem);
    }

    /**
    * Creates a new plan position item with the primary key. Does not add the plan position item to the database.
    *
    * @param planPositionItemPK the primary key for the new plan position item
    * @return the new plan position item
    */
    public com.ext.portlet.model.PlanPositionItem createPlanPositionItem(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK) {
        return _planPositionItemLocalService.createPlanPositionItem(planPositionItemPK);
    }

    /**
    * Deletes the plan position item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionItemPK the primary key of the plan position item
    * @throws PortalException if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanPositionItem(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planPositionItemLocalService.deletePlanPositionItem(planPositionItemPK);
    }

    /**
    * Deletes the plan position item from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionItem the plan position item
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanPositionItem(
        com.ext.portlet.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPositionItemLocalService.deletePlanPositionItem(planPositionItem);
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
        return _planPositionItemLocalService.dynamicQuery(dynamicQuery);
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
        return _planPositionItemLocalService.dynamicQuery(dynamicQuery, start,
            end);
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
        return _planPositionItemLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _planPositionItemLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.PlanPositionItem fetchPlanPositionItem(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionItemLocalService.fetchPlanPositionItem(planPositionItemPK);
    }

    /**
    * Returns the plan position item with the primary key.
    *
    * @param planPositionItemPK the primary key of the plan position item
    * @return the plan position item
    * @throws PortalException if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem getPlanPositionItem(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPositionItemLocalService.getPlanPositionItem(planPositionItemPK);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPositionItemLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan position items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan position items
    * @param end the upper bound of the range of plan position items (not inclusive)
    * @return the range of plan position items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanPositionItem> getPlanPositionItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionItemLocalService.getPlanPositionItems(start, end);
    }

    /**
    * Returns the number of plan position items.
    *
    * @return the number of plan position items
    * @throws SystemException if a system exception occurred
    */
    public int getPlanPositionItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionItemLocalService.getPlanPositionItemsCount();
    }

    /**
    * Updates the plan position item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPositionItem the plan position item
    * @return the plan position item that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem updatePlanPositionItem(
        com.ext.portlet.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionItemLocalService.updatePlanPositionItem(planPositionItem);
    }

    /**
    * Updates the plan position item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPositionItem the plan position item
    * @param merge whether to merge the plan position item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan position item that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanPositionItem updatePlanPositionItem(
        com.ext.portlet.model.PlanPositionItem planPositionItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionItemLocalService.updatePlanPositionItem(planPositionItem,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planPositionItemLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planPositionItemLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<com.ext.portlet.model.PlanPositionItem> getAllIdsForPlanPositions(
        com.ext.portlet.model.PlanPositions planPositions)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionItemLocalService.getAllIdsForPlanPositions(planPositions);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanPositionItemLocalService getWrappedPlanPositionItemLocalService() {
        return _planPositionItemLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanPositionItemLocalService(
        PlanPositionItemLocalService planPositionItemLocalService) {
        _planPositionItemLocalService = planPositionItemLocalService;
    }

    public PlanPositionItemLocalService getWrappedService() {
        return _planPositionItemLocalService;
    }

    public void setWrappedService(
        PlanPositionItemLocalService planPositionItemLocalService) {
        _planPositionItemLocalService = planPositionItemLocalService;
    }
}
