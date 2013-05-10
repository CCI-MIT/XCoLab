package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchFocusAreaOntologyTermException;
import com.ext.portlet.model.FocusAreaOntologyTerm;
import com.ext.portlet.model.impl.FocusAreaOntologyTermImpl;
import com.ext.portlet.model.impl.FocusAreaOntologyTermModelImpl;
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
import com.ext.portlet.service.persistence.BalloonStatsEntryPersistence;
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
import com.ext.portlet.service.persistence.EmailListPersistence;
import com.ext.portlet.service.persistence.FocusAreaOntologyTermPersistence;
import com.ext.portlet.service.persistence.FocusAreaPersistence;
import com.ext.portlet.service.persistence.LandingPagePersistence;
import com.ext.portlet.service.persistence.MessagePersistence;
import com.ext.portlet.service.persistence.MessageRecipientStatusPersistence;
import com.ext.portlet.service.persistence.MessagingIgnoredRecipientsPersistence;
import com.ext.portlet.service.persistence.MessagingMessageConversionPersistence;
import com.ext.portlet.service.persistence.MessagingMessageConversionTypePersistence;
import com.ext.portlet.service.persistence.MessagingMessagePersistence;
import com.ext.portlet.service.persistence.MessagingMessageRecipientPersistence;
import com.ext.portlet.service.persistence.MessagingRedirectLinkPersistence;
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
 * The persistence implementation for the focus area ontology term service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTermPersistence
 * @see FocusAreaOntologyTermUtil
 * @generated
 */
