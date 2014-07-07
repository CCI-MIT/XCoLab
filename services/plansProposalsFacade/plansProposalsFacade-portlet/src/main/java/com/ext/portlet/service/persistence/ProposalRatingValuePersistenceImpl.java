package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalRatingValueException;
import com.ext.portlet.model.ProposalRatingValue;
import com.ext.portlet.model.impl.ProposalRatingValueImpl;
import com.ext.portlet.model.impl.ProposalRatingValueModelImpl;
import com.ext.portlet.service.persistence.ProposalRatingValuePersistence;

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
 * The persistence implementation for the proposal rating value service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingValuePersistence
 * @see ProposalRatingValueUtil
 * @generated
 */
public class ProposalRatingValuePersistenceImpl extends BasePersistenceImpl<ProposalRatingValue>
    implements ProposalRatingValuePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalRatingValueUtil} to access the proposal rating value persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalRatingValueImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingValueModelImpl.FINDER_CACHE_ENABLED,
            ProposalRatingValueImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingValueModelImpl.FINDER_CACHE_ENABLED,
            ProposalRatingValueImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingValueModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROPOSALRATINGVALUE = "SELECT proposalRatingValue FROM ProposalRatingValue proposalRatingValue";
    private static final String _SQL_COUNT_PROPOSALRATINGVALUE = "SELECT COUNT(proposalRatingValue) FROM ProposalRatingValue proposalRatingValue";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalRatingValue.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalRatingValue exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalRatingValuePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ProposalRatingValue _nullProposalRatingValue = new ProposalRatingValueImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalRatingValue> toCacheModel() {
                return _nullProposalRatingValueCacheModel;
            }
        };

    private static CacheModel<ProposalRatingValue> _nullProposalRatingValueCacheModel =
        new CacheModel<ProposalRatingValue>() {
            @Override
            public ProposalRatingValue toEntityModel() {
                return _nullProposalRatingValue;
            }
        };

    public ProposalRatingValuePersistenceImpl() {
        setModelClass(ProposalRatingValue.class);
    }

    /**
     * Caches the proposal rating value in the entity cache if it is enabled.
     *
     * @param proposalRatingValue the proposal rating value
     */
    @Override
    public void cacheResult(ProposalRatingValue proposalRatingValue) {
        EntityCacheUtil.putResult(ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingValueImpl.class, proposalRatingValue.getPrimaryKey(),
            proposalRatingValue);

        proposalRatingValue.resetOriginalValues();
    }

    /**
     * Caches the proposal rating values in the entity cache if it is enabled.
     *
     * @param proposalRatingValues the proposal rating values
     */
    @Override
    public void cacheResult(List<ProposalRatingValue> proposalRatingValues) {
        for (ProposalRatingValue proposalRatingValue : proposalRatingValues) {
            if (EntityCacheUtil.getResult(
                        ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalRatingValueImpl.class,
                        proposalRatingValue.getPrimaryKey()) == null) {
                cacheResult(proposalRatingValue);
            } else {
                proposalRatingValue.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal rating values.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalRatingValueImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalRatingValueImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal rating value.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProposalRatingValue proposalRatingValue) {
        EntityCacheUtil.removeResult(ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingValueImpl.class, proposalRatingValue.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProposalRatingValue> proposalRatingValues) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalRatingValue proposalRatingValue : proposalRatingValues) {
            EntityCacheUtil.removeResult(ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
                ProposalRatingValueImpl.class,
                proposalRatingValue.getPrimaryKey());
        }
    }

    /**
     * Creates a new proposal rating value with the primary key. Does not add the proposal rating value to the database.
     *
     * @param id the primary key for the new proposal rating value
     * @return the new proposal rating value
     */
    @Override
    public ProposalRatingValue create(long id) {
        ProposalRatingValue proposalRatingValue = new ProposalRatingValueImpl();

        proposalRatingValue.setNew(true);
        proposalRatingValue.setPrimaryKey(id);

        return proposalRatingValue;
    }

    /**
     * Removes the proposal rating value with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the proposal rating value
     * @return the proposal rating value that was removed
     * @throws com.ext.portlet.NoSuchProposalRatingValueException if a proposal rating value with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingValue remove(long id)
        throws NoSuchProposalRatingValueException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the proposal rating value with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal rating value
     * @return the proposal rating value that was removed
     * @throws com.ext.portlet.NoSuchProposalRatingValueException if a proposal rating value with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingValue remove(Serializable primaryKey)
        throws NoSuchProposalRatingValueException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalRatingValue proposalRatingValue = (ProposalRatingValue) session.get(ProposalRatingValueImpl.class,
                    primaryKey);

            if (proposalRatingValue == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalRatingValueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalRatingValue);
        } catch (NoSuchProposalRatingValueException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalRatingValue removeImpl(
        ProposalRatingValue proposalRatingValue) throws SystemException {
        proposalRatingValue = toUnwrappedModel(proposalRatingValue);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(proposalRatingValue)) {
                proposalRatingValue = (ProposalRatingValue) session.get(ProposalRatingValueImpl.class,
                        proposalRatingValue.getPrimaryKeyObj());
            }

            if (proposalRatingValue != null) {
                session.delete(proposalRatingValue);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (proposalRatingValue != null) {
            clearCache(proposalRatingValue);
        }

        return proposalRatingValue;
    }

    @Override
    public ProposalRatingValue updateImpl(
        com.ext.portlet.model.ProposalRatingValue proposalRatingValue)
        throws SystemException {
        proposalRatingValue = toUnwrappedModel(proposalRatingValue);

        boolean isNew = proposalRatingValue.isNew();

        Session session = null;

        try {
            session = openSession();

            if (proposalRatingValue.isNew()) {
                session.save(proposalRatingValue);

                proposalRatingValue.setNew(false);
            } else {
                session.merge(proposalRatingValue);
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

        EntityCacheUtil.putResult(ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
            ProposalRatingValueImpl.class, proposalRatingValue.getPrimaryKey(),
            proposalRatingValue);

        return proposalRatingValue;
    }

    protected ProposalRatingValue toUnwrappedModel(
        ProposalRatingValue proposalRatingValue) {
        if (proposalRatingValue instanceof ProposalRatingValueImpl) {
            return proposalRatingValue;
        }

        ProposalRatingValueImpl proposalRatingValueImpl = new ProposalRatingValueImpl();

        proposalRatingValueImpl.setNew(proposalRatingValue.isNew());
        proposalRatingValueImpl.setPrimaryKey(proposalRatingValue.getPrimaryKey());

        proposalRatingValueImpl.setId(proposalRatingValue.getId());
        proposalRatingValueImpl.setRatingTypeId(proposalRatingValue.getRatingTypeId());
        proposalRatingValueImpl.setValue(proposalRatingValue.getValue());
        proposalRatingValueImpl.setName(proposalRatingValue.getName());
        proposalRatingValueImpl.setDescription(proposalRatingValue.getDescription());

        return proposalRatingValueImpl;
    }

    /**
     * Returns the proposal rating value with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal rating value
     * @return the proposal rating value
     * @throws com.ext.portlet.NoSuchProposalRatingValueException if a proposal rating value with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingValue findByPrimaryKey(Serializable primaryKey)
        throws NoSuchProposalRatingValueException, SystemException {
        ProposalRatingValue proposalRatingValue = fetchByPrimaryKey(primaryKey);

        if (proposalRatingValue == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchProposalRatingValueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return proposalRatingValue;
    }

    /**
     * Returns the proposal rating value with the primary key or throws a {@link com.ext.portlet.NoSuchProposalRatingValueException} if it could not be found.
     *
     * @param id the primary key of the proposal rating value
     * @return the proposal rating value
     * @throws com.ext.portlet.NoSuchProposalRatingValueException if a proposal rating value with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingValue findByPrimaryKey(long id)
        throws NoSuchProposalRatingValueException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the proposal rating value with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal rating value
     * @return the proposal rating value, or <code>null</code> if a proposal rating value with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingValue fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ProposalRatingValue proposalRatingValue = (ProposalRatingValue) EntityCacheUtil.getResult(ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
                ProposalRatingValueImpl.class, primaryKey);

        if (proposalRatingValue == _nullProposalRatingValue) {
            return null;
        }

        if (proposalRatingValue == null) {
            Session session = null;

            try {
                session = openSession();

                proposalRatingValue = (ProposalRatingValue) session.get(ProposalRatingValueImpl.class,
                        primaryKey);

                if (proposalRatingValue != null) {
                    cacheResult(proposalRatingValue);
                } else {
                    EntityCacheUtil.putResult(ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalRatingValueImpl.class, primaryKey,
                        _nullProposalRatingValue);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ProposalRatingValueModelImpl.ENTITY_CACHE_ENABLED,
                    ProposalRatingValueImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return proposalRatingValue;
    }

    /**
     * Returns the proposal rating value with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the proposal rating value
     * @return the proposal rating value, or <code>null</code> if a proposal rating value with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalRatingValue fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the proposal rating values.
     *
     * @return the proposal rating values
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalRatingValue> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal rating values.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal rating values
     * @param end the upper bound of the range of proposal rating values (not inclusive)
     * @return the range of proposal rating values
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalRatingValue> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal rating values.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of proposal rating values
     * @param end the upper bound of the range of proposal rating values (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal rating values
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ProposalRatingValue> findAll(int start, int end,
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

        List<ProposalRatingValue> list = (List<ProposalRatingValue>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALRATINGVALUE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALRATINGVALUE;

                if (pagination) {
                    sql = sql.concat(ProposalRatingValueModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ProposalRatingValue>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ProposalRatingValue>(list);
                } else {
                    list = (List<ProposalRatingValue>) QueryUtil.list(q,
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
     * Removes all the proposal rating values from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ProposalRatingValue proposalRatingValue : findAll()) {
            remove(proposalRatingValue);
        }
    }

    /**
     * Returns the number of proposal rating values.
     *
     * @return the number of proposal rating values
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

                Query q = session.createQuery(_SQL_COUNT_PROPOSALRATINGVALUE);

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
     * Initializes the proposal rating value persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalRatingValue")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalRatingValue>> listenersList = new ArrayList<ModelListener<ProposalRatingValue>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalRatingValue>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalRatingValueImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
