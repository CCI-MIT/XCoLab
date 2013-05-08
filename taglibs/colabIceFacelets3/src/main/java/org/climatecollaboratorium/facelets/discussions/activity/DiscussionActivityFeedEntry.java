/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.facelets.discussions.activity;


import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.Activity.BaseFeedEntryWithMailInfo;
import com.ext.portlet.Activity.ICollabActivityInterpreter;
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
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;


public class DiscussionActivityFeedEntry extends BaseSocialActivityInterpreter implements ICollabActivityInterpreter {
	
    private static final String[] _CLASS_NAMES = {
        DiscussionCategoryGroup.class.getName(),
        DiscussionCategory.class.getName(),
        DiscussionMessage.class.getName()
    };
	
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}
	
	private static Log _log = LogFactoryUtil.getLog(DiscussionActivityFeedEntry.class);
	
	public static String CATEGORY_ADDED = "%s added category %s to %s"; // user, categorygroup
	public static String DISCUSSION_ADDED = "%s started new discussion %s in %s"; // user, thread, categorygroup
    public static String COMMENT_ADDED = "%s added comment to %s in %s"; // user, thread, category
    public static String DISCUSSION_COMMENT_ADDED = "%s added comment to %s"; // user, comment, thread, categorygroup
	
	public static String hyperlink = "<a href=\"%s\">%s</a>";
		
	/**
	 * Activities format is as follows:
	 *  a) activity.className should be DiscussionCategoryGroup 
	 *  b) if activity refers to category/thread their ids will be available in extra data (for category it will be just category
	 *       for thread it will be: "categoryId,threadId", for message "categoryId,threadId,messageId"
	 *  c) for backward compatibility approach mentioned above will be used only when activity class name is 
	 *       DiscussionCategoryGroup 
	 */
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {
		
			try {	
		DiscussionActivityKeys activityType = DiscussionActivityKeys.fromId(activity.getType());
		
		String body =  "";
		String mailSubject = activityType.getPrettyName();
		String mailBody = "";
		String title = activityType.getPrettyName();
		Long[] ids = ActivityUtil.getIdsFromExtraData(activity.getExtraData());
		
		if (activityType == DiscussionActivityKeys.ADD_CATEGORY) {
		    DiscussionCategory category = null;

	        if (activity.getClassName().equals(DiscussionCategoryGroup.class.getName())) {
	            category = DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(ids[0]);
	        }
	        else {
	            category = DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(activity.getClassPK());
	        }
		    DiscussionCategoryGroup categoryGroup = DiscussionCategoryLocalServiceUtil.getCategoryGroup(category);
		    
		    body = String.format(CATEGORY_ADDED, getUser(activity), getCategory(category), getCategoryGroup(categoryGroup));
		    mailBody = body;
            //body = String.format(CATEGORY_ADDED, navUrl.getUrlWithParameters("discussion", keyValue)getUser(activity), getCategory(category), getCategoryGroup(categoryGroup));
		}
		else if (activityType == DiscussionActivityKeys.ADD_DISCUSSION) {
		    /*
		     *  for backward compatibility check if class name is activity class name is discussioncategorygroup,
		     *    if it is then read threadid from extra data
		     *    if it is't, read thread id from classPK 
		     */
		    
            DiscussionMessage discussion = null;
            if (activity.getClassName().equals(DiscussionCategoryGroup.class.getName())) {
                discussion = DiscussionMessageLocalServiceUtil.getThreadByThreadId(ids[1]);
            }
            else {
                discussion = DiscussionMessageLocalServiceUtil.getThreadByThreadId(activity.getClassPK());
            }
            DiscussionCategory category = DiscussionMessageLocalServiceUtil.getCategory(discussion);
            DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(discussion);
            
		    body = String.format(DISCUSSION_ADDED, getUser(activity), getDiscussion(discussion), getCategory(category));
		    mailBody = getMailBodyForMessage(discussion);
		}
		else if (activityType == DiscussionActivityKeys.ADD_COMMENT) {
		    DiscussionMessage comment  = null;
            
            if (activity.getClassName().equals(DiscussionCategoryGroup.class.getName())) {
                comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(ids[2]);
            }
            else {
                comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(activity.getClassPK());
            }
            
            DiscussionMessage discussion = DiscussionMessageLocalServiceUtil.getThread(comment);
            DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(comment);
		    if (discussion == DiscussionCategoryGroupLocalServiceUtil.getCommentThread(categoryGroup)) {
	            body = String.format(DISCUSSION_COMMENT_ADDED, getUser(activity), getCategoryGroup(DiscussionMessageLocalServiceUtil.getCategoryGroup(discussion)));
		    }
		    else {
	            body = String.format(COMMENT_ADDED, getUser(activity),  getDiscussion(discussion), getCategory(DiscussionMessageLocalServiceUtil.getCategory(discussion)));
		    }
		    mailBody = getMailBodyForMessage(comment);
        }
		else if (activityType == DiscussionActivityKeys.ADD_DISCUSSION_COMMENT) {
		    DiscussionMessage comment  = null;
            
            if (activity.getClassName().equals(DiscussionCategoryGroup.class.getName())) {
                comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(ids[2]);
            }
            else {
                comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(activity.getClassPK());
            }
            
            
            
            DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(comment);
            DiscussionMessage discussion = DiscussionMessageLocalServiceUtil.getThread(comment);

            body = String.format(DISCUSSION_COMMENT_ADDED, getUser(activity), getCategoryGroup(DiscussionMessageLocalServiceUtil.getCategoryGroup(discussion)));
            mailBody = getMailBodyForMessage(comment);
		}

        return new BaseFeedEntryWithMailInfo("", title, body, mailSubject, mailBody);
			}
        
		catch (Exception e) {
		    e.printStackTrace();
		}
	
		return null;
		
	}
		
	private String getUser(SocialActivity activity) {
		String result = "<user removed>";
		try {
			return CommunityUtil.generateUserURL(activity.getUserId());
		} catch (PortalException e) {
			_log.info(e.getMessage());
		} catch (SystemException e) {
			_log.info(e.getMessage());
		}
		return result;
	}
	
	public String getCategoryGroup(DiscussionCategoryGroup categoryGroup) {

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
	    return String.format(hyperlink, navUrl.toString(), categoryGroup.getDescription());
	}
	
	public String getCategory(DiscussionCategory category) throws PortalException, SystemException {
	    DiscussionCategoryGroup categoryGroup = DiscussionCategoryLocalServiceUtil.getCategoryGroup(category);

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
        
        return String.format(hyperlink, 
                navUrl.getUrlWithParameters("discussion", "pageType", "CATEGORY", "categoryId", String.valueOf(category.getCategoryId())).toString(), 
                category.getName());
    }
	
	public String getDiscussion(DiscussionMessage discussion) throws PortalException, SystemException {
        DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(discussion);
        
        DiscussionMessage thread = discussion.getThreadId() > 0 ? DiscussionMessageLocalServiceUtil.getThread(discussion) : discussion;

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
	    return String.format(hyperlink, 
	            navUrl.getUrlWithParameters("discussion", "pageType", "THREAD", "threadId", String.valueOf(thread.getMessageId())).toString(), 
	            thread.getSubject());
	}
	
	public String getComment(DiscussionMessage comment) throws PortalException, SystemException {
        DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(comment);
        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
        
        String text = comment.getBody().trim();
        text = text.substring(0, Math.min(20, text.length())) + "...";
        
        return String.format(hyperlink,
                navUrl.getUrlWithParameters("discussion", "pageType", "THREAD", "threadId", 
                        String.valueOf(comment.getThreadId()), "messageId", 
                                String.valueOf(comment.getMessageId())).toString(), 
                        text);
    }


    @Override
    public String getName(Long classNameId, Long classPK, Integer type, String extraData) {
        /*
         *  activity stream name for given parameters is decoded in following way:
         *  1. find discussion group category and take it name
         *  2. if extraData is null end procedure
         *  3. if extraData isn't null split it by comma
         *  4. take first part of splited string, it represents id of discussioncategory (get name of that category)
         *  5. take second part of splited string (if exists), it represents id of discussionthread
         *  
         *  After all of that return combined string. 
         */
        StringBuilder name = new StringBuilder();
           
        try {
            DiscussionCategoryGroup group = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(classPK);
            name.append(getCategoryGroup(group));
            
            if (extraData != null && extraData.length() > 0) {
                String[] ids = extraData.split(",");
                if (ids.length > 0) {
                    Long categoryId = Long.parseLong(ids[0]);
                    DiscussionCategory category = DiscussionCategoryLocalServiceUtil.getDiscussionCategoryById(categoryId);
                    name.append(" &gt; ");
                    name.append(getCategory(category));
                }
                if (ids.length > 1) {
                    Long threadId = Long.parseLong(ids[1]);
                    DiscussionMessage message = DiscussionMessageLocalServiceUtil.getThreadByThreadId(threadId);
                    
                    name.append(" &gt; ");
                    name.append(getDiscussion(message));
                }
            }
        } catch (PortalException e) {
            _log.error("Can't read activity name for discussion classPk: " + classPK + "\textra data: " + extraData, e);
        } catch (SystemException e) {
            _log.error("Can't read activity name for discussion classPk: " + classPK + "\textra data: " + extraData, e);
        } catch (NumberFormatException e) {
            _log.error("Can't read activity name for discussion classPk: " + classPK + "\textra data: " + extraData, e);
        }
        
        return name.toString(); 
    }
	
	private final static String DISCUSSION_MSG_MAIL_BODY_TEMPLATE = 
	    "USER_LINK_PLACEHOLDER posted a new message in the forum THREAD_TOPIC_PLACEHOLDER:\n\n" +
	    "MESSAGE_BODY_PLACEHOLDER\n\n" +
	    "Visit MESSAGE_LINK_PLACEHOLDER to view and respond to this message.\n";
	
	private final static String USER_LINK_PLACEHOLDER = "USER_LINK_PLACEHOLDER";
	private final static String THREAD_TOPIC_PLACEHOLDER = "THREAD_TOPIC_PLACEHOLDER";
	private final static String MESSAGE_BODY_PLACEHOLDER = "MESSAGE_BODY_PLACEHOLDER";
	private final static String MESSAGE_LINK_PLACEHOLDER = "MESSAGE_LINK_PLACEHOLDER";
	        
    private String getMailBodyForMessage(DiscussionMessage message) throws SystemException, PortalException {
        DiscussionMessage thread = message.getThreadId() > 0 ? DiscussionMessageLocalServiceUtil.getThread(message) : message;
        /*
        System.out.println(DISCUSSION_MSG_MAIL_BODY_TEMPLATE
                .replaceAll(USER_LINK_PLACEHOLDER, getUserLink(message.getAuthor())));
        
        System.out.println(DISCUSSION_MSG_MAIL_BODY_TEMPLATE
                .replaceAll(THREAD_TOPIC_PLACEHOLDER, thread.getSubject()));
        

        System.out.println(DISCUSSION_MSG_MAIL_BODY_TEMPLATE
                .replaceAll(MESSAGE_BODY_PLACEHOLDER, message.getBody()));

        System.out.println(DISCUSSION_MSG_MAIL_BODY_TEMPLATE
                .replaceAll(MESSAGE_LINK_PLACEHOLDER, getDiscussion(message)));
                */
        try {
            String tmp = DISCUSSION_MSG_MAIL_BODY_TEMPLATE.replace(USER_LINK_PLACEHOLDER, getUserLink(DiscussionMessageLocalServiceUtil.getAuthor(message)));
            tmp = tmp.replace(THREAD_TOPIC_PLACEHOLDER, thread.getSubject());
            tmp = tmp.replace(MESSAGE_BODY_PLACEHOLDER, message.getBody());
            tmp = tmp.replace(MESSAGE_LINK_PLACEHOLDER, getDiscussion(message));
            return tmp;
        }
        catch (Throwable t) {
            t.printStackTrace();
            System.out.println("user link: " + getUserLink(DiscussionMessageLocalServiceUtil.getAuthor(message)));
            System.out.println("thread topic: " + thread.getSubject());
            System.out.println("body: " + message.getBody());
            System.out.println("message link: " + getDiscussion(message));
        }
        return "";
    }
    
    private String getUserLink(User user) {
        return "<a href='http://climatecolab.org/web/guest/member/-/member/userId/" + user.getUserId() + "'>" + user.getScreenName() + "</a>";
    }
	

}
