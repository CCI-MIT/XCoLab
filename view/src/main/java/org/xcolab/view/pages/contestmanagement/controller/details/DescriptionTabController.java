package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.LabelValue;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.beans.ContestDescriptionBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/contest/details/contestId/{contestId}")
public class DescriptionTabController extends AbstractTabController {

    private static final ContestDetailsTabs tab = ContestDetailsTabs.DESCRIPTION;
    private static final String TAB_VIEW = "contestmanagement/details/descriptionTab";

    @ModelAttribute("proposalTemplateSelectionItems")
    public List<LabelValue> populateProposalTemplateSelectionItems() {
        return getProposalTemplateSelectionItems();
    }

    private List<LabelValue> getProposalTemplateSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        //TODO COLAB-2612: why do we need this? and why is hard coded?
        List<Long> excludedList =
                Arrays.asList(1L, 2L, 106L, 201L, 202L, 301L, 401L, 1000401L, 1000501L, 1300104L,
                        1300201L, 1300302L, 1300401L, 1300601L, 1300602L);
        for (IProposalTemplate proposalTemplate : proposalTemplateClient.getProposalTemplates()) {
            if (!excludedList.contains(proposalTemplate.getId())) {
                selectItems
                        .add(new LabelValue(proposalTemplate.getId(), proposalTemplate.getName()));
            }
        }
        return selectItems;
    }

    @ModelAttribute("scheduleTemplateSelectionItems")
    public List<LabelValue> populateScheduleSelectionItems(HttpServletRequest request,
            @PathVariable long contestId) {
        ContestWrapper contest = contestClient.getContest(contestId);
        Long existingContestScheduleId = contest.getContestScheduleId();
        boolean contestHasProposals = contest.getProposalsCount() > 0;
        return ContestScheduleLifecycleUtil
                .getScheduleTemplateSelectionItems(existingContestScheduleId, contestHasProposals);
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @GetMapping(value = {"", "tab/DESCRIPTION"})
    public String showDescriptionTab(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @PathVariable long contestId) {

        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        if (!model.containsAttribute("contestDescriptionBean")) {
            ContestWrapper contest = contestClient.getContest(contestId);
            model.addAttribute("contestDescriptionBean", new ContestDescriptionBean(contest));
        }
        return TAB_VIEW;
    }

    @PostMapping("tab/DESCRIPTION")
    public String updateDescription(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @PathVariable long contestId,
            @Valid ContestDescriptionBean contestDescriptionBean, BindingResult result) {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        //check for contest name year uniqueness
        final ContestWrapper contest = contestClient.getContest(contestId);
        if (!contestClient.isContestTitleYearUnique(contestDescriptionBean.getTitle(),
                contest.getContestYear(), contest.getId())) {
            AlertMessage
                    .danger("Contest name and year must be unique, a contest with the given name "
                            + "already exists for this year.")
                    .flash(request);
            return showDescriptionTab(request, response, model, member, contestId);
        }

        if (result.hasErrors()) {
            AlertMessage.danger("Error while updating.").flash(request);
            return showDescriptionTab(request, response, model, member, contestId);
        }

        contestDescriptionBean.persist(contest);
        return showDescriptionTab(request, response, model, member, contestId);
    }
}
