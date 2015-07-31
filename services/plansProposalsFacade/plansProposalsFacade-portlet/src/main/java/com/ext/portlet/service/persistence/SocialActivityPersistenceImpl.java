package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchSocialActivityException;
import com.ext.portlet.model.SocialActivity;
import com.ext.portlet.model.impl.SocialActivityImpl;
import com.ext.portlet.model.impl.SocialActivityModelImpl;
import com.ext.portlet.service.persistence.SocialActivityPersistence;

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
 * The persistence implementation for the social activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityPersistence
 * @see SocialActivityUtil
 * @generated
 */
public class SocialActivityPersistenceImpl extends BasePersistenceImpl<SocialActivity>
    implements SocialActivityPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link SocialActivityUtil} to access the social activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = SocialActivityImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
            SocialActivityModelImpl.FINDER_CACHE_ENABLED,
            SocialActivityImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
            SocialActivityModelImpl.FINDER_CACHE_ENABLED,
            SocialActivityImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
            SocialActivityModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_SOCIALACTIVITY = "SELECT socialActivity FROM SocialActivity socialActivity";
    private static final String _SQL_COUNT_SOCIALACTIVITY = "SELECT COUNT(socialActivity) FROM SocialActivity socialActivity";
    private static final String _ORDER_BY_ENTITY_ALIAS = "socialActivity.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SocialActivity exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(SocialActivityPersistenceImpl.class);
    private static SocialActivity _nullSocialActivity = new SocialActivityImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<SocialActivity> toCacheModel() {
                return _nullSocialActivityCacheModel;
            }
        };

    private static CacheModel<SocialActivity> _nullSocialActivityCacheModel = new CacheModel<SocialActivity>() {
            @Override
            public SocialActivity toEntityModel() {
                return _nullSocialActivity;
            }
        };

    public SocialActivityPersistenceImpl() {
        setModelClass(SocialActivity.class);
    }

    /**
     * Caches the social activity in the entity cache if it is enabled.
     *
     * @param socialActivity the social activity
     */
    @Override
    public void cacheResult(SocialActivity socialActivity) {
        EntityCacheUtil.putResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
            SocialActivityImpl.class, socialActivity.getPrimaryKey(),
            socialActivity);

        socialActivity.resetOriginalValues();
    }

    /**
     * Caches the social activities in the entity cache if it is enabled.
     *
     * @param socialActivities the social activities
     */
    @Override
    public void cacheResult(List<SocialActivity> socialActivities) {
        for (SocialActivity socialActivity : socialActivities) {
            if (EntityCacheUtil.getResult(
                        SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
                        SocialActivityImpl.class, socialActivity.getPrimaryKey()) == null) {
                cacheResult(socialActivity);
            } else {
                socialActivity.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all social activities.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(SocialActivityImpl.class.getName());
        }

        EntityCacheUtil.clearCache(SocialActivityImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the social activity.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(SocialActivity socialActivity) {
        EntityCacheUtil.removeResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
            SocialActivityImpl.class, socialActivity.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<SocialActivity> socialActivities) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (SocialActivity socialActivity : socialActivities) {
            EntityCacheUtil.removeResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
                SocialActivityImpl.class, socialActivity.getPrimaryKey());
        }
    }

    /**
     * Creates a new social activity with the primary key. Does not add the social activity to the database.
     *
     * @param activityId the primary key for the new social activity
     * @return the new social activity
     */
    @Override
    public SocialActivity create(long activityId) {
        SocialActivity socialActivity = new SocialActivityImpl();

        socialActivity.setNew(true);
        socialActivity.setPrimaryKey(activityId);

        return socialActivity;
    }

    /**
     * Removes the social activity with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param activityId the primary key of the social activity
     * @return the social activity that was removed
     * @throws com.ext.portlet.NoSuchSocialActivityException if a social activity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SocialActivity remove(long activityId)
        throws NoSuchSocialActivityException, SystemException {
        return remove((Serializable) activityId);
    }

    /**
     * Removes the social activity with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the social activity
     * @return the social activity that was removed
     * @throws com.ext.portlet.NoSuchSocialActivityException if a social activity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SocialActivity remove(Serializable primaryKey)
        throws NoSuchSocialActivityException, SystemException {
        Session session = null;

        try {
            session = openSession();

            SocialActivity socialActivity = (SocialActivity) session.get(SocialActivityImpl.class,
                    primaryKey);

            if (socialActivity == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchSocialActivityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(socialActivity);
        } catch (NoSuchSocialActivityException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected SocialActivity removeImpl(SocialActivity socialActivity)
        throws SystemException {
        socialActivity = toUnwrappedModel(socialActivity);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(socialActivity)) {
                socialActivity = (SocialActivity) session.get(SocialActivityImpl.class,
                        socialActivity.getPrimaryKeyObj());
            }

            if (socialActivity != null) {
                session.delete(socialActivity);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (socialActivity != null) {
            clearCache(socialActivity);
        }

        return socialActivity;
    }

    @Override
    public SocialActivity updateImpl(
        com.ext.portlet.model.SocialActivity socialActivity)
        throws SystemException {
        socialActivity = toUnwrappedModel(socialActivity);

        boolean isNew = socialActivity.isNew();

        Session session = null;

        try {
            session = openSession();

            if (socialActivity.isNew()) {
                session.save(socialActivity);

                socialActivity.setNew(false);
            } else {
                session.merge(socialActivity);
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

        EntityCacheUtil.putResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
            SocialActivityImpl.class, socialActivity.getPrimaryKey(),
            socialActivity);

        return socialActivity;
    }

    protected SocialActivity toUnwrappedModel(SocialActivity socialActivity) {
        if (socialActivity instanceof SocialActivityImpl) {
            return socialActivity;
        }

        SocialActivityImpl socialActivityImpl = new SocialActivityImpl();

        socialActivityImpl.setNew(socialActivity.isNew());
        socialActivityImpl.setPrimaryKey(socialActivity.getPrimaryKey());

        socialActivityImpl.setActivityId(socialActivity.getActivityId());
        socialActivityImpl.setUserId(socialActivity.getUserId());

        return socialActivityImpl;
    }

    /**
     * Returns the social activity with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the social activity
     * @return the social activity
     * @throws com.ext.portlet.NoSuchSocialActivityException if a social activity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SocialActivity findByPrimaryKey(Serializable primaryKey)
        throws NoSuchSocialActivityException, SystemException {
        SocialActivity socialActivity = fetchByPrimaryKey(primaryKey);

        if (socialActivity == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchSocialActivityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return socialActivity;
    }

    /**
     * Returns the social activity with the primary key or throws a {@link com.ext.portlet.NoSuchSocialActivityException} if it could not be found.
     *
     * @param activityId the primary key of the social activity
     * @return the social activity
     * @throws com.ext.portlet.NoSuchSocialActivityException if a social activity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SocialActivity findByPrimaryKey(long activityId)
        throws NoSuchSocialActivityException, SystemException {
        return findByPrimaryKey((Serializable) activityId);
    }

    /**
     * Returns the social activity with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the social activity
     * @return the social activity, or <code>null</code> if a social activity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SocialActivity fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        SocialActivity socialActivity = (SocialActivity) EntityCacheUtil.getResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
                SocialActivityImpl.class, primaryKey);

        if (socialActivity == _nullSocialActivity) {
            return null;
        }

        if (socialActivity == null) {
            Session session = null;

            try {
                session = openSession();

                socialActivity = (SocialActivity) session.get(SocialActivityImpl.class,
                        primaryKey);

                if (socialActivity != null) {
                    cacheResult(socialActivity);
                } else {
                    EntityCacheUtil.putResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
                        SocialActivityImpl.class, primaryKey,
                        _nullSocialActivity);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(SocialActivityModelImpl.ENTITY_CACHE_ENABLED,
                    SocialActivityImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return socialActivity;
    }

    /**
     * Returns the social activity with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param activityId the primary key of the social activity
     * @return the social activity, or <code>null</code> if a social activity with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public SocialActivity fetchByPrimaryKey(long activityId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) activityId);
    }

    /**
     * Returns all the social activities.
     *
     * @return the social activities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SocialActivity> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the social activities.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of social activities
     * @param end the upper bound of the range of social activities (not inclusive)
     * @return the range of social activities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SocialActivity> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the social activities.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of social activities
     * @param end the upper bound of the range of social activities (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of social activities
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<SocialActivity> findAll(int start, int end,
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

        List<SocialActivity> list = (List<SocialActivity>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_SOCIALACTIVITY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_SOCIALACTIVITY;

                if (pagination) {
                    sql = sql.concat(SocialActivityModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<SocialActivity>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<SocialActivity>(list);
                } else {
                    list = (List<SocialActivity>) QueryUtil.list(q,
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
     * Removes all the social activities from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (SocialActivity socialActivity : findAll()) {
            remove(socialActivity);
        }
    }

    /**
     * Returns the number of social activities.
     *
     * @return the number of social activities
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

                Query q = session.createQuery(_SQL_COUNT_SOCIALACTIVITY);

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
     * Initializes the social activity persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.SocialActivity")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<SocialActivity>> listenersList = new ArrayList<ModelListener<SocialActivity>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<SocialActivity>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(SocialActivityImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
