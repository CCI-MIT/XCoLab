package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestTeamMemberException;
import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.model.impl.ContestTeamMemberImpl;
import com.ext.portlet.model.impl.ContestTeamMemberModelImpl;
import com.ext.portlet.service.persistence.ContestTeamMemberPersistence;

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
 * The persistence implementation for the contest team member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberPersistence
 * @see ContestTeamMemberUtil
 * @generated
 */
public class ContestTeamMemberPersistenceImpl extends BasePersistenceImpl<ContestTeamMember>
    implements ContestTeamMemberPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestTeamMemberUtil} to access the contest team member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestTeamMemberImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED,
            ContestTeamMemberImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED,
            ContestTeamMemberImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTID =
        new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED,
            ContestTeamMemberImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID =
        new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED,
            ContestTeamMemberImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContestId",
            new String[] { Long.class.getName() },
            ContestTeamMemberModelImpl.CONTESTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTID = new FinderPath(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContestId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTID_CONTESTID_2 = "contestTeamMember.contestId = ?";
    private static final String _SQL_SELECT_CONTESTTEAMMEMBER = "SELECT contestTeamMember FROM ContestTeamMember contestTeamMember";
    private static final String _SQL_SELECT_CONTESTTEAMMEMBER_WHERE = "SELECT contestTeamMember FROM ContestTeamMember contestTeamMember WHERE ";
    private static final String _SQL_COUNT_CONTESTTEAMMEMBER = "SELECT COUNT(contestTeamMember) FROM ContestTeamMember contestTeamMember";
    private static final String _SQL_COUNT_CONTESTTEAMMEMBER_WHERE = "SELECT COUNT(contestTeamMember) FROM ContestTeamMember contestTeamMember WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestTeamMember.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestTeamMember exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContestTeamMember exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestTeamMemberPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ContestTeamMember _nullContestTeamMember = new ContestTeamMemberImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestTeamMember> toCacheModel() {
                return _nullContestTeamMemberCacheModel;
            }
        };

    private static CacheModel<ContestTeamMember> _nullContestTeamMemberCacheModel =
        new CacheModel<ContestTeamMember>() {
            @Override
            public ContestTeamMember toEntityModel() {
                return _nullContestTeamMember;
            }
        };

    public ContestTeamMemberPersistenceImpl() {
        setModelClass(ContestTeamMember.class);
    }

    /**
     * Returns all the contest team members where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @return the matching contest team members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestTeamMember> findByContestId(long contestId)
        throws SystemException {
        return findByContestId(contestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the contest team members where contestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestId the contest ID
     * @param start the lower bound of the range of contest team members
     * @param end the upper bound of the range of contest team members (not inclusive)
     * @return the range of matching contest team members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestTeamMember> findByContestId(long contestId, int start,
        int end) throws SystemException {
        return findByContestId(contestId, start, end, null);
    }

    /**
     * Returns an ordered range of all the contest team members where contestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestId the contest ID
     * @param start the lower bound of the range of contest team members
     * @param end the upper bound of the range of contest team members (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contest team members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestTeamMember> findByContestId(long contestId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID;
            finderArgs = new Object[] { contestId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTID;
            finderArgs = new Object[] { contestId, start, end, orderByComparator };
        }

        List<ContestTeamMember> list = (List<ContestTeamMember>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ContestTeamMember contestTeamMember : list) {
                if ((contestId != contestTeamMember.getContestId())) {
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

            query.append(_SQL_SELECT_CONTESTTEAMMEMBER_WHERE);

            query.append(_FINDER_COLUMN_CONTESTID_CONTESTID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestTeamMemberModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestId);

                if (!pagination) {
                    list = (List<ContestTeamMember>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestTeamMember>(list);
                } else {
                    list = (List<ContestTeamMember>) QueryUtil.list(q,
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
     * Returns the first contest team member in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest team member
     * @throws com.ext.portlet.NoSuchContestTeamMemberException if a matching contest team member could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember findByContestId_First(long contestId,
        OrderByComparator orderByComparator)
        throws NoSuchContestTeamMemberException, SystemException {
        ContestTeamMember contestTeamMember = fetchByContestId_First(contestId,
                orderByComparator);

        if (contestTeamMember != null) {
            return contestTeamMember;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestId=");
        msg.append(contestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestTeamMemberException(msg.toString());
    }

    /**
     * Returns the first contest team member in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest team member, or <code>null</code> if a matching contest team member could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember fetchByContestId_First(long contestId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ContestTeamMember> list = findByContestId(contestId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest team member in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest team member
     * @throws com.ext.portlet.NoSuchContestTeamMemberException if a matching contest team member could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember findByContestId_Last(long contestId,
        OrderByComparator orderByComparator)
        throws NoSuchContestTeamMemberException, SystemException {
        ContestTeamMember contestTeamMember = fetchByContestId_Last(contestId,
                orderByComparator);

        if (contestTeamMember != null) {
            return contestTeamMember;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestId=");
        msg.append(contestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestTeamMemberException(msg.toString());
    }

    /**
     * Returns the last contest team member in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest team member, or <code>null</code> if a matching contest team member could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember fetchByContestId_Last(long contestId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContestId(contestId);

        if (count == 0) {
            return null;
        }

        List<ContestTeamMember> list = findByContestId(contestId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contest team members before and after the current contest team member in the ordered set where contestId = &#63;.
     *
     * @param id the primary key of the current contest team member
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest team member
     * @throws com.ext.portlet.NoSuchContestTeamMemberException if a contest team member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember[] findByContestId_PrevAndNext(long id,
        long contestId, OrderByComparator orderByComparator)
        throws NoSuchContestTeamMemberException, SystemException {
        ContestTeamMember contestTeamMember = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ContestTeamMember[] array = new ContestTeamMemberImpl[3];

            array[0] = getByContestId_PrevAndNext(session, contestTeamMember,
                    contestId, orderByComparator, true);

            array[1] = contestTeamMember;

            array[2] = getByContestId_PrevAndNext(session, contestTeamMember,
                    contestId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ContestTeamMember getByContestId_PrevAndNext(Session session,
        ContestTeamMember contestTeamMember, long contestId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTESTTEAMMEMBER_WHERE);

        query.append(_FINDER_COLUMN_CONTESTID_CONTESTID_2);

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
            query.append(ContestTeamMemberModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contestTeamMember);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ContestTeamMember> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contest team members where contestId = &#63; from the database.
     *
     * @param contestId the contest ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestId(long contestId) throws SystemException {
        for (ContestTeamMember contestTeamMember : findByContestId(contestId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contestTeamMember);
        }
    }

    /**
     * Returns the number of contest team members where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @return the number of matching contest team members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestId(long contestId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTID;

        Object[] finderArgs = new Object[] { contestId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTESTTEAMMEMBER_WHERE);

            query.append(_FINDER_COLUMN_CONTESTID_CONTESTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestId);

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
     * Caches the contest team member in the entity cache if it is enabled.
     *
     * @param contestTeamMember the contest team member
     */
    @Override
    public void cacheResult(ContestTeamMember contestTeamMember) {
        EntityCacheUtil.putResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberImpl.class, contestTeamMember.getPrimaryKey(),
            contestTeamMember);

        contestTeamMember.resetOriginalValues();
    }

    /**
     * Caches the contest team members in the entity cache if it is enabled.
     *
     * @param contestTeamMembers the contest team members
     */
    @Override
    public void cacheResult(List<ContestTeamMember> contestTeamMembers) {
        for (ContestTeamMember contestTeamMember : contestTeamMembers) {
            if (EntityCacheUtil.getResult(
                        ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
                        ContestTeamMemberImpl.class,
                        contestTeamMember.getPrimaryKey()) == null) {
                cacheResult(contestTeamMember);
            } else {
                contestTeamMember.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest team members.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestTeamMemberImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestTeamMemberImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest team member.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestTeamMember contestTeamMember) {
        EntityCacheUtil.removeResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberImpl.class, contestTeamMember.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContestTeamMember> contestTeamMembers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestTeamMember contestTeamMember : contestTeamMembers) {
            EntityCacheUtil.removeResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
                ContestTeamMemberImpl.class, contestTeamMember.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest team member with the primary key. Does not add the contest team member to the database.
     *
     * @param id the primary key for the new contest team member
     * @return the new contest team member
     */
    @Override
    public ContestTeamMember create(long id) {
        ContestTeamMember contestTeamMember = new ContestTeamMemberImpl();

        contestTeamMember.setNew(true);
        contestTeamMember.setPrimaryKey(id);

        return contestTeamMember;
    }

    /**
     * Removes the contest team member with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the contest team member
     * @return the contest team member that was removed
     * @throws com.ext.portlet.NoSuchContestTeamMemberException if a contest team member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember remove(long id)
        throws NoSuchContestTeamMemberException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the contest team member with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest team member
     * @return the contest team member that was removed
     * @throws com.ext.portlet.NoSuchContestTeamMemberException if a contest team member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember remove(Serializable primaryKey)
        throws NoSuchContestTeamMemberException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestTeamMember contestTeamMember = (ContestTeamMember) session.get(ContestTeamMemberImpl.class,
                    primaryKey);

            if (contestTeamMember == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestTeamMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestTeamMember);
        } catch (NoSuchContestTeamMemberException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestTeamMember removeImpl(ContestTeamMember contestTeamMember)
        throws SystemException {
        contestTeamMember = toUnwrappedModel(contestTeamMember);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contestTeamMember)) {
                contestTeamMember = (ContestTeamMember) session.get(ContestTeamMemberImpl.class,
                        contestTeamMember.getPrimaryKeyObj());
            }

            if (contestTeamMember != null) {
                session.delete(contestTeamMember);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contestTeamMember != null) {
            clearCache(contestTeamMember);
        }

        return contestTeamMember;
    }

    @Override
    public ContestTeamMember updateImpl(
        com.ext.portlet.model.ContestTeamMember contestTeamMember)
        throws SystemException {
        contestTeamMember = toUnwrappedModel(contestTeamMember);

        boolean isNew = contestTeamMember.isNew();

        ContestTeamMemberModelImpl contestTeamMemberModelImpl = (ContestTeamMemberModelImpl) contestTeamMember;

        Session session = null;

        try {
            session = openSession();

            if (contestTeamMember.isNew()) {
                session.save(contestTeamMember);

                contestTeamMember.setNew(false);
            } else {
                session.merge(contestTeamMember);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ContestTeamMemberModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((contestTeamMemberModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestTeamMemberModelImpl.getOriginalContestId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID,
                    args);

                args = new Object[] { contestTeamMemberModelImpl.getContestId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberImpl.class, contestTeamMember.getPrimaryKey(),
            contestTeamMember);

        return contestTeamMember;
    }

    protected ContestTeamMember toUnwrappedModel(
        ContestTeamMember contestTeamMember) {
        if (contestTeamMember instanceof ContestTeamMemberImpl) {
            return contestTeamMember;
        }

        ContestTeamMemberImpl contestTeamMemberImpl = new ContestTeamMemberImpl();

        contestTeamMemberImpl.setNew(contestTeamMember.isNew());
        contestTeamMemberImpl.setPrimaryKey(contestTeamMember.getPrimaryKey());

        contestTeamMemberImpl.setId(contestTeamMember.getId());
        contestTeamMemberImpl.setContestId(contestTeamMember.getContestId());
        contestTeamMemberImpl.setUserId(contestTeamMember.getUserId());
        contestTeamMemberImpl.setRoleId(contestTeamMember.getRoleId());

        return contestTeamMemberImpl;
    }

    /**
     * Returns the contest team member with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest team member
     * @return the contest team member
     * @throws com.ext.portlet.NoSuchContestTeamMemberException if a contest team member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContestTeamMemberException, SystemException {
        ContestTeamMember contestTeamMember = fetchByPrimaryKey(primaryKey);

        if (contestTeamMember == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContestTeamMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contestTeamMember;
    }

    /**
     * Returns the contest team member with the primary key or throws a {@link com.ext.portlet.NoSuchContestTeamMemberException} if it could not be found.
     *
     * @param id the primary key of the contest team member
     * @return the contest team member
     * @throws com.ext.portlet.NoSuchContestTeamMemberException if a contest team member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember findByPrimaryKey(long id)
        throws NoSuchContestTeamMemberException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the contest team member with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest team member
     * @return the contest team member, or <code>null</code> if a contest team member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContestTeamMember contestTeamMember = (ContestTeamMember) EntityCacheUtil.getResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
                ContestTeamMemberImpl.class, primaryKey);

        if (contestTeamMember == _nullContestTeamMember) {
            return null;
        }

        if (contestTeamMember == null) {
            Session session = null;

            try {
                session = openSession();

                contestTeamMember = (ContestTeamMember) session.get(ContestTeamMemberImpl.class,
                        primaryKey);

                if (contestTeamMember != null) {
                    cacheResult(contestTeamMember);
                } else {
                    EntityCacheUtil.putResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
                        ContestTeamMemberImpl.class, primaryKey,
                        _nullContestTeamMember);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContestTeamMemberModelImpl.ENTITY_CACHE_ENABLED,
                    ContestTeamMemberImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contestTeamMember;
    }

    /**
     * Returns the contest team member with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the contest team member
     * @return the contest team member, or <code>null</code> if a contest team member with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMember fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the contest team members.
     *
     * @return the contest team members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestTeamMember> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest team members.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest team members
     * @param end the upper bound of the range of contest team members (not inclusive)
     * @return the range of contest team members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestTeamMember> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest team members.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest team members
     * @param end the upper bound of the range of contest team members (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest team members
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestTeamMember> findAll(int start, int end,
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

        List<ContestTeamMember> list = (List<ContestTeamMember>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTTEAMMEMBER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTTEAMMEMBER;

                if (pagination) {
                    sql = sql.concat(ContestTeamMemberModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContestTeamMember>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestTeamMember>(list);
                } else {
                    list = (List<ContestTeamMember>) QueryUtil.list(q,
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
     * Removes all the contest team members from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContestTeamMember contestTeamMember : findAll()) {
            remove(contestTeamMember);
        }
    }

    /**
     * Returns the number of contest team members.
     *
     * @return the number of contest team members
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

                Query q = session.createQuery(_SQL_COUNT_CONTESTTEAMMEMBER);

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
     * Initializes the contest team member persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestTeamMember")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestTeamMember>> listenersList = new ArrayList<ModelListener<ContestTeamMember>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestTeamMember>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestTeamMemberImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
