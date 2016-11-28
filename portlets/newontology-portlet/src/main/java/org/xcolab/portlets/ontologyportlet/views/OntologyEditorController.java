package org.xcolab.portlets.ontologyportlet.views;

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
import org.xcolab.client.contest.OntologyClient;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.members.PermissionsClient;

import java.io.IOException;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class OntologyEditorController {
    private static final Integer THRESHOLD_TO_AVOID_NODE_COLLISION = 1000;

    @RequestMapping
    public String handleRenderRequest(RenderRequest request, RenderResponse response, Model model) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        if(PermissionsClient.canAdminAll(themeDisplay.getUserId())) {
            return "editor";
        }else{
            return "notAllowed";
        }
    }


    @ResourceMapping("ontologyEditorList")
    public void ontologyEditorList(ResourceRequest request, ResourceResponse response,
                                        @RequestParam(required = false) Long ontologySpace,
                                        @RequestParam(required = false) Long ontologyTermParentId)
            throws IOException {

        JSONArray responseArray = JSONFactoryUtil.createJSONArray();
        if (ontologySpace != null ) {
            //
            if(ontologyTermParentId == null && ontologyTermParentId.longValue() == 0l){
                ontologyTermParentId = 0l;
            }
            List<OntologyTerm> ontologyTerms = OntologyClientUtil.getOntologyTerms(ontologyTermParentId, ontologySpace);
            //return ontologyTerms
            
        }else{
            List<OntologySpace> ontologySpaces = OntologyClientUtil.getAllOntologySpaces();
            //return ontology spaces.

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

        response.getPortletOutputStream().write(responseArray.toString().getBytes());

    }

    @ResourceMapping("contentEditorGetLatestArticleVersion")
    public void contentEditorGetLatestArticleVersion(ResourceRequest request, ResourceResponse response,
                                                     @RequestParam(required = false) Long articleId)
            throws IOException, ContentNotFoundException {
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

    @ResourceMapping("createArticleFolder")
    public void createArticleFolder(ResourceRequest request, ResourceResponse response,
                                   @RequestParam(required = false) String folderName,
                                   @RequestParam(required = false) Long parentFolderId) throws IOException{
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Long userId = themeDisplay.getUser().getUserId();

        ContentFolder contentFolder = new ContentFolder();
        contentFolder.setContentFolderName(folderName);
        contentFolder.setParentFolderId(parentFolderId);

        ContentsClient.createContentFolder(contentFolder);

        defaultOperationReturnMessage(true, "Folder created successfully", response);
    }
    @ResourceMapping("moveArticleVersion")
    public void moveArticleVersion(ResourceRequest request, ResourceResponse response,
                                   @RequestParam(required = false) Long articleId,
                                   @RequestParam(required = false) Long folderId)
            throws IOException, ContentNotFoundException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Long userId = themeDisplay.getUser().getUserId();

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

    @ResourceMapping("saveContentArticleVersion")
    public void saveContentArticleVersion(ResourceRequest request, ResourceResponse response,
                                          @RequestParam(required = false) Long articleId,
                                          @RequestParam(required = false) String title,
                                          @RequestParam(required = false) Long folderId,
                                          @RequestParam(required = false) String content
    ) throws IOException {

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
