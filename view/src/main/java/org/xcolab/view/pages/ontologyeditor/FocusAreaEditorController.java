package org.xcolab.view.pages.ontologyeditor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.pojo.wrapper.FocusAreaWrapper;
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
public class FocusAreaEditorController {

    @Autowired
    private IOntologyClient ontologyClient;

    @Autowired
    private IPermissionClient permissionClient;

    @ModelAttribute("allFocusAreas")
    public List<FocusAreaWrapper> getAllFocusAreas() {
        return ontologyClient.getAllFocusAreas();
    }

    @ModelAttribute("ontologySpaces")
    public List<OntologySpaceWrapper> getOntologySpaces() {
        return ontologyClient.getAllOntologySpaces();
    }

    @GetMapping("/ontology-editor/focusAreaEditor")
    public String handleRenderRequest(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member) {

        if (!permissionClient.canAdminAll(member)) {
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

        FocusAreaWrapper focusArea;
        if (id != null && id != 0L) {
            focusArea = ontologyClient.getFocusArea(id);
            focusArea.setName(name);
            focusArea.setSortOrder(order);
            ontologyClient.updateFocusArea(focusArea);
        } else {
            focusArea = new FocusAreaWrapper();
            focusArea.setSortOrder(order);
            focusArea.setName(name);
            focusArea = ontologyClient.createFocusArea(focusArea);
        }
        updateFocusAreaOntologyTerms(focusArea, ontologySpaces);

        defaultOperationReturnMessage(true, "Ontology term updated successfully", response);
    }

    private void updateFocusAreaOntologyTerms(FocusAreaWrapper focusArea, String[] ontologyTerms) {
        ontologyClient.deleteFocusAreaOntologyTerm(focusArea.getId(), null);

        if (ontologyTerms != null) {
            for (String ontId : ontologyTerms) {
                ontologyClient.addOntologyTermsToFocusAreaByOntologyTermId(focusArea.getId(),
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

        FocusAreaWrapper focusArea = ontologyClient.getFocusArea(focusAreaId);
        if (focusArea != null) {
            articleVersion.put("id", focusArea.getId());
            articleVersion.put("order", focusArea.getSortOrder());
            articleVersion.put("name", focusArea.getName());
            List<OntologyTermWrapper> allTerms =
                    ontologyClient.getOntologyTermsForFocusArea(focusArea);
            JSONArray array = new JSONArray();
            if (allTerms != null) {
                for (OntologyTermWrapper ot : allTerms) {
                    array.put(ot.getOntologySpaceId() + "_" + ot.getId());
                }
            }

            articleVersion.put("ontologySpaces", array);
        }

        response.getOutputStream().write(articleVersion.toString().getBytes());
    }
}
