package com.ext.portlet.service.persistence;
import com.ext.portlet.model.User_;
import com.ext.portlet.model.impl.User_Impl;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import javax.management.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by juan on 10/12/14.
 */

public class User_FinderImpl extends BasePersistenceImpl<User_>
        implements User_Finder {


    public List<User_> getUsersSortedByScreenNameAsc(int begin, int end, String filter) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_SCREENNAME_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByScreenNameAscFilteredByCategory(int begin, int end, String filter, String categoryName) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_SCREENNAME_ASC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByScreenNameDesc(int begin, int end, String filter) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_SCREENNAME_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByScreenNameDescFilteredByCategory (int begin, int end, String filter, String categoryName) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_SCREENNAME_DESC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByRoleNameAsc(int begin, int end, String filter) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ROLENAME_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByRoleNameDesc(int begin, int end, String filter) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ROLENAME_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByMemberSinceAsc(int begin, int end, String filter) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_MEMBER_SINCE_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByMemberSinceAscFilteredByCategory(int begin, int end, String filter, String categoryName) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_MEMBER_SINCE_ASC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByMemberSinceDesc(int begin, int end, String filter) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_MEMBER_SINCE_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByMemberSinceDescFilteredByCategoryName(int begin, int end, String filter, String categoryName) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_MEMBER_SINCE_DESC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByActivityCountAsc(int begin, int end, String filter) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByActivityCountAscFilteredByCategory(int begin, int end, String filter, String categoryName) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByActivityCountDesc(int begin, int end, String filter) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<User_> getUsersSortedByActivityCountDescFilteredByCategory(int begin, int end, String filter, String categoryName) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User_>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public List<BigInteger> getUserActivityCount(Long userId) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USER_ACTIVITY_COUNT);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            //q.addEntity("User_", User_Impl.class);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(userId.toString());
            List<BigInteger> results = (List<BigInteger>) QueryUtil.list(q, getDialect(), 0, Integer.MAX_VALUE);
            return results;
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    public static final String GET_USERS_SORTED_BY_SCREENNAME_ASC =
            User_Finder.class.getName() +
                    ".getUsersSortedByScreenNameAsc";

    public static final String GET_USERS_SORTED_BY_SCREENNAME_ASC_FILTERED_BY_CATEGORY =
            User_Finder.class.getName() +
                    ".getUsersSortedByScreenNameAscFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_SCREENNAME_DESC =
            User_Finder.class.getName() +
                    ".getUsersSortedByScreenNameDesc";

    public static final String GET_USERS_SORTED_BY_SCREENNAME_DESC_FILTERED_BY_CATEGORY =
            User_Finder.class.getName() +
                    ".getUsersSortedByScreenNameDescFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_ROLENAME_ASC =
            User_Finder.class.getName() +
                    ".getUsersSortedByRoleNameAsc";

    public static final String GET_USERS_SORTED_BY_ROLENAME_DESC =
            User_Finder.class.getName() +
                    ".getUsersSortedByRoleNameDesc";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_ASC =
            User_Finder.class.getName() +
                    ".getUsersSortedByMemberSinceAsc";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_ASC_FILTERED_BY_CATEGORY =
            User_Finder.class.getName() +
                    ".getUsersSortedByMemberSinceAscFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_DESC =
            User_Finder.class.getName() +
                    ".getUsersSortedByMemberSinceDesc";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_DESC_FILTERED_BY_CATEGORY =
            User_Finder.class.getName() +
                    ".getUsersSortedByMemberSinceDescFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC =
            User_Finder.class.getName() +
                    ".getUsersSortedByActivityCountAsc";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC_FILTERED_BY_CATEGORY =
            User_Finder.class.getName() +
                    ".getUsersSortedByActivityCountAscFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC =
            User_Finder.class.getName() +
                    ".getUsersSortedByActivityCountDesc";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC_FILTERED_BY_CATEGORY =
            User_Finder.class.getName() +
                    ".getUsersSortedByActivityCountDescFilteredByCategory";

    public static final String GET_USER_ACTIVITY_COUNT =
            User_Finder.class.getName() +
                    ".getUserActivityCount";

}