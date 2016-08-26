package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalVoteException;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.model.impl.ProposalVoteImpl;
import com.ext.portlet.model.impl.ProposalVoteModelImpl;
import com.ext.portlet.service.persistence.ProposalVotePersistence;

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
 * The persistence implementation for the proposal vote service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVotePersistence
 * @see ProposalVoteUtil
 * @generated
 */
public class ProposalVotePersistenceImpl extends BasePersistenceImpl<ProposalVote>
    implements ProposalVotePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalVoteUtil} to access the proposal vote persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalVoteImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProposalId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProposalId",
            new String[] { Long.class.getName() },
            ProposalVoteModelImpl.PROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProposalId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALID_2 = "proposalVote.proposalId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByProposalIdContestPhaseId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByProposalIdContestPhaseId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ProposalVoteModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalVoteModelImpl.CONTESTPHASEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalIdContestPhaseId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2 =
        "proposalVote.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2 =
        "proposalVote.id.contestPhaseId = ?";
    public static final FinderPath FINDER_PATH_FETCH_BY_PROPOSALIDUSERID = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByProposalIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ProposalVoteModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalVoteModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALIDUSERID = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALIDUSERID_PROPOSALID_2 = "proposalVote.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDUSERID_USERID_2 = "proposalVote.id.userId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
            new String[] { Long.class.getName() },
            ProposalVoteModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_USERID_USERID_2 = "proposalVote.id.userId = ?";
    public static final FinderPath FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByContestPhaseIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ProposalVoteModelImpl.CONTESTPHASEID_COLUMN_BITMASK |
            ProposalVoteModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTPHASEIDUSERID = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByContestPhaseIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTPHASEIDUSERID_CONTESTPHASEID_2 =
        "proposalVote.id.contestPhaseId = ? AND ";
    private static final String _FINDER_COLUMN_CONTESTPHASEIDUSERID_USERID_2 = "proposalVote.id.userId = ?";
    private static final String _SQL_SELECT_PROPOSALVOTE = "SELECT proposalVote FROM ProposalVote proposalVote";
    private static final String _SQL_SELECT_PROPOSALVOTE_WHERE = "SELECT proposalVote FROM ProposalVote proposalVote WHERE ";
    private static final String _SQL_COUNT_PROPOSALVOTE = "SELECT COUNT(proposalVote) FROM ProposalVote proposalVote";
    private static final String _SQL_COUNT_PROPOSALVOTE_WHERE = "SELECT COUNT(proposalVote) FROM ProposalVote proposalVote WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalVote.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalVote exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProposalVote exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalVotePersistenceImpl.class);
    private static ProposalVote _nullProposalVote = new ProposalVoteImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalVote> toCacheModel() {
                return _nullProposalVoteCacheModel;
            }
        };

    private static CacheModel<ProposalVote> _nullProposalVoteCacheModel = new CacheModel<ProposalVote>() {
            @Override
            public ProposalVote toEntityModel() {
                return _nullProposalVote;
            }
        };

    public ProposalVotePersistenceImpl() {
        setModelClass(ProposalVote.class);
    }

    /**
     * Returns all the proposal votes where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findByProposalId(long proposalId)
        throws SystemException {
        return findByProposalId(proposalId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal votes where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @return the range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findByProposalId(long proposalId, int start,
        int end) throws SystemException {
        return findByProposalId(proposalId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal votes where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findByProposalId(long proposalId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
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

        List<ProposalVote> list = (List<ProposalVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalVote proposalVote : list) {
                if ((proposalId != proposalVote.getProposalId())) {
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

            query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalVoteModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                if (!pagination) {
                    list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalVote>(list);
                } else {
                    list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
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
     * Returns the first proposal vote in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByProposalId_First(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = fetchByProposalId_First(proposalId,
                orderByComparator);

        if (proposalVote != null) {
            return proposalVote;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalVoteException(msg.toString());
    }

    /**
     * Returns the first proposal vote in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByProposalId_First(long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ProposalVote> list = findByProposalId(proposalId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal vote in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = fetchByProposalId_Last(proposalId,
                orderByComparator);

        if (proposalVote != null) {
            return proposalVote;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalVoteException(msg.toString());
    }

    /**
     * Returns the last proposal vote in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByProposalId(proposalId);

        if (count == 0) {
            return null;
        }

        List<ProposalVote> list = findByProposalId(proposalId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal votes before and after the current proposal vote in the ordered set where proposalId = &#63;.
     *
     * @param proposalVotePK the primary key of the current proposal vote
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote[] findByProposalId_PrevAndNext(
        ProposalVotePK proposalVotePK, long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = findByPrimaryKey(proposalVotePK);

        Session session = null;

        try {
            session = openSession();

            ProposalVote[] array = new ProposalVoteImpl[3];

            array[0] = getByProposalId_PrevAndNext(session, proposalVote,
                    proposalId, orderByComparator, true);

            array[1] = proposalVote;

            array[2] = getByProposalId_PrevAndNext(session, proposalVote,
                    proposalId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalVote getByProposalId_PrevAndNext(Session session,
        ProposalVote proposalVote, long proposalId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

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
            query.append(ProposalVoteModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalVote);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalVote> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal votes where proposalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProposalId(long proposalId) throws SystemException {
        for (ProposalVote proposalVote : findByProposalId(proposalId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalVote);
        }
    }

    /**
     * Returns the number of proposal votes where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the number of matching proposal votes
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

            query.append(_SQL_COUNT_PROPOSALVOTE_WHERE);

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
     * Returns all the proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @return the matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId) throws SystemException {
        return findByProposalIdContestPhaseId(proposalId, contestPhaseId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @return the range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId, int start, int end) throws SystemException {
        return findByProposalIdContestPhaseId(proposalId, contestPhaseId,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID;
            finderArgs = new Object[] { proposalId, contestPhaseId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID;
            finderArgs = new Object[] {
                    proposalId, contestPhaseId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalVote> list = (List<ProposalVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalVote proposalVote : list) {
                if ((proposalId != proposalVote.getProposalId()) ||
                        (contestPhaseId != proposalVote.getContestPhaseId())) {
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

            query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalVoteModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(contestPhaseId);

                if (!pagination) {
                    list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalVote>(list);
                } else {
                    list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
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
     * Returns the first proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByProposalIdContestPhaseId_First(long proposalId,
        long contestPhaseId, OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = fetchByProposalIdContestPhaseId_First(proposalId,
                contestPhaseId, orderByComparator);

        if (proposalVote != null) {
            return proposalVote;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(", contestPhaseId=");
        msg.append(contestPhaseId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalVoteException(msg.toString());
    }

    /**
     * Returns the first proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByProposalIdContestPhaseId_First(long proposalId,
        long contestPhaseId, OrderByComparator orderByComparator)
        throws SystemException {
        List<ProposalVote> list = findByProposalIdContestPhaseId(proposalId,
                contestPhaseId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByProposalIdContestPhaseId_Last(long proposalId,
        long contestPhaseId, OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = fetchByProposalIdContestPhaseId_Last(proposalId,
                contestPhaseId, orderByComparator);

        if (proposalVote != null) {
            return proposalVote;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(", contestPhaseId=");
        msg.append(contestPhaseId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalVoteException(msg.toString());
    }

    /**
     * Returns the last proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByProposalIdContestPhaseId_Last(long proposalId,
        long contestPhaseId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByProposalIdContestPhaseId(proposalId, contestPhaseId);

        if (count == 0) {
            return null;
        }

        List<ProposalVote> list = findByProposalIdContestPhaseId(proposalId,
                contestPhaseId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal votes before and after the current proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalVotePK the primary key of the current proposal vote
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote[] findByProposalIdContestPhaseId_PrevAndNext(
        ProposalVotePK proposalVotePK, long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = findByPrimaryKey(proposalVotePK);

        Session session = null;

        try {
            session = openSession();

            ProposalVote[] array = new ProposalVoteImpl[3];

            array[0] = getByProposalIdContestPhaseId_PrevAndNext(session,
                    proposalVote, proposalId, contestPhaseId,
                    orderByComparator, true);

            array[1] = proposalVote;

            array[2] = getByProposalIdContestPhaseId_PrevAndNext(session,
                    proposalVote, proposalId, contestPhaseId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalVote getByProposalIdContestPhaseId_PrevAndNext(
        Session session, ProposalVote proposalVote, long proposalId,
        long contestPhaseId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2);

        query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2);

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
            query.append(ProposalVoteModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        qPos.add(contestPhaseId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalVote);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalVote> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal votes where proposalId = &#63; and contestPhaseId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId) throws SystemException {
        for (ProposalVote proposalVote : findByProposalIdContestPhaseId(
                proposalId, contestPhaseId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(proposalVote);
        }
    }

    /**
     * Returns the number of proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @return the number of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID;

        Object[] finderArgs = new Object[] { proposalId, contestPhaseId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(contestPhaseId);

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
     * Returns the proposal vote where proposalId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalVoteException} if it could not be found.
     *
     * @param proposalId the proposal ID
     * @param userId the user ID
     * @return the matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByProposalIdUserId(long proposalId, long userId)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = fetchByProposalIdUserId(proposalId, userId);

        if (proposalVote == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchProposalVoteException(msg.toString());
        }

        return proposalVote;
    }

    /**
     * Returns the proposal vote where proposalId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param proposalId the proposal ID
     * @param userId the user ID
     * @return the matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByProposalIdUserId(long proposalId, long userId)
        throws SystemException {
        return fetchByProposalIdUserId(proposalId, userId, true);
    }

    /**
     * Returns the proposal vote where proposalId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param proposalId the proposal ID
     * @param userId the user ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByProposalIdUserId(long proposalId, long userId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { proposalId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PROPOSALIDUSERID,
                    finderArgs, this);
        }

        if (result instanceof ProposalVote) {
            ProposalVote proposalVote = (ProposalVote) result;

            if ((proposalId != proposalVote.getProposalId()) ||
                    (userId != proposalVote.getUserId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDUSERID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(userId);

                List<ProposalVote> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDUSERID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ProposalVotePersistenceImpl.fetchByProposalIdUserId(long, long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ProposalVote proposalVote = list.get(0);

                    result = proposalVote;

                    cacheResult(proposalVote);

                    if ((proposalVote.getProposalId() != proposalId) ||
                            (proposalVote.getUserId() != userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDUSERID,
                            finderArgs, proposalVote);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDUSERID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ProposalVote) result;
        }
    }

    /**
     * Removes the proposal vote where proposalId = &#63; and userId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param userId the user ID
     * @return the proposal vote that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote removeByProposalIdUserId(long proposalId, long userId)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = findByProposalIdUserId(proposalId, userId);

        return remove(proposalVote);
    }

    /**
     * Returns the number of proposal votes where proposalId = &#63; and userId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param userId the user ID
     * @return the number of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalIdUserId(long proposalId, long userId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALIDUSERID;

        Object[] finderArgs = new Object[] { proposalId, userId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDUSERID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

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
     * Returns all the proposal votes where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findByUserId(long userId)
        throws SystemException {
        return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal votes where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @return the range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findByUserId(long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal votes where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findByUserId(long userId, int start, int end,
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

        List<ProposalVote> list = (List<ProposalVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalVote proposalVote : list) {
                if ((userId != proposalVote.getUserId())) {
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

            query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalVoteModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                if (!pagination) {
                    list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalVote>(list);
                } else {
                    list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
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
     * Returns the first proposal vote in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByUserId_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = fetchByUserId_First(userId,
                orderByComparator);

        if (proposalVote != null) {
            return proposalVote;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalVoteException(msg.toString());
    }

    /**
     * Returns the first proposal vote in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByUserId_First(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ProposalVote> list = findByUserId(userId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal vote in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByUserId_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = fetchByUserId_Last(userId, orderByComparator);

        if (proposalVote != null) {
            return proposalVote;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalVoteException(msg.toString());
    }

    /**
     * Returns the last proposal vote in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByUserId_Last(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUserId(userId);

        if (count == 0) {
            return null;
        }

        List<ProposalVote> list = findByUserId(userId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal votes before and after the current proposal vote in the ordered set where userId = &#63;.
     *
     * @param proposalVotePK the primary key of the current proposal vote
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote[] findByUserId_PrevAndNext(
        ProposalVotePK proposalVotePK, long userId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = findByPrimaryKey(proposalVotePK);

        Session session = null;

        try {
            session = openSession();

            ProposalVote[] array = new ProposalVoteImpl[3];

            array[0] = getByUserId_PrevAndNext(session, proposalVote, userId,
                    orderByComparator, true);

            array[1] = proposalVote;

            array[2] = getByUserId_PrevAndNext(session, proposalVote, userId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalVote getByUserId_PrevAndNext(Session session,
        ProposalVote proposalVote, long userId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

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
            query.append(ProposalVoteModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalVote);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalVote> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal votes where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUserId(long userId) throws SystemException {
        for (ProposalVote proposalVote : findByUserId(userId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalVote);
        }
    }

    /**
     * Returns the number of proposal votes where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching proposal votes
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

            query.append(_SQL_COUNT_PROPOSALVOTE_WHERE);

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
     * Returns the proposal vote where contestPhaseId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalVoteException} if it could not be found.
     *
     * @param contestPhaseId the contest phase ID
     * @param userId the user ID
     * @return the matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByContestPhaseIdUserId(long contestPhaseId,
        long userId) throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = fetchByContestPhaseIdUserId(contestPhaseId,
                userId);

        if (proposalVote == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contestPhaseId=");
            msg.append(contestPhaseId);

            msg.append(", userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchProposalVoteException(msg.toString());
        }

        return proposalVote;
    }

    /**
     * Returns the proposal vote where contestPhaseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param contestPhaseId the contest phase ID
     * @param userId the user ID
     * @return the matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByContestPhaseIdUserId(long contestPhaseId,
        long userId) throws SystemException {
        return fetchByContestPhaseIdUserId(contestPhaseId, userId, true);
    }

    /**
     * Returns the proposal vote where contestPhaseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param contestPhaseId the contest phase ID
     * @param userId the user ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching proposal vote, or <code>null</code> if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByContestPhaseIdUserId(long contestPhaseId,
        long userId, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { contestPhaseId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID,
                    finderArgs, this);
        }

        if (result instanceof ProposalVote) {
            ProposalVote proposalVote = (ProposalVote) result;

            if ((contestPhaseId != proposalVote.getContestPhaseId()) ||
                    (userId != proposalVote.getUserId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTPHASEIDUSERID_CONTESTPHASEID_2);

            query.append(_FINDER_COLUMN_CONTESTPHASEIDUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestPhaseId);

                qPos.add(userId);

                List<ProposalVote> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ProposalVotePersistenceImpl.fetchByContestPhaseIdUserId(long, long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ProposalVote proposalVote = list.get(0);

                    result = proposalVote;

                    cacheResult(proposalVote);

                    if ((proposalVote.getContestPhaseId() != contestPhaseId) ||
                            (proposalVote.getUserId() != userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID,
                            finderArgs, proposalVote);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ProposalVote) result;
        }
    }

    /**
     * Removes the proposal vote where contestPhaseId = &#63; and userId = &#63; from the database.
     *
     * @param contestPhaseId the contest phase ID
     * @param userId the user ID
     * @return the proposal vote that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote removeByContestPhaseIdUserId(long contestPhaseId,
        long userId) throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = findByContestPhaseIdUserId(contestPhaseId,
                userId);

        return remove(proposalVote);
    }

    /**
     * Returns the number of proposal votes where contestPhaseId = &#63; and userId = &#63;.
     *
     * @param contestPhaseId the contest phase ID
     * @param userId the user ID
     * @return the number of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestPhaseIdUserId(long contestPhaseId, long userId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTPHASEIDUSERID;

        Object[] finderArgs = new Object[] { contestPhaseId, userId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTPHASEIDUSERID_CONTESTPHASEID_2);

            query.append(_FINDER_COLUMN_CONTESTPHASEIDUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestPhaseId);

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
     * Caches the proposal vote in the entity cache if it is enabled.
     *
     * @param proposalVote the proposal vote
     */
    @Override
    public void cacheResult(ProposalVote proposalVote) {
        EntityCacheUtil.putResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteImpl.class, proposalVote.getPrimaryKey(), proposalVote);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDUSERID,
            new Object[] { proposalVote.getProposalId(), proposalVote.getUserId() },
            proposalVote);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID,
            new Object[] {
                proposalVote.getContestPhaseId(), proposalVote.getUserId()
            }, proposalVote);

        proposalVote.resetOriginalValues();
    }

    /**
     * Caches the proposal votes in the entity cache if it is enabled.
     *
     * @param proposalVotes the proposal votes
     */
    @Override
    public void cacheResult(List<ProposalVote> proposalVotes) {
        for (ProposalVote proposalVote : proposalVotes) {
            if (EntityCacheUtil.getResult(
                        ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalVoteImpl.class, proposalVote.getPrimaryKey()) == null) {
                cacheResult(proposalVote);
            } else {
                proposalVote.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal votes.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalVoteImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalVoteImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal vote.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProposalVote proposalVote) {
        EntityCacheUtil.removeResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteImpl.class, proposalVote.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(proposalVote);
    }

    @Override
    public void clearCache(List<ProposalVote> proposalVotes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalVote proposalVote : proposalVotes) {
            EntityCacheUtil.removeResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
                ProposalVoteImpl.class, proposalVote.getPrimaryKey());

            clearUniqueFindersCache(proposalVote);
        }
    }

    protected void cacheUniqueFindersCache(ProposalVote proposalVote) {
        if (proposalVote.isNew()) {
            Object[] args = new Object[] {
                    proposalVote.getProposalId(), proposalVote.getUserId()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALIDUSERID,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDUSERID,
                args, proposalVote);

            args = new Object[] {
                    proposalVote.getContestPhaseId(), proposalVote.getUserId()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTPHASEIDUSERID,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID,
                args, proposalVote);
        } else {
            ProposalVoteModelImpl proposalVoteModelImpl = (ProposalVoteModelImpl) proposalVote;

            if ((proposalVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PROPOSALIDUSERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalVote.getProposalId(), proposalVote.getUserId()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALIDUSERID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDUSERID,
                    args, proposalVote);
            }

            if ((proposalVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalVote.getContestPhaseId(),
                        proposalVote.getUserId()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTPHASEIDUSERID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID,
                    args, proposalVote);
            }
        }
    }

    protected void clearUniqueFindersCache(ProposalVote proposalVote) {
        ProposalVoteModelImpl proposalVoteModelImpl = (ProposalVoteModelImpl) proposalVote;

        Object[] args = new Object[] {
                proposalVote.getProposalId(), proposalVote.getUserId()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDUSERID, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDUSERID, args);

        if ((proposalVoteModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_PROPOSALIDUSERID.getColumnBitmask()) != 0) {
            args = new Object[] {
                    proposalVoteModelImpl.getOriginalProposalId(),
                    proposalVoteModelImpl.getOriginalUserId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDUSERID,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDUSERID,
                args);
        }

        args = new Object[] {
                proposalVote.getContestPhaseId(), proposalVote.getUserId()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTPHASEIDUSERID,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID,
            args);

        if ((proposalVoteModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID.getColumnBitmask()) != 0) {
            args = new Object[] {
                    proposalVoteModelImpl.getOriginalContestPhaseId(),
                    proposalVoteModelImpl.getOriginalUserId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTPHASEIDUSERID,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTPHASEIDUSERID,
                args);
        }
    }

    /**
     * Creates a new proposal vote with the primary key. Does not add the proposal vote to the database.
     *
     * @param proposalVotePK the primary key for the new proposal vote
     * @return the new proposal vote
     */
    @Override
    public ProposalVote create(ProposalVotePK proposalVotePK) {
        ProposalVote proposalVote = new ProposalVoteImpl();

        proposalVote.setNew(true);
        proposalVote.setPrimaryKey(proposalVotePK);

        return proposalVote;
    }

    /**
     * Removes the proposal vote with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param proposalVotePK the primary key of the proposal vote
     * @return the proposal vote that was removed
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote remove(ProposalVotePK proposalVotePK)
        throws NoSuchProposalVoteException, SystemException {
        return remove((Serializable) proposalVotePK);
    }

    /**
     * Removes the proposal vote with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal vote
     * @return the proposal vote that was removed
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote remove(Serializable primaryKey)
        throws NoSuchProposalVoteException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalVote proposalVote = (ProposalVote) session.get(ProposalVoteImpl.class,
                    primaryKey);

            if (proposalVote == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalVote);
        } catch (NoSuchProposalVoteException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalVote removeImpl(ProposalVote proposalVote)
        throws SystemException {
        proposalVote = toUnwrappedModel(proposalVote);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposalVote)) {
                proposalVote = (ProposalVote) session.get(ProposalVoteImpl.class,
                        proposalVote.getPrimaryKeyObj());
            }

            if (proposalVote != null) {
                session.delete(proposalVote);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposalVote != null) {
            clearCache(proposalVote);
        }

        return proposalVote;
    }

    @Override
    public ProposalVote updateImpl(
        com.ext.portlet.model.ProposalVote proposalVote)
        throws SystemException {
        proposalVote = toUnwrappedModel(proposalVote);

        boolean isNew = proposalVote.isNew();

        ProposalVoteModelImpl proposalVoteModelImpl = (ProposalVoteModelImpl) proposalVote;

        Session session = null;

        try {
            session = openSession();

            if (proposalVote.isNew()) {
                session.save(proposalVote);

                proposalVote.setNew(false);
            } else {
                session.merge(proposalVote);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ProposalVoteModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((proposalVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalVoteModelImpl.getOriginalProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);

                args = new Object[] { proposalVoteModelImpl.getProposalId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);
            }

            if ((proposalVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalVoteModelImpl.getOriginalProposalId(),
                        proposalVoteModelImpl.getOriginalContestPhaseId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID,
                    args);

                args = new Object[] {
                        proposalVoteModelImpl.getProposalId(),
                        proposalVoteModelImpl.getContestPhaseId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID,
                    args);
            }

            if ((proposalVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalVoteModelImpl.getOriginalUserId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);

                args = new Object[] { proposalVoteModelImpl.getUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteImpl.class, proposalVote.getPrimaryKey(), proposalVote);

        clearUniqueFindersCache(proposalVote);
        cacheUniqueFindersCache(proposalVote);

        return proposalVote;
    }

    protected ProposalVote toUnwrappedModel(ProposalVote proposalVote) {
        if (proposalVote instanceof ProposalVoteImpl) {
            return proposalVote;
        }

        ProposalVoteImpl proposalVoteImpl = new ProposalVoteImpl();

        proposalVoteImpl.setNew(proposalVote.isNew());
        proposalVoteImpl.setPrimaryKey(proposalVote.getPrimaryKey());

        proposalVoteImpl.setProposalId(proposalVote.getProposalId());
        proposalVoteImpl.setContestPhaseId(proposalVote.getContestPhaseId());
        proposalVoteImpl.setUserId(proposalVote.getUserId());
        proposalVoteImpl.setCreateDate(proposalVote.getCreateDate());
        proposalVoteImpl.setIsValid(proposalVote.isIsValid());
        proposalVoteImpl.setConfirmationEmailSendDate(proposalVote.getConfirmationEmailSendDate());
        proposalVoteImpl.setConfirmationToken(proposalVote.getConfirmationToken());

        return proposalVoteImpl;
    }

    /**
     * Returns the proposal vote with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal vote
     * @return the proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = fetchByPrimaryKey(primaryKey);

        if (proposalVote == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposalVote;
    }

    /**
     * Returns the proposal vote with the primary key or throws a {@link com.ext.portlet.NoSuchProposalVoteException} if it could not be found.
     *
     * @param proposalVotePK the primary key of the proposal vote
     * @return the proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByPrimaryKey(ProposalVotePK proposalVotePK)
        throws NoSuchProposalVoteException, SystemException {
        return findByPrimaryKey((Serializable) proposalVotePK);
    }

    /**
     * Returns the proposal vote with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal vote
     * @return the proposal vote, or <code>null</code> if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProposalVote proposalVote = (ProposalVote) EntityCacheUtil.getResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
                ProposalVoteImpl.class, primaryKey);

        if (proposalVote == _nullProposalVote) {
            return null;
        }

        if (proposalVote == null) {
            Session session = null;

            try {
                session = openSession();

                proposalVote = (ProposalVote) session.get(ProposalVoteImpl.class,
                        primaryKey);

                if (proposalVote != null) {
                    cacheResult(proposalVote);
                } else {
                    EntityCacheUtil.putResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalVoteImpl.class, primaryKey, _nullProposalVote);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalVoteImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposalVote;
    }

    /**
     * Returns the proposal vote with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param proposalVotePK the primary key of the proposal vote
     * @return the proposal vote, or <code>null</code> if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByPrimaryKey(ProposalVotePK proposalVotePK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) proposalVotePK);
    }

    /**
     * Returns all the proposal votes.
     *
     * @return the proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal votes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @return the range of proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal votes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalVote> findAll(int start, int end,
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

        List<ProposalVote> list = (List<ProposalVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALVOTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALVOTE;

                if (pagination) {
                    sql = sql.concat(ProposalVoteModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalVote>(list);
                } else {
                    list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
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
     * Removes all the proposal votes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProposalVote proposalVote : findAll()) {
            remove(proposalVote);
        }
    }

    /**
     * Returns the number of proposal votes.
     *
     * @return the number of proposal votes
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSALVOTE);

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
     * Initializes the proposal vote persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalVote")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalVote>> listenersList = new ArrayList<ModelListener<ProposalVote>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalVote>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalVoteImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
