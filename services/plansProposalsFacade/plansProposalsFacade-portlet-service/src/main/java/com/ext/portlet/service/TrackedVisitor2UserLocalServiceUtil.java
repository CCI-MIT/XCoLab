package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for TrackedVisitor2User. This utility wraps
 * {@link com.ext.portlet.service.impl.TrackedVisitor2UserLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitor2UserLocalService
 * @see com.ext.portlet.service.base.TrackedVisitor2UserLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.TrackedVisitor2UserLocalServiceImpl
 * @generated
 */
public class TrackedVisitor2UserLocalServiceUtil {
    private static TrackedVisitor2UserLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.TrackedVisitor2UserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the tracked visitor2 user to the database. Also notifies the appropriate model listeners.
    *
    * @param trackedVisitor2User the tracked visitor2 user
    * @return the tracked visitor2 user that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.TrackedVisitor2User addTrackedVisitor2User(
        com.ext.portlet.model.TrackedVisitor2User trackedVisitor2User)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addTrackedVisitor2User(trackedVisitor2User);
    }

    /**
    * Creates a new tracked visitor2 user with the primary key. Does not add the tracked visitor2 user to the database.
    *
    * @param id the primary key for the new tracked visitor2 user
    * @return the new tracked visitor2 user
    */
    public static com.ext.portlet.model.TrackedVisitor2User createTrackedVisitor2User(
        long id) {
        return getService().createTrackedVisitor2User(id);
    }

    /**
    * Deletes the tracked visitor2 user with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the tracked visitor2 user
    * @return the tracked visitor2 user that was removed
    * @throws PortalException if a tracked visitor2 user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.TrackedVisitor2User deleteTrackedVisitor2User(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteTrackedVisitor2User(id);
    }

    /**
    * Deletes the tracked visitor2 user from the database. Also notifies the appropriate model listeners.
    *
    * @param trackedVisitor2User the tracked visitor2 user
    * @return the tracked visitor2 user that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.TrackedVisitor2User deleteTrackedVisitor2User(
        com.ext.portlet.model.TrackedVisitor2User trackedVisitor2User)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteTrackedVisitor2User(trackedVisitor2User);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitor2UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitor2UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.TrackedVisitor2User fetchTrackedVisitor2User(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchTrackedVisitor2User(id);
    }

    /**
    * Returns the tracked visitor2 user with the primary key.
    *
    * @param id the primary key of the tracked visitor2 user
    * @return the tracked visitor2 user
    * @throws PortalException if a tracked visitor2 user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.TrackedVisitor2User getTrackedVisitor2User(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getTrackedVisitor2User(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the tracked visitor2 users.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitor2UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of tracked visitor2 users
    * @param end the upper bound of the range of tracked visitor2 users (not inclusive)
    * @return the range of tracked visitor2 users
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.TrackedVisitor2User> getTrackedVisitor2Users(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getTrackedVisitor2Users(start, end);
    }

    /**
    * Returns the number of tracked visitor2 users.
    *
    * @return the number of tracked visitor2 users
    * @throws SystemException if a system exception occurred
    */
    public static int getTrackedVisitor2UsersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getTrackedVisitor2UsersCount();
    }

    /**
    * Updates the tracked visitor2 user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param trackedVisitor2User the tracked visitor2 user
    * @return the tracked visitor2 user that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.TrackedVisitor2User updateTrackedVisitor2User(
        com.ext.portlet.model.TrackedVisitor2User trackedVisitor2User)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateTrackedVisitor2User(trackedVisitor2User);
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

    public static java.lang.String findUuidForUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findUuidForUserId(userId);
    }

    public static com.ext.portlet.model.TrackedVisitor2User addIfNotExists(
        java.lang.String uuid, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addIfNotExists(uuid, userId);
    }

    public static void clearService() {
        _service = null;
    }

    public static TrackedVisitor2UserLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    TrackedVisitor2UserLocalService.class.getName());

            if (invokableLocalService instanceof TrackedVisitor2UserLocalService) {
                _service = (TrackedVisitor2UserLocalService) invokableLocalService;
            } else {
                _service = new TrackedVisitor2UserLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(TrackedVisitor2UserLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(TrackedVisitor2UserLocalService service) {
    }
}
