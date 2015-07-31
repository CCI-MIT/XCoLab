package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Users_Roles;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the users_ roles service. This utility wraps {@link Users_RolesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Users_RolesPersistence
 * @see Users_RolesPersistenceImpl
 * @generated
 */
public class Users_RolesUtil {
    private static Users_RolesPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(Users_Roles users_Roles) {
        getPersistence().clearCache(users_Roles);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<Users_Roles> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Users_Roles> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Users_Roles> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static Users_Roles update(Users_Roles users_Roles)
        throws SystemException {
        return getPersistence().update(users_Roles);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static Users_Roles update(Users_Roles users_Roles,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(users_Roles, serviceContext);
    }

    /**
    * Caches the users_ roles in the entity cache if it is enabled.
    *
    * @param users_Roles the users_ roles
    */
    public static void cacheResult(
        com.ext.portlet.model.Users_Roles users_Roles) {
        getPersistence().cacheResult(users_Roles);
    }

    /**
    * Caches the users_ roleses in the entity cache if it is enabled.
    *
    * @param users_Roleses the users_ roleses
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.Users_Roles> users_Roleses) {
        getPersistence().cacheResult(users_Roleses);
    }

    /**
    * Creates a new users_ roles with the primary key. Does not add the users_ roles to the database.
    *
    * @param users_RolesPK the primary key for the new users_ roles
    * @return the new users_ roles
    */
    public static com.ext.portlet.model.Users_Roles create(
        Users_RolesPK users_RolesPK) {
        return getPersistence().create(users_RolesPK);
    }

    /**
    * Removes the users_ roles with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param users_RolesPK the primary key of the users_ roles
    * @return the users_ roles that was removed
    * @throws com.ext.portlet.NoSuchUsers_RolesException if a users_ roles with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Users_Roles remove(
        Users_RolesPK users_RolesPK)
        throws com.ext.portlet.NoSuchUsers_RolesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(users_RolesPK);
    }

    public static com.ext.portlet.model.Users_Roles updateImpl(
        com.ext.portlet.model.Users_Roles users_Roles)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(users_Roles);
    }

    /**
    * Returns the users_ roles with the primary key or throws a {@link com.ext.portlet.NoSuchUsers_RolesException} if it could not be found.
    *
    * @param users_RolesPK the primary key of the users_ roles
    * @return the users_ roles
    * @throws com.ext.portlet.NoSuchUsers_RolesException if a users_ roles with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Users_Roles findByPrimaryKey(
        Users_RolesPK users_RolesPK)
        throws com.ext.portlet.NoSuchUsers_RolesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(users_RolesPK);
    }

    /**
    * Returns the users_ roles with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param users_RolesPK the primary key of the users_ roles
    * @return the users_ roles, or <code>null</code> if a users_ roles with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Users_Roles fetchByPrimaryKey(
        Users_RolesPK users_RolesPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(users_RolesPK);
    }

    /**
    * Returns all the users_ roleses.
    *
    * @return the users_ roleses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Users_Roles> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<com.ext.portlet.model.Users_Roles> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the users_ roleses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Users_RolesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of users_ roleses
    * @param end the upper bound of the range of users_ roleses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of users_ roleses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Users_Roles> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the users_ roleses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of users_ roleses.
    *
    * @return the number of users_ roleses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static Users_RolesPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (Users_RolesPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    Users_RolesPersistence.class.getName());

            ReferenceRegistry.registerReference(Users_RolesUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(Users_RolesPersistence persistence) {
    }
}
