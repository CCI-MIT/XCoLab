package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ContestPhaseType. This utility wraps
 * {@link com.ext.portlet.service.impl.ContestPhaseTypeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseTypeLocalService
 * @see com.ext.portlet.service.base.ContestPhaseTypeLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ContestPhaseTypeLocalServiceImpl
 * @generated
 */
public class ContestPhaseTypeLocalServiceUtil {
    private static ContestPhaseTypeLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ContestPhaseTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the contest phase type to the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseType the contest phase type
    * @return the contest phase type that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseType addContestPhaseType(
        com.ext.portlet.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addContestPhaseType(contestPhaseType);
    }

    /**
    * Creates a new contest phase type with the primary key. Does not add the contest phase type to the database.
    *
    * @param id the primary key for the new contest phase type
    * @return the new contest phase type
    */
    public static com.ext.portlet.model.ContestPhaseType createContestPhaseType(
        long id) {
        return getService().createContestPhaseType(id);
    }

    /**
    * Deletes the contest phase type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase type
    * @return the contest phase type that was removed
    * @throws PortalException if a contest phase type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseType deleteContestPhaseType(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteContestPhaseType(id);
    }

    /**
    * Deletes the contest phase type from the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseType the contest phase type
    * @return the contest phase type that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseType deleteContestPhaseType(
        com.ext.portlet.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteContestPhaseType(contestPhaseType);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.ContestPhaseType fetchContestPhaseType(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchContestPhaseType(id);
    }

    /**
    * Returns the contest phase type with the primary key.
    *
    * @param id the primary key of the contest phase type
    * @return the contest phase type
    * @throws PortalException if a contest phase type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseType getContestPhaseType(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhaseType(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest phase types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phase types
    * @param end the upper bound of the range of contest phase types (not inclusive)
    * @return the range of contest phase types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhaseType> getContestPhaseTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhaseTypes(start, end);
    }

    /**
    * Returns the number of contest phase types.
    *
    * @return the number of contest phase types
    * @throws SystemException if a system exception occurred
    */
    public static int getContestPhaseTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestPhaseTypesCount();
    }

    /**
    * Updates the contest phase type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseType the contest phase type
    * @return the contest phase type that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseType updateContestPhaseType(
        com.ext.portlet.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestPhaseType(contestPhaseType);
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

    public static void clearService() {
        _service = null;
    }

    public static ContestPhaseTypeLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestPhaseTypeLocalService.class.getName());

            if (invokableLocalService instanceof ContestPhaseTypeLocalService) {
                _service = (ContestPhaseTypeLocalService) invokableLocalService;
            } else {
                _service = new ContestPhaseTypeLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ContestPhaseTypeLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ContestPhaseTypeLocalService service) {
    }
}
