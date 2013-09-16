package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlansFilterPositionException;
import com.ext.portlet.model.PlansFilterPosition;
import com.ext.portlet.model.impl.PlansFilterPositionImpl;
import com.ext.portlet.model.impl.PlansFilterPositionModelImpl;
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
import com.ext.portlet.service.persistence.PlanItemGroupPersistence;
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
import com.ext.portlet.service.persistence.Proposal2PhasePersistence;
import com.ext.portlet.service.persistence.ProposalAttributePersistence;
import com.ext.portlet.service.persistence.ProposalAttributeTypePersistence;
import com.ext.portlet.service.persistence.ProposalPersistence;
import com.ext.portlet.service.persistence.ProposalVersionPersistence;

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
 * The persistence implementation for the plans filter position service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterPositionPersistence
 * @see PlansFilterPositionUtil
 * @generated
 */
public class PlansFilterPositionPersistenceImpl extends BasePersistenceImpl<PlansFilterPosition>
    implements PlansFilterPositionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlansFilterPositionUtil} to access the plans filter position persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlansFilterPositionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERIDPLANTYPEID =
        new FinderPath(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionModelImpl.FINDER_CACHE_ENABLED,
            PlansFilterPositionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserIdPlanTypeId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERIDPLANTYPEID =
        new FinderPath(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionModelImpl.FINDER_CACHE_ENABLED,
            PlansFilterPositionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByUserIdPlanTypeId",
            new String[] { Long.class.getName(), Long.class.getName() },
            PlansFilterPositionModelImpl.USERID_COLUMN_BITMASK |
            PlansFilterPositionModelImpl.PLANTYPEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERIDPLANTYPEID = new FinderPath(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByUserIdPlanTypeId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionModelImpl.FINDER_CACHE_ENABLED,
            PlansFilterPositionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionModelImpl.FINDER_CACHE_ENABLED,
            PlansFilterPositionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANSFILTERPOSITION = "SELECT plansFilterPosition FROM PlansFilterPosition plansFilterPosition";
    private static final String _SQL_SELECT_PLANSFILTERPOSITION_WHERE = "SELECT plansFilterPosition FROM PlansFilterPosition plansFilterPosition WHERE ";
    private static final String _SQL_COUNT_PLANSFILTERPOSITION = "SELECT COUNT(plansFilterPosition) FROM PlansFilterPosition plansFilterPosition";
    private static final String _SQL_COUNT_PLANSFILTERPOSITION_WHERE = "SELECT COUNT(plansFilterPosition) FROM PlansFilterPosition plansFilterPosition WHERE ";
    private static final String _FINDER_COLUMN_USERIDPLANTYPEID_USERID_2 = "plansFilterPosition.id.userId = ? AND ";
    private static final String _FINDER_COLUMN_USERIDPLANTYPEID_PLANTYPEID_2 = "plansFilterPosition.id.planTypeId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "plansFilterPosition.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlansFilterPosition exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlansFilterPosition exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlansFilterPositionPersistenceImpl.class);
    private static PlansFilterPosition _nullPlansFilterPosition = new PlansFilterPositionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlansFilterPosition> toCacheModel() {
                return _nullPlansFilterPositionCacheModel;
            }
        };

    private static CacheModel<PlansFilterPosition> _nullPlansFilterPositionCacheModel =
        new CacheModel<PlansFilterPosition>() {
            public PlansFilterPosition toEntityModel() {
                return _nullPlansFilterPosition;
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
    @BeanReference(type = PlanItemGroupPersistence.class)
    protected PlanItemGroupPersistence planItemGroupPersistence;
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
    @BeanReference(type = ProposalPersistence.class)
    protected ProposalPersistence proposalPersistence;
    @BeanReference(type = Proposal2PhasePersistence.class)
    protected Proposal2PhasePersistence proposal2PhasePersistence;
    @BeanReference(type = ProposalAttributePersistence.class)
    protected ProposalAttributePersistence proposalAttributePersistence;
    @BeanReference(type = ProposalAttributeTypePersistence.class)
    protected ProposalAttributeTypePersistence proposalAttributeTypePersistence;
    @BeanReference(type = ProposalVersionPersistence.class)
    protected ProposalVersionPersistence proposalVersionPersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the plans filter position in the entity cache if it is enabled.
     *
     * @param plansFilterPosition the plans filter position
     */
    public void cacheResult(PlansFilterPosition plansFilterPosition) {
        EntityCacheUtil.putResult(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionImpl.class, plansFilterPosition.getPrimaryKey(),
            plansFilterPosition);

        plansFilterPosition.resetOriginalValues();
    }

    /**
     * Caches the plans filter positions in the entity cache if it is enabled.
     *
     * @param plansFilterPositions the plans filter positions
     */
    public void cacheResult(List<PlansFilterPosition> plansFilterPositions) {
        for (PlansFilterPosition plansFilterPosition : plansFilterPositions) {
            if (EntityCacheUtil.getResult(
                        PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
                        PlansFilterPositionImpl.class,
                        plansFilterPosition.getPrimaryKey()) == null) {
                cacheResult(plansFilterPosition);
            } else {
                plansFilterPosition.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plans filter positions.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlansFilterPositionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlansFilterPositionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plans filter position.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlansFilterPosition plansFilterPosition) {
        EntityCacheUtil.removeResult(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionImpl.class, plansFilterPosition.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlansFilterPosition> plansFilterPositions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlansFilterPosition plansFilterPosition : plansFilterPositions) {
            EntityCacheUtil.removeResult(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
                PlansFilterPositionImpl.class,
                plansFilterPosition.getPrimaryKey());
        }
    }

    /**
     * Creates a new plans filter position with the primary key. Does not add the plans filter position to the database.
     *
     * @param plansFilterPositionPK the primary key for the new plans filter position
     * @return the new plans filter position
     */
    public PlansFilterPosition create(
        PlansFilterPositionPK plansFilterPositionPK) {
        PlansFilterPosition plansFilterPosition = new PlansFilterPositionImpl();

        plansFilterPosition.setNew(true);
        plansFilterPosition.setPrimaryKey(plansFilterPositionPK);

        return plansFilterPosition;
    }

    /**
     * Removes the plans filter position with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param plansFilterPositionPK the primary key of the plans filter position
     * @return the plans filter position that was removed
     * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a plans filter position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansFilterPosition remove(
        PlansFilterPositionPK plansFilterPositionPK)
        throws NoSuchPlansFilterPositionException, SystemException {
        return remove((Serializable) plansFilterPositionPK);
    }

    /**
     * Removes the plans filter position with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plans filter position
     * @return the plans filter position that was removed
     * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a plans filter position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlansFilterPosition remove(Serializable primaryKey)
        throws NoSuchPlansFilterPositionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlansFilterPosition plansFilterPosition = (PlansFilterPosition) session.get(PlansFilterPositionImpl.class,
                    primaryKey);

            if (plansFilterPosition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlansFilterPositionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(plansFilterPosition);
        } catch (NoSuchPlansFilterPositionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlansFilterPosition removeImpl(
        PlansFilterPosition plansFilterPosition) throws SystemException {
        plansFilterPosition = toUnwrappedModel(plansFilterPosition);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, plansFilterPosition);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(plansFilterPosition);

        return plansFilterPosition;
    }

    @Override
    public PlansFilterPosition updateImpl(
        com.ext.portlet.model.PlansFilterPosition plansFilterPosition,
        boolean merge) throws SystemException {
        plansFilterPosition = toUnwrappedModel(plansFilterPosition);

        boolean isNew = plansFilterPosition.isNew();

        PlansFilterPositionModelImpl plansFilterPositionModelImpl = (PlansFilterPositionModelImpl) plansFilterPosition;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, plansFilterPosition, merge);

            plansFilterPosition.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlansFilterPositionModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((plansFilterPositionModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERIDPLANTYPEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(plansFilterPositionModelImpl.getOriginalUserId()),
                        Long.valueOf(plansFilterPositionModelImpl.getOriginalPlanTypeId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERIDPLANTYPEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERIDPLANTYPEID,
                    args);

                args = new Object[] {
                        Long.valueOf(plansFilterPositionModelImpl.getUserId()),
                        Long.valueOf(plansFilterPositionModelImpl.getPlanTypeId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERIDPLANTYPEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERIDPLANTYPEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterPositionImpl.class, plansFilterPosition.getPrimaryKey(),
            plansFilterPosition);

        return plansFilterPosition;
    }

    protected PlansFilterPosition toUnwrappedModel(
        PlansFilterPosition plansFilterPosition) {
        if (plansFilterPosition instanceof PlansFilterPositionImpl) {
            return plansFilterPosition;
        }

        PlansFilterPositionImpl plansFilterPositionImpl = new PlansFilterPositionImpl();

        plansFilterPositionImpl.setNew(plansFilterPosition.isNew());
        plansFilterPositionImpl.setPrimaryKey(plansFilterPosition.getPrimaryKey());

        plansFilterPositionImpl.setUserId(plansFilterPosition.getUserId());
        plansFilterPositionImpl.setPlanTypeId(plansFilterPosition.getPlanTypeId());
        plansFilterPositionImpl.setPositionId(plansFilterPosition.getPositionId());

        return plansFilterPositionImpl;
    }

    /**
     * Returns the plans filter position with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plans filter position
     * @return the plans filter position
     * @throws com.liferay.portal.NoSuchModelException if a plans filter position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlansFilterPosition findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((PlansFilterPositionPK) primaryKey);
    }

    /**
     * Returns the plans filter position with the primary key or throws a {@link com.ext.portlet.NoSuchPlansFilterPositionException} if it could not be found.
     *
     * @param plansFilterPositionPK the primary key of the plans filter position
     * @return the plans filter position
     * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a plans filter position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansFilterPosition findByPrimaryKey(
        PlansFilterPositionPK plansFilterPositionPK)
        throws NoSuchPlansFilterPositionException, SystemException {
        PlansFilterPosition plansFilterPosition = fetchByPrimaryKey(plansFilterPositionPK);

        if (plansFilterPosition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    plansFilterPositionPK);
            }

            throw new NoSuchPlansFilterPositionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                plansFilterPositionPK);
        }

        return plansFilterPosition;
    }

    /**
     * Returns the plans filter position with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plans filter position
     * @return the plans filter position, or <code>null</code> if a plans filter position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlansFilterPosition fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((PlansFilterPositionPK) primaryKey);
    }

    /**
     * Returns the plans filter position with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param plansFilterPositionPK the primary key of the plans filter position
     * @return the plans filter position, or <code>null</code> if a plans filter position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansFilterPosition fetchByPrimaryKey(
        PlansFilterPositionPK plansFilterPositionPK) throws SystemException {
        PlansFilterPosition plansFilterPosition = (PlansFilterPosition) EntityCacheUtil.getResult(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
                PlansFilterPositionImpl.class, plansFilterPositionPK);

        if (plansFilterPosition == _nullPlansFilterPosition) {
            return null;
        }

        if (plansFilterPosition == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                plansFilterPosition = (PlansFilterPosition) session.get(PlansFilterPositionImpl.class,
                        plansFilterPositionPK);
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (plansFilterPosition != null) {
                    cacheResult(plansFilterPosition);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlansFilterPositionModelImpl.ENTITY_CACHE_ENABLED,
                        PlansFilterPositionImpl.class, plansFilterPositionPK,
                        _nullPlansFilterPosition);
                }

                closeSession(session);
            }
        }

        return plansFilterPosition;
    }

    /**
     * Returns all the plans filter positions where userId = &#63; and planTypeId = &#63;.
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @return the matching plans filter positions
     * @throws SystemException if a system exception occurred
     */
    public List<PlansFilterPosition> findByUserIdPlanTypeId(long userId,
        long planTypeId) throws SystemException {
        return findByUserIdPlanTypeId(userId, planTypeId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plans filter positions where userId = &#63; and planTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @param start the lower bound of the range of plans filter positions
     * @param end the upper bound of the range of plans filter positions (not inclusive)
     * @return the range of matching plans filter positions
     * @throws SystemException if a system exception occurred
     */
    public List<PlansFilterPosition> findByUserIdPlanTypeId(long userId,
        long planTypeId, int start, int end) throws SystemException {
        return findByUserIdPlanTypeId(userId, planTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plans filter positions where userId = &#63; and planTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @param start the lower bound of the range of plans filter positions
     * @param end the upper bound of the range of plans filter positions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plans filter positions
     * @throws SystemException if a system exception occurred
     */
    public List<PlansFilterPosition> findByUserIdPlanTypeId(long userId,
        long planTypeId, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERIDPLANTYPEID;
            finderArgs = new Object[] { userId, planTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERIDPLANTYPEID;
            finderArgs = new Object[] {
                    userId, planTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<PlansFilterPosition> list = (List<PlansFilterPosition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PLANSFILTERPOSITION_WHERE);

            query.append(_FINDER_COLUMN_USERIDPLANTYPEID_USERID_2);

            query.append(_FINDER_COLUMN_USERIDPLANTYPEID_PLANTYPEID_2);

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

                qPos.add(userId);

                qPos.add(planTypeId);

                list = (List<PlansFilterPosition>) QueryUtil.list(q,
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
     * Returns the first plans filter position in the ordered set where userId = &#63; and planTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plans filter position
     * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a matching plans filter position could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansFilterPosition findByUserIdPlanTypeId_First(long userId,
        long planTypeId, OrderByComparator orderByComparator)
        throws NoSuchPlansFilterPositionException, SystemException {
        List<PlansFilterPosition> list = findByUserIdPlanTypeId(userId,
                planTypeId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(", planTypeId=");
            msg.append(planTypeId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlansFilterPositionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last plans filter position in the ordered set where userId = &#63; and planTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plans filter position
     * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a matching plans filter position could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansFilterPosition findByUserIdPlanTypeId_Last(long userId,
        long planTypeId, OrderByComparator orderByComparator)
        throws NoSuchPlansFilterPositionException, SystemException {
        int count = countByUserIdPlanTypeId(userId, planTypeId);

        List<PlansFilterPosition> list = findByUserIdPlanTypeId(userId,
                planTypeId, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(", planTypeId=");
            msg.append(planTypeId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlansFilterPositionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the plans filter positions before and after the current plans filter position in the ordered set where userId = &#63; and planTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param plansFilterPositionPK the primary key of the current plans filter position
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plans filter position
     * @throws com.ext.portlet.NoSuchPlansFilterPositionException if a plans filter position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansFilterPosition[] findByUserIdPlanTypeId_PrevAndNext(
        PlansFilterPositionPK plansFilterPositionPK, long userId,
        long planTypeId, OrderByComparator orderByComparator)
        throws NoSuchPlansFilterPositionException, SystemException {
        PlansFilterPosition plansFilterPosition = findByPrimaryKey(plansFilterPositionPK);

        Session session = null;

        try {
            session = openSession();

            PlansFilterPosition[] array = new PlansFilterPositionImpl[3];

            array[0] = getByUserIdPlanTypeId_PrevAndNext(session,
                    plansFilterPosition, userId, planTypeId, orderByComparator,
                    true);

            array[1] = plansFilterPosition;

            array[2] = getByUserIdPlanTypeId_PrevAndNext(session,
                    plansFilterPosition, userId, planTypeId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlansFilterPosition getByUserIdPlanTypeId_PrevAndNext(
        Session session, PlansFilterPosition plansFilterPosition, long userId,
        long planTypeId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANSFILTERPOSITION_WHERE);

        query.append(_FINDER_COLUMN_USERIDPLANTYPEID_USERID_2);

        query.append(_FINDER_COLUMN_USERIDPLANTYPEID_PLANTYPEID_2);

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

        qPos.add(userId);

        qPos.add(planTypeId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(plansFilterPosition);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlansFilterPosition> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the plans filter positions.
     *
     * @return the plans filter positions
     * @throws SystemException if a system exception occurred
     */
    public List<PlansFilterPosition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plans filter positions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plans filter positions
     * @param end the upper bound of the range of plans filter positions (not inclusive)
     * @return the range of plans filter positions
     * @throws SystemException if a system exception occurred
     */
    public List<PlansFilterPosition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plans filter positions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plans filter positions
     * @param end the upper bound of the range of plans filter positions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plans filter positions
     * @throws SystemException if a system exception occurred
     */
    public List<PlansFilterPosition> findAll(int start, int end,
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

        List<PlansFilterPosition> list = (List<PlansFilterPosition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANSFILTERPOSITION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANSFILTERPOSITION;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlansFilterPosition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlansFilterPosition>) QueryUtil.list(q,
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
     * Removes all the plans filter positions where userId = &#63; and planTypeId = &#63; from the database.
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByUserIdPlanTypeId(long userId, long planTypeId)
        throws SystemException {
        for (PlansFilterPosition plansFilterPosition : findByUserIdPlanTypeId(
                userId, planTypeId)) {
            remove(plansFilterPosition);
        }
    }

    /**
     * Removes all the plans filter positions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlansFilterPosition plansFilterPosition : findAll()) {
            remove(plansFilterPosition);
        }
    }

    /**
     * Returns the number of plans filter positions where userId = &#63; and planTypeId = &#63;.
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @return the number of matching plans filter positions
     * @throws SystemException if a system exception occurred
     */
    public int countByUserIdPlanTypeId(long userId, long planTypeId)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId, planTypeId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERIDPLANTYPEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANSFILTERPOSITION_WHERE);

            query.append(_FINDER_COLUMN_USERIDPLANTYPEID_USERID_2);

            query.append(_FINDER_COLUMN_USERIDPLANTYPEID_PLANTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                qPos.add(planTypeId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERIDPLANTYPEID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plans filter positions.
     *
     * @return the number of plans filter positions
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANSFILTERPOSITION);

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
     * Initializes the plans filter position persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlansFilterPosition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlansFilterPosition>> listenersList = new ArrayList<ModelListener<PlansFilterPosition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlansFilterPosition>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlansFilterPositionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
