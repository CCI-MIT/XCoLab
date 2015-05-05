package com.ext.portlet.service.persistence;

public interface User_Finder {
    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameAsc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameDesc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByScreenNameDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByRoleNameAsc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByRoleNameDesc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceAsc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceDesc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByMemberSinceDescFilteredByCategoryName(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountAsc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountAscFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountDesc(
        int begin, int end, java.lang.String filter);

    public java.util.List<com.ext.portlet.model.User_> getUsersSortedByActivityCountDescFilteredByCategory(
        int begin, int end, java.lang.String filter,
        java.lang.String categoryName);

    public java.util.List<java.math.BigInteger> getUserActivityCount(
        java.lang.Long userId);
}
