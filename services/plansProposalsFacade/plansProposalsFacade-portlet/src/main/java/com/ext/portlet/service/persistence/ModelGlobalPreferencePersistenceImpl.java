package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchModelGlobalPreferenceException;
import com.ext.portlet.model.ModelGlobalPreference;
import com.ext.portlet.model.impl.ModelGlobalPreferenceImpl;
import com.ext.portlet.model.impl.ModelGlobalPreferenceModelImpl;
import com.ext.portlet.service.persistence.ModelGlobalPreferencePersistence;

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
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the model global preference service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelGlobalPreferencePersistence
 * @see ModelGlobalPreferenceUtil
 * @generated
 */
public class ModelGlobalPreferencePersistenceImpl extends BasePersistenceImpl<ModelGlobalPreference>
    implements ModelGlobalPreferencePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ModelGlobalPreferenceUtil} to access the model global preference persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ModelGlobalPreferenceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_MODELID = new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByModelId", new String[] { Long.class.getName() },
            ModelGlobalPreferenceModelImpl.MODELID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELID = new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModelId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_MODELID_MODELID_2 = "modelGlobalPreference.modelId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELCATEGORYID =
        new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByModelCategoryId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELCATEGORYID =
        new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModelCategoryId",
            new String[] { Long.class.getName() },
            ModelGlobalPreferenceModelImpl.MODELCATEGORYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELCATEGORYID = new FinderPath(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByModelCategoryId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_MODELCATEGORYID_MODELCATEGORYID_2 =
        "modelGlobalPreference.modelCategoryId = ?";
    private static final String _SQL_SELECT_MODELGLOBALPREFERENCE = "SELECT modelGlobalPreference FROM ModelGlobalPreference modelGlobalPreference";
    private static final String _SQL_SELECT_MODELGLOBALPREFERENCE_WHERE = "SELECT modelGlobalPreference FROM ModelGlobalPreference modelGlobalPreference WHERE ";
    private static final String _SQL_COUNT_MODELGLOBALPREFERENCE = "SELECT COUNT(modelGlobalPreference) FROM ModelGlobalPreference modelGlobalPreference";
    private static final String _SQL_COUNT_MODELGLOBALPREFERENCE_WHERE = "SELECT COUNT(modelGlobalPreference) FROM ModelGlobalPreference modelGlobalPreference WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "modelGlobalPreference.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModelGlobalPreference exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ModelGlobalPreference exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ModelGlobalPreferencePersistenceImpl.class);
    private static ModelGlobalPreference _nullModelGlobalPreference = new ModelGlobalPreferenceImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ModelGlobalPreference> toCacheModel() {
                return _nullModelGlobalPreferenceCacheModel;
            }
        };

    private static CacheModel<ModelGlobalPreference> _nullModelGlobalPreferenceCacheModel =
        new CacheModel<ModelGlobalPreference>() {
            @Override
            public ModelGlobalPreference toEntityModel() {
                return _nullModelGlobalPreference;
            }
        };

    public ModelGlobalPreferencePersistenceImpl() {
        setModelClass(ModelGlobalPreference.class);
    }

    /**
     * Returns the model global preference where modelId = &#63; or throws a {@link com.ext.portlet.NoSuchModelGlobalPreferenceException} if it could not be found.
     *
     * @param modelId the model ID
     * @return the matching model global preference
     * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a matching model global preference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference findByModelId(long modelId)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        ModelGlobalPreference modelGlobalPreference = fetchByModelId(modelId);

        if (modelGlobalPreference == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelId=");
            msg.append(modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchModelGlobalPreferenceException(msg.toString());
        }

        return modelGlobalPreference;
    }

    /**
     * Returns the model global preference where modelId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param modelId the model ID
     * @return the matching model global preference, or <code>null</code> if a matching model global preference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference fetchByModelId(long modelId)
        throws SystemException {
        return fetchByModelId(modelId, true);
    }

    /**
     * Returns the model global preference where modelId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param modelId the model ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching model global preference, or <code>null</code> if a matching model global preference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference fetchByModelId(long modelId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { modelId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MODELID,
                    finderArgs, this);
        }

        if (result instanceof ModelGlobalPreference) {
            ModelGlobalPreference modelGlobalPreference = (ModelGlobalPreference) result;

            if ((modelId != modelGlobalPreference.getModelId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_MODELGLOBALPREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_MODELID_MODELID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

                List<ModelGlobalPreference> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ModelGlobalPreferencePersistenceImpl.fetchByModelId(long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ModelGlobalPreference modelGlobalPreference = list.get(0);

                    result = modelGlobalPreference;

                    cacheResult(modelGlobalPreference);

                    if ((modelGlobalPreference.getModelId() != modelId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELID,
                            finderArgs, modelGlobalPreference);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ModelGlobalPreference) result;
        }
    }

    /**
     * Removes the model global preference where modelId = &#63; from the database.
     *
     * @param modelId the model ID
     * @return the model global preference that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference removeByModelId(long modelId)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        ModelGlobalPreference modelGlobalPreference = findByModelId(modelId);

        return remove(modelGlobalPreference);
    }

    /**
     * Returns the number of model global preferences where modelId = &#63;.
     *
     * @param modelId the model ID
     * @return the number of matching model global preferences
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByModelId(long modelId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MODELID;

        Object[] finderArgs = new Object[] { modelId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODELGLOBALPREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_MODELID_MODELID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the model global preferences where modelCategoryId = &#63;.
     *
     * @param modelCategoryId the model category ID
     * @return the matching model global preferences
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelGlobalPreference> findByModelCategoryId(
        long modelCategoryId) throws SystemException {
        return findByModelCategoryId(modelCategoryId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model global preferences where modelCategoryId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelGlobalPreferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param modelCategoryId the model category ID
     * @param start the lower bound of the range of model global preferences
     * @param end the upper bound of the range of model global preferences (not inclusive)
     * @return the range of matching model global preferences
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelGlobalPreference> findByModelCategoryId(
        long modelCategoryId, int start, int end) throws SystemException {
        return findByModelCategoryId(modelCategoryId, start, end, null);
    }

    /**
     * Returns an ordered range of all the model global preferences where modelCategoryId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelGlobalPreferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param modelCategoryId the model category ID
     * @param start the lower bound of the range of model global preferences
     * @param end the upper bound of the range of model global preferences (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching model global preferences
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelGlobalPreference> findByModelCategoryId(
        long modelCategoryId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELCATEGORYID;
            finderArgs = new Object[] { modelCategoryId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELCATEGORYID;
            finderArgs = new Object[] {
                    modelCategoryId,
                    
                    start, end, orderByComparator
                };
        }

        List<ModelGlobalPreference> list = (List<ModelGlobalPreference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ModelGlobalPreference modelGlobalPreference : list) {
                if ((modelCategoryId != modelGlobalPreference.getModelCategoryId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_MODELGLOBALPREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_MODELCATEGORYID_MODELCATEGORYID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ModelGlobalPreferenceModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelCategoryId);

                if (!pagination) {
                    list = (List<ModelGlobalPreference>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ModelGlobalPreference>(list);
                } else {
                    list = (List<ModelGlobalPreference>) QueryUtil.list(q,
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
     * Returns the first model global preference in the ordered set where modelCategoryId = &#63;.
     *
     * @param modelCategoryId the model category ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching model global preference
     * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a matching model global preference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference findByModelCategoryId_First(
        long modelCategoryId, OrderByComparator orderByComparator)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        ModelGlobalPreference modelGlobalPreference = fetchByModelCategoryId_First(modelCategoryId,
                orderByComparator);

        if (modelGlobalPreference != null) {
            return modelGlobalPreference;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("modelCategoryId=");
        msg.append(modelCategoryId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchModelGlobalPreferenceException(msg.toString());
    }

    /**
     * Returns the first model global preference in the ordered set where modelCategoryId = &#63;.
     *
     * @param modelCategoryId the model category ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching model global preference, or <code>null</code> if a matching model global preference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference fetchByModelCategoryId_First(
        long modelCategoryId, OrderByComparator orderByComparator)
        throws SystemException {
        List<ModelGlobalPreference> list = findByModelCategoryId(modelCategoryId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last model global preference in the ordered set where modelCategoryId = &#63;.
     *
     * @param modelCategoryId the model category ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching model global preference
     * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a matching model global preference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference findByModelCategoryId_Last(
        long modelCategoryId, OrderByComparator orderByComparator)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        ModelGlobalPreference modelGlobalPreference = fetchByModelCategoryId_Last(modelCategoryId,
                orderByComparator);

        if (modelGlobalPreference != null) {
            return modelGlobalPreference;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("modelCategoryId=");
        msg.append(modelCategoryId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchModelGlobalPreferenceException(msg.toString());
    }

    /**
     * Returns the last model global preference in the ordered set where modelCategoryId = &#63;.
     *
     * @param modelCategoryId the model category ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching model global preference, or <code>null</code> if a matching model global preference could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference fetchByModelCategoryId_Last(
        long modelCategoryId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByModelCategoryId(modelCategoryId);

        if (count == 0) {
            return null;
        }

        List<ModelGlobalPreference> list = findByModelCategoryId(modelCategoryId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the model global preferences before and after the current model global preference in the ordered set where modelCategoryId = &#63;.
     *
     * @param modelGlobalPreferencePK the primary key of the current model global preference
     * @param modelCategoryId the model category ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next model global preference
     * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference[] findByModelCategoryId_PrevAndNext(
        long modelGlobalPreferencePK, long modelCategoryId,
        OrderByComparator orderByComparator)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        ModelGlobalPreference modelGlobalPreference = findByPrimaryKey(modelGlobalPreferencePK);

        Session session = null;

        try {
            session = openSession();

            ModelGlobalPreference[] array = new ModelGlobalPreferenceImpl[3];

            array[0] = getByModelCategoryId_PrevAndNext(session,
                    modelGlobalPreference, modelCategoryId, orderByComparator,
                    true);

            array[1] = modelGlobalPreference;

            array[2] = getByModelCategoryId_PrevAndNext(session,
                    modelGlobalPreference, modelCategoryId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ModelGlobalPreference getByModelCategoryId_PrevAndNext(
        Session session, ModelGlobalPreference modelGlobalPreference,
        long modelCategoryId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MODELGLOBALPREFERENCE_WHERE);

        query.append(_FINDER_COLUMN_MODELCATEGORYID_MODELCATEGORYID_2);

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
        } else {
            query.append(ModelGlobalPreferenceModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(modelCategoryId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(modelGlobalPreference);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ModelGlobalPreference> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the model global preferences where modelCategoryId = &#63; from the database.
     *
     * @param modelCategoryId the model category ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByModelCategoryId(long modelCategoryId)
        throws SystemException {
        for (ModelGlobalPreference modelGlobalPreference : findByModelCategoryId(
                modelCategoryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(modelGlobalPreference);
        }
    }

    /**
     * Returns the number of model global preferences where modelCategoryId = &#63;.
     *
     * @param modelCategoryId the model category ID
     * @return the number of matching model global preferences
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByModelCategoryId(long modelCategoryId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MODELCATEGORYID;

        Object[] finderArgs = new Object[] { modelCategoryId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODELGLOBALPREFERENCE_WHERE);

            query.append(_FINDER_COLUMN_MODELCATEGORYID_MODELCATEGORYID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelCategoryId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the model global preference in the entity cache if it is enabled.
     *
     * @param modelGlobalPreference the model global preference
     */
    @Override
    public void cacheResult(ModelGlobalPreference modelGlobalPreference) {
        EntityCacheUtil.putResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class,
            modelGlobalPreference.getPrimaryKey(), modelGlobalPreference);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELID,
            new Object[] { modelGlobalPreference.getModelId() },
            modelGlobalPreference);

        modelGlobalPreference.resetOriginalValues();
    }

    /**
     * Caches the model global preferences in the entity cache if it is enabled.
     *
     * @param modelGlobalPreferences the model global preferences
     */
    @Override
    public void cacheResult(List<ModelGlobalPreference> modelGlobalPreferences) {
        for (ModelGlobalPreference modelGlobalPreference : modelGlobalPreferences) {
            if (EntityCacheUtil.getResult(
                        ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
                        ModelGlobalPreferenceImpl.class,
                        modelGlobalPreference.getPrimaryKey()) == null) {
                cacheResult(modelGlobalPreference);
            } else {
                modelGlobalPreference.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all model global preferences.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ModelGlobalPreferenceImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ModelGlobalPreferenceImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the model global preference.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ModelGlobalPreference modelGlobalPreference) {
        EntityCacheUtil.removeResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class,
            modelGlobalPreference.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(modelGlobalPreference);
    }

    @Override
    public void clearCache(List<ModelGlobalPreference> modelGlobalPreferences) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ModelGlobalPreference modelGlobalPreference : modelGlobalPreferences) {
            EntityCacheUtil.removeResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
                ModelGlobalPreferenceImpl.class,
                modelGlobalPreference.getPrimaryKey());

            clearUniqueFindersCache(modelGlobalPreference);
        }
    }

    protected void cacheUniqueFindersCache(
        ModelGlobalPreference modelGlobalPreference) {
        if (modelGlobalPreference.isNew()) {
            Object[] args = new Object[] { modelGlobalPreference.getModelId() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELID, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELID, args,
                modelGlobalPreference);
        } else {
            ModelGlobalPreferenceModelImpl modelGlobalPreferenceModelImpl = (ModelGlobalPreferenceModelImpl) modelGlobalPreference;

            if ((modelGlobalPreferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_MODELID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { modelGlobalPreference.getModelId() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELID, args,
                    Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELID, args,
                    modelGlobalPreference);
            }
        }
    }

    protected void clearUniqueFindersCache(
        ModelGlobalPreference modelGlobalPreference) {
        ModelGlobalPreferenceModelImpl modelGlobalPreferenceModelImpl = (ModelGlobalPreferenceModelImpl) modelGlobalPreference;

        Object[] args = new Object[] { modelGlobalPreference.getModelId() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELID, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELID, args);

        if ((modelGlobalPreferenceModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_MODELID.getColumnBitmask()) != 0) {
            args = new Object[] {
                    modelGlobalPreferenceModelImpl.getOriginalModelId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELID, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELID, args);
        }
    }

    /**
     * Creates a new model global preference with the primary key. Does not add the model global preference to the database.
     *
     * @param modelGlobalPreferencePK the primary key for the new model global preference
     * @return the new model global preference
     */
    @Override
    public ModelGlobalPreference create(long modelGlobalPreferencePK) {
        ModelGlobalPreference modelGlobalPreference = new ModelGlobalPreferenceImpl();

        modelGlobalPreference.setNew(true);
        modelGlobalPreference.setPrimaryKey(modelGlobalPreferencePK);

        return modelGlobalPreference;
    }

    /**
     * Removes the model global preference with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param modelGlobalPreferencePK the primary key of the model global preference
     * @return the model global preference that was removed
     * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference remove(long modelGlobalPreferencePK)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        return remove((Serializable) modelGlobalPreferencePK);
    }

    /**
     * Removes the model global preference with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the model global preference
     * @return the model global preference that was removed
     * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference remove(Serializable primaryKey)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelGlobalPreference modelGlobalPreference = (ModelGlobalPreference) session.get(ModelGlobalPreferenceImpl.class,
                    primaryKey);

            if (modelGlobalPreference == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchModelGlobalPreferenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(modelGlobalPreference);
        } catch (NoSuchModelGlobalPreferenceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ModelGlobalPreference removeImpl(
        ModelGlobalPreference modelGlobalPreference) throws SystemException {
        modelGlobalPreference = toUnwrappedModel(modelGlobalPreference);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(modelGlobalPreference)) {
                modelGlobalPreference = (ModelGlobalPreference) session.get(ModelGlobalPreferenceImpl.class,
                        modelGlobalPreference.getPrimaryKeyObj());
            }

            if (modelGlobalPreference != null) {
                session.delete(modelGlobalPreference);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (modelGlobalPreference != null) {
            clearCache(modelGlobalPreference);
        }

        return modelGlobalPreference;
    }

    @Override
    public ModelGlobalPreference updateImpl(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference)
        throws SystemException {
        modelGlobalPreference = toUnwrappedModel(modelGlobalPreference);

        boolean isNew = modelGlobalPreference.isNew();

        ModelGlobalPreferenceModelImpl modelGlobalPreferenceModelImpl = (ModelGlobalPreferenceModelImpl) modelGlobalPreference;

        Session session = null;

        try {
            session = openSession();

            if (modelGlobalPreference.isNew()) {
                session.save(modelGlobalPreference);

                modelGlobalPreference.setNew(false);
            } else {
                session.merge(modelGlobalPreference);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ModelGlobalPreferenceModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((modelGlobalPreferenceModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELCATEGORYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        modelGlobalPreferenceModelImpl.getOriginalModelCategoryId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELCATEGORYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELCATEGORYID,
                    args);

                args = new Object[] {
                        modelGlobalPreferenceModelImpl.getModelCategoryId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELCATEGORYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELCATEGORYID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
            ModelGlobalPreferenceImpl.class,
            modelGlobalPreference.getPrimaryKey(), modelGlobalPreference);

        clearUniqueFindersCache(modelGlobalPreference);
        cacheUniqueFindersCache(modelGlobalPreference);

        return modelGlobalPreference;
    }

    protected ModelGlobalPreference toUnwrappedModel(
        ModelGlobalPreference modelGlobalPreference) {
        if (modelGlobalPreference instanceof ModelGlobalPreferenceImpl) {
            return modelGlobalPreference;
        }

        ModelGlobalPreferenceImpl modelGlobalPreferenceImpl = new ModelGlobalPreferenceImpl();

        modelGlobalPreferenceImpl.setNew(modelGlobalPreference.isNew());
        modelGlobalPreferenceImpl.setPrimaryKey(modelGlobalPreference.getPrimaryKey());

        modelGlobalPreferenceImpl.setModelGlobalPreferencePK(modelGlobalPreference.getModelGlobalPreferencePK());
        modelGlobalPreferenceImpl.setModelId(modelGlobalPreference.getModelId());
        modelGlobalPreferenceImpl.setVisible(modelGlobalPreference.isVisible());
        modelGlobalPreferenceImpl.setWeight(modelGlobalPreference.getWeight());
        modelGlobalPreferenceImpl.setExpertEvaluationPageId(modelGlobalPreference.getExpertEvaluationPageId());
        modelGlobalPreferenceImpl.setModelCategoryId(modelGlobalPreference.getModelCategoryId());

        return modelGlobalPreferenceImpl;
    }

    /**
     * Returns the model global preference with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the model global preference
     * @return the model global preference
     * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        ModelGlobalPreference modelGlobalPreference = fetchByPrimaryKey(primaryKey);

        if (modelGlobalPreference == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchModelGlobalPreferenceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return modelGlobalPreference;
    }

    /**
     * Returns the model global preference with the primary key or throws a {@link com.ext.portlet.NoSuchModelGlobalPreferenceException} if it could not be found.
     *
     * @param modelGlobalPreferencePK the primary key of the model global preference
     * @return the model global preference
     * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference findByPrimaryKey(long modelGlobalPreferencePK)
        throws NoSuchModelGlobalPreferenceException, SystemException {
        return findByPrimaryKey((Serializable) modelGlobalPreferencePK);
    }

    /**
     * Returns the model global preference with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the model global preference
     * @return the model global preference, or <code>null</code> if a model global preference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ModelGlobalPreference modelGlobalPreference = (ModelGlobalPreference) EntityCacheUtil.getResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
                ModelGlobalPreferenceImpl.class, primaryKey);

        if (modelGlobalPreference == _nullModelGlobalPreference) {
            return null;
        }

        if (modelGlobalPreference == null) {
            Session session = null;

            try {
                session = openSession();

                modelGlobalPreference = (ModelGlobalPreference) session.get(ModelGlobalPreferenceImpl.class,
                        primaryKey);

                if (modelGlobalPreference != null) {
                    cacheResult(modelGlobalPreference);
                } else {
                    EntityCacheUtil.putResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
                        ModelGlobalPreferenceImpl.class, primaryKey,
                        _nullModelGlobalPreference);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ModelGlobalPreferenceModelImpl.ENTITY_CACHE_ENABLED,
                    ModelGlobalPreferenceImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return modelGlobalPreference;
    }

    /**
     * Returns the model global preference with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param modelGlobalPreferencePK the primary key of the model global preference
     * @return the model global preference, or <code>null</code> if a model global preference with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelGlobalPreference fetchByPrimaryKey(long modelGlobalPreferencePK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) modelGlobalPreferencePK);
    }

    /**
     * Returns all the model global preferences.
     *
     * @return the model global preferences
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelGlobalPreference> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model global preferences.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelGlobalPreferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of model global preferences
     * @param end the upper bound of the range of model global preferences (not inclusive)
     * @return the range of model global preferences
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelGlobalPreference> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the model global preferences.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelGlobalPreferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of model global preferences
     * @param end the upper bound of the range of model global preferences (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of model global preferences
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelGlobalPreference> findAll(int start, int end,
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

        List<ModelGlobalPreference> list = (List<ModelGlobalPreference>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MODELGLOBALPREFERENCE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MODELGLOBALPREFERENCE;

                if (pagination) {
                    sql = sql.concat(ModelGlobalPreferenceModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ModelGlobalPreference>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ModelGlobalPreference>(list);
                } else {
                    list = (List<ModelGlobalPreference>) QueryUtil.list(q,
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
     * Removes all the model global preferences from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ModelGlobalPreference modelGlobalPreference : findAll()) {
            remove(modelGlobalPreference);
        }
    }

    /**
     * Returns the number of model global preferences.
     *
     * @return the number of model global preferences
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

                Query q = session.createQuery(_SQL_COUNT_MODELGLOBALPREFERENCE);

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
     * Initializes the model global preference persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ModelGlobalPreference")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelGlobalPreference>> listenersList = new ArrayList<ModelListener<ModelGlobalPreference>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelGlobalPreference>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ModelGlobalPreferenceImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
