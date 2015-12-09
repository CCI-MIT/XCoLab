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
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TIER = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTier",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIER = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTier",
            new String[] { Long.class.getName() },
            ContestModelImpl.CONTESTTIER_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TIER = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTier",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_TIER_CONTESTTIER_2 = "contest.contestTier = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TIERTYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTierType",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIERTYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTierType",
            new String[] { Long.class.getName(), Long.class.getName() },
            ContestModelImpl.CONTESTTIER_COLUMN_BITMASK |
            ContestModelImpl.CONTESTTYPEID_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TIERTYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTierType",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_TIERTYPE_CONTESTTIER_2 = "contest.contestTier = ? AND ";
    private static final String _FINDER_COLUMN_TIERTYPE_CONTESTTYPEID_2 = "contest.contestTypeId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActivePrivate",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActivePrivate",
            new String[] { Boolean.class.getName(), Boolean.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.CONTESTPRIVATE_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEPRIVATE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActivePrivate",
            new String[] { Boolean.class.getName(), Boolean.class.getName() });
    private static final String _FINDER_COLUMN_ACTIVEPRIVATE_CONTESTACTIVE_2 = "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEPRIVATE_CONTESTPRIVATE_2 = "contest.contestPrivate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEPRIVATETYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActivePrivateType",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEPRIVATETYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActivePrivateType",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Long.class.getName()
            },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.CONTESTPRIVATE_COLUMN_BITMASK |
            ContestModelImpl.CONTESTTYPEID_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEPRIVATETYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActivePrivateType",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Long.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTACTIVE_2 =
        "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTPRIVATE_2 =
        "contest.contestPrivate = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTTYPEID_2 =
        "contest.contestTypeId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActive",
            new String[] {
                Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActive",
            new String[] { Boolean.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActive",
            new String[] { Boolean.class.getName() });
    private static final String _FINDER_COLUMN_ACTIVE_CONTESTACTIVE_2 = "contest.contestActive = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVETYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveType",
            new String[] {
                Boolean.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVETYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveType",
            new String[] { Boolean.class.getName(), Long.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.CONTESTTYPEID_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVETYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveType",
            new String[] { Boolean.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_ACTIVETYPE_CONTESTACTIVE_2 = "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVETYPE_CONTESTTYPEID_2 = "contest.contestTypeId = ?";
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
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATUREDTYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveFeaturedType",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDTYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActiveFeaturedType",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Long.class.getName()
            },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FEATURED_COLUMN_BITMASK |
            ContestModelImpl.CONTESTTYPEID_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFEATUREDTYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActiveFeaturedType",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Long.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDTYPE_CONTESTACTIVE_2 =
        "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDTYPE_FEATURED_2 = "contest.featured = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDTYPE_CONTESTTYPEID_2 =
        "contest.contestTypeId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByActiveFeaturedPrivate",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActiveFeaturedPrivate",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Boolean.class.getName()
            },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FEATURED_COLUMN_BITMASK |
            ContestModelImpl.CONTESTPRIVATE_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFEATUREDPRIVATE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActiveFeaturedPrivate",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Boolean.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_CONTESTACTIVE_2 =
        "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_FEATURED_2 = "contest.featured = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_CONTESTPRIVATE_2 =
        "contest.contestPrivate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATETYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByActiveFeaturedPrivateType",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Boolean.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATETYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActiveFeaturedPrivateType",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Boolean.class.getName(), Long.class.getName()
            },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FEATURED_COLUMN_BITMASK |
            ContestModelImpl.CONTESTPRIVATE_COLUMN_BITMASK |
            ContestModelImpl.CONTESTTYPEID_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFEATUREDPRIVATETYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActiveFeaturedPrivateType",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                Boolean.class.getName(), Long.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTACTIVE_2 =
        "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_FEATURED_2 =
        "contest.featured = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTPRIVATE_2 =
        "contest.contestPrivate = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTTYPEID_2 =
        "contest.contestTypeId = ?";
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
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGTYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveFlagType",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveFlagType",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Long.class.getName()
            },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FLAG_COLUMN_BITMASK |
            ContestModelImpl.CONTESTTYPEID_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFLAGTYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveFlagType",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Long.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIVEFLAGTYPE_CONTESTACTIVE_2 = "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTYPE_FLAG_2 = "contest.flag = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTYPE_CONTESTTYPEID_2 = "contest.contestTypeId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveFlagPrivate",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActiveFlagPrivate",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Boolean.class.getName()
            },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FLAG_COLUMN_BITMASK |
            ContestModelImpl.CONTESTPRIVATE_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFLAGPRIVATE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActiveFlagPrivate",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Boolean.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIVEFLAGPRIVATE_CONTESTACTIVE_2 =
        "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGPRIVATE_FLAG_2 = "contest.flag = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGPRIVATE_CONTESTPRIVATE_2 =
        "contest.contestPrivate = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATETYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByActiveFlagPrivateType",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Boolean.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATETYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActiveFlagPrivateType",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Boolean.class.getName(), Long.class.getName()
            },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FLAG_COLUMN_BITMASK |
            ContestModelImpl.CONTESTPRIVATE_COLUMN_BITMASK |
            ContestModelImpl.CONTESTTYPEID_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFLAGPRIVATETYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActiveFlagPrivateType",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                Boolean.class.getName(), Long.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTACTIVE_2 =
        "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_FLAG_2 = "contest.flag = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTPRIVATE_2 =
        "contest.contestPrivate = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTTYPEID_2 =
        "contest.contestTypeId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTTYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestType",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTTYPE =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContestType",
            new String[] { Long.class.getName() },
            ContestModelImpl.CONTESTTYPEID_COLUMN_BITMASK |
            ContestModelImpl.WEIGHT_COLUMN_BITMASK |
            ContestModelImpl.CREATED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTTYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContestType",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTTYPE_CONTESTTYPEID_2 = "contest.contestTypeId = ?";
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
     * Returns all the contests where contestTier = &#63;.
     *
     * @param contestTier the contest tier
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByTier(long contestTier) throws SystemException {
        return findByTier(contestTier, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
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
    public List<Contest> findByTier(long contestTier, int start, int end)
        throws SystemException {
        return findByTier(contestTier, start, end, null);
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
    public List<Contest> findByTier(long contestTier, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIER;
            finderArgs = new Object[] { contestTier };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TIER;
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

            query.append(_FINDER_COLUMN_TIER_CONTESTTIER_2);

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
    public Contest findByTier_First(long contestTier,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByTier_First(contestTier, orderByComparator);

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
    public Contest fetchByTier_First(long contestTier,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByTier(contestTier, 0, 1, orderByComparator);

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
    public Contest findByTier_Last(long contestTier,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByTier_Last(contestTier, orderByComparator);

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
    public Contest fetchByTier_Last(long contestTier,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByTier(contestTier);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByTier(contestTier, count - 1, count,
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
    public Contest[] findByTier_PrevAndNext(long ContestPK, long contestTier,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByTier_PrevAndNext(session, contest, contestTier,
                    orderByComparator, true);

            array[1] = contest;

            array[2] = getByTier_PrevAndNext(session, contest, contestTier,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByTier_PrevAndNext(Session session, Contest contest,
        long contestTier, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_TIER_CONTESTTIER_2);

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
    public void removeByTier(long contestTier) throws SystemException {
        for (Contest contest : findByTier(contestTier, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
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
    public int countByTier(long contestTier) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TIER;

        Object[] finderArgs = new Object[] { contestTier };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_TIER_CONTESTTIER_2);

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
     * Returns all the contests where contestTier = &#63; and contestTypeId = &#63;.
     *
     * @param contestTier the contest tier
     * @param contestTypeId the contest type ID
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByTierType(long contestTier, long contestTypeId)
        throws SystemException {
        return findByTierType(contestTier, contestTypeId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestTier = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestTier the contest tier
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByTierType(long contestTier, long contestTypeId,
        int start, int end) throws SystemException {
        return findByTierType(contestTier, contestTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestTier = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestTier the contest tier
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByTierType(long contestTier, long contestTypeId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIERTYPE;
            finderArgs = new Object[] { contestTier, contestTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TIERTYPE;
            finderArgs = new Object[] {
                    contestTier, contestTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestTier != contest.getContestTier()) ||
                        (contestTypeId != contest.getContestTypeId())) {
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

            query.append(_FINDER_COLUMN_TIERTYPE_CONTESTTIER_2);

            query.append(_FINDER_COLUMN_TIERTYPE_CONTESTTYPEID_2);

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

                qPos.add(contestTypeId);

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
     * Returns the first contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
     *
     * @param contestTier the contest tier
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByTierType_First(long contestTier, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByTierType_First(contestTier, contestTypeId,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestTier=");
        msg.append(contestTier);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
     *
     * @param contestTier the contest tier
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByTierType_First(long contestTier, long contestTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByTierType(contestTier, contestTypeId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
     *
     * @param contestTier the contest tier
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByTierType_Last(long contestTier, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByTierType_Last(contestTier, contestTypeId,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestTier=");
        msg.append(contestTier);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
     *
     * @param contestTier the contest tier
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByTierType_Last(long contestTier, long contestTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByTierType(contestTier, contestTypeId);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByTierType(contestTier, contestTypeId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestTier the contest tier
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByTierType_PrevAndNext(long ContestPK,
        long contestTier, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByTierType_PrevAndNext(session, contest, contestTier,
                    contestTypeId, orderByComparator, true);

            array[1] = contest;

            array[2] = getByTierType_PrevAndNext(session, contest, contestTier,
                    contestTypeId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByTierType_PrevAndNext(Session session,
        Contest contest, long contestTier, long contestTypeId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_TIERTYPE_CONTESTTIER_2);

        query.append(_FINDER_COLUMN_TIERTYPE_CONTESTTYPEID_2);

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

        qPos.add(contestTypeId);

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
     * Removes all the contests where contestTier = &#63; and contestTypeId = &#63; from the database.
     *
     * @param contestTier the contest tier
     * @param contestTypeId the contest type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByTierType(long contestTier, long contestTypeId)
        throws SystemException {
        for (Contest contest : findByTierType(contestTier, contestTypeId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestTier = &#63; and contestTypeId = &#63;.
     *
     * @param contestTier the contest tier
     * @param contestTypeId the contest type ID
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTierType(long contestTier, long contestTypeId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TIERTYPE;

        Object[] finderArgs = new Object[] { contestTier, contestTypeId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_TIERTYPE_CONTESTTIER_2);

            query.append(_FINDER_COLUMN_TIERTYPE_CONTESTTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestTier);

                qPos.add(contestTypeId);

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
    public List<Contest> findByActivePrivate(boolean contestActive,
        boolean contestPrivate) throws SystemException {
        return findByActivePrivate(contestActive, contestPrivate,
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
    public List<Contest> findByActivePrivate(boolean contestActive,
        boolean contestPrivate, int start, int end) throws SystemException {
        return findByActivePrivate(contestActive, contestPrivate, start, end,
            null);
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
    public List<Contest> findByActivePrivate(boolean contestActive,
        boolean contestPrivate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEPRIVATE;
            finderArgs = new Object[] { contestActive, contestPrivate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEPRIVATE;
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

            query.append(_FINDER_COLUMN_ACTIVEPRIVATE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEPRIVATE_CONTESTPRIVATE_2);

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
    public Contest findByActivePrivate_First(boolean contestActive,
        boolean contestPrivate, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActivePrivate_First(contestActive,
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
    public Contest fetchByActivePrivate_First(boolean contestActive,
        boolean contestPrivate, OrderByComparator orderByComparator)
        throws SystemException {
        List<Contest> list = findByActivePrivate(contestActive, contestPrivate,
                0, 1, orderByComparator);

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
    public Contest findByActivePrivate_Last(boolean contestActive,
        boolean contestPrivate, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActivePrivate_Last(contestActive,
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
    public Contest fetchByActivePrivate_Last(boolean contestActive,
        boolean contestPrivate, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByActivePrivate(contestActive, contestPrivate);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActivePrivate(contestActive, contestPrivate,
                count - 1, count, orderByComparator);

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
    public Contest[] findByActivePrivate_PrevAndNext(long ContestPK,
        boolean contestActive, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActivePrivate_PrevAndNext(session, contest,
                    contestActive, contestPrivate, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActivePrivate_PrevAndNext(session, contest,
                    contestActive, contestPrivate, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActivePrivate_PrevAndNext(Session session,
        Contest contest, boolean contestActive, boolean contestPrivate,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEPRIVATE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEPRIVATE_CONTESTPRIVATE_2);

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
    public void removeByActivePrivate(boolean contestActive,
        boolean contestPrivate) throws SystemException {
        for (Contest contest : findByActivePrivate(contestActive,
                contestPrivate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
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
    public int countByActivePrivate(boolean contestActive,
        boolean contestPrivate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEPRIVATE;

        Object[] finderArgs = new Object[] { contestActive, contestPrivate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEPRIVATE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEPRIVATE_CONTESTPRIVATE_2);

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
     * Returns all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActivePrivateType(boolean contestActive,
        boolean contestPrivate, long contestTypeId) throws SystemException {
        return findByActivePrivateType(contestActive, contestPrivate,
            contestTypeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActivePrivateType(boolean contestActive,
        boolean contestPrivate, long contestTypeId, int start, int end)
        throws SystemException {
        return findByActivePrivateType(contestActive, contestPrivate,
            contestTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActivePrivateType(boolean contestActive,
        boolean contestPrivate, long contestTypeId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEPRIVATETYPE;
            finderArgs = new Object[] {
                    contestActive, contestPrivate, contestTypeId
                };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEPRIVATETYPE;
            finderArgs = new Object[] {
                    contestActive, contestPrivate, contestTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (contestPrivate != contest.getContestPrivate()) ||
                        (contestTypeId != contest.getContestTypeId())) {
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

            query.append(_FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTPRIVATE_2);

            query.append(_FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTTYPEID_2);

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

                qPos.add(contestTypeId);

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
     * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActivePrivateType_First(boolean contestActive,
        boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActivePrivateType_First(contestActive,
                contestPrivate, contestTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActivePrivateType_First(boolean contestActive,
        boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByActivePrivateType(contestActive,
                contestPrivate, contestTypeId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActivePrivateType_Last(boolean contestActive,
        boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActivePrivateType_Last(contestActive,
                contestPrivate, contestTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActivePrivateType_Last(boolean contestActive,
        boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActivePrivateType(contestActive, contestPrivate,
                contestTypeId);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActivePrivateType(contestActive,
                contestPrivate, contestTypeId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActivePrivateType_PrevAndNext(long ContestPK,
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActivePrivateType_PrevAndNext(session, contest,
                    contestActive, contestPrivate, contestTypeId,
                    orderByComparator, true);

            array[1] = contest;

            array[2] = getByActivePrivateType_PrevAndNext(session, contest,
                    contestActive, contestPrivate, contestTypeId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActivePrivateType_PrevAndNext(Session session,
        Contest contest, boolean contestActive, boolean contestPrivate,
        long contestTypeId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTPRIVATE_2);

        query.append(_FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTTYPEID_2);

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

        qPos.add(contestTypeId);

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
     * Removes all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActivePrivateType(boolean contestActive,
        boolean contestPrivate, long contestTypeId) throws SystemException {
        for (Contest contest : findByActivePrivateType(contestActive,
                contestPrivate, contestTypeId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActivePrivateType(boolean contestActive,
        boolean contestPrivate, long contestTypeId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEPRIVATETYPE;

        Object[] finderArgs = new Object[] {
                contestActive, contestPrivate, contestTypeId
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTPRIVATE_2);

            query.append(_FINDER_COLUMN_ACTIVEPRIVATETYPE_CONTESTTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(contestPrivate);

                qPos.add(contestTypeId);

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
    public List<Contest> findByActive(boolean contestActive)
        throws SystemException {
        return findByActive(contestActive, QueryUtil.ALL_POS,
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
    public List<Contest> findByActive(boolean contestActive, int start, int end)
        throws SystemException {
        return findByActive(contestActive, start, end, null);
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
    public List<Contest> findByActive(boolean contestActive, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE;
            finderArgs = new Object[] { contestActive };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVE;
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

            query.append(_FINDER_COLUMN_ACTIVE_CONTESTACTIVE_2);

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
    public Contest findByActive_First(boolean contestActive,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActive_First(contestActive, orderByComparator);

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
    public Contest fetchByActive_First(boolean contestActive,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByActive(contestActive, 0, 1, orderByComparator);

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
    public Contest findByActive_Last(boolean contestActive,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActive_Last(contestActive, orderByComparator);

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
    public Contest fetchByActive_Last(boolean contestActive,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActive(contestActive);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActive(contestActive, count - 1, count,
                orderByComparator);

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
    public Contest[] findByActive_PrevAndNext(long ContestPK,
        boolean contestActive, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActive_PrevAndNext(session, contest, contestActive,
                    orderByComparator, true);

            array[1] = contest;

            array[2] = getByActive_PrevAndNext(session, contest, contestActive,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActive_PrevAndNext(Session session, Contest contest,
        boolean contestActive, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVE_CONTESTACTIVE_2);

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
    public void removeByActive(boolean contestActive) throws SystemException {
        for (Contest contest : findByActive(contestActive, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
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
    public int countByActive(boolean contestActive) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVE;

        Object[] finderArgs = new Object[] { contestActive };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVE_CONTESTACTIVE_2);

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
     * Returns all the contests where contestActive = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestTypeId the contest type ID
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveType(boolean contestActive,
        long contestTypeId) throws SystemException {
        return findByActiveType(contestActive, contestTypeId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveType(boolean contestActive,
        long contestTypeId, int start, int end) throws SystemException {
        return findByActiveType(contestActive, contestTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveType(boolean contestActive,
        long contestTypeId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVETYPE;
            finderArgs = new Object[] { contestActive, contestTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVETYPE;
            finderArgs = new Object[] {
                    contestActive, contestTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (contestTypeId != contest.getContestTypeId())) {
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

            query.append(_FINDER_COLUMN_ACTIVETYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVETYPE_CONTESTTYPEID_2);

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

                qPos.add(contestTypeId);

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
     * Returns the first contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveType_First(boolean contestActive,
        long contestTypeId, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveType_First(contestActive, contestTypeId,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveType_First(boolean contestActive,
        long contestTypeId, OrderByComparator orderByComparator)
        throws SystemException {
        List<Contest> list = findByActiveType(contestActive, contestTypeId, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveType_Last(boolean contestActive,
        long contestTypeId, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveType_Last(contestActive, contestTypeId,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveType_Last(boolean contestActive,
        long contestTypeId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByActiveType(contestActive, contestTypeId);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveType(contestActive, contestTypeId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveType_PrevAndNext(long ContestPK,
        boolean contestActive, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveType_PrevAndNext(session, contest,
                    contestActive, contestTypeId, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveType_PrevAndNext(session, contest,
                    contestActive, contestTypeId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveType_PrevAndNext(Session session,
        Contest contest, boolean contestActive, long contestTypeId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVETYPE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVETYPE_CONTESTTYPEID_2);

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

        qPos.add(contestTypeId);

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
     * Removes all the contests where contestActive = &#63; and contestTypeId = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param contestTypeId the contest type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveType(boolean contestActive, long contestTypeId)
        throws SystemException {
        for (Contest contest : findByActiveType(contestActive, contestTypeId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param contestTypeId the contest type ID
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveType(boolean contestActive, long contestTypeId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVETYPE;

        Object[] finderArgs = new Object[] { contestActive, contestTypeId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVETYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVETYPE_CONTESTTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(contestTypeId);

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
     * Returns all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestTypeId the contest type ID
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeaturedType(boolean contestActive,
        boolean featured, long contestTypeId) throws SystemException {
        return findByActiveFeaturedType(contestActive, featured, contestTypeId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeaturedType(boolean contestActive,
        boolean featured, long contestTypeId, int start, int end)
        throws SystemException {
        return findByActiveFeaturedType(contestActive, featured, contestTypeId,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeaturedType(boolean contestActive,
        boolean featured, long contestTypeId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDTYPE;
            finderArgs = new Object[] { contestActive, featured, contestTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATUREDTYPE;
            finderArgs = new Object[] {
                    contestActive, featured, contestTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (featured != contest.getFeatured()) ||
                        (contestTypeId != contest.getContestTypeId())) {
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

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDTYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDTYPE_FEATURED_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDTYPE_CONTESTTYPEID_2);

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

                qPos.add(contestTypeId);

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
     * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFeaturedType_First(boolean contestActive,
        boolean featured, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFeaturedType_First(contestActive,
                featured, contestTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", featured=");
        msg.append(featured);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFeaturedType_First(boolean contestActive,
        boolean featured, long contestTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByActiveFeaturedType(contestActive, featured,
                contestTypeId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFeaturedType_Last(boolean contestActive,
        boolean featured, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFeaturedType_Last(contestActive,
                featured, contestTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", featured=");
        msg.append(featured);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFeaturedType_Last(boolean contestActive,
        boolean featured, long contestTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActiveFeaturedType(contestActive, featured,
                contestTypeId);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFeaturedType(contestActive, featured,
                contestTypeId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveFeaturedType_PrevAndNext(long ContestPK,
        boolean contestActive, boolean featured, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFeaturedType_PrevAndNext(session, contest,
                    contestActive, featured, contestTypeId, orderByComparator,
                    true);

            array[1] = contest;

            array[2] = getByActiveFeaturedType_PrevAndNext(session, contest,
                    contestActive, featured, contestTypeId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFeaturedType_PrevAndNext(Session session,
        Contest contest, boolean contestActive, boolean featured,
        long contestTypeId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDTYPE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDTYPE_FEATURED_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDTYPE_CONTESTTYPEID_2);

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

        qPos.add(contestTypeId);

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
     * Removes all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestTypeId the contest type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveFeaturedType(boolean contestActive,
        boolean featured, long contestTypeId) throws SystemException {
        for (Contest contest : findByActiveFeaturedType(contestActive,
                featured, contestTypeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
                null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestTypeId the contest type ID
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveFeaturedType(boolean contestActive,
        boolean featured, long contestTypeId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFEATUREDTYPE;

        Object[] finderArgs = new Object[] {
                contestActive, featured, contestTypeId
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDTYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDTYPE_FEATURED_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDTYPE_CONTESTTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(featured);

                qPos.add(contestTypeId);

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
    public List<Contest> findByActiveFeaturedPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate) throws SystemException {
        return findByActiveFeaturedPrivate(contestActive, featured,
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
    public List<Contest> findByActiveFeaturedPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate, int start, int end)
        throws SystemException {
        return findByActiveFeaturedPrivate(contestActive, featured,
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
    public List<Contest> findByActiveFeaturedPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATE;
            finderArgs = new Object[] { contestActive, featured, contestPrivate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATE;
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

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_FEATURED_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_CONTESTPRIVATE_2);

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
    public Contest findByActiveFeaturedPrivate_First(boolean contestActive,
        boolean featured, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFeaturedPrivate_First(contestActive,
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
    public Contest fetchByActiveFeaturedPrivate_First(boolean contestActive,
        boolean featured, boolean contestPrivate,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByActiveFeaturedPrivate(contestActive,
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
    public Contest findByActiveFeaturedPrivate_Last(boolean contestActive,
        boolean featured, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFeaturedPrivate_Last(contestActive,
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
    public Contest fetchByActiveFeaturedPrivate_Last(boolean contestActive,
        boolean featured, boolean contestPrivate,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActiveFeaturedPrivate(contestActive, featured,
                contestPrivate);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFeaturedPrivate(contestActive,
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
    public Contest[] findByActiveFeaturedPrivate_PrevAndNext(long ContestPK,
        boolean contestActive, boolean featured, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFeaturedPrivate_PrevAndNext(session, contest,
                    contestActive, featured, contestPrivate, orderByComparator,
                    true);

            array[1] = contest;

            array[2] = getByActiveFeaturedPrivate_PrevAndNext(session, contest,
                    contestActive, featured, contestPrivate, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFeaturedPrivate_PrevAndNext(Session session,
        Contest contest, boolean contestActive, boolean featured,
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

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_FEATURED_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_CONTESTPRIVATE_2);

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
    public void removeByActiveFeaturedPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate) throws SystemException {
        for (Contest contest : findByActiveFeaturedPrivate(contestActive,
                featured, contestPrivate, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
                null)) {
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
    public int countByActiveFeaturedPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFEATUREDPRIVATE;

        Object[] finderArgs = new Object[] {
                contestActive, featured, contestPrivate
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_FEATURED_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATE_CONTESTPRIVATE_2);

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
     * Returns all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeaturedPrivateType(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId) throws SystemException {
        return findByActiveFeaturedPrivateType(contestActive, featured,
            contestPrivate, contestTypeId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeaturedPrivateType(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId, int start, int end) throws SystemException {
        return findByActiveFeaturedPrivateType(contestActive, featured,
            contestPrivate, contestTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFeaturedPrivateType(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATETYPE;
            finderArgs = new Object[] {
                    contestActive, featured, contestPrivate, contestTypeId
                };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATETYPE;
            finderArgs = new Object[] {
                    contestActive, featured, contestPrivate, contestTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (featured != contest.getFeatured()) ||
                        (contestPrivate != contest.getContestPrivate()) ||
                        (contestTypeId != contest.getContestTypeId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(6 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(6);
            }

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_FEATURED_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTPRIVATE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTTYPEID_2);

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

                qPos.add(contestTypeId);

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
     * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFeaturedPrivateType_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFeaturedPrivateType_First(contestActive,
                featured, contestPrivate, contestTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(10);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", featured=");
        msg.append(featured);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFeaturedPrivateType_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId, OrderByComparator orderByComparator)
        throws SystemException {
        List<Contest> list = findByActiveFeaturedPrivateType(contestActive,
                featured, contestPrivate, contestTypeId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFeaturedPrivateType_Last(boolean contestActive,
        boolean featured, boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFeaturedPrivateType_Last(contestActive,
                featured, contestPrivate, contestTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(10);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", featured=");
        msg.append(featured);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFeaturedPrivateType_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByActiveFeaturedPrivateType(contestActive, featured,
                contestPrivate, contestTypeId);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFeaturedPrivateType(contestActive,
                featured, contestPrivate, contestTypeId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveFeaturedPrivateType_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFeaturedPrivateType_PrevAndNext(session,
                    contest, contestActive, featured, contestPrivate,
                    contestTypeId, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFeaturedPrivateType_PrevAndNext(session,
                    contest, contestActive, featured, contestPrivate,
                    contestTypeId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFeaturedPrivateType_PrevAndNext(
        Session session, Contest contest, boolean contestActive,
        boolean featured, boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_FEATURED_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTPRIVATE_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTTYPEID_2);

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

        qPos.add(contestTypeId);

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
     * Removes all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveFeaturedPrivateType(boolean contestActive,
        boolean featured, boolean contestPrivate, long contestTypeId)
        throws SystemException {
        for (Contest contest : findByActiveFeaturedPrivateType(contestActive,
                featured, contestPrivate, contestTypeId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveFeaturedPrivateType(boolean contestActive,
        boolean featured, boolean contestPrivate, long contestTypeId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFEATUREDPRIVATETYPE;

        Object[] finderArgs = new Object[] {
                contestActive, featured, contestPrivate, contestTypeId
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_FEATURED_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTPRIVATE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATUREDPRIVATETYPE_CONTESTTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(featured);

                qPos.add(contestPrivate);

                qPos.add(contestTypeId);

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
     * Returns all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestTypeId the contest type ID
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagType(boolean contestActive, int flag,
        long contestTypeId) throws SystemException {
        return findByActiveFlagType(contestActive, flag, contestTypeId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagType(boolean contestActive, int flag,
        long contestTypeId, int start, int end) throws SystemException {
        return findByActiveFlagType(contestActive, flag, contestTypeId, start,
            end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagType(boolean contestActive, int flag,
        long contestTypeId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTYPE;
            finderArgs = new Object[] { contestActive, flag, contestTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGTYPE;
            finderArgs = new Object[] {
                    contestActive, flag, contestTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (flag != contest.getFlag()) ||
                        (contestTypeId != contest.getContestTypeId())) {
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

            query.append(_FINDER_COLUMN_ACTIVEFLAGTYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTYPE_FLAG_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTYPE_CONTESTTYPEID_2);

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

                qPos.add(contestTypeId);

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
     * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlagType_First(boolean contestActive, int flag,
        long contestTypeId, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagType_First(contestActive, flag,
                contestTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flag=");
        msg.append(flag);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlagType_First(boolean contestActive, int flag,
        long contestTypeId, OrderByComparator orderByComparator)
        throws SystemException {
        List<Contest> list = findByActiveFlagType(contestActive, flag,
                contestTypeId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlagType_Last(boolean contestActive, int flag,
        long contestTypeId, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagType_Last(contestActive, flag,
                contestTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flag=");
        msg.append(flag);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlagType_Last(boolean contestActive, int flag,
        long contestTypeId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByActiveFlagType(contestActive, flag, contestTypeId);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFlagType(contestActive, flag,
                contestTypeId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveFlagType_PrevAndNext(long ContestPK,
        boolean contestActive, int flag, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFlagType_PrevAndNext(session, contest,
                    contestActive, flag, contestTypeId, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFlagType_PrevAndNext(session, contest,
                    contestActive, flag, contestTypeId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFlagType_PrevAndNext(Session session,
        Contest contest, boolean contestActive, int flag, long contestTypeId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFLAGTYPE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAGTYPE_FLAG_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAGTYPE_CONTESTTYPEID_2);

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

        qPos.add(contestTypeId);

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
     * Removes all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestTypeId the contest type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveFlagType(boolean contestActive, int flag,
        long contestTypeId) throws SystemException {
        for (Contest contest : findByActiveFlagType(contestActive, flag,
                contestTypeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestTypeId the contest type ID
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveFlagType(boolean contestActive, int flag,
        long contestTypeId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFLAGTYPE;

        Object[] finderArgs = new Object[] { contestActive, flag, contestTypeId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTYPE_FLAG_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTYPE_CONTESTTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(flag);

                qPos.add(contestTypeId);

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
    public List<Contest> findByActiveFlagPrivate(boolean contestActive,
        int flag, boolean contestPrivate) throws SystemException {
        return findByActiveFlagPrivate(contestActive, flag, contestPrivate,
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
    public List<Contest> findByActiveFlagPrivate(boolean contestActive,
        int flag, boolean contestPrivate, int start, int end)
        throws SystemException {
        return findByActiveFlagPrivate(contestActive, flag, contestPrivate,
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
    public List<Contest> findByActiveFlagPrivate(boolean contestActive,
        int flag, boolean contestPrivate, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATE;
            finderArgs = new Object[] { contestActive, flag, contestPrivate };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATE;
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

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATE_FLAG_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATE_CONTESTPRIVATE_2);

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
    public Contest findByActiveFlagPrivate_First(boolean contestActive,
        int flag, boolean contestPrivate, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagPrivate_First(contestActive, flag,
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
    public Contest fetchByActiveFlagPrivate_First(boolean contestActive,
        int flag, boolean contestPrivate, OrderByComparator orderByComparator)
        throws SystemException {
        List<Contest> list = findByActiveFlagPrivate(contestActive, flag,
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
    public Contest findByActiveFlagPrivate_Last(boolean contestActive,
        int flag, boolean contestPrivate, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagPrivate_Last(contestActive, flag,
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
    public Contest fetchByActiveFlagPrivate_Last(boolean contestActive,
        int flag, boolean contestPrivate, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByActiveFlagPrivate(contestActive, flag, contestPrivate);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFlagPrivate(contestActive, flag,
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
    public Contest[] findByActiveFlagPrivate_PrevAndNext(long ContestPK,
        boolean contestActive, int flag, boolean contestPrivate,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFlagPrivate_PrevAndNext(session, contest,
                    contestActive, flag, contestPrivate, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFlagPrivate_PrevAndNext(session, contest,
                    contestActive, flag, contestPrivate, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFlagPrivate_PrevAndNext(Session session,
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

        query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATE_FLAG_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATE_CONTESTPRIVATE_2);

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
    public void removeByActiveFlagPrivate(boolean contestActive, int flag,
        boolean contestPrivate) throws SystemException {
        for (Contest contest : findByActiveFlagPrivate(contestActive, flag,
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
    public int countByActiveFlagPrivate(boolean contestActive, int flag,
        boolean contestPrivate) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFLAGPRIVATE;

        Object[] finderArgs = new Object[] { contestActive, flag, contestPrivate };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATE_FLAG_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATE_CONTESTPRIVATE_2);

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
     * Returns all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagPrivateType(boolean contestActive,
        int flag, boolean contestPrivate, long contestTypeId)
        throws SystemException {
        return findByActiveFlagPrivateType(contestActive, flag, contestPrivate,
            contestTypeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagPrivateType(boolean contestActive,
        int flag, boolean contestPrivate, long contestTypeId, int start, int end)
        throws SystemException {
        return findByActiveFlagPrivateType(contestActive, flag, contestPrivate,
            contestTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByActiveFlagPrivateType(boolean contestActive,
        int flag, boolean contestPrivate, long contestTypeId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATETYPE;
            finderArgs = new Object[] {
                    contestActive, flag, contestPrivate, contestTypeId
                };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATETYPE;
            finderArgs = new Object[] {
                    contestActive, flag, contestPrivate, contestTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestActive != contest.getContestActive()) ||
                        (flag != contest.getFlag()) ||
                        (contestPrivate != contest.getContestPrivate()) ||
                        (contestTypeId != contest.getContestTypeId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(6 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(6);
            }

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_FLAG_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTPRIVATE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTTYPEID_2);

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

                qPos.add(contestTypeId);

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
     * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlagPrivateType_First(boolean contestActive,
        int flag, boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagPrivateType_First(contestActive,
                flag, contestPrivate, contestTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(10);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flag=");
        msg.append(flag);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlagPrivateType_First(boolean contestActive,
        int flag, boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByActiveFlagPrivateType(contestActive, flag,
                contestPrivate, contestTypeId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByActiveFlagPrivateType_Last(boolean contestActive,
        int flag, boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByActiveFlagPrivateType_Last(contestActive,
                flag, contestPrivate, contestTypeId, orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(10);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestActive=");
        msg.append(contestActive);

        msg.append(", flag=");
        msg.append(flag);

        msg.append(", contestPrivate=");
        msg.append(contestPrivate);

        msg.append(", contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByActiveFlagPrivateType_Last(boolean contestActive,
        int flag, boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActiveFlagPrivateType(contestActive, flag,
                contestPrivate, contestTypeId);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByActiveFlagPrivateType(contestActive, flag,
                contestPrivate, contestTypeId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByActiveFlagPrivateType_PrevAndNext(long ContestPK,
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFlagPrivateType_PrevAndNext(session, contest,
                    contestActive, flag, contestPrivate, contestTypeId,
                    orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFlagPrivateType_PrevAndNext(session, contest,
                    contestActive, flag, contestPrivate, contestTypeId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFlagPrivateType_PrevAndNext(Session session,
        Contest contest, boolean contestActive, int flag,
        boolean contestPrivate, long contestTypeId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_FLAG_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTPRIVATE_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTTYPEID_2);

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

        qPos.add(contestTypeId);

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
     * Removes all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActiveFlagPrivateType(boolean contestActive, int flag,
        boolean contestPrivate, long contestTypeId) throws SystemException {
        for (Contest contest : findByActiveFlagPrivateType(contestActive, flag,
                contestPrivate, contestTypeId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param contestPrivate the contest private
     * @param contestTypeId the contest type ID
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActiveFlagPrivateType(boolean contestActive, int flag,
        boolean contestPrivate, long contestTypeId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVEFLAGPRIVATETYPE;

        Object[] finderArgs = new Object[] {
                contestActive, flag, contestPrivate, contestTypeId
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_FLAG_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTPRIVATE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAGPRIVATETYPE_CONTESTTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(flag);

                qPos.add(contestPrivate);

                qPos.add(contestTypeId);

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
     * Returns all the contests where contestTypeId = &#63;.
     *
     * @param contestTypeId the contest type ID
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestType(long contestTypeId)
        throws SystemException {
        return findByContestType(contestTypeId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestType(long contestTypeId, int start,
        int end) throws SystemException {
        return findByContestType(contestTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestTypeId the contest type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Contest> findByContestType(long contestTypeId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTTYPE;
            finderArgs = new Object[] { contestTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTTYPE;
            finderArgs = new Object[] {
                    contestTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Contest contest : list) {
                if ((contestTypeId != contest.getContestTypeId())) {
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

            query.append(_FINDER_COLUMN_CONTESTTYPE_CONTESTTYPEID_2);

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

                qPos.add(contestTypeId);

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
     * Returns the first contest in the ordered set where contestTypeId = &#63;.
     *
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByContestType_First(long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByContestType_First(contestTypeId,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the first contest in the ordered set where contestTypeId = &#63;.
     *
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByContestType_First(long contestTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Contest> list = findByContestType(contestTypeId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last contest in the ordered set where contestTypeId = &#63;.
     *
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByContestType_Last(long contestTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByContestType_Last(contestTypeId,
                orderByComparator);

        if (contest != null) {
            return contest;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestTypeId=");
        msg.append(contestTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchContestException(msg.toString());
    }

    /**
     * Returns the last contest in the ordered set where contestTypeId = &#63;.
     *
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByContestType_Last(long contestTypeId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByContestType(contestTypeId);

        if (count == 0) {
            return null;
        }

        List<Contest> list = findByContestType(contestTypeId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestTypeId = &#63;.
     *
     * @param ContestPK the primary key of the current contest
     * @param contestTypeId the contest type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest[] findByContestType_PrevAndNext(long ContestPK,
        long contestTypeId, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByContestType_PrevAndNext(session, contest,
                    contestTypeId, orderByComparator, true);

            array[1] = contest;

            array[2] = getByContestType_PrevAndNext(session, contest,
                    contestTypeId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByContestType_PrevAndNext(Session session,
        Contest contest, long contestTypeId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_CONTESTTYPE_CONTESTTYPEID_2);

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

        qPos.add(contestTypeId);

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
     * Removes all the contests where contestTypeId = &#63; from the database.
     *
     * @param contestTypeId the contest type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByContestType(long contestTypeId)
        throws SystemException {
        for (Contest contest : findByContestType(contestTypeId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where contestTypeId = &#63;.
     *
     * @param contestTypeId the contest type ID
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestType(long contestTypeId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTTYPE;

        Object[] finderArgs = new Object[] { contestTypeId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_CONTESTTYPE_CONTESTTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestTypeId);

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
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestTier()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TIER, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIER,
                    args);

                args = new Object[] { contestModelImpl.getContestTier() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TIER, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIER,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIERTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestTier(),
                        contestModelImpl.getOriginalContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TIERTYPE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIERTYPE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestTier(),
                        contestModelImpl.getContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TIERTYPE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIERTYPE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEPRIVATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEPRIVATE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEPRIVATE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEPRIVATETYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalContestPrivate(),
                        contestModelImpl.getOriginalContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEPRIVATETYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEPRIVATETYPE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getContestPrivate(),
                        contestModelImpl.getContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEPRIVATETYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEPRIVATETYPE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
                    args);

                args = new Object[] { contestModelImpl.getContestActive() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVETYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVETYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVETYPE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVETYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVETYPE,
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
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFeatured(),
                        contestModelImpl.getOriginalContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATUREDTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDTYPE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFeatured(),
                        contestModelImpl.getContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATUREDTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDTYPE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFeatured(),
                        contestModelImpl.getOriginalContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATUREDPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFeatured(),
                        contestModelImpl.getContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATUREDPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATETYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFeatured(),
                        contestModelImpl.getOriginalContestPrivate(),
                        contestModelImpl.getOriginalContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATUREDPRIVATETYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATETYPE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFeatured(),
                        contestModelImpl.getContestPrivate(),
                        contestModelImpl.getContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATUREDPRIVATETYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATUREDPRIVATETYPE,
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
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFlag(),
                        contestModelImpl.getOriginalContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTYPE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFlag(),
                        contestModelImpl.getContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTYPE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFlag(),
                        contestModelImpl.getOriginalContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFlag(),
                        contestModelImpl.getContestPrivate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGPRIVATE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATETYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestActive(),
                        contestModelImpl.getOriginalFlag(),
                        contestModelImpl.getOriginalContestPrivate(),
                        contestModelImpl.getOriginalContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGPRIVATETYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATETYPE,
                    args);

                args = new Object[] {
                        contestModelImpl.getContestActive(),
                        contestModelImpl.getFlag(),
                        contestModelImpl.getContestPrivate(),
                        contestModelImpl.getContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGPRIVATETYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGPRIVATETYPE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestModelImpl.getOriginalContestTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTTYPE,
                    args);

                args = new Object[] { contestModelImpl.getContestTypeId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTTYPE,
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
        contestImpl.setContestTypeId(contest.getContestTypeId());
        contestImpl.setContestName(contest.getContestName());
        contestImpl.setContestShortName(contest.getContestShortName());
        contestImpl.setContestDescription(contest.getContestDescription());
        contestImpl.setContestModelDescription(contest.getContestModelDescription());
        contestImpl.setContestPositionsDescription(contest.getContestPositionsDescription());
        contestImpl.setCreated(contest.getCreated());
        contestImpl.setUpdated(contest.getUpdated());
        contestImpl.setAuthorId(contest.getAuthorId());
        contestImpl.setContestActive(contest.isContestActive());
        contestImpl.setPlanTemplateId(contest.getPlanTemplateId());
        contestImpl.setContestScheduleId(contest.getContestScheduleId());
        contestImpl.setProposalCreationTemplateString(contest.getProposalCreationTemplateString());
        contestImpl.setVoteTemplateString(contest.getVoteTemplateString());
        contestImpl.setProposalVoteTemplateString(contest.getProposalVoteTemplateString());
        contestImpl.setProposalVoteConfirmationTemplateString(contest.getProposalVoteConfirmationTemplateString());
        contestImpl.setVoteQuestionTemplateString(contest.getVoteQuestionTemplateString());
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
        contestImpl.setFellowDiscussionGroupId(contest.getFellowDiscussionGroupId());
        contestImpl.setWeight(contest.getWeight());
        contestImpl.setResourcesUrl(contest.getResourcesUrl());
        contestImpl.setContestPrivate(contest.isContestPrivate());
        contestImpl.setUsePermissions(contest.isUsePermissions());
        contestImpl.setContestCreationStatus(contest.getContestCreationStatus());
        contestImpl.setDefaultModelId(contest.getDefaultModelId());
        contestImpl.setOtherModels(contest.getOtherModels());
        contestImpl.setDefaultModelSettings(contest.getDefaultModelSettings());
        contestImpl.setPoints(contest.getPoints());
        contestImpl.setDefaultParentPointType(contest.getDefaultParentPointType());
        contestImpl.setPointDistributionStrategy(contest.getPointDistributionStrategy());
        contestImpl.setEmailTemplateUrl(contest.getEmailTemplateUrl());
        contestImpl.setShow_in_tile_view(contest.isShow_in_tile_view());
        contestImpl.setShow_in_list_view(contest.isShow_in_list_view());
        contestImpl.setShow_in_outline_view(contest.isShow_in_outline_view());
        contestImpl.setHideRibbons(contest.isHideRibbons());

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
