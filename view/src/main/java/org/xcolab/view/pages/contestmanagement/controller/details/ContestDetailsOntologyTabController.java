package org.xcolab.view.pages.contestmanagement.controller.details;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTerm;
import org.xcolab.entity.utils.enums.OntologySpaceEnum;
import org.xcolab.util.IdListUtil;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.pages.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.OntologyWrapper;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("view")
public class ContestDetailsOntologyTabController extends ContestDetailsBaseTabController {

    private final static Logger _log =
            LoggerFactory.getLogger(ContestDetailsOntologyTabController.class);
    static final private TabEnum tab = ContestDetailsTabs.ONTOLOGY;
    static final private String TAB_VIEW = "details/ontologyTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
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

    @RequestMapping(params = "tab=ONTOLOGY")
    public String showOntologyTabController(HttpServletRequest request,
            HttpServletResponse response, Model model) {

        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        OntologyWrapper ontologyWrapper = new OntologyWrapper();
        model.addAttribute("ontologyTerms", ontologyWrapper.getOntologyTerms());
        model.addAttribute("ontologySpaces", ontologyWrapper.getSortedOntologySpaces());
        model.addAttribute("contestOntologyTerms",
                ontologyWrapper.getOntologyTermIdsForFocusAreaOfContest(getContest()));
        setPageAttributes(request, model, tab);
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestOntology")
    public void updateOntologyTabController(HttpServletRequest request, Model model,
            HttpServletResponse response) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            List<Long> selectedOntologyTerms =
                    IdListUtil.getIdsFromString(request.getParameter("selectedOntologyTerms"));

            Contest contest = getContest();
            Long focusAreaId = contest.getFocusAreaId();
            if (focusAreaId <= 0) {
                FocusArea focusArea = new FocusArea();

                focusArea.setName(" Focus area for " + contest.getContestShortName());
                focusArea.setOrder_(0);
                focusArea = OntologyClientUtil.createFocusArea(focusArea);
                focusAreaId = focusArea.getId();
                contest.setFocusAreaId(focusAreaId);
                ContestClientUtil.updateContest(contest);
            }

            for (FocusAreaOntologyTerm focusAreaOntologyTerm : OntologyClientUtil
                    .getFocusAreaOntologyTermsByFocusArea(focusAreaId)) {
                OntologyClientUtil
                        .deleteFocusAreaOntologyTerm(focusAreaOntologyTerm.getFocusAreaId(),
                                focusAreaOntologyTerm.getOntologyTermId());
            }

            for (Long ontologyTerm : selectedOntologyTerms) {
                OntologyClientUtil
                        .addOntologyTermsToFocusAreaByOntologyTermId(focusAreaId, ontologyTerm);
            }

            SetRenderParameterUtil
                    .setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch (IOException e) {
            _log.warn("Update contest overview failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateContestOntology", "error=true"})
    public String reportError(HttpServletRequest request, Model model) {
        return TAB_VIEW;
    }

}