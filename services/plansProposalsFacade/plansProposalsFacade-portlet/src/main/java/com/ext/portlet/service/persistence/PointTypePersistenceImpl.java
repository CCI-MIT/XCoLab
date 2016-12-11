package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPointTypeException;
import com.ext.portlet.model.PointType;
import com.ext.portlet.model.impl.PointTypeImpl;
import com.ext.portlet.model.impl.PointTypeModelImpl;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the point type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointTypePersistence
 * @see PointTypeUtil
 * @generated
 */
public class PointTypePersistenceImpl extends BasePersistenceImpl<PointType>
    implements PointTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PointTypeUtil} to access the point type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PointTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
            PointTypeModelImpl.FINDER_CACHE_ENABLED, PointTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
            PointTypeModelImpl.FINDER_CACHE_ENABLED, PointTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
            PointTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPOINTTYPEID =
        new FinderPath(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
            PointTypeModelImpl.FINDER_CACHE_ENABLED, PointTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByParentPointTypeId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPOINTTYPEID =
        new FinderPath(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
            PointTypeModelImpl.FINDER_CACHE_ENABLED, PointTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByParentPointTypeId", new String[] { Long.class.getName() },
            PointTypeModelImpl.PARENTPOINTTYPEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PARENTPOINTTYPEID = new FinderPath(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
            PointTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByParentPointTypeId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PARENTPOINTTYPEID_PARENTPOINTTYPEID_2 =
        "pointType.parentPointTypeId = ?";
    private static final String _SQL_SELECT_POINTTYPE = "SELECT pointType FROM PointType pointType";
    private static final String _SQL_SELECT_POINTTYPE_WHERE = "SELECT pointType FROM PointType pointType WHERE ";
    private static final String _SQL_COUNT_POINTTYPE = "SELECT COUNT(pointType) FROM PointType pointType";
    private static final String _SQL_COUNT_POINTTYPE_WHERE = "SELECT COUNT(pointType) FROM PointType pointType WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "pointType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PointType exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PointType exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PointTypePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static PointType _nullPointType = new PointTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PointType> toCacheModel() {
                return _nullPointTypeCacheModel;
            }
        };

    private static CacheModel<PointType> _nullPointTypeCacheModel = new CacheModel<PointType>() {
            @Override
            public PointType toEntityModel() {
                return _nullPointType;
            }
        };

    public PointTypePersistenceImpl() {
        setModelClass(PointType.class);
    }

    /**
     * Returns all the point types where parentPointTypeId = &#63;.
     *
     * @param parentPointTypeId the parent point type ID
     * @return the matching point types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointType> findByParentPointTypeId(long parentPointTypeId)
        throws SystemException {
        return findByParentPointTypeId(parentPointTypeId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the point types where parentPointTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param parentPointTypeId the parent point type ID
     * @param start the lower bound of the range of point types
     * @param end the upper bound of the range of point types (not inclusive)
     * @return the range of matching point types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointType> findByParentPointTypeId(long parentPointTypeId,
        int start, int end) throws SystemException {
        return findByParentPointTypeId(parentPointTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the point types where parentPointTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param parentPointTypeId the parent point type ID
     * @param start the lower bound of the range of point types
     * @param end the upper bound of the range of point types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching point types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointType> findByParentPointTypeId(long parentPointTypeId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPOINTTYPEID;
            finderArgs = new Object[] { parentPointTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTPOINTTYPEID;
            finderArgs = new Object[] {
                    parentPointTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<PointType> list = (List<PointType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PointType pointType : list) {
                if ((parentPointTypeId != pointType.getParentPointTypeId())) {
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

            query.append(_SQL_SELECT_POINTTYPE_WHERE);

            query.append(_FINDER_COLUMN_PARENTPOINTTYPEID_PARENTPOINTTYPEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointTypeModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(parentPointTypeId);

                if (!pagination) {
                    list = (List<PointType>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointType>(list);
                } else {
                    list = (List<PointType>) QueryUtil.list(q, getDialect(),
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
     * Returns the first point type in the ordered set where parentPointTypeId = &#63;.
     *
     * @param parentPointTypeId the parent point type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching point type
     * @throws com.ext.portlet.NoSuchPointTypeException if a matching point type could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType findByParentPointTypeId_First(long parentPointTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchPointTypeException, SystemException {
        PointType pointType = fetchByParentPointTypeId_First(parentPointTypeId,
                orderByComparator);

        if (pointType != null) {
            return pointType;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("parentPointTypeId=");
        msg.append(parentPointTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointTypeException(msg.toString());
    }

    /**
     * Returns the first point type in the ordered set where parentPointTypeId = &#63;.
     *
     * @param parentPointTypeId the parent point type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching point type, or <code>null</code> if a matching point type could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType fetchByParentPointTypeId_First(long parentPointTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PointType> list = findByParentPointTypeId(parentPointTypeId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last point type in the ordered set where parentPointTypeId = &#63;.
     *
     * @param parentPointTypeId the parent point type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching point type
     * @throws com.ext.portlet.NoSuchPointTypeException if a matching point type could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType findByParentPointTypeId_Last(long parentPointTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchPointTypeException, SystemException {
        PointType pointType = fetchByParentPointTypeId_Last(parentPointTypeId,
                orderByComparator);

        if (pointType != null) {
            return pointType;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("parentPointTypeId=");
        msg.append(parentPointTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointTypeException(msg.toString());
    }

    /**
     * Returns the last point type in the ordered set where parentPointTypeId = &#63;.
     *
     * @param parentPointTypeId the parent point type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching point type, or <code>null</code> if a matching point type could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType fetchByParentPointTypeId_Last(long parentPointTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByParentPointTypeId(parentPointTypeId);

        if (count == 0) {
            return null;
        }

        List<PointType> list = findByParentPointTypeId(parentPointTypeId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the point types before and after the current point type in the ordered set where parentPointTypeId = &#63;.
     *
     * @param id the primary key of the current point type
     * @param parentPointTypeId the parent point type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next point type
     * @throws com.ext.portlet.NoSuchPointTypeException if a point type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType[] findByParentPointTypeId_PrevAndNext(long id,
        long parentPointTypeId, OrderByComparator orderByComparator)
        throws NoSuchPointTypeException, SystemException {
        PointType pointType = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PointType[] array = new PointTypeImpl[3];

            array[0] = getByParentPointTypeId_PrevAndNext(session, pointType,
                    parentPointTypeId, orderByComparator, true);

            array[1] = pointType;

            array[2] = getByParentPointTypeId_PrevAndNext(session, pointType,
                    parentPointTypeId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PointType getByParentPointTypeId_PrevAndNext(Session session,
        PointType pointType, long parentPointTypeId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTTYPE_WHERE);

        query.append(_FINDER_COLUMN_PARENTPOINTTYPEID_PARENTPOINTTYPEID_2);

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
            query.append(PointTypeModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(parentPointTypeId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(pointType);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PointType> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the point types where parentPointTypeId = &#63; from the database.
     *
     * @param parentPointTypeId the parent point type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByParentPointTypeId(long parentPointTypeId)
        throws SystemException {
        for (PointType pointType : findByParentPointTypeId(parentPointTypeId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(pointType);
        }
    }

    /**
     * Returns the number of point types where parentPointTypeId = &#63;.
     *
     * @param parentPointTypeId the parent point type ID
     * @return the number of matching point types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByParentPointTypeId(long parentPointTypeId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTPOINTTYPEID;

        Object[] finderArgs = new Object[] { parentPointTypeId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_POINTTYPE_WHERE);

            query.append(_FINDER_COLUMN_PARENTPOINTTYPEID_PARENTPOINTTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(parentPointTypeId);

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
     * Caches the point type in the entity cache if it is enabled.
     *
     * @param pointType the point type
     */
    @Override
    public void cacheResult(PointType pointType) {
        EntityCacheUtil.putResult(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
            PointTypeImpl.class, pointType.getPrimaryKey(), pointType);

        pointType.resetOriginalValues();
    }

    /**
     * Caches the point types in the entity cache if it is enabled.
     *
     * @param pointTypes the point types
     */
    @Override
    public void cacheResult(List<PointType> pointTypes) {
        for (PointType pointType : pointTypes) {
            if (EntityCacheUtil.getResult(
                        PointTypeModelImpl.ENTITY_CACHE_ENABLED,
                        PointTypeImpl.class, pointType.getPrimaryKey()) == null) {
                cacheResult(pointType);
            } else {
                pointType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all point types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PointTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PointTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the point type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PointType pointType) {
        EntityCacheUtil.removeResult(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
            PointTypeImpl.class, pointType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PointType> pointTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PointType pointType : pointTypes) {
            EntityCacheUtil.removeResult(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
                PointTypeImpl.class, pointType.getPrimaryKey());
        }
    }

    /**
     * Creates a new point type with the primary key. Does not add the point type to the database.
     *
     * @param id the primary key for the new point type
     * @return the new point type
     */
    @Override
    public PointType create(long id) {
        PointType pointType = new PointTypeImpl();

        pointType.setNew(true);
        pointType.setPrimaryKey(id);

        return pointType;
    }

    /**
     * Removes the point type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the point type
     * @return the point type that was removed
     * @throws com.ext.portlet.NoSuchPointTypeException if a point type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType remove(long id)
        throws NoSuchPointTypeException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the point type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the point type
     * @return the point type that was removed
     * @throws com.ext.portlet.NoSuchPointTypeException if a point type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType remove(Serializable primaryKey)
        throws NoSuchPointTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PointType pointType = (PointType) session.get(PointTypeImpl.class,
                    primaryKey);

            if (pointType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPointTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(pointType);
        } catch (NoSuchPointTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PointType removeImpl(PointType pointType)
        throws SystemException {
        pointType = toUnwrappedModel(pointType);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(pointType)) {
                pointType = (PointType) session.get(PointTypeImpl.class,
                        pointType.getPrimaryKeyObj());
            }

            if (pointType != null) {
                session.delete(pointType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (pointType != null) {
            clearCache(pointType);
        }

        return pointType;
    }

    @Override
    public PointType updateImpl(com.ext.portlet.model.PointType pointType)
        throws SystemException {
        pointType = toUnwrappedModel(pointType);

        boolean isNew = pointType.isNew();

        PointTypeModelImpl pointTypeModelImpl = (PointTypeModelImpl) pointType;

        Session session = null;

        try {
            session = openSession();

            if (pointType.isNew()) {
                session.save(pointType);

                pointType.setNew(false);
            } else {
                session.merge(pointType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PointTypeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((pointTypeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPOINTTYPEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointTypeModelImpl.getOriginalParentPointTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTPOINTTYPEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPOINTTYPEID,
                    args);

                args = new Object[] { pointTypeModelImpl.getParentPointTypeId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTPOINTTYPEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTPOINTTYPEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
            PointTypeImpl.class, pointType.getPrimaryKey(), pointType);

        return pointType;
    }

    protected PointType toUnwrappedModel(PointType pointType) {
        if (pointType instanceof PointTypeImpl) {
            return pointType;
        }

        PointTypeImpl pointTypeImpl = new PointTypeImpl();

        pointTypeImpl.setNew(pointType.isNew());
        pointTypeImpl.setPrimaryKey(pointType.getPrimaryKey());

        pointTypeImpl.setId(pointType.getId());
        pointTypeImpl.setParentPointTypeId(pointType.getParentPointTypeId());
        pointTypeImpl.setPercentageOfParent(pointType.getPercentageOfParent());
        pointTypeImpl.setDistributionStrategy(pointType.getDistributionStrategy());
        pointTypeImpl.setReceiverLimitationStrategy(pointType.getReceiverLimitationStrategy());
        pointTypeImpl.setName(pointType.getName());
        pointTypeImpl.setSort(pointType.getSort());

        return pointTypeImpl;
    }

    /**
     * Returns the point type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the point type
     * @return the point type
     * @throws com.ext.portlet.NoSuchPointTypeException if a point type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPointTypeException, SystemException {
        PointType pointType = fetchByPrimaryKey(primaryKey);

        if (pointType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPointTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return pointType;
    }

    /**
     * Returns the point type with the primary key or throws a {@link com.ext.portlet.NoSuchPointTypeException} if it could not be found.
     *
     * @param id the primary key of the point type
     * @return the point type
     * @throws com.ext.portlet.NoSuchPointTypeException if a point type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType findByPrimaryKey(long id)
        throws NoSuchPointTypeException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the point type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the point type
     * @return the point type, or <code>null</code> if a point type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PointType pointType = (PointType) EntityCacheUtil.getResult(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
                PointTypeImpl.class, primaryKey);

        if (pointType == _nullPointType) {
            return null;
        }

        if (pointType == null) {
            Session session = null;

            try {
                session = openSession();

                pointType = (PointType) session.get(PointTypeImpl.class,
                        primaryKey);

                if (pointType != null) {
                    cacheResult(pointType);
                } else {
                    EntityCacheUtil.putResult(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
                        PointTypeImpl.class, primaryKey, _nullPointType);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PointTypeModelImpl.ENTITY_CACHE_ENABLED,
                    PointTypeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return pointType;
    }

    /**
     * Returns the point type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the point type
     * @return the point type, or <code>null</code> if a point type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointType fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the point types.
     *
     * @return the point types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the point types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of point types
     * @param end the upper bound of the range of point types (not inclusive)
     * @return the range of point types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the point types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of point types
     * @param end the upper bound of the range of point types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of point types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointType> findAll(int start, int end,
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

        List<PointType> list = (List<PointType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_POINTTYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_POINTTYPE;

                if (pagination) {
                    sql = sql.concat(PointTypeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PointType>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointType>(list);
                } else {
                    list = (List<PointType>) QueryUtil.list(q, getDialect(),
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
     * Removes all the point types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PointType pointType : findAll()) {
            remove(pointType);
        }
    }

    /**
     * Returns the number of point types.
     *
     * @return the number of point types
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

                Query q = session.createQuery(_SQL_COUNT_POINTTYPE);

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
     * Initializes the point type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PointType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PointType>> listenersList = new ArrayList<ModelListener<PointType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PointType>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PointTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
