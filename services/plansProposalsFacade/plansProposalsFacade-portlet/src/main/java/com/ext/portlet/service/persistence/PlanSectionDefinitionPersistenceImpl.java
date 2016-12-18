package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanSectionDefinitionException;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.impl.PlanSectionDefinitionImpl;
import com.ext.portlet.model.impl.PlanSectionDefinitionModelImpl;
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
 * The persistence implementation for the plan section definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionDefinitionPersistence
 * @see PlanSectionDefinitionUtil
 * @generated
 */
public class PlanSectionDefinitionPersistenceImpl extends BasePersistenceImpl<PlanSectionDefinition>
    implements PlanSectionDefinitionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanSectionDefinitionUtil} to access the plan section definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanSectionDefinitionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionModelImpl.FINDER_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionModelImpl.FINDER_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANSECTIONDEFINITION = "SELECT planSectionDefinition FROM PlanSectionDefinition planSectionDefinition";
    private static final String _SQL_COUNT_PLANSECTIONDEFINITION = "SELECT COUNT(planSectionDefinition) FROM PlanSectionDefinition planSectionDefinition";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planSectionDefinition.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanSectionDefinition exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanSectionDefinitionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id", "type"
            });
    private static PlanSectionDefinition _nullPlanSectionDefinition = new PlanSectionDefinitionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanSectionDefinition> toCacheModel() {
                return _nullPlanSectionDefinitionCacheModel;
            }
        };

    private static CacheModel<PlanSectionDefinition> _nullPlanSectionDefinitionCacheModel =
        new CacheModel<PlanSectionDefinition>() {
            @Override
            public PlanSectionDefinition toEntityModel() {
                return _nullPlanSectionDefinition;
            }
        };

    public PlanSectionDefinitionPersistenceImpl() {
        setModelClass(PlanSectionDefinition.class);
    }

    /**
     * Caches the plan section definition in the entity cache if it is enabled.
     *
     * @param planSectionDefinition the plan section definition
     */
    @Override
    public void cacheResult(PlanSectionDefinition planSectionDefinition) {
        EntityCacheUtil.putResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            planSectionDefinition.getPrimaryKey(), planSectionDefinition);

        planSectionDefinition.resetOriginalValues();
    }

    /**
     * Caches the plan section definitions in the entity cache if it is enabled.
     *
     * @param planSectionDefinitions the plan section definitions
     */
    @Override
    public void cacheResult(List<PlanSectionDefinition> planSectionDefinitions) {
        for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if (EntityCacheUtil.getResult(
                        PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanSectionDefinitionImpl.class,
                        planSectionDefinition.getPrimaryKey()) == null) {
                cacheResult(planSectionDefinition);
            } else {
                planSectionDefinition.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan section definitions.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanSectionDefinitionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanSectionDefinitionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan section definition.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanSectionDefinition planSectionDefinition) {
        EntityCacheUtil.removeResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            planSectionDefinition.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlanSectionDefinition> planSectionDefinitions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            EntityCacheUtil.removeResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                PlanSectionDefinitionImpl.class,
                planSectionDefinition.getPrimaryKey());
        }
    }

    /**
     * Creates a new plan section definition with the primary key. Does not add the plan section definition to the database.
     *
     * @param id the primary key for the new plan section definition
     * @return the new plan section definition
     */
    @Override
    public PlanSectionDefinition create(long id) {
        PlanSectionDefinition planSectionDefinition = new PlanSectionDefinitionImpl();

        planSectionDefinition.setNew(true);
        planSectionDefinition.setPrimaryKey(id);

        return planSectionDefinition;
    }

    /**
     * Removes the plan section definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the plan section definition
     * @return the plan section definition that was removed
     * @throws com.ext.portlet.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanSectionDefinition remove(long id)
        throws NoSuchPlanSectionDefinitionException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the plan section definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan section definition
     * @return the plan section definition that was removed
     * @throws com.ext.portlet.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanSectionDefinition remove(Serializable primaryKey)
        throws NoSuchPlanSectionDefinitionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanSectionDefinition planSectionDefinition = (PlanSectionDefinition) session.get(PlanSectionDefinitionImpl.class,
                    primaryKey);

            if (planSectionDefinition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanSectionDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planSectionDefinition);
        } catch (NoSuchPlanSectionDefinitionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanSectionDefinition removeImpl(
        PlanSectionDefinition planSectionDefinition) throws SystemException {
        planSectionDefinition = toUnwrappedModel(planSectionDefinition);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planSectionDefinition)) {
                planSectionDefinition = (PlanSectionDefinition) session.get(PlanSectionDefinitionImpl.class,
                        planSectionDefinition.getPrimaryKeyObj());
            }

            if (planSectionDefinition != null) {
                session.delete(planSectionDefinition);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planSectionDefinition != null) {
            clearCache(planSectionDefinition);
        }

        return planSectionDefinition;
    }

    @Override
    public PlanSectionDefinition updateImpl(
        com.ext.portlet.model.PlanSectionDefinition planSectionDefinition)
        throws SystemException {
        planSectionDefinition = toUnwrappedModel(planSectionDefinition);

        boolean isNew = planSectionDefinition.isNew();

        Session session = null;

        try {
            session = openSession();

            if (planSectionDefinition.isNew()) {
                session.save(planSectionDefinition);

                planSectionDefinition.setNew(false);
            } else {
                session.merge(planSectionDefinition);
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

        EntityCacheUtil.putResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            planSectionDefinition.getPrimaryKey(), planSectionDefinition);

        return planSectionDefinition;
    }

    protected PlanSectionDefinition toUnwrappedModel(
        PlanSectionDefinition planSectionDefinition) {
        if (planSectionDefinition instanceof PlanSectionDefinitionImpl) {
            return planSectionDefinition;
        }

        PlanSectionDefinitionImpl planSectionDefinitionImpl = new PlanSectionDefinitionImpl();

        planSectionDefinitionImpl.setNew(planSectionDefinition.isNew());
        planSectionDefinitionImpl.setPrimaryKey(planSectionDefinition.getPrimaryKey());

        planSectionDefinitionImpl.setId(planSectionDefinition.getId());
        planSectionDefinitionImpl.setType(planSectionDefinition.getType());
        planSectionDefinitionImpl.setAdminTitle(planSectionDefinition.getAdminTitle());
        planSectionDefinitionImpl.setTitle(planSectionDefinition.getTitle());
        planSectionDefinitionImpl.setDefaultText(planSectionDefinition.getDefaultText());
        planSectionDefinitionImpl.setHelpText(planSectionDefinition.getHelpText());
        planSectionDefinitionImpl.setCharacterLimit(planSectionDefinition.getCharacterLimit());
        planSectionDefinitionImpl.setFocusAreaId(planSectionDefinition.getFocusAreaId());
        planSectionDefinitionImpl.setTier(planSectionDefinition.getTier());
        planSectionDefinitionImpl.setAllowedContestTypeIds(planSectionDefinition.getAllowedContestTypeIds());
        planSectionDefinitionImpl.setAllowedValues(planSectionDefinition.getAllowedValues());
        planSectionDefinitionImpl.setAdditionalIds(planSectionDefinition.getAdditionalIds());
        planSectionDefinitionImpl.setLocked(planSectionDefinition.isLocked());
        planSectionDefinitionImpl.setContestIntegrationRelevance(planSectionDefinition.isContestIntegrationRelevance());

        return planSectionDefinitionImpl;
    }

    /**
     * Returns the plan section definition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan section definition
     * @return the plan section definition
     * @throws com.ext.portlet.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanSectionDefinition findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanSectionDefinitionException, SystemException {
        PlanSectionDefinition planSectionDefinition = fetchByPrimaryKey(primaryKey);

        if (planSectionDefinition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanSectionDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planSectionDefinition;
    }

    /**
     * Returns the plan section definition with the primary key or throws a {@link com.ext.portlet.NoSuchPlanSectionDefinitionException} if it could not be found.
     *
     * @param id the primary key of the plan section definition
     * @return the plan section definition
     * @throws com.ext.portlet.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanSectionDefinition findByPrimaryKey(long id)
        throws NoSuchPlanSectionDefinitionException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the plan section definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan section definition
     * @return the plan section definition, or <code>null</code> if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanSectionDefinition fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanSectionDefinition planSectionDefinition = (PlanSectionDefinition) EntityCacheUtil.getResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                PlanSectionDefinitionImpl.class, primaryKey);

        if (planSectionDefinition == _nullPlanSectionDefinition) {
            return null;
        }

        if (planSectionDefinition == null) {
            Session session = null;

            try {
                session = openSession();

                planSectionDefinition = (PlanSectionDefinition) session.get(PlanSectionDefinitionImpl.class,
                        primaryKey);

                if (planSectionDefinition != null) {
                    cacheResult(planSectionDefinition);
                } else {
                    EntityCacheUtil.putResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanSectionDefinitionImpl.class, primaryKey,
                        _nullPlanSectionDefinition);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                    PlanSectionDefinitionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planSectionDefinition;
    }

    /**
     * Returns the plan section definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the plan section definition
     * @return the plan section definition, or <code>null</code> if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanSectionDefinition fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the plan section definitions.
     *
     * @return the plan section definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanSectionDefinition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan section definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan section definitions
     * @param end the upper bound of the range of plan section definitions (not inclusive)
     * @return the range of plan section definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanSectionDefinition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan section definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan section definitions
     * @param end the upper bound of the range of plan section definitions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan section definitions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanSectionDefinition> findAll(int start, int end,
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

        List<PlanSectionDefinition> list = (List<PlanSectionDefinition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANSECTIONDEFINITION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANSECTIONDEFINITION;

                if (pagination) {
                    sql = sql.concat(PlanSectionDefinitionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanSectionDefinition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanSectionDefinition>(list);
                } else {
                    list = (List<PlanSectionDefinition>) QueryUtil.list(q,
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
     * Removes all the plan section definitions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanSectionDefinition planSectionDefinition : findAll()) {
            remove(planSectionDefinition);
        }
    }

    /**
     * Returns the number of plan section definitions.
     *
     * @return the number of plan section definitions
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

                Query q = session.createQuery(_SQL_COUNT_PLANSECTIONDEFINITION);

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
     * Initializes the plan section definition persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanSectionDefinition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanSectionDefinition>> listenersList = new ArrayList<ModelListener<PlanSectionDefinition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanSectionDefinition>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanSectionDefinitionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
