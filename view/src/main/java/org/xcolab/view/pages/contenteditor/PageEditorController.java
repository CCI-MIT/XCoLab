package org.xcolab.view.pages.contenteditor;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.client.content.pojo.IContentFolder;
import org.xcolab.client.content.pojo.IContentPage;
import org.xcolab.client.content.pojo.tables.pojos.ContentPage;
import org.xcolab.client.user.PermissionsClient;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.view.errors.AccessDeniedPage;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PageEditorController extends BaseContentEditor {

    @Autowired
    private IContentClient contentClient;

    @GetMapping("/content-editor/pageEditor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member) {
        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        Map<String, String> map = new LinkedHashMap<>();
        map = getArticles(1L, "", map);

        model.addAttribute("allArticles", map);
        return "contenteditor/pageEditor";
    }

    //var parameters={pageId : pageId, pageTitle: pageTitle, mainContentArticleId:
    // mainContentArticleId
    //,menuArticleId: menuArticleId};

    @GetMapping("/content-editor/previewContentPage")
    public String previewContentPage(HttpServletRequest request, HttpServletResponse response,
            Member member, @RequestParam(required = false) Long mainContentArticleId,
            @RequestParam(required = false) Long menuArticleId, Model model) throws IOException {

        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("contentArticleId", mainContentArticleId);

        if (menuArticleId != null) {
            model.addAttribute("menuArticleId", menuArticleId);
        }
        return "content/contentPage";
    }

    @PostMapping("/content-editor/saveContentPage")
    public void saveContentArticleVersion(HttpServletRequest request, HttpServletResponse response,
            Member member, @RequestParam(required = false) Long pageId,
            @RequestParam(required = false) String pageTitle,
            @RequestParam(required = false) Long mainContentArticleId,
            @RequestParam(required = false) Long menuArticleId,
            @RequestParam(required = false) String metaDescription) throws IOException {

        if (!PermissionsClient.canAdminAll(member)) {
            defaultOperationReturnMessage(false, "Not allowed to save page", "", response);
        }

        IContentPage contentPage;
        if (pageId != 0) {
            try {
                contentPage = contentClient.getContentPage(pageId);
            } catch (ContentNotFoundException e) {
                contentPage = new ContentPage();
            }
        } else {
            contentPage = new ContentPage();
        }

        contentPage.setContentArticleId(mainContentArticleId);
        contentPage.setTitle(pageTitle);

        if (menuArticleId != 0) {
            contentPage.setMenuArticleId(menuArticleId);
        }

        if (metaDescription != null) {
            contentPage.setMetaDescription(metaDescription);
        }

        if (pageId == 0) {
            contentClient.createContentPage(contentPage);
        } else {
            contentClient.updateContentPage(contentPage);
        }

        defaultOperationReturnMessage(true, "Content page created successfully", "", response);
    }

    private Map<String, String> getArticles(Long folderId, String path, Map<String, String> map) {
        List<IContentFolder> contentFolders = contentClient.getContentFolders(folderId);
        if (contentFolders != null) {
            for (IContentFolder cf : contentFolders) {
                if (cf.getId() != IContentFolder.RESOURCE_FOLDER_ID) {
                    map = getArticles(cf.getId(),
                            path + "/" + cf.getName(), map);
                }
            }
        }
        List<IContentArticleVersion> contentArticles =
                contentClient.getContentFolderArticleVersions(folderId);
        if (contentArticles != null) {
            for (IContentArticleVersion ca : contentArticles) {
                map.put(path + "/" + ca.getTitle(), ca.getArticleId().toString());
            }
        }
        return map;
    }

    @GetMapping("/content-editor/pageEditorGetPage")
    @ResponseBody
    public IContentPage contentEditorListFolder(HttpServletRequest request,
            @RequestParam Long pageId) throws ContentNotFoundException {
        return contentClient.getContentPage(pageId);
    }

    @GetMapping("/content-editor/pageEditorListFolder")
    public void contentEditorListFolder(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String node) throws IOException {
        List<IContentPage> pages = contentClient.getContentPages(null);
        JSONArray responseArray = new JSONArray();
        for (IContentPage cp : pages) {
            responseArray.put(articleNode(cp.getTitle(), cp.getId()));
        }
        response.getOutputStream().write(responseArray.toString().getBytes());
    }
}
