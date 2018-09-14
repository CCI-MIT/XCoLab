package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contents.ContentsClient;
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
        return tabWrapper;
    }

    @GetMapping
    public String showResourcesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, Member member) {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        prepareView(request, response, model, member, getContest().getResourceArticleId() != 0);
        return TAB_VIEW;
    }

    @PostMapping("enable")
    public String createResourcesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, Member member, @RequestParam boolean enable) {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        prepareView(request, response, model, member, enable);
        return "redirect:" + tab.getTabUrl(getContestId());
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

    private void prepareView(HttpServletRequest request, HttpServletResponse response, Model model,
            Member member, boolean resourcePageEnabled) {
        if (resourcePageEnabled) {
            long userId = MemberAuthUtil.getuserId(request);
            wikiPageWrapper = new WikiPageWrapper(getContest(), userId);
            model.addAttribute("contestResourcesBean", wikiPageWrapper.getContestResourcesBean());
        } else {
            ContentsClient.deleteContentArticle(getContest().getResourceArticleId());
            getContest().deleteResourceArticle();
            wikiPageWrapper = null;
        }

        model.addAttribute("resourcePageEnabled", resourcePageEnabled);
    }
}
