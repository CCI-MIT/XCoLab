package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest phase ribbon type local service. This utility wraps {@link com.ext.portlet.service.impl.ContestPhaseRibbonTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseRibbonTypeLocalService
 * @see com.ext.portlet.service.base.ContestPhaseRibbonTypeLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ContestPhaseRibbonTypeLocalServiceImpl
 * @generated
 */
public class ContestPhaseRibbonTypeLocalServiceUtil {
    private static ContestPhaseRibbonTypeLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ContestPhaseRibbonTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the contest phase ribbon type to the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseRibbonType the contest phase ribbon type
    * @return the contest phase ribbon type that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseRibbonType addContestPhaseRibbonType(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addContestPhaseRibbonType(contestPhaseRibbonType);
    }

    /**
    * Creates a new contest phase ribbon type with the primary key. Does not add the contest phase ribbon type to the database.
    *
    * @param id the primary key for the new contest phase ribbon type
    * @return the new contest phase ribbon type
    */
    public static com.ext.portlet.model.ContestPhaseRibbonType createContestPhaseRibbonType(
        long id) {
        return getService().createContestPhaseRibbonType(id);
    }

    /**
    * Deletes the contest phase ribbon type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase ribbon type
    * @throws PortalException if a contest phase ribbon type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContestPhaseRibbonType(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContestPhaseRibbonType(id);
    }

    /**
    * Deletes the contest phase ribbon type from the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseRibbonType the contest phase ribbon type
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContestPhaseRibbonType(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContestPhaseRibbonType(contestPhaseRibbonType);
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

    public static com.ext.portlet.model.ContestPhaseRibbonType fetchContestPhaseRibbonType(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchContestPhaseRibbonType(id);
    }

    /**
    * Returns the contest phase ribbon type with the primary key.
    *
    * @param id the primary key of the contest phase ribbon type
    * @return the contest phase ribbon type
    * @throws PortalException if a contest phase ribbon type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseRibbonType getContestPhaseRibbonType(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhaseRibbonType(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest phase ribbon types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phase ribbon types
    * @param end the upper bound of the range of contest phase ribbon types (not inclusive)
    * @return the range of contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhaseRibbonType> getContestPhaseRibbonTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhaseRibbonTypes(start, end);
    }

    /**
    * Returns the number of contest phase ribbon types.
    *
    * @return the number of contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public static int getContestPhaseRibbonTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhaseRibbonTypesCount();
    }

    /**
    * Updates the contest phase ribbon type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseRibbonType the contest phase ribbon type
    * @return the contest phase ribbon type that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseRibbonType updateContestPhaseRibbonType(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestPhaseRibbonType(contestPhaseRibbonType);
    }

    /**
    * Updates the contest phase ribbon type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseRibbonType the contest phase ribbon type
    * @param merge whether to merge the contest phase ribbon type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contest phase ribbon type that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseRibbonType updateContestPhaseRibbonType(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateContestPhaseRibbonType(contestPhaseRibbonType, merge);
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

    public static void clearService() {
        _service = null;
    }

    public static ContestPhaseRibbonTypeLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestPhaseRibbonTypeLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestPhaseRibbonTypeLocalService.class.getName(),
                    portletClassLoader);

            _service = new ContestPhaseRibbonTypeLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestPhaseRibbonTypeLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ContestPhaseRibbonTypeLocalService.class);
        }

        return _service;
    }

    public void setService(ContestPhaseRibbonTypeLocalService service) {
        MethodCache.remove(ContestPhaseRibbonTypeLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestPhaseRibbonTypeLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ContestPhaseRibbonTypeLocalService.class);
    }
}
