package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.taglibs.xcolab.controller.BaseTabController;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractTabController extends BaseTabController {

    protected TabWrapper tabWrapper;
    private Contest contest;
    private Contest contestWrapper;

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, HttpServletRequest request) {
        return getAllVisibleTabsWrapped(request, ContestDetailsTabs.values());
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public abstract TabWrapper populateCurrentTabWrapped(HttpServletRequest request);

    @ModelAttribute("contestWrapper")
    public Contest populateContestWrapper(Model model, HttpServletRequest request,
            @PathVariable long contestId) {
        initContest(contestId);
        return contestWrapper;
    }

    private void initContest(long contestId) {
        contest = ContestClientUtil.getContest(contestId);
        contestWrapper = (contest);
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
