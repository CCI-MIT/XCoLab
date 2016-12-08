package org.xcolab.portlets.ontologyportlet.views;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;



import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologySpace;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.entity.utils.members.MemberAuthUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class FocusAreaEditorController {

    @ModelAttribute("allFocusAreas")
    public List<FocusArea> getAllFocusAreas() {
        return OntologyClientUtil.getAllFocusAreas();
    }

    @ModelAttribute("ontologySpaces")
    public List<OntologySpace> getOntologySpaces() {
        return OntologyClientUtil.getAllOntologySpaces();
    }

    @RequestMapping(params = "focusAreaEditor=true")
    public String handleRenderRequest(RenderRequest request, RenderResponse response, Model model) {

        long memberId = MemberAuthUtil.getMemberId(request);
        if (PermissionsClient.canAdminAll(memberId)) {
            return "focusAreaEditor";
        } else {
            return "notAllowed";
        }
    }

    private void defaultOperationReturnMessage(boolean success, String message,
            ResourceResponse response) throws IOException {
        JSONObject articleVersion = new JSONObject();
        JSONObject folderNode = new JSONObject();
        folderNode.put("success", success);
        folderNode.put("msg", message);
        response.getPortletOutputStream().write(articleVersion.toString().getBytes());
    }


    @ResourceMapping("saveFocusArea")
    public void saveFocusArea(ResourceRequest request, ResourceResponse response,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Integer order,
            @RequestParam(required = false) String name,
            @RequestParam(value = "ontologySpaces[]")String[] ontologySpaces
    ) throws IOException {


        FocusArea focusArea;
        if (id != null && id != 0l) {
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

        OntologyClientUtil.deleteFocusAreaOntologyTerm(focusArea.getId_(), null);

        if (ontologyTerms != null) {
            for (String ontId : ontologyTerms) {
                OntologyClientUtil.addOntologyTermsToFocusAreaByOntologyTermId(focusArea.getId_(),getOntologyTermId(ontId));
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

    @ResourceMapping("focusAreaEditorGetFocusArea")
    public void getFocusArea(ResourceRequest request,
            ResourceResponse response,
            @RequestParam(required = false) Long focusAreaId)
            throws IOException {
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

        response.getPortletOutputStream().write(articleVersion.toString().getBytes());
    }
}
