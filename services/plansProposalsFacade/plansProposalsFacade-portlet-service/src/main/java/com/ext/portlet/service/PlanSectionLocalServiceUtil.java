package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for PlanSection. This utility wraps
 * {@link com.ext.portlet.service.impl.PlanSectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionLocalService
 * @see com.ext.portlet.service.base.PlanSectionLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanSectionLocalServiceImpl
 * @generated
 */
public class PlanSectionLocalServiceUtil {
    private static PlanSectionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanSectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan section to the database. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @return the plan section that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSection addPlanSection(
        com.ext.portlet.model.PlanSection planSection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanSection(planSection);
    }

    /**
    * Creates a new plan section with the primary key. Does not add the plan section to the database.
    *
    * @param id the primary key for the new plan section
    * @return the new plan section
    */
    public static com.ext.portlet.model.PlanSection createPlanSection(long id) {
        return getService().createPlanSection(id);
    }

    /**
    * Deletes the plan section with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan section
    * @return the plan section that was removed
    * @throws PortalException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSection deletePlanSection(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanSection(id);
    }

    /**
    * Deletes the plan section from the database. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @return the plan section that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSection deletePlanSection(
        com.ext.portlet.model.PlanSection planSection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanSection(planSection);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.PlanSection fetchPlanSection(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanSection(id);
    }

    /**
    * Returns the plan section with the primary key.
    *
    * @param id the primary key of the plan section
    * @return the plan section
    * @throws PortalException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSection getPlanSection(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanSection(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan sections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan sections
    * @param end the upper bound of the range of plan sections (not inclusive)
    * @return the range of plan sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSection> getPlanSections(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanSections(start, end);
    }

    /**
    * Returns the number of plan sections.
    *
    * @return the number of plan sections
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanSectionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanSectionsCount();
    }

    /**
    * Updates the plan section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @return the plan section that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSection updatePlanSection(
        com.ext.portlet.model.PlanSection planSection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanSection(planSection);
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

    public static com.ext.portlet.model.PlanSection getCurrentForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCurrentForPlanSectionDef(plan, def);
    }

    public static com.ext.portlet.model.PlanSection getCurrentForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def, boolean createOnEmpty)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCurrentForPlanSectionDef(plan, def, createOnEmpty);
    }

    public static com.ext.portlet.model.PlanSection getForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getForPlanSectionDef(plan, def);
    }

    public static com.ext.portlet.model.PlanSection getForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def, boolean current,
        boolean createOnEmpty)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getForPlanSectionDef(plan, def, current, createOnEmpty);
    }

    public static com.ext.portlet.model.PlanSection createForPlanFrom(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSection from, boolean store)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().createForPlanFrom(plan, from, store);
    }

    public static com.ext.portlet.model.PlanSection createNewVersionForPlanSectionDefinition(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlanSectionDefinition(plan, def);
    }

    public static com.ext.portlet.model.PlanSection createNewVersionForPlanSectionDefinition(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .createNewVersionForPlanSectionDefinition(plan, def, store);
    }

    public static java.util.List<com.ext.portlet.model.PlanSection> getAllForPlanDefinition(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllForPlanDefinition(plan, def);
    }

    public static void store(com.ext.portlet.model.PlanSection ps)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(ps);
    }

    public static com.ext.portlet.model.PlanSectionDefinition getDefinition(
        com.ext.portlet.model.PlanSection ps)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getDefinition(ps);
    }

    public static void addPlanReference(com.ext.portlet.model.PlanSection ps,
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().addPlanReference(ps, planId);
    }

    public static java.util.List<com.ext.portlet.model.PlanItem> getReferencedPlans(
        com.ext.portlet.model.PlanSection ps)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getReferencedPlans(ps);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanSectionLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanSectionLocalService.class.getName());

            if (invokableLocalService instanceof PlanSectionLocalService) {
                _service = (PlanSectionLocalService) invokableLocalService;
            } else {
                _service = new PlanSectionLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(PlanSectionLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(PlanSectionLocalService service) {
    }
}
