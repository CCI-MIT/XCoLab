package org.xcolab.portlets.contestmanagement.wrappers;


import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ContestScheduleLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.enums.ColabConstants;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.portlets.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.ContestCreatorUtil;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.wrappers.BaseContestWrapper;

import java.sql.Timestamp;
import java.util.*;

public class ContestScheduleWrapper {

    private final static Log _log = LogFactoryUtil.getLog(ContestScheduleWrapper.class);

    private List<ContestPhaseBean> schedulePhases;
    private ContestSchedule contestSchedule;
    private List<BaseContestWrapper> contestsUsingSelectedSchedule;
    private Boolean createNew = false;

    public ContestScheduleWrapper() {
        schedulePhases = new ArrayList<>();
        contestsUsingSelectedSchedule = new ArrayList<>();
    }

    public ContestScheduleWrapper(Long scheduleId) {
        initContestSchedule(scheduleId);
        initContestPhases(scheduleId);
        initContestsUsingSelectedSchedule(scheduleId);
    }

    private void initContestPhases(Long scheduleId) {
            schedulePhases = new ArrayList<>();
            List<ContestPhase> contestPhases =
                    ContestClient.getPhasesForContestScheduleIdAndContest(scheduleId,
                            ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
            for (ContestPhase contestPhase : contestPhases) {
                schedulePhases.add(new ContestPhaseBean(contestPhase));
            }
            addDummySchedulePhase();
    }

    private void initContestSchedule(Long scheduleId) {

            if (scheduleId != null) {
                contestSchedule = ContestClient.getContestSchedule(scheduleId);
            } else {
                List<ContestSchedule> contestScheduleList = ContestClient.getAllContestSchedules();
                contestSchedule = contestScheduleList.get(0);
            }

    }

    private void addDummySchedulePhase() {


            ContestPhase dummyContestPhase = new ContestPhase();
            ContestPhaseBean dummyContestPhaseBean = new ContestPhaseBean(dummyContestPhase);
            dummyContestPhaseBean.setContestPK(ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
            dummyContestPhaseBean.setContestScheduleId(getScheduleId());
            dummyContestPhaseBean.setContestPhasePK(ContestPhaseBean.CREATE_PHASE_CONTEST_PK);
            dummyContestPhaseBean.setContestSchedulePK(ContestPhaseBean.CREATE_PHASE_CONTEST_PK);
            dummyContestPhaseBean
                    .setContestPhaseAutopromote(ContestPhasePromoteType.DEFAULT.getValue());
            dummyContestPhase = ContestClient
                    .createContestPhase(dummyContestPhase);
            schedulePhases.add(dummyContestPhaseBean);
            ContestClient.deleteContestPhase(dummyContestPhase.getContestPhasePK());

    }

    private void initContestsUsingSelectedSchedule(Long scheduleId) {
            List<Contest> contestsUsingSelectedScheduleList = ContestClient
                    .getContestsByContestScheduleId(scheduleId);
            contestsUsingSelectedSchedule = new ArrayList<>();
            for (Contest contest : contestsUsingSelectedScheduleList) {
                contestsUsingSelectedSchedule.add(new BaseContestWrapper(contest));
            }
    }

    public List<BaseContestWrapper> getContestsUsingSelectedSchedule() {
        return contestsUsingSelectedSchedule;
    }

    public Long getScheduleId() {
        return contestSchedule.getId_();
    }

    public String getScheduleName() {
        return contestSchedule.getName();
    }

    public void setScheduleId(Long contestScheduleId) {
        initContestSchedule(contestScheduleId);
        initContestsUsingSelectedSchedule(contestScheduleId);
    }

    public void setScheduleName(String name) {
        this.contestSchedule.setName(name);
    }

    public List<ContestPhaseBean> getSchedulePhases() {
        return schedulePhases;
    }

    public void setSchedulePhases(List<ContestPhaseBean> schedulePhases) {
        this.schedulePhases = schedulePhases;
    }

    public Boolean getCreateNew() {
        return createNew;
    }

    public void setCreateNew(Boolean createNew) {
        this.createNew = createNew;
    }

    public void persist() {
        removeEmptyContestPhases();
        if (createNew) {
            createNewScheduleFromExistingSchedule();
        } else {
            persistUpdatedSchedule();
        }
    }

    private void removeEmptyContestPhases() {
        List<ContestPhaseBean> emptyContestPhases = new ArrayList<>();
        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            boolean contestPhaseIsEmpty = (contestPhaseBean.getContestPhasePK() == null);
            if (contestPhaseIsEmpty) {
                emptyContestPhases.add(contestPhaseBean);
            }
        }
        for (ContestPhaseBean emptyContestPhaseBean : emptyContestPhases) {
            schedulePhases.remove(emptyContestPhaseBean);
        }
    }

    private void createNewScheduleFromExistingSchedule() {
            Long existingContestScheduleId = contestSchedule.getId_();

            ContestSchedule newContestSchedule = new ContestSchedule();
            newContestSchedule.setBaseScheduleId(contestSchedule.getBaseScheduleId());
            newContestSchedule.setDescription(contestSchedule.getDescription());
            newContestSchedule.setName(contestSchedule.getName());
            newContestSchedule.setStatus(contestSchedule.getStatus());
            newContestSchedule = ContestClient.createContestSchedule(newContestSchedule);

            contestSchedule.setId_(existingContestScheduleId);
            duplicateContestPhasesFromExistingScheduleToNewSchedule(existingContestScheduleId,
                    newContestSchedule.getId_());
            contestSchedule = newContestSchedule;
    }

    private static void duplicateContestPhasesFromExistingScheduleToNewSchedule(Long existingContestScheduleId, Long newContestScheduleId) {

            List<ContestPhase> contestPhasesForExistingSchedule = ContestClient
                    .getPhasesForContestScheduleId(existingContestScheduleId);
            for (ContestPhase contestPhase : contestPhasesForExistingSchedule) {
                contestPhase.setContestScheduleId(newContestScheduleId);
                createContestPhaseFromExistingContestPhaseWithContestId(contestPhase,
                        ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
            }

    }

    public static void changeContestScheduleForContest(Contest contest, Long contestScheduleId) {
            List<ContestPhase> contestPhasesForContestSchedule = ContestClient.getAllContestPhases(contestScheduleId);
            updateContestPhasesWithProposalsToNewScheduleId(contest, contestScheduleId);
            createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(contest,
                    contestPhasesForContestSchedule, contestScheduleId);
            updateContestPhasesOfContestAccordingToContestSchedule(contest, contestScheduleId);
    }

    private void persistUpdatedSchedule() {

            createNewSchedulesPhases();
            makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(contestSchedule.getId_());
            updateContestsUsingSchedule(contestSchedule.getId_());
            ContestClient.updateContestSchedule(contestSchedule);

            persistChangesToSchedulePhases();

    }

    private void createNewSchedulesPhases() {
        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            Boolean isNewSchedulePhase = contestPhaseBean.getContestPhaseTypeOld().equals(0L);
            if (isNewSchedulePhase) {
                contestPhaseBean.persist();
            }
        }
    }

    private void persistChangesToSchedulePhases() {
        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            contestPhaseBean.persist();
        }
    }
    private static void updateContestPhasesWithProposalsToNewScheduleId(Contest contest, Long newScheduleId) {

            List<ContestPhase> existingContestPhasesForContest = ContestClient.getAllContestPhases(contest.getContestPK());
            Date now = new Date();
            for (ContestPhase phase : existingContestPhasesForContest) {
                if (!(phase.getPhaseStartDate().after(now))) {
                    phase.setContestScheduleId(newScheduleId);
                    ContestClient.updateContestPhase(phase);

                }
            }

    }
    private void updateContestsUsingSchedule(Long contestScheduleId) {

            List<Contest> contestsUsingScheduleId = ContestClient
                    .getContestsByContestScheduleId(contestScheduleId);
            for (Contest contest : contestsUsingScheduleId) {
                updateContestPhasesOfContestAccordingToUpdatedContestSchedule(contest);
            }

    }

