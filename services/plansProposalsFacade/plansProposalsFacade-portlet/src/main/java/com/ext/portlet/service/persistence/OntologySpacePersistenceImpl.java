package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchOntologySpaceException;
import com.ext.portlet.model.OntologySpace;
import com.ext.portlet.model.impl.OntologySpaceImpl;
import com.ext.portlet.model.impl.OntologySpaceModelImpl;
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
 * The persistence implementation for the ontology space service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OntologySpacePersistence
 * @see OntologySpaceUtil
 * @generated
 */
public class OntologySpacePersistenceImpl extends BasePersistenceImpl<OntologySpace>
    implements OntologySpacePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link OntologySpaceUtil} to access the ontology space persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = OntologySpaceImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_NAME = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED,
            OntologySpaceImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByName",
            new String[] { String.class.getName() },
            OntologySpaceModelImpl.NAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
            new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED,
            OntologySpaceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED,
            OntologySpaceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ONTOLOGYSPACE = "SELECT ontologySpace FROM OntologySpace ontologySpace";
    private static final String _SQL_SELECT_ONTOLOGYSPACE_WHERE = "SELECT ontologySpace FROM OntologySpace ontologySpace WHERE ";
    private static final String _SQL_COUNT_ONTOLOGYSPACE = "SELECT COUNT(ontologySpace) FROM OntologySpace ontologySpace";
    private static final String _SQL_COUNT_ONTOLOGYSPACE_WHERE = "SELECT COUNT(ontologySpace) FROM OntologySpace ontologySpace WHERE ";
    private static final String _FINDER_COLUMN_NAME_NAME_1 = "ontologySpace.name IS NULL";
    private static final String _FINDER_COLUMN_NAME_NAME_2 = "ontologySpace.name = ?";
    private static final String _FINDER_COLUMN_NAME_NAME_3 = "(ontologySpace.name IS NULL OR ontologySpace.name = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ontologySpace.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OntologySpace exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OntologySpace exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(OntologySpacePersistenceImpl.class);
    private static OntologySpace _nullOntologySpace = new OntologySpaceImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<OntologySpace> toCacheModel() {
                return _nullOntologySpaceCacheModel;
            }
        };

    private static CacheModel<OntologySpace> _nullOntologySpaceCacheModel = new CacheModel<OntologySpace>() {
            public OntologySpace toEntityModel() {
                return _nullOntologySpace;
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
     * Caches the ontology space in the entity cache if it is enabled.
     *
     * @param ontologySpace the ontology space
     */
    public void cacheResult(OntologySpace ontologySpace) {
        EntityCacheUtil.putResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceImpl.class, ontologySpace.getPrimaryKey(),
            ontologySpace);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { ontologySpace.getName() }, ontologySpace);

        ontologySpace.resetOriginalValues();
    }

    /**
     * Caches the ontology spaces in the entity cache if it is enabled.
     *
     * @param ontologySpaces the ontology spaces
     */
    public void cacheResult(List<OntologySpace> ontologySpaces) {
        for (OntologySpace ontologySpace : ontologySpaces) {
            if (EntityCacheUtil.getResult(
                        OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                        OntologySpaceImpl.class, ontologySpace.getPrimaryKey()) == null) {
                cacheResult(ontologySpace);
            } else {
                ontologySpace.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ontology spaces.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(OntologySpaceImpl.class.getName());
        }

        EntityCacheUtil.clearCache(OntologySpaceImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ontology space.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(OntologySpace ontologySpace) {
        EntityCacheUtil.removeResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceImpl.class, ontologySpace.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(ontologySpace);
    }

    @Override
    public void clearCache(List<OntologySpace> ontologySpaces) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (OntologySpace ontologySpace : ontologySpaces) {
            EntityCacheUtil.removeResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                OntologySpaceImpl.class, ontologySpace.getPrimaryKey());

            clearUniqueFindersCache(ontologySpace);
        }
    }

    protected void clearUniqueFindersCache(OntologySpace ontologySpace) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME,
            new Object[] { ontologySpace.getName() });
    }

    /**
     * Creates a new ontology space with the primary key. Does not add the ontology space to the database.
     *
     * @param id the primary key for the new ontology space
     * @return the new ontology space
     */
    public OntologySpace create(long id) {
        OntologySpace ontologySpace = new OntologySpaceImpl();

        ontologySpace.setNew(true);
        ontologySpace.setPrimaryKey(id);

        return ontologySpace;
    }

    /**
     * Removes the ontology space with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the ontology space
     * @return the ontology space that was removed
     * @throws com.ext.portlet.NoSuchOntologySpaceException if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public OntologySpace remove(long id)
        throws NoSuchOntologySpaceException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the ontology space with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ontology space
     * @return the ontology space that was removed
     * @throws com.ext.portlet.NoSuchOntologySpaceException if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace remove(Serializable primaryKey)
        throws NoSuchOntologySpaceException, SystemException {
        Session session = null;

        try {
            session = openSession();

            OntologySpace ontologySpace = (OntologySpace) session.get(OntologySpaceImpl.class,
                    primaryKey);

            if (ontologySpace == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchOntologySpaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ontologySpace);
        } catch (NoSuchOntologySpaceException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected OntologySpace removeImpl(OntologySpace ontologySpace)
        throws SystemException {
        ontologySpace = toUnwrappedModel(ontologySpace);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, ontologySpace);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(ontologySpace);

        return ontologySpace;
    }

    @Override
    public OntologySpace updateImpl(
        com.ext.portlet.model.OntologySpace ontologySpace, boolean merge)
        throws SystemException {
        ontologySpace = toUnwrappedModel(ontologySpace);

        boolean isNew = ontologySpace.isNew();

        OntologySpaceModelImpl ontologySpaceModelImpl = (OntologySpaceModelImpl) ontologySpace;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, ontologySpace, merge);

            ontologySpace.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !OntologySpaceModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
            OntologySpaceImpl.class, ontologySpace.getPrimaryKey(),
            ontologySpace);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                new Object[] { ontologySpace.getName() }, ontologySpace);
        } else {
            if ((ontologySpaceModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_NAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ontologySpaceModelImpl.getOriginalName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_NAME, args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                    new Object[] { ontologySpace.getName() }, ontologySpace);
            }
        }

        return ontologySpace;
    }

    protected OntologySpace toUnwrappedModel(OntologySpace ontologySpace) {
        if (ontologySpace instanceof OntologySpaceImpl) {
            return ontologySpace;
        }

        OntologySpaceImpl ontologySpaceImpl = new OntologySpaceImpl();

        ontologySpaceImpl.setNew(ontologySpace.isNew());
        ontologySpaceImpl.setPrimaryKey(ontologySpace.getPrimaryKey());

        ontologySpaceImpl.setId(ontologySpace.getId());
        ontologySpaceImpl.setName(ontologySpace.getName());
        ontologySpaceImpl.setDescription(ontologySpace.getDescription());

        return ontologySpaceImpl;
    }

    /**
     * Returns the ontology space with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ontology space
     * @return the ontology space
     * @throws com.liferay.portal.NoSuchModelException if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the ontology space with the primary key or throws a {@link com.ext.portlet.NoSuchOntologySpaceException} if it could not be found.
     *
     * @param id the primary key of the ontology space
     * @return the ontology space
     * @throws com.ext.portlet.NoSuchOntologySpaceException if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public OntologySpace findByPrimaryKey(long id)
        throws NoSuchOntologySpaceException, SystemException {
        OntologySpace ontologySpace = fetchByPrimaryKey(id);

        if (ontologySpace == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchOntologySpaceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return ontologySpace;
    }

    /**
     * Returns the ontology space with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ontology space
     * @return the ontology space, or <code>null</code> if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public OntologySpace fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the ontology space with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the ontology space
     * @return the ontology space, or <code>null</code> if a ontology space with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public OntologySpace fetchByPrimaryKey(long id) throws SystemException {
        OntologySpace ontologySpace = (OntologySpace) EntityCacheUtil.getResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                OntologySpaceImpl.class, id);

        if (ontologySpace == _nullOntologySpace) {
            return null;
        }

        if (ontologySpace == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                ontologySpace = (OntologySpace) session.get(OntologySpaceImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (ontologySpace != null) {
                    cacheResult(ontologySpace);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(OntologySpaceModelImpl.ENTITY_CACHE_ENABLED,
                        OntologySpaceImpl.class, id, _nullOntologySpace);
                }

                closeSession(session);
            }
        }

        return ontologySpace;
    }

    /**
     * Returns the ontology space where name = &#63; or throws a {@link com.ext.portlet.NoSuchOntologySpaceException} if it could not be found.
     *
     * @param name the name
     * @return the matching ontology space
     * @throws com.ext.portlet.NoSuchOntologySpaceException if a matching ontology space could not be found
     * @throws SystemException if a system exception occurred
     */
    public OntologySpace findByName(String name)
        throws NoSuchOntologySpaceException, SystemException {
        OntologySpace ontologySpace = fetchByName(name);

        if (ontologySpace == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("name=");
            msg.append(name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchOntologySpaceException(msg.toString());
        }

        return ontologySpace;
    }

    /**
     * Returns the ontology space where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param name the name
     * @return the matching ontology space, or <code>null</code> if a matching ontology space could not be found
     * @throws SystemException if a system exception occurred
     */
    public OntologySpace fetchByName(String name) throws SystemException {
        return fetchByName(name, true);
    }

    /**
     * Returns the ontology space where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param name the name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching ontology space, or <code>null</code> if a matching ontology space could not be found
     * @throws SystemException if a system exception occurred
     */
    public OntologySpace fetchByName(String name, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_NAME,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_SELECT_ONTOLOGYSPACE_WHERE);

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

                List<OntologySpace> list = q.list();

                result = list;

                OntologySpace ontologySpace = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                        finderArgs, list);
                } else {
                    ontologySpace = list.get(0);

                    cacheResult(ontologySpace);

                    if ((ontologySpace.getName() == null) ||
                            !ontologySpace.getName().equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_NAME,
                            finderArgs, ontologySpace);
                    }
                }

                return ontologySpace;
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
                return (OntologySpace) result;
            }
        }
    }

    /**
     * Returns all the ontology spaces.
     *
     * @return the ontology spaces
     * @throws SystemException if a system exception occurred
     */
    public List<OntologySpace> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the ontology spaces.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of ontology spaces
     * @param end the upper bound of the range of ontology spaces (not inclusive)
     * @return the range of ontology spaces
     * @throws SystemException if a system exception occurred
     */
    public List<OntologySpace> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the ontology spaces.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of ontology spaces
     * @param end the upper bound of the range of ontology spaces (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of ontology spaces
     * @throws SystemException if a system exception occurred
     */
    public List<OntologySpace> findAll(int start, int end,
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

        List<OntologySpace> list = (List<OntologySpace>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ONTOLOGYSPACE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ONTOLOGYSPACE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<OntologySpace>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<OntologySpace>) QueryUtil.list(q,
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
     * Removes the ontology space where name = &#63; from the database.
     *
     * @param name the name
     * @throws SystemException if a system exception occurred
     */
    public void removeByName(String name)
        throws NoSuchOntologySpaceException, SystemException {
        OntologySpace ontologySpace = findByName(name);

        remove(ontologySpace);
    }

    /**
     * Removes all the ontology spaces from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (OntologySpace ontologySpace : findAll()) {
            remove(ontologySpace);
        }
    }

    /**
     * Returns the number of ontology spaces where name = &#63;.
     *
     * @param name the name
     * @return the number of matching ontology spaces
     * @throws SystemException if a system exception occurred
     */
    public int countByName(String name) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAME,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_ONTOLOGYSPACE_WHERE);

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
     * Returns the number of ontology spaces.
     *
     * @return the number of ontology spaces
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_ONTOLOGYSPACE);

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
     * Initializes the ontology space persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.OntologySpace")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<OntologySpace>> listenersList = new ArrayList<ModelListener<OntologySpace>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<OntologySpace>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(OntologySpaceImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
