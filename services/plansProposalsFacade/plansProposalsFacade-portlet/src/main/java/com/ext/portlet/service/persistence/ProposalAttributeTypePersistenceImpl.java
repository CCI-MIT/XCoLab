package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalAttributeTypeException;
import com.ext.portlet.model.ProposalAttributeType;
import com.ext.portlet.model.impl.ProposalAttributeTypeImpl;
import com.ext.portlet.model.impl.ProposalAttributeTypeModelImpl;
import com.ext.portlet.service.persistence.ProposalAttributeTypePersistence;

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
 * The persistence implementation for the proposal attribute type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributeTypePersistence
 * @see ProposalAttributeTypeUtil
 * @generated
 */
public class ProposalAttributeTypePersistenceImpl extends BasePersistenceImpl<ProposalAttributeType>
    implements ProposalAttributeTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalAttributeTypeUtil} to access the proposal attribute type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalAttributeTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROPOSALATTRIBUTETYPE = "SELECT proposalAttributeType FROM ProposalAttributeType proposalAttributeType";
    private static final String _SQL_COUNT_PROPOSALATTRIBUTETYPE = "SELECT COUNT(proposalAttributeType) FROM ProposalAttributeType proposalAttributeType";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalAttributeType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalAttributeType exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalAttributeTypePersistenceImpl.class);
    private static ProposalAttributeType _nullProposalAttributeType = new ProposalAttributeTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalAttributeType> toCacheModel() {
                return _nullProposalAttributeTypeCacheModel;
            }
        };

    private static CacheModel<ProposalAttributeType> _nullProposalAttributeTypeCacheModel =
        new CacheModel<ProposalAttributeType>() {
            @Override
            public ProposalAttributeType toEntityModel() {
                return _nullProposalAttributeType;
            }
        };

    public ProposalAttributeTypePersistenceImpl() {
        setModelClass(ProposalAttributeType.class);
    }

    /**
     * Caches the proposal attribute type in the entity cache if it is enabled.
     *
     * @param proposalAttributeType the proposal attribute type
     */
    @Override
    public void cacheResult(ProposalAttributeType proposalAttributeType) {
        EntityCacheUtil.putResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeImpl.class,
            proposalAttributeType.getPrimaryKey(), proposalAttributeType);

        proposalAttributeType.resetOriginalValues();
    }

    /**
     * Caches the proposal attribute types in the entity cache if it is enabled.
     *
     * @param proposalAttributeTypes the proposal attribute types
     */
    @Override
    public void cacheResult(List<ProposalAttributeType> proposalAttributeTypes) {
        for (ProposalAttributeType proposalAttributeType : proposalAttributeTypes) {
            if (EntityCacheUtil.getResult(
                        ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalAttributeTypeImpl.class,
                        proposalAttributeType.getPrimaryKey()) == null) {
                cacheResult(proposalAttributeType);
            } else {
                proposalAttributeType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal attribute types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalAttributeTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalAttributeTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal attribute type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProposalAttributeType proposalAttributeType) {
        EntityCacheUtil.removeResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeImpl.class,
            proposalAttributeType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProposalAttributeType> proposalAttributeTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalAttributeType proposalAttributeType : proposalAttributeTypes) {
            EntityCacheUtil.removeResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalAttributeTypeImpl.class,
                proposalAttributeType.getPrimaryKey());
        }
    }

    /**
     * Creates a new proposal attribute type with the primary key. Does not add the proposal attribute type to the database.
     *
     * @param name the primary key for the new proposal attribute type
     * @return the new proposal attribute type
     */
    @Override
    public ProposalAttributeType create(String name) {
        ProposalAttributeType proposalAttributeType = new ProposalAttributeTypeImpl();

        proposalAttributeType.setNew(true);
        proposalAttributeType.setPrimaryKey(name);

        return proposalAttributeType;
    }

    /**
     * Removes the proposal attribute type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param name the primary key of the proposal attribute type
     * @return the proposal attribute type that was removed
     * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttributeType remove(String name)
        throws NoSuchProposalAttributeTypeException, SystemException {
        return remove((Serializable) name);
    }

    /**
     * Removes the proposal attribute type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal attribute type
     * @return the proposal attribute type that was removed
     * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttributeType remove(Serializable primaryKey)
        throws NoSuchProposalAttributeTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalAttributeType proposalAttributeType = (ProposalAttributeType) session.get(ProposalAttributeTypeImpl.class,
                    primaryKey);

            if (proposalAttributeType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalAttributeTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalAttributeType);
        } catch (NoSuchProposalAttributeTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalAttributeType removeImpl(
        ProposalAttributeType proposalAttributeType) throws SystemException {
        proposalAttributeType = toUnwrappedModel(proposalAttributeType);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposalAttributeType)) {
                proposalAttributeType = (ProposalAttributeType) session.get(ProposalAttributeTypeImpl.class,
                        proposalAttributeType.getPrimaryKeyObj());
            }

            if (proposalAttributeType != null) {
                session.delete(proposalAttributeType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposalAttributeType != null) {
            clearCache(proposalAttributeType);
        }

        return proposalAttributeType;
    }

    @Override
    public ProposalAttributeType updateImpl(
        com.ext.portlet.model.ProposalAttributeType proposalAttributeType)
        throws SystemException {
        proposalAttributeType = toUnwrappedModel(proposalAttributeType);

        boolean isNew = proposalAttributeType.isNew();

        Session session = null;

        try {
            session = openSession();

            if (proposalAttributeType.isNew()) {
                session.save(proposalAttributeType);

                proposalAttributeType.setNew(false);
            } else {
                session.merge(proposalAttributeType);
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

        EntityCacheUtil.putResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeImpl.class,
            proposalAttributeType.getPrimaryKey(), proposalAttributeType);

        return proposalAttributeType;
    }

    protected ProposalAttributeType toUnwrappedModel(
        ProposalAttributeType proposalAttributeType) {
        if (proposalAttributeType instanceof ProposalAttributeTypeImpl) {
            return proposalAttributeType;
        }

        ProposalAttributeTypeImpl proposalAttributeTypeImpl = new ProposalAttributeTypeImpl();

        proposalAttributeTypeImpl.setNew(proposalAttributeType.isNew());
        proposalAttributeTypeImpl.setPrimaryKey(proposalAttributeType.getPrimaryKey());

        proposalAttributeTypeImpl.setName(proposalAttributeType.getName());
        proposalAttributeTypeImpl.setVisibleInVersionHistory(proposalAttributeType.isVisibleInVersionHistory());
        proposalAttributeTypeImpl.setCopyOnPromote(proposalAttributeType.isCopyOnPromote());

        return proposalAttributeTypeImpl;
    }

    /**
     * Returns the proposal attribute type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal attribute type
     * @return the proposal attribute type
     * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttributeType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProposalAttributeTypeException, SystemException {
        ProposalAttributeType proposalAttributeType = fetchByPrimaryKey(primaryKey);

        if (proposalAttributeType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalAttributeTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposalAttributeType;
    }

    /**
     * Returns the proposal attribute type with the primary key or throws a {@link com.ext.portlet.NoSuchProposalAttributeTypeException} if it could not be found.
     *
     * @param name the primary key of the proposal attribute type
     * @return the proposal attribute type
     * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttributeType findByPrimaryKey(String name)
        throws NoSuchProposalAttributeTypeException, SystemException {
        return findByPrimaryKey((Serializable) name);
    }

    /**
     * Returns the proposal attribute type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal attribute type
     * @return the proposal attribute type, or <code>null</code> if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttributeType fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProposalAttributeType proposalAttributeType = (ProposalAttributeType) EntityCacheUtil.getResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalAttributeTypeImpl.class, primaryKey);

        if (proposalAttributeType == _nullProposalAttributeType) {
            return null;
        }

        if (proposalAttributeType == null) {
            Session session = null;

            try {
                session = openSession();

                proposalAttributeType = (ProposalAttributeType) session.get(ProposalAttributeTypeImpl.class,
                        primaryKey);

                if (proposalAttributeType != null) {
                    cacheResult(proposalAttributeType);
                } else {
                    EntityCacheUtil.putResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalAttributeTypeImpl.class, primaryKey,
                        _nullProposalAttributeType);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalAttributeTypeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposalAttributeType;
    }

    /**
     * Returns the proposal attribute type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param name the primary key of the proposal attribute type
     * @return the proposal attribute type, or <code>null</code> if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttributeType fetchByPrimaryKey(String name)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) name);
    }

    /**
     * Returns all the proposal attribute types.
     *
     * @return the proposal attribute types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalAttributeType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal attribute types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalAttributeTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal attribute types
     * @param end the upper bound of the range of proposal attribute types (not inclusive)
     * @return the range of proposal attribute types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalAttributeType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal attribute types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalAttributeTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal attribute types
     * @param end the upper bound of the range of proposal attribute types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal attribute types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalAttributeType> findAll(int start, int end,
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

        List<ProposalAttributeType> list = (List<ProposalAttributeType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALATTRIBUTETYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALATTRIBUTETYPE;

                if (pagination) {
                    sql = sql.concat(ProposalAttributeTypeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProposalAttributeType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalAttributeType>(list);
                } else {
                    list = (List<ProposalAttributeType>) QueryUtil.list(q,
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
     * Removes all the proposal attribute types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProposalAttributeType proposalAttributeType : findAll()) {
            remove(proposalAttributeType);
        }
    }

    /**
     * Returns the number of proposal attribute types.
     *
     * @return the number of proposal attribute types
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSALATTRIBUTETYPE);

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
     * Initializes the proposal attribute type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalAttributeType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalAttributeType>> listenersList = new ArrayList<ModelListener<ProposalAttributeType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalAttributeType>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalAttributeTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
