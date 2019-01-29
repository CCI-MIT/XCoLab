package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.taglibs.xcolab.controller.BaseTabController;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractTabController extends BaseTabController {

    protected TabWrapper tabWrapper;

    @Override
    @ModelAttribute("tabs")
    public List<TabWrapper> populateTabs(Model model, HttpServletRequest request) {
        return getAllVisibleTabsWrapped(request, ContestDetailsTabs.values());
    }

    @Override
    @ModelAttribute("currentTabWrapped")
    public abstract TabWrapper populateCurrentTabWrapped(HttpServletRequest request);

    @ModelAttribute("contestWrapper")
    public ContestWrapper populateContestWrapper(Model model, HttpServletRequest request,
            @PathVariable long contestId) {
        return contestClient.getContest(contestId);
    }
}
