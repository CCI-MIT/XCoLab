package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.AbstractContest;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.html.LabelValue;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.pages.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassActions;
import org.xcolab.view.pages.contestmanagement.entities.massactions.MassActionDataWrapper;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ContestOverviewWrapper implements MassActionDataWrapper {

    private final Map<Long, Contest> contests = new LinkedHashMap<>();
    private final Map<Long, Boolean> selectedContests = new HashMap<>();
    private final Map<Long, Boolean> subscribedToContest = new HashMap<>();
    private final MassMessageBean massMessageBean = new MassMessageBean();
    private final ContestFlagTextToolTipBean contestFlagTextToolTipBean =
            new ContestFlagTextToolTipBean();
    private final ContestModelSettingsBean contestModelSettingsBean = new ContestModelSettingsBean();

    private ContestMassActions selectedMassAction;
    private Long memberId;

    @SuppressWarnings("unused")
    public ContestOverviewWrapper() {
        populateContestsAndSelectedList();
    }

    public ContestOverviewWrapper(Member member) {
        populateContestsAndSelectedList();
        populateSubscribedToContestList(member);
    }

    private void populateContestsAndSelectedList() {
        List<Contest> allContests = ContestClientUtil.getAllContests();
        // LinkedHashMap will maintain insertion order
        allContests.sort(Comparator.comparing(AbstractContest::getWeight));
        for (Contest contest : allContests) {
            if (contest.getIsSharedContestInForeignColab()) {
                RestService contestService = new RefreshingRestService(CoLabService.CONTEST,
                        ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE);

                Contest foreignContest = ContestClient.fromService(contestService)
                        .getContest(contest.getContestPK());
                foreignContest.setUpForeignContestVisualConfigsFromLocal(contest);

                addContestToMaps(foreignContest);
            } else {
                addContestToMaps(contest);
            }
        }
    }

    private void addContestToMaps(Contest contest) {
        contests.put(contest.getContestPK(), contest);
        selectedContests.put(contest.getContestPK(), false);
    }

    private void populateSubscribedToContestList(Member member) {
        for (Entry<Long, Contest> contestEntry : contests.entrySet()) {
            final Long contestId = contestEntry.getKey();
            Boolean isUserSubscribedToContest = ContestClientUtil
                    .isMemberSubscribedToContest(contestId, member.getId_());
            subscribedToContest.put(contestId, isUserSubscribedToContest);
        }
    }

    public Map<Long, Contest> getContests() {
        return contests;
    }

    public Map<Long, Boolean> getSelectedContests() {
        return selectedContests;
    }

    @Override
    public MassMessageBean getMassMessageBean() {
        return massMessageBean;
    }

    @Override
    public ContestModelSettingsBean getContestModelSettingsBean() {
        return contestModelSettingsBean;
    }

    @Override
    public ContestFlagTextToolTipBean getContestFlagTextToolTipBean() {
        return contestFlagTextToolTipBean;
    }

    @Override
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setSelectedMassAction(ContestMassActions selectedMassAction) {
        this.selectedMassAction = selectedMassAction;
    }

    public ContestMassActions getSelectedMassAction() {
        return selectedMassAction;
    }

    public Map<Long, Boolean> getSubscribedToContest() {
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
        return selectedContests.entrySet().stream()
                .filter(Entry::getValue)
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }
}
