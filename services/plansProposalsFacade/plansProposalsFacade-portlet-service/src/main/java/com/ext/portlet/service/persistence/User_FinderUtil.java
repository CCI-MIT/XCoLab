package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;


public class User_FinderUtil {
    private static User_Finder _finder;

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameAsc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByScreenNameAsc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByScreenNameAscFilteredByCategory(begin, end,
            filter, categoryName);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameDesc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByScreenNameDesc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByScreenNameDescFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByRoleNameAsc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByRoleNameAsc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByRoleNameDesc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByRoleNameDesc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceAsc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByMemberSinceAsc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByMemberSinceAscFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceDesc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByMemberSinceDesc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceDescFilteredByCategoryName(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByMemberSinceDescFilteredByCategoryName(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountAsc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByActivityCountAsc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName) {
        return getFinder()
                   .getUsersSortedByActivityCountAscFilteredByCategory(begin,
            end, filter, categoryName);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountDesc(
        int begin, int end, java.lang.String filter) {
        return getFinder().getUsersSortedByActivityCountDesc(begin, end, filter);
    }

    public static java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountDescFilteredByCategory(
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

    public static User_Finder getFinder() {
        if (_finder == null) {
            _finder = (User_Finder) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    User_Finder.class.getName());

            ReferenceRegistry.registerReference(User_FinderUtil.class, "_finder");
        }

        return _finder;
    }

    public void setFinder(User_Finder finder) {
        _finder = finder;

        ReferenceRegistry.registerReference(User_FinderUtil.class, "_finder");
    }
}
