package org.xcolab.portlets.contestmanagement.utils.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.ContestScheduleLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.ContestCreatorUtil;
import org.xcolab.util.exceptions.DatabaseAccessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class ContestScheduleLifecycleUtil {

    private final static Logger _log = LoggerFactory.getLogger(ContestScheduleLifecycleUtil.class);

    private ContestScheduleLifecycleUtil() {

    }

    public static void deleteContestSchedule(Long scheduleId) {
        try {
            boolean isContestScheduleUsed = ContestScheduleLocalServiceUtil
                    .isContestScheduleUsed(scheduleId);
            if (!isContestScheduleUsed) {
                removeContestSchedulePhases(scheduleId);
                removeContestPhasesOfContestsThatAreUsingSchedule(scheduleId);
                removeContestSchedule(scheduleId);
            } else {
                throw new IllegalArgumentException(
                        "Contest schedule used by contests and can not be deleted!");
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void removeContestSchedule(Long scheduleId) {
        try {
            ContestScheduleLocalServiceUtil.deleteContestSchedule(scheduleId);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            _log.debug("Attempt to delete already deleted contest schedule: " + scheduleId);
        }
    }

    private static void removeContestSchedulePhases(Long scheduleId) {

        List<ContestPhase> contestSchedulePhases = ContestClient
                .getPhasesForContestScheduleId(scheduleId);
        removeContestPhases(contestSchedulePhases);

    }

    public static void removeContestPhases(List<ContestPhase> contestPhases) {
        for (ContestPhase contestPhase : contestPhases) {
            removeContestPhase(contestPhase);
        }
    }

    public static void removeContestPhase(ContestPhase contestPhase) {
        try {
            Long contestPhaseId = contestPhase.getContestPhasePK();
            List<Proposal2Phase> proposal2Phases =
                    Proposal2PhaseLocalServiceUtil.getByContestPhaseId(contestPhaseId);
            if (!proposal2Phases.isEmpty()) {
                // TODO how should we treat these remaining entries?
                _log.warn("There are remaining proposal2phase entries for contestPhaseId:"
                        + contestPhaseId);
            }
            ContestClient.deleteContestPhase(contestPhase.getContestPhasePK());
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void removeContestPhasesOfContestsThatAreUsingSchedule(Long scheduleId) {

        List<Contest> contestsUsingSchedule = ContestClient
                .getContestsByContestScheduleId(scheduleId);
        for (Contest contestUsingSchedule : contestsUsingSchedule) {
            List<ContestPhase> contestSchedulePhases =
                    ContestClient
                            .getPhasesForContestScheduleIdAndContest(scheduleId,
                                    contestUsingSchedule.getContestPK());
            removeContestPhases(contestSchedulePhases);
        }

    }

    public static List<LabelValue> getAllScheduleTemplateSelectionItems() {
        ContestCreatorUtil.insertSeedDataToContestScheduleTableIfNotAvailable();
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestSchedule scheduleTemplate : ContestClient.getAllContestSchedules()) {
            selectItems.add(new LabelValue(scheduleTemplate.getId_(), scheduleTemplate.getName()));
        }
        Collections.sort(selectItems);

        return selectItems;
    }

    public static List<LabelValue> getScheduleTemplateSelectionItems(long existingScheduleId,
            boolean onlyShowSchedulesWithSamePhases) {
        List<LabelValue> selectItems = new ArrayList<>();
        if (!onlyShowSchedulesWithSamePhases) {
            selectItems = getAllScheduleTemplateSelectionItems();
        } else {

            List<ContestPhase> currentPhases = getCurrentPhasesForSchedule(existingScheduleId);
            for (ContestSchedule scheduleTemplate : ContestClient.getAllContestSchedules()) {
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

    private static List<ContestPhase> getCurrentPhasesForSchedule(Long existingContestScheduleId) {
        return ContestClient
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

    //TODO: if we want to support changing the schedule of an active contest,
    // we will have to support similar methods to change (rather than delete) proposal2phases and ratings
//    public static void deleteProposal2PhasesWithContestPhaseId(Long contestPhaseId) {
//        try {
//            List<Proposal2Phase> proposal2Phases = Proposal2PhaseLocalServiceUtil
//                    .getByContestPhaseId(contestPhaseId);
//
//            for (Proposal2Phase proposal2Phase : proposal2Phases) {
//                Proposal2PhaseLocalServiceUtil.deleteProposal2Phase(proposal2Phase);
//            }
//        } catch (SystemException e) {
//            throw new DatabaseAccessException(e);
//        }
//    }
//
//    public static void deleteProposalRatingsWithContestPhaseId(Long contestPhaseId) {
//        try {
//            List<ProposalRating> proposalRatings = new ArrayList<>(); // ProposalRatingLocalServiceUtil.getRatingsForContestPhase(contestPhaseId);
//
//            for (ProposalRating proposalRating : proposalRatings) {
//                ProposalRatingLocalServiceUtil.deleteProposalRating(proposalRating);
//            }
//        } catch (SystemException e) {
//            throw new DatabaseAccessException(e);
//        }
//    }

}
