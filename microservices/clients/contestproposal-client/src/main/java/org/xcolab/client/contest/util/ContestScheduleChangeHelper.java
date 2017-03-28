package org.xcolab.client.contest.util;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ContestScheduleChangeHelper {

    private final long contestId;
    private final List<ContestPhase> existingPhases;
    private final List<ContestPhase> schedulePhases;

    public ContestScheduleChangeHelper(long contestId, long newScheduleId) {
        this(contestId, ContestClientUtil.getPhasesForContestScheduleId(newScheduleId));
    }

    public ContestScheduleChangeHelper(long contestId, List<ContestPhase> schedulePhases) {
        this.contestId = contestId;
        this.existingPhases = ContestClientUtil.getAllContestPhases(contestId);
        this.schedulePhases = schedulePhases;
    }

    public boolean startedPhaseTypesMatchSchedule() {
        return doStartedPhasesMatch(existingPhases, schedulePhases);
    }

    public static boolean doStartedPhasesMatch(List<ContestPhase> currentSchedulePhases,
            List<ContestPhase> schedulePhases) {

        List<ContestPhase> oldPhases = new ArrayList<>(currentSchedulePhases);
        List<ContestPhase> newPhases = new ArrayList<>(schedulePhases);

        oldPhases.sort(Comparator.comparing(ContestPhase::getPhaseStartDate));
        newPhases.sort(Comparator.comparing(ContestPhase::getPhaseStartDate));

        Iterator<ContestPhase> oldPhasesIt = oldPhases.iterator();
        Iterator<ContestPhase> newPhasesIt = newPhases.iterator();
        while (oldPhasesIt.hasNext() && newPhasesIt.hasNext()) {

            ContestPhase oldPhase = oldPhasesIt.next();
            if (oldPhase.isAlreadyStarted()) {
                ContestPhase newPhase = newPhasesIt.next();
                if (!newPhase.isAlreadyStarted()) {
                    return false;
                }
                boolean arePhaseTypesEqual = newPhase.getContestPhaseType().longValue()
                                == oldPhase.getContestPhaseType();
                if (!arePhaseTypesEqual) {
                    return false;
                }
            } else {
                break;
            }
        }
        return true;
    }

    public void changeScheduleForStartedContest() {
        removeFutureContestPhases();
        updateStartedContestPhases();
        createFutureContestPhases();
    }

    private void removeFutureContestPhases() {
        for (ContestPhase contestPhase : existingPhases) {
            if (!contestPhase.isAlreadyStarted()) {
                ContestClientUtil.deleteContestPhase(contestPhase.getContestPhasePK());
            }
        }
    }

    private void updateStartedContestPhases() {
        Iterator<ContestPhase> oldPhasesIt = existingPhases.iterator();
        Iterator<ContestPhase> newPhasesIt = schedulePhases.iterator();
        while (oldPhasesIt.hasNext() && newPhasesIt.hasNext()) {

            ContestPhase oldPhase = oldPhasesIt.next();
            if (oldPhase.isAlreadyStarted()) {
                ContestPhase newPhase = newPhasesIt.next();
                updateStartedContestPhaseWithTemplate(oldPhase, newPhase);
            }
        }
    }

    private void updateStartedContestPhaseWithTemplate(ContestPhase contestPhase,
            ContestPhase templatePhase) {

        final boolean isDifferentContestType = contestPhase.getContestPhaseType().longValue()
                != templatePhase.getContestPhaseType();
        if (isDifferentContestType) {
            throw new PhaseTypeMismatchScheduleChangeException();
        }

        final boolean isEndDateEqual = Objects.equals(contestPhase.getPhaseEndDate(),
                templatePhase.getPhaseEndDate());
        if (!isEndDateEqual) {
            if (contestPhase.isEnded() || templatePhase.isEnded()) {
                throw new PastEndDateScheduleChangeException();
            }
            contestPhase.setPhaseEndDate(templatePhase.getPhaseEndDate());
            if (!isContestPhaseAlreadyPromoted(contestPhase)) {
                contestPhase.setContestPhaseAutopromote(templatePhase.getContestPhaseAutopromote());
            }
        }

        contestPhase.setContestScheduleId(templatePhase.getContestScheduleId());
        contestPhase.setFellowScreeningActive(templatePhase.getFellowScreeningActive());
        contestPhase.setContestPhaseDescriptionOverride(
                templatePhase.getContestPhaseDescriptionOverride());
        contestPhase.setCreated(new Timestamp(new Date().getTime()));
        contestPhase.setUpdated(new Timestamp(new Date().getTime()));

        ContestClientUtil.updateContestPhase(contestPhase);
    }

    private static boolean isContestPhaseAlreadyPromoted(ContestPhase contestPhase) {
        return contestPhase.getContestPhaseAutopromote()
                .equals(ContestPhasePromoteType.PROMOTE_DONE.getValue());
    }

    private void createFutureContestPhases() {
        for (ContestPhase schedulePhase : schedulePhases) {
            if (!schedulePhase.isAlreadyStarted()) {
                cloneContestPhaseWithContestId(schedulePhase, contestId);
            }
        }
    }

    private static void cloneContestPhaseWithContestId(
            ContestPhase contestSchedulePhase, Long contestId) {

        ContestPhase newContestPhase = ContestPhase.clone(contestSchedulePhase);
        newContestPhase.setContestPK(contestId);
        ContestClientUtil.createContestPhase(newContestPhase);
    }

    public void changeScheduleForBlankContest() {
        removeExistingContestPhases();

        for (ContestPhase schedulePhase : schedulePhases) {
            cloneContestPhaseWithContestId(schedulePhase, contestId);
        }
    }

    private void removeExistingContestPhases() {

        List<ContestPhase> contestPhases = ContestClientUtil.getAllContestPhases(contestId);
        for (ContestPhase contestPhase : contestPhases) {
            ContestClientUtil.deleteContestPhase(contestPhase.getContestPhasePK());
        }
    }

    public static class IllegalScheduleChangeException extends UnsupportedOperationException {

        public IllegalScheduleChangeException(String message) {
            super(message);
        }

        public IllegalScheduleChangeException(Contest contest, long scheduleId) {
            super("Can't edit schedule " + scheduleId + " because contest "
                    + contest.getContestPK() + " already has "
                    + (contest).getProposalsCount() + " proposals");
        }
    }

    public static class PhaseTypeMismatchScheduleChangeException extends IllegalScheduleChangeException {
        public PhaseTypeMismatchScheduleChangeException() {
            super("Cannot change contest phase type of a started contest phase.");
        }
    }

    public static class PastEndDateScheduleChangeException extends IllegalScheduleChangeException {
        public PastEndDateScheduleChangeException() {
            super("Cannot change phase to or from end date that already passed.");
        }
    }
}
