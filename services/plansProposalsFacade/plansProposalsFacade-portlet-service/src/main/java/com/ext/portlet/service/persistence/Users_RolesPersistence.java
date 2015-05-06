package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Users_Roles;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the users_ roles service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Users_RolesPersistenceImpl
 * @see Users_RolesUtil
 * @generated
 */
public interface Users_RolesPersistence extends BasePersistence<Users_Roles> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link Users_RolesUtil} to access the users_ roles persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the users_ roles in the entity cache if it is enabled.
    *
    * @param users_Roles the users_ roles
    */
    public void cacheResult(com.ext.portlet.model.Users_Roles users_Roles);

    /**
    * Caches the users_ roleses in the entity cache if it is enabled.
    *
    * @param users_Roleses the users_ roleses
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.Users_Roles> users_Roleses);

    /**
    * Creates a new users_ roles with the primary key. Does not add the users_ roles to the database.
    *
    * @param users_RolesPK the primary key for the new users_ roles
    * @return the new users_ roles
    */
    public com.ext.portlet.model.Users_Roles create(Users_RolesPK users_RolesPK);

    /**
    * Removes the users_ roles with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param users_RolesPK the primary key of the users_ roles
    * @return the users_ roles that was removed
    * @throws com.ext.portlet.NoSuchUsers_RolesException if a users_ roles with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Users_Roles remove(Users_RolesPK users_RolesPK)
        throws com.ext.portlet.NoSuchUsers_RolesException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.Users_Roles updateImpl(
        com.ext.portlet.model.Users_Roles users_Roles)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the users_ roles with the primary key or throws a {@link com.ext.portlet.NoSuchUsers_RolesException} if it could not be found.
    *
    * @param users_RolesPK the primary key of the users_ roles
    * @return the users_ roles
    * @throws com.ext.portlet.NoSuchUsers_RolesException if a users_ roles with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Users_Roles findByPrimaryKey(
        Users_RolesPK users_RolesPK)
        throws com.ext.portlet.NoSuchUsers_RolesException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the users_ roles with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param users_RolesPK the primary key of the users_ roles
    * @return the users_ roles, or <code>null</code> if a users_ roles with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Users_Roles fetchByPrimaryKey(
        Users_RolesPK users_RolesPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the users_ roleses.
    *
    * @return the users_ roleses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Users_Roles> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Users_Roles> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Users_Roles> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the users_ roleses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of users_ roleses.
    *
    * @return the number of users_ roleses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
