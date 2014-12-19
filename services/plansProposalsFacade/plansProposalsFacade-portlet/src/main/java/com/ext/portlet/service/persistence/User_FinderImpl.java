package com.ext.portlet.service.persistence;
import com.ext.portlet.model.User_;
import com.ext.portlet.model.impl.User_Impl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;
import java.util.List;

/**
 * Created by juan on 10/12/14.
 */

public class User_FinderImpl extends BasePersistenceImpl<User_>
        implements User_Finder {


    public List<User_> getUsersSortedByScreenNameAsc(int begin, int end) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_SCREENNAME_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
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

    public List<User_> getUsersSortedByScreenNameDesc(int begin, int end) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_SCREENNAME_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
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

    public List<User_> getUsersSortedByActivityCountAsc(int begin, int end) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
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

    public List<User_> getUsersSortedByActivityCountDesc(int begin, int end) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
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

    public List<User_> getUsersSortedByRoleNameAsc(int begin, int end) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ROLENAME_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
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

    public List<User_> getUsersSortedByRoleNameDesc(int begin, int end) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ROLENAME_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
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

    public List<User_> getUsersSortedByMemberSinceAsc(int begin, int end) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_MEMBER_SINCE_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
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

    public List<User_> getUsersSortedByMemberSinceDesc(int begin, int end) {
        Session session = null;
        try {
            session = openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_MEMBER_SINCE_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", User_Impl.class);
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


    public static final String GET_USERS_SORTED_BY_SCREENNAME_ASC =
            User_Finder.class.getName() +
                    ".getUsersSortedByScreenNameAsc";

    public static final String GET_USERS_SORTED_BY_SCREENNAME_DESC =
            User_Finder.class.getName() +
                    ".getUsersSortedByScreenNameDesc";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC =
            User_Finder.class.getName() +
                    ".getUsersSortedByActivityCountAsc";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC =
            User_Finder.class.getName() +
                    ".getUsersSortedByActivityCountDesc";

    public static final String GET_USERS_SORTED_BY_ROLENAME_ASC =
            User_Finder.class.getName() +
                    ".getUsersSortedByRoleNameAsc";

    public static final String GET_USERS_SORTED_BY_ROLENAME_DESC =
            User_Finder.class.getName() +
                    ".getUsersSortedByRoleNameDesc";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_ASC =
            User_Finder.class.getName() +
                    ".getUsersSortedByMemberSinceAsc";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_DESC =
            User_Finder.class.getName() +
                    ".getUsersSortedByMemberSinceDesc";






}