package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.taglibs.xcolab.controller.BaseTabController;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


public abstract class AbstractTabController extends BaseTabController {

    protected TabWrapper tabWrapper;
    private Contest contest;
    private Contest contestWrapper;

    public static boolean getCreateNewContestParameterFromRequest(HttpServletRequest request) {
        String createContestParameter = request.getParameter("createContest");
        return Boolean.parseBoolean(createContestParameter);
    }

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, HttpServletRequest request) {
        return getAllVisibleTabsWrapped(request, ContestDetailsTabs.values());
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public abstract TabWrapper populateCurrentTabWrapped(HttpServletRequest request);

    @Override
    public void setPageAttributes(HttpServletRequest request, Model model, TabEnum tab) {

        String pageTitle = contest.getContestShortName();
        String pageSubTitle = tab.getDisplayName() + " - " + pageTitle;
        String pageDescription = "Contest details for " + pageTitle;
        setSeoTexts(request, pageTitle, pageSubTitle, pageDescription);
    }

    @ModelAttribute("contestWrapper")
    public Contest populateContestWrapper(Model model, HttpServletRequest request,
            @PathVariable long contestId) {
        initContest(contestId);
        return contestWrapper;
    }

    private void initContest(long contestId) {
        try {
            contest = ContestClientUtil.getContest(contestId);
            contestWrapper = (contest);
        } catch (ContestNotFoundException e) {
            throw new DatabaseAccessException(e);
        }
    }

    public Long getContestPK() {
        return contest.getContestPK();
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public Contest getContest(long contestId) {
        if (contest != null) {
            return contest;
        }
        initContest(contestId);
        return contest;
    }

    public TabWrapper getTabWrapper() {
        return tabWrapper;
    }

    public void setTabWrapper(TabWrapper tabWrapper) {
        this.tabWrapper = tabWrapper;
    }

    public Contest getContestWrapper() {
        return contestWrapper;
    }

    public void setContestWrapper(Contest contestWrapper) {
        this.contestWrapper = contestWrapper;
    }

}
