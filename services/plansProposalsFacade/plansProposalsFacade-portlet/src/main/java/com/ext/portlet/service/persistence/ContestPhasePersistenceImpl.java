package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestPhaseException;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.impl.ContestPhaseImpl;
import com.ext.portlet.model.impl.ContestPhaseModelImpl;
import com.ext.portlet.service.persistence.ContestPhasePersistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the contest phase service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhasePersistence
 * @see ContestPhaseUtil
 * @generated
 */
public class ContestPhasePersistenceImpl extends BasePersistenceImpl<ContestPhase>
    implements ContestPhasePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestPhaseUtil} to access the contest phase persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestPhaseImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTIDSTARTEND =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestIdStartEnd",
            new String[] {
                Long.class.getName(), Date.class.getName(), Date.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_CONTESTIDSTARTEND =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByContestIdStartEnd",
            new String[] {
                Long.class.getName(), Date.class.getName(), Date.class.getName()
            });
    private static final String _FINDER_COLUMN_CONTESTIDSTARTEND_CONTESTPK_2 = "contestPhase.ContestPK = ? AND ";
    private static final String _FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_1 =
        "contestPhase.PhaseStartDate <= NULL AND ";
    private static final String _FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_2 =
        "contestPhase.PhaseStartDate <= ? AND ";
    private static final String _FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_1 = "contestPhase.PhaseEndDate >= NULL";
    private static final String _FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_2 = "contestPhase.PhaseEndDate >= ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTSCHEDULEID =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestScheduleId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTSCHEDULEID =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByContestScheduleId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ContestPhaseModelImpl.CONTESTSCHEDULEID_COLUMN_BITMASK |
            ContestPhaseModelImpl.CONTESTPK_COLUMN_BITMASK |
            ContestPhaseModelImpl.PHASESTARTDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTSCHEDULEID = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByContestScheduleId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTSCHEDULEID_CONTESTSCHEDULEID_2 =
        "contestPhase.contestScheduleId = ? AND ";
    private static final String _FINDER_COLUMN_CONTESTSCHEDULEID_CONTESTPK_2 = "contestPhase.ContestPK = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTID =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContestId",
            new String[] { Long.class.getName() },
            ContestPhaseModelImpl.CONTESTPK_COLUMN_BITMASK |
            ContestPhaseModelImpl.PHASESTARTDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTID = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContestId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTID_CONTESTPK_2 = "contestPhase.ContestPK = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PHASEACTIVEOVERRIDE =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByPhaseActiveOverride",
            new String[] {
                Long.class.getName(), Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEACTIVEOVERRIDE =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByPhaseActiveOverride",
            new String[] { Long.class.getName(), Boolean.class.getName() },
            ContestPhaseModelImpl.CONTESTPK_COLUMN_BITMASK |
            ContestPhaseModelImpl.PHASEACTIVEOVERRIDE_COLUMN_BITMASK |
            ContestPhaseModelImpl.PHASESTARTDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PHASEACTIVEOVERRIDE = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByPhaseActiveOverride",
            new String[] { Long.class.getName(), Boolean.class.getName() });
    private static final String _FINDER_COLUMN_PHASEACTIVEOVERRIDE_CONTESTPK_2 = "contestPhase.ContestPK = ? AND ";
    private static final String _FINDER_COLUMN_PHASEACTIVEOVERRIDE_PHASEACTIVEOVERRIDE_2 =
        "contestPhase.phaseActiveOverride = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PHASEINACTIVEOVERRIDE =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByPhaseInactiveOverride",
            new String[] {
                Long.class.getName(), Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEINACTIVEOVERRIDE =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByPhaseInactiveOverride",
            new String[] { Long.class.getName(), Boolean.class.getName() },
            ContestPhaseModelImpl.CONTESTPK_COLUMN_BITMASK |
            ContestPhaseModelImpl.PHASEINACTIVEOVERRIDE_COLUMN_BITMASK |
            ContestPhaseModelImpl.PHASESTARTDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PHASEINACTIVEOVERRIDE = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByPhaseInactiveOverride",
            new String[] { Long.class.getName(), Boolean.class.getName() });
    private static final String _FINDER_COLUMN_PHASEINACTIVEOVERRIDE_CONTESTPK_2 =
        "contestPhase.ContestPK = ? AND ";
    private static final String _FINDER_COLUMN_PHASEINACTIVEOVERRIDE_PHASEINACTIVEOVERRIDE_2 =
        "contestPhase.phaseInactiveOverride = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PHASEAUTOPROMOTE =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPhaseAutopromote",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEAUTOPROMOTE =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByPhaseAutopromote", new String[] { String.class.getName() },
            ContestPhaseModelImpl.CONTESTPHASEAUTOPROMOTE_COLUMN_BITMASK |
            ContestPhaseModelImpl.PHASESTARTDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PHASEAUTOPROMOTE = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByPhaseAutopromote", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_1 =
        "contestPhase.contestPhaseAutopromote IS NULL";
    private static final String _FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_2 =
        "contestPhase.contestPhaseAutopromote = ?";
    private static final String _FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_3 =
        "(contestPhase.contestPhaseAutopromote IS NULL OR contestPhase.contestPhaseAutopromote = '')";
    private static final String _SQL_SELECT_CONTESTPHASE = "SELECT contestPhase FROM ContestPhase contestPhase";
    private static final String _SQL_SELECT_CONTESTPHASE_WHERE = "SELECT contestPhase FROM ContestPhase contestPhase WHERE ";
    private static final String _SQL_COUNT_CONTESTPHASE = "SELECT COUNT(contestPhase) FROM ContestPhase contestPhase";
    private static final String _SQL_COUNT_CONTESTPHASE_WHERE = "SELECT COUNT(contestPhase) FROM ContestPhase contestPhase WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestPhase.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestPhase exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContestPhase exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestPhasePersistenceImpl.class);
    private static ContestPhase _nullContestPhase = new ContestPhaseImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestPhase> toCacheModel() {
                return _nullContestPhaseCacheModel;
            }
        };

    private static CacheModel<ContestPhase> _nullContestPhaseCacheModel = new CacheModel<ContestPhase>() {
            @Override
            public ContestPhase toEntityModel() {
                return _nullContestPhase;
            }
        };

    public ContestPhasePersistenceImpl() {
        setModelClass(ContestPhase.class);
    }

    /**
     * Returns all the contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @return the matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByContestIdStartEnd(long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate) throws SystemException {
        return findByContestIdStartEnd(ContestPK, PhaseStartDate, PhaseEndDate,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @return the range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByContestIdStartEnd(long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate, int start, int end)
        throws SystemException {
        return findByContestIdStartEnd(ContestPK, PhaseStartDate, PhaseEndDate,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByContestIdStartEnd(long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTIDSTARTEND;
        finderArgs = new Object[] {
                ContestPK, PhaseStartDate, PhaseEndDate,
                
                start, end, orderByComparator
            };

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ContestPhase contestPhase : list) {
                if ((ContestPK != contestPhase.getContestPK()) ||
                        (PhaseStartDate.getTime() < contestPhase.getPhaseStartDate()
                                                                    .getTime()) ||
                        (PhaseEndDate.getTime() > contestPhase.getPhaseEndDate()
                                                                  .getTime())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(5 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(5);
            }

            query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_CONTESTPK_2);

            boolean bindPhaseStartDate = false;

            if (PhaseStartDate == null) {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_1);
            } else {
                bindPhaseStartDate = true;

                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_2);
            }

            boolean bindPhaseEndDate = false;

            if (PhaseEndDate == null) {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_1);
            } else {
                bindPhaseEndDate = true;

                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                if (bindPhaseStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseStartDate));
                }

                if (bindPhaseEndDate) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseEndDate));
                }

                if (!pagination) {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestPhase>(list);
                } else {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest phase in the ordered set where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByContestIdStartEnd_First(long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByContestIdStartEnd_First(ContestPK,
                PhaseStartDate, PhaseEndDate, orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ContestPK=");
        msg.append(ContestPK);

        msg.append(", PhaseStartDate=");
        msg.append(PhaseStartDate);

        msg.append(", PhaseEndDate=");
        msg.append(PhaseEndDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the first contest phase in the ordered set where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByContestIdStartEnd_First(long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate,
        OrderByComparator orderByComparator) throws SystemException {
        List<ContestPhase> list = findByContestIdStartEnd(ContestPK,
                PhaseStartDate, PhaseEndDate, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest phase in the ordered set where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByContestIdStartEnd_Last(long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByContestIdStartEnd_Last(ContestPK,
                PhaseStartDate, PhaseEndDate, orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ContestPK=");
        msg.append(ContestPK);

        msg.append(", PhaseStartDate=");
        msg.append(PhaseStartDate);

        msg.append(", PhaseEndDate=");
        msg.append(PhaseEndDate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the last contest phase in the ordered set where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByContestIdStartEnd_Last(long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContestIdStartEnd(ContestPK, PhaseStartDate,
                PhaseEndDate);

        if (count == 0) {
            return null;
        }

        List<ContestPhase> list = findByContestIdStartEnd(ContestPK,
                PhaseStartDate, PhaseEndDate, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
     *
     * @param ContestPhasePK the primary key of the current contest phase
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase[] findByContestIdStartEnd_PrevAndNext(
        long ContestPhasePK, long ContestPK, Date PhaseStartDate,
        Date PhaseEndDate, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = findByPrimaryKey(ContestPhasePK);

        Session session = null;

        try {
            session = openSession();

            ContestPhase[] array = new ContestPhaseImpl[3];

            array[0] = getByContestIdStartEnd_PrevAndNext(session,
                    contestPhase, ContestPK, PhaseStartDate, PhaseEndDate,
                    orderByComparator, true);

            array[1] = contestPhase;

            array[2] = getByContestIdStartEnd_PrevAndNext(session,
                    contestPhase, ContestPK, PhaseStartDate, PhaseEndDate,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ContestPhase getByContestIdStartEnd_PrevAndNext(Session session,
        ContestPhase contestPhase, long ContestPK, Date PhaseStartDate,
        Date PhaseEndDate, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

        query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_CONTESTPK_2);

        boolean bindPhaseStartDate = false;

        if (PhaseStartDate == null) {
            query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_1);
        } else {
            bindPhaseStartDate = true;

            query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_2);
        }

        boolean bindPhaseEndDate = false;

        if (PhaseEndDate == null) {
            query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_1);
        } else {
            bindPhaseEndDate = true;

            query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_2);
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
            query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ContestPK);

        if (bindPhaseStartDate) {
            qPos.add(CalendarUtil.getTimestamp(PhaseStartDate));
        }

        if (bindPhaseEndDate) {
            qPos.add(CalendarUtil.getTimestamp(PhaseEndDate));
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contestPhase);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ContestPhase> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63; from the database.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestIdStartEnd(long ContestPK, Date PhaseStartDate,
        Date PhaseEndDate) throws SystemException {
        for (ContestPhase contestPhase : findByContestIdStartEnd(ContestPK,
                PhaseStartDate, PhaseEndDate, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(contestPhase);
        }
    }

    /**
     * Returns the number of contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @return the number of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestIdStartEnd(long ContestPK, Date PhaseStartDate,
        Date PhaseEndDate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_CONTESTIDSTARTEND;

        Object[] finderArgs = new Object[] {
                ContestPK, PhaseStartDate, PhaseEndDate
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_CONTESTPK_2);

            boolean bindPhaseStartDate = false;

            if (PhaseStartDate == null) {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_1);
            } else {
                bindPhaseStartDate = true;

                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_2);
            }

            boolean bindPhaseEndDate = false;

            if (PhaseEndDate == null) {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_1);
            } else {
                bindPhaseEndDate = true;

                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                if (bindPhaseStartDate) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseStartDate));
                }

                if (bindPhaseEndDate) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseEndDate));
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
     * Returns all the contest phases where contestScheduleId = &#63; and ContestPK = &#63;.
     *
     * @param contestScheduleId the contest schedule ID
     * @param ContestPK the contest p k
     * @return the matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByContestScheduleId(long contestScheduleId,
        long ContestPK) throws SystemException {
        return findByContestScheduleId(contestScheduleId, ContestPK,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phases where contestScheduleId = &#63; and ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestScheduleId the contest schedule ID
     * @param ContestPK the contest p k
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @return the range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByContestScheduleId(long contestScheduleId,
        long ContestPK, int start, int end) throws SystemException {
        return findByContestScheduleId(contestScheduleId, ContestPK, start,
            end, null);
    }

    /**
     * Returns an ordered range of all the contest phases where contestScheduleId = &#63; and ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestScheduleId the contest schedule ID
     * @param ContestPK the contest p k
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByContestScheduleId(long contestScheduleId,
        long ContestPK, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTSCHEDULEID;
            finderArgs = new Object[] { contestScheduleId, ContestPK };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTSCHEDULEID;
            finderArgs = new Object[] {
                    contestScheduleId, ContestPK,
                    
                    start, end, orderByComparator
                };
        }

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ContestPhase contestPhase : list) {
                if ((contestScheduleId != contestPhase.getContestScheduleId()) ||
                        (ContestPK != contestPhase.getContestPK())) {
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

            query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTSCHEDULEID_CONTESTSCHEDULEID_2);

            query.append(_FINDER_COLUMN_CONTESTSCHEDULEID_CONTESTPK_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestScheduleId);

                qPos.add(ContestPK);

                if (!pagination) {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestPhase>(list);
                } else {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest phase in the ordered set where contestScheduleId = &#63; and ContestPK = &#63;.
     *
     * @param contestScheduleId the contest schedule ID
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByContestScheduleId_First(long contestScheduleId,
        long ContestPK, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByContestScheduleId_First(contestScheduleId,
                ContestPK, orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestScheduleId=");
        msg.append(contestScheduleId);

        msg.append(", ContestPK=");
        msg.append(ContestPK);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the first contest phase in the ordered set where contestScheduleId = &#63; and ContestPK = &#63;.
     *
     * @param contestScheduleId the contest schedule ID
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByContestScheduleId_First(long contestScheduleId,
        long ContestPK, OrderByComparator orderByComparator)
        throws SystemException {
        List<ContestPhase> list = findByContestScheduleId(contestScheduleId,
                ContestPK, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest phase in the ordered set where contestScheduleId = &#63; and ContestPK = &#63;.
     *
     * @param contestScheduleId the contest schedule ID
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByContestScheduleId_Last(long contestScheduleId,
        long ContestPK, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByContestScheduleId_Last(contestScheduleId,
                ContestPK, orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestScheduleId=");
        msg.append(contestScheduleId);

        msg.append(", ContestPK=");
        msg.append(ContestPK);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the last contest phase in the ordered set where contestScheduleId = &#63; and ContestPK = &#63;.
     *
     * @param contestScheduleId the contest schedule ID
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByContestScheduleId_Last(long contestScheduleId,
        long ContestPK, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByContestScheduleId(contestScheduleId, ContestPK);

        if (count == 0) {
            return null;
        }

        List<ContestPhase> list = findByContestScheduleId(contestScheduleId,
                ContestPK, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contest phases before and after the current contest phase in the ordered set where contestScheduleId = &#63; and ContestPK = &#63;.
     *
     * @param ContestPhasePK the primary key of the current contest phase
     * @param contestScheduleId the contest schedule ID
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase[] findByContestScheduleId_PrevAndNext(
        long ContestPhasePK, long contestScheduleId, long ContestPK,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = findByPrimaryKey(ContestPhasePK);

        Session session = null;

        try {
            session = openSession();

            ContestPhase[] array = new ContestPhaseImpl[3];

            array[0] = getByContestScheduleId_PrevAndNext(session,
                    contestPhase, contestScheduleId, ContestPK,
                    orderByComparator, true);

            array[1] = contestPhase;

            array[2] = getByContestScheduleId_PrevAndNext(session,
                    contestPhase, contestScheduleId, ContestPK,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ContestPhase getByContestScheduleId_PrevAndNext(Session session,
        ContestPhase contestPhase, long contestScheduleId, long ContestPK,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

        query.append(_FINDER_COLUMN_CONTESTSCHEDULEID_CONTESTSCHEDULEID_2);

        query.append(_FINDER_COLUMN_CONTESTSCHEDULEID_CONTESTPK_2);

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
            query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestScheduleId);

        qPos.add(ContestPK);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contestPhase);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ContestPhase> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contest phases where contestScheduleId = &#63; and ContestPK = &#63; from the database.
     *
     * @param contestScheduleId the contest schedule ID
     * @param ContestPK the contest p k
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestScheduleId(long contestScheduleId, long ContestPK)
        throws SystemException {
        for (ContestPhase contestPhase : findByContestScheduleId(
                contestScheduleId, ContestPK, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(contestPhase);
        }
    }

    /**
     * Returns the number of contest phases where contestScheduleId = &#63; and ContestPK = &#63;.
     *
     * @param contestScheduleId the contest schedule ID
     * @param ContestPK the contest p k
     * @return the number of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestScheduleId(long contestScheduleId, long ContestPK)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTSCHEDULEID;

        Object[] finderArgs = new Object[] { contestScheduleId, ContestPK };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTSCHEDULEID_CONTESTSCHEDULEID_2);

            query.append(_FINDER_COLUMN_CONTESTSCHEDULEID_CONTESTPK_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestScheduleId);

                qPos.add(ContestPK);

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
     * Returns all the contest phases where ContestPK = &#63;.
     *
     * @param ContestPK the contest p k
     * @return the matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByContestId(long ContestPK)
        throws SystemException {
        return findByContestId(ContestPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the contest phases where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @return the range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByContestId(long ContestPK, int start, int end)
        throws SystemException {
        return findByContestId(ContestPK, start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phases where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByContestId(long ContestPK, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID;
            finderArgs = new Object[] { ContestPK };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTID;
            finderArgs = new Object[] { ContestPK, start, end, orderByComparator };
        }

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ContestPhase contestPhase : list) {
                if ((ContestPK != contestPhase.getContestPK())) {
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

            query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTID_CONTESTPK_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                if (!pagination) {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestPhase>(list);
                } else {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest phase in the ordered set where ContestPK = &#63;.
     *
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByContestId_First(long ContestPK,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByContestId_First(ContestPK,
                orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ContestPK=");
        msg.append(ContestPK);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the first contest phase in the ordered set where ContestPK = &#63;.
     *
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByContestId_First(long ContestPK,
        OrderByComparator orderByComparator) throws SystemException {
        List<ContestPhase> list = findByContestId(ContestPK, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest phase in the ordered set where ContestPK = &#63;.
     *
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByContestId_Last(long ContestPK,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByContestId_Last(ContestPK,
                orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ContestPK=");
        msg.append(ContestPK);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the last contest phase in the ordered set where ContestPK = &#63;.
     *
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByContestId_Last(long ContestPK,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContestId(ContestPK);

        if (count == 0) {
            return null;
        }

        List<ContestPhase> list = findByContestId(ContestPK, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63;.
     *
     * @param ContestPhasePK the primary key of the current contest phase
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase[] findByContestId_PrevAndNext(long ContestPhasePK,
        long ContestPK, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = findByPrimaryKey(ContestPhasePK);

        Session session = null;

        try {
            session = openSession();

            ContestPhase[] array = new ContestPhaseImpl[3];

            array[0] = getByContestId_PrevAndNext(session, contestPhase,
                    ContestPK, orderByComparator, true);

            array[1] = contestPhase;

            array[2] = getByContestId_PrevAndNext(session, contestPhase,
                    ContestPK, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ContestPhase getByContestId_PrevAndNext(Session session,
        ContestPhase contestPhase, long ContestPK,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

        query.append(_FINDER_COLUMN_CONTESTID_CONTESTPK_2);

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
            query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ContestPK);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contestPhase);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ContestPhase> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contest phases where ContestPK = &#63; from the database.
     *
     * @param ContestPK the contest p k
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestId(long ContestPK) throws SystemException {
        for (ContestPhase contestPhase : findByContestId(ContestPK,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contestPhase);
        }
    }

    /**
     * Returns the number of contest phases where ContestPK = &#63;.
     *
     * @param ContestPK the contest p k
     * @return the number of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestId(long ContestPK) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTID;

        Object[] finderArgs = new Object[] { ContestPK };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTID_CONTESTPK_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

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
     * Returns all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseActiveOverride the phase active override
     * @return the matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByPhaseActiveOverride(long ContestPK,
        boolean phaseActiveOverride) throws SystemException {
        return findByPhaseActiveOverride(ContestPK, phaseActiveOverride,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param phaseActiveOverride the phase active override
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @return the range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByPhaseActiveOverride(long ContestPK,
        boolean phaseActiveOverride, int start, int end)
        throws SystemException {
        return findByPhaseActiveOverride(ContestPK, phaseActiveOverride, start,
            end, null);
    }

    /**
     * Returns an ordered range of all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param phaseActiveOverride the phase active override
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByPhaseActiveOverride(long ContestPK,
        boolean phaseActiveOverride, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEACTIVEOVERRIDE;
            finderArgs = new Object[] { ContestPK, phaseActiveOverride };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PHASEACTIVEOVERRIDE;
            finderArgs = new Object[] {
                    ContestPK, phaseActiveOverride,
                    
                    start, end, orderByComparator
                };
        }

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ContestPhase contestPhase : list) {
                if ((ContestPK != contestPhase.getContestPK()) ||
                        (phaseActiveOverride != contestPhase.getPhaseActiveOverride())) {
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

            query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_PHASEACTIVEOVERRIDE_CONTESTPK_2);

            query.append(_FINDER_COLUMN_PHASEACTIVEOVERRIDE_PHASEACTIVEOVERRIDE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                qPos.add(phaseActiveOverride);

                if (!pagination) {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestPhase>(list);
                } else {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseActiveOverride the phase active override
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByPhaseActiveOverride_First(long ContestPK,
        boolean phaseActiveOverride, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByPhaseActiveOverride_First(ContestPK,
                phaseActiveOverride, orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ContestPK=");
        msg.append(ContestPK);

        msg.append(", phaseActiveOverride=");
        msg.append(phaseActiveOverride);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the first contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseActiveOverride the phase active override
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByPhaseActiveOverride_First(long ContestPK,
        boolean phaseActiveOverride, OrderByComparator orderByComparator)
        throws SystemException {
        List<ContestPhase> list = findByPhaseActiveOverride(ContestPK,
                phaseActiveOverride, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseActiveOverride the phase active override
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByPhaseActiveOverride_Last(long ContestPK,
        boolean phaseActiveOverride, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByPhaseActiveOverride_Last(ContestPK,
                phaseActiveOverride, orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ContestPK=");
        msg.append(ContestPK);

        msg.append(", phaseActiveOverride=");
        msg.append(phaseActiveOverride);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the last contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseActiveOverride the phase active override
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByPhaseActiveOverride_Last(long ContestPK,
        boolean phaseActiveOverride, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByPhaseActiveOverride(ContestPK, phaseActiveOverride);

        if (count == 0) {
            return null;
        }

        List<ContestPhase> list = findByPhaseActiveOverride(ContestPK,
                phaseActiveOverride, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
     *
     * @param ContestPhasePK the primary key of the current contest phase
     * @param ContestPK the contest p k
     * @param phaseActiveOverride the phase active override
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase[] findByPhaseActiveOverride_PrevAndNext(
        long ContestPhasePK, long ContestPK, boolean phaseActiveOverride,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = findByPrimaryKey(ContestPhasePK);

        Session session = null;

        try {
            session = openSession();

            ContestPhase[] array = new ContestPhaseImpl[3];

            array[0] = getByPhaseActiveOverride_PrevAndNext(session,
                    contestPhase, ContestPK, phaseActiveOverride,
                    orderByComparator, true);

            array[1] = contestPhase;

            array[2] = getByPhaseActiveOverride_PrevAndNext(session,
                    contestPhase, ContestPK, phaseActiveOverride,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ContestPhase getByPhaseActiveOverride_PrevAndNext(
        Session session, ContestPhase contestPhase, long ContestPK,
        boolean phaseActiveOverride, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

        query.append(_FINDER_COLUMN_PHASEACTIVEOVERRIDE_CONTESTPK_2);

        query.append(_FINDER_COLUMN_PHASEACTIVEOVERRIDE_PHASEACTIVEOVERRIDE_2);

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
            query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ContestPK);

        qPos.add(phaseActiveOverride);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contestPhase);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ContestPhase> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63; from the database.
     *
     * @param ContestPK the contest p k
     * @param phaseActiveOverride the phase active override
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPhaseActiveOverride(long ContestPK,
        boolean phaseActiveOverride) throws SystemException {
        for (ContestPhase contestPhase : findByPhaseActiveOverride(ContestPK,
                phaseActiveOverride, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contestPhase);
        }
    }

    /**
     * Returns the number of contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseActiveOverride the phase active override
     * @return the number of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPhaseActiveOverride(long ContestPK,
        boolean phaseActiveOverride) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PHASEACTIVEOVERRIDE;

        Object[] finderArgs = new Object[] { ContestPK, phaseActiveOverride };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_PHASEACTIVEOVERRIDE_CONTESTPK_2);

            query.append(_FINDER_COLUMN_PHASEACTIVEOVERRIDE_PHASEACTIVEOVERRIDE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                qPos.add(phaseActiveOverride);

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
     * Returns all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseInactiveOverride the phase inactive override
     * @return the matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByPhaseInactiveOverride(long ContestPK,
        boolean phaseInactiveOverride) throws SystemException {
        return findByPhaseInactiveOverride(ContestPK, phaseInactiveOverride,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param phaseInactiveOverride the phase inactive override
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @return the range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByPhaseInactiveOverride(long ContestPK,
        boolean phaseInactiveOverride, int start, int end)
        throws SystemException {
        return findByPhaseInactiveOverride(ContestPK, phaseInactiveOverride,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param phaseInactiveOverride the phase inactive override
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByPhaseInactiveOverride(long ContestPK,
        boolean phaseInactiveOverride, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEINACTIVEOVERRIDE;
            finderArgs = new Object[] { ContestPK, phaseInactiveOverride };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PHASEINACTIVEOVERRIDE;
            finderArgs = new Object[] {
                    ContestPK, phaseInactiveOverride,
                    
                    start, end, orderByComparator
                };
        }

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ContestPhase contestPhase : list) {
                if ((ContestPK != contestPhase.getContestPK()) ||
                        (phaseInactiveOverride != contestPhase.getPhaseInactiveOverride())) {
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

            query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_PHASEINACTIVEOVERRIDE_CONTESTPK_2);

            query.append(_FINDER_COLUMN_PHASEINACTIVEOVERRIDE_PHASEINACTIVEOVERRIDE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                qPos.add(phaseInactiveOverride);

                if (!pagination) {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestPhase>(list);
                } else {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseInactiveOverride the phase inactive override
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByPhaseInactiveOverride_First(long ContestPK,
        boolean phaseInactiveOverride, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByPhaseInactiveOverride_First(ContestPK,
                phaseInactiveOverride, orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ContestPK=");
        msg.append(ContestPK);

        msg.append(", phaseInactiveOverride=");
        msg.append(phaseInactiveOverride);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the first contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseInactiveOverride the phase inactive override
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByPhaseInactiveOverride_First(long ContestPK,
        boolean phaseInactiveOverride, OrderByComparator orderByComparator)
        throws SystemException {
        List<ContestPhase> list = findByPhaseInactiveOverride(ContestPK,
                phaseInactiveOverride, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseInactiveOverride the phase inactive override
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByPhaseInactiveOverride_Last(long ContestPK,
        boolean phaseInactiveOverride, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByPhaseInactiveOverride_Last(ContestPK,
                phaseInactiveOverride, orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("ContestPK=");
        msg.append(ContestPK);

        msg.append(", phaseInactiveOverride=");
        msg.append(phaseInactiveOverride);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the last contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseInactiveOverride the phase inactive override
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByPhaseInactiveOverride_Last(long ContestPK,
        boolean phaseInactiveOverride, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByPhaseInactiveOverride(ContestPK,
                phaseInactiveOverride);

        if (count == 0) {
            return null;
        }

        List<ContestPhase> list = findByPhaseInactiveOverride(ContestPK,
                phaseInactiveOverride, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
     *
     * @param ContestPhasePK the primary key of the current contest phase
     * @param ContestPK the contest p k
     * @param phaseInactiveOverride the phase inactive override
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase[] findByPhaseInactiveOverride_PrevAndNext(
        long ContestPhasePK, long ContestPK, boolean phaseInactiveOverride,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = findByPrimaryKey(ContestPhasePK);

        Session session = null;

        try {
            session = openSession();

            ContestPhase[] array = new ContestPhaseImpl[3];

            array[0] = getByPhaseInactiveOverride_PrevAndNext(session,
                    contestPhase, ContestPK, phaseInactiveOverride,
                    orderByComparator, true);

            array[1] = contestPhase;

            array[2] = getByPhaseInactiveOverride_PrevAndNext(session,
                    contestPhase, ContestPK, phaseInactiveOverride,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ContestPhase getByPhaseInactiveOverride_PrevAndNext(
        Session session, ContestPhase contestPhase, long ContestPK,
        boolean phaseInactiveOverride, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

        query.append(_FINDER_COLUMN_PHASEINACTIVEOVERRIDE_CONTESTPK_2);

        query.append(_FINDER_COLUMN_PHASEINACTIVEOVERRIDE_PHASEINACTIVEOVERRIDE_2);

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
            query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ContestPK);

        qPos.add(phaseInactiveOverride);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contestPhase);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ContestPhase> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63; from the database.
     *
     * @param ContestPK the contest p k
     * @param phaseInactiveOverride the phase inactive override
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPhaseInactiveOverride(long ContestPK,
        boolean phaseInactiveOverride) throws SystemException {
        for (ContestPhase contestPhase : findByPhaseInactiveOverride(
                ContestPK, phaseInactiveOverride, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(contestPhase);
        }
    }

    /**
     * Returns the number of contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
     *
     * @param ContestPK the contest p k
     * @param phaseInactiveOverride the phase inactive override
     * @return the number of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPhaseInactiveOverride(long ContestPK,
        boolean phaseInactiveOverride) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PHASEINACTIVEOVERRIDE;

        Object[] finderArgs = new Object[] { ContestPK, phaseInactiveOverride };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_PHASEINACTIVEOVERRIDE_CONTESTPK_2);

            query.append(_FINDER_COLUMN_PHASEINACTIVEOVERRIDE_PHASEINACTIVEOVERRIDE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                qPos.add(phaseInactiveOverride);

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
     * Returns all the contest phases where contestPhaseAutopromote = &#63;.
     *
     * @param contestPhaseAutopromote the contest phase autopromote
     * @return the matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByPhaseAutopromote(
        String contestPhaseAutopromote) throws SystemException {
        return findByPhaseAutopromote(contestPhaseAutopromote,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phases where contestPhaseAutopromote = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestPhaseAutopromote the contest phase autopromote
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @return the range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByPhaseAutopromote(
        String contestPhaseAutopromote, int start, int end)
        throws SystemException {
        return findByPhaseAutopromote(contestPhaseAutopromote, start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phases where contestPhaseAutopromote = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestPhaseAutopromote the contest phase autopromote
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findByPhaseAutopromote(
        String contestPhaseAutopromote, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEAUTOPROMOTE;
            finderArgs = new Object[] { contestPhaseAutopromote };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PHASEAUTOPROMOTE;
            finderArgs = new Object[] {
                    contestPhaseAutopromote,
                    
                    start, end, orderByComparator
                };
        }

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ContestPhase contestPhase : list) {
                if (!Validator.equals(contestPhaseAutopromote,
                            contestPhase.getContestPhaseAutopromote())) {
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

            query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

            boolean bindContestPhaseAutopromote = false;

            if (contestPhaseAutopromote == null) {
                query.append(_FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_1);
            } else if (contestPhaseAutopromote.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_3);
            } else {
                bindContestPhaseAutopromote = true;

                query.append(_FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindContestPhaseAutopromote) {
                    qPos.add(contestPhaseAutopromote);
                }

                if (!pagination) {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestPhase>(list);
                } else {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest phase in the ordered set where contestPhaseAutopromote = &#63;.
     *
     * @param contestPhaseAutopromote the contest phase autopromote
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByPhaseAutopromote_First(
        String contestPhaseAutopromote, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByPhaseAutopromote_First(contestPhaseAutopromote,
                orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestPhaseAutopromote=");
        msg.append(contestPhaseAutopromote);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the first contest phase in the ordered set where contestPhaseAutopromote = &#63;.
     *
     * @param contestPhaseAutopromote the contest phase autopromote
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByPhaseAutopromote_First(
        String contestPhaseAutopromote, OrderByComparator orderByComparator)
        throws SystemException {
        List<ContestPhase> list = findByPhaseAutopromote(contestPhaseAutopromote,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest phase in the ordered set where contestPhaseAutopromote = &#63;.
     *
     * @param contestPhaseAutopromote the contest phase autopromote
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByPhaseAutopromote_Last(
        String contestPhaseAutopromote, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByPhaseAutopromote_Last(contestPhaseAutopromote,
                orderByComparator);

        if (contestPhase != null) {
            return contestPhase;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestPhaseAutopromote=");
        msg.append(contestPhaseAutopromote);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestPhaseException(msg.toString());
    }

    /**
     * Returns the last contest phase in the ordered set where contestPhaseAutopromote = &#63;.
     *
     * @param contestPhaseAutopromote the contest phase autopromote
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByPhaseAutopromote_Last(
        String contestPhaseAutopromote, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByPhaseAutopromote(contestPhaseAutopromote);

        if (count == 0) {
            return null;
        }

        List<ContestPhase> list = findByPhaseAutopromote(contestPhaseAutopromote,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contest phases before and after the current contest phase in the ordered set where contestPhaseAutopromote = &#63;.
     *
     * @param ContestPhasePK the primary key of the current contest phase
     * @param contestPhaseAutopromote the contest phase autopromote
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase[] findByPhaseAutopromote_PrevAndNext(
        long ContestPhasePK, String contestPhaseAutopromote,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = findByPrimaryKey(ContestPhasePK);

        Session session = null;

        try {
            session = openSession();

            ContestPhase[] array = new ContestPhaseImpl[3];

            array[0] = getByPhaseAutopromote_PrevAndNext(session, contestPhase,
                    contestPhaseAutopromote, orderByComparator, true);

            array[1] = contestPhase;

            array[2] = getByPhaseAutopromote_PrevAndNext(session, contestPhase,
                    contestPhaseAutopromote, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ContestPhase getByPhaseAutopromote_PrevAndNext(Session session,
        ContestPhase contestPhase, String contestPhaseAutopromote,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

        boolean bindContestPhaseAutopromote = false;

        if (contestPhaseAutopromote == null) {
            query.append(_FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_1);
        } else if (contestPhaseAutopromote.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_3);
        } else {
            bindContestPhaseAutopromote = true;

            query.append(_FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_2);
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
            query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindContestPhaseAutopromote) {
            qPos.add(contestPhaseAutopromote);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contestPhase);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ContestPhase> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contest phases where contestPhaseAutopromote = &#63; from the database.
     *
     * @param contestPhaseAutopromote the contest phase autopromote
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPhaseAutopromote(String contestPhaseAutopromote)
        throws SystemException {
        for (ContestPhase contestPhase : findByPhaseAutopromote(
                contestPhaseAutopromote, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
                null)) {
            remove(contestPhase);
        }
    }

    /**
     * Returns the number of contest phases where contestPhaseAutopromote = &#63;.
     *
     * @param contestPhaseAutopromote the contest phase autopromote
     * @return the number of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPhaseAutopromote(String contestPhaseAutopromote)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PHASEAUTOPROMOTE;

        Object[] finderArgs = new Object[] { contestPhaseAutopromote };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTESTPHASE_WHERE);

            boolean bindContestPhaseAutopromote = false;

            if (contestPhaseAutopromote == null) {
                query.append(_FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_1);
            } else if (contestPhaseAutopromote.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_3);
            } else {
                bindContestPhaseAutopromote = true;

                query.append(_FINDER_COLUMN_PHASEAUTOPROMOTE_CONTESTPHASEAUTOPROMOTE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindContestPhaseAutopromote) {
                    qPos.add(contestPhaseAutopromote);
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
     * Caches the contest phase in the entity cache if it is enabled.
     *
     * @param contestPhase the contest phase
     */
    @Override
    public void cacheResult(ContestPhase contestPhase) {
        EntityCacheUtil.putResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseImpl.class, contestPhase.getPrimaryKey(), contestPhase);

        contestPhase.resetOriginalValues();
    }

    /**
     * Caches the contest phases in the entity cache if it is enabled.
     *
     * @param contestPhases the contest phases
     */
    @Override
    public void cacheResult(List<ContestPhase> contestPhases) {
        for (ContestPhase contestPhase : contestPhases) {
            if (EntityCacheUtil.getResult(
                        ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseImpl.class, contestPhase.getPrimaryKey()) == null) {
                cacheResult(contestPhase);
            } else {
                contestPhase.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest phases.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestPhaseImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestPhaseImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest phase.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestPhase contestPhase) {
        EntityCacheUtil.removeResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseImpl.class, contestPhase.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContestPhase> contestPhases) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestPhase contestPhase : contestPhases) {
            EntityCacheUtil.removeResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseImpl.class, contestPhase.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest phase with the primary key. Does not add the contest phase to the database.
     *
     * @param ContestPhasePK the primary key for the new contest phase
     * @return the new contest phase
     */
    @Override
    public ContestPhase create(long ContestPhasePK) {
        ContestPhase contestPhase = new ContestPhaseImpl();

        contestPhase.setNew(true);
        contestPhase.setPrimaryKey(ContestPhasePK);

        return contestPhase;
    }

    /**
     * Removes the contest phase with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ContestPhasePK the primary key of the contest phase
     * @return the contest phase that was removed
     * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase remove(long ContestPhasePK)
        throws NoSuchContestPhaseException, SystemException {
        return remove((Serializable) ContestPhasePK);
    }

    /**
     * Removes the contest phase with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest phase
     * @return the contest phase that was removed
     * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase remove(Serializable primaryKey)
        throws NoSuchContestPhaseException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestPhase contestPhase = (ContestPhase) session.get(ContestPhaseImpl.class,
                    primaryKey);

            if (contestPhase == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestPhaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestPhase);
        } catch (NoSuchContestPhaseException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestPhase removeImpl(ContestPhase contestPhase)
        throws SystemException {
        contestPhase = toUnwrappedModel(contestPhase);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contestPhase)) {
                contestPhase = (ContestPhase) session.get(ContestPhaseImpl.class,
                        contestPhase.getPrimaryKeyObj());
            }

            if (contestPhase != null) {
                session.delete(contestPhase);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contestPhase != null) {
            clearCache(contestPhase);
        }

        return contestPhase;
    }

    @Override
    public ContestPhase updateImpl(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws SystemException {
        contestPhase = toUnwrappedModel(contestPhase);

        boolean isNew = contestPhase.isNew();

        ContestPhaseModelImpl contestPhaseModelImpl = (ContestPhaseModelImpl) contestPhase;

        Session session = null;

        try {
            session = openSession();

            if (contestPhase.isNew()) {
                session.save(contestPhase);

                contestPhase.setNew(false);
            } else {
                session.merge(contestPhase);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ContestPhaseModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((contestPhaseModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTSCHEDULEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestPhaseModelImpl.getOriginalContestScheduleId(),
                        contestPhaseModelImpl.getOriginalContestPK()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTSCHEDULEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTSCHEDULEID,
                    args);

                args = new Object[] {
                        contestPhaseModelImpl.getContestScheduleId(),
                        contestPhaseModelImpl.getContestPK()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTSCHEDULEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTSCHEDULEID,
                    args);
            }

            if ((contestPhaseModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestPhaseModelImpl.getOriginalContestPK()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID,
                    args);

                args = new Object[] { contestPhaseModelImpl.getContestPK() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID,
                    args);
            }

            if ((contestPhaseModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEACTIVEOVERRIDE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestPhaseModelImpl.getOriginalContestPK(),
                        contestPhaseModelImpl.getOriginalPhaseActiveOverride()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PHASEACTIVEOVERRIDE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEACTIVEOVERRIDE,
                    args);

                args = new Object[] {
                        contestPhaseModelImpl.getContestPK(),
                        contestPhaseModelImpl.getPhaseActiveOverride()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PHASEACTIVEOVERRIDE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEACTIVEOVERRIDE,
                    args);
            }

            if ((contestPhaseModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEINACTIVEOVERRIDE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestPhaseModelImpl.getOriginalContestPK(),
                        contestPhaseModelImpl.getOriginalPhaseInactiveOverride()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PHASEINACTIVEOVERRIDE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEINACTIVEOVERRIDE,
                    args);

                args = new Object[] {
                        contestPhaseModelImpl.getContestPK(),
                        contestPhaseModelImpl.getPhaseInactiveOverride()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PHASEINACTIVEOVERRIDE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEINACTIVEOVERRIDE,
                    args);
            }

            if ((contestPhaseModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEAUTOPROMOTE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestPhaseModelImpl.getOriginalContestPhaseAutopromote()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PHASEAUTOPROMOTE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEAUTOPROMOTE,
                    args);

                args = new Object[] {
                        contestPhaseModelImpl.getContestPhaseAutopromote()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PHASEAUTOPROMOTE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PHASEAUTOPROMOTE,
                    args);
            }
        }

        EntityCacheUtil.putResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseImpl.class, contestPhase.getPrimaryKey(), contestPhase);

        return contestPhase;
    }

    protected ContestPhase toUnwrappedModel(ContestPhase contestPhase) {
        if (contestPhase instanceof ContestPhaseImpl) {
            return contestPhase;
        }

        ContestPhaseImpl contestPhaseImpl = new ContestPhaseImpl();

        contestPhaseImpl.setNew(contestPhase.isNew());
        contestPhaseImpl.setPrimaryKey(contestPhase.getPrimaryKey());

        contestPhaseImpl.setContestPhasePK(contestPhase.getContestPhasePK());
        contestPhaseImpl.setContestPK(contestPhase.getContestPK());
        contestPhaseImpl.setContestPhaseType(contestPhase.getContestPhaseType());
        contestPhaseImpl.setContestScheduleId(contestPhase.getContestScheduleId());
        contestPhaseImpl.setFellowScreeningActive(contestPhase.isFellowScreeningActive());
        contestPhaseImpl.setContestPhaseAutopromote(contestPhase.getContestPhaseAutopromote());
        contestPhaseImpl.setContestPhaseDescriptionOverride(contestPhase.getContestPhaseDescriptionOverride());
        contestPhaseImpl.setPhaseActiveOverride(contestPhase.isPhaseActiveOverride());
        contestPhaseImpl.setPhaseInactiveOverride(contestPhase.isPhaseInactiveOverride());
        contestPhaseImpl.setPhaseStartDate(contestPhase.getPhaseStartDate());
        contestPhaseImpl.setPhaseEndDate(contestPhase.getPhaseEndDate());
        contestPhaseImpl.setPhaseBufferEndDated(contestPhase.getPhaseBufferEndDated());
        contestPhaseImpl.setNextStatus(contestPhase.getNextStatus());
        contestPhaseImpl.setCreated(contestPhase.getCreated());
        contestPhaseImpl.setUpdated(contestPhase.getUpdated());
        contestPhaseImpl.setAuthorId(contestPhase.getAuthorId());

        return contestPhaseImpl;
    }

    /**
     * Returns the contest phase with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest phase
     * @return the contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByPrimaryKey(primaryKey);

        if (contestPhase == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContestPhaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contestPhase;
    }

    /**
     * Returns the contest phase with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseException} if it could not be found.
     *
     * @param ContestPhasePK the primary key of the contest phase
     * @return the contest phase
     * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByPrimaryKey(long ContestPhasePK)
        throws NoSuchContestPhaseException, SystemException {
        return findByPrimaryKey((Serializable) ContestPhasePK);
    }

    /**
     * Returns the contest phase with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest phase
     * @return the contest phase, or <code>null</code> if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContestPhase contestPhase = (ContestPhase) EntityCacheUtil.getResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseImpl.class, primaryKey);

        if (contestPhase == _nullContestPhase) {
            return null;
        }

        if (contestPhase == null) {
            Session session = null;

            try {
                session = openSession();

                contestPhase = (ContestPhase) session.get(ContestPhaseImpl.class,
                        primaryKey);

                if (contestPhase != null) {
                    cacheResult(contestPhase);
                } else {
                    EntityCacheUtil.putResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseImpl.class, primaryKey, _nullContestPhase);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
                    ContestPhaseImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contestPhase;
    }

    /**
     * Returns the contest phase with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ContestPhasePK the primary key of the contest phase
     * @return the contest phase, or <code>null</code> if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase fetchByPrimaryKey(long ContestPhasePK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ContestPhasePK);
    }

    /**
     * Returns all the contest phases.
     *
     * @return the contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @return the range of contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest phases
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhase> findAll(int start, int end,
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

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTPHASE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTPHASE;

                if (pagination) {
                    sql = sql.concat(ContestPhaseModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestPhase>(list);
                } else {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
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
     * Removes all the contest phases from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContestPhase contestPhase : findAll()) {
            remove(contestPhase);
        }
    }

    /**
     * Returns the number of contest phases.
     *
     * @return the number of contest phases
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

                Query q = session.createQuery(_SQL_COUNT_CONTESTPHASE);

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
     * Initializes the contest phase persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestPhase")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestPhase>> listenersList = new ArrayList<ModelListener<ContestPhase>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestPhase>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestPhaseImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
