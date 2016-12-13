package org.xcolab.portlets.contestmanagement.controller.details;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.ContestClientUtil;

import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.FocusAreaOntologyTerm;
import org.xcolab.enums.OntologySpaceEnum;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.OntologyWrapper;
import org.xcolab.util.IdListUtil;
import org.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestDetailsOntologyTabController extends ContestDetailsBaseTabController {

    private final static Logger _log = LoggerFactory.getLogger(ContestDetailsOntologyTabController.class);
    static final private TabEnum tab = ContestDetailsTabs.ONTOLOGY;
    static final private String TAB_VIEW = "details/ontologyTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
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
    public String showOntologyTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {

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
    public void updateOntologyTabController(ActionRequest request, Model model, ActionResponse response) {

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

            for (FocusAreaOntologyTerm focusAreaOntologyTerm : OntologyClientUtil.getFocusAreaOntologyTermsByFocusArea(focusAreaId)) {
                OntologyClientUtil.deleteFocusAreaOntologyTerm(focusAreaOntologyTerm.getFocusAreaId(),focusAreaOntologyTerm.getOntologyTermId());
            }

            for (Long ontologyTerm : selectedOntologyTerms) {
                OntologyClientUtil.addOntologyTermsToFocusAreaByOntologyTermId(focusAreaId,ontologyTerm);
            }

            SetRenderParameterUtil.setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch (IOException e) {
            _log.warn("Update contest overview failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateContestOntology", "error=true"})
    public String reportError(PortletRequest request, Model model) {
        return TAB_VIEW;
    }

}