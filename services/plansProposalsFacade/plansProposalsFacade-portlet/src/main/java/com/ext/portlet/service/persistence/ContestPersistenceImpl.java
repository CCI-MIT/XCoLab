package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.impl.ContestImpl;
import com.ext.portlet.model.impl.ContestModelImpl;
import com.ext.portlet.service.persistence.ContestPersistence;

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
 * The persistence implementation for the contest service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPersistence
 * @see ContestUtil
 * @generated
 */
public class ContestPersistenceImpl extends BasePersistenceImpl<Contest>
    implements ContestPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestUtil} to access the contest persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByType",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
            new String[] { Long.class.getName() },
            ContestModelImpl.PLANTYPEID_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_TYPE_PLANTYPEID_2 = "contest.PlanTypeId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTTIER =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestTier",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTTIER =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContestTier",
            new String[] { Long.class.getName() },
            ContestModelImpl.CONTESTTIER_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTTIER = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContestTier",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTTIER_CONTESTTIER_2 = "contest.contestTier = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTACTIVECONTESTPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByContestActivecontestPrivate",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTACTIVECONTESTPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByContestActivecontestPrivate",
            new String[] { Boolean.class.getName(), Boolean.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.CONTESTPRIVATE_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTACTIVECONTESTPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByContestActivecontestPrivate",
            new String[] { Boolean.class.getName(), Boolean.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTACTIVECONTESTPRIVATE_CONTESTACTIVE_2 =
        "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_CONTESTACTIVECONTESTPRIVATE_CONTESTPRIVATE_2 =
        "contest.contestPrivate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTACTIVE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestActive",
            new String[] {
                Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTACTIVE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContestActive",
            new String[] { Boolean.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTACTIVE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContestActive",
            new String[] { Boolean.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTACTIVE_CONTESTACTIVE_2 = "contest.contestActive = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATURED =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveFeatured",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATURED =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveFeatured",
            new String[] { Boolean.class.getName(), Boolean.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FEATURED_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFEATURED = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveFeatured",
            new String[] { Boolean.class.getName(), Boolean.class.getName() });
    private static final String _FINDER_COLUMN_ACTIVEFEATURED_CONTESTACTIVE_2 = "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATURED_FEATURED_2 = "contest.featured = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATUREDCONTESTPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByActiveFeaturedcontestPrivate",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDCONTESTPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActiveFeaturedcontestPrivate",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Boolean.class.getName()
            },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FEATURED_COLUMN_BITMASK |
            ContestModelImpl.CONTESTPRIVATE_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFEATUREDCONTESTPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActiveFeaturedcontestPrivate",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Boolean.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_CONTESTACTIVE_2 =
        "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_FEATURED_2 =
        "contest.featured = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_CONTESTPRIVATE_2 =
        "contest.contestPrivate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAG =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveFlag",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAG =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveFlag",
            new String[] { Boolean.class.getName(), Integer.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FLAG_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFLAG = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveFlag",
            new String[] { Boolean.class.getName(), Integer.class.getName() });
    private static final String _FINDER_COLUMN_ACTIVEFLAG_CONTESTACTIVE_2 = "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAG_FLAG_2 = "contest.flag = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGCONTEST =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveFlagContest",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGCONTEST =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActiveFlagContest",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Boolean.class.getName()
            },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FLAG_COLUMN_BITMASK |
            ContestModelImpl.CONTESTPRIVATE_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFLAGCONTEST = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActiveFlagContest",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Boolean.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIVEFLAGCONTEST_CONTESTACTIVE_2 =
        "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGCONTEST_FLAG_2 = "contest.flag = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGCONTEST_CONTESTPRIVATE_2 =
        "contest.contestPrivate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGTEXT =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveFlagText",
            new String[] {
                Boolean.class.getName(), String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXT =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveFlagText",
            new String[] { Boolean.class.getName(), String.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FLAGTEXT_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXT = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveFlagText",
            new String[] { Boolean.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXT_CONTESTACTIVE_2 = "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_1 = "contest.flagText IS NULL";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_2 = "contest.flagText = ?";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_3 = "(contest.flagText IS NULL OR contest.flagText = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGTEXTCONTESTPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByActiveFlagTextcontestPrivate",
            new String[] {
                Boolean.class.getName(), String.class.getName(),
                Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXTCONTESTPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActiveFlagTextcontestPrivate",
            new String[] {
                Boolean.class.getName(), String.class.getName(),
                Boolean.class.getName()
            },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FLAGTEXT_COLUMN_BITMASK |
            ContestModelImpl.CONTESTPRIVATE_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXTCONTESTPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActiveFlagTextcontestPrivate",
            new String[] {
                Boolean.class.getName(), String.class.getName(),
                Boolean.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_CONTESTACTIVE_2 =
        "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_1 =
        "contest.flagText IS NULL AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_2 =
        "contest.flagText = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_3 =
        "(contest.flagText IS NULL OR contest.flagText = '') AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_CONTESTPRIVATE_2 =
        "contest.contestPrivate = ?";
    private static final String _SQL_SELECT_CONTEST = "SELECT contest FROM Contest contest";
    private static final String _SQL_SELECT_CONTEST_WHERE = "SELECT contest FROM Contest contest WHERE ";
    private static final String _SQL_COUNT_CONTEST = "SELECT COUNT(contest) FROM Contest contest";
    private static final String _SQL_COUNT_CONTEST_WHERE = "SELECT COUNT(contest) FROM Contest contest WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contest.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Contest exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Contest exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "featured"
            });
    private static Contest _nullContest = new ContestImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Contest> toCacheModel() {
                return _nullContestCacheModel;
            }
        };

    private static CacheModel<Contest> _nullContestCacheModel = new CacheModel<Contest>() {
            @Override
            public Contest toEntityModel() {
                return _nullContest;
            }
        };

    public ContestPersistenceImpl() {
        setModelClass(Contest.class);
    }

    /**
     * Returns all the contests where PlanTypeId = &#63;.
     *
     * @param PlanTypeId the plan type ID
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByType(long PlanTypeId) throws SystemException {
        return findByType(PlanTypeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where PlanTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param PlanTypeId the plan type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByType(long PlanTypeId, int start, int end)
        throws SystemException {
        return findByType(PlanTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where PlanTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param PlanTypeId the plan type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByType(long PlanTypeId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
            finderArgs = new Object[] { PlanTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
            finderArgs = new Object[] { PlanTypeId, start, end, orderByComparator };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((PlanTypeId != contest.getPlanTypeId())) {
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

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_TYPE_PLANTYPEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(PlanTypeId);

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest in the ordered set where PlanTypeId = &#63;.
     *
     * @param PlanTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByType_First(long PlanTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByType_First(PlanTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("PlanTypeId=");
        msg.append(PlanTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where PlanTypeId = &#63;.
     *
     * @param PlanTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByType_First(long PlanTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByType(PlanTypeId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where PlanTypeId = &#63;.
     *
     * @param PlanTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByType_Last(long PlanTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByType_Last(PlanTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("PlanTypeId=");
        msg.append(PlanTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where PlanTypeId = &#63;.
     *
     * @param PlanTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByType_Last(long PlanTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByType(PlanTypeId);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByType(PlanTypeId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where PlanTypeId = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param PlanTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByType_PrevAndNext(long ContestPK, long PlanTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByType_PrevAndNext(session, contest, PlanTypeId,
                    orderByComparator, true);

            array[1] = contest;

            array[2] = getByType_PrevAndNext(session, contest, PlanTypeId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByType_PrevAndNext(Session session, Contest contest,
        long PlanTypeId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_TYPE_PLANTYPEID_2);

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
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(PlanTypeId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contests where PlanTypeId = &#63; from the database.
     *
     * @param PlanTypeId the plan type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByType(long PlanTypeId) throws SystemException {
        for (Contest contest : findByType(PlanTypeId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where PlanTypeId = &#63;.
     *
     * @param PlanTypeId the plan type ID
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByType(long PlanTypeId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TYPE;

        Object[] finderArgs = new Object[] { PlanTypeId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_TYPE_PLANTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(PlanTypeId);

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
     * Returns all the contests where contestTier = &#63;.
     *
     * @param contestTier the contest tier
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestTier(long contestTier)
        throws SystemException {
        return findByContestTier(contestTier, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestTier = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestTier the contest tier
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestTier(long contestTier, int start, int end)
        throws SystemException {
        return findByContestTier(contestTier, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestTier = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestTier the contest tier
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestTier(long contestTier, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTTIER;
            finderArgs = new Object[] { contestTier };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTTIER;
            finderArgs = new Object[] { contestTier, start, end, orderByComparator };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestTier != contest.getContestTier())) {
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

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_CONTESTTIER_CONTESTTIER_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestTier);

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest in the ordered set where contestTier = &#63;.
     *
     * @param contestTier the contest tier
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByContestTier_First(long contestTier,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByContestTier_First(contestTier,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestTier=");
        msg.append(contestTier);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestTier = &#63;.
     *
     * @param contestTier the contest tier
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByContestTier_First(long contestTier,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByContestTier(contestTier, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestTier = &#63;.
     *
     * @param contestTier the contest tier
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByContestTier_Last(long contestTier,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByContestTier_Last(contestTier, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestTier=");
        msg.append(contestTier);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestTier = &#63;.
     *
     * @param contestTier the contest tier
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByContestTier_Last(long contestTier,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContestTier(contestTier);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByContestTier(contestTier, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestTier = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestTier the contest tier
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByContestTier_PrevAndNext(long ContestPK,
        long contestTier, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByContestTier_PrevAndNext(session, contest,
                    contestTier, orderByComparator, true);

            array[1] = contest;

            array[2] = getByContestTier_PrevAndNext(session, contest,
                    contestTier, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByContestTier_PrevAndNext(Session session,
        Contest contest, long contestTier, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_CONTESTTIER_CONTESTTIER_2);

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
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestTier);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contests where contestTier = &#63; from the database.
     *
     * @param contestTier the contest tier
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestTier(long contestTier) throws SystemException {
        for (Contest contest : findByContestTier(contestTier,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestTier = &#63;.
     *
     * @param contestTier the contest tier
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestTier(long contestTier) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTTIER;

        Object[] finderArgs = new Object[] { contestTier };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_CONTESTTIER_CONTESTTIER_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestTier);

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
     * Returns all the contests where contestActive = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestActivecontestPrivate(
        boolean contestActive, boolean contestPrivate)
        throws SystemException {
        return findByContestActivecontestPrivate(contestActive, contestPrivate,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and contestPrivate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestActivecontestPrivate(
        boolean contestActive, boolean contestPrivate, int start, int end)
        throws SystemException {
        return findByContestActivecontestPrivate(contestActive, contestPrivate,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and contestPrivate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestActivecontestPrivate(
        boolean contestActive, boolean contestPrivate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTACTIVECONTESTPRIVATE;
            finderArgs = new Object[] { contestActive, contestPrivate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTACTIVECONTESTPRIVATE;
            finderArgs = new Object[] {
                    contestActive, contestPrivate,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (contestPrivate != contest.getContestPrivate())) {
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

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_CONTESTACTIVECONTESTPRIVATE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_CONTESTACTIVECONTESTPRIVATE_CONTESTPRIVATE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(contestPrivate);

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByContestActivecontestPrivate_First(
        boolean contestActive, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByContestActivecontestPrivate_First(contestActive,
                contestPrivate, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByContestActivecontestPrivate_First(
        boolean contestActive, boolean contestPrivate,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByContestActivecontestPrivate(contestActive,
                contestPrivate, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByContestActivecontestPrivate_Last(
        boolean contestActive, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByContestActivecontestPrivate_Last(contestActive,
                contestPrivate, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByContestActivecontestPrivate_Last(
        boolean contestActive, boolean contestPrivate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContestActivecontestPrivate(contestActive,
                contestPrivate);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByContestActivecontestPrivate(contestActive,
                contestPrivate, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByContestActivecontestPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByContestActivecontestPrivate_PrevAndNext(session,
                    contest, contestActive, contestPrivate, orderByComparator,
                    true);

            array[1] = contest;

            array[2] = getByContestActivecontestPrivate_PrevAndNext(session,
                    contest, contestActive, contestPrivate, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByContestActivecontestPrivate_PrevAndNext(
        Session session, Contest contest, boolean contestActive,
        boolean contestPrivate, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_CONTESTACTIVECONTESTPRIVATE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_CONTESTACTIVECONTESTPRIVATE_CONTESTPRIVATE_2);

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
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        qPos.add(contestPrivate);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contests where contestActive = &#63; and contestPrivate = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestActivecontestPrivate(boolean contestActive,
        boolean contestPrivate) throws SystemException {
        for (Contest contest : findByContestActivecontestPrivate(
                contestActive, contestPrivate, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestActivecontestPrivate(boolean contestActive,
        boolean contestPrivate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTACTIVECONTESTPRIVATE;

        Object[] finderArgs = new Object[] { contestActive, contestPrivate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_CONTESTACTIVECONTESTPRIVATE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_CONTESTACTIVECONTESTPRIVATE_CONTESTPRIVATE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(contestPrivate);

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
     * Returns all the contests where contestActive = &#63;.
     *
     * @param contestActive the contest active
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestActive(boolean contestActive)
        throws SystemException {
        return findByContestActive(contestActive, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestActive(boolean contestActive, int start,
        int end) throws SystemException {
        return findByContestActive(contestActive, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestActive(boolean contestActive, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTACTIVE;
            finderArgs = new Object[] { contestActive };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTACTIVE;
            finderArgs = new Object[] {
                    contestActive,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive())) {
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

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_CONTESTACTIVE_CONTESTACTIVE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest in the ordered set where contestActive = &#63;.
     *
     * @param contestActive the contest active
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByContestActive_First(boolean contestActive,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByContestActive_First(contestActive,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63;.
     *
     * @param contestActive the contest active
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByContestActive_First(boolean contestActive,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByContestActive(contestActive, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63;.
     *
     * @param contestActive the contest active
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByContestActive_Last(boolean contestActive,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByContestActive_Last(contestActive,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63;.
     *
     * @param contestActive the contest active
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByContestActive_Last(boolean contestActive,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContestActive(contestActive);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByContestActive(contestActive, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByContestActive_PrevAndNext(long ContestPK,
        boolean contestActive, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByContestActive_PrevAndNext(session, contest,
                    contestActive, orderByComparator, true);

            array[1] = contest;

            array[2] = getByContestActive_PrevAndNext(session, contest,
                    contestActive, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByContestActive_PrevAndNext(Session session,
        Contest contest, boolean contestActive,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_CONTESTACTIVE_CONTESTACTIVE_2);

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
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contests where contestActive = &#63; from the database.
     *
     * @param contestActive the contest active
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestActive(boolean contestActive)
        throws SystemException {
        for (Contest contest : findByContestActive(contestActive,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63;.
     *
     * @param contestActive the contest active
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestActive(boolean contestActive)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTACTIVE;

        Object[] finderArgs = new Object[] { contestActive };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_CONTESTACTIVE_CONTESTACTIVE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

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
     * Returns all the contests where contestActive = &#63; and featured = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeatured(boolean contestActive,
        boolean featured) throws SystemException {
        return findByActiveFeatured(contestActive, featured, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and featured = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeatured(boolean contestActive,
        boolean featured, int start, int end) throws SystemException {
        return findByActiveFeatured(contestActive, featured, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeatured(boolean contestActive,
        boolean featured, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATURED;
            finderArgs = new Object[] { contestActive, featured };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATURED;
            finderArgs = new Object[] {
                    contestActive, featured,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (featured != contest.getFeatured())) {
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

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFEATURED_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATURED_FEATURED_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(featured);

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFeatured_First(boolean contestActive,
        boolean featured, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFeatured_First(contestActive, featured,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", featured=");
        msg.append(featured);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFeatured_First(boolean contestActive,
        boolean featured, OrderByComparator orderByComparator)
        throws SystemException {
        List<Contest> list = findByActiveFeatured(contestActive, featured, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFeatured_Last(boolean contestActive,
        boolean featured, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFeatured_Last(contestActive, featured,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", featured=");
        msg.append(featured);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFeatured_Last(boolean contestActive,
        boolean featured, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByActiveFeatured(contestActive, featured);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFeatured(contestActive, featured,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param featured the featured
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveFeatured_PrevAndNext(long ContestPK,
        boolean contestActive, boolean featured,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFeatured_PrevAndNext(session, contest,
                    contestActive, featured, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFeatured_PrevAndNext(session, contest,
                    contestActive, featured, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFeatured_PrevAndNext(Session session,
        Contest contest, boolean contestActive, boolean featured,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFEATURED_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATURED_FEATURED_2);

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
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        qPos.add(featured);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contests where contestActive = &#63; and featured = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveFeatured(boolean contestActive, boolean featured)
        throws SystemException {
        for (Contest contest : findByActiveFeatured(contestActive, featured,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and featured = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveFeatured(boolean contestActive, boolean featured)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFEATURED;

        Object[] finderArgs = new Object[] { contestActive, featured };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFEATURED_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATURED_FEATURED_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(featured);

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
     * Returns all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeaturedcontestPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate)
        throws SystemException {
        return findByActiveFeaturedcontestPrivate(contestActive, featured,
            contestPrivate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeaturedcontestPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate,
        int start, int end) throws SystemException {
        return findByActiveFeaturedcontestPrivate(contestActive, featured,
            contestPrivate, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeaturedcontestPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDCONTESTPRIVATE;
            finderArgs = new Object[] { contestActive, featured, contestPrivate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATUREDCONTESTPRIVATE;
            finderArgs = new Object[] {
                    contestActive, featured, contestPrivate,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (featured != contest.getFeatured()) ||
                        (contestPrivate != contest.getContestPrivate())) {
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

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_FEATURED_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_CONTESTPRIVATE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(featured);

                qPos.add(contestPrivate);

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFeaturedcontestPrivate_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFeaturedcontestPrivate_First(contestActive,
                featured, contestPrivate, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", featured=");
        msg.append(featured);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFeaturedcontestPrivate_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByActiveFeaturedcontestPrivate(contestActive,
                featured, contestPrivate, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFeaturedcontestPrivate_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFeaturedcontestPrivate_Last(contestActive,
                featured, contestPrivate, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", featured=");
        msg.append(featured);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFeaturedcontestPrivate_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActiveFeaturedcontestPrivate(contestActive,
                featured, contestPrivate);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFeaturedcontestPrivate(contestActive,
                featured, contestPrivate, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveFeaturedcontestPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        boolean contestPrivate, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFeaturedcontestPrivate_PrevAndNext(session,
                    contest, contestActive, featured, contestPrivate,
                    orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFeaturedcontestPrivate_PrevAndNext(session,
                    contest, contestActive, featured, contestPrivate,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFeaturedcontestPrivate_PrevAndNext(
        Session session, Contest contest, boolean contestActive,
        boolean featured, boolean contestPrivate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_FEATURED_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_CONTESTPRIVATE_2);

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
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        qPos.add(featured);

        qPos.add(contestPrivate);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveFeaturedcontestPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate) throws SystemException {
        for (Contest contest : findByActiveFeaturedcontestPrivate(
                contestActive, featured, contestPrivate, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveFeaturedcontestPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFEATUREDCONTESTPRIVATE;

        Object[] finderArgs = new Object[] {
                contestActive, featured, contestPrivate
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_FEATURED_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDCONTESTPRIVATE_CONTESTPRIVATE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(featured);

                qPos.add(contestPrivate);

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
     * Returns all the contests where contestActive = &#63; and flag = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlag(boolean contestActive, int flag)
        throws SystemException {
        return findByActiveFlag(contestActive, flag, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and flag = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlag(boolean contestActive, int flag,
        int start, int end) throws SystemException {
        return findByActiveFlag(contestActive, flag, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlag(boolean contestActive, int flag,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAG;
            finderArgs = new Object[] { contestActive, flag };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAG;
            finderArgs = new Object[] {
                    contestActive, flag,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (flag != contest.getFlag())) {
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

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAG_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAG_FLAG_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(flag);

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlag_First(boolean contestActive, int flag,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlag_First(contestActive, flag,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flag=");
        msg.append(flag);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlag_First(boolean contestActive, int flag,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByActiveFlag(contestActive, flag, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlag_Last(boolean contestActive, int flag,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlag_Last(contestActive, flag,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flag=");
        msg.append(flag);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlag_Last(boolean contestActive, int flag,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActiveFlag(contestActive, flag);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFlag(contestActive, flag, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param flag the flag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveFlag_PrevAndNext(long ContestPK,
        boolean contestActive, int flag, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFlag_PrevAndNext(session, contest,
                    contestActive, flag, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFlag_PrevAndNext(session, contest,
                    contestActive, flag, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFlag_PrevAndNext(Session session,
        Contest contest, boolean contestActive, int flag,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFLAG_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAG_FLAG_2);

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
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        qPos.add(flag);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contests where contestActive = &#63; and flag = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveFlag(boolean contestActive, int flag)
        throws SystemException {
        for (Contest contest : findByActiveFlag(contestActive, flag,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and flag = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveFlag(boolean contestActive, int flag)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFLAG;

        Object[] finderArgs = new Object[] { contestActive, flag };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAG_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAG_FLAG_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(flag);

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
     * Returns all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagContest(boolean contestActive,
        int flag, boolean contestPrivate) throws SystemException {
        return findByActiveFlagContest(contestActive, flag, contestPrivate,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagContest(boolean contestActive,
        int flag, boolean contestPrivate, int start, int end)
        throws SystemException {
        return findByActiveFlagContest(contestActive, flag, contestPrivate,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagContest(boolean contestActive,
        int flag, boolean contestPrivate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGCONTEST;
            finderArgs = new Object[] { contestActive, flag, contestPrivate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGCONTEST;
            finderArgs = new Object[] {
                    contestActive, flag, contestPrivate,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (flag != contest.getFlag()) ||
                        (contestPrivate != contest.getContestPrivate())) {
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

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGCONTEST_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGCONTEST_FLAG_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGCONTEST_CONTESTPRIVATE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(flag);

                qPos.add(contestPrivate);

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlagContest_First(boolean contestActive,
        int flag, boolean contestPrivate, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagContest_First(contestActive, flag,
                contestPrivate, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flag=");
        msg.append(flag);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlagContest_First(boolean contestActive,
        int flag, boolean contestPrivate, OrderByComparator orderByComparator)
        throws SystemException {
        List<Contest> list = findByActiveFlagContest(contestActive, flag,
                contestPrivate, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlagContest_Last(boolean contestActive,
        int flag, boolean contestPrivate, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagContest_Last(contestActive, flag,
                contestPrivate, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flag=");
        msg.append(flag);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlagContest_Last(boolean contestActive,
        int flag, boolean contestPrivate, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByActiveFlagContest(contestActive, flag, contestPrivate);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFlagContest(contestActive, flag,
                contestPrivate, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveFlagContest_PrevAndNext(long ContestPK,
        boolean contestActive, int flag, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFlagContest_PrevAndNext(session, contest,
                    contestActive, flag, contestPrivate, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFlagContest_PrevAndNext(session, contest,
                    contestActive, flag, contestPrivate, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFlagContest_PrevAndNext(Session session,
        Contest contest, boolean contestActive, int flag,
        boolean contestPrivate, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFLAGCONTEST_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAGCONTEST_FLAG_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAGCONTEST_CONTESTPRIVATE_2);

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
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        qPos.add(flag);

        qPos.add(contestPrivate);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveFlagContest(boolean contestActive, int flag,
        boolean contestPrivate) throws SystemException {
        for (Contest contest : findByActiveFlagContest(contestActive, flag,
                contestPrivate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveFlagContest(boolean contestActive, int flag,
        boolean contestPrivate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFLAGCONTEST;

        Object[] finderArgs = new Object[] { contestActive, flag, contestPrivate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGCONTEST_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGCONTEST_FLAG_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGCONTEST_CONTESTPRIVATE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(flag);

                qPos.add(contestPrivate);

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
     * Returns all the contests where contestActive = &#63; and flagText = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagText(boolean contestActive,
        String flagText) throws SystemException {
        return findByActiveFlagText(contestActive, flagText, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and flagText = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagText(boolean contestActive,
        String flagText, int start, int end) throws SystemException {
        return findByActiveFlagText(contestActive, flagText, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and flagText = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagText(boolean contestActive,
        String flagText, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXT;
            finderArgs = new Object[] { contestActive, flagText };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGTEXT;
            finderArgs = new Object[] {
                    contestActive, flagText,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        !Validator.equals(flagText, contest.getFlagText())) {
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

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_CONTESTACTIVE_2);

            boolean bindFlagText = false;

            if (flagText == null) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_1);
            } else if (flagText.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_3);
            } else {
                bindFlagText = true;

                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                if (bindFlagText) {
                    qPos.add(flagText);
                }

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlagText_First(boolean contestActive,
        String flagText, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagText_First(contestActive, flagText,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flagText=");
        msg.append(flagText);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlagText_First(boolean contestActive,
        String flagText, OrderByComparator orderByComparator)
        throws SystemException {
        List<Contest> list = findByActiveFlagText(contestActive, flagText, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlagText_Last(boolean contestActive,
        String flagText, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagText_Last(contestActive, flagText,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flagText=");
        msg.append(flagText);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlagText_Last(boolean contestActive,
        String flagText, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByActiveFlagText(contestActive, flagText);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFlagText(contestActive, flagText,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flagText = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveFlagText_PrevAndNext(long ContestPK,
        boolean contestActive, String flagText,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFlagText_PrevAndNext(session, contest,
                    contestActive, flagText, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFlagText_PrevAndNext(session, contest,
                    contestActive, flagText, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFlagText_PrevAndNext(Session session,
        Contest contest, boolean contestActive, String flagText,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_CONTESTACTIVE_2);

        boolean bindFlagText = false;

        if (flagText == null) {
            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_1);
        } else if (flagText.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_3);
        } else {
            bindFlagText = true;

            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_2);
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
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        if (bindFlagText) {
            qPos.add(flagText);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contests where contestActive = &#63; and flagText = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveFlagText(boolean contestActive, String flagText)
        throws SystemException {
        for (Contest contest : findByActiveFlagText(contestActive, flagText,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and flagText = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveFlagText(boolean contestActive, String flagText)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXT;

        Object[] finderArgs = new Object[] { contestActive, flagText };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_CONTESTACTIVE_2);

            boolean bindFlagText = false;

            if (flagText == null) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_1);
            } else if (flagText.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_3);
            } else {
                bindFlagText = true;

                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                if (bindFlagText) {
                    qPos.add(flagText);
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
     * Returns all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param contestPrivate the contest private
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagTextcontestPrivate(
        boolean contestActive, String flagText, boolean contestPrivate)
        throws SystemException {
        return findByActiveFlagTextcontestPrivate(contestActive, flagText,
            contestPrivate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param contestPrivate the contest private
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagTextcontestPrivate(
        boolean contestActive, String flagText, boolean contestPrivate,
        int start, int end) throws SystemException {
        return findByActiveFlagTextcontestPrivate(contestActive, flagText,
            contestPrivate, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param contestPrivate the contest private
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagTextcontestPrivate(
        boolean contestActive, String flagText, boolean contestPrivate,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXTCONTESTPRIVATE;
            finderArgs = new Object[] { contestActive, flagText, contestPrivate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGTEXTCONTESTPRIVATE;
            finderArgs = new Object[] {
                    contestActive, flagText, contestPrivate,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        !Validator.equals(flagText, contest.getFlagText()) ||
                        (contestPrivate != contest.getContestPrivate())) {
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

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_CONTESTACTIVE_2);

            boolean bindFlagText = false;

            if (flagText == null) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_1);
            } else if (flagText.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_3);
            } else {
                bindFlagText = true;

                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_2);
            }

            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_CONTESTPRIVATE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                if (bindFlagText) {
                    qPos.add(flagText);
                }

                qPos.add(contestPrivate);

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlagTextcontestPrivate_First(
        boolean contestActive, String flagText, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagTextcontestPrivate_First(contestActive,
                flagText, contestPrivate, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flagText=");
        msg.append(flagText);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlagTextcontestPrivate_First(
        boolean contestActive, String flagText, boolean contestPrivate,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByActiveFlagTextcontestPrivate(contestActive,
                flagText, contestPrivate, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlagTextcontestPrivate_Last(
        boolean contestActive, String flagText, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagTextcontestPrivate_Last(contestActive,
                flagText, contestPrivate, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flagText=");
        msg.append(flagText);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlagTextcontestPrivate_Last(
        boolean contestActive, String flagText, boolean contestPrivate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActiveFlagTextcontestPrivate(contestActive,
                flagText, contestPrivate);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFlagTextcontestPrivate(contestActive,
                flagText, contestPrivate, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param contestPrivate the contest private
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveFlagTextcontestPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, String flagText,
        boolean contestPrivate, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFlagTextcontestPrivate_PrevAndNext(session,
                    contest, contestActive, flagText, contestPrivate,
                    orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFlagTextcontestPrivate_PrevAndNext(session,
                    contest, contestActive, flagText, contestPrivate,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFlagTextcontestPrivate_PrevAndNext(
        Session session, Contest contest, boolean contestActive,
        String flagText, boolean contestPrivate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_CONTESTACTIVE_2);

        boolean bindFlagText = false;

        if (flagText == null) {
            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_1);
        } else if (flagText.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_3);
        } else {
            bindFlagText = true;

            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_2);
        }

        query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_CONTESTPRIVATE_2);

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
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        if (bindFlagText) {
            qPos.add(flagText);
        }

        qPos.add(contestPrivate);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param contestPrivate the contest private
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveFlagTextcontestPrivate(boolean contestActive,
        String flagText, boolean contestPrivate) throws SystemException {
        for (Contest contest : findByActiveFlagTextcontestPrivate(
                contestActive, flagText, contestPrivate, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param contestPrivate the contest private
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveFlagTextcontestPrivate(boolean contestActive,
        String flagText, boolean contestPrivate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXTCONTESTPRIVATE;

        Object[] finderArgs = new Object[] {
                contestActive, flagText, contestPrivate
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_CONTESTACTIVE_2);

            boolean bindFlagText = false;

            if (flagText == null) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_1);
            } else if (flagText.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_3);
            } else {
                bindFlagText = true;

                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_FLAGTEXT_2);
            }

            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXTCONTESTPRIVATE_CONTESTPRIVATE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                if (bindFlagText) {
                    qPos.add(flagText);
                }

                qPos.add(contestPrivate);

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
     * Caches the contest in the entity cache if it is enabled.
     *
     * @param contest the contest
     */
    @Override
    public void cacheResult(Contest contest) {
        EntityCacheUtil.putResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestImpl.class, contest.getPrimaryKey(), contest);

        contest.resetOriginalValues();
    }

    /**
     * Caches the contests in the entity cache if it is enabled.
     *
     * @param contests the contests
     */
    @Override
    public void cacheResult(List<Contest> contests) {
        for (Contest contest : contests) {
            if (EntityCacheUtil.getResult(
                        ContestModelImpl.ENTITY_CACHE_ENABLED,
                        ContestImpl.class, contest.getPrimaryKey()) == null) {
                cacheResult(contest);
            } else {
                contest.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contests.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Contest contest) {
        EntityCacheUtil.removeResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestImpl.class, contest.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<Contest> contests) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Contest contest : contests) {
            EntityCacheUtil.removeResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
                ContestImpl.class, contest.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest with the primary key. Does not add the contest to the database.
     *
     * @param ContestPK the primary key for the new contest
     * @return the new contest
     */
    @Override
    public Contest create(long ContestPK) {
        Contest contest = new ContestImpl();

        contest.setNew(true);
        contest.setPrimaryKey(ContestPK);

        return contest;
    }

    /**
     * Removes the contest with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ContestPK the primary key of the contest
     * @return the contest that was removed
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest remove(long ContestPK)
        throws NoSuchContestException, SystemException {
        return remove((Serializable) ContestPK);
    }

    /**
     * Removes the contest with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest
     * @return the contest that was removed
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest remove(Serializable primaryKey)
        throws NoSuchContestException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Contest contest = (Contest) session.get(ContestImpl.class,
                    primaryKey);

            if (contest == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contest);
        } catch (NoSuchContestException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Contest removeImpl(Contest contest) throws SystemException {
        contest = toUnwrappedModel(contest);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contest)) {
                contest = (Contest) session.get(ContestImpl.class,
                        contest.getPrimaryKeyObj());
            }

            if (contest != null) {
                session.delete(contest);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contest != null) {
            clearCache(contest);
        }

        return contest;
    }

    @Override
    public Contest updateImpl(com.ext.portlet.model.Contest contest)
        throws SystemException {
        contest = toUnwrappedModel(contest);

        boolean isNew = contest.isNew();

        ContestModelImpl contestModelImpl = (ContestModelImpl) contest;

        Session session = null;

        try {
            session = openSession();

            if (contest.isNew()) {
                session.save(contest);

                contest.setNew(false);
            } else {
                session.merge(contest);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ContestModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalPlanTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
                    args);

                args = new Object[] { contestModelImpl.getPlanTypeId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTTIER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestTier()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTTIER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTTIER,
                    args);

                args = new Object[] { contestModelImpl.getContestTier() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTTIER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTTIER,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTACTIVECONTESTPRIVATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTACTIVECONTESTPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTACTIVECONTESTPRIVATE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTACTIVECONTESTPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTACTIVECONTESTPRIVATE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTACTIVE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTACTIVE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTACTIVE,
                    args);

                args = new Object[] { contestModelImpl.getContestActive() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTACTIVE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTACTIVE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATURED.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFeatured()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATURED,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATURED,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFeatured()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATURED,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATURED,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDCONTESTPRIVATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFeatured(),
                        contestModelImpl.getOriginalContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATUREDCONTESTPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDCONTESTPRIVATE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFeatured(),
                        contestModelImpl.getContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATUREDCONTESTPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDCONTESTPRIVATE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAG.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFlag()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAG,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAG,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFlag()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAG,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAG,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGCONTEST.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFlag(),
                        contestModelImpl.getOriginalContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGCONTEST,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGCONTEST,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFlag(),
                        contestModelImpl.getContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGCONTEST,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGCONTEST,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXT.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFlagText()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXT,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXT,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFlagText()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXT,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXT,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXTCONTESTPRIVATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFlagText(),
                        contestModelImpl.getOriginalContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXTCONTESTPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXTCONTESTPRIVATE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFlagText(),
                        contestModelImpl.getContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXTCONTESTPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXTCONTESTPRIVATE,
                    args);
            }
        }

        EntityCacheUtil.putResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestImpl.class, contest.getPrimaryKey(), contest);

        return contest;
    }

    protected Contest toUnwrappedModel(Contest contest) {
        if (contest instanceof ContestImpl) {
            return contest;
        }

        ContestImpl contestImpl = new ContestImpl();

        contestImpl.setNew(contest.isNew());
        contestImpl.setPrimaryKey(contest.getPrimaryKey());

        contestImpl.setContestPK(contest.getContestPK());
        contestImpl.setContestName(contest.getContestName());
        contestImpl.setContestShortName(contest.getContestShortName());
        contestImpl.setContestDescription(contest.getContestDescription());
        contestImpl.setContestModelDescription(contest.getContestModelDescription());
        contestImpl.setContestPositionsDescription(contest.getContestPositionsDescription());
        contestImpl.setDefaultPlanDescription(contest.getDefaultPlanDescription());
        contestImpl.setPlanTypeId(contest.getPlanTypeId());
        contestImpl.setCreated(contest.getCreated());
        contestImpl.setUpdated(contest.getUpdated());
        contestImpl.setAuthorId(contest.getAuthorId());
        contestImpl.setContestActive(contest.isContestActive());
        contestImpl.setPlanTemplateId(contest.getPlanTemplateId());
        contestImpl.setContestScheduleId(contest.getContestScheduleId());
        contestImpl.setFocusAreaId(contest.getFocusAreaId());
        contestImpl.setContestTier(contest.getContestTier());
        contestImpl.setContestLogoId(contest.getContestLogoId());
        contestImpl.setFeatured(contest.isFeatured());
        contestImpl.setPlansOpenByDefault(contest.isPlansOpenByDefault());
        contestImpl.setSponsorLogoId(contest.getSponsorLogoId());
        contestImpl.setSponsorText(contest.getSponsorText());
        contestImpl.setSponsorLink(contest.getSponsorLink());
        contestImpl.setFlag(contest.getFlag());
        contestImpl.setFlagText(contest.getFlagText());
        contestImpl.setFlagTooltip(contest.getFlagTooltip());
        contestImpl.setGroupId(contest.getGroupId());
        contestImpl.setDiscussionGroupId(contest.getDiscussionGroupId());
        contestImpl.setWeight(contest.getWeight());
        contestImpl.setResourcesUrl(contest.getResourcesUrl());
        contestImpl.setContestPrivate(contest.isContestPrivate());
        contestImpl.setUsePermissions(contest.isUsePermissions());
        contestImpl.setContestCreationStatus(contest.getContestCreationStatus());
        contestImpl.setDefaultModelId(contest.getDefaultModelId());
        contestImpl.setOtherModels(contest.getOtherModels());
        contestImpl.setPoints(contest.getPoints());
        contestImpl.setDefaultParentPointType(contest.getDefaultParentPointType());
        contestImpl.setPointDistributionStrategy(contest.getPointDistributionStrategy());

        return contestImpl;
    }

    /**
     * Returns the contest with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest
     * @return the contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByPrimaryKey(primaryKey);

        if (contest == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contest;
    }

    /**
     * Returns the contest with the primary key or throws a {@link com.ext.portlet.NoSuchContestException} if it could not be found.
     *
     * @param ContestPK the primary key of the contest
     * @return the contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByPrimaryKey(long ContestPK)
        throws NoSuchContestException, SystemException {
        return findByPrimaryKey((Serializable) ContestPK);
    }

    /**
     * Returns the contest with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest
     * @return the contest, or <code>null</code> if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        Contest contest = (Contest) EntityCacheUtil.getResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
                ContestImpl.class, primaryKey);

        if (contest == _nullContest) {
            return null;
        }

        if (contest == null) {
            Session session = null;

            try {
                session = openSession();

                contest = (Contest) session.get(ContestImpl.class, primaryKey);

                if (contest != null) {
                    cacheResult(contest);
                } else {
                    EntityCacheUtil.putResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
                        ContestImpl.class, primaryKey, _nullContest);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
                    ContestImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contest;
    }

    /**
     * Returns the contest with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ContestPK the primary key of the contest
     * @return the contest, or <code>null</code> if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByPrimaryKey(long ContestPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) ContestPK);
    }

    /**
     * Returns all the contests.
     *
     * @return the contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contests.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findAll(int start, int end,
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

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTEST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTEST;

                if (pagination) {
                    sql = sql.concat(ContestModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Contest>(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
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
     * Removes all the contests from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (Contest contest : findAll()) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests.
     *
     * @return the number of contests
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

                Query q = session.createQuery(_SQL_COUNT_CONTEST);

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
     * Initializes the contest persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.Contest")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Contest>> listenersList = new ArrayList<ModelListener<Contest>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Contest>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
