package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link User_LocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see User_LocalService
 * @generated
 */
public class User_LocalServiceWrapper implements User_LocalService,
    ServiceWrapper<User_LocalService> {
    private User_LocalService _user_LocalService;

    public User_LocalServiceWrapper(User_LocalService user_LocalService) {
        _user_LocalService = user_LocalService;
    }

    /**
    * Adds the user_ to the database. Also notifies the appropriate model listeners.
    *
    * @param user_ the user_
    * @return the user_ that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.User_ addUser_(
        com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.addUser_(user_);
    }

    /**
    * Creates a new user_ with the primary key. Does not add the user_ to the database.
    *
    * @param userId the primary key for the new user_
    * @return the new user_
    */
    @Override
    public com.ext.portlet.model.User_ createUser_(long userId) {
        return _user_LocalService.createUser_(userId);
    }

    /**
    * Deletes the user_ with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param userId the primary key of the user_
    * @return the user_ that was removed
    * @throws PortalException if a user_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.User_ deleteUser_(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.deleteUser_(userId);
    }

    /**
    * Deletes the user_ from the database. Also notifies the appropriate model listeners.
    *
    * @param user_ the user_
    * @return the user_ that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.User_ deleteUser_(
        com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.deleteUser_(user_);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _user_LocalService.dynamicQuery();
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
        return _user_LocalService.dynamicQuery(dynamicQuery);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.dynamicQuery(dynamicQuery, start, end);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _user_LocalService.dynamicQueryCount(dynamicQuery);
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
        return _user_LocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.ext.portlet.model.User_ fetchUser_(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.fetchUser_(userId);
    }

    /**
    * Returns the user_ with the primary key.
    *
    * @param userId the primary key of the user_
    * @return the user_
    * @throws PortalException if a user_ with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.User_ getUser_(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUser_(userId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getPersistedModel(primaryKeyObj);
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
    @Override
    public java.util.List<com.ext.portlet.model.User_> getUser_s(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUser_s(start, end);
    }

    /**
    * Returns the number of user_s.
    *
    * @return the number of user_s
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getUser_sCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUser_sCount();
    }

    /**
    * Updates the user_ in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param user_ the user_
    * @return the user_ that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.User_ updateUser_(
        com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.updateUser_(user_);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addRole_User_(long roleId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_LocalService.addRole_User_(roleId, userId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addRole_User_(long roleId, com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_LocalService.addRole_User_(roleId, user_);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addRole_User_s(long roleId, long[] userIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_LocalService.addRole_User_s(roleId, userIds);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addRole_User_s(long roleId,
        java.util.List<com.ext.portlet.model.User_> User_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_LocalService.addRole_User_s(roleId, User_s);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void clearRole_User_s(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_LocalService.clearRole_User_s(roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteRole_User_(long roleId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_LocalService.deleteRole_User_(roleId, userId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteRole_User_(long roleId, com.ext.portlet.model.User_ user_)
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_LocalService.deleteRole_User_(roleId, user_);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteRole_User_s(long roleId, long[] userIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_LocalService.deleteRole_User_s(roleId, userIds);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteRole_User_s(long roleId,
        java.util.List<com.ext.portlet.model.User_> User_s)
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_LocalService.deleteRole_User_s(roleId, User_s);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.User_> getRole_User_s(
        long roleId) throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getRole_User_s(roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.User_> getRole_User_s(
        long roleId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getRole_User_s(roleId, start, end);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.User_> getRole_User_s(
        long roleId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getRole_User_s(roleId, start, end,
            orderByComparator);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getRole_User_sCount(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getRole_User_sCount(roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public boolean hasRole_User_(long roleId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.hasRole_User_(roleId, userId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public boolean hasRole_User_s(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.hasRole_User_s(roleId);
    }

    /**
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void setRole_User_s(long roleId, long[] userIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        _user_LocalService.setRole_User_s(roleId, userIds);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _user_LocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _user_LocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _user_LocalService.invokeMethod(name, parameterTypes, arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByScreenNameAsc(begin, end,
            filter);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByScreenNameAscFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByScreenNameDesc(begin, end,
            filter);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByScreenNameDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByRoleNameAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByRoleNameAsc(begin, end, filter);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByRoleNameDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByRoleNameDesc(begin, end,
            filter);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByMemberSinceAsc(begin, end,
            filter);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByMemberSinceAscFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByMemberSinceDesc(begin, end,
            filter);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByMemberSinceDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByActivityCountAsc(begin, end,
            filter);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByActivityCountAscFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByActivityCountDesc(begin, end,
            filter);
    }

    @Override
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUsersSortedByActivityCountDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<java.math.BigInteger> getUserActivityCount(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _user_LocalService.getUserActivityCount(userId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public User_LocalService getWrappedUser_LocalService() {
        return _user_LocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedUser_LocalService(User_LocalService user_LocalService) {
        _user_LocalService = user_LocalService;
    }

    @Override
    public User_LocalService getWrappedService() {
        return _user_LocalService;
    }

    @Override
    public void setWrappedService(User_LocalService user_LocalService) {
        _user_LocalService = user_LocalService;
    }
}
