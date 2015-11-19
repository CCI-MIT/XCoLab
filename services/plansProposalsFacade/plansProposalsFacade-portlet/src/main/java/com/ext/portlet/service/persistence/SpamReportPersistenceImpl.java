package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchSpamReportException;
import com.ext.portlet.model.SpamReport;
import com.ext.portlet.model.impl.SpamReportImpl;
import com.ext.portlet.model.impl.SpamReportModelImpl;
import com.ext.portlet.service.persistence.SpamReportPersistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the spam report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SpamReportPersistence
 * @see SpamReportUtil
 * @generated
 */
public class SpamReportPersistenceImpl extends BasePersistenceImpl<SpamReport>
    implements SpamReportPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link SpamReportUtil} to access the spam report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = SpamReportImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, SpamReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, SpamReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SPAMUSERID =
        new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, SpamReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySpamUserId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPAMUSERID =
        new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, SpamReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySpamUserId",
            new String[] { Long.class.getName() },
            SpamReportModelImpl.SPAMUSERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SPAMUSERID = new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySpamUserId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_SPAMUSERID_SPAMUSERID_2 = "spamReport.spamUserId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DISCUSSIONMESSAGEID =
        new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, SpamReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByDiscussionMessageId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DISCUSSIONMESSAGEID =
        new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, SpamReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByDiscussionMessageId", new String[] { Long.class.getName() },
            SpamReportModelImpl.DISCUSSIONMESSAGEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_DISCUSSIONMESSAGEID = new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByDiscussionMessageId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_DISCUSSIONMESSAGEID_DISCUSSIONMESSAGEID_2 =
        "spamReport.discussionMessageId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_REPORTERUSERID =
        new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, SpamReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByReporterUserId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REPORTERUSERID =
        new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, SpamReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByReporterUserId",
            new String[] { Long.class.getName() },
            SpamReportModelImpl.REPORTERUSERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_REPORTERUSERID = new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByReporterUserId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_REPORTERUSERID_REPORTERUSERID_2 = "spamReport.reporterUserId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SPAMUSERIDDISCUSSIONMESSAGEID =
        new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, SpamReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findBySpamUserIdDiscussionMessageId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPAMUSERIDDISCUSSIONMESSAGEID =
        new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, SpamReportImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findBySpamUserIdDiscussionMessageId",
            new String[] { Long.class.getName(), Long.class.getName() },
            SpamReportModelImpl.SPAMUSERID_COLUMN_BITMASK |
            SpamReportModelImpl.DISCUSSIONMESSAGEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SPAMUSERIDDISCUSSIONMESSAGEID =
        new FinderPath(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBySpamUserIdDiscussionMessageId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_SPAMUSERIDDISCUSSIONMESSAGEID_SPAMUSERID_2 =
        "spamReport.spamUserId = ? AND ";
    private static final String _FINDER_COLUMN_SPAMUSERIDDISCUSSIONMESSAGEID_DISCUSSIONMESSAGEID_2 =
        "spamReport.discussionMessageId = ?";
    private static final String _SQL_SELECT_SPAMREPORT = "SELECT spamReport FROM SpamReport spamReport";
    private static final String _SQL_SELECT_SPAMREPORT_WHERE = "SELECT spamReport FROM SpamReport spamReport WHERE ";
    private static final String _SQL_COUNT_SPAMREPORT = "SELECT COUNT(spamReport) FROM SpamReport spamReport";
    private static final String _SQL_COUNT_SPAMREPORT_WHERE = "SELECT COUNT(spamReport) FROM SpamReport spamReport WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "spamReport.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SpamReport exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SpamReport exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(SpamReportPersistenceImpl.class);
    private static SpamReport _nullSpamReport = new SpamReportImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<SpamReport> toCacheModel() {
                return _nullSpamReportCacheModel;
            }
        };

    private static CacheModel<SpamReport> _nullSpamReportCacheModel = new CacheModel<SpamReport>() {
            @Override
            public SpamReport toEntityModel() {
                return _nullSpamReport;
            }
        };

    public SpamReportPersistenceImpl() {
        setModelClass(SpamReport.class);
    }

    /**
     * Returns all the spam reports where spamUserId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @return the matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findBySpamUserId(long spamUserId)
        throws SystemException {
        return findBySpamUserId(spamUserId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the spam reports where spamUserId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param spamUserId the spam user ID
     * @param start the lower bound of the range of spam reports
     * @param end the upper bound of the range of spam reports (not inclusive)
     * @return the range of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findBySpamUserId(long spamUserId, int start, int end)
        throws SystemException {
        return findBySpamUserId(spamUserId, start, end, null);
    }

    /**
     * Returns an ordered range of all the spam reports where spamUserId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param spamUserId the spam user ID
     * @param start the lower bound of the range of spam reports
     * @param end the upper bound of the range of spam reports (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findBySpamUserId(long spamUserId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPAMUSERID;
            finderArgs = new Object[] { spamUserId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SPAMUSERID;
            finderArgs = new Object[] { spamUserId, start, end, orderByComparator };
        }

        List<SpamReport> list = (List<SpamReport>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SpamReport spamReport : list) {
                if ((spamUserId != spamReport.getSpamUserId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_SPAMREPORT_WHERE);

            query.append(_FINDER_COLUMN_SPAMUSERID_SPAMUSERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(SpamReportModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(spamUserId);

                if (!pagination) {
                    list = (List<SpamReport>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SpamReport>(list);
                } else {
                    list = (List<SpamReport>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first spam report in the ordered set where spamUserId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport findBySpamUserId_First(long spamUserId,
        OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = fetchBySpamUserId_First(spamUserId,
                orderByComparator);

        if (spamReport != null) {
            return spamReport;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("spamUserId=");
        msg.append(spamUserId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSpamReportException(msg.toString());
    }

    /**
     * Returns the first spam report in the ordered set where spamUserId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport fetchBySpamUserId_First(long spamUserId,
        OrderByComparator orderByComparator) throws SystemException {
        List<SpamReport> list = findBySpamUserId(spamUserId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last spam report in the ordered set where spamUserId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport findBySpamUserId_Last(long spamUserId,
        OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = fetchBySpamUserId_Last(spamUserId,
                orderByComparator);

        if (spamReport != null) {
            return spamReport;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("spamUserId=");
        msg.append(spamUserId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSpamReportException(msg.toString());
    }

    /**
     * Returns the last spam report in the ordered set where spamUserId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport fetchBySpamUserId_Last(long spamUserId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySpamUserId(spamUserId);

        if (count == 0) {
            return null;
        }

        List<SpamReport> list = findBySpamUserId(spamUserId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the spam reports before and after the current spam report in the ordered set where spamUserId = &#63;.
     *
     * @param id_ the primary key of the current spam report
     * @param spamUserId the spam user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport[] findBySpamUserId_PrevAndNext(long id_, long spamUserId,
        OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            SpamReport[] array = new SpamReportImpl[3];

            array[0] = getBySpamUserId_PrevAndNext(session, spamReport,
                    spamUserId, orderByComparator, true);

            array[1] = spamReport;

            array[2] = getBySpamUserId_PrevAndNext(session, spamReport,
                    spamUserId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SpamReport getBySpamUserId_PrevAndNext(Session session,
        SpamReport spamReport, long spamUserId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SPAMREPORT_WHERE);

        query.append(_FINDER_COLUMN_SPAMUSERID_SPAMUSERID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(SpamReportModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(spamUserId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(spamReport);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SpamReport> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the spam reports where spamUserId = &#63; from the database.
     *
     * @param spamUserId the spam user ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySpamUserId(long spamUserId) throws SystemException {
        for (SpamReport spamReport : findBySpamUserId(spamUserId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(spamReport);
        }
    }

    /**
     * Returns the number of spam reports where spamUserId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @return the number of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySpamUserId(long spamUserId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SPAMUSERID;

        Object[] finderArgs = new Object[] { spamUserId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_SPAMREPORT_WHERE);

            query.append(_FINDER_COLUMN_SPAMUSERID_SPAMUSERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(spamUserId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the spam reports where discussionMessageId = &#63;.
     *
     * @param discussionMessageId the discussion message ID
     * @return the matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findByDiscussionMessageId(long discussionMessageId)
        throws SystemException {
        return findByDiscussionMessageId(discussionMessageId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the spam reports where discussionMessageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param discussionMessageId the discussion message ID
     * @param start the lower bound of the range of spam reports
     * @param end the upper bound of the range of spam reports (not inclusive)
     * @return the range of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findByDiscussionMessageId(
        long discussionMessageId, int start, int end) throws SystemException {
        return findByDiscussionMessageId(discussionMessageId, start, end, null);
    }

    /**
     * Returns an ordered range of all the spam reports where discussionMessageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param discussionMessageId the discussion message ID
     * @param start the lower bound of the range of spam reports
     * @param end the upper bound of the range of spam reports (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findByDiscussionMessageId(
        long discussionMessageId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DISCUSSIONMESSAGEID;
            finderArgs = new Object[] { discussionMessageId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DISCUSSIONMESSAGEID;
            finderArgs = new Object[] {
                    discussionMessageId,
                    
                    start, end, orderByComparator
                };
        }

        List<SpamReport> list = (List<SpamReport>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SpamReport spamReport : list) {
                if ((discussionMessageId != spamReport.getDiscussionMessageId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_SPAMREPORT_WHERE);

            query.append(_FINDER_COLUMN_DISCUSSIONMESSAGEID_DISCUSSIONMESSAGEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(SpamReportModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(discussionMessageId);

                if (!pagination) {
                    list = (List<SpamReport>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SpamReport>(list);
                } else {
                    list = (List<SpamReport>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first spam report in the ordered set where discussionMessageId = &#63;.
     *
     * @param discussionMessageId the discussion message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport findByDiscussionMessageId_First(
        long discussionMessageId, OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = fetchByDiscussionMessageId_First(discussionMessageId,
                orderByComparator);

        if (spamReport != null) {
            return spamReport;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("discussionMessageId=");
        msg.append(discussionMessageId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSpamReportException(msg.toString());
    }

    /**
     * Returns the first spam report in the ordered set where discussionMessageId = &#63;.
     *
     * @param discussionMessageId the discussion message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport fetchByDiscussionMessageId_First(
        long discussionMessageId, OrderByComparator orderByComparator)
        throws SystemException {
        List<SpamReport> list = findByDiscussionMessageId(discussionMessageId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last spam report in the ordered set where discussionMessageId = &#63;.
     *
     * @param discussionMessageId the discussion message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport findByDiscussionMessageId_Last(long discussionMessageId,
        OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = fetchByDiscussionMessageId_Last(discussionMessageId,
                orderByComparator);

        if (spamReport != null) {
            return spamReport;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("discussionMessageId=");
        msg.append(discussionMessageId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSpamReportException(msg.toString());
    }

    /**
     * Returns the last spam report in the ordered set where discussionMessageId = &#63;.
     *
     * @param discussionMessageId the discussion message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport fetchByDiscussionMessageId_Last(
        long discussionMessageId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByDiscussionMessageId(discussionMessageId);

        if (count == 0) {
            return null;
        }

        List<SpamReport> list = findByDiscussionMessageId(discussionMessageId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the spam reports before and after the current spam report in the ordered set where discussionMessageId = &#63;.
     *
     * @param id_ the primary key of the current spam report
     * @param discussionMessageId the discussion message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport[] findByDiscussionMessageId_PrevAndNext(long id_,
        long discussionMessageId, OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            SpamReport[] array = new SpamReportImpl[3];

            array[0] = getByDiscussionMessageId_PrevAndNext(session,
                    spamReport, discussionMessageId, orderByComparator, true);

            array[1] = spamReport;

            array[2] = getByDiscussionMessageId_PrevAndNext(session,
                    spamReport, discussionMessageId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SpamReport getByDiscussionMessageId_PrevAndNext(Session session,
        SpamReport spamReport, long discussionMessageId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SPAMREPORT_WHERE);

        query.append(_FINDER_COLUMN_DISCUSSIONMESSAGEID_DISCUSSIONMESSAGEID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(SpamReportModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(discussionMessageId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(spamReport);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SpamReport> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the spam reports where discussionMessageId = &#63; from the database.
     *
     * @param discussionMessageId the discussion message ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByDiscussionMessageId(long discussionMessageId)
        throws SystemException {
        for (SpamReport spamReport : findByDiscussionMessageId(
                discussionMessageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(spamReport);
        }
    }

    /**
     * Returns the number of spam reports where discussionMessageId = &#63;.
     *
     * @param discussionMessageId the discussion message ID
     * @return the number of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByDiscussionMessageId(long discussionMessageId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_DISCUSSIONMESSAGEID;

        Object[] finderArgs = new Object[] { discussionMessageId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_SPAMREPORT_WHERE);

            query.append(_FINDER_COLUMN_DISCUSSIONMESSAGEID_DISCUSSIONMESSAGEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(discussionMessageId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the spam reports where reporterUserId = &#63;.
     *
     * @param reporterUserId the reporter user ID
     * @return the matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findByReporterUserId(long reporterUserId)
        throws SystemException {
        return findByReporterUserId(reporterUserId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the spam reports where reporterUserId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param reporterUserId the reporter user ID
     * @param start the lower bound of the range of spam reports
     * @param end the upper bound of the range of spam reports (not inclusive)
     * @return the range of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findByReporterUserId(long reporterUserId,
        int start, int end) throws SystemException {
        return findByReporterUserId(reporterUserId, start, end, null);
    }

    /**
     * Returns an ordered range of all the spam reports where reporterUserId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param reporterUserId the reporter user ID
     * @param start the lower bound of the range of spam reports
     * @param end the upper bound of the range of spam reports (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findByReporterUserId(long reporterUserId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REPORTERUSERID;
            finderArgs = new Object[] { reporterUserId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_REPORTERUSERID;
            finderArgs = new Object[] {
                    reporterUserId,
                    
                    start, end, orderByComparator
                };
        }

        List<SpamReport> list = (List<SpamReport>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SpamReport spamReport : list) {
                if ((reporterUserId != spamReport.getReporterUserId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_SPAMREPORT_WHERE);

            query.append(_FINDER_COLUMN_REPORTERUSERID_REPORTERUSERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(SpamReportModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(reporterUserId);

                if (!pagination) {
                    list = (List<SpamReport>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SpamReport>(list);
                } else {
                    list = (List<SpamReport>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first spam report in the ordered set where reporterUserId = &#63;.
     *
     * @param reporterUserId the reporter user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport findByReporterUserId_First(long reporterUserId,
        OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = fetchByReporterUserId_First(reporterUserId,
                orderByComparator);

        if (spamReport != null) {
            return spamReport;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("reporterUserId=");
        msg.append(reporterUserId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSpamReportException(msg.toString());
    }

    /**
     * Returns the first spam report in the ordered set where reporterUserId = &#63;.
     *
     * @param reporterUserId the reporter user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport fetchByReporterUserId_First(long reporterUserId,
        OrderByComparator orderByComparator) throws SystemException {
        List<SpamReport> list = findByReporterUserId(reporterUserId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last spam report in the ordered set where reporterUserId = &#63;.
     *
     * @param reporterUserId the reporter user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport findByReporterUserId_Last(long reporterUserId,
        OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = fetchByReporterUserId_Last(reporterUserId,
                orderByComparator);

        if (spamReport != null) {
            return spamReport;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("reporterUserId=");
        msg.append(reporterUserId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSpamReportException(msg.toString());
    }

    /**
     * Returns the last spam report in the ordered set where reporterUserId = &#63;.
     *
     * @param reporterUserId the reporter user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport fetchByReporterUserId_Last(long reporterUserId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByReporterUserId(reporterUserId);

        if (count == 0) {
            return null;
        }

        List<SpamReport> list = findByReporterUserId(reporterUserId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the spam reports before and after the current spam report in the ordered set where reporterUserId = &#63;.
     *
     * @param id_ the primary key of the current spam report
     * @param reporterUserId the reporter user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport[] findByReporterUserId_PrevAndNext(long id_,
        long reporterUserId, OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            SpamReport[] array = new SpamReportImpl[3];

            array[0] = getByReporterUserId_PrevAndNext(session, spamReport,
                    reporterUserId, orderByComparator, true);

            array[1] = spamReport;

            array[2] = getByReporterUserId_PrevAndNext(session, spamReport,
                    reporterUserId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SpamReport getByReporterUserId_PrevAndNext(Session session,
        SpamReport spamReport, long reporterUserId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SPAMREPORT_WHERE);

        query.append(_FINDER_COLUMN_REPORTERUSERID_REPORTERUSERID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(SpamReportModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(reporterUserId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(spamReport);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SpamReport> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the spam reports where reporterUserId = &#63; from the database.
     *
     * @param reporterUserId the reporter user ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByReporterUserId(long reporterUserId)
        throws SystemException {
        for (SpamReport spamReport : findByReporterUserId(reporterUserId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(spamReport);
        }
    }

    /**
     * Returns the number of spam reports where reporterUserId = &#63;.
     *
     * @param reporterUserId the reporter user ID
     * @return the number of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByReporterUserId(long reporterUserId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_REPORTERUSERID;

        Object[] finderArgs = new Object[] { reporterUserId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_SPAMREPORT_WHERE);

            query.append(_FINDER_COLUMN_REPORTERUSERID_REPORTERUSERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(reporterUserId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the spam reports where spamUserId = &#63; and discussionMessageId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @param discussionMessageId the discussion message ID
     * @return the matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findBySpamUserIdDiscussionMessageId(
        long spamUserId, long discussionMessageId) throws SystemException {
        return findBySpamUserIdDiscussionMessageId(spamUserId,
            discussionMessageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the spam reports where spamUserId = &#63; and discussionMessageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param spamUserId the spam user ID
     * @param discussionMessageId the discussion message ID
     * @param start the lower bound of the range of spam reports
     * @param end the upper bound of the range of spam reports (not inclusive)
     * @return the range of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findBySpamUserIdDiscussionMessageId(
        long spamUserId, long discussionMessageId, int start, int end)
        throws SystemException {
        return findBySpamUserIdDiscussionMessageId(spamUserId,
            discussionMessageId, start, end, null);
    }

    /**
     * Returns an ordered range of all the spam reports where spamUserId = &#63; and discussionMessageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param spamUserId the spam user ID
     * @param discussionMessageId the discussion message ID
     * @param start the lower bound of the range of spam reports
     * @param end the upper bound of the range of spam reports (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findBySpamUserIdDiscussionMessageId(
        long spamUserId, long discussionMessageId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPAMUSERIDDISCUSSIONMESSAGEID;
            finderArgs = new Object[] { spamUserId, discussionMessageId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SPAMUSERIDDISCUSSIONMESSAGEID;
            finderArgs = new Object[] {
                    spamUserId, discussionMessageId,
                    
                    start, end, orderByComparator
                };
        }

        List<SpamReport> list = (List<SpamReport>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (SpamReport spamReport : list) {
                if ((spamUserId != spamReport.getSpamUserId()) ||
                        (discussionMessageId != spamReport.getDiscussionMessageId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_SPAMREPORT_WHERE);

            query.append(_FINDER_COLUMN_SPAMUSERIDDISCUSSIONMESSAGEID_SPAMUSERID_2);

            query.append(_FINDER_COLUMN_SPAMUSERIDDISCUSSIONMESSAGEID_DISCUSSIONMESSAGEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(SpamReportModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(spamUserId);

                qPos.add(discussionMessageId);

                if (!pagination) {
                    list = (List<SpamReport>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SpamReport>(list);
                } else {
                    list = (List<SpamReport>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @param discussionMessageId the discussion message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport findBySpamUserIdDiscussionMessageId_First(
        long spamUserId, long discussionMessageId,
        OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = fetchBySpamUserIdDiscussionMessageId_First(spamUserId,
                discussionMessageId, orderByComparator);

        if (spamReport != null) {
            return spamReport;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("spamUserId=");
        msg.append(spamUserId);

        msg.append(", discussionMessageId=");
        msg.append(discussionMessageId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSpamReportException(msg.toString());
    }

    /**
     * Returns the first spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @param discussionMessageId the discussion message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching spam report, or <code>null</code> if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport fetchBySpamUserIdDiscussionMessageId_First(
        long spamUserId, long discussionMessageId,
        OrderByComparator orderByComparator) throws SystemException {
        List<SpamReport> list = findBySpamUserIdDiscussionMessageId(spamUserId,
                discussionMessageId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @param discussionMessageId the discussion message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport findBySpamUserIdDiscussionMessageId_Last(
        long spamUserId, long discussionMessageId,
        OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = fetchBySpamUserIdDiscussionMessageId_Last(spamUserId,
                discussionMessageId, orderByComparator);

        if (spamReport != null) {
            return spamReport;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("spamUserId=");
        msg.append(spamUserId);

        msg.append(", discussionMessageId=");
        msg.append(discussionMessageId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchSpamReportException(msg.toString());
    }

    /**
     * Returns the last spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @param discussionMessageId the discussion message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching spam report, or <code>null</code> if a matching spam report could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport fetchBySpamUserIdDiscussionMessageId_Last(
        long spamUserId, long discussionMessageId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySpamUserIdDiscussionMessageId(spamUserId,
                discussionMessageId);

        if (count == 0) {
            return null;
        }

        List<SpamReport> list = findBySpamUserIdDiscussionMessageId(spamUserId,
                discussionMessageId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the spam reports before and after the current spam report in the ordered set where spamUserId = &#63; and discussionMessageId = &#63;.
     *
     * @param id_ the primary key of the current spam report
     * @param spamUserId the spam user ID
     * @param discussionMessageId the discussion message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport[] findBySpamUserIdDiscussionMessageId_PrevAndNext(
        long id_, long spamUserId, long discussionMessageId,
        OrderByComparator orderByComparator)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            SpamReport[] array = new SpamReportImpl[3];

            array[0] = getBySpamUserIdDiscussionMessageId_PrevAndNext(session,
                    spamReport, spamUserId, discussionMessageId,
                    orderByComparator, true);

            array[1] = spamReport;

            array[2] = getBySpamUserIdDiscussionMessageId_PrevAndNext(session,
                    spamReport, spamUserId, discussionMessageId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected SpamReport getBySpamUserIdDiscussionMessageId_PrevAndNext(
        Session session, SpamReport spamReport, long spamUserId,
        long discussionMessageId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_SPAMREPORT_WHERE);

        query.append(_FINDER_COLUMN_SPAMUSERIDDISCUSSIONMESSAGEID_SPAMUSERID_2);

        query.append(_FINDER_COLUMN_SPAMUSERIDDISCUSSIONMESSAGEID_DISCUSSIONMESSAGEID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(SpamReportModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(spamUserId);

        qPos.add(discussionMessageId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(spamReport);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<SpamReport> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the spam reports where spamUserId = &#63; and discussionMessageId = &#63; from the database.
     *
     * @param spamUserId the spam user ID
     * @param discussionMessageId the discussion message ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySpamUserIdDiscussionMessageId(long spamUserId,
        long discussionMessageId) throws SystemException {
        for (SpamReport spamReport : findBySpamUserIdDiscussionMessageId(
                spamUserId, discussionMessageId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(spamReport);
        }
    }

    /**
     * Returns the number of spam reports where spamUserId = &#63; and discussionMessageId = &#63;.
     *
     * @param spamUserId the spam user ID
     * @param discussionMessageId the discussion message ID
     * @return the number of matching spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySpamUserIdDiscussionMessageId(long spamUserId,
        long discussionMessageId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SPAMUSERIDDISCUSSIONMESSAGEID;

        Object[] finderArgs = new Object[] { spamUserId, discussionMessageId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_SPAMREPORT_WHERE);

            query.append(_FINDER_COLUMN_SPAMUSERIDDISCUSSIONMESSAGEID_SPAMUSERID_2);

            query.append(_FINDER_COLUMN_SPAMUSERIDDISCUSSIONMESSAGEID_DISCUSSIONMESSAGEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(spamUserId);

                qPos.add(discussionMessageId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the spam report in the entity cache if it is enabled.
     *
     * @param spamReport the spam report
     */
    @Override
    public void cacheResult(SpamReport spamReport) {
        EntityCacheUtil.putResult(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportImpl.class, spamReport.getPrimaryKey(), spamReport);

        spamReport.resetOriginalValues();
    }

    /**
     * Caches the spam reports in the entity cache if it is enabled.
     *
     * @param spamReports the spam reports
     */
    @Override
    public void cacheResult(List<SpamReport> spamReports) {
        for (SpamReport spamReport : spamReports) {
            if (EntityCacheUtil.getResult(
                        SpamReportModelImpl.ENTITY_CACHE_ENABLED,
                        SpamReportImpl.class, spamReport.getPrimaryKey()) == null) {
                cacheResult(spamReport);
            } else {
                spamReport.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all spam reports.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(SpamReportImpl.class.getName());
        }

        EntityCacheUtil.clearCache(SpamReportImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the spam report.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(SpamReport spamReport) {
        EntityCacheUtil.removeResult(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportImpl.class, spamReport.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<SpamReport> spamReports) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (SpamReport spamReport : spamReports) {
            EntityCacheUtil.removeResult(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
                SpamReportImpl.class, spamReport.getPrimaryKey());
        }
    }

    /**
     * Creates a new spam report with the primary key. Does not add the spam report to the database.
     *
     * @param id_ the primary key for the new spam report
     * @return the new spam report
     */
    @Override
    public SpamReport create(long id_) {
        SpamReport spamReport = new SpamReportImpl();

        spamReport.setNew(true);
        spamReport.setPrimaryKey(id_);

        return spamReport;
    }

    /**
     * Removes the spam report with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id_ the primary key of the spam report
     * @return the spam report that was removed
     * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport remove(long id_)
        throws NoSuchSpamReportException, SystemException {
        return remove((Serializable) id_);
    }

    /**
     * Removes the spam report with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the spam report
     * @return the spam report that was removed
     * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport remove(Serializable primaryKey)
        throws NoSuchSpamReportException, SystemException {
        Session session = null;

        try {
            session = openSession();

            SpamReport spamReport = (SpamReport) session.get(SpamReportImpl.class,
                    primaryKey);

            if (spamReport == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchSpamReportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(spamReport);
        } catch (NoSuchSpamReportException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected SpamReport removeImpl(SpamReport spamReport)
        throws SystemException {
        spamReport = toUnwrappedModel(spamReport);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(spamReport)) {
                spamReport = (SpamReport) session.get(SpamReportImpl.class,
                        spamReport.getPrimaryKeyObj());
            }

            if (spamReport != null) {
                session.delete(spamReport);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (spamReport != null) {
            clearCache(spamReport);
        }

        return spamReport;
    }

    @Override
    public SpamReport updateImpl(com.ext.portlet.model.SpamReport spamReport)
        throws SystemException {
        spamReport = toUnwrappedModel(spamReport);

        boolean isNew = spamReport.isNew();

        SpamReportModelImpl spamReportModelImpl = (SpamReportModelImpl) spamReport;

        Session session = null;

        try {
            session = openSession();

            if (spamReport.isNew()) {
                session.save(spamReport);

                spamReport.setNew(false);
            } else {
                session.merge(spamReport);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !SpamReportModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((spamReportModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPAMUSERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        spamReportModelImpl.getOriginalSpamUserId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SPAMUSERID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPAMUSERID,
                    args);

                args = new Object[] { spamReportModelImpl.getSpamUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SPAMUSERID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPAMUSERID,
                    args);
            }

            if ((spamReportModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DISCUSSIONMESSAGEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        spamReportModelImpl.getOriginalDiscussionMessageId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DISCUSSIONMESSAGEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DISCUSSIONMESSAGEID,
                    args);

                args = new Object[] { spamReportModelImpl.getDiscussionMessageId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DISCUSSIONMESSAGEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DISCUSSIONMESSAGEID,
                    args);
            }

            if ((spamReportModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REPORTERUSERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        spamReportModelImpl.getOriginalReporterUserId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REPORTERUSERID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REPORTERUSERID,
                    args);

                args = new Object[] { spamReportModelImpl.getReporterUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REPORTERUSERID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_REPORTERUSERID,
                    args);
            }

            if ((spamReportModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPAMUSERIDDISCUSSIONMESSAGEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        spamReportModelImpl.getOriginalSpamUserId(),
                        spamReportModelImpl.getOriginalDiscussionMessageId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SPAMUSERIDDISCUSSIONMESSAGEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPAMUSERIDDISCUSSIONMESSAGEID,
                    args);

                args = new Object[] {
                        spamReportModelImpl.getSpamUserId(),
                        spamReportModelImpl.getDiscussionMessageId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SPAMUSERIDDISCUSSIONMESSAGEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SPAMUSERIDDISCUSSIONMESSAGEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
            SpamReportImpl.class, spamReport.getPrimaryKey(), spamReport);

        return spamReport;
    }

    protected SpamReport toUnwrappedModel(SpamReport spamReport) {
        if (spamReport instanceof SpamReportImpl) {
            return spamReport;
        }

        SpamReportImpl spamReportImpl = new SpamReportImpl();

        spamReportImpl.setNew(spamReport.isNew());
        spamReportImpl.setPrimaryKey(spamReport.getPrimaryKey());

        spamReportImpl.setId_(spamReport.getId_());
        spamReportImpl.setSpamUserId(spamReport.getSpamUserId());
        spamReportImpl.setReporterUserId(spamReport.getReporterUserId());
        spamReportImpl.setDiscussionMessageId(spamReport.getDiscussionMessageId());
        spamReportImpl.setIsAdminReport(spamReport.isIsAdminReport());

        return spamReportImpl;
    }

    /**
     * Returns the spam report with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the spam report
     * @return the spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport findByPrimaryKey(Serializable primaryKey)
        throws NoSuchSpamReportException, SystemException {
        SpamReport spamReport = fetchByPrimaryKey(primaryKey);

        if (spamReport == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchSpamReportException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return spamReport;
    }

    /**
     * Returns the spam report with the primary key or throws a {@link com.ext.portlet.NoSuchSpamReportException} if it could not be found.
     *
     * @param id_ the primary key of the spam report
     * @return the spam report
     * @throws com.ext.portlet.NoSuchSpamReportException if a spam report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport findByPrimaryKey(long id_)
        throws NoSuchSpamReportException, SystemException {
        return findByPrimaryKey((Serializable) id_);
    }

    /**
     * Returns the spam report with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the spam report
     * @return the spam report, or <code>null</code> if a spam report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        SpamReport spamReport = (SpamReport) EntityCacheUtil.getResult(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
                SpamReportImpl.class, primaryKey);

        if (spamReport == _nullSpamReport) {
            return null;
        }

        if (spamReport == null) {
            Session session = null;

            try {
                session = openSession();

                spamReport = (SpamReport) session.get(SpamReportImpl.class,
                        primaryKey);

                if (spamReport != null) {
                    cacheResult(spamReport);
                } else {
                    EntityCacheUtil.putResult(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
                        SpamReportImpl.class, primaryKey, _nullSpamReport);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(SpamReportModelImpl.ENTITY_CACHE_ENABLED,
                    SpamReportImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return spamReport;
    }

    /**
     * Returns the spam report with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id_ the primary key of the spam report
     * @return the spam report, or <code>null</code> if a spam report with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SpamReport fetchByPrimaryKey(long id_) throws SystemException {
        return fetchByPrimaryKey((Serializable) id_);
    }

    /**
     * Returns all the spam reports.
     *
     * @return the spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the spam reports.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of spam reports
     * @param end the upper bound of the range of spam reports (not inclusive)
     * @return the range of spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the spam reports.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SpamReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of spam reports
     * @param end the upper bound of the range of spam reports (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SpamReport> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<SpamReport> list = (List<SpamReport>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_SPAMREPORT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_SPAMREPORT;

                if (pagination) {
                    sql = sql.concat(SpamReportModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<SpamReport>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SpamReport>(list);
                } else {
                    list = (List<SpamReport>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the spam reports from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (SpamReport spamReport : findAll()) {
            remove(spamReport);
        }
    }

    /**
     * Returns the number of spam reports.
     *
     * @return the number of spam reports
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_SPAMREPORT);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the spam report persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.SpamReport")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<SpamReport>> listenersList = new ArrayList<ModelListener<SpamReport>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<SpamReport>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(SpamReportImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
