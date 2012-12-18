package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.NoSuchModelDiscussionException;
import com.ext.portlet.models.model.ModelDiscussion;
import com.ext.portlet.models.model.impl.ModelDiscussionImpl;
import com.ext.portlet.models.model.impl.ModelDiscussionModelImpl;
import com.ext.portlet.models.service.persistence.ModelCategoryPersistence;
import com.ext.portlet.models.service.persistence.ModelDiscussionPersistence;
import com.ext.portlet.models.service.persistence.ModelGlobalPreferencePersistence;
import com.ext.portlet.models.service.persistence.ModelInputGroupPersistence;
import com.ext.portlet.models.service.persistence.ModelInputItemPersistence;
import com.ext.portlet.models.service.persistence.ModelOutputChartOrderPersistence;
import com.ext.portlet.models.service.persistence.ModelOutputItemPersistence;
import com.ext.portlet.models.service.persistence.ModelPositionPersistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the model discussion service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelDiscussionPersistence
 * @see ModelDiscussionUtil
 * @generated
 */
public class ModelDiscussionPersistenceImpl extends BasePersistenceImpl<ModelDiscussion>
    implements ModelDiscussionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ModelDiscussionUtil} to access the model discussion persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ModelDiscussionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELID = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            ModelDiscussionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByModelId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID =
        new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            ModelDiscussionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModelId",
            new String[] { Long.class.getName() },
            ModelDiscussionModelImpl.MODELID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELID = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DISCUSSIONID =
        new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            ModelDiscussionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByDiscussionId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DISCUSSIONID =
        new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            ModelDiscussionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDiscussionId",
            new String[] { Long.class.getName() },
            ModelDiscussionModelImpl.CATEGORYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_DISCUSSIONID = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDiscussionId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            ModelDiscussionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED,
            ModelDiscussionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MODELDISCUSSION = "SELECT modelDiscussion FROM ModelDiscussion modelDiscussion";
    private static final String _SQL_SELECT_MODELDISCUSSION_WHERE = "SELECT modelDiscussion FROM ModelDiscussion modelDiscussion WHERE ";
    private static final String _SQL_COUNT_MODELDISCUSSION = "SELECT COUNT(modelDiscussion) FROM ModelDiscussion modelDiscussion";
    private static final String _SQL_COUNT_MODELDISCUSSION_WHERE = "SELECT COUNT(modelDiscussion) FROM ModelDiscussion modelDiscussion WHERE ";
    private static final String _FINDER_COLUMN_MODELID_MODELID_2 = "modelDiscussion.modelId = ?";
    private static final String _FINDER_COLUMN_DISCUSSIONID_CATEGORYID_2 = "modelDiscussion.categoryId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "modelDiscussion.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModelDiscussion exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ModelDiscussion exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ModelDiscussionPersistenceImpl.class);
    private static ModelDiscussion _nullModelDiscussion = new ModelDiscussionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ModelDiscussion> toCacheModel() {
                return _nullModelDiscussionCacheModel;
            }
        };

    private static CacheModel<ModelDiscussion> _nullModelDiscussionCacheModel = new CacheModel<ModelDiscussion>() {
            public ModelDiscussion toEntityModel() {
                return _nullModelDiscussion;
            }
        };

    @BeanReference(type = ModelCategoryPersistence.class)
    protected ModelCategoryPersistence modelCategoryPersistence;
    @BeanReference(type = ModelDiscussionPersistence.class)
    protected ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(type = ModelGlobalPreferencePersistence.class)
    protected ModelGlobalPreferencePersistence modelGlobalPreferencePersistence;
    @BeanReference(type = ModelInputGroupPersistence.class)
    protected ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(type = ModelInputItemPersistence.class)
    protected ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(type = ModelOutputChartOrderPersistence.class)
    protected ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(type = ModelOutputItemPersistence.class)
    protected ModelOutputItemPersistence modelOutputItemPersistence;
    @BeanReference(type = ModelPositionPersistence.class)
    protected ModelPositionPersistence modelPositionPersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the model discussion in the entity cache if it is enabled.
     *
     * @param modelDiscussion the model discussion
     */
    public void cacheResult(ModelDiscussion modelDiscussion) {
        EntityCacheUtil.putResult(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionImpl.class, modelDiscussion.getPrimaryKey(),
            modelDiscussion);

        modelDiscussion.resetOriginalValues();
    }

    /**
     * Caches the model discussions in the entity cache if it is enabled.
     *
     * @param modelDiscussions the model discussions
     */
    public void cacheResult(List<ModelDiscussion> modelDiscussions) {
        for (ModelDiscussion modelDiscussion : modelDiscussions) {
            if (EntityCacheUtil.getResult(
                        ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                        ModelDiscussionImpl.class,
                        modelDiscussion.getPrimaryKey()) == null) {
                cacheResult(modelDiscussion);
            } else {
                modelDiscussion.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all model discussions.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ModelDiscussionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ModelDiscussionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the model discussion.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ModelDiscussion modelDiscussion) {
        EntityCacheUtil.removeResult(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionImpl.class, modelDiscussion.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ModelDiscussion> modelDiscussions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ModelDiscussion modelDiscussion : modelDiscussions) {
            EntityCacheUtil.removeResult(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                ModelDiscussionImpl.class, modelDiscussion.getPrimaryKey());
        }
    }

    /**
     * Creates a new model discussion with the primary key. Does not add the model discussion to the database.
     *
     * @param modelDiscussionId the primary key for the new model discussion
     * @return the new model discussion
     */
    public ModelDiscussion create(long modelDiscussionId) {
        ModelDiscussion modelDiscussion = new ModelDiscussionImpl();

        modelDiscussion.setNew(true);
        modelDiscussion.setPrimaryKey(modelDiscussionId);

        return modelDiscussion;
    }

    /**
     * Removes the model discussion with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param modelDiscussionId the primary key of the model discussion
     * @return the model discussion that was removed
     * @throws com.ext.portlet.models.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelDiscussion remove(long modelDiscussionId)
        throws NoSuchModelDiscussionException, SystemException {
        return remove(Long.valueOf(modelDiscussionId));
    }

    /**
     * Removes the model discussion with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the model discussion
     * @return the model discussion that was removed
     * @throws com.ext.portlet.models.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelDiscussion remove(Serializable primaryKey)
        throws NoSuchModelDiscussionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelDiscussion modelDiscussion = (ModelDiscussion) session.get(ModelDiscussionImpl.class,
                    primaryKey);

            if (modelDiscussion == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchModelDiscussionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(modelDiscussion);
        } catch (NoSuchModelDiscussionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ModelDiscussion removeImpl(ModelDiscussion modelDiscussion)
        throws SystemException {
        modelDiscussion = toUnwrappedModel(modelDiscussion);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, modelDiscussion);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(modelDiscussion);

        return modelDiscussion;
    }

    @Override
    public ModelDiscussion updateImpl(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion,
        boolean merge) throws SystemException {
        modelDiscussion = toUnwrappedModel(modelDiscussion);

        boolean isNew = modelDiscussion.isNew();

        ModelDiscussionModelImpl modelDiscussionModelImpl = (ModelDiscussionModelImpl) modelDiscussion;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelDiscussion, merge);

            modelDiscussion.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ModelDiscussionModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((modelDiscussionModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(modelDiscussionModelImpl.getOriginalModelId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID,
                    args);

                args = new Object[] {
                        Long.valueOf(modelDiscussionModelImpl.getModelId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID,
                    args);
            }

            if ((modelDiscussionModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DISCUSSIONID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(modelDiscussionModelImpl.getOriginalCategoryId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DISCUSSIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DISCUSSIONID,
                    args);

                args = new Object[] {
                        Long.valueOf(modelDiscussionModelImpl.getCategoryId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DISCUSSIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DISCUSSIONID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
            ModelDiscussionImpl.class, modelDiscussion.getPrimaryKey(),
            modelDiscussion);

        return modelDiscussion;
    }

    protected ModelDiscussion toUnwrappedModel(ModelDiscussion modelDiscussion) {
        if (modelDiscussion instanceof ModelDiscussionImpl) {
            return modelDiscussion;
        }

        ModelDiscussionImpl modelDiscussionImpl = new ModelDiscussionImpl();

        modelDiscussionImpl.setNew(modelDiscussion.isNew());
        modelDiscussionImpl.setPrimaryKey(modelDiscussion.getPrimaryKey());

        modelDiscussionImpl.setModelDiscussionId(modelDiscussion.getModelDiscussionId());
        modelDiscussionImpl.setModelId(modelDiscussion.getModelId());
        modelDiscussionImpl.setCategoryId(modelDiscussion.getCategoryId());

        return modelDiscussionImpl;
    }

    /**
     * Returns the model discussion with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the model discussion
     * @return the model discussion
     * @throws com.liferay.portal.NoSuchModelException if a model discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelDiscussion findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the model discussion with the primary key or throws a {@link com.ext.portlet.models.NoSuchModelDiscussionException} if it could not be found.
     *
     * @param modelDiscussionId the primary key of the model discussion
     * @return the model discussion
     * @throws com.ext.portlet.models.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelDiscussion findByPrimaryKey(long modelDiscussionId)
        throws NoSuchModelDiscussionException, SystemException {
        ModelDiscussion modelDiscussion = fetchByPrimaryKey(modelDiscussionId);

        if (modelDiscussion == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + modelDiscussionId);
            }

            throw new NoSuchModelDiscussionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                modelDiscussionId);
        }

        return modelDiscussion;
    }

    /**
     * Returns the model discussion with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the model discussion
     * @return the model discussion, or <code>null</code> if a model discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelDiscussion fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the model discussion with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param modelDiscussionId the primary key of the model discussion
     * @return the model discussion, or <code>null</code> if a model discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelDiscussion fetchByPrimaryKey(long modelDiscussionId)
        throws SystemException {
        ModelDiscussion modelDiscussion = (ModelDiscussion) EntityCacheUtil.getResult(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                ModelDiscussionImpl.class, modelDiscussionId);

        if (modelDiscussion == _nullModelDiscussion) {
            return null;
        }

        if (modelDiscussion == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                modelDiscussion = (ModelDiscussion) session.get(ModelDiscussionImpl.class,
                        Long.valueOf(modelDiscussionId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (modelDiscussion != null) {
                    cacheResult(modelDiscussion);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ModelDiscussionModelImpl.ENTITY_CACHE_ENABLED,
                        ModelDiscussionImpl.class, modelDiscussionId,
                        _nullModelDiscussion);
                }

                closeSession(session);
            }
        }

        return modelDiscussion;
    }

    /**
     * Returns all the model discussions where modelId = &#63;.
     *
     * @param modelId the model ID
     * @return the matching model discussions
     * @throws SystemException if a system exception occurred
     */
    public List<ModelDiscussion> findByModelId(long modelId)
        throws SystemException {
        return findByModelId(modelId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model discussions where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param start the lower bound of the range of model discussions
     * @param end the upper bound of the range of model discussions (not inclusive)
     * @return the range of matching model discussions
     * @throws SystemException if a system exception occurred
     */
    public List<ModelDiscussion> findByModelId(long modelId, int start, int end)
        throws SystemException {
        return findByModelId(modelId, start, end, null);
    }

    /**
     * Returns an ordered range of all the model discussions where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param start the lower bound of the range of model discussions
     * @param end the upper bound of the range of model discussions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching model discussions
     * @throws SystemException if a system exception occurred
     */
    public List<ModelDiscussion> findByModelId(long modelId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID;
            finderArgs = new Object[] { modelId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELID;
            finderArgs = new Object[] { modelId, start, end, orderByComparator };
        }

        List<ModelDiscussion> list = (List<ModelDiscussion>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_MODELDISCUSSION_WHERE);

            query.append(_FINDER_COLUMN_MODELID_MODELID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

                list = (List<ModelDiscussion>) QueryUtil.list(q, getDialect(),
                        start, end);
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
     * Returns the first model discussion in the ordered set where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching model discussion
     * @throws com.ext.portlet.models.NoSuchModelDiscussionException if a matching model discussion could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelDiscussion findByModelId_First(long modelId,
        OrderByComparator orderByComparator)
        throws NoSuchModelDiscussionException, SystemException {
        List<ModelDiscussion> list = findByModelId(modelId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelId=");
            msg.append(modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelDiscussionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last model discussion in the ordered set where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching model discussion
     * @throws com.ext.portlet.models.NoSuchModelDiscussionException if a matching model discussion could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelDiscussion findByModelId_Last(long modelId,
        OrderByComparator orderByComparator)
        throws NoSuchModelDiscussionException, SystemException {
        int count = countByModelId(modelId);

        List<ModelDiscussion> list = findByModelId(modelId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelId=");
            msg.append(modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelDiscussionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the model discussions before and after the current model discussion in the ordered set where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelDiscussionId the primary key of the current model discussion
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next model discussion
     * @throws com.ext.portlet.models.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelDiscussion[] findByModelId_PrevAndNext(long modelDiscussionId,
        long modelId, OrderByComparator orderByComparator)
        throws NoSuchModelDiscussionException, SystemException {
        ModelDiscussion modelDiscussion = findByPrimaryKey(modelDiscussionId);

        Session session = null;

        try {
            session = openSession();

            ModelDiscussion[] array = new ModelDiscussionImpl[3];

            array[0] = getByModelId_PrevAndNext(session, modelDiscussion,
                    modelId, orderByComparator, true);

            array[1] = modelDiscussion;

            array[2] = getByModelId_PrevAndNext(session, modelDiscussion,
                    modelId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ModelDiscussion getByModelId_PrevAndNext(Session session,
        ModelDiscussion modelDiscussion, long modelId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MODELDISCUSSION_WHERE);

        query.append(_FINDER_COLUMN_MODELID_MODELID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(modelId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(modelDiscussion);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ModelDiscussion> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the model discussions where categoryId = &#63;.
     *
     * @param categoryId the category ID
     * @return the matching model discussions
     * @throws SystemException if a system exception occurred
     */
    public List<ModelDiscussion> findByDiscussionId(long categoryId)
        throws SystemException {
        return findByDiscussionId(categoryId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model discussions where categoryId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param categoryId the category ID
     * @param start the lower bound of the range of model discussions
     * @param end the upper bound of the range of model discussions (not inclusive)
     * @return the range of matching model discussions
     * @throws SystemException if a system exception occurred
     */
    public List<ModelDiscussion> findByDiscussionId(long categoryId, int start,
        int end) throws SystemException {
        return findByDiscussionId(categoryId, start, end, null);
    }

    /**
     * Returns an ordered range of all the model discussions where categoryId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param categoryId the category ID
     * @param start the lower bound of the range of model discussions
     * @param end the upper bound of the range of model discussions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching model discussions
     * @throws SystemException if a system exception occurred
     */
    public List<ModelDiscussion> findByDiscussionId(long categoryId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_DISCUSSIONID;
            finderArgs = new Object[] { categoryId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DISCUSSIONID;
            finderArgs = new Object[] { categoryId, start, end, orderByComparator };
        }

        List<ModelDiscussion> list = (List<ModelDiscussion>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_MODELDISCUSSION_WHERE);

            query.append(_FINDER_COLUMN_DISCUSSIONID_CATEGORYID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(categoryId);

                list = (List<ModelDiscussion>) QueryUtil.list(q, getDialect(),
                        start, end);
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
     * Returns the first model discussion in the ordered set where categoryId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param categoryId the category ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching model discussion
     * @throws com.ext.portlet.models.NoSuchModelDiscussionException if a matching model discussion could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelDiscussion findByDiscussionId_First(long categoryId,
        OrderByComparator orderByComparator)
        throws NoSuchModelDiscussionException, SystemException {
        List<ModelDiscussion> list = findByDiscussionId(categoryId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("categoryId=");
            msg.append(categoryId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelDiscussionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last model discussion in the ordered set where categoryId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param categoryId the category ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching model discussion
     * @throws com.ext.portlet.models.NoSuchModelDiscussionException if a matching model discussion could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelDiscussion findByDiscussionId_Last(long categoryId,
        OrderByComparator orderByComparator)
        throws NoSuchModelDiscussionException, SystemException {
        int count = countByDiscussionId(categoryId);

        List<ModelDiscussion> list = findByDiscussionId(categoryId, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("categoryId=");
            msg.append(categoryId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelDiscussionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the model discussions before and after the current model discussion in the ordered set where categoryId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelDiscussionId the primary key of the current model discussion
     * @param categoryId the category ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next model discussion
     * @throws com.ext.portlet.models.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelDiscussion[] findByDiscussionId_PrevAndNext(
        long modelDiscussionId, long categoryId,
        OrderByComparator orderByComparator)
        throws NoSuchModelDiscussionException, SystemException {
        ModelDiscussion modelDiscussion = findByPrimaryKey(modelDiscussionId);

        Session session = null;

        try {
            session = openSession();

            ModelDiscussion[] array = new ModelDiscussionImpl[3];

            array[0] = getByDiscussionId_PrevAndNext(session, modelDiscussion,
                    categoryId, orderByComparator, true);

            array[1] = modelDiscussion;

            array[2] = getByDiscussionId_PrevAndNext(session, modelDiscussion,
                    categoryId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ModelDiscussion getByDiscussionId_PrevAndNext(Session session,
        ModelDiscussion modelDiscussion, long categoryId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MODELDISCUSSION_WHERE);

        query.append(_FINDER_COLUMN_DISCUSSIONID_CATEGORYID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(categoryId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(modelDiscussion);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ModelDiscussion> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the model discussions.
     *
     * @return the model discussions
     * @throws SystemException if a system exception occurred
     */
    public List<ModelDiscussion> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model discussions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of model discussions
     * @param end the upper bound of the range of model discussions (not inclusive)
     * @return the range of model discussions
     * @throws SystemException if a system exception occurred
     */
    public List<ModelDiscussion> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the model discussions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of model discussions
     * @param end the upper bound of the range of model discussions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of model discussions
     * @throws SystemException if a system exception occurred
     */
    public List<ModelDiscussion> findAll(int start, int end,
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

        List<ModelDiscussion> list = (List<ModelDiscussion>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MODELDISCUSSION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MODELDISCUSSION;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ModelDiscussion>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelDiscussion>) QueryUtil.list(q,
                            getDialect(), start, end);
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
     * Removes all the model discussions where modelId = &#63; from the database.
     *
     * @param modelId the model ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByModelId(long modelId) throws SystemException {
        for (ModelDiscussion modelDiscussion : findByModelId(modelId)) {
            remove(modelDiscussion);
        }
    }

    /**
     * Removes all the model discussions where categoryId = &#63; from the database.
     *
     * @param categoryId the category ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByDiscussionId(long categoryId) throws SystemException {
        for (ModelDiscussion modelDiscussion : findByDiscussionId(categoryId)) {
            remove(modelDiscussion);
        }
    }

    /**
     * Removes all the model discussions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ModelDiscussion modelDiscussion : findAll()) {
            remove(modelDiscussion);
        }
    }

    /**
     * Returns the number of model discussions where modelId = &#63;.
     *
     * @param modelId the model ID
     * @return the number of matching model discussions
     * @throws SystemException if a system exception occurred
     */
    public int countByModelId(long modelId) throws SystemException {
        Object[] finderArgs = new Object[] { modelId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODELDISCUSSION_WHERE);

            query.append(_FINDER_COLUMN_MODELID_MODELID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of model discussions where categoryId = &#63;.
     *
     * @param categoryId the category ID
     * @return the number of matching model discussions
     * @throws SystemException if a system exception occurred
     */
    public int countByDiscussionId(long categoryId) throws SystemException {
        Object[] finderArgs = new Object[] { categoryId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DISCUSSIONID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODELDISCUSSION_WHERE);

            query.append(_FINDER_COLUMN_DISCUSSIONID_CATEGORYID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(categoryId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DISCUSSIONID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of model discussions.
     *
     * @return the number of model discussions
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_MODELDISCUSSION);

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
     * Initializes the model discussion persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.models.model.ModelDiscussion")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelDiscussion>> listenersList = new ArrayList<ModelListener<ModelDiscussion>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelDiscussion>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ModelDiscussionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
