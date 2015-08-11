package org.xcolab.portlets.contestmanagement.wrappers;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import org.xcolab.portlets.contestmanagement.beans.ContestFlagTextToolTipBean;
import org.xcolab.portlets.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.portlets.contestmanagement.beans.MassMessageBean;
import org.xcolab.portlets.contestmanagement.entities.ContestMassActions;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.wrapper.ContestWrapper;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * Created by Thomas on 3/3/2015.
 */
public class ContestOverviewWrapper {

    private List<ContestWrapper> contestWrappers;
    private Long selectedMassAction;
    private List<Boolean> selectedContest;
    private List<Long> selectedContestIds;
    private List<Boolean> subscribedToContest;
    private MassMessageBean massMessageBean;
    private ContestFlagTextToolTipBean contestFlagTextToolTipBean;
    private ContestModelSettingsBean contestModelSettingsBean;
    private Long userId;

    public ContestOverviewWrapper() throws Exception{
        initLists();
        populateContestWrappersAndSelectedContestList();
    }

    public ContestOverviewWrapper(PortletRequest request) throws Exception {
        initLists();
        userId = PortalUtil.getUserId(request);
        populateContestWrappersAndSelectedContestList();
        populateSubscribedToContestList(userId);
    }

    private void initLists() {
        contestWrappers = new ArrayList<>();
        selectedContest = new ArrayList<>();
        subscribedToContest = new ArrayList<>();
        contestFlagTextToolTipBean = new ContestFlagTextToolTipBean();
        contestModelSettingsBean = new ContestModelSettingsBean();
        massMessageBean = new MassMessageBean();
    }

    private void populateSubscribedToContestList(Long userId) throws Exception {

        for (ContestWrapper contestWrapper : contestWrappers) {
            Boolean isUserSubscribedToContest = ContestLocalServiceUtil.isSubscribed(contestWrapper.getContestPK(), userId);
            subscribedToContest.add(isUserSubscribedToContest);
        }
    }

    private void populateContestWrappersAndSelectedContestList() throws Exception {
        List<Contest> contests = ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE);
        for (Contest contest : contests) {
            contestWrappers.add(new ContestWrapper(contest));
            selectedContest.add(new Boolean(false));
        }
    }

    public List<ContestWrapper> getContestWrappers() {
        return contestWrappers;
    }

    public void setContestWrappers(List<ContestWrapper> contestWrappers) {
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

    public void persistOrder() throws Exception {
        for (ContestWrapper contestWrapper : contestWrappers) {
            Contest contest = ContestLocalServiceUtil.getContest(contestWrapper.getContestPK());
            contest.setWeight(contestWrapper.getWeight());
            contest.persist();
        }
    }

    public String getSelectedMassActionTitle() throws Exception {
        String selectedMassActionTitle = "";
        Long selectedMassActionAbsolute = Math.abs(selectedMassAction.longValue());
        for (ContestMassActions contestMassAction : ContestMassActions.values()) {
            if (selectedMassActionAbsolute == contestMassAction.ordinal()) {
                if (selectedMassAction.longValue() < 0) {
                    selectedMassActionTitle = contestMassAction.getReverseActionDisplayName();
                } else {
                    selectedMassActionTitle = contestMassAction.getActionDisplayName();
                }
                break;
            }
        }
        return selectedMassActionTitle;
    }

    public void executeMassAction(PortletRequest request, ResourceResponse response) throws Exception {
        boolean isOrderMassAction = selectedMassAction == ContestMassActions.ORDER.ordinal();
        if(isOrderMassAction){
            persistOrder();
            return;
        }

        Method massActionMethod = getSelectedMassActionMethod(selectedMassAction);
        Class massActionClass = massActionMethod.getDeclaringClass();
        selectedContestIds = getSelectedContestIds();

        Boolean isReportMassAction =
                (selectedMassAction == ContestMassActions.REPORT_PEOPLE_IN_CURRENT_PHASE.ordinal());
        Boolean isMessageMassAction =
                (selectedMassAction == ContestMassActions.MESSAGE.ordinal());
        Boolean isSetFlagTextToolTipAction =
                (selectedMassAction == ContestMassActions.FLAG.ordinal());
        Boolean isSetModelSettingsAction =
                (selectedMassAction == ContestMassActions.MODEL_SETTINGS.ordinal());
        Boolean isMethodFromContestWrapper =
                (massActionClass == ContestWrapper.class);

        if (isReportMassAction) {
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
            massActionMethod.invoke(null, selectedContestIds, executeSetAction, request);
        }
    }

    private void invokeMassActionReportMethod(Method massActionMethod, PortletRequest request, ResourceResponse response) throws Exception {
        massActionMethod.invoke(null, selectedContestIds, response, request);
    }

    private void invokeMassActionMessageMethod(Method massActionMethod, PortletRequest request) throws Exception {
        massActionMethod.invoke(null, selectedContestIds, massMessageBean, request);
    }

    private void invokeSetFlagTextToolTipMethod(Method massActionMethod, PortletRequest request) throws Exception {
        massActionMethod.invoke(null, selectedContestIds, contestFlagTextToolTipBean, request);
    }

    void invokeSetModelSettingsMethod(Method massActionMethod, PortletRequest request) throws Exception {
        massActionMethod.invoke(null, selectedContestIds, contestModelSettingsBean, request);
    }

    private void invokeContestWrapperMethod(Method massActionMethod, PortletRequest request) throws Exception {
        Boolean executeSetAction = (selectedMassAction > 0);
        for (ContestWrapper contestWrapper : contestWrappers) {
            int index = contestWrappers.indexOf(contestWrapper);
            if (selectedContest.get(index)) {
                massActionMethod.invoke(contestWrapper, executeSetAction);
                contestWrapper.persist();
            }
        }
    }

    private List<Long> getSelectedContestIds() {
        List<Long> contestIds = new ArrayList<>();
        for (ContestWrapper contestWrapper : contestWrappers) {
            int index = contestWrappers.indexOf(contestWrapper);
            if (selectedContest.get(index)) {
                contestIds.add(contestWrapper.getContestPK());
            }
        }
        return contestIds;
    }

    private Method getSelectedMassActionMethod(Long selectedMassAction) {
        Long selectedMassActionAbsolute = Math.abs(selectedMassAction.longValue());
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
