package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.view.pages.contestmanagement.beans.ContestPhaseBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.xcolab.view.pages.contestmanagement.beans.ContestPhaseBean.CREATE_CONTEST_PHASE_PK;

public class ContestScheduleBean {

    private List<ContestPhaseBean> schedulePhases;
    private ContestSchedule contestSchedule;
    private List<Contest> contestsUsingSelectedSchedule;
    private Boolean createNew = false;

    public ContestScheduleBean() {
        schedulePhases = new ArrayList<>();
        contestsUsingSelectedSchedule = new ArrayList<>();
    }

    public ContestScheduleBean(Long scheduleId) {
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
        wrappedContestsUsingSchedule.addAll(contestsUsingSchedule);
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

    public boolean isUsedInNonEmptyContest() {
        return contestSchedule.isUsedInNonEmptyContest();
    }

    public void setPhaseEndDates() {
        ContestPhaseBean prevContestPhase = null;
        for (ContestPhaseBean contestPhase : schedulePhases) {
            if (contestPhase.isContestPhaseDeleted()) {
                continue;
            }
            if (prevContestPhase != null) {
                prevContestPhase.setPhaseEndDate(contestPhase.getPhaseStartDate());
            }
            prevContestPhase = contestPhase;
        }
    }

    public boolean isValidSchedule() {
        boolean isValid = true;
        ContestPhaseBean prevContestPhase = null;
        for (ContestPhaseBean contestPhase : schedulePhases) {
            if (contestPhase.isContestPhaseDeleted()) {
                continue;
            }
            if (prevContestPhase != null) {
                Date prevStartDate = prevContestPhase.getPhaseStartDate();
                Date prevEndDate = prevContestPhase.getPhaseEndDate();
                Date curStartDate = contestPhase.getPhaseStartDate();

                if (prevStartDate != null && prevEndDate != null && curStartDate != null) {
                    isValid &= prevStartDate.before(curStartDate);
                    isValid &= prevEndDate.equals(curStartDate);
                } else {
                    isValid = false;
                }
            }
            prevContestPhase = contestPhase;
        }
        return isValid;
    }

    public boolean areContestsCompatibleWithSchedule() {
        List<ContestPhase> schedulePhases = getSchedulePhases().stream()
                .map(ContestPhaseBean::getContestPhase)
                .collect(Collectors.toList());

        return ContestClientUtil.getContestsByContestScheduleId(getScheduleId())
                .stream()
                .allMatch(contest -> contest.isCompatibleWithSchedulePhases(schedulePhases));
    }

    public void persist() {
        removeEmptyContestPhases();
        if (createNew) {
            createNewScheduleFromExistingSchedule();
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
            contest.changeScheduleTo(contestScheduleId);
        }

        if (!contestsUsingScheduleId.isEmpty()) {
            ServiceRequestUtils.clearCache(CacheName.CONTEST_DETAILS);
        }
    }
}
