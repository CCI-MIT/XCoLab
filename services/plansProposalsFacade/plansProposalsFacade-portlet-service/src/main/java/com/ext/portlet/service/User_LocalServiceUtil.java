package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for User_. This utility wraps
 * {@link com.ext.portlet.service.impl.User_LocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see User_LocalService
 * @see com.ext.portlet.service.base.User_LocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.User_LocalServiceImpl
 * @generated
 */
public class User_LocalServiceUtil {
    private static User_LocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.User_LocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the user_ to the database. Also notifies the appropriate model listeners.
    *
    * @param user_ the user_
    * @return the user_ that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ addUser_(
        com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addUser_(user_);
    }

    /**
    * Creates a new user_ with the primary key. Does not add the user_ to the database.
    *
    * @param userId the primary key for the new user_
    * @return the new user_
    */
    public static com.ext.portlet.model.User_ createUser_(long userId) {
        return getService().createUser_(userId);
    }

    /**
    * Deletes the user_ with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param userId the primary key of the user_
    * @return the user_ that was removed
    * @throws PortalException if a user_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ deleteUser_(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteUser_(userId);
    }

    /**
    * Deletes the user_ from the database. Also notifies the appropriate model listeners.
    *
    * @param user_ the user_
    * @return the user_ that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ deleteUser_(
        com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteUser_(user_);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.User_ fetchUser_(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchUser_(userId);
    }

    /**
    * Returns the user_ with the primary key.
    *
    * @param userId the primary key of the user_
    * @return the user_
    * @throws PortalException if a user_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ getUser_(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getUser_(userId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the user_s.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of user_s
    * @param end the upper bound of the range of user_s (not inclusive)
    * @return the range of user_s
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> getUser_s(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUser_s(start, end);
    }

    /**
    * Returns the number of user_s.
    *
    * @return the number of user_s
    * @throws SystemException if a system exception occurred
    */
    public static int getUser_sCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUser_sCount();
    }

    /**
    * Updates the user_ in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param user_ the user_
    * @return the user_ that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ updateUser_(
        com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateUser_(user_);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static void addRole_User_(long roleId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().addRole_User_(roleId, userId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static void addRole_User_(long roleId,
        com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().addRole_User_(roleId, user_);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static void addRole_User_s(long roleId, long[] userIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().addRole_User_s(roleId, userIds);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static void addRole_User_s(long roleId,
        java.util.List<com.ext.portlet.model.User_> User_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().addRole_User_s(roleId, User_s);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static void clearRole_User_s(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().clearRole_User_s(roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static void deleteRole_User_(long roleId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteRole_User_(roleId, userId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static void deleteRole_User_(long roleId,
        com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteRole_User_(roleId, user_);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static void deleteRole_User_s(long roleId, long[] userIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteRole_User_s(roleId, userIds);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static void deleteRole_User_s(long roleId,
        java.util.List<com.ext.portlet.model.User_> User_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteRole_User_s(roleId, User_s);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> getRole_User_s(
        long roleId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getRole_User_s(roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> getRole_User_s(
        long roleId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getRole_User_s(roleId, start, end);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> getRole_User_s(
        long roleId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getRole_User_s(roleId, start, end, orderByComparator);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static int getRole_User_sCount(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getRole_User_sCount(roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static boolean hasRole_User_(long roleId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().hasRole_User_(roleId, userId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static boolean hasRole_User_s(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().hasRole_User_s(roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    public static void setRole_User_s(long roleId, long[] userIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setRole_User_s(roleId, userIds);
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

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByScreenNameAsc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByScreenNameAscFilteredByCategory(begin, end,
            filter, memberCategory);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByScreenNameDesc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByScreenNameDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByRoleNameAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByRoleNameAsc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByRoleNameDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByRoleNameDesc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByMemberSinceAsc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByMemberSinceAscFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByMemberSinceDesc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByMemberSinceDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByActivityCountAsc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByActivityCountAscFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByActivityCountDesc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByActivityCountDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    public static java.util.List<java.math.BigInteger> getUserActivityCount(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUserActivityCount(userId);
    }

    public static void clearService() {
        _service = null;
    }

    public static User_LocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    User_LocalService.class.getName());

            if (invokableLocalService instanceof User_LocalService) {
                _service = (User_LocalService) invokableLocalService;
            } else {
                _service = new User_LocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(User_LocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(User_LocalService service) {
    }
}
