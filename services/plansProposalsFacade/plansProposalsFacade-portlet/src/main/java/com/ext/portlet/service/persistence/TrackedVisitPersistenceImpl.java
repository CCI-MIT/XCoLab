package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchTrackedVisitException;
import com.ext.portlet.model.TrackedVisit;
import com.ext.portlet.model.impl.TrackedVisitImpl;
import com.ext.portlet.model.impl.TrackedVisitModelImpl;
import com.ext.portlet.service.persistence.TrackedVisitPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the tracked visit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitPersistence
 * @see TrackedVisitUtil
 * @generated
 */
public class TrackedVisitPersistenceImpl extends BasePersistenceImpl<TrackedVisit>
    implements TrackedVisitPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link TrackedVisitUtil} to access the tracked visit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = TrackedVisitImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitModelImpl.FINDER_CACHE_ENABLED, TrackedVisitImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitModelImpl.FINDER_CACHE_ENABLED, TrackedVisitImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitModelImpl.FINDER_CACHE_ENABLED, TrackedVisitImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitModelImpl.FINDER_CACHE_ENABLED, TrackedVisitImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
            new String[] { String.class.getName() },
            TrackedVisitModelImpl.UUID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_UUID_UUID_1 = "trackedVisit.uuid IS NULL";
    private static final String _FINDER_COLUMN_UUID_UUID_2 = "trackedVisit.uuid = ?";
    private static final String _FINDER_COLUMN_UUID_UUID_3 = "(trackedVisit.uuid IS NULL OR trackedVisit.uuid = '')";
    private static final String _SQL_SELECT_TRACKEDVISIT = "SELECT trackedVisit FROM TrackedVisit trackedVisit";
    private static final String _SQL_SELECT_TRACKEDVISIT_WHERE = "SELECT trackedVisit FROM TrackedVisit trackedVisit WHERE ";
    private static final String _SQL_COUNT_TRACKEDVISIT = "SELECT COUNT(trackedVisit) FROM TrackedVisit trackedVisit";
    private static final String _SQL_COUNT_TRACKEDVISIT_WHERE = "SELECT COUNT(trackedVisit) FROM TrackedVisit trackedVisit WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "trackedVisit.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrackedVisit exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TrackedVisit exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(TrackedVisitPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id", "uuid"
            });
    private static TrackedVisit _nullTrackedVisit = new TrackedVisitImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<TrackedVisit> toCacheModel() {
                return _nullTrackedVisitCacheModel;
            }
        };

    private static CacheModel<TrackedVisit> _nullTrackedVisitCacheModel = new CacheModel<TrackedVisit>() {
            @Override
            public TrackedVisit toEntityModel() {
                return _nullTrackedVisit;
            }
        };

    public TrackedVisitPersistenceImpl() {
        setModelClass(TrackedVisit.class);
    }

    /**
     * Returns all the tracked visits where uuid = &#63;.
     *
     * @param uuid the uuid
     * @return the matching tracked visits
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TrackedVisit> findByUuid(String uuid) throws SystemException {
        return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the tracked visits where uuid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param uuid the uuid
     * @param start the lower bound of the range of tracked visits
     * @param end the upper bound of the range of tracked visits (not inclusive)
     * @return the range of matching tracked visits
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TrackedVisit> findByUuid(String uuid, int start, int end)
        throws SystemException {
        return findByUuid(uuid, start, end, null);
    }

    /**
     * Returns an ordered range of all the tracked visits where uuid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param uuid the uuid
     * @param start the lower bound of the range of tracked visits
     * @param end the upper bound of the range of tracked visits (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching tracked visits
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TrackedVisit> findByUuid(String uuid, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
            finderArgs = new Object[] { uuid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
            finderArgs = new Object[] { uuid, start, end, orderByComparator };
        }

        List<TrackedVisit> list = (List<TrackedVisit>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (TrackedVisit trackedVisit : list) {
                if (!Validator.equals(uuid, trackedVisit.getUuid())) {
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

            query.append(_SQL_SELECT_TRACKEDVISIT_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_UUID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(TrackedVisitModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

                if (!pagination) {
                    list = (List<TrackedVisit>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<TrackedVisit>(list);
                } else {
                    list = (List<TrackedVisit>) QueryUtil.list(q, getDialect(),
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
     * Returns the first tracked visit in the ordered set where uuid = &#63;.
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching tracked visit
     * @throws com.ext.portlet.NoSuchTrackedVisitException if a matching tracked visit could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit findByUuid_First(String uuid,
        OrderByComparator orderByComparator)
        throws NoSuchTrackedVisitException, SystemException {
        TrackedVisit trackedVisit = fetchByUuid_First(uuid, orderByComparator);

        if (trackedVisit != null) {
            return trackedVisit;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uuid=");
        msg.append(uuid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchTrackedVisitException(msg.toString());
    }

    /**
     * Returns the first tracked visit in the ordered set where uuid = &#63;.
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching tracked visit, or <code>null</code> if a matching tracked visit could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit fetchByUuid_First(String uuid,
        OrderByComparator orderByComparator) throws SystemException {
        List<TrackedVisit> list = findByUuid(uuid, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last tracked visit in the ordered set where uuid = &#63;.
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching tracked visit
     * @throws com.ext.portlet.NoSuchTrackedVisitException if a matching tracked visit could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit findByUuid_Last(String uuid,
        OrderByComparator orderByComparator)
        throws NoSuchTrackedVisitException, SystemException {
        TrackedVisit trackedVisit = fetchByUuid_Last(uuid, orderByComparator);

        if (trackedVisit != null) {
            return trackedVisit;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("uuid=");
        msg.append(uuid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchTrackedVisitException(msg.toString());
    }

    /**
     * Returns the last tracked visit in the ordered set where uuid = &#63;.
     *
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching tracked visit, or <code>null</code> if a matching tracked visit could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit fetchByUuid_Last(String uuid,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUuid(uuid);

        if (count == 0) {
            return null;
        }

        List<TrackedVisit> list = findByUuid(uuid, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the tracked visits before and after the current tracked visit in the ordered set where uuid = &#63;.
     *
     * @param id the primary key of the current tracked visit
     * @param uuid the uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next tracked visit
     * @throws com.ext.portlet.NoSuchTrackedVisitException if a tracked visit with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit[] findByUuid_PrevAndNext(long id, String uuid,
        OrderByComparator orderByComparator)
        throws NoSuchTrackedVisitException, SystemException {
        TrackedVisit trackedVisit = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            TrackedVisit[] array = new TrackedVisitImpl[3];

            array[0] = getByUuid_PrevAndNext(session, trackedVisit, uuid,
                    orderByComparator, true);

            array[1] = trackedVisit;

            array[2] = getByUuid_PrevAndNext(session, trackedVisit, uuid,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected TrackedVisit getByUuid_PrevAndNext(Session session,
        TrackedVisit trackedVisit, String uuid,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_TRACKEDVISIT_WHERE);

        boolean bindUuid = false;

        if (uuid == null) {
            query.append(_FINDER_COLUMN_UUID_UUID_1);
        } else if (uuid.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_UUID_UUID_3);
        } else {
            bindUuid = true;

            query.append(_FINDER_COLUMN_UUID_UUID_2);
        }

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
            query.append(TrackedVisitModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindUuid) {
            qPos.add(uuid);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(trackedVisit);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<TrackedVisit> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the tracked visits where uuid = &#63; from the database.
     *
     * @param uuid the uuid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUuid(String uuid) throws SystemException {
        for (TrackedVisit trackedVisit : findByUuid(uuid, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(trackedVisit);
        }
    }

    /**
     * Returns the number of tracked visits where uuid = &#63;.
     *
     * @param uuid the uuid
     * @return the number of matching tracked visits
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUuid(String uuid) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

        Object[] finderArgs = new Object[] { uuid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_TRACKEDVISIT_WHERE);

            boolean bindUuid = false;

            if (uuid == null) {
                query.append(_FINDER_COLUMN_UUID_UUID_1);
            } else if (uuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_UUID_UUID_3);
            } else {
                bindUuid = true;

                query.append(_FINDER_COLUMN_UUID_UUID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUuid) {
                    qPos.add(uuid);
                }

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
     * Caches the tracked visit in the entity cache if it is enabled.
     *
     * @param trackedVisit the tracked visit
     */
    @Override
    public void cacheResult(TrackedVisit trackedVisit) {
        EntityCacheUtil.putResult(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitImpl.class, trackedVisit.getPrimaryKey(), trackedVisit);

        trackedVisit.resetOriginalValues();
    }

    /**
     * Caches the tracked visits in the entity cache if it is enabled.
     *
     * @param trackedVisits the tracked visits
     */
    @Override
    public void cacheResult(List<TrackedVisit> trackedVisits) {
        for (TrackedVisit trackedVisit : trackedVisits) {
            if (EntityCacheUtil.getResult(
                        TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
                        TrackedVisitImpl.class, trackedVisit.getPrimaryKey()) == null) {
                cacheResult(trackedVisit);
            } else {
                trackedVisit.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all tracked visits.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(TrackedVisitImpl.class.getName());
        }

        EntityCacheUtil.clearCache(TrackedVisitImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the tracked visit.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(TrackedVisit trackedVisit) {
        EntityCacheUtil.removeResult(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitImpl.class, trackedVisit.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<TrackedVisit> trackedVisits) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (TrackedVisit trackedVisit : trackedVisits) {
            EntityCacheUtil.removeResult(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
                TrackedVisitImpl.class, trackedVisit.getPrimaryKey());
        }
    }

    /**
     * Creates a new tracked visit with the primary key. Does not add the tracked visit to the database.
     *
     * @param id the primary key for the new tracked visit
     * @return the new tracked visit
     */
    @Override
    public TrackedVisit create(long id) {
        TrackedVisit trackedVisit = new TrackedVisitImpl();

        trackedVisit.setNew(true);
        trackedVisit.setPrimaryKey(id);

        return trackedVisit;
    }

    /**
     * Removes the tracked visit with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the tracked visit
     * @return the tracked visit that was removed
     * @throws com.ext.portlet.NoSuchTrackedVisitException if a tracked visit with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit remove(long id)
        throws NoSuchTrackedVisitException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the tracked visit with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the tracked visit
     * @return the tracked visit that was removed
     * @throws com.ext.portlet.NoSuchTrackedVisitException if a tracked visit with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit remove(Serializable primaryKey)
        throws NoSuchTrackedVisitException, SystemException {
        Session session = null;

        try {
            session = openSession();

            TrackedVisit trackedVisit = (TrackedVisit) session.get(TrackedVisitImpl.class,
                    primaryKey);

            if (trackedVisit == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchTrackedVisitException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(trackedVisit);
        } catch (NoSuchTrackedVisitException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected TrackedVisit removeImpl(TrackedVisit trackedVisit)
        throws SystemException {
        trackedVisit = toUnwrappedModel(trackedVisit);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(trackedVisit)) {
                trackedVisit = (TrackedVisit) session.get(TrackedVisitImpl.class,
                        trackedVisit.getPrimaryKeyObj());
            }

            if (trackedVisit != null) {
                session.delete(trackedVisit);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (trackedVisit != null) {
            clearCache(trackedVisit);
        }

        return trackedVisit;
    }

    @Override
    public TrackedVisit updateImpl(
        com.ext.portlet.model.TrackedVisit trackedVisit)
        throws SystemException {
        trackedVisit = toUnwrappedModel(trackedVisit);

        boolean isNew = trackedVisit.isNew();

        TrackedVisitModelImpl trackedVisitModelImpl = (TrackedVisitModelImpl) trackedVisit;

        Session session = null;

        try {
            session = openSession();

            if (trackedVisit.isNew()) {
                session.save(trackedVisit);

                trackedVisit.setNew(false);
            } else {
                session.merge(trackedVisit);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !TrackedVisitModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((trackedVisitModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        trackedVisitModelImpl.getOriginalUuid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
                    args);

                args = new Object[] { trackedVisitModelImpl.getUuid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
                    args);
            }
        }

        EntityCacheUtil.putResult(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitImpl.class, trackedVisit.getPrimaryKey(), trackedVisit);

        return trackedVisit;
    }

    protected TrackedVisit toUnwrappedModel(TrackedVisit trackedVisit) {
        if (trackedVisit instanceof TrackedVisitImpl) {
            return trackedVisit;
        }

        TrackedVisitImpl trackedVisitImpl = new TrackedVisitImpl();

        trackedVisitImpl.setNew(trackedVisit.isNew());
        trackedVisitImpl.setPrimaryKey(trackedVisit.getPrimaryKey());

        trackedVisitImpl.setId(trackedVisit.getId());
        trackedVisitImpl.setUuid(trackedVisit.getUuid());
        trackedVisitImpl.setIp(trackedVisit.getIp());
        trackedVisitImpl.setCity(trackedVisit.getCity());
        trackedVisitImpl.setCountry(trackedVisit.getCountry());
        trackedVisitImpl.setUrl(trackedVisit.getUrl());
        trackedVisitImpl.setBrowser(trackedVisit.getBrowser());
        trackedVisitImpl.setHeaders(trackedVisit.getHeaders());
        trackedVisitImpl.setReferer(trackedVisit.getReferer());
        trackedVisitImpl.setCreateDate(trackedVisit.getCreateDate());

        return trackedVisitImpl;
    }

    /**
     * Returns the tracked visit with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the tracked visit
     * @return the tracked visit
     * @throws com.ext.portlet.NoSuchTrackedVisitException if a tracked visit with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit findByPrimaryKey(Serializable primaryKey)
        throws NoSuchTrackedVisitException, SystemException {
        TrackedVisit trackedVisit = fetchByPrimaryKey(primaryKey);

        if (trackedVisit == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchTrackedVisitException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return trackedVisit;
    }

    /**
     * Returns the tracked visit with the primary key or throws a {@link com.ext.portlet.NoSuchTrackedVisitException} if it could not be found.
     *
     * @param id the primary key of the tracked visit
     * @return the tracked visit
     * @throws com.ext.portlet.NoSuchTrackedVisitException if a tracked visit with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit findByPrimaryKey(long id)
        throws NoSuchTrackedVisitException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the tracked visit with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the tracked visit
     * @return the tracked visit, or <code>null</code> if a tracked visit with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        TrackedVisit trackedVisit = (TrackedVisit) EntityCacheUtil.getResult(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
                TrackedVisitImpl.class, primaryKey);

        if (trackedVisit == _nullTrackedVisit) {
            return null;
        }

        if (trackedVisit == null) {
            Session session = null;

            try {
                session = openSession();

                trackedVisit = (TrackedVisit) session.get(TrackedVisitImpl.class,
                        primaryKey);

                if (trackedVisit != null) {
                    cacheResult(trackedVisit);
                } else {
                    EntityCacheUtil.putResult(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
                        TrackedVisitImpl.class, primaryKey, _nullTrackedVisit);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(TrackedVisitModelImpl.ENTITY_CACHE_ENABLED,
                    TrackedVisitImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return trackedVisit;
    }

    /**
     * Returns the tracked visit with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the tracked visit
     * @return the tracked visit, or <code>null</code> if a tracked visit with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisit fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the tracked visits.
     *
     * @return the tracked visits
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TrackedVisit> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the tracked visits.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of tracked visits
     * @param end the upper bound of the range of tracked visits (not inclusive)
     * @return the range of tracked visits
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TrackedVisit> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the tracked visits.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of tracked visits
     * @param end the upper bound of the range of tracked visits (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of tracked visits
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TrackedVisit> findAll(int start, int end,
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

        List<TrackedVisit> list = (List<TrackedVisit>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_TRACKEDVISIT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_TRACKEDVISIT;

                if (pagination) {
                    sql = sql.concat(TrackedVisitModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<TrackedVisit>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<TrackedVisit>(list);
                } else {
                    list = (List<TrackedVisit>) QueryUtil.list(q, getDialect(),
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
     * Removes all the tracked visits from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (TrackedVisit trackedVisit : findAll()) {
            remove(trackedVisit);
        }
    }

    /**
     * Returns the number of tracked visits.
     *
     * @return the number of tracked visits
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

                Query q = session.createQuery(_SQL_COUNT_TRACKEDVISIT);

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

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the tracked visit persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.TrackedVisit")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<TrackedVisit>> listenersList = new ArrayList<ModelListener<TrackedVisit>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<TrackedVisit>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(TrackedVisitImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
