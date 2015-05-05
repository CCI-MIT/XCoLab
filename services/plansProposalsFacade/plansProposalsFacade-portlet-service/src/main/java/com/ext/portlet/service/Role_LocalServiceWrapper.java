package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link Role_LocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see Role_LocalService
 * @generated
 */
public class Role_LocalServiceWrapper implements Role_LocalService,
    ServiceWrapper<Role_LocalService> {
    private Role_LocalService _role_LocalService;

    public Role_LocalServiceWrapper(Role_LocalService role_LocalService) {
        _role_LocalService = role_LocalService;
    }

    /**
    * Adds the role_ to the database. Also notifies the appropriate model listeners.
    *
    * @param role_ the role_
    * @return the role_ that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Role_ addRole_(
        com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.addRole_(role_);
    }

    /**
    * Creates a new role_ with the primary key. Does not add the role_ to the database.
    *
    * @param roleId the primary key for the new role_
    * @return the new role_
    */
    @Override
    public com.ext.portlet.model.Role_ createRole_(long roleId) {
        return _role_LocalService.createRole_(roleId);
    }

    /**
    * Deletes the role_ with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param roleId the primary key of the role_
    * @return the role_ that was removed
    * @throws PortalException if a role_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Role_ deleteRole_(long roleId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.deleteRole_(roleId);
    }

    /**
    * Deletes the role_ from the database. Also notifies the appropriate model listeners.
    *
    * @param role_ the role_
    * @return the role_ that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Role_ deleteRole_(
        com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.deleteRole_(role_);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _role_LocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Role_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Role_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.ext.portlet.model.Role_ fetchRole_(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.fetchRole_(roleId);
    }

    /**
    * Returns the role_ with the primary key.
    *
    * @param roleId the primary key of the role_
    * @return the role_
    * @throws PortalException if a role_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Role_ getRole_(long roleId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.getRole_(roleId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.getPersistedModel(primaryKeyObj);
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
    @Override
    public java.util.List<com.ext.portlet.model.Role_> getRole_s(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.getRole_s(start, end);
    }

    /**
    * Returns the number of role_s.
    *
    * @return the number of role_s
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getRole_sCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.getRole_sCount();
    }

    /**
    * Updates the role_ in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param role_ the role_
    * @return the role_ that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Role_ updateRole_(
        com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.updateRole_(role_);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addUser_Role_(long userId, long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_LocalService.addUser_Role_(userId, roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addUser_Role_(long userId, com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_LocalService.addUser_Role_(userId, role_);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addUser_Role_s(long userId, long[] roleIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_LocalService.addUser_Role_s(userId, roleIds);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addUser_Role_s(long userId,
        java.util.List<com.ext.portlet.model.Role_> Role_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_LocalService.addUser_Role_s(userId, Role_s);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void clearUser_Role_s(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_LocalService.clearUser_Role_s(userId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteUser_Role_(long userId, long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_LocalService.deleteUser_Role_(userId, roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteUser_Role_(long userId, com.ext.portlet.model.Role_ role_)
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_LocalService.deleteUser_Role_(userId, role_);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteUser_Role_s(long userId, long[] roleIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_LocalService.deleteUser_Role_s(userId, roleIds);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteUser_Role_s(long userId,
        java.util.List<com.ext.portlet.model.Role_> Role_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_LocalService.deleteUser_Role_s(userId, Role_s);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.Role_> getUser_Role_s(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.getUser_Role_s(userId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.Role_> getUser_Role_s(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.getUser_Role_s(userId, start, end);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.Role_> getUser_Role_s(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.getUser_Role_s(userId, start, end,
            orderByComparator);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getUser_Role_sCount(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.getUser_Role_sCount(userId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public boolean hasUser_Role_(long userId, long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.hasUser_Role_(userId, roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public boolean hasUser_Role_s(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _role_LocalService.hasUser_Role_s(userId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void setUser_Role_s(long userId, long[] roleIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        _role_LocalService.setUser_Role_s(userId, roleIds);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _role_LocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _role_LocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _role_LocalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public Role_LocalService getWrappedRole_LocalService() {
        return _role_LocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedRole_LocalService(Role_LocalService role_LocalService) {
        _role_LocalService = role_LocalService;
    }

    @Override
    public Role_LocalService getWrappedService() {
        return _role_LocalService;
    }

    @Override
    public void setWrappedService(Role_LocalService role_LocalService) {
        _role_LocalService = role_LocalService;
    }
}
