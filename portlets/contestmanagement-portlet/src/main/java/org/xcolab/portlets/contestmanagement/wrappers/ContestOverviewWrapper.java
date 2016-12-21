package org.xcolab.portlets.contestmanagement.wrappers;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.AbstractContest;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.portlets.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.portlets.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.portlets.contestmanagement.beans.MassMessageBean;
import org.xcolab.portlets.contestmanagement.entities.ContestMassActions;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.MassActionUtil;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;

public class ContestOverviewWrapper {

    private List<Contest> contestWrappers;
    private Long selectedMassAction;
    private List<Boolean> selectedContest;
    private List<Long> selectedContestIds;
    private List<Boolean> subscribedToContest;
    private MassMessageBean massMessageBean;
    private ContestFlagTextToolTipBean contestFlagTextToolTipBean;
    private ContestModelSettingsBean contestModelSettingsBean;

    public ContestOverviewWrapper() {
        initLists();
        populateContestWrappersAndSelectedContestList();
    }

    public ContestOverviewWrapper(PortletRequest request) {
        initLists();
        populateContestWrappersAndSelectedContestList();
        populateSubscribedToContestList(MemberAuthUtil.getMemberId(request));
    }

    private void initLists() {
        contestWrappers = new ArrayList<>();
        selectedContest = new ArrayList<>();
        subscribedToContest = new ArrayList<>();
        contestFlagTextToolTipBean = new ContestFlagTextToolTipBean();
        contestModelSettingsBean = new ContestModelSettingsBean();
        massMessageBean = new MassMessageBean();
    }

    private void populateSubscribedToContestList(long memberId) {
        for (Contest contestWrapper : contestWrappers) {
            Boolean isUserSubscribedToContest = ContestClientUtil
                    .isMemberSubscribedToContest(contestWrapper.getContestPK(), memberId);
            subscribedToContest.add(isUserSubscribedToContest);
        }
    }

