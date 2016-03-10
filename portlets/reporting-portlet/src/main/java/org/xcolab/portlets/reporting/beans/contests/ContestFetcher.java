package org.xcolab.portlets.reporting.beans.contests;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.text.SimpleDateFormat;
import java.util.*;

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
			if (contest.getCreated() != null && contest.getCreated().after(targetDate)
					&& contest.getContestPK() != 1300601L
					&& contest.getContestPK() != 1301001L
					) {
				targetContests.add(contest);
			}
		}
		return targetContests;
	}


	public static List<Contest> getContestsIn2015() throws Exception {
		Set<Long> targetContests = new HashSet<>();
		List<Long> targetContestPhaseTypes = Arrays.asList(1L, 2L, 4L, 5L, 201L, 401L);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		for (ContestPhase cp : ContestPhaseLocalServiceUtil.getContestPhases(0, Integer.MAX_VALUE)) {
			String phaseStartYear = (cp.getPhaseStartDate() != null) ? sdf.format(cp.getPhaseStartDate()) : "";
			if (cp.getPhaseStartDate() != null && phaseStartYear.equals("2015") && targetContestPhaseTypes.contains(cp.getContestPhaseType()))
				targetContests.add(cp.getContestPK());
		}

		List<Contest> ret = new LinkedList<>();
		for (Long targetContest : targetContests) {
			try {
				ret.add(ContestLocalServiceUtil.getContest(targetContest));
			} catch (Throwable e) {
				//e.printStackTrace();
			}
		}

		return ret;
	}

	public static List<ContestPhase> getContestPhases(List<Contest> contests) throws Exception {
		List<ContestPhase> ret = new LinkedList<>();
		for (Contest contest : contests) {
			List<ContestPhase> phasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
			ret.addAll(phasesForContest);
		}
		return ret;
	}
}
