package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for PlanMeta. This utility wraps
 * {@link com.ext.portlet.service.impl.PlanMetaLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PlanMetaLocalService
 * @see com.ext.portlet.service.base.PlanMetaLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanMetaLocalServiceImpl
 * @generated
 */
public class PlanMetaLocalServiceUtil {
    private static PlanMetaLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanMetaLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan meta to the database. Also notifies the appropriate model listeners.
    *
    * @param planMeta the plan meta
    * @return the plan meta that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta addPlanMeta(
        com.ext.portlet.model.PlanMeta planMeta)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanMeta(planMeta);
    }

    /**
    * Creates a new plan meta with the primary key. Does not add the plan meta to the database.
    *
    * @param id the primary key for the new plan meta
    * @return the new plan meta
    */
    public static com.ext.portlet.model.PlanMeta createPlanMeta(long id) {
        return getService().createPlanMeta(id);
    }

    /**
    * Deletes the plan meta with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan meta
    * @return the plan meta that was removed
    * @throws PortalException if a plan meta with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta deletePlanMeta(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanMeta(id);
    }

    /**
    * Deletes the plan meta from the database. Also notifies the appropriate model listeners.
    *
    * @param planMeta the plan meta
    * @return the plan meta that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta deletePlanMeta(
        com.ext.portlet.model.PlanMeta planMeta)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanMeta(planMeta);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanMetaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanMetaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.PlanMeta fetchPlanMeta(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanMeta(id);
    }

    /**
    * Returns the plan meta with the primary key.
    *
    * @param id the primary key of the plan meta
    * @return the plan meta
    * @throws PortalException if a plan meta with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta getPlanMeta(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanMeta(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan metas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanMetaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan metas
    * @param end the upper bound of the range of plan metas (not inclusive)
    * @return the range of plan metas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanMeta> getPlanMetas(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanMetas(start, end);
    }

    /**
    * Returns the number of plan metas.
    *
    * @return the number of plan metas
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanMetasCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanMetasCount();
    }

    /**
    * Updates the plan meta in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planMeta the plan meta
    * @return the plan meta that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta updatePlanMeta(
        com.ext.portlet.model.PlanMeta planMeta)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanMeta(planMeta);
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

    public static com.ext.portlet.model.PlanMeta createPlanMeta(
        com.ext.portlet.model.PlanItem plan, java.lang.Long planTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlanMeta(plan, planTypeId);
    }

    public static com.ext.portlet.model.PlanMeta getCurrentForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCurrentForPlan(plan);
    }

    public static java.util.List<com.ext.portlet.model.PlanMeta> getAllForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllForPlan(plan);
    }

    public static com.ext.portlet.model.PlanMeta createNewVersionForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlan(plan);
    }

    public static com.ext.portlet.model.PlanMeta createNewVersionForPlan(
        com.ext.portlet.model.PlanItem plan, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlan(plan, store);
    }

    public static void store(com.ext.portlet.model.PlanMeta pm)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(pm);
    }

    public static void vote(com.ext.portlet.model.PlanMeta pm)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().vote(pm);
    }

    public static void unvote(com.ext.portlet.model.PlanMeta pm)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().unvote(pm);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanMetaLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanMetaLocalService.class.getName());

            if (invokableLocalService instanceof PlanMetaLocalService) {
                _service = (PlanMetaLocalService) invokableLocalService;
            } else {
                _service = new PlanMetaLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(PlanMetaLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(PlanMetaLocalService service) {
    }
}
