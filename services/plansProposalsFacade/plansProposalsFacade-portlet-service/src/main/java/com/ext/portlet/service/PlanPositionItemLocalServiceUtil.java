package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for PlanPositionItem. This utility wraps
 * {@link com.ext.portlet.service.impl.PlanPositionItemLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionItemLocalService
 * @see com.ext.portlet.service.base.PlanPositionItemLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanPositionItemLocalServiceImpl
 * @generated
 */
public class PlanPositionItemLocalServiceUtil {
    private static PlanPositionItemLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanPositionItemLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan position item to the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionItem the plan position item
    * @return the plan position item that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositionItem addPlanPositionItem(
        com.ext.portlet.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanPositionItem(planPositionItem);
    }

    /**
    * Creates a new plan position item with the primary key. Does not add the plan position item to the database.
    *
    * @param planPositionItemPK the primary key for the new plan position item
    * @return the new plan position item
    */
    public static com.ext.portlet.model.PlanPositionItem createPlanPositionItem(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK) {
        return getService().createPlanPositionItem(planPositionItemPK);
    }

    /**
    * Deletes the plan position item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionItemPK the primary key of the plan position item
    * @return the plan position item that was removed
    * @throws PortalException if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositionItem deletePlanPositionItem(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanPositionItem(planPositionItemPK);
    }

    /**
    * Deletes the plan position item from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionItem the plan position item
    * @return the plan position item that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositionItem deletePlanPositionItem(
        com.ext.portlet.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanPositionItem(planPositionItem);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.PlanPositionItem fetchPlanPositionItem(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanPositionItem(planPositionItemPK);
    }

    /**
    * Returns the plan position item with the primary key.
    *
    * @param planPositionItemPK the primary key of the plan position item
    * @return the plan position item
    * @throws PortalException if a plan position item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositionItem getPlanPositionItem(
        com.ext.portlet.service.persistence.PlanPositionItemPK planPositionItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanPositionItem(planPositionItemPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan position items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan position items
    * @param end the upper bound of the range of plan position items (not inclusive)
    * @return the range of plan position items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanPositionItem> getPlanPositionItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanPositionItems(start, end);
    }

    /**
    * Returns the number of plan position items.
    *
    * @return the number of plan position items
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanPositionItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanPositionItemsCount();
    }

    /**
    * Updates the plan position item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPositionItem the plan position item
    * @return the plan position item that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositionItem updatePlanPositionItem(
        com.ext.portlet.model.PlanPositionItem planPositionItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanPositionItem(planPositionItem);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.util.List<com.ext.portlet.model.PlanPositionItem> getAllIdsForPlanPositions(
        com.ext.portlet.model.PlanPositions planPositions)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllIdsForPlanPositions(planPositions);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanPositionItemLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanPositionItemLocalService.class.getName());

            if (invokableLocalService instanceof PlanPositionItemLocalService) {
                _service = (PlanPositionItemLocalService) invokableLocalService;
            } else {
                _service = new PlanPositionItemLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(PlanPositionItemLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(PlanPositionItemLocalService service) {
    }
}
