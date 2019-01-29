package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.view.pages.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassActions;
import org.xcolab.view.pages.contestmanagement.entities.massactions.MassActionDataWrapper;

import java.util.ArrayList;
import java.util.List;

public class MassActionConfirmationWrapper implements MassActionDataWrapper {

    private List<ContestWrapper> contestWrappers;
    private List<Long> contestIds;
    private List<Boolean> selectedContest;
    private ContestMassActions selectedMassAction;
    private Integer itemCount;
    private Long userId;

    public MassActionConfirmationWrapper() {
        this.selectedContest = new ArrayList<>();
        this.contestWrappers = new ArrayList<>();
        this.contestIds = new ArrayList<>();
    }

    public MassActionConfirmationWrapper(List<Long> contestIds,
            ContestMassActions selectedMassAction) {
        this.selectedContest = new ArrayList<>();
        this.contestWrappers = new ArrayList<>();
        this.selectedMassAction= selectedMassAction;
        this.itemCount = contestIds.size();
        this.contestIds = contestIds;
        populateValidContestWrapper(contestIds);
    }

    private void populateValidContestWrapper(List<Long> contestIds) {
        for (long contestId : contestIds) {
            try {
                ContestWrapper contest = StaticContestContext.getContestClient()
                        .getContest(contestId);
                this.contestWrappers.add(contest);
                this.selectedContest.add(false);
            } catch (ContestNotFoundException ignored) {
                // Contest was removed already
            }
        }
    }

    public List<Boolean> getSelectedContest() {
        return selectedContest;
    }

    public void setSelectedContest(List<Boolean> selectedContest) {
        this.selectedContest = selectedContest;
    }

    public List<Long> getContestIds() {
        return contestIds;
    }

    public void setContestIds(List<Long> contestIds) {
        this.contestIds = contestIds;
    }

    public List<ContestWrapper> getContestWrappers() {
        return contestWrappers;
    }

    public void setContestWrappers(List<ContestWrapper> contestWrappers) {
        this.contestWrappers = contestWrappers;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public ContestMassActions getSelectedMassAction() {
        return selectedMassAction;
    }

    public void setSelectedMassAction(ContestMassActions selectedMassAction) {
        this.selectedMassAction = selectedMassAction;
    }

    @Override
    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getSelectedContestIds() {
        List<Long> selectedContestIds = new ArrayList<>();
        for (int i = 0; i < selectedContest.size(); i++) {
            if (selectedContest.get(i) != null && selectedContest.get(i)) {
                selectedContestIds.add(contestIds.get(i));
            }
        }
        return selectedContestIds;
    }

    @Override
    public MassMessageBean getMassMessageBean() {
        return null;
    }

    @Override
    public ContestModelSettingsBean getContestModelSettingsBean() {
        return null;
    }

    @Override
    public ContestFlagTextToolTipBean getContestFlagTextToolTipBean() {
        return null;
    }
}
