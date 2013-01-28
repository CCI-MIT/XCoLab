package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchFocusAreaException;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.impl.FocusAreaImpl;
import com.ext.portlet.model.impl.FocusAreaModelImpl;
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
import com.ext.portlet.service.persistence.ContestDebatePersistence;
import com.ext.portlet.service.persistence.ContestPersistence;
import com.ext.portlet.service.persistence.ContestPhaseColumnPersistence;
import com.ext.portlet.service.persistence.ContestPhasePersistence;
import com.ext.portlet.service.persistence.ContestPhaseTypePersistence;
import com.ext.portlet.service.persistence.ContestTeamMemberPersistence;
import com.ext.portlet.service.persistence.DiscussionCategoryGroupPersistence;
import com.ext.portlet.service.persistence.DiscussionCategoryPersistence;
import com.ext.portlet.service.persistence.DiscussionMessageFlagPersistence;
import com.ext.portlet.service.persistence.DiscussionMessagePersistence;
import com.ext.portlet.service.persistence.FocusAreaOntologyTermPersistence;
import com.ext.portlet.service.persistence.FocusAreaPersistence;
import com.ext.portlet.service.persistence.MessagePersistence;
import com.ext.portlet.service.persistence.MessageRecipientStatusPersistence;
import com.ext.portlet.service.persistence.MessagingUserPreferencesPersistence;
import com.ext.portlet.service.persistence.ModelCategoryPersistence;
import com.ext.portlet.service.persistence.ModelDiscussionPersistence;
import com.ext.portlet.service.persistence.ModelGlobalPreferencePersistence;
import com.ext.portlet.service.persistence.ModelInputGroupPersistence;
import com.ext.portlet.service.persistence.ModelInputItemPersistence;
import com.ext.portlet.service.persistence.ModelOutputChartOrderPersistence;
import com.ext.portlet.service.persistence.ModelOutputItemPersistence;
import com.ext.portlet.service.persistence.ModelPositionPersistence;
import com.ext.portlet.service.persistence.OntologySpacePersistence;
import com.ext.portlet.service.persistence.OntologyTermEntityPersistence;
import com.ext.portlet.service.persistence.OntologyTermPersistence;
import com.ext.portlet.service.persistence.PlanAttributeFilterPersistence;
import com.ext.portlet.service.persistence.PlanAttributePersistence;
import com.ext.portlet.service.persistence.PlanColumnSettingsPersistence;
import com.ext.portlet.service.persistence.PlanDescriptionPersistence;
import com.ext.portlet.service.persistence.PlanFanPersistence;
import com.ext.portlet.service.persistence.PlanItemPersistence;
import com.ext.portlet.service.persistence.PlanMetaPersistence;
import com.ext.portlet.service.persistence.PlanModelRunPersistence;
import com.ext.portlet.service.persistence.PlanPositionItemPersistence;
import com.ext.portlet.service.persistence.PlanPositionPersistence;
import com.ext.portlet.service.persistence.PlanPositionsPersistence;
import com.ext.portlet.service.persistence.PlanPropertyFilterPersistence;
import com.ext.portlet.service.persistence.PlanRelatedPersistence;
import com.ext.portlet.service.persistence.PlanSectionDefinitionPersistence;
import com.ext.portlet.service.persistence.PlanSectionPersistence;
import com.ext.portlet.service.persistence.PlanSectionPlanMapPersistence;
import com.ext.portlet.service.persistence.PlanTeamHistoryPersistence;
import com.ext.portlet.service.persistence.PlanTemplatePersistence;
import com.ext.portlet.service.persistence.PlanTemplateSectionPersistence;
import com.ext.portlet.service.persistence.PlanTypeAttributePersistence;
import com.ext.portlet.service.persistence.PlanTypeColumnPersistence;
import com.ext.portlet.service.persistence.PlanTypePersistence;
import com.ext.portlet.service.persistence.PlanVotePersistence;
import com.ext.portlet.service.persistence.PlansFilterPersistence;
import com.ext.portlet.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.service.persistence.PlansUserSettingsPersistence;

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
 * The persistence implementation for the focus area service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaPersistence
 * @see FocusAreaUtil
 * @generated
 */
