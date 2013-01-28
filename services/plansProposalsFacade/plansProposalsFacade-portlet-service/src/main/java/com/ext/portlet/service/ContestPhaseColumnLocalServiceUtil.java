package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest phase column local service. This utility wraps {@link com.ext.portlet.service.impl.ContestPhaseColumnLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseColumnLocalService
 * @see com.ext.portlet.service.base.ContestPhaseColumnLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ContestPhaseColumnLocalServiceImpl
 * @generated
 */
public class ContestPhaseColumnLocalServiceUtil {
    private static ContestPhaseColumnLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ContestPhaseColumnLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the contest phase column to the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseColumn the contest phase column
    * @return the contest phase column that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseColumn addContestPhaseColumn(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addContestPhaseColumn(contestPhaseColumn);
    }

    /**
    * Creates a new contest phase column with the primary key. Does not add the contest phase column to the database.
    *
    * @param id the primary key for the new contest phase column
    * @return the new contest phase column
    */
    public static com.ext.portlet.model.ContestPhaseColumn createContestPhaseColumn(
        long id) {
        return getService().createContestPhaseColumn(id);
    }

    /**
    * Deletes the contest phase column with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase column
    * @throws PortalException if a contest phase column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContestPhaseColumn(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContestPhaseColumn(id);
    }

    /**
    * Deletes the contest phase column from the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseColumn the contest phase column
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContestPhaseColumn(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContestPhaseColumn(contestPhaseColumn);
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

    public static com.ext.portlet.model.ContestPhaseColumn fetchContestPhaseColumn(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchContestPhaseColumn(id);
    }

    /**
    * Returns the contest phase column with the primary key.
    *
    * @param id the primary key of the contest phase column
    * @return the contest phase column
    * @throws PortalException if a contest phase column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseColumn getContestPhaseColumn(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhaseColumn(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest phase columns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phase columns
    * @param end the upper bound of the range of contest phase columns (not inclusive)
    * @return the range of contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhaseColumn> getContestPhaseColumns(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhaseColumns(start, end);
    }

    /**
    * Returns the number of contest phase columns.
    *
    * @return the number of contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public static int getContestPhaseColumnsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhaseColumnsCount();
    }

    /**
    * Updates the contest phase column in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseColumn the contest phase column
    * @return the contest phase column that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseColumn updateContestPhaseColumn(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestPhaseColumn(contestPhaseColumn);
    }

    /**
    * Updates the contest phase column in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseColumn the contest phase column
    * @param merge whether to merge the contest phase column with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contest phase column that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseColumn updateContestPhaseColumn(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestPhaseColumn(contestPhaseColumn, merge);
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

    public static java.util.List<com.ext.portlet.model.ContestPhaseColumn> getPhaseColumns(
        java.lang.Long contestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPhaseColumns(contestPhasePK);
    }

    public static void clearService() {
        _service = null;
    }

    public static ContestPhaseColumnLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestPhaseColumnLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestPhaseColumnLocalService.class.getName(),
                    portletClassLoader);

            _service = new ContestPhaseColumnLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestPhaseColumnLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ContestPhaseColumnLocalService.class);
        }

        return _service;
    }

    public void setService(ContestPhaseColumnLocalService service) {
        MethodCache.remove(ContestPhaseColumnLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestPhaseColumnLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ContestPhaseColumnLocalService.class);
    }
}
