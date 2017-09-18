package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.html.LabelValue;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassActions;
import org.xcolab.view.pages.contestmanagement.entities.massactions.MassActionDataWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ContestOverviewWrapper implements MassActionDataWrapper {

    private List<Contest> contestWrappers;
    private Long selectedMassAction;
    private List<Boolean> selectedContest;
    private List<Boolean> subscribedToContest;
    private MassMessageBean massMessageBean;
    private ContestFlagTextToolTipBean contestFlagTextToolTipBean;
    private ContestModelSettingsBean contestModelSettingsBean;
    private Long memberId;

    public ContestOverviewWrapper() {
        initLists();
        populateContestWrappersAndSelectedContestList();
    }

    private void initLists() {
        contestWrappers = new ArrayList<>();
        selectedContest = new ArrayList<>();
        subscribedToContest = new ArrayList<>();
        contestFlagTextToolTipBean = new ContestFlagTextToolTipBean();
        contestModelSettingsBean = new ContestModelSettingsBean();
        massMessageBean = new MassMessageBean();
    }

    private void populateContestWrappersAndSelectedContestList() {
        List<Contest> contests = ContestClientUtil.getAllContests();
        for (Contest contest : contests) {
            if (contest.getIsSharedContestInForeignColab()) {
                try {
                    RestService contestService = new RefreshingRestService(CoLabService.CONTEST,
                            ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE);

                    Contest foreignContest = ContestClient.fromService(contestService)
                            .getContest(contest.getContestPK());
                    foreignContest.setUpForeignContestVisualConfigsFromLocal(contest);

                    contestWrappers.add(foreignContest);

                } catch (ContestNotFoundException notFound) {
                }
            } else {
                contestWrappers.add((contest));
            }
            selectedContest.add(false);
        }
    }

    public ContestOverviewWrapper(HttpServletRequest request) {
        initLists();
        populateContestWrappersAndSelectedContestList();
        populateSubscribedToContestList(MemberAuthUtil.getMemberId(request));
    }

    private void populateSubscribedToContestList(long memberId) {
        for (Contest contestWrapper : contestWrappers) {
            Boolean isUserSubscribedToContest = ContestClientUtil
                    .isMemberSubscribedToContest(contestWrapper.getContestPK(), memberId);
            subscribedToContest.add(isUserSubscribedToContest);
        }
    }

    public List<Contest> getContestWrappers() {
        return contestWrappers;
    }

    public void setContestWrappers(List<Contest> contestWrappers) {
        this.contestWrappers = contestWrappers;
    }

    public Long getSelectedMassAction() {
        return selectedMassAction;
    }

    public void setSelectedMassAction(Long selectedMassAction) {
        this.selectedMassAction = selectedMassAction;
    }

    public List<Boolean> getSelectedContest() {
        return selectedContest;
    }

    public void setSelectedContest(List<Boolean> selectedContest) {
        this.selectedContest = selectedContest;
    }

    @Override
    public MassMessageBean getMassMessageBean() {
        return massMessageBean;
    }

    public void setMassMessageBean(MassMessageBean massMessageBean) {
        this.massMessageBean = massMessageBean;
    }

    @Override
    public ContestModelSettingsBean getContestModelSettingsBean() {
        return contestModelSettingsBean;
    }

    public void setContestModelSettingsBean(ContestModelSettingsBean contestModelSettingsBean) {
        this.contestModelSettingsBean = contestModelSettingsBean;
    }

    @Override
    public ContestFlagTextToolTipBean getContestFlagTextToolTipBean() {
        return contestFlagTextToolTipBean;
    }

    public void setContestFlagTextToolTipBean(
            ContestFlagTextToolTipBean contestFlagTextToolTipBean) {
        this.contestFlagTextToolTipBean = contestFlagTextToolTipBean;
    }

    @Override
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public List<Boolean> getSubscribedToContest() {
        return subscribedToContest;
    }

    public int getMassActionIndex(String massAction) {
        return ContestMassActions.valueOf(massAction).ordinal();
    }

    public List<LabelValue> getFlagOptions() {
        return ContestFlagTextToolTipBean.getFlagOptions();
    }

    public List<LabelValue> getModelIds() {
        return ContestModelSettingsBean.getAllModelIds();
    }

    public List<Long> getSelectedContestIds() {
        List<Long> contestIds = new ArrayList<>();
        for (int i = 0; i < contestWrappers.size(); i++) {
            Contest contestWrapper = contestWrappers.get(i);
            if (selectedContest.get(i)) {
                contestIds.add(contestWrapper.getContestPK());
            }
        }
        return contestIds;
    }
}
