package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchDiscussionMessageException;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.impl.DiscussionMessageImpl;
import com.ext.portlet.model.impl.DiscussionMessageModelImpl;
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
 * The persistence implementation for the discussion message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessagePersistence
 * @see DiscussionMessageUtil
 * @generated
 */
public class DiscussionMessagePersistenceImpl extends BasePersistenceImpl<DiscussionMessage>
    implements DiscussionMessagePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DiscussionMessageUtil} to access the discussion message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DiscussionMessageImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CATEGORYIDTHREADID =
        new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCategoryIdThreadId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYIDTHREADID =
        new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByCategoryIdThreadId",
            new String[] { Long.class.getName(), Long.class.getName() },
            DiscussionMessageModelImpl.CATEGORYID_COLUMN_BITMASK |
            DiscussionMessageModelImpl.THREADID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORYIDTHREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCategoryIdThreadId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_THREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByThreadId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_THREADID =
        new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByThreadId",
            new String[] { Long.class.getName() },
            DiscussionMessageModelImpl.THREADID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_THREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByThreadId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_SINGLETHREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchBySingleThreadId", new String[] { Long.class.getName() },
            DiscussionMessageModelImpl.MESSAGEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SINGLETHREADID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySingleThreadId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBJECTLIKE =
        new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySubjectLike",
            new String[] {
                String.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBJECTLIKE =
        new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySubjectLike",
            new String[] { String.class.getName(), Long.class.getName() },
            DiscussionMessageModelImpl.SUBJECT_COLUMN_BITMASK |
            DiscussionMessageModelImpl.CATEGORYGROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SUBJECTLIKE = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySubjectLike",
            new String[] { String.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BODYLIKE = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBodyLike",
            new String[] {
                String.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BODYLIKE =
        new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBodyLike",
            new String[] { String.class.getName(), Long.class.getName() },
            DiscussionMessageModelImpl.BODY_COLUMN_BITMASK |
            DiscussionMessageModelImpl.CATEGORYGROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_BODYLIKE = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBodyLike",
            new String[] { String.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_MESSAGEID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByMessageId", new String[] { Long.class.getName() },
            DiscussionMessageModelImpl.MESSAGEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGEID = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMessageId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED,
            DiscussionMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_DISCUSSIONMESSAGE = "SELECT discussionMessage FROM DiscussionMessage discussionMessage";
    private static final String _SQL_SELECT_DISCUSSIONMESSAGE_WHERE = "SELECT discussionMessage FROM DiscussionMessage discussionMessage WHERE ";
    private static final String _SQL_COUNT_DISCUSSIONMESSAGE = "SELECT COUNT(discussionMessage) FROM DiscussionMessage discussionMessage";
    private static final String _SQL_COUNT_DISCUSSIONMESSAGE_WHERE = "SELECT COUNT(discussionMessage) FROM DiscussionMessage discussionMessage WHERE ";
    private static final String _FINDER_COLUMN_CATEGORYIDTHREADID_CATEGORYID_2 = "discussionMessage.categoryId = ? AND ";
    private static final String _FINDER_COLUMN_CATEGORYIDTHREADID_THREADID_2 = "discussionMessage.threadId = ? AND discussionMessage.deleted is null";
    private static final String _FINDER_COLUMN_THREADID_THREADID_2 = "discussionMessage.threadId = ? AND discussionMessage.deleted is null";
    private static final String _FINDER_COLUMN_SINGLETHREADID_MESSAGEID_2 = "discussionMessage.messageId = ? AND discussionMessage.deleted is null";
    private static final String _FINDER_COLUMN_SUBJECTLIKE_SUBJECT_1 = "discussionMessage.subject LIKE NULL AND ";
    private static final String _FINDER_COLUMN_SUBJECTLIKE_SUBJECT_2 = "discussionMessage.subject LIKE ? AND ";
    private static final String _FINDER_COLUMN_SUBJECTLIKE_SUBJECT_3 = "(discussionMessage.subject IS NULL OR discussionMessage.subject LIKE ?) AND ";
    private static final String _FINDER_COLUMN_SUBJECTLIKE_CATEGORYGROUPID_2 = "discussionMessage.categoryGroupId = ? AND discussionMessage.deleted is null";
    private static final String _FINDER_COLUMN_BODYLIKE_BODY_1 = "discussionMessage.body LIKE NULL AND ";
    private static final String _FINDER_COLUMN_BODYLIKE_BODY_2 = "discussionMessage.body LIKE ? AND ";
    private static final String _FINDER_COLUMN_BODYLIKE_BODY_3 = "(discussionMessage.body IS NULL OR discussionMessage.body LIKE ?) AND ";
    private static final String _FINDER_COLUMN_BODYLIKE_CATEGORYGROUPID_2 = "discussionMessage.categoryGroupId = ? AND discussionMessage.deleted is null";
    private static final String _FINDER_COLUMN_MESSAGEID_MESSAGEID_2 = "discussionMessage.messageId = ? AND discussionMessage.deleted is null";
    private static final String _ORDER_BY_ENTITY_ALIAS = "discussionMessage.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DiscussionMessage exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DiscussionMessage exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DiscussionMessagePersistenceImpl.class);
    private static DiscussionMessage _nullDiscussionMessage = new DiscussionMessageImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DiscussionMessage> toCacheModel() {
                return _nullDiscussionMessageCacheModel;
            }
        };

    private static CacheModel<DiscussionMessage> _nullDiscussionMessageCacheModel =
        new CacheModel<DiscussionMessage>() {
            public DiscussionMessage toEntityModel() {
                return _nullDiscussionMessage;
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
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the discussion message in the entity cache if it is enabled.
     *
     * @param discussionMessage the discussion message
     */
    public void cacheResult(DiscussionMessage discussionMessage) {
        EntityCacheUtil.putResult(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageImpl.class, discussionMessage.getPrimaryKey(),
            discussionMessage);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
            new Object[] { Long.valueOf(discussionMessage.getMessageId()) },
            discussionMessage);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEID,
            new Object[] { Long.valueOf(discussionMessage.getMessageId()) },
            discussionMessage);

        discussionMessage.resetOriginalValues();
    }

    /**
     * Caches the discussion messages in the entity cache if it is enabled.
     *
     * @param discussionMessages the discussion messages
     */
    public void cacheResult(List<DiscussionMessage> discussionMessages) {
        for (DiscussionMessage discussionMessage : discussionMessages) {
            if (EntityCacheUtil.getResult(
                        DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionMessageImpl.class,
                        discussionMessage.getPrimaryKey()) == null) {
                cacheResult(discussionMessage);
            } else {
                discussionMessage.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all discussion messages.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DiscussionMessageImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DiscussionMessageImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the discussion message.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DiscussionMessage discussionMessage) {
        EntityCacheUtil.removeResult(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageImpl.class, discussionMessage.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(discussionMessage);
    }

    @Override
    public void clearCache(List<DiscussionMessage> discussionMessages) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DiscussionMessage discussionMessage : discussionMessages) {
            EntityCacheUtil.removeResult(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionMessageImpl.class, discussionMessage.getPrimaryKey());

            clearUniqueFindersCache(discussionMessage);
        }
    }

    protected void clearUniqueFindersCache(DiscussionMessage discussionMessage) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
            new Object[] { Long.valueOf(discussionMessage.getMessageId()) });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGEID,
            new Object[] { Long.valueOf(discussionMessage.getMessageId()) });
    }

    /**
     * Creates a new discussion message with the primary key. Does not add the discussion message to the database.
     *
     * @param pk the primary key for the new discussion message
     * @return the new discussion message
     */
    public DiscussionMessage create(long pk) {
        DiscussionMessage discussionMessage = new DiscussionMessageImpl();

        discussionMessage.setNew(true);
        discussionMessage.setPrimaryKey(pk);

        return discussionMessage;
    }

    /**
     * Removes the discussion message with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param pk the primary key of the discussion message
     * @return the discussion message that was removed
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage remove(long pk)
        throws NoSuchDiscussionMessageException, SystemException {
        return remove(Long.valueOf(pk));
    }

    /**
     * Removes the discussion message with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the discussion message
     * @return the discussion message that was removed
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessage remove(Serializable primaryKey)
        throws NoSuchDiscussionMessageException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DiscussionMessage discussionMessage = (DiscussionMessage) session.get(DiscussionMessageImpl.class,
                    primaryKey);

            if (discussionMessage == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDiscussionMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(discussionMessage);
        } catch (NoSuchDiscussionMessageException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DiscussionMessage removeImpl(DiscussionMessage discussionMessage)
        throws SystemException {
        discussionMessage = toUnwrappedModel(discussionMessage);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, discussionMessage);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(discussionMessage);

        return discussionMessage;
    }

    @Override
    public DiscussionMessage updateImpl(
        com.ext.portlet.model.DiscussionMessage discussionMessage, boolean merge)
        throws SystemException {
        discussionMessage = toUnwrappedModel(discussionMessage);

        boolean isNew = discussionMessage.isNew();

        DiscussionMessageModelImpl discussionMessageModelImpl = (DiscussionMessageModelImpl) discussionMessage;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, discussionMessage, merge);

            discussionMessage.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !DiscussionMessageModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((discussionMessageModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYIDTHREADID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(discussionMessageModelImpl.getOriginalCategoryId()),
                        Long.valueOf(discussionMessageModelImpl.getOriginalThreadId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CATEGORYIDTHREADID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYIDTHREADID,
                    args);

                args = new Object[] {
                        Long.valueOf(discussionMessageModelImpl.getCategoryId()),
                        Long.valueOf(discussionMessageModelImpl.getThreadId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CATEGORYIDTHREADID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYIDTHREADID,
                    args);
            }

            if ((discussionMessageModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_THREADID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(discussionMessageModelImpl.getOriginalThreadId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_THREADID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_THREADID,
                    args);

                args = new Object[] {
                        Long.valueOf(discussionMessageModelImpl.getThreadId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_THREADID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_THREADID,
                    args);
            }

            if ((discussionMessageModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBJECTLIKE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        discussionMessageModelImpl.getOriginalSubject(),
                        Long.valueOf(discussionMessageModelImpl.getOriginalCategoryGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUBJECTLIKE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBJECTLIKE,
                    args);

                args = new Object[] {
                        discussionMessageModelImpl.getSubject(),
                        Long.valueOf(discussionMessageModelImpl.getCategoryGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SUBJECTLIKE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBJECTLIKE,
                    args);
            }

            if ((discussionMessageModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BODYLIKE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        discussionMessageModelImpl.getOriginalBody(),
                        Long.valueOf(discussionMessageModelImpl.getOriginalCategoryGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BODYLIKE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BODYLIKE,
                    args);

                args = new Object[] {
                        discussionMessageModelImpl.getBody(),
                        Long.valueOf(discussionMessageModelImpl.getCategoryGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BODYLIKE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BODYLIKE,
                    args);
            }
        }

        EntityCacheUtil.putResult(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionMessageImpl.class, discussionMessage.getPrimaryKey(),
            discussionMessage);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                new Object[] { Long.valueOf(discussionMessage.getMessageId()) },
                discussionMessage);

            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                new Object[] { Long.valueOf(discussionMessage.getMessageId()) },
                discussionMessage);
        } else {
            if ((discussionMessageModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_SINGLETHREADID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(discussionMessageModelImpl.getOriginalMessageId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SINGLETHREADID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                    new Object[] { Long.valueOf(
                            discussionMessage.getMessageId()) },
                    discussionMessage);
            }

            if ((discussionMessageModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_MESSAGEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(discussionMessageModelImpl.getOriginalMessageId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                    new Object[] { Long.valueOf(
                            discussionMessage.getMessageId()) },
                    discussionMessage);
            }
        }

        return discussionMessage;
    }

    protected DiscussionMessage toUnwrappedModel(
        DiscussionMessage discussionMessage) {
        if (discussionMessage instanceof DiscussionMessageImpl) {
            return discussionMessage;
        }

        DiscussionMessageImpl discussionMessageImpl = new DiscussionMessageImpl();

        discussionMessageImpl.setNew(discussionMessage.isNew());
        discussionMessageImpl.setPrimaryKey(discussionMessage.getPrimaryKey());

        discussionMessageImpl.setPk(discussionMessage.getPk());
        discussionMessageImpl.setMessageId(discussionMessage.getMessageId());
        discussionMessageImpl.setSubject(discussionMessage.getSubject());
        discussionMessageImpl.setBody(discussionMessage.getBody());
        discussionMessageImpl.setThreadId(discussionMessage.getThreadId());
        discussionMessageImpl.setCategoryId(discussionMessage.getCategoryId());
        discussionMessageImpl.setCategoryGroupId(discussionMessage.getCategoryGroupId());
        discussionMessageImpl.setAuthorId(discussionMessage.getAuthorId());
        discussionMessageImpl.setCreateDate(discussionMessage.getCreateDate());
        discussionMessageImpl.setVersion(discussionMessage.getVersion());
        discussionMessageImpl.setDeleted(discussionMessage.getDeleted());
        discussionMessageImpl.setResponsesCount(discussionMessage.getResponsesCount());
        discussionMessageImpl.setLastActivityDate(discussionMessage.getLastActivityDate());
        discussionMessageImpl.setLastActivityAuthorId(discussionMessage.getLastActivityAuthorId());

        return discussionMessageImpl;
    }

    /**
     * Returns the discussion message with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the discussion message
     * @return the discussion message
     * @throws com.liferay.portal.NoSuchModelException if a discussion message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessage findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the discussion message with the primary key or throws a {@link com.ext.portlet.NoSuchDiscussionMessageException} if it could not be found.
     *
     * @param pk the primary key of the discussion message
     * @return the discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findByPrimaryKey(long pk)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = fetchByPrimaryKey(pk);

        if (discussionMessage == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + pk);
            }

            throw new NoSuchDiscussionMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                pk);
        }

        return discussionMessage;
    }

    /**
     * Returns the discussion message with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the discussion message
     * @return the discussion message, or <code>null</code> if a discussion message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionMessage fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the discussion message with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param pk the primary key of the discussion message
     * @return the discussion message, or <code>null</code> if a discussion message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage fetchByPrimaryKey(long pk)
        throws SystemException {
        DiscussionMessage discussionMessage = (DiscussionMessage) EntityCacheUtil.getResult(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionMessageImpl.class, pk);

        if (discussionMessage == _nullDiscussionMessage) {
            return null;
        }

        if (discussionMessage == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                discussionMessage = (DiscussionMessage) session.get(DiscussionMessageImpl.class,
                        Long.valueOf(pk));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (discussionMessage != null) {
                    cacheResult(discussionMessage);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(DiscussionMessageModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionMessageImpl.class, pk, _nullDiscussionMessage);
                }

                closeSession(session);
            }
        }

        return discussionMessage;
    }

    /**
     * Returns all the discussion messages where categoryId = &#63; and threadId = &#63;.
     *
     * @param categoryId the category ID
     * @param threadId the thread ID
     * @return the matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findByCategoryIdThreadId(long categoryId,
        long threadId) throws SystemException {
        return findByCategoryIdThreadId(categoryId, threadId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the discussion messages where categoryId = &#63; and threadId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param categoryId the category ID
     * @param threadId the thread ID
     * @param start the lower bound of the range of discussion messages
     * @param end the upper bound of the range of discussion messages (not inclusive)
     * @return the range of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findByCategoryIdThreadId(long categoryId,
        long threadId, int start, int end) throws SystemException {
        return findByCategoryIdThreadId(categoryId, threadId, start, end, null);
    }

    /**
     * Returns an ordered range of all the discussion messages where categoryId = &#63; and threadId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param categoryId the category ID
     * @param threadId the thread ID
     * @param start the lower bound of the range of discussion messages
     * @param end the upper bound of the range of discussion messages (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findByCategoryIdThreadId(long categoryId,
        long threadId, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYIDTHREADID;
            finderArgs = new Object[] { categoryId, threadId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CATEGORYIDTHREADID;
            finderArgs = new Object[] {
                    categoryId, threadId,
                    
                    start, end, orderByComparator
                };
        }

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_DISCUSSIONMESSAGE_WHERE);

            query.append(_FINDER_COLUMN_CATEGORYIDTHREADID_CATEGORYID_2);

            query.append(_FINDER_COLUMN_CATEGORYIDTHREADID_THREADID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(DiscussionMessageModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(categoryId);

                qPos.add(threadId);

                list = (List<DiscussionMessage>) QueryUtil.list(q,
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
     * Returns the first discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param categoryId the category ID
     * @param threadId the thread ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findByCategoryIdThreadId_First(long categoryId,
        long threadId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        List<DiscussionMessage> list = findByCategoryIdThreadId(categoryId,
                threadId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("categoryId=");
            msg.append(categoryId);

            msg.append(", threadId=");
            msg.append(threadId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param categoryId the category ID
     * @param threadId the thread ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findByCategoryIdThreadId_Last(long categoryId,
        long threadId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        int count = countByCategoryIdThreadId(categoryId, threadId);

        List<DiscussionMessage> list = findByCategoryIdThreadId(categoryId,
                threadId, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("categoryId=");
            msg.append(categoryId);

            msg.append(", threadId=");
            msg.append(threadId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the discussion messages before and after the current discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the current discussion message
     * @param categoryId the category ID
     * @param threadId the thread ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage[] findByCategoryIdThreadId_PrevAndNext(long pk,
        long categoryId, long threadId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findByPrimaryKey(pk);

        Session session = null;

        try {
            session = openSession();

            DiscussionMessage[] array = new DiscussionMessageImpl[3];

            array[0] = getByCategoryIdThreadId_PrevAndNext(session,
                    discussionMessage, categoryId, threadId, orderByComparator,
                    true);

            array[1] = discussionMessage;

            array[2] = getByCategoryIdThreadId_PrevAndNext(session,
                    discussionMessage, categoryId, threadId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected DiscussionMessage getByCategoryIdThreadId_PrevAndNext(
        Session session, DiscussionMessage discussionMessage, long categoryId,
        long threadId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_DISCUSSIONMESSAGE_WHERE);

        query.append(_FINDER_COLUMN_CATEGORYIDTHREADID_CATEGORYID_2);

        query.append(_FINDER_COLUMN_CATEGORYIDTHREADID_THREADID_2);

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
            query.append(DiscussionMessageModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(categoryId);

        qPos.add(threadId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(discussionMessage);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<DiscussionMessage> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the discussion messages where threadId = &#63;.
     *
     * @param threadId the thread ID
     * @return the matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findByThreadId(long threadId)
        throws SystemException {
        return findByThreadId(threadId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the discussion messages where threadId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param threadId the thread ID
     * @param start the lower bound of the range of discussion messages
     * @param end the upper bound of the range of discussion messages (not inclusive)
     * @return the range of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findByThreadId(long threadId, int start,
        int end) throws SystemException {
        return findByThreadId(threadId, start, end, null);
    }

    /**
     * Returns an ordered range of all the discussion messages where threadId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param threadId the thread ID
     * @param start the lower bound of the range of discussion messages
     * @param end the upper bound of the range of discussion messages (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findByThreadId(long threadId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_THREADID;
            finderArgs = new Object[] { threadId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_THREADID;
            finderArgs = new Object[] { threadId, start, end, orderByComparator };
        }

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_DISCUSSIONMESSAGE_WHERE);

            query.append(_FINDER_COLUMN_THREADID_THREADID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(DiscussionMessageModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(threadId);

                list = (List<DiscussionMessage>) QueryUtil.list(q,
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
     * Returns the first discussion message in the ordered set where threadId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param threadId the thread ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findByThreadId_First(long threadId,
        OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        List<DiscussionMessage> list = findByThreadId(threadId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("threadId=");
            msg.append(threadId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last discussion message in the ordered set where threadId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param threadId the thread ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findByThreadId_Last(long threadId,
        OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        int count = countByThreadId(threadId);

        List<DiscussionMessage> list = findByThreadId(threadId, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("threadId=");
            msg.append(threadId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the discussion messages before and after the current discussion message in the ordered set where threadId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the current discussion message
     * @param threadId the thread ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage[] findByThreadId_PrevAndNext(long pk,
        long threadId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findByPrimaryKey(pk);

        Session session = null;

        try {
            session = openSession();

            DiscussionMessage[] array = new DiscussionMessageImpl[3];

            array[0] = getByThreadId_PrevAndNext(session, discussionMessage,
                    threadId, orderByComparator, true);

            array[1] = discussionMessage;

            array[2] = getByThreadId_PrevAndNext(session, discussionMessage,
                    threadId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected DiscussionMessage getByThreadId_PrevAndNext(Session session,
        DiscussionMessage discussionMessage, long threadId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_DISCUSSIONMESSAGE_WHERE);

        query.append(_FINDER_COLUMN_THREADID_THREADID_2);

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
            query.append(DiscussionMessageModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(threadId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(discussionMessage);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<DiscussionMessage> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the discussion message where messageId = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionMessageException} if it could not be found.
     *
     * @param messageId the message ID
     * @return the matching discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findBySingleThreadId(long messageId)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = fetchBySingleThreadId(messageId);

        if (discussionMessage == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("messageId=");
            msg.append(messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDiscussionMessageException(msg.toString());
        }

        return discussionMessage;
    }

    /**
     * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param messageId the message ID
     * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage fetchBySingleThreadId(long messageId)
        throws SystemException {
        return fetchBySingleThreadId(messageId, true);
    }

    /**
     * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param messageId the message ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage fetchBySingleThreadId(long messageId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_DISCUSSIONMESSAGE_WHERE);

            query.append(_FINDER_COLUMN_SINGLETHREADID_MESSAGEID_2);

            query.append(DiscussionMessageModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                List<DiscussionMessage> list = q.list();

                result = list;

                DiscussionMessage discussionMessage = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                        finderArgs, list);
                } else {
                    discussionMessage = list.get(0);

                    cacheResult(discussionMessage);

                    if ((discussionMessage.getMessageId() != messageId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                            finderArgs, discussionMessage);
                    }
                }

                return discussionMessage;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SINGLETHREADID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (DiscussionMessage) result;
            }
        }
    }

    /**
     * Returns all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
     *
     * @param subject the subject
     * @param categoryGroupId the category group ID
     * @return the matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findBySubjectLike(String subject,
        long categoryGroupId) throws SystemException {
        return findBySubjectLike(subject, categoryGroupId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param subject the subject
     * @param categoryGroupId the category group ID
     * @param start the lower bound of the range of discussion messages
     * @param end the upper bound of the range of discussion messages (not inclusive)
     * @return the range of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findBySubjectLike(String subject,
        long categoryGroupId, int start, int end) throws SystemException {
        return findBySubjectLike(subject, categoryGroupId, start, end, null);
    }

    /**
     * Returns an ordered range of all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param subject the subject
     * @param categoryGroupId the category group ID
     * @param start the lower bound of the range of discussion messages
     * @param end the upper bound of the range of discussion messages (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findBySubjectLike(String subject,
        long categoryGroupId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SUBJECTLIKE;
            finderArgs = new Object[] { subject, categoryGroupId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SUBJECTLIKE;
            finderArgs = new Object[] {
                    subject, categoryGroupId,
                    
                    start, end, orderByComparator
                };
        }

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_DISCUSSIONMESSAGE_WHERE);

            if (subject == null) {
                query.append(_FINDER_COLUMN_SUBJECTLIKE_SUBJECT_1);
            } else {
                if (subject.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_SUBJECTLIKE_SUBJECT_3);
                } else {
                    query.append(_FINDER_COLUMN_SUBJECTLIKE_SUBJECT_2);
                }
            }

            query.append(_FINDER_COLUMN_SUBJECTLIKE_CATEGORYGROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(DiscussionMessageModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (subject != null) {
                    qPos.add(subject);
                }

                qPos.add(categoryGroupId);

                list = (List<DiscussionMessage>) QueryUtil.list(q,
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
     * Returns the first discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param subject the subject
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findBySubjectLike_First(String subject,
        long categoryGroupId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        List<DiscussionMessage> list = findBySubjectLike(subject,
                categoryGroupId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("subject=");
            msg.append(subject);

            msg.append(", categoryGroupId=");
            msg.append(categoryGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param subject the subject
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findBySubjectLike_Last(String subject,
        long categoryGroupId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        int count = countBySubjectLike(subject, categoryGroupId);

        List<DiscussionMessage> list = findBySubjectLike(subject,
                categoryGroupId, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("subject=");
            msg.append(subject);

            msg.append(", categoryGroupId=");
            msg.append(categoryGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the discussion messages before and after the current discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the current discussion message
     * @param subject the subject
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage[] findBySubjectLike_PrevAndNext(long pk,
        String subject, long categoryGroupId,
        OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findByPrimaryKey(pk);

        Session session = null;

        try {
            session = openSession();

            DiscussionMessage[] array = new DiscussionMessageImpl[3];

            array[0] = getBySubjectLike_PrevAndNext(session, discussionMessage,
                    subject, categoryGroupId, orderByComparator, true);

            array[1] = discussionMessage;

            array[2] = getBySubjectLike_PrevAndNext(session, discussionMessage,
                    subject, categoryGroupId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected DiscussionMessage getBySubjectLike_PrevAndNext(Session session,
        DiscussionMessage discussionMessage, String subject,
        long categoryGroupId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_DISCUSSIONMESSAGE_WHERE);

        if (subject == null) {
            query.append(_FINDER_COLUMN_SUBJECTLIKE_SUBJECT_1);
        } else {
            if (subject.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_SUBJECTLIKE_SUBJECT_3);
            } else {
                query.append(_FINDER_COLUMN_SUBJECTLIKE_SUBJECT_2);
            }
        }

        query.append(_FINDER_COLUMN_SUBJECTLIKE_CATEGORYGROUPID_2);

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
            query.append(DiscussionMessageModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (subject != null) {
            qPos.add(subject);
        }

        qPos.add(categoryGroupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(discussionMessage);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<DiscussionMessage> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
     *
     * @param body the body
     * @param categoryGroupId the category group ID
     * @return the matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findByBodyLike(String body,
        long categoryGroupId) throws SystemException {
        return findByBodyLike(body, categoryGroupId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param body the body
     * @param categoryGroupId the category group ID
     * @param start the lower bound of the range of discussion messages
     * @param end the upper bound of the range of discussion messages (not inclusive)
     * @return the range of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findByBodyLike(String body,
        long categoryGroupId, int start, int end) throws SystemException {
        return findByBodyLike(body, categoryGroupId, start, end, null);
    }

    /**
     * Returns an ordered range of all the discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param body the body
     * @param categoryGroupId the category group ID
     * @param start the lower bound of the range of discussion messages
     * @param end the upper bound of the range of discussion messages (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findByBodyLike(String body,
        long categoryGroupId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BODYLIKE;
            finderArgs = new Object[] { body, categoryGroupId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BODYLIKE;
            finderArgs = new Object[] {
                    body, categoryGroupId,
                    
                    start, end, orderByComparator
                };
        }

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_DISCUSSIONMESSAGE_WHERE);

            if (body == null) {
                query.append(_FINDER_COLUMN_BODYLIKE_BODY_1);
            } else {
                if (body.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_BODYLIKE_BODY_3);
                } else {
                    query.append(_FINDER_COLUMN_BODYLIKE_BODY_2);
                }
            }

            query.append(_FINDER_COLUMN_BODYLIKE_CATEGORYGROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(DiscussionMessageModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (body != null) {
                    qPos.add(body);
                }

                qPos.add(categoryGroupId);

                list = (List<DiscussionMessage>) QueryUtil.list(q,
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
     * Returns the first discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param body the body
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findByBodyLike_First(String body,
        long categoryGroupId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        List<DiscussionMessage> list = findByBodyLike(body, categoryGroupId, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("body=");
            msg.append(body);

            msg.append(", categoryGroupId=");
            msg.append(categoryGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param body the body
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findByBodyLike_Last(String body,
        long categoryGroupId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        int count = countByBodyLike(body, categoryGroupId);

        List<DiscussionMessage> list = findByBodyLike(body, categoryGroupId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("body=");
            msg.append(body);

            msg.append(", categoryGroupId=");
            msg.append(categoryGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchDiscussionMessageException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the discussion messages before and after the current discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the current discussion message
     * @param body the body
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage[] findByBodyLike_PrevAndNext(long pk, String body,
        long categoryGroupId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findByPrimaryKey(pk);

        Session session = null;

        try {
            session = openSession();

            DiscussionMessage[] array = new DiscussionMessageImpl[3];

            array[0] = getByBodyLike_PrevAndNext(session, discussionMessage,
                    body, categoryGroupId, orderByComparator, true);

            array[1] = discussionMessage;

            array[2] = getByBodyLike_PrevAndNext(session, discussionMessage,
                    body, categoryGroupId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected DiscussionMessage getByBodyLike_PrevAndNext(Session session,
        DiscussionMessage discussionMessage, String body, long categoryGroupId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_DISCUSSIONMESSAGE_WHERE);

        if (body == null) {
            query.append(_FINDER_COLUMN_BODYLIKE_BODY_1);
        } else {
            if (body.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_BODYLIKE_BODY_3);
            } else {
                query.append(_FINDER_COLUMN_BODYLIKE_BODY_2);
            }
        }

        query.append(_FINDER_COLUMN_BODYLIKE_CATEGORYGROUPID_2);

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
            query.append(DiscussionMessageModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (body != null) {
            qPos.add(body);
        }

        qPos.add(categoryGroupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(discussionMessage);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<DiscussionMessage> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the discussion message where messageId = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionMessageException} if it could not be found.
     *
     * @param messageId the message ID
     * @return the matching discussion message
     * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage findByMessageId(long messageId)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = fetchByMessageId(messageId);

        if (discussionMessage == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("messageId=");
            msg.append(messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDiscussionMessageException(msg.toString());
        }

        return discussionMessage;
    }

    /**
     * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param messageId the message ID
     * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage fetchByMessageId(long messageId)
        throws SystemException {
        return fetchByMessageId(messageId, true);
    }

    /**
     * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param messageId the message ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
     * @throws SystemException if a system exception occurred
     */
    public DiscussionMessage fetchByMessageId(long messageId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_DISCUSSIONMESSAGE_WHERE);

            query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

            query.append(DiscussionMessageModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                List<DiscussionMessage> list = q.list();

                result = list;

                DiscussionMessage discussionMessage = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                        finderArgs, list);
                } else {
                    discussionMessage = list.get(0);

                    cacheResult(discussionMessage);

                    if ((discussionMessage.getMessageId() != messageId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                            finderArgs, discussionMessage);
                    }
                }

                return discussionMessage;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGEID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (DiscussionMessage) result;
            }
        }
    }

    /**
     * Returns all the discussion messages.
     *
     * @return the discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the discussion messages.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of discussion messages
     * @param end the upper bound of the range of discussion messages (not inclusive)
     * @return the range of discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the discussion messages.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of discussion messages
     * @param end the upper bound of the range of discussion messages (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of discussion messages
     * @throws SystemException if a system exception occurred
     */
    public List<DiscussionMessage> findAll(int start, int end,
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

        List<DiscussionMessage> list = (List<DiscussionMessage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DISCUSSIONMESSAGE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DISCUSSIONMESSAGE.concat(DiscussionMessageModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<DiscussionMessage>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<DiscussionMessage>) QueryUtil.list(q,
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
     * Removes all the discussion messages where categoryId = &#63; and threadId = &#63; from the database.
     *
     * @param categoryId the category ID
     * @param threadId the thread ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByCategoryIdThreadId(long categoryId, long threadId)
        throws SystemException {
        for (DiscussionMessage discussionMessage : findByCategoryIdThreadId(
                categoryId, threadId)) {
            remove(discussionMessage);
        }
    }

    /**
     * Removes all the discussion messages where threadId = &#63; from the database.
     *
     * @param threadId the thread ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByThreadId(long threadId) throws SystemException {
        for (DiscussionMessage discussionMessage : findByThreadId(threadId)) {
            remove(discussionMessage);
        }
    }

    /**
     * Removes the discussion message where messageId = &#63; from the database.
     *
     * @param messageId the message ID
     * @throws SystemException if a system exception occurred
     */
    public void removeBySingleThreadId(long messageId)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findBySingleThreadId(messageId);

        remove(discussionMessage);
    }

    /**
     * Removes all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63; from the database.
     *
     * @param subject the subject
     * @param categoryGroupId the category group ID
     * @throws SystemException if a system exception occurred
     */
    public void removeBySubjectLike(String subject, long categoryGroupId)
        throws SystemException {
        for (DiscussionMessage discussionMessage : findBySubjectLike(subject,
                categoryGroupId)) {
            remove(discussionMessage);
        }
    }

    /**
     * Removes all the discussion messages where body LIKE &#63; and categoryGroupId = &#63; from the database.
     *
     * @param body the body
     * @param categoryGroupId the category group ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByBodyLike(String body, long categoryGroupId)
        throws SystemException {
        for (DiscussionMessage discussionMessage : findByBodyLike(body,
                categoryGroupId)) {
            remove(discussionMessage);
        }
    }

    /**
     * Removes the discussion message where messageId = &#63; from the database.
     *
     * @param messageId the message ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByMessageId(long messageId)
        throws NoSuchDiscussionMessageException, SystemException {
        DiscussionMessage discussionMessage = findByMessageId(messageId);

        remove(discussionMessage);
    }

    /**
     * Removes all the discussion messages from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (DiscussionMessage discussionMessage : findAll()) {
            remove(discussionMessage);
        }
    }

    /**
     * Returns the number of discussion messages where categoryId = &#63; and threadId = &#63;.
     *
     * @param categoryId the category ID
     * @param threadId the thread ID
     * @return the number of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public int countByCategoryIdThreadId(long categoryId, long threadId)
        throws SystemException {
        Object[] finderArgs = new Object[] { categoryId, threadId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CATEGORYIDTHREADID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_DISCUSSIONMESSAGE_WHERE);

            query.append(_FINDER_COLUMN_CATEGORYIDTHREADID_CATEGORYID_2);

            query.append(_FINDER_COLUMN_CATEGORYIDTHREADID_THREADID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(categoryId);

                qPos.add(threadId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CATEGORYIDTHREADID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of discussion messages where threadId = &#63;.
     *
     * @param threadId the thread ID
     * @return the number of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public int countByThreadId(long threadId) throws SystemException {
        Object[] finderArgs = new Object[] { threadId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_THREADID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_DISCUSSIONMESSAGE_WHERE);

            query.append(_FINDER_COLUMN_THREADID_THREADID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(threadId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_THREADID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of discussion messages where messageId = &#63;.
     *
     * @param messageId the message ID
     * @return the number of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public int countBySingleThreadId(long messageId) throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SINGLETHREADID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_DISCUSSIONMESSAGE_WHERE);

            query.append(_FINDER_COLUMN_SINGLETHREADID_MESSAGEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SINGLETHREADID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
     *
     * @param subject the subject
     * @param categoryGroupId the category group ID
     * @return the number of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public int countBySubjectLike(String subject, long categoryGroupId)
        throws SystemException {
        Object[] finderArgs = new Object[] { subject, categoryGroupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SUBJECTLIKE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_DISCUSSIONMESSAGE_WHERE);

            if (subject == null) {
                query.append(_FINDER_COLUMN_SUBJECTLIKE_SUBJECT_1);
            } else {
                if (subject.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_SUBJECTLIKE_SUBJECT_3);
                } else {
                    query.append(_FINDER_COLUMN_SUBJECTLIKE_SUBJECT_2);
                }
            }

            query.append(_FINDER_COLUMN_SUBJECTLIKE_CATEGORYGROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (subject != null) {
                    qPos.add(subject);
                }

                qPos.add(categoryGroupId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SUBJECTLIKE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
     *
     * @param body the body
     * @param categoryGroupId the category group ID
     * @return the number of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public int countByBodyLike(String body, long categoryGroupId)
        throws SystemException {
        Object[] finderArgs = new Object[] { body, categoryGroupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_BODYLIKE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_DISCUSSIONMESSAGE_WHERE);

            if (body == null) {
                query.append(_FINDER_COLUMN_BODYLIKE_BODY_1);
            } else {
                if (body.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_BODYLIKE_BODY_3);
                } else {
                    query.append(_FINDER_COLUMN_BODYLIKE_BODY_2);
                }
            }

            query.append(_FINDER_COLUMN_BODYLIKE_CATEGORYGROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (body != null) {
                    qPos.add(body);
                }

                qPos.add(categoryGroupId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_BODYLIKE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of discussion messages where messageId = &#63;.
     *
     * @param messageId the message ID
     * @return the number of matching discussion messages
     * @throws SystemException if a system exception occurred
     */
    public int countByMessageId(long messageId) throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MESSAGEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_DISCUSSIONMESSAGE_WHERE);

            query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MESSAGEID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of discussion messages.
     *
     * @return the number of discussion messages
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_DISCUSSIONMESSAGE);

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
     * Initializes the discussion message persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.DiscussionMessage")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DiscussionMessage>> listenersList = new ArrayList<ModelListener<DiscussionMessage>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DiscussionMessage>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DiscussionMessageImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
