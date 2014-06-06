package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchStaffMemberException;
import com.ext.portlet.model.StaffMember;
import com.ext.portlet.model.impl.StaffMemberImpl;
import com.ext.portlet.model.impl.StaffMemberModelImpl;
import com.ext.portlet.service.persistence.StaffMemberPersistence;

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
 * The persistence implementation for the staff member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffMemberPersistence
 * @see StaffMemberUtil
 * @generated
 */
public class StaffMemberPersistenceImpl extends BasePersistenceImpl<StaffMember>
    implements StaffMemberPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link StaffMemberUtil} to access the staff member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = StaffMemberImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
            StaffMemberModelImpl.FINDER_CACHE_ENABLED, StaffMemberImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
            StaffMemberModelImpl.FINDER_CACHE_ENABLED, StaffMemberImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
            StaffMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
            StaffMemberModelImpl.FINDER_CACHE_ENABLED, StaffMemberImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
        new FinderPath(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
            StaffMemberModelImpl.FINDER_CACHE_ENABLED, StaffMemberImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
            new String[] { Long.class.getName() },
            StaffMemberModelImpl.USERID_COLUMN_BITMASK |
            StaffMemberModelImpl.SORT_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
            StaffMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_USERID_USERID_2 = "staffMember.userId = ?";
    private static final String _SQL_SELECT_STAFFMEMBER = "SELECT staffMember FROM StaffMember staffMember";
    private static final String _SQL_SELECT_STAFFMEMBER_WHERE = "SELECT staffMember FROM StaffMember staffMember WHERE ";
    private static final String _SQL_COUNT_STAFFMEMBER = "SELECT COUNT(staffMember) FROM StaffMember staffMember";
    private static final String _SQL_COUNT_STAFFMEMBER_WHERE = "SELECT COUNT(staffMember) FROM StaffMember staffMember WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "staffMember.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StaffMember exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No StaffMember exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(StaffMemberPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static StaffMember _nullStaffMember = new StaffMemberImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<StaffMember> toCacheModel() {
                return _nullStaffMemberCacheModel;
            }
        };

    private static CacheModel<StaffMember> _nullStaffMemberCacheModel = new CacheModel<StaffMember>() {
            @Override
            public StaffMember toEntityModel() {
                return _nullStaffMember;
            }
        };

    public StaffMemberPersistenceImpl() {
        setModelClass(StaffMember.class);
    }

    /**
     * Returns all the staff members where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching staff members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StaffMember> findByUserId(long userId)
        throws SystemException {
        return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the staff members where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of staff members
     * @param end the upper bound of the range of staff members (not inclusive)
     * @return the range of matching staff members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StaffMember> findByUserId(long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the staff members where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of staff members
     * @param end the upper bound of the range of staff members (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching staff members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StaffMember> findByUserId(long userId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId, start, end, orderByComparator };
        }

        List<StaffMember> list = (List<StaffMember>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (StaffMember staffMember : list) {
                if ((userId != staffMember.getUserId())) {
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

            query.append(_SQL_SELECT_STAFFMEMBER_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(StaffMemberModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                if (!pagination) {
                    list = (List<StaffMember>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StaffMember>(list);
                } else {
                    list = (List<StaffMember>) QueryUtil.list(q, getDialect(),
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
     * Returns the first staff member in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching staff member
     * @throws com.ext.portlet.NoSuchStaffMemberException if a matching staff member could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember findByUserId_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchStaffMemberException, SystemException {
        StaffMember staffMember = fetchByUserId_First(userId, orderByComparator);

        if (staffMember != null) {
            return staffMember;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchStaffMemberException(msg.toString());
    }

    /**
     * Returns the first staff member in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching staff member, or <code>null</code> if a matching staff member could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember fetchByUserId_First(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        List<StaffMember> list = findByUserId(userId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last staff member in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching staff member
     * @throws com.ext.portlet.NoSuchStaffMemberException if a matching staff member could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember findByUserId_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchStaffMemberException, SystemException {
        StaffMember staffMember = fetchByUserId_Last(userId, orderByComparator);

        if (staffMember != null) {
            return staffMember;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchStaffMemberException(msg.toString());
    }

    /**
     * Returns the last staff member in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching staff member, or <code>null</code> if a matching staff member could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember fetchByUserId_Last(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUserId(userId);

        if (count == 0) {
            return null;
        }

        List<StaffMember> list = findByUserId(userId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the staff members before and after the current staff member in the ordered set where userId = &#63;.
     *
     * @param id the primary key of the current staff member
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next staff member
     * @throws com.ext.portlet.NoSuchStaffMemberException if a staff member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember[] findByUserId_PrevAndNext(long id, long userId,
        OrderByComparator orderByComparator)
        throws NoSuchStaffMemberException, SystemException {
        StaffMember staffMember = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            StaffMember[] array = new StaffMemberImpl[3];

            array[0] = getByUserId_PrevAndNext(session, staffMember, userId,
                    orderByComparator, true);

            array[1] = staffMember;

            array[2] = getByUserId_PrevAndNext(session, staffMember, userId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected StaffMember getByUserId_PrevAndNext(Session session,
        StaffMember staffMember, long userId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_STAFFMEMBER_WHERE);

        query.append(_FINDER_COLUMN_USERID_USERID_2);

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
            query.append(StaffMemberModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(staffMember);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<StaffMember> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the staff members where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUserId(long userId) throws SystemException {
        for (StaffMember staffMember : findByUserId(userId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(staffMember);
        }
    }

    /**
     * Returns the number of staff members where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching staff members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUserId(long userId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_STAFFMEMBER_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

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
     * Caches the staff member in the entity cache if it is enabled.
     *
     * @param staffMember the staff member
     */
    @Override
    public void cacheResult(StaffMember staffMember) {
        EntityCacheUtil.putResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
            StaffMemberImpl.class, staffMember.getPrimaryKey(), staffMember);

        staffMember.resetOriginalValues();
    }

    /**
     * Caches the staff members in the entity cache if it is enabled.
     *
     * @param staffMembers the staff members
     */
    @Override
    public void cacheResult(List<StaffMember> staffMembers) {
        for (StaffMember staffMember : staffMembers) {
            if (EntityCacheUtil.getResult(
                        StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
                        StaffMemberImpl.class, staffMember.getPrimaryKey()) == null) {
                cacheResult(staffMember);
            } else {
                staffMember.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all staff members.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(StaffMemberImpl.class.getName());
        }

        EntityCacheUtil.clearCache(StaffMemberImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the staff member.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(StaffMember staffMember) {
        EntityCacheUtil.removeResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
            StaffMemberImpl.class, staffMember.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<StaffMember> staffMembers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (StaffMember staffMember : staffMembers) {
            EntityCacheUtil.removeResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
                StaffMemberImpl.class, staffMember.getPrimaryKey());
        }
    }

    /**
     * Creates a new staff member with the primary key. Does not add the staff member to the database.
     *
     * @param id the primary key for the new staff member
     * @return the new staff member
     */
    @Override
    public StaffMember create(long id) {
        StaffMember staffMember = new StaffMemberImpl();

        staffMember.setNew(true);
        staffMember.setPrimaryKey(id);

        return staffMember;
    }

    /**
     * Removes the staff member with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the staff member
     * @return the staff member that was removed
     * @throws com.ext.portlet.NoSuchStaffMemberException if a staff member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember remove(long id)
        throws NoSuchStaffMemberException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the staff member with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the staff member
     * @return the staff member that was removed
     * @throws com.ext.portlet.NoSuchStaffMemberException if a staff member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember remove(Serializable primaryKey)
        throws NoSuchStaffMemberException, SystemException {
        Session session = null;

        try {
            session = openSession();

            StaffMember staffMember = (StaffMember) session.get(StaffMemberImpl.class,
                    primaryKey);

            if (staffMember == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchStaffMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(staffMember);
        } catch (NoSuchStaffMemberException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected StaffMember removeImpl(StaffMember staffMember)
        throws SystemException {
        staffMember = toUnwrappedModel(staffMember);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(staffMember)) {
                staffMember = (StaffMember) session.get(StaffMemberImpl.class,
                        staffMember.getPrimaryKeyObj());
            }

            if (staffMember != null) {
                session.delete(staffMember);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (staffMember != null) {
            clearCache(staffMember);
        }

        return staffMember;
    }

    @Override
    public StaffMember updateImpl(com.ext.portlet.model.StaffMember staffMember)
        throws SystemException {
        staffMember = toUnwrappedModel(staffMember);

        boolean isNew = staffMember.isNew();

        StaffMemberModelImpl staffMemberModelImpl = (StaffMemberModelImpl) staffMember;

        Session session = null;

        try {
            session = openSession();

            if (staffMember.isNew()) {
                session.save(staffMember);

                staffMember.setNew(false);
            } else {
                session.merge(staffMember);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !StaffMemberModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((staffMemberModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        staffMemberModelImpl.getOriginalUserId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);

                args = new Object[] { staffMemberModelImpl.getUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);
            }
        }

        EntityCacheUtil.putResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
            StaffMemberImpl.class, staffMember.getPrimaryKey(), staffMember);

        return staffMember;
    }

    protected StaffMember toUnwrappedModel(StaffMember staffMember) {
        if (staffMember instanceof StaffMemberImpl) {
            return staffMember;
        }

        StaffMemberImpl staffMemberImpl = new StaffMemberImpl();

        staffMemberImpl.setNew(staffMember.isNew());
        staffMemberImpl.setPrimaryKey(staffMember.getPrimaryKey());

        staffMemberImpl.setId(staffMember.getId());
        staffMemberImpl.setUserId(staffMember.getUserId());
        staffMemberImpl.setCategoryId(staffMember.getCategoryId());
        staffMemberImpl.setFirstNames(staffMember.getFirstNames());
        staffMemberImpl.setLastName(staffMember.getLastName());
        staffMemberImpl.setUrl(staffMember.getUrl());
        staffMemberImpl.setPhotoUrl(staffMember.getPhotoUrl());
        staffMemberImpl.setSort(staffMember.getSort());

        return staffMemberImpl;
    }

    /**
     * Returns the staff member with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the staff member
     * @return the staff member
     * @throws com.ext.portlet.NoSuchStaffMemberException if a staff member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember findByPrimaryKey(Serializable primaryKey)
        throws NoSuchStaffMemberException, SystemException {
        StaffMember staffMember = fetchByPrimaryKey(primaryKey);

        if (staffMember == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchStaffMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return staffMember;
    }

    /**
     * Returns the staff member with the primary key or throws a {@link com.ext.portlet.NoSuchStaffMemberException} if it could not be found.
     *
     * @param id the primary key of the staff member
     * @return the staff member
     * @throws com.ext.portlet.NoSuchStaffMemberException if a staff member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember findByPrimaryKey(long id)
        throws NoSuchStaffMemberException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the staff member with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the staff member
     * @return the staff member, or <code>null</code> if a staff member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        StaffMember staffMember = (StaffMember) EntityCacheUtil.getResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
                StaffMemberImpl.class, primaryKey);

        if (staffMember == _nullStaffMember) {
            return null;
        }

        if (staffMember == null) {
            Session session = null;

            try {
                session = openSession();

                staffMember = (StaffMember) session.get(StaffMemberImpl.class,
                        primaryKey);

                if (staffMember != null) {
                    cacheResult(staffMember);
                } else {
                    EntityCacheUtil.putResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
                        StaffMemberImpl.class, primaryKey, _nullStaffMember);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
                    StaffMemberImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return staffMember;
    }

    /**
     * Returns the staff member with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the staff member
     * @return the staff member, or <code>null</code> if a staff member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public StaffMember fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the staff members.
     *
     * @return the staff members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StaffMember> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the staff members.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of staff members
     * @param end the upper bound of the range of staff members (not inclusive)
     * @return the range of staff members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StaffMember> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the staff members.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of staff members
     * @param end the upper bound of the range of staff members (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of staff members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<StaffMember> findAll(int start, int end,
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

        List<StaffMember> list = (List<StaffMember>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_STAFFMEMBER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_STAFFMEMBER;

                if (pagination) {
                    sql = sql.concat(StaffMemberModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<StaffMember>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<StaffMember>(list);
                } else {
                    list = (List<StaffMember>) QueryUtil.list(q, getDialect(),
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
     * Removes all the staff members from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (StaffMember staffMember : findAll()) {
            remove(staffMember);
        }
    }

    /**
     * Returns the number of staff members.
     *
     * @return the number of staff members
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

                Query q = session.createQuery(_SQL_COUNT_STAFFMEMBER);

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
     * Initializes the staff member persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.StaffMember")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<StaffMember>> listenersList = new ArrayList<ModelListener<StaffMember>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<StaffMember>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(StaffMemberImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
