/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.Activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.entity.utils.enums.ColabConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ActivityUtil {

    private static final Logger _log = LoggerFactory.getLogger(ActivityUtil.class);

    public static final long AGGREGATION_TIME_WINDOW = (long) 1000 * 60 * 60; // 1h

    public static List<ActivityEntry> retrieveAllActivities(int pagestart, int next) {
       return  ActivitiesClientUtil.getActivityEntries(pagestart, next, null, null);
    }

    public static List<ActivityEntry> groupUserActivities(long userId) {
        return groupActivities(ActivitiesClientUtil.getActivityEntries(0,1000,userId, null));
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


    public static int getAllActivitiesCount() {
        return ActivitiesClientUtil.countActivities(null, null);
    }

    public static int getActivitiesCount(long userId) throws SearchException {

        int searchResultCount = getAggregatedActivitySearchResults(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS).getLength();
        if (searchResultCount == 0) {
            return groupUserActivities(userId).size();
        }

        return searchResultCount;
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

        BooleanQuery subQuery = BooleanQueryFactoryUtil.create(context);
        subQuery.addExactTerm("userId", userId);

        try {
            query.add(subQuery, BooleanClauseOccur.MUST);
        } catch (ParseException e) {
            _log.error("", e);
        }

        Sort sort = SortFactoryUtil.create("createDate", Sort.FLOAT_TYPE, true);
        return SearchEngineUtil.search(SearchEngineUtil.getDefaultSearchEngineId(), context.getCompanyId(), query, sort, start, end);
    }

}