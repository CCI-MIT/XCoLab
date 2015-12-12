package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Xcolab_User. This utility wraps
 * {@link com.ext.portlet.service.impl.Xcolab_UserLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see Xcolab_UserLocalService
 * @see com.ext.portlet.service.base.Xcolab_UserLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.Xcolab_UserLocalServiceImpl
 * @generated
 */
public class Xcolab_UserLocalServiceUtil {
    private static Xcolab_UserLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.Xcolab_UserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenName(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByScreenName(begin, end, filter,
            ascendingOrder);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByScreenNameFilteredByCategory(begin, end,
            filter, memberCategory, ascendingOrder);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleName(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByRoleName(begin, end, filter, ascendingOrder);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSince(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByMemberSince(begin, end, filter,
            ascendingOrder);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByMemberSinceFilteredByCategory(begin, end,
            filter, memberCategory, ascendingOrder);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCount(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByActivityCount(begin, end, filter,
            ascendingOrder);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByActivityCountFilteredByCategory(begin, end,
            filter, memberCategory, ascendingOrder);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByPoints(
        int begin, int end, java.lang.String filter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByPoints(begin, end, filter, ascendingOrder);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategoryFilter, boolean ascendingOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByPointsFilteredByCategory(begin, end,
            filter, memberCategoryFilter, ascendingOrder);
    }

    public static java.util.List<java.lang.Long> getUserActivityCount(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUserActivityCount(userId);
    }

    public static java.util.List<com.liferay.portal.model.User> findUsersByLoginIP(
        java.lang.String loginIP)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findUsersByLoginIP(loginIP);
    }

    public static void clearService() {
        _service = null;
    }

    public static Xcolab_UserLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    Xcolab_UserLocalService.class.getName());

            if (invokableLocalService instanceof Xcolab_UserLocalService) {
                _service = (Xcolab_UserLocalService) invokableLocalService;
            } else {
                _service = new Xcolab_UserLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(Xcolab_UserLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(Xcolab_UserLocalService service) {
    }
}
