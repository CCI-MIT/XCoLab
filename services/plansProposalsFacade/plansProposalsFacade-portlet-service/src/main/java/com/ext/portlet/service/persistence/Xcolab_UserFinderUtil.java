package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class Xcolab_UserFinderUtil {
    private static Xcolab_UserFinder _finder;

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameAsc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByScreenNameAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByScreenNameAscFilteredByCategory(begin, end,
            filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameDesc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByScreenNameDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByScreenNameDescFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleNameAsc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByRoleNameAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleNameDesc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByRoleNameDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceAsc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByMemberSinceAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByMemberSinceAscFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceDesc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByMemberSinceDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceDescFilteredByCategoryName(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByMemberSinceDescFilteredByCategoryName(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountAsc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByActivityCountAsc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByActivityCountAscFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountDesc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByActivityCountDesc(begin, end, filter);
    }

    public static java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByActivityCountDescFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static java.util.List<java.math.BigInteger> getUserActivityCount(
        java.lang.Long userId) {
        return getFinder().getUserActivityCount(userId);
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
