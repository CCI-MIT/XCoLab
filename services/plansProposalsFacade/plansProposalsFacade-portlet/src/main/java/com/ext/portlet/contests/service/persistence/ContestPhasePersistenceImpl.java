package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.NoSuchContestPhaseException;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.model.impl.ContestPhaseImpl;
import com.ext.portlet.contests.model.impl.ContestPhaseModelImpl;
import com.ext.portlet.contests.service.persistence.ContestDebatePersistence;
import com.ext.portlet.contests.service.persistence.ContestPersistence;
import com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence;
import com.ext.portlet.contests.service.persistence.ContestPhasePersistence;
import com.ext.portlet.contests.service.persistence.ContestTeamMemberPersistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
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
    public static final FinderPath FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByContestIdStartEnd",
            new String[] {
                Long.class.getName(), Date.class.getName(), Date.class.getName()
            },
            ContestPhaseModelImpl.CONTESTPK_COLUMN_BITMASK |
            ContestPhaseModelImpl.PHASESTARTDATE_COLUMN_BITMASK |
            ContestPhaseModelImpl.PHASEENDDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTIDSTARTEND = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByContestIdStartEnd",
            new String[] {
                Long.class.getName(), Date.class.getName(), Date.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTID =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID =
        new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContestId",
            new String[] { Long.class.getName() },
            ContestPhaseModelImpl.CONTESTPK_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTID = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContestId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, ContestPhaseImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTESTPHASE = "SELECT contestPhase FROM ContestPhase contestPhase";
    private static final String _SQL_SELECT_CONTESTPHASE_WHERE = "SELECT contestPhase FROM ContestPhase contestPhase WHERE ";
    private static final String _SQL_COUNT_CONTESTPHASE = "SELECT COUNT(contestPhase) FROM ContestPhase contestPhase";
    private static final String _SQL_COUNT_CONTESTPHASE_WHERE = "SELECT COUNT(contestPhase) FROM ContestPhase contestPhase WHERE ";
    private static final String _FINDER_COLUMN_CONTESTIDSTARTEND_CONTESTPK_2 = "contestPhase.ContestPK = ? AND ";
    private static final String _FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_1 =
        "contestPhase.PhaseStartDate <= NULL AND ";
    private static final String _FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_2 =
        "contestPhase.PhaseStartDate <= ? AND ";
    private static final String _FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_1 = "contestPhase.PhaseEndDate >= NULL";
    private static final String _FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_2 = "contestPhase.PhaseEndDate >= ?";
    private static final String _FINDER_COLUMN_CONTESTID_CONTESTPK_2 = "contestPhase.ContestPK = ?";
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
            public ContestPhase toEntityModel() {
                return _nullContestPhase;
            }
        };

    @BeanReference(type = ContestPersistence.class)
    protected ContestPersistence contestPersistence;
    @BeanReference(type = ContestDebatePersistence.class)
    protected ContestDebatePersistence contestDebatePersistence;
    @BeanReference(type = ContestPhasePersistence.class)
    protected ContestPhasePersistence contestPhasePersistence;
    @BeanReference(type = ContestPhaseColumnPersistence.class)
    protected ContestPhaseColumnPersistence contestPhaseColumnPersistence;
    @BeanReference(type = ContestTeamMemberPersistence.class)
    protected ContestTeamMemberPersistence contestTeamMemberPersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the contest phase in the entity cache if it is enabled.
     *
     * @param contestPhase the contest phase
     */
    public void cacheResult(ContestPhase contestPhase) {
        EntityCacheUtil.putResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseImpl.class, contestPhase.getPrimaryKey(), contestPhase);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
            new Object[] {
                Long.valueOf(contestPhase.getContestPK()),
                
            contestPhase.getPhaseStartDate(),
                
            contestPhase.getPhaseEndDate()
            }, contestPhase);

        contestPhase.resetOriginalValues();
    }

    /**
     * Caches the contest phases in the entity cache if it is enabled.
     *
     * @param contestPhases the contest phases
     */
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

        clearUniqueFindersCache(contestPhase);
    }

    @Override
    public void clearCache(List<ContestPhase> contestPhases) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestPhase contestPhase : contestPhases) {
            EntityCacheUtil.removeResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseImpl.class, contestPhase.getPrimaryKey());

            clearUniqueFindersCache(contestPhase);
        }
    }

    protected void clearUniqueFindersCache(ContestPhase contestPhase) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
            new Object[] {
                Long.valueOf(contestPhase.getContestPK()),
                
            contestPhase.getPhaseStartDate(),
                
            contestPhase.getPhaseEndDate()
            });
    }

    /**
     * Creates a new contest phase with the primary key. Does not add the contest phase to the database.
     *
     * @param ContestPhasePK the primary key for the new contest phase
     * @return the new contest phase
     */
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
     * @throws com.ext.portlet.contests.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhase remove(long ContestPhasePK)
        throws NoSuchContestPhaseException, SystemException {
        return remove(Long.valueOf(ContestPhasePK));
    }

    /**
     * Removes the contest phase with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest phase
     * @return the contest phase that was removed
     * @throws com.ext.portlet.contests.NoSuchContestPhaseException if a contest phase with the primary key could not be found
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

            BatchSessionUtil.delete(session, contestPhase);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(contestPhase);

        return contestPhase;
    }

    @Override
    public ContestPhase updateImpl(
        com.ext.portlet.contests.model.ContestPhase contestPhase, boolean merge)
        throws SystemException {
        contestPhase = toUnwrappedModel(contestPhase);

        boolean isNew = contestPhase.isNew();

        ContestPhaseModelImpl contestPhaseModelImpl = (ContestPhaseModelImpl) contestPhase;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contestPhase, merge);

            contestPhase.setNew(false);
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
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(contestPhaseModelImpl.getOriginalContestPK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID,
                    args);

                args = new Object[] {
                        Long.valueOf(contestPhaseModelImpl.getContestPK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseImpl.class, contestPhase.getPrimaryKey(), contestPhase);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                new Object[] {
                    Long.valueOf(contestPhase.getContestPK()),
                    
                contestPhase.getPhaseStartDate(),
                    
                contestPhase.getPhaseEndDate()
                }, contestPhase);
        } else {
            if ((contestPhaseModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(contestPhaseModelImpl.getOriginalContestPK()),
                        
                        contestPhaseModelImpl.getOriginalPhaseStartDate(),
                        
                        contestPhaseModelImpl.getOriginalPhaseEndDate()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTIDSTARTEND,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                    new Object[] {
                        Long.valueOf(contestPhase.getContestPK()),
                        
                    contestPhase.getPhaseStartDate(),
                        
                    contestPhase.getPhaseEndDate()
                    }, contestPhase);
            }
        }

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
        contestPhaseImpl.setContestPhaseName(contestPhase.getContestPhaseName());
        contestPhaseImpl.setContestPhaseDescription(contestPhase.getContestPhaseDescription());
        contestPhaseImpl.setContestPhaseStatus(contestPhase.getContestPhaseStatus());
        contestPhaseImpl.setPhaseStartDate(contestPhase.getPhaseStartDate());
        contestPhaseImpl.setPhaseEndDate(contestPhase.getPhaseEndDate());
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
     * @throws com.liferay.portal.NoSuchModelException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhase findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contest phase with the primary key or throws a {@link com.ext.portlet.contests.NoSuchContestPhaseException} if it could not be found.
     *
     * @param ContestPhasePK the primary key of the contest phase
     * @return the contest phase
     * @throws com.ext.portlet.contests.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhase findByPrimaryKey(long ContestPhasePK)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByPrimaryKey(ContestPhasePK);

        if (contestPhase == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ContestPhasePK);
            }

            throw new NoSuchContestPhaseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                ContestPhasePK);
        }

        return contestPhase;
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
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contest phase with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ContestPhasePK the primary key of the contest phase
     * @return the contest phase, or <code>null</code> if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhase fetchByPrimaryKey(long ContestPhasePK)
        throws SystemException {
        ContestPhase contestPhase = (ContestPhase) EntityCacheUtil.getResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseImpl.class, ContestPhasePK);

        if (contestPhase == _nullContestPhase) {
            return null;
        }

        if (contestPhase == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                contestPhase = (ContestPhase) session.get(ContestPhaseImpl.class,
                        Long.valueOf(ContestPhasePK));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (contestPhase != null) {
                    cacheResult(contestPhase);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ContestPhaseModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseImpl.class, ContestPhasePK,
                        _nullContestPhase);
                }

                closeSession(session);
            }
        }

        return contestPhase;
    }

    /**
     * Returns the contest phase where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63; or throws a {@link com.ext.portlet.contests.NoSuchContestPhaseException} if it could not be found.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @return the matching contest phase
     * @throws com.ext.portlet.contests.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhase findByContestIdStartEnd(long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate)
        throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = fetchByContestIdStartEnd(ContestPK,
                PhaseStartDate, PhaseEndDate);

        if (contestPhase == null) {
            StringBundler msg = new StringBundler(8);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("ContestPK=");
            msg.append(ContestPK);

            msg.append(", PhaseStartDate=");
            msg.append(PhaseStartDate);

            msg.append(", PhaseEndDate=");
            msg.append(PhaseEndDate);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchContestPhaseException(msg.toString());
        }

        return contestPhase;
    }

    /**
     * Returns the contest phase where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @return the matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhase fetchByContestIdStartEnd(long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate) throws SystemException {
        return fetchByContestIdStartEnd(ContestPK, PhaseStartDate,
            PhaseEndDate, true);
    }

    /**
     * Returns the contest phase where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching contest phase, or <code>null</code> if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhase fetchByContestIdStartEnd(long ContestPK,
        Date PhaseStartDate, Date PhaseEndDate, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                ContestPK, PhaseStartDate, PhaseEndDate
            };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_SELECT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_CONTESTPK_2);

            if (PhaseStartDate == null) {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_1);
            } else {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_2);
            }

            if (PhaseEndDate == null) {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_1);
            } else {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_2);
            }

            query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                if (PhaseStartDate != null) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseStartDate));
                }

                if (PhaseEndDate != null) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseEndDate));
                }

                List<ContestPhase> list = q.list();

                result = list;

                ContestPhase contestPhase = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                        finderArgs, list);
                } else {
                    contestPhase = list.get(0);

                    cacheResult(contestPhase);

                    if ((contestPhase.getContestPK() != ContestPK) ||
                            (contestPhase.getPhaseStartDate() == null) ||
                            !contestPhase.getPhaseStartDate()
                                             .equals(PhaseStartDate) ||
                            (contestPhase.getPhaseEndDate() == null) ||
                            !contestPhase.getPhaseEndDate().equals(PhaseEndDate)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                            finderArgs, contestPhase);
                    }
                }

                return contestPhase;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDSTARTEND,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (ContestPhase) result;
            }
        }
    }

    /**
     * Returns all the contest phases where ContestPK = &#63;.
     *
     * @param ContestPK the contest p k
     * @return the matching contest phases
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhase> findByContestId(long ContestPK)
        throws SystemException {
        return findByContestId(ContestPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the contest phases where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @return the range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhase> findByContestId(long ContestPK, int start, int end)
        throws SystemException {
        return findByContestId(ContestPK, start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phases where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhase> findByContestId(long ContestPK, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID;
            finderArgs = new Object[] { ContestPK };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTID;
            finderArgs = new Object[] { ContestPK, start, end, orderByComparator };
        }

        List<ContestPhase> list = (List<ContestPhase>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

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
            }
            else {
                query.append(ContestPhaseModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                        start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first contest phase in the ordered set where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase
     * @throws com.ext.portlet.contests.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhase findByContestId_First(long ContestPK,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        List<ContestPhase> list = findByContestId(ContestPK, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("ContestPK=");
            msg.append(ContestPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestPhaseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last contest phase in the ordered set where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase
     * @throws com.ext.portlet.contests.NoSuchContestPhaseException if a matching contest phase could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhase findByContestId_Last(long ContestPK,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseException, SystemException {
        int count = countByContestId(ContestPK);

        List<ContestPhase> list = findByContestId(ContestPK, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("ContestPK=");
            msg.append(ContestPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestPhaseException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPhasePK the primary key of the current contest phase
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest phase
     * @throws com.ext.portlet.contests.NoSuchContestPhaseException if a contest phase with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
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
        }
        else {
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
     * Returns all the contest phases.
     *
     * @return the contest phases
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhase> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @return the range of contest phases
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhase> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phases.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contest phases
     * @param end the upper bound of the range of contest phases (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest phases
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhase> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = new Object[] { start, end, orderByComparator };

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
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
                sql = _SQL_SELECT_CONTESTPHASE.concat(ContestPhaseModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ContestPhase>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes the contest phase where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63; from the database.
     *
     * @param ContestPK the contest p k
     * @param PhaseStartDate the phase start date
     * @param PhaseEndDate the phase end date
     * @throws SystemException if a system exception occurred
     */
    public void removeByContestIdStartEnd(long ContestPK, Date PhaseStartDate,
        Date PhaseEndDate) throws NoSuchContestPhaseException, SystemException {
        ContestPhase contestPhase = findByContestIdStartEnd(ContestPK,
                PhaseStartDate, PhaseEndDate);

        remove(contestPhase);
    }

    /**
     * Removes all the contest phases where ContestPK = &#63; from the database.
     *
     * @param ContestPK the contest p k
     * @throws SystemException if a system exception occurred
     */
    public void removeByContestId(long ContestPK) throws SystemException {
        for (ContestPhase contestPhase : findByContestId(ContestPK)) {
            remove(contestPhase);
        }
    }

    /**
     * Removes all the contest phases from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ContestPhase contestPhase : findAll()) {
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
    public int countByContestIdStartEnd(long ContestPK, Date PhaseStartDate,
        Date PhaseEndDate) throws SystemException {
        Object[] finderArgs = new Object[] {
                ContestPK, PhaseStartDate, PhaseEndDate
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTIDSTARTEND,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_CONTESTPHASE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_CONTESTPK_2);

            if (PhaseStartDate == null) {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_1);
            } else {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASESTARTDATE_2);
            }

            if (PhaseEndDate == null) {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_1);
            } else {
                query.append(_FINDER_COLUMN_CONTESTIDSTARTEND_PHASEENDDATE_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                if (PhaseStartDate != null) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseStartDate));
                }

                if (PhaseEndDate != null) {
                    qPos.add(CalendarUtil.getTimestamp(PhaseEndDate));
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTIDSTARTEND,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contest phases where ContestPK = &#63;.
     *
     * @param ContestPK the contest p k
     * @return the number of matching contest phases
     * @throws SystemException if a system exception occurred
     */
    public int countByContestId(long ContestPK) throws SystemException {
        Object[] finderArgs = new Object[] { ContestPK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTID,
                finderArgs, this);

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
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contest phases.
     *
     * @return the number of contest phases
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_CONTESTPHASE);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);

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
                        "value.object.listener.com.ext.portlet.contests.model.ContestPhase")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestPhase>> listenersList = new ArrayList<ModelListener<ContestPhase>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestPhase>) InstanceFactory.newInstance(
                            listenerClassName));
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
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
