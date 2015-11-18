package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link Xcolab_UserLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see Xcolab_UserLocalService
 * @generated
 */
public class Xcolab_UserLocalServiceWrapper implements Xcolab_UserLocalService,
    ServiceWrapper<Xcolab_UserLocalService> {
    private Xcolab_UserLocalService _xcolab_UserLocalService;

    public Xcolab_UserLocalServiceWrapper(
        Xcolab_UserLocalService xcolab_UserLocalService) {
        _xcolab_UserLocalService = xcolab_UserLocalService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _xcolab_UserLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _xcolab_UserLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _xcolab_UserLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByScreenNameAsc(begin,
            end, filter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByScreenNameAscFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByScreenNameDesc(begin,
            end, filter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByScreenNameDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleNameAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByRoleNameAsc(begin, end,
            filter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleNameDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByRoleNameDesc(begin,
            end, filter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByMemberSinceAsc(begin,
            end, filter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByMemberSinceAscFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByMemberSinceDesc(begin,
            end, filter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByMemberSinceDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByActivityCountAsc(begin,
            end, filter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByActivityCountAscFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByActivityCountDesc(begin,
            end, filter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByActivityCountDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsAsc(
        int begin, int end, java.lang.String filter) {
        return _xcolab_UserLocalService.getUsersSortedByPointsAsc(begin, end,
            filter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsDesc(
        int begin, int end, java.lang.String filter) {
        return _xcolab_UserLocalService.getUsersSortedByPointsDesc(begin, end,
            filter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategoryFilter) {
        return _xcolab_UserLocalService.getUsersSortedByPointsAscFilteredByCategory(begin,
            end, filter, memberCategoryFilter);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategoryFilter) {
        return _xcolab_UserLocalService.getUsersSortedByPointsDescFilteredByCategory(begin,
            end, filter, memberCategoryFilter);
    }

    @Override
    public java.util.List<java.lang.Long> getUserActivityCount(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUserActivityCount(userId);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> findUsersByLoginIP(
        java.lang.String loginIP) {
        return _xcolab_UserLocalService.findUsersByLoginIP(loginIP);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public Xcolab_UserLocalService getWrappedXcolab_UserLocalService() {
        return _xcolab_UserLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedXcolab_UserLocalService(
        Xcolab_UserLocalService xcolab_UserLocalService) {
        _xcolab_UserLocalService = xcolab_UserLocalService;
    }

    @Override
    public Xcolab_UserLocalService getWrappedService() {
        return _xcolab_UserLocalService;
    }

    @Override
    public void setWrappedService(
        Xcolab_UserLocalService xcolab_UserLocalService) {
        _xcolab_UserLocalService = xcolab_UserLocalService;
    }
}
