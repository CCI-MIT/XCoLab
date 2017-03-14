package org.xcolab.view.pages.contenteditor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
            map = getArticles(1l, "", map);

            model.addAttribute("allArticles", map);
            return "contenteditor/pageEditor";
        } else {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
    }

    //var parameters={pageId : pageId, pageTitle: pageTitle, mainContentArticleId:
    // mainContentArticleId
    //,menuArticleId: menuArticleId};

    @GetMapping("/content-editor/previewContentPage")
    public String previewContentPage(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long mainContentArticleId,
            @RequestParam(required = false) Long menuArticleId,
            Model model
    ) throws IOException {


        long memberId = MemberAuthUtil.getMemberId(request);
        if (PermissionsClient.canAdminAll(memberId)) {

            model.addAttribute("contentArticleId", mainContentArticleId);

            if (menuArticleId != null) {
                model.addAttribute("menuArticleId", menuArticleId);
            }
            return "content/contentPage";
        } else {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
    }

    @PostMapping("/content-editor/saveContentPage")
    public void saveContentArticleVersion(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long pageId,
            @RequestParam(required = false) String pageTitle,
            @RequestParam(required = false) Long mainContentArticleId,
            @RequestParam(required = false) Long menuArticleId
    ) throws IOException {
        long userId = MemberAuthUtil.getMemberId(request);

        ContentPage contentPage;
        if (pageId != 0) {
            contentPage = ContentsClient.getContentPage(pageId);
        } else {
            contentPage = new ContentPage();
        }

        contentPage.setContentArticleId(mainContentArticleId);
        contentPage.setTitle(pageTitle);

        if (menuArticleId != 0) {
            contentPage.setMenuArticleId(menuArticleId);
        }

        if (pageId == 0) {
            ContentsClient.createContentPage(contentPage);
        } else {
            ContentsClient.updateContentPage(contentPage);
        }


        defaultOperationReturnMessage(true, "Content page created successfully","", response);
    }

    private Map<String, String> getArticles(Long folderId, String path,
            Map<String, String> map) {
        List<ContentFolder> contentFolders = ContentsClient.getContentFolders(folderId);
        if (contentFolders != null) {
            for (ContentFolder cf : contentFolders) {
                if (cf.getContentFolderId().longValue() != ContentFolder.RESOURCE_FOLDER_ID) {

                    map = getArticles(cf.getContentFolderId(),
                            path + "/" + cf.getContentFolderName(), map);
                }
            }
        }
        List<ContentArticleVersion> contentArticles =
                ContentsClient.getChildArticleVersions(folderId);
        if (contentArticles != null) {
            for (ContentArticleVersion ca : contentArticles) {
                map.put(path + "/" + ca.getTitle(),
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
            contentPage.put("createdDate", cp.getCreatedDate());

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
