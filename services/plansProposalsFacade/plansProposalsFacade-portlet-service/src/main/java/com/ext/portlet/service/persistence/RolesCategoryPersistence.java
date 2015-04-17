package com.ext.portlet.service.persistence;

import com.ext.portlet.model.RolesCategory;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the roles category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RolesCategoryPersistenceImpl
 * @see RolesCategoryUtil
 * @generated
 */
public interface RolesCategoryPersistence extends BasePersistence<RolesCategory> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RolesCategoryUtil} to access the roles category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the roles category in the entity cache if it is enabled.
    *
    * @param rolesCategory the roles category
    */
    public void cacheResult(com.ext.portlet.model.RolesCategory rolesCategory);

    /**
    * Caches the roles categories in the entity cache if it is enabled.
    *
    * @param rolesCategories the roles categories
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.RolesCategory> rolesCategories);

    /**
    * Creates a new roles category with the primary key. Does not add the roles category to the database.
    *
    * @param roleId the primary key for the new roles category
    * @return the new roles category
    */
    public com.ext.portlet.model.RolesCategory create(long roleId);

    /**
    * Removes the roles category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param roleId the primary key of the roles category
    * @return the roles category that was removed
    * @throws com.ext.portlet.NoSuchRolesCategoryException if a roles category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.RolesCategory remove(long roleId)
        throws com.ext.portlet.NoSuchRolesCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.RolesCategory updateImpl(
        com.ext.portlet.model.RolesCategory rolesCategory)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the roles category with the primary key or throws a {@link com.ext.portlet.NoSuchRolesCategoryException} if it could not be found.
    *
    * @param roleId the primary key of the roles category
    * @return the roles category
    * @throws com.ext.portlet.NoSuchRolesCategoryException if a roles category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.RolesCategory findByPrimaryKey(long roleId)
        throws com.ext.portlet.NoSuchRolesCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the roles category with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param roleId the primary key of the roles category
    * @return the roles category, or <code>null</code> if a roles category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.RolesCategory fetchByPrimaryKey(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the roles categories.
    *
    * @return the roles categories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.RolesCategory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the roles categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.RolesCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of roles categories
    * @param end the upper bound of the range of roles categories (not inclusive)
    * @return the range of roles categories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.RolesCategory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the roles categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.RolesCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of roles categories
    * @param end the upper bound of the range of roles categories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of roles categories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.RolesCategory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the roles categories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of roles categories.
    *
    * @return the number of roles categories
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
