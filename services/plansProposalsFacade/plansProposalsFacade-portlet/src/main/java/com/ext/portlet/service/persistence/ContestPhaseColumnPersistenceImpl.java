package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestPhaseColumnException;
import com.ext.portlet.model.ContestPhaseColumn;
import com.ext.portlet.model.impl.ContestPhaseColumnImpl;
import com.ext.portlet.model.impl.ContestPhaseColumnModelImpl;
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
import com.ext.portlet.service.persistence.Plan2ProposalPersistence;
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
import com.ext.portlet.service.persistence.ProposalSupporterPersistence;
import com.ext.portlet.service.persistence.ProposalVersionPersistence;
import com.ext.portlet.service.persistence.ProposalVotePersistence;

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
 * The persistence implementation for the contest phase column service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseColumnPersistence
 * @see ContestPhaseColumnUtil
 * @generated
 */
public class ContestPhaseColumnPersistenceImpl extends BasePersistenceImpl<ContestPhaseColumn>
    implements ContestPhaseColumnPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestPhaseColumnUtil} to access the contest phase column persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestPhaseColumnImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTPHASEPK =
        new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED,
            ContestPhaseColumnImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContestPhasePK",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEPK =
        new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED,
            ContestPhaseColumnImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContestPhasePK",
            new String[] { Long.class.getName() },
            ContestPhaseColumnModelImpl.CONTESTPHASEPK_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTPHASEPK = new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContestPhasePK",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED,
            ContestPhaseColumnImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED,
            ContestPhaseColumnImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTESTPHASECOLUMN = "SELECT contestPhaseColumn FROM ContestPhaseColumn contestPhaseColumn";
    private static final String _SQL_SELECT_CONTESTPHASECOLUMN_WHERE = "SELECT contestPhaseColumn FROM ContestPhaseColumn contestPhaseColumn WHERE ";
    private static final String _SQL_COUNT_CONTESTPHASECOLUMN = "SELECT COUNT(contestPhaseColumn) FROM ContestPhaseColumn contestPhaseColumn";
    private static final String _SQL_COUNT_CONTESTPHASECOLUMN_WHERE = "SELECT COUNT(contestPhaseColumn) FROM ContestPhaseColumn contestPhaseColumn WHERE ";
    private static final String _FINDER_COLUMN_CONTESTPHASEPK_CONTESTPHASEPK_2 = "contestPhaseColumn.ContestPhasePK = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestPhaseColumn.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestPhaseColumn exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContestPhaseColumn exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestPhaseColumnPersistenceImpl.class);
    private static ContestPhaseColumn _nullContestPhaseColumn = new ContestPhaseColumnImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestPhaseColumn> toCacheModel() {
                return _nullContestPhaseColumnCacheModel;
            }
        };

    private static CacheModel<ContestPhaseColumn> _nullContestPhaseColumnCacheModel =
        new CacheModel<ContestPhaseColumn>() {
            public ContestPhaseColumn toEntityModel() {
                return _nullContestPhaseColumn;
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
    @BeanReference(type = Plan2ProposalPersistence.class)
    protected Plan2ProposalPersistence plan2ProposalPersistence;
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
    @BeanReference(type = ProposalSupporterPersistence.class)
    protected ProposalSupporterPersistence proposalSupporterPersistence;
    @BeanReference(type = ProposalVersionPersistence.class)
    protected ProposalVersionPersistence proposalVersionPersistence;
    @BeanReference(type = ProposalVotePersistence.class)
    protected ProposalVotePersistence proposalVotePersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the contest phase column in the entity cache if it is enabled.
     *
     * @param contestPhaseColumn the contest phase column
     */
    public void cacheResult(ContestPhaseColumn contestPhaseColumn) {
        EntityCacheUtil.putResult(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnImpl.class, contestPhaseColumn.getPrimaryKey(),
            contestPhaseColumn);

        contestPhaseColumn.resetOriginalValues();
    }

    /**
     * Caches the contest phase columns in the entity cache if it is enabled.
     *
     * @param contestPhaseColumns the contest phase columns
     */
    public void cacheResult(List<ContestPhaseColumn> contestPhaseColumns) {
        for (ContestPhaseColumn contestPhaseColumn : contestPhaseColumns) {
            if (EntityCacheUtil.getResult(
                        ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseColumnImpl.class,
                        contestPhaseColumn.getPrimaryKey()) == null) {
                cacheResult(contestPhaseColumn);
            } else {
                contestPhaseColumn.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest phase columns.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestPhaseColumnImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestPhaseColumnImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest phase column.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestPhaseColumn contestPhaseColumn) {
        EntityCacheUtil.removeResult(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnImpl.class, contestPhaseColumn.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContestPhaseColumn> contestPhaseColumns) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestPhaseColumn contestPhaseColumn : contestPhaseColumns) {
            EntityCacheUtil.removeResult(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseColumnImpl.class, contestPhaseColumn.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest phase column with the primary key. Does not add the contest phase column to the database.
     *
     * @param id the primary key for the new contest phase column
     * @return the new contest phase column
     */
    public ContestPhaseColumn create(long id) {
        ContestPhaseColumn contestPhaseColumn = new ContestPhaseColumnImpl();

        contestPhaseColumn.setNew(true);
        contestPhaseColumn.setPrimaryKey(id);

        return contestPhaseColumn;
    }

    /**
     * Removes the contest phase column with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the contest phase column
     * @return the contest phase column that was removed
     * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a contest phase column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhaseColumn remove(long id)
        throws NoSuchContestPhaseColumnException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the contest phase column with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest phase column
     * @return the contest phase column that was removed
     * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a contest phase column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseColumn remove(Serializable primaryKey)
        throws NoSuchContestPhaseColumnException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestPhaseColumn contestPhaseColumn = (ContestPhaseColumn) session.get(ContestPhaseColumnImpl.class,
                    primaryKey);

            if (contestPhaseColumn == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestPhaseColumnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestPhaseColumn);
        } catch (NoSuchContestPhaseColumnException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestPhaseColumn removeImpl(
        ContestPhaseColumn contestPhaseColumn) throws SystemException {
        contestPhaseColumn = toUnwrappedModel(contestPhaseColumn);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, contestPhaseColumn);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(contestPhaseColumn);

        return contestPhaseColumn;
    }

    @Override
    public ContestPhaseColumn updateImpl(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn,
        boolean merge) throws SystemException {
        contestPhaseColumn = toUnwrappedModel(contestPhaseColumn);

        boolean isNew = contestPhaseColumn.isNew();

        ContestPhaseColumnModelImpl contestPhaseColumnModelImpl = (ContestPhaseColumnModelImpl) contestPhaseColumn;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contestPhaseColumn, merge);

            contestPhaseColumn.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ContestPhaseColumnModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((contestPhaseColumnModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEPK.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(contestPhaseColumnModelImpl.getOriginalContestPhasePK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTPHASEPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEPK,
                    args);

                args = new Object[] {
                        Long.valueOf(contestPhaseColumnModelImpl.getContestPhasePK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTPHASEPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEPK,
                    args);
            }
        }

        EntityCacheUtil.putResult(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseColumnImpl.class, contestPhaseColumn.getPrimaryKey(),
            contestPhaseColumn);

        return contestPhaseColumn;
    }

    protected ContestPhaseColumn toUnwrappedModel(
        ContestPhaseColumn contestPhaseColumn) {
        if (contestPhaseColumn instanceof ContestPhaseColumnImpl) {
            return contestPhaseColumn;
        }

        ContestPhaseColumnImpl contestPhaseColumnImpl = new ContestPhaseColumnImpl();

        contestPhaseColumnImpl.setNew(contestPhaseColumn.isNew());
        contestPhaseColumnImpl.setPrimaryKey(contestPhaseColumn.getPrimaryKey());

        contestPhaseColumnImpl.setId(contestPhaseColumn.getId());
        contestPhaseColumnImpl.setContestPhasePK(contestPhaseColumn.getContestPhasePK());
        contestPhaseColumnImpl.setColumnName(contestPhaseColumn.getColumnName());
        contestPhaseColumnImpl.setColumnWeight(contestPhaseColumn.getColumnWeight());
        contestPhaseColumnImpl.setDefaultSort(contestPhaseColumn.isDefaultSort());

        return contestPhaseColumnImpl;
    }

    /**
     * Returns the contest phase column with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest phase column
     * @return the contest phase column
     * @throws com.liferay.portal.NoSuchModelException if a contest phase column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseColumn findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contest phase column with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseColumnException} if it could not be found.
     *
     * @param id the primary key of the contest phase column
     * @return the contest phase column
     * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a contest phase column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhaseColumn findByPrimaryKey(long id)
        throws NoSuchContestPhaseColumnException, SystemException {
        ContestPhaseColumn contestPhaseColumn = fetchByPrimaryKey(id);

        if (contestPhaseColumn == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchContestPhaseColumnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return contestPhaseColumn;
    }

    /**
     * Returns the contest phase column with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest phase column
     * @return the contest phase column, or <code>null</code> if a contest phase column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseColumn fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contest phase column with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the contest phase column
     * @return the contest phase column, or <code>null</code> if a contest phase column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhaseColumn fetchByPrimaryKey(long id)
        throws SystemException {
        ContestPhaseColumn contestPhaseColumn = (ContestPhaseColumn) EntityCacheUtil.getResult(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseColumnImpl.class, id);

        if (contestPhaseColumn == _nullContestPhaseColumn) {
            return null;
        }

        if (contestPhaseColumn == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                contestPhaseColumn = (ContestPhaseColumn) session.get(ContestPhaseColumnImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (contestPhaseColumn != null) {
                    cacheResult(contestPhaseColumn);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ContestPhaseColumnModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseColumnImpl.class, id,
                        _nullContestPhaseColumn);
                }

                closeSession(session);
            }
        }

        return contestPhaseColumn;
    }

    /**
     * Returns all the contest phase columns where ContestPhasePK = &#63;.
     *
     * @param ContestPhasePK the contest phase p k
     * @return the matching contest phase columns
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhaseColumn> findByContestPhasePK(long ContestPhasePK)
        throws SystemException {
        return findByContestPhasePK(ContestPhasePK, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phase columns where ContestPhasePK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPhasePK the contest phase p k
     * @param start the lower bound of the range of contest phase columns
     * @param end the upper bound of the range of contest phase columns (not inclusive)
     * @return the range of matching contest phase columns
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhaseColumn> findByContestPhasePK(long ContestPhasePK,
        int start, int end) throws SystemException {
        return findByContestPhasePK(ContestPhasePK, start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phase columns where ContestPhasePK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPhasePK the contest phase p k
     * @param start the lower bound of the range of contest phase columns
     * @param end the upper bound of the range of contest phase columns (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contest phase columns
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhaseColumn> findByContestPhasePK(long ContestPhasePK,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPHASEPK;
            finderArgs = new Object[] { ContestPhasePK };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTPHASEPK;
            finderArgs = new Object[] {
                    ContestPhasePK,
                    
                    start, end, orderByComparator
                };
        }

        List<ContestPhaseColumn> list = (List<ContestPhaseColumn>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_CONTESTPHASECOLUMN_WHERE);

            query.append(_FINDER_COLUMN_CONTESTPHASEPK_CONTESTPHASEPK_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(ContestPhaseColumnModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPhasePK);

                list = (List<ContestPhaseColumn>) QueryUtil.list(q,
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
     * Returns the first contest phase column in the ordered set where ContestPhasePK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPhasePK the contest phase p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest phase column
     * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a matching contest phase column could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhaseColumn findByContestPhasePK_First(long ContestPhasePK,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseColumnException, SystemException {
        List<ContestPhaseColumn> list = findByContestPhasePK(ContestPhasePK, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("ContestPhasePK=");
            msg.append(ContestPhasePK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestPhaseColumnException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last contest phase column in the ordered set where ContestPhasePK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPhasePK the contest phase p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest phase column
     * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a matching contest phase column could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhaseColumn findByContestPhasePK_Last(long ContestPhasePK,
        OrderByComparator orderByComparator)
        throws NoSuchContestPhaseColumnException, SystemException {
        int count = countByContestPhasePK(ContestPhasePK);

        List<ContestPhaseColumn> list = findByContestPhasePK(ContestPhasePK,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("ContestPhasePK=");
            msg.append(ContestPhasePK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestPhaseColumnException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the contest phase columns before and after the current contest phase column in the ordered set where ContestPhasePK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param id the primary key of the current contest phase column
     * @param ContestPhasePK the contest phase p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest phase column
     * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a contest phase column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhaseColumn[] findByContestPhasePK_PrevAndNext(long id,
        long ContestPhasePK, OrderByComparator orderByComparator)
        throws NoSuchContestPhaseColumnException, SystemException {
        ContestPhaseColumn contestPhaseColumn = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ContestPhaseColumn[] array = new ContestPhaseColumnImpl[3];

            array[0] = getByContestPhasePK_PrevAndNext(session,
                    contestPhaseColumn, ContestPhasePK, orderByComparator, true);

            array[1] = contestPhaseColumn;

            array[2] = getByContestPhasePK_PrevAndNext(session,
                    contestPhaseColumn, ContestPhasePK, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ContestPhaseColumn getByContestPhasePK_PrevAndNext(
        Session session, ContestPhaseColumn contestPhaseColumn,
        long ContestPhasePK, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTESTPHASECOLUMN_WHERE);

        query.append(_FINDER_COLUMN_CONTESTPHASEPK_CONTESTPHASEPK_2);

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
        else {
            query.append(ContestPhaseColumnModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ContestPhasePK);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contestPhaseColumn);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ContestPhaseColumn> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the contest phase columns.
     *
     * @return the contest phase columns
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhaseColumn> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phase columns.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contest phase columns
     * @param end the upper bound of the range of contest phase columns (not inclusive)
     * @return the range of contest phase columns
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhaseColumn> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phase columns.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contest phase columns
     * @param end the upper bound of the range of contest phase columns (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest phase columns
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhaseColumn> findAll(int start, int end,
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

        List<ContestPhaseColumn> list = (List<ContestPhaseColumn>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTPHASECOLUMN);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTPHASECOLUMN.concat(ContestPhaseColumnModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ContestPhaseColumn>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ContestPhaseColumn>) QueryUtil.list(q,
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
     * Removes all the contest phase columns where ContestPhasePK = &#63; from the database.
     *
     * @param ContestPhasePK the contest phase p k
     * @throws SystemException if a system exception occurred
     */
    public void removeByContestPhasePK(long ContestPhasePK)
        throws SystemException {
        for (ContestPhaseColumn contestPhaseColumn : findByContestPhasePK(
                ContestPhasePK)) {
            remove(contestPhaseColumn);
        }
    }

    /**
     * Removes all the contest phase columns from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ContestPhaseColumn contestPhaseColumn : findAll()) {
            remove(contestPhaseColumn);
        }
    }

    /**
     * Returns the number of contest phase columns where ContestPhasePK = &#63;.
     *
     * @param ContestPhasePK the contest phase p k
     * @return the number of matching contest phase columns
     * @throws SystemException if a system exception occurred
     */
    public int countByContestPhasePK(long ContestPhasePK)
        throws SystemException {
        Object[] finderArgs = new Object[] { ContestPhasePK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTPHASEPK,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTESTPHASECOLUMN_WHERE);

            query.append(_FINDER_COLUMN_CONTESTPHASEPK_CONTESTPHASEPK_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPhasePK);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTPHASEPK,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contest phase columns.
     *
     * @return the number of contest phase columns
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_CONTESTPHASECOLUMN);

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
     * Initializes the contest phase column persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestPhaseColumn")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestPhaseColumn>> listenersList = new ArrayList<ModelListener<ContestPhaseColumn>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestPhaseColumn>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestPhaseColumnImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
