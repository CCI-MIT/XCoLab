package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.beans.ContestResourcesBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.pages.contestmanagement.wrappers.WikiPageWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/details/contestId/{contestId}/tab/RESOURCES")
public class ResourcesTabController extends AbstractTabController {

    private static final ContestDetailsTabs tab = ContestDetailsTabs.RESOURCES;
    private static final String TAB_VIEW = "contestmanagement/details/resourcesTab";

    private WikiPageWrapper wikiPageWrapper;

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @GetMapping
    public String showResourcesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, Member member) {

        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        long userId = MemberAuthUtil.getuserId(request);
        wikiPageWrapper = new WikiPageWrapper(getContest(), userId);
        model.addAttribute("contestResourcesBean", wikiPageWrapper.getContestResourcesBean());
        return TAB_VIEW;
    }

    @PostMapping("update")
    public String updateResourcesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, Member member,
            @PathVariable long contestId,
            @ModelAttribute ContestResourcesBean updatedContestResourcesBean,
            BindingResult result) throws UnsupportedEncodingException, ParseException {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
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