    private void makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(Long contestScheduleId) {

            List<Contest> contestsUsingScheduleId = ContestClient
                    .getContestsByContestScheduleId(contestScheduleId);
            for (Contest contest : contestsUsingScheduleId) {
                createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(contest,
                        contestScheduleId);
            }

    }

    private static void createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(Contest contest,
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

    private void createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(Contest contest, Long contestScheduleId) {

            List<ContestPhase> existingContestPhasesForContest = ContestClient
                    .getAllContestPhases(contest.getContestPK());
            for (ContestPhaseBean contestPhaseOfContestSchedule : schedulePhases) {
                Long contestPhaseType = contestPhaseOfContestSchedule.getContestPhaseType();
                if (!isContestPhaseTypeInContestPhaseList(existingContestPhasesForContest,
                        contestPhaseType, contestScheduleId)) {
                    createContestPhaseFromExistingContestPhaseWithContestId(
                            contestPhaseOfContestSchedule, contest.getContestPK());
                }
            }

    }

    private static boolean isContestPhaseTypeInContestPhaseList(List<ContestPhase> contestPhases, Long contestPhaseTyp, Long contestScheduleId) {
        for (ContestPhase existingContestPhaseForContest : contestPhases) {
            if (existingContestPhaseForContest.getContestPhaseType() == contestPhaseTyp &&
                    existingContestPhaseForContest.getContestScheduleId() == contestScheduleId) {
                return true;
            }
        }
        return false;
    }

    public static List<LabelValue> getAllScheduleTemplateSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();

            ContestCreatorUtil.insertSeedDataToContestScheduleTableIfNotAvailable();
            for (ContestSchedule scheduleTemplate : ContestClient.getAllContestSchedules()) {
                selectItems.add(new LabelValue(scheduleTemplate.getId_(), scheduleTemplate.getName()));
            }
            Collections.sort(selectItems);

        return selectItems;
    }

