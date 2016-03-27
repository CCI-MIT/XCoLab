package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalMoveHistoryException;
import com.ext.portlet.model.ProposalMoveHistory;
import com.ext.portlet.model.impl.ProposalMoveHistoryImpl;
import com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl;
import com.ext.portlet.service.persistence.ProposalMoveHistoryPersistence;

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
 * The persistence implementation for the proposal move history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalMoveHistoryPersistence
 * @see ProposalMoveHistoryUtil
 * @generated
 */
public class ProposalMoveHistoryPersistenceImpl extends BasePersistenceImpl<ProposalMoveHistory>
    implements ProposalMoveHistoryPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalMoveHistoryUtil} to access the proposal move history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalMoveHistoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SOURCEPROPOSALID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySourceProposalId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPROPOSALID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findBySourceProposalId", new String[] { Long.class.getName() },
            ProposalMoveHistoryModelImpl.SOURCEPROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SOURCEPROPOSALID = new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBySourceProposalId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_SOURCEPROPOSALID_SOURCEPROPOSALID_2 =
        "proposalMoveHistory.sourceProposalId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SOURCECONTESTID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySourceContestId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCECONTESTID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySourceContestId",
            new String[] { Long.class.getName() },
            ProposalMoveHistoryModelImpl.SOURCECONTESTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SOURCECONTESTID = new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBySourceContestId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_SOURCECONTESTID_SOURCECONTESTID_2 =
        "proposalMoveHistory.sourceContestId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SOURCEPHASEID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySourcePhaseId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPHASEID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySourcePhaseId",
            new String[] { Long.class.getName() },
            ProposalMoveHistoryModelImpl.SOURCEPHASEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SOURCEPHASEID = new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySourcePhaseId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_SOURCEPHASEID_SOURCEPHASEID_2 = "proposalMoveHistory.sourcePhaseId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETPROPOSALID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTargetProposalId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPROPOSALID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByTargetProposalId", new String[] { Long.class.getName() },
            ProposalMoveHistoryModelImpl.TARGETPROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TARGETPROPOSALID = new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByTargetProposalId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_TARGETPROPOSALID_TARGETPROPOSALID_2 =
        "proposalMoveHistory.targetProposalId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETCONTESTID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTargetContestId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETCONTESTID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTargetContestId",
            new String[] { Long.class.getName() },
            ProposalMoveHistoryModelImpl.TARGETCONTESTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TARGETCONTESTID = new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByTargetContestId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_TARGETCONTESTID_TARGETCONTESTID_2 =
        "proposalMoveHistory.targetContestId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETPHASEID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTargetPhaseId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPHASEID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTargetPhaseId",
            new String[] { Long.class.getName() },
            ProposalMoveHistoryModelImpl.TARGETPHASEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TARGETPHASEID = new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTargetPhaseId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_TARGETPHASEID_TARGETPHASEID_2 = "proposalMoveHistory.targetPhaseId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SOURCEPROPOSALIDCONTESTID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findBySourceProposalIdContestId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPROPOSALIDCONTESTID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findBySourceProposalIdContestId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ProposalMoveHistoryModelImpl.SOURCEPROPOSALID_COLUMN_BITMASK |
            ProposalMoveHistoryModelImpl.SOURCECONTESTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SOURCEPROPOSALIDCONTESTID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBySourceProposalIdContestId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_SOURCEPROPOSALIDCONTESTID_SOURCEPROPOSALID_2 =
        "proposalMoveHistory.sourceProposalId = ? AND ";
    private static final String _FINDER_COLUMN_SOURCEPROPOSALIDCONTESTID_SOURCECONTESTID_2 =
        "proposalMoveHistory.sourceContestId = ?";
    public static final FinderPath FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByTargetProposalIdContestId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ProposalMoveHistoryModelImpl.TARGETPROPOSALID_COLUMN_BITMASK |
            ProposalMoveHistoryModelImpl.TARGETCONTESTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TARGETPROPOSALIDCONTESTID =
        new FinderPath(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByTargetProposalIdContestId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_TARGETPROPOSALIDCONTESTID_TARGETPROPOSALID_2 =
        "proposalMoveHistory.targetProposalId = ? AND ";
    private static final String _FINDER_COLUMN_TARGETPROPOSALIDCONTESTID_TARGETCONTESTID_2 =
        "proposalMoveHistory.targetContestId = ?";
    private static final String _SQL_SELECT_PROPOSALMOVEHISTORY = "SELECT proposalMoveHistory FROM ProposalMoveHistory proposalMoveHistory";
    private static final String _SQL_SELECT_PROPOSALMOVEHISTORY_WHERE = "SELECT proposalMoveHistory FROM ProposalMoveHistory proposalMoveHistory WHERE ";
    private static final String _SQL_COUNT_PROPOSALMOVEHISTORY = "SELECT COUNT(proposalMoveHistory) FROM ProposalMoveHistory proposalMoveHistory";
    private static final String _SQL_COUNT_PROPOSALMOVEHISTORY_WHERE = "SELECT COUNT(proposalMoveHistory) FROM ProposalMoveHistory proposalMoveHistory WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalMoveHistory.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalMoveHistory exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProposalMoveHistory exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalMoveHistoryPersistenceImpl.class);
    private static ProposalMoveHistory _nullProposalMoveHistory = new ProposalMoveHistoryImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalMoveHistory> toCacheModel() {
                return _nullProposalMoveHistoryCacheModel;
            }
        };

    private static CacheModel<ProposalMoveHistory> _nullProposalMoveHistoryCacheModel =
        new CacheModel<ProposalMoveHistory>() {
            @Override
            public ProposalMoveHistory toEntityModel() {
                return _nullProposalMoveHistory;
            }
        };

    public ProposalMoveHistoryPersistenceImpl() {
        setModelClass(ProposalMoveHistory.class);
    }

    /**
     * Returns all the proposal move histories where sourceProposalId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @return the matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourceProposalId(
        long sourceProposalId) throws SystemException {
        return findBySourceProposalId(sourceProposalId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal move histories where sourceProposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param sourceProposalId the source proposal ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @return the range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourceProposalId(
        long sourceProposalId, int start, int end) throws SystemException {
        return findBySourceProposalId(sourceProposalId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal move histories where sourceProposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param sourceProposalId the source proposal ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourceProposalId(
        long sourceProposalId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPROPOSALID;
            finderArgs = new Object[] { sourceProposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SOURCEPROPOSALID;
            finderArgs = new Object[] {
                    sourceProposalId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalMoveHistory> list = (List<ProposalMoveHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalMoveHistory proposalMoveHistory : list) {
                if ((sourceProposalId != proposalMoveHistory.getSourceProposalId())) {
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

            query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_SOURCEPROPOSALID_SOURCEPROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(sourceProposalId);

                if (!pagination) {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalMoveHistory>(list);
                } else {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
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
     * Returns the first proposal move history in the ordered set where sourceProposalId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findBySourceProposalId_First(
        long sourceProposalId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchBySourceProposalId_First(sourceProposalId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("sourceProposalId=");
        msg.append(sourceProposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the first proposal move history in the ordered set where sourceProposalId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchBySourceProposalId_First(
        long sourceProposalId, OrderByComparator orderByComparator)
        throws SystemException {
        List<ProposalMoveHistory> list = findBySourceProposalId(sourceProposalId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal move history in the ordered set where sourceProposalId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findBySourceProposalId_Last(
        long sourceProposalId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchBySourceProposalId_Last(sourceProposalId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("sourceProposalId=");
        msg.append(sourceProposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the last proposal move history in the ordered set where sourceProposalId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchBySourceProposalId_Last(
        long sourceProposalId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countBySourceProposalId(sourceProposalId);

        if (count == 0) {
            return null;
        }

        List<ProposalMoveHistory> list = findBySourceProposalId(sourceProposalId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourceProposalId = &#63;.
     *
     * @param id_ the primary key of the current proposal move history
     * @param sourceProposalId the source proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory[] findBySourceProposalId_PrevAndNext(long id_,
        long sourceProposalId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            ProposalMoveHistory[] array = new ProposalMoveHistoryImpl[3];

            array[0] = getBySourceProposalId_PrevAndNext(session,
                    proposalMoveHistory, sourceProposalId, orderByComparator,
                    true);

            array[1] = proposalMoveHistory;

            array[2] = getBySourceProposalId_PrevAndNext(session,
                    proposalMoveHistory, sourceProposalId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalMoveHistory getBySourceProposalId_PrevAndNext(
        Session session, ProposalMoveHistory proposalMoveHistory,
        long sourceProposalId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

        query.append(_FINDER_COLUMN_SOURCEPROPOSALID_SOURCEPROPOSALID_2);

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
            query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(sourceProposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalMoveHistory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalMoveHistory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal move histories where sourceProposalId = &#63; from the database.
     *
     * @param sourceProposalId the source proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySourceProposalId(long sourceProposalId)
        throws SystemException {
        for (ProposalMoveHistory proposalMoveHistory : findBySourceProposalId(
                sourceProposalId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalMoveHistory);
        }
    }

    /**
     * Returns the number of proposal move histories where sourceProposalId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @return the number of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySourceProposalId(long sourceProposalId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SOURCEPROPOSALID;

        Object[] finderArgs = new Object[] { sourceProposalId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_SOURCEPROPOSALID_SOURCEPROPOSALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(sourceProposalId);

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
     * Returns all the proposal move histories where sourceContestId = &#63;.
     *
     * @param sourceContestId the source contest ID
     * @return the matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourceContestId(long sourceContestId)
        throws SystemException {
        return findBySourceContestId(sourceContestId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal move histories where sourceContestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param sourceContestId the source contest ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @return the range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourceContestId(
        long sourceContestId, int start, int end) throws SystemException {
        return findBySourceContestId(sourceContestId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal move histories where sourceContestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param sourceContestId the source contest ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourceContestId(
        long sourceContestId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCECONTESTID;
            finderArgs = new Object[] { sourceContestId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SOURCECONTESTID;
            finderArgs = new Object[] {
                    sourceContestId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalMoveHistory> list = (List<ProposalMoveHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalMoveHistory proposalMoveHistory : list) {
                if ((sourceContestId != proposalMoveHistory.getSourceContestId())) {
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

            query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_SOURCECONTESTID_SOURCECONTESTID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(sourceContestId);

                if (!pagination) {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalMoveHistory>(list);
                } else {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
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
     * Returns the first proposal move history in the ordered set where sourceContestId = &#63;.
     *
     * @param sourceContestId the source contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findBySourceContestId_First(
        long sourceContestId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchBySourceContestId_First(sourceContestId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("sourceContestId=");
        msg.append(sourceContestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the first proposal move history in the ordered set where sourceContestId = &#63;.
     *
     * @param sourceContestId the source contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchBySourceContestId_First(
        long sourceContestId, OrderByComparator orderByComparator)
        throws SystemException {
        List<ProposalMoveHistory> list = findBySourceContestId(sourceContestId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal move history in the ordered set where sourceContestId = &#63;.
     *
     * @param sourceContestId the source contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findBySourceContestId_Last(
        long sourceContestId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchBySourceContestId_Last(sourceContestId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("sourceContestId=");
        msg.append(sourceContestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the last proposal move history in the ordered set where sourceContestId = &#63;.
     *
     * @param sourceContestId the source contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchBySourceContestId_Last(
        long sourceContestId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countBySourceContestId(sourceContestId);

        if (count == 0) {
            return null;
        }

        List<ProposalMoveHistory> list = findBySourceContestId(sourceContestId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourceContestId = &#63;.
     *
     * @param id_ the primary key of the current proposal move history
     * @param sourceContestId the source contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory[] findBySourceContestId_PrevAndNext(long id_,
        long sourceContestId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            ProposalMoveHistory[] array = new ProposalMoveHistoryImpl[3];

            array[0] = getBySourceContestId_PrevAndNext(session,
                    proposalMoveHistory, sourceContestId, orderByComparator,
                    true);

            array[1] = proposalMoveHistory;

            array[2] = getBySourceContestId_PrevAndNext(session,
                    proposalMoveHistory, sourceContestId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalMoveHistory getBySourceContestId_PrevAndNext(
        Session session, ProposalMoveHistory proposalMoveHistory,
        long sourceContestId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

        query.append(_FINDER_COLUMN_SOURCECONTESTID_SOURCECONTESTID_2);

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
            query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(sourceContestId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalMoveHistory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalMoveHistory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal move histories where sourceContestId = &#63; from the database.
     *
     * @param sourceContestId the source contest ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySourceContestId(long sourceContestId)
        throws SystemException {
        for (ProposalMoveHistory proposalMoveHistory : findBySourceContestId(
                sourceContestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalMoveHistory);
        }
    }

    /**
     * Returns the number of proposal move histories where sourceContestId = &#63;.
     *
     * @param sourceContestId the source contest ID
     * @return the number of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySourceContestId(long sourceContestId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SOURCECONTESTID;

        Object[] finderArgs = new Object[] { sourceContestId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_SOURCECONTESTID_SOURCECONTESTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(sourceContestId);

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
     * Returns all the proposal move histories where sourcePhaseId = &#63;.
     *
     * @param sourcePhaseId the source phase ID
     * @return the matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourcePhaseId(long sourcePhaseId)
        throws SystemException {
        return findBySourcePhaseId(sourcePhaseId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal move histories where sourcePhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param sourcePhaseId the source phase ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @return the range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourcePhaseId(long sourcePhaseId,
        int start, int end) throws SystemException {
        return findBySourcePhaseId(sourcePhaseId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal move histories where sourcePhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param sourcePhaseId the source phase ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourcePhaseId(long sourcePhaseId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPHASEID;
            finderArgs = new Object[] { sourcePhaseId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SOURCEPHASEID;
            finderArgs = new Object[] {
                    sourcePhaseId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalMoveHistory> list = (List<ProposalMoveHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalMoveHistory proposalMoveHistory : list) {
                if ((sourcePhaseId != proposalMoveHistory.getSourcePhaseId())) {
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

            query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_SOURCEPHASEID_SOURCEPHASEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(sourcePhaseId);

                if (!pagination) {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalMoveHistory>(list);
                } else {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
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
     * Returns the first proposal move history in the ordered set where sourcePhaseId = &#63;.
     *
     * @param sourcePhaseId the source phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findBySourcePhaseId_First(long sourcePhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchBySourcePhaseId_First(sourcePhaseId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("sourcePhaseId=");
        msg.append(sourcePhaseId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the first proposal move history in the ordered set where sourcePhaseId = &#63;.
     *
     * @param sourcePhaseId the source phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchBySourcePhaseId_First(long sourcePhaseId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ProposalMoveHistory> list = findBySourcePhaseId(sourcePhaseId, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal move history in the ordered set where sourcePhaseId = &#63;.
     *
     * @param sourcePhaseId the source phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findBySourcePhaseId_Last(long sourcePhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchBySourcePhaseId_Last(sourcePhaseId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("sourcePhaseId=");
        msg.append(sourcePhaseId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the last proposal move history in the ordered set where sourcePhaseId = &#63;.
     *
     * @param sourcePhaseId the source phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchBySourcePhaseId_Last(long sourcePhaseId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySourcePhaseId(sourcePhaseId);

        if (count == 0) {
            return null;
        }

        List<ProposalMoveHistory> list = findBySourcePhaseId(sourcePhaseId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourcePhaseId = &#63;.
     *
     * @param id_ the primary key of the current proposal move history
     * @param sourcePhaseId the source phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory[] findBySourcePhaseId_PrevAndNext(long id_,
        long sourcePhaseId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            ProposalMoveHistory[] array = new ProposalMoveHistoryImpl[3];

            array[0] = getBySourcePhaseId_PrevAndNext(session,
                    proposalMoveHistory, sourcePhaseId, orderByComparator, true);

            array[1] = proposalMoveHistory;

            array[2] = getBySourcePhaseId_PrevAndNext(session,
                    proposalMoveHistory, sourcePhaseId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalMoveHistory getBySourcePhaseId_PrevAndNext(
        Session session, ProposalMoveHistory proposalMoveHistory,
        long sourcePhaseId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

        query.append(_FINDER_COLUMN_SOURCEPHASEID_SOURCEPHASEID_2);

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
            query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(sourcePhaseId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalMoveHistory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalMoveHistory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal move histories where sourcePhaseId = &#63; from the database.
     *
     * @param sourcePhaseId the source phase ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySourcePhaseId(long sourcePhaseId)
        throws SystemException {
        for (ProposalMoveHistory proposalMoveHistory : findBySourcePhaseId(
                sourcePhaseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalMoveHistory);
        }
    }

    /**
     * Returns the number of proposal move histories where sourcePhaseId = &#63;.
     *
     * @param sourcePhaseId the source phase ID
     * @return the number of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySourcePhaseId(long sourcePhaseId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SOURCEPHASEID;

        Object[] finderArgs = new Object[] { sourcePhaseId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_SOURCEPHASEID_SOURCEPHASEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(sourcePhaseId);

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
     * Returns all the proposal move histories where targetProposalId = &#63;.
     *
     * @param targetProposalId the target proposal ID
     * @return the matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findByTargetProposalId(
        long targetProposalId) throws SystemException {
        return findByTargetProposalId(targetProposalId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal move histories where targetProposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetProposalId the target proposal ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @return the range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findByTargetProposalId(
        long targetProposalId, int start, int end) throws SystemException {
        return findByTargetProposalId(targetProposalId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal move histories where targetProposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetProposalId the target proposal ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findByTargetProposalId(
        long targetProposalId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPROPOSALID;
            finderArgs = new Object[] { targetProposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETPROPOSALID;
            finderArgs = new Object[] {
                    targetProposalId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalMoveHistory> list = (List<ProposalMoveHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalMoveHistory proposalMoveHistory : list) {
                if ((targetProposalId != proposalMoveHistory.getTargetProposalId())) {
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

            query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_TARGETPROPOSALID_TARGETPROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetProposalId);

                if (!pagination) {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalMoveHistory>(list);
                } else {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
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
     * Returns the first proposal move history in the ordered set where targetProposalId = &#63;.
     *
     * @param targetProposalId the target proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findByTargetProposalId_First(
        long targetProposalId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchByTargetProposalId_First(targetProposalId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetProposalId=");
        msg.append(targetProposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the first proposal move history in the ordered set where targetProposalId = &#63;.
     *
     * @param targetProposalId the target proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchByTargetProposalId_First(
        long targetProposalId, OrderByComparator orderByComparator)
        throws SystemException {
        List<ProposalMoveHistory> list = findByTargetProposalId(targetProposalId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal move history in the ordered set where targetProposalId = &#63;.
     *
     * @param targetProposalId the target proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findByTargetProposalId_Last(
        long targetProposalId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchByTargetProposalId_Last(targetProposalId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetProposalId=");
        msg.append(targetProposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the last proposal move history in the ordered set where targetProposalId = &#63;.
     *
     * @param targetProposalId the target proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchByTargetProposalId_Last(
        long targetProposalId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByTargetProposalId(targetProposalId);

        if (count == 0) {
            return null;
        }

        List<ProposalMoveHistory> list = findByTargetProposalId(targetProposalId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal move histories before and after the current proposal move history in the ordered set where targetProposalId = &#63;.
     *
     * @param id_ the primary key of the current proposal move history
     * @param targetProposalId the target proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory[] findByTargetProposalId_PrevAndNext(long id_,
        long targetProposalId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            ProposalMoveHistory[] array = new ProposalMoveHistoryImpl[3];

            array[0] = getByTargetProposalId_PrevAndNext(session,
                    proposalMoveHistory, targetProposalId, orderByComparator,
                    true);

            array[1] = proposalMoveHistory;

            array[2] = getByTargetProposalId_PrevAndNext(session,
                    proposalMoveHistory, targetProposalId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalMoveHistory getByTargetProposalId_PrevAndNext(
        Session session, ProposalMoveHistory proposalMoveHistory,
        long targetProposalId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

        query.append(_FINDER_COLUMN_TARGETPROPOSALID_TARGETPROPOSALID_2);

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
            query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(targetProposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalMoveHistory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalMoveHistory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal move histories where targetProposalId = &#63; from the database.
     *
     * @param targetProposalId the target proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByTargetProposalId(long targetProposalId)
        throws SystemException {
        for (ProposalMoveHistory proposalMoveHistory : findByTargetProposalId(
                targetProposalId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalMoveHistory);
        }
    }

    /**
     * Returns the number of proposal move histories where targetProposalId = &#63;.
     *
     * @param targetProposalId the target proposal ID
     * @return the number of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTargetProposalId(long targetProposalId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TARGETPROPOSALID;

        Object[] finderArgs = new Object[] { targetProposalId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_TARGETPROPOSALID_TARGETPROPOSALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetProposalId);

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
     * Returns all the proposal move histories where targetContestId = &#63;.
     *
     * @param targetContestId the target contest ID
     * @return the matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findByTargetContestId(long targetContestId)
        throws SystemException {
        return findByTargetContestId(targetContestId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal move histories where targetContestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetContestId the target contest ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @return the range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findByTargetContestId(
        long targetContestId, int start, int end) throws SystemException {
        return findByTargetContestId(targetContestId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal move histories where targetContestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetContestId the target contest ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findByTargetContestId(
        long targetContestId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETCONTESTID;
            finderArgs = new Object[] { targetContestId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETCONTESTID;
            finderArgs = new Object[] {
                    targetContestId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalMoveHistory> list = (List<ProposalMoveHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalMoveHistory proposalMoveHistory : list) {
                if ((targetContestId != proposalMoveHistory.getTargetContestId())) {
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

            query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_TARGETCONTESTID_TARGETCONTESTID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetContestId);

                if (!pagination) {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalMoveHistory>(list);
                } else {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
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
     * Returns the first proposal move history in the ordered set where targetContestId = &#63;.
     *
     * @param targetContestId the target contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findByTargetContestId_First(
        long targetContestId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchByTargetContestId_First(targetContestId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetContestId=");
        msg.append(targetContestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the first proposal move history in the ordered set where targetContestId = &#63;.
     *
     * @param targetContestId the target contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchByTargetContestId_First(
        long targetContestId, OrderByComparator orderByComparator)
        throws SystemException {
        List<ProposalMoveHistory> list = findByTargetContestId(targetContestId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal move history in the ordered set where targetContestId = &#63;.
     *
     * @param targetContestId the target contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findByTargetContestId_Last(
        long targetContestId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchByTargetContestId_Last(targetContestId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetContestId=");
        msg.append(targetContestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the last proposal move history in the ordered set where targetContestId = &#63;.
     *
     * @param targetContestId the target contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchByTargetContestId_Last(
        long targetContestId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByTargetContestId(targetContestId);

        if (count == 0) {
            return null;
        }

        List<ProposalMoveHistory> list = findByTargetContestId(targetContestId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal move histories before and after the current proposal move history in the ordered set where targetContestId = &#63;.
     *
     * @param id_ the primary key of the current proposal move history
     * @param targetContestId the target contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory[] findByTargetContestId_PrevAndNext(long id_,
        long targetContestId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            ProposalMoveHistory[] array = new ProposalMoveHistoryImpl[3];

            array[0] = getByTargetContestId_PrevAndNext(session,
                    proposalMoveHistory, targetContestId, orderByComparator,
                    true);

            array[1] = proposalMoveHistory;

            array[2] = getByTargetContestId_PrevAndNext(session,
                    proposalMoveHistory, targetContestId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalMoveHistory getByTargetContestId_PrevAndNext(
        Session session, ProposalMoveHistory proposalMoveHistory,
        long targetContestId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

        query.append(_FINDER_COLUMN_TARGETCONTESTID_TARGETCONTESTID_2);

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
            query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(targetContestId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalMoveHistory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalMoveHistory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal move histories where targetContestId = &#63; from the database.
     *
     * @param targetContestId the target contest ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByTargetContestId(long targetContestId)
        throws SystemException {
        for (ProposalMoveHistory proposalMoveHistory : findByTargetContestId(
                targetContestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalMoveHistory);
        }
    }

    /**
     * Returns the number of proposal move histories where targetContestId = &#63;.
     *
     * @param targetContestId the target contest ID
     * @return the number of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTargetContestId(long targetContestId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TARGETCONTESTID;

        Object[] finderArgs = new Object[] { targetContestId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_TARGETCONTESTID_TARGETCONTESTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetContestId);

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
     * Returns all the proposal move histories where targetPhaseId = &#63;.
     *
     * @param targetPhaseId the target phase ID
     * @return the matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findByTargetPhaseId(long targetPhaseId)
        throws SystemException {
        return findByTargetPhaseId(targetPhaseId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal move histories where targetPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetPhaseId the target phase ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @return the range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findByTargetPhaseId(long targetPhaseId,
        int start, int end) throws SystemException {
        return findByTargetPhaseId(targetPhaseId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal move histories where targetPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetPhaseId the target phase ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findByTargetPhaseId(long targetPhaseId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPHASEID;
            finderArgs = new Object[] { targetPhaseId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETPHASEID;
            finderArgs = new Object[] {
                    targetPhaseId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalMoveHistory> list = (List<ProposalMoveHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalMoveHistory proposalMoveHistory : list) {
                if ((targetPhaseId != proposalMoveHistory.getTargetPhaseId())) {
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

            query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_TARGETPHASEID_TARGETPHASEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetPhaseId);

                if (!pagination) {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalMoveHistory>(list);
                } else {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
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
     * Returns the first proposal move history in the ordered set where targetPhaseId = &#63;.
     *
     * @param targetPhaseId the target phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findByTargetPhaseId_First(long targetPhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchByTargetPhaseId_First(targetPhaseId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetPhaseId=");
        msg.append(targetPhaseId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the first proposal move history in the ordered set where targetPhaseId = &#63;.
     *
     * @param targetPhaseId the target phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchByTargetPhaseId_First(long targetPhaseId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ProposalMoveHistory> list = findByTargetPhaseId(targetPhaseId, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal move history in the ordered set where targetPhaseId = &#63;.
     *
     * @param targetPhaseId the target phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findByTargetPhaseId_Last(long targetPhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchByTargetPhaseId_Last(targetPhaseId,
                orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetPhaseId=");
        msg.append(targetPhaseId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the last proposal move history in the ordered set where targetPhaseId = &#63;.
     *
     * @param targetPhaseId the target phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchByTargetPhaseId_Last(long targetPhaseId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByTargetPhaseId(targetPhaseId);

        if (count == 0) {
            return null;
        }

        List<ProposalMoveHistory> list = findByTargetPhaseId(targetPhaseId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal move histories before and after the current proposal move history in the ordered set where targetPhaseId = &#63;.
     *
     * @param id_ the primary key of the current proposal move history
     * @param targetPhaseId the target phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory[] findByTargetPhaseId_PrevAndNext(long id_,
        long targetPhaseId, OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            ProposalMoveHistory[] array = new ProposalMoveHistoryImpl[3];

            array[0] = getByTargetPhaseId_PrevAndNext(session,
                    proposalMoveHistory, targetPhaseId, orderByComparator, true);

            array[1] = proposalMoveHistory;

            array[2] = getByTargetPhaseId_PrevAndNext(session,
                    proposalMoveHistory, targetPhaseId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalMoveHistory getByTargetPhaseId_PrevAndNext(
        Session session, ProposalMoveHistory proposalMoveHistory,
        long targetPhaseId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

        query.append(_FINDER_COLUMN_TARGETPHASEID_TARGETPHASEID_2);

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
            query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(targetPhaseId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalMoveHistory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalMoveHistory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal move histories where targetPhaseId = &#63; from the database.
     *
     * @param targetPhaseId the target phase ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByTargetPhaseId(long targetPhaseId)
        throws SystemException {
        for (ProposalMoveHistory proposalMoveHistory : findByTargetPhaseId(
                targetPhaseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(proposalMoveHistory);
        }
    }

    /**
     * Returns the number of proposal move histories where targetPhaseId = &#63;.
     *
     * @param targetPhaseId the target phase ID
     * @return the number of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTargetPhaseId(long targetPhaseId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TARGETPHASEID;

        Object[] finderArgs = new Object[] { targetPhaseId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_TARGETPHASEID_TARGETPHASEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetPhaseId);

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
     * Returns all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @param sourceContestId the source contest ID
     * @return the matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourceProposalIdContestId(
        long sourceProposalId, long sourceContestId) throws SystemException {
        return findBySourceProposalIdContestId(sourceProposalId,
            sourceContestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param sourceProposalId the source proposal ID
     * @param sourceContestId the source contest ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @return the range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourceProposalIdContestId(
        long sourceProposalId, long sourceContestId, int start, int end)
        throws SystemException {
        return findBySourceProposalIdContestId(sourceProposalId,
            sourceContestId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param sourceProposalId the source proposal ID
     * @param sourceContestId the source contest ID
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findBySourceProposalIdContestId(
        long sourceProposalId, long sourceContestId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPROPOSALIDCONTESTID;
            finderArgs = new Object[] { sourceProposalId, sourceContestId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SOURCEPROPOSALIDCONTESTID;
            finderArgs = new Object[] {
                    sourceProposalId, sourceContestId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalMoveHistory> list = (List<ProposalMoveHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ProposalMoveHistory proposalMoveHistory : list) {
                if ((sourceProposalId != proposalMoveHistory.getSourceProposalId()) ||
                        (sourceContestId != proposalMoveHistory.getSourceContestId())) {
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

            query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_SOURCEPROPOSALIDCONTESTID_SOURCEPROPOSALID_2);

            query.append(_FINDER_COLUMN_SOURCEPROPOSALIDCONTESTID_SOURCECONTESTID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(sourceProposalId);

                qPos.add(sourceContestId);

                if (!pagination) {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalMoveHistory>(list);
                } else {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
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
     * Returns the first proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @param sourceContestId the source contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findBySourceProposalIdContestId_First(
        long sourceProposalId, long sourceContestId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchBySourceProposalIdContestId_First(sourceProposalId,
                sourceContestId, orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("sourceProposalId=");
        msg.append(sourceProposalId);

        msg.append(", sourceContestId=");
        msg.append(sourceContestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the first proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @param sourceContestId the source contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchBySourceProposalIdContestId_First(
        long sourceProposalId, long sourceContestId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ProposalMoveHistory> list = findBySourceProposalIdContestId(sourceProposalId,
                sourceContestId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @param sourceContestId the source contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findBySourceProposalIdContestId_Last(
        long sourceProposalId, long sourceContestId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchBySourceProposalIdContestId_Last(sourceProposalId,
                sourceContestId, orderByComparator);

        if (proposalMoveHistory != null) {
            return proposalMoveHistory;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("sourceProposalId=");
        msg.append(sourceProposalId);

        msg.append(", sourceContestId=");
        msg.append(sourceContestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchProposalMoveHistoryException(msg.toString());
    }

    /**
     * Returns the last proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @param sourceContestId the source contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchBySourceProposalIdContestId_Last(
        long sourceProposalId, long sourceContestId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySourceProposalIdContestId(sourceProposalId,
                sourceContestId);

        if (count == 0) {
            return null;
        }

        List<ProposalMoveHistory> list = findBySourceProposalIdContestId(sourceProposalId,
                sourceContestId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
     *
     * @param id_ the primary key of the current proposal move history
     * @param sourceProposalId the source proposal ID
     * @param sourceContestId the source contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory[] findBySourceProposalIdContestId_PrevAndNext(
        long id_, long sourceProposalId, long sourceContestId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = findByPrimaryKey(id_);

        Session session = null;

        try {
            session = openSession();

            ProposalMoveHistory[] array = new ProposalMoveHistoryImpl[3];

            array[0] = getBySourceProposalIdContestId_PrevAndNext(session,
                    proposalMoveHistory, sourceProposalId, sourceContestId,
                    orderByComparator, true);

            array[1] = proposalMoveHistory;

            array[2] = getBySourceProposalIdContestId_PrevAndNext(session,
                    proposalMoveHistory, sourceProposalId, sourceContestId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalMoveHistory getBySourceProposalIdContestId_PrevAndNext(
        Session session, ProposalMoveHistory proposalMoveHistory,
        long sourceProposalId, long sourceContestId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

        query.append(_FINDER_COLUMN_SOURCEPROPOSALIDCONTESTID_SOURCEPROPOSALID_2);

        query.append(_FINDER_COLUMN_SOURCEPROPOSALIDCONTESTID_SOURCECONTESTID_2);

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
            query.append(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(sourceProposalId);

        qPos.add(sourceContestId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalMoveHistory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalMoveHistory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63; from the database.
     *
     * @param sourceProposalId the source proposal ID
     * @param sourceContestId the source contest ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySourceProposalIdContestId(long sourceProposalId,
        long sourceContestId) throws SystemException {
        for (ProposalMoveHistory proposalMoveHistory : findBySourceProposalIdContestId(
                sourceProposalId, sourceContestId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(proposalMoveHistory);
        }
    }

    /**
     * Returns the number of proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
     *
     * @param sourceProposalId the source proposal ID
     * @param sourceContestId the source contest ID
     * @return the number of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySourceProposalIdContestId(long sourceProposalId,
        long sourceContestId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SOURCEPROPOSALIDCONTESTID;

        Object[] finderArgs = new Object[] { sourceProposalId, sourceContestId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_SOURCEPROPOSALIDCONTESTID_SOURCEPROPOSALID_2);

            query.append(_FINDER_COLUMN_SOURCEPROPOSALIDCONTESTID_SOURCECONTESTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(sourceProposalId);

                qPos.add(sourceContestId);

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
     * Returns the proposal move history where targetProposalId = &#63; and targetContestId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalMoveHistoryException} if it could not be found.
     *
     * @param targetProposalId the target proposal ID
     * @param targetContestId the target contest ID
     * @return the matching proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findByTargetProposalIdContestId(
        long targetProposalId, long targetContestId)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchByTargetProposalIdContestId(targetProposalId,
                targetContestId);

        if (proposalMoveHistory == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("targetProposalId=");
            msg.append(targetProposalId);

            msg.append(", targetContestId=");
            msg.append(targetContestId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchProposalMoveHistoryException(msg.toString());
        }

        return proposalMoveHistory;
    }

    /**
     * Returns the proposal move history where targetProposalId = &#63; and targetContestId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param targetProposalId the target proposal ID
     * @param targetContestId the target contest ID
     * @return the matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchByTargetProposalIdContestId(
        long targetProposalId, long targetContestId) throws SystemException {
        return fetchByTargetProposalIdContestId(targetProposalId,
            targetContestId, true);
    }

    /**
     * Returns the proposal move history where targetProposalId = &#63; and targetContestId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param targetProposalId the target proposal ID
     * @param targetContestId the target contest ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchByTargetProposalIdContestId(
        long targetProposalId, long targetContestId, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { targetProposalId, targetContestId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID,
                    finderArgs, this);
        }

        if (result instanceof ProposalMoveHistory) {
            ProposalMoveHistory proposalMoveHistory = (ProposalMoveHistory) result;

            if ((targetProposalId != proposalMoveHistory.getTargetProposalId()) ||
                    (targetContestId != proposalMoveHistory.getTargetContestId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_TARGETPROPOSALIDCONTESTID_TARGETPROPOSALID_2);

            query.append(_FINDER_COLUMN_TARGETPROPOSALIDCONTESTID_TARGETCONTESTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetProposalId);

                qPos.add(targetContestId);

                List<ProposalMoveHistory> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ProposalMoveHistoryPersistenceImpl.fetchByTargetProposalIdContestId(long, long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ProposalMoveHistory proposalMoveHistory = list.get(0);

                    result = proposalMoveHistory;

                    cacheResult(proposalMoveHistory);

                    if ((proposalMoveHistory.getTargetProposalId() != targetProposalId) ||
                            (proposalMoveHistory.getTargetContestId() != targetContestId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID,
                            finderArgs, proposalMoveHistory);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ProposalMoveHistory) result;
        }
    }

    /**
     * Removes the proposal move history where targetProposalId = &#63; and targetContestId = &#63; from the database.
     *
     * @param targetProposalId the target proposal ID
     * @param targetContestId the target contest ID
     * @return the proposal move history that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory removeByTargetProposalIdContestId(
        long targetProposalId, long targetContestId)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = findByTargetProposalIdContestId(targetProposalId,
                targetContestId);

        return remove(proposalMoveHistory);
    }

    /**
     * Returns the number of proposal move histories where targetProposalId = &#63; and targetContestId = &#63;.
     *
     * @param targetProposalId the target proposal ID
     * @param targetContestId the target contest ID
     * @return the number of matching proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTargetProposalIdContestId(long targetProposalId,
        long targetContestId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TARGETPROPOSALIDCONTESTID;

        Object[] finderArgs = new Object[] { targetProposalId, targetContestId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALMOVEHISTORY_WHERE);

            query.append(_FINDER_COLUMN_TARGETPROPOSALIDCONTESTID_TARGETPROPOSALID_2);

            query.append(_FINDER_COLUMN_TARGETPROPOSALIDCONTESTID_TARGETCONTESTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetProposalId);

                qPos.add(targetContestId);

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
     * Caches the proposal move history in the entity cache if it is enabled.
     *
     * @param proposalMoveHistory the proposal move history
     */
    @Override
    public void cacheResult(ProposalMoveHistory proposalMoveHistory) {
        EntityCacheUtil.putResult(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class, proposalMoveHistory.getPrimaryKey(),
            proposalMoveHistory);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID,
            new Object[] {
                proposalMoveHistory.getTargetProposalId(),
                proposalMoveHistory.getTargetContestId()
            }, proposalMoveHistory);

        proposalMoveHistory.resetOriginalValues();
    }

    /**
     * Caches the proposal move histories in the entity cache if it is enabled.
     *
     * @param proposalMoveHistories the proposal move histories
     */
    @Override
    public void cacheResult(List<ProposalMoveHistory> proposalMoveHistories) {
        for (ProposalMoveHistory proposalMoveHistory : proposalMoveHistories) {
            if (EntityCacheUtil.getResult(
                        ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalMoveHistoryImpl.class,
                        proposalMoveHistory.getPrimaryKey()) == null) {
                cacheResult(proposalMoveHistory);
            } else {
                proposalMoveHistory.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal move histories.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalMoveHistoryImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalMoveHistoryImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal move history.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProposalMoveHistory proposalMoveHistory) {
        EntityCacheUtil.removeResult(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class, proposalMoveHistory.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(proposalMoveHistory);
    }

    @Override
    public void clearCache(List<ProposalMoveHistory> proposalMoveHistories) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalMoveHistory proposalMoveHistory : proposalMoveHistories) {
            EntityCacheUtil.removeResult(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
                ProposalMoveHistoryImpl.class,
                proposalMoveHistory.getPrimaryKey());

            clearUniqueFindersCache(proposalMoveHistory);
        }
    }

    protected void cacheUniqueFindersCache(
        ProposalMoveHistory proposalMoveHistory) {
        if (proposalMoveHistory.isNew()) {
            Object[] args = new Object[] {
                    proposalMoveHistory.getTargetProposalId(),
                    proposalMoveHistory.getTargetContestId()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TARGETPROPOSALIDCONTESTID,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID,
                args, proposalMoveHistory);
        } else {
            ProposalMoveHistoryModelImpl proposalMoveHistoryModelImpl = (ProposalMoveHistoryModelImpl) proposalMoveHistory;

            if ((proposalMoveHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalMoveHistory.getTargetProposalId(),
                        proposalMoveHistory.getTargetContestId()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TARGETPROPOSALIDCONTESTID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID,
                    args, proposalMoveHistory);
            }
        }
    }

    protected void clearUniqueFindersCache(
        ProposalMoveHistory proposalMoveHistory) {
        ProposalMoveHistoryModelImpl proposalMoveHistoryModelImpl = (ProposalMoveHistoryModelImpl) proposalMoveHistory;

        Object[] args = new Object[] {
                proposalMoveHistory.getTargetProposalId(),
                proposalMoveHistory.getTargetContestId()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETPROPOSALIDCONTESTID,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID,
            args);

        if ((proposalMoveHistoryModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID.getColumnBitmask()) != 0) {
            args = new Object[] {
                    proposalMoveHistoryModelImpl.getOriginalTargetProposalId(),
                    proposalMoveHistoryModelImpl.getOriginalTargetContestId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETPROPOSALIDCONTESTID,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_TARGETPROPOSALIDCONTESTID,
                args);
        }
    }

    /**
     * Creates a new proposal move history with the primary key. Does not add the proposal move history to the database.
     *
     * @param id_ the primary key for the new proposal move history
     * @return the new proposal move history
     */
    @Override
    public ProposalMoveHistory create(long id_) {
        ProposalMoveHistory proposalMoveHistory = new ProposalMoveHistoryImpl();

        proposalMoveHistory.setNew(true);
        proposalMoveHistory.setPrimaryKey(id_);

        return proposalMoveHistory;
    }

    /**
     * Removes the proposal move history with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id_ the primary key of the proposal move history
     * @return the proposal move history that was removed
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory remove(long id_)
        throws NoSuchProposalMoveHistoryException, SystemException {
        return remove((Serializable) id_);
    }

    /**
     * Removes the proposal move history with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal move history
     * @return the proposal move history that was removed
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory remove(Serializable primaryKey)
        throws NoSuchProposalMoveHistoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalMoveHistory proposalMoveHistory = (ProposalMoveHistory) session.get(ProposalMoveHistoryImpl.class,
                    primaryKey);

            if (proposalMoveHistory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalMoveHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalMoveHistory);
        } catch (NoSuchProposalMoveHistoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalMoveHistory removeImpl(
        ProposalMoveHistory proposalMoveHistory) throws SystemException {
        proposalMoveHistory = toUnwrappedModel(proposalMoveHistory);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposalMoveHistory)) {
                proposalMoveHistory = (ProposalMoveHistory) session.get(ProposalMoveHistoryImpl.class,
                        proposalMoveHistory.getPrimaryKeyObj());
            }

            if (proposalMoveHistory != null) {
                session.delete(proposalMoveHistory);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposalMoveHistory != null) {
            clearCache(proposalMoveHistory);
        }

        return proposalMoveHistory;
    }

    @Override
    public ProposalMoveHistory updateImpl(
        com.ext.portlet.model.ProposalMoveHistory proposalMoveHistory)
        throws SystemException {
        proposalMoveHistory = toUnwrappedModel(proposalMoveHistory);

        boolean isNew = proposalMoveHistory.isNew();

        ProposalMoveHistoryModelImpl proposalMoveHistoryModelImpl = (ProposalMoveHistoryModelImpl) proposalMoveHistory;

        Session session = null;

        try {
            session = openSession();

            if (proposalMoveHistory.isNew()) {
                session.save(proposalMoveHistory);

                proposalMoveHistory.setNew(false);
            } else {
                session.merge(proposalMoveHistory);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ProposalMoveHistoryModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((proposalMoveHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalMoveHistoryModelImpl.getOriginalSourceProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SOURCEPROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPROPOSALID,
                    args);

                args = new Object[] {
                        proposalMoveHistoryModelImpl.getSourceProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SOURCEPROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPROPOSALID,
                    args);
            }

            if ((proposalMoveHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCECONTESTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalMoveHistoryModelImpl.getOriginalSourceContestId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SOURCECONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCECONTESTID,
                    args);

                args = new Object[] {
                        proposalMoveHistoryModelImpl.getSourceContestId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SOURCECONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCECONTESTID,
                    args);
            }

            if ((proposalMoveHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPHASEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalMoveHistoryModelImpl.getOriginalSourcePhaseId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SOURCEPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPHASEID,
                    args);

                args = new Object[] {
                        proposalMoveHistoryModelImpl.getSourcePhaseId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SOURCEPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPHASEID,
                    args);
            }

            if ((proposalMoveHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalMoveHistoryModelImpl.getOriginalTargetProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETPROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPROPOSALID,
                    args);

                args = new Object[] {
                        proposalMoveHistoryModelImpl.getTargetProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETPROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPROPOSALID,
                    args);
            }

            if ((proposalMoveHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETCONTESTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalMoveHistoryModelImpl.getOriginalTargetContestId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETCONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETCONTESTID,
                    args);

                args = new Object[] {
                        proposalMoveHistoryModelImpl.getTargetContestId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETCONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETCONTESTID,
                    args);
            }

            if ((proposalMoveHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPHASEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalMoveHistoryModelImpl.getOriginalTargetPhaseId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPHASEID,
                    args);

                args = new Object[] {
                        proposalMoveHistoryModelImpl.getTargetPhaseId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPHASEID,
                    args);
            }

            if ((proposalMoveHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPROPOSALIDCONTESTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        proposalMoveHistoryModelImpl.getOriginalSourceProposalId(),
                        proposalMoveHistoryModelImpl.getOriginalSourceContestId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SOURCEPROPOSALIDCONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPROPOSALIDCONTESTID,
                    args);

                args = new Object[] {
                        proposalMoveHistoryModelImpl.getSourceProposalId(),
                        proposalMoveHistoryModelImpl.getSourceContestId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SOURCEPROPOSALIDCONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SOURCEPROPOSALIDCONTESTID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
            ProposalMoveHistoryImpl.class, proposalMoveHistory.getPrimaryKey(),
            proposalMoveHistory);

        clearUniqueFindersCache(proposalMoveHistory);
        cacheUniqueFindersCache(proposalMoveHistory);

        return proposalMoveHistory;
    }

    protected ProposalMoveHistory toUnwrappedModel(
        ProposalMoveHistory proposalMoveHistory) {
        if (proposalMoveHistory instanceof ProposalMoveHistoryImpl) {
            return proposalMoveHistory;
        }

        ProposalMoveHistoryImpl proposalMoveHistoryImpl = new ProposalMoveHistoryImpl();

        proposalMoveHistoryImpl.setNew(proposalMoveHistory.isNew());
        proposalMoveHistoryImpl.setPrimaryKey(proposalMoveHistory.getPrimaryKey());

        proposalMoveHistoryImpl.setId_(proposalMoveHistory.getId_());
        proposalMoveHistoryImpl.setSourceProposalId(proposalMoveHistory.getSourceProposalId());
        proposalMoveHistoryImpl.setSourceContestId(proposalMoveHistory.getSourceContestId());
        proposalMoveHistoryImpl.setSourcePhaseId(proposalMoveHistory.getSourcePhaseId());
        proposalMoveHistoryImpl.setTargetProposalId(proposalMoveHistory.getTargetProposalId());
        proposalMoveHistoryImpl.setTargetContestId(proposalMoveHistory.getTargetContestId());
        proposalMoveHistoryImpl.setTargetPhaseId(proposalMoveHistory.getTargetPhaseId());
        proposalMoveHistoryImpl.setMovingUserId(proposalMoveHistory.getMovingUserId());
        proposalMoveHistoryImpl.setMoveDate(proposalMoveHistory.getMoveDate());
        proposalMoveHistoryImpl.setMoveType(proposalMoveHistory.getMoveType());

        return proposalMoveHistoryImpl;
    }

    /**
     * Returns the proposal move history with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal move history
     * @return the proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProposalMoveHistoryException, SystemException {
        ProposalMoveHistory proposalMoveHistory = fetchByPrimaryKey(primaryKey);

        if (proposalMoveHistory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalMoveHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposalMoveHistory;
    }

    /**
     * Returns the proposal move history with the primary key or throws a {@link com.ext.portlet.NoSuchProposalMoveHistoryException} if it could not be found.
     *
     * @param id_ the primary key of the proposal move history
     * @return the proposal move history
     * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory findByPrimaryKey(long id_)
        throws NoSuchProposalMoveHistoryException, SystemException {
        return findByPrimaryKey((Serializable) id_);
    }

    /**
     * Returns the proposal move history with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal move history
     * @return the proposal move history, or <code>null</code> if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProposalMoveHistory proposalMoveHistory = (ProposalMoveHistory) EntityCacheUtil.getResult(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
                ProposalMoveHistoryImpl.class, primaryKey);

        if (proposalMoveHistory == _nullProposalMoveHistory) {
            return null;
        }

        if (proposalMoveHistory == null) {
            Session session = null;

            try {
                session = openSession();

                proposalMoveHistory = (ProposalMoveHistory) session.get(ProposalMoveHistoryImpl.class,
                        primaryKey);

                if (proposalMoveHistory != null) {
                    cacheResult(proposalMoveHistory);
                } else {
                    EntityCacheUtil.putResult(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalMoveHistoryImpl.class, primaryKey,
                        _nullProposalMoveHistory);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalMoveHistoryModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalMoveHistoryImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposalMoveHistory;
    }

    /**
     * Returns the proposal move history with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id_ the primary key of the proposal move history
     * @return the proposal move history, or <code>null</code> if a proposal move history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalMoveHistory fetchByPrimaryKey(long id_)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id_);
    }

    /**
     * Returns all the proposal move histories.
     *
     * @return the proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal move histories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @return the range of proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal move histories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal move histories
     * @param end the upper bound of the range of proposal move histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal move histories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalMoveHistory> findAll(int start, int end,
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

        List<ProposalMoveHistory> list = (List<ProposalMoveHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALMOVEHISTORY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALMOVEHISTORY;

                if (pagination) {
                    sql = sql.concat(ProposalMoveHistoryModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalMoveHistory>(list);
                } else {
                    list = (List<ProposalMoveHistory>) QueryUtil.list(q,
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
     * Removes all the proposal move histories from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProposalMoveHistory proposalMoveHistory : findAll()) {
            remove(proposalMoveHistory);
        }
    }

    /**
     * Returns the number of proposal move histories.
     *
     * @return the number of proposal move histories
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSALMOVEHISTORY);

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
     * Initializes the proposal move history persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalMoveHistory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalMoveHistory>> listenersList = new ArrayList<ModelListener<ProposalMoveHistory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalMoveHistory>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalMoveHistoryImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
