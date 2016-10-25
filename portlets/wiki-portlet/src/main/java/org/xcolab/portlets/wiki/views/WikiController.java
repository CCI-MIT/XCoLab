package org.xcolab.portlets.wiki.views;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.portlets.wiki.util.WikiPreferences;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class WikiController {

    @RenderMapping
    public String home(PortletRequest request, PortletResponse response, Model model) {
        final WikiPreferences preferences = new WikiPreferences(request);
        final long folderId = Long.parseLong(preferences.getWikiFolderId());
        try {
            if (folderId > 0 && PermissionsClient
                    .canAdminAll(Long.parseLong(request.getRemoteUser()))) {
                final List<ContentArticleVersion> contentArticleVersions = ContentsClient
                        .getContentArticleVersions(0, Integer.MAX_VALUE, folderId, null, null,
                                null);
                model.addAttribute("contentArticleVersions", contentArticleVersions);
            }
        } catch (NumberFormatException ignored) {
            //not logged in
        }
        return "wikiList";
    }

    @RequestMapping(params = "show=wiki")
    public String showWikiPage(PortletRequest request, PortletResponse response, Model model,
            @RequestParam String pageTitle) throws ContentNotFoundException {
        final WikiPreferences preferences = new WikiPreferences(request);
        final long folderId = Long.parseLong(preferences.getWikiFolderId());

        if (folderId > 0 && StringUtils.isNotBlank(pageTitle)) {
            final ContentArticleVersion contentArticleVersion =
                    ContentsClient.getLatestContentArticleVersion(folderId, pageTitle);
            final ContentArticle contentArticle = ContentsClient
                    .getContentArticle(contentArticleVersion.getContentArticleId());
            if (contentArticle.canView(getMemberId(request))) {
                model.addAttribute("contentArticleVersion", contentArticleVersion);
            }
        }
        return "wiki";
    }

    private Long getMemberId(PortletRequest request) {
        try {
            return Long.parseLong(request.getRemoteUser());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @RequestMapping(params = "show=resource")
    public String showResourcePage(PortletRequest request, PortletResponse response, Model model,
            @RequestParam String contestUrlName, @RequestParam long contestYear)
            throws ContestNotFoundException {

        final Contest contest = ContestClientUtil.getContest(contestUrlName, contestYear);

        try {
            if (contest.getResourceArticleId() > 0) {
                final ContentArticle contentArticle = ContentsClient
                        .getContentArticle(contest.getResourceArticleId());
                ContentArticleVersion contentArticleVersion =
                        ContentsClient.getContentArticleVersion(contentArticle.getMaxVersionId());
                model.addAttribute("contentArticleVersion", contentArticleVersion);
            }
        } catch (ContentNotFoundException e) {
            //TODO: logging
        }

        return "wiki";
    }
}