    public static List<LabelValue> getScheduleTemplateSelectionItems(long existingScheduleId, boolean onlyShowSchedulesWithSamePhases) {
        List<LabelValue> selectItems = new ArrayList<>();
        if (!onlyShowSchedulesWithSamePhases) {
            selectItems = getAllScheduleTemplateSelectionItems();
        } else {

                List<ContestPhase> currentPhases = getCurrentPhasesForSchedule(existingScheduleId);
                for (ContestSchedule scheduleTemplate : ContestClient.getAllContestSchedules()) {
                    if (arePhasesCompatibleUntilCurrentPhase(currentPhases, scheduleTemplate.getId_())) {
                        selectItems.add(new LabelValue(scheduleTemplate.getId_(), scheduleTemplate.getName()));
                    }
                }
                Collections.sort(selectItems);

        }
        return selectItems;
    }

    private static List<ContestPhase> getCurrentPhasesForSchedule(Long existingContestScheduleId) {

            return ContestClient
                    .getPhasesForContestScheduleIdAndContest(existingContestScheduleId, 0l);

    }

    private static boolean arePhasesCompatibleUntilCurrentPhase(List<ContestPhase> curentContestSchedulePhases, Long selectableScheduleId) {
            List<ContestPhase> selectablePhases = getCurrentPhasesForSchedule(selectableScheduleId);
            Date now = new Date();
            for (int i=0; i < curentContestSchedulePhases.size();i++){
                ContestPhase phase =  curentContestSchedulePhases.get(i);
                if (!(phase.getPhaseStartDate().after(now))) {
                    boolean arePhaseTypesEqual = (selectablePhases.size() > i &&
                            selectablePhases.get(i).getContestPhaseType() == phase.getContestPhaseType());
                    if (!arePhaseTypesEqual) {
                        return false;
                    }
                } else {
                    break;
                }
            }
        return true;
    }

    private static void updateContestPhasesOfContestAccordingToContestSchedule(Contest contest,
            Long scheduleTemplateId) {


            List<ContestPhase> newPhasesForContest = new ArrayList<>();
            List<ContestPhase> existingPhasesOfContest = ContestClient
                    .getAllContestPhases(contest.getContestPK());
            Map<Long, Long> phaseTypeIdToPhaseIdMapForExistingContestPhases = new LinkedHashMap<>();
            populatePhaseTypeIdToPhaseIdMap(contest,
                    phaseTypeIdToPhaseIdMapForExistingContestPhases);

            List<ContestPhase> phasesOfContestSchedule = ContestClient.
                    getPhasesForContestScheduleIdAndContest(scheduleTemplateId,
                            ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
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
                ContestPhase existingPhase;

                existingPhase = ContestClient
                            .getContestPhase(phaseIdOfExistingPhaseWithSchedulePhaseType);

                updateExistingContestPhaseAccordingToContestSchedulePhase(existingPhase,
                        schedulePhase);
                existingPhasesOfContest.remove(existingPhase);
            }

            removeRemainingUnusedContestPhases(existingPhasesOfContest);

    }

