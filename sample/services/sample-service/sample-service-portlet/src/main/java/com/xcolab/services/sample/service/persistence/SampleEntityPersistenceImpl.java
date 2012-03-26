package com.xcolab.services.sample.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.xcolab.services.sample.NoSuchSampleEntityException;
import com.xcolab.services.sample.model.SampleEntity;
import com.xcolab.services.sample.model.impl.SampleEntityImpl;
import com.xcolab.services.sample.model.impl.SampleEntityModelImpl;
import com.xcolab.services.sample.service.persistence.SampleEntityPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the sample entity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SampleEntityPersistence
 * @see SampleEntityUtil
 * @generated
 */
public class SampleEntityPersistenceImpl extends BasePersistenceImpl<SampleEntity>
    implements SampleEntityPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link SampleEntityUtil} to access the sample entity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = SampleEntityImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SampleEntityModelImpl.ENTITY_CACHE_ENABLED,
            SampleEntityModelImpl.FINDER_CACHE_ENABLED, SampleEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SampleEntityModelImpl.ENTITY_CACHE_ENABLED,
            SampleEntityModelImpl.FINDER_CACHE_ENABLED, SampleEntityImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SampleEntityModelImpl.ENTITY_CACHE_ENABLED,
            SampleEntityModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_SAMPLEENTITY = "SELECT sampleEntity FROM SampleEntity sampleEntity";
    private static final String _SQL_COUNT_SAMPLEENTITY = "SELECT COUNT(sampleEntity) FROM SampleEntity sampleEntity";
    private static final String _ORDER_BY_ENTITY_ALIAS = "sampleEntity.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SampleEntity exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(SampleEntityPersistenceImpl.class);
    private static SampleEntity _nullSampleEntity = new SampleEntityImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<SampleEntity> toCacheModel() {
                return _nullSampleEntityCacheModel;
            }
        };

    private static CacheModel<SampleEntity> _nullSampleEntityCacheModel = new CacheModel<SampleEntity>() {
            public SampleEntity toEntityModel() {
                return _nullSampleEntity;
            }
        };

    @BeanReference(type = SampleEntityPersistence.class)
    protected SampleEntityPersistence sampleEntityPersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the sample entity in the entity cache if it is enabled.
     *
     * @param sampleEntity the sample entity
     */
    public void cacheResult(SampleEntity sampleEntity) {
        EntityCacheUtil.putResult(SampleEntityModelImpl.ENTITY_CACHE_ENABLED,
            SampleEntityImpl.class, sampleEntity.getPrimaryKey(), sampleEntity);

        sampleEntity.resetOriginalValues();
    }

    /**
     * Caches the sample entities in the entity cache if it is enabled.
     *
     * @param sampleEntities the sample entities
     */
    public void cacheResult(List<SampleEntity> sampleEntities) {
        for (SampleEntity sampleEntity : sampleEntities) {
            if (EntityCacheUtil.getResult(
                        SampleEntityModelImpl.ENTITY_CACHE_ENABLED,
                        SampleEntityImpl.class, sampleEntity.getPrimaryKey()) == null) {
                cacheResult(sampleEntity);
            } else {
                sampleEntity.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all sample entities.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(SampleEntityImpl.class.getName());
        }

        EntityCacheUtil.clearCache(SampleEntityImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the sample entity.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(SampleEntity sampleEntity) {
        EntityCacheUtil.removeResult(SampleEntityModelImpl.ENTITY_CACHE_ENABLED,
            SampleEntityImpl.class, sampleEntity.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<SampleEntity> sampleEntities) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (SampleEntity sampleEntity : sampleEntities) {
            EntityCacheUtil.removeResult(SampleEntityModelImpl.ENTITY_CACHE_ENABLED,
                SampleEntityImpl.class, sampleEntity.getPrimaryKey());
        }
    }

    /**
     * Creates a new sample entity with the primary key. Does not add the sample entity to the database.
     *
     * @param id the primary key for the new sample entity
     * @return the new sample entity
     */
    public SampleEntity create(long id) {
        SampleEntity sampleEntity = new SampleEntityImpl();

        sampleEntity.setNew(true);
        sampleEntity.setPrimaryKey(id);

        return sampleEntity;
    }

    /**
     * Removes the sample entity with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the sample entity
     * @return the sample entity that was removed
     * @throws com.xcolab.services.sample.NoSuchSampleEntityException if a sample entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public SampleEntity remove(long id)
        throws NoSuchSampleEntityException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the sample entity with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the sample entity
     * @return the sample entity that was removed
     * @throws com.xcolab.services.sample.NoSuchSampleEntityException if a sample entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SampleEntity remove(Serializable primaryKey)
        throws NoSuchSampleEntityException, SystemException {
        Session session = null;

        try {
            session = openSession();

            SampleEntity sampleEntity = (SampleEntity) session.get(SampleEntityImpl.class,
                    primaryKey);

            if (sampleEntity == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchSampleEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(sampleEntity);
        } catch (NoSuchSampleEntityException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected SampleEntity removeImpl(SampleEntity sampleEntity)
        throws SystemException {
        sampleEntity = toUnwrappedModel(sampleEntity);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, sampleEntity);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(sampleEntity);

        return sampleEntity;
    }

    @Override
    public SampleEntity updateImpl(
        com.xcolab.services.sample.model.SampleEntity sampleEntity,
        boolean merge) throws SystemException {
        sampleEntity = toUnwrappedModel(sampleEntity);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, sampleEntity, merge);

            sampleEntity.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        EntityCacheUtil.putResult(SampleEntityModelImpl.ENTITY_CACHE_ENABLED,
            SampleEntityImpl.class, sampleEntity.getPrimaryKey(), sampleEntity);

        return sampleEntity;
    }

    protected SampleEntity toUnwrappedModel(SampleEntity sampleEntity) {
        if (sampleEntity instanceof SampleEntityImpl) {
            return sampleEntity;
        }

        SampleEntityImpl sampleEntityImpl = new SampleEntityImpl();

        sampleEntityImpl.setNew(sampleEntity.isNew());
        sampleEntityImpl.setPrimaryKey(sampleEntity.getPrimaryKey());

        sampleEntityImpl.setId(sampleEntity.getId());
        sampleEntityImpl.setContent(sampleEntity.getContent());
        sampleEntityImpl.setCreated(sampleEntity.getCreated());
        sampleEntityImpl.setAuthorId(sampleEntity.getAuthorId());

        return sampleEntityImpl;
    }

    /**
     * Returns the sample entity with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the sample entity
     * @return the sample entity
     * @throws com.liferay.portal.NoSuchModelException if a sample entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SampleEntity findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the sample entity with the primary key or throws a {@link com.xcolab.services.sample.NoSuchSampleEntityException} if it could not be found.
     *
     * @param id the primary key of the sample entity
     * @return the sample entity
     * @throws com.xcolab.services.sample.NoSuchSampleEntityException if a sample entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public SampleEntity findByPrimaryKey(long id)
        throws NoSuchSampleEntityException, SystemException {
        SampleEntity sampleEntity = fetchByPrimaryKey(id);

        if (sampleEntity == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchSampleEntityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return sampleEntity;
    }

    /**
     * Returns the sample entity with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the sample entity
     * @return the sample entity, or <code>null</code> if a sample entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SampleEntity fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the sample entity with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the sample entity
     * @return the sample entity, or <code>null</code> if a sample entity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public SampleEntity fetchByPrimaryKey(long id) throws SystemException {
        SampleEntity sampleEntity = (SampleEntity) EntityCacheUtil.getResult(SampleEntityModelImpl.ENTITY_CACHE_ENABLED,
                SampleEntityImpl.class, id);

        if (sampleEntity == _nullSampleEntity) {
            return null;
        }

        if (sampleEntity == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                sampleEntity = (SampleEntity) session.get(SampleEntityImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (sampleEntity != null) {
                    cacheResult(sampleEntity);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(SampleEntityModelImpl.ENTITY_CACHE_ENABLED,
                        SampleEntityImpl.class, id, _nullSampleEntity);
                }

                closeSession(session);
            }
        }

        return sampleEntity;
    }

    /**
     * Returns all the sample entities.
     *
     * @return the sample entities
     * @throws SystemException if a system exception occurred
     */
    public List<SampleEntity> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the sample entities.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of sample entities
     * @param end the upper bound of the range of sample entities (not inclusive)
     * @return the range of sample entities
     * @throws SystemException if a system exception occurred
     */
    public List<SampleEntity> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the sample entities.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of sample entities
     * @param end the upper bound of the range of sample entities (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of sample entities
     * @throws SystemException if a system exception occurred
     */
    public List<SampleEntity> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = new Object[] { start, end, orderByComparator };

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<SampleEntity> list = (List<SampleEntity>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_SAMPLEENTITY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_SAMPLEENTITY;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<SampleEntity>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<SampleEntity>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the sample entities from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (SampleEntity sampleEntity : findAll()) {
            remove(sampleEntity);
        }
    }

    /**
     * Returns the number of sample entities.
     *
     * @return the number of sample entities
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_SAMPLEENTITY);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the sample entity persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.xcolab.services.sample.model.SampleEntity")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<SampleEntity>> listenersList = new ArrayList<ModelListener<SampleEntity>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<SampleEntity>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(SampleEntityImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
