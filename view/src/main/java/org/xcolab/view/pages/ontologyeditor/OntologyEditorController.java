package org.xcolab.view.pages.ontologyeditor;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.pojo.wrapper.OntologySpaceWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.errors.AccessDeniedPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class OntologyEditorController {

    private static final Integer THRESHOLD_TO_AVOID_NODE_COLLISION = 1000;

    @Autowired
    private IOntologyClient ontologyClient;
    
    @Autowired
    private IPermissionClient permissionClient;

    @GetMapping("/ontology-editor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member) {

        if (!permissionClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        return "/ontology-editor/ontologyEditor";
    }


    @GetMapping("/ontology-editor/ontologyEditorList")
    public void ontologyEditorList(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) String node) throws IOException {

        Long ontologySpaceId = null;
        Long ontologyTermParentId = null;

        if (node != null && !node.isEmpty()) {
            String[] ids = node.split("_");
            ontologySpaceId = Long.parseLong(ids[0]);
            ontologyTermParentId = Long.parseLong(ids[1]);
        }

        JSONArray responseArray = new JSONArray();
        if (ontologySpaceId != null) {
            //
            List<OntologyTermWrapper> ontologyTerms =
                    ontologyClient.getOntologyTerms(ontologyTermParentId, ontologySpaceId);
            for (OntologyTermWrapper ot : ontologyTerms) {
                responseArray
                        .put(ontologyTermNode(ot.getName(), ot.getOntologySpaceId(), ot.getId()));
            }

        } else {
            List<OntologySpaceWrapper> ontologySpaces = ontologyClient.getAllOntologySpaces();
            for (OntologySpaceWrapper os : ontologySpaces) {
                responseArray.put(ontologySpaceNode(os.getName(), os.getId()));
            }
        }


        response.getOutputStream().write(responseArray.toString().getBytes());
    }

    @GetMapping("/ontology-editor/ontologyEditorGetOntologyTerm")
    public void contentEditorGetLatestArticleVersion(HttpServletRequest request,
            HttpServletResponse response, @RequestParam(required = false) Long ontologyTermId)
            throws IOException {
        JSONObject articleVersion = new JSONObject();

        OntologyTermWrapper ontologyTerm = ontologyClient.getOntologyTerm(ontologyTermId);
        if (ontologyTerm != null) {
            articleVersion.put("id", ontologyTerm.getId());
            articleVersion.put("order", ontologyTerm.getOrder());
            articleVersion.put("descriptionUrl", ontologyTerm.getDescriptionUrl());
            articleVersion.put("name", ontologyTerm.getName());
            articleVersion.put("ontologySpaceId", ontologyTerm.getOntologySpaceId());
            articleVersion.put("parentId", ontologyTerm.getParentId());

        }

        response.getOutputStream().write(articleVersion.toString().getBytes());
    }

    private void deleteOntologyTermAndChildren(Long id) {
        OntologyTermWrapper ot = ontologyClient.getOntologyTerm(id);
        List<OntologyTermWrapper> children = ot.getChildren();
        if (children != null) {
            for (OntologyTermWrapper child : children) {
                deleteOntologyTermAndChildren(child.getId());
            }
        }
        ontologyClient.deleteOntologyTerm(id);
    }

    @PostMapping("/ontology-editor/deleteOntologyTerm")
    public void deleteOntologyTerm(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long id) throws IOException {
        if (id != null && id != 0L) {
            deleteOntologyTermAndChildren(id);
        }
        defaultOperationReturnMessage(true, "Ontology term deleted successfully", response);
    }

    @PostMapping("/ontology-editor/saveOntologyTerm")
    public void saveOntologyTerm(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String descriptionUrl,
            @RequestParam(required = false) Integer order,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long ontologySpaceId,
            @RequestParam(required = false) Long parentId) throws IOException {

        if (id != null && id != 0L) {
            OntologyTermWrapper ontologyTerm = ontologyClient.getOntologyTerm(id);
            ontologyTerm.setDescriptionUrl(descriptionUrl);
            ontologyTerm.setSortOrder(order);
            ontologyTerm.setName(name);

            ontologyClient.updateOntologyTerm(ontologyTerm);
        } else {
            OntologyTermWrapper ontologyTerm = new OntologyTermWrapper();
            ontologyTerm.setOntologySpaceId(ontologySpaceId);
            ontologyTerm.setParentId(parentId);
            ontologyTerm.setDescriptionUrl(descriptionUrl);
            ontologyTerm.setSortOrder(order);
            ontologyTerm.setName(name);
            ontologyClient.createOntologyTerm(ontologyTerm);
        }

        defaultOperationReturnMessage(true, "Ontology term updated successfully", response);
    }

    private void defaultOperationReturnMessage(boolean success, String message,
            HttpServletResponse response) throws IOException {
        JSONObject articleVersion = new JSONObject();
        JSONObject folderNode = new JSONObject();
        folderNode.put("success", success);
        folderNode.put("msg", message);
        response.getOutputStream().write(articleVersion.toString().getBytes());
    }

    private JSONObject treeNode(String label, Long ontologyTermParentId, Long ontologySpaceId,
            String kind, boolean loadOnDemand) {
        JSONObject folderNode = new JSONObject();

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
        return treeNode(label, ontologyTermParentId, (ontologySpaceId), "folder", true);
    }

    private JSONObject ontologySpaceNode(String label, Long ontologySpaceId) {
        return treeNode(label, null, ontologySpaceId, "folder", true);
    }

    private void printOntologyHierarchy() {
        for (OntologyTermWrapper oTerm : ontologyClient.getAllOntologyTerms()) {
            if (oTerm.getParent() == null) {
                printOntologies(ontologyClient.getOntologyTerm(oTerm.getId()), 0);
            } else if (oTerm.getParent().getId() == 0) {
                printOntologies(ontologyClient.getOntologyTerm(oTerm.getId()), 0);
            }
        }
    }

    private void printOntologies(OntologyTermWrapper term, int depth) {
        for (OntologyTermWrapper child : ontologyClient.getChildOntologyTerms(term.getId())) {
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                prefix.append("; ");
            }
            System.out.println(prefix.toString() + child.getId() + "; " + child.getName());
            printOntologies(child, depth + 1);
        }
    }
}
