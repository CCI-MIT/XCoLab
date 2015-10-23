package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import org.xcolab.portlets.contestmanagement.entities.ContestMassActions;
import org.xcolab.portlets.contestmanagement.utils.MassActionUtil;
import org.xcolab.wrapper.ContestWrapper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas on 10/23/2015.
 */
public class MassActionConfirmationWrapper {

    private List<ContestWrapper> contestWrappers;
    private List<Integer> contestIds;
    private List<Boolean> selectedContest;
    private Integer massActionId;
    private Integer itemCount;

    public MassActionConfirmationWrapper() {
        this.selectedContest = new ArrayList<>();
        this.contestWrappers = new ArrayList<>();
        this.contestIds = new ArrayList<>();
    }

    public MassActionConfirmationWrapper(List<Integer> contestIds, Integer massActionId) throws Exception {
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

    public Integer getMassActionId() {
        return massActionId;
    }

    public void setMassActionId(Integer massActionId) {
        this.massActionId = massActionId;
    }

    private void populateValidContestWrapper(List<Integer> contestIds) throws Exception {
        for (Integer contestId : contestIds) {
            try {
                Contest contest = ContestLocalServiceUtil.getContest(contestId);
                this.contestWrappers.add(new ContestWrapper(contest));
                this.selectedContest.add(new Boolean(false));
            } catch (NoSuchContestException e) {
                // Contest was removed already
            }
        }
    }

    public String getSelectedMassActionTitle() throws Exception {
        return MassActionUtil.getSelectedMassActionTitle(massActionId.longValue());
    }

    public void invokeMassActionForSelectedContests() throws Exception {
        List<Long> contestToBeDeleted = new ArrayList<>();
        for (Integer contestId : contestIds) {
            int index = contestIds.indexOf(contestId);
            if (index < selectedContest.size() && Validator.isNotNull(selectedContest.get(index)) && selectedContest.get(index)) {
                contestToBeDeleted.add(contestId.longValue());
            }
        }
        if (massActionId == ContestMassActions.DELETE.ordinal()) {
            Boolean deletePhases = true;
            ContestMassActions.values()[massActionId].getMethod().invoke(null, contestToBeDeleted, deletePhases, null);
        } else {
            throw new Exception("No action defined for mass action id: " + massActionId);
        }
    }


}
