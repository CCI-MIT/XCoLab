package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.IFocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.IdListUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.pages.contestmanagement.wrappers.OntologyWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;
import org.xcolab.view.util.entity.enums.OntologySpaceEnum;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/details/contestId/{contestId}/tab/ONTOLOGY")
public class OntologyTabController extends AbstractTabController {

    static final private ContestDetailsTabs tab = ContestDetailsTabs.ONTOLOGY;
    static final private String TAB_VIEW = "contestmanagement/details/ontologyTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @ModelAttribute("anyOntologyTermIds")
    public List<Long> populateAnyOntologyTermIds() {
        List<Long> anyOntologyTermIds = new ArrayList<>();
        for (OntologySpaceEnum ontologySpaceEnum : OntologySpaceEnum.values()) {
            anyOntologyTermIds.add(ontologySpaceEnum.getAnyOntologyTermId());
        }
        return anyOntologyTermIds;
    }

    @GetMapping
    public String showOntologyTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member, @PathVariable long contestId) {

        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        OntologyWrapper ontologyWrapper = new OntologyWrapper();
        model.addAttribute("ontologyTerms", ontologyWrapper.getOntologyTerms());
        model.addAttribute("ontologySpaces", ontologyWrapper.getSortedOntologySpaces());
        model.addAttribute("contestOntologyTerms",
                ontologyWrapper.getOntologyTermIdsForFocusAreaOfContest(
                        contestClient.getContest(contestId)));
        return TAB_VIEW;
    }

    @PostMapping("update")
    public String updateOntologyTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member,
            @PathVariable long contestId) {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        List<Long> selectedOntologyTerms =
                IdListUtil.getIdsFromString(request.getParameter("selectedOntologyTerms"));

        ContestWrapper contest = contestClient.getContest(contestId);
        Long focusAreaId = contest.getFocusAreaId();
        if (focusAreaId == null) {
            FocusAreaWrapper focusArea = new FocusAreaWrapper();

            focusArea.setName("Focus area for " + contest.getTitle());
            focusArea.setSortOrder(0);
            focusArea = ontologyClient.createFocusArea(focusArea);
            focusAreaId = focusArea.getId();
            contest.setFocusAreaId(focusAreaId);
            contestClient.updateContest(contest);
        }

        for (IFocusAreaOntologyTerm focusAreaOntologyTerm : ontologyClient
                .getFocusAreaOntologyTermsByFocusArea(focusAreaId)) {
            ontologyClient.deleteFocusAreaOntologyTerm(focusAreaOntologyTerm.getFocusAreaId(),
                            focusAreaOntologyTerm.getOntologyTermId());
        }

        for (Long ontologyTerm : selectedOntologyTerms) {
            ontologyClient.addOntologyTermsToFocusAreaByOntologyTermId(focusAreaId, ontologyTerm);
        }
        return "redirect:" + tab.getTabUrl(contestId);
    }
}
