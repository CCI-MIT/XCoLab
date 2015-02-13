package org.xcolab.portlets.contestmanagement.controller;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.xcolab.controller.BaseTabController;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.views.ContestDetailsTabs;
import org.xcolab.wrapper.ContestWrapper;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.PortletRequest;
import java.util.List;


public abstract class ContestDetailsBaseTabController extends BaseTabController {

    private Contest contest;
    private ContestWrapper contestWrapper;
    protected TabWrapper tabWrapper;

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, PortletRequest request) throws PortalException, SystemException {
        return getAllVisibleTabsWrapped(model, request, ContestDetailsTabs.values());
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public abstract TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException;

    @ModelAttribute("contestWrapper")
    public ContestWrapper populateContestWrapper(Model model, PortletRequest request){
        try {
            Long contestId = getContestIdFromRequest(request);
            if (contestId != null) {
                contest = ContestLocalServiceUtil.getContest(contestId);
            } else {
                // TODO 10144L is admin id, check what we want to have here
                contest = ContestLocalServiceUtil.createNewContest(10144L, "created contest");
            }
            contestWrapper = new ContestWrapper(contest);
           return contestWrapper;
        } catch (Exception e){
        }
        return null;
    }

    public void setPageAttributes(PortletRequest request, Model model, TabEnum tab)
            throws PortalException, SystemException {

        String pageTitle = contest.getContestShortName();
        String pageSubTitle = tab.getDisplayName() + " - " + pageTitle;
        String pageDescription = "Contest details for " + pageTitle;
        setSeoTexts(request, pageTitle, pageSubTitle, pageDescription);
    }

    public static Long getContestIdFromRequest(PortletRequest request){
        String contestIdParameter = request.getParameter("contestId");
        return Long.parseLong(contestIdParameter);
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public TabWrapper getTabWrapper() {
        return tabWrapper;
    }

    public void setTabWrapper(TabWrapper tabWrapper) {
        this.tabWrapper = tabWrapper;
    }

    public ContestWrapper getContestWrapper() {
        return contestWrapper;
    }

    public void setContestWrapper(ContestWrapper contestWrapper) {
        this.contestWrapper = contestWrapper;
    }
}
