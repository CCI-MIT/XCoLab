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
import org.xcolab.wrappers.BaseContestWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 2/20/2015.
 */
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
        try {
            initContestSchedule(scheduleId);
            initContestPhases(scheduleId);
            initContestsUsingSelectedSchedule(scheduleId);
        } catch (SystemException e) {
            _log.info("Could not create ContestScheduleWrapper for scheduleId: " + scheduleId, e);
        }
    }

    private void initContestPhases(Long scheduleId) throws SystemException {
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
        } catch (Exception ignored) { }
    }

    private void addDummySchedulePhase() throws SystemException {
        Long dummyContestPhaseId = CounterLocalServiceUtil.increment(ContestPhase.class.getName());
        ContestPhase dummyContestPhase = ContestPhaseLocalServiceUtil.createContestPhase(dummyContestPhaseId);
        ContestPhaseBean dummyContestPhaseBean = new ContestPhaseBean(dummyContestPhase);
        dummyContestPhaseBean.setContestPK(ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
        dummyContestPhaseBean.setContestScheduleId(getScheduleId());
        dummyContestPhaseBean.setContestPhasePK(ContestPhaseBean.CREATE_PHASE_CONTEST_PK);
        dummyContestPhaseBean.setContestSchedulePK(ContestPhaseBean.CREATE_PHASE_CONTEST_PK);
        dummyContestPhaseBean.setContestPhaseAutopromote(ContestPhasePromoteType.DEFAULT.getValue());
        schedulePhases.add(dummyContestPhaseBean);
        ContestPhaseLocalServiceUtil.deleteContestPhase(dummyContestPhase);
    }

    private void initContestsUsingSelectedSchedule(Long scheduleId) throws SystemException {
        List<Contest> contestsUsingSelectedScheduleList = ContestLocalServiceUtil.getContestsByContestScheduleId(scheduleId);
        contestsUsingSelectedSchedule = new ArrayList<>();
        for (Contest contest : contestsUsingSelectedScheduleList) {
            contestsUsingSelectedSchedule.add(new BaseContestWrapper(contest));
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

    public void persist() throws SystemException, PortalException {
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

    private void createNewScheduleFromExistingSchedule() throws SystemException {
        Long existingContestScheduleId = contestSchedule.getId();
        Long newContestScheduleId = CounterLocalServiceUtil.increment(ContestSchedule.class.getName());

        contestSchedule.setId(newContestScheduleId);
        ContestSchedule newContestSchedule = ContestScheduleLocalServiceUtil.addContestSchedule(contestSchedule);
        newContestSchedule.persist();

        contestSchedule.setId(existingContestScheduleId);
        duplicateContestPhasesFromExistingScheduleToNewSchedule(existingContestScheduleId, newContestScheduleId);
        contestSchedule = newContestSchedule;
    }

    private static void duplicateContestPhasesFromExistingScheduleToNewSchedule(Long existingContestScheduleId, Long newContestScheduleId) throws SystemException {
        List<ContestPhase> contestPhasesForExistingSchedule = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(existingContestScheduleId);
        for (ContestPhase contestPhase : contestPhasesForExistingSchedule) {
            contestPhase.setContestScheduleId(newContestScheduleId);
            createContestPhaseFromExistingContestPhaseWithContestId(contestPhase, ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
        }
    }

    public static void changeContestScheduleForContest(Contest contest, Long contestScheduleId) throws PortalException, SystemException {

        List<ContestPhase> contestPhasesForContestSchedule = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(contestScheduleId);
        updateContestPhasesWithProposalsToNewScheduleId(contest, contestScheduleId);
        createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(contest, contestPhasesForContestSchedule, contestScheduleId);
        updateContestPhasesOfContestAccordingToContestSchedule(contest, contestScheduleId);
    }

    private void persistUpdatedSchedule() throws SystemException, PortalException {
        createNewSchedulesPhases();
        makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(contestSchedule.getId());
        updateContestsUsingSchedule(contestSchedule.getId());
        contestSchedule.persist();
        persistChangesToSchedulePhases();
    }

    private void createNewSchedulesPhases() throws SystemException, PortalException {
        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            Boolean isNewSchedulePhase = contestPhaseBean.getContestPhaseTypeOld().equals(0L);
            if (isNewSchedulePhase) {
                contestPhaseBean.persist();
            }
        }
    }

    private void persistChangesToSchedulePhases() throws SystemException, PortalException {
        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            contestPhaseBean.persist();
        }
    }
    private static void updateContestPhasesWithProposalsToNewScheduleId(Contest contest, Long newScheduleId)
            throws SystemException{
        List<ContestPhase> existingContestPhasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest);
        Date now = new Date();
        for(int i=0; i < existingContestPhasesForContest.size();i++) {
            ContestPhase phase = existingContestPhasesForContest.get(i);
            if (!(phase.getPhaseStartDate().getTime() > now.getTime())) {
                phase.setContestScheduleId(newScheduleId);
                ContestPhaseLocalServiceUtil.updateContestPhase(phase);
            }
        }
    }
    private void updateContestsUsingSchedule(Long contestScheduleId) throws SystemException, PortalException {
        List<Contest> contestsUsingScheduleId = ContestLocalServiceUtil.getContestsByContestScheduleId(contestScheduleId);
        for (Contest contest : contestsUsingScheduleId) {
            updateContestPhasesOfContestAccordingToUpdatedContestSchedule(contest);
        }
    }

    private void makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(Long contestScheduleId) throws SystemException, PortalException {
        List<Contest> contestsUsingScheduleId = ContestLocalServiceUtil.getContestsByContestScheduleId(contestScheduleId);
        for (Contest contest : contestsUsingScheduleId) {
            createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(contest, contestScheduleId);
        }
    }

    private static void createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(Contest contest,
                        List<ContestPhase> contestPhasesForContestSchedule, Long contestScheduleId) throws SystemException {
        List<ContestPhase> existingContestPhasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest);
        for (ContestPhase contestPhaseOfContestSchedule : contestPhasesForContestSchedule) {
            Long contestPhaseType = contestPhaseOfContestSchedule.getContestPhaseType();
            if (!isContestPhaseTypeInContestPhaseList(existingContestPhasesForContest, contestPhaseType, contestScheduleId)) {
                createContestPhaseFromExistingContestPhaseWithContestId(contestPhaseOfContestSchedule, contest.getContestPK());
            }
        }
    }

    private void createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(Contest contest, Long contestScheduleId) throws SystemException, PortalException {
        List<ContestPhase> existingContestPhasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest);
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
            } catch (Exception e) {
                _log.warn("Couldn't fetch schedule template selection items", e);
            }
        }
        return selectItems;
    }

    private static List<ContestPhase> getCurrentPhasesForSchedule(Long existingContestScheduleId) throws SystemException{
           return ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(existingContestScheduleId, 0);
    }
    private static List<Long> getContestSchedulePhaseTypes(Long existingContestScheduleId) throws SystemException {
        List<Long> contestSchedulePhaseTypes = new ArrayList<>();
        List<ContestPhase> contestPhasesForContestSchedule =
                ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(existingContestScheduleId, 0);
        for (ContestPhase contestPhase : contestPhasesForContestSchedule) {
            contestSchedulePhaseTypes.add(contestPhase.getContestPhaseType());
        }
        return contestSchedulePhaseTypes;
    }

    private static boolean arePhasesCompatibleUntilCurrentPhase(List<ContestPhase> curentContestSchedulePhases, Long selectableScheduleId)
        throws SystemException {
            List<ContestPhase> selectablePhases = getCurrentPhasesForSchedule(selectableScheduleId);
            Date now = new Date();
            for(int i=0; i < curentContestSchedulePhases.size();i++){
                ContestPhase phase =  curentContestSchedulePhases.get(i);
                if(!(phase.getPhaseStartDate().getTime() > now.getTime())){
                    //phases are always created in chronological order
                    if(!(selectablePhases.size() > i &&
                            selectablePhases.get(i).getContestPhaseType() == phase.getContestPhaseType()))
                        return false;

                }else{
                    break;
                }
            }
        return true;
    }
    private static boolean areAllPhaseTypesOfScheduleAvailableInOtherSchedule(Long contestScheduleId1, Long contestScheduleId2) throws SystemException {
        List<Long> contestSchedule1PhaseTypes = getContestSchedulePhaseTypes(contestScheduleId1);
        List<Long> contestSchedule2PhaseTypes = getContestSchedulePhaseTypes(contestScheduleId2);
        return contestSchedule2PhaseTypes.containsAll(contestSchedule1PhaseTypes);
    }

    public static void updateContestPhasesOfContestAccordingToContestSchedule(Contest contest, Long scheduleTemplateId) throws SystemException, PortalException {

        List<ContestPhase> newPhasesForContest = new ArrayList<>();
        List<ContestPhase> existingPhasesOfContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        Map<Long, Long> phaseTypeIdToPhaseIdMapForExistingContestPhases = new LinkedHashMap<>();
        populatePhaseTypeIdToPhaseIdMap(contest, phaseTypeIdToPhaseIdMapForExistingContestPhases);

        List<ContestPhase> phasesOfContestSchedule = ContestPhaseLocalServiceUtil.
                getPhasesForContestScheduleIdAndContest(scheduleTemplateId, ColabConstants.DEFAULT_CONTEST_SCHEDULE_ID);
        for (ContestPhase schedulePhase : phasesOfContestSchedule) {

            Long phaseTypeIdOfSchedulePhase = schedulePhase.getContestPhaseType();
            Long phaseIdOfExistingPhaseWithSchedulePhaseType = phaseTypeIdToPhaseIdMapForExistingContestPhases.get(phaseTypeIdOfSchedulePhase);

            if (phaseIdOfExistingPhaseWithSchedulePhaseType == null) {
                removeContestPhases(newPhasesForContest);
                throw new PortalException("Can't map new contestPhaseTypeId: " + phaseTypeIdOfSchedulePhase + " to any of the existing contestPhaseTypeIds.");
            }
            ContestPhase existingPhase = ContestPhaseLocalServiceUtil.getContestPhase(phaseIdOfExistingPhaseWithSchedulePhaseType);
            updateExistingContestPhaseAccordingToContestSchedulePhase(existingPhase, schedulePhase);
            existingPhasesOfContest.remove(existingPhase);
        }

        removeRemainingUnusedContestPhases(existingPhasesOfContest);
    }

    public void updateContestPhasesOfContestAccordingToUpdatedContestSchedule(Contest contest) throws SystemException, PortalException {

        List<ContestPhase> newPhasesForContest = new ArrayList<>();
        List<ContestPhase> existingPhasesOfContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        Map<Long, Long> phaseTypeIdToPhaseIdMapForExistingContestPhases = new LinkedHashMap<>();
        populatePhaseTypeIdToPhaseIdMap(contest, phaseTypeIdToPhaseIdMapForExistingContestPhases);

        for (ContestPhaseBean schedulePhase : schedulePhases) {

            Long phaseTypeIdOfSchedulePhase = schedulePhase.getContestPhaseTypeOld();
            Long phaseIdOfExistingPhaseWithSchedulePhaseType = phaseTypeIdToPhaseIdMapForExistingContestPhases.get(phaseTypeIdOfSchedulePhase);

            if (phaseIdOfExistingPhaseWithSchedulePhaseType == null) {
                removeContestPhases(newPhasesForContest);
                throw new PortalException("Can't map new contestPhaseTypeId: " + phaseTypeIdOfSchedulePhase + " to any of the existing contestPhaseTypeIds.");
            }
            if (!schedulePhase.isContestPhaseDeleted()) {
                ContestPhase existingPhase = ContestPhaseLocalServiceUtil.getContestPhase(phaseIdOfExistingPhaseWithSchedulePhaseType);
                updateExistingContestPhaseAccordingToContestSchedulePhase(existingPhase, schedulePhase.getContestPhase());
                existingPhasesOfContest.remove(existingPhase);
            }
        }

        removeRemainingUnusedContestPhases(existingPhasesOfContest);
    }

    private static void removeRemainingUnusedContestPhases(List<ContestPhase> contestPhases) throws SystemException {
        removeContestPhases(contestPhases);
    }

    private static void updateExistingContestPhaseAccordingToContestSchedulePhase(ContestPhase existingContestPhase, ContestPhase contestSchedulePhase) throws SystemException {
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

    public static void deleteProposal2PhasesWithContestPhaseId(Long contestPhaseId) throws SystemException {
        List<Proposal2Phase> proposal2Phases = Proposal2PhaseLocalServiceUtil.getByContestPhaseId(contestPhaseId);

        for (Proposal2Phase proposal2Phase : proposal2Phases) {
            Proposal2PhaseLocalServiceUtil.deleteProposal2Phase(proposal2Phase);
        }
    }

    public static void deleteProposalRatingsWithContestPhaseId(Long contestPhaseId) throws SystemException {
        List<ProposalRating> proposalRatings = new ArrayList<>(); // ProposalRatingLocalServiceUtil.getRatingsForContestPhase(contestPhaseId);

        for (ProposalRating proposalRating : proposalRatings) {
            ProposalRatingLocalServiceUtil.deleteProposalRating(proposalRating);
        }
    }

    public static void createContestPhasesAccordingToContestScheduleAndRemoveExistingPhases(Contest contest, Long newScheduleTemplateId) throws SystemException {
        removeExistingContestPhases(contest);
        List<ContestPhase> contestSchedulePhases = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(newScheduleTemplateId);
        for (ContestPhase contestSchedulePhase : contestSchedulePhases) {
            createContestPhaseFromExistingContestPhaseWithContestId(contestSchedulePhase, contest.getContestPK());
        }
    }

    private static Boolean isUpdateSchedulePhaseType(ContestPhaseBean contestSchedulePhase) {
        return !(contestSchedulePhase.getContestPhaseTypeOld().equals(0L) &&
                contestSchedulePhase.getContestPhaseTypeOld().equals(contestSchedulePhase.getContestPhaseType()));
    }

    private static void createContestPhaseFromExistingContestPhaseWithContestId(ContestPhaseBean contestSchedulePhase, Long contestId) throws SystemException, PortalException {
        ContestPhase contestPhase = contestSchedulePhase.getContestPhase();
        if (isUpdateSchedulePhaseType(contestSchedulePhase)) {
            contestPhase.setContestPhaseType(contestSchedulePhase.getContestPhaseTypeOld());
        }
        ContestPhase newContestPhase = ContestPhaseLocalServiceUtil.createFromContestPhase(contestPhase);
        newContestPhase.setContestPK(contestId);
        newContestPhase.persist();
    }

    private static void createContestPhaseFromExistingContestPhaseWithContestId(ContestPhase contestSchedulePhase, Long contestId) throws SystemException {

        ContestPhase newContestPhase = ContestPhaseLocalServiceUtil.createFromContestPhase(contestSchedulePhase);
        newContestPhase.setContestPK(contestId);
        newContestPhase.persist();
    }

    private static void populatePhaseTypeIdToPhaseIdMap(Contest contest, Map<Long, Long> contestPhaseTypeIdPhaseIdMap) throws SystemException {
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        for (ContestPhase contestPhase : contestPhases) {
            contestPhaseTypeIdPhaseIdMap.put(contestPhase.getContestPhaseType(), contestPhase.getPrimaryKey());
        }
    }

    private static void removeExistingContestPhases(Contest contest) throws SystemException {
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        for (ContestPhase contestPhase : contestPhases) {
            ContestPhaseLocalServiceUtil.deleteContestPhase(contestPhase);
        }
    }

    private static void removeContestPhases(List<ContestPhase> contestPhases) throws SystemException {
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

    public static void deleteContestSchedule(Long scheduleId) throws SystemException, PortalException {
        boolean isContestScheduleUsed = ContestScheduleLocalServiceUtil.isContestScheduleUsed(scheduleId);
        if (!isContestScheduleUsed) {
            removeContestSchedulePhases(scheduleId);
            removeContestPhaseOfContestThatAreUsingSchedule(scheduleId);
            removeContestSchedule(scheduleId);
        } else {
            throw new PortalException("Contest schedule used by contests and can not be deleted!");
        }
    }

    private static void removeContestSchedulePhases(Long scheduleId) throws SystemException {
        List<ContestPhase> contestSchedulePhases = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(scheduleId);
        removeContestPhases(contestSchedulePhases);
    }

    private static void removeContestPhaseOfContestThatAreUsingSchedule(Long scheduleId) throws SystemException {
        List<Contest> contestsUsingSchedule = ContestLocalServiceUtil.getContestsByContestScheduleId(scheduleId);
        for (Contest contestUsingSchedule : contestsUsingSchedule) {
            List<ContestPhase> contestSchedulePhases =
                    ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(scheduleId, contestUsingSchedule.getContestPK());
            removeContestPhases(contestSchedulePhases);
        }
    }

    private static void removeContestSchedule(Long scheduleId) throws SystemException, PortalException {
        ContestScheduleLocalServiceUtil.deleteContestSchedule(scheduleId);
    }
}
