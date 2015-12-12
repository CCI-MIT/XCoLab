package com.ext.utils;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.Dialect;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.util.dao.orm.CustomSQLUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by johannes on 12/11/15.
 */
public class CustomSqlUtil {
    public static <T> List<T> getListFromQuery(String queryIdentifier, int begin, int end, Dialect dialect, QueryInitializer queryInitializer) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(queryIdentifier);
            final SQLQuery query = queryInitializer.getQuery(session, sql);
            //noinspection unchecked
            return (List<T>) QueryUtil.list(query, dialect, begin, end);
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

    public static long getLongFromQuery(String queryIdentifier, Dialect dialect, QueryInitializer queryInitializer) {
        Number result = getNumberFromQuery(queryIdentifier, dialect, queryInitializer);
        if (result != null) {
            return result.longValue();
        }
        return 0L;
    }

    public static int getIntFromQuery(String queryIdentifier, Dialect dialect, QueryInitializer queryInitializer) {
        Number result = getNumberFromQuery(queryIdentifier, dialect, queryInitializer);
        if (result != null) {
            return result.intValue();
        }
        return 0;
    }

    public static Number getNumberFromQuery(String queryIdentifier, Dialect dialect, QueryInitializer queryInitializer) {
        SessionFactory sessionFactory = (SessionFactory) PortalBeanLocatorUtil.locate("liferaySessionFactory");
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String sql = CustomSQLUtil.get(queryIdentifier);
            final SQLQuery query = queryInitializer.getQuery(session, sql);
            //noinspection unchecked
            List<Number> results = (List<Number>) QueryUtil.list(query, dialect, 0, Integer.MAX_VALUE);
            if (!results.isEmpty() && results.get(0) != null) {
                return results.get(0);
            }
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

    public abstract static class QueryInitializer {
        private final String entityName;
        private final Class<?> implClass;
        private final boolean isCached;

        public QueryInitializer(String entityName, Class<?> implClass , boolean isCached) {
            this.entityName = entityName;
            this.implClass = implClass;
            this.isCached = isCached;
        }

        public QueryInitializer(boolean isCached) {
            this ("", null, isCached);
        }

        public SQLQuery getQuery(Session session, String sql) throws ClassNotFoundException {
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(isCached);
            if (!StringUtils.isEmpty(entityName) && implClass != null) {
                q.addEntity(entityName, implClass);
            }
            QueryPos qPos = QueryPos.getInstance(q);
            fillFilters(qPos);
            return q;
        }

        protected abstract void fillFilters(QueryPos queryPos);
    }
}
