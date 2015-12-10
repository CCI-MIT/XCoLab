package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPointsDistributionConfigurationException;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.model.impl.PointsDistributionConfigurationImpl;
import com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl;
import com.ext.portlet.service.persistence.PointsDistributionConfigurationPersistence;

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
 * The persistence implementation for the points distribution configuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointsDistributionConfigurationPersistence
 * @see PointsDistributionConfigurationUtil
 * @generated
 */
public class PointsDistributionConfigurationPersistenceImpl
    extends BasePersistenceImpl<PointsDistributionConfiguration>
    implements PointsDistributionConfigurationPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PointsDistributionConfigurationUtil} to access the points distribution configuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PointsDistributionConfigurationImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETUSERID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTargetUserId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETUSERID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTargetUserId",
            new String[] { Long.class.getName() },
            PointsDistributionConfigurationModelImpl.TARGETUSERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TARGETUSERID = new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByTargetUserId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_TARGETUSERID_TARGETUSERID_2 = "pointsDistributionConfiguration.targetUserId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETSUBPROPOSALID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByTargetSubProposalId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETSUBPROPOSALID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByTargetSubProposalId", new String[] { Long.class.getName() },
            PointsDistributionConfigurationModelImpl.TARGETSUBPROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TARGETSUBPROPOSALID = new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByTargetSubProposalId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_TARGETSUBPROPOSALID_TARGETSUBPROPOSALID_2 =
        "pointsDistributionConfiguration.targetSubProposalId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETPLANSECTIONDEFINITIONID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByTargetPlanSectionDefinitionId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPLANSECTIONDEFINITIONID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByTargetPlanSectionDefinitionId",
            new String[] { Long.class.getName() },
            PointsDistributionConfigurationModelImpl.TARGETPLANSECTIONDEFINITIONID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TARGETPLANSECTIONDEFINITIONID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByTargetPlanSectionDefinitionId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_TARGETPLANSECTIONDEFINITIONID_TARGETPLANSECTIONDEFINITIONID_2 =
        "pointsDistributionConfiguration.targetPlanSectionDefinitionId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProposalId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProposalId",
            new String[] { Long.class.getName() },
            PointsDistributionConfigurationModelImpl.PROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID = new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALID_2 = "pointsDistributionConfiguration.proposalId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDPOINTTYPEID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByProposalIdPointTypeId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDPOINTTYPEID =
        new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByProposalIdPointTypeId",
            new String[] { Long.class.getName(), Long.class.getName() },
            PointsDistributionConfigurationModelImpl.PROPOSALID_COLUMN_BITMASK |
            PointsDistributionConfigurationModelImpl.POINTTYPEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALIDPOINTTYPEID = new FinderPath(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalIdPointTypeId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALIDPOINTTYPEID_PROPOSALID_2 =
        "pointsDistributionConfiguration.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDPOINTTYPEID_POINTTYPEID_2 =
        "pointsDistributionConfiguration.pointTypeId = ?";
    private static final String _SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION = "SELECT pointsDistributionConfiguration FROM PointsDistributionConfiguration pointsDistributionConfiguration";
    private static final String _SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE =
        "SELECT pointsDistributionConfiguration FROM PointsDistributionConfiguration pointsDistributionConfiguration WHERE ";
    private static final String _SQL_COUNT_POINTSDISTRIBUTIONCONFIGURATION = "SELECT COUNT(pointsDistributionConfiguration) FROM PointsDistributionConfiguration pointsDistributionConfiguration";
    private static final String _SQL_COUNT_POINTSDISTRIBUTIONCONFIGURATION_WHERE =
        "SELECT COUNT(pointsDistributionConfiguration) FROM PointsDistributionConfiguration pointsDistributionConfiguration WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "pointsDistributionConfiguration.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PointsDistributionConfiguration exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PointsDistributionConfiguration exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PointsDistributionConfigurationPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static PointsDistributionConfiguration _nullPointsDistributionConfiguration =
        new PointsDistributionConfigurationImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PointsDistributionConfiguration> toCacheModel() {
                return _nullPointsDistributionConfigurationCacheModel;
            }
        };

    private static CacheModel<PointsDistributionConfiguration> _nullPointsDistributionConfigurationCacheModel =
        new CacheModel<PointsDistributionConfiguration>() {
            @Override
            public PointsDistributionConfiguration toEntityModel() {
                return _nullPointsDistributionConfiguration;
            }
        };

    public PointsDistributionConfigurationPersistenceImpl() {
        setModelClass(PointsDistributionConfiguration.class);
    }

    /**
     * Returns all the points distribution configurations where targetUserId = &#63;.
     *
     * @param targetUserId the target user ID
     * @return the matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByTargetUserId(
        long targetUserId) throws SystemException {
        return findByTargetUserId(targetUserId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the points distribution configurations where targetUserId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetUserId the target user ID
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @return the range of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByTargetUserId(
        long targetUserId, int start, int end) throws SystemException {
        return findByTargetUserId(targetUserId, start, end, null);
    }

    /**
     * Returns an ordered range of all the points distribution configurations where targetUserId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetUserId the target user ID
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByTargetUserId(
        long targetUserId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETUSERID;
            finderArgs = new Object[] { targetUserId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETUSERID;
            finderArgs = new Object[] {
                    targetUserId,
                    
                    start, end, orderByComparator
                };
        }

        List<PointsDistributionConfiguration> list = (List<PointsDistributionConfiguration>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PointsDistributionConfiguration pointsDistributionConfiguration : list) {
                if ((targetUserId != pointsDistributionConfiguration.getTargetUserId())) {
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

            query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

            query.append(_FINDER_COLUMN_TARGETUSERID_TARGETUSERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetUserId);

                if (!pagination) {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointsDistributionConfiguration>(list);
                } else {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
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
     * Returns the first points distribution configuration in the ordered set where targetUserId = &#63;.
     *
     * @param targetUserId the target user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByTargetUserId_First(
        long targetUserId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByTargetUserId_First(targetUserId,
                orderByComparator);

        if (pointsDistributionConfiguration != null) {
            return pointsDistributionConfiguration;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetUserId=");
        msg.append(targetUserId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsDistributionConfigurationException(msg.toString());
    }

    /**
     * Returns the first points distribution configuration in the ordered set where targetUserId = &#63;.
     *
     * @param targetUserId the target user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByTargetUserId_First(
        long targetUserId, OrderByComparator orderByComparator)
        throws SystemException {
        List<PointsDistributionConfiguration> list = findByTargetUserId(targetUserId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last points distribution configuration in the ordered set where targetUserId = &#63;.
     *
     * @param targetUserId the target user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByTargetUserId_Last(
        long targetUserId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByTargetUserId_Last(targetUserId,
                orderByComparator);

        if (pointsDistributionConfiguration != null) {
            return pointsDistributionConfiguration;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetUserId=");
        msg.append(targetUserId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsDistributionConfigurationException(msg.toString());
    }

    /**
     * Returns the last points distribution configuration in the ordered set where targetUserId = &#63;.
     *
     * @param targetUserId the target user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByTargetUserId_Last(
        long targetUserId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByTargetUserId(targetUserId);

        if (count == 0) {
            return null;
        }

        List<PointsDistributionConfiguration> list = findByTargetUserId(targetUserId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where targetUserId = &#63;.
     *
     * @param id the primary key of the current points distribution configuration
     * @param targetUserId the target user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration[] findByTargetUserId_PrevAndNext(
        long id, long targetUserId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PointsDistributionConfiguration[] array = new PointsDistributionConfigurationImpl[3];

            array[0] = getByTargetUserId_PrevAndNext(session,
                    pointsDistributionConfiguration, targetUserId,
                    orderByComparator, true);

            array[1] = pointsDistributionConfiguration;

            array[2] = getByTargetUserId_PrevAndNext(session,
                    pointsDistributionConfiguration, targetUserId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PointsDistributionConfiguration getByTargetUserId_PrevAndNext(
        Session session,
        PointsDistributionConfiguration pointsDistributionConfiguration,
        long targetUserId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

        query.append(_FINDER_COLUMN_TARGETUSERID_TARGETUSERID_2);

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
            query.append(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(targetUserId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(pointsDistributionConfiguration);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PointsDistributionConfiguration> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the points distribution configurations where targetUserId = &#63; from the database.
     *
     * @param targetUserId the target user ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByTargetUserId(long targetUserId)
        throws SystemException {
        for (PointsDistributionConfiguration pointsDistributionConfiguration : findByTargetUserId(
                targetUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(pointsDistributionConfiguration);
        }
    }

    /**
     * Returns the number of points distribution configurations where targetUserId = &#63;.
     *
     * @param targetUserId the target user ID
     * @return the number of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTargetUserId(long targetUserId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TARGETUSERID;

        Object[] finderArgs = new Object[] { targetUserId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

            query.append(_FINDER_COLUMN_TARGETUSERID_TARGETUSERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetUserId);

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
     * Returns all the points distribution configurations where targetSubProposalId = &#63;.
     *
     * @param targetSubProposalId the target sub proposal ID
     * @return the matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByTargetSubProposalId(
        long targetSubProposalId) throws SystemException {
        return findByTargetSubProposalId(targetSubProposalId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the points distribution configurations where targetSubProposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetSubProposalId the target sub proposal ID
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @return the range of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByTargetSubProposalId(
        long targetSubProposalId, int start, int end) throws SystemException {
        return findByTargetSubProposalId(targetSubProposalId, start, end, null);
    }

    /**
     * Returns an ordered range of all the points distribution configurations where targetSubProposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetSubProposalId the target sub proposal ID
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByTargetSubProposalId(
        long targetSubProposalId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETSUBPROPOSALID;
            finderArgs = new Object[] { targetSubProposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETSUBPROPOSALID;
            finderArgs = new Object[] {
                    targetSubProposalId,
                    
                    start, end, orderByComparator
                };
        }

        List<PointsDistributionConfiguration> list = (List<PointsDistributionConfiguration>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PointsDistributionConfiguration pointsDistributionConfiguration : list) {
                if ((targetSubProposalId != pointsDistributionConfiguration.getTargetSubProposalId())) {
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

            query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

            query.append(_FINDER_COLUMN_TARGETSUBPROPOSALID_TARGETSUBPROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetSubProposalId);

                if (!pagination) {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointsDistributionConfiguration>(list);
                } else {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
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
     * Returns the first points distribution configuration in the ordered set where targetSubProposalId = &#63;.
     *
     * @param targetSubProposalId the target sub proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByTargetSubProposalId_First(
        long targetSubProposalId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByTargetSubProposalId_First(targetSubProposalId,
                orderByComparator);

        if (pointsDistributionConfiguration != null) {
            return pointsDistributionConfiguration;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetSubProposalId=");
        msg.append(targetSubProposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsDistributionConfigurationException(msg.toString());
    }

    /**
     * Returns the first points distribution configuration in the ordered set where targetSubProposalId = &#63;.
     *
     * @param targetSubProposalId the target sub proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByTargetSubProposalId_First(
        long targetSubProposalId, OrderByComparator orderByComparator)
        throws SystemException {
        List<PointsDistributionConfiguration> list = findByTargetSubProposalId(targetSubProposalId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last points distribution configuration in the ordered set where targetSubProposalId = &#63;.
     *
     * @param targetSubProposalId the target sub proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByTargetSubProposalId_Last(
        long targetSubProposalId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByTargetSubProposalId_Last(targetSubProposalId,
                orderByComparator);

        if (pointsDistributionConfiguration != null) {
            return pointsDistributionConfiguration;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetSubProposalId=");
        msg.append(targetSubProposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsDistributionConfigurationException(msg.toString());
    }

    /**
     * Returns the last points distribution configuration in the ordered set where targetSubProposalId = &#63;.
     *
     * @param targetSubProposalId the target sub proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByTargetSubProposalId_Last(
        long targetSubProposalId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByTargetSubProposalId(targetSubProposalId);

        if (count == 0) {
            return null;
        }

        List<PointsDistributionConfiguration> list = findByTargetSubProposalId(targetSubProposalId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where targetSubProposalId = &#63;.
     *
     * @param id the primary key of the current points distribution configuration
     * @param targetSubProposalId the target sub proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration[] findByTargetSubProposalId_PrevAndNext(
        long id, long targetSubProposalId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PointsDistributionConfiguration[] array = new PointsDistributionConfigurationImpl[3];

            array[0] = getByTargetSubProposalId_PrevAndNext(session,
                    pointsDistributionConfiguration, targetSubProposalId,
                    orderByComparator, true);

            array[1] = pointsDistributionConfiguration;

            array[2] = getByTargetSubProposalId_PrevAndNext(session,
                    pointsDistributionConfiguration, targetSubProposalId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PointsDistributionConfiguration getByTargetSubProposalId_PrevAndNext(
        Session session,
        PointsDistributionConfiguration pointsDistributionConfiguration,
        long targetSubProposalId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

        query.append(_FINDER_COLUMN_TARGETSUBPROPOSALID_TARGETSUBPROPOSALID_2);

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
            query.append(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(targetSubProposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(pointsDistributionConfiguration);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PointsDistributionConfiguration> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the points distribution configurations where targetSubProposalId = &#63; from the database.
     *
     * @param targetSubProposalId the target sub proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByTargetSubProposalId(long targetSubProposalId)
        throws SystemException {
        for (PointsDistributionConfiguration pointsDistributionConfiguration : findByTargetSubProposalId(
                targetSubProposalId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(pointsDistributionConfiguration);
        }
    }

    /**
     * Returns the number of points distribution configurations where targetSubProposalId = &#63;.
     *
     * @param targetSubProposalId the target sub proposal ID
     * @return the number of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTargetSubProposalId(long targetSubProposalId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TARGETSUBPROPOSALID;

        Object[] finderArgs = new Object[] { targetSubProposalId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

            query.append(_FINDER_COLUMN_TARGETSUBPROPOSALID_TARGETSUBPROPOSALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetSubProposalId);

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
     * Returns all the points distribution configurations where targetPlanSectionDefinitionId = &#63;.
     *
     * @param targetPlanSectionDefinitionId the target plan section definition ID
     * @return the matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId) throws SystemException {
        return findByTargetPlanSectionDefinitionId(targetPlanSectionDefinitionId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the points distribution configurations where targetPlanSectionDefinitionId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetPlanSectionDefinitionId the target plan section definition ID
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @return the range of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId, int start, int end)
        throws SystemException {
        return findByTargetPlanSectionDefinitionId(targetPlanSectionDefinitionId,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the points distribution configurations where targetPlanSectionDefinitionId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param targetPlanSectionDefinitionId the target plan section definition ID
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPLANSECTIONDEFINITIONID;
            finderArgs = new Object[] { targetPlanSectionDefinitionId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TARGETPLANSECTIONDEFINITIONID;
            finderArgs = new Object[] {
                    targetPlanSectionDefinitionId,
                    
                    start, end, orderByComparator
                };
        }

        List<PointsDistributionConfiguration> list = (List<PointsDistributionConfiguration>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PointsDistributionConfiguration pointsDistributionConfiguration : list) {
                if ((targetPlanSectionDefinitionId != pointsDistributionConfiguration.getTargetPlanSectionDefinitionId())) {
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

            query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

            query.append(_FINDER_COLUMN_TARGETPLANSECTIONDEFINITIONID_TARGETPLANSECTIONDEFINITIONID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetPlanSectionDefinitionId);

                if (!pagination) {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointsDistributionConfiguration>(list);
                } else {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
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
     * Returns the first points distribution configuration in the ordered set where targetPlanSectionDefinitionId = &#63;.
     *
     * @param targetPlanSectionDefinitionId the target plan section definition ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByTargetPlanSectionDefinitionId_First(
        long targetPlanSectionDefinitionId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByTargetPlanSectionDefinitionId_First(targetPlanSectionDefinitionId,
                orderByComparator);

        if (pointsDistributionConfiguration != null) {
            return pointsDistributionConfiguration;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetPlanSectionDefinitionId=");
        msg.append(targetPlanSectionDefinitionId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsDistributionConfigurationException(msg.toString());
    }

    /**
     * Returns the first points distribution configuration in the ordered set where targetPlanSectionDefinitionId = &#63;.
     *
     * @param targetPlanSectionDefinitionId the target plan section definition ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByTargetPlanSectionDefinitionId_First(
        long targetPlanSectionDefinitionId, OrderByComparator orderByComparator)
        throws SystemException {
        List<PointsDistributionConfiguration> list = findByTargetPlanSectionDefinitionId(targetPlanSectionDefinitionId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last points distribution configuration in the ordered set where targetPlanSectionDefinitionId = &#63;.
     *
     * @param targetPlanSectionDefinitionId the target plan section definition ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByTargetPlanSectionDefinitionId_Last(
        long targetPlanSectionDefinitionId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByTargetPlanSectionDefinitionId_Last(targetPlanSectionDefinitionId,
                orderByComparator);

        if (pointsDistributionConfiguration != null) {
            return pointsDistributionConfiguration;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("targetPlanSectionDefinitionId=");
        msg.append(targetPlanSectionDefinitionId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsDistributionConfigurationException(msg.toString());
    }

    /**
     * Returns the last points distribution configuration in the ordered set where targetPlanSectionDefinitionId = &#63;.
     *
     * @param targetPlanSectionDefinitionId the target plan section definition ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByTargetPlanSectionDefinitionId_Last(
        long targetPlanSectionDefinitionId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByTargetPlanSectionDefinitionId(targetPlanSectionDefinitionId);

        if (count == 0) {
            return null;
        }

        List<PointsDistributionConfiguration> list = findByTargetPlanSectionDefinitionId(targetPlanSectionDefinitionId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where targetPlanSectionDefinitionId = &#63;.
     *
     * @param id the primary key of the current points distribution configuration
     * @param targetPlanSectionDefinitionId the target plan section definition ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration[] findByTargetPlanSectionDefinitionId_PrevAndNext(
        long id, long targetPlanSectionDefinitionId,
        OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PointsDistributionConfiguration[] array = new PointsDistributionConfigurationImpl[3];

            array[0] = getByTargetPlanSectionDefinitionId_PrevAndNext(session,
                    pointsDistributionConfiguration,
                    targetPlanSectionDefinitionId, orderByComparator, true);

            array[1] = pointsDistributionConfiguration;

            array[2] = getByTargetPlanSectionDefinitionId_PrevAndNext(session,
                    pointsDistributionConfiguration,
                    targetPlanSectionDefinitionId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PointsDistributionConfiguration getByTargetPlanSectionDefinitionId_PrevAndNext(
        Session session,
        PointsDistributionConfiguration pointsDistributionConfiguration,
        long targetPlanSectionDefinitionId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

        query.append(_FINDER_COLUMN_TARGETPLANSECTIONDEFINITIONID_TARGETPLANSECTIONDEFINITIONID_2);

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
            query.append(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(targetPlanSectionDefinitionId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(pointsDistributionConfiguration);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PointsDistributionConfiguration> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the points distribution configurations where targetPlanSectionDefinitionId = &#63; from the database.
     *
     * @param targetPlanSectionDefinitionId the target plan section definition ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId) throws SystemException {
        for (PointsDistributionConfiguration pointsDistributionConfiguration : findByTargetPlanSectionDefinitionId(
                targetPlanSectionDefinitionId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(pointsDistributionConfiguration);
        }
    }

    /**
     * Returns the number of points distribution configurations where targetPlanSectionDefinitionId = &#63;.
     *
     * @param targetPlanSectionDefinitionId the target plan section definition ID
     * @return the number of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByTargetPlanSectionDefinitionId(
        long targetPlanSectionDefinitionId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_TARGETPLANSECTIONDEFINITIONID;

        Object[] finderArgs = new Object[] { targetPlanSectionDefinitionId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

            query.append(_FINDER_COLUMN_TARGETPLANSECTIONDEFINITIONID_TARGETPLANSECTIONDEFINITIONID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(targetPlanSectionDefinitionId);

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
     * Returns all the points distribution configurations where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByProposalId(
        long proposalId) throws SystemException {
        return findByProposalId(proposalId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the points distribution configurations where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @return the range of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByProposalId(
        long proposalId, int start, int end) throws SystemException {
        return findByProposalId(proposalId, start, end, null);
    }

    /**
     * Returns an ordered range of all the points distribution configurations where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByProposalId(
        long proposalId, int start, int end, OrderByComparator orderByComparator)
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

        List<PointsDistributionConfiguration> list = (List<PointsDistributionConfiguration>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PointsDistributionConfiguration pointsDistributionConfiguration : list) {
                if ((proposalId != pointsDistributionConfiguration.getProposalId())) {
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

            query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                if (!pagination) {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointsDistributionConfiguration>(list);
                } else {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
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
     * Returns the first points distribution configuration in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByProposalId_First(
        long proposalId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByProposalId_First(proposalId,
                orderByComparator);

        if (pointsDistributionConfiguration != null) {
            return pointsDistributionConfiguration;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsDistributionConfigurationException(msg.toString());
    }

    /**
     * Returns the first points distribution configuration in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByProposalId_First(
        long proposalId, OrderByComparator orderByComparator)
        throws SystemException {
        List<PointsDistributionConfiguration> list = findByProposalId(proposalId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last points distribution configuration in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByProposalId_Last(
        long proposalId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByProposalId_Last(proposalId,
                orderByComparator);

        if (pointsDistributionConfiguration != null) {
            return pointsDistributionConfiguration;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsDistributionConfigurationException(msg.toString());
    }

    /**
     * Returns the last points distribution configuration in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByProposalId_Last(
        long proposalId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByProposalId(proposalId);

        if (count == 0) {
            return null;
        }

        List<PointsDistributionConfiguration> list = findByProposalId(proposalId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where proposalId = &#63;.
     *
     * @param id the primary key of the current points distribution configuration
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration[] findByProposalId_PrevAndNext(
        long id, long proposalId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PointsDistributionConfiguration[] array = new PointsDistributionConfigurationImpl[3];

            array[0] = getByProposalId_PrevAndNext(session,
                    pointsDistributionConfiguration, proposalId,
                    orderByComparator, true);

            array[1] = pointsDistributionConfiguration;

            array[2] = getByProposalId_PrevAndNext(session,
                    pointsDistributionConfiguration, proposalId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PointsDistributionConfiguration getByProposalId_PrevAndNext(
        Session session,
        PointsDistributionConfiguration pointsDistributionConfiguration,
        long proposalId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

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
            query.append(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(pointsDistributionConfiguration);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PointsDistributionConfiguration> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the points distribution configurations where proposalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProposalId(long proposalId) throws SystemException {
        for (PointsDistributionConfiguration pointsDistributionConfiguration : findByProposalId(
                proposalId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(pointsDistributionConfiguration);
        }
    }

    /**
     * Returns the number of points distribution configurations where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the number of matching points distribution configurations
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

            query.append(_SQL_COUNT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

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
     * Returns all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param pointTypeId the point type ID
     * @return the matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByProposalIdPointTypeId(
        long proposalId, long pointTypeId) throws SystemException {
        return findByProposalIdPointTypeId(proposalId, pointTypeId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param pointTypeId the point type ID
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @return the range of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByProposalIdPointTypeId(
        long proposalId, long pointTypeId, int start, int end)
        throws SystemException {
        return findByProposalIdPointTypeId(proposalId, pointTypeId, start, end,
            null);
    }

    /**
     * Returns an ordered range of all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param pointTypeId the point type ID
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findByProposalIdPointTypeId(
        long proposalId, long pointTypeId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDPOINTTYPEID;
            finderArgs = new Object[] { proposalId, pointTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDPOINTTYPEID;
            finderArgs = new Object[] {
                    proposalId, pointTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<PointsDistributionConfiguration> list = (List<PointsDistributionConfiguration>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PointsDistributionConfiguration pointsDistributionConfiguration : list) {
                if ((proposalId != pointsDistributionConfiguration.getProposalId()) ||
                        (pointTypeId != pointsDistributionConfiguration.getPointTypeId())) {
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

            query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDPOINTTYPEID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDPOINTTYPEID_POINTTYPEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(pointTypeId);

                if (!pagination) {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointsDistributionConfiguration>(list);
                } else {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
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
     * Returns the first points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param pointTypeId the point type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByProposalIdPointTypeId_First(
        long proposalId, long pointTypeId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByProposalIdPointTypeId_First(proposalId,
                pointTypeId, orderByComparator);

        if (pointsDistributionConfiguration != null) {
            return pointsDistributionConfiguration;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(", pointTypeId=");
        msg.append(pointTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsDistributionConfigurationException(msg.toString());
    }

    /**
     * Returns the first points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param pointTypeId the point type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByProposalIdPointTypeId_First(
        long proposalId, long pointTypeId, OrderByComparator orderByComparator)
        throws SystemException {
        List<PointsDistributionConfiguration> list = findByProposalIdPointTypeId(proposalId,
                pointTypeId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param pointTypeId the point type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByProposalIdPointTypeId_Last(
        long proposalId, long pointTypeId, OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByProposalIdPointTypeId_Last(proposalId,
                pointTypeId, orderByComparator);

        if (pointsDistributionConfiguration != null) {
            return pointsDistributionConfiguration;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(", pointTypeId=");
        msg.append(pointTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsDistributionConfigurationException(msg.toString());
    }

    /**
     * Returns the last points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param pointTypeId the point type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points distribution configuration, or <code>null</code> if a matching points distribution configuration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByProposalIdPointTypeId_Last(
        long proposalId, long pointTypeId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByProposalIdPointTypeId(proposalId, pointTypeId);

        if (count == 0) {
            return null;
        }

        List<PointsDistributionConfiguration> list = findByProposalIdPointTypeId(proposalId,
                pointTypeId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the points distribution configurations before and after the current points distribution configuration in the ordered set where proposalId = &#63; and pointTypeId = &#63;.
     *
     * @param id the primary key of the current points distribution configuration
     * @param proposalId the proposal ID
     * @param pointTypeId the point type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration[] findByProposalIdPointTypeId_PrevAndNext(
        long id, long proposalId, long pointTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PointsDistributionConfiguration[] array = new PointsDistributionConfigurationImpl[3];

            array[0] = getByProposalIdPointTypeId_PrevAndNext(session,
                    pointsDistributionConfiguration, proposalId, pointTypeId,
                    orderByComparator, true);

            array[1] = pointsDistributionConfiguration;

            array[2] = getByProposalIdPointTypeId_PrevAndNext(session,
                    pointsDistributionConfiguration, proposalId, pointTypeId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PointsDistributionConfiguration getByProposalIdPointTypeId_PrevAndNext(
        Session session,
        PointsDistributionConfiguration pointsDistributionConfiguration,
        long proposalId, long pointTypeId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALIDPOINTTYPEID_PROPOSALID_2);

        query.append(_FINDER_COLUMN_PROPOSALIDPOINTTYPEID_POINTTYPEID_2);

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
            query.append(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        qPos.add(pointTypeId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(pointsDistributionConfiguration);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PointsDistributionConfiguration> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the points distribution configurations where proposalId = &#63; and pointTypeId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param pointTypeId the point type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProposalIdPointTypeId(long proposalId, long pointTypeId)
        throws SystemException {
        for (PointsDistributionConfiguration pointsDistributionConfiguration : findByProposalIdPointTypeId(
                proposalId, pointTypeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
                null)) {
            remove(pointsDistributionConfiguration);
        }
    }

    /**
     * Returns the number of points distribution configurations where proposalId = &#63; and pointTypeId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param pointTypeId the point type ID
     * @return the number of matching points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalIdPointTypeId(long proposalId, long pointTypeId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALIDPOINTTYPEID;

        Object[] finderArgs = new Object[] { proposalId, pointTypeId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_POINTSDISTRIBUTIONCONFIGURATION_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDPOINTTYPEID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDPOINTTYPEID_POINTTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(pointTypeId);

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
     * Caches the points distribution configuration in the entity cache if it is enabled.
     *
     * @param pointsDistributionConfiguration the points distribution configuration
     */
    @Override
    public void cacheResult(
        PointsDistributionConfiguration pointsDistributionConfiguration) {
        EntityCacheUtil.putResult(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            pointsDistributionConfiguration.getPrimaryKey(),
            pointsDistributionConfiguration);

        pointsDistributionConfiguration.resetOriginalValues();
    }

    /**
     * Caches the points distribution configurations in the entity cache if it is enabled.
     *
     * @param pointsDistributionConfigurations the points distribution configurations
     */
    @Override
    public void cacheResult(
        List<PointsDistributionConfiguration> pointsDistributionConfigurations) {
        for (PointsDistributionConfiguration pointsDistributionConfiguration : pointsDistributionConfigurations) {
            if (EntityCacheUtil.getResult(
                        PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
                        PointsDistributionConfigurationImpl.class,
                        pointsDistributionConfiguration.getPrimaryKey()) == null) {
                cacheResult(pointsDistributionConfiguration);
            } else {
                pointsDistributionConfiguration.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all points distribution configurations.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PointsDistributionConfigurationImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PointsDistributionConfigurationImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the points distribution configuration.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        PointsDistributionConfiguration pointsDistributionConfiguration) {
        EntityCacheUtil.removeResult(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            pointsDistributionConfiguration.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<PointsDistributionConfiguration> pointsDistributionConfigurations) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PointsDistributionConfiguration pointsDistributionConfiguration : pointsDistributionConfigurations) {
            EntityCacheUtil.removeResult(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
                PointsDistributionConfigurationImpl.class,
                pointsDistributionConfiguration.getPrimaryKey());
        }
    }

    /**
     * Creates a new points distribution configuration with the primary key. Does not add the points distribution configuration to the database.
     *
     * @param id the primary key for the new points distribution configuration
     * @return the new points distribution configuration
     */
    @Override
    public PointsDistributionConfiguration create(long id) {
        PointsDistributionConfiguration pointsDistributionConfiguration = new PointsDistributionConfigurationImpl();

        pointsDistributionConfiguration.setNew(true);
        pointsDistributionConfiguration.setPrimaryKey(id);

        return pointsDistributionConfiguration;
    }

    /**
     * Removes the points distribution configuration with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the points distribution configuration
     * @return the points distribution configuration that was removed
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration remove(long id)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the points distribution configuration with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the points distribution configuration
     * @return the points distribution configuration that was removed
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration remove(Serializable primaryKey)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PointsDistributionConfiguration pointsDistributionConfiguration = (PointsDistributionConfiguration) session.get(PointsDistributionConfigurationImpl.class,
                    primaryKey);

            if (pointsDistributionConfiguration == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPointsDistributionConfigurationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(pointsDistributionConfiguration);
        } catch (NoSuchPointsDistributionConfigurationException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PointsDistributionConfiguration removeImpl(
        PointsDistributionConfiguration pointsDistributionConfiguration)
        throws SystemException {
        pointsDistributionConfiguration = toUnwrappedModel(pointsDistributionConfiguration);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(pointsDistributionConfiguration)) {
                pointsDistributionConfiguration = (PointsDistributionConfiguration) session.get(PointsDistributionConfigurationImpl.class,
                        pointsDistributionConfiguration.getPrimaryKeyObj());
            }

            if (pointsDistributionConfiguration != null) {
                session.delete(pointsDistributionConfiguration);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (pointsDistributionConfiguration != null) {
            clearCache(pointsDistributionConfiguration);
        }

        return pointsDistributionConfiguration;
    }

    @Override
    public PointsDistributionConfiguration updateImpl(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration)
        throws SystemException {
        pointsDistributionConfiguration = toUnwrappedModel(pointsDistributionConfiguration);

        boolean isNew = pointsDistributionConfiguration.isNew();

        PointsDistributionConfigurationModelImpl pointsDistributionConfigurationModelImpl =
            (PointsDistributionConfigurationModelImpl) pointsDistributionConfiguration;

        Session session = null;

        try {
            session = openSession();

            if (pointsDistributionConfiguration.isNew()) {
                session.save(pointsDistributionConfiguration);

                pointsDistributionConfiguration.setNew(false);
            } else {
                session.merge(pointsDistributionConfiguration);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew ||
                !PointsDistributionConfigurationModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((pointsDistributionConfigurationModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETUSERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointsDistributionConfigurationModelImpl.getOriginalTargetUserId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETUSERID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETUSERID,
                    args);

                args = new Object[] {
                        pointsDistributionConfigurationModelImpl.getTargetUserId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETUSERID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETUSERID,
                    args);
            }

            if ((pointsDistributionConfigurationModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETSUBPROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointsDistributionConfigurationModelImpl.getOriginalTargetSubProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETSUBPROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETSUBPROPOSALID,
                    args);

                args = new Object[] {
                        pointsDistributionConfigurationModelImpl.getTargetSubProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETSUBPROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETSUBPROPOSALID,
                    args);
            }

            if ((pointsDistributionConfigurationModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPLANSECTIONDEFINITIONID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointsDistributionConfigurationModelImpl.getOriginalTargetPlanSectionDefinitionId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETPLANSECTIONDEFINITIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPLANSECTIONDEFINITIONID,
                    args);

                args = new Object[] {
                        pointsDistributionConfigurationModelImpl.getTargetPlanSectionDefinitionId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TARGETPLANSECTIONDEFINITIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TARGETPLANSECTIONDEFINITIONID,
                    args);
            }

            if ((pointsDistributionConfigurationModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointsDistributionConfigurationModelImpl.getOriginalProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);

                args = new Object[] {
                        pointsDistributionConfigurationModelImpl.getProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);
            }

            if ((pointsDistributionConfigurationModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDPOINTTYPEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointsDistributionConfigurationModelImpl.getOriginalProposalId(),
                        pointsDistributionConfigurationModelImpl.getOriginalPointTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDPOINTTYPEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDPOINTTYPEID,
                    args);

                args = new Object[] {
                        pointsDistributionConfigurationModelImpl.getProposalId(),
                        pointsDistributionConfigurationModelImpl.getPointTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDPOINTTYPEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDPOINTTYPEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
            PointsDistributionConfigurationImpl.class,
            pointsDistributionConfiguration.getPrimaryKey(),
            pointsDistributionConfiguration);

        return pointsDistributionConfiguration;
    }

    protected PointsDistributionConfiguration toUnwrappedModel(
        PointsDistributionConfiguration pointsDistributionConfiguration) {
        if (pointsDistributionConfiguration instanceof PointsDistributionConfigurationImpl) {
            return pointsDistributionConfiguration;
        }

        PointsDistributionConfigurationImpl pointsDistributionConfigurationImpl = new PointsDistributionConfigurationImpl();

        pointsDistributionConfigurationImpl.setNew(pointsDistributionConfiguration.isNew());
        pointsDistributionConfigurationImpl.setPrimaryKey(pointsDistributionConfiguration.getPrimaryKey());

        pointsDistributionConfigurationImpl.setId(pointsDistributionConfiguration.getId());
        pointsDistributionConfigurationImpl.setProposalId(pointsDistributionConfiguration.getProposalId());
        pointsDistributionConfigurationImpl.setPointTypeId(pointsDistributionConfiguration.getPointTypeId());
        pointsDistributionConfigurationImpl.setTargetUserId(pointsDistributionConfiguration.getTargetUserId());
        pointsDistributionConfigurationImpl.setTargetSubProposalId(pointsDistributionConfiguration.getTargetSubProposalId());
        pointsDistributionConfigurationImpl.setTargetPlanSectionDefinitionId(pointsDistributionConfiguration.getTargetPlanSectionDefinitionId());
        pointsDistributionConfigurationImpl.setPercentage(pointsDistributionConfiguration.getPercentage());
        pointsDistributionConfigurationImpl.setCreator(pointsDistributionConfiguration.getCreator());
        pointsDistributionConfigurationImpl.setCreateDate(pointsDistributionConfiguration.getCreateDate());

        return pointsDistributionConfigurationImpl;
    }

    /**
     * Returns the points distribution configuration with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the points distribution configuration
     * @return the points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByPrimaryKey(
        Serializable primaryKey)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = fetchByPrimaryKey(primaryKey);

        if (pointsDistributionConfiguration == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPointsDistributionConfigurationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return pointsDistributionConfiguration;
    }

    /**
     * Returns the points distribution configuration with the primary key or throws a {@link com.ext.portlet.NoSuchPointsDistributionConfigurationException} if it could not be found.
     *
     * @param id the primary key of the points distribution configuration
     * @return the points distribution configuration
     * @throws com.ext.portlet.NoSuchPointsDistributionConfigurationException if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration findByPrimaryKey(long id)
        throws NoSuchPointsDistributionConfigurationException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the points distribution configuration with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the points distribution configuration
     * @return the points distribution configuration, or <code>null</code> if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        PointsDistributionConfiguration pointsDistributionConfiguration = (PointsDistributionConfiguration) EntityCacheUtil.getResult(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
                PointsDistributionConfigurationImpl.class, primaryKey);

        if (pointsDistributionConfiguration == _nullPointsDistributionConfiguration) {
            return null;
        }

        if (pointsDistributionConfiguration == null) {
            Session session = null;

            try {
                session = openSession();

                pointsDistributionConfiguration = (PointsDistributionConfiguration) session.get(PointsDistributionConfigurationImpl.class,
                        primaryKey);

                if (pointsDistributionConfiguration != null) {
                    cacheResult(pointsDistributionConfiguration);
                } else {
                    EntityCacheUtil.putResult(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
                        PointsDistributionConfigurationImpl.class, primaryKey,
                        _nullPointsDistributionConfiguration);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PointsDistributionConfigurationModelImpl.ENTITY_CACHE_ENABLED,
                    PointsDistributionConfigurationImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return pointsDistributionConfiguration;
    }

    /**
     * Returns the points distribution configuration with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the points distribution configuration
     * @return the points distribution configuration, or <code>null</code> if a points distribution configuration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PointsDistributionConfiguration fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the points distribution configurations.
     *
     * @return the points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the points distribution configurations.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @return the range of points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the points distribution configurations.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of points distribution configurations
     * @param end the upper bound of the range of points distribution configurations (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of points distribution configurations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PointsDistributionConfiguration> findAll(int start, int end,
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

        List<PointsDistributionConfiguration> list = (List<PointsDistributionConfiguration>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_POINTSDISTRIBUTIONCONFIGURATION;

                if (pagination) {
                    sql = sql.concat(PointsDistributionConfigurationModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PointsDistributionConfiguration>(list);
                } else {
                    list = (List<PointsDistributionConfiguration>) QueryUtil.list(q,
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
     * Removes all the points distribution configurations from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PointsDistributionConfiguration pointsDistributionConfiguration : findAll()) {
            remove(pointsDistributionConfiguration);
        }
    }

    /**
     * Returns the number of points distribution configurations.
     *
     * @return the number of points distribution configurations
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

                Query q = session.createQuery(_SQL_COUNT_POINTSDISTRIBUTIONCONFIGURATION);

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
     * Initializes the points distribution configuration persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PointsDistributionConfiguration")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PointsDistributionConfiguration>> listenersList =
                    new ArrayList<ModelListener<PointsDistributionConfiguration>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PointsDistributionConfiguration>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PointsDistributionConfigurationImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
