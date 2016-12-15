package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPointDistributionTargetException;
import com.ext.portlet.model.PointDistributionTarget;
import com.ext.portlet.model.impl.PointDistributionTargetImpl;
import com.ext.portlet.model.impl.PointDistributionTargetModelImpl;
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
 * The persistence implementation for the point distribution target service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointDistributionTargetPersistence
 * @see PointDistributionTargetUtil
 * @generated
 */
public class PointDistributionTargetPersistenceImpl extends BasePersistenceImpl<PointDistributionTarget>
    implements PointDistributionTargetPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PointDistributionTargetUtil} to access the point distribution target persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PointDistributionTargetImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetModelImpl.FINDER_CACHE_ENABLED,
            PointDistributionTargetImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetModelImpl.FINDER_CACHE_ENABLED,
            PointDistributionTargetImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetModelImpl.FINDER_CACHE_ENABLED,
            PointDistributionTargetImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProposalId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetModelImpl.FINDER_CACHE_ENABLED,
            PointDistributionTargetImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProposalId",
            new String[] { Long.class.getName() },
            PointDistributionTargetModelImpl.PROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID = new FinderPath(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProposalId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALID_2 = "pointDistributionTarget.proposalId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTID =
        new FinderPath(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetModelImpl.FINDER_CACHE_ENABLED,
            PointDistributionTargetImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID =
        new FinderPath(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetModelImpl.FINDER_CACHE_ENABLED,
            PointDistributionTargetImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContestId",
            new String[] { Long.class.getName() },
            PointDistributionTargetModelImpl.CONTESTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTID = new FinderPath(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContestId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTID_CONTESTID_2 = "pointDistributionTarget.contestId = ?";
    private static final String _SQL_SELECT_POINTDISTRIBUTIONTARGET = "SELECT pointDistributionTarget FROM PointDistributionTarget pointDistributionTarget";
    private static final String _SQL_SELECT_POINTDISTRIBUTIONTARGET_WHERE = "SELECT pointDistributionTarget FROM PointDistributionTarget pointDistributionTarget WHERE ";
    private static final String _SQL_COUNT_POINTDISTRIBUTIONTARGET = "SELECT COUNT(pointDistributionTarget) FROM PointDistributionTarget pointDistributionTarget";
    private static final String _SQL_COUNT_POINTDISTRIBUTIONTARGET_WHERE = "SELECT COUNT(pointDistributionTarget) FROM PointDistributionTarget pointDistributionTarget WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "pointDistributionTarget.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PointDistributionTarget exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PointDistributionTarget exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PointDistributionTargetPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static PointDistributionTarget _nullPointDistributionTarget = new PointDistributionTargetImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PointDistributionTarget> toCacheModel() {
                return _nullPointDistributionTargetCacheModel;
            }
        };

    private static CacheModel<PointDistributionTarget> _nullPointDistributionTargetCacheModel =
        new CacheModel<PointDistributionTarget>() {
            @Override
            public PointDistributionTarget toEntityModel() {
                return _nullPointDistributionTarget;
            }
        };

    public PointDistributionTargetPersistenceImpl() {
        setModelClass(PointDistributionTarget.class);
    }

    /**
     * Returns all the point distribution targets where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the matching point distribution targets
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointDistributionTarget> findByProposalId(long proposalId)
        throws SystemException {
        return findByProposalId(proposalId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the point distribution targets where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of point distribution targets
     * @param end the upper bound of the range of point distribution targets (not inclusive)
     * @return the range of matching point distribution targets
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointDistributionTarget> findByProposalId(long proposalId,
        int start, int end) throws SystemException {
        return findByProposalId(proposalId, start, end, null);
    }

    /**
     * Returns an ordered range of all the point distribution targets where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of point distribution targets
     * @param end the upper bound of the range of point distribution targets (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching point distribution targets
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointDistributionTarget> findByProposalId(long proposalId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID;
            finderArgs = new Object[] { proposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID;
            finderArgs = new Object[] { proposalId, start, end, orderByComparator };
        }

        List<PointDistributionTarget> list = (List<PointDistributionTarget>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PointDistributionTarget pointDistributionTarget : list) {
                if ((proposalId != pointDistributionTarget.getProposalId())) {
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

            query.append(_SQL_SELECT_POINTDISTRIBUTIONTARGET_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointDistributionTargetModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                if (!pagination) {
                    list = (List<PointDistributionTarget>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointDistributionTarget>(list);
                } else {
                    list = (List<PointDistributionTarget>) QueryUtil.list(q,
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
     * Returns the first point distribution target in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching point distribution target
     * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget findByProposalId_First(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchPointDistributionTargetException, SystemException {
        PointDistributionTarget pointDistributionTarget = fetchByProposalId_First(proposalId,
                orderByComparator);

        if (pointDistributionTarget != null) {
            return pointDistributionTarget;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointDistributionTargetException(msg.toString());
    }

    /**
     * Returns the first point distribution target in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget fetchByProposalId_First(long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PointDistributionTarget> list = findByProposalId(proposalId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last point distribution target in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching point distribution target
     * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget findByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchPointDistributionTargetException, SystemException {
        PointDistributionTarget pointDistributionTarget = fetchByProposalId_Last(proposalId,
                orderByComparator);

        if (pointDistributionTarget != null) {
            return pointDistributionTarget;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointDistributionTargetException(msg.toString());
    }

    /**
     * Returns the last point distribution target in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget fetchByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByProposalId(proposalId);

        if (count == 0) {
            return null;
        }

        List<PointDistributionTarget> list = findByProposalId(proposalId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the point distribution targets before and after the current point distribution target in the ordered set where proposalId = &#63;.
     *
     * @param id the primary key of the current point distribution target
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next point distribution target
     * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget[] findByProposalId_PrevAndNext(long id,
        long proposalId, OrderByComparator orderByComparator)
        throws NoSuchPointDistributionTargetException, SystemException {
        PointDistributionTarget pointDistributionTarget = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PointDistributionTarget[] array = new PointDistributionTargetImpl[3];

            array[0] = getByProposalId_PrevAndNext(session,
                    pointDistributionTarget, proposalId, orderByComparator, true);

            array[1] = pointDistributionTarget;

            array[2] = getByProposalId_PrevAndNext(session,
                    pointDistributionTarget, proposalId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PointDistributionTarget getByProposalId_PrevAndNext(
        Session session, PointDistributionTarget pointDistributionTarget,
        long proposalId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTDISTRIBUTIONTARGET_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

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
            query.append(PointDistributionTargetModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(pointDistributionTarget);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PointDistributionTarget> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the point distribution targets where proposalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProposalId(long proposalId) throws SystemException {
        for (PointDistributionTarget pointDistributionTarget : findByProposalId(
                proposalId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(pointDistributionTarget);
        }
    }

    /**
     * Returns the number of point distribution targets where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the number of matching point distribution targets
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalId(long proposalId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALID;

        Object[] finderArgs = new Object[] { proposalId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_POINTDISTRIBUTIONTARGET_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

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
     * Returns all the point distribution targets where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @return the matching point distribution targets
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointDistributionTarget> findByContestId(long contestId)
        throws SystemException {
        return findByContestId(contestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the point distribution targets where contestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestId the contest ID
     * @param start the lower bound of the range of point distribution targets
     * @param end the upper bound of the range of point distribution targets (not inclusive)
     * @return the range of matching point distribution targets
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointDistributionTarget> findByContestId(long contestId,
        int start, int end) throws SystemException {
        return findByContestId(contestId, start, end, null);
    }

    /**
     * Returns an ordered range of all the point distribution targets where contestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestId the contest ID
     * @param start the lower bound of the range of point distribution targets
     * @param end the upper bound of the range of point distribution targets (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching point distribution targets
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointDistributionTarget> findByContestId(long contestId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
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

        List<PointDistributionTarget> list = (List<PointDistributionTarget>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PointDistributionTarget pointDistributionTarget : list) {
                if ((contestId != pointDistributionTarget.getContestId())) {
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

            query.append(_SQL_SELECT_POINTDISTRIBUTIONTARGET_WHERE);

            query.append(_FINDER_COLUMN_CONTESTID_CONTESTID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointDistributionTargetModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestId);

                if (!pagination) {
                    list = (List<PointDistributionTarget>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointDistributionTarget>(list);
                } else {
                    list = (List<PointDistributionTarget>) QueryUtil.list(q,
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
     * Returns the first point distribution target in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching point distribution target
     * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget findByContestId_First(long contestId,
        OrderByComparator orderByComparator)
        throws NoSuchPointDistributionTargetException, SystemException {
        PointDistributionTarget pointDistributionTarget = fetchByContestId_First(contestId,
                orderByComparator);

        if (pointDistributionTarget != null) {
            return pointDistributionTarget;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestId=");
        msg.append(contestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointDistributionTargetException(msg.toString());
    }

    /**
     * Returns the first point distribution target in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget fetchByContestId_First(long contestId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PointDistributionTarget> list = findByContestId(contestId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last point distribution target in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching point distribution target
     * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget findByContestId_Last(long contestId,
        OrderByComparator orderByComparator)
        throws NoSuchPointDistributionTargetException, SystemException {
        PointDistributionTarget pointDistributionTarget = fetchByContestId_Last(contestId,
                orderByComparator);

        if (pointDistributionTarget != null) {
            return pointDistributionTarget;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestId=");
        msg.append(contestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointDistributionTargetException(msg.toString());
    }

    /**
     * Returns the last point distribution target in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget fetchByContestId_Last(long contestId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContestId(contestId);

        if (count == 0) {
            return null;
        }

        List<PointDistributionTarget> list = findByContestId(contestId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the point distribution targets before and after the current point distribution target in the ordered set where contestId = &#63;.
     *
     * @param id the primary key of the current point distribution target
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next point distribution target
     * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget[] findByContestId_PrevAndNext(long id,
        long contestId, OrderByComparator orderByComparator)
        throws NoSuchPointDistributionTargetException, SystemException {
        PointDistributionTarget pointDistributionTarget = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PointDistributionTarget[] array = new PointDistributionTargetImpl[3];

            array[0] = getByContestId_PrevAndNext(session,
                    pointDistributionTarget, contestId, orderByComparator, true);

            array[1] = pointDistributionTarget;

            array[2] = getByContestId_PrevAndNext(session,
                    pointDistributionTarget, contestId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PointDistributionTarget getByContestId_PrevAndNext(
        Session session, PointDistributionTarget pointDistributionTarget,
        long contestId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTDISTRIBUTIONTARGET_WHERE);

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
            query.append(PointDistributionTargetModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(pointDistributionTarget);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PointDistributionTarget> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the point distribution targets where contestId = &#63; from the database.
     *
     * @param contestId the contest ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestId(long contestId) throws SystemException {
        for (PointDistributionTarget pointDistributionTarget : findByContestId(
                contestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(pointDistributionTarget);
        }
    }

    /**
     * Returns the number of point distribution targets where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @return the number of matching point distribution targets
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

            query.append(_SQL_COUNT_POINTDISTRIBUTIONTARGET_WHERE);

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
     * Caches the point distribution target in the entity cache if it is enabled.
     *
     * @param pointDistributionTarget the point distribution target
     */
    @Override
    public void cacheResult(PointDistributionTarget pointDistributionTarget) {
        EntityCacheUtil.putResult(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetImpl.class,
            pointDistributionTarget.getPrimaryKey(), pointDistributionTarget);

        pointDistributionTarget.resetOriginalValues();
    }

    /**
     * Caches the point distribution targets in the entity cache if it is enabled.
     *
     * @param pointDistributionTargets the point distribution targets
     */
    @Override
    public void cacheResult(
        List<PointDistributionTarget> pointDistributionTargets) {
        for (PointDistributionTarget pointDistributionTarget : pointDistributionTargets) {
            if (EntityCacheUtil.getResult(
                        PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
                        PointDistributionTargetImpl.class,
                        pointDistributionTarget.getPrimaryKey()) == null) {
                cacheResult(pointDistributionTarget);
            } else {
                pointDistributionTarget.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all point distribution targets.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PointDistributionTargetImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PointDistributionTargetImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the point distribution target.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PointDistributionTarget pointDistributionTarget) {
        EntityCacheUtil.removeResult(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetImpl.class,
            pointDistributionTarget.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<PointDistributionTarget> pointDistributionTargets) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PointDistributionTarget pointDistributionTarget : pointDistributionTargets) {
            EntityCacheUtil.removeResult(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
                PointDistributionTargetImpl.class,
                pointDistributionTarget.getPrimaryKey());
        }
    }

    /**
     * Creates a new point distribution target with the primary key. Does not add the point distribution target to the database.
     *
     * @param id the primary key for the new point distribution target
     * @return the new point distribution target
     */
    @Override
    public PointDistributionTarget create(long id) {
        PointDistributionTarget pointDistributionTarget = new PointDistributionTargetImpl();

        pointDistributionTarget.setNew(true);
        pointDistributionTarget.setPrimaryKey(id);

        return pointDistributionTarget;
    }

    /**
     * Removes the point distribution target with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the point distribution target
     * @return the point distribution target that was removed
     * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget remove(long id)
        throws NoSuchPointDistributionTargetException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the point distribution target with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the point distribution target
     * @return the point distribution target that was removed
     * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget remove(Serializable primaryKey)
        throws NoSuchPointDistributionTargetException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PointDistributionTarget pointDistributionTarget = (PointDistributionTarget) session.get(PointDistributionTargetImpl.class,
                    primaryKey);

            if (pointDistributionTarget == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPointDistributionTargetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(pointDistributionTarget);
        } catch (NoSuchPointDistributionTargetException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PointDistributionTarget removeImpl(
        PointDistributionTarget pointDistributionTarget)
        throws SystemException {
        pointDistributionTarget = toUnwrappedModel(pointDistributionTarget);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(pointDistributionTarget)) {
                pointDistributionTarget = (PointDistributionTarget) session.get(PointDistributionTargetImpl.class,
                        pointDistributionTarget.getPrimaryKeyObj());
            }

            if (pointDistributionTarget != null) {
                session.delete(pointDistributionTarget);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (pointDistributionTarget != null) {
            clearCache(pointDistributionTarget);
        }

        return pointDistributionTarget;
    }

    @Override
    public PointDistributionTarget updateImpl(
        com.ext.portlet.model.PointDistributionTarget pointDistributionTarget)
        throws SystemException {
        pointDistributionTarget = toUnwrappedModel(pointDistributionTarget);

        boolean isNew = pointDistributionTarget.isNew();

        PointDistributionTargetModelImpl pointDistributionTargetModelImpl = (PointDistributionTargetModelImpl) pointDistributionTarget;

        Session session = null;

        try {
            session = openSession();

            if (pointDistributionTarget.isNew()) {
                session.save(pointDistributionTarget);

                pointDistributionTarget.setNew(false);
            } else {
                session.merge(pointDistributionTarget);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PointDistributionTargetModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((pointDistributionTargetModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointDistributionTargetModelImpl.getOriginalProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);

                args = new Object[] {
                        pointDistributionTargetModelImpl.getProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);
            }

            if ((pointDistributionTargetModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointDistributionTargetModelImpl.getOriginalContestId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID,
                    args);

                args = new Object[] {
                        pointDistributionTargetModelImpl.getContestId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
            PointDistributionTargetImpl.class,
            pointDistributionTarget.getPrimaryKey(), pointDistributionTarget);

        return pointDistributionTarget;
    }

    protected PointDistributionTarget toUnwrappedModel(
        PointDistributionTarget pointDistributionTarget) {
        if (pointDistributionTarget instanceof PointDistributionTargetImpl) {
            return pointDistributionTarget;
        }

        PointDistributionTargetImpl pointDistributionTargetImpl = new PointDistributionTargetImpl();

        pointDistributionTargetImpl.setNew(pointDistributionTarget.isNew());
        pointDistributionTargetImpl.setPrimaryKey(pointDistributionTarget.getPrimaryKey());

        pointDistributionTargetImpl.setId(pointDistributionTarget.getId());
        pointDistributionTargetImpl.setContestId(pointDistributionTarget.getContestId());
        pointDistributionTargetImpl.setProposalId(pointDistributionTarget.getProposalId());
        pointDistributionTargetImpl.setNumberOfPoints(pointDistributionTarget.getNumberOfPoints());
        pointDistributionTargetImpl.setPointTypeOverride(pointDistributionTarget.getPointTypeOverride());

        return pointDistributionTargetImpl;
    }

    /**
     * Returns the point distribution target with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the point distribution target
     * @return the point distribution target
     * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPointDistributionTargetException, SystemException {
        PointDistributionTarget pointDistributionTarget = fetchByPrimaryKey(primaryKey);

        if (pointDistributionTarget == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPointDistributionTargetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return pointDistributionTarget;
    }

    /**
     * Returns the point distribution target with the primary key or throws a {@link com.ext.portlet.NoSuchPointDistributionTargetException} if it could not be found.
     *
     * @param id the primary key of the point distribution target
     * @return the point distribution target
     * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget findByPrimaryKey(long id)
        throws NoSuchPointDistributionTargetException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the point distribution target with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the point distribution target
     * @return the point distribution target, or <code>null</code> if a point distribution target with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PointDistributionTarget pointDistributionTarget = (PointDistributionTarget) EntityCacheUtil.getResult(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
                PointDistributionTargetImpl.class, primaryKey);

        if (pointDistributionTarget == _nullPointDistributionTarget) {
            return null;
        }

        if (pointDistributionTarget == null) {
            Session session = null;

            try {
                session = openSession();

                pointDistributionTarget = (PointDistributionTarget) session.get(PointDistributionTargetImpl.class,
                        primaryKey);

                if (pointDistributionTarget != null) {
                    cacheResult(pointDistributionTarget);
                } else {
                    EntityCacheUtil.putResult(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
                        PointDistributionTargetImpl.class, primaryKey,
                        _nullPointDistributionTarget);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PointDistributionTargetModelImpl.ENTITY_CACHE_ENABLED,
                    PointDistributionTargetImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return pointDistributionTarget;
    }

    /**
     * Returns the point distribution target with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the point distribution target
     * @return the point distribution target, or <code>null</code> if a point distribution target with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointDistributionTarget fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the point distribution targets.
     *
     * @return the point distribution targets
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointDistributionTarget> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the point distribution targets.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of point distribution targets
     * @param end the upper bound of the range of point distribution targets (not inclusive)
     * @return the range of point distribution targets
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointDistributionTarget> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the point distribution targets.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of point distribution targets
     * @param end the upper bound of the range of point distribution targets (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of point distribution targets
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointDistributionTarget> findAll(int start, int end,
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

        List<PointDistributionTarget> list = (List<PointDistributionTarget>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_POINTDISTRIBUTIONTARGET);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_POINTDISTRIBUTIONTARGET;

                if (pagination) {
                    sql = sql.concat(PointDistributionTargetModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PointDistributionTarget>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointDistributionTarget>(list);
                } else {
                    list = (List<PointDistributionTarget>) QueryUtil.list(q,
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
     * Removes all the point distribution targets from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PointDistributionTarget pointDistributionTarget : findAll()) {
            remove(pointDistributionTarget);
        }
    }

    /**
     * Returns the number of point distribution targets.
     *
     * @return the number of point distribution targets
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

                Query q = session.createQuery(_SQL_COUNT_POINTDISTRIBUTIONTARGET);

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
     * Initializes the point distribution target persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PointDistributionTarget")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PointDistributionTarget>> listenersList = new ArrayList<ModelListener<PointDistributionTarget>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PointDistributionTarget>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PointDistributionTargetImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
