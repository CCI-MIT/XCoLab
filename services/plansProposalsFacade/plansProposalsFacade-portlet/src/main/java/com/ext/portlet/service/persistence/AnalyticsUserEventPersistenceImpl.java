package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchAnalyticsUserEventException;
import com.ext.portlet.model.AnalyticsUserEvent;
import com.ext.portlet.model.impl.AnalyticsUserEventImpl;
import com.ext.portlet.model.impl.AnalyticsUserEventModelImpl;
import com.ext.portlet.service.persistence.AnalyticsUserEventPersistence;

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
 * The persistence implementation for the analytics user event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsUserEventPersistence
 * @see AnalyticsUserEventUtil
 * @generated
 */
public class AnalyticsUserEventPersistenceImpl extends BasePersistenceImpl<AnalyticsUserEvent>
    implements AnalyticsUserEventPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AnalyticsUserEventUtil} to access the analytics user event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AnalyticsUserEventImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventModelImpl.FINDER_CACHE_ENABLED,
            AnalyticsUserEventImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventModelImpl.FINDER_CACHE_ENABLED,
            AnalyticsUserEventImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ANALYTICSUSEREVENT = "SELECT analyticsUserEvent FROM AnalyticsUserEvent analyticsUserEvent";
    private static final String _SQL_COUNT_ANALYTICSUSEREVENT = "SELECT COUNT(analyticsUserEvent) FROM AnalyticsUserEvent analyticsUserEvent";
    private static final String _ORDER_BY_ENTITY_ALIAS = "analyticsUserEvent.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AnalyticsUserEvent exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AnalyticsUserEventPersistenceImpl.class);
    private static AnalyticsUserEvent _nullAnalyticsUserEvent = new AnalyticsUserEventImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AnalyticsUserEvent> toCacheModel() {
                return _nullAnalyticsUserEventCacheModel;
            }
        };

    private static CacheModel<AnalyticsUserEvent> _nullAnalyticsUserEventCacheModel =
        new CacheModel<AnalyticsUserEvent>() {
            @Override
            public AnalyticsUserEvent toEntityModel() {
                return _nullAnalyticsUserEvent;
            }
        };

    public AnalyticsUserEventPersistenceImpl() {
        setModelClass(AnalyticsUserEvent.class);
    }

    /**
     * Caches the analytics user event in the entity cache if it is enabled.
     *
     * @param analyticsUserEvent the analytics user event
     */
    @Override
    public void cacheResult(AnalyticsUserEvent analyticsUserEvent) {
        EntityCacheUtil.putResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventImpl.class, analyticsUserEvent.getPrimaryKey(),
            analyticsUserEvent);

        analyticsUserEvent.resetOriginalValues();
    }

    /**
     * Caches the analytics user events in the entity cache if it is enabled.
     *
     * @param analyticsUserEvents the analytics user events
     */
    @Override
    public void cacheResult(List<AnalyticsUserEvent> analyticsUserEvents) {
        for (AnalyticsUserEvent analyticsUserEvent : analyticsUserEvents) {
            if (EntityCacheUtil.getResult(
                        AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
                        AnalyticsUserEventImpl.class,
                        analyticsUserEvent.getPrimaryKey()) == null) {
                cacheResult(analyticsUserEvent);
            } else {
                analyticsUserEvent.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all analytics user events.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AnalyticsUserEventImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AnalyticsUserEventImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the analytics user event.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AnalyticsUserEvent analyticsUserEvent) {
        EntityCacheUtil.removeResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventImpl.class, analyticsUserEvent.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AnalyticsUserEvent> analyticsUserEvents) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AnalyticsUserEvent analyticsUserEvent : analyticsUserEvents) {
            EntityCacheUtil.removeResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
                AnalyticsUserEventImpl.class, analyticsUserEvent.getPrimaryKey());
        }
    }

    /**
     * Creates a new analytics user event with the primary key. Does not add the analytics user event to the database.
     *
     * @param analyticsUserEventPK the primary key for the new analytics user event
     * @return the new analytics user event
     */
    @Override
    public AnalyticsUserEvent create(AnalyticsUserEventPK analyticsUserEventPK) {
        AnalyticsUserEvent analyticsUserEvent = new AnalyticsUserEventImpl();

        analyticsUserEvent.setNew(true);
        analyticsUserEvent.setPrimaryKey(analyticsUserEventPK);

        return analyticsUserEvent;
    }

    /**
     * Removes the analytics user event with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param analyticsUserEventPK the primary key of the analytics user event
     * @return the analytics user event that was removed
     * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AnalyticsUserEvent remove(AnalyticsUserEventPK analyticsUserEventPK)
        throws NoSuchAnalyticsUserEventException, SystemException {
        return remove((Serializable) analyticsUserEventPK);
    }

    /**
     * Removes the analytics user event with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the analytics user event
     * @return the analytics user event that was removed
     * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AnalyticsUserEvent remove(Serializable primaryKey)
        throws NoSuchAnalyticsUserEventException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AnalyticsUserEvent analyticsUserEvent = (AnalyticsUserEvent) session.get(AnalyticsUserEventImpl.class,
                    primaryKey);

            if (analyticsUserEvent == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAnalyticsUserEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(analyticsUserEvent);
        } catch (NoSuchAnalyticsUserEventException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AnalyticsUserEvent removeImpl(
        AnalyticsUserEvent analyticsUserEvent) throws SystemException {
        analyticsUserEvent = toUnwrappedModel(analyticsUserEvent);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(analyticsUserEvent)) {
                analyticsUserEvent = (AnalyticsUserEvent) session.get(AnalyticsUserEventImpl.class,
                        analyticsUserEvent.getPrimaryKeyObj());
            }

            if (analyticsUserEvent != null) {
                session.delete(analyticsUserEvent);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (analyticsUserEvent != null) {
            clearCache(analyticsUserEvent);
        }

        return analyticsUserEvent;
    }

    @Override
    public AnalyticsUserEvent updateImpl(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent)
        throws SystemException {
        analyticsUserEvent = toUnwrappedModel(analyticsUserEvent);

        boolean isNew = analyticsUserEvent.isNew();

        Session session = null;

        try {
            session = openSession();

            if (analyticsUserEvent.isNew()) {
                session.save(analyticsUserEvent);

                analyticsUserEvent.setNew(false);
            } else {
                session.merge(analyticsUserEvent);
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

        EntityCacheUtil.putResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventImpl.class, analyticsUserEvent.getPrimaryKey(),
            analyticsUserEvent);

        return analyticsUserEvent;
    }

    protected AnalyticsUserEvent toUnwrappedModel(
        AnalyticsUserEvent analyticsUserEvent) {
        if (analyticsUserEvent instanceof AnalyticsUserEventImpl) {
            return analyticsUserEvent;
        }

        AnalyticsUserEventImpl analyticsUserEventImpl = new AnalyticsUserEventImpl();

        analyticsUserEventImpl.setNew(analyticsUserEvent.isNew());
        analyticsUserEventImpl.setPrimaryKey(analyticsUserEvent.getPrimaryKey());

        analyticsUserEventImpl.setUserId(analyticsUserEvent.getUserId());
        analyticsUserEventImpl.setIdString(analyticsUserEvent.getIdString());
        analyticsUserEventImpl.setCategory(analyticsUserEvent.getCategory());
        analyticsUserEventImpl.setAction(analyticsUserEvent.getAction());
        analyticsUserEventImpl.setLabel(analyticsUserEvent.getLabel());
        analyticsUserEventImpl.setValue(analyticsUserEvent.getValue());
        analyticsUserEventImpl.setCreated(analyticsUserEvent.getCreated());

        return analyticsUserEventImpl;
    }

    /**
     * Returns the analytics user event with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the analytics user event
     * @return the analytics user event
     * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AnalyticsUserEvent findByPrimaryKey(Serializable primaryKey)
        throws NoSuchAnalyticsUserEventException, SystemException {
        AnalyticsUserEvent analyticsUserEvent = fetchByPrimaryKey(primaryKey);

        if (analyticsUserEvent == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchAnalyticsUserEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return analyticsUserEvent;
    }

    /**
     * Returns the analytics user event with the primary key or throws a {@link com.ext.portlet.NoSuchAnalyticsUserEventException} if it could not be found.
     *
     * @param analyticsUserEventPK the primary key of the analytics user event
     * @return the analytics user event
     * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AnalyticsUserEvent findByPrimaryKey(
        AnalyticsUserEventPK analyticsUserEventPK)
        throws NoSuchAnalyticsUserEventException, SystemException {
        return findByPrimaryKey((Serializable) analyticsUserEventPK);
    }

    /**
     * Returns the analytics user event with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the analytics user event
     * @return the analytics user event, or <code>null</code> if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AnalyticsUserEvent fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        AnalyticsUserEvent analyticsUserEvent = (AnalyticsUserEvent) EntityCacheUtil.getResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
                AnalyticsUserEventImpl.class, primaryKey);

        if (analyticsUserEvent == _nullAnalyticsUserEvent) {
            return null;
        }

        if (analyticsUserEvent == null) {
            Session session = null;

            try {
                session = openSession();

                analyticsUserEvent = (AnalyticsUserEvent) session.get(AnalyticsUserEventImpl.class,
                        primaryKey);

                if (analyticsUserEvent != null) {
                    cacheResult(analyticsUserEvent);
                } else {
                    EntityCacheUtil.putResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
                        AnalyticsUserEventImpl.class, primaryKey,
                        _nullAnalyticsUserEvent);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
                    AnalyticsUserEventImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return analyticsUserEvent;
    }

    /**
     * Returns the analytics user event with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param analyticsUserEventPK the primary key of the analytics user event
     * @return the analytics user event, or <code>null</code> if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AnalyticsUserEvent fetchByPrimaryKey(
        AnalyticsUserEventPK analyticsUserEventPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) analyticsUserEventPK);
    }

    /**
     * Returns all the analytics user events.
     *
     * @return the analytics user events
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AnalyticsUserEvent> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the analytics user events.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.AnalyticsUserEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of analytics user events
     * @param end the upper bound of the range of analytics user events (not inclusive)
     * @return the range of analytics user events
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AnalyticsUserEvent> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the analytics user events.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.AnalyticsUserEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of analytics user events
     * @param end the upper bound of the range of analytics user events (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of analytics user events
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<AnalyticsUserEvent> findAll(int start, int end,
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

        List<AnalyticsUserEvent> list = (List<AnalyticsUserEvent>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ANALYTICSUSEREVENT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ANALYTICSUSEREVENT;

                if (pagination) {
                    sql = sql.concat(AnalyticsUserEventModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<AnalyticsUserEvent>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<AnalyticsUserEvent>(list);
                } else {
                    list = (List<AnalyticsUserEvent>) QueryUtil.list(q,
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
     * Removes all the analytics user events from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (AnalyticsUserEvent analyticsUserEvent : findAll()) {
            remove(analyticsUserEvent);
        }
    }

    /**
     * Returns the number of analytics user events.
     *
     * @return the number of analytics user events
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

                Query q = session.createQuery(_SQL_COUNT_ANALYTICSUSEREVENT);

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
     * Initializes the analytics user event persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.AnalyticsUserEvent")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AnalyticsUserEvent>> listenersList = new ArrayList<ModelListener<AnalyticsUserEvent>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AnalyticsUserEvent>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AnalyticsUserEventImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
