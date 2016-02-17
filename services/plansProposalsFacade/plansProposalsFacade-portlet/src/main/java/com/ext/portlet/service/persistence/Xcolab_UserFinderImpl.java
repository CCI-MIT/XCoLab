package com.ext.portlet.service.persistence;

import com.ext.utils.CustomSqlUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.List;

/**
 * Created by juan on 10/12/14.
 */

public class Xcolab_UserFinderImpl extends BasePersistenceImpl<User>
        implements Xcolab_UserFinder {

    public static final String USER_ENTITY_NAME = "User_";
    public static final String USER_IMPL_CLASS_NAME = "com.liferay.portal.model.impl.UserImpl";

    @Override
    public List<User> getUsersSortedByScreenNameAsc(int begin, int end, final String filter) throws SystemException {
        return getUsers(begin, end, filter, GET_USERS_SORTED_BY_SCREENNAME_ASC);
    }

    @Override
    public List<User> getUsersSortedByScreenNameAscFilteredByCategory(int begin, int end, final String filter, final String categoryName) throws SystemException {
        return getUsersByCategory(begin, end, filter, categoryName, GET_USERS_SORTED_BY_SCREENNAME_ASC_FILTERED_BY_CATEGORY);
    }

    @Override
    public List<User> getUsersSortedByScreenNameDesc(int begin, int end, final String filter) throws SystemException {
        return getUsers(begin, end, filter, GET_USERS_SORTED_BY_SCREENNAME_DESC);
    }

    @Override
    public List<User> getUsersSortedByScreenNameDescFilteredByCategory (int begin, int end, final String filter, final String categoryName) throws SystemException {
        return getUsersByCategory(begin, end, filter, categoryName, GET_USERS_SORTED_BY_SCREENNAME_DESC_FILTERED_BY_CATEGORY);
    }

    @Override
    public List<User> getUsersSortedByRoleNameAsc(int begin, int end, String filter) throws SystemException {
        return getUsers(begin, end, filter, GET_USERS_SORTED_BY_ROLENAME_ASC);
    }

    @Override
    public List<User> getUsersSortedByRoleNameDesc(int begin, int end, String filter) throws SystemException {
        return getUsers(begin, end, filter, GET_USERS_SORTED_BY_ROLENAME_DESC);
    }

    @Override
    public List<User> getUsersSortedByMemberSinceAsc(int begin, int end, String filter) throws SystemException {
        return getUsers(begin, end, filter, GET_USERS_SORTED_BY_MEMBER_SINCE_ASC);
    }

    @Override
    public List<User> getUsersSortedByMemberSinceAscFilteredByCategory(int begin, int end, String filter, String categoryName) throws SystemException {
        return getUsersByCategory(begin, end, filter, categoryName, GET_USERS_SORTED_BY_MEMBER_SINCE_ASC_FILTERED_BY_CATEGORY);
    }

    @Override
    public List<User> getUsersSortedByMemberSinceDesc(int begin, int end, String filter) throws SystemException {
        return getUsers(begin, end, filter, GET_USERS_SORTED_BY_MEMBER_SINCE_DESC);
    }

    @Override
    public List<User> getUsersSortedByMemberSinceDescFilteredByCategoryName(int begin, int end, String filter, String categoryName) throws SystemException {
        return getUsersByCategory(begin, end, filter, categoryName, GET_USERS_SORTED_BY_MEMBER_SINCE_DESC_FILTERED_BY_CATEGORY);
    }

    @Override
    public List<User> getUsersSortedByActivityCountAsc(int begin, int end, String filter) throws SystemException {
        return getUsers(begin, end, filter, GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC);
    }

    @Override
    public List<User> getUsersSortedByActivityCountAscFilteredByCategory(int begin, int end, String filter, String categoryName) throws SystemException {
        return getUsersByCategory(begin, end, filter, categoryName, GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC_FILTERED_BY_CATEGORY);
    }

    @Override
    public List<User> getUsersSortedByActivityCountDesc(int begin, int end, String filter) throws SystemException {
        return getUsers(begin, end, filter, GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC);
    }

    @Override
    public List<User> getUsersSortedByActivityCountDescFilteredByCategory(int begin, int end, String filter, String categoryName) throws SystemException {
        return getUsersByCategory(begin, end, filter, categoryName, GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC_FILTERED_BY_CATEGORY);
    }

    @Override
    public long getUserActivityCount(final long userId) {
        return CustomSqlUtil.getLongFromQuery(Xcolab_UserFinder.class.getName() + GET_USER_ACTIVITY_COUNT, getDialect(),
                new CustomSqlUtil.QueryInitializer(false) {
            @Override
            protected void fillFilters(QueryPos queryPos) {
                queryPos.add(Long.toString(userId));
            }
        });
    }

    @Override
    public List<User> getUsersSortedByPointsAsc(int begin, int end, String filter) throws SystemException {
        return getUsers(begin, end, filter, GET_USERS_SORTED_BY_POINTS_ASC);
    }

    @Override
    public List<User> getUsersSortedByPointsAscFilteredByCategory(int begin, int end, String filter, String categoryName) throws SystemException {
        return getUsersByCategory(begin, end, filter, categoryName, GET_USERS_SORTED_BY_POINTS_ASC_FILTERED_BY_CATEGORY);
    }

    @Override
    public List<User> getUsersSortedByPointsDesc(int begin, int end, String filter) throws SystemException {
        return getUsers(begin, end, filter, GET_USERS_SORTED_BY_POINTS_DESC);
    }

    @Override
    public List<User> getUsersSortedByPointsDescFilteredByCategory(int begin, int end, String filter, String categoryName) throws SystemException {
        return getUsersByCategory(begin, end, filter, categoryName, GET_USERS_SORTED_BY_POINTS_DESC_FILTERED_BY_CATEGORY);
    }

    private List<User> getUsers(int begin, int end, final String filter, String queryName) throws SystemException {
        try {
            return CustomSqlUtil.getListFromQuery(Xcolab_UserFinder.class.getName() + queryName, begin, end, getDialect(),
                    new CustomSqlUtil.QueryInitializer(USER_ENTITY_NAME, PortalClassLoaderUtil.getClassLoader().loadClass(USER_IMPL_CLASS_NAME), false) {
                        @Override
                        public void fillFilters(QueryPos queryPos) {
                            queryPos.add(filter);
                            queryPos.add(filter);
                            queryPos.add(filter);
                        }
                    });
        } catch (ClassNotFoundException e) {
            throw new SystemException(e);
        }
    }

    private List<User> getUsersByCategory(int begin, int end, final String filter, final String categoryName, String queryIdentifier) throws SystemException {
        try {
            return CustomSqlUtil.getListFromQuery(Xcolab_UserFinder.class.getName() + queryIdentifier, begin, end, getDialect(),
                    new CustomSqlUtil.QueryInitializer(USER_ENTITY_NAME, PortalClassLoaderUtil.getClassLoader().loadClass(USER_IMPL_CLASS_NAME), false) {
                        @Override
                        public void fillFilters(QueryPos queryPos) {
                            queryPos.add(filter);
                            queryPos.add(filter);
                            queryPos.add(filter);
                            queryPos.add(categoryName);
                        }
                    });
        } catch (ClassNotFoundException e) {
            throw new SystemException(e);
        }
    }

    @Override
    public List<User> findUsersByLoginIP(int begin, int end, final String filter) throws SystemException {
        try {
            return CustomSqlUtil.getListFromQuery(Xcolab_UserFinder.class.getName() + FIND_USERS_BY_LOGIN_IP, begin, end, getDialect(),
                    new CustomSqlUtil.QueryInitializer(USER_ENTITY_NAME, PortalClassLoaderUtil.getClassLoader().loadClass(USER_IMPL_CLASS_NAME), false) {
                        @Override
                        public void fillFilters(QueryPos queryPos) {
                            queryPos.add(filter);
                            queryPos.add(filter);
                        }
                    });
        } catch (ClassNotFoundException e) {
            throw new SystemException(e);
        }
    }

    @Override
    public Long getUserMaterializedPoints(Long userId) throws SystemException {
        return getPoints(userId, GET_USER_MATERIALIZED_POINTS);
    }

    @Override
    public Long getUserHypotheticalPoints(Long userId) throws SystemException {
        return getPoints(userId, GET_USER_HYPTOTHETICAL_POINTS);
    }

    private Long getPoints(final long userId, String queryName) throws SystemException {
        return CustomSqlUtil.getLongFromQuery(Xcolab_UserFinder.class.getName() + queryName, getDialect(),
                new CustomSqlUtil.QueryInitializer(false) {
                    @Override
                    public void fillFilters(QueryPos queryPos) {
                        queryPos.add(Long.toString(userId));
                    }
                });
    }

    public static final String GET_USERS_SORTED_BY_SCREENNAME_ASC = ".getUsersSortedByScreenNameAsc";
    public static final String GET_USERS_SORTED_BY_SCREENNAME_ASC_FILTERED_BY_CATEGORY = ".getUsersSortedByScreenNameAscFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_SCREENNAME_DESC = ".getUsersSortedByScreenNameDesc";
    public static final String GET_USERS_SORTED_BY_SCREENNAME_DESC_FILTERED_BY_CATEGORY = ".getUsersSortedByScreenNameDescFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_ROLENAME_ASC = ".getUsersSortedByRoleNameAsc";
    public static final String GET_USERS_SORTED_BY_ROLENAME_DESC = ".getUsersSortedByRoleNameDesc";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_ASC = ".getUsersSortedByMemberSinceAsc";
    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_ASC_FILTERED_BY_CATEGORY = ".getUsersSortedByMemberSinceAscFilteredByCategory";
    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_DESC = ".getUsersSortedByMemberSinceDesc";
    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_DESC_FILTERED_BY_CATEGORY = ".getUsersSortedByMemberSinceDescFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC = ".getUsersSortedByActivityCountAsc";
    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC_FILTERED_BY_CATEGORY = ".getUsersSortedByActivityCountAscFilteredByCategory";
    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC = ".getUsersSortedByActivityCountDesc";
    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC_FILTERED_BY_CATEGORY = ".getUsersSortedByActivityCountDescFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_POINTS_ASC = ".getUsersSortedByPointsAsc";
    public static final String GET_USERS_SORTED_BY_POINTS_ASC_FILTERED_BY_CATEGORY = ".getUsersSortedByPointsAscFilteredByCategory";
    public static final String GET_USERS_SORTED_BY_POINTS_DESC = ".getUsersSortedByPointsDesc";
    public static final String GET_USERS_SORTED_BY_POINTS_DESC_FILTERED_BY_CATEGORY = ".getUsersSortedByPointsDescFilteredByCategory";

    public static final String FIND_USERS_BY_LOGIN_IP = ".findUsersByLoginIP";

    public static final String GET_USER_ACTIVITY_COUNT = ".getUserActivityCount";

    public static final String GET_USER_MATERIALIZED_POINTS = ".getUserMaterializedPoints";
    public static final String GET_USER_HYPTOTHETICAL_POINTS = ".getUserHypotheticalPoints";
}