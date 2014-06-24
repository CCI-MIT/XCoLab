package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestEmailTemplateException;
import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.model.impl.ContestEmailTemplateImpl;
import com.ext.portlet.model.impl.ContestEmailTemplateModelImpl;
import com.ext.portlet.service.persistence.ContestEmailTemplatePersistence;

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
 * The persistence implementation for the contest email template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestEmailTemplatePersistence
 * @see ContestEmailTemplateUtil
 * @generated
 */
public class ContestEmailTemplatePersistenceImpl extends BasePersistenceImpl<ContestEmailTemplate>
    implements ContestEmailTemplatePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestEmailTemplateUtil} to access the contest email template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestEmailTemplateImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
            ContestEmailTemplateModelImpl.FINDER_CACHE_ENABLED,
            ContestEmailTemplateImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
            ContestEmailTemplateModelImpl.FINDER_CACHE_ENABLED,
            ContestEmailTemplateImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
            ContestEmailTemplateModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTESTEMAILTEMPLATE = "SELECT contestEmailTemplate FROM ContestEmailTemplate contestEmailTemplate";
    private static final String _SQL_COUNT_CONTESTEMAILTEMPLATE = "SELECT COUNT(contestEmailTemplate) FROM ContestEmailTemplate contestEmailTemplate";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestEmailTemplate.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestEmailTemplate exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestEmailTemplatePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "type"
            });
    private static ContestEmailTemplate _nullContestEmailTemplate = new ContestEmailTemplateImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestEmailTemplate> toCacheModel() {
                return _nullContestEmailTemplateCacheModel;
            }
        };

    private static CacheModel<ContestEmailTemplate> _nullContestEmailTemplateCacheModel =
        new CacheModel<ContestEmailTemplate>() {
            @Override
            public ContestEmailTemplate toEntityModel() {
                return _nullContestEmailTemplate;
            }
        };

    public ContestEmailTemplatePersistenceImpl() {
        setModelClass(ContestEmailTemplate.class);
    }

    /**
     * Caches the contest email template in the entity cache if it is enabled.
     *
     * @param contestEmailTemplate the contest email template
     */
    @Override
    public void cacheResult(ContestEmailTemplate contestEmailTemplate) {
        EntityCacheUtil.putResult(ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
            ContestEmailTemplateImpl.class,
            contestEmailTemplate.getPrimaryKey(), contestEmailTemplate);

        contestEmailTemplate.resetOriginalValues();
    }

    /**
     * Caches the contest email templates in the entity cache if it is enabled.
     *
     * @param contestEmailTemplates the contest email templates
     */
    @Override
    public void cacheResult(List<ContestEmailTemplate> contestEmailTemplates) {
        for (ContestEmailTemplate contestEmailTemplate : contestEmailTemplates) {
            if (EntityCacheUtil.getResult(
                        ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
                        ContestEmailTemplateImpl.class,
                        contestEmailTemplate.getPrimaryKey()) == null) {
                cacheResult(contestEmailTemplate);
            } else {
                contestEmailTemplate.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest email templates.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestEmailTemplateImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestEmailTemplateImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest email template.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestEmailTemplate contestEmailTemplate) {
        EntityCacheUtil.removeResult(ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
            ContestEmailTemplateImpl.class, contestEmailTemplate.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContestEmailTemplate> contestEmailTemplates) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestEmailTemplate contestEmailTemplate : contestEmailTemplates) {
            EntityCacheUtil.removeResult(ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
                ContestEmailTemplateImpl.class,
                contestEmailTemplate.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest email template with the primary key. Does not add the contest email template to the database.
     *
     * @param type the primary key for the new contest email template
     * @return the new contest email template
     */
    @Override
    public ContestEmailTemplate create(String type) {
        ContestEmailTemplate contestEmailTemplate = new ContestEmailTemplateImpl();

        contestEmailTemplate.setNew(true);
        contestEmailTemplate.setPrimaryKey(type);

        return contestEmailTemplate;
    }

    /**
     * Removes the contest email template with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param type the primary key of the contest email template
     * @return the contest email template that was removed
     * @throws com.ext.portlet.NoSuchContestEmailTemplateException if a contest email template with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestEmailTemplate remove(String type)
        throws NoSuchContestEmailTemplateException, SystemException {
        return remove((Serializable) type);
    }

    /**
     * Removes the contest email template with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest email template
     * @return the contest email template that was removed
     * @throws com.ext.portlet.NoSuchContestEmailTemplateException if a contest email template with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestEmailTemplate remove(Serializable primaryKey)
        throws NoSuchContestEmailTemplateException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestEmailTemplate contestEmailTemplate = (ContestEmailTemplate) session.get(ContestEmailTemplateImpl.class,
                    primaryKey);

            if (contestEmailTemplate == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestEmailTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestEmailTemplate);
        } catch (NoSuchContestEmailTemplateException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestEmailTemplate removeImpl(
        ContestEmailTemplate contestEmailTemplate) throws SystemException {
        contestEmailTemplate = toUnwrappedModel(contestEmailTemplate);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contestEmailTemplate)) {
                contestEmailTemplate = (ContestEmailTemplate) session.get(ContestEmailTemplateImpl.class,
                        contestEmailTemplate.getPrimaryKeyObj());
            }

            if (contestEmailTemplate != null) {
                session.delete(contestEmailTemplate);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contestEmailTemplate != null) {
            clearCache(contestEmailTemplate);
        }

        return contestEmailTemplate;
    }

    @Override
    public ContestEmailTemplate updateImpl(
        com.ext.portlet.model.ContestEmailTemplate contestEmailTemplate)
        throws SystemException {
        contestEmailTemplate = toUnwrappedModel(contestEmailTemplate);

        boolean isNew = contestEmailTemplate.isNew();

        Session session = null;

        try {
            session = openSession();

            if (contestEmailTemplate.isNew()) {
                session.save(contestEmailTemplate);

                contestEmailTemplate.setNew(false);
            } else {
                session.merge(contestEmailTemplate);
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

        EntityCacheUtil.putResult(ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
            ContestEmailTemplateImpl.class,
            contestEmailTemplate.getPrimaryKey(), contestEmailTemplate);

        return contestEmailTemplate;
    }

    protected ContestEmailTemplate toUnwrappedModel(
        ContestEmailTemplate contestEmailTemplate) {
        if (contestEmailTemplate instanceof ContestEmailTemplateImpl) {
            return contestEmailTemplate;
        }

        ContestEmailTemplateImpl contestEmailTemplateImpl = new ContestEmailTemplateImpl();

        contestEmailTemplateImpl.setNew(contestEmailTemplate.isNew());
        contestEmailTemplateImpl.setPrimaryKey(contestEmailTemplate.getPrimaryKey());

        contestEmailTemplateImpl.setType(contestEmailTemplate.getType());
        contestEmailTemplateImpl.setHeader(contestEmailTemplate.getHeader());
        contestEmailTemplateImpl.setFooter(contestEmailTemplate.getFooter());

        return contestEmailTemplateImpl;
    }

    /**
     * Returns the contest email template with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest email template
     * @return the contest email template
     * @throws com.ext.portlet.NoSuchContestEmailTemplateException if a contest email template with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestEmailTemplate findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContestEmailTemplateException, SystemException {
        ContestEmailTemplate contestEmailTemplate = fetchByPrimaryKey(primaryKey);

        if (contestEmailTemplate == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContestEmailTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contestEmailTemplate;
    }

    /**
     * Returns the contest email template with the primary key or throws a {@link com.ext.portlet.NoSuchContestEmailTemplateException} if it could not be found.
     *
     * @param type the primary key of the contest email template
     * @return the contest email template
     * @throws com.ext.portlet.NoSuchContestEmailTemplateException if a contest email template with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestEmailTemplate findByPrimaryKey(String type)
        throws NoSuchContestEmailTemplateException, SystemException {
        return findByPrimaryKey((Serializable) type);
    }

    /**
     * Returns the contest email template with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest email template
     * @return the contest email template, or <code>null</code> if a contest email template with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestEmailTemplate fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContestEmailTemplate contestEmailTemplate = (ContestEmailTemplate) EntityCacheUtil.getResult(ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
                ContestEmailTemplateImpl.class, primaryKey);

        if (contestEmailTemplate == _nullContestEmailTemplate) {
            return null;
        }

        if (contestEmailTemplate == null) {
            Session session = null;

            try {
                session = openSession();

                contestEmailTemplate = (ContestEmailTemplate) session.get(ContestEmailTemplateImpl.class,
                        primaryKey);

                if (contestEmailTemplate != null) {
                    cacheResult(contestEmailTemplate);
                } else {
                    EntityCacheUtil.putResult(ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
                        ContestEmailTemplateImpl.class, primaryKey,
                        _nullContestEmailTemplate);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContestEmailTemplateModelImpl.ENTITY_CACHE_ENABLED,
                    ContestEmailTemplateImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contestEmailTemplate;
    }

    /**
     * Returns the contest email template with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param type the primary key of the contest email template
     * @return the contest email template, or <code>null</code> if a contest email template with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestEmailTemplate fetchByPrimaryKey(String type)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) type);
    }

    /**
     * Returns all the contest email templates.
     *
     * @return the contest email templates
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestEmailTemplate> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest email templates.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestEmailTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest email templates
     * @param end the upper bound of the range of contest email templates (not inclusive)
     * @return the range of contest email templates
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestEmailTemplate> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest email templates.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestEmailTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest email templates
     * @param end the upper bound of the range of contest email templates (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest email templates
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestEmailTemplate> findAll(int start, int end,
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

        List<ContestEmailTemplate> list = (List<ContestEmailTemplate>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTEMAILTEMPLATE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTEMAILTEMPLATE;

                if (pagination) {
                    sql = sql.concat(ContestEmailTemplateModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContestEmailTemplate>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestEmailTemplate>(list);
                } else {
                    list = (List<ContestEmailTemplate>) QueryUtil.list(q,
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
     * Removes all the contest email templates from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContestEmailTemplate contestEmailTemplate : findAll()) {
            remove(contestEmailTemplate);
        }
    }

    /**
     * Returns the number of contest email templates.
     *
     * @return the number of contest email templates
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

                Query q = session.createQuery(_SQL_COUNT_CONTESTEMAILTEMPLATE);

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
     * Initializes the contest email template persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestEmailTemplate")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestEmailTemplate>> listenersList = new ArrayList<ModelListener<ContestEmailTemplate>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestEmailTemplate>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestEmailTemplateImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
