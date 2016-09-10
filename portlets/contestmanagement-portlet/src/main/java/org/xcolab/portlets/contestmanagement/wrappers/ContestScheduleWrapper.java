package org.xcolab.portlets.contestmanagement.wrappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.enums.ColabConstants;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.portlets.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.portlets.contestmanagement.utils.ContestScheduleLifecycleUtil;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.wrappers.BaseContestWrapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContestScheduleWrapper {

    private final static Logger _log = LoggerFactory.getLogger(ContestScheduleWrapper.class);

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

    public void setScheduleId(Long contestScheduleId) {
        initContestSchedule(contestScheduleId);
        initContestsUsingSelectedSchedule(contestScheduleId);
    }

    public String getScheduleName() {
        return contestSchedule.getName();
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
        ContestScheduleLifecycleUtil.duplicateContestPhasesFromExistingScheduleToNewSchedule(existingContestScheduleId,
                newContestSchedule.getId_());
        contestSchedule = newContestSchedule;
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

    private void updateContestsUsingSchedule(Long contestScheduleId) {

        List<Contest> contestsUsingScheduleId = ContestClient
                .getContestsByContestScheduleId(contestScheduleId);
        for (Contest contest : contestsUsingScheduleId) {
            updateContestPhasesOfContestAccordingToUpdatedContestSchedule(contest);
        }

    }

    private void makeSureThatAllContestsUsingScheduleIdHaveCorrectContestPhases(
            Long contestScheduleId) {

        List<Contest> contestsUsingScheduleId = ContestClient
                .getContestsByContestScheduleId(contestScheduleId);
        for (Contest contest : contestsUsingScheduleId) {
            createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(contest,
                    contestScheduleId);
        }

    }

    private void createMissingContestPhasesIfContestDoesNotHaveSamePhasesAsSchedule(Contest contest,
            Long contestScheduleId) {

        List<ContestPhase> existingContestPhasesForContest = ContestClient
                .getAllContestPhases(contest.getContestPK());
        for (ContestPhaseBean contestPhaseOfContestSchedule : schedulePhases) {
            Long contestPhaseType = contestPhaseOfContestSchedule.getContestPhaseType();
            if (!ContestScheduleLifecycleUtil.isContestPhaseTypeInContestPhaseList(existingContestPhasesForContest,
                    contestPhaseType, contestScheduleId)) {
                ContestScheduleLifecycleUtil.createContestPhaseFromExistingContestPhaseWithContestId(
                        contestPhaseOfContestSchedule, contest.getContestPK());
            }
        }

    }

    private void updateContestPhasesOfContestAccordingToUpdatedContestSchedule(Contest contest) {

        List<ContestPhase> existingPhasesOfContest = ContestClient
                .getAllContestPhases(contest.getContestPK());
        Map<Long, Long> phaseTypeIdToPhaseIdMapForExistingContestPhases = new LinkedHashMap<>();
        ContestScheduleLifecycleUtil.populatePhaseTypeIdToPhaseIdMap(contest,
                phaseTypeIdToPhaseIdMapForExistingContestPhases);

        List<ContestPhase> newPhasesForContest = new ArrayList<>();
        for (ContestPhaseBean schedulePhase : schedulePhases) {

            Long phaseTypeIdOfSchedulePhase = schedulePhase.getContestPhaseTypeOld();
            Long phaseIdOfExistingPhaseWithSchedulePhaseType = phaseTypeIdToPhaseIdMapForExistingContestPhases
                    .get(phaseTypeIdOfSchedulePhase);

            if (phaseIdOfExistingPhaseWithSchedulePhaseType == null) {
                ContestScheduleLifecycleUtil.removeContestPhases(newPhasesForContest);
                throw new InternalException("Can't map new contestPhaseTypeId: "
                        + phaseTypeIdOfSchedulePhase
                        + " to any of the existing contestPhaseTypeIds.");
            }
            if (!schedulePhase.isContestPhaseDeleted()) {

                ContestPhase existingPhase = ContestClient
                        .getContestPhase(phaseIdOfExistingPhaseWithSchedulePhaseType);
                ContestScheduleLifecycleUtil
                        .updateExistingContestPhaseAccordingToContestSchedulePhase(existingPhase,
                        schedulePhase.getContestPhase());
                existingPhasesOfContest.remove(existingPhase);

            }
        }

        ContestScheduleLifecycleUtil.removeRemainingUnusedContestPhases(existingPhasesOfContest);

    }
}