    private void populateContestWrappersAndSelectedContestList() {
            List<Contest> contests = ContestClientUtil.getAllContests();
            for (Contest contest : contests) {
                if (contest.getIsSharedContestInForeignColab()) {
                    try {
                        RestService contestService = new RefreshingRestService(CoLabService.CONTEST,
                                ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                                ConfigurationAttributeKey.PARTNER_COLAB_PORT);

                        Contest foreignContest = ContestClient.fromService(contestService).getContest(contest.getContestPK());
                        foreignContest.setUpForeignContestVisualConfigsFromLocal(contest);

                        contestWrappers.add(foreignContest);

                    } catch (ContestNotFoundException notFound){
                    }
                } else {
                    contestWrappers.add((contest));
                }
                selectedContest.add(false);
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

    public MassMessageBean getMassMessageBean() {
        return massMessageBean;
    }

    public void setMassMessageBean(MassMessageBean massMessageBean) {
        this.massMessageBean = massMessageBean;
    }

    public ContestModelSettingsBean getContestModelSettingsBean() {
        return contestModelSettingsBean;
    }

    public void setContestModelSettingsBean(ContestModelSettingsBean contestModelSettingsBean) {
        this.contestModelSettingsBean = contestModelSettingsBean;
    }

    public ContestFlagTextToolTipBean getContestFlagTextToolTipBean() {
        return contestFlagTextToolTipBean;
    }

    public void setContestFlagTextToolTipBean(ContestFlagTextToolTipBean contestFlagTextToolTipBean) {
        this.contestFlagTextToolTipBean = contestFlagTextToolTipBean;
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

    public void persistOrder() {
            for (Contest contestWrapper : contestWrappers) {
                Contest contest = contestWrapper.getWrapped();
                if(contest.getIsSharedContestInForeignColab()){
                    contest = ContestClientUtil.getContest(contest.getContestPK());
                }
                contest.setWeight(contestWrapper.getWeight());

                ContestClientUtil.updateContest(contest);
            }
    }

    public String getSelectedMassActionTitle() {
        return MassActionUtil.getSelectedMassActionTitle(selectedMassAction);
    }

    public void executeMassAction(PortletRequest request, Object response)
            throws InvocationTargetException, IllegalAccessException {
        boolean isOrderMassAction = selectedMassAction == ContestMassActions.ORDER.ordinal();
        if (isOrderMassAction) {
            persistOrder();
            return;
        }

        Method massActionMethod = getSelectedMassActionMethod(selectedMassAction);
        Class massActionClass = massActionMethod.getDeclaringClass();
        selectedContestIds = getSelectedContestIds();

        Boolean isResponseObjectRequiredForMassAction =
                (selectedMassAction == ContestMassActions.REPORT_PEOPLE_IN_CURRENT_PHASE.ordinal());
        Boolean isMessageMassAction =
                (selectedMassAction == ContestMassActions.MESSAGE.ordinal());
        Boolean isSetFlagTextToolTipAction =
                (selectedMassAction == ContestMassActions.FLAG.ordinal());
        Boolean isSetModelSettingsAction =
                (selectedMassAction == ContestMassActions.MODEL_SETTINGS.ordinal());
        Boolean isMethodFromContestWrapper =
                (massActionClass == AbstractContest.class);

        if (isResponseObjectRequiredForMassAction) {
            invokeMassActionReportMethod(massActionMethod, request, response);
        } else if (isMessageMassAction) {
            invokeMassActionMessageMethod(massActionMethod, request);
        } else if (isSetFlagTextToolTipAction) {
            invokeSetFlagTextToolTipMethod(massActionMethod, request);
        } else if (isMethodFromContestWrapper) {
            invokeContestWrapperMethod(massActionMethod, request);
        } else if (isSetModelSettingsAction) {
            invokeSetModelSettingsMethod(massActionMethod, request);
        } else {
            Boolean executeSetAction = (selectedMassAction > 0);
            //HERE THE MAGIC HAPPENS
            Boolean actionConfirmed=false;
            massActionMethod.invoke(null, selectedContestIds, actionConfirmed, request);
        }
    }

    private void invokeMassActionReportMethod(Method massActionMethod, PortletRequest request, Object response)
            throws InvocationTargetException, IllegalAccessException {
        massActionMethod.invoke(null, selectedContestIds, response, request);
    }

    private void invokeMassActionMessageMethod(Method massActionMethod, PortletRequest request)
            throws InvocationTargetException, IllegalAccessException {
        massActionMethod.invoke(null, selectedContestIds, massMessageBean, request);
    }

    private void invokeSetFlagTextToolTipMethod(Method massActionMethod, PortletRequest request)
            throws InvocationTargetException, IllegalAccessException {
        massActionMethod.invoke(null, selectedContestIds, contestFlagTextToolTipBean, request);
    }

    void invokeSetModelSettingsMethod(Method massActionMethod, PortletRequest request)
            throws InvocationTargetException, IllegalAccessException {
        massActionMethod.invoke(null, selectedContestIds, contestModelSettingsBean, request);
    }

    private void invokeContestWrapperMethod(Method massActionMethod, PortletRequest request)
            throws InvocationTargetException, IllegalAccessException {

            Boolean executeSetAction = (selectedMassAction > 0);
            for (Contest contestWrapper : contestWrappers) {
                int index = contestWrappers.indexOf(contestWrapper);
                if (selectedContest.get(index)) {
                    Contest c;
                    if(contestWrapper.getIsSharedContestInForeignColab()){
                         c = ContestClientUtil.getContest(contestWrapper.getContestPK());
                    }else{
                         c = contestWrapper;
                    }
                    massActionMethod.invoke(c, executeSetAction);
                    c.persist();
                }
            }

    }

    public List<Long> getSelectedContestIds() {
        List<Long> contestIds = new ArrayList<>();
        for (Contest contestWrapper : contestWrappers) {
            int index = contestWrappers.indexOf(contestWrapper);
            if (selectedContest.get(index)) {
                contestIds.add(contestWrapper.getContestPK());
            }
        }
        return contestIds;
    }

    private Method getSelectedMassActionMethod(Long selectedMassAction) {
        Long selectedMassActionAbsolute = Math.abs(selectedMassAction);
        Method massActionMethod = null;
        for (ContestMassActions contestMassAction : ContestMassActions.values()) {
            if (selectedMassActionAbsolute == contestMassAction.ordinal()) {
                massActionMethod = contestMassAction.getMethod();
                break;
            }
        }
        return massActionMethod;
    }

}
