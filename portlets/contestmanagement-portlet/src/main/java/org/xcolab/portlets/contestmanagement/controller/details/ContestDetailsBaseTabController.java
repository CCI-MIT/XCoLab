package org.xcolab.portlets.contestmanagement.controller.details;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.controller.BaseTabController;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.wrapper.TabWrapper;
import org.xcolab.wrappers.BaseContestWrapper;

import java.util.List;

import javax.portlet.PortletRequest;

public abstract class ContestDetailsBaseTabController extends BaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestDetailsBaseTabController.class);

    private Contest contest;
    private BaseContestWrapper contestWrapper;
    protected TabWrapper tabWrapper;

    public static final String NO_PERMISSION_TAB_VIEW = "common/noPermissionTab";
    public static final String NOT_FOUND_TAB_VIEW = "common/notFound";

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, PortletRequest request) throws PortalException, SystemException {
        return getAllVisibleTabsWrapped(request, ContestDetailsTabs.values());
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public abstract TabWrapper populateCurrentTabWrapped(PortletRequest request)
            throws PortalException, SystemException;

    @ModelAttribute("contestWrapper")
    public BaseContestWrapper populateContestWrapper(Model model, PortletRequest request) {
        initContest(request);
        return contestWrapper;
    }

    private void initContest(PortletRequest request) {
        Long contestId = getContestIdFromRequest(request);
        try {
            contest = ContestClient.getContest(contestId);
            contestWrapper = new BaseContestWrapper(contest);
        } catch (ContestNotFoundException e) {
            throw new DatabaseAccessException(e);
        }
    }

    @Override
    public void setPageAttributes(PortletRequest request, Model model, TabEnum tab)
            throws PortalException, SystemException {

        String pageTitle = contest.getContestShortName();
        String pageSubTitle = tab.getDisplayName() + " - " + pageTitle;
        String pageDescription = "Contest details for " + pageTitle;
        setSeoTexts(request, pageTitle, pageSubTitle, pageDescription);
    }

    public static boolean getCreateNewContestParameterFromRequest(PortletRequest request) {
        String createContestParameter = request.getParameter("createContest");
        return Boolean.parseBoolean(createContestParameter);
    }

    public Long getContestPK() {
        return contest.getContestPK();
    }

    public Contest getContest() {
        return contest;
    }

    public Contest getContest(PortletRequest request) {
        if (contest != null) {
            return contest;
        }
        initContest(request);
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

    public BaseContestWrapper getContestWrapper() {
        return contestWrapper;
    }

    public void setContestWrapper(BaseContestWrapper contestWrapper) {
        this.contestWrapper = contestWrapper;
    }

}
