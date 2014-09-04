package org.xcolab.portlets.reporting.beans.contests;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pdeboer
 *         First created on 25/07/14 at 11:17
 */
public class ContestFetcher {
    public static List<Contest> getContestsIn2013() throws SystemException {
        List<Contest> targetContests = new LinkedList<>();
        for (Contest contest : ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            if (contest.getContestShortName().endsWith("2013")) {
                targetContests.add(contest);
            }
        }
        return targetContests;
    }

    public static List<Contest> getContestsIn2014() throws Exception {
        List<Contest> targetContests = new LinkedList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date targetDate = sdf.parse("2014-02-20");
        for (Contest contest : ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            if(contest.getContestActive()
                    && contest.getFlag() == 0 //right now all closed contests have flag=0
                    && contest.getCreated().after(targetDate)
                    ) {
                targetContests.add(contest);
            }
        }
        return targetContests;
    }

    public static List<ContestPhase> getContestPhasesIn2014() throws Exception {
        List<Contest> contestsIn2014 = getContestsIn2014();
        List<ContestPhase> ret = new LinkedList<>();
        for (Contest contest : contestsIn2014) {
            List<ContestPhase> phasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
            ret.addAll(phasesForContest);
        }
        return ret;
    }
}
