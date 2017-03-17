package org.xcolab.view.pages.contestmanagement.utils.schedule;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.view.util.entity.enums.ContestPhaseTypeValue;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.view.pages.contestmanagement.entities.LabelValue;
import org.xcolab.view.pages.contestmanagement.utils.ContestCreatorUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    public static void removeContestPhases(List<ContestPhase> contestPhases) {
        for (ContestPhase contestPhase : contestPhases) {
            removeContestPhase(contestPhase);
        }
    }

    public static void removeContestPhase(ContestPhase contestPhase) {
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
        List<LabelValue> selectItems = new ArrayList<>();
        if (!onlyShowSchedulesWithSamePhases) {
            selectItems = getAllScheduleTemplateSelectionItems();
        } else {

            List<ContestPhase> currentPhases = getCurrentPhasesForSchedule(existingScheduleId);
            for (ContestSchedule scheduleTemplate : ContestClientUtil.getAllContestSchedules()) {
                if (arePhasesCompatibleUntilCurrentPhase(currentPhases,
                        scheduleTemplate.getId_())) {
                    selectItems.add(new LabelValue(scheduleTemplate.getId_(),
                            scheduleTemplate.getName()));
                }
            }
            Collections.sort(selectItems);

        }
        return selectItems;
    }

    public static List<LabelValue> getAllScheduleTemplateSelectionItems() {
        ContestCreatorUtil.insertSeedDataToContestScheduleTableIfNotAvailable();
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestSchedule scheduleTemplate : ContestClientUtil.getAllContestSchedules()) {
            selectItems.add(new LabelValue(scheduleTemplate.getId_(), scheduleTemplate.getName()));
        }
        Collections.sort(selectItems);

        return selectItems;
    }

    private static List<ContestPhase> getCurrentPhasesForSchedule(Long existingContestScheduleId) {
        return ContestClientUtil
                .getTemplatePhasesForContestScheduleId(existingContestScheduleId);

    }

    private static boolean arePhasesCompatibleUntilCurrentPhase(
            List<ContestPhase> currentContestSchedulePhases, Long selectableScheduleId) {
        List<ContestPhase> selectablePhases = getCurrentPhasesForSchedule(selectableScheduleId);
        Date now = new Date();
        for (int i = 0; i < currentContestSchedulePhases.size(); i++) {
            ContestPhase phase = currentContestSchedulePhases.get(i);
            if (phase.getPhaseStartDate() != null) {
                if (!(phase.getPhaseStartDate().after(now))) {
                    boolean arePhaseTypesEqual = (selectablePhases.size() > i &&
                            Objects.equals(selectablePhases.get(i).getContestPhaseType(),
                                    phase.getContestPhaseType()));
                    if (!arePhaseTypesEqual) {
                        return false;
                    }
                } else {
                    break;
                }
            }
        }
        return true;
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

}
