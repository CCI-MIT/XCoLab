package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanColumnSettingsException;
import com.ext.portlet.model.PlanColumnSettings;
import com.ext.portlet.model.impl.PlanColumnSettingsImpl;
import com.ext.portlet.model.impl.PlanColumnSettingsModelImpl;
import com.ext.portlet.service.persistence.PlanColumnSettingsPersistence;

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
 * The persistence implementation for the plan column settings service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettingsPersistence
 * @see PlanColumnSettingsUtil
 * @generated
 */
public class PlanColumnSettingsPersistenceImpl extends BasePersistenceImpl<PlanColumnSettings>
    implements PlanColumnSettingsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanColumnSettingsUtil} to access the plan column settings persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanColumnSettingsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            PlanColumnSettingsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            PlanColumnSettingsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME =
        new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByPlanUserSettingsIdColumnName",
            new String[] { Long.class.getName(), String.class.getName() },
            PlanColumnSettingsModelImpl.PLANUSERSETTINGSID_COLUMN_BITMASK |
            PlanColumnSettingsModelImpl.COLUMNNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME =
        new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByPlanUserSettingsIdColumnName",
            new String[] { Long.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_PLANUSERSETTINGSID_2 =
        "planColumnSettings.planUserSettingsId = ? AND ";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_1 =
        "planColumnSettings.columnName IS NULL";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_2 =
        "planColumnSettings.columnName = ?";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_3 =
        "(planColumnSettings.columnName IS NULL OR planColumnSettings.columnName = '')";
    private static final String _SQL_SELECT_PLANCOLUMNSETTINGS = "SELECT planColumnSettings FROM PlanColumnSettings planColumnSettings";
    private static final String _SQL_SELECT_PLANCOLUMNSETTINGS_WHERE = "SELECT planColumnSettings FROM PlanColumnSettings planColumnSettings WHERE ";
    private static final String _SQL_COUNT_PLANCOLUMNSETTINGS = "SELECT COUNT(planColumnSettings) FROM PlanColumnSettings planColumnSettings";
    private static final String _SQL_COUNT_PLANCOLUMNSETTINGS_WHERE = "SELECT COUNT(planColumnSettings) FROM PlanColumnSettings planColumnSettings WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planColumnSettings.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanColumnSettings exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanColumnSettings exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanColumnSettingsPersistenceImpl.class);
    private static PlanColumnSettings _nullPlanColumnSettings = new PlanColumnSettingsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanColumnSettings> toCacheModel() {
                return _nullPlanColumnSettingsCacheModel;
            }
        };

    private static CacheModel<PlanColumnSettings> _nullPlanColumnSettingsCacheModel =
        new CacheModel<PlanColumnSettings>() {
            @Override
            public PlanColumnSettings toEntityModel() {
                return _nullPlanColumnSettings;
            }
        };

    public PlanColumnSettingsPersistenceImpl() {
        setModelClass(PlanColumnSettings.class);
    }

    /**
     * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanColumnSettingsException} if it could not be found.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param columnName the column name
     * @return the matching plan column settings
     * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a matching plan column settings could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings findByPlanUserSettingsIdColumnName(
        long planUserSettingsId, String columnName)
        throws NoSuchPlanColumnSettingsException, SystemException {
        PlanColumnSettings planColumnSettings = fetchByPlanUserSettingsIdColumnName(planUserSettingsId,
                columnName);

        if (planColumnSettings == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planUserSettingsId=");
            msg.append(planUserSettingsId);

            msg.append(", columnName=");
            msg.append(columnName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanColumnSettingsException(msg.toString());
        }

        return planColumnSettings;
    }

    /**
     * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param columnName the column name
     * @return the matching plan column settings, or <code>null</code> if a matching plan column settings could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        long planUserSettingsId, String columnName) throws SystemException {
        return fetchByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName, true);
    }

    /**
     * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param columnName the column name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan column settings, or <code>null</code> if a matching plan column settings could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        long planUserSettingsId, String columnName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, columnName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                    finderArgs, this);
        }

        if (result instanceof PlanColumnSettings) {
            PlanColumnSettings planColumnSettings = (PlanColumnSettings) result;

            if ((planUserSettingsId != planColumnSettings.getPlanUserSettingsId()) ||
                    !Validator.equals(columnName,
                        planColumnSettings.getColumnName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PLANCOLUMNSETTINGS_WHERE);

            query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_PLANUSERSETTINGSID_2);

            boolean bindColumnName = false;

            if (columnName == null) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_1);
            } else if (columnName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_3);
            } else {
                bindColumnName = true;

                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planUserSettingsId);

                if (bindColumnName) {
                    qPos.add(columnName);
                }

                List<PlanColumnSettings> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "PlanColumnSettingsPersistenceImpl.fetchByPlanUserSettingsIdColumnName(long, String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    PlanColumnSettings planColumnSettings = list.get(0);

                    result = planColumnSettings;

                    cacheResult(planColumnSettings);

                    if ((planColumnSettings.getPlanUserSettingsId() != planUserSettingsId) ||
                            (planColumnSettings.getColumnName() == null) ||
                            !planColumnSettings.getColumnName()
                                                   .equals(columnName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                            finderArgs, planColumnSettings);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (PlanColumnSettings) result;
        }
    }

    /**
     * Removes the plan column settings where planUserSettingsId = &#63; and columnName = &#63; from the database.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param columnName the column name
     * @return the plan column settings that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings removeByPlanUserSettingsIdColumnName(
        long planUserSettingsId, String columnName)
        throws NoSuchPlanColumnSettingsException, SystemException {
        PlanColumnSettings planColumnSettings = findByPlanUserSettingsIdColumnName(planUserSettingsId,
                columnName);

        return remove(planColumnSettings);
    }

    /**
     * Returns the number of plan column settingses where planUserSettingsId = &#63; and columnName = &#63;.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param columnName the column name
     * @return the number of matching plan column settingses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPlanUserSettingsIdColumnName(long planUserSettingsId,
        String columnName) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME;

        Object[] finderArgs = new Object[] { planUserSettingsId, columnName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANCOLUMNSETTINGS_WHERE);

            query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_PLANUSERSETTINGSID_2);

            boolean bindColumnName = false;

            if (columnName == null) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_1);
            } else if (columnName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_3);
            } else {
                bindColumnName = true;

                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planUserSettingsId);

                if (bindColumnName) {
                    qPos.add(columnName);
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
     * Caches the plan column settings in the entity cache if it is enabled.
     *
     * @param planColumnSettings the plan column settings
     */
    @Override
    public void cacheResult(PlanColumnSettings planColumnSettings) {
        EntityCacheUtil.putResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey(),
            planColumnSettings);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
            new Object[] {
                planColumnSettings.getPlanUserSettingsId(),
                planColumnSettings.getColumnName()
            }, planColumnSettings);

        planColumnSettings.resetOriginalValues();
    }

    /**
     * Caches the plan column settingses in the entity cache if it is enabled.
     *
     * @param planColumnSettingses the plan column settingses
     */
    @Override
    public void cacheResult(List<PlanColumnSettings> planColumnSettingses) {
        for (PlanColumnSettings planColumnSettings : planColumnSettingses) {
            if (EntityCacheUtil.getResult(
                        PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                        PlanColumnSettingsImpl.class,
                        planColumnSettings.getPrimaryKey()) == null) {
                cacheResult(planColumnSettings);
            } else {
                planColumnSettings.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan column settingses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanColumnSettingsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanColumnSettingsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan column settings.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanColumnSettings planColumnSettings) {
        EntityCacheUtil.removeResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planColumnSettings);
    }

    @Override
    public void clearCache(List<PlanColumnSettings> planColumnSettingses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanColumnSettings planColumnSettings : planColumnSettingses) {
            EntityCacheUtil.removeResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey());

            clearUniqueFindersCache(planColumnSettings);
        }
    }

    protected void cacheUniqueFindersCache(
        PlanColumnSettings planColumnSettings) {
        if (planColumnSettings.isNew()) {
            Object[] args = new Object[] {
                    planColumnSettings.getPlanUserSettingsId(),
                    planColumnSettings.getColumnName()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                args, planColumnSettings);
        } else {
            PlanColumnSettingsModelImpl planColumnSettingsModelImpl = (PlanColumnSettingsModelImpl) planColumnSettings;

            if ((planColumnSettingsModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planColumnSettings.getPlanUserSettingsId(),
                        planColumnSettings.getColumnName()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                    args, planColumnSettings);
            }
        }
    }

    protected void clearUniqueFindersCache(
        PlanColumnSettings planColumnSettings) {
        PlanColumnSettingsModelImpl planColumnSettingsModelImpl = (PlanColumnSettingsModelImpl) planColumnSettings;

        Object[] args = new Object[] {
                planColumnSettings.getPlanUserSettingsId(),
                planColumnSettings.getColumnName()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
            args);

        if ((planColumnSettingsModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME.getColumnBitmask()) != 0) {
            args = new Object[] {
                    planColumnSettingsModelImpl.getOriginalPlanUserSettingsId(),
                    planColumnSettingsModelImpl.getOriginalColumnName()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                args);
        }
    }

    /**
     * Creates a new plan column settings with the primary key. Does not add the plan column settings to the database.
     *
     * @param planColumnSettingsId the primary key for the new plan column settings
     * @return the new plan column settings
     */
    @Override
    public PlanColumnSettings create(long planColumnSettingsId) {
        PlanColumnSettings planColumnSettings = new PlanColumnSettingsImpl();

        planColumnSettings.setNew(true);
        planColumnSettings.setPrimaryKey(planColumnSettingsId);

        return planColumnSettings;
    }

    /**
     * Removes the plan column settings with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planColumnSettingsId the primary key of the plan column settings
     * @return the plan column settings that was removed
     * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings remove(long planColumnSettingsId)
        throws NoSuchPlanColumnSettingsException, SystemException {
        return remove((Serializable) planColumnSettingsId);
    }

    /**
     * Removes the plan column settings with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan column settings
     * @return the plan column settings that was removed
     * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings remove(Serializable primaryKey)
        throws NoSuchPlanColumnSettingsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanColumnSettings planColumnSettings = (PlanColumnSettings) session.get(PlanColumnSettingsImpl.class,
                    primaryKey);

            if (planColumnSettings == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanColumnSettingsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planColumnSettings);
        } catch (NoSuchPlanColumnSettingsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanColumnSettings removeImpl(
        PlanColumnSettings planColumnSettings) throws SystemException {
        planColumnSettings = toUnwrappedModel(planColumnSettings);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planColumnSettings)) {
                planColumnSettings = (PlanColumnSettings) session.get(PlanColumnSettingsImpl.class,
                        planColumnSettings.getPrimaryKeyObj());
            }

            if (planColumnSettings != null) {
                session.delete(planColumnSettings);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planColumnSettings != null) {
            clearCache(planColumnSettings);
        }

        return planColumnSettings;
    }

    @Override
    public PlanColumnSettings updateImpl(
        com.ext.portlet.model.PlanColumnSettings planColumnSettings)
        throws SystemException {
        planColumnSettings = toUnwrappedModel(planColumnSettings);

        boolean isNew = planColumnSettings.isNew();

        Session session = null;

        try {
            session = openSession();

            if (planColumnSettings.isNew()) {
                session.save(planColumnSettings);

                planColumnSettings.setNew(false);
            } else {
                session.merge(planColumnSettings);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanColumnSettingsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey(),
            planColumnSettings);

        clearUniqueFindersCache(planColumnSettings);
        cacheUniqueFindersCache(planColumnSettings);

        return planColumnSettings;
    }

    protected PlanColumnSettings toUnwrappedModel(
        PlanColumnSettings planColumnSettings) {
        if (planColumnSettings instanceof PlanColumnSettingsImpl) {
            return planColumnSettings;
        }

        PlanColumnSettingsImpl planColumnSettingsImpl = new PlanColumnSettingsImpl();

        planColumnSettingsImpl.setNew(planColumnSettings.isNew());
        planColumnSettingsImpl.setPrimaryKey(planColumnSettings.getPrimaryKey());

        planColumnSettingsImpl.setPlanColumnSettingsId(planColumnSettings.getPlanColumnSettingsId());
        planColumnSettingsImpl.setColumnName(planColumnSettings.getColumnName());
        planColumnSettingsImpl.setPlanUserSettingsId(planColumnSettings.getPlanUserSettingsId());
        planColumnSettingsImpl.setVisible(planColumnSettings.isVisible());

        return planColumnSettingsImpl;
    }

    /**
     * Returns the plan column settings with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan column settings
     * @return the plan column settings
     * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanColumnSettingsException, SystemException {
        PlanColumnSettings planColumnSettings = fetchByPrimaryKey(primaryKey);

        if (planColumnSettings == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanColumnSettingsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planColumnSettings;
    }

    /**
     * Returns the plan column settings with the primary key or throws a {@link com.ext.portlet.NoSuchPlanColumnSettingsException} if it could not be found.
     *
     * @param planColumnSettingsId the primary key of the plan column settings
     * @return the plan column settings
     * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings findByPrimaryKey(long planColumnSettingsId)
        throws NoSuchPlanColumnSettingsException, SystemException {
        return findByPrimaryKey((Serializable) planColumnSettingsId);
    }

    /**
     * Returns the plan column settings with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan column settings
     * @return the plan column settings, or <code>null</code> if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanColumnSettings planColumnSettings = (PlanColumnSettings) EntityCacheUtil.getResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                PlanColumnSettingsImpl.class, primaryKey);

        if (planColumnSettings == _nullPlanColumnSettings) {
            return null;
        }

        if (planColumnSettings == null) {
            Session session = null;

            try {
                session = openSession();

                planColumnSettings = (PlanColumnSettings) session.get(PlanColumnSettingsImpl.class,
                        primaryKey);

                if (planColumnSettings != null) {
                    cacheResult(planColumnSettings);
                } else {
                    EntityCacheUtil.putResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                        PlanColumnSettingsImpl.class, primaryKey,
                        _nullPlanColumnSettings);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                    PlanColumnSettingsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planColumnSettings;
    }

    /**
     * Returns the plan column settings with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planColumnSettingsId the primary key of the plan column settings
     * @return the plan column settings, or <code>null</code> if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings fetchByPrimaryKey(long planColumnSettingsId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) planColumnSettingsId);
    }

    /**
     * Returns all the plan column settingses.
     *
     * @return the plan column settingses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanColumnSettings> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan column settingses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanColumnSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan column settingses
     * @param end the upper bound of the range of plan column settingses (not inclusive)
     * @return the range of plan column settingses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanColumnSettings> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan column settingses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanColumnSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan column settingses
     * @param end the upper bound of the range of plan column settingses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan column settingses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanColumnSettings> findAll(int start, int end,
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

        List<PlanColumnSettings> list = (List<PlanColumnSettings>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANCOLUMNSETTINGS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANCOLUMNSETTINGS;

                if (pagination) {
                    sql = sql.concat(PlanColumnSettingsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanColumnSettings>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanColumnSettings>(list);
                } else {
                    list = (List<PlanColumnSettings>) QueryUtil.list(q,
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
     * Removes all the plan column settingses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanColumnSettings planColumnSettings : findAll()) {
            remove(planColumnSettings);
        }
    }

    /**
     * Returns the number of plan column settingses.
     *
     * @return the number of plan column settingses
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

                Query q = session.createQuery(_SQL_COUNT_PLANCOLUMNSETTINGS);

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
     * Initializes the plan column settings persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanColumnSettings")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanColumnSettings>> listenersList = new ArrayList<ModelListener<PlanColumnSettings>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanColumnSettings>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanColumnSettingsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
