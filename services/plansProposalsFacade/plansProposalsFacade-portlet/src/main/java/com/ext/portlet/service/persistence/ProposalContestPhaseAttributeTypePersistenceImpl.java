package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException;
import com.ext.portlet.model.ProposalContestPhaseAttributeType;
import com.ext.portlet.model.impl.ProposalContestPhaseAttributeTypeImpl;
import com.ext.portlet.model.impl.ProposalContestPhaseAttributeTypeModelImpl;
import com.ext.portlet.service.persistence.ProposalContestPhaseAttributeTypePersistence;

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

/**
 * The persistence implementation for the proposal contest phase attribute type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeTypePersistence
 * @see ProposalContestPhaseAttributeTypeUtil
 * @generated
 */
public class ProposalContestPhaseAttributeTypePersistenceImpl
    extends BasePersistenceImpl<ProposalContestPhaseAttributeType>
    implements ProposalContestPhaseAttributeTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalContestPhaseAttributeTypeUtil} to access the proposal contest phase attribute type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalContestPhaseAttributeTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTETYPE = "SELECT proposalContestPhaseAttributeType FROM ProposalContestPhaseAttributeType proposalContestPhaseAttributeType";
    private static final String _SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTETYPE = "SELECT COUNT(proposalContestPhaseAttributeType) FROM ProposalContestPhaseAttributeType proposalContestPhaseAttributeType";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalContestPhaseAttributeType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalContestPhaseAttributeType exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalContestPhaseAttributeTypePersistenceImpl.class);
    private static ProposalContestPhaseAttributeType _nullProposalContestPhaseAttributeType =
        new ProposalContestPhaseAttributeTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalContestPhaseAttributeType> toCacheModel() {
                return _nullProposalContestPhaseAttributeTypeCacheModel;
            }
        };

    private static CacheModel<ProposalContestPhaseAttributeType> _nullProposalContestPhaseAttributeTypeCacheModel =
        new CacheModel<ProposalContestPhaseAttributeType>() {
            @Override
            public ProposalContestPhaseAttributeType toEntityModel() {
                return _nullProposalContestPhaseAttributeType;
            }
        };

    public ProposalContestPhaseAttributeTypePersistenceImpl() {
        setModelClass(ProposalContestPhaseAttributeType.class);
    }

    /**
     * Caches the proposal contest phase attribute type in the entity cache if it is enabled.
     *
     * @param proposalContestPhaseAttributeType the proposal contest phase attribute type
     */
    @Override
    public void cacheResult(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        EntityCacheUtil.putResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeImpl.class,
            proposalContestPhaseAttributeType.getPrimaryKey(),
            proposalContestPhaseAttributeType);

        proposalContestPhaseAttributeType.resetOriginalValues();
    }

    /**
     * Caches the proposal contest phase attribute types in the entity cache if it is enabled.
     *
     * @param proposalContestPhaseAttributeTypes the proposal contest phase attribute types
     */
    @Override
    public void cacheResult(
        List<ProposalContestPhaseAttributeType> proposalContestPhaseAttributeTypes) {
        for (ProposalContestPhaseAttributeType proposalContestPhaseAttributeType : proposalContestPhaseAttributeTypes) {
            if (EntityCacheUtil.getResult(
                        ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalContestPhaseAttributeTypeImpl.class,
                        proposalContestPhaseAttributeType.getPrimaryKey()) == null) {
                cacheResult(proposalContestPhaseAttributeType);
            } else {
                proposalContestPhaseAttributeType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal contest phase attribute types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalContestPhaseAttributeTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalContestPhaseAttributeTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal contest phase attribute type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        EntityCacheUtil.removeResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeImpl.class,
            proposalContestPhaseAttributeType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<ProposalContestPhaseAttributeType> proposalContestPhaseAttributeTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalContestPhaseAttributeType proposalContestPhaseAttributeType : proposalContestPhaseAttributeTypes) {
            EntityCacheUtil.removeResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalContestPhaseAttributeTypeImpl.class,
                proposalContestPhaseAttributeType.getPrimaryKey());
        }
    }

    /**
     * Creates a new proposal contest phase attribute type with the primary key. Does not add the proposal contest phase attribute type to the database.
     *
     * @param name the primary key for the new proposal contest phase attribute type
     * @return the new proposal contest phase attribute type
     */
    @Override
    public ProposalContestPhaseAttributeType create(String name) {
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType = new ProposalContestPhaseAttributeTypeImpl();

        proposalContestPhaseAttributeType.setNew(true);
        proposalContestPhaseAttributeType.setPrimaryKey(name);

        return proposalContestPhaseAttributeType;
    }

    /**
     * Removes the proposal contest phase attribute type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param name the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type that was removed
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttributeType remove(String name)
        throws NoSuchProposalContestPhaseAttributeTypeException, SystemException {
        return remove((Serializable) name);
    }

    /**
     * Removes the proposal contest phase attribute type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type that was removed
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttributeType remove(Serializable primaryKey)
        throws NoSuchProposalContestPhaseAttributeTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalContestPhaseAttributeType proposalContestPhaseAttributeType = (ProposalContestPhaseAttributeType) session.get(ProposalContestPhaseAttributeTypeImpl.class,
                    primaryKey);

            if (proposalContestPhaseAttributeType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalContestPhaseAttributeTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalContestPhaseAttributeType);
        } catch (NoSuchProposalContestPhaseAttributeTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalContestPhaseAttributeType removeImpl(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType)
        throws SystemException {
        proposalContestPhaseAttributeType = toUnwrappedModel(proposalContestPhaseAttributeType);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposalContestPhaseAttributeType)) {
                proposalContestPhaseAttributeType = (ProposalContestPhaseAttributeType) session.get(ProposalContestPhaseAttributeTypeImpl.class,
                        proposalContestPhaseAttributeType.getPrimaryKeyObj());
            }

            if (proposalContestPhaseAttributeType != null) {
                session.delete(proposalContestPhaseAttributeType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposalContestPhaseAttributeType != null) {
            clearCache(proposalContestPhaseAttributeType);
        }

        return proposalContestPhaseAttributeType;
    }

    @Override
    public ProposalContestPhaseAttributeType updateImpl(
        com.ext.portlet.model.ProposalContestPhaseAttributeType proposalContestPhaseAttributeType)
        throws SystemException {
        proposalContestPhaseAttributeType = toUnwrappedModel(proposalContestPhaseAttributeType);

        boolean isNew = proposalContestPhaseAttributeType.isNew();

        Session session = null;

        try {
            session = openSession();

            if (proposalContestPhaseAttributeType.isNew()) {
                session.save(proposalContestPhaseAttributeType);

                proposalContestPhaseAttributeType.setNew(false);
            } else {
                session.merge(proposalContestPhaseAttributeType);
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

        EntityCacheUtil.putResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeImpl.class,
            proposalContestPhaseAttributeType.getPrimaryKey(),
            proposalContestPhaseAttributeType);

        return proposalContestPhaseAttributeType;
    }

    protected ProposalContestPhaseAttributeType toUnwrappedModel(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        if (proposalContestPhaseAttributeType instanceof ProposalContestPhaseAttributeTypeImpl) {
            return proposalContestPhaseAttributeType;
        }

        ProposalContestPhaseAttributeTypeImpl proposalContestPhaseAttributeTypeImpl =
            new ProposalContestPhaseAttributeTypeImpl();

        proposalContestPhaseAttributeTypeImpl.setNew(proposalContestPhaseAttributeType.isNew());
        proposalContestPhaseAttributeTypeImpl.setPrimaryKey(proposalContestPhaseAttributeType.getPrimaryKey());

        proposalContestPhaseAttributeTypeImpl.setName(proposalContestPhaseAttributeType.getName());
        proposalContestPhaseAttributeTypeImpl.setCopyOnPromote(proposalContestPhaseAttributeType.isCopyOnPromote());

        return proposalContestPhaseAttributeTypeImpl;
    }

    /**
     * Returns the proposal contest phase attribute type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttributeType findByPrimaryKey(
        Serializable primaryKey)
        throws NoSuchProposalContestPhaseAttributeTypeException, SystemException {
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType = fetchByPrimaryKey(primaryKey);

        if (proposalContestPhaseAttributeType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalContestPhaseAttributeTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposalContestPhaseAttributeType;
    }

    /**
     * Returns the proposal contest phase attribute type with the primary key or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException} if it could not be found.
     *
     * @param name the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttributeType findByPrimaryKey(String name)
        throws NoSuchProposalContestPhaseAttributeTypeException, SystemException {
        return findByPrimaryKey((Serializable) name);
    }

    /**
     * Returns the proposal contest phase attribute type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type, or <code>null</code> if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttributeType fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType = (ProposalContestPhaseAttributeType) EntityCacheUtil.getResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalContestPhaseAttributeTypeImpl.class, primaryKey);

        if (proposalContestPhaseAttributeType == _nullProposalContestPhaseAttributeType) {
            return null;
        }

        if (proposalContestPhaseAttributeType == null) {
            Session session = null;

            try {
                session = openSession();

                proposalContestPhaseAttributeType = (ProposalContestPhaseAttributeType) session.get(ProposalContestPhaseAttributeTypeImpl.class,
                        primaryKey);

                if (proposalContestPhaseAttributeType != null) {
                    cacheResult(proposalContestPhaseAttributeType);
                } else {
                    EntityCacheUtil.putResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalContestPhaseAttributeTypeImpl.class,
                        primaryKey, _nullProposalContestPhaseAttributeType);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalContestPhaseAttributeTypeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposalContestPhaseAttributeType;
    }

    /**
     * Returns the proposal contest phase attribute type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param name the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type, or <code>null</code> if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttributeType fetchByPrimaryKey(String name)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) name);
    }

    /**
     * Returns all the proposal contest phase attribute types.
     *
     * @return the proposal contest phase attribute types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttributeType> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal contest phase attribute types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal contest phase attribute types
     * @param end the upper bound of the range of proposal contest phase attribute types (not inclusive)
     * @return the range of proposal contest phase attribute types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttributeType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal contest phase attribute types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal contest phase attribute types
     * @param end the upper bound of the range of proposal contest phase attribute types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal contest phase attribute types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalContestPhaseAttributeType> findAll(int start, int end,
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

        List<ProposalContestPhaseAttributeType> list = (List<ProposalContestPhaseAttributeType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTETYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTETYPE;

                if (pagination) {
                    sql = sql.concat(ProposalContestPhaseAttributeTypeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProposalContestPhaseAttributeType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalContestPhaseAttributeType>(list);
                } else {
                    list = (List<ProposalContestPhaseAttributeType>) QueryUtil.list(q,
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
     * Removes all the proposal contest phase attribute types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProposalContestPhaseAttributeType proposalContestPhaseAttributeType : findAll()) {
            remove(proposalContestPhaseAttributeType);
        }
    }

    /**
     * Returns the number of proposal contest phase attribute types.
     *
     * @return the number of proposal contest phase attribute types
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTETYPE);

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
     * Initializes the proposal contest phase attribute type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalContestPhaseAttributeType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalContestPhaseAttributeType>> listenersList =
                    new ArrayList<ModelListener<ProposalContestPhaseAttributeType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalContestPhaseAttributeType>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalContestPhaseAttributeTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
