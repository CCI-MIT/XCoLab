package org.xcolab.view.pages.contenteditor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.errors.AccessDeniedPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContentEditorResourceController extends BaseContentEditor {

    @Autowired
    private IContentClient contentClient;

    @Autowired
    private IContestClient contestClient;
    
    @Autowired
    private IPermissionClient permissionClient;

    @GetMapping("/content-editor/resourcePagesEditor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member) {
        if (!permissionClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        return "contenteditor/resourcePagesEditor";
    }

    @GetMapping("/content-editor/contentEditorGetLatestResourceArticleVersion")
    public void contentEditorGetLatestArticleVersion(HttpServletRequest request,
            HttpServletResponse response, @RequestParam(required = false) Long articleId)
            throws IOException {

        IContentArticleVersion contentArticleVersion;
        try {
            contentArticleVersion = contentClient.getLatestContentArticleVersion(articleId);
        } catch (ContentNotFoundException e) {
            contentArticleVersion = null;
        }

        ContestWrapper contest = contestClient.getContestByResourceArticleId(articleId);


        JSONObject articleVersion =
                getContentArticleVersion(articleId, contentArticleVersion, contest.getContestUrl(),
                        contest.getResourceArticleUrl());

        response.getOutputStream().write(articleVersion.toString().getBytes());
    }

    private JSONObject getContentArticleVersion(Long articleId,
            IContentArticleVersion contentArticleVersion, String contestURL,
            String contestArticleUrl) {

        JSONArray versions = new JSONArray();
        List<IContentArticleVersion> cavs = contentClient
                .getContentArticleVersions(0, Integer.MAX_VALUE, null, articleId, null, null, null);

        JSONObject articleVersion;
        for (IContentArticleVersion cav : cavs) {
            articleVersion = new JSONObject();
            articleVersion.put("createdAt", cav.getCreatedAt());
            articleVersion.put("contentArticleVersionId", cav.getId());
            versions.put(articleVersion);
        }
        articleVersion = new JSONObject();
        if (contentArticleVersion != null) {
            articleVersion.put("title", contentArticleVersion.getTitle());
            articleVersion.put("folderId", contentArticleVersion.getFolderId());
            articleVersion.put("articleId", contentArticleVersion.getArticleId());
            articleVersion.put("content", contentArticleVersion.getContent());
            articleVersion.put("createdAt", contentArticleVersion.getCreatedAt());
            articleVersion.put("versions", versions);
            articleVersion.put("contestURL", contestURL);
            articleVersion.put("contestArticleURL", contestArticleUrl);
        }
        return articleVersion;
    }

    @GetMapping("/content-editor/resourcePagesListFolder")
    public void contentEditorListFolder(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String node) throws IOException {

        JSONArray responseArray = new JSONArray();

        if (node == null || node.isEmpty()) {//root
            List<ContestWrapper> allContests = contestClient.getAllContests();
            Map<String, String> yearFolders = new LinkedHashMap<>();
            for (ContestWrapper c : allContests) {
                yearFolders.put(c.getContestYear().toString(), "");

            }
            List<String> yearsList = new ArrayList<>(yearFolders.keySet());
            Collections.sort(yearsList);

            for (int i = yearsList.size() - 1; i >= 0; i--) {
                String year = yearsList.get(i);
                responseArray.put(folderNode(year, year));
            }
        } else {//year
            Long year = Long.parseLong(node); //should be the year
            List<ContestWrapper> contestsInYear = contestClient.getAllContestsInYear(year);
            for (ContestWrapper c : contestsInYear) {
                if (c.getResourceArticleId() != null && c.getResourceArticleId() != 0L) {
                    responseArray
                            .put(articleNode(c.getTitle(), c.getResourceArticleId()));
                }
            }
        }

        response.getOutputStream().write(responseArray.toString().getBytes());
    }
}
