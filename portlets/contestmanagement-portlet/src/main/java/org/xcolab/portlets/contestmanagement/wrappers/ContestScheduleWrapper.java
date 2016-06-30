package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestSchedule;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestScheduleLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.enums.ColabConstants;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.portlets.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.ContestCreatorUtil;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.wrappers.BaseContestWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        try {
            schedulePhases = new ArrayList<>();
            List<ContestPhase> contestPhases =
                    ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(scheduleId,
                            ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
            for (ContestPhase contestPhase : contestPhases) {
                schedulePhases.add(new ContestPhaseBean(contestPhase));
            }
            addDummySchedulePhase();
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private void initContestSchedule(Long scheduleId) {
        try {
            if (scheduleId != null) {
                contestSchedule = ContestScheduleLocalServiceUtil.getContestSchedule(scheduleId);
            } else {
                List<ContestSchedule> contestScheduleList = ContestScheduleLocalServiceUtil.getContestSchedules(0, Integer.MAX_VALUE);
                contestSchedule = contestScheduleList.get(0);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new IllegalArgumentException("Contest schedule " + scheduleId + " does not exist");
        }
    }

    private void addDummySchedulePhase() {
        try {
            Long dummyContestPhaseId = CounterLocalServiceUtil
                    .increment(ContestPhase.class.getName());
            ContestPhase dummyContestPhase = ContestPhaseLocalServiceUtil
                    .createContestPhase(dummyContestPhaseId);
            ContestPhaseBean dummyContestPhaseBean = new ContestPhaseBean(dummyContestPhase);
            dummyContestPhaseBean.setContestPK(ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
            dummyContestPhaseBean.setContestScheduleId(getScheduleId());
            dummyContestPhaseBean.setContestPhasePK(ContestPhaseBean.CREATE_PHASE_CONTEST_PK);
            dummyContestPhaseBean.setContestSchedulePK(ContestPhaseBean.CREATE_PHASE_CONTEST_PK);
            dummyContestPhaseBean
                    .setContestPhaseAutopromote(ContestPhasePromoteType.DEFAULT.getValue());
            schedulePhases.add(dummyContestPhaseBean);
            ContestPhaseLocalServiceUtil.deleteContestPhase(dummyContestPhase);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private void initContestsUsingSelectedSchedule(Long scheduleId) {
        try {
            List<Contest> contestsUsingSelectedScheduleList = ContestLocalServiceUtil
                    .getContestsByContestScheduleId(scheduleId);
            contestsUsingSelectedSchedule = new ArrayList<>();
            for (Contest contest : contestsUsingSelectedScheduleList) {
                contestsUsingSelectedSchedule.add(new BaseContestWrapper(contest));
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public List<BaseContestWrapper> getContestsUsingSelectedSchedule() {
        return contestsUsingSelectedSchedule;
    }

    public Long getScheduleId() {
        return contestSchedule.getId();
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
        try {
            Long existingContestScheduleId = contestSchedule.getId();
            Long newContestScheduleId = CounterLocalServiceUtil
                    .increment(ContestSchedule.class.getName());

            contestSchedule.setId(newContestScheduleId);
            ContestSchedule newContestSchedule = ContestScheduleLocalServiceUtil
                    .addContestSchedule(contestSchedule);
            newContestSchedule.persist();

            contestSchedule.setId(existingContestScheduleId);
            duplicateContestPhasesFromExistingScheduleToNewSchedule(existingContestScheduleId,
                    newContestScheduleId);
            contestSchedule = newContestSchedule;
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void duplicateContestPhasesFromExistingScheduleToNewSchedule(Long existingContestScheduleId, Long newContestScheduleId) {
        try {
            List<ContestPhase> contestPhasesForExistingSchedule = ContestPhaseLocalServiceUtil
                    .getPhasesForContestScheduleId(existingContestScheduleId);
            for (ContestPhase contestPhase : contestPhasesForExistingSchedule) {
                contestPhase.setContestScheduleId(newContestScheduleId);
                createContestPhaseFromExistingContestPhaseWithContestId(contestPhase,
                        ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public static void changeContestScheduleForContest(Contest contest, Long contestScheduleId) {
        try {
            List<ContestPhase> contestPhasesForContestSchedule = ContestPhaseLocalServiceUtil
                    .getPhasesForContestScheduleId(contestScheduleId);
            updateContestPhasesWithProposalsToNewScheduleId(contest, contestScheduleId);
            createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(contest,
                    contestPhasesForContestSchedule, contestScheduleId);
            updateContestPhasesOfContestAccordingToContestSchedule(contest, contestScheduleId);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private void persistUpdatedSchedule() {
        try {
            createNewSchedulesPhases();
            makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(contestSchedule.getId());
            updateContestsUsingSchedule(contestSchedule.getId());
            contestSchedule.persist();
            persistChangesToSchedulePhases();
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
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
        try {
            List<ContestPhase> existingContestPhasesForContest = ContestPhaseLocalServiceUtil
                    .getPhasesForContest(contest);
            Date now = new Date();
            for (ContestPhase phase : existingContestPhasesForContest) {
                if (!(phase.getPhaseStartDate().after(now))) {
                    phase.setContestScheduleId(newScheduleId);
                    ContestPhaseLocalServiceUtil.updateContestPhase(phase);
                }
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }
    private void updateContestsUsingSchedule(Long contestScheduleId) {
        try {
            List<Contest> contestsUsingScheduleId = ContestLocalServiceUtil
                    .getContestsByContestScheduleId(contestScheduleId);
            for (Contest contest : contestsUsingScheduleId) {
                updateContestPhasesOfContestAccordingToUpdatedContestSchedule(contest);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private void makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(Long contestScheduleId) {
        try {
            List<Contest> contestsUsingScheduleId = ContestLocalServiceUtil
                    .getContestsByContestScheduleId(contestScheduleId);
            for (Contest contest : contestsUsingScheduleId) {
                createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(contest,
                        contestScheduleId);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(Contest contest,
                        List<ContestPhase> contestPhasesForContestSchedule, Long contestScheduleId) {
        try {
            List<ContestPhase> existingContestPhasesForContest = ContestPhaseLocalServiceUtil
                    .getPhasesForContest(contest);
            for (ContestPhase contestPhaseOfContestSchedule : contestPhasesForContestSchedule) {
                Long contestPhaseType = contestPhaseOfContestSchedule.getContestPhaseType();
                if (!isContestPhaseTypeInContestPhaseList(existingContestPhasesForContest,
                        contestPhaseType, contestScheduleId)) {
                    createContestPhaseFromExistingContestPhaseWithContestId(
                            contestPhaseOfContestSchedule, contest.getContestPK());
                }
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private void createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(Contest contest, Long contestScheduleId) {
        try {
            List<ContestPhase> existingContestPhasesForContest = ContestPhaseLocalServiceUtil
                    .getPhasesForContest(contest);
            for (ContestPhaseBean contestPhaseOfContestSchedule : schedulePhases) {
                Long contestPhaseType = contestPhaseOfContestSchedule.getContestPhaseType();
                if (!isContestPhaseTypeInContestPhaseList(existingContestPhasesForContest,
                        contestPhaseType, contestScheduleId)) {
                    createContestPhaseFromExistingContestPhaseWithContestId(
                            contestPhaseOfContestSchedule, contest.getContestPK());
                }
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
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
        try {
            ContestCreatorUtil.insertSeedDataToContestScheduleTableIfNotAvailable();
            for (ContestSchedule scheduleTemplate : ContestScheduleLocalServiceUtil.getContestSchedules(0, Integer.MAX_VALUE)) {
                selectItems.add(new LabelValue(scheduleTemplate.getId(), scheduleTemplate.getName()));
            }
            Collections.sort(selectItems);
        } catch (SystemException ignored) { }
        return selectItems;
    }

    public static List<LabelValue> getScheduleTemplateSelectionItems(long existingScheduleId, boolean onlyShowSchedulesWithSamePhases) {
        List<LabelValue> selectItems = new ArrayList<>();
        if (!onlyShowSchedulesWithSamePhases) {
            selectItems = getAllScheduleTemplateSelectionItems();
        } else {
            try {
                List<ContestPhase> currentPhases = getCurrentPhasesForSchedule(existingScheduleId);
                for (ContestSchedule scheduleTemplate : ContestScheduleLocalServiceUtil.getContestSchedules(0, Integer.MAX_VALUE)) {
                    if (arePhasesCompatibleUntilCurrentPhase(currentPhases, scheduleTemplate.getId())) {
                        selectItems.add(new LabelValue(scheduleTemplate.getId(), scheduleTemplate.getName()));
                    }
                }
                Collections.sort(selectItems);
            } catch (SystemException e) {
                throw new DatabaseAccessException(e);
            }
        }
        return selectItems;
    }

    private static List<ContestPhase> getCurrentPhasesForSchedule(Long existingContestScheduleId) {
        try {
            return ContestPhaseLocalServiceUtil
                    .getPhasesForContestScheduleIdAndContest(existingContestScheduleId, 0);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
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

        try {
            List<ContestPhase> newPhasesForContest = new ArrayList<>();
            List<ContestPhase> existingPhasesOfContest = ContestPhaseLocalServiceUtil
                    .getPhasesForContest(contest.getContestPK());
            Map<Long, Long> phaseTypeIdToPhaseIdMapForExistingContestPhases = new LinkedHashMap<>();
            populatePhaseTypeIdToPhaseIdMap(contest,
                    phaseTypeIdToPhaseIdMapForExistingContestPhases);

            List<ContestPhase> phasesOfContestSchedule = ContestPhaseLocalServiceUtil.
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
                try {
                    existingPhase = ContestPhaseLocalServiceUtil
                            .getContestPhase(phaseIdOfExistingPhaseWithSchedulePhaseType);
                } catch (PortalException e) {
                    throw new IllegalStateException("Existing phase "
                            + phaseIdOfExistingPhaseWithSchedulePhaseType + " of contest "
                            + contest.getContestPK() + " not found while mapping to new schedule");
                }
                updateExistingContestPhaseAccordingToContestSchedulePhase(existingPhase,
                        schedulePhase);
                existingPhasesOfContest.remove(existingPhase);
            }

            removeRemainingUnusedContestPhases(existingPhasesOfContest);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private void updateContestPhasesOfContestAccordingToUpdatedContestSchedule(Contest contest) {
        try {
            List<ContestPhase> newPhasesForContest = new ArrayList<>();
            List<ContestPhase> existingPhasesOfContest = ContestPhaseLocalServiceUtil
                    .getPhasesForContest(contest.getContestPK());
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
                    try {
                        ContestPhase existingPhase = ContestPhaseLocalServiceUtil
                                .getContestPhase(phaseIdOfExistingPhaseWithSchedulePhaseType);
                        updateExistingContestPhaseAccordingToContestSchedulePhase(existingPhase,
                                schedulePhase.getContestPhase());
                        existingPhasesOfContest.remove(existingPhase);
                    } catch (PortalException e) {
                        throw ReferenceResolutionException.toObject(ContestPhase.class,
                                phaseIdOfExistingPhaseWithSchedulePhaseType).build();
                    }
                }
            }

            removeRemainingUnusedContestPhases(existingPhasesOfContest);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void removeRemainingUnusedContestPhases(List<ContestPhase> contestPhases) {
        removeContestPhases(contestPhases);
    }

    private static void updateExistingContestPhaseAccordingToContestSchedulePhase(
            ContestPhase existingContestPhase, ContestPhase contestSchedulePhase) {
        existingContestPhase.setContestPhaseType(contestSchedulePhase.getContestPhaseType());
        existingContestPhase.setContestScheduleId(contestSchedulePhase.getContestScheduleId());
        existingContestPhase.setContestPhaseDescriptionOverride(contestSchedulePhase.getContestPhaseDescriptionOverride());
        existingContestPhase.setUpdated(new Date());
        existingContestPhase.setPhaseStartDate(contestSchedulePhase.getPhaseStartDate());
        existingContestPhase.setPhaseEndDate(contestSchedulePhase.getPhaseEndDate());
        if (!isContestPhaseAlreadyPromoted(existingContestPhase)) {
            existingContestPhase.setContestPhaseAutopromote(contestSchedulePhase.getContestPhaseAutopromote());
        }
        existingContestPhase.setFellowScreeningActive(contestSchedulePhase.getFellowScreeningActive());
        try {
            ContestPhaseLocalServiceUtil.updateContestPhase(existingContestPhase);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
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
        try {
            List<ContestPhase> contestSchedulePhases = ContestPhaseLocalServiceUtil
                    .getPhasesForContestScheduleId(newScheduleTemplateId);
            for (ContestPhase contestSchedulePhase : contestSchedulePhases) {
                createContestPhaseFromExistingContestPhaseWithContestId(contestSchedulePhase,
                        contest.getContestPK());
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static Boolean isUpdateSchedulePhaseType(ContestPhaseBean contestSchedulePhase) {
        return !(contestSchedulePhase.getContestPhaseTypeOld().equals(0L) &&
                contestSchedulePhase.getContestPhaseTypeOld().equals(contestSchedulePhase.getContestPhaseType()));
    }

    private static void createContestPhaseFromExistingContestPhaseWithContestId(ContestPhaseBean contestSchedulePhase, Long contestId) {
        try {
            ContestPhase contestPhase = contestSchedulePhase.getContestPhase();
            if (isUpdateSchedulePhaseType(contestSchedulePhase)) {
                contestPhase.setContestPhaseType(contestSchedulePhase.getContestPhaseTypeOld());
            }
            ContestPhase newContestPhase = ContestPhaseLocalServiceUtil
                    .createFromContestPhase(contestPhase);
            newContestPhase.setContestPK(contestId);
            newContestPhase.persist();
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void createContestPhaseFromExistingContestPhaseWithContestId(
            ContestPhase contestSchedulePhase, Long contestId) {
        try {
            ContestPhase newContestPhase = ContestPhaseLocalServiceUtil
                    .createFromContestPhase(contestSchedulePhase);
            newContestPhase.setContestPK(contestId);
            newContestPhase.persist();
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void populatePhaseTypeIdToPhaseIdMap(Contest contest, Map<Long, Long> contestPhaseTypeIdPhaseIdMap) {
        try {
            List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil
                    .getPhasesForContest(contest.getContestPK());
            for (ContestPhase contestPhase : contestPhases) {
                contestPhaseTypeIdPhaseIdMap
                        .put(contestPhase.getContestPhaseType(), contestPhase.getPrimaryKey());
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void removeExistingContestPhases(Contest contest) {
        try {
            List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil
                    .getPhasesForContest(contest.getContestPK());
            for (ContestPhase contestPhase : contestPhases) {
                ContestPhaseLocalServiceUtil.deleteContestPhase(contestPhase);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
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
                ContestPhaseLocalServiceUtil.deleteContestPhase(contestPhase);
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
        try {
            List<ContestPhase> contestSchedulePhases = ContestPhaseLocalServiceUtil
                    .getPhasesForContestScheduleId(scheduleId);
            removeContestPhases(contestSchedulePhases);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    private static void removeContestPhaseOfContestThatAreUsingSchedule(Long scheduleId) {
        try {
            List<Contest> contestsUsingSchedule = ContestLocalServiceUtil
                    .getContestsByContestScheduleId(scheduleId);
            for (Contest contestUsingSchedule : contestsUsingSchedule) {
                List<ContestPhase> contestSchedulePhases =
                        ContestPhaseLocalServiceUtil
                                .getPhasesForContestScheduleIdAndContest(scheduleId,
                                        contestUsingSchedule.getContestPK());
                removeContestPhases(contestSchedulePhases);
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
}
