package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.LabelStringValue;
import org.xcolab.commons.html.LabelValue;
import org.xcolab.util.enums.contest.ContestTier;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.beans.ContestAdminBean;
import org.xcolab.view.pages.contestmanagement.beans.ContestModelSettingsBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/details/contestId/{contestId}/tab/ADMIN")
public class ContestAdminTabController extends AbstractTabController {

    static final private ContestDetailsTabs tab = ContestDetailsTabs.ADMIN;
    static final private String TAB_VIEW = "contestmanagement/details/adminTab";

    @Autowired
    private IContestTypeClient contestTypeClient;

    @Autowired
    private IContestClient contestClient;

    @ModelAttribute("contestLevelSelectionItems")
    public List<LabelValue> populateContestLevelSelectionItems() {
        return getContestLevelSelectionItems();
    }

    private List<LabelValue> getContestLevelSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        for (ContestTier contestLevel : ContestTier.values()) {
            selectItems.add(new LabelValue(contestLevel.getTierType(), contestLevel.getTierName()));
        }
        return selectItems;
    }

    @ModelAttribute("contestTypeSelectionItems")
    public List<LabelValue> populateContestTypeSelectionItems() {
        return getContestTypeSelectionItems();
    }

    private List<LabelValue> getContestTypeSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();

        for (ContestType contestType : contestTypeClient.getAllContestTypes()) {
            selectItems.add(new LabelValue(contestType.getId(),
                    String.format("%d - %s with %s", contestType.getId(),
                            contestType.getContestName(), contestType.getProposalNamePlural())));
        }

        return selectItems;
    }

    @ModelAttribute("modelIdsSelectionItems")
    public List<LabelValue> populateModelIdsSelectionItems() {
        return ContestModelSettingsBean.getAllModelIds();
    }

    @ModelAttribute("modelRegionsSelectionItems")
    public List<LabelStringValue> populateModelRegionsSelectionItems() {
        return ContestModelSettingsBean.getAllModelRegions();
    }

    @Override
    @ModelAttribute("currentTabWrapped")
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @GetMapping
    public String showAdminTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @PathVariable long contestId) {

        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("contestAdminBean", new ContestAdminBean(contestClient.getContest(contestId)));
        return TAB_VIEW;
    }

    @PostMapping("update")
    public String updateAdminTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @ModelAttribute ContestAdminBean updateContestAdminBean,
            @PathVariable long contestId) {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        updateContestAdminBean.persist(contestClient.getContest(contestId));
        return "redirect:" + tab.getTabUrl(contestId);
    }
}
