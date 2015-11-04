/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.portlets.discussions.activity;

import com.ext.portlet.Activity.BaseFeedEntryWithMailInfo;
import com.ext.portlet.Activity.DiscussionActivityKeys;
import com.ext.portlet.Activity.ICollabActivityInterpreter;
import com.ext.portlet.NoSuchDiscussionMessageException;
import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import org.apache.commons.lang.StringEscapeUtils;
import org.climatecollaboratorium.facelets.discussions.activity.NavigationUrl;
import org.xcolab.utils.IdListUtil;

import java.util.List;

public class DiscussionActivityFeedEntry extends BaseSocialActivityInterpreter implements ICollabActivityInterpreter {

    private static final String[] _CLASS_NAMES = { DiscussionCategoryGroup.class.getName(),
            DiscussionCategory.class.getName(), DiscussionMessage.class.getName() };

    @Override
    public String[] getClassNames() {
        return _CLASS_NAMES;
    }

    private static final Log _log = LogFactoryUtil.getLog(DiscussionActivityFeedEntry.class);

    public static final String CATEGORY_ADDED = "%s added a category %s to %s"; // user,
                                                                        // categorygroup
    public static final String DISCUSSION_ADDED = "%s started a new discussion %s in %s"; // user,
                                                                                  // thread,
                                                                                  // categorygroup
    public static final String FORUM_COMMENT_ADDED = "%s added a comment to %s in %s"; // user,
                                                                         // thread,
                                                                         // category
    public static final String PROPOSAL_COMMENT_ADDED = "%s added a comment to %s"; // user,
                                                                              // comment,
                                                                              // thread,
                                                                              // categorygroup

    public static final String HYPERLINK_FORMAT = "<a href=\"%s\">%s</a>";

    /**
     * Activities format is as follows: a) activity.className should be
     * DiscussionCategoryGroup b) if activity refers to category/thread their
     * ids will be available in extra data (for category it will be just
     * category for thread it will be: "categoryId,threadId", for message
     * "categoryId,threadId,messageId" c) for backward compatibility approach
     * mentioned above will be used only when activity class name is
     * DiscussionCategoryGroup
     */
    @Override
    protected SocialActivityFeedEntry doInterpret(SocialActivity activity, ThemeDisplay themeDisplay) throws PortalException, SystemException {

        try {
            DiscussionActivityKeys activityType = DiscussionActivityKeys.fromId(activity.getType());

            String body = "";
            String mailSubject = activityType.getPrettyName();
            String mailBody = "";
            String title = activityType.getPrettyName();
            List<Long> ids = IdListUtil.getIdsFromString(activity.getExtraData());

            if (activityType == DiscussionActivityKeys.ADD_CATEGORY) {
                DiscussionCategory category;

                if (activity.getClassName().equals(DiscussionCategoryGroup.class.getName())) {
                    category = DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(ids.get(0));
                } else {
                    category = DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(activity.getClassPK());
                }
                DiscussionCategoryGroup categoryGroup = DiscussionCategoryLocalServiceUtil.getCategoryGroup(category);

                body = String.format(CATEGORY_ADDED, getUser(activity), getDiscussionCategoryLink(category),
                        getCategoryGroupLink(categoryGroup));
                mailBody = body;
                // body = String.format(CATEGORY_ADDED,
                // navUrl.getUrlWithParameters("discussion",
                // keyValue)getUser(activity), getDiscussionCategoryLink(category),
                // getCategoryGroupLink(categoryGroup));
            } else if (activityType == DiscussionActivityKeys.ADD_DISCUSSION) {
                /*
                 * for backward compatibility check if class name is activity
                 * class name is discussioncategorygroup, if it is then read
                 * threadid from extra data if it is't, read thread id from
                 * classPK
                 */

                DiscussionMessage discussion;
                try{
                    if (activity.getClassName().equals(DiscussionCategoryGroup.class.getName())) {
                        discussion = DiscussionMessageLocalServiceUtil.getThreadByThreadId(ids.get(1));
                    } else {
                        discussion = DiscussionMessageLocalServiceUtil.getThreadByThreadId(activity.getClassPK());
                    }
                } catch (SystemException | NoSuchDiscussionMessageException e){
                    // No discussion could be found - return null and discard activity
                    return null;
                }
                DiscussionCategory category = DiscussionMessageLocalServiceUtil.getCategory(discussion);

                body = String.format(DISCUSSION_ADDED, getUser(activity), getDiscussionMessageLink(discussion),
                        getDiscussionCategoryLink(category));
                mailBody = getMailBodyForForumComment(discussion);
            } else if (activityType == DiscussionActivityKeys.ADD_FORUM_COMMENT) {
                DiscussionMessage comment;

                if (activity.getClassName().equals(DiscussionCategoryGroup.class.getName())) {
                    comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(ids.get(2));
                } else {
                    comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(activity.getClassPK());
                }

                DiscussionMessage discussion = DiscussionMessageLocalServiceUtil.getThread(comment);
                DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(comment);
                if (discussion == DiscussionCategoryGroupLocalServiceUtil.getCommentThread(categoryGroup)) {
                    body = String.format(PROPOSAL_COMMENT_ADDED, getUser(activity),
                            getCategoryGroupLink(DiscussionMessageLocalServiceUtil.getCategoryGroup(discussion)));
                    mailBody = getMailBodyForForumComment(comment);
                } else {
                    body = String.format(FORUM_COMMENT_ADDED, getUser(activity), getDiscussionMessageLink(discussion),
                            getDiscussionCategoryLink(DiscussionMessageLocalServiceUtil.getCategory(discussion)));
                    mailBody = getMailBodyForForumComment(comment);
                }
            } else if (activityType == DiscussionActivityKeys.ADD_PROPOSAL_DISCUSSION_COMMENT) {
                DiscussionMessage comment;

                if (activity.getClassName().equals(DiscussionCategoryGroup.class.getName())) {
                    comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(ids.get(2));
                } else {
                    comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(activity.getClassPK());
                }

                DiscussionMessage discussion = DiscussionMessageLocalServiceUtil.getThread(comment);

                body = String.format(PROPOSAL_COMMENT_ADDED, getUser(activity),
                        getCategoryGroupLink(DiscussionMessageLocalServiceUtil.getCategoryGroup(discussion)));

                mailBody = getMailBodyForProposalComment(comment);
            }

            return new BaseFeedEntryWithMailInfo("", title, body, mailSubject, mailBody);
        }

        catch (SystemException | PortalException e) {
        	_log.error(String.format("Can't interpret activity (%s)", activity.toString()) , e);
        }
        
        return null;

    }

