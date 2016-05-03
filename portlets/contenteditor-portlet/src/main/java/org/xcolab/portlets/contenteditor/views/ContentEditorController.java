package org.xcolab.portlets.contenteditor.views;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
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
    private static final Integer THRESHOLD_TO_AVOID_NODE_COLLISION = 1000;

    @RequestMapping
    public String handleRenderRequest(RenderRequest request, RenderResponse response, Model model) {
        return "editor";
    }


    @ResourceMapping("contentEditorListFolder")
    public void contentEditorListFolder(ResourceRequest request, ResourceResponse response,
                                        @RequestParam(required = false) String node)
            throws IOException, SystemException, PortalException {

        JSONArray responseArray = JSONFactoryUtil.createJSONArray();
        Long folderId = 0L;
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
                responseArray.put(articleNode(ca.getTitle(), ca.getContentArticleId()));
            }
        }

        response.getPortletOutputStream().write(responseArray.toString().getBytes());

    }

    @ResourceMapping("contentEditorGetLatestArticleVersion")
    public void contentEditorGetLatestArticleVersion(ResourceRequest request, ResourceResponse response,
                                                     @RequestParam(required = false) Long articleId)
            throws IOException, SystemException, PortalException, ContentNotFoundException {
        JSONObject articleVersion = JSONFactoryUtil.createJSONObject();

        ContentArticleVersion contentArticleVersion = ContentsClient.getLatestContentArticleVersion(articleId);
        if (contentArticleVersion != null) {
            articleVersion.put("title", contentArticleVersion.getTitle());
            articleVersion.put("folderId", contentArticleVersion.getFolderId());
            articleVersion.put("articleId", contentArticleVersion.getContentArticleId());
            articleVersion.put("content", contentArticleVersion.getContent());
        }

        response.getPortletOutputStream().write(articleVersion.toString().getBytes());
    }

    @ResourceMapping("moveArticleVersion")
    public void moveArticleVersion(ResourceRequest request, ResourceResponse response,
                                   @RequestParam(required = false) Long articleId,
                                   @RequestParam(required = false) Long folderId)
            throws IOException, SystemException, PortalException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Long userId = themeDisplay.getUser().getUserId();

        ContentArticleVersion contentArticleVersion = ContentsClient.getLatestContentArticleVersionByContentArticleId(articleId);
        ContentArticleVersion newContentArticleVersion = new ContentArticleVersion();
        newContentArticleVersion.setTitle(contentArticleVersion.getTitle());
        newContentArticleVersion.setContent(contentArticleVersion.getContent());
        newContentArticleVersion.setContentArticleId(contentArticleVersion.getContentArticleId());

        newContentArticleVersion.setAuthorId(userId);
        newContentArticleVersion.setFolderId(folderId);
        ContentsClient.createContentArticleVersion(newContentArticleVersion);

        defaultOperationReturnMessage(true, "Article moved successfully", response);

    }

    @ResourceMapping("saveContentArticleVersion")
    public void saveContentArticleVersion(ResourceRequest request, ResourceResponse response,
                                          @RequestParam(required = false) Long articleId,
                                          @RequestParam(required = false) String title,
                                          @RequestParam(required = false) Long folderId,
                                          @RequestParam(required = false) String content
    )
            throws IOException, SystemException, PortalException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Long userId = themeDisplay.getUser().getUserId();

        ContentArticleVersion contentArticleVersion = new ContentArticleVersion();

        contentArticleVersion.setContentArticleId(articleId);

        contentArticleVersion.setAuthorId(userId);
        contentArticleVersion.setFolderId((folderId));
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

    private JSONObject treeNode(String label, String id, String kind, boolean loadOnDemand) {
        JSONObject folderNode = JSONFactoryUtil.createJSONObject();
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
