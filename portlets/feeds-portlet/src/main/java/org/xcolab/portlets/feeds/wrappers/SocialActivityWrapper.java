package org.xcolab.portlets.feeds.wrappers;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.ocpsoft.pretty.time.PrettyTime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xcolab.activityEntry.ActivityEntryType;
import org.xcolab.activityEntry.discussion.DiscussionBaseActivityEntry;
import org.xcolab.activityEntry.member.MemberJoinedActivityEntry;
import org.xcolab.activityEntry.proposal.ProposalBaseActivityEntry;
import org.xcolab.client.activities.pojo.ActivityEntry;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;

public class SocialActivityWrapper implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActivityEntry activity;

    private int daysBetween;
    private boolean indicateNewDate;
    private final static Log _log = LogFactoryUtil.getLog(SocialActivityWrapper.class);
    private long daysAgo = 0;
    private String body;
    private static PrettyTime timeAgoConverter = new PrettyTime();
    private final boolean odd;
    private PortletRequest request;


    public SocialActivityWrapper(ActivityEntry activity, int daysBetween, boolean indicateNewDate, boolean odd, PortletRequest request, int maxLength) {
        this.activity = activity;
        this.request = request;

        
        this.daysBetween = daysBetween;
        this.indicateNewDate = indicateNewDate;

        final int milisecondsInDay = 1000 * 60 * 60 * 24;
        long createDay = activity.getCreateDate().getTime() / milisecondsInDay;
        long daysNow = new Date().getTime() / milisecondsInDay;
        daysAgo = daysNow - createDay;
        body = activity.getActivityEntryBody();//getBodyFromFeedEntry(activityFeedEntry, maxLength);
        if (body != null) {
            body = body.replaceAll("c.my_sites[^\\\"]*", "web/guest/member/-/member/userId/" + activity.getMemberId());
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
        return new Date(activity.getCreateDate().getTime());
    }
    
    public boolean getIndicateNewDate() {
        return indicateNewDate;
    }

    public Boolean getIsEmpty() {
       return isEmpty(activity);
    }
    
    public static Boolean isEmpty(ActivityEntry entry) {
        String body = entry.getActivityEntryBody();//getBodyFromFeedEntry(entry, 0);
        return body == null || body.trim().length() == 0;
    }

    public static Boolean isEmpty(ActivityEntry activity, PortletRequest request) {
        try {

            //UserLocalServiceUtil.getUser(activity.getMemberId());
            //SocialActivityFeedEntry entry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY));
            return isEmpty(activity);
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
        return timeAgoConverter.format(new Date(activity.getCreateDate().getTime()));
    }
    
    public boolean isOdd() {
        return odd;
    }
    
    public ActivityType getType() {
        return ActivityType.getType(activity.getPrimaryType()+ "",activity.getSecondaryType()+"");
    }


    public static enum ActivityType {
		VOTE("up", ActivityEntryType.PROPOSOSAL.getPrimaryTypeId() +"" + ProposalBaseActivityEntry.ProposalActivitySubType.PROPOSAL_VOTE.getSecondaryTypeId(),
                ActivityEntryType.PROPOSOSAL.getPrimaryTypeId() +"" + ProposalBaseActivityEntry.ProposalActivitySubType.PROPOSAL_VOTE_RETRACT.getSecondaryTypeId(),
                ActivityEntryType.PROPOSOSAL.getPrimaryTypeId() +"" + ProposalBaseActivityEntry.ProposalActivitySubType.PROPOSAL_VOTE_SWITCH.getSecondaryTypeId(),
                ActivityEntryType.PROPOSOSAL.getPrimaryTypeId() +"" + ProposalBaseActivityEntry.ProposalActivitySubType.PROPOSAL_SUPPORTER_ADDED.getSecondaryTypeId(),
                ActivityEntryType.PROPOSOSAL.getPrimaryTypeId() +"" + ProposalBaseActivityEntry.ProposalActivitySubType.PROPOSAL_SUPPORTER_REMOVED.getSecondaryTypeId()
		),
		EDIT("edit",
                ActivityEntryType.PROPOSOSAL.getPrimaryTypeId() +"" + ProposalBaseActivityEntry.ProposalActivitySubType.PROPOSAL_ATTRIBUTE_UPDATE.getSecondaryTypeId(),
                ActivityEntryType.PROPOSOSAL.getPrimaryTypeId() +"" + ProposalBaseActivityEntry.ProposalActivitySubType.PROPOSAL_MEMBER_ADDED.getSecondaryTypeId(),
                ActivityEntryType.PROPOSOSAL.getPrimaryTypeId() +"" + ProposalBaseActivityEntry.ProposalActivitySubType.PROPOSAL_MEMBER_REMOVED.getSecondaryTypeId()
				),
		NEW("new",
                ActivityEntryType.PROPOSOSAL.getPrimaryTypeId() +"" + ProposalBaseActivityEntry.ProposalActivitySubType.PROPOSAL_CREATED.getSecondaryTypeId()),
		COMMENT("comment",
                ActivityEntryType.DISCUSSION.getPrimaryTypeId() +"" + DiscussionBaseActivityEntry.DiscussionActivitySubType.DISCUSSION_ADDED.getSecondaryTypeId(),
                ActivityEntryType.DISCUSSION.getPrimaryTypeId() +"" + DiscussionBaseActivityEntry.DiscussionActivitySubType.DISCUSSION_CATEGORY_ADDED.getSecondaryTypeId(),
                ActivityEntryType.DISCUSSION.getPrimaryTypeId() +"" + DiscussionBaseActivityEntry.DiscussionActivitySubType.DISCUSSION_PROPOSAL_COMMENT.getSecondaryTypeId(),
                ActivityEntryType.DISCUSSION.getPrimaryTypeId() +"" + DiscussionBaseActivityEntry.DiscussionActivitySubType.DISCUSSION_FORUM_COMMENT.getSecondaryTypeId()
        ),
        USER("new_user",
                ActivityEntryType.MEMBER.getPrimaryTypeId() +"" + MemberJoinedActivityEntry.MemberSubActivityType.MEMBER_JOINED.getSecondaryTypeId());

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

        public static ActivityType getType(String clasz, String type) {
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
