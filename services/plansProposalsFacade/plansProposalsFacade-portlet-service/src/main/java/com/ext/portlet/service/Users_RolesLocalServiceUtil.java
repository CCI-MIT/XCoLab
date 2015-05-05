package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Users_Roles. This utility wraps
 * {@link com.ext.portlet.service.impl.Users_RolesLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see Users_RolesLocalService
 * @see com.ext.portlet.service.base.Users_RolesLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.Users_RolesLocalServiceImpl
 * @generated
 */
public class Users_RolesLocalServiceUtil {
    private static Users_RolesLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.Users_RolesLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the users_ roles to the database. Also notifies the appropriate model listeners.
    *
    * @param users_Roles the users_ roles
    * @return the users_ roles that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Users_Roles addUsers_Roles(
        com.ext.portlet.model.Users_Roles users_Roles)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addUsers_Roles(users_Roles);
    }

    /**
    * Creates a new users_ roles with the primary key. Does not add the users_ roles to the database.
    *
    * @param users_RolesPK the primary key for the new users_ roles
    * @return the new users_ roles
    */
    public static com.ext.portlet.model.Users_Roles createUsers_Roles(
        com.ext.portlet.service.persistence.Users_RolesPK users_RolesPK) {
        return getService().createUsers_Roles(users_RolesPK);
    }

    /**
    * Deletes the users_ roles with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param users_RolesPK the primary key of the users_ roles
    * @return the users_ roles that was removed
    * @throws PortalException if a users_ roles with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Users_Roles deleteUsers_Roles(
        com.ext.portlet.service.persistence.Users_RolesPK users_RolesPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteUsers_Roles(users_RolesPK);
    }

    /**
    * Deletes the users_ roles from the database. Also notifies the appropriate model listeners.
    *
    * @param users_Roles the users_ roles
    * @return the users_ roles that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Users_Roles deleteUsers_Roles(
        com.ext.portlet.model.Users_Roles users_Roles)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteUsers_Roles(users_Roles);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Users_RolesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Users_RolesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.Users_Roles fetchUsers_Roles(
        com.ext.portlet.service.persistence.Users_RolesPK users_RolesPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchUsers_Roles(users_RolesPK);
    }

    /**
    * Returns the users_ roles with the primary key.
    *
    * @param users_RolesPK the primary key of the users_ roles
    * @return the users_ roles
    * @throws PortalException if a users_ roles with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Users_Roles getUsers_Roles(
        com.ext.portlet.service.persistence.Users_RolesPK users_RolesPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsers_Roles(users_RolesPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the users_ roleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Users_RolesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of users_ roleses
    * @param end the upper bound of the range of users_ roleses (not inclusive)
    * @return the range of users_ roleses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Users_Roles> getUsers_Roleses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsers_Roleses(start, end);
    }

    /**
    * Returns the number of users_ roleses.
    *
    * @return the number of users_ roleses
    * @throws SystemException if a system exception occurred
    */
    public static int getUsers_RolesesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsers_RolesesCount();
    }

    /**
    * Updates the users_ roles in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param users_Roles the users_ roles
    * @return the users_ roles that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Users_Roles updateUsers_Roles(
        com.ext.portlet.model.Users_Roles users_Roles)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateUsers_Roles(users_Roles);
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

    public static Users_RolesLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    Users_RolesLocalService.class.getName());

            if (invokableLocalService instanceof Users_RolesLocalService) {
                _service = (Users_RolesLocalService) invokableLocalService;
            } else {
                _service = new Users_RolesLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(Users_RolesLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(Users_RolesLocalService service) {
    }
}
