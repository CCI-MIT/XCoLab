package org.xcolab.view.pages.contestmanagement.utils.schedule;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IContestSchedule;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.tables.pojos.ContestSchedule;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.util.ContestScheduleChangeHelper;
import org.xcolab.commons.html.LabelValue;
import org.xcolab.util.enums.contest.ContestPhaseTypeValue;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.view.pages.contestmanagement.utils.ContestCreatorUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class ContestScheduleLifecycleUtil {

    private static final Logger _log = LoggerFactory.getLogger(ContestScheduleLifecycleUtil.class);
    private static final String NEW_CONTEST_SCHEDULE_NAME = "New contest schedule";

    private ContestScheduleLifecycleUtil() {

    }

    public static void deleteContestSchedule(Long scheduleId) {
        boolean isContestScheduleUsed = StaticContestContext.getContestClient()
                .isContestScheduleUsed(scheduleId);
        if (!isContestScheduleUsed) {
            removeContestSchedulePhases(scheduleId);
            removeContestPhasesOfContestsThatAreUsingSchedule(scheduleId);
            StaticContestContext.getContestClient().deleteContestSchedule(scheduleId);
        } else {
            throw new IllegalArgumentException(
                    "Contest schedule used by contests and can not be deleted!");
        }
    }

    private static void removeContestSchedulePhases(Long scheduleId) {

        List<ContestPhaseWrapper> contestSchedulePhases = StaticContestContext.getContestClient()
                .getTemplatePhasesForContestScheduleId(scheduleId);
        removeContestPhases(contestSchedulePhases);
    }

    private static void removeContestPhases(List<ContestPhaseWrapper> contestPhases) {
        for (ContestPhaseWrapper contestPhase : contestPhases) {
            removeContestPhase(contestPhase);
        }
    }

    private static void removeContestPhase(ContestPhaseWrapper contestPhase) {
        Long contestPhaseId = contestPhase.getId();
        List<IProposal2Phase> proposal2Phases = StaticProposalContext.getProposalPhaseClient()
                .getProposal2PhaseByContestPhaseId(contestPhaseId);
        if (!proposal2Phases.isEmpty()) {
            // TODO COLAB-2615: how should we treat these remaining entries?
            _log.warn("There are remaining proposal2phase entries for contestPhaseId: {}",
                    contestPhaseId);
        }
        StaticContestContext.getContestClient().deleteContestPhase(contestPhase.getId());
    }

    private static void removeContestPhasesOfContestsThatAreUsingSchedule(Long scheduleId) {

        List<ContestWrapper> contestsUsingSchedule = StaticContestContext.getContestClient()
                .getContestsByContestScheduleId(scheduleId);
        for (ContestWrapper contestUsingSchedule : contestsUsingSchedule) {
            List<ContestPhaseWrapper> contestSchedulePhases =
                    StaticContestContext.getContestClient()
                            .getPhasesForContestScheduleIdAndContest(scheduleId,
                                    contestUsingSchedule.getId());
            removeContestPhases(contestSchedulePhases);
        }

    }

    public static List<LabelValue> getScheduleTemplateSelectionItems(long existingScheduleId,
            boolean onlyShowSchedulesWithSamePhases) {
        if (!onlyShowSchedulesWithSamePhases) {
            return getAllScheduleTemplateSelectionItems();
        }

        List<ContestPhaseWrapper> currentSchedulePhases = getCurrentPhasesForSchedule(existingScheduleId);
        List<LabelValue> selectItems = new ArrayList<>();
        for (IContestSchedule candidateSchedule : StaticContestContext.getContestClient()
                .getAllContestSchedules()) {
            final List<ContestPhaseWrapper> newSchedulePhases =
                    getCurrentPhasesForSchedule(candidateSchedule.getId());
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
        return StaticContestContext.getContestClient().getAllContestSchedules().stream()
                .map(ScheduleLabel::new)
                .sorted()
                .collect(Collectors.toList());
    }

    private static List<ContestPhaseWrapper> getCurrentPhasesForSchedule(Long existingContestScheduleId) {
        return StaticContestContext.getContestClient()
                .getTemplatePhasesForContestScheduleId(existingContestScheduleId);
    }

    public static IContestSchedule createNewSchedule() {
        return createProposalCreationOnlySchedule(NEW_CONTEST_SCHEDULE_NAME);
    }

    public static IContestSchedule createProposalCreationOnlySchedule(String name) {
        IContestSchedule newContestSchedule = new ContestSchedule();
        newContestSchedule.setName(name);

        newContestSchedule = StaticContestContext.getContestClient()
                .createContestSchedule(newContestSchedule);

        ContestPhaseWrapper contestPhase = new ContestPhaseWrapper();
        contestPhase.setContestId(0L);
        contestPhase.setContestScheduleId(newContestSchedule.getId());
        contestPhase.setContestPhaseTypeId(ContestPhaseTypeValue.PROPOSAL_CREATION.getTypeId());
        contestPhase.setPhaseStartDate(new Timestamp(DateTime.now().getMillis()));
        contestPhase.setContestPhaseAutopromote(ContestPhasePromoteType.DEFAULT.getValue());
        StaticContestContext.getContestClient().createContestPhase(contestPhase);

        return newContestSchedule;
    }

    private static class ScheduleLabel extends LabelValue {

        public ScheduleLabel(IContestSchedule schedule) {
            super(schedule.getId(), schedule.getName());
        }
    }
}
