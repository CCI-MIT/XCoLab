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

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByScreenNameAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByScreenNameAscFilteredByCategory(begin, end,
            filter, memberCategory);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByScreenNameDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByScreenNameDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleNameAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByRoleNameAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleNameDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByRoleNameDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByMemberSinceAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByMemberSinceAscFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByMemberSinceDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByMemberSinceDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByActivityCountAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByActivityCountAscFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUsersSortedByActivityCountDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getUsersSortedByActivityCountDescFilteredByCategory(begin,
            end, filter, memberCategory);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsAsc(
        int begin, int end, java.lang.String filter) {
        return getService().getUsersSortedByPointsAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsDesc(
        int begin, int end, java.lang.String filter) {
        return getService().getUsersSortedByPointsDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategoryFilter) {
        return getService()
                   .getUsersSortedByPointsAscFilteredByCategory(begin, end,
            filter, memberCategoryFilter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String memberCategoryFilter) {
        return getService()
                   .getUsersSortedByPointsDescFilteredByCategory(begin, end,
            filter, memberCategoryFilter);
    }

    public static java.util.List<java.lang.Long> getUserActivityCount(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getUserActivityCount(userId);
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
