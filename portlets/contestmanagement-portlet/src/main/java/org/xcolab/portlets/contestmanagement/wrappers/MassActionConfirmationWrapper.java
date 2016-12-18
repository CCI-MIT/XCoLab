package org.xcolab.portlets.contestmanagement.wrappers;

import com.liferay.portal.kernel.util.Validator;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.portlets.contestmanagement.entities.ContestMassActions;
import org.xcolab.portlets.contestmanagement.utils.MassActionUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 10/23/2015.
 */
public class MassActionConfirmationWrapper {

    private List<Contest> contestWrappers;
    private List<Integer> contestIds;
    private List<Boolean> selectedContest;
    private Integer massActionId;
    private Integer itemCount;

    public MassActionConfirmationWrapper() {
        this.selectedContest = new ArrayList<>();
        this.contestWrappers = new ArrayList<>();
        this.contestIds = new ArrayList<>();
    }

    public MassActionConfirmationWrapper(List<Integer> contestIds, Integer massActionId) {
        this.selectedContest = new ArrayList<>();
        this.contestWrappers = new ArrayList<>();
        this.massActionId = massActionId;
        this.itemCount = contestIds.size();
        this.contestIds = contestIds;
        populateValidContestWrapper(contestIds);
    }

    public List<Boolean> getSelectedContest() {
        return selectedContest;
    }

    public List<Integer> getContestIds() {
        return contestIds;
    }

    public void setContestIds(List<Integer> contestIds) {
        this.contestIds = contestIds;
    }

    public void setSelectedContest(List<Boolean> selectedContest) {
        this.selectedContest = selectedContest;
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

    public Integer getMassActionId() {
        return massActionId;
    }

    public void setMassActionId(Integer massActionId) {
        this.massActionId = massActionId;
    }

    private void populateValidContestWrapper(List<Integer> contestIds) {
        for (Integer contestId : contestIds) {
            try {
                Contest contest = ContestClientUtil.getContest(contestId);
                this.contestWrappers.add(new Contest(contest));
                this.selectedContest.add(false);
            } catch (ContestNotFoundException ignored) {
                // Contest was removed already
            }
        }
    }

    public String getSelectedMassActionTitle() {
        return MassActionUtil.getSelectedMassActionTitle(massActionId.longValue());
    }

    public void invokeMassActionForSelectedContests()
            throws InvocationTargetException, IllegalAccessException {
        List<Long> contestToBeDeleted = new ArrayList<>();
        for (Integer contestId : contestIds) {
            int index = contestIds.indexOf(contestId);
            if (index < selectedContest.size() && Validator.isNotNull(selectedContest.get(index)) && selectedContest
                    .get(index)) {
                contestToBeDeleted.add(contestId.longValue());
            }
        }
        if (massActionId == ContestMassActions.DELETE.ordinal()) {
            final boolean actionConfirmed = true;
            ContestMassActions.values()[massActionId].getMethod().invoke(null, contestToBeDeleted, actionConfirmed, null);
        } else if (massActionId == ContestMassActions.DELETE_WITH_PHASES.ordinal()){
            final boolean actionConfirmed = true;
            ContestMassActions.values()[massActionId].getMethod().invoke(null, contestToBeDeleted, actionConfirmed, null);
        } else {
            throw new IllegalArgumentException(
                        "No action defined for mass action id: " + massActionId);
        }
    }

}
