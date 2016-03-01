package org.xcolab.portlets.admintasks;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.BalloonUserTracking;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.utils.iptranslation.Location;
import com.ext.utils.iptranslation.service.IpTranslationServiceUtil;
import com.icesoft.faces.async.render.SessionRenderer;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import edu.emory.mathcs.backport.java.util.Collections;
import org.apache.commons.lang3.StringUtils;
import org.xcolab.portlets.admintasks.data.DataBean;
import org.xcolab.utils.UrlBuilder;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdminTasksBean {
	private static final Log _log = LogFactoryUtil.getLog(AdminTasksBean.class);

	private final static String REQUEST_PARAM_NAME = "com.liferay.portal.kernel.servlet.PortletServletRequest";
	private final static long defaultCompanyId = 10112L;

	private final DataBean dataBean = new DataBean();

	private final List<String> messages;

	public List<String> getMessages() {
		return messages;
	}

    public AdminTasksBean(){
        SessionRenderer.addCurrentSession("pushMessages");
        messages = new ArrayList<>();
    }

	public String fixWikiPermissions() throws SystemException, PortalException {

		if (true) {
			throw new PortalException(
					"Fix wiki permissions method needs to be adjusted to liferay 6.2");
		}
		Long companyId = defaultCompanyId;
		Role guest = RoleLocalServiceUtil.getRole(companyId,
				RoleConstants.GUEST);
		Role userRole = RoleLocalServiceUtil.getRole(companyId,
				RoleConstants.USER);
		Role siteMemberRole = RoleLocalServiceUtil.getRole(companyId,
				RoleConstants.SITE_MEMBER);

		int idx = 0;
		int total = WikiPageLocalServiceUtil.getWikiPagesCount();

		for (WikiPage wp : WikiPageLocalServiceUtil.getWikiPages(0,
				Integer.MAX_VALUE)) {
			idx++;
			System.out.println(idx + " of " + total);

			Resource resource = null;

			/*
			 * try { resource =
			 * ResourceLocalServiceUtil.getResource(defaultCompanyId,
			 * WikiPage.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
			 * String.valueOf(wp.getResourcePrimKey())); } catch
			 * (NoSuchResourceException nsre) {
			 * System.out.println("Can't find resource for page: " +
			 * wp.getPageId()); //resource =
			 * ResourceLocalServiceUtil.addResource(defaultCompanyId,
			 * WikiPage.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
			 * String.valueOf(wp.getResourcePrimKey()));
			 * System.out.println("New resource created: " +
			 * resource.getResourceId());
			 * //ResourceLocalServiceUtil.updateResource(resource); }
			 */
			/*
			 * PermissionLocalServiceUtil.setRolePermissions(guest.getRoleId(),
			 * companyId, WikiPage.class.getName(),
			 * ResourceConstants.SCOPE_COMPANY,
			 * String.valueOf(wp.getResourcePrimKey()), guestActions);
			 */
			/*
			 * PermissionLocalServiceUtil.setRolePermissions(guest.getRoleId(),
			 * actionIds, resource.getResourceId());
			 * PermissionLocalServiceUtil.setRolePermissions
			 * (userRole.getRoleId(), actionIds, resource.getResourceId());
			 * PermissionLocalServiceUtil
			 * .setRolePermissions(siteMemberRole.getRoleId(), actionIds,
			 * resource.getResourceId());
			 */
			/*
			 * PermissionServiceUtil.setRolePermissions( guest.getRoleId(),
			 * wp.getGroupId(), actionIds, resource.getResourceId());
			 */
		}
		return null;
	}

	public void addMissingCommentsSocialActivities() throws SystemException, PortalException {

		/*
		 * ClassLoader portletClassLoader = (ClassLoader)
		 * PortletBeanLocatorUtil.locate(servletContextName,
		 * name))(MESSAGE_ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader");
		 */
		ClassName cn = ClassNameLocalServiceUtil
				.getClassName(DiscussionCategoryGroup.class.getName());

		Set<String> discussionNamesToIgnore = new HashSet<>();
		List<DiscussionCategoryGroup> dcgs = DiscussionCategoryGroupLocalServiceUtil
				.getDiscussionCategoryGroups(0, 10000);
		Collections.sort(dcgs, new Comparator<DiscussionCategoryGroup>() {

			public int compare(DiscussionCategoryGroup o1,
					DiscussionCategoryGroup o2) {
				if (o1.getCommentsThread() <= 0) {
					if (o2.getCommentsThread() <= 0) {
						return (int) (o1.getId() - o2.getId());
					}
					return 1;
				}
				if (o2.getCommentsThread() <= 0) {
					return -1;
				}
				try {
					DiscussionMessage o1m = DiscussionCategoryGroupLocalServiceUtil
							.getCommentThread(o1);
					DiscussionMessage o2m = DiscussionCategoryGroupLocalServiceUtil
							.getCommentThread(o2);
					if (o1m.getCreateDate().before(o2m.getCreateDate())) {
						return -1;
					} else if (o2m.getCreateDate().before(o1m.getCreateDate())) {
						return 1;
					}
					return 0;

				} catch (Exception e) {
					// ignore
				}
				return 0;
			}
		});

		Collections.reverse(dcgs);
		for (DiscussionCategoryGroup dcg : dcgs) {
			if (dcg.getCommentsThread() <= 0) {
				continue;
			}
			DiscussionMessage message = DiscussionCategoryGroupLocalServiceUtil
					.getCommentThread(dcg);

			if (discussionNamesToIgnore.contains(message.getSubject().trim())) {
				continue;
			}
			discussionNamesToIgnore.add(message.getSubject().trim());

			DynamicQuery activityQuery = DynamicQueryFactoryUtil
					.forClass(SocialActivity.class);// , portletClassLoader);

			// ActivityUtil.getExtraDataForIds(wrapped.getCategoryGroupId(),
			// getThreadId(wrapped), wrapped.getMessageId()), 0);

			activityQuery.add(PropertyFactoryUtil.forName("userId").eq(
					message.getAuthorId()));
			activityQuery.add(PropertyFactoryUtil.forName("classNameId").eq(
					cn.getClassNameId()));
			activityQuery.add(PropertyFactoryUtil.forName("classPK").eq(
					dcg.getId()));
			activityQuery.add(PropertyFactoryUtil.forName("createDate").gt(
					message.getCreateDate().getTime()));
			activityQuery.add(PropertyFactoryUtil.forName("createDate").lt(
					message.getCreateDate().getTime() + 10000));

			activityQuery.addOrder(OrderFactoryUtil.desc("createDate"));

			List<SocialActivity> activities = SocialActivityLocalServiceUtil.dynamicQuery(activityQuery);
			if (activities.isEmpty()) {
				System.out.println("---\tNo activity for discussion "
						+ dcg.getId());

				SocialActivityLocalServiceUtil
						.addUniqueActivity(message.getAuthorId(), 10136L,
								message.getCreateDate(),
								DiscussionCategoryGroup.class.getName(), dcg
										.getId(), 4, ActivityUtil
										.getExtraDataForIds(dcg.getId(),
												message.getMessageId(),
												message.getMessageId()), 0);
			} else {
				System.out.println("+++\tActivity for discussion found "
						+ dcg.getId());
			}
		}
	}

	public void fixSocialActivitiesErrors() throws SystemException, PortalException {
		PrincipalThreadLocal.setName(10144L);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		Map map = ec.getRequestMap();
		ThemeDisplay td = (ThemeDisplay) map.get(WebKeys.THEME_DISPLAY);
		int badCount = 0;
		Set<String> removeEmptyActivitiesWithClass = new HashSet<String>();
		removeEmptyActivitiesWithClass.add("com.ext.portlet");
		removeEmptyActivitiesWithClass
				.add("com.ext.portlet.debaterevision.model.DebateItem");
		removeEmptyActivitiesWithClass
				.add("com.liferay.portlet.wiki.model.WikiPage");
		removeEmptyActivitiesWithClass
				.add("com.liferay.portlet.messageboards.model.MBMessage");
		removeEmptyActivitiesWithClass
				.add("com.ext.portlet.debates.action.EditDebateMessageAction");
		removeEmptyActivitiesWithClass
				.add("com.ext.portlet.debaterevision.model.Debate");
		removeEmptyActivitiesWithClass
				.add("com.ext.portlet.model.DiscussionMessage");
		removeEmptyActivitiesWithClass
				.add("com.ext.portlet.debaterevision.model.DebateCategory");
		removeEmptyActivitiesWithClass
				.add("com.ext.portlet.model.DiscussionCategoryGroup");
		removeEmptyActivitiesWithClass
				.add("com.liferay.portlet.blogs.model.BlogsEntry");

		for (SocialActivity activity : SocialActivityLocalServiceUtil
				.getSocialActivities(0, Integer.MAX_VALUE)) {
			try {
				// System.out.println("processing activity: " +
				// activity.getActivityId());
				String className = ClassNameLocalServiceUtil.getClassName(
						activity.getClassNameId()).getClassName();
				/*
				 * if
				 * (className.equals("com.liferay.portlet.wiki.model.WikiPage"))
				 * { System.out.println("\t\t\t" + activity); }
				 */
				SocialActivityFeedEntry interpreted = SocialActivityInterpreterLocalServiceUtil
						.interpret(activity, td);
				if (interpreted == null) {
					badCount++;
					System.out.println("Can't interpret activity: "
							+ activity.getActivityId() + "\n\t\t"
							+ new Date(activity.getCreateDate()) + "\t"
							+ className);
					System.out.println("\t\t\t" + activity);
					if (removeEmptyActivitiesWithClass.contains(className)) {
						SocialActivityLocalServiceUtil.deleteActivity(activity);
					}
				}
			} catch (Exception e) {
				badCount++;
				System.out.println("Can't interpret activity: "
						+ new Date(activity.getCreateDate())
						+ "\t"
						+ ClassNameLocalServiceUtil.getClassName(
								activity.getClassNameId()).getClassName());
			}
		}
		System.out.println("Bad activities count: " + badCount);
	}

	public String fixProposalDiscussionUrlsAndDescriptions() throws SystemException, PortalException {
		for (Proposal proposal : ProposalLocalServiceUtil.getProposals(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
			DiscussionCategoryGroup proposalDiscussion = DiscussionCategoryGroupLocalServiceUtil
					.getDiscussionCategoryGroup(proposal.getDiscussionId());
			final Contest contest = ProposalLocalServiceUtil.getLatestProposalContest(proposal.getProposalId());
			proposalDiscussion.setUrl(UrlBuilder.getProposalCommentsUrl(contest, proposal));
            String proposalName = ProposalAttributeLocalServiceUtil.getAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();
			ContestType contestType = ContestTypeLocalServiceUtil.getContestTypeFromProposalId(proposal.getProposalId());
			proposalDiscussion.setDescription(String.format("%s %s", contestType.getProposalName(), proposalName));
			DiscussionCategoryGroupLocalServiceUtil.updateDiscussionCategoryGroup(proposalDiscussion);
		}
		return null;
	}
	
	public String findWikiPageActivities() throws SystemException, PortalException {
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(WikiPage.class);
		
		for (SocialActivity activity: SocialActivityLocalServiceUtil.getActivities(classNameId, 0, Integer.MAX_VALUE)) {
			try {
				WikiPage page = WikiPageLocalServiceUtil.getPage(activity.getClassPK());
				JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

				extraDataJSONObject.put("title", page.getTitle());
				extraDataJSONObject.put("version", page.getVersion());
			
				activity.setExtraData(extraDataJSONObject.toString());
				SocialActivityLocalServiceUtil.updateSocialActivity(activity);
			}
			catch (SystemException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String translateIp() throws Exception {
		System.out.println(IpTranslationServiceUtil.getLocationForIp("89.67.113.30"));
		return null;
	}
	
	public String reloadIpLocationData() throws Exception {
		IpTranslationServiceUtil.reloadLocationAndBlockData();
		return null;
		
	}

	public String populateLocationDataIntoBalloon() throws Exception {
		for (BalloonUserTracking but: BalloonUserTrackingLocalServiceUtil.getBalloonUserTrackings(0, Integer.MAX_VALUE)) {
			Location location = IpTranslationServiceUtil.getLocationForIp(but.getIp());
			if (location != null) {
				if (StringUtils.isBlank(but.getCity()) && StringUtils.isNotBlank(location.getCity())) {
					but.setCity(location.getCity());
				}

				if (StringUtils.isBlank(but.getCountry()) && StringUtils.isNotBlank(location.getCountry())) {
					but.setCountry(location.getCountry());
				}
				
				if (but.getLatitude() == 0 && location.getLatitude() != 0) {
					but.setLatitude(location.getLatitude());
				}
				if (but.getLongitude() == 0 && location.getLongitude() != 0) {
					but.setLongitude(location.getLongitude());
				}
				
				BalloonUserTrackingLocalServiceUtil.updateBalloonUserTracking(but);
			}
		}
		
		//IpTranslationServiceUtil.reloadLocationAndBlockData();
		return null;
		
	}

    // ----- Reindex Tasks -----
    public void removeUsers() throws SearchException, SystemException {
        pushAjaxUpdate("Removing Users from index");
        Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);
        for (User u : UserLocalServiceUtil.getUsers(0,Integer.MAX_VALUE)) {
			indexer.delete(u);
		}
        pushAjaxUpdateFinishedIndexerTask();
    }
    public void removeProposals() throws SearchException, SystemException {
        pushAjaxUpdate("Removing Proposals from index");
        Indexer indexer = IndexerRegistryUtil.getIndexer(Proposal.class);
        for (Proposal p : ProposalLocalServiceUtil.getProposals(0,Integer.MAX_VALUE)) {
			indexer.delete(p);
		}
        pushAjaxUpdateFinishedIndexerTask();
    }
    public void removeContests() throws SearchException, SystemException {
        pushAjaxUpdate("Removing Contests from index");
        Indexer indexer = IndexerRegistryUtil.getIndexer(Contest.class);
        for (Contest c : ContestLocalServiceUtil.getContests(0,Integer.MAX_VALUE)) {
			indexer.delete(c);
		}
        pushAjaxUpdateFinishedIndexerTask();
    }
    public void removeActivities() throws SearchException, SystemException {
        pushAjaxUpdate("Removing Activities from index");
        Indexer indexer = IndexerRegistryUtil.getIndexer(SocialActivity.class);
        for (SocialActivity s : SocialActivityLocalServiceUtil.getSocialActivities(0,Integer.MAX_VALUE)) {
			indexer.delete(s);
		}
        pushAjaxUpdateFinishedIndexerTask();
    }
    public void removeDiscussions() throws SearchException, SystemException {
        pushAjaxUpdate("Removing Discussions from index");
        Indexer indexer = IndexerRegistryUtil.getIndexer(DiscussionMessage.class);
        for (DiscussionMessage m : DiscussionMessageLocalServiceUtil.getDiscussionMessages(0,Integer.MAX_VALUE)) {
			indexer.delete(m);
		}
        pushAjaxUpdateFinishedIndexerTask();
    }
    public void reindexUsers() throws SearchException, SystemException {
        pushAjaxUpdate("Reindexing Users index");
        Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);
        for (User u : UserLocalServiceUtil.getUsers(0,Integer.MAX_VALUE)) {
			indexer.reindex(u);
		}
        pushAjaxUpdateFinishedIndexerTask();
    }
    public void reindexProposals() throws SearchException, SystemException {
        pushAjaxUpdate("Reindexing Proposals index");
        Indexer indexer = IndexerRegistryUtil.getIndexer(Proposal.class);
        for (Proposal p : ProposalLocalServiceUtil.getProposals(0,Integer.MAX_VALUE)) {
			indexer.reindex(p);
		}
        pushAjaxUpdateFinishedIndexerTask();
    }
    public void reindexContests() throws SearchException, SystemException {
        pushAjaxUpdate("Reindexing Contests index");
        Indexer indexer = IndexerRegistryUtil.getIndexer(Contest.class);
        for (Contest c : ContestLocalServiceUtil.getContests(0,Integer.MAX_VALUE)) {
			indexer.reindex(c);
		}
        pushAjaxUpdateFinishedIndexerTask();
    }
    public void reindexActivities() throws SearchException, SystemException {
        pushAjaxUpdate("Reindexing Activities from index");
        Indexer indexer = IndexerRegistryUtil.getIndexer(SocialActivity.class);
        for (SocialActivity s : SocialActivityLocalServiceUtil.getSocialActivities(0,Integer.MAX_VALUE)) {
			indexer.reindex(s);
		}
        pushAjaxUpdateFinishedIndexerTask();
    }
    public void reindexDiscussions() throws SearchException, SystemException {
        pushAjaxUpdate("Reindexing Users Discussions index");
        Indexer indexer = IndexerRegistryUtil.getIndexer(DiscussionMessage.class);
        for (DiscussionMessage m : DiscussionMessageLocalServiceUtil.getDiscussionMessages(0,Integer.MAX_VALUE)) {
			indexer.reindex(m);
		}
        pushAjaxUpdateFinishedIndexerTask();
    }

    private void pushAjaxUpdateFinishedIndexerTask(){
        pushAjaxUpdate("Finished triggering removal. Removal is done async, please check the frontend and verify that all items were removed before triggering reindex actions.");
    }

    private void pushAjaxUpdate(String message){
        messages.add(message);
        SessionRenderer.render("pushMessages");
    }

	public DataBean getDataBean() {
		return dataBean;
	}
}
