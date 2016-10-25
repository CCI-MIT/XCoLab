package org.xcolab.portlets.contestmanagement.controller.details;


import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.FocusAreaOntologyTerm;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.enums.OntologySpaceEnum;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.OntologyWrapper;
import org.xcolab.utils.IdListUtil;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2/13/2015.
 */

@Controller
@RequestMapping("view")
public class ContestDetailsOntologyTabController extends ContestDetailsBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestDetailsOntologyTabController.class);
    static final private TabEnum tab = ContestDetailsTabs.ONTOLOGY;
    static final private String TAB_VIEW = "details/ontologyTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException {
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

        try {
            OntologyWrapper ontologyWrapper = new OntologyWrapper();
            model.addAttribute("ontologyTerms", ontologyWrapper.getOntologyTerms());
            model.addAttribute("ontologySpaces", ontologyWrapper.getSortedOntologySpaces());
            model.addAttribute("contestOntologyTerms",
                    ontologyWrapper.getOntologyTermIdsForFocusAreaOfContest(getContest()));
            setPageAttributes(request, model, tab);
            return TAB_VIEW;
        } catch (SystemException | PortalException e) {
            _log.warn("Could not show ontology tab: ", e);
            SetRenderParameterUtil.addActionExceptionMessageToSession(request, e);
        }
        return NOT_FOUND_TAB_VIEW;
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
                FocusArea focusArea = FocusAreaLocalServiceUtil
                        .createFocusArea(CounterLocalServiceUtil.increment(FocusArea.class.getName()));
                focusArea.persist();
                FocusAreaLocalServiceUtil.updateFocusArea(focusArea);
                focusAreaId = focusArea.getId();
                contest.setFocusAreaId(focusAreaId);
                ContestClientUtil.updateContest(contest);
            }

            for (FocusAreaOntologyTerm focusAreaOntologyTerm : FocusAreaOntologyTermLocalServiceUtil
                    .findTermsByFocusArea(focusAreaId)) {
                FocusAreaOntologyTermLocalServiceUtil.deleteFocusAreaOntologyTerm(focusAreaOntologyTerm);
            }

            for (Long ontologyTerm : selectedOntologyTerms) {
                FocusAreaOntologyTermLocalServiceUtil.addAreaTerm(focusAreaId, ontologyTerm);
            }

            SetRenderParameterUtil.setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch (SystemException | PortalException | IOException e) {
            _log.warn("Update contest overview failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateContestOntology", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

}