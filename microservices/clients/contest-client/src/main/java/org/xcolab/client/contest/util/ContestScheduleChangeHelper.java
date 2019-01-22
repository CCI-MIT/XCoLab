package org.xcolab.client.contest.util;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ContestScheduleChangeHelper {

    private final long contestId;
    private final List<SchedulePhase> existingPhases;
    private final List<SchedulePhase> newPhases;

    public ContestScheduleChangeHelper(long contestId, long newScheduleId) {
        this(contestId, StaticContestContext.getContestClient()
                .getTemplatePhasesForContestScheduleId(newScheduleId));
    }

    public ContestScheduleChangeHelper(long contestId, List<ContestPhaseWrapper> newPhases) {
        this.contestId = contestId;
        this.existingPhases = SchedulePhase.wrapList(StaticContestContext.getContestClient()
                .getAllContestPhases(contestId));
        this.newPhases = SchedulePhase.wrapList(newPhases);
    }

    public boolean isValidChange() {
        return doStartedPhasesMatch(existingPhases, newPhases)
                && isActivePhaseValid(existingPhases, newPhases);
    }

    public static boolean isValidChange(List<ContestPhaseWrapper> existingPhases,
            List<ContestPhaseWrapper> newPhases) {
        final List<SchedulePhase> existingSchedulePhases = SchedulePhase.wrapList(existingPhases);
        final List<SchedulePhase> newSchedulePhases = SchedulePhase.wrapList(newPhases);

        return doStartedPhasesMatch(existingSchedulePhases, newSchedulePhases)
                && isActivePhaseValid(existingSchedulePhases, newSchedulePhases);
    }

    private static boolean doStartedPhasesMatch(List<SchedulePhase> existingPhases,
            List<SchedulePhase> newPhases) {

        Iterator<SchedulePhase> oldPhasesIt = existingPhases.iterator();
        Iterator<SchedulePhase> newPhasesIt = newPhases.iterator();
        while (oldPhasesIt.hasNext() && newPhasesIt.hasNext()) {

            SchedulePhase oldPhase = oldPhasesIt.next();
            if (oldPhase.isAlreadyStarted()) {
                SchedulePhase newPhase = newPhasesIt.next();
                if (!newPhase.isAlreadyStarted()) {
                    return false;
                }
                boolean arePhaseTypesEqual = newPhase.getContestPhaseTypeId().longValue()
                                == oldPhase.getContestPhaseTypeId();
                if (!arePhaseTypesEqual) {
                    return false;
                }
            } else {
                break;
            }
        }
        return true;
    }

    private static boolean isActivePhaseValid(List<SchedulePhase> existingPhases,
            List<SchedulePhase> newPhases) {
        Optional<SchedulePhase> activeCurrentPhaseOpt = getActivePhase(existingPhases);
        Optional<SchedulePhase> activeNewPhaseOpt = getActivePhase(newPhases);
        if (activeCurrentPhaseOpt.isPresent() != activeNewPhaseOpt.isPresent()) {
            return false;
        }

        if (activeCurrentPhaseOpt.isPresent()) {
            final SchedulePhase activeCurrentPhase = activeCurrentPhaseOpt.get();
            final SchedulePhase activeNewPhase = activeNewPhaseOpt.get();

            final boolean typesMatch = activeCurrentPhase.getContestPhaseTypeId().longValue()
                    == activeNewPhase.getContestPhaseTypeId();
            final boolean positionsMatch = activeCurrentPhase.getPosition()
                    == activeNewPhase.getPosition();
            return typesMatch && positionsMatch;
        }
        return true;
    }

    private static Optional<SchedulePhase> getActivePhase(List<SchedulePhase> contestPhases) {
        return contestPhases.stream()
                .filter(ContestPhaseWrapper::isActive)
                .findFirst();
    }

    public void changeScheduleForStartedContest() {
        if (!isValidChange()) {
            throw new IllegalScheduleChangeException("Schedule change invalid");
        }
        removeFutureContestPhases();
        updateStartedContestPhases();
        createFutureContestPhases();
    }

    private void removeFutureContestPhases() {
        for (ContestPhaseWrapper contestPhase : existingPhases) {
            if (!contestPhase.isAlreadyStarted()) {
                StaticContestContext.getContestClient().deleteContestPhase(contestPhase.getId());
            }
        }
    }

    private void updateStartedContestPhases() {
        Iterator<SchedulePhase> oldPhasesIt = existingPhases.iterator();
        Iterator<SchedulePhase> newPhasesIt = newPhases.iterator();
        while (oldPhasesIt.hasNext() && newPhasesIt.hasNext()) {

            SchedulePhase oldPhase = oldPhasesIt.next();
            if (oldPhase.isAlreadyStarted()) {
                SchedulePhase newPhase = newPhasesIt.next();
                updateStartedContestPhaseWithTemplate(oldPhase, newPhase);
            }
        }
    }

    private void updateStartedContestPhaseWithTemplate(SchedulePhase contestPhase,
            SchedulePhase templatePhase) {

        final boolean isDifferentContestType = contestPhase.getContestPhaseTypeId().longValue()
                != templatePhase.getContestPhaseTypeId();
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
        contestPhase.setCreatedAt(new Timestamp(new Date().getTime()));
        contestPhase.setUpdatedAt(new Timestamp(new Date().getTime()));

        StaticContestContext.getContestClient().updateContestPhase(contestPhase);
    }

    private static boolean isContestPhaseAlreadyPromoted(ContestPhaseWrapper contestPhase) {
        return contestPhase.getContestPhaseAutopromote()
                .equals(ContestPhasePromoteType.PROMOTE_DONE.getValue());
    }

    private void createFutureContestPhases() {
        for (ContestPhaseWrapper schedulePhase : newPhases) {
            if (!schedulePhase.isAlreadyStarted()) {
                cloneContestPhaseWithContestId(schedulePhase, contestId);
            }
        }
    }

    private static void cloneContestPhaseWithContestId(
            ContestPhaseWrapper contestSchedulePhase, Long contestId) {

        ContestPhaseWrapper newContestPhase = ContestPhaseWrapper.clone(contestSchedulePhase);
        newContestPhase.setContestId(contestId);
        StaticContestContext.getContestClient().createContestPhase(newContestPhase);
    }

    public void changeScheduleForBlankContest() {
        removeExistingContestPhases();

        for (ContestPhaseWrapper schedulePhase : newPhases) {
            cloneContestPhaseWithContestId(schedulePhase, contestId);
        }
    }

    private void removeExistingContestPhases() {

        List<ContestPhaseWrapper> contestPhases = StaticContestContext.getContestClient()
                .getAllContestPhases(contestId);
        for (ContestPhaseWrapper contestPhase : contestPhases) {
            StaticContestContext.getContestClient().deleteContestPhase(contestPhase.getId());
        }
    }

    public static class IllegalScheduleChangeException extends UnsupportedOperationException {

        public IllegalScheduleChangeException(String message) {
            super(message);
        }
    }

    public static class PhaseTypeMismatchScheduleChangeException
            extends IllegalScheduleChangeException {

        public PhaseTypeMismatchScheduleChangeException() {
            super("Cannot change contest phase type of a started contest phase.");
        }
    }

    public static class PastEndDateScheduleChangeException
            extends IllegalScheduleChangeException {

        public PastEndDateScheduleChangeException() {
            super("Cannot change phase to or from end date that already passed.");
        }
    }

}
