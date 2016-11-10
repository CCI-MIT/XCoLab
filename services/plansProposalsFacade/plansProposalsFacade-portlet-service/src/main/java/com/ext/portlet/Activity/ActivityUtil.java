/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanClauseOccurImpl;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.exceptions.ActivityEntryNotFoundException;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.enums.ColabConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ActivityUtil {

    private static final Log _log = LogFactoryUtil.getLog(ActivityUtil.class);

    public static final long AGGREGATION_TIME_WINDOW = (long) 1000 * 60 * 60; // 1h

    private static final String ADMINISTRATOR_ROLE_NAME = "Administrator";

    public static List<ActivityEntry> retrieveAllActivities(int pagestart, int next) throws SystemException {
       return  ActivitiesClientUtil.getActivityEntries(pagestart, next, null, null);
        //return SocialActivityLocalServiceUtil.getSocialActivities(pagestart, next);
    }

    public static List<ActivityEntry> retrieveWindowedActivities(int start, int end) throws SystemException, PortalException {
        return retrieveWindowedActivities(start, end, false);
    }

    public static List<ActivityEntry> retrieveWindowedActivities(int start, int end, boolean showAdmin) throws SystemException, PortalException {
        Hits hits;
        if (showAdmin) {
            hits = getAllAggregatedActivitySearchResults(start, end);
        } else {
            List<Long> administratorsIds = getAdministratorIds();
            hits = getAggregatedActivitySearchResultsExcludingUsers(administratorsIds, start, end);
        }

        return retrieveAggregatedSocialActivities(hits);
    }

    public static List<ActivityEntry> retrieveWindowedActivities(long userId, int start, int end) throws SystemException, SearchException {
        Hits hits = getAggregatedActivitySearchResults(userId, start, end);
        return retrieveAggregatedSocialActivities(hits);
    }

    private static List<ActivityEntry> retrieveAggregatedSocialActivities(Hits hits) {
        List<ActivityEntry> aggregatedSocialActivities = new ArrayList<>(hits.getLength());

        for (Document activityDoc : hits.getDocs()) {
            try {
                ActivityEntry sa = ActivitiesClientUtil.getActivityEntry(GetterUtil.getLong(activityDoc.getField("activityId").getValue()));
                aggregatedSocialActivities.add(sa);
            } catch (ActivityEntryNotFoundException ignored) {
            }
        }
        return aggregatedSocialActivities;
    }

    /*
    public static List<SocialActivity> groupAllActivities() throws SystemException {
        return groupActivities(SocialActivityLocalServiceUtil.getOrganizationActivities(ColabConstants.COLAB_COMPANY_ID, QueryUtil.ALL_POS, QueryUtil.ALL_POS));
    }*/

    public static List<ActivityEntry> groupUserActivities(long userId) throws SystemException {
        return groupActivities(
                ActivitiesClientUtil.getActivityEntries(0,1000,userId, null)
        );
    }

    public static List<ActivityEntry> groupActivities(List<ActivityEntry> activities) {
        //find all activities of same type
        Map<String, List<ActivityEntry>> activitiesMap = new HashMap<>(10000);
        for (ActivityEntry a : activities) {
            if (!activitiesMap.containsKey(getSocialActivityKey(a))) {
                activitiesMap.put(getSocialActivityKey(a), new LinkedList<ActivityEntry>());
            }
            activitiesMap.get(getSocialActivityKey(a)).add(a);
        }
        return clusterActivities(activitiesMap);
    }


    public static int getAllActivitiesCount() throws SystemException, SearchException {

        return ActivitiesClientUtil.countActivities(null, null);

    }

    public static int getActivitiesCount(long userId) throws SystemException, SearchException {

        int searchResultCount = getAggregatedActivitySearchResults(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS).getLength();
        if (searchResultCount == 0) {
            return groupUserActivities(userId).size();
        }

        return searchResultCount;
    }

    public static String getExtraDataForIds(Long... ids) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(ids[i]);
        }
        return sb.toString();
    }

    private static List<ActivityEntry> clusterActivities(Map<String, List<ActivityEntry>> activitiesMap) {
        //cluster
        List<ActivityEntry> aggregatedActivities = new LinkedList<>();
        Comparator<ActivityEntry> sorter = new Comparator<ActivityEntry>() {
            @Override
            public int compare(ActivityEntry o1, ActivityEntry o2) {
                return new Long(o1.getCreateDate().getTime()).compareTo(o2.getCreateDate().getTime());
            }
        };
        for (Collection<ActivityEntry> activitiesMapValue : activitiesMap.values()) {
            List<ActivityEntry> socialActivities = new ArrayList<>(activitiesMapValue); //convert to array for sorting
            Collections.sort(socialActivities, sorter);

            ActivityEntry curMin = null;
            for (ActivityEntry socialActivity : socialActivities) {
                if (curMin == null || socialActivity.getCreateDate().getTime() - curMin.getCreateDate().getTime() < AGGREGATION_TIME_WINDOW) {
                    curMin = socialActivity;
                } else {
                    aggregatedActivities.add(curMin);
                    curMin = socialActivity;
                }
            }
            aggregatedActivities.add(curMin);
        }

        // Sort the activities in reverse order (sort by date DESC)
        Collections.sort(aggregatedActivities, Collections.reverseOrder(sorter));

        return aggregatedActivities;
    }

    private static String getSocialActivityKey(ActivityEntry sa) {
        return sa.getPrimaryType() + "_" + sa.getClassPrimaryKey() + "_" + sa.getSecondaryType() + "_" + sa.getMemberId();
    }

    private static Hits getAggregatedActivitySearchResults(long userId, int start, int end) throws SearchException {
        SearchContext context = new SearchContext();
        context.setCompanyId(ColabConstants.COLAB_COMPANY_ID);
        BooleanQuery query = BooleanQueryFactoryUtil.create(context);
        //query.addRequiredTerm(Field.ENTRY_CLASS_NAME, SocialActivity.class.getName());

        BooleanQuery subQuery = BooleanQueryFactoryUtil.create(context);
        subQuery.addExactTerm("userId", userId);

        try {
            query.add(subQuery, BooleanClauseOccur.MUST);
        } catch (ParseException e) {
            _log.error(e);
        }

        Sort sort = SortFactoryUtil.create("createDate", Sort.FLOAT_TYPE, true);
        return SearchEngineUtil.search(SearchEngineUtil.getDefaultSearchEngineId(), context.getCompanyId(), query, sort, start, end);
    }

    private static Hits getAllAggregatedActivitySearchResults(int start, int end) throws SearchException {
        return getAggregatedActivitySearchResultsExcludingUsers(new ArrayList<Long>(), start, end);
    }

    private static Hits getAggregatedActivitySearchResultsExcludingUsers(List<Long> excludedUserIds, int start, int end) throws SearchException {
        SearchContext context = new SearchContext();
        context.setCompanyId(ColabConstants.COLAB_COMPANY_ID);
        BooleanQuery query = BooleanQueryFactoryUtil.create(context);
        //query.addRequiredTerm(Field.ENTRY_CLASS_NAME, SocialActivity.class.getName());

        BooleanQuery excludeQuery = BooleanQueryFactoryUtil.create(context);
        for (Long excludedUserId : excludedUserIds) {
            excludeQuery.addExactTerm("userId", excludedUserId);
        }

        try {
            query.add(excludeQuery, BooleanClauseOccurImpl.MUST_NOT);
        } catch (ParseException e) {
            _log.error(e);
        }

        Sort sort = SortFactoryUtil.create("createDate", Sort.FLOAT_TYPE, true);
        return SearchEngineUtil.search(SearchEngineUtil.getDefaultSearchEngineId(), context.getCompanyId(), query, sort, start, end);
    }

    private static List<Long> getAdministratorIds() throws SystemException, PortalException {
        Role r = RoleLocalServiceUtil.getRole(ColabConstants.COLAB_COMPANY_ID, ADMINISTRATOR_ROLE_NAME);

        List<Long> administratorsIds = new ArrayList<>();
        for (long userId : UserLocalServiceUtil.getRoleUserIds(r.getRoleId())) {
            administratorsIds.add(userId);
        }

        return administratorsIds;
    }

    public static HashMap<Long,Integer> getUsersActivityCount() throws SystemException, PortalException {

        List<User> liferayUsers = UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE);
        HashMap<Long, Integer> activityCounts = new HashMap<>();

        for(User u:liferayUsers) {
            Long userId = u.getUserId();
            activityCounts.put(userId, SocialActivityLocalServiceUtil.getUserActivitiesCount(userId));
        }

        return activityCounts;
    }
}