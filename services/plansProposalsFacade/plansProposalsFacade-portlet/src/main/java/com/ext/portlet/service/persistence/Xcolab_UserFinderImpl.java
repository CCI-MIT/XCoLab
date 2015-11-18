package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by juan on 10/12/14.
 */

public class Xcolab_UserFinderImpl extends BasePersistenceImpl<User>
        implements Xcolab_UserFinder {
    public List<User> getUsersSortedByScreenNameAsc(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_SCREENNAME_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByScreenNameAscFilteredByCategory(int begin, int end, String filter, String categoryName) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_SCREENNAME_ASC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByScreenNameDesc(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_SCREENNAME_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByScreenNameDescFilteredByCategory (int begin, int end, String filter, String categoryName) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_SCREENNAME_DESC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByRoleNameAsc(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ROLENAME_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByRoleNameDesc(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ROLENAME_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByMemberSinceAsc(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_MEMBER_SINCE_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByMemberSinceAscFilteredByCategory(int begin, int end, String filter, String categoryName) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_MEMBER_SINCE_ASC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByMemberSinceDesc(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_MEMBER_SINCE_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByMemberSinceDescFilteredByCategoryName(int begin, int end, String filter, String categoryName) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_MEMBER_SINCE_DESC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByActivityCountAsc(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByActivityCountAscFilteredByCategory(int begin, int end, String filter, String categoryName) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByActivityCountDesc(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;

        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByActivityCountDescFilteredByCategory(int begin, int end, String filter, String categoryName) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<BigInteger> getUserActivityCount(Long userId) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_UserACTIVITY_COUNT);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            //q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
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
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public List<User> getUsersSortedByPointsAsc(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_POINTS_ASC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
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

    public List<User> getUsersSortedByPointsAscFilteredByCategory(int begin, int end, String filter, String categoryName) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_POINTS_ASC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
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

    public List<User> getUsersSortedByPointsDesc(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_POINTS_DESC);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
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

    public List<User> getUsersSortedByPointsDescFilteredByCategory(int begin, int end, String filter, String categoryName) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USERS_SORTED_BY_POINTS_DESC_FILTERED_BY_CATEGORY);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(filter);
            qPos.add(categoryName);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
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

    public Long getUserMaterializedPoints(Long userId) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USER_MATERIALIZED_POINTS);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(userId.toString());
            List<Double> results = (List<Double>) QueryUtil.list(q, getDialect(), 0, Integer.MAX_VALUE);
            if (results.size() > 0 && results.get(0) != null) {
                return results.get(0).longValue();
            }
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return 0L;
    }

    public Long getUserHypotheticalPoints(Long userId) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    GET_USER_HYPTOTHETICAL_POINTS);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(userId.toString());
            List<Double> results = (List<Double>) QueryUtil.list(q, getDialect(), 0, Integer.MAX_VALUE);
            if (results.size() > 0 && results.get(0) != null) {
                return results.get(0).longValue();
            }
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }
        return 0L;
    }

    public List<User> findUsersByLoginIP(int begin, int end, String filter) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(
                    FIND_USERS_BY_LOGIN_IP);
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("User_", PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
            QueryPos qPos = QueryPos.getInstance(q);
            qPos.add(filter);
            qPos.add(filter);
            return (List<User>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (ORMException | ClassNotFoundException e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            sessionFactory.closeSession(session);
        }
        return null;
    }

    public static final String GET_USERS_SORTED_BY_SCREENNAME_ASC =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByScreenNameAsc";

    public static final String GET_USERS_SORTED_BY_SCREENNAME_ASC_FILTERED_BY_CATEGORY =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByScreenNameAscFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_SCREENNAME_DESC =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByScreenNameDesc";

    public static final String GET_USERS_SORTED_BY_SCREENNAME_DESC_FILTERED_BY_CATEGORY =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByScreenNameDescFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_ROLENAME_ASC =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByRoleNameAsc";

    public static final String GET_USERS_SORTED_BY_ROLENAME_DESC =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByRoleNameDesc";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_ASC =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByMemberSinceAsc";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_ASC_FILTERED_BY_CATEGORY =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByMemberSinceAscFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_DESC =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByMemberSinceDesc";

    public static final String GET_USERS_SORTED_BY_MEMBER_SINCE_DESC_FILTERED_BY_CATEGORY =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByMemberSinceDescFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByActivityCountAsc";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_ASC_FILTERED_BY_CATEGORY =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByActivityCountAscFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByActivityCountDesc";

    public static final String GET_USERS_SORTED_BY_ACTIVITY_COUNT_DESC_FILTERED_BY_CATEGORY =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByActivityCountDescFilteredByCategory";

    public static final String GET_UserACTIVITY_COUNT =
            Xcolab_UserFinder.class.getName() +
                    ".getUserActivityCount";

    public static final String GET_USERS_SORTED_BY_POINTS_ASC =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByPointsAsc";

    public static final String GET_USERS_SORTED_BY_POINTS_ASC_FILTERED_BY_CATEGORY =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByPointsAscFilteredByCategory";

    public static final String GET_USERS_SORTED_BY_POINTS_DESC =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByPointsDesc";

    public static final String GET_USERS_SORTED_BY_POINTS_DESC_FILTERED_BY_CATEGORY =
            Xcolab_UserFinder.class.getName() +
                    ".getUsersSortedByPointsDescFilteredByCategory";

    public static final String GET_USER_MATERIALIZED_POINTS =
            Xcolab_UserFinder.class.getName() +
                    ".getUserMaterializedPoints";

    public static final String GET_USER_HYPTOTHETICAL_POINTS =
            Xcolab_UserFinder.class.getName() +
                    ".getUserHypotheticalPoints";

    public static final String FIND_USERS_BY_LOGIN_IP =
            Xcolab_UserFinder.class.getName() +
                    ".findUsersByLoginIP";

}