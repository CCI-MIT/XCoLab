package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Role_;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the role_ service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Role_PersistenceImpl
 * @see Role_Util
 * @generated
 */
public interface Role_Persistence extends BasePersistence<Role_> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link Role_Util} to access the role_ persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the role_ in the entity cache if it is enabled.
    *
    * @param role_ the role_
    */
    public void cacheResult(com.ext.portlet.model.Role_ role_);

    /**
    * Caches the role_s in the entity cache if it is enabled.
    *
    * @param role_s the role_s
    */
    public void cacheResult(java.util.List<com.ext.portlet.model.Role_> role_s);

    /**
    * Creates a new role_ with the primary key. Does not add the role_ to the database.
    *
    * @param roleId the primary key for the new role_
    * @return the new role_
    */
    public com.ext.portlet.model.Role_ create(long roleId);

    /**
    * Removes the role_ with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param roleId the primary key of the role_
    * @return the role_ that was removed
    * @throws com.ext.portlet.NoSuchRole_Exception if a role_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Role_ remove(long roleId)
        throws com.ext.portlet.NoSuchRole_Exception,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.Role_ updateImpl(
        com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the role_ with the primary key or throws a {@link com.ext.portlet.NoSuchRole_Exception} if it could not be found.
    *
    * @param roleId the primary key of the role_
    * @return the role_
    * @throws com.ext.portlet.NoSuchRole_Exception if a role_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Role_ findByPrimaryKey(long roleId)
        throws com.ext.portlet.NoSuchRole_Exception,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the role_ with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param roleId the primary key of the role_
    * @return the role_, or <code>null</code> if a role_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Role_ fetchByPrimaryKey(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the role_s.
    *
    * @return the role_s
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Role_> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Role_> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Role_> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the role_s from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of role_s.
    *
    * @return the number of role_s
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the user_s associated with the role_.
    *
    * @param pk the primary key of the role_
    * @return the user_s associated with the role_
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.User_> getUser_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.User_> getUser_s(long pk,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.User_> getUser_s(long pk,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of user_s associated with the role_.
    *
    * @param pk the primary key of the role_
    * @return the number of user_s associated with the role_
    * @throws SystemException if a system exception occurred
    */
    public int getUser_sSize(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the user_ is associated with the role_.
    *
    * @param pk the primary key of the role_
    * @param user_PK the primary key of the user_
    * @return <code>true</code> if the user_ is associated with the role_; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsUser_(long pk, long user_PK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns <code>true</code> if the role_ has any user_s associated with it.
    *
    * @param pk the primary key of the role_ to check for associations with user_s
    * @return <code>true</code> if the role_ has any user_s associated with it; <code>false</code> otherwise
    * @throws SystemException if a system exception occurred
    */
    public boolean containsUser_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Adds an association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_PK the primary key of the user_
    * @throws SystemException if a system exception occurred
    */
    public void addUser_(long pk, long user_PK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Adds an association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_ the user_
    * @throws SystemException if a system exception occurred
    */
    public void addUser_(long pk, com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Adds an association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_PKs the primary keys of the user_s
    * @throws SystemException if a system exception occurred
    */
    public void addUser_s(long pk, long[] user_PKs)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Adds an association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_s the user_s
    * @throws SystemException if a system exception occurred
    */
    public void addUser_s(long pk,
        java.util.List<com.ext.portlet.model.User_> user_s)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Clears all associations between the role_ and its user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_ to clear the associated user_s from
    * @throws SystemException if a system exception occurred
    */
    public void clearUser_s(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_PK the primary key of the user_
    * @throws SystemException if a system exception occurred
    */
    public void removeUser_(long pk, long user_PK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_ the user_
    * @throws SystemException if a system exception occurred
    */
    public void removeUser_(long pk, com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_PKs the primary keys of the user_s
    * @throws SystemException if a system exception occurred
    */
    public void removeUser_s(long pk, long[] user_PKs)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_s the user_s
    * @throws SystemException if a system exception occurred
    */
    public void removeUser_s(long pk,
        java.util.List<com.ext.portlet.model.User_> user_s)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Sets the user_s associated with the role_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_PKs the primary keys of the user_s to be associated with the role_
    * @throws SystemException if a system exception occurred
    */
    public void setUser_s(long pk, long[] user_PKs)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Sets the user_s associated with the role_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
    *
    * @param pk the primary key of the role_
    * @param user_s the user_s to be associated with the role_
    * @throws SystemException if a system exception occurred
    */
    public void setUser_s(long pk,
        java.util.List<com.ext.portlet.model.User_> user_s)
        throws com.liferay.portal.kernel.exception.SystemException;
}