    private void updateContestPhasesOfContestAccordingToUpdatedContestSchedule(Contest contest) {

            List<ContestPhase> newPhasesForContest = new ArrayList<>();
            List<ContestPhase> existingPhasesOfContest = ContestClient
                    .getAllContestPhases(contest.getContestPK());
            Map<Long, Long> phaseTypeIdToPhaseIdMapForExistingContestPhases = new LinkedHashMap<>();
            populatePhaseTypeIdToPhaseIdMap(contest,
                    phaseTypeIdToPhaseIdMapForExistingContestPhases);

            for (ContestPhaseBean schedulePhase : schedulePhases) {

                Long phaseTypeIdOfSchedulePhase = schedulePhase.getContestPhaseTypeOld();
                Long phaseIdOfExistingPhaseWithSchedulePhaseType = phaseTypeIdToPhaseIdMapForExistingContestPhases
                        .get(phaseTypeIdOfSchedulePhase);

                if (phaseIdOfExistingPhaseWithSchedulePhaseType == null) {
                    removeContestPhases(newPhasesForContest);
                    throw new InternalException("Can't map new contestPhaseTypeId: "
                            + phaseTypeIdOfSchedulePhase
                            + " to any of the existing contestPhaseTypeIds.");
                }
                if (!schedulePhase.isContestPhaseDeleted()) {

                        ContestPhase existingPhase = ContestClient
                                .getContestPhase(phaseIdOfExistingPhaseWithSchedulePhaseType);
                        updateExistingContestPhaseAccordingToContestSchedulePhase(existingPhase,
                                schedulePhase.getContestPhase());
                        existingPhasesOfContest.remove(existingPhase);

                }
            }

            removeRemainingUnusedContestPhases(existingPhasesOfContest);

    }

    private static void removeRemainingUnusedContestPhases(List<ContestPhase> contestPhases) {
        removeContestPhases(contestPhases);
    }

    private static void updateExistingContestPhaseAccordingToContestSchedulePhase(
            ContestPhase existingContestPhase, ContestPhase contestSchedulePhase) {
        existingContestPhase.setContestPhaseType(contestSchedulePhase.getContestPhaseType());
        existingContestPhase.setContestScheduleId(contestSchedulePhase.getContestScheduleId());
        existingContestPhase.setContestPhaseDescriptionOverride(contestSchedulePhase.getContestPhaseDescriptionOverride());
        existingContestPhase.setUpdated(new Timestamp(new Date().getTime()));
        existingContestPhase.setPhaseStartDate(contestSchedulePhase.getPhaseStartDate());
        existingContestPhase.setPhaseEndDate(contestSchedulePhase.getPhaseEndDate());
        if (!isContestPhaseAlreadyPromoted(existingContestPhase)) {
            existingContestPhase.setContestPhaseAutopromote(contestSchedulePhase.getContestPhaseAutopromote());
        }
        existingContestPhase.setFellowScreeningActive(contestSchedulePhase.getFellowScreeningActive());

        ContestClient.updateContestPhase(existingContestPhase);

    }

    private static boolean isContestPhaseAlreadyPromoted(ContestPhase contestPhase) {
        return contestPhase.getContestPhaseAutopromote().equals(ContestPhasePromoteType.PROMOTE_DONE.getValue());
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
                contestSchedulePhase.getContestPhaseTypeOld().equals(contestSchedulePhase.getContestPhaseType()));
    }

    private static void createContestPhaseFromExistingContestPhaseWithContestId(ContestPhaseBean contestSchedulePhase, Long contestId) {

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

    private static void populatePhaseTypeIdToPhaseIdMap(Contest contest, Map<Long, Long> contestPhaseTypeIdPhaseIdMap) {

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

    private static void removeContestPhases(List<ContestPhase> contestPhases) {
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

    private static void removeContestSchedule(Long scheduleId) {
        try {
            ContestScheduleLocalServiceUtil.deleteContestSchedule(scheduleId);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            _log.debug("Attempt to delete already deleted contest schedule: " + scheduleId);
        }
    }
}
