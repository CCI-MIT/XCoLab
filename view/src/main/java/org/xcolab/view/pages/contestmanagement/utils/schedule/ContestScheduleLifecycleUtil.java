package org.xcolab.view.pages.contestmanagement.utils.schedule;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.util.ContestScheduleChangeHelper;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.view.pages.contestmanagement.entities.LabelValue;
import org.xcolab.view.pages.contestmanagement.utils.ContestCreatorUtil;
import org.xcolab.view.util.entity.enums.ContestPhaseTypeValue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ContestScheduleLifecycleUtil {

    private final static Logger _log = LoggerFactory.getLogger(ContestScheduleLifecycleUtil.class);
    private static final String NEW_CONTEST_SCHEDULE_NAME = "New contest schedule";

    private ContestScheduleLifecycleUtil() {

    }

    public static void deleteContestSchedule(Long scheduleId) {
        boolean isContestScheduleUsed = ContestClientUtil.isContestScheduleUsed(scheduleId);
        if (!isContestScheduleUsed) {
            removeContestSchedulePhases(scheduleId);
            removeContestPhasesOfContestsThatAreUsingSchedule(scheduleId);
            ContestClientUtil.deleteContestSchedule(scheduleId);
        } else {
            throw new IllegalArgumentException(
                    "Contest schedule used by contests and can not be deleted!");
        }
    }

    private static void removeContestSchedulePhases(Long scheduleId) {

        List<ContestPhase> contestSchedulePhases = ContestClientUtil
                .getPhasesForContestScheduleId(scheduleId);
        removeContestPhases(contestSchedulePhases);

    }

    private static void removeContestPhases(List<ContestPhase> contestPhases) {
        for (ContestPhase contestPhase : contestPhases) {
            removeContestPhase(contestPhase);
        }
    }

    private static void removeContestPhase(ContestPhase contestPhase) {
        Long contestPhaseId = contestPhase.getContestPhasePK();
        List<Proposal2Phase> proposal2Phases = ProposalPhaseClientUtil
                .getProposal2PhaseByContestPhaseId(contestPhaseId);
        if (!proposal2Phases.isEmpty()) {
            // TODO how should we treat these remaining entries?
            _log.warn("There are remaining proposal2phase entries for contestPhaseId: {}",
                    contestPhaseId);
        }
        ContestClientUtil.deleteContestPhase(contestPhase.getContestPhasePK());
    }

    private static void removeContestPhasesOfContestsThatAreUsingSchedule(Long scheduleId) {

        List<Contest> contestsUsingSchedule = ContestClientUtil
                .getContestsByContestScheduleId(scheduleId);
        for (Contest contestUsingSchedule : contestsUsingSchedule) {
            List<ContestPhase> contestSchedulePhases =
                    ContestClientUtil
                            .getPhasesForContestScheduleIdAndContest(scheduleId,
                                    contestUsingSchedule.getContestPK());
            removeContestPhases(contestSchedulePhases);
        }

    }

    public static List<LabelValue> getScheduleTemplateSelectionItems(long existingScheduleId,
            boolean onlyShowSchedulesWithSamePhases) {
        if (!onlyShowSchedulesWithSamePhases) {
            return getAllScheduleTemplateSelectionItems();
        }

        List<ContestPhase> currentSchedulePhases = getCurrentPhasesForSchedule(existingScheduleId);
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestSchedule candidateSchedule : ContestClientUtil.getAllContestSchedules()) {
            final List<ContestPhase> newSchedulePhases =
                    getCurrentPhasesForSchedule(candidateSchedule.getId_());
            if (ContestScheduleChangeHelper
                    .isValidChange(currentSchedulePhases, newSchedulePhases)) {
                selectItems.add(new ScheduleLabel(candidateSchedule));
            }
        }
        Collections.sort(selectItems);

        return selectItems;
    }

    public static List<LabelValue> getAllScheduleTemplateSelectionItems() {
        ContestCreatorUtil.insertSeedDataToContestScheduleTableIfNotAvailable();
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestSchedule scheduleTemplate : ContestClientUtil.getAllContestSchedules()) {
            selectItems.add(new ScheduleLabel(scheduleTemplate));
        }
        Collections.sort(selectItems);

        return selectItems;
    }

    private static List<ContestPhase> getCurrentPhasesForSchedule(Long existingContestScheduleId) {
        return ContestClientUtil
                .getTemplatePhasesForContestScheduleId(existingContestScheduleId);
    }

    public static ContestSchedule createNewSchedule() {
        return createProposalCreationOnlySchedule(NEW_CONTEST_SCHEDULE_NAME);
    }

    public static ContestSchedule createProposalCreationOnlySchedule(String name) {
        ContestSchedule newContestSchedule = new ContestSchedule();
        newContestSchedule.setName(name);

        newContestSchedule = ContestClientUtil.createContestSchedule(newContestSchedule);

        ContestPhase contestPhase = new ContestPhase();
        contestPhase.setContestPK(0L);
        contestPhase.setContestScheduleId(newContestSchedule.getId_());
        contestPhase.setContestPhaseType(ContestPhaseTypeValue.PROPOSAL_CREATION.getTypeId());
        contestPhase.setPhaseStartDate(new Timestamp(DateTime.now().getMillis()));
        contestPhase.setContestPhaseAutopromote(ContestPhasePromoteType.DEFAULT.getValue());
        contestPhase.setFellowScreeningActive(false);
        ContestClientUtil.createContestPhase(contestPhase);

        return newContestSchedule;
    }

    private static class ScheduleLabel extends LabelValue {

        public ScheduleLabel(ContestSchedule schedule) {
            super(schedule.getId_(), schedule.getName());
        }
    }
}