    private String getUser(SocialActivity activity) {
        try {
            return CommunityUtil.generateUserURL(activity.getUserId());
        } catch (PortalException | SystemException e) {
            _log.info(e.getMessage());
        }
        return "<user removed>";
    }

    public String getCategoryGroupLink(DiscussionCategoryGroup categoryGroup) {

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
        return String.format(HYPERLINK_FORMAT, StringEscapeUtils.escapeHtml(navUrl.toString()), categoryGroup.getDescription());
    }

    public String getDiscussionCategoryLink(DiscussionCategory category) throws PortalException, SystemException {
        DiscussionCategoryGroup categoryGroup = DiscussionCategoryLocalServiceUtil.getCategoryGroup(category);

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());

        return String.format(
                HYPERLINK_FORMAT,
                StringEscapeUtils.escapeHtml(navUrl.getUrlWithParameters("discussion", "pageType", "CATEGORY", "categoryId",
                        String.valueOf(category.getCategoryId())).toString()), category.getName());
    }

    public String getDiscussionMessageLink(DiscussionMessage discussion) throws PortalException, SystemException {
        DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(discussion);

        DiscussionMessage thread = discussion.getThreadId() > 0 ? DiscussionMessageLocalServiceUtil
                .getThread(discussion) : discussion;

        String linkText;
        if (Validator.isNull(thread.getSubject())) {
            linkText = "climatecolab.org";
        } else {
            linkText = thread.getSubject();
        }
        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
        return String.format(
                HYPERLINK_FORMAT,
                StringEscapeUtils.escapeHtml(navUrl.getUrlWithParameters("discussion", "pageType", "THREAD", "threadId",
                        String.valueOf(thread.getMessageId())).toString()), linkText);
    }

    public String getDiscussionComment(DiscussionMessage comment) throws PortalException, SystemException {
        DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(comment);
        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());

        String text = comment.getBody().trim();
        text = text.substring(0, Math.min(20, text.length())) + "...";

        return String.format(
                HYPERLINK_FORMAT,
                StringEscapeUtils.escapeHtml(navUrl.getUrlWithParameters("discussion", "pageType", "THREAD", "threadId",
                        String.valueOf(comment.getThreadId()), "messageId", String.valueOf(comment.getMessageId()))
                        .toString()), text);
    }

    @Override
    public String getName(Long classNameId, Long classPK, Integer type, String extraData) {
        /*
         * activity stream name for given parameters is decoded in following
         * way: 1. find discussion group category and take it name 2. if
         * extraData is null end procedure 3. if extraData isn't null split it
         * by comma 4. take first part of split string, it represents id of
         * discussioncategory (get name of that category) 5. take second part of
         * split string (if exists), it represents id of discussionthread
         * 
         * After all of that return combined string.
         */
        StringBuilder name = new StringBuilder();

        try {
            DiscussionCategoryGroup group = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(classPK);
            name.append(getCategoryGroupLink(group));

            if (extraData != null && !extraData.isEmpty()) {
                List<Long> ids = IdListUtil.getIdsFromString(extraData);
                if (!ids.isEmpty()) {
                    long categoryId = ids.get(0);
                    DiscussionCategory category = DiscussionCategoryLocalServiceUtil
                            .getDiscussionCategoryById(categoryId);
                    name.append(" &gt; ");
                    name.append(getDiscussionCategoryLink(category));
                }
                if (ids.size() > 1) {
                    long threadId = ids.get(1);
                    DiscussionMessage message = DiscussionMessageLocalServiceUtil.getThreadByThreadId(threadId);

                    name.append(" &gt; ");
                    name.append(getDiscussionMessageLink(message));
                }
            }
        } catch (PortalException | SystemException | NumberFormatException e) {
            _log.error("Can't read activity name for discussion classPk: " + classPK + "\textra data: " + extraData, e);
        }

        return name.toString();
    }

    private final static String PROPOSAL_COMMENT_MSG_MAIL_BODY_TEMPLATE = "<b>USER_LINK_PLACEHOLDER posted a comment on the THREAD_TOPIC_PLACEHOLDER</b>:" +
            "<br/><br/>" + "<div style='margin-left: 20px'>MESSAGE_BODY_PLACEHOLDER</div>";

    private final static String FORUM_COMMENT_MSG_MAIL_BODY_TEMPLATE = "<b>USER_LINK_PLACEHOLDER posted a comment on THREAD_TOPIC_PLACEHOLDER in the forum THREAD_GROUP_PLACEHOLDER</b>:" +
            "<br/><br/>" + "<div style='margin-left: 20px'>MESSAGE_BODY_PLACEHOLDER</div>";

    private final static String USER_LINK_PLACEHOLDER = "USER_LINK_PLACEHOLDER";
    private final static String THREAD_TOPIC_PLACEHOLDER = "THREAD_TOPIC_PLACEHOLDER";
    private final static String MESSAGE_BODY_PLACEHOLDER = "MESSAGE_BODY_PLACEHOLDER";
    private final static String THREAD_GROUP_PLACEHOLDER = "THREAD_GROUP_PLACEHOLDER";

    private String getMailBodyForProposalComment(DiscussionMessage message) throws SystemException, PortalException {
        try {
            String tmp = StringUtil.replace(PROPOSAL_COMMENT_MSG_MAIL_BODY_TEMPLATE, USER_LINK_PLACEHOLDER,
                    getUserLink(DiscussionMessageLocalServiceUtil.getAuthor(message)));
            tmp = StringUtil.replace(tmp, THREAD_TOPIC_PLACEHOLDER, getCategoryGroupLink(DiscussionMessageLocalServiceUtil.getCategoryGroup(message)));
            tmp = StringUtil.replace(tmp, MESSAGE_BODY_PLACEHOLDER, message.getBody().replace("\\n", "<br/>"));
            return tmp;
        } catch (SystemException | PortalException t) {
            _log.error(t);
        }
        return StringPool.BLANK;
    }

    private String getMailBodyForForumComment(DiscussionMessage message) throws SystemException, PortalException {
        try {
            String tmp = StringUtil.replace(FORUM_COMMENT_MSG_MAIL_BODY_TEMPLATE, USER_LINK_PLACEHOLDER,
                    getUserLink(DiscussionMessageLocalServiceUtil.getAuthor(message)));
            tmp = StringUtil.replace(tmp, THREAD_TOPIC_PLACEHOLDER, getDiscussionMessageLink(message));
            tmp = StringUtil.replace(tmp, THREAD_GROUP_PLACEHOLDER, getDiscussionCategoryLink(DiscussionMessageLocalServiceUtil.getCategory(message)));
            tmp = StringUtil.replace(tmp, MESSAGE_BODY_PLACEHOLDER, message.getBody());
            return tmp;
        } catch (SystemException e) {
            _log.error(e);
        }
        return StringPool.BLANK;
    }

    private String getUserLink(User user) {
        return "<a style='color: black' href='http://climatecolab.org/web/guest/member/-/member/userId/" + user.getUserId() + "'>"
                + user.getScreenName() + "</a>";
    }

}
