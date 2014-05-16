package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchBalloonLinkException;
import com.ext.portlet.model.BalloonLink;
import com.ext.portlet.model.impl.BalloonLinkImpl;
import com.ext.portlet.model.impl.BalloonLinkModelImpl;
import com.ext.portlet.service.persistence.BalloonLinkPersistence;

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
 * The persistence implementation for the balloon link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonLinkPersistence
 * @see BalloonLinkUtil
 * @generated
 */
public class BalloonLinkPersistenceImpl extends BasePersistenceImpl<BalloonLink>
    implements BalloonLinkPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link BalloonLinkUtil} to access the balloon link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = BalloonLinkImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
            BalloonLinkModelImpl.FINDER_CACHE_ENABLED, BalloonLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
            BalloonLinkModelImpl.FINDER_CACHE_ENABLED, BalloonLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
            BalloonLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BALLOONUSERUUID =
        new FinderPath(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
            BalloonLinkModelImpl.FINDER_CACHE_ENABLED, BalloonLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBalloonUserUuid",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BALLOONUSERUUID =
        new FinderPath(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
            BalloonLinkModelImpl.FINDER_CACHE_ENABLED, BalloonLinkImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBalloonUserUuid",
            new String[] { String.class.getName() },
            BalloonLinkModelImpl.BALLOONUSERUUID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_BALLOONUSERUUID = new FinderPath(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
            BalloonLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByBalloonUserUuid", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_1 =
        "balloonLink.balloonUserUuid IS NULL";
    private static final String _FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_2 =
        "balloonLink.balloonUserUuid = ?";
    private static final String _FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_3 =
        "(balloonLink.balloonUserUuid IS NULL OR balloonLink.balloonUserUuid = '')";
    private static final String _SQL_SELECT_BALLOONLINK = "SELECT balloonLink FROM BalloonLink balloonLink";
    private static final String _SQL_SELECT_BALLOONLINK_WHERE = "SELECT balloonLink FROM BalloonLink balloonLink WHERE ";
    private static final String _SQL_COUNT_BALLOONLINK = "SELECT COUNT(balloonLink) FROM BalloonLink balloonLink";
    private static final String _SQL_COUNT_BALLOONLINK_WHERE = "SELECT COUNT(balloonLink) FROM BalloonLink balloonLink WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "balloonLink.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BalloonLink exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BalloonLink exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(BalloonLinkPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "uuid"
            });
    private static BalloonLink _nullBalloonLink = new BalloonLinkImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<BalloonLink> toCacheModel() {
                return _nullBalloonLinkCacheModel;
            }
        };

    private static CacheModel<BalloonLink> _nullBalloonLinkCacheModel = new CacheModel<BalloonLink>() {
            @Override
            public BalloonLink toEntityModel() {
                return _nullBalloonLink;
            }
        };

    public BalloonLinkPersistenceImpl() {
        setModelClass(BalloonLink.class);
    }

    /**
     * Returns all the balloon links where balloonUserUuid = &#63;.
     *
     * @param balloonUserUuid the balloon user uuid
     * @return the matching balloon links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonLink> findByBalloonUserUuid(String balloonUserUuid)
        throws SystemException {
        return findByBalloonUserUuid(balloonUserUuid, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the balloon links where balloonUserUuid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param balloonUserUuid the balloon user uuid
     * @param start the lower bound of the range of balloon links
     * @param end the upper bound of the range of balloon links (not inclusive)
     * @return the range of matching balloon links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonLink> findByBalloonUserUuid(String balloonUserUuid,
        int start, int end) throws SystemException {
        return findByBalloonUserUuid(balloonUserUuid, start, end, null);
    }

    /**
     * Returns an ordered range of all the balloon links where balloonUserUuid = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param balloonUserUuid the balloon user uuid
     * @param start the lower bound of the range of balloon links
     * @param end the upper bound of the range of balloon links (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching balloon links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonLink> findByBalloonUserUuid(String balloonUserUuid,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BALLOONUSERUUID;
            finderArgs = new Object[] { balloonUserUuid };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BALLOONUSERUUID;
            finderArgs = new Object[] {
                    balloonUserUuid,
                    
                    start, end, orderByComparator
                };
        }

        List<BalloonLink> list = (List<BalloonLink>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BalloonLink balloonLink : list) {
                if (!Validator.equals(balloonUserUuid,
                            balloonLink.getBalloonUserUuid())) {
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

            query.append(_SQL_SELECT_BALLOONLINK_WHERE);

            boolean bindBalloonUserUuid = false;

            if (balloonUserUuid == null) {
                query.append(_FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_1);
            } else if (balloonUserUuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_3);
            } else {
                bindBalloonUserUuid = true;

                query.append(_FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BalloonLinkModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindBalloonUserUuid) {
                    qPos.add(balloonUserUuid);
                }

                if (!pagination) {
                    list = (List<BalloonLink>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BalloonLink>(list);
                } else {
                    list = (List<BalloonLink>) QueryUtil.list(q, getDialect(),
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
     * Returns the first balloon link in the ordered set where balloonUserUuid = &#63;.
     *
     * @param balloonUserUuid the balloon user uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching balloon link
     * @throws com.ext.portlet.NoSuchBalloonLinkException if a matching balloon link could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink findByBalloonUserUuid_First(String balloonUserUuid,
        OrderByComparator orderByComparator)
        throws NoSuchBalloonLinkException, SystemException {
        BalloonLink balloonLink = fetchByBalloonUserUuid_First(balloonUserUuid,
                orderByComparator);

        if (balloonLink != null) {
            return balloonLink;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("balloonUserUuid=");
        msg.append(balloonUserUuid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBalloonLinkException(msg.toString());
    }

    /**
     * Returns the first balloon link in the ordered set where balloonUserUuid = &#63;.
     *
     * @param balloonUserUuid the balloon user uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching balloon link, or <code>null</code> if a matching balloon link could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink fetchByBalloonUserUuid_First(String balloonUserUuid,
        OrderByComparator orderByComparator) throws SystemException {
        List<BalloonLink> list = findByBalloonUserUuid(balloonUserUuid, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last balloon link in the ordered set where balloonUserUuid = &#63;.
     *
     * @param balloonUserUuid the balloon user uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching balloon link
     * @throws com.ext.portlet.NoSuchBalloonLinkException if a matching balloon link could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink findByBalloonUserUuid_Last(String balloonUserUuid,
        OrderByComparator orderByComparator)
        throws NoSuchBalloonLinkException, SystemException {
        BalloonLink balloonLink = fetchByBalloonUserUuid_Last(balloonUserUuid,
                orderByComparator);

        if (balloonLink != null) {
            return balloonLink;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("balloonUserUuid=");
        msg.append(balloonUserUuid);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBalloonLinkException(msg.toString());
    }

    /**
     * Returns the last balloon link in the ordered set where balloonUserUuid = &#63;.
     *
     * @param balloonUserUuid the balloon user uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching balloon link, or <code>null</code> if a matching balloon link could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink fetchByBalloonUserUuid_Last(String balloonUserUuid,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByBalloonUserUuid(balloonUserUuid);

        if (count == 0) {
            return null;
        }

        List<BalloonLink> list = findByBalloonUserUuid(balloonUserUuid,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the balloon links before and after the current balloon link in the ordered set where balloonUserUuid = &#63;.
     *
     * @param uuid the primary key of the current balloon link
     * @param balloonUserUuid the balloon user uuid
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next balloon link
     * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink[] findByBalloonUserUuid_PrevAndNext(String uuid,
        String balloonUserUuid, OrderByComparator orderByComparator)
        throws NoSuchBalloonLinkException, SystemException {
        BalloonLink balloonLink = findByPrimaryKey(uuid);

        Session session = null;

        try {
            session = openSession();

            BalloonLink[] array = new BalloonLinkImpl[3];

            array[0] = getByBalloonUserUuid_PrevAndNext(session, balloonLink,
                    balloonUserUuid, orderByComparator, true);

            array[1] = balloonLink;

            array[2] = getByBalloonUserUuid_PrevAndNext(session, balloonLink,
                    balloonUserUuid, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BalloonLink getByBalloonUserUuid_PrevAndNext(Session session,
        BalloonLink balloonLink, String balloonUserUuid,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BALLOONLINK_WHERE);

        boolean bindBalloonUserUuid = false;

        if (balloonUserUuid == null) {
            query.append(_FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_1);
        } else if (balloonUserUuid.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_3);
        } else {
            bindBalloonUserUuid = true;

            query.append(_FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_2);
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
            query.append(BalloonLinkModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindBalloonUserUuid) {
            qPos.add(balloonUserUuid);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(balloonLink);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BalloonLink> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the balloon links where balloonUserUuid = &#63; from the database.
     *
     * @param balloonUserUuid the balloon user uuid
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByBalloonUserUuid(String balloonUserUuid)
        throws SystemException {
        for (BalloonLink balloonLink : findByBalloonUserUuid(balloonUserUuid,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(balloonLink);
        }
    }

    /**
     * Returns the number of balloon links where balloonUserUuid = &#63;.
     *
     * @param balloonUserUuid the balloon user uuid
     * @return the number of matching balloon links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByBalloonUserUuid(String balloonUserUuid)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_BALLOONUSERUUID;

        Object[] finderArgs = new Object[] { balloonUserUuid };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_BALLOONLINK_WHERE);

            boolean bindBalloonUserUuid = false;

            if (balloonUserUuid == null) {
                query.append(_FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_1);
            } else if (balloonUserUuid.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_3);
            } else {
                bindBalloonUserUuid = true;

                query.append(_FINDER_COLUMN_BALLOONUSERUUID_BALLOONUSERUUID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindBalloonUserUuid) {
                    qPos.add(balloonUserUuid);
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
     * Caches the balloon link in the entity cache if it is enabled.
     *
     * @param balloonLink the balloon link
     */
    @Override
    public void cacheResult(BalloonLink balloonLink) {
        EntityCacheUtil.putResult(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
            BalloonLinkImpl.class, balloonLink.getPrimaryKey(), balloonLink);

        balloonLink.resetOriginalValues();
    }

    /**
     * Caches the balloon links in the entity cache if it is enabled.
     *
     * @param balloonLinks the balloon links
     */
    @Override
    public void cacheResult(List<BalloonLink> balloonLinks) {
        for (BalloonLink balloonLink : balloonLinks) {
            if (EntityCacheUtil.getResult(
                        BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
                        BalloonLinkImpl.class, balloonLink.getPrimaryKey()) == null) {
                cacheResult(balloonLink);
            } else {
                balloonLink.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all balloon links.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(BalloonLinkImpl.class.getName());
        }

        EntityCacheUtil.clearCache(BalloonLinkImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the balloon link.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(BalloonLink balloonLink) {
        EntityCacheUtil.removeResult(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
            BalloonLinkImpl.class, balloonLink.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<BalloonLink> balloonLinks) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (BalloonLink balloonLink : balloonLinks) {
            EntityCacheUtil.removeResult(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
                BalloonLinkImpl.class, balloonLink.getPrimaryKey());
        }
    }

    /**
     * Creates a new balloon link with the primary key. Does not add the balloon link to the database.
     *
     * @param uuid the primary key for the new balloon link
     * @return the new balloon link
     */
    @Override
    public BalloonLink create(String uuid) {
        BalloonLink balloonLink = new BalloonLinkImpl();

        balloonLink.setNew(true);
        balloonLink.setPrimaryKey(uuid);

        return balloonLink;
    }

    /**
     * Removes the balloon link with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param uuid the primary key of the balloon link
     * @return the balloon link that was removed
     * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink remove(String uuid)
        throws NoSuchBalloonLinkException, SystemException {
        return remove((Serializable) uuid);
    }

    /**
     * Removes the balloon link with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the balloon link
     * @return the balloon link that was removed
     * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink remove(Serializable primaryKey)
        throws NoSuchBalloonLinkException, SystemException {
        Session session = null;

        try {
            session = openSession();

            BalloonLink balloonLink = (BalloonLink) session.get(BalloonLinkImpl.class,
                    primaryKey);

            if (balloonLink == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchBalloonLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(balloonLink);
        } catch (NoSuchBalloonLinkException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected BalloonLink removeImpl(BalloonLink balloonLink)
        throws SystemException {
        balloonLink = toUnwrappedModel(balloonLink);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(balloonLink)) {
                balloonLink = (BalloonLink) session.get(BalloonLinkImpl.class,
                        balloonLink.getPrimaryKeyObj());
            }

            if (balloonLink != null) {
                session.delete(balloonLink);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (balloonLink != null) {
            clearCache(balloonLink);
        }

        return balloonLink;
    }

    @Override
    public BalloonLink updateImpl(com.ext.portlet.model.BalloonLink balloonLink)
        throws SystemException {
        balloonLink = toUnwrappedModel(balloonLink);

        boolean isNew = balloonLink.isNew();

        BalloonLinkModelImpl balloonLinkModelImpl = (BalloonLinkModelImpl) balloonLink;

        Session session = null;

        try {
            session = openSession();

            if (balloonLink.isNew()) {
                session.save(balloonLink);

                balloonLink.setNew(false);
            } else {
                session.merge(balloonLink);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !BalloonLinkModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((balloonLinkModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BALLOONUSERUUID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        balloonLinkModelImpl.getOriginalBalloonUserUuid()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BALLOONUSERUUID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BALLOONUSERUUID,
                    args);

                args = new Object[] { balloonLinkModelImpl.getBalloonUserUuid() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BALLOONUSERUUID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BALLOONUSERUUID,
                    args);
            }
        }

        EntityCacheUtil.putResult(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
            BalloonLinkImpl.class, balloonLink.getPrimaryKey(), balloonLink);

        return balloonLink;
    }

    protected BalloonLink toUnwrappedModel(BalloonLink balloonLink) {
        if (balloonLink instanceof BalloonLinkImpl) {
            return balloonLink;
        }

        BalloonLinkImpl balloonLinkImpl = new BalloonLinkImpl();

        balloonLinkImpl.setNew(balloonLink.isNew());
        balloonLinkImpl.setPrimaryKey(balloonLink.getPrimaryKey());

        balloonLinkImpl.setUuid(balloonLink.getUuid());
        balloonLinkImpl.setTargetUrl(balloonLink.getTargetUrl());
        balloonLinkImpl.setVisits(balloonLink.getVisits());
        balloonLinkImpl.setBalloonUserUuid(balloonLink.getBalloonUserUuid());
        balloonLinkImpl.setCreateDate(balloonLink.getCreateDate());

        return balloonLinkImpl;
    }

    /**
     * Returns the balloon link with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the balloon link
     * @return the balloon link
     * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink findByPrimaryKey(Serializable primaryKey)
        throws NoSuchBalloonLinkException, SystemException {
        BalloonLink balloonLink = fetchByPrimaryKey(primaryKey);

        if (balloonLink == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchBalloonLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return balloonLink;
    }

    /**
     * Returns the balloon link with the primary key or throws a {@link com.ext.portlet.NoSuchBalloonLinkException} if it could not be found.
     *
     * @param uuid the primary key of the balloon link
     * @return the balloon link
     * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink findByPrimaryKey(String uuid)
        throws NoSuchBalloonLinkException, SystemException {
        return findByPrimaryKey((Serializable) uuid);
    }

    /**
     * Returns the balloon link with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the balloon link
     * @return the balloon link, or <code>null</code> if a balloon link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        BalloonLink balloonLink = (BalloonLink) EntityCacheUtil.getResult(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
                BalloonLinkImpl.class, primaryKey);

        if (balloonLink == _nullBalloonLink) {
            return null;
        }

        if (balloonLink == null) {
            Session session = null;

            try {
                session = openSession();

                balloonLink = (BalloonLink) session.get(BalloonLinkImpl.class,
                        primaryKey);

                if (balloonLink != null) {
                    cacheResult(balloonLink);
                } else {
                    EntityCacheUtil.putResult(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
                        BalloonLinkImpl.class, primaryKey, _nullBalloonLink);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(BalloonLinkModelImpl.ENTITY_CACHE_ENABLED,
                    BalloonLinkImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return balloonLink;
    }

    /**
     * Returns the balloon link with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param uuid the primary key of the balloon link
     * @return the balloon link, or <code>null</code> if a balloon link with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonLink fetchByPrimaryKey(String uuid) throws SystemException {
        return fetchByPrimaryKey((Serializable) uuid);
    }

    /**
     * Returns all the balloon links.
     *
     * @return the balloon links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonLink> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the balloon links.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of balloon links
     * @param end the upper bound of the range of balloon links (not inclusive)
     * @return the range of balloon links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonLink> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the balloon links.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of balloon links
     * @param end the upper bound of the range of balloon links (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of balloon links
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonLink> findAll(int start, int end,
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

        List<BalloonLink> list = (List<BalloonLink>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_BALLOONLINK);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_BALLOONLINK;

                if (pagination) {
                    sql = sql.concat(BalloonLinkModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<BalloonLink>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BalloonLink>(list);
                } else {
                    list = (List<BalloonLink>) QueryUtil.list(q, getDialect(),
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
     * Removes all the balloon links from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (BalloonLink balloonLink : findAll()) {
            remove(balloonLink);
        }
    }

    /**
     * Returns the number of balloon links.
     *
     * @return the number of balloon links
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

                Query q = session.createQuery(_SQL_COUNT_BALLOONLINK);

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
     * Initializes the balloon link persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.BalloonLink")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<BalloonLink>> listenersList = new ArrayList<ModelListener<BalloonLink>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<BalloonLink>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(BalloonLinkImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
