package org.xcolab.portlets.contestmanagement.wrappers;


import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
    private static final Long DEFAULT_CONTEST_ID_FOR_SCHEDULE = 0L;

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
        } catch (Exception e){
            _log.info("Could not create ContestScheduleWrapper for scheduleId: " + scheduleId, e);
        }
    }


    private void initContestPhases(Long scheduleId) throws Exception{
        schedulePhases = new ArrayList<>();
        List<ContestPhase> contestPhases =
                ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(scheduleId, DEFAULT_CONTEST_ID_FOR_SCHEDULE);
        for(ContestPhase contestPhase : contestPhases) {
            schedulePhases.add(new ContestPhaseBean(contestPhase));
        }
        addDummySchedulePhase();
    }

    private void initContestSchedule(Long scheduleId){
        try {
            if (scheduleId != null) {
                contestSchedule = ContestScheduleLocalServiceUtil.getContestSchedule(scheduleId);
            } else {
                List<ContestSchedule> contestScheduleList = ContestScheduleLocalServiceUtil.getContestSchedules(0, Integer.MAX_VALUE);
                contestSchedule = contestScheduleList.get(0);
            }
        } catch (Exception e){

        }

    }
    private void addDummySchedulePhase() throws Exception{
        Long dummyContestPhaseId = CounterLocalServiceUtil.increment(ContestPhase.class.getName());
        ContestPhase dummyContestPhase = ContestPhaseLocalServiceUtil.createContestPhase(dummyContestPhaseId);
        ContestPhaseBean dummyContestPhaseBean = new ContestPhaseBean(dummyContestPhase);
        dummyContestPhaseBean.setContestPK(DEFAULT_CONTEST_ID_FOR_SCHEDULE);
        dummyContestPhaseBean.setContestScheduleId(getScheduleId());
        dummyContestPhaseBean.setContestPhasePK(ContestPhaseBean.CREATE_PHASE_CONTEST_PK);
        dummyContestPhaseBean.setContestSchedulePK(ContestPhaseBean.CREATE_PHASE_CONTEST_PK);
        schedulePhases.add(dummyContestPhaseBean);
        ContestPhaseLocalServiceUtil.deleteContestPhase(dummyContestPhase);
    }

    private void initContestsUsingSelectedSchedule(Long scheduleId) throws Exception{
        List<Contest> contestsUsingSelectedScheduleList = ContestLocalServiceUtil.getContestsByContestScheduleId(scheduleId);
        contestsUsingSelectedSchedule = new ArrayList<>();
        for(Contest contest : contestsUsingSelectedScheduleList){
            contestsUsingSelectedSchedule.add(new ContestWrapper(contest));
        }
    }

    public List<ContestWrapper> getContestsUsingSelectedSchedule() {
        return contestsUsingSelectedSchedule;
    }

    public Long getScheduleId(){
        return contestSchedule.getId();
    }

    public String getScheduleName(){
        return contestSchedule.getName();
    }

    public void setScheduleId(Long contestScheduleId){
        try{
            initContestSchedule(contestScheduleId);
            initContestsUsingSelectedSchedule(contestScheduleId);
        } catch (Exception e){
        }
    }

    public void setScheduleName(String name){
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

    public void persist() throws Exception{
        removeEmptyContestPhases();
        if(createNew){
            createNewScheduleFromExistingSchedule();
        } else {
            persistSchedule();
        }
    }

    private void removeEmptyContestPhases() throws Exception{
        List<ContestPhaseBean> emptyContestPhases = new ArrayList<>();
        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            boolean contestPhaseIsEmpty = (contestPhaseBean.getContestPhasePK() == null);
            if(contestPhaseIsEmpty){
                emptyContestPhases.add(contestPhaseBean);
            }
        }
        for(ContestPhaseBean emptyContestPhaseBean : emptyContestPhases) {
            schedulePhases.remove(emptyContestPhaseBean);
        }

    }

    private void createNewScheduleFromExistingSchedule() throws Exception{
        Long existingContestScheduleId = contestSchedule.getId();
        Long newContestScheduleId = CounterLocalServiceUtil.increment(ContestSchedule.class.getName());

        contestSchedule.setId(newContestScheduleId);
        ContestSchedule newContestSchedule = ContestScheduleLocalServiceUtil.addContestSchedule(contestSchedule);
        newContestSchedule.persist();

        contestSchedule.setId(existingContestScheduleId);
        duplicateContestPhasesFromExistingScheduleToNewSchedule(existingContestScheduleId, newContestScheduleId);
        contestSchedule = newContestSchedule;
    }

    private static void duplicateContestPhasesFromExistingScheduleToNewSchedule(Long existingContestScheduleId, Long newContestScheduleId) throws Exception{
        List<ContestPhase> contestPhasesForExistingSchedule = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(existingContestScheduleId);
        for(ContestPhase contestPhase : contestPhasesForExistingSchedule){
            contestPhase.setContestScheduleId(newContestScheduleId);
            createContestPhaseFromExistingContestPhaseWithContestId(contestPhase, DEFAULT_CONTEST_ID_FOR_SCHEDULE);
        }
    }

    private void persistSchedule() throws Exception{

        makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(contestSchedule.getId());

        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            contestPhaseBean.persist();
            updateAssociatedContestPhaseDates(contestPhaseBean);
        }
        contestSchedule.persist();
    }

    private static void makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(Long contestScheduleId) throws Exception{
        List<Contest> contestsUsingScheduleId = ContestLocalServiceUtil.getContestsByContestScheduleId(contestScheduleId);
        for(Contest contest : contestsUsingScheduleId){
            createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(contest, contestScheduleId);
            updateContestPhasesAccordingToContestSchedule(contest, contestScheduleId);
        }
    }


    private static void createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(Contest contest, Long contestScheduleId) throws Exception{

        List<ContestPhase> existingContestPhasesForContest = ContestPhaseLocalServiceUtil.getPhasesForContest(contest);
        List<ContestPhase> contestPhasesOfContestSchedule = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(contestScheduleId);

        for(ContestPhase contestPhaseOfContestSchedule : contestPhasesOfContestSchedule){
            Long contestPhaseType = contestPhaseOfContestSchedule.getContestPhaseType();
            if(!isContestPhaseTypeInContestPhaseList(existingContestPhasesForContest, contestPhaseType, contestScheduleId)) {
                createContestPhaseFromExistingContestPhaseWithContestId(contestPhaseOfContestSchedule, contest.getContestPK());
            }
        }
    }


    private static boolean isContestPhaseTypeInContestPhaseList(List<ContestPhase> contestPhases, Long contestPhaseTyp, Long contestScheduleId){
        for(ContestPhase existingContestPhaseForContest : contestPhases){
            if(existingContestPhaseForContest.getContestPhaseType() == contestPhaseTyp &&
                    existingContestPhaseForContest.getContestScheduleId() == contestScheduleId) return true;
        }
        return false;
    }

    private void updateAssociatedContestPhaseDates(ContestPhaseBean contestPhaseBean) throws Exception{
        Date updatedPhaseStartDate = contestPhaseBean.getPhaseStartDate();
        Date updatedPhaseEndDate = contestPhaseBean.getPhaseEndDate();
        Long contestScheduleId = contestPhaseBean.getContestScheduleId();
        Long contestPhaseType = contestPhaseBean.getContestPhaseType();

        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndPhaseType(contestScheduleId, contestPhaseType);
        for(ContestPhase contestPhase : contestPhases){
            contestPhase.setPhaseStartDate(updatedPhaseStartDate);
            contestPhase.setPhaseEndDate(updatedPhaseEndDate);
            contestPhase.persist();
            ContestPhaseLocalServiceUtil.updateContestPhase(contestPhase);
        }

    }

    public static List<LabelValue> getAllScheduleTemplateSelectionItems(){
        List<LabelValue> selectItems = new ArrayList<>();
        try {
            ContestCreatorUtil.insertSeedDataToContestScheduleTableIfNotAvailable();
            for (ContestSchedule scheduleTemplate : ContestScheduleLocalServiceUtil.getContestSchedules(0, Integer.MAX_VALUE)) {
                selectItems.add(new LabelValue(scheduleTemplate.getId(), scheduleTemplate.getName()));
            }
        } catch (Exception e){
        }
        return selectItems;
    }

    public static List<LabelValue> getScheduleTemplateSelectionItems(long existingScheduleId, boolean showOnlySchedulesWithSamePhases){
        List<LabelValue> selectItems = new ArrayList<>();
        if(!showOnlySchedulesWithSamePhases){
            selectItems = getAllScheduleTemplateSelectionItems();
        } else {
            try {
                ContestCreatorUtil.insertSeedDataToContestScheduleTableIfNotAvailable();
                for (ContestSchedule scheduleTemplate : ContestScheduleLocalServiceUtil.getContestSchedules(0, Integer.MAX_VALUE)) {
                    if(haveContestSchedulesSamePhases(existingScheduleId, scheduleTemplate.getId())){
                        selectItems.add(new LabelValue(scheduleTemplate.getId(), scheduleTemplate.getName()));
                    }
                }
            } catch (Exception e) {
            }
        }
        return selectItems;
    }

    private static List<Long> getContestSchedulePhaseTypes(Long existingContestScheduleId) throws Exception{
        List<Long> contestSchedulePhaseTypes = new ArrayList<>();
        List<ContestPhase> contestPhasesForContestSchedule = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(existingContestScheduleId, 0);
        for(ContestPhase contestPhase : contestPhasesForContestSchedule) {
            contestSchedulePhaseTypes.add(contestPhase.getContestPhaseType());
        }
        return contestSchedulePhaseTypes;
    }

    private static boolean haveContestSchedulesSamePhases(Long contestScheduleId1, Long contestScheduleId2) throws Exception{
        List<Long> contestSchedule1PhaseTypes = getContestSchedulePhaseTypes(contestScheduleId1);
        List<Long> contestSchedule2PhaseTypes = getContestSchedulePhaseTypes(contestScheduleId2);
        return areContestSchedulesPhasesEqual(contestSchedule1PhaseTypes, contestSchedule2PhaseTypes);
    }

    private static boolean areContestSchedulesPhasesEqual(List<Long> referenceContestSchedulePhases,List<Long> testContestSchedulePhases) {
        boolean haveContestSchedulesSamePhases = true;
        for (int i = 0; i < referenceContestSchedulePhases.size(); i++) {
            if(!referenceContestSchedulePhases.get(i).equals(testContestSchedulePhases.get(i))){
                haveContestSchedulesSamePhases = false;
                break;
            }
        }
        return haveContestSchedulesSamePhases;
    }


    public static void updateContestPhasesAccordingToContestSchedule(Contest contest, Long scheduleTemplateId) throws Exception{

        List<ContestPhase> oldContestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        List<ContestPhase> newContestPhases = new ArrayList<>();

        //Map<Long, Long> oldContestPhaseIdToNewContestPhaseIdMap = new LinkedHashMap<>();
        Map<Long, Long> oldContestPhaseTypeIdPhaseIdMap = new LinkedHashMap<>();
        populateContestPhaseTypeIdPhaseId(contest, oldContestPhaseTypeIdPhaseIdMap);

        List<ContestPhase> contestSchedulePhases = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(scheduleTemplateId, 0L);

        for(ContestPhase contestPhase : contestSchedulePhases){

            Long contestPhaseTypeId = contestPhase.getContestPhaseType();
            Long oldContestPhaseId = oldContestPhaseTypeIdPhaseIdMap.get(contestPhaseTypeId);

            if(oldContestPhaseId == null){
                removeContestPhases(newContestPhases);
                throw new Exception("Can't map new contestPhaseTypeId: " + contestPhaseTypeId + " to any of the old contestPhaseTypeIds." );
            }

            ContestPhase contestPhaseNew = ContestPhaseLocalServiceUtil.
                    createContestPhase(CounterLocalServiceUtil.increment(ContestPhase.class.getName()));

            contestPhase.setContestPK(contest.getContestPK());
            contestPhase.setPrimaryKey(contestPhaseNew.getPrimaryKey());
            ContestPhaseLocalServiceUtil.addContestPhase(contestPhase);
            newContestPhases.add(contestPhase);

            Long newContestPhaseId = contestPhaseNew.getContestPhasePK();
            updateContestPhaseIdForAllProposal2PhaseMatchingContestPhaseId(oldContestPhaseId, newContestPhaseId);

            //oldContestPhaseIdToNewContestPhaseIdMap.put(oldContestPhaseId, newContestPhaseId);
        }

        /*
        List<Proposal> proposalInThisContest = ProposalLocalServiceUtil.getProposalsInContest(contest.getContestPK());

        for(Proposal proposal : proposalInThisContest){
            Long proposalId = proposal.getProposalId();
            List<Proposal2Phase> proposal2Phases = Proposal2PhaseLocalServiceUtil.getByProposalId(proposalId);
            for(Proposal2Phase proposal2Phase : proposal2Phases){
                Long oldContestPhaseId = proposal2Phase.getContestPhaseId();
                Long newContestPhaseId = oldContestPhaseIdToNewContestPhaseIdMap.get(oldContestPhaseId);
                if(newContestPhaseId != null) {
                    proposal2Phase.setContestPhaseId(newContestPhaseId);
                    Proposal2PhaseLocalServiceUtil.updateProposal2Phase(proposal2Phase);
                }
            }
        }
        */

        removeContestPhases(oldContestPhases);
    }

    public static void updateContestPhaseIdForAllProposal2PhaseMatchingContestPhaseId
            (Long oldContestPhaseId, Long newContestPhaseId) throws Exception{
        List<Proposal2Phase> proposal2Phases = Proposal2PhaseLocalServiceUtil.getByContestPhaseId(oldContestPhaseId);
        for(Proposal2Phase proposal2Phase : proposal2Phases){
            if(newContestPhaseId != null) {
                proposal2Phase.setContestPhaseId(newContestPhaseId);
                Proposal2PhaseLocalServiceUtil.updateProposal2Phase(proposal2Phase);
            }
        }
    }

    public static void createContestPhasesAccordingToContestScheduleAndRemoveExistingPhases(Contest contest, Long newScheduleTemplateId) throws Exception {
        removeExistingContestPhases(contest);
        List<ContestPhase> contestSchedulePhases = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(newScheduleTemplateId);
        for(ContestPhase contestSchedulePhase : contestSchedulePhases){
            createContestPhaseFromExistingContestPhaseWithContestId(contestSchedulePhase, contest.getContestPK());
        }
    }

    private static void createContestPhaseFromExistingContestPhaseWithContestId(ContestPhase contestSchedulePhase, Long contestId) throws Exception{
        ContestPhase newContestPhase = ContestPhaseLocalServiceUtil.createFromContestPhase(contestSchedulePhase);
        newContestPhase.setContestPK(contestId);
        newContestPhase.persist();
    }

    private static void populateContestPhaseTypeIdPhaseId(Contest contest, Map<Long, Long> contestPhaseTypeIdPhaseIdMap) throws Exception{
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        for(ContestPhase contestPhase : contestPhases){
            contestPhaseTypeIdPhaseIdMap.put(contestPhase.getContestPhaseType(), contestPhase.getPrimaryKey());
        }
    }

    private static void removeExistingContestPhases(Contest contest) throws Exception{
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contest.getContestPK());
        for(ContestPhase contestPhase : contestPhases){
            ContestPhaseLocalServiceUtil.deleteContestPhase(contestPhase);
        }
    }

    private static void removeContestPhases( List<ContestPhase> contestPhases) throws Exception{
        for(ContestPhase contestPhase : contestPhases){
            ContestPhaseLocalServiceUtil.deleteContestPhase(contestPhase);
        }
    }

    public static void deleteContestSchedule(Long scheduleId) throws Exception{
        removeContestSchedulePhases(scheduleId);
        removeContestPhaseOfContestThatAreUsingSchedule(scheduleId);
        removeContestSchedule(scheduleId);
    }

    private static void removeContestSchedulePhases(Long scheduleId) throws Exception{
        List<ContestPhase> contestSchedulePhases = ContestPhaseLocalServiceUtil.getPhasesForContestScheduleId(scheduleId);
        removeContestPhases(contestSchedulePhases);
    }

    private static void removeContestPhaseOfContestThatAreUsingSchedule(Long scheduleId) throws Exception{
        List<Contest> contestsUsingSchedule = ContestLocalServiceUtil.getContestsByContestScheduleId(scheduleId);
        for(Contest contestUsingSchedule : contestsUsingSchedule){
            List<ContestPhase>  contestSchedulePhases =
                    ContestPhaseLocalServiceUtil.getPhasesForContestScheduleIdAndContest(scheduleId, contestUsingSchedule.getContestPK());
            removeContestPhases(contestSchedulePhases);
        }
    }

    private static void removeContestSchedule(Long scheduleId) throws Exception{
        ContestScheduleLocalServiceUtil.deleteContestSchedule(scheduleId);
    }

}
