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

        if (PermissionsClient.canAdminAll(themeDisplay.getUserId())) {
            return "editor";
        } else {
            return "notAllowed";
        }
    }


    @ResourceMapping("ontologyEditorList")
    public void ontologyEditorList(ResourceRequest request, ResourceResponse response,
            @RequestParam(required = false) String node)
            throws IOException {

        Long ontologySpaceId = null;
        Long ontologyTermParentId = null;

        if (node != null && !node.isEmpty()) {
            String[] ids = node.split("_");
            ontologySpaceId = Long.parseLong(ids[0]);
            ontologyTermParentId = Long.parseLong(ids[1]);
        }

        JSONArray responseArray = JSONFactoryUtil.createJSONArray();
        if (ontologySpaceId != null) {
            //
            if (ontologyTermParentId == null) {
                ontologyTermParentId = 0l;
            }
            List<OntologyTerm> ontologyTerms =
                    OntologyClientUtil.getOntologyTerms(ontologyTermParentId, ontologySpaceId);
            for (OntologyTerm ot : ontologyTerms) {
                responseArray
                        .put(ontologyTermNode(ot.getName(), ot.getOntologySpaceId(),
                                ot.getId()));
            }

        } else {
            List<OntologySpace> ontologySpaces = OntologyClientUtil.getAllOntologySpaces();
            for (OntologySpace os : ontologySpaces) {
                responseArray.put(ontologySpaceNode(os.getName(), os.getId()));
            }
        }


        response.getPortletOutputStream().write(responseArray.toString().getBytes());

    }

    @ResourceMapping("ontologyEditorGetOntologyTerm")
    public void contentEditorGetLatestArticleVersion(ResourceRequest request,
            ResourceResponse response,
            @RequestParam(required = false) Long ontologyTermId)
            throws IOException {
        JSONObject articleVersion = JSONFactoryUtil.createJSONObject();

        OntologyTerm ontologyTerm = OntologyClientUtil.getOntologyTerm(ontologyTermId);
        if (ontologyTerm != null) {
            articleVersion.put("id", ontologyTerm.getId());
            articleVersion.put("order", ontologyTerm.getOrder());
            articleVersion.put("descriptionUrl", ontologyTerm.getDescriptionUrl());
            articleVersion.put("name", ontologyTerm.getName());
            articleVersion.put("ontologySpaceId", ontologyTerm.getOntologySpaceId());

        }

        response.getPortletOutputStream().write(articleVersion.toString().getBytes());
    }

    @ResourceMapping("createArticleFolder")
    public void createArticleFolder(ResourceRequest request, ResourceResponse response,
            @RequestParam(required = false) String folderName,
            @RequestParam(required = false) Long parentFolderId) throws IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Long userId = themeDisplay.getUser().getUserId();

        //        ContentFolder contentFolder = new ContentFolder();
        //        contentFolder.setContentFolderName(folderName);
        //        contentFolder.setParentFolderId(parentFolderId);
        //
        //        ContentsClient.createContentFolder(contentFolder);

        defaultOperationReturnMessage(true, "Folder created successfully", response);
    }

    @ResourceMapping("moveArticleVersion")
    public void moveArticleVersion(ResourceRequest request, ResourceResponse response,
            @RequestParam(required = false) Long articleId,
            @RequestParam(required = false) Long folderId)
            throws IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Long userId = themeDisplay.getUser().getUserId();

        //        ContentArticleVersion contentArticleVersion =
        //                ContentsClient.getLatestContentArticleVersion(articleId);
        //        ContentArticleVersion newContentArticleVersion = new ContentArticleVersion();
        //        newContentArticleVersion.setTitle(contentArticleVersion.getTitle());
        //        newContentArticleVersion.setContent(contentArticleVersion.getContent());
        //        newContentArticleVersion.setContentArticleId(contentArticleVersion
        // .getContentArticleId());
        //
        //        newContentArticleVersion.setAuthorId(userId);
        //        newContentArticleVersion.setFolderId(folderId);
        //        ContentsClient.createContentArticleVersion(newContentArticleVersion);

        defaultOperationReturnMessage(true, "Article moved successfully", response);

    }

    @ResourceMapping("saveOntologyTerm")
    public void saveOntologyTerm(ResourceRequest request, ResourceResponse response,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String descriptionUrl,
            @RequestParam(required = false) Integer order,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long ontologySpaceId


    ) throws IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        Long userId = themeDisplay.getUser().getUserId();

        OntologyTerm ontologyTerm = new OntologyTerm();
        ontologyTerm.setId_(id);
        ontologyTerm.setDescriptionUrl(descriptionUrl);
        ontologyTerm.setOrder_(order);
        ontologyTerm.setName(name);
        ontologyTerm.setOntologySpaceId(ontologySpaceId);

        OntologyClientUtil.updateOntologyTerm(ontologyTerm);

        defaultOperationReturnMessage(true, "Ontology term updated successfully", response);
    }

    private void defaultOperationReturnMessage(boolean success, String message,
            ResourceResponse response) throws IOException {
        JSONObject articleVersion = JSONFactoryUtil.createJSONObject();
        JSONObject folderNode = JSONFactoryUtil.createJSONObject();
        folderNode.put("success", success);
        folderNode.put("msg", message);
        response.getPortletOutputStream().write(articleVersion.toString().getBytes());
    }

    private JSONObject treeNode(String label, Long ontologyTermParentId, Long ontologySpaceId,
            String kind, boolean loadOnDemand) {
        JSONObject folderNode = JSONFactoryUtil.createJSONObject();

        folderNode.put("label", label);
        folderNode.put("kind", kind);
        String id = "";

        if (ontologySpaceId != null) {
            id = ontologySpaceId + "_";
        } else {
            id = "0_";
        }
        if (ontologyTermParentId != null) {
            id += ontologyTermParentId;
        } else {
            id += "0";
        }

        folderNode.put("id", id);
        if (loadOnDemand) {
            folderNode.put("load_on_demand", loadOnDemand + "");
        }
        return folderNode;
    }

    private JSONObject ontologyTermNode(String label, Long ontologySpaceId,
            Long ontologyTermParentId) {
        return treeNode(label, ontologyTermParentId, (ontologySpaceId),
                 "folder", true);
    }

    private JSONObject ontologySpaceNode(String label, Long ontologySpaceId) {
        return treeNode(label, null, ontologySpaceId, "folder", true);
    }
}
