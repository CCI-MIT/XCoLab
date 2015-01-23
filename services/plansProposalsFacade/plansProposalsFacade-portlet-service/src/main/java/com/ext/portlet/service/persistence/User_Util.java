package com.ext.portlet.service.persistence;

import com.ext.portlet.model.User_;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the user_ service. This utility wraps {@link User_PersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see User_Persistence
 * @see User_PersistenceImpl
 * @generated
 */
public class User_Util {
    private static User_Persistence _persistence;

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
    public static void clearCache(User_ user_) {
        getPersistence().clearCache(user_);
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
    public static List<User_> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<User_> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<User_> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static User_ update(User_ user_) throws SystemException {
        return getPersistence().update(user_);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static User_ update(User_ user_, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(user_, serviceContext);
    }

    /**
    * Returns all the user_s where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching user_s
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId);
    }

    /**
    * Returns a range of all the user_s where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of user_s
    * @param end the upper bound of the range of user_s (not inclusive)
    * @return the range of matching user_s
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    /**
    * Returns an ordered range of all the user_s where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of user_s
    * @param end the upper bound of the range of user_s (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching user_s
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first user_ in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user_
    * @throws com.ext.portlet.NoSuchUser_Exception if a matching user_ could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ findByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchUser_Exception,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the first user_ in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user_, or <code>null</code> if a matching user_ could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ fetchByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the last user_ in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user_
    * @throws com.ext.portlet.NoSuchUser_Exception if a matching user_ could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ findByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchUser_Exception,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the last user_ in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user_, or <code>null</code> if a matching user_ could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ fetchByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_Last(userId, orderByComparator);
    }

    /**
    * Removes all the user_s where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUserId(userId);
    }

    /**
    * Returns the number of user_s where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching user_s
    * @throws SystemException if a system exception occurred
    */
    public static int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUserId(userId);
    }

    /**
    * Caches the user_ in the entity cache if it is enabled.
    *
    * @param user_ the user_
    */
    public static void cacheResult(com.ext.portlet.model.User_ user_) {
        getPersistence().cacheResult(user_);
    }

    /**
    * Caches the user_s in the entity cache if it is enabled.
    *
    * @param user_s the user_s
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.User_> user_s) {
        getPersistence().cacheResult(user_s);
    }

    /**
    * Creates a new user_ with the primary key. Does not add the user_ to the database.
    *
    * @param userId the primary key for the new user_
    * @return the new user_
    */
    public static com.ext.portlet.model.User_ create(long userId) {
        return getPersistence().create(userId);
    }

    /**
    * Removes the user_ with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param userId the primary key of the user_
    * @return the user_ that was removed
    * @throws com.ext.portlet.NoSuchUser_Exception if a user_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ remove(long userId)
        throws com.ext.portlet.NoSuchUser_Exception,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(userId);
    }

    public static com.ext.portlet.model.User_ updateImpl(
        com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(user_);
    }

    /**
    * Returns the user_ with the primary key or throws a {@link com.ext.portlet.NoSuchUser_Exception} if it could not be found.
    *
    * @param userId the primary key of the user_
    * @return the user_
    * @throws com.ext.portlet.NoSuchUser_Exception if a user_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ findByPrimaryKey(long userId)
        throws com.ext.portlet.NoSuchUser_Exception,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(userId);
    }

    /**
    * Returns the user_ with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param userId the primary key of the user_
    * @return the user_, or <code>null</code> if a user_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.User_ fetchByPrimaryKey(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(userId);
    }

    /**
    * Returns all the user_s.
    *
    * @return the user_s
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<com.ext.portlet.model.User_> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the user_s.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of user_s
    * @param end the upper bound of the range of user_s (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of user_s
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.User_> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the user_s from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of user_s.
    *
    * @return the number of user_s
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    /**
    * Returns all the role_s associated with the user_.
    *
    * @param pk the primary key of the user_
    * @return the role_s associated with the user_
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Role_> getRole_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getRole_s(pk);
    }

    /**
    * Returns a range of all the role_s associated with the user_.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param pk the primary key of the user_
    * @param start the lower bound of the range of user_s
    * @param end the upper bound of the range of user_s (not inclusive)
    * @return the range of role_s associated with the user_
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Role_> getRole_s(
        long pk, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getRole_s(pk, start, end);
    }

    /**
    * Returns an ordered range of all the role_s associated with the user_.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param pk the primary key of the user_
    * @param start the lower bound of the range of user_s
    * @param end the upper bound of the range of user_s (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of role_s associated with the user_
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Role_> getRole_s(
        long pk, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getRole_s(pk, start, end, orderByComparator);
    }

    /**
    * Returns the number of role_s associated with the user_.
    *
    * @param pk the primary key of the user_
    * @return the number of role_s associated with the user_
    * @throws SystemException if a system exception occurred
    */
    public static int getRole_sSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().getRole_sSize(pk);
    }

    /**
    * Returns <code>true</code> if the role_ is associated with the user_.
    *
    * @param pk the primary key of the user_
    * @param role_PK the primary key of the role_
    * @return <code>true</code> if the role_ is associated with the user_; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsRole_(long pk, long role_PK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsRole_(pk, role_PK);
    }

    /**
    * Returns <code>true</code> if the user_ has any role_s associated with it.
    *
    * @param pk the primary key of the user_ to check for associations with role_s
    * @return <code>true</code> if the user_ has any role_s associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public static boolean containsRole_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().containsRole_s(pk);
    }

    /**
    * Adds an association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_PK the primary key of the role_
    * @throws SystemException if a system exception occurred
    */
    public static void addRole_(long pk, long role_PK)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().addRole_(pk, role_PK);
    }

    /**
    * Adds an association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_ the role_
    * @throws SystemException if a system exception occurred
    */
    public static void addRole_(long pk, com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().addRole_(pk, role_);
    }

    /**
    * Adds an association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_PKs the primary keys of the role_s
    * @throws SystemException if a system exception occurred
    */
    public static void addRole_s(long pk, long[] role_PKs)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().addRole_s(pk, role_PKs);
    }

    /**
    * Adds an association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_s the role_s
    * @throws SystemException if a system exception occurred
    */
    public static void addRole_s(long pk,
        java.util.List<com.ext.portlet.model.Role_> role_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().addRole_s(pk, role_s);
    }

    /**
    * Clears all associations between the user_ and its role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_ to clear the associated role_s from
    * @throws SystemException if a system exception occurred
    */
    public static void clearRole_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().clearRole_s(pk);
    }

    /**
    * Removes the association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_PK the primary key of the role_
    * @throws SystemException if a system exception occurred
    */
    public static void removeRole_(long pk, long role_PK)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeRole_(pk, role_PK);
    }

    /**
    * Removes the association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_ the role_
    * @throws SystemException if a system exception occurred
    */
    public static void removeRole_(long pk, com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeRole_(pk, role_);
    }

    /**
    * Removes the association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_PKs the primary keys of the role_s
    * @throws SystemException if a system exception occurred
    */
    public static void removeRole_s(long pk, long[] role_PKs)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeRole_s(pk, role_PKs);
    }

    /**
    * Removes the association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_s the role_s
    * @throws SystemException if a system exception occurred
    */
    public static void removeRole_s(long pk,
        java.util.List<com.ext.portlet.model.Role_> role_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeRole_s(pk, role_s);
    }

    /**
    * Sets the role_s associated with the user_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_PKs the primary keys of the role_s to be associated with the user_
    * @throws SystemException if a system exception occurred
    */
    public static void setRole_s(long pk, long[] role_PKs)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().setRole_s(pk, role_PKs);
    }

    /**
    * Sets the role_s associated with the user_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_s the role_s to be associated with the user_
    * @throws SystemException if a system exception occurred
    */
    public static void setRole_s(long pk,
        java.util.List<com.ext.portlet.model.Role_> role_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().setRole_s(pk, role_s);
    }

    public static User_Persistence getPersistence() {
        if (_persistence == null) {
            _persistence = (User_Persistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    User_Persistence.class.getName());

            ReferenceRegistry.registerReference(User_Util.class, "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(User_Persistence persistence) {
    }
}
