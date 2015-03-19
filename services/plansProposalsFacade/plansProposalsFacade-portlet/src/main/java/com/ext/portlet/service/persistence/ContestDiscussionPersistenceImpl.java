package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestDiscussionException;
import com.ext.portlet.model.ContestDiscussion;
import com.ext.portlet.model.impl.ContestDiscussionImpl;
import com.ext.portlet.model.impl.ContestDiscussionModelImpl;
import com.ext.portlet.service.persistence.ContestDiscussionPersistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the contest discussion service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestDiscussionPersistence
 * @see ContestDiscussionUtil
 * @generated
 */
public class ContestDiscussionPersistenceImpl extends BasePersistenceImpl<ContestDiscussion>
    implements ContestDiscussionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestDiscussionUtil} to access the contest discussion persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestDiscussionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ContestDiscussionModelImpl.FINDER_CACHE_ENABLED,
            ContestDiscussionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ContestDiscussionModelImpl.FINDER_CACHE_ENABLED,
            ContestDiscussionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ContestDiscussionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_CONTESTIDANDTAB = new FinderPath(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ContestDiscussionModelImpl.FINDER_CACHE_ENABLED,
            ContestDiscussionImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByContestIdAndTab",
            new String[] { Long.class.getName(), String.class.getName() },
            ContestDiscussionModelImpl.CONTESTID_COLUMN_BITMASK |
            ContestDiscussionModelImpl.TAB_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTIDANDTAB = new FinderPath(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ContestDiscussionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByContestIdAndTab",
            new String[] { Long.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTIDANDTAB_CONTESTID_2 = "contestDiscussion.ContestId = ? AND ";
    private static final String _FINDER_COLUMN_CONTESTIDANDTAB_TAB_1 = "contestDiscussion.Tab IS NULL";
    private static final String _FINDER_COLUMN_CONTESTIDANDTAB_TAB_2 = "contestDiscussion.Tab = ?";
    private static final String _FINDER_COLUMN_CONTESTIDANDTAB_TAB_3 = "(contestDiscussion.Tab IS NULL OR contestDiscussion.Tab = '')";
    private static final String _SQL_SELECT_CONTESTDISCUSSION = "SELECT contestDiscussion FROM ContestDiscussion contestDiscussion";
    private static final String _SQL_SELECT_CONTESTDISCUSSION_WHERE = "SELECT contestDiscussion FROM ContestDiscussion contestDiscussion WHERE ";
    private static final String _SQL_COUNT_CONTESTDISCUSSION = "SELECT COUNT(contestDiscussion) FROM ContestDiscussion contestDiscussion";
    private static final String _SQL_COUNT_CONTESTDISCUSSION_WHERE = "SELECT COUNT(contestDiscussion) FROM ContestDiscussion contestDiscussion WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestDiscussion.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestDiscussion exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContestDiscussion exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestDiscussionPersistenceImpl.class);
    private static ContestDiscussion _nullContestDiscussion = new ContestDiscussionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestDiscussion> toCacheModel() {
                return _nullContestDiscussionCacheModel;
            }
        };

    private static CacheModel<ContestDiscussion> _nullContestDiscussionCacheModel =
        new CacheModel<ContestDiscussion>() {
            @Override
            public ContestDiscussion toEntityModel() {
                return _nullContestDiscussion;
            }
        };

    public ContestDiscussionPersistenceImpl() {
        setModelClass(ContestDiscussion.class);
    }

    /**
     * Returns the contest discussion where ContestId = &#63; and Tab = &#63; or throws a {@link com.ext.portlet.NoSuchContestDiscussionException} if it could not be found.
     *
     * @param ContestId the contest ID
     * @param Tab the tab
     * @return the matching contest discussion
     * @throws com.ext.portlet.NoSuchContestDiscussionException if a matching contest discussion could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDiscussion findByContestIdAndTab(long ContestId, String Tab)
        throws NoSuchContestDiscussionException, SystemException {
        ContestDiscussion contestDiscussion = fetchByContestIdAndTab(ContestId,
                Tab);

        if (contestDiscussion == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("ContestId=");
            msg.append(ContestId);

            msg.append(", Tab=");
            msg.append(Tab);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchContestDiscussionException(msg.toString());
        }

        return contestDiscussion;
    }

    /**
     * Returns the contest discussion where ContestId = &#63; and Tab = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param ContestId the contest ID
     * @param Tab the tab
     * @return the matching contest discussion, or <code>null</code> if a matching contest discussion could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDiscussion fetchByContestIdAndTab(long ContestId, String Tab)
        throws SystemException {
        return fetchByContestIdAndTab(ContestId, Tab, true);
    }

    /**
     * Returns the contest discussion where ContestId = &#63; and Tab = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param ContestId the contest ID
     * @param Tab the tab
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching contest discussion, or <code>null</code> if a matching contest discussion could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDiscussion fetchByContestIdAndTab(long ContestId, String Tab,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { ContestId, Tab };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTESTIDANDTAB,
                    finderArgs, this);
        }

        if (result instanceof ContestDiscussion) {
            ContestDiscussion contestDiscussion = (ContestDiscussion) result;

            if ((ContestId != contestDiscussion.getContestId()) ||
                    !Validator.equals(Tab, contestDiscussion.getTab())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_CONTESTDISCUSSION_WHERE);

            query.append(_FINDER_COLUMN_CONTESTIDANDTAB_CONTESTID_2);

            boolean bindTab = false;

            if (Tab == null) {
                query.append(_FINDER_COLUMN_CONTESTIDANDTAB_TAB_1);
            } else if (Tab.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CONTESTIDANDTAB_TAB_3);
            } else {
                bindTab = true;

                query.append(_FINDER_COLUMN_CONTESTIDANDTAB_TAB_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestId);

                if (bindTab) {
                    qPos.add(Tab);
                }

                List<ContestDiscussion> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDANDTAB,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ContestDiscussionPersistenceImpl.fetchByContestIdAndTab(long, String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ContestDiscussion contestDiscussion = list.get(0);

                    result = contestDiscussion;

                    cacheResult(contestDiscussion);

                    if ((contestDiscussion.getContestId() != ContestId) ||
                            (contestDiscussion.getTab() == null) ||
                            !contestDiscussion.getTab().equals(Tab)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDANDTAB,
                            finderArgs, contestDiscussion);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDANDTAB,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ContestDiscussion) result;
        }
    }

    /**
     * Removes the contest discussion where ContestId = &#63; and Tab = &#63; from the database.
     *
     * @param ContestId the contest ID
     * @param Tab the tab
     * @return the contest discussion that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDiscussion removeByContestIdAndTab(long ContestId, String Tab)
        throws NoSuchContestDiscussionException, SystemException {
        ContestDiscussion contestDiscussion = findByContestIdAndTab(ContestId,
                Tab);

        return remove(contestDiscussion);
    }

    /**
     * Returns the number of contest discussions where ContestId = &#63; and Tab = &#63;.
     *
     * @param ContestId the contest ID
     * @param Tab the tab
     * @return the number of matching contest discussions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestIdAndTab(long ContestId, String Tab)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTIDANDTAB;

        Object[] finderArgs = new Object[] { ContestId, Tab };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTESTDISCUSSION_WHERE);

            query.append(_FINDER_COLUMN_CONTESTIDANDTAB_CONTESTID_2);

            boolean bindTab = false;

            if (Tab == null) {
                query.append(_FINDER_COLUMN_CONTESTIDANDTAB_TAB_1);
            } else if (Tab.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_CONTESTIDANDTAB_TAB_3);
            } else {
                bindTab = true;

                query.append(_FINDER_COLUMN_CONTESTIDANDTAB_TAB_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestId);

                if (bindTab) {
                    qPos.add(Tab);
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
     * Caches the contest discussion in the entity cache if it is enabled.
     *
     * @param contestDiscussion the contest discussion
     */
    @Override
    public void cacheResult(ContestDiscussion contestDiscussion) {
        EntityCacheUtil.putResult(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ContestDiscussionImpl.class, contestDiscussion.getPrimaryKey(),
            contestDiscussion);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDANDTAB,
            new Object[] {
                contestDiscussion.getContestId(), contestDiscussion.getTab()
            }, contestDiscussion);

        contestDiscussion.resetOriginalValues();
    }

    /**
     * Caches the contest discussions in the entity cache if it is enabled.
     *
     * @param contestDiscussions the contest discussions
     */
    @Override
    public void cacheResult(List<ContestDiscussion> contestDiscussions) {
        for (ContestDiscussion contestDiscussion : contestDiscussions) {
            if (EntityCacheUtil.getResult(
                        ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                        ContestDiscussionImpl.class,
                        contestDiscussion.getPrimaryKey()) == null) {
                cacheResult(contestDiscussion);
            } else {
                contestDiscussion.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest discussions.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestDiscussionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestDiscussionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest discussion.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestDiscussion contestDiscussion) {
        EntityCacheUtil.removeResult(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ContestDiscussionImpl.class, contestDiscussion.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(contestDiscussion);
    }

    @Override
    public void clearCache(List<ContestDiscussion> contestDiscussions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestDiscussion contestDiscussion : contestDiscussions) {
            EntityCacheUtil.removeResult(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                ContestDiscussionImpl.class, contestDiscussion.getPrimaryKey());

            clearUniqueFindersCache(contestDiscussion);
        }
    }

    protected void cacheUniqueFindersCache(ContestDiscussion contestDiscussion) {
        if (contestDiscussion.isNew()) {
            Object[] args = new Object[] {
                    contestDiscussion.getContestId(), contestDiscussion.getTab()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTIDANDTAB,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDANDTAB,
                args, contestDiscussion);
        } else {
            ContestDiscussionModelImpl contestDiscussionModelImpl = (ContestDiscussionModelImpl) contestDiscussion;

            if ((contestDiscussionModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CONTESTIDANDTAB.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        contestDiscussion.getContestId(),
                        contestDiscussion.getTab()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTIDANDTAB,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDANDTAB,
                    args, contestDiscussion);
            }
        }
    }

    protected void clearUniqueFindersCache(ContestDiscussion contestDiscussion) {
        ContestDiscussionModelImpl contestDiscussionModelImpl = (ContestDiscussionModelImpl) contestDiscussion;

        Object[] args = new Object[] {
                contestDiscussion.getContestId(), contestDiscussion.getTab()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTIDANDTAB, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDANDTAB, args);

        if ((contestDiscussionModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_CONTESTIDANDTAB.getColumnBitmask()) != 0) {
            args = new Object[] {
                    contestDiscussionModelImpl.getOriginalContestId(),
                    contestDiscussionModelImpl.getOriginalTab()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTIDANDTAB,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDANDTAB,
                args);
        }
    }

    /**
     * Creates a new contest discussion with the primary key. Does not add the contest discussion to the database.
     *
     * @param DiscussionId the primary key for the new contest discussion
     * @return the new contest discussion
     */
    @Override
    public ContestDiscussion create(long DiscussionId) {
        ContestDiscussion contestDiscussion = new ContestDiscussionImpl();

        contestDiscussion.setNew(true);
        contestDiscussion.setPrimaryKey(DiscussionId);

        return contestDiscussion;
    }

    /**
     * Removes the contest discussion with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param DiscussionId the primary key of the contest discussion
     * @return the contest discussion that was removed
     * @throws com.ext.portlet.NoSuchContestDiscussionException if a contest discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDiscussion remove(long DiscussionId)
        throws NoSuchContestDiscussionException, SystemException {
        return remove((Serializable) DiscussionId);
    }

    /**
     * Removes the contest discussion with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest discussion
     * @return the contest discussion that was removed
     * @throws com.ext.portlet.NoSuchContestDiscussionException if a contest discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDiscussion remove(Serializable primaryKey)
        throws NoSuchContestDiscussionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestDiscussion contestDiscussion = (ContestDiscussion) session.get(ContestDiscussionImpl.class,
                    primaryKey);

            if (contestDiscussion == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestDiscussionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestDiscussion);
        } catch (NoSuchContestDiscussionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestDiscussion removeImpl(ContestDiscussion contestDiscussion)
        throws SystemException {
        contestDiscussion = toUnwrappedModel(contestDiscussion);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contestDiscussion)) {
                contestDiscussion = (ContestDiscussion) session.get(ContestDiscussionImpl.class,
                        contestDiscussion.getPrimaryKeyObj());
            }

            if (contestDiscussion != null) {
                session.delete(contestDiscussion);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contestDiscussion != null) {
            clearCache(contestDiscussion);
        }

        return contestDiscussion;
    }

    @Override
    public ContestDiscussion updateImpl(
        com.ext.portlet.model.ContestDiscussion contestDiscussion)
        throws SystemException {
        contestDiscussion = toUnwrappedModel(contestDiscussion);

        boolean isNew = contestDiscussion.isNew();

        Session session = null;

        try {
            session = openSession();

            if (contestDiscussion.isNew()) {
                session.save(contestDiscussion);

                contestDiscussion.setNew(false);
            } else {
                session.merge(contestDiscussion);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ContestDiscussionModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ContestDiscussionImpl.class, contestDiscussion.getPrimaryKey(),
            contestDiscussion);

        clearUniqueFindersCache(contestDiscussion);
        cacheUniqueFindersCache(contestDiscussion);

        return contestDiscussion;
    }

    protected ContestDiscussion toUnwrappedModel(
        ContestDiscussion contestDiscussion) {
        if (contestDiscussion instanceof ContestDiscussionImpl) {
            return contestDiscussion;
        }

        ContestDiscussionImpl contestDiscussionImpl = new ContestDiscussionImpl();

        contestDiscussionImpl.setNew(contestDiscussion.isNew());
        contestDiscussionImpl.setPrimaryKey(contestDiscussion.getPrimaryKey());

        contestDiscussionImpl.setDiscussionId(contestDiscussion.getDiscussionId());
        contestDiscussionImpl.setContestId(contestDiscussion.getContestId());
        contestDiscussionImpl.setTab(contestDiscussion.getTab());

        return contestDiscussionImpl;
    }

    /**
     * Returns the contest discussion with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest discussion
     * @return the contest discussion
     * @throws com.ext.portlet.NoSuchContestDiscussionException if a contest discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDiscussion findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContestDiscussionException, SystemException {
        ContestDiscussion contestDiscussion = fetchByPrimaryKey(primaryKey);

        if (contestDiscussion == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContestDiscussionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contestDiscussion;
    }

    /**
     * Returns the contest discussion with the primary key or throws a {@link com.ext.portlet.NoSuchContestDiscussionException} if it could not be found.
     *
     * @param DiscussionId the primary key of the contest discussion
     * @return the contest discussion
     * @throws com.ext.portlet.NoSuchContestDiscussionException if a contest discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDiscussion findByPrimaryKey(long DiscussionId)
        throws NoSuchContestDiscussionException, SystemException {
        return findByPrimaryKey((Serializable) DiscussionId);
    }

    /**
     * Returns the contest discussion with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest discussion
     * @return the contest discussion, or <code>null</code> if a contest discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDiscussion fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContestDiscussion contestDiscussion = (ContestDiscussion) EntityCacheUtil.getResult(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                ContestDiscussionImpl.class, primaryKey);

        if (contestDiscussion == _nullContestDiscussion) {
            return null;
        }

        if (contestDiscussion == null) {
            Session session = null;

            try {
                session = openSession();

                contestDiscussion = (ContestDiscussion) session.get(ContestDiscussionImpl.class,
                        primaryKey);

                if (contestDiscussion != null) {
                    cacheResult(contestDiscussion);
                } else {
                    EntityCacheUtil.putResult(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                        ContestDiscussionImpl.class, primaryKey,
                        _nullContestDiscussion);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContestDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                    ContestDiscussionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contestDiscussion;
    }

    /**
     * Returns the contest discussion with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param DiscussionId the primary key of the contest discussion
     * @return the contest discussion, or <code>null</code> if a contest discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDiscussion fetchByPrimaryKey(long DiscussionId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) DiscussionId);
    }

    /**
     * Returns all the contest discussions.
     *
     * @return the contest discussions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestDiscussion> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest discussions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestDiscussionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest discussions
     * @param end the upper bound of the range of contest discussions (not inclusive)
     * @return the range of contest discussions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestDiscussion> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest discussions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestDiscussionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest discussions
     * @param end the upper bound of the range of contest discussions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest discussions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestDiscussion> findAll(int start, int end,
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

        List<ContestDiscussion> list = (List<ContestDiscussion>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTDISCUSSION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTDISCUSSION;

                if (pagination) {
                    sql = sql.concat(ContestDiscussionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContestDiscussion>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestDiscussion>(list);
                } else {
                    list = (List<ContestDiscussion>) QueryUtil.list(q,
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
     * Removes all the contest discussions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContestDiscussion contestDiscussion : findAll()) {
            remove(contestDiscussion);
        }
    }

    /**
     * Returns the number of contest discussions.
     *
     * @return the number of contest discussions
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

                Query q = session.createQuery(_SQL_COUNT_CONTESTDISCUSSION);

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
     * Initializes the contest discussion persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestDiscussion")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestDiscussion>> listenersList = new ArrayList<ModelListener<ContestDiscussion>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestDiscussion>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestDiscussionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
