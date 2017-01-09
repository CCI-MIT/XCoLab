package org.xcolab.view.pages.contestmanagement.controller.details;

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

import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.contestmanagement.beans.ContestResourcesBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.pages.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.WikiPageWrapper;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class ContestDetailsResourcesTabController extends ContestDetailsBaseTabController {

    private final static Logger _log =
            LoggerFactory.getLogger(ContestDetailsResourcesTabController.class);
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
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=RESOURCES")
    public String showResourcesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model) {

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
    public void updateResourcesTabController(HttpServletRequest request, Model model,
            HttpServletResponse response,
            @ModelAttribute ContestResourcesBean updatedContestResourcesBean,
            BindingResult result) {

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
            SetRenderParameterUtil
                    .setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch (ParseException | IOException e) {
            _log.warn("Update contest resources failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateContestResources", "error=true"})
    public String reportError(HttpServletRequest request, Model model) {
        return TAB_VIEW;
    }

}