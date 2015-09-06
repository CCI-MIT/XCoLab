package com.ext.portlet.service.persistence;

public interface Xcolab_UserFinder {
    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameAsc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameDesc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByScreenNameDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleNameAsc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByRoleNameDesc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceAsc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceDesc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByMemberSinceDescFilteredByCategoryName(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountAsc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountDesc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.liferay.portal.model.User> getUsersSortedByActivityCountDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<java.math.BigInteger> getUserActivityCount(
        java.lang.Long userId);
}
