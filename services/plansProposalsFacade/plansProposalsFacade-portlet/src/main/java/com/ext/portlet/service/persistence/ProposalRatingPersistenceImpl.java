package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalRatingException;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.model.impl.ProposalRatingImpl;
import com.ext.portlet.model.impl.ProposalRatingModelImpl;
import com.ext.portlet.service.persistence.ProposalRatingPersistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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
 * The persistence implementation for the proposal rating service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingPersistence
 * @see ProposalRatingUtil
 * @generated
 */
public class ProposalRatingPersistenceImpl extends BasePersistenceImpl<ProposalRating>
    implements ProposalRatingPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalRatingUtil} to access the proposal rating persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalRatingImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingModelImpl.FINDER_CACHE_ENABLED,
            ProposalRatingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingModelImpl.FINDER_CACHE_ENABLED,
            ProposalRatingImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROPOSALRATING = "SELECT proposalRating FROM ProposalRating proposalRating";
    private static final String _SQL_COUNT_PROPOSALRATING = "SELECT COUNT(proposalRating) FROM ProposalRating proposalRating";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalRating.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalRating exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalRatingPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id", "comment"
            });
    private static ProposalRating _nullProposalRating = new ProposalRatingImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalRating> toCacheModel() {
                return _nullProposalRatingCacheModel;
            }
        };

    private static CacheModel<ProposalRating> _nullProposalRatingCacheModel = new CacheModel<ProposalRating>() {
            @Override
            public ProposalRating toEntityModel() {
                return _nullProposalRating;
            }
        };

    public ProposalRatingPersistenceImpl() {
        setModelClass(ProposalRating.class);
    }

    /**
     * Caches the proposal rating in the entity cache if it is enabled.
     *
     * @param proposalRating the proposal rating
     */
    @Override
    public void cacheResult(ProposalRating proposalRating) {
        EntityCacheUtil.putResult(ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingImpl.class, proposalRating.getPrimaryKey(),
            proposalRating);

        proposalRating.resetOriginalValues();
    }

    /**
     * Caches the proposal ratings in the entity cache if it is enabled.
     *
     * @param proposalRatings the proposal ratings
     */
    @Override
    public void cacheResult(List<ProposalRating> proposalRatings) {
        for (ProposalRating proposalRating : proposalRatings) {
            if (EntityCacheUtil.getResult(
                        ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalRatingImpl.class, proposalRating.getPrimaryKey()) == null) {
                cacheResult(proposalRating);
            } else {
                proposalRating.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal ratings.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalRatingImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalRatingImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal rating.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProposalRating proposalRating) {
        EntityCacheUtil.removeResult(ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingImpl.class, proposalRating.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProposalRating> proposalRatings) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalRating proposalRating : proposalRatings) {
            EntityCacheUtil.removeResult(ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
                ProposalRatingImpl.class, proposalRating.getPrimaryKey());
        }
    }

    /**
     * Creates a new proposal rating with the primary key. Does not add the proposal rating to the database.
     *
     * @param id the primary key for the new proposal rating
     * @return the new proposal rating
     */
    @Override
    public ProposalRating create(long id) {
        ProposalRating proposalRating = new ProposalRatingImpl();

        proposalRating.setNew(true);
        proposalRating.setPrimaryKey(id);

        return proposalRating;
    }

    /**
     * Removes the proposal rating with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the proposal rating
     * @return the proposal rating that was removed
     * @throws com.ext.portlet.NoSuchProposalRatingException if a proposal rating with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRating remove(long id)
        throws NoSuchProposalRatingException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the proposal rating with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal rating
     * @return the proposal rating that was removed
     * @throws com.ext.portlet.NoSuchProposalRatingException if a proposal rating with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRating remove(Serializable primaryKey)
        throws NoSuchProposalRatingException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalRating proposalRating = (ProposalRating) session.get(ProposalRatingImpl.class,
                    primaryKey);

            if (proposalRating == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalRatingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalRating);
        } catch (NoSuchProposalRatingException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalRating removeImpl(ProposalRating proposalRating)
        throws SystemException {
        proposalRating = toUnwrappedModel(proposalRating);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposalRating)) {
                proposalRating = (ProposalRating) session.get(ProposalRatingImpl.class,
                        proposalRating.getPrimaryKeyObj());
            }

            if (proposalRating != null) {
                session.delete(proposalRating);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposalRating != null) {
            clearCache(proposalRating);
        }

        return proposalRating;
    }

    @Override
    public ProposalRating updateImpl(
        com.ext.portlet.model.ProposalRating proposalRating)
        throws SystemException {
        proposalRating = toUnwrappedModel(proposalRating);

        boolean isNew = proposalRating.isNew();

        Session session = null;

        try {
            session = openSession();

            if (proposalRating.isNew()) {
                session.save(proposalRating);

                proposalRating.setNew(false);
            } else {
                session.merge(proposalRating);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingImpl.class, proposalRating.getPrimaryKey(),
            proposalRating);

        return proposalRating;
    }

    protected ProposalRating toUnwrappedModel(ProposalRating proposalRating) {
        if (proposalRating instanceof ProposalRatingImpl) {
            return proposalRating;
        }

        ProposalRatingImpl proposalRatingImpl = new ProposalRatingImpl();

        proposalRatingImpl.setNew(proposalRating.isNew());
        proposalRatingImpl.setPrimaryKey(proposalRating.getPrimaryKey());

        proposalRatingImpl.setId(proposalRating.getId());
        proposalRatingImpl.setProposalId(proposalRating.getProposalId());
        proposalRatingImpl.setContestPhaseId(proposalRating.getContestPhaseId());
        proposalRatingImpl.setUserId(proposalRating.getUserId());
        proposalRatingImpl.setRatingValueId(proposalRating.getRatingValueId());
        proposalRatingImpl.setComment(proposalRating.getComment());
        proposalRatingImpl.setCommentEnabled(proposalRating.isCommentEnabled());
        proposalRatingImpl.setOtherDataString(proposalRating.getOtherDataString());

        return proposalRatingImpl;
    }

    /**
     * Returns the proposal rating with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal rating
     * @return the proposal rating
     * @throws com.ext.portlet.NoSuchProposalRatingException if a proposal rating with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRating findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProposalRatingException, SystemException {
        ProposalRating proposalRating = fetchByPrimaryKey(primaryKey);

        if (proposalRating == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalRatingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposalRating;
    }

    /**
     * Returns the proposal rating with the primary key or throws a {@link com.ext.portlet.NoSuchProposalRatingException} if it could not be found.
     *
     * @param id the primary key of the proposal rating
     * @return the proposal rating
     * @throws com.ext.portlet.NoSuchProposalRatingException if a proposal rating with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRating findByPrimaryKey(long id)
        throws NoSuchProposalRatingException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the proposal rating with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal rating
     * @return the proposal rating, or <code>null</code> if a proposal rating with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRating fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProposalRating proposalRating = (ProposalRating) EntityCacheUtil.getResult(ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
                ProposalRatingImpl.class, primaryKey);

        if (proposalRating == _nullProposalRating) {
            return null;
        }

        if (proposalRating == null) {
            Session session = null;

            try {
                session = openSession();

                proposalRating = (ProposalRating) session.get(ProposalRatingImpl.class,
                        primaryKey);

                if (proposalRating != null) {
                    cacheResult(proposalRating);
                } else {
                    EntityCacheUtil.putResult(ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalRatingImpl.class, primaryKey,
                        _nullProposalRating);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalRatingModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalRatingImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposalRating;
    }

    /**
     * Returns the proposal rating with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the proposal rating
     * @return the proposal rating, or <code>null</code> if a proposal rating with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRating fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the proposal ratings.
     *
     * @return the proposal ratings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalRating> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal ratings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal ratings
     * @param end the upper bound of the range of proposal ratings (not inclusive)
     * @return the range of proposal ratings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalRating> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal ratings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal ratings
     * @param end the upper bound of the range of proposal ratings (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal ratings
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalRating> findAll(int start, int end,
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

        List<ProposalRating> list = (List<ProposalRating>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALRATING);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALRATING;

                if (pagination) {
                    sql = sql.concat(ProposalRatingModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProposalRating>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalRating>(list);
                } else {
                    list = (List<ProposalRating>) QueryUtil.list(q,
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
     * Removes all the proposal ratings from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProposalRating proposalRating : findAll()) {
            remove(proposalRating);
        }
    }

    /**
     * Returns the number of proposal ratings.
     *
     * @return the number of proposal ratings
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSALRATING);

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
     * Initializes the proposal rating persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalRating")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalRating>> listenersList = new ArrayList<ModelListener<ProposalRating>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalRating>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalRatingImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
