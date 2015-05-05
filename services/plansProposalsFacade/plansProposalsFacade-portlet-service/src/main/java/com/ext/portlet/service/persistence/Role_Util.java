package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Role_;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the role_ service. This utility wraps {@link Role_PersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Role_Persistence
 * @see Role_PersistenceImpl
 * @generated
 */
public class Role_Util {
    private static Role_Persistence _persistence;

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
    public static void clearCache(Role_ role_) {
        getPersistence().clearCache(role_);
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
    public static List<Role_> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Role_> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Role_> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static Role_ update(Role_ role_) throws SystemException {
        return getPersistence().update(role_);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static Role_ update(Role_ role_, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(role_, serviceContext);
    }

    /**
    * Caches the role_ in the entity cache if it is enabled.
    *
    * @param role_ the role_
    */
    public static void cacheResult(com.ext.portlet.model.Role_ role_) {
        getPersistence().cacheResult(role_);
    }

    /**
    * Caches the role_s in the entity cache if it is enabled.
    *
    * @param role_s the role_s
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.Role_> role_s) {
        getPersistence().cacheResult(role_s);
    }

    /**
    * Creates a new role_ with the primary key. Does not add the role_ to the database.
    *
    * @param roleId the primary key for the new role_
    * @return the new role_
    */
    public static com.ext.portlet.model.Role_ create(long roleId) {
        return getPersistence().create(roleId);
    }

    /**
    * Removes the role_ with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param roleId the primary key of the role_
    * @return the role_ that was removed
    * @throws com.ext.portlet.NoSuchRole_Exception if a role_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Role_ remove(long roleId)
        throws com.ext.portlet.NoSuchRole_Exception,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(roleId);
    }

    public static com.ext.portlet.model.Role_ updateImpl(
        com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(role_);
    }

    /**
    * Returns the role_ with the primary key or throws a {@link com.ext.portlet.NoSuchRole_Exception} if it could not be found.
    *
    * @param roleId the primary key of the role_
    * @return the role_
    * @throws com.ext.portlet.NoSuchRole_Exception if a role_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Role_ findByPrimaryKey(long roleId)
        throws com.ext.portlet.NoSuchRole_Exception,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(roleId);
    }

    /**
    * Returns the role_ with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param roleId the primary key of the role_
    * @return the role_, or <code>null</code> if a role_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Role_ fetchByPrimaryKey(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(roleId);
    }

    /**
    * Returns all the role_s.
    *
    * @return the role_s
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Role_> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the role_s.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Role_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of role_s
    * @param end the upper bound of the range of role_s (not inclusive)
    * @return the range of role_s
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Role_> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the role_s.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Role_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of role_s
    * @param end the upper bound of the range of role_s (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of role_s
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Role_> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the role_s from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of role_s.
    *
    * @return the number of role_s
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    /**
    * Returns all the user_s associated with the role_.
    *
    * @param pk the primary key of the role_
    * @return the user_s associated with the role_
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> getUser_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getUser_s(pk);
    }

    /**
    * Returns a range of all the user_s associated with the role_.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Role_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param pk the primary key of the role_
    * @param start the lower bound of the range of role_s
    * @param end the upper bound of the range of role_s (not inclusive)
    * @return the range of user_s associated with the role_
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> getUser_s(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getUser_s(pk, start, end);
    }

    /**
    * Returns an ordered range of all the user_s associated with the role_.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Role_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param pk the primary key of the role_
    * @param start the lower bound of the range of role_s
    * @param end the upper bound of the range of role_s (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of user_s associated with the role_
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> getUser_s(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getUser_s(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of user_s associated with the role_.
    *
    * @param pk the primary key of the role_
    * @return the number of user_s associated with the role_
    * @throws SystemException if a system exception occurred
    */
    public static int getUser_sSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getUser_sSize(pk);
    }

    /**
    * Returns <code>true</code> if the user_ is associated with the role_.
    *
    * @param pk the primary key of the role_
    * @param user_PK the primary key of the user_
    * @return <code>true</code> if the user_ is associated with the role_; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsUser_(long pk, long user_PK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsUser_(pk, user_PK);
    }

    /**
    * Returns <code>true</code> if the role_ has any user_s associated with it.
    *
    * @param pk the primary key of the role_ to check for associations with user_s
    * @return <code>true</code> if the role_ has any user_s associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsUser_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsUser_s(pk);
    }

    /**
    * Adds an association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_PK the primary key of the user_
    * @throws SystemException if a system exception occurred
    */
    public static void addUser_(long pk, long user_PK)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().addUser_(pk, user_PK);
    }

    /**
    * Adds an association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_ the user_
    * @throws SystemException if a system exception occurred
    */
    public static void addUser_(long pk, com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().addUser_(pk, user_);
    }

    /**
    * Adds an association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_PKs the primary keys of the user_s
    * @throws SystemException if a system exception occurred
    */
    public static void addUser_s(long pk, long[] user_PKs)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().addUser_s(pk, user_PKs);
    }

    /**
    * Adds an association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_s the user_s
    * @throws SystemException if a system exception occurred
    */
    public static void addUser_s(long pk,
        java.util.List<com.ext.portlet.model.User_> user_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().addUser_s(pk, user_s);
    }

    /**
    * Clears all associations between the role_ and its user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_ to clear the associated user_s from
    * @throws SystemException if a system exception occurred
    */
    public static void clearUser_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().clearUser_s(pk);
    }

    /**
    * Removes the association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_PK the primary key of the user_
    * @throws SystemException if a system exception occurred
    */
    public static void removeUser_(long pk, long user_PK)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeUser_(pk, user_PK);
    }

    /**
    * Removes the association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_ the user_
    * @throws SystemException if a system exception occurred
    */
    public static void removeUser_(long pk, com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeUser_(pk, user_);
    }

    /**
    * Removes the association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_PKs the primary keys of the user_s
    * @throws SystemException if a system exception occurred
    */
    public static void removeUser_s(long pk, long[] user_PKs)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeUser_s(pk, user_PKs);
    }

    /**
    * Removes the association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_s the user_s
    * @throws SystemException if a system exception occurred
    */
    public static void removeUser_s(long pk,
        java.util.List<com.ext.portlet.model.User_> user_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeUser_s(pk, user_s);
    }

    /**
    * Sets the user_s associated with the role_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_PKs the primary keys of the user_s to be associated with the role_
    * @throws SystemException if a system exception occurred
    */
    public static void setUser_s(long pk, long[] user_PKs)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().setUser_s(pk, user_PKs);
    }

    /**
    * Sets the user_s associated with the role_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_s the user_s to be associated with the role_
    * @throws SystemException if a system exception occurred
    */
    public static void setUser_s(long pk,
        java.util.List<com.ext.portlet.model.User_> user_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().setUser_s(pk, user_s);
    }

    public static Role_Persistence getPersistence() {
        if (_persistence == null) {
            _persistence = (Role_Persistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    Role_Persistence.class.getName());

            ReferenceRegistry.registerReference(Role_Util.class, "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(Role_Persistence persistence) {
    }
}