public class FocusAreaPersistenceImpl extends BasePersistenceImpl<FocusArea>
    implements FocusAreaPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link FocusAreaUtil} to access the focus area persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = FocusAreaImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaModelImpl.FINDER_CACHE_ENABLED, FocusAreaImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByName",
            new String[] { String.class.getName() },
            FocusAreaModelImpl.NAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaModelImpl.FINDER_CACHE_ENABLED, FocusAreaImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaModelImpl.FINDER_CACHE_ENABLED, FocusAreaImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FOCUSAREA = "SELECT focusArea FROM FocusArea focusArea";
    private static final String _SQL_SELECT_FOCUSAREA_WHERE = "SELECT focusArea FROM FocusArea focusArea WHERE ";
    private static final String _SQL_COUNT_FOCUSAREA = "SELECT COUNT(focusArea) FROM FocusArea focusArea";
    private static final String _SQL_COUNT_FOCUSAREA_WHERE = "SELECT COUNT(focusArea) FROM FocusArea focusArea WHERE ";
    private static final String _FINDER_COLUMN_NAME_NAME_1 = "focusArea.name IS NULL";
    private static final String _FINDER_COLUMN_NAME_NAME_2 = "focusArea.name = ?";
    private static final String _FINDER_COLUMN_NAME_NAME_3 = "(focusArea.name IS NULL OR focusArea.name = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "focusArea.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FocusArea exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FocusArea exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(FocusAreaPersistenceImpl.class);
    private static FocusArea _nullFocusArea = new FocusAreaImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<FocusArea> toCacheModel() {
                return _nullFocusAreaCacheModel;
            }
        };

    private static CacheModel<FocusArea> _nullFocusAreaCacheModel = new CacheModel<FocusArea>() {
            public FocusArea toEntityModel() {
                return _nullFocusArea;
            }
        };

    @BeanReference(type = ActivitySubscriptionPersistence.class)
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;
    @BeanReference(type = ContestPersistence.class)
    protected ContestPersistence contestPersistence;
    @BeanReference(type = ContestDebatePersistence.class)
    protected ContestDebatePersistence contestDebatePersistence;
    @BeanReference(type = ContestPhasePersistence.class)
    protected ContestPhasePersistence contestPhasePersistence;
    @BeanReference(type = ContestPhaseColumnPersistence.class)
    protected ContestPhaseColumnPersistence contestPhaseColumnPersistence;
    @BeanReference(type = ContestPhaseTypePersistence.class)
    protected ContestPhaseTypePersistence contestPhaseTypePersistence;
    @BeanReference(type = ContestTeamMemberPersistence.class)
    protected ContestTeamMemberPersistence contestTeamMemberPersistence;
    @BeanReference(type = DiscussionCategoryPersistence.class)
    protected DiscussionCategoryPersistence discussionCategoryPersistence;
    @BeanReference(type = DiscussionCategoryGroupPersistence.class)
    protected DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence;
    @BeanReference(type = DiscussionMessagePersistence.class)
    protected DiscussionMessagePersistence discussionMessagePersistence;
    @BeanReference(type = DiscussionMessageFlagPersistence.class)
    protected DiscussionMessageFlagPersistence discussionMessageFlagPersistence;
    @BeanReference(type = FocusAreaPersistence.class)
    protected FocusAreaPersistence focusAreaPersistence;
    @BeanReference(type = FocusAreaOntologyTermPersistence.class)
    protected FocusAreaOntologyTermPersistence focusAreaOntologyTermPersistence;
    @BeanReference(type = MessagePersistence.class)
    protected MessagePersistence messagePersistence;
    @BeanReference(type = MessageRecipientStatusPersistence.class)
    protected MessageRecipientStatusPersistence messageRecipientStatusPersistence;
    @BeanReference(type = MessagingUserPreferencesPersistence.class)
    protected MessagingUserPreferencesPersistence messagingUserPreferencesPersistence;
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
    @BeanReference(type = OntologySpacePersistence.class)
    protected OntologySpacePersistence ontologySpacePersistence;
    @BeanReference(type = OntologyTermPersistence.class)
    protected OntologyTermPersistence ontologyTermPersistence;
    @BeanReference(type = OntologyTermEntityPersistence.class)
    protected OntologyTermEntityPersistence ontologyTermEntityPersistence;
    @BeanReference(type = PlanAttributePersistence.class)
    protected PlanAttributePersistence planAttributePersistence;
    @BeanReference(type = PlanAttributeFilterPersistence.class)
    protected PlanAttributeFilterPersistence planAttributeFilterPersistence;
    @BeanReference(type = PlanColumnSettingsPersistence.class)
    protected PlanColumnSettingsPersistence planColumnSettingsPersistence;
    @BeanReference(type = PlanDescriptionPersistence.class)
    protected PlanDescriptionPersistence planDescriptionPersistence;
    @BeanReference(type = PlanFanPersistence.class)
    protected PlanFanPersistence planFanPersistence;
    @BeanReference(type = PlanItemPersistence.class)
    protected PlanItemPersistence planItemPersistence;
    @BeanReference(type = PlanMetaPersistence.class)
    protected PlanMetaPersistence planMetaPersistence;
    @BeanReference(type = PlanModelRunPersistence.class)
    protected PlanModelRunPersistence planModelRunPersistence;
    @BeanReference(type = PlanPositionPersistence.class)
    protected PlanPositionPersistence planPositionPersistence;
    @BeanReference(type = PlanPositionItemPersistence.class)
    protected PlanPositionItemPersistence planPositionItemPersistence;
    @BeanReference(type = PlanPositionsPersistence.class)
    protected PlanPositionsPersistence planPositionsPersistence;
    @BeanReference(type = PlanPropertyFilterPersistence.class)
    protected PlanPropertyFilterPersistence planPropertyFilterPersistence;
    @BeanReference(type = PlanRelatedPersistence.class)
    protected PlanRelatedPersistence planRelatedPersistence;
    @BeanReference(type = PlanSectionPersistence.class)
    protected PlanSectionPersistence planSectionPersistence;
    @BeanReference(type = PlanSectionDefinitionPersistence.class)
    protected PlanSectionDefinitionPersistence planSectionDefinitionPersistence;
    @BeanReference(type = PlanSectionPlanMapPersistence.class)
    protected PlanSectionPlanMapPersistence planSectionPlanMapPersistence;
    @BeanReference(type = PlansFilterPersistence.class)
    protected PlansFilterPersistence plansFilterPersistence;
    @BeanReference(type = PlansFilterPositionPersistence.class)
    protected PlansFilterPositionPersistence plansFilterPositionPersistence;
    @BeanReference(type = PlansUserSettingsPersistence.class)
    protected PlansUserSettingsPersistence plansUserSettingsPersistence;
    @BeanReference(type = PlanTeamHistoryPersistence.class)
    protected PlanTeamHistoryPersistence planTeamHistoryPersistence;
    @BeanReference(type = PlanTemplatePersistence.class)
    protected PlanTemplatePersistence planTemplatePersistence;
    @BeanReference(type = PlanTemplateSectionPersistence.class)
    protected PlanTemplateSectionPersistence planTemplateSectionPersistence;
    @BeanReference(type = PlanTypePersistence.class)
    protected PlanTypePersistence planTypePersistence;
    @BeanReference(type = PlanTypeAttributePersistence.class)
    protected PlanTypeAttributePersistence planTypeAttributePersistence;
    @BeanReference(type = PlanTypeColumnPersistence.class)
    protected PlanTypeColumnPersistence planTypeColumnPersistence;
    @BeanReference(type = PlanVotePersistence.class)
    protected PlanVotePersistence planVotePersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the focus area in the entity cache if it is enabled.
     *
     * @param focusArea the focus area
     */
    public void cacheResult(FocusArea focusArea) {
        EntityCacheUtil.putResult(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaImpl.class, focusArea.getPrimaryKey(), focusArea);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { focusArea.getName() }, focusArea);

        focusArea.resetOriginalValues();
    }

    /**
     * Caches the focus areas in the entity cache if it is enabled.
     *
     * @param focusAreas the focus areas
     */
    public void cacheResult(List<FocusArea> focusAreas) {
        for (FocusArea focusArea : focusAreas) {
            if (EntityCacheUtil.getResult(
                        FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                        FocusAreaImpl.class, focusArea.getPrimaryKey()) == null) {
                cacheResult(focusArea);
            } else {
                focusArea.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all focus areas.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(FocusAreaImpl.class.getName());
        }

        EntityCacheUtil.clearCache(FocusAreaImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the focus area.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(FocusArea focusArea) {
        EntityCacheUtil.removeResult(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaImpl.class, focusArea.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(focusArea);
    }

    @Override
    public void clearCache(List<FocusArea> focusAreas) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (FocusArea focusArea : focusAreas) {
            EntityCacheUtil.removeResult(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                FocusAreaImpl.class, focusArea.getPrimaryKey());

            clearUniqueFindersCache(focusArea);
        }
    }

    protected void clearUniqueFindersCache(FocusArea focusArea) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { focusArea.getName() });
    }

    /**
     * Creates a new focus area with the primary key. Does not add the focus area to the database.
     *
     * @param id the primary key for the new focus area
     * @return the new focus area
     */
    public FocusArea create(long id) {
        FocusArea focusArea = new FocusAreaImpl();

        focusArea.setNew(true);
        focusArea.setPrimaryKey(id);

        return focusArea;
    }

    /**
     * Removes the focus area with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the focus area
     * @return the focus area that was removed
     * @throws com.ext.portlet.NoSuchFocusAreaException if a focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusArea remove(long id)
        throws NoSuchFocusAreaException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the focus area with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the focus area
     * @return the focus area that was removed
     * @throws com.ext.portlet.NoSuchFocusAreaException if a focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FocusArea remove(Serializable primaryKey)
        throws NoSuchFocusAreaException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FocusArea focusArea = (FocusArea) session.get(FocusAreaImpl.class,
                    primaryKey);

            if (focusArea == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchFocusAreaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(focusArea);
        } catch (NoSuchFocusAreaException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected FocusArea removeImpl(FocusArea focusArea)
        throws SystemException {
        focusArea = toUnwrappedModel(focusArea);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, focusArea);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(focusArea);

        return focusArea;
    }

    @Override
    public FocusArea updateImpl(com.ext.portlet.model.FocusArea focusArea,
        boolean merge) throws SystemException {
        focusArea = toUnwrappedModel(focusArea);

        boolean isNew = focusArea.isNew();

        FocusAreaModelImpl focusAreaModelImpl = (FocusAreaModelImpl) focusArea;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, focusArea, merge);

            focusArea.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !FocusAreaModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaImpl.class, focusArea.getPrimaryKey(), focusArea);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                new Object[] { focusArea.getName() }, focusArea);
        } else {
            if ((focusAreaModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        focusAreaModelImpl.getOriginalName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                    new Object[] { focusArea.getName() }, focusArea);
            }
        }

        return focusArea;
    }

    protected FocusArea toUnwrappedModel(FocusArea focusArea) {
        if (focusArea instanceof FocusAreaImpl) {
            return focusArea;
        }

        FocusAreaImpl focusAreaImpl = new FocusAreaImpl();

        focusAreaImpl.setNew(focusArea.isNew());
        focusAreaImpl.setPrimaryKey(focusArea.getPrimaryKey());

        focusAreaImpl.setId(focusArea.getId());
        focusAreaImpl.setName(focusArea.getName());

        return focusAreaImpl;
    }

    /**
     * Returns the focus area with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the focus area
     * @return the focus area
     * @throws com.liferay.portal.NoSuchModelException if a focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FocusArea findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the focus area with the primary key or throws a {@link com.ext.portlet.NoSuchFocusAreaException} if it could not be found.
     *
     * @param id the primary key of the focus area
     * @return the focus area
     * @throws com.ext.portlet.NoSuchFocusAreaException if a focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusArea findByPrimaryKey(long id)
        throws NoSuchFocusAreaException, SystemException {
        FocusArea focusArea = fetchByPrimaryKey(id);

        if (focusArea == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchFocusAreaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return focusArea;
    }

    /**
     * Returns the focus area with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the focus area
     * @return the focus area, or <code>null</code> if a focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FocusArea fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the focus area with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the focus area
     * @return the focus area, or <code>null</code> if a focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusArea fetchByPrimaryKey(long id) throws SystemException {
        FocusArea focusArea = (FocusArea) EntityCacheUtil.getResult(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                FocusAreaImpl.class, id);

        if (focusArea == _nullFocusArea) {
            return null;
        }

        if (focusArea == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                focusArea = (FocusArea) session.get(FocusAreaImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (focusArea != null) {
                    cacheResult(focusArea);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(FocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                        FocusAreaImpl.class, id, _nullFocusArea);
                }

                closeSession(session);
            }
        }

        return focusArea;
    }

    /**
     * Returns the focus area where name = &#63; or throws a {@link com.ext.portlet.NoSuchFocusAreaException} if it could not be found.
     *
     * @param name the name
     * @return the matching focus area
     * @throws com.ext.portlet.NoSuchFocusAreaException if a matching focus area could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusArea findByName(String name)
        throws NoSuchFocusAreaException, SystemException {
        FocusArea focusArea = fetchByName(name);

        if (focusArea == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("name=");
            msg.append(name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchFocusAreaException(msg.toString());
        }

        return focusArea;
    }

    /**
     * Returns the focus area where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param name the name
     * @return the matching focus area, or <code>null</code> if a matching focus area could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusArea fetchByName(String name) throws SystemException {
        return fetchByName(name, true);
    }

    /**
     * Returns the focus area where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param name the name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching focus area, or <code>null</code> if a matching focus area could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusArea fetchByName(String name, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_SELECT_FOCUSAREA_WHERE);

            if (name == null) {
                query.append(_FINDER_COLUMN_NAME_NAME_1);
            } else {
                if (name.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_NAME_NAME_3);
                } else {
                    query.append(_FINDER_COLUMN_NAME_NAME_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                List<FocusArea> list = q.list();

                result = list;

                FocusArea focusArea = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs, list);
                } else {
                    focusArea = list.get(0);

                    cacheResult(focusArea);

                    if ((focusArea.getName() == null) ||
                            !focusArea.getName().equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                            finderArgs, focusArea);
                    }
                }

                return focusArea;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (FocusArea) result;
            }
        }
    }

    /**
     * Returns all the focus areas.
     *
     * @return the focus areas
     * @throws SystemException if a system exception occurred
     */
    public List<FocusArea> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the focus areas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of focus areas
     * @param end the upper bound of the range of focus areas (not inclusive)
     * @return the range of focus areas
     * @throws SystemException if a system exception occurred
     */
    public List<FocusArea> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the focus areas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of focus areas
     * @param end the upper bound of the range of focus areas (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of focus areas
     * @throws SystemException if a system exception occurred
     */
    public List<FocusArea> findAll(int start, int end,
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

        List<FocusArea> list = (List<FocusArea>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FOCUSAREA);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FOCUSAREA;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<FocusArea>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<FocusArea>) QueryUtil.list(q, getDialect(),
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
     * Removes the focus area where name = &#63; from the database.
     *
     * @param name the name
     * @throws SystemException if a system exception occurred
     */
    public void removeByName(String name)
        throws NoSuchFocusAreaException, SystemException {
        FocusArea focusArea = findByName(name);

        remove(focusArea);
    }

    /**
     * Removes all the focus areas from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (FocusArea focusArea : findAll()) {
            remove(focusArea);
        }
    }

    /**
     * Returns the number of focus areas where name = &#63;.
     *
     * @param name the name
     * @return the number of matching focus areas
     * @throws SystemException if a system exception occurred
     */
    public int countByName(String name) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FOCUSAREA_WHERE);

            if (name == null) {
                query.append(_FINDER_COLUMN_NAME_NAME_1);
            } else {
                if (name.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_NAME_NAME_3);
                } else {
                    query.append(_FINDER_COLUMN_NAME_NAME_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAME,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of focus areas.
     *
     * @return the number of focus areas
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_FOCUSAREA);

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
     * Initializes the focus area persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.FocusArea")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FocusArea>> listenersList = new ArrayList<ModelListener<FocusArea>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FocusArea>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(FocusAreaImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
