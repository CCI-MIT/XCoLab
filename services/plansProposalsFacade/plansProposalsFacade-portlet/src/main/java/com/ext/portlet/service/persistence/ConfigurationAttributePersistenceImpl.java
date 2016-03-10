package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchConfigurationAttributeException;
import com.ext.portlet.model.ConfigurationAttribute;
import com.ext.portlet.model.impl.ConfigurationAttributeImpl;
import com.ext.portlet.model.impl.ConfigurationAttributeModelImpl;
import com.ext.portlet.service.persistence.ConfigurationAttributePersistence;

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
 * The persistence implementation for the configuration attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConfigurationAttributePersistence
 * @see ConfigurationAttributeUtil
 * @generated
 */
public class ConfigurationAttributePersistenceImpl extends BasePersistenceImpl<ConfigurationAttribute>
    implements ConfigurationAttributePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ConfigurationAttributeUtil} to access the configuration attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ConfigurationAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ConfigurationAttributeModelImpl.FINDER_CACHE_ENABLED,
            ConfigurationAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ConfigurationAttributeModelImpl.FINDER_CACHE_ENABLED,
            ConfigurationAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ConfigurationAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONFIGURATIONATTRIBUTE = "SELECT configurationAttribute FROM ConfigurationAttribute configurationAttribute";
    private static final String _SQL_COUNT_CONFIGURATIONATTRIBUTE = "SELECT COUNT(configurationAttribute) FROM ConfigurationAttribute configurationAttribute";
    private static final String _ORDER_BY_ENTITY_ALIAS = "configurationAttribute.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ConfigurationAttribute exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ConfigurationAttributePersistenceImpl.class);
    private static ConfigurationAttribute _nullConfigurationAttribute = new ConfigurationAttributeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ConfigurationAttribute> toCacheModel() {
                return _nullConfigurationAttributeCacheModel;
            }
        };

    private static CacheModel<ConfigurationAttribute> _nullConfigurationAttributeCacheModel =
        new CacheModel<ConfigurationAttribute>() {
            @Override
            public ConfigurationAttribute toEntityModel() {
                return _nullConfigurationAttribute;
            }
        };

    public ConfigurationAttributePersistenceImpl() {
        setModelClass(ConfigurationAttribute.class);
    }

    /**
     * Caches the configuration attribute in the entity cache if it is enabled.
     *
     * @param configurationAttribute the configuration attribute
     */
    @Override
    public void cacheResult(ConfigurationAttribute configurationAttribute) {
        EntityCacheUtil.putResult(ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ConfigurationAttributeImpl.class,
            configurationAttribute.getPrimaryKey(), configurationAttribute);

        configurationAttribute.resetOriginalValues();
    }

    /**
     * Caches the configuration attributes in the entity cache if it is enabled.
     *
     * @param configurationAttributes the configuration attributes
     */
    @Override
    public void cacheResult(
        List<ConfigurationAttribute> configurationAttributes) {
        for (ConfigurationAttribute configurationAttribute : configurationAttributes) {
            if (EntityCacheUtil.getResult(
                        ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        ConfigurationAttributeImpl.class,
                        configurationAttribute.getPrimaryKey()) == null) {
                cacheResult(configurationAttribute);
            } else {
                configurationAttribute.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all configuration attributes.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ConfigurationAttributeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ConfigurationAttributeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the configuration attribute.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ConfigurationAttribute configurationAttribute) {
        EntityCacheUtil.removeResult(ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ConfigurationAttributeImpl.class,
            configurationAttribute.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ConfigurationAttribute> configurationAttributes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ConfigurationAttribute configurationAttribute : configurationAttributes) {
            EntityCacheUtil.removeResult(ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ConfigurationAttributeImpl.class,
                configurationAttribute.getPrimaryKey());
        }
    }

    /**
     * Creates a new configuration attribute with the primary key. Does not add the configuration attribute to the database.
     *
     * @param configurationAttributePK the primary key for the new configuration attribute
     * @return the new configuration attribute
     */
    @Override
    public ConfigurationAttribute create(
        ConfigurationAttributePK configurationAttributePK) {
        ConfigurationAttribute configurationAttribute = new ConfigurationAttributeImpl();

        configurationAttribute.setNew(true);
        configurationAttribute.setPrimaryKey(configurationAttributePK);

        return configurationAttribute;
    }

    /**
     * Removes the configuration attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param configurationAttributePK the primary key of the configuration attribute
     * @return the configuration attribute that was removed
     * @throws com.ext.portlet.NoSuchConfigurationAttributeException if a configuration attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ConfigurationAttribute remove(
        ConfigurationAttributePK configurationAttributePK)
        throws NoSuchConfigurationAttributeException, SystemException {
        return remove((Serializable) configurationAttributePK);
    }

    /**
     * Removes the configuration attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the configuration attribute
     * @return the configuration attribute that was removed
     * @throws com.ext.portlet.NoSuchConfigurationAttributeException if a configuration attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ConfigurationAttribute remove(Serializable primaryKey)
        throws NoSuchConfigurationAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ConfigurationAttribute configurationAttribute = (ConfigurationAttribute) session.get(ConfigurationAttributeImpl.class,
                    primaryKey);

            if (configurationAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchConfigurationAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(configurationAttribute);
        } catch (NoSuchConfigurationAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ConfigurationAttribute removeImpl(
        ConfigurationAttribute configurationAttribute)
        throws SystemException {
        configurationAttribute = toUnwrappedModel(configurationAttribute);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(configurationAttribute)) {
                configurationAttribute = (ConfigurationAttribute) session.get(ConfigurationAttributeImpl.class,
                        configurationAttribute.getPrimaryKeyObj());
            }

            if (configurationAttribute != null) {
                session.delete(configurationAttribute);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (configurationAttribute != null) {
            clearCache(configurationAttribute);
        }

        return configurationAttribute;
    }

    @Override
    public ConfigurationAttribute updateImpl(
        com.ext.portlet.model.ConfigurationAttribute configurationAttribute)
        throws SystemException {
        configurationAttribute = toUnwrappedModel(configurationAttribute);

        boolean isNew = configurationAttribute.isNew();

        Session session = null;

        try {
            session = openSession();

            if (configurationAttribute.isNew()) {
                session.save(configurationAttribute);

                configurationAttribute.setNew(false);
            } else {
                session.merge(configurationAttribute);
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

        EntityCacheUtil.putResult(ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ConfigurationAttributeImpl.class,
            configurationAttribute.getPrimaryKey(), configurationAttribute);

        return configurationAttribute;
    }

    protected ConfigurationAttribute toUnwrappedModel(
        ConfigurationAttribute configurationAttribute) {
        if (configurationAttribute instanceof ConfigurationAttributeImpl) {
            return configurationAttribute;
        }

        ConfigurationAttributeImpl configurationAttributeImpl = new ConfigurationAttributeImpl();

        configurationAttributeImpl.setNew(configurationAttribute.isNew());
        configurationAttributeImpl.setPrimaryKey(configurationAttribute.getPrimaryKey());

        configurationAttributeImpl.setName(configurationAttribute.getName());
        configurationAttributeImpl.setAdditionalId(configurationAttribute.getAdditionalId());
        configurationAttributeImpl.setNumericValue(configurationAttribute.getNumericValue());
        configurationAttributeImpl.setStringValue(configurationAttribute.getStringValue());
        configurationAttributeImpl.setRealValue(configurationAttribute.getRealValue());

        return configurationAttributeImpl;
    }

    /**
     * Returns the configuration attribute with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the configuration attribute
     * @return the configuration attribute
     * @throws com.ext.portlet.NoSuchConfigurationAttributeException if a configuration attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ConfigurationAttribute findByPrimaryKey(Serializable primaryKey)
        throws NoSuchConfigurationAttributeException, SystemException {
        ConfigurationAttribute configurationAttribute = fetchByPrimaryKey(primaryKey);

        if (configurationAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchConfigurationAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return configurationAttribute;
    }

    /**
     * Returns the configuration attribute with the primary key or throws a {@link com.ext.portlet.NoSuchConfigurationAttributeException} if it could not be found.
     *
     * @param configurationAttributePK the primary key of the configuration attribute
     * @return the configuration attribute
     * @throws com.ext.portlet.NoSuchConfigurationAttributeException if a configuration attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ConfigurationAttribute findByPrimaryKey(
        ConfigurationAttributePK configurationAttributePK)
        throws NoSuchConfigurationAttributeException, SystemException {
        return findByPrimaryKey((Serializable) configurationAttributePK);
    }

    /**
     * Returns the configuration attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the configuration attribute
     * @return the configuration attribute, or <code>null</code> if a configuration attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ConfigurationAttribute fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ConfigurationAttribute configurationAttribute = (ConfigurationAttribute) EntityCacheUtil.getResult(ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ConfigurationAttributeImpl.class, primaryKey);

        if (configurationAttribute == _nullConfigurationAttribute) {
            return null;
        }

        if (configurationAttribute == null) {
            Session session = null;

            try {
                session = openSession();

                configurationAttribute = (ConfigurationAttribute) session.get(ConfigurationAttributeImpl.class,
                        primaryKey);

                if (configurationAttribute != null) {
                    cacheResult(configurationAttribute);
                } else {
                    EntityCacheUtil.putResult(ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        ConfigurationAttributeImpl.class, primaryKey,
                        _nullConfigurationAttribute);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ConfigurationAttributeModelImpl.ENTITY_CACHE_ENABLED,
                    ConfigurationAttributeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return configurationAttribute;
    }

    /**
     * Returns the configuration attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param configurationAttributePK the primary key of the configuration attribute
     * @return the configuration attribute, or <code>null</code> if a configuration attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ConfigurationAttribute fetchByPrimaryKey(
        ConfigurationAttributePK configurationAttributePK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) configurationAttributePK);
    }

    /**
     * Returns all the configuration attributes.
     *
     * @return the configuration attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ConfigurationAttribute> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the configuration attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ConfigurationAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of configuration attributes
     * @param end the upper bound of the range of configuration attributes (not inclusive)
     * @return the range of configuration attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ConfigurationAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the configuration attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ConfigurationAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of configuration attributes
     * @param end the upper bound of the range of configuration attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of configuration attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ConfigurationAttribute> findAll(int start, int end,
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

        List<ConfigurationAttribute> list = (List<ConfigurationAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONFIGURATIONATTRIBUTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONFIGURATIONATTRIBUTE;

                if (pagination) {
                    sql = sql.concat(ConfigurationAttributeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ConfigurationAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ConfigurationAttribute>(list);
                } else {
                    list = (List<ConfigurationAttribute>) QueryUtil.list(q,
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
     * Removes all the configuration attributes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ConfigurationAttribute configurationAttribute : findAll()) {
            remove(configurationAttribute);
        }
    }

    /**
     * Returns the number of configuration attributes.
     *
     * @return the number of configuration attributes
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

                Query q = session.createQuery(_SQL_COUNT_CONFIGURATIONATTRIBUTE);

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
     * Initializes the configuration attribute persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ConfigurationAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ConfigurationAttribute>> listenersList = new ArrayList<ModelListener<ConfigurationAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ConfigurationAttribute>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ConfigurationAttributeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
