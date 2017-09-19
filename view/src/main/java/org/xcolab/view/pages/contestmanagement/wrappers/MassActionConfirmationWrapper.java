package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassAction;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassActions;
import org.xcolab.view.pages.contestmanagement.entities.massactions.MassActionDataWrapper;

import java.util.ArrayList;
import java.util.List;

public class MassActionConfirmationWrapper implements MassActionDataWrapper {

    private List<Contest> contestWrappers;
    private List<Long> contestIds;
    private List<Boolean> selectedContest;
    private String selectedMassActionName;
    private Integer itemCount;
    private Long memberId;

    public MassActionConfirmationWrapper() {
        this.selectedContest = new ArrayList<>();
        this.contestWrappers = new ArrayList<>();
        this.contestIds = new ArrayList<>();
    }

    public MassActionConfirmationWrapper(List<Long> contestIds,
            ContestMassActions selectedMassActionWrapper) {
        this.selectedContest = new ArrayList<>();
        this.contestWrappers = new ArrayList<>();
        this.selectedMassActionName = selectedMassActionWrapper.name();
        this.itemCount = contestIds.size();
        this.contestIds = contestIds;
        populateValidContestWrapper(contestIds);
    }

    private void populateValidContestWrapper(List<Long> contestIds) {
        for (long contestId : contestIds) {
            try {
                Contest contest = ContestClientUtil.getContest(contestId);
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

    public List<Contest> getContestWrappers() {
        return contestWrappers;
    }

    public void setContestWrappers(List<Contest> contestWrappers) {
        this.contestWrappers = contestWrappers;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public String getSelectedMassActionName() {
        return selectedMassActionName;
    }

    public void setSelectedMassActionName(String selectedMassActionName) {
        this.selectedMassActionName = selectedMassActionName;
    }

    @Override
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public ContestMassAction getSelectedMassAction() {
        return ContestMassActions.valueOf(selectedMassActionName).getAction();
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
