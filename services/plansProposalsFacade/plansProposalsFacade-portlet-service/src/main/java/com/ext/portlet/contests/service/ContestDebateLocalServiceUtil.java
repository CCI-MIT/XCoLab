package com.ext.portlet.contests.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest debate local service. This utility wraps {@link com.ext.portlet.contests.service.impl.ContestDebateLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestDebateLocalService
 * @see com.ext.portlet.contests.service.base.ContestDebateLocalServiceBaseImpl
 * @see com.ext.portlet.contests.service.impl.ContestDebateLocalServiceImpl
 * @generated
 */
public class ContestDebateLocalServiceUtil {
    private static ContestDebateLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.contests.service.impl.ContestDebateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the contest debate to the database. Also notifies the appropriate model listeners.
    *
    * @param contestDebate the contest debate
    * @return the contest debate that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestDebate addContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addContestDebate(contestDebate);
    }

    /**
    * Creates a new contest debate with the primary key. Does not add the contest debate to the database.
    *
    * @param id the primary key for the new contest debate
    * @return the new contest debate
    */
    public static com.ext.portlet.contests.model.ContestDebate createContestDebate(
        long id) {
        return getService().createContestDebate(id);
    }

    /**
    * Deletes the contest debate with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest debate
    * @throws PortalException if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContestDebate(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContestDebate(id);
    }

    /**
    * Deletes the contest debate from the database. Also notifies the appropriate model listeners.
    *
    * @param contestDebate the contest debate
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContestDebate(contestDebate);
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

    public static com.ext.portlet.contests.model.ContestDebate fetchContestDebate(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchContestDebate(id);
    }

    /**
    * Returns the contest debate with the primary key.
    *
    * @param id the primary key of the contest debate
    * @return the contest debate
    * @throws PortalException if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestDebate getContestDebate(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestDebate(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest debates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest debates
    * @param end the upper bound of the range of contest debates (not inclusive)
    * @return the range of contest debates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> getContestDebates(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestDebates(start, end);
    }

    /**
    * Returns the number of contest debates.
    *
    * @return the number of contest debates
    * @throws SystemException if a system exception occurred
    */
    public static int getContestDebatesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestDebatesCount();
    }

    /**
    * Updates the contest debate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestDebate the contest debate
    * @return the contest debate that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestDebate updateContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestDebate(contestDebate);
    }

    /**
    * Updates the contest debate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestDebate the contest debate
    * @param merge whether to merge the contest debate with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contest debate that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestDebate updateContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestDebate(contestDebate, merge);
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

    public static com.ext.portlet.contests.model.ContestDebate createContestDebate(
        java.lang.Long debateId, java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createContestDebate(debateId, contestId);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> getContestDebates(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestDebates(contestId);
    }

    public static void store(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(contestDebate);
    }

    public static void delete(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().delete(contestDebate);
    }

    public static void clearService() {
        _service = null;
    }

    public static ContestDebateLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestDebateLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestDebateLocalService.class.getName(),
                    portletClassLoader);

            _service = new ContestDebateLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestDebateLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ContestDebateLocalService.class);
        }

        return _service;
    }

    public void setService(ContestDebateLocalService service) {
        MethodCache.remove(ContestDebateLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestDebateLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ContestDebateLocalService.class);
    }
}
