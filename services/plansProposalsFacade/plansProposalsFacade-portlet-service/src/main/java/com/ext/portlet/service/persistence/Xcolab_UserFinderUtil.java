package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class Xcolab_UserFinderUtil {
    private static Xcolab_UserFinder _finder;

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUsersSortedByScreenNameAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .getUsersSortedByScreenNameAscFilteredByCategory(begin, end,
            filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUsersSortedByScreenNameDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .getUsersSortedByScreenNameDescFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleNameAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUsersSortedByRoleNameAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleNameDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUsersSortedByRoleNameDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUsersSortedByMemberSinceAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .getUsersSortedByMemberSinceAscFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUsersSortedByMemberSinceDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceDescFilteredByCategoryName(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .getUsersSortedByMemberSinceDescFilteredByCategoryName(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUsersSortedByActivityCountAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .getUsersSortedByActivityCountAscFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUsersSortedByActivityCountDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .getUsersSortedByActivityCountDescFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static long getUserActivityCount(long userId) {
        return getFinder().getUserActivityCount(userId);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsAsc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUsersSortedByPointsAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .getUsersSortedByPointsAscFilteredByCategory(begin, end,
            filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsDesc(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUsersSortedByPointsDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByPointsDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder()
                   .getUsersSortedByPointsDescFilteredByCategory(begin, end,
            filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> findUsersByLoginIP(
        int begin, int end, java.lang.String filter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().findUsersByLoginIP(begin, end, filter);
    }

    public static java.lang.Long getUserMaterializedPoints(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUserMaterializedPoints(userId);
    }

    public static java.lang.Long getUserHypotheticalPoints(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getFinder().getUserHypotheticalPoints(userId);
    }

    public static Xcolab_UserFinder getFinder() {
        if (_finder == null) {
            _finder = (Xcolab_UserFinder) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    Xcolab_UserFinder.class.getName());

            ReferenceRegistry.registerReference(Xcolab_UserFinderUtil.class,
                "_finder");
        }

        return _finder;
    }

    public void setFinder(Xcolab_UserFinder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(Xcolab_UserFinderUtil.class,
            "_finder");
    }
}
