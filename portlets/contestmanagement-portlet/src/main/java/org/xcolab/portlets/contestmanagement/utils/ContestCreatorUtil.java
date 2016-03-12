package org.xcolab.portlets.contestmanagement.utils;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestSchedule;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestScheduleLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.enums.ContestPhaseTypeValue;
import org.xcolab.portlets.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.portlets.contestmanagement.wrappers.ContestScheduleWrapper;

/**
 * Helper class used to automatically create sets of new contests. Uses JSON data to create these contests.
 * <p/>
 * Created by Mente on 27/02/15.
 */
public final class ContestCreatorUtil {
    private static final Log _log = LogFactoryUtil.getLog(ContestCreatorUtil.class);

    public static final String SEED_CONTEST_SCHEDULE_NAME = "Seed Contest Schedule";
    //TODO: remove hard coded defaults
    public final static Long DEFAULT_CONTEST_SCHEDULE_ID = 601L;
    public final static Long DEFAULT_CONTEST_TEMPLATE_ID = 102L;

    private ContestCreatorUtil() { }

    public static Contest createNewContest(String contestShortName) throws SystemException, PortalException {
        Contest contest = ContestLocalServiceUtil.createNewContest(10144L, contestShortName);
        contest.setContestYear(DateTime.now().getYear());
        contest.setContestPrivate(true);
        contest.setShow_in_tile_view(true);
        contest.setShow_in_list_view(true);
        contest.setShow_in_outline_view(true);
        contest.setPlanTemplateId(DEFAULT_CONTEST_TEMPLATE_ID);
        contest.setContestScheduleId(DEFAULT_CONTEST_SCHEDULE_ID);
        contest.persist();
        ContestScheduleWrapper.createContestPhasesAccordingToContestScheduleAndRemoveExistingPhases(contest,
                DEFAULT_CONTEST_SCHEDULE_ID);
        return contest;
    }

    public static void insertSeedDataToContestScheduleTableIfNotAvailable() throws SystemException {
        if (ContestScheduleLocalServiceUtil.getContestSchedulesCount() == 0) {
            ContestSchedule contestSchedule = ContestScheduleLocalServiceUtil.
                    createContestSchedule(CounterLocalServiceUtil.increment(ContestSchedule.class.getName()));
            contestSchedule.setName(SEED_CONTEST_SCHEDULE_NAME);
            contestSchedule.persist();
            ContestScheduleLocalServiceUtil.updateContestSchedule(contestSchedule);

            createContestPhaseForSeedSchedule(new ContestPhaseBean(ContestPhaseTypeValue.PROPOSAL_CREATION,
                            DateTimeFormat.forPattern("MM/dd/yyyy hh:mm:ss a").parseDateTime("1/1/2016 12:00:00 AM").toDate(),
                            null, ContestPhasePromoteType.DEFAULT.getValue(), false),
                    contestSchedule.getId());
        }
    }

    private static void createContestPhaseForSeedSchedule(ContestPhaseBean contestPhaseBean, long contestScheduleId)
            throws SystemException {
        ContestPhase contestPhase = ContestPhaseLocalServiceUtil.
                createContestPhase(CounterLocalServiceUtil.increment(ContestPhase.class.getName()));

        contestPhase.setContestPK(0L);
        contestPhase.setContestScheduleId(contestScheduleId);
        contestPhase.setContestPhaseType(contestPhaseBean.getContestPhaseType());
        contestPhase.setPhaseStartDate(contestPhaseBean.getPhaseStartDate());

        if (contestPhaseBean.getPhaseEndDate() != null) {
            contestPhase.setPhaseEndDate(contestPhaseBean.getPhaseEndDate());
        }

        contestPhase.setContestPhaseAutopromote(contestPhaseBean.getContestPhaseAutopromote());
        contestPhase.setFellowScreeningActive(contestPhaseBean.getFellowScreeningActive());
        contestPhase.persist();
    }

    public static ContestSchedule createNewSchedule() throws SystemException {
        Long newContestScheduleId = CounterLocalServiceUtil.increment(ContestSchedule.class.getName());
        ContestSchedule newContestSchedule =
                ContestScheduleLocalServiceUtil.createContestSchedule(newContestScheduleId);
        newContestSchedule.setName("New contest schedule");
        newContestSchedule.persist();
        ContestScheduleLocalServiceUtil.updateContestSchedule(newContestSchedule);
        return newContestSchedule;
    }
}
