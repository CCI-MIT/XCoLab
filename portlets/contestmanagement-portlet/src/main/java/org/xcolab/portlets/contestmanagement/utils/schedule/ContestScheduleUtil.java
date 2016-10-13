package org.xcolab.portlets.contestmanagement.utils.schedule;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.util.GroupingUtil;
import org.xcolab.util.GroupingUtil.DuplicateElementException;
import org.xcolab.util.functions.Function;
import org.xcolab.wrappers.BaseContestWrapper;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class ContestScheduleUtil {

    public static void changeScheduleForContest(Contest contest, long contestScheduleId) {

        final ContestScheduleChangeHelper contestScheduleChangeHelper =
                new ContestScheduleChangeHelper(contest, contestScheduleId);

        if (isBlankContest(contest)) {
            contestScheduleChangeHelper.changeScheduleForBlankContest();
        } else {
            contestScheduleChangeHelper.changeScheduleForStartedContest();
        }
        contest.setContestScheduleId(contestScheduleId);
        ContestClientUtil.updateContest(contest);
    }

    public static boolean isBlankContest(Contest contest) {
        final BaseContestWrapper contestWrapper = new BaseContestWrapper(contest);
        final boolean contestHasProposals = contestWrapper.getTotalProposalsCount() > 0;
        return !contestHasProposals;
    }

    public static boolean canUpdateContestToSchedule(Contest contest, long newScheduleId) {
        return isBlankContest(contest)
                || existingPhaseTypesMatchSchedule(contest.getContestPK(), newScheduleId);
    }

    private static boolean existingPhaseTypesMatchSchedule(long contestId, long scheduleId) {
        final List<ContestPhase> existingPhases = ContestClientUtil
                .getAllContestPhases(contestId);
        final List<ContestPhase> schedulePhases = ContestClientUtil
                .getPhasesForContestScheduleId(scheduleId);

        //TODO: improve contest change algorithm to allow more detailed changes of started contest
        if (existingPhases.size() != schedulePhases.size()) {
            return false;
        }

        for (Iterator<ContestPhase> existingPhasesIterator = existingPhases.iterator(),
                schedulePhasesIterator = schedulePhases.iterator();
                existingPhasesIterator.hasNext() && schedulePhasesIterator.hasNext();) {

            final ContestPhase existingPhase = existingPhasesIterator.next();
            final ContestPhase schedulePhase = schedulePhasesIterator.next();
            if (!existingPhase.getContestPhaseType()
                    .equals(schedulePhase.getContestPhaseType())) {
                return false;
            }
        }
        return true;
    }

    public static Map<Long, ContestPhase> groupContestPhasesByPhaseTypeId(List<ContestPhase> phases) {
        try {
            return GroupingUtil.groupByUnique(phases, new Function<ContestPhase, Long>() {
                @Override
                public Long apply(ContestPhase contestPhase) {
                    return contestPhase.getContestPhaseType();
                }
            });
        } catch (DuplicateElementException e) {
            throw new IllegalArgumentException("There should only be one phase of each type per contest: " + e.getLocalizedMessage());
        }
    }
}
