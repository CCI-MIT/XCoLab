package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalRatingTypeException;
import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.model.impl.ProposalRatingTypeImpl;
import com.ext.portlet.model.impl.ProposalRatingTypeModelImpl;
import com.ext.portlet.service.persistence.ProposalRatingTypePersistence;

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
 * The persistence implementation for the proposal rating type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingTypePersistence
 * @see ProposalRatingTypeUtil
 * @generated
 */
public class ProposalRatingTypePersistenceImpl extends BasePersistenceImpl<ProposalRatingType>
    implements ProposalRatingTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalRatingTypeUtil} to access the proposal rating type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalRatingTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingTypeModelImpl.FINDER_CACHE_ENABLED,
            ProposalRatingTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingTypeModelImpl.FINDER_CACHE_ENABLED,
            ProposalRatingTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROPOSALRATINGTYPE = "SELECT proposalRatingType FROM ProposalRatingType proposalRatingType";
    private static final String _SQL_COUNT_PROPOSALRATINGTYPE = "SELECT COUNT(proposalRatingType) FROM ProposalRatingType proposalRatingType";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalRatingType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalRatingType exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalRatingTypePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ProposalRatingType _nullProposalRatingType = new ProposalRatingTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalRatingType> toCacheModel() {
                return _nullProposalRatingTypeCacheModel;
            }
        };

    private static CacheModel<ProposalRatingType> _nullProposalRatingTypeCacheModel =
        new CacheModel<ProposalRatingType>() {
            @Override
            public ProposalRatingType toEntityModel() {
                return _nullProposalRatingType;
            }
        };

    public ProposalRatingTypePersistenceImpl() {
        setModelClass(ProposalRatingType.class);
    }

    /**
     * Caches the proposal rating type in the entity cache if it is enabled.
     *
     * @param proposalRatingType the proposal rating type
     */
    @Override
    public void cacheResult(ProposalRatingType proposalRatingType) {
        EntityCacheUtil.putResult(ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingTypeImpl.class, proposalRatingType.getPrimaryKey(),
            proposalRatingType);

        proposalRatingType.resetOriginalValues();
    }

    /**
     * Caches the proposal rating types in the entity cache if it is enabled.
     *
     * @param proposalRatingTypes the proposal rating types
     */
    @Override
    public void cacheResult(List<ProposalRatingType> proposalRatingTypes) {
        for (ProposalRatingType proposalRatingType : proposalRatingTypes) {
            if (EntityCacheUtil.getResult(
                        ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalRatingTypeImpl.class,
                        proposalRatingType.getPrimaryKey()) == null) {
                cacheResult(proposalRatingType);
            } else {
                proposalRatingType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal rating types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalRatingTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalRatingTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal rating type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProposalRatingType proposalRatingType) {
        EntityCacheUtil.removeResult(ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingTypeImpl.class, proposalRatingType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProposalRatingType> proposalRatingTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalRatingType proposalRatingType : proposalRatingTypes) {
            EntityCacheUtil.removeResult(ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalRatingTypeImpl.class, proposalRatingType.getPrimaryKey());
        }
    }

    /**
     * Creates a new proposal rating type with the primary key. Does not add the proposal rating type to the database.
     *
     * @param id the primary key for the new proposal rating type
     * @return the new proposal rating type
     */
    @Override
    public ProposalRatingType create(long id) {
        ProposalRatingType proposalRatingType = new ProposalRatingTypeImpl();

        proposalRatingType.setNew(true);
        proposalRatingType.setPrimaryKey(id);

        return proposalRatingType;
    }

    /**
     * Removes the proposal rating type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the proposal rating type
     * @return the proposal rating type that was removed
     * @throws com.ext.portlet.NoSuchProposalRatingTypeException if a proposal rating type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingType remove(long id)
        throws NoSuchProposalRatingTypeException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the proposal rating type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal rating type
     * @return the proposal rating type that was removed
     * @throws com.ext.portlet.NoSuchProposalRatingTypeException if a proposal rating type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingType remove(Serializable primaryKey)
        throws NoSuchProposalRatingTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalRatingType proposalRatingType = (ProposalRatingType) session.get(ProposalRatingTypeImpl.class,
                    primaryKey);

            if (proposalRatingType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalRatingTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalRatingType);
        } catch (NoSuchProposalRatingTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalRatingType removeImpl(
        ProposalRatingType proposalRatingType) throws SystemException {
        proposalRatingType = toUnwrappedModel(proposalRatingType);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposalRatingType)) {
                proposalRatingType = (ProposalRatingType) session.get(ProposalRatingTypeImpl.class,
                        proposalRatingType.getPrimaryKeyObj());
            }

            if (proposalRatingType != null) {
                session.delete(proposalRatingType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposalRatingType != null) {
            clearCache(proposalRatingType);
        }

        return proposalRatingType;
    }

    @Override
    public ProposalRatingType updateImpl(
        com.ext.portlet.model.ProposalRatingType proposalRatingType)
        throws SystemException {
        proposalRatingType = toUnwrappedModel(proposalRatingType);

        boolean isNew = proposalRatingType.isNew();

        Session session = null;

        try {
            session = openSession();

            if (proposalRatingType.isNew()) {
                session.save(proposalRatingType);

                proposalRatingType.setNew(false);
            } else {
                session.merge(proposalRatingType);
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

        EntityCacheUtil.putResult(ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingTypeImpl.class, proposalRatingType.getPrimaryKey(),
            proposalRatingType);

        return proposalRatingType;
    }

    protected ProposalRatingType toUnwrappedModel(
        ProposalRatingType proposalRatingType) {
        if (proposalRatingType instanceof ProposalRatingTypeImpl) {
            return proposalRatingType;
        }

        ProposalRatingTypeImpl proposalRatingTypeImpl = new ProposalRatingTypeImpl();

        proposalRatingTypeImpl.setNew(proposalRatingType.isNew());
        proposalRatingTypeImpl.setPrimaryKey(proposalRatingType.getPrimaryKey());

        proposalRatingTypeImpl.setId(proposalRatingType.getId());
        proposalRatingTypeImpl.setLabel(proposalRatingType.getLabel());
        proposalRatingTypeImpl.setDescription(proposalRatingType.getDescription());
        proposalRatingTypeImpl.setJudgeType(proposalRatingType.getJudgeType());
        proposalRatingTypeImpl.setIsActive(proposalRatingType.isIsActive());

        return proposalRatingTypeImpl;
    }

    /**
     * Returns the proposal rating type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal rating type
     * @return the proposal rating type
     * @throws com.ext.portlet.NoSuchProposalRatingTypeException if a proposal rating type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProposalRatingTypeException, SystemException {
        ProposalRatingType proposalRatingType = fetchByPrimaryKey(primaryKey);

        if (proposalRatingType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalRatingTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposalRatingType;
    }

    /**
     * Returns the proposal rating type with the primary key or throws a {@link com.ext.portlet.NoSuchProposalRatingTypeException} if it could not be found.
     *
     * @param id the primary key of the proposal rating type
     * @return the proposal rating type
     * @throws com.ext.portlet.NoSuchProposalRatingTypeException if a proposal rating type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingType findByPrimaryKey(long id)
        throws NoSuchProposalRatingTypeException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the proposal rating type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal rating type
     * @return the proposal rating type, or <code>null</code> if a proposal rating type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingType fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProposalRatingType proposalRatingType = (ProposalRatingType) EntityCacheUtil.getResult(ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalRatingTypeImpl.class, primaryKey);

        if (proposalRatingType == _nullProposalRatingType) {
            return null;
        }

        if (proposalRatingType == null) {
            Session session = null;

            try {
                session = openSession();

                proposalRatingType = (ProposalRatingType) session.get(ProposalRatingTypeImpl.class,
                        primaryKey);

                if (proposalRatingType != null) {
                    cacheResult(proposalRatingType);
                } else {
                    EntityCacheUtil.putResult(ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalRatingTypeImpl.class, primaryKey,
                        _nullProposalRatingType);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalRatingTypeModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalRatingTypeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposalRatingType;
    }

    /**
     * Returns the proposal rating type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the proposal rating type
     * @return the proposal rating type, or <code>null</code> if a proposal rating type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingType fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the proposal rating types.
     *
     * @return the proposal rating types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalRatingType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal rating types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal rating types
     * @param end the upper bound of the range of proposal rating types (not inclusive)
     * @return the range of proposal rating types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalRatingType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal rating types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal rating types
     * @param end the upper bound of the range of proposal rating types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal rating types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalRatingType> findAll(int start, int end,
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

        List<ProposalRatingType> list = (List<ProposalRatingType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALRATINGTYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALRATINGTYPE;

                if (pagination) {
                    sql = sql.concat(ProposalRatingTypeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProposalRatingType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalRatingType>(list);
                } else {
                    list = (List<ProposalRatingType>) QueryUtil.list(q,
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
     * Removes all the proposal rating types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProposalRatingType proposalRatingType : findAll()) {
            remove(proposalRatingType);
        }
    }

    /**
     * Returns the number of proposal rating types.
     *
     * @return the number of proposal rating types
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSALRATINGTYPE);

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
     * Initializes the proposal rating type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalRatingType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalRatingType>> listenersList = new ArrayList<ModelListener<ProposalRatingType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalRatingType>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalRatingTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