public class FocusAreaOntologyTermPersistenceImpl extends BasePersistenceImpl<FocusAreaOntologyTerm>
    implements FocusAreaOntologyTermPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link FocusAreaOntologyTermUtil} to access the focus area ontology term persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = FocusAreaOntologyTermImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FOCUSAREAID =
        new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FocusAreaOntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFocusAreaId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FOCUSAREAID =
        new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FocusAreaOntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFocusAreaId",
            new String[] { Long.class.getName() },
            FocusAreaOntologyTermModelImpl.FOCUSAREAID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FOCUSAREAID = new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFocusAreaId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FocusAreaOntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED,
            FocusAreaOntologyTermImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_FOCUSAREAONTOLOGYTERM = "SELECT focusAreaOntologyTerm FROM FocusAreaOntologyTerm focusAreaOntologyTerm";
    private static final String _SQL_SELECT_FOCUSAREAONTOLOGYTERM_WHERE = "SELECT focusAreaOntologyTerm FROM FocusAreaOntologyTerm focusAreaOntologyTerm WHERE ";
    private static final String _SQL_COUNT_FOCUSAREAONTOLOGYTERM = "SELECT COUNT(focusAreaOntologyTerm) FROM FocusAreaOntologyTerm focusAreaOntologyTerm";
    private static final String _SQL_COUNT_FOCUSAREAONTOLOGYTERM_WHERE = "SELECT COUNT(focusAreaOntologyTerm) FROM FocusAreaOntologyTerm focusAreaOntologyTerm WHERE ";
    private static final String _FINDER_COLUMN_FOCUSAREAID_FOCUSAREAID_2 = "focusAreaOntologyTerm.id.focusAreaId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "focusAreaOntologyTerm.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FocusAreaOntologyTerm exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No FocusAreaOntologyTerm exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(FocusAreaOntologyTermPersistenceImpl.class);
    private static FocusAreaOntologyTerm _nullFocusAreaOntologyTerm = new FocusAreaOntologyTermImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<FocusAreaOntologyTerm> toCacheModel() {
                return _nullFocusAreaOntologyTermCacheModel;
            }
        };

    private static CacheModel<FocusAreaOntologyTerm> _nullFocusAreaOntologyTermCacheModel =
        new CacheModel<FocusAreaOntologyTerm>() {
            public FocusAreaOntologyTerm toEntityModel() {
                return _nullFocusAreaOntologyTerm;
            }
        };

    @BeanReference(type = ActivitySubscriptionPersistence.class)
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;
    @BeanReference(type = BalloonStatsEntryPersistence.class)
    protected BalloonStatsEntryPersistence balloonStatsEntryPersistence;
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
    @BeanReference(type = EmailListPersistence.class)
    protected EmailListPersistence emailListPersistence;
    @BeanReference(type = FocusAreaPersistence.class)
    protected FocusAreaPersistence focusAreaPersistence;
    @BeanReference(type = FocusAreaOntologyTermPersistence.class)
    protected FocusAreaOntologyTermPersistence focusAreaOntologyTermPersistence;
    @BeanReference(type = LandingPagePersistence.class)
    protected LandingPagePersistence landingPagePersistence;
    @BeanReference(type = MessagePersistence.class)
    protected MessagePersistence messagePersistence;
    @BeanReference(type = MessageRecipientStatusPersistence.class)
    protected MessageRecipientStatusPersistence messageRecipientStatusPersistence;
    @BeanReference(type = MessagingIgnoredRecipientsPersistence.class)
    protected MessagingIgnoredRecipientsPersistence messagingIgnoredRecipientsPersistence;
    @BeanReference(type = MessagingMessagePersistence.class)
    protected MessagingMessagePersistence messagingMessagePersistence;
    @BeanReference(type = MessagingMessageConversionPersistence.class)
    protected MessagingMessageConversionPersistence messagingMessageConversionPersistence;
    @BeanReference(type = MessagingMessageConversionTypePersistence.class)
    protected MessagingMessageConversionTypePersistence messagingMessageConversionTypePersistence;
    @BeanReference(type = MessagingMessageRecipientPersistence.class)
    protected MessagingMessageRecipientPersistence messagingMessageRecipientPersistence;
    @BeanReference(type = MessagingRedirectLinkPersistence.class)
    protected MessagingRedirectLinkPersistence messagingRedirectLinkPersistence;
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
     * Caches the focus area ontology term in the entity cache if it is enabled.
     *
     * @param focusAreaOntologyTerm the focus area ontology term
     */
    public void cacheResult(FocusAreaOntologyTerm focusAreaOntologyTerm) {
        EntityCacheUtil.putResult(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermImpl.class,
            focusAreaOntologyTerm.getPrimaryKey(), focusAreaOntologyTerm);

        focusAreaOntologyTerm.resetOriginalValues();
    }

    /**
     * Caches the focus area ontology terms in the entity cache if it is enabled.
     *
     * @param focusAreaOntologyTerms the focus area ontology terms
     */
    public void cacheResult(List<FocusAreaOntologyTerm> focusAreaOntologyTerms) {
        for (FocusAreaOntologyTerm focusAreaOntologyTerm : focusAreaOntologyTerms) {
            if (EntityCacheUtil.getResult(
                        FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                        FocusAreaOntologyTermImpl.class,
                        focusAreaOntologyTerm.getPrimaryKey()) == null) {
                cacheResult(focusAreaOntologyTerm);
            } else {
                focusAreaOntologyTerm.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all focus area ontology terms.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(FocusAreaOntologyTermImpl.class.getName());
        }

        EntityCacheUtil.clearCache(FocusAreaOntologyTermImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the focus area ontology term.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(FocusAreaOntologyTerm focusAreaOntologyTerm) {
        EntityCacheUtil.removeResult(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermImpl.class,
            focusAreaOntologyTerm.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<FocusAreaOntologyTerm> focusAreaOntologyTerms) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (FocusAreaOntologyTerm focusAreaOntologyTerm : focusAreaOntologyTerms) {
            EntityCacheUtil.removeResult(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                FocusAreaOntologyTermImpl.class,
                focusAreaOntologyTerm.getPrimaryKey());
        }
    }

    /**
     * Creates a new focus area ontology term with the primary key. Does not add the focus area ontology term to the database.
     *
     * @param focusAreaOntologyTermPK the primary key for the new focus area ontology term
     * @return the new focus area ontology term
     */
    public FocusAreaOntologyTerm create(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK) {
        FocusAreaOntologyTerm focusAreaOntologyTerm = new FocusAreaOntologyTermImpl();

        focusAreaOntologyTerm.setNew(true);
        focusAreaOntologyTerm.setPrimaryKey(focusAreaOntologyTermPK);

        return focusAreaOntologyTerm;
    }

    /**
     * Removes the focus area ontology term with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
     * @return the focus area ontology term that was removed
     * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a focus area ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusAreaOntologyTerm remove(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        return remove((Serializable) focusAreaOntologyTermPK);
    }

    /**
     * Removes the focus area ontology term with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the focus area ontology term
     * @return the focus area ontology term that was removed
     * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a focus area ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FocusAreaOntologyTerm remove(Serializable primaryKey)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        Session session = null;

        try {
            session = openSession();

            FocusAreaOntologyTerm focusAreaOntologyTerm = (FocusAreaOntologyTerm) session.get(FocusAreaOntologyTermImpl.class,
                    primaryKey);

            if (focusAreaOntologyTerm == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchFocusAreaOntologyTermException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(focusAreaOntologyTerm);
        } catch (NoSuchFocusAreaOntologyTermException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected FocusAreaOntologyTerm removeImpl(
        FocusAreaOntologyTerm focusAreaOntologyTerm) throws SystemException {
        focusAreaOntologyTerm = toUnwrappedModel(focusAreaOntologyTerm);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, focusAreaOntologyTerm);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(focusAreaOntologyTerm);

        return focusAreaOntologyTerm;
    }

    @Override
    public FocusAreaOntologyTerm updateImpl(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm,
        boolean merge) throws SystemException {
        focusAreaOntologyTerm = toUnwrappedModel(focusAreaOntologyTerm);

        boolean isNew = focusAreaOntologyTerm.isNew();

        FocusAreaOntologyTermModelImpl focusAreaOntologyTermModelImpl = (FocusAreaOntologyTermModelImpl) focusAreaOntologyTerm;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, focusAreaOntologyTerm, merge);

            focusAreaOntologyTerm.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !FocusAreaOntologyTermModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((focusAreaOntologyTermModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FOCUSAREAID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(focusAreaOntologyTermModelImpl.getOriginalFocusAreaId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FOCUSAREAID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FOCUSAREAID,
                    args);

                args = new Object[] {
                        Long.valueOf(focusAreaOntologyTermModelImpl.getFocusAreaId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FOCUSAREAID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FOCUSAREAID,
                    args);
            }
        }

        EntityCacheUtil.putResult(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
            FocusAreaOntologyTermImpl.class,
            focusAreaOntologyTerm.getPrimaryKey(), focusAreaOntologyTerm);

        return focusAreaOntologyTerm;
    }

    protected FocusAreaOntologyTerm toUnwrappedModel(
        FocusAreaOntologyTerm focusAreaOntologyTerm) {
        if (focusAreaOntologyTerm instanceof FocusAreaOntologyTermImpl) {
            return focusAreaOntologyTerm;
        }

        FocusAreaOntologyTermImpl focusAreaOntologyTermImpl = new FocusAreaOntologyTermImpl();

        focusAreaOntologyTermImpl.setNew(focusAreaOntologyTerm.isNew());
        focusAreaOntologyTermImpl.setPrimaryKey(focusAreaOntologyTerm.getPrimaryKey());

        focusAreaOntologyTermImpl.setFocusAreaId(focusAreaOntologyTerm.getFocusAreaId());
        focusAreaOntologyTermImpl.setOntologyTermId(focusAreaOntologyTerm.getOntologyTermId());

        return focusAreaOntologyTermImpl;
    }

    /**
     * Returns the focus area ontology term with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the focus area ontology term
     * @return the focus area ontology term
     * @throws com.liferay.portal.NoSuchModelException if a focus area ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FocusAreaOntologyTerm findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((FocusAreaOntologyTermPK) primaryKey);
    }

    /**
     * Returns the focus area ontology term with the primary key or throws a {@link com.ext.portlet.NoSuchFocusAreaOntologyTermException} if it could not be found.
     *
     * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
     * @return the focus area ontology term
     * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a focus area ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusAreaOntologyTerm findByPrimaryKey(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        FocusAreaOntologyTerm focusAreaOntologyTerm = fetchByPrimaryKey(focusAreaOntologyTermPK);

        if (focusAreaOntologyTerm == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    focusAreaOntologyTermPK);
            }

            throw new NoSuchFocusAreaOntologyTermException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                focusAreaOntologyTermPK);
        }

        return focusAreaOntologyTerm;
    }

    /**
     * Returns the focus area ontology term with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the focus area ontology term
     * @return the focus area ontology term, or <code>null</code> if a focus area ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public FocusAreaOntologyTerm fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((FocusAreaOntologyTermPK) primaryKey);
    }

    /**
     * Returns the focus area ontology term with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
     * @return the focus area ontology term, or <code>null</code> if a focus area ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusAreaOntologyTerm fetchByPrimaryKey(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws SystemException {
        FocusAreaOntologyTerm focusAreaOntologyTerm = (FocusAreaOntologyTerm) EntityCacheUtil.getResult(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                FocusAreaOntologyTermImpl.class, focusAreaOntologyTermPK);

        if (focusAreaOntologyTerm == _nullFocusAreaOntologyTerm) {
            return null;
        }

        if (focusAreaOntologyTerm == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                focusAreaOntologyTerm = (FocusAreaOntologyTerm) session.get(FocusAreaOntologyTermImpl.class,
                        focusAreaOntologyTermPK);
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (focusAreaOntologyTerm != null) {
                    cacheResult(focusAreaOntologyTerm);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(FocusAreaOntologyTermModelImpl.ENTITY_CACHE_ENABLED,
                        FocusAreaOntologyTermImpl.class,
                        focusAreaOntologyTermPK, _nullFocusAreaOntologyTerm);
                }

                closeSession(session);
            }
        }

        return focusAreaOntologyTerm;
    }

    /**
     * Returns all the focus area ontology terms where focusAreaId = &#63;.
     *
     * @param focusAreaId the focus area ID
     * @return the matching focus area ontology terms
     * @throws SystemException if a system exception occurred
     */
    public List<FocusAreaOntologyTerm> findByFocusAreaId(long focusAreaId)
        throws SystemException {
        return findByFocusAreaId(focusAreaId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the focus area ontology terms where focusAreaId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param focusAreaId the focus area ID
     * @param start the lower bound of the range of focus area ontology terms
     * @param end the upper bound of the range of focus area ontology terms (not inclusive)
     * @return the range of matching focus area ontology terms
     * @throws SystemException if a system exception occurred
     */
    public List<FocusAreaOntologyTerm> findByFocusAreaId(long focusAreaId,
        int start, int end) throws SystemException {
        return findByFocusAreaId(focusAreaId, start, end, null);
    }

    /**
     * Returns an ordered range of all the focus area ontology terms where focusAreaId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param focusAreaId the focus area ID
     * @param start the lower bound of the range of focus area ontology terms
     * @param end the upper bound of the range of focus area ontology terms (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching focus area ontology terms
     * @throws SystemException if a system exception occurred
     */
    public List<FocusAreaOntologyTerm> findByFocusAreaId(long focusAreaId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FOCUSAREAID;
            finderArgs = new Object[] { focusAreaId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FOCUSAREAID;
            finderArgs = new Object[] { focusAreaId, start, end, orderByComparator };
        }

        List<FocusAreaOntologyTerm> list = (List<FocusAreaOntologyTerm>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_FOCUSAREAONTOLOGYTERM_WHERE);

            query.append(_FINDER_COLUMN_FOCUSAREAID_FOCUSAREAID_2);

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

                qPos.add(focusAreaId);

                list = (List<FocusAreaOntologyTerm>) QueryUtil.list(q,
                        getDialect(), start, end);
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
     * Returns the first focus area ontology term in the ordered set where focusAreaId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param focusAreaId the focus area ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching focus area ontology term
     * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a matching focus area ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusAreaOntologyTerm findByFocusAreaId_First(long focusAreaId,
        OrderByComparator orderByComparator)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        List<FocusAreaOntologyTerm> list = findByFocusAreaId(focusAreaId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("focusAreaId=");
            msg.append(focusAreaId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFocusAreaOntologyTermException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last focus area ontology term in the ordered set where focusAreaId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param focusAreaId the focus area ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching focus area ontology term
     * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a matching focus area ontology term could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusAreaOntologyTerm findByFocusAreaId_Last(long focusAreaId,
        OrderByComparator orderByComparator)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        int count = countByFocusAreaId(focusAreaId);

        List<FocusAreaOntologyTerm> list = findByFocusAreaId(focusAreaId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("focusAreaId=");
            msg.append(focusAreaId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchFocusAreaOntologyTermException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the focus area ontology terms before and after the current focus area ontology term in the ordered set where focusAreaId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param focusAreaOntologyTermPK the primary key of the current focus area ontology term
     * @param focusAreaId the focus area ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next focus area ontology term
     * @throws com.ext.portlet.NoSuchFocusAreaOntologyTermException if a focus area ontology term with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public FocusAreaOntologyTerm[] findByFocusAreaId_PrevAndNext(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK, long focusAreaId,
        OrderByComparator orderByComparator)
        throws NoSuchFocusAreaOntologyTermException, SystemException {
        FocusAreaOntologyTerm focusAreaOntologyTerm = findByPrimaryKey(focusAreaOntologyTermPK);

        Session session = null;

        try {
            session = openSession();

            FocusAreaOntologyTerm[] array = new FocusAreaOntologyTermImpl[3];

            array[0] = getByFocusAreaId_PrevAndNext(session,
                    focusAreaOntologyTerm, focusAreaId, orderByComparator, true);

            array[1] = focusAreaOntologyTerm;

            array[2] = getByFocusAreaId_PrevAndNext(session,
                    focusAreaOntologyTerm, focusAreaId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected FocusAreaOntologyTerm getByFocusAreaId_PrevAndNext(
        Session session, FocusAreaOntologyTerm focusAreaOntologyTerm,
        long focusAreaId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_FOCUSAREAONTOLOGYTERM_WHERE);

        query.append(_FINDER_COLUMN_FOCUSAREAID_FOCUSAREAID_2);

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

        qPos.add(focusAreaId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(focusAreaOntologyTerm);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<FocusAreaOntologyTerm> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the focus area ontology terms.
     *
     * @return the focus area ontology terms
     * @throws SystemException if a system exception occurred
     */
    public List<FocusAreaOntologyTerm> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the focus area ontology terms.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of focus area ontology terms
     * @param end the upper bound of the range of focus area ontology terms (not inclusive)
     * @return the range of focus area ontology terms
     * @throws SystemException if a system exception occurred
     */
    public List<FocusAreaOntologyTerm> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the focus area ontology terms.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of focus area ontology terms
     * @param end the upper bound of the range of focus area ontology terms (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of focus area ontology terms
     * @throws SystemException if a system exception occurred
     */
    public List<FocusAreaOntologyTerm> findAll(int start, int end,
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

        List<FocusAreaOntologyTerm> list = (List<FocusAreaOntologyTerm>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_FOCUSAREAONTOLOGYTERM);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_FOCUSAREAONTOLOGYTERM;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<FocusAreaOntologyTerm>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<FocusAreaOntologyTerm>) QueryUtil.list(q,
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
     * Removes all the focus area ontology terms where focusAreaId = &#63; from the database.
     *
     * @param focusAreaId the focus area ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByFocusAreaId(long focusAreaId) throws SystemException {
        for (FocusAreaOntologyTerm focusAreaOntologyTerm : findByFocusAreaId(
                focusAreaId)) {
            remove(focusAreaOntologyTerm);
        }
    }

    /**
     * Removes all the focus area ontology terms from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (FocusAreaOntologyTerm focusAreaOntologyTerm : findAll()) {
            remove(focusAreaOntologyTerm);
        }
    }

    /**
     * Returns the number of focus area ontology terms where focusAreaId = &#63;.
     *
     * @param focusAreaId the focus area ID
     * @return the number of matching focus area ontology terms
     * @throws SystemException if a system exception occurred
     */
    public int countByFocusAreaId(long focusAreaId) throws SystemException {
        Object[] finderArgs = new Object[] { focusAreaId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FOCUSAREAID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_FOCUSAREAONTOLOGYTERM_WHERE);

            query.append(_FINDER_COLUMN_FOCUSAREAID_FOCUSAREAID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(focusAreaId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FOCUSAREAID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of focus area ontology terms.
     *
     * @return the number of focus area ontology terms
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_FOCUSAREAONTOLOGYTERM);

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
     * Initializes the focus area ontology term persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.FocusAreaOntologyTerm")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<FocusAreaOntologyTerm>> listenersList = new ArrayList<ModelListener<FocusAreaOntologyTerm>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<FocusAreaOntologyTerm>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(FocusAreaOntologyTermImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
