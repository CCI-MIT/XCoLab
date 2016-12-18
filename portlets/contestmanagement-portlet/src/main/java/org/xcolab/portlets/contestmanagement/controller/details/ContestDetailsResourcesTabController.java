package org.xcolab.portlets.contestmanagement.controller.details;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.ContestResourcesBean;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.WikiPageWrapper;
import org.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.text.ParseException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestDetailsResourcesTabController extends ContestDetailsBaseTabController {

    private final static Logger _log = LoggerFactory.getLogger(ContestDetailsResourcesTabController.class);
    static final private TabEnum tab = ContestDetailsTabs.RESOURCES;
    static final private String TAB_VIEW = "details/resourcesTab";

    @Autowired
    private Validator validator;
    private WikiPageWrapper wikiPageWrapper;

    @InitBinder("contestResourcesBean")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=RESOURCES")
    public String showResourcesTabController(PortletRequest request, PortletResponse response, Model model) {

        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        long memberId = MemberAuthUtil.getMemberId(request);
        wikiPageWrapper = new WikiPageWrapper(getContest(), memberId);
        setPageAttributes(request, model, tab);
        model.addAttribute("contestResourcesBean", wikiPageWrapper.getContestResourcesBean());
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestResources")
    public void updateResourcesTabController(ActionRequest request, Model model, ActionResponse response,
            @ModelAttribute ContestResourcesBean updatedContestResourcesBean, BindingResult result) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateContestResources");
            return;
        }

        try {
            wikiPageWrapper.updateWikiPage(updatedContestResourcesBean);
            SetRenderParameterUtil.setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch (ParseException | IOException e) {
            _log.warn("Update contest resources failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateContestResources", "error=true"})
    public String reportError(PortletRequest request, Model model) {
        return TAB_VIEW;
    }

}