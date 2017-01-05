package org.xcolab.view.pages.contenteditor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.auth.MemberAuthUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContentEditorController {  
    private static final Integer THRESHOLD_TO_AVOID_NODE_COLLISION = 1000;

    @GetMapping("/content-editor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletRequest response, Model model) {
        long memberId = MemberAuthUtil.getMemberId(request);
        if (PermissionsClient.canAdminAll(memberId)) {
            return "/contenteditor/editor";
        } else {
            return "notAllowed";
        }
    }

    @GetMapping("/content-editor/contentEditorListFolder")
    public void contentEditorListFolder(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam(required = false) String node)
            throws IOException {

        JSONArray responseArray = new JSONArray();
        Long folderId = 1L;
        if (node != null && !node.isEmpty()) {
            folderId = Long.parseLong(node);
        }
        List<ContentFolder> contentFolders = ContentsClient.getContentFolders(folderId);

        if (contentFolders != null) {
            for (ContentFolder cf : contentFolders) {
                responseArray.put(folderNode(cf.getContentFolderName(), cf.getContentFolderId().toString()));
            }
        }
        List<ContentArticleVersion> contentArticles = ContentsClient.getChildArticleVersions(folderId);
        if (contentArticles != null) {
            for (ContentArticleVersion ca : contentArticles) {
                responseArray.put(articleNode(ca.getTitle(), ca.getContentArticleId()));
            }
        }

        response.getOutputStream().write(responseArray.toString().getBytes());

    }


    @GetMapping("/content-editor/contentEditorGetLatestArticleVersion")
    public void contentEditorGetLatestArticleVersion(HttpServletRequest request, HttpServletResponse response,
                                                     @RequestParam(required = false) Long articleId)
            throws IOException, ContentNotFoundException {
        JSONObject articleVersion = new JSONObject();

        ContentArticleVersion contentArticleVersion = ContentsClient.getLatestContentArticleVersion(articleId);
        if (contentArticleVersion != null) {
            articleVersion.put("title", contentArticleVersion.getTitle());
            articleVersion.put("folderId", contentArticleVersion.getFolderId());
            articleVersion.put("articleId", contentArticleVersion.getContentArticleId());
            articleVersion.put("content", contentArticleVersion.getContent());
        }

        response.getOutputStream().write(articleVersion.toString().getBytes());
    }


    @PostMapping("/content-editor/createArticleFolder")
    public void createArticleFolder(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = false) String folderName,
                                   @RequestParam(required = false) Long parentFolderId) throws IOException{
        ContentFolder contentFolder = new ContentFolder();
        contentFolder.setContentFolderName(folderName);
        contentFolder.setParentFolderId(parentFolderId);

        ContentsClient.createContentFolder(contentFolder);

        defaultOperationReturnMessage(true, "Folder created successfully", response);
    }

    @PostMapping("/content-editor/moveArticleVersion")
    public void moveArticleVersion(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = false) Long articleId,
                                   @RequestParam(required = false) Long folderId)
            throws IOException, ContentNotFoundException {
        long userId = MemberAuthUtil.getMemberId(request);

        ContentArticleVersion contentArticleVersion = ContentsClient.getLatestContentArticleVersion(articleId);
        ContentArticleVersion newContentArticleVersion = new ContentArticleVersion();
        newContentArticleVersion.setTitle(contentArticleVersion.getTitle());
        newContentArticleVersion.setContent(contentArticleVersion.getContent());
        newContentArticleVersion.setContentArticleId(contentArticleVersion.getContentArticleId());

        newContentArticleVersion.setAuthorId(userId);
        newContentArticleVersion.setFolderId(folderId);
        ContentsClient.createContentArticleVersion(newContentArticleVersion);

        defaultOperationReturnMessage(true, "Article moved successfully", response);
    }


    @PostMapping("/content-editor/saveContentArticleVersion")
    public void saveContentArticleVersion(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam(required = false) Long articleId,
                                          @RequestParam(required = false) String title,
                                          @RequestParam(required = false) Long folderId,
                                          @RequestParam(required = false) String content
    ) throws IOException {
        long userId = MemberAuthUtil.getMemberId(request);

        ContentArticleVersion contentArticleVersion = new ContentArticleVersion();

        contentArticleVersion.setContentArticleId(articleId);

        contentArticleVersion.setAuthorId(userId);
        contentArticleVersion.setFolderId((folderId));
        contentArticleVersion.setTitle(title);
        contentArticleVersion.setContent(content);
        ContentsClient.createContentArticleVersion(contentArticleVersion);


        defaultOperationReturnMessage(true, "Article version created successfully", response);
    }

    private void defaultOperationReturnMessage(boolean success, String message, HttpServletResponse response) throws IOException {
        JSONObject articleVersion = new JSONObject();
        JSONObject folderNode = new JSONObject();
        folderNode.put("success", success);
        folderNode.put("msg", message);
        response.getOutputStream().write(articleVersion.toString().getBytes());
    }

    private JSONObject treeNode(String label, String id, String kind, boolean loadOnDemand) {
        JSONObject folderNode = new JSONObject();
        folderNode.put("label", label);
        folderNode.put("id", id);
        folderNode.put("kind", kind);
        if (loadOnDemand) {
            folderNode.put("load_on_demand", loadOnDemand + "");
        }
        return folderNode;
    }

    private JSONObject articleNode(String label, Long id) {
        return treeNode(label, (THRESHOLD_TO_AVOID_NODE_COLLISION +id) + "", "article", false);
    }

    private JSONObject folderNode(String label, String id) {
        return treeNode(label, id, "folder", true);
    }
}
