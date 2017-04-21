package org.xcolab.view.pages.contestmanagement.controller.details;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.beans.ContestDescriptionBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.util.html.LabelValue;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/contest/details/contestId/{contestId}")
public class DescriptionTabController extends AbstractTabController {

    private final static Logger _log =
            LoggerFactory.getLogger(DescriptionTabController.class);
    static final private ContestDetailsTabs tab = ContestDetailsTabs.DESCRIPTION;
    static final private String TAB_VIEW = "contestmanagement/details/descriptionTab";

    @ModelAttribute("proposalTemplateSelectionItems")
    public List<LabelValue> populateProposalTemplateSelectionItems() {
        return getProposalTemplateSelectionItems();
    }

    private List<LabelValue> getProposalTemplateSelectionItems() {
        List<LabelValue> selectItems = new ArrayList<>();
        //TODO: why do we need this? and why is hard coded?
        List<Long> excludedList =
                Arrays.asList(1L, 2L, 106L, 201L, 202L, 301L, 401L, 1000401L, 1000501L, 1300104L,
                        1300201L, 1300302L,
                        1300401L, 1300601L, 1300602L);
        for (PlanTemplate proposalTemplate : PlanTemplateClientUtil.getPlanTemplates()) {
            if (!excludedList.contains(proposalTemplate.getId_())) {
                selectItems
                        .add(new LabelValue(proposalTemplate.getId_(), proposalTemplate.getName()));
            }
        }
        return selectItems;
    }

    @ModelAttribute("scheduleTemplateSelectionItems")
    public List<LabelValue> populateScheduleSelectionItems(HttpServletRequest request,
            @PathVariable long contestId) {
        return getContestScheduleSelectionItems(contestId);
    }

    private List<LabelValue> getContestScheduleSelectionItems(long contestId) {
        Contest contest = getContest(contestId);
        Long existingContestScheduleId = contest.getContestScheduleId();
        Boolean contestHasProposals = contest.getProposalsCount() > 0;
        return ContestScheduleLifecycleUtil
                .getScheduleTemplateSelectionItems(existingContestScheduleId, contestHasProposals);
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @GetMapping(value = {"", "tab/DESCRIPTION"})
    public String showDescriptionTab(HttpServletRequest request, HttpServletResponse response,
            Model model) {

        if (!tabWrapper.getCanView()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
        setPageAttributes(request, model, tab);
        if (!model.containsAttribute("contestDescriptionBean")) {
            Contest contest = getContest();
            model.addAttribute("contestDescriptionBean", new ContestDescriptionBean(contest));
        }
        return TAB_VIEW;
    }

    @PostMapping("tab/DESCRIPTION")
    public String updateDescription(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long contestId,
            @Valid ContestDescriptionBean contestDescriptionBean, BindingResult result) {

        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        if (result.hasErrors()) {
            AlertMessage.danger("Error while updating.").flash(request);
            return showDescriptionTab(request, response, model);
        }

        final Contest contest = getContest();

        contestDescriptionBean.persist(contest);
        return showDescriptionTab(request, response, model);
    }
}
