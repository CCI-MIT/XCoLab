package org.xcolab.view.pages.contestmanagement.wrappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.view.pages.contestmanagement.beans.ContestPhaseBean;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleChangeHelper
        .IllegalScheduleChangeException;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.xcolab.view.pages.contestmanagement.beans.ContestPhaseBean
        .CREATE_CONTEST_PHASE_PK;

public class ContestScheduleWrapper {

    private final static Logger _log = LoggerFactory.getLogger(ContestScheduleWrapper.class);

    private List<ContestPhaseBean> schedulePhases;
    private ContestSchedule contestSchedule;
    private List<Contest> contestsUsingSelectedSchedule;
    private Boolean createNew = false;

    public ContestScheduleWrapper() {
        schedulePhases = new ArrayList<>();
        contestsUsingSelectedSchedule = new ArrayList<>();
    }

    public ContestScheduleWrapper(Long scheduleId) {
        contestSchedule = loadContestSchedule(scheduleId);
        schedulePhases = loadContestPhases(scheduleId);
        contestsUsingSelectedSchedule = loadContestsUsingSchedule(scheduleId);
    }

    private ContestSchedule loadContestSchedule(Long scheduleId) {

        if (scheduleId != null) {
            return ContestClientUtil.getContestSchedule(scheduleId);
        } else {
            List<ContestSchedule> contestScheduleList = ContestClientUtil.getAllContestSchedules();
            return contestScheduleList.get(0);
        }
    }

    private List<ContestPhaseBean> loadContestPhases(Long scheduleId) {
        List<ContestPhaseBean> schedulePhaseBeans = new ArrayList<>();
        List<ContestPhase> contestPhases =
                ContestClientUtil.getTemplatePhasesForContestScheduleId(scheduleId);
        for (ContestPhase contestPhase : contestPhases) {
            schedulePhaseBeans.add(new ContestPhaseBean(contestPhase));
        }
        schedulePhaseBeans.add(createDummyContestPhaseBean());
        return schedulePhaseBeans;
    }

    private ContestPhaseBean createDummyContestPhaseBean() {

        ContestPhase dummyContestPhase = new ContestPhase();
        ContestPhaseBean dummyContestPhaseBean = new ContestPhaseBean(dummyContestPhase);
        dummyContestPhaseBean.setContestPK(ContestPhase.SCHEDULE_TEMPLATE_PHASE_CONTEST_ID);
        dummyContestPhaseBean.setContestScheduleId(getScheduleId());
        dummyContestPhaseBean.setContestPhasePK(CREATE_CONTEST_PHASE_PK);
        dummyContestPhaseBean.setContestSchedulePK(ContestPhaseBean.DEFAULT_CONTEST_SCHEDULE);
        dummyContestPhaseBean
                .setContestPhaseAutopromote(ContestPhasePromoteType.DEFAULT.getValue());
        return dummyContestPhaseBean;
    }

    public Long getScheduleId() {
        return contestSchedule.getId_();
    }

    public void setScheduleId(Long contestScheduleId) {
        contestSchedule = loadContestSchedule(contestScheduleId);
        contestsUsingSelectedSchedule = loadContestsUsingSchedule(contestScheduleId);
    }

    private List<Contest> loadContestsUsingSchedule(long scheduleId) {
        List<Contest> wrappedContestsUsingSchedule = new ArrayList<>();
        List<Contest> contestsUsingSchedule =
                ContestClientUtil.getContestsByContestScheduleId(scheduleId);
        for (Contest contest : contestsUsingSchedule) {
            wrappedContestsUsingSchedule.add((contest));
        }
        return wrappedContestsUsingSchedule;
    }

    public List<Contest> getContestsUsingSelectedSchedule() {
        return contestsUsingSelectedSchedule;
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
            //TODO: once we improve the algorithm we don't have to fail here anymore
            List<Contest> contestsUsingScheduleId = ContestClientUtil
                    .getContestsByContestScheduleId(getScheduleId());
            for (Contest contest : contestsUsingScheduleId) {
                if (!ContestScheduleUtil.isBlankContest(contest)) {
                    throw new IllegalScheduleChangeException(contest, getScheduleId());
                }
            }
        }
        persistUpdatedSchedule();
    }

    private void removeEmptyContestPhases() {
        for (Iterator<ContestPhaseBean> iterator = schedulePhases.iterator();
                iterator.hasNext(); ) {
            final ContestPhaseBean contestPhaseBean = iterator.next();
            boolean contestPhaseIsEmpty = (contestPhaseBean.getContestPhasePK() == null);
            if (contestPhaseIsEmpty) {
                iterator.remove();
            }
        }
    }

    private void createNewScheduleFromExistingSchedule() {
        ContestSchedule newContestSchedule = new ContestSchedule();
        newContestSchedule.setBaseScheduleId(contestSchedule.getBaseScheduleId());
        newContestSchedule.setDescription(contestSchedule.getDescription());
        newContestSchedule.setName(contestSchedule.getName());
        newContestSchedule.setStatus(contestSchedule.getStatus());
        newContestSchedule = ContestClientUtil.createContestSchedule(newContestSchedule);
        contestSchedule = newContestSchedule;

        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            contestPhaseBean.setContestPhasePK(CREATE_CONTEST_PHASE_PK);
            contestPhaseBean.setContestScheduleId(contestSchedule.getId_());
        }
    }

    private void persistUpdatedSchedule() {
        updateScheduleContestPhases();
        updateContestsUsingSchedule(contestSchedule.getId_());

        ContestClientUtil.updateContestSchedule(contestSchedule);
    }

    private void updateScheduleContestPhases() {
        for (ContestPhaseBean contestPhaseBean : schedulePhases) {
            contestPhaseBean.persist();
        }
    }

    private void updateContestsUsingSchedule(long contestScheduleId) {

        List<Contest> contestsUsingScheduleId = ContestClientUtil
                .getContestsByContestScheduleId(contestScheduleId);
        for (Contest contest : contestsUsingScheduleId) {
            ContestScheduleUtil.changeScheduleForContest(contest, contestScheduleId);
        }

    }
}
