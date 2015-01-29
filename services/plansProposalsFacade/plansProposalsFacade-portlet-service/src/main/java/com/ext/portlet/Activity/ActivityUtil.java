/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import java.util.*;

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
import com.liferay.portal.kernel.search.Field;
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
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

public class ActivityUtil {

    private static Log _log = LogFactoryUtil.getLog(ActivityUtil.class);

    public static final long AGGREGATION_TIME_WINDOW = 1000 * 60 * 60 * 1l; // 1h

    private static final long DEFAULT_COMPANY_ID = 10112L;

    private static final String ADMINISTRATOR_ROLE_NAME = "Administrator";

    public static List<SocialActivity> retrieveAllActivities(int pagestart, int next) throws SystemException {

        List<SocialActivity> activities = SocialActivityLocalServiceUtil.getSocialActivities(pagestart, next);

        return activities;
    }

    public static List<SocialActivity> retrieveWindowedActivities(int start, int end) throws SystemException, PortalException {
        return retrieveWindowedActivities(start, end, false);
    }

    public static List<SocialActivity> retrieveWindowedActivities(int start, int end, boolean showAdmin) throws SystemException, PortalException {
        Hits hits;
        if (showAdmin) {
            hits = getAllAggregatedActivitySearchResults(start, end);
        } else {
            List<Long> administratorsIds = getAdministratorIds();
            hits = getAggregatedActivitySearchResultsExcludingUsers(administratorsIds, start, end);
        }

        return retrieveAggregatedSocialActivities(hits);
    }

    public static List<SocialActivity> retrieveWindowedActivities(long userId, int start, int end) throws SystemException, PortalException {
        Hits hits = getAggregatedActivitySearchResults(userId, start, end);
        return retrieveAggregatedSocialActivities(hits);
    }

    private static List<SocialActivity> retrieveAggregatedSocialActivities(Hits hits) {
        List<SocialActivity> aggregatedSocialActivities = new ArrayList<>(hits.getLength());

        for (Document activityDoc : hits.getDocs()) {
            try {
                SocialActivity sa = SocialActivityLocalServiceUtil.getSocialActivity(GetterUtil.getLong(activityDoc.getField("activityId").getValue()));
                aggregatedSocialActivities.add(sa);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        return aggregatedSocialActivities;
    }

    public static List<SocialActivity> groupAllActivities() throws SystemException {
        return groupActivities(SocialActivityLocalServiceUtil.getOrganizationActivities(DEFAULT_COMPANY_ID, QueryUtil.ALL_POS, QueryUtil.ALL_POS));
    }

    public static List<SocialActivity> groupUserActivities(long userId) throws SystemException {
        return groupActivities(SocialActivityLocalServiceUtil.getUserActivities(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS));
    }

    public static List<SocialActivity> groupActivities(List<SocialActivity> activities) {
        //find all activities of same type
        Map<String, List<SocialActivity>> activitiesMap = new HashMap<>(10000);
        for (SocialActivity a : activities) {
            if (!activitiesMap.containsKey(getSocialActivityKey(a))) {
                activitiesMap.put(getSocialActivityKey(a), new LinkedList<SocialActivity>());
            }
            activitiesMap.get(getSocialActivityKey(a)).add(a);
        }

        List<SocialActivity> aggregatedActivities = clusterActivities(activitiesMap);


        return aggregatedActivities;
    }


    public static int getAllActivitiesCount() throws SystemException, PortalException {
        int searchResultCount = getAllAggregatedActivitySearchResults(QueryUtil.ALL_POS, QueryUtil.ALL_POS).getLength();
        if (searchResultCount == 0) {
            return groupAllActivities().size();
        }

        return searchResultCount;
    }

    public static int getActivitiesCount(long userId) throws SystemException, PortalException {

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

    public static Long[] getIdsFromExtraData(String extraData) {
        if (extraData == null || extraData.trim().length() == 0) {
            return new Long[]{};
        }
        String[] idStrs = extraData.split(",");
        Long[] ret = new Long[idStrs.length];

        for (int i = 0; i < idStrs.length; i++) {
            try {
                ret[i] = Long.parseLong(idStrs[i]);
            } catch (NumberFormatException e) {
                ret[i] = 0L;
            }
        }
        return ret;
    }

    private static List<SocialActivity> clusterActivities(Map<String, List<SocialActivity>> activitiesMap) {
        //cluster
        List<SocialActivity> aggregatedActivities = new LinkedList<>();
        Comparator<SocialActivity> sorter = new Comparator<SocialActivity>() {
            @Override
            public int compare(SocialActivity o1, SocialActivity o2) {
                return new Long(o1.getCreateDate()).compareTo(o2.getCreateDate());
            }
        };
        for (Collection<SocialActivity> activitiesMapValue : activitiesMap.values()) {
            List<SocialActivity> socialActivities = new ArrayList<>(activitiesMapValue); //convert to array for sorting
            Collections.sort(socialActivities, sorter);

            SocialActivity curMin = null;
            for (SocialActivity socialActivity : socialActivities) {
                if (curMin == null || socialActivity.getCreateDate() - curMin.getCreateDate() < AGGREGATION_TIME_WINDOW) {
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

    private static String getSocialActivityKey(SocialActivity sa) {
        return sa.getClassNameId() + "_" + sa.getClassPK() + "_" + sa.getType() + "_" + sa.getUserId();
    }

    private static Hits getAggregatedActivitySearchResults(long userId, int start, int end) throws SearchException {
        SearchContext context = new SearchContext();
        context.setCompanyId(DEFAULT_COMPANY_ID);
        BooleanQuery query = BooleanQueryFactoryUtil.create(context);
        query.addRequiredTerm(Field.ENTRY_CLASS_NAME, SocialActivity.class.getName());

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
        context.setCompanyId(DEFAULT_COMPANY_ID);
        BooleanQuery query = BooleanQueryFactoryUtil.create(context);
        query.addRequiredTerm(Field.ENTRY_CLASS_NAME, SocialActivity.class.getName());

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
        Role r = RoleLocalServiceUtil.getRole(DEFAULT_COMPANY_ID, ADMINISTRATOR_ROLE_NAME);

        List<Long> administratorsIds = new ArrayList<Long>();
        for (long userId : UserLocalServiceUtil.getRoleUserIds(r.getRoleId())) {
            administratorsIds.add(userId);
        }

        return administratorsIds;
    }

    public static HashMap<Long,Integer> getUsersActivityCount() throws SystemException, PortalException {

        List<User> liferayUsers = UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE);
        HashMap<Long, Integer> activityCounts = new HashMap<Long, Integer>();

        for(User u:liferayUsers)
        {
            Long userId = u.getUserId();
            activityCounts.put(userId, SocialActivityLocalServiceUtil.getUserActivitiesCount(userId));
        }

        return activityCounts;

}
}