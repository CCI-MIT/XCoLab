package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMessageRecipientStatusException;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.model.impl.MessageRecipientStatusImpl;
import com.ext.portlet.model.impl.MessageRecipientStatusModelImpl;
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
 * The persistence implementation for the message recipient status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientStatusPersistence
 * @see MessageRecipientStatusUtil
 * @generated
 */
public class MessageRecipientStatusPersistenceImpl extends BasePersistenceImpl<MessageRecipientStatus>
    implements MessageRecipientStatusPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MessageRecipientStatusUtil} to access the message recipient status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MessageRecipientStatusImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RECEIVINGUSER =
        new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByReceivingUser",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVINGUSER =
        new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByReceivingUser",
            new String[] { Long.class.getName() },
            MessageRecipientStatusModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_RECEIVINGUSER = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByReceivingUser",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_RECEIVINGUSERARCHIVED =
        new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByReceivingUserArchived",
            new String[] {
                Long.class.getName(), Boolean.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVINGUSERARCHIVED =
        new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByReceivingUserArchived",
            new String[] { Long.class.getName(), Boolean.class.getName() },
            MessageRecipientStatusModelImpl.USERID_COLUMN_BITMASK |
            MessageRecipientStatusModelImpl.ARCHIVED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_RECEIVINGUSERARCHIVED = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByReceivingUserArchived",
            new String[] { Long.class.getName(), Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MESSAGEID =
        new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMessageId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID =
        new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMessageId",
            new String[] { Long.class.getName() },
            MessageRecipientStatusModelImpl.MESSAGEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGEID = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMessageId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_MESSAGERECIEVER = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            MessageRecipientStatusImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByMessageReciever",
            new String[] { Long.class.getName(), Long.class.getName() },
            MessageRecipientStatusModelImpl.MESSAGEID_COLUMN_BITMASK |
            MessageRecipientStatusModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGERECIEVER = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByMessageReciever",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MESSAGERECIPIENTSTATUS = "SELECT messageRecipientStatus FROM MessageRecipientStatus messageRecipientStatus";
    private static final String _SQL_SELECT_MESSAGERECIPIENTSTATUS_WHERE = "SELECT messageRecipientStatus FROM MessageRecipientStatus messageRecipientStatus WHERE ";
    private static final String _SQL_COUNT_MESSAGERECIPIENTSTATUS = "SELECT COUNT(messageRecipientStatus) FROM MessageRecipientStatus messageRecipientStatus";
    private static final String _SQL_COUNT_MESSAGERECIPIENTSTATUS_WHERE = "SELECT COUNT(messageRecipientStatus) FROM MessageRecipientStatus messageRecipientStatus WHERE ";
    private static final String _FINDER_COLUMN_RECEIVINGUSER_USERID_2 = "messageRecipientStatus.userId = ?";
    private static final String _FINDER_COLUMN_RECEIVINGUSERARCHIVED_USERID_2 = "messageRecipientStatus.userId = ? AND ";
    private static final String _FINDER_COLUMN_RECEIVINGUSERARCHIVED_ARCHIVED_2 = "messageRecipientStatus.archived = ?";
    private static final String _FINDER_COLUMN_MESSAGEID_MESSAGEID_2 = "messageRecipientStatus.messageId = ?";
    private static final String _FINDER_COLUMN_MESSAGERECIEVER_MESSAGEID_2 = "messageRecipientStatus.messageId = ? AND ";
    private static final String _FINDER_COLUMN_MESSAGERECIEVER_USERID_2 = "messageRecipientStatus.userId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "messageRecipientStatus.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MessageRecipientStatus exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MessageRecipientStatus exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MessageRecipientStatusPersistenceImpl.class);
    private static MessageRecipientStatus _nullMessageRecipientStatus = new MessageRecipientStatusImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MessageRecipientStatus> toCacheModel() {
                return _nullMessageRecipientStatusCacheModel;
            }
        };

    private static CacheModel<MessageRecipientStatus> _nullMessageRecipientStatusCacheModel =
        new CacheModel<MessageRecipientStatus>() {
            public MessageRecipientStatus toEntityModel() {
                return _nullMessageRecipientStatus;
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
     * Caches the message recipient status in the entity cache if it is enabled.
     *
     * @param messageRecipientStatus the message recipient status
     */
    public void cacheResult(MessageRecipientStatus messageRecipientStatus) {
        EntityCacheUtil.putResult(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            messageRecipientStatus.getPrimaryKey(), messageRecipientStatus);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
            new Object[] {
                Long.valueOf(messageRecipientStatus.getMessageId()),
                Long.valueOf(messageRecipientStatus.getUserId())
            }, messageRecipientStatus);

        messageRecipientStatus.resetOriginalValues();
    }

    /**
     * Caches the message recipient statuses in the entity cache if it is enabled.
     *
     * @param messageRecipientStatuses the message recipient statuses
     */
    public void cacheResult(
        List<MessageRecipientStatus> messageRecipientStatuses) {
        for (MessageRecipientStatus messageRecipientStatus : messageRecipientStatuses) {
            if (EntityCacheUtil.getResult(
                        MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
                        MessageRecipientStatusImpl.class,
                        messageRecipientStatus.getPrimaryKey()) == null) {
                cacheResult(messageRecipientStatus);
            } else {
                messageRecipientStatus.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all message recipient statuses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MessageRecipientStatusImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MessageRecipientStatusImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the message recipient status.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MessageRecipientStatus messageRecipientStatus) {
        EntityCacheUtil.removeResult(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            messageRecipientStatus.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(messageRecipientStatus);
    }

    @Override
    public void clearCache(
        List<MessageRecipientStatus> messageRecipientStatuses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MessageRecipientStatus messageRecipientStatus : messageRecipientStatuses) {
            EntityCacheUtil.removeResult(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
                MessageRecipientStatusImpl.class,
                messageRecipientStatus.getPrimaryKey());

            clearUniqueFindersCache(messageRecipientStatus);
        }
    }

    protected void clearUniqueFindersCache(
        MessageRecipientStatus messageRecipientStatus) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
            new Object[] {
                Long.valueOf(messageRecipientStatus.getMessageId()),
                Long.valueOf(messageRecipientStatus.getUserId())
            });
    }

    /**
     * Creates a new message recipient status with the primary key. Does not add the message recipient status to the database.
     *
     * @param messageRecipientId the primary key for the new message recipient status
     * @return the new message recipient status
     */
    public MessageRecipientStatus create(long messageRecipientId) {
        MessageRecipientStatus messageRecipientStatus = new MessageRecipientStatusImpl();

        messageRecipientStatus.setNew(true);
        messageRecipientStatus.setPrimaryKey(messageRecipientId);

        return messageRecipientStatus;
    }

    /**
     * Removes the message recipient status with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param messageRecipientId the primary key of the message recipient status
     * @return the message recipient status that was removed
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus remove(long messageRecipientId)
        throws NoSuchMessageRecipientStatusException, SystemException {
        return remove(Long.valueOf(messageRecipientId));
    }

    /**
     * Removes the message recipient status with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the message recipient status
     * @return the message recipient status that was removed
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessageRecipientStatus remove(Serializable primaryKey)
        throws NoSuchMessageRecipientStatusException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessageRecipientStatus messageRecipientStatus = (MessageRecipientStatus) session.get(MessageRecipientStatusImpl.class,
                    primaryKey);

            if (messageRecipientStatus == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMessageRecipientStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(messageRecipientStatus);
        } catch (NoSuchMessageRecipientStatusException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MessageRecipientStatus removeImpl(
        MessageRecipientStatus messageRecipientStatus)
        throws SystemException {
        messageRecipientStatus = toUnwrappedModel(messageRecipientStatus);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, messageRecipientStatus);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(messageRecipientStatus);

        return messageRecipientStatus;
    }

    @Override
    public MessageRecipientStatus updateImpl(
        com.ext.portlet.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge) throws SystemException {
        messageRecipientStatus = toUnwrappedModel(messageRecipientStatus);

        boolean isNew = messageRecipientStatus.isNew();

        MessageRecipientStatusModelImpl messageRecipientStatusModelImpl = (MessageRecipientStatusModelImpl) messageRecipientStatus;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messageRecipientStatus, merge);

            messageRecipientStatus.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !MessageRecipientStatusModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((messageRecipientStatusModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVINGUSER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(messageRecipientStatusModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RECEIVINGUSER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVINGUSER,
                    args);

                args = new Object[] {
                        Long.valueOf(messageRecipientStatusModelImpl.getUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RECEIVINGUSER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVINGUSER,
                    args);
            }

            if ((messageRecipientStatusModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVINGUSERARCHIVED.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(messageRecipientStatusModelImpl.getOriginalUserId()),
                        Boolean.valueOf(messageRecipientStatusModelImpl.getOriginalArchived())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RECEIVINGUSERARCHIVED,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVINGUSERARCHIVED,
                    args);

                args = new Object[] {
                        Long.valueOf(messageRecipientStatusModelImpl.getUserId()),
                        Boolean.valueOf(messageRecipientStatusModelImpl.getArchived())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_RECEIVINGUSERARCHIVED,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVINGUSERARCHIVED,
                    args);
            }

            if ((messageRecipientStatusModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(messageRecipientStatusModelImpl.getOriginalMessageId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID,
                    args);

                args = new Object[] {
                        Long.valueOf(messageRecipientStatusModelImpl.getMessageId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
            MessageRecipientStatusImpl.class,
            messageRecipientStatus.getPrimaryKey(), messageRecipientStatus);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                new Object[] {
                    Long.valueOf(messageRecipientStatus.getMessageId()),
                    Long.valueOf(messageRecipientStatus.getUserId())
                }, messageRecipientStatus);
        } else {
            if ((messageRecipientStatusModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_MESSAGERECIEVER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(messageRecipientStatusModelImpl.getOriginalMessageId()),
                        Long.valueOf(messageRecipientStatusModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGERECIEVER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                    new Object[] {
                        Long.valueOf(messageRecipientStatus.getMessageId()),
                        Long.valueOf(messageRecipientStatus.getUserId())
                    }, messageRecipientStatus);
            }
        }

        return messageRecipientStatus;
    }

    protected MessageRecipientStatus toUnwrappedModel(
        MessageRecipientStatus messageRecipientStatus) {
        if (messageRecipientStatus instanceof MessageRecipientStatusImpl) {
            return messageRecipientStatus;
        }

        MessageRecipientStatusImpl messageRecipientStatusImpl = new MessageRecipientStatusImpl();

        messageRecipientStatusImpl.setNew(messageRecipientStatus.isNew());
        messageRecipientStatusImpl.setPrimaryKey(messageRecipientStatus.getPrimaryKey());

        messageRecipientStatusImpl.setMessageRecipientId(messageRecipientStatus.getMessageRecipientId());
        messageRecipientStatusImpl.setMessageId(messageRecipientStatus.getMessageId());
        messageRecipientStatusImpl.setUserId(messageRecipientStatus.getUserId());
        messageRecipientStatusImpl.setOpened(messageRecipientStatus.isOpened());
        messageRecipientStatusImpl.setArchived(messageRecipientStatus.isArchived());

        return messageRecipientStatusImpl;
    }

    /**
     * Returns the message recipient status with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the message recipient status
     * @return the message recipient status
     * @throws com.liferay.portal.NoSuchModelException if a message recipient status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessageRecipientStatus findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the message recipient status with the primary key or throws a {@link com.ext.portlet.NoSuchMessageRecipientStatusException} if it could not be found.
     *
     * @param messageRecipientId the primary key of the message recipient status
     * @return the message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus findByPrimaryKey(long messageRecipientId)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = fetchByPrimaryKey(messageRecipientId);

        if (messageRecipientStatus == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    messageRecipientId);
            }

            throw new NoSuchMessageRecipientStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                messageRecipientId);
        }

        return messageRecipientStatus;
    }

    /**
     * Returns the message recipient status with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the message recipient status
     * @return the message recipient status, or <code>null</code> if a message recipient status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessageRecipientStatus fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the message recipient status with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param messageRecipientId the primary key of the message recipient status
     * @return the message recipient status, or <code>null</code> if a message recipient status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus fetchByPrimaryKey(long messageRecipientId)
        throws SystemException {
        MessageRecipientStatus messageRecipientStatus = (MessageRecipientStatus) EntityCacheUtil.getResult(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
                MessageRecipientStatusImpl.class, messageRecipientId);

        if (messageRecipientStatus == _nullMessageRecipientStatus) {
            return null;
        }

        if (messageRecipientStatus == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                messageRecipientStatus = (MessageRecipientStatus) session.get(MessageRecipientStatusImpl.class,
                        Long.valueOf(messageRecipientId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (messageRecipientStatus != null) {
                    cacheResult(messageRecipientStatus);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(MessageRecipientStatusModelImpl.ENTITY_CACHE_ENABLED,
                        MessageRecipientStatusImpl.class, messageRecipientId,
                        _nullMessageRecipientStatus);
                }

                closeSession(session);
            }
        }

        return messageRecipientStatus;
    }

    /**
     * Returns all the message recipient statuses where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findByReceivingUser(long userId)
        throws SystemException {
        return findByReceivingUser(userId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the message recipient statuses where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of message recipient statuses
     * @param end the upper bound of the range of message recipient statuses (not inclusive)
     * @return the range of matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findByReceivingUser(long userId,
        int start, int end) throws SystemException {
        return findByReceivingUser(userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the message recipient statuses where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of message recipient statuses
     * @param end the upper bound of the range of message recipient statuses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findByReceivingUser(long userId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVINGUSER;
            finderArgs = new Object[] { userId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RECEIVINGUSER;
            finderArgs = new Object[] { userId, start, end, orderByComparator };
        }

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_MESSAGERECIPIENTSTATUS_WHERE);

            query.append(_FINDER_COLUMN_RECEIVINGUSER_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(MessageRecipientStatusModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                list = (List<MessageRecipientStatus>) QueryUtil.list(q,
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
     * Returns the first message recipient status in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus findByReceivingUser_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchMessageRecipientStatusException, SystemException {
        List<MessageRecipientStatus> list = findByReceivingUser(userId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last message recipient status in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus findByReceivingUser_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchMessageRecipientStatusException, SystemException {
        int count = countByReceivingUser(userId);

        List<MessageRecipientStatus> list = findByReceivingUser(userId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the message recipient statuses before and after the current message recipient status in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param messageRecipientId the primary key of the current message recipient status
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus[] findByReceivingUser_PrevAndNext(
        long messageRecipientId, long userId,
        OrderByComparator orderByComparator)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = findByPrimaryKey(messageRecipientId);

        Session session = null;

        try {
            session = openSession();

            MessageRecipientStatus[] array = new MessageRecipientStatusImpl[3];

            array[0] = getByReceivingUser_PrevAndNext(session,
                    messageRecipientStatus, userId, orderByComparator, true);

            array[1] = messageRecipientStatus;

            array[2] = getByReceivingUser_PrevAndNext(session,
                    messageRecipientStatus, userId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected MessageRecipientStatus getByReceivingUser_PrevAndNext(
        Session session, MessageRecipientStatus messageRecipientStatus,
        long userId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MESSAGERECIPIENTSTATUS_WHERE);

        query.append(_FINDER_COLUMN_RECEIVINGUSER_USERID_2);

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
            query.append(MessageRecipientStatusModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(messageRecipientStatus);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<MessageRecipientStatus> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the message recipient statuses where userId = &#63; and archived = &#63;.
     *
     * @param userId the user ID
     * @param archived the archived
     * @return the matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findByReceivingUserArchived(
        long userId, boolean archived) throws SystemException {
        return findByReceivingUserArchived(userId, archived, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the message recipient statuses where userId = &#63; and archived = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param archived the archived
     * @param start the lower bound of the range of message recipient statuses
     * @param end the upper bound of the range of message recipient statuses (not inclusive)
     * @return the range of matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findByReceivingUserArchived(
        long userId, boolean archived, int start, int end)
        throws SystemException {
        return findByReceivingUserArchived(userId, archived, start, end, null);
    }

    /**
     * Returns an ordered range of all the message recipient statuses where userId = &#63; and archived = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param archived the archived
     * @param start the lower bound of the range of message recipient statuses
     * @param end the upper bound of the range of message recipient statuses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findByReceivingUserArchived(
        long userId, boolean archived, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_RECEIVINGUSERARCHIVED;
            finderArgs = new Object[] { userId, archived };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_RECEIVINGUSERARCHIVED;
            finderArgs = new Object[] {
                    userId, archived,
                    
                    start, end, orderByComparator
                };
        }

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_MESSAGERECIPIENTSTATUS_WHERE);

            query.append(_FINDER_COLUMN_RECEIVINGUSERARCHIVED_USERID_2);

            query.append(_FINDER_COLUMN_RECEIVINGUSERARCHIVED_ARCHIVED_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(MessageRecipientStatusModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                qPos.add(archived);

                list = (List<MessageRecipientStatus>) QueryUtil.list(q,
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
     * Returns the first message recipient status in the ordered set where userId = &#63; and archived = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param archived the archived
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus findByReceivingUserArchived_First(
        long userId, boolean archived, OrderByComparator orderByComparator)
        throws NoSuchMessageRecipientStatusException, SystemException {
        List<MessageRecipientStatus> list = findByReceivingUserArchived(userId,
                archived, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(", archived=");
            msg.append(archived);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last message recipient status in the ordered set where userId = &#63; and archived = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param archived the archived
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus findByReceivingUserArchived_Last(
        long userId, boolean archived, OrderByComparator orderByComparator)
        throws NoSuchMessageRecipientStatusException, SystemException {
        int count = countByReceivingUserArchived(userId, archived);

        List<MessageRecipientStatus> list = findByReceivingUserArchived(userId,
                archived, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(", archived=");
            msg.append(archived);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the message recipient statuses before and after the current message recipient status in the ordered set where userId = &#63; and archived = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param messageRecipientId the primary key of the current message recipient status
     * @param userId the user ID
     * @param archived the archived
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus[] findByReceivingUserArchived_PrevAndNext(
        long messageRecipientId, long userId, boolean archived,
        OrderByComparator orderByComparator)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = findByPrimaryKey(messageRecipientId);

        Session session = null;

        try {
            session = openSession();

            MessageRecipientStatus[] array = new MessageRecipientStatusImpl[3];

            array[0] = getByReceivingUserArchived_PrevAndNext(session,
                    messageRecipientStatus, userId, archived,
                    orderByComparator, true);

            array[1] = messageRecipientStatus;

            array[2] = getByReceivingUserArchived_PrevAndNext(session,
                    messageRecipientStatus, userId, archived,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected MessageRecipientStatus getByReceivingUserArchived_PrevAndNext(
        Session session, MessageRecipientStatus messageRecipientStatus,
        long userId, boolean archived, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MESSAGERECIPIENTSTATUS_WHERE);

        query.append(_FINDER_COLUMN_RECEIVINGUSERARCHIVED_USERID_2);

        query.append(_FINDER_COLUMN_RECEIVINGUSERARCHIVED_ARCHIVED_2);

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
            query.append(MessageRecipientStatusModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        qPos.add(archived);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(messageRecipientStatus);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<MessageRecipientStatus> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the message recipient statuses where messageId = &#63;.
     *
     * @param messageId the message ID
     * @return the matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findByMessageId(long messageId)
        throws SystemException {
        return findByMessageId(messageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the message recipient statuses where messageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param messageId the message ID
     * @param start the lower bound of the range of message recipient statuses
     * @param end the upper bound of the range of message recipient statuses (not inclusive)
     * @return the range of matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findByMessageId(long messageId,
        int start, int end) throws SystemException {
        return findByMessageId(messageId, start, end, null);
    }

    /**
     * Returns an ordered range of all the message recipient statuses where messageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param messageId the message ID
     * @param start the lower bound of the range of message recipient statuses
     * @param end the upper bound of the range of message recipient statuses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findByMessageId(long messageId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID;
            finderArgs = new Object[] { messageId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MESSAGEID;
            finderArgs = new Object[] { messageId, start, end, orderByComparator };
        }

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_MESSAGERECIPIENTSTATUS_WHERE);

            query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(MessageRecipientStatusModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                list = (List<MessageRecipientStatus>) QueryUtil.list(q,
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
     * Returns the first message recipient status in the ordered set where messageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param messageId the message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus findByMessageId_First(long messageId,
        OrderByComparator orderByComparator)
        throws NoSuchMessageRecipientStatusException, SystemException {
        List<MessageRecipientStatus> list = findByMessageId(messageId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("messageId=");
            msg.append(messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last message recipient status in the ordered set where messageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param messageId the message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus findByMessageId_Last(long messageId,
        OrderByComparator orderByComparator)
        throws NoSuchMessageRecipientStatusException, SystemException {
        int count = countByMessageId(messageId);

        List<MessageRecipientStatus> list = findByMessageId(messageId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("messageId=");
            msg.append(messageId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the message recipient statuses before and after the current message recipient status in the ordered set where messageId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param messageRecipientId the primary key of the current message recipient status
     * @param messageId the message ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus[] findByMessageId_PrevAndNext(
        long messageRecipientId, long messageId,
        OrderByComparator orderByComparator)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = findByPrimaryKey(messageRecipientId);

        Session session = null;

        try {
            session = openSession();

            MessageRecipientStatus[] array = new MessageRecipientStatusImpl[3];

            array[0] = getByMessageId_PrevAndNext(session,
                    messageRecipientStatus, messageId, orderByComparator, true);

            array[1] = messageRecipientStatus;

            array[2] = getByMessageId_PrevAndNext(session,
                    messageRecipientStatus, messageId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected MessageRecipientStatus getByMessageId_PrevAndNext(
        Session session, MessageRecipientStatus messageRecipientStatus,
        long messageId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MESSAGERECIPIENTSTATUS_WHERE);

        query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

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
            query.append(MessageRecipientStatusModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(messageId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(messageRecipientStatus);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<MessageRecipientStatus> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the message recipient status where messageId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchMessageRecipientStatusException} if it could not be found.
     *
     * @param messageId the message ID
     * @param userId the user ID
     * @return the matching message recipient status
     * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus findByMessageReciever(long messageId,
        long userId)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = fetchByMessageReciever(messageId,
                userId);

        if (messageRecipientStatus == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("messageId=");
            msg.append(messageId);

            msg.append(", userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMessageRecipientStatusException(msg.toString());
        }

        return messageRecipientStatus;
    }

    /**
     * Returns the message recipient status where messageId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param messageId the message ID
     * @param userId the user ID
     * @return the matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus fetchByMessageReciever(long messageId,
        long userId) throws SystemException {
        return fetchByMessageReciever(messageId, userId, true);
    }

    /**
     * Returns the message recipient status where messageId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param messageId the message ID
     * @param userId the user ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessageRecipientStatus fetchByMessageReciever(long messageId,
        long userId, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { messageId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_MESSAGERECIPIENTSTATUS_WHERE);

            query.append(_FINDER_COLUMN_MESSAGERECIEVER_MESSAGEID_2);

            query.append(_FINDER_COLUMN_MESSAGERECIEVER_USERID_2);

            query.append(MessageRecipientStatusModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                qPos.add(userId);

                List<MessageRecipientStatus> list = q.list();

                result = list;

                MessageRecipientStatus messageRecipientStatus = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                        finderArgs, list);
                } else {
                    messageRecipientStatus = list.get(0);

                    cacheResult(messageRecipientStatus);

                    if ((messageRecipientStatus.getMessageId() != messageId) ||
                            (messageRecipientStatus.getUserId() != userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                            finderArgs, messageRecipientStatus);
                    }
                }

                return messageRecipientStatus;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGERECIEVER,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (MessageRecipientStatus) result;
            }
        }
    }

    /**
     * Returns all the message recipient statuses.
     *
     * @return the message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the message recipient statuses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of message recipient statuses
     * @param end the upper bound of the range of message recipient statuses (not inclusive)
     * @return the range of message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the message recipient statuses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of message recipient statuses
     * @param end the upper bound of the range of message recipient statuses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public List<MessageRecipientStatus> findAll(int start, int end,
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

        List<MessageRecipientStatus> list = (List<MessageRecipientStatus>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MESSAGERECIPIENTSTATUS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MESSAGERECIPIENTSTATUS.concat(MessageRecipientStatusModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<MessageRecipientStatus>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessageRecipientStatus>) QueryUtil.list(q,
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
     * Removes all the message recipient statuses where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByReceivingUser(long userId) throws SystemException {
        for (MessageRecipientStatus messageRecipientStatus : findByReceivingUser(
                userId)) {
            remove(messageRecipientStatus);
        }
    }

    /**
     * Removes all the message recipient statuses where userId = &#63; and archived = &#63; from the database.
     *
     * @param userId the user ID
     * @param archived the archived
     * @throws SystemException if a system exception occurred
     */
    public void removeByReceivingUserArchived(long userId, boolean archived)
        throws SystemException {
        for (MessageRecipientStatus messageRecipientStatus : findByReceivingUserArchived(
                userId, archived)) {
            remove(messageRecipientStatus);
        }
    }

    /**
     * Removes all the message recipient statuses where messageId = &#63; from the database.
     *
     * @param messageId the message ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByMessageId(long messageId) throws SystemException {
        for (MessageRecipientStatus messageRecipientStatus : findByMessageId(
                messageId)) {
            remove(messageRecipientStatus);
        }
    }

    /**
     * Removes the message recipient status where messageId = &#63; and userId = &#63; from the database.
     *
     * @param messageId the message ID
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByMessageReciever(long messageId, long userId)
        throws NoSuchMessageRecipientStatusException, SystemException {
        MessageRecipientStatus messageRecipientStatus = findByMessageReciever(messageId,
                userId);

        remove(messageRecipientStatus);
    }

    /**
     * Removes all the message recipient statuses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (MessageRecipientStatus messageRecipientStatus : findAll()) {
            remove(messageRecipientStatus);
        }
    }

    /**
     * Returns the number of message recipient statuses where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public int countByReceivingUser(long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RECEIVINGUSER,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MESSAGERECIPIENTSTATUS_WHERE);

            query.append(_FINDER_COLUMN_RECEIVINGUSER_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RECEIVINGUSER,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of message recipient statuses where userId = &#63; and archived = &#63;.
     *
     * @param userId the user ID
     * @param archived the archived
     * @return the number of matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public int countByReceivingUserArchived(long userId, boolean archived)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId, archived };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_RECEIVINGUSERARCHIVED,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_MESSAGERECIPIENTSTATUS_WHERE);

            query.append(_FINDER_COLUMN_RECEIVINGUSERARCHIVED_USERID_2);

            query.append(_FINDER_COLUMN_RECEIVINGUSERARCHIVED_ARCHIVED_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                qPos.add(archived);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_RECEIVINGUSERARCHIVED,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of message recipient statuses where messageId = &#63;.
     *
     * @param messageId the message ID
     * @return the number of matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public int countByMessageId(long messageId) throws SystemException {
        Object[] finderArgs = new Object[] { messageId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MESSAGEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MESSAGERECIPIENTSTATUS_WHERE);

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
     * Returns the number of message recipient statuses where messageId = &#63; and userId = &#63;.
     *
     * @param messageId the message ID
     * @param userId the user ID
     * @return the number of matching message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public int countByMessageReciever(long messageId, long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { messageId, userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MESSAGERECIEVER,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_MESSAGERECIPIENTSTATUS_WHERE);

            query.append(_FINDER_COLUMN_MESSAGERECIEVER_MESSAGEID_2);

            query.append(_FINDER_COLUMN_MESSAGERECIEVER_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                qPos.add(userId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MESSAGERECIEVER,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of message recipient statuses.
     *
     * @return the number of message recipient statuses
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_MESSAGERECIPIENTSTATUS);

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
     * Initializes the message recipient status persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MessageRecipientStatus")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessageRecipientStatus>> listenersList = new ArrayList<ModelListener<MessageRecipientStatus>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessageRecipientStatus>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MessageRecipientStatusImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
