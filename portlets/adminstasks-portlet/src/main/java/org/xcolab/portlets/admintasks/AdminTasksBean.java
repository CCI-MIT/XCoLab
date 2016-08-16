package org.xcolab.portlets.admintasks;

import com.icesoft.faces.async.render.SessionRenderer;
import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class AdminTasksBean {
	private static final Log _log = LogFactoryUtil.getLog(AdminTasksBean.class);

    private final List<String> messages;

	public List<String> getMessages() {
		return messages;
	}

    public AdminTasksBean(){
        SessionRenderer.addCurrentSession("pushMessages");
        messages = new ArrayList<>();
    }

    //TODO: this might be useful but needs to be reworked
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
				String className = ClassNameLocalServiceUtil.getClassName(
						activity.getClassNameId()).getClassName();
				SocialActivityFeedEntry interpreted = SocialActivityInterpreterLocalServiceUtil
						.interpret(activity, td);
				if (interpreted == null) {
					badCount++;
					if (removeEmptyActivitiesWithClass.contains(className)) {
						SocialActivityLocalServiceUtil.deleteActivity(activity);
					}
				}
			} catch (Exception e) {
				badCount++;
			}
		}
	}

	public String populateLocationDataIntoBalloon() throws Exception {
		for (BalloonUserTracking but: BalloonsClient.getAllBalloonUserTracking()) {
			Location location = TrackingClient.getLocationForIp(but.getIp());
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
				BalloonsClient.updateBalloonUserTracking(but);
			}
		}
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

    private void pushAjaxUpdateFinishedIndexerTask(){
        pushAjaxUpdate("Finished triggering removal. Removal is done async, please check the frontend and verify that all items were removed before triggering reindex actions.");
    }

    private void pushAjaxUpdate(String message){
        messages.add(message);
        SessionRenderer.render("pushMessages");
    }
}
