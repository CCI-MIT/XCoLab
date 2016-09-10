package org.xcolab.portlets.contestmanagement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ContestScheduleLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.enums.ColabConstants;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.portlets.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.util.exceptions.DatabaseAccessException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class ContestScheduleLifecycleUtil {

    private final static Logger _log = LoggerFactory.getLogger(ContestScheduleLifecycleUtil.class);

    private ContestScheduleLifecycleUtil() {

    }

    public static void removeContestSchedule(Long scheduleId) {
        try {
            ContestScheduleLocalServiceUtil.deleteContestSchedule(scheduleId);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            _log.debug("Attempt to delete already deleted contest schedule: " + scheduleId);
        }
    }

    public static void removeContestPhases(List<ContestPhase> contestPhases) {
        try {
            for (ContestPhase contestPhase : contestPhases) {
                Long contestPhaseId = contestPhase.getContestPhasePK();
                List<Proposal2Phase> proposal2Phases =
                        Proposal2PhaseLocalServiceUtil.getByContestPhaseId(contestPhaseId);
                if (!proposal2Phases.isEmpty()) {
                    // TODO how should we treat these remaining entries?
                    _log.warn("There are remaining proposal2phase entries for contestPhaseId:"
                            + contestPhaseId);
                }
                ContestClient.deleteContestPhase(contestPhase.getContestPhasePK());

            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public static void deleteContestSchedule(Long scheduleId) {
        try {
            boolean isContestScheduleUsed = ContestScheduleLocalServiceUtil
                    .isContestScheduleUsed(scheduleId);
            if (!isContestScheduleUsed) {
                removeContestSchedulePhases(scheduleId);
                removeContestPhaseOfContestThatAreUsingSchedule(scheduleId);
                removeContestSchedule(scheduleId);
            } else {
                throw new IllegalArgumentException(
                        "Contest schedule used by contests and can not be deleted!");
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void removeContestSchedulePhases(Long scheduleId) {

        List<ContestPhase> contestSchedulePhases = ContestClient
                .getPhasesForContestScheduleId(scheduleId);
        removeContestPhases(contestSchedulePhases);

    }

    private static void removeContestPhaseOfContestThatAreUsingSchedule(Long scheduleId) {

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

    public static void duplicateContestPhasesFromExistingScheduleToNewSchedule(
            Long existingContestScheduleId, Long newContestScheduleId) {

        List<ContestPhase> contestPhasesForExistingSchedule = ContestClient
                .getPhasesForContestScheduleId(existingContestScheduleId);
        for (ContestPhase contestPhase : contestPhasesForExistingSchedule) {
            contestPhase.setContestScheduleId(newContestScheduleId);
            createContestPhaseFromExistingContestPhaseWithContestId(contestPhase,
                    ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
        }

    }

    public static void changeContestScheduleForContest(Contest contest, Long contestScheduleId) {
        List<ContestPhase> contestPhasesForContestSchedule = ContestClient
                .getAllContestPhases(contestScheduleId);
        updateContestPhasesWithProposalsToNewScheduleId(contest, contestScheduleId);
        createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(contest,
                contestPhasesForContestSchedule, contestScheduleId);
        updateContestPhasesOfContestAccordingToContestSchedule(contest, contestScheduleId);
    }

    private static void updateContestPhasesWithProposalsToNewScheduleId(Contest contest,
            Long newScheduleId) {

        List<ContestPhase> existingContestPhasesForContest = ContestClient
                .getAllContestPhases(contest.getContestPK());
        Date now = new Date();
        for (ContestPhase phase : existingContestPhasesForContest) {
            if (!(phase.getPhaseStartDate().after(now))) {
                phase.setContestScheduleId(newScheduleId);
                ContestClient.updateContestPhase(phase);

            }
        }

    }

    private static void createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(
            Contest contest,
            List<ContestPhase> contestPhasesForContestSchedule, Long contestScheduleId) {

        List<ContestPhase> existingContestPhasesForContest = ContestClient
                .getAllContestPhases(contest.getContestPK());
        for (ContestPhase contestPhaseOfContestSchedule : contestPhasesForContestSchedule) {
            Long contestPhaseType = contestPhaseOfContestSchedule.getContestPhaseType();
            if (!isContestPhaseTypeInContestPhaseList(existingContestPhasesForContest,
                    contestPhaseType, contestScheduleId)) {
                createContestPhaseFromExistingContestPhaseWithContestId(
                        contestPhaseOfContestSchedule, contest.getContestPK());
            }
        }

    }

    public static boolean isContestPhaseTypeInContestPhaseList(List<ContestPhase> contestPhases,
            Long contestPhaseTyp, Long contestScheduleId) {
        for (ContestPhase existingContestPhaseForContest : contestPhases) {
            if (Objects
                    .equals(existingContestPhaseForContest.getContestPhaseType(), contestPhaseTyp)
                    &&
                    Objects.equals(existingContestPhaseForContest.getContestScheduleId(),
                            contestScheduleId)) {
                return true;
            }
        }
        return false;
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
                .getPhasesForContestScheduleIdAndContest(existingContestScheduleId, 0L);

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

    private static void updateContestPhasesOfContestAccordingToContestSchedule(Contest contest,
            Long scheduleTemplateId) {

        List<ContestPhase> existingPhasesOfContest = ContestClient
                .getAllContestPhases(contest.getContestPK());
        Map<Long, Long> phaseTypeIdToPhaseIdMapForExistingContestPhases = new LinkedHashMap<>();
        populatePhaseTypeIdToPhaseIdMap(contest,
                phaseTypeIdToPhaseIdMapForExistingContestPhases);

        List<ContestPhase> phasesOfContestSchedule = ContestClient.
                getPhasesForContestScheduleIdAndContest(scheduleTemplateId,
                        ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
        List<ContestPhase> newPhasesForContest = new ArrayList<>();
        for (ContestPhase schedulePhase : phasesOfContestSchedule) {

            Long phaseTypeIdOfSchedulePhase = schedulePhase.getContestPhaseType();
            Long phaseIdOfExistingPhaseWithSchedulePhaseType = phaseTypeIdToPhaseIdMapForExistingContestPhases
                    .get(phaseTypeIdOfSchedulePhase);

            if (phaseIdOfExistingPhaseWithSchedulePhaseType == null) {
                removeContestPhases(newPhasesForContest);
                throw new IllegalStateException(
                        "Can't map new contestPhaseTypeId: " + phaseTypeIdOfSchedulePhase
                                + " to any of the existing contestPhaseTypeIds.");
            }

            ContestPhase existingPhase = ContestClient
                    .getContestPhase(phaseIdOfExistingPhaseWithSchedulePhaseType);

            updateExistingContestPhaseAccordingToContestSchedulePhase(existingPhase,
                    schedulePhase);
            existingPhasesOfContest.remove(existingPhase);
        }

        removeRemainingUnusedContestPhases(existingPhasesOfContest);

    }

    public static void removeRemainingUnusedContestPhases(List<ContestPhase> contestPhases) {
        removeContestPhases(contestPhases);
    }

    public static void updateExistingContestPhaseAccordingToContestSchedulePhase(
            ContestPhase existingContestPhase, ContestPhase contestSchedulePhase) {
        existingContestPhase.setContestPhaseType(contestSchedulePhase.getContestPhaseType());
        existingContestPhase.setContestScheduleId(contestSchedulePhase.getContestScheduleId());
        existingContestPhase.setContestPhaseDescriptionOverride(
                contestSchedulePhase.getContestPhaseDescriptionOverride());
        existingContestPhase.setUpdated(new Timestamp(new Date().getTime()));
        existingContestPhase.setPhaseStartDate(contestSchedulePhase.getPhaseStartDate());
        existingContestPhase.setPhaseEndDate(contestSchedulePhase.getPhaseEndDate());
        if (!isContestPhaseAlreadyPromoted(existingContestPhase)) {
            existingContestPhase
                    .setContestPhaseAutopromote(contestSchedulePhase.getContestPhaseAutopromote());
        }
        existingContestPhase
                .setFellowScreeningActive(contestSchedulePhase.getFellowScreeningActive());

        ContestClient.updateContestPhase(existingContestPhase);

    }

    private static boolean isContestPhaseAlreadyPromoted(ContestPhase contestPhase) {
        return contestPhase.getContestPhaseAutopromote()
                .equals(ContestPhasePromoteType.PROMOTE_DONE.getValue());
    }

    public static void deleteProposal2PhasesWithContestPhaseId(Long contestPhaseId) {
        try {
            List<Proposal2Phase> proposal2Phases = Proposal2PhaseLocalServiceUtil
                    .getByContestPhaseId(contestPhaseId);

            for (Proposal2Phase proposal2Phase : proposal2Phases) {
                Proposal2PhaseLocalServiceUtil.deleteProposal2Phase(proposal2Phase);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public static void deleteProposalRatingsWithContestPhaseId(Long contestPhaseId) {
        try {
            List<ProposalRating> proposalRatings = new ArrayList<>(); // ProposalRatingLocalServiceUtil.getRatingsForContestPhase(contestPhaseId);

            for (ProposalRating proposalRating : proposalRatings) {
                ProposalRatingLocalServiceUtil.deleteProposalRating(proposalRating);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public static void createContestPhasesAccordingToContestScheduleAndRemoveExistingPhases(
            Contest contest, Long newScheduleTemplateId) {
        removeExistingContestPhases(contest);

        List<ContestPhase> contestSchedulePhases = ContestClient
                .getPhasesForContestScheduleId(newScheduleTemplateId);
        for (ContestPhase contestSchedulePhase : contestSchedulePhases) {
            createContestPhaseFromExistingContestPhaseWithContestId(contestSchedulePhase,
                    contest.getContestPK());
        }

    }

    private static Boolean isUpdateSchedulePhaseType(ContestPhaseBean contestSchedulePhase) {
        return !(contestSchedulePhase.getContestPhaseTypeOld().equals(0L) &&
                contestSchedulePhase.getContestPhaseTypeOld()
                        .equals(contestSchedulePhase.getContestPhaseType()));
    }

    public static void createContestPhaseFromExistingContestPhaseWithContestId(
            ContestPhaseBean contestSchedulePhase, Long contestId) {

        ContestPhase contestPhase = contestSchedulePhase.getContestPhase();
        if (isUpdateSchedulePhaseType(contestSchedulePhase)) {
            contestPhase.setContestPhaseType(contestSchedulePhase.getContestPhaseTypeOld());
        }
        ContestPhase newContestPhase = ContestPhase.createFromContestPhase(contestPhase);

        newContestPhase.setContestPK(contestId);
        ContestClient.createContestPhase(newContestPhase);

    }

    private static void createContestPhaseFromExistingContestPhaseWithContestId(
            ContestPhase contestSchedulePhase, Long contestId) {

        ContestPhase newContestPhase = ContestPhase
                .createFromContestPhase(contestSchedulePhase);
        newContestPhase.setContestPK(contestId);
        ContestClient.createContestPhase(newContestPhase);

    }

    public static void populatePhaseTypeIdToPhaseIdMap(Contest contest,
            Map<Long, Long> contestPhaseTypeIdPhaseIdMap) {

        List<ContestPhase> contestPhases = ContestClient
                .getAllContestPhases(contest.getContestPK());
        for (ContestPhase contestPhase : contestPhases) {
            contestPhaseTypeIdPhaseIdMap
                    .put(contestPhase.getContestPhaseType(), contestPhase.getContestPhasePK());
        }
    }

    private static void removeExistingContestPhases(Contest contest) {

        List<ContestPhase> contestPhases = ContestClient
                .getAllContestPhases(contest.getContestPK());
        for (ContestPhase contestPhase : contestPhases) {
            ContestClient.deleteContestPhase(contestPhase.getContestPhasePK());

        }
    }
}
