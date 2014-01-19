package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanTypeAttributeException;
import com.ext.portlet.model.PlanTypeAttribute;
import com.ext.portlet.model.impl.PlanTypeAttributeImpl;
import com.ext.portlet.model.impl.PlanTypeAttributeModelImpl;
import com.ext.portlet.service.persistence.PlanTypeAttributePersistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the plan type attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeAttributePersistence
 * @see PlanTypeAttributeUtil
 * @generated
 */
public class PlanTypeAttributePersistenceImpl extends BasePersistenceImpl<PlanTypeAttribute>
    implements PlanTypeAttributePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanTypeAttributeUtil} to access the plan type attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTypeAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            PlanTypeAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            PlanTypeAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByPlanTypeIdAttributeName",
            new String[] { Long.class.getName(), String.class.getName() },
            PlanTypeAttributeModelImpl.PLANTYPEID_COLUMN_BITMASK |
            PlanTypeAttributeModelImpl.ATTRIBUTENAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANTYPEIDATTRIBUTENAME = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByPlanTypeIdAttributeName",
            new String[] { Long.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_PLANTYPEID_2 =
        "planTypeAttribute.planTypeId = ? AND ";
    private static final String _FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_1 =
        "planTypeAttribute.attributeName IS NULL";
    private static final String _FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_2 =
        "planTypeAttribute.attributeName = ?";
    private static final String _FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_3 =
        "(planTypeAttribute.attributeName IS NULL OR planTypeAttribute.attributeName = '')";
    private static final String _SQL_SELECT_PLANTYPEATTRIBUTE = "SELECT planTypeAttribute FROM PlanTypeAttribute planTypeAttribute";
    private static final String _SQL_SELECT_PLANTYPEATTRIBUTE_WHERE = "SELECT planTypeAttribute FROM PlanTypeAttribute planTypeAttribute WHERE ";
    private static final String _SQL_COUNT_PLANTYPEATTRIBUTE = "SELECT COUNT(planTypeAttribute) FROM PlanTypeAttribute planTypeAttribute";
    private static final String _SQL_COUNT_PLANTYPEATTRIBUTE_WHERE = "SELECT COUNT(planTypeAttribute) FROM PlanTypeAttribute planTypeAttribute WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planTypeAttribute.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanTypeAttribute exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanTypeAttribute exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanTypeAttributePersistenceImpl.class);
    private static PlanTypeAttribute _nullPlanTypeAttribute = new PlanTypeAttributeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanTypeAttribute> toCacheModel() {
                return _nullPlanTypeAttributeCacheModel;
            }
        };

    private static CacheModel<PlanTypeAttribute> _nullPlanTypeAttributeCacheModel =
        new CacheModel<PlanTypeAttribute>() {
            @Override
            public PlanTypeAttribute toEntityModel() {
                return _nullPlanTypeAttribute;
            }
        };

    public PlanTypeAttributePersistenceImpl() {
        setModelClass(PlanTypeAttribute.class);
    }

    /**
     * Returns the plan type attribute where planTypeId = &#63; and attributeName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanTypeAttributeException} if it could not be found.
     *
     * @param planTypeId the plan type ID
     * @param attributeName the attribute name
     * @return the matching plan type attribute
     * @throws com.ext.portlet.NoSuchPlanTypeAttributeException if a matching plan type attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute findByPlanTypeIdAttributeName(long planTypeId,
        String attributeName)
        throws NoSuchPlanTypeAttributeException, SystemException {
        PlanTypeAttribute planTypeAttribute = fetchByPlanTypeIdAttributeName(planTypeId,
                attributeName);

        if (planTypeAttribute == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planTypeId=");
            msg.append(planTypeId);

            msg.append(", attributeName=");
            msg.append(attributeName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanTypeAttributeException(msg.toString());
        }

        return planTypeAttribute;
    }

    /**
     * Returns the plan type attribute where planTypeId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planTypeId the plan type ID
     * @param attributeName the attribute name
     * @return the matching plan type attribute, or <code>null</code> if a matching plan type attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute fetchByPlanTypeIdAttributeName(long planTypeId,
        String attributeName) throws SystemException {
        return fetchByPlanTypeIdAttributeName(planTypeId, attributeName, true);
    }

    /**
     * Returns the plan type attribute where planTypeId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planTypeId the plan type ID
     * @param attributeName the attribute name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan type attribute, or <code>null</code> if a matching plan type attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute fetchByPlanTypeIdAttributeName(long planTypeId,
        String attributeName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planTypeId, attributeName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                    finderArgs, this);
        }

        if (result instanceof PlanTypeAttribute) {
            PlanTypeAttribute planTypeAttribute = (PlanTypeAttribute) result;

            if ((planTypeId != planTypeAttribute.getPlanTypeId()) ||
                    !Validator.equals(attributeName,
                        planTypeAttribute.getAttributeName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PLANTYPEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_PLANTYPEID_2);

            boolean bindAttributeName = false;

            if (attributeName == null) {
                query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_1);
            } else if (attributeName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_3);
            } else {
                bindAttributeName = true;

                query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planTypeId);

                if (bindAttributeName) {
                    qPos.add(attributeName);
                }

                List<PlanTypeAttribute> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "PlanTypeAttributePersistenceImpl.fetchByPlanTypeIdAttributeName(long, String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    PlanTypeAttribute planTypeAttribute = list.get(0);

                    result = planTypeAttribute;

                    cacheResult(planTypeAttribute);

                    if ((planTypeAttribute.getPlanTypeId() != planTypeId) ||
                            (planTypeAttribute.getAttributeName() == null) ||
                            !planTypeAttribute.getAttributeName()
                                                  .equals(attributeName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                            finderArgs, planTypeAttribute);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (PlanTypeAttribute) result;
        }
    }

    /**
     * Removes the plan type attribute where planTypeId = &#63; and attributeName = &#63; from the database.
     *
     * @param planTypeId the plan type ID
     * @param attributeName the attribute name
     * @return the plan type attribute that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute removeByPlanTypeIdAttributeName(long planTypeId,
        String attributeName)
        throws NoSuchPlanTypeAttributeException, SystemException {
        PlanTypeAttribute planTypeAttribute = findByPlanTypeIdAttributeName(planTypeId,
                attributeName);

        return remove(planTypeAttribute);
    }

    /**
     * Returns the number of plan type attributes where planTypeId = &#63; and attributeName = &#63;.
     *
     * @param planTypeId the plan type ID
     * @param attributeName the attribute name
     * @return the number of matching plan type attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPlanTypeIdAttributeName(long planTypeId,
        String attributeName) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PLANTYPEIDATTRIBUTENAME;

        Object[] finderArgs = new Object[] { planTypeId, attributeName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANTYPEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_PLANTYPEID_2);

            boolean bindAttributeName = false;

            if (attributeName == null) {
                query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_1);
            } else if (attributeName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_3);
            } else {
                bindAttributeName = true;

                query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planTypeId);

                if (bindAttributeName) {
                    qPos.add(attributeName);
                }

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
     * Caches the plan type attribute in the entity cache if it is enabled.
     *
     * @param planTypeAttribute the plan type attribute
     */
    @Override
    public void cacheResult(PlanTypeAttribute planTypeAttribute) {
        EntityCacheUtil.putResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey(),
            planTypeAttribute);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
            new Object[] {
                planTypeAttribute.getPlanTypeId(),
                planTypeAttribute.getAttributeName()
            }, planTypeAttribute);

        planTypeAttribute.resetOriginalValues();
    }

    /**
     * Caches the plan type attributes in the entity cache if it is enabled.
     *
     * @param planTypeAttributes the plan type attributes
     */
    @Override
    public void cacheResult(List<PlanTypeAttribute> planTypeAttributes) {
        for (PlanTypeAttribute planTypeAttribute : planTypeAttributes) {
            if (EntityCacheUtil.getResult(
                        PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeAttributeImpl.class,
                        planTypeAttribute.getPrimaryKey()) == null) {
                cacheResult(planTypeAttribute);
            } else {
                planTypeAttribute.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan type attributes.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanTypeAttributeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanTypeAttributeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan type attribute.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanTypeAttribute planTypeAttribute) {
        EntityCacheUtil.removeResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planTypeAttribute);
    }

    @Override
    public void clearCache(List<PlanTypeAttribute> planTypeAttributes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanTypeAttribute planTypeAttribute : planTypeAttributes) {
            EntityCacheUtil.removeResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey());

            clearUniqueFindersCache(planTypeAttribute);
        }
    }

    protected void cacheUniqueFindersCache(PlanTypeAttribute planTypeAttribute) {
        if (planTypeAttribute.isNew()) {
            Object[] args = new Object[] {
                    planTypeAttribute.getPlanTypeId(),
                    planTypeAttribute.getAttributeName()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANTYPEIDATTRIBUTENAME,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                args, planTypeAttribute);
        } else {
            PlanTypeAttributeModelImpl planTypeAttributeModelImpl = (PlanTypeAttributeModelImpl) planTypeAttribute;

            if ((planTypeAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planTypeAttribute.getPlanTypeId(),
                        planTypeAttribute.getAttributeName()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANTYPEIDATTRIBUTENAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                    args, planTypeAttribute);
            }
        }
    }

    protected void clearUniqueFindersCache(PlanTypeAttribute planTypeAttribute) {
        PlanTypeAttributeModelImpl planTypeAttributeModelImpl = (PlanTypeAttributeModelImpl) planTypeAttribute;

        Object[] args = new Object[] {
                planTypeAttribute.getPlanTypeId(),
                planTypeAttribute.getAttributeName()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANTYPEIDATTRIBUTENAME,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
            args);

        if ((planTypeAttributeModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME.getColumnBitmask()) != 0) {
            args = new Object[] {
                    planTypeAttributeModelImpl.getOriginalPlanTypeId(),
                    planTypeAttributeModelImpl.getOriginalAttributeName()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANTYPEIDATTRIBUTENAME,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                args);
        }
    }

    /**
     * Creates a new plan type attribute with the primary key. Does not add the plan type attribute to the database.
     *
     * @param planTypeAttributeId the primary key for the new plan type attribute
     * @return the new plan type attribute
     */
    @Override
    public PlanTypeAttribute create(long planTypeAttributeId) {
        PlanTypeAttribute planTypeAttribute = new PlanTypeAttributeImpl();

        planTypeAttribute.setNew(true);
        planTypeAttribute.setPrimaryKey(planTypeAttributeId);

        return planTypeAttribute;
    }

    /**
     * Removes the plan type attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planTypeAttributeId the primary key of the plan type attribute
     * @return the plan type attribute that was removed
     * @throws com.ext.portlet.NoSuchPlanTypeAttributeException if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute remove(long planTypeAttributeId)
        throws NoSuchPlanTypeAttributeException, SystemException {
        return remove((Serializable) planTypeAttributeId);
    }

    /**
     * Removes the plan type attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan type attribute
     * @return the plan type attribute that was removed
     * @throws com.ext.portlet.NoSuchPlanTypeAttributeException if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute remove(Serializable primaryKey)
        throws NoSuchPlanTypeAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTypeAttribute planTypeAttribute = (PlanTypeAttribute) session.get(PlanTypeAttributeImpl.class,
                    primaryKey);

            if (planTypeAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanTypeAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planTypeAttribute);
        } catch (NoSuchPlanTypeAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanTypeAttribute removeImpl(PlanTypeAttribute planTypeAttribute)
        throws SystemException {
        planTypeAttribute = toUnwrappedModel(planTypeAttribute);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planTypeAttribute)) {
                planTypeAttribute = (PlanTypeAttribute) session.get(PlanTypeAttributeImpl.class,
                        planTypeAttribute.getPrimaryKeyObj());
            }

            if (planTypeAttribute != null) {
                session.delete(planTypeAttribute);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planTypeAttribute != null) {
            clearCache(planTypeAttribute);
        }

        return planTypeAttribute;
    }

    @Override
    public PlanTypeAttribute updateImpl(
        com.ext.portlet.model.PlanTypeAttribute planTypeAttribute)
        throws SystemException {
        planTypeAttribute = toUnwrappedModel(planTypeAttribute);

        boolean isNew = planTypeAttribute.isNew();

        Session session = null;

        try {
            session = openSession();

            if (planTypeAttribute.isNew()) {
                session.save(planTypeAttribute);

                planTypeAttribute.setNew(false);
            } else {
                session.merge(planTypeAttribute);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanTypeAttributeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey(),
            planTypeAttribute);

        clearUniqueFindersCache(planTypeAttribute);
        cacheUniqueFindersCache(planTypeAttribute);

        return planTypeAttribute;
    }

    protected PlanTypeAttribute toUnwrappedModel(
        PlanTypeAttribute planTypeAttribute) {
        if (planTypeAttribute instanceof PlanTypeAttributeImpl) {
            return planTypeAttribute;
        }

        PlanTypeAttributeImpl planTypeAttributeImpl = new PlanTypeAttributeImpl();

        planTypeAttributeImpl.setNew(planTypeAttribute.isNew());
        planTypeAttributeImpl.setPrimaryKey(planTypeAttribute.getPrimaryKey());

        planTypeAttributeImpl.setPlanTypeAttributeId(planTypeAttribute.getPlanTypeAttributeId());
        planTypeAttributeImpl.setPlanTypeId(planTypeAttribute.getPlanTypeId());
        planTypeAttributeImpl.setAttributeName(planTypeAttribute.getAttributeName());

        return planTypeAttributeImpl;
    }

    /**
     * Returns the plan type attribute with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan type attribute
     * @return the plan type attribute
     * @throws com.ext.portlet.NoSuchPlanTypeAttributeException if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanTypeAttributeException, SystemException {
        PlanTypeAttribute planTypeAttribute = fetchByPrimaryKey(primaryKey);

        if (planTypeAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanTypeAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planTypeAttribute;
    }

    /**
     * Returns the plan type attribute with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTypeAttributeException} if it could not be found.
     *
     * @param planTypeAttributeId the primary key of the plan type attribute
     * @return the plan type attribute
     * @throws com.ext.portlet.NoSuchPlanTypeAttributeException if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute findByPrimaryKey(long planTypeAttributeId)
        throws NoSuchPlanTypeAttributeException, SystemException {
        return findByPrimaryKey((Serializable) planTypeAttributeId);
    }

    /**
     * Returns the plan type attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan type attribute
     * @return the plan type attribute, or <code>null</code> if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanTypeAttribute planTypeAttribute = (PlanTypeAttribute) EntityCacheUtil.getResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeAttributeImpl.class, primaryKey);

        if (planTypeAttribute == _nullPlanTypeAttribute) {
            return null;
        }

        if (planTypeAttribute == null) {
            Session session = null;

            try {
                session = openSession();

                planTypeAttribute = (PlanTypeAttribute) session.get(PlanTypeAttributeImpl.class,
                        primaryKey);

                if (planTypeAttribute != null) {
                    cacheResult(planTypeAttribute);
                } else {
                    EntityCacheUtil.putResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeAttributeImpl.class, primaryKey,
                        _nullPlanTypeAttribute);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                    PlanTypeAttributeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planTypeAttribute;
    }

    /**
     * Returns the plan type attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planTypeAttributeId the primary key of the plan type attribute
     * @return the plan type attribute, or <code>null</code> if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute fetchByPrimaryKey(long planTypeAttributeId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) planTypeAttributeId);
    }

    /**
     * Returns all the plan type attributes.
     *
     * @return the plan type attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanTypeAttribute> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan type attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTypeAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan type attributes
     * @param end the upper bound of the range of plan type attributes (not inclusive)
     * @return the range of plan type attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanTypeAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan type attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTypeAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan type attributes
     * @param end the upper bound of the range of plan type attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan type attributes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanTypeAttribute> findAll(int start, int end,
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

        List<PlanTypeAttribute> list = (List<PlanTypeAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANTYPEATTRIBUTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANTYPEATTRIBUTE;

                if (pagination) {
                    sql = sql.concat(PlanTypeAttributeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanTypeAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanTypeAttribute>(list);
                } else {
                    list = (List<PlanTypeAttribute>) QueryUtil.list(q,
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
     * Removes all the plan type attributes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanTypeAttribute planTypeAttribute : findAll()) {
            remove(planTypeAttribute);
        }
    }

    /**
     * Returns the number of plan type attributes.
     *
     * @return the number of plan type attributes
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

                Query q = session.createQuery(_SQL_COUNT_PLANTYPEATTRIBUTE);

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
     * Initializes the plan type attribute persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanTypeAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTypeAttribute>> listenersList = new ArrayList<ModelListener<PlanTypeAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTypeAttribute>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanTypeAttributeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
