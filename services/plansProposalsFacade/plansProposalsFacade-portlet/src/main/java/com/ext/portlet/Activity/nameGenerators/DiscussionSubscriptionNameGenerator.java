package com.ext.portlet.Activity.nameGenerators;

import com.ext.portlet.model.ActivitySubscription;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.ext.utils.NavigationUrl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.utils.IdListUtil;

import java.util.List;

public class DiscussionSubscriptionNameGenerator extends BaseSubscriptionNameGenerator {

    private final static Log _log = LogFactoryUtil.getLog(DiscussionSubscriptionNameGenerator.class);

    public static String hyperlink = "<a href=\"%s\">%s</a>";

    @Override
    public String getName(ActivitySubscription subscription) {
        Long classNameId = subscription.getClassNameId();
        Long classPK = subscription.getClassPK();
        Integer type = subscription.getType();
        String extraData = subscription.getExtraData();
        
        /*
         * activity stream name for given parameters is decoded in following
         * way: 1. find discussion group category and take it name 2. if
         * extraData is null end procedure 3. if extraData isn't null split it
         * by comma 4. take first part of splited string, it represents id of
         * discussioncategory (get name of that category) 5. take second part of
         * splited string (if exists), it represents id of discussionthread
         * 
         * After all of that return combined string.
         */
        StringBuilder name = new StringBuilder();

        try {
            DiscussionCategoryGroup group = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(classPK);
            name.append(getCategoryGroup(group));

            if (extraData != null && !extraData.isEmpty()) {
                List<Long> ids = IdListUtil.getIdsFromString(extraData);
                if (!ids.isEmpty()) {
                    long categoryId = ids.get(0);
                    DiscussionCategory category = DiscussionCategoryLocalServiceUtil
                            .getDiscussionCategoryById(categoryId);
                    name.append(" &gt; ");
                    name.append(getCategory(category));
                }
                if (ids.size()> 1) {
                    long threadId = ids.get(1);
                    DiscussionMessage message = DiscussionMessageLocalServiceUtil.getThreadByThreadId(threadId);

                    name.append(" &gt; ");
                    name.append(getDiscussion(message));
                }
            }
        } catch (PortalException | SystemException | NumberFormatException e) {
            _log.error("Can't read activity name for discussion classPk: " + classPK + "\textra data: " + extraData, e);
        }

        return name.toString();
    }

    public String getCategory(DiscussionCategory category) throws PortalException, SystemException {
        DiscussionCategoryGroup categoryGroup = DiscussionCategoryLocalServiceUtil.getCategoryGroup(category);

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());

        return String.format(
                hyperlink,
                navUrl.getUrlWithParameters("discussion", "pageType", "CATEGORY", "categoryId",
                        String.valueOf(category.getCategoryId())).toString(), category.getName());
    }

    public String getDiscussion(DiscussionMessage discussion) throws PortalException, SystemException {
        DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(discussion);

        DiscussionMessage thread = discussion.getThreadId() > 0 ? DiscussionMessageLocalServiceUtil
                .getThread(discussion) : discussion;

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
        return String.format(
                hyperlink,
                navUrl.getUrlWithParameters("discussion", "pageType", "THREAD", "threadId",
                        String.valueOf(thread.getMessageId())).toString(), thread.getSubject());
    }

    public String getComment(DiscussionMessage comment) throws PortalException, SystemException {
        DiscussionCategoryGroup categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(comment);
        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());

        String text = comment.getBody().trim();
        text = text.substring(0, Math.min(20, text.length())) + "...";

        return String.format(
                hyperlink,
                navUrl.getUrlWithParameters("discussion", "pageType", "THREAD", "threadId",
                        String.valueOf(comment.getThreadId()), "messageId", String.valueOf(comment.getMessageId()))
                        .toString(), text);
    }

    public String getCategoryGroup(DiscussionCategoryGroup categoryGroup) {

        NavigationUrl navUrl = new NavigationUrl(categoryGroup.getUrl());
        return String.format(hyperlink, navUrl.toString(), categoryGroup.getDescription());
    }

    @Override
    protected Class<?> getSupportedClass() {
        return DiscussionCategoryGroup.class;
    }

}
