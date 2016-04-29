package org.xcolab.portlets.contenteditor.views;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;

import java.io.IOException;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class ContentEditorController {
/*
    @RequestMapping
    public String contentEditor() {
        return "editor";
    }
*/

    @RequestMapping
    public String handleRenderRequest(RenderRequest request, RenderResponse response, Model model) {
        return "editor";
    }


    @ResourceMapping("contentEditorListFolder")
    public void contentEditorListFolder(ResourceRequest request, ResourceResponse response,
                                        @RequestParam(required = false) String node)
            throws IOException, SystemException, PortalException {

        JSONArray responseArray = JSONFactoryUtil.createJSONArray();
        Long folderId = 0l;
        if (node != null && !node.isEmpty()) {
            folderId = Long.parseLong(node);
        }
        List<ContentFolder> contentFolders = ContentsClient.getChildFolders(folderId);

        if (contentFolders != null) {
            for (ContentFolder cf : contentFolders) {
                responseArray.put(folderNode(cf.getContentFolderName(), cf.getContentFolderId().toString()));
            }
        }
        List<ContentArticleVersion> contentArticles = ContentsClient.getChildArticleVersions(folderId);
        if (contentArticles != null) {
            for (ContentArticleVersion ca : contentArticles) {
                responseArray.put(articleNode(ca.getTitle(), ca.getContentArticleId().toString()));
            }
        }

        response.getPortletOutputStream().write(responseArray.toString().getBytes());

    }

    @ResourceMapping("contentEditorGetLatestArticleVersion")
    public void contentEditorGetLatestArticleVersion(ResourceRequest request, ResourceResponse response,
                                                     @RequestParam(required = false) Long articleId)
            throws IOException, SystemException, PortalException {
        JSONObject articleVersion = JSONFactoryUtil.createJSONObject();

        ContentArticleVersion contentArticleVersion = ContentsClient.getLatestContentArticleVersionByContentArticleId(articleId);
        if (contentArticleVersion != null) {
            articleVersion.put("title", contentArticleVersion.getTitle());
            articleVersion.put("folderId", contentArticleVersion.getFolderId());
            articleVersion.put("articleId", contentArticleVersion.getContentArticleId());
            articleVersion.put("content", contentArticleVersion.getContent());
        }

        response.getPortletOutputStream().write(articleVersion.toString().getBytes());
    }

    @ResourceMapping("saveNewArticleVersion")
    public void saveNewArticleVersion(ResourceRequest request, ResourceResponse response,
                                      @RequestParam(required = false) String articleId,
                                      @RequestParam(required = false) String title,
                                      @RequestParam(required = false) String folderId,
                                      @RequestParam(required = false) String content
    )
            throws IOException, SystemException, PortalException {

        ContentArticleVersion contentArticleVersion = new ContentArticleVersion();
        if (articleId == null) {
            contentArticleVersion.setContentArticleId(null);
        } else {
            contentArticleVersion.setContentArticleId(Long.parseLong(articleId));
        }
        contentArticleVersion.setFolderId(Long.parseLong(folderId));
        contentArticleVersion.setTitle(title);
        contentArticleVersion.setContent(content);
        ContentsClient.createContentArticleVersion(contentArticleVersion);

        defaultOperationReturnMessage(true, "Article version created successfully", response);
    }

    private void defaultOperationReturnMessage(boolean success, String message, ResourceResponse response) throws IOException {
        JSONObject articleVersion = JSONFactoryUtil.createJSONObject();
        JSONObject folderNode = JSONFactoryUtil.createJSONObject();
        folderNode.put("success", success);
        folderNode.put("msg", message);
        response.getPortletOutputStream().write(articleVersion.toString().getBytes());
    }

    private JSONObject treeNode(String label, String id, String kind) {
        JSONObject folderNode = JSONFactoryUtil.createJSONObject();
        folderNode.put("label", label);
        folderNode.put("id", id);
        folderNode.put("kind", kind);
        folderNode.put("load_on_demand", "true");
        return folderNode;
    }

    private JSONObject articleNode(String label, String id) {
        return treeNode(label, id, "article");
    }

    private JSONObject folderNode(String label, String id) {
        return treeNode(label, id, "folder");
    }
}
