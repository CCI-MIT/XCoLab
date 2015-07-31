package com.ext.portlet.service.persistence;

import com.ext.portlet.model.User_;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the user_ service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see User_PersistenceImpl
 * @see User_Util
 * @generated
 */
public interface User_Persistence extends BasePersistence<User_> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link User_Util} to access the user_ persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the user_s where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching user_s
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.User_> findByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.User_> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.User_> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first user_ in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user_
    * @throws com.ext.portlet.NoSuchUser_Exception if a matching user_ could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.User_ findByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchUser_Exception,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first user_ in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching user_, or <code>null</code> if a matching user_ could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.User_ fetchByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last user_ in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user_
    * @throws com.ext.portlet.NoSuchUser_Exception if a matching user_ could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.User_ findByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchUser_Exception,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last user_ in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching user_, or <code>null</code> if a matching user_ could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.User_ fetchByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the user_s where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of user_s where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching user_s
    * @throws SystemException if a system exception occurred
    */
    public int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the user_ in the entity cache if it is enabled.
    *
    * @param user_ the user_
    */
    public void cacheResult(com.ext.portlet.model.User_ user_);

    /**
    * Caches the user_s in the entity cache if it is enabled.
    *
    * @param user_s the user_s
    */
    public void cacheResult(java.util.List<com.ext.portlet.model.User_> user_s);

    /**
    * Creates a new user_ with the primary key. Does not add the user_ to the database.
    *
    * @param userId the primary key for the new user_
    * @return the new user_
    */
    public com.ext.portlet.model.User_ create(long userId);

    /**
    * Removes the user_ with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param userId the primary key of the user_
    * @return the user_ that was removed
    * @throws com.ext.portlet.NoSuchUser_Exception if a user_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.User_ remove(long userId)
        throws com.ext.portlet.NoSuchUser_Exception,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.User_ updateImpl(
        com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the user_ with the primary key or throws a {@link com.ext.portlet.NoSuchUser_Exception} if it could not be found.
    *
    * @param userId the primary key of the user_
    * @return the user_
    * @throws com.ext.portlet.NoSuchUser_Exception if a user_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.User_ findByPrimaryKey(long userId)
        throws com.ext.portlet.NoSuchUser_Exception,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the user_ with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param userId the primary key of the user_
    * @return the user_, or <code>null</code> if a user_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.User_ fetchByPrimaryKey(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the user_s.
    *
    * @return the user_s
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.User_> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.User_> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.User_> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the user_s from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of user_s.
    *
    * @return the number of user_s
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the role_s associated with the user_.
    *
    * @param pk the primary key of the user_
    * @return the role_s associated with the user_
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Role_> getRole_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Role_> getRole_s(long pk,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Role_> getRole_s(long pk,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of role_s associated with the user_.
    *
    * @param pk the primary key of the user_
    * @return the number of role_s associated with the user_
    * @throws SystemException if a system exception occurred
    */
    public int getRole_sSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the role_ is associated with the user_.
    *
    * @param pk the primary key of the user_
    * @param role_PK the primary key of the role_
    * @return <code>true</code> if the role_ is associated with the user_; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsRole_(long pk, long role_PK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the user_ has any role_s associated with it.
    *
    * @param pk the primary key of the user_ to check for associations with role_s
    * @return <code>true</code> if the user_ has any role_s associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsRole_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Adds an association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_PK the primary key of the role_
    * @throws SystemException if a system exception occurred
    */
    public void addRole_(long pk, long role_PK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Adds an association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_ the role_
    * @throws SystemException if a system exception occurred
    */
    public void addRole_(long pk, com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Adds an association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_PKs the primary keys of the role_s
    * @throws SystemException if a system exception occurred
    */
    public void addRole_s(long pk, long[] role_PKs)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Adds an association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_s the role_s
    * @throws SystemException if a system exception occurred
    */
    public void addRole_s(long pk,
        java.util.List<com.ext.portlet.model.Role_> role_s)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Clears all associations between the user_ and its role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_ to clear the associated role_s from
    * @throws SystemException if a system exception occurred
    */
    public void clearRole_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_PK the primary key of the role_
    * @throws SystemException if a system exception occurred
    */
    public void removeRole_(long pk, long role_PK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_ the role_
    * @throws SystemException if a system exception occurred
    */
    public void removeRole_(long pk, com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_PKs the primary keys of the role_s
    * @throws SystemException if a system exception occurred
    */
    public void removeRole_s(long pk, long[] role_PKs)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_s the role_s
    * @throws SystemException if a system exception occurred
    */
    public void removeRole_s(long pk,
        java.util.List<com.ext.portlet.model.Role_> role_s)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Sets the role_s associated with the user_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_PKs the primary keys of the role_s to be associated with the user_
    * @throws SystemException if a system exception occurred
    */
    public void setRole_s(long pk, long[] role_PKs)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Sets the role_s associated with the user_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the user_
    * @param role_s the role_s to be associated with the user_
    * @throws SystemException if a system exception occurred
    */
    public void setRole_s(long pk,
        java.util.List<com.ext.portlet.model.Role_> role_s)
        throws com.liferay.portal.kernel.exception.SystemException;
}
