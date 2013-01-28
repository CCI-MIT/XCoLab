package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest phase local service. This utility wraps {@link com.ext.portlet.service.impl.ContestPhaseLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseLocalService
 * @see com.ext.portlet.service.base.ContestPhaseLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ContestPhaseLocalServiceImpl
 * @generated
 */
public class ContestPhaseLocalServiceUtil {
    private static ContestPhaseLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ContestPhaseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the contest phase to the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhase the contest phase
    * @return the contest phase that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase addContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addContestPhase(contestPhase);
    }

    /**
    * Creates a new contest phase with the primary key. Does not add the contest phase to the database.
    *
    * @param ContestPhasePK the primary key for the new contest phase
    * @return the new contest phase
    */
    public static com.ext.portlet.model.ContestPhase createContestPhase(
        long ContestPhasePK) {
        return getService().createContestPhase(ContestPhasePK);
    }

    /**
    * Deletes the contest phase with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @throws PortalException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContestPhase(long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContestPhase(ContestPhasePK);
    }

    /**
    * Deletes the contest phase from the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhase the contest phase
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContestPhase(contestPhase);
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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
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

    public static com.ext.portlet.model.ContestPhase fetchContestPhase(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchContestPhase(ContestPhasePK);
    }

    /**
    * Returns the contest phase with the primary key.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase
    * @throws PortalException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase getContestPhase(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhase(ContestPhasePK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> getContestPhases(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhases(start, end);
    }

    /**
    * Returns the number of contest phases.
    *
    * @return the number of contest phases
    * @throws SystemException if a system exception occurred
    */
    public static int getContestPhasesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhasesCount();
    }

    /**
    * Updates the contest phase in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhase the contest phase
    * @return the contest phase that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase updateContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestPhase(contestPhase);
    }

    /**
    * Updates the contest phase in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhase the contest phase
    * @param merge whether to merge the contest phase with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contest phase that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase updateContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestPhase(contestPhase, merge);
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

    public static java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlans(contestPhase);
    }

    public static com.ext.portlet.contests.ContestStatus getContestStatus(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestStatus(contestPhase);
    }

    public static java.util.List<java.lang.String> getPhaseColumns(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPhaseColumns(contestPhase);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhaseColumn> getPhaseColumnsRaw(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPhaseColumnsRaw(contestPhase);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhase> getPreviousPhases(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPreviousPhases(contestPhase);
    }

    public static com.ext.portlet.model.ContestPhase getNextContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getNextContestPhase(contestPhase);
    }

    public static boolean getPhaseActive(
        com.ext.portlet.model.ContestPhase contestPhase) {
        return getService().getPhaseActive(contestPhase);
    }

    public static java.util.List<com.ext.portlet.model.ContestPhase> getPhasesForContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPhasesForContest(contest);
    }

    public static com.ext.portlet.model.ContestPhase getActivePhaseForContest(
        com.ext.portlet.model.Contest contest)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getActivePhaseForContest(contest);
    }

    /**
    * from ContestPhaseImpl
    */
    public static com.ext.portlet.model.Contest getContest(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContest(contestPhase);
    }

    public static java.lang.String getName(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getName(contestPhase);
    }

    public static void clearService() {
        _service = null;
    }

    public static ContestPhaseLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestPhaseLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestPhaseLocalService.class.getName(), portletClassLoader);

            _service = new ContestPhaseLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestPhaseLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ContestPhaseLocalService.class);
        }

        return _service;
    }

    public void setService(ContestPhaseLocalService service) {
        MethodCache.remove(ContestPhaseLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestPhaseLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ContestPhaseLocalService.class);
    }
}
