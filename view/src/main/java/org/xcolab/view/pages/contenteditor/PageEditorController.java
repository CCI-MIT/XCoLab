package org.xcolab.view.pages.contenteditor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;
import org.xcolab.client.contents.pojo.ContentPage;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PageEditorController extends BaseContentEditor {

    @GetMapping("/content-editor/pageEditor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletRequest response,
            Model model) {
        long memberId = MemberAuthUtil.getMemberId(request);
        if (PermissionsClient.canAdminAll(memberId)) {

            Map<String, String> map = new LinkedHashMap<>();
            map = getArticles(1l, 0, map);

            model.addAttribute("allArticles", map);
            return "contenteditor/pageEditor";
        } else {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
    }

    private String getTabbedContet(int number) {
        String tab = "";
        for (int i = 0; i < number; i++) {
            tab += "-";
        }
        return tab;
    }

    private Map<String, String> getArticles(Long folderId, Integer numberOfTabs,
            Map<String, String> map) {
        List<ContentFolder> contentFolders = ContentsClient.getContentFolders(folderId);
        if (contentFolders != null) {
            for (ContentFolder cf : contentFolders) {
                if (cf.getContentFolderId().longValue() != ContentFolder.RESOURCE_FOLDER_ID) {
                    map.put(getTabbedContet(numberOfTabs) + cf.getContentFolderName(),
                            cf.getContentFolderId().toString());
                    map = getArticles(cf.getContentFolderId(), ++numberOfTabs, map);
                }
            }
        }
        List<ContentArticleVersion> contentArticles =
                ContentsClient.getChildArticleVersions(folderId);
        if (contentArticles != null) {
            for (ContentArticleVersion ca : contentArticles) {
                map.put(getTabbedContet(numberOfTabs) + ca.getTitle(),
                        ca.getContentArticleId().toString());
            }
        }
        return map;
    }

    @GetMapping("/content-editor/pageEditorGetPage")
    public void contentEditorListFolder(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long pageId) {

        try {
            ContentPage cp = ContentsClient.getContentPage(pageId);
            JSONObject contentPage = new JSONObject();
            contentPage.put("contentArticleId", cp.getContentArticleId());
            contentPage.put("menuArticleId", cp.getMenuArticleId());
            contentPage.put("pageTitle", cp.getTitle());
            contentPage.put("pageId", cp.getPageId());

            response.getOutputStream().write(contentPage.toString().getBytes());
        } catch (IOException i) {

        }

    }

    @GetMapping("/content-editor/pageEditorListFolder")
    public void contentEditorListFolder(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String node)
            throws IOException {
        List<ContentPage> pages = ContentsClient.getContentPages(null);
        JSONArray responseArray = new JSONArray();
        for (ContentPage cp : pages) {
            responseArray
                    .put(articleNode(cp.getTitle(),
                            cp.getPageId()));
        }
        response.getOutputStream().write(responseArray.toString().getBytes());
    }
}
