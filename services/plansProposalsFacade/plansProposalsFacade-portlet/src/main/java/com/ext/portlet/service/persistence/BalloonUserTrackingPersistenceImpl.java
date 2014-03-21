package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchBalloonUserTrackingException;
import com.ext.portlet.model.BalloonUserTracking;
import com.ext.portlet.model.impl.BalloonUserTrackingImpl;
import com.ext.portlet.model.impl.BalloonUserTrackingModelImpl;
import com.ext.portlet.service.persistence.BalloonUserTrackingPersistence;

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
 * The persistence implementation for the balloon user tracking service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonUserTrackingPersistence
 * @see BalloonUserTrackingUtil
 * @generated
 */
public class BalloonUserTrackingPersistenceImpl extends BasePersistenceImpl<BalloonUserTracking>
    implements BalloonUserTrackingPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link BalloonUserTrackingUtil} to access the balloon user tracking persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = BalloonUserTrackingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
            BalloonUserTrackingModelImpl.FINDER_CACHE_ENABLED,
            BalloonUserTrackingImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
            BalloonUserTrackingModelImpl.FINDER_CACHE_ENABLED,
            BalloonUserTrackingImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
            BalloonUserTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAIL = new FinderPath(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
            BalloonUserTrackingModelImpl.FINDER_CACHE_ENABLED,
            BalloonUserTrackingImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByEmail",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAIL = new FinderPath(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
            BalloonUserTrackingModelImpl.FINDER_CACHE_ENABLED,
            BalloonUserTrackingImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByEmail",
            new String[] { String.class.getName() },
            BalloonUserTrackingModelImpl.EMAIL_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_EMAIL = new FinderPath(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
            BalloonUserTrackingModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByEmail",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_EMAIL_EMAIL_1 = "balloonUserTracking.email IS NULL";
    private static final String _FINDER_COLUMN_EMAIL_EMAIL_2 = "balloonUserTracking.email = ?";
    private static final String _FINDER_COLUMN_EMAIL_EMAIL_3 = "(balloonUserTracking.email IS NULL OR balloonUserTracking.email = '')";
    private static final String _SQL_SELECT_BALLOONUSERTRACKING = "SELECT balloonUserTracking FROM BalloonUserTracking balloonUserTracking";
    private static final String _SQL_SELECT_BALLOONUSERTRACKING_WHERE = "SELECT balloonUserTracking FROM BalloonUserTracking balloonUserTracking WHERE ";
    private static final String _SQL_COUNT_BALLOONUSERTRACKING = "SELECT COUNT(balloonUserTracking) FROM BalloonUserTracking balloonUserTracking";
    private static final String _SQL_COUNT_BALLOONUSERTRACKING_WHERE = "SELECT COUNT(balloonUserTracking) FROM BalloonUserTracking balloonUserTracking WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "balloonUserTracking.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BalloonUserTracking exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No BalloonUserTracking exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(BalloonUserTrackingPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "uuid"
            });
    private static BalloonUserTracking _nullBalloonUserTracking = new BalloonUserTrackingImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<BalloonUserTracking> toCacheModel() {
                return _nullBalloonUserTrackingCacheModel;
            }
        };

    private static CacheModel<BalloonUserTracking> _nullBalloonUserTrackingCacheModel =
        new CacheModel<BalloonUserTracking>() {
            @Override
            public BalloonUserTracking toEntityModel() {
                return _nullBalloonUserTracking;
            }
        };

    public BalloonUserTrackingPersistenceImpl() {
        setModelClass(BalloonUserTracking.class);
    }

    /**
     * Returns all the balloon user trackings where email = &#63;.
     *
     * @param email the email
     * @return the matching balloon user trackings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonUserTracking> findByEmail(String email)
        throws SystemException {
        return findByEmail(email, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the balloon user trackings where email = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonUserTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param email the email
     * @param start the lower bound of the range of balloon user trackings
     * @param end the upper bound of the range of balloon user trackings (not inclusive)
     * @return the range of matching balloon user trackings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonUserTracking> findByEmail(String email, int start,
        int end) throws SystemException {
        return findByEmail(email, start, end, null);
    }

    /**
     * Returns an ordered range of all the balloon user trackings where email = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonUserTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param email the email
     * @param start the lower bound of the range of balloon user trackings
     * @param end the upper bound of the range of balloon user trackings (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching balloon user trackings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonUserTracking> findByEmail(String email, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAIL;
            finderArgs = new Object[] { email };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_EMAIL;
            finderArgs = new Object[] { email, start, end, orderByComparator };
        }

        List<BalloonUserTracking> list = (List<BalloonUserTracking>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (BalloonUserTracking balloonUserTracking : list) {
                if (!Validator.equals(email, balloonUserTracking.getEmail())) {
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

            query.append(_SQL_SELECT_BALLOONUSERTRACKING_WHERE);

            boolean bindEmail = false;

            if (email == null) {
                query.append(_FINDER_COLUMN_EMAIL_EMAIL_1);
            } else if (email.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
            } else {
                bindEmail = true;

                query.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(BalloonUserTrackingModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindEmail) {
                    qPos.add(email);
                }

                if (!pagination) {
                    list = (List<BalloonUserTracking>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BalloonUserTracking>(list);
                } else {
                    list = (List<BalloonUserTracking>) QueryUtil.list(q,
                            getDialect(), start, end);
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
     * Returns the first balloon user tracking in the ordered set where email = &#63;.
     *
     * @param email the email
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching balloon user tracking
     * @throws com.ext.portlet.NoSuchBalloonUserTrackingException if a matching balloon user tracking could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking findByEmail_First(String email,
        OrderByComparator orderByComparator)
        throws NoSuchBalloonUserTrackingException, SystemException {
        BalloonUserTracking balloonUserTracking = fetchByEmail_First(email,
                orderByComparator);

        if (balloonUserTracking != null) {
            return balloonUserTracking;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("email=");
        msg.append(email);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBalloonUserTrackingException(msg.toString());
    }

    /**
     * Returns the first balloon user tracking in the ordered set where email = &#63;.
     *
     * @param email the email
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching balloon user tracking, or <code>null</code> if a matching balloon user tracking could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking fetchByEmail_First(String email,
        OrderByComparator orderByComparator) throws SystemException {
        List<BalloonUserTracking> list = findByEmail(email, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last balloon user tracking in the ordered set where email = &#63;.
     *
     * @param email the email
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching balloon user tracking
     * @throws com.ext.portlet.NoSuchBalloonUserTrackingException if a matching balloon user tracking could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking findByEmail_Last(String email,
        OrderByComparator orderByComparator)
        throws NoSuchBalloonUserTrackingException, SystemException {
        BalloonUserTracking balloonUserTracking = fetchByEmail_Last(email,
                orderByComparator);

        if (balloonUserTracking != null) {
            return balloonUserTracking;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("email=");
        msg.append(email);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchBalloonUserTrackingException(msg.toString());
    }

    /**
     * Returns the last balloon user tracking in the ordered set where email = &#63;.
     *
     * @param email the email
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching balloon user tracking, or <code>null</code> if a matching balloon user tracking could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking fetchByEmail_Last(String email,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByEmail(email);

        if (count == 0) {
            return null;
        }

        List<BalloonUserTracking> list = findByEmail(email, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the balloon user trackings before and after the current balloon user tracking in the ordered set where email = &#63;.
     *
     * @param uuid the primary key of the current balloon user tracking
     * @param email the email
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next balloon user tracking
     * @throws com.ext.portlet.NoSuchBalloonUserTrackingException if a balloon user tracking with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking[] findByEmail_PrevAndNext(String uuid,
        String email, OrderByComparator orderByComparator)
        throws NoSuchBalloonUserTrackingException, SystemException {
        BalloonUserTracking balloonUserTracking = findByPrimaryKey(uuid);

        Session session = null;

        try {
            session = openSession();

            BalloonUserTracking[] array = new BalloonUserTrackingImpl[3];

            array[0] = getByEmail_PrevAndNext(session, balloonUserTracking,
                    email, orderByComparator, true);

            array[1] = balloonUserTracking;

            array[2] = getByEmail_PrevAndNext(session, balloonUserTracking,
                    email, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected BalloonUserTracking getByEmail_PrevAndNext(Session session,
        BalloonUserTracking balloonUserTracking, String email,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_BALLOONUSERTRACKING_WHERE);

        boolean bindEmail = false;

        if (email == null) {
            query.append(_FINDER_COLUMN_EMAIL_EMAIL_1);
        } else if (email.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
        } else {
            bindEmail = true;

            query.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
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
            query.append(BalloonUserTrackingModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindEmail) {
            qPos.add(email);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(balloonUserTracking);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<BalloonUserTracking> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the balloon user trackings where email = &#63; from the database.
     *
     * @param email the email
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByEmail(String email) throws SystemException {
        for (BalloonUserTracking balloonUserTracking : findByEmail(email,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(balloonUserTracking);
        }
    }

    /**
     * Returns the number of balloon user trackings where email = &#63;.
     *
     * @param email the email
     * @return the number of matching balloon user trackings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByEmail(String email) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_EMAIL;

        Object[] finderArgs = new Object[] { email };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_BALLOONUSERTRACKING_WHERE);

            boolean bindEmail = false;

            if (email == null) {
                query.append(_FINDER_COLUMN_EMAIL_EMAIL_1);
            } else if (email.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
            } else {
                bindEmail = true;

                query.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindEmail) {
                    qPos.add(email);
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
     * Caches the balloon user tracking in the entity cache if it is enabled.
     *
     * @param balloonUserTracking the balloon user tracking
     */
    @Override
    public void cacheResult(BalloonUserTracking balloonUserTracking) {
        EntityCacheUtil.putResult(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
            BalloonUserTrackingImpl.class, balloonUserTracking.getPrimaryKey(),
            balloonUserTracking);

        balloonUserTracking.resetOriginalValues();
    }

    /**
     * Caches the balloon user trackings in the entity cache if it is enabled.
     *
     * @param balloonUserTrackings the balloon user trackings
     */
    @Override
    public void cacheResult(List<BalloonUserTracking> balloonUserTrackings) {
        for (BalloonUserTracking balloonUserTracking : balloonUserTrackings) {
            if (EntityCacheUtil.getResult(
                        BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
                        BalloonUserTrackingImpl.class,
                        balloonUserTracking.getPrimaryKey()) == null) {
                cacheResult(balloonUserTracking);
            } else {
                balloonUserTracking.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all balloon user trackings.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(BalloonUserTrackingImpl.class.getName());
        }

        EntityCacheUtil.clearCache(BalloonUserTrackingImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the balloon user tracking.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(BalloonUserTracking balloonUserTracking) {
        EntityCacheUtil.removeResult(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
            BalloonUserTrackingImpl.class, balloonUserTracking.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<BalloonUserTracking> balloonUserTrackings) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (BalloonUserTracking balloonUserTracking : balloonUserTrackings) {
            EntityCacheUtil.removeResult(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
                BalloonUserTrackingImpl.class,
                balloonUserTracking.getPrimaryKey());
        }
    }

    /**
     * Creates a new balloon user tracking with the primary key. Does not add the balloon user tracking to the database.
     *
     * @param uuid the primary key for the new balloon user tracking
     * @return the new balloon user tracking
     */
    @Override
    public BalloonUserTracking create(String uuid) {
        BalloonUserTracking balloonUserTracking = new BalloonUserTrackingImpl();

        balloonUserTracking.setNew(true);
        balloonUserTracking.setPrimaryKey(uuid);

        return balloonUserTracking;
    }

    /**
     * Removes the balloon user tracking with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param uuid the primary key of the balloon user tracking
     * @return the balloon user tracking that was removed
     * @throws com.ext.portlet.NoSuchBalloonUserTrackingException if a balloon user tracking with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking remove(String uuid)
        throws NoSuchBalloonUserTrackingException, SystemException {
        return remove((Serializable) uuid);
    }

    /**
     * Removes the balloon user tracking with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the balloon user tracking
     * @return the balloon user tracking that was removed
     * @throws com.ext.portlet.NoSuchBalloonUserTrackingException if a balloon user tracking with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking remove(Serializable primaryKey)
        throws NoSuchBalloonUserTrackingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            BalloonUserTracking balloonUserTracking = (BalloonUserTracking) session.get(BalloonUserTrackingImpl.class,
                    primaryKey);

            if (balloonUserTracking == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchBalloonUserTrackingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(balloonUserTracking);
        } catch (NoSuchBalloonUserTrackingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected BalloonUserTracking removeImpl(
        BalloonUserTracking balloonUserTracking) throws SystemException {
        balloonUserTracking = toUnwrappedModel(balloonUserTracking);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(balloonUserTracking)) {
                balloonUserTracking = (BalloonUserTracking) session.get(BalloonUserTrackingImpl.class,
                        balloonUserTracking.getPrimaryKeyObj());
            }

            if (balloonUserTracking != null) {
                session.delete(balloonUserTracking);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (balloonUserTracking != null) {
            clearCache(balloonUserTracking);
        }

        return balloonUserTracking;
    }

    @Override
    public BalloonUserTracking updateImpl(
        com.ext.portlet.model.BalloonUserTracking balloonUserTracking)
        throws SystemException {
        balloonUserTracking = toUnwrappedModel(balloonUserTracking);

        boolean isNew = balloonUserTracking.isNew();

        BalloonUserTrackingModelImpl balloonUserTrackingModelImpl = (BalloonUserTrackingModelImpl) balloonUserTracking;

        Session session = null;

        try {
            session = openSession();

            if (balloonUserTracking.isNew()) {
                session.save(balloonUserTracking);

                balloonUserTracking.setNew(false);
            } else {
                session.merge(balloonUserTracking);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !BalloonUserTrackingModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((balloonUserTrackingModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAIL.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        balloonUserTrackingModelImpl.getOriginalEmail()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMAIL, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAIL,
                    args);

                args = new Object[] { balloonUserTrackingModelImpl.getEmail() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_EMAIL, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_EMAIL,
                    args);
            }
        }

        EntityCacheUtil.putResult(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
            BalloonUserTrackingImpl.class, balloonUserTracking.getPrimaryKey(),
            balloonUserTracking);

        return balloonUserTracking;
    }

    protected BalloonUserTracking toUnwrappedModel(
        BalloonUserTracking balloonUserTracking) {
        if (balloonUserTracking instanceof BalloonUserTrackingImpl) {
            return balloonUserTracking;
        }

        BalloonUserTrackingImpl balloonUserTrackingImpl = new BalloonUserTrackingImpl();

        balloonUserTrackingImpl.setNew(balloonUserTracking.isNew());
        balloonUserTrackingImpl.setPrimaryKey(balloonUserTracking.getPrimaryKey());

        balloonUserTrackingImpl.setUuid(balloonUserTracking.getUuid());
        balloonUserTrackingImpl.setEmail(balloonUserTracking.getEmail());
        balloonUserTrackingImpl.setParent(balloonUserTracking.getParent());
        balloonUserTrackingImpl.setIp(balloonUserTracking.getIp());
        balloonUserTrackingImpl.setCreateDate(balloonUserTracking.getCreateDate());
        balloonUserTrackingImpl.setRegistrationDate(balloonUserTracking.getRegistrationDate());
        balloonUserTrackingImpl.setFormFiledDate(balloonUserTracking.getFormFiledDate());
        balloonUserTrackingImpl.setUserId(balloonUserTracking.getUserId());
        balloonUserTrackingImpl.setBalloonTextId(balloonUserTracking.getBalloonTextId());
        balloonUserTrackingImpl.setReferrer(balloonUserTracking.getReferrer());
        balloonUserTrackingImpl.setLatitude(balloonUserTracking.getLatitude());
        balloonUserTrackingImpl.setLongitude(balloonUserTracking.getLongitude());
        balloonUserTrackingImpl.setCity(balloonUserTracking.getCity());
        balloonUserTrackingImpl.setCountry(balloonUserTracking.getCountry());
        balloonUserTrackingImpl.setExtraData(balloonUserTracking.getExtraData());
        balloonUserTrackingImpl.setBalloonLinkUuid(balloonUserTracking.getBalloonLinkUuid());
        balloonUserTrackingImpl.setBalloonLinkContext(balloonUserTracking.getBalloonLinkContext());
        balloonUserTrackingImpl.setUserAgent(balloonUserTracking.getUserAgent());

        return balloonUserTrackingImpl;
    }

    /**
     * Returns the balloon user tracking with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the balloon user tracking
     * @return the balloon user tracking
     * @throws com.ext.portlet.NoSuchBalloonUserTrackingException if a balloon user tracking with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking findByPrimaryKey(Serializable primaryKey)
        throws NoSuchBalloonUserTrackingException, SystemException {
        BalloonUserTracking balloonUserTracking = fetchByPrimaryKey(primaryKey);

        if (balloonUserTracking == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchBalloonUserTrackingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return balloonUserTracking;
    }

    /**
     * Returns the balloon user tracking with the primary key or throws a {@link com.ext.portlet.NoSuchBalloonUserTrackingException} if it could not be found.
     *
     * @param uuid the primary key of the balloon user tracking
     * @return the balloon user tracking
     * @throws com.ext.portlet.NoSuchBalloonUserTrackingException if a balloon user tracking with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking findByPrimaryKey(String uuid)
        throws NoSuchBalloonUserTrackingException, SystemException {
        return findByPrimaryKey((Serializable) uuid);
    }

    /**
     * Returns the balloon user tracking with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the balloon user tracking
     * @return the balloon user tracking, or <code>null</code> if a balloon user tracking with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        BalloonUserTracking balloonUserTracking = (BalloonUserTracking) EntityCacheUtil.getResult(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
                BalloonUserTrackingImpl.class, primaryKey);

        if (balloonUserTracking == _nullBalloonUserTracking) {
            return null;
        }

        if (balloonUserTracking == null) {
            Session session = null;

            try {
                session = openSession();

                balloonUserTracking = (BalloonUserTracking) session.get(BalloonUserTrackingImpl.class,
                        primaryKey);

                if (balloonUserTracking != null) {
                    cacheResult(balloonUserTracking);
                } else {
                    EntityCacheUtil.putResult(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
                        BalloonUserTrackingImpl.class, primaryKey,
                        _nullBalloonUserTracking);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(BalloonUserTrackingModelImpl.ENTITY_CACHE_ENABLED,
                    BalloonUserTrackingImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return balloonUserTracking;
    }

    /**
     * Returns the balloon user tracking with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param uuid the primary key of the balloon user tracking
     * @return the balloon user tracking, or <code>null</code> if a balloon user tracking with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonUserTracking fetchByPrimaryKey(String uuid)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) uuid);
    }

    /**
     * Returns all the balloon user trackings.
     *
     * @return the balloon user trackings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonUserTracking> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the balloon user trackings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonUserTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of balloon user trackings
     * @param end the upper bound of the range of balloon user trackings (not inclusive)
     * @return the range of balloon user trackings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonUserTracking> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the balloon user trackings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonUserTrackingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of balloon user trackings
     * @param end the upper bound of the range of balloon user trackings (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of balloon user trackings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonUserTracking> findAll(int start, int end,
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

        List<BalloonUserTracking> list = (List<BalloonUserTracking>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_BALLOONUSERTRACKING);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_BALLOONUSERTRACKING;

                if (pagination) {
                    sql = sql.concat(BalloonUserTrackingModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<BalloonUserTracking>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BalloonUserTracking>(list);
                } else {
                    list = (List<BalloonUserTracking>) QueryUtil.list(q,
                            getDialect(), start, end);
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
     * Removes all the balloon user trackings from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (BalloonUserTracking balloonUserTracking : findAll()) {
            remove(balloonUserTracking);
        }
    }

    /**
     * Returns the number of balloon user trackings.
     *
     * @return the number of balloon user trackings
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

                Query q = session.createQuery(_SQL_COUNT_BALLOONUSERTRACKING);

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
     * Initializes the balloon user tracking persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.BalloonUserTracking")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<BalloonUserTracking>> listenersList = new ArrayList<ModelListener<BalloonUserTracking>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<BalloonUserTracking>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(BalloonUserTrackingImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
