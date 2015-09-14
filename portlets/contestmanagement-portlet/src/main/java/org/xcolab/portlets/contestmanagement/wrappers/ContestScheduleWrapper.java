package org.xcolab.portlets.contestmanagement.wrappers;


import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.enums.ColabConstants;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.portlets.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.ContestCreatorUtil;
import org.xcolab.wrapper.ContestWrapper;

import java.util.*;

/**
 * Created by Thomas on 2/20/2015.
 */
public class ContestScheduleWrapper {

    private final static Log _log = LogFactoryUtil.getLog(ContestScheduleWrapper.class);

    private List<ContestPhaseBean> schedulePhases;
    private ContestSchedule contestSchedule;
    private List<ContestWrapper> contestsUsingSelectedSchedule;
    private Boolean createNew = false;

    public ContestScheduleWrapper() {
        schedulePhases = new ArrayList<>();
        contestsUsingSelectedSchedule = new ArrayList<>();
    }

    public ContestScheduleWrapper(Long scheduleId) {
        try {
            initContestSchedule(scheduleId);
            initContestPhases(scheduleId);
            initContestsUsingSelectedSchedule(scheduleId);
        } catch (Exception e) {
            _log.info("Could not create ContestScheduleWrapper for scheduleId: " + scheduleId, e);
        }
    }


