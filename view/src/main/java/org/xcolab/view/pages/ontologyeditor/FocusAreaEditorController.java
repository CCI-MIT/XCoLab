package org.xcolab.view.pages.ontologyeditor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.errors.AccessDeniedPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FocusAreaEditorController {

    @ModelAttribute("allFocusAreas")
    public List<FocusArea> getAllFocusAreas() {
        return OntologyClientUtil.getAllFocusAreas();
    }

    @ModelAttribute("ontologySpaces")
    public List<OntologySpace> getOntologySpaces() {
        return OntologyClientUtil.getAllOntologySpaces();
    }

    @GetMapping("/ontology-editor/focusAreaEditor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member) {

        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        return "/ontology-editor/focusAreaEditor";
    }

    private void defaultOperationReturnMessage(boolean success, String message,
            HttpServletResponse response) throws IOException {
        JSONObject articleVersion = new JSONObject();
        JSONObject folderNode = new JSONObject();
        folderNode.put("success", success);
        folderNode.put("msg", message);
        response.getOutputStream().write(articleVersion.toString().getBytes());
    }


    @PostMapping("/ontology-editor/saveFocusArea")
    public void saveFocusArea(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long id, @RequestParam(required = false) Integer order,
            @RequestParam(required = false) String name,
            @RequestParam(value = "ontologySpaces[]") String[] ontologySpaces) throws IOException {

        FocusArea focusArea;
        if (id != null && id != 0L) {
            focusArea = OntologyClientUtil.getFocusArea(id);
            focusArea.setName(name);
            focusArea.setOrder_(order);
            OntologyClientUtil.updateFocusArea(focusArea);
        } else {
            focusArea = new FocusArea();
            focusArea.setOrder_(order);
            focusArea.setName(name);
            focusArea = OntologyClientUtil.createFocusArea(focusArea);
        }
        updateFocusAreaOntologyTerms(focusArea, ontologySpaces);

        defaultOperationReturnMessage(true, "Ontology term updated successfully", response);
    }

    private void updateFocusAreaOntologyTerms(FocusArea focusArea, String[] ontologyTerms) {

        OntologyClientUtil.deleteFocusAreaOntologyTerm(focusArea.getId(), null);

        if (ontologyTerms != null) {
            for (String ontId : ontologyTerms) {
                OntologyClientUtil.addOntologyTermsToFocusAreaByOntologyTermId(focusArea.getId(),
                        getOntologyTermId(ontId));
            }
        }

    }

    private Long getOntologyTermId(String node) {

        Long ontologyTermParentId = null;
        if (node != null && !node.isEmpty()) {
            String[] ids = node.split("_");
            ontologyTermParentId = Long.parseLong(ids[1]);
        }
        return ontologyTermParentId;

    }

    @GetMapping("/ontology-editor/focusAreaEditorGetFocusArea")
    public void getFocusArea(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false) Long focusAreaId) throws IOException {
        JSONObject articleVersion = new JSONObject();

        FocusArea focusArea = OntologyClientUtil.getFocusArea(focusAreaId);
        if (focusArea != null) {
            articleVersion.put("id", focusArea.getId());
            articleVersion.put("order", focusArea.getOrder_());
            articleVersion.put("name", focusArea.getName());
            List<OntologyTerm> allTerms =
                    OntologyClientUtil.getOntologyTermsForFocusArea(focusArea);
            JSONArray array = new JSONArray();
            if (allTerms != null) {
                for (OntologyTerm ot : allTerms) {
                    array.put(ot.getOntologySpaceId() + "_" + ot.getId());
                }
            }

            articleVersion.put("ontologySpaces", array);

        }

        response.getOutputStream().write(articleVersion.toString().getBytes());
    }
}
