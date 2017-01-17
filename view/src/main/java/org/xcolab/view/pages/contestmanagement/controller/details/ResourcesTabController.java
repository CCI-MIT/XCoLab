package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.entity.utils.flash.AlertMessage;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.beans.ContestResourcesBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.pages.contestmanagement.wrappers.WikiPageWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/details/contestId/{contestId}/tab/RESOURCES")
public class ResourcesTabController extends AbstractTabController {

    private static final ContestDetailsTabs tab = ContestDetailsTabs.RESOURCES;
    private static final String TAB_VIEW = "contestmanagement/details/resourcesTab";

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

    @GetMapping
    public String showResourcesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model) {

        if (!tabWrapper.getCanView()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        long memberId = MemberAuthUtil.getMemberId(request);
        wikiPageWrapper = new WikiPageWrapper(getContest(), memberId);
        setPageAttributes(request, model, tab);
        model.addAttribute("contestResourcesBean", wikiPageWrapper.getContestResourcesBean());
        return TAB_VIEW;
    }

    @PostMapping("update")
    public String updateResourcesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, @PathVariable long contestId,
            @ModelAttribute ContestResourcesBean updatedContestResourcesBean,
            BindingResult result) throws UnsupportedEncodingException, ParseException {

        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        if (result.hasErrors()) {
            AlertMessage.danger("An error occurred while updating").flash(request);
            return tab.getTabUrl(contestId);
        }

        wikiPageWrapper.updateWikiPage(updatedContestResourcesBean);
        AlertMessage.CHANGES_SAVED.flash(request);
        return "redirect:" + tab.getTabUrl(contestId);
    }
}