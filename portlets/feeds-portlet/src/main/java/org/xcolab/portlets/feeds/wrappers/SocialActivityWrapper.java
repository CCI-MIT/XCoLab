package org.xcolab.portlets.feeds.wrappers;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;

import com.ext.portlet.Activity.DiscussionActivityKeys;
import com.ext.portlet.Activity.LoginRegisterActivityKeys;
import com.ext.portlet.Activity.ProposalActivityKeys;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.Proposal;
import com.liferay.portal.model.User;
import org.jsoup.Jsoup;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.ocpsoft.pretty.time.PrettyTime;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SocialActivityWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SocialActivity activity;
    private SocialActivityFeedEntry activityFeedEntry;
    private int daysBetween;
    private boolean indicateNewDate;
    private final static Log _log = LogFactoryUtil.getLog(SocialActivityWrapper.class);
    private long daysAgo = 0;
    private String body;
    private static PrettyTime timeAgoConverter = new PrettyTime();
    private final boolean odd;
    private PortletRequest request;


    public SocialActivityWrapper(SocialActivity activity, int daysBetween, boolean indicateNewDate, boolean odd, PortletRequest request, int maxLength) {
        this.activity = activity;
        this.request = request;
        try {
            activityFeedEntry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY));
        } catch(Exception e) {
            e.printStackTrace();
            //ignore
        }
        
        this.daysBetween = daysBetween;
        this.indicateNewDate = indicateNewDate;

        final int milisecondsInDay = 1000 * 60 * 60 * 24;
        long createDay = activity.getCreateDate() / milisecondsInDay;
        long daysNow = new Date().getTime() / milisecondsInDay;
        daysAgo = daysNow - createDay;
        body = getBodyFromFeedEntry(activityFeedEntry, maxLength);
        if (body != null) {
            body = body.replaceAll("c.my_sites[^\\\"]*", "web/guest/member/-/member/userId/" + activity.getUserId());
        }
        
        this.odd = odd;
    }
    
    private static String getBodyFromFeedEntry(SocialActivityFeedEntry entry, int maxLength) {
        if (entry == null) {
            return "";
        }
        String body =  (entry.getBody().trim().equals("") ? entry.getTitle() : entry.getBody());
		Document html = Jsoup.parse(body);
        String plainText = html.text();

		if (maxLength > 0 && plainText.length() > maxLength) {
			String dots = "...";

            // Determine length of all link texts
            int linkTextLength = 0;
            Elements linkElements = html.select("a");
            for (Element linkElement : linkElements) {
                linkTextLength += linkElement.text().length();
            }

            // Calculate max space left for link texts
            int charactersForEachLink = (int)Math.floor(1.0 * (maxLength - (plainText.length() - linkTextLength)) / (1.0 * linkElements.size()));
            for (int i = 0; i < linkElements.size(); i++) {
                String text = linkElements.get(i).text();

                // Trim text if necessary
                if (text.length() > charactersForEachLink) {
                    text = text.substring(0, charactersForEachLink - dots.length());
                    linkElements.get(i).text(text + dots);
                }
            }

			body = html.select("body").html();
		}

		return body;
    }
    public String getBody() {
        return body;
    }

    public boolean isToday() {
        return daysBetween == 0;
    }
    
    public boolean isYesterday() {
        return daysBetween == 1;
    }
    
    public Date getCreateDate() {
        return new Date(activity.getCreateDate());
    }
    
    public boolean getIndicateNewDate() {
        return indicateNewDate;
    }

    public Boolean getIsEmpty() {
       return isEmpty(activityFeedEntry); 
    }
    
    public static Boolean isEmpty(SocialActivityFeedEntry entry) {
        String body = getBodyFromFeedEntry(entry, 0);
        return body == null || body.trim().length() == 0;
    }

    public static Boolean isEmpty(SocialActivity activity, PortletRequest request) {
        try {

            UserLocalServiceUtil.getUser(activity.getUserId());
            SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY));
            return isEmpty(entry);
        } catch (Throwable e) {
            _log.error("Some error interpreting activity: "+e.getMessage());
            return false;
        }
    }

    public void setDaysAgo(long daysAgo) {
        this.daysAgo = daysAgo;
    }

    public long getDaysAgo() {
        return daysAgo;
    }
    
    public String getActivityDateAgo() {
        return timeAgoConverter.format(new Date(activity.getCreateDate()));
    }
    
    public boolean isOdd() {
        return odd;
    }
    
    public ActivityType getType() {
        return ActivityType.getType(activity.getClassName(), activity.getType());
    }


    public static enum ActivityType {
		VOTE("up", Proposal.class.getName() + ProposalActivityKeys.VOTE.ordinal(),
                Proposal.class.getName() + ProposalActivityKeys.VOTE_RETRACT.ordinal(),
                Proposal.class.getName() + ProposalActivityKeys.VOTE_SWITCH.ordinal(),
				"com.ext.portlet.plans.model.PlanItem7",
				"com.ext.portlet.plans.model.PlanItem8",
				"com.ext.portlet.plans.model.PlanItem9",
				"com.ext.portlet.plans.model.PlanItem10",
				"com.ext.portlet.plans.model.PlanItem11",
				"com.ext.portlet.plans.model.PlanItem14",
				"com.ext.portlet.plans.model.PlanItem15"
		),
		EDIT("edit", Proposal.class.getName() + ProposalActivityKeys.ATTRIBUTE_UPDATE.ordinal(),
				Proposal.class.getName() + ProposalActivityKeys.USER_ADD.ordinal(),
                Proposal.class.getName() + ProposalActivityKeys.USER_REMOVE.ordinal(),
				"com.ext.portlet.plans.model.PlanItem0",
				"com.ext.portlet.plans.model.PlanItem2",
				"com.ext.portlet.plans.model.PlanItem3",
				"com.ext.portlet.plans.model.PlanItem4",
				"com.ext.portlet.plans.model.PlanItem5",
				"com.ext.portlet.plans.model.PlanItem6",
				"com.ext.portlet.plans.model.PlanItem12",
				"com.ext.portlet.plans.model.PlanItem13",
				"com.ext.portlet.plans.model.PlanItem16",
				"com.ext.portlet.plans.model.PlanItem17",
				"com.ext.portlet.plans.model.PlanItem18",
                "com.liferay.portlet.blogs.model.BlogsEntry3"),
		NEW("new", Proposal.class.getName() + ProposalActivityKeys.PROPOSAL_CREATE.ordinal(),
				"com.ext.portlet.plans.model.PlanItem1",
                "com.liferay.portlet.blogs.model.BlogsEntry2"),
		COMMENT("comment", DiscussionCategoryGroup.class.getName() + DiscussionActivityKeys.ALL.ordinal(),
                DiscussionCategoryGroup.class.getName() + DiscussionActivityKeys.ADD_CATEGORY.ordinal(),
                DiscussionCategoryGroup.class.getName() + DiscussionActivityKeys.ADD_DISCUSSION.ordinal(),
                DiscussionCategoryGroup.class.getName() + DiscussionActivityKeys.ADD_DISCUSSION_COMMENT.ordinal(),
                DiscussionCategoryGroup.class.getName() + DiscussionActivityKeys.ADD_COMMENT.ordinal(),
                "com.liferay.portlet.blogs.model.BlogsEntry1"),
        USER("new_user", User.class.getName()+ LoginRegisterActivityKeys.USER_REGISTERED.getType());

        private final String[] classes;
        private final String displayName;
        private final static Map<String, ActivityType> activityMap = new HashMap<String, SocialActivityWrapper.ActivityType>();
        private final static ActivityType defaultType = COMMENT;

        static {
            for (ActivityType t: ActivityType.values()) {
                for (String clasz: t.classes) {
                    activityMap.put(clasz, t);
                }
            }
        }

        public static ActivityType getType(String clasz, int type) {
            ActivityType t = activityMap.get(clasz + type);
            return t == null ? defaultType : t;
        }

        ActivityType(String displayName, String...classes) {
            this.displayName = displayName;
            this.classes = classes;
        }

        public String getDisplayName() {
            return displayName;
        }

    }
}
