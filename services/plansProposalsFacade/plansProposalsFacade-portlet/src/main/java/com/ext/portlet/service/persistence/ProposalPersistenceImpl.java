package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalException;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.impl.ProposalImpl;
import com.ext.portlet.model.impl.ProposalModelImpl;
import com.ext.portlet.service.persistence.ProposalPersistence;

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
import com.liferay.portal.kernel.util.CalendarUtil;
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
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the proposal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalPersistence
 * @see ProposalUtil
 * @generated
 */
public class ProposalPersistenceImpl extends BasePersistenceImpl<Proposal>
    implements ProposalPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalUtil} to access the proposal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalModelImpl.ENTITY_CACHE_ENABLED,
            ProposalModelImpl.FINDER_CACHE_ENABLED, ProposalImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalModelImpl.ENTITY_CACHE_ENABLED,
            ProposalModelImpl.FINDER_CACHE_ENABLED, ProposalImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalModelImpl.ENTITY_CACHE_ENABLED,
            ProposalModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDAFTER =
        new FinderPath(ProposalModelImpl.ENTITY_CACHE_ENABLED,
            ProposalModelImpl.FINDER_CACHE_ENABLED, ProposalImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByModifiedAfter",
            new String[] {
                Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_MODIFIEDAFTER =
        new FinderPath(ProposalModelImpl.ENTITY_CACHE_ENABLED,
            ProposalModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByModifiedAfter",
            new String[] { Date.class.getName() });
    private static final String _FINDER_COLUMN_MODIFIEDAFTER_UPDATEDDATE_1 = "proposal.updatedDate >= NULL";
    private static final String _FINDER_COLUMN_MODIFIEDAFTER_UPDATEDDATE_2 = "proposal.updatedDate >= ?";
    private static final String _SQL_SELECT_PROPOSAL = "SELECT proposal FROM Proposal proposal";
    private static final String _SQL_SELECT_PROPOSAL_WHERE = "SELECT proposal FROM Proposal proposal WHERE ";
    private static final String _SQL_COUNT_PROPOSAL = "SELECT COUNT(proposal) FROM Proposal proposal";
    private static final String _SQL_COUNT_PROPOSAL_WHERE = "SELECT COUNT(proposal) FROM Proposal proposal WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposal.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Proposal exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Proposal exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalPersistenceImpl.class);
    private static Proposal _nullProposal = new ProposalImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Proposal> toCacheModel() {
                return _nullProposalCacheModel;
            }
        };

    private static CacheModel<Proposal> _nullProposalCacheModel = new CacheModel<Proposal>() {
            @Override
            public Proposal toEntityModel() {
                return _nullProposal;
            }
        };

    public ProposalPersistenceImpl() {
        setModelClass(Proposal.class);
    }

    /**
     * Returns all the proposals where updatedDate &ge; &#63;.
     *
     * @param updatedDate the updated date
     * @return the matching proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Proposal> findByModifiedAfter(Date updatedDate)
        throws SystemException {
        return findByModifiedAfter(updatedDate, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposals where updatedDate &ge; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param updatedDate the updated date
     * @param start the lower bound of the range of proposals
     * @param end the upper bound of the range of proposals (not inclusive)
     * @return the range of matching proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Proposal> findByModifiedAfter(Date updatedDate, int start,
        int end) throws SystemException {
        return findByModifiedAfter(updatedDate, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposals where updatedDate &ge; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param updatedDate the updated date
     * @param start the lower bound of the range of proposals
     * @param end the upper bound of the range of proposals (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Proposal> findByModifiedAfter(Date updatedDate, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODIFIEDAFTER;
        finderArgs = new Object[] { updatedDate, start, end, orderByComparator };

        List<Proposal> list = (List<Proposal>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Proposal proposal : list) {
                if ((updatedDate.getTime() > proposal.getUpdatedDate().getTime())) {
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

            query.append(_SQL_SELECT_PROPOSAL_WHERE);

            boolean bindUpdatedDate = false;

            if (updatedDate == null) {
                query.append(_FINDER_COLUMN_MODIFIEDAFTER_UPDATEDDATE_1);
            } else {
                bindUpdatedDate = true;

                query.append(_FINDER_COLUMN_MODIFIEDAFTER_UPDATEDDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUpdatedDate) {
                    qPos.add(CalendarUtil.getTimestamp(updatedDate));
                }

                if (!pagination) {
                    list = (List<Proposal>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Proposal>(list);
                } else {
                    list = (List<Proposal>) QueryUtil.list(q, getDialect(),
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
     * Returns the first proposal in the ordered set where updatedDate &ge; &#63;.
     *
     * @param updatedDate the updated date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal
     * @throws com.ext.portlet.NoSuchProposalException if a matching proposal could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal findByModifiedAfter_First(Date updatedDate,
        OrderByComparator orderByComparator)
        throws NoSuchProposalException, SystemException {
        Proposal proposal = fetchByModifiedAfter_First(updatedDate,
                orderByComparator);

        if (proposal != null) {
            return proposal;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("updatedDate=");
        msg.append(updatedDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalException(msg.toString());
    }

    /**
     * Returns the first proposal in the ordered set where updatedDate &ge; &#63;.
     *
     * @param updatedDate the updated date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal, or <code>null</code> if a matching proposal could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal fetchByModifiedAfter_First(Date updatedDate,
        OrderByComparator orderByComparator) throws SystemException {
        List<Proposal> list = findByModifiedAfter(updatedDate, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal in the ordered set where updatedDate &ge; &#63;.
     *
     * @param updatedDate the updated date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal
     * @throws com.ext.portlet.NoSuchProposalException if a matching proposal could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal findByModifiedAfter_Last(Date updatedDate,
        OrderByComparator orderByComparator)
        throws NoSuchProposalException, SystemException {
        Proposal proposal = fetchByModifiedAfter_Last(updatedDate,
                orderByComparator);

        if (proposal != null) {
            return proposal;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("updatedDate=");
        msg.append(updatedDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalException(msg.toString());
    }

    /**
     * Returns the last proposal in the ordered set where updatedDate &ge; &#63;.
     *
     * @param updatedDate the updated date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal, or <code>null</code> if a matching proposal could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal fetchByModifiedAfter_Last(Date updatedDate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByModifiedAfter(updatedDate);

        if (count == 0) {
            return null;
        }

        List<Proposal> list = findByModifiedAfter(updatedDate, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposals before and after the current proposal in the ordered set where updatedDate &ge; &#63;.
     *
     * @param proposalId the primary key of the current proposal
     * @param updatedDate the updated date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal
     * @throws com.ext.portlet.NoSuchProposalException if a proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal[] findByModifiedAfter_PrevAndNext(long proposalId,
        Date updatedDate, OrderByComparator orderByComparator)
        throws NoSuchProposalException, SystemException {
        Proposal proposal = findByPrimaryKey(proposalId);

        Session session = null;

        try {
            session = openSession();

            Proposal[] array = new ProposalImpl[3];

            array[0] = getByModifiedAfter_PrevAndNext(session, proposal,
                    updatedDate, orderByComparator, true);

            array[1] = proposal;

            array[2] = getByModifiedAfter_PrevAndNext(session, proposal,
                    updatedDate, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Proposal getByModifiedAfter_PrevAndNext(Session session,
        Proposal proposal, Date updatedDate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSAL_WHERE);

        boolean bindUpdatedDate = false;

        if (updatedDate == null) {
            query.append(_FINDER_COLUMN_MODIFIEDAFTER_UPDATEDDATE_1);
        } else {
            bindUpdatedDate = true;

            query.append(_FINDER_COLUMN_MODIFIEDAFTER_UPDATEDDATE_2);
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
            query.append(ProposalModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindUpdatedDate) {
            qPos.add(CalendarUtil.getTimestamp(updatedDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposal);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Proposal> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposals where updatedDate &ge; &#63; from the database.
     *
     * @param updatedDate the updated date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByModifiedAfter(Date updatedDate)
        throws SystemException {
        for (Proposal proposal : findByModifiedAfter(updatedDate,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposal);
        }
    }

    /**
     * Returns the number of proposals where updatedDate &ge; &#63;.
     *
     * @param updatedDate the updated date
     * @return the number of matching proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByModifiedAfter(Date updatedDate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_MODIFIEDAFTER;

        Object[] finderArgs = new Object[] { updatedDate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSAL_WHERE);

            boolean bindUpdatedDate = false;

            if (updatedDate == null) {
                query.append(_FINDER_COLUMN_MODIFIEDAFTER_UPDATEDDATE_1);
            } else {
                bindUpdatedDate = true;

                query.append(_FINDER_COLUMN_MODIFIEDAFTER_UPDATEDDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUpdatedDate) {
                    qPos.add(CalendarUtil.getTimestamp(updatedDate));
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
     * Caches the proposal in the entity cache if it is enabled.
     *
     * @param proposal the proposal
     */
    @Override
    public void cacheResult(Proposal proposal) {
        EntityCacheUtil.putResult(ProposalModelImpl.ENTITY_CACHE_ENABLED,
            ProposalImpl.class, proposal.getPrimaryKey(), proposal);

        proposal.resetOriginalValues();
    }

    /**
     * Caches the proposals in the entity cache if it is enabled.
     *
     * @param proposals the proposals
     */
    @Override
    public void cacheResult(List<Proposal> proposals) {
        for (Proposal proposal : proposals) {
            if (EntityCacheUtil.getResult(
                        ProposalModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalImpl.class, proposal.getPrimaryKey()) == null) {
                cacheResult(proposal);
            } else {
                proposal.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposals.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Proposal proposal) {
        EntityCacheUtil.removeResult(ProposalModelImpl.ENTITY_CACHE_ENABLED,
            ProposalImpl.class, proposal.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<Proposal> proposals) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Proposal proposal : proposals) {
            EntityCacheUtil.removeResult(ProposalModelImpl.ENTITY_CACHE_ENABLED,
                ProposalImpl.class, proposal.getPrimaryKey());
        }
    }

    /**
     * Creates a new proposal with the primary key. Does not add the proposal to the database.
     *
     * @param proposalId the primary key for the new proposal
     * @return the new proposal
     */
    @Override
    public Proposal create(long proposalId) {
        Proposal proposal = new ProposalImpl();

        proposal.setNew(true);
        proposal.setPrimaryKey(proposalId);

        return proposal;
    }

    /**
     * Removes the proposal with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param proposalId the primary key of the proposal
     * @return the proposal that was removed
     * @throws com.ext.portlet.NoSuchProposalException if a proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal remove(long proposalId)
        throws NoSuchProposalException, SystemException {
        return remove((Serializable) proposalId);
    }

    /**
     * Removes the proposal with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal
     * @return the proposal that was removed
     * @throws com.ext.portlet.NoSuchProposalException if a proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal remove(Serializable primaryKey)
        throws NoSuchProposalException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Proposal proposal = (Proposal) session.get(ProposalImpl.class,
                    primaryKey);

            if (proposal == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposal);
        } catch (NoSuchProposalException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Proposal removeImpl(Proposal proposal) throws SystemException {
        proposal = toUnwrappedModel(proposal);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposal)) {
                proposal = (Proposal) session.get(ProposalImpl.class,
                        proposal.getPrimaryKeyObj());
            }

            if (proposal != null) {
                session.delete(proposal);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposal != null) {
            clearCache(proposal);
        }

        return proposal;
    }

    @Override
    public Proposal updateImpl(com.ext.portlet.model.Proposal proposal)
        throws SystemException {
        proposal = toUnwrappedModel(proposal);

        boolean isNew = proposal.isNew();

        Session session = null;

        try {
            session = openSession();

            if (proposal.isNew()) {
                session.save(proposal);

                proposal.setNew(false);
            } else {
                session.merge(proposal);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ProposalModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(ProposalModelImpl.ENTITY_CACHE_ENABLED,
            ProposalImpl.class, proposal.getPrimaryKey(), proposal);

        return proposal;
    }

    protected Proposal toUnwrappedModel(Proposal proposal) {
        if (proposal instanceof ProposalImpl) {
            return proposal;
        }

        ProposalImpl proposalImpl = new ProposalImpl();

        proposalImpl.setNew(proposal.isNew());
        proposalImpl.setPrimaryKey(proposal.getPrimaryKey());

        proposalImpl.setProposalId(proposal.getProposalId());
        proposalImpl.setCreateDate(proposal.getCreateDate());
        proposalImpl.setUpdatedDate(proposal.getUpdatedDate());
        proposalImpl.setCurrentVersion(proposal.getCurrentVersion());
        proposalImpl.setAuthorId(proposal.getAuthorId());
        proposalImpl.setVisible(proposal.isVisible());
        proposalImpl.setDiscussionId(proposal.getDiscussionId());
        proposalImpl.setResultsDiscussionId(proposal.getResultsDiscussionId());
        proposalImpl.setJudgeDiscussionId(proposal.getJudgeDiscussionId());
        proposalImpl.setFellowDiscussionId(proposal.getFellowDiscussionId());
        proposalImpl.setAdvisorDiscussionId(proposal.getAdvisorDiscussionId());
        proposalImpl.setGroupId(proposal.getGroupId());

        return proposalImpl;
    }

    /**
     * Returns the proposal with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal
     * @return the proposal
     * @throws com.ext.portlet.NoSuchProposalException if a proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProposalException, SystemException {
        Proposal proposal = fetchByPrimaryKey(primaryKey);

        if (proposal == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposal;
    }

    /**
     * Returns the proposal with the primary key or throws a {@link com.ext.portlet.NoSuchProposalException} if it could not be found.
     *
     * @param proposalId the primary key of the proposal
     * @return the proposal
     * @throws com.ext.portlet.NoSuchProposalException if a proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal findByPrimaryKey(long proposalId)
        throws NoSuchProposalException, SystemException {
        return findByPrimaryKey((Serializable) proposalId);
    }

    /**
     * Returns the proposal with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal
     * @return the proposal, or <code>null</code> if a proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        Proposal proposal = (Proposal) EntityCacheUtil.getResult(ProposalModelImpl.ENTITY_CACHE_ENABLED,
                ProposalImpl.class, primaryKey);

        if (proposal == _nullProposal) {
            return null;
        }

        if (proposal == null) {
            Session session = null;

            try {
                session = openSession();

                proposal = (Proposal) session.get(ProposalImpl.class, primaryKey);

                if (proposal != null) {
                    cacheResult(proposal);
                } else {
                    EntityCacheUtil.putResult(ProposalModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalImpl.class, primaryKey, _nullProposal);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposal;
    }

    /**
     * Returns the proposal with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param proposalId the primary key of the proposal
     * @return the proposal, or <code>null</code> if a proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Proposal fetchByPrimaryKey(long proposalId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) proposalId);
    }

    /**
     * Returns all the proposals.
     *
     * @return the proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Proposal> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposals
     * @param end the upper bound of the range of proposals (not inclusive)
     * @return the range of proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Proposal> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposals
     * @param end the upper bound of the range of proposals (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Proposal> findAll(int start, int end,
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

        List<Proposal> list = (List<Proposal>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSAL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSAL;

                if (pagination) {
                    sql = sql.concat(ProposalModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<Proposal>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Proposal>(list);
                } else {
                    list = (List<Proposal>) QueryUtil.list(q, getDialect(),
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
     * Removes all the proposals from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (Proposal proposal : findAll()) {
            remove(proposal);
        }
    }

    /**
     * Returns the number of proposals.
     *
     * @return the number of proposals
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSAL);

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
     * Initializes the proposal persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.Proposal")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Proposal>> listenersList = new ArrayList<ModelListener<Proposal>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Proposal>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
