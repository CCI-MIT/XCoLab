package org.xcolab.view.pages.content;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.errors.ErrorText;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WikiController {

    @Autowired
    private IContentClient contentClient;

    @Autowired
    private IContestClient contestClient;
    
    @Autowired
    private IPermissionClient permissionClient;

    @GetMapping("/wiki")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model,
            UserWrapper member) {
        final long folderId = ConfigurationAttributeKey.WIKI_CONTENT_FOLDER_ID.get();

        if (folderId > 0 && permissionClient.canAdminAll(member)) {
            final List<IContentArticleVersion> contentArticleVersions = contentClient
                    .getContentArticleVersions(0, Integer.MAX_VALUE, folderId,
                            null, null, null, null);
            model.addAttribute("contentArticleVersions", contentArticleVersions);
        }
        return "content/wikiList";
    }

    @GetMapping("/wiki/{pageTitle:.*}")
    public String showWikiPage(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @PathVariable String pageTitle) throws IOException {
        final long folderId = ConfigurationAttributeKey.WIKI_CONTENT_FOLDER_ID.get();

        if (folderId > 0 && StringUtils.isNotBlank(pageTitle)) {
            try {
                final IContentArticleVersion contentArticleVersion = contentClient
                        .getLatestContentArticleVersion(folderId, pageTitle.replace('+', ' '));
                final IContentArticle contentArticle = contentClient
                        .getContentArticle(contentArticleVersion.getArticleId());

                if (!contentArticle.canView(member)) {
                    return new AccessDeniedPage(member).toViewName(response);
                }
                model.addAttribute("contentArticleVersion", contentArticleVersion);
            } catch (ContentNotFoundException e) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
                return ErrorText.NOT_FOUND.flashAndReturnView(request);
            }
        }
        return "content/wiki";
    }

    @GetMapping("/resources/{contestYear}/{contestUrlName}")
    public String showResourcePage(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long contestYear, @PathVariable String contestUrlName)
            throws ContestNotFoundException, IOException {

        final ContestWrapper contest = contestClient.getContest(contestUrlName, contestYear);

        if (contest == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }
        try {
            if (contest.getResourceArticleId() > 0) {
                final IContentArticle contentArticle =
                        contentClient.getContentArticle(contest.getResourceArticleId());
                IContentArticleVersion contentArticleVersion =
                        contentClient.getContentArticleVersion(contentArticle.getMaxVersionId());
                model.addAttribute("contentArticleVersion", contentArticleVersion);
            }
        } catch (ContentNotFoundException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ErrorText.NOT_FOUND.flashAndReturnView(request);
        }

        return "content/wiki";
    }
}
