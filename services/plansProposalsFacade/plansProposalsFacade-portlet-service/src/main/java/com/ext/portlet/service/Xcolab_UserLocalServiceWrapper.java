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
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenName(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByScreenName(begin, end,
            filter, ascendingOrder);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByScreenNameFilteredByCategory(begin,
            end, filter, memberCategory, ascendingOrder);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleName(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByRoleName(begin, end,
            filter, ascendingOrder);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSince(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByMemberSince(begin, end,
            filter, ascendingOrder);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByMemberSinceFilteredByCategory(begin,
            end, filter, memberCategory, ascendingOrder);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCount(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByActivityCount(begin,
            end, filter, ascendingOrder);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByActivityCountFilteredByCategory(begin,
            end, filter, memberCategory, ascendingOrder);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByPoints(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByPoints(begin, end,
            filter, ascendingOrder);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategoryFilter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUsersSortedByPointsFilteredByCategory(begin,
            end, filter, memberCategoryFilter, ascendingOrder);
    }

    @Override
    public long getUserActivityCount(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _xcolab_UserLocalService.getUserActivityCount(userId);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> findUsersByLoginIP(
        java.lang.String loginIP)
        throws com.liferay.portal.kernel.exception.SystemException {
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