    private void initContestPhases(Long scheduleId) throws Exception {
        schedulePhases = new ArrayList<>();
        List<ContestPhase> contestPhases =
                ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(scheduleId, ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
        for (ContestPhase contestPhase : contestPhases) {
            schedulePhases.add(new ContestPhaseBean(contestPhase));
        }
        addDummySchedulePhase();
    }

    private void initContestSchedule(Long scheduleId) {
        try {
            if (scheduleId != null) {
                contestSchedule = ContestScheduleLocalServiceUtil.getContestSchedule(scheduleId);
            } else {
                List<ContestSchedule> contestScheduleList = ContestScheduleLocalServiceUtil.getContestSchedules(0, Integer.MAX_VALUE);
                contestSchedule = contestScheduleList.get(0);
            }
        } catch (Exception e) {

        }

    }

    private void addDummySchedulePhase() throws Exception {
        Long dummyContestPhaseId = CounterLocalServiceUtil.increment(ContestPhase.class.getName());
        ContestPhase dummyContestPhase = ContestPhaseLocalServiceUtil.createContestPhase(dummyContestPhaseId);
        ContestPhaseBean dummyContestPhaseBean = new ContestPhaseBean(dummyContestPhase);
        dummyContestPhaseBean.setContestPK(ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
        dummyContestPhaseBean.setContestScheduleId(getScheduleId());
        dummyContestPhaseBean.setContestPhasePK(ContestPhaseBean.CREATE_PHASE_CONTEST_PK);
        dummyContestPhaseBean.setContestSchedulePK(ContestPhaseBean.CREATE_PHASE_CONTEST_PK);
        schedulePhases.add(dummyContestPhaseBean);
        ContestPhaseLocalServiceUtil.deleteContestPhase(dummyContestPhase);
    }

    private void initContestsUsingSelectedSchedule(Long scheduleId) throws Exception {
        List<Contest> contestsUsingSelectedScheduleList = ContestLocalServiceUtil.getContestsByContestScheduleId(scheduleId);
        contestsUsingSelectedSchedule = new ArrayList<>();
        for (Contest contest : contestsUsingSelectedScheduleList) {
            contestsUsingSelectedSchedule.add(new ContestWrapper(contest));
        }
    }

    public List<ContestWrapper> getContestsUsingSelectedSchedule() {
        return contestsUsingSelectedSchedule;
    }

    public Long getScheduleId() {
        return contestSchedule.getId();
    }

    public String getScheduleName() {
        return contestSchedule.getName();
    }

    public void setScheduleId(Long contestScheduleId) {
        try {
            initContestSchedule(contestScheduleId);
            initContestsUsingSelectedSchedule(contestScheduleId);
        } catch (Exception e) {
            _log.warn("Could init with new schedule id:", e);
        }
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

    public void persist() throws Exception {
        removeEmptyContestPhases();
        if (createNew) {
            createNewScheduleFromExistingSchedule();
        } else {
            persistUpdatedSchedule();
        }
    }

    private void removeEmptyContestPhases() throws Exception {
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

    private void createNewScheduleFromExistingSchedule() throws Exception {
        Long existingContestScheduleId = contestSchedule.getId();
        Long newContestScheduleId = CounterLocalServiceUtil.increment(ContestSchedule.class.getName());

        contestSchedule.setId(newContestScheduleId);
        ContestSchedule newContestSchedule = ContestScheduleLocalServiceUtil.addContestSchedule(contestSchedule);
        newContestSchedule.persist();

        contestSchedule.setId(existingContestScheduleId);
        duplicateContestPhasesFromExistingScheduleToNewSchedule(existingContestScheduleId, newContestScheduleId);
        contestSchedule = newContestSchedule;
    }

    private static void duplicateContestPhasesFromExistingScheduleToNewSchedule(Long existingContestScheduleId, Long newContestScheduleId) throws Exception {
        List<ContestPhase> contestPhasesForExistingSchedule = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(existingContestScheduleId);
        for (ContestPhase contestPhase : contestPhasesForExistingSchedule) {
            contestPhase.setContestScheduleId(newContestScheduleId);
            createContestPhaseFromExistingContestPhaseWithContestId(contestPhase, ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
        }
    }

    private void persistUpdatedSchedule() throws Exception {
        createNewSchedulesPhases();
        makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(contestSchedule.getId());
        updateContestsUsingSchedule(contestSchedule.getId());
        contestSchedule.persist();
        persistChangesToSchedulePhases();
    }

    private void createNewSchedulesPhases() throws Exception {
        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            Boolean isNewSchedulePhase = contestPhaseBean.getContestPhaseTypeOld().equals(0L);
            if(isNewSchedulePhase) {
                contestPhaseBean.persist();
            }
        }
    }
    private void persistChangesToSchedulePhases() throws Exception {
        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            contestPhaseBean.persist();
        }
    }

    private void updateContestsUsingSchedule(Long contestScheduleId) throws Exception {
        List<Contest> contestsUsingScheduleId = ContestLocalServiceUtil.getContestsByContestScheduleId(contestScheduleId);
        for (Contest contest : contestsUsingScheduleId) {
            updateContestPhasesOfContestAccordingToContestSchedule(contest, contestScheduleId);
        }
    }

    private void makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(Long contestScheduleId) throws Exception {
        List<Contest> contestsUsingScheduleId = ContestLocalServiceUtil.getContestsByContestScheduleId(contestScheduleId);
        for (Contest contest : contestsUsingScheduleId) {
            createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(contest, contestScheduleId);
        }
    }

    private void createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(Contest contest, Long contestScheduleId) throws Exception {

        List<ContestPhase> existingContestPhasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest);
        //List<ContestPhase> contestPhasesForContestSchedule = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(contestScheduleId);

        for (ContestPhaseBean contestPhaseOfContestSchedule : schedulePhases) {
            Long contestPhaseType = contestPhaseOfContestSchedule.getContestPhaseType();
            if (!isContestPhaseTypeInContestPhaseList(existingContestPhasesForContest, contestPhaseType, contestScheduleId)) {
                createContestPhaseFromExistingContestPhaseWithContestId(contestPhaseOfContestSchedule, contest.getContestPK());
            }
        }
    }

    private static boolean isContestPhaseTypeInContestPhaseList(List<ContestPhase> contestPhases, Long contestPhaseTyp, Long contestScheduleId) {
        for (ContestPhase existingContestPhaseForContest : contestPhases) {
            if (existingContestPhaseForContest.getContestPhaseType() == contestPhaseTyp &&
                    existingContestPhaseForContest.getContestScheduleId() == contestScheduleId) return true;
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
        } catch (Exception e) {
        }
        return selectItems;
    }

    public static List<LabelValue> getScheduleTemplateSelectionItems(long existingScheduleId, boolean showOnlySchedulesWithSamePhases) {
        List<LabelValue> selectItems = new ArrayList<>();
        if (!showOnlySchedulesWithSamePhases) {
            selectItems = getAllScheduleTemplateSelectionItems();
        } else {
            try {
                ContestCreatorUtil.insertSeedDataToContestScheduleTableIfNotAvailable();
                for (ContestSchedule scheduleTemplate : ContestScheduleLocalServiceUtil.getContestSchedules(0, Integer.MAX_VALUE)) {
                    if (haveContestSchedulesSamePhases(existingScheduleId, scheduleTemplate.getId())) {
                        selectItems.add(new LabelValue(scheduleTemplate.getId(), scheduleTemplate.getName()));
                    }
                }
                Collections.sort(selectItems);
            } catch (Exception e) {
                _log.warn("Couldn't fetch schedule template selection items", e);
            }
        }
        return selectItems;
    }

    private static List<Long> getContestSchedulePhaseTypes(Long existingContestScheduleId) throws Exception {
        List<Long> contestSchedulePhaseTypes = new ArrayList<>();
        List<ContestPhase> contestPhasesForContestSchedule = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(existingContestScheduleId, 0);
        for (ContestPhase contestPhase : contestPhasesForContestSchedule) {
            contestSchedulePhaseTypes.add(contestPhase.getContestPhaseType());
        }
        return contestSchedulePhaseTypes;
    }

    private static boolean haveContestSchedulesSamePhases(Long contestScheduleId1, Long contestScheduleId2) throws Exception {
        List<Long> contestSchedule1PhaseTypes = getContestSchedulePhaseTypes(contestScheduleId1);
        List<Long> contestSchedule2PhaseTypes = getContestSchedulePhaseTypes(contestScheduleId2);
        return areContestSchedulesPhasesEqual(contestSchedule1PhaseTypes, contestSchedule2PhaseTypes);
    }

    private static boolean areContestSchedulesPhasesEqual(List<Long> referenceContestSchedulePhases, List<Long> testContestSchedulePhases) {
        boolean haveContestSchedulesSamePhases = true;
        for (int i = 0; i < referenceContestSchedulePhases.size(); i++) {
            try {
                if (!referenceContestSchedulePhases.get(i).equals(testContestSchedulePhases.get(i))) {
                    haveContestSchedulesSamePhases = false;
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                haveContestSchedulesSamePhases = false;
                break;
            }
        }
        return haveContestSchedulesSamePhases;
    }


    public void updateContestPhasesOfContestAccordingToContestSchedule(Contest contest, Long scheduleTemplateId) throws Exception {

        List<ContestPhase> newPhasesForContest = new ArrayList<>();
        List<ContestPhase> existingPhasesOfContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        Map<Long, Long> phaseTypeIdToPhaseIdMapForExistingContestPhases = new LinkedHashMap<>();
        populatePhaseTypeIdToPhaseIdMap(contest, phaseTypeIdToPhaseIdMapForExistingContestPhases);

        List<ContestPhase> phasesOfContestSchedule = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(scheduleTemplateId, ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
        //for (ContestPhase schedulePhase : phasesOfContestSchedule) {
        for (ContestPhaseBean schedulePhase : schedulePhases) {

            Long phaseTypeIdOfSchedulePhase = schedulePhase.getContestPhaseTypeOld();
            Long phaseIdOfExistingPhaseWithSchedulePhaseType = phaseTypeIdToPhaseIdMapForExistingContestPhases.get(phaseTypeIdOfSchedulePhase);

            if (phaseIdOfExistingPhaseWithSchedulePhaseType == null) {
                removeContestPhases(newPhasesForContest);
                throw new Exception("Can't map new contestPhaseTypeId: " + phaseTypeIdOfSchedulePhase + " to any of the existing contestPhaseTypeIds.");
            }
            if(!schedulePhase.isContestPhaseDeleted()) {
                ContestPhase existingPhase = ContestPhaseLocalServiceUtil.getContestPhase(phaseIdOfExistingPhaseWithSchedulePhaseType);
                updateExistingContestPhaseAccordingToContestSchedulePhase(existingPhase, schedulePhase.getContestPhase());
                existingPhasesOfContest.remove(existingPhase);
            }
        }

        removeRemainingUnusedContestPhases(existingPhasesOfContest);
    }

    private static void removeRemainingUnusedContestPhases(List<ContestPhase> contestPhases) throws Exception {
        removeContestPhases(contestPhases);
    }

    private void updateExistingContestPhaseAccordingToContestSchedulePhase(ContestPhase existingContestPhase, ContestPhase contestSchedulePhase) throws SystemException {
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
        ContestPhaseLocalServiceUtil.updateContestPhase(existingContestPhase);
    }

    private static void setNewContestPhasePK(ContestPhase contestPhase, Contest contest) throws SystemException {
        ContestPhase contestPhaseNew = ContestPhaseLocalServiceUtil.
                createContestPhase(CounterLocalServiceUtil.increment(ContestPhase.class.getName()));

        contestPhase.setContestPK(contest.getContestPK());
        contestPhase.setPrimaryKey(contestPhaseNew.getPrimaryKey());
        ContestPhaseLocalServiceUtil.addContestPhase(contestPhase);
    }

    private static boolean isContestPhaseAlreadyPromoted(ContestPhase contestPhase) {
        return contestPhase.getContestPhaseAutopromote().equals(ContestPhasePromoteType.PROMOTE_DONE.getValue());
    }

    public static void deleteProposal2PhasesWithContestPhaseId(Long contestPhaseId) throws Exception {
        List<Proposal2Phase> proposal2Phases = Proposal2PhaseLocalServiceUtil.getByContestPhaseId(contestPhaseId);

        for (Iterator i = proposal2Phases.iterator(); i.hasNext(); ) {
            Proposal2Phase proposal2Phase = (Proposal2Phase) i.next();
            Proposal2PhaseLocalServiceUtil.deleteProposal2Phase(proposal2Phase);
        }
    }

    public static void deleteProposalRatingsWithContestPhaseId
            (Long contestPhaseId) throws Exception {
        List<ProposalRating> proposalRatings = new ArrayList<>(); // ProposalRatingLocalServiceUtil.getRatingsForContestPhase(contestPhaseId);

        for (Iterator i = proposalRatings.iterator(); i.hasNext(); ) {
            ProposalRating proposalRating = (ProposalRating) i.next();
            ProposalRatingLocalServiceUtil.deleteProposalRating(proposalRating);
        }
    }

    public static void createContestPhasesAccordingToContestScheduleAndRemoveExistingPhases(Contest contest, Long newScheduleTemplateId) throws Exception {
        removeExistingContestPhases(contest);
        List<ContestPhase> contestSchedulePhases = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(newScheduleTemplateId);
        for (ContestPhase contestSchedulePhase : contestSchedulePhases) {
            createContestPhaseFromExistingContestPhaseWithContestId(contestSchedulePhase, contest.getContestPK());
        }
    }

    private static Boolean isUpdateSchedulePhaseType(ContestPhaseBean contestSchedulePhase){
        return !(       contestSchedulePhase.getContestPhaseTypeOld().equals(0L) &&
                        contestSchedulePhase.getContestPhaseTypeOld().equals(contestSchedulePhase.getContestPhaseType()));
    }
    private static void createContestPhaseFromExistingContestPhaseWithContestId(ContestPhaseBean contestSchedulePhase, Long contestId) throws Exception {
        ContestPhase contestPhase = contestSchedulePhase.getContestPhase();
        if(isUpdateSchedulePhaseType(contestSchedulePhase)) {
            contestPhase.setContestPhaseType(contestSchedulePhase.getContestPhaseTypeOld());
        }
        ContestPhase newContestPhase = ContestPhaseLocalServiceUtil.createFromContestPhase(contestPhase);
        newContestPhase.setContestPK(contestId);
        newContestPhase.persist();
    }
    private static void createContestPhaseFromExistingContestPhaseWithContestId(ContestPhase contestSchedulePhase, Long contestId) throws Exception {

        ContestPhase newContestPhase = ContestPhaseLocalServiceUtil.createFromContestPhase(contestSchedulePhase);
        newContestPhase.setContestPK(contestId);
        newContestPhase.persist();
    }

    private static void populatePhaseTypeIdToPhaseIdMap(Contest contest, Map<Long, Long> contestPhaseTypeIdPhaseIdMap) throws Exception {
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        for (ContestPhase contestPhase : contestPhases) {
            contestPhaseTypeIdPhaseIdMap.put(contestPhase.getContestPhaseType(), contestPhase.getPrimaryKey());
        }
    }

    private static void removeExistingContestPhases(Contest contest) throws Exception {
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        for (ContestPhase contestPhase : contestPhases) {
            ContestPhaseLocalServiceUtil.deleteContestPhase(contestPhase);
        }
    }

    private static void removeContestPhases(List<ContestPhase> contestPhases) throws Exception {
        for (ContestPhase contestPhase : contestPhases) {
            Long contestPhaseId = contestPhase.getContestPhasePK();
            List<Proposal2Phase> proposal2Phases =
                    Proposal2PhaseLocalServiceUtil.getByContestPhaseId(contestPhaseId);
            if (!proposal2Phases.isEmpty()) {
                // TODO how should we treat these remaining entries?
                _log.warn("There are remaining proposal2phase entries for contestPhaseId:" + contestPhaseId);
            }
            ContestPhaseLocalServiceUtil.deleteContestPhase(contestPhase);
        }
    }

    public static void deleteContestSchedule(Long scheduleId) throws Exception {
        boolean isContestScheduleUsed = ContestScheduleLocalServiceUtil.isContestScheduleUsed(scheduleId);
        if (!isContestScheduleUsed) {
            removeContestSchedulePhases(scheduleId);
            removeContestPhaseOfContestThatAreUsingSchedule(scheduleId);
            removeContestSchedule(scheduleId);
        } else {
            throw new Exception("Contest schedule used by contests and can not be deleted!");
        }
    }

    private static void removeContestSchedulePhases(Long scheduleId) throws Exception {
        List<ContestPhase> contestSchedulePhases = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(scheduleId);
        removeContestPhases(contestSchedulePhases);
    }

    private static void removeContestPhaseOfContestThatAreUsingSchedule(Long scheduleId) throws Exception {
        List<Contest> contestsUsingSchedule = ContestLocalServiceUtil.getContestsByContestScheduleId(scheduleId);
        for (Contest contestUsingSchedule : contestsUsingSchedule) {
            List<ContestPhase> contestSchedulePhases =
                    ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(scheduleId, contestUsingSchedule.getContestPK());
            removeContestPhases(contestSchedulePhases);
        }
    }

    private static void removeContestSchedule(Long scheduleId) throws Exception {
        ContestScheduleLocalServiceUtil.deleteContestSchedule(scheduleId);
    }

}
