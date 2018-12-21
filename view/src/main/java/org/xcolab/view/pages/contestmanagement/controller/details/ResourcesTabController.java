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
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;
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

    private WikiPageWrapper wikiPageWrapper;

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @GetMapping
    public String showResourcesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, Member member, @PathVariable long contestId) {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        Contest contest = ContestClientUtil.getContest(contestId);
        boolean enabled = contest.getResourceArticleId() != 0;

        if (enabled) {
            long userId = MemberAuthUtil.getuserId();
            wikiPageWrapper = new WikiPageWrapper(contest, userId);
            model.addAttribute("contestResourcesBean", wikiPageWrapper.getContestResourcesBean());
        }

        model.addAttribute("resourcePageEnabled", enabled);
        return TAB_VIEW;
    }

    @PostMapping("toggle")
    public String createResourcesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, Member member, @RequestParam boolean enable,
            @PathVariable long contestId) {

        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        Contest contest = ContestClientUtil.getContest(contestId);
        if (enable) {
            ContentArticleVersion contentArticleVersion = new ContentArticleVersion();
            contentArticleVersion.setFolderId(ContentFolder.RESOURCE_FOLDER_ID);
            contentArticleVersion.setAuthorUserId(member.getId());
            contentArticleVersion.setTitle(contest.getTitle());
            contentArticleVersion.setContent("");
            contentArticleVersion = ContentsClient.createContentArticleVersion(contentArticleVersion);

            try {
                ContentArticle contentArticle = ContentsClient.getContentArticle(contentArticleVersion.getArticleId());
                contest.setResourceArticleId(contentArticle.getId());
                ContestClientUtil.updateContest(contest);
            } catch (ContentNotFoundException e) {
                throw new IllegalStateException("Could not retrieve ContentArticle after creation");
            }
        } else {
            ContentsClient.deleteContentArticle(contest.getResourceArticleId());
            contest.deleteResourceArticle();
        }

        model.addAttribute("resourcePageEnabled", enable);

        return "redirect:" + tab.getTabUrl(contestId);
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
