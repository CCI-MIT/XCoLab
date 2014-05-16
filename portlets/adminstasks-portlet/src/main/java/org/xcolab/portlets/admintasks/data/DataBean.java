package org.xcolab.portlets.admintasks.data;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portlet.social.model.SocialActivity;

import java.util.*;

/**
 * @author pdeboer
 *         First created on 14/11/13 at 15:07
 */
public class DataBean {
    private List<SocialActivityWrapper> activities = new LinkedList<SocialActivityWrapper>(); //getActivities();
    //TODO enable if list of activities is asked. make better by using http://icefaces-showcase.icesoft.org/showcase.jsf;jsessionid=D91E1EEDF6C7564BD02C8A822271C0F9?grp=compatMenu&exp=outputResource

    public void interpretActivities() throws PortalException, SystemException {
        activities = fetchActivities();
    }

    public List<SocialActivityWrapper> fetchActivities() throws SystemException, PortalException {
        if (activities == null) {
            activities = new ArrayList<SocialActivityWrapper>();
            int lastDaysBetween = -1;
            Date now = new Date();
            int i = 0;

            for (SocialActivity activity : ActivityUtil.retrieveAllActivities(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
                if (SocialActivityWrapper.isEmpty(activity)) {
                    continue;
                }

                int curDaysBetween = DateUtil.getDaysBetween(new Date(activity.getCreateDate()), now, TimeZone.getDefault());
                activities.add(new SocialActivityWrapper(activity, curDaysBetween, lastDaysBetween < curDaysBetween, i % 2 == 1));
                lastDaysBetween = curDaysBetween;
                i++;
            }
        }
        return activities;
    }

    public List<SocialActivityWrapper> getActivities() {
        try {
            if(activities == null) activities = fetchActivities();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return activities;
    }

}
